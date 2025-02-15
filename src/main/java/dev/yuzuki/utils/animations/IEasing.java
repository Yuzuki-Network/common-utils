package dev.yuzuki.utils.animations;

/**
 * Easing interface for animations
 */
public interface IEasing {
    /**
     * Ease the value
     * @param v Value to ease
     * @return Eased value
     */
    double ease(Double v);
}
