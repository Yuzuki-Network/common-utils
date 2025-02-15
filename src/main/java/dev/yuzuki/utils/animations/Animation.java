package dev.yuzuki.utils.animations;

/**
 * Animation interface for easing
 */
public class Animation {
    private IEasing easing;
    private long startMs;
    private double targetValue = Double.NaN, startValue = Double.NaN, tempValue;

    /**
     * Create a new instance of the Animation
     * Default easing is LINEAR
     */
    public Animation() {
        this.easing = Easing.LINEAR;
    }

    /**
     * Create a new instance of the Animation
     * @param easing Easing function
     */
    public Animation(IEasing easing) {
        this.easing = easing;
    }

    /**
     * Get the current value of the animation
     * @param durationMs Duration of the animation in milliseconds
     * @param endValue End value of the animation
     * @return Current value of the animation
     */
    public double get(long durationMs, double endValue) {
        if (Double.isNaN(targetValue) || targetValue != endValue) {
            this.targetValue = endValue;
            this.startMs = System.currentTimeMillis();
            if (Double.isNaN(startValue)) {
                this.startValue = 0;
            } else {
                this.startValue = tempValue;
            }
        }

        long elapsedMs = System.currentTimeMillis() - startMs;

        if (elapsedMs >= durationMs) {
            return (float) endValue;
        }

        this.tempValue = easing.ease((double) elapsedMs / durationMs) * (endValue - startValue) + startValue;

        return tempValue;
    }

    /**
     * Get the current value of the animation
     * @param easing Easing function
     */
    public void setEasing(IEasing easing) {
        this.easing = easing;
    }
}
