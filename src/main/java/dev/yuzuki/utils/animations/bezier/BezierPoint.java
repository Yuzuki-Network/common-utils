package dev.yuzuki.utils.animations.bezier;

/**
 * Bézier point for the Bézier curve
 * @see <a href="https://javascript.info/bezier-curve">Javascript.info Bezier curve</a>
 */
public class BezierPoint {
    private double x, x1, x2;
    private final double y, y1, y2;

    /**
     * Create a new Bézier point
     * @param x1 left side of the control point. Must be between 0 and 1. Also, must be less than x
     * @param y1 left side of the control point. Must be between 0 and 1
     * @param x middle point. Must be between 0 and 1  Also, must be greater than x1 and less than x2
     * @param y middle point. Must be between 0 and 1
     * @param x2 right side of the control point. Must be between 0 and 1  Also, must be greater than x
     * @param y2 right side of the control point. Must be between 0 and 1
     */
    public BezierPoint(double x1, double y1, double x, double y, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Fix the point to be within the bounds of the previous and next points
     * @param previous Previous point in the Bézier curve. should not be null
     * @param next Next point in the Bézier curve. should not be null
     */
    public void fixPoint(BezierPoint previous, BezierPoint next) {
        if (previous == null || next == null) {
            return;
        }

        if (previous.getX() > this.x1) {
            this.x1 = previous.getX();
        }

        if (previous.getX() > this.x) {
            this.x = previous.getX();
        }

        if (next.getX() < this.x2) {
            this.x2 = next.getX();
        }

        if (next.getX() < this.x) {
            this.x = next.getX();
        }
    }

    /**
     * Get the x value of the point
     * @return x value. it should be between 0 and 1
     */
    public double getX() {
        return x;
    }

    /**
     * Get the y value of the point
     * @return y value. it should be between 0 and 1
     */
    public double getY() {
        return y;
    }

    /**
     * Get the x1 value of the point. This is a left side of the control point
     * @return x1 value. it should be between 0 and 1
     */
    public double getX1() {
        return x1;
    }

    /**
     * Get the y1 value of the point. This is a left side of the control point
     * @return y1 value. it should be between 0 and 1
     */
    public double getY1() {
        return y1;
    }

    /**
     * Get the x2 value of the point. This is a right side of the control point
     * @return x2 value. it should be between 0 and 1
     */
    public double getX2() {
        return x2;
    }

    /**
     * Get the y2 value of the point. This is a right side of the control point
     * @return y2 value. it should be between 0 and 1
     */
    public double getY2() {
        return y2;
    }
}
