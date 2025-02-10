package dev.yuzuki.utils.animations;

/**
 *  <H1>BezierEasingBuilder</H1>
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
 *          .build();
 *      }
 * </pre>
 * or
 *
 * <pre>
 *     {@code
 *     BezierEasing bezierEasing = new BezierEasing(0.25, 0.1, 0.25, 1.0);
 *     }
 * </pre>
 *
 * This class is same as the cubic-bezier in CSS. <br>
 * If you need some document, see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/easing-function">MDN Web Docs</a>
 */
public class BezierEasing implements IEasing {
    private final double x1, y1, x2, y2;

    public BezierEasing(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    private double cubicBezier(double t, double x1, double y1, double x2, double y2) {
        double u = 1 - t;
        double tt = t * t;
        double uu = u * u;
        double uuu = uu * u;
        double ttt = tt * t;

        double p = uuu * 0;
        p += 3 * uu * t * y1;
        p += 3 * u * tt * y2;
        p += ttt * 1;

        return p;
    }

    @Override
    public double ease(Double v) {
        return cubicBezier(v, x1, y1, x2, y2);
    }

    public static Builder Builder() {
        return new Builder();
    }

    /**
     * Builder class for {@link BezierEasing}
     */
    public static class Builder {
        private double x1 = -999, y1 = -999, x2 = -999, y2 = -999;

        public Builder x1(double x1) {
            if (x1 <= 0 || x1 >= 1)
                throw new IllegalArgumentException("x1 must be between 0 and 1");
            this.x1 = x1;
            return this;
        }

        public Builder y1(double y1) {
            if (y1 <= 0 || y1 >= 1)
                throw new IllegalArgumentException("y1 must be between 0 and 1");
            this.y1 = y1;
            return this;
        }

        public Builder x2(double x2) {
            if (x2 <= 0 || x2 >= 1)
                throw new IllegalArgumentException("x2 must be between 0 and 1");
            this.x2 = x2;
            return this;
        }

        public Builder y2(double y2) {
            if (y2 <= 0 || y2 >= 1)
                throw new IllegalArgumentException("y2 must be between 0 and 1");
            this.y2 = y2;
            return this;
        }

        public BezierEasing build() {
            if (x1 == -999 || y1 == -999 || x2 == -999 || y2 == -999) {
                throw new IllegalArgumentException("Missing values");
            }

            return new BezierEasing(x1, y1, x2, y2);
        }
    }
}