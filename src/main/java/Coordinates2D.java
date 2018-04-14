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
