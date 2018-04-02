import java.util.Optional;

/*
This class describes a 2D set of coordinates and provides helpful methods for dealing with their geometry
 */
public class Coordinates2D {
    private double x;
    private double y;
    private double xErr;
    private double yErr;

    /**
     * Basic constructor for Coordinates2D class
     * @param x The x coordinate
     * @param y The y coordinate
     * @param xErr The error on the x coordinate. We can use as one of the axes on the error circle/ellipse
     * @param yErr The error on the y coordinate. We can use as the other axis on the error circle/ellipse
     *
     */
    public Coordinates2D(double x, double y, double xErr, double yErr) {
        this.x = x;
        this.y = y;
        this.xErr = xErr;
        this.yErr = yErr;
    }

    /*
    Helpful functionality methods
     */

    /**
     * Calculate the distance between two coordinate points.
     * @param b The Coordinates2D object to compare to
     * @return The distance between the centers of the circles given by the two Coordinates2D objects.
     *
     */
    public Optional<Double> distance(Coordinates2D b) {
        if(b == null) {
            //Can't do distance to a nonexistent point
            return Optional.empty();
        }
        //Distance formula
        //sqrt(x2-x1)^2 - (y2-y1)^2
        return Optional.of(Math.sqrt(Math.pow(b.getX() - this.x, 2) + Math.pow(b.getY() - this.y, 2)));
    }

    /**
     * Calculate the midpoint of the line formed by the intersections of two coordinate circles.
     * Circle radii are from the average of the x and y errors. This could be changed later to allow ellipse-shaped error regions.
     *
     * @param b The second circle to compare to.
     * @return A Coordinates2D object. If there was no intersection, the x and y coordinates will be Double.NaN
     *
     */
    public Optional<Coordinates2D> circleIntersection(Coordinates2D b) {
        if(b == null) {
            //Can't compute intersection with a null circle. And we can't make b an optional parameter (optional parameters not allowed)
            return Optional.empty();
        }
        //Circle intersection calculated using https://math.stackexchange.com/a/1367732
        //Code adapted from https://gist.github.com/jupdike/bfe5eb23d1c395d8a0a1a4ddd94882ac
        double aRadius = (this.xErr + this.yErr) / 2;
        double bRadius = (b.getXErr() + b.getYErr()) / 2;

        //Analogous to variable R in gist code
        Optional<Double> optCenterDistance = distance(b);


        if(optCenterDistance.isPresent()) {
            double centerDistance = optCenterDistance.get();
            if(!(Math.abs(aRadius - bRadius) <= centerDistance && centerDistance <= aRadius + bRadius)) {
                //Return a coordinates object with NaN,NaN
                return Optional.empty();
            }

            //Set up some variables we need for the calculation
            double r2 = Math.pow(centerDistance, 2);
            double a = (Math.pow(aRadius, 2) - Math.pow(bRadius, 2)) / (2 * r2);
            //aRadius^2 - bRadius^2. Analogous to r2r2 from gist code
            double aR2bR2 = Math.pow(aRadius, 2) - Math.pow(bRadius, 2);
            double c = Math.sqrt((2 * Math.pow(aRadius, 2) + Math.pow(bRadius, 2)) / r2 - aR2bR2 / Math.pow(r2, 2) - 1);

            double fx = (this.x + b.getX()) / 2 + a * (b.getX() - this.x);
            double gx = c * (b.getY() - this.y) / 2;
            double ix1 = fx + gx;
            double ix2 = fx - gx;

            double fy = (this.y + b.getY()) / 2 + a * (b.getY() - this.y);
            double gy = c * (this.x - b.getX()) / 2;
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
     * Get a string describing the coordinate.
     * @return A string describing the coordinate
     */
    @Override
    public String toString() {
        return String.format("Coordinate centered on (%.4f, %.4f) with xErr %.4f and yErr %.4f", x, y, xErr, yErr);
    }

    @Override
    public boolean equals(Object b) {
        if(b == null) {
            return false;
        }

        if(b.getClass() != this.getClass()) {
            return false;
        }

        Coordinates2D other = (Coordinates2D)b;
        return other.getX() == this.x && other.getY() == this.y && other.getXErr() == this.xErr && other.getYErr() == this.yErr;
    }




    /*
    Accessor/mutator methods
     */

    /**
     * Get the value of x coordinate.
     * @return The x coordinate

     */
    public double getX() {
        return x;
    }

    /**
     * Set the value of the x coordinate to the given value.
     * @param x The value to set the x coordinate to

     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get the value of y coordinate
     * @return The y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Set the value of the y coordinate to the given value.
     * @param y The value to set the y coordinate to
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get the x error of the coordinate.
     * We can use this error to represent the radius of a circle centered on the x and y coordinates.
     * @return The x error value
     */
    public double getXErr() {
        return xErr;
    }

    /**
     * Set the value of the x error to the given value.
     * @param xErr The value to set the x error to
     */
    public void setXErr(double xErr) {
        this.xErr = xErr;
    }

    /**
     * Get the y error of the coordinate.
     * @return The y error value
     */
    public double getYErr() {
        return yErr;
    }

    /**
     * Set the value of the y error to the given value.
     * @param yErr The value to set the y error to
     */
    public void setYErr(double yErr) {
        this.yErr = yErr;
    }
}
