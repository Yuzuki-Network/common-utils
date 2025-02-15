package dev.yuzuki.utils.animations.bezier;

import dev.yuzuki.utils.animations.IEasing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * BezierEasingBuilder
 * This class is used to create a custom easing function using the cubic bezier algorithm. <br>
 * Implementing with the {@link IEasing} interface. <br>
 * Usage example
 * <pre>
 *     {@code
 *      BezierEasing.Builder()
 *          .x1(0.25)
 *          .y1(0.1)
 *          .x2(0.25)
 *          .y2(1.0)
 *          .point(new BezierPoint(0.5, 0.5, 0.5, 0.5, 0.5, 0.5)) // additionally you can add custom points
 *          .build();
 *      }
 * </pre>
 * or
 *
 * <pre>
 *     {@code
 *     BezierEasing bezierEasing = new BezierEasing(0.25, 0.1, 0.25, 1.0);
 *     BezierEasing bezierEasing = new BezierEasing(0.25, 0.1, 0.25, 1.0, new BezierPoint(0.5, 0.5, 0.5, 0.5, 0.5, 0.5));
 *     }
 * </pre>
 *
 * This class is same as the cubic-bezier in CSS. <br>
 * If you need some document, see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/easing-function">MDN Web Docs</a>
 */
public class BezierEasing implements IEasing {
    private final List<BezierPoint> points = new ArrayList<>();

    /**
     * Create a new instance of the BezierEasing
     * @param x1 control point 1 of start point. Must be between 0 and 1
     * @param y1 control point 1 of start point. Must be between 0 and 1
     * @param x2 control point 2 of end point. Must be between 0 and 1
     * @param y2 control point 2 of end point. Must be between 0 and 1
     */
    public BezierEasing(double x1, double y1, double x2, double y2) {
        this(Arrays.asList(new BezierPoint(0, 0, 0, 0, x1, y1), new BezierPoint(x2, y2, 1, 1, 0, 0)));
    }

    /**
     * Create a new instance of the BezierEasing
     * @param x1 control point 1 of start point. Must be between 0 and 1
     * @param y1 control point 1 of start point. Must be between 0 and 1
     * @param x2 control point 2 of end point. Must be between 0 and 1
     * @param y2 control point 2 of end point. Must be between 0 and 1
     * @param points BezierPoint array
     */
    public BezierEasing(double x1, double y1, double x2, double y2, BezierPoint... points) {
        this(Arrays.asList(new BezierPoint(0, 0, 0, 0, x1, y1), new BezierPoint(x2, y2, 1, 1, 0, 0)));
        this.points.addAll(Arrays.asList(points));
    }

    /**
     * Create a new instance of the BezierEasing. For internal use
     * @param points BezierPoint array
     */
    private BezierEasing(List<BezierPoint> points) {
        this.points.addAll(points);
        this.points.sort(Comparator.comparingDouble(BezierPoint::getX));
    }

    @Override
    public double ease(Double v) {
        for (int i = 0; i < points.size() - 1; i++) {
            BezierPoint p1 = points.get(i);
            BezierPoint p2 = points.get(i + 1);
            if (v >= p1.getX() && v < p2.getX()) {
                double t = (v - p1.getX()) / (p2.getX() - p1.getX());
                return cubicBezier(t, p1.getX(), p1.getY(), p1.getX2(), p1.getY2(), p2.getX1(), p2.getY1(), p2.getX(), p2.getY());
            }
        }
        return v;
    }

    /**
     * Cubic bezier algorithm
     * @param t time. Must be between 0 and 1
     * @param x0 X of the start point
     * @param y0 Y of the start point
     * @param x1 X of the control point 1
     * @param y1 Y of the control point 1
     * @param x2 X of the control point 2
     * @param y2 Y of the control point 2
     * @param x3 X of the end point
     * @param y3 Y of the end point
     * @return calculated value
     *
     */
    private double cubicBezier(double t, double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.pow(1 - t, 3) * y0 + 3 * Math.pow(1 - t, 2) * t * y1 + 3 * (1 - t) * Math.pow(t, 2) * y2 + Math.pow(t, 3) * y3;
    }

    /**
     * Create a new instance of the BezierEasing.Builder
     * @return BezierEasing.Builder
     */
    public static Builder Builder() {
        return new Builder();
    }

    /**
     * Builder class for {@link BezierEasing}
     */
    public static class Builder {
        private double x1 = -999, y1 = -999, x2 = -999, y2 = -999;
        private final List<BezierPoint> points = new ArrayList<>();

        /**
         * Set the x1 value of the cubic bezier
         * @param x1 right side vector of the control point 1. Must be between 0 and 1
         * @return BezierEasing.Builder
         */
        public Builder x1(double x1) {
            if (x1 < 0 || x1 > 1)
                throw new IllegalArgumentException("x1 must be between 0 and 1");
            this.x1 = x1;
            return this;
        }

        /**
         * Set the y1 value of the cubic bezier
         * @param y1 right side vector of the control point 1. Must be between 0 and 1
         * @return BezierEasing.Builder
         */
        public Builder y1(double y1) {
            if (y1 < 0 || y1 > 1)
                throw new IllegalArgumentException("y1 must be between 0 and 1");
            this.y1 = y1;
            return this;
        }

        /**
         * Set the x2 value of the cubic bezier
         * @param x2 left side vector of the control point 2. Must be between 0 and 1
         * @return BezierEasing.Builder
         */
        public Builder x2(double x2) {
            if (x2 < 0 || x2 > 1)
                throw new IllegalArgumentException("x2 must be between 0 and 1");
            this.x2 = x2;
            return this;
        }

        /**
         * Set the y2 value of the cubic bezier
         * @param y2 left side vector of the control point 2. Must be between 0 and 1
         * @return BezierEasing.Builder
         */
        public Builder y2(double y2) {
            if (y2 < 0 || y2 > 1)
                throw new IllegalArgumentException("y2 must be between 0 and 1");
            this.y2 = y2;
            return this;
        }

        /**
         * Set the points of the cubic bezier
         * @param points BezierPoint array
         * @return BezierEasing.Builder
         */
        public Builder points(BezierPoint... points) {
            this.points.addAll(Arrays.asList(points));
            return this;
        }

        /**
         * Set the point of the cubic bezier
         * @param point BezierPoint
         * @return BezierEasing.Builder
         */
        public Builder point(BezierPoint point) {
            this.points.add(point);
            return this;
        }

        /**
         * Build the cubic bezier easing function
         * @return BezierEasing
         */
        public BezierEasing build() {
            if (x1 == -999 || y1 == -999 || x2 == -999 || y2 == -999) {
                throw new IllegalArgumentException("Missing values");
            }

            return new BezierEasing(x1, y1, x2, y2, points.toArray(new BezierPoint[0]));
        }
    }
}