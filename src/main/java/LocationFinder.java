import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import java.util.Optional;

public class LocationFinder {
    /**
     * Calculate the midpoint of the line formed by the intersections of two coordinate circles.
     * Circle radii are from the average of the x and y errors. This could be changed later to allow ellipse-shaped error regions.
     *
     * @param a The first circle in the comparison
     * @param b The second circle in the comparison
     * @return An optional of Coordinates2D, empty if no intersection was found
     *
     */
    public Optional<Coordinates2D> circleIntersection(Coordinates2D a, Coordinates2D b) {
        if(b == null) {
            //Can't compute intersection with a null circle. And we can't make b an optional parameter (optional parameters not allowed)
            return Optional.empty();
        }
        //Circle intersection calculated using https://math.stackexchange.com/a/1367732
        //Code adapted from https://gist.github.com/jupdike/bfe5eb23d1c395d8a0a1a4ddd94882ac
        double aRadius = (a.getXErr() + a.getYErr()) / 2;
        double bRadius = (b.getXErr() + b.getYErr()) / 2;

        //Analogous to variable R in gist code
        Optional<Double> optCenterDistance = a.distance(b);


        if(optCenterDistance.isPresent()) {
            double centerDistance = optCenterDistance.get();
            if(!(Math.abs(aRadius - bRadius) <= centerDistance && centerDistance <= aRadius + bRadius)) {
                //Return a coordinates object with NaN,NaN
                return Optional.empty();
            }

            //Set up some variables we need for the calculation
            double r2 = Math.pow(centerDistance, 2);
            double expA = (Math.pow(aRadius, 2) - Math.pow(bRadius, 2)) / (2 * r2);
            //aRadius^2 - bRadius^2. Analogous to r2r2 from gist code
            double aR2bR2 = Math.pow(aRadius, 2) - Math.pow(bRadius, 2);
            double c = Math.sqrt((2 * Math.pow(aRadius, 2) + Math.pow(bRadius, 2)) / r2 - aR2bR2 / Math.pow(r2, 2) - 1);

            double fx = (a.getX() + b.getX()) / 2 + expA * (b.getX() - a.getX());
            double gx = c * (b.getY() - a.getY()) / 2;
            double ix1 = fx + gx;
            double ix2 = fx - gx;

            double fy = (a.getY() + b.getY()) / 2 + expA * (b.getY() - a.getY());
            double gy = c * (a.getX() - b.getX()) / 2;
            double iy1 = fy + gy;
            double iy2 = fy - gy;

            //The two intersection points are now (ix1, iy1) and (ix2, iy2). We calculate the midpoint of them.
            double vX = (ix1 + ix2) / 2;
            double vY = (iy1 + iy2) / 2;

            return Optional.of(new Coordinates2D(vX, vY, 0,0));

        } else {
            return Optional.empty();
        }


    }

    /**
     *
     * Calculate the intersection of three or more beacon circles using the trilateration/least squares optimizer
     *
     * @param coords The array of Coordinates2D objects to calculate the intersection on
     * @return The best intersection the solver can generate, note that this may be quite far off if there is not an actual intersection
     */
    public Coordinates2D multipleIntersection(Coordinates2D[] coords) {
        //Separate the coords into positions and distances
        double[][] positions = new double[coords.length][2];
        double[] distances = new double[coords.length];

        for(int i=0; i<coords.length; i++) {
            positions[i][0] = coords[i].getX();
            positions[i][1] = coords[i].getY();

            distances[i] = coords[i].getXErr();
        }

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();
        double[] centroid = optimum.getPoint().toArray();
        double[] stdDev = optimum.getSigma(0).toArray();

        return new Coordinates2D(centroid[0], centroid[1], stdDev[0]*1.96, stdDev[1]*1.96);

    }
}
