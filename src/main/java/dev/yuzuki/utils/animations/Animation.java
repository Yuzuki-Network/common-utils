package dev.yuzuki.utils.animations;

public class Animation {
    private IEasing easing;
    private long startMs;
    private double targetValue = Double.NaN, startValue = Double.NaN, tempValue;

    public Animation() {
        this.easing = Easing.LINEAR;
    }

    public Animation(IEasing easing) {
        this.easing = easing;
    }

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

    public void setEasing(IEasing easing) {
        this.easing = easing;
    }
}
