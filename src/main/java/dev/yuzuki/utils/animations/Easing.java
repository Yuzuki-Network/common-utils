package dev.yuzuki.utils.animations;

import java.util.function.Consumer;

/**
 * Easing functions for animations
 * @see <a href="https://easings.net/">Easing functions</a>
 */
public enum Easing implements IEasing {
    /**
     * Linear easing
     */
    LINEAR(Double::floatValue),

    /**
     * Ease in sine easing @see <a href="https://easings.net/#easeInSine">Ease in sine</a>
     */
    EASE_IN_SINE(v -> (1 - Math.cos((v * Math.PI) / 2))),

    /**
     * Ease out sine easing @see <a href="https://easings.net/#easeOutSine">Ease out sine</a>
     */
    EASE_OUT_SINE(v -> Math.sin((v * Math.PI) / 2)),

    /**
     * Ease in out sine easing @see <a href="https://easings.net/#easeInOutSine">Ease in out sine</a>
     */
    EASE_IN_OUT_SINE(v -> (-(Math.cos(Math.PI * v) - 1) / 2)),

    /**
     * Ease in quad easing @see <a href="https://easings.net/#easeInQuad">Ease in quad</a>
     */
    EASE_IN_QUAD(v -> (float) (v * v)),

    /**
     * Ease out quad easing @see <a href="https://easings.net/#easeOutQuad">Ease out quad</a>
     */
    EASE_OUT_QUAD(v -> (float) (1 - (1 - v) * (1 - v))),

    /**
     * Ease in out quad easing @see <a href="https://easings.net/#easeInOutQuad">Ease in out quad</a>
     */
    EASE_IN_OUT_QUAD(v -> v < 0.5 ? (float) (2 * v * v) : (float) (1 - Math.pow(-2 * v + 2, 2) / 2)),

    /**
     * Ease in cubic easing @see <a href="https://easings.net/#easeInCubic">Ease in cubic</a>
     */
    EASE_IN_CUBIC(v -> (float) (v * v * v)),

    /**
     * Ease out cubic easing @see <a href="https://easings.net/#easeOutCubic">Ease out cubic</a>
     */
    EASE_OUT_CUBIC(v -> (float) (1 - Math.pow(1 - v, 3))),

    /**
     * Ease in out cubic easing @see <a href="https://easings.net/#easeInOutCubic">Ease in out cubic</a>
     */
    EASE_IN_OUT_CUBIC(v -> v < 0.5 ? (float) (4 * v * v * v) : (float) (1 - Math.pow(-2 * v + 2, 3) / 2)),

    /**
     * Ease in quart easing @see <a href="https://easings.net/#easeInQuart">Ease in quart</a>
     */
    EASE_IN_QUART(v -> (float) (v * v * v * v)),

    /**
     * Ease out quart easing @see <a href="https://easings.net/#easeOutQuart">Ease out quart</a>
     */
    EASE_OUT_QUART(v -> (float) (1 - Math.pow(1 - v, 4))),

    /**
     * Ease in out quart easing @see <a href="https://easings.net/#easeInOutQuart">Ease in out quart</a>
     */
    EASE_IN_OUT_QUART(v -> v < 0.5 ? (float) (8 * v * v * v * v) : (float) (1 - Math.pow(-2 * v + 2, 4) / 2)),

    /**
     * Ease in quint easing @see <a href="https://easings.net/#easeInQuint">Ease in quint</a>
     */
    EASE_IN_QUINT(v -> (float) (v * v * v * v * v)),

    /**
     * Ease out quint easing @see <a href="https://easings.net/#easeOutQuint">Ease out quint</a>
     */
    EASE_OUT_QUINT(v -> (float) (1 - Math.pow(1 - v, 5))),

    /**
     * Ease in out quint easing @see <a href="https://easings.net/#easeInOutQuint">Ease in out quint</a>
     */
    EASE_IN_OUT_QUINT(v -> v < 0.5 ? (float) (16 * v * v * v * v * v) : (float) (1 - Math.pow(-2 * v + 2, 5) / 2)),

    /**
     * Ease in expo easing @see <a href="https://easings.net/#easeInExpo">Ease in expo</a>
     */
    EASE_IN_EXPO(v -> v == 0 ? 0 : (float) Math.pow(2, 10 * v - 10)),

    /**
     * Ease out expo easing @see <a href="https://easings.net/#easeOutExpo">Ease out expo</a>
     */
    EASE_OUT_EXPO(v -> v == 1 ? 1 : (float) (1 - Math.pow(2, -10 * v))),

    /**
     * Ease in out expo easing @see <a href="https://easings.net/#easeInOutExpo">Ease in out expo</a>
     */
    EASE_IN_OUT_EXPO(v -> v == 0 ? 0 : v == 1 ? 1 : v < 0.5 ? (float) Math.pow(2, 20 * v - 10) / 2 : (float) (2 - Math.pow(2, -20 * v + 10) / 2)),

    /**
     * Ease in circ easing @see <a href="https://easings.net/#easeInCirc">Ease in circ</a>
     */
    EASE_IN_CIRC(v -> (float) (1 - Math.sqrt(1 - Math.pow(v, 2)))),

    /**
     * Ease out circ easing @see <a href="https://easings.net/#easeOutCirc">Ease out circ</a>
     */
    EASE_OUT_CIRC(v -> (float) Math.sqrt(1 - Math.pow(v - 1, 2))),

    /**
     * Ease in out circ easing @see <a href="https://easings.net/#easeInOutCirc">Ease in out circ</a>
     */
    EASE_IN_OUT_CIRC(v -> v < 0.5 ? (float) ((1 - Math.sqrt(1 - Math.pow(2 * v, 2))) / 2) : (float) ((Math.sqrt(1 - Math.pow(-2 * v + 2, 2)) + 1) / 2)),

    /**
     * Ease in back easing @see <a href="https://easings.net/#easeInBack">Ease in back</a>
     */
    EASE_IN_BACK(v -> (float) (v * v * (2.70158 * v - 1.70158))),

    /**
     * Ease out back easing @see <a href="https://easings.net/#easeOutBack">Ease out back</a>
     */
    EASE_OUT_BACK(v -> (float) ((float) 3.70158 * Math.pow(v - 1, 3) + 1.70158 * Math.pow(v - 1, 2))),

    /**
     * Ease in out back easing @see <a href="https://easings.net/#easeInOutBack">Ease in out back</a>
     */
    EASE_IN_OUT_BACK(v -> v < 0.5 ? (float) (Math.pow(2 * v, 2) * ((2.5949095 + 1) * 2 * v - 2.5949095)) / 2 : (float) (Math.pow(2 * v - 2, 2) * ((2.5949095 + 1) * (v * 2 - 2) + 2.5949095) + 2) / 2),

    /**
     * Ease in elastic easing @see <a href="https://easings.net/#easeInElastic">Ease in elastic</a>
     */
    EASE_IN_ELASTIC(v -> v == 0 ? 0 : (float) (v == 1 ? 1 : -Math.pow(2, 10 * v - 10) * Math.sin((v * 10 - 10.75) * (2 * Math.PI) / 3))),

    /**
     * Ease out elastic easing @see <a href="https://easings.net/#easeOutElastic">Ease out elastic</a>
     */
    EASE_OUT_ELASTIC(v -> v == 0 ? 0 : (float) (v == 1 ? 1 : Math.pow(2, -10 * v) + Math.sin((v * 10 - 0.75) * (2 * Math.PI) / 3))),

    /**
     * Ease in out elastic easing @see <a href="https://easings.net/#easeInOutElastic">Ease in out elastic</a>
     */
    EASE_IN_OUT_ELASTIC(v -> v == 0 ? 0 : (float) (v == 1 ? 1 : v < 0.5 ? -Math.pow(2, 20 * v - 10) * Math.sin((20 * v - 11.125) * (2 * Math.PI) / 4.5) / 2 : Math.pow(2, -20 * v + 10) * Math.sin((20 * v - 11.125) * (2 * Math.PI) / 4.5) / 2 + 1)),

    /**
     * Ease in bounce easing @see <a href="https://easings.net/#easeInBounce">Ease in bounce</a>
     */
    EASE_OUT_BOUNCE(v -> {
        if (v < 1 / 2.75) {
            return (float) (7.5625 * v * v);
        } else if (v < 2 / 2.75) {
            return (float) (7.5625 * (v -= (float) (1.5 / 2.75)) * v + 0.75);
        } else if (v < 2.5 / 2.75) {
            return (float) (7.5625 * (v -= (float) (2.25 / 2.75)) * v + 0.9375);
        } else {
            return (float) (7.5625 * (v -= (float) (2.625 / 2.75)) * v + 0.984375);
        }
    }),

    /**
     * Ease out bounce easing @see <a href="https://easings.net/#easeOutBounce">Ease out bounce</a>
     */
    EASE_IN_BOUNCE(v -> 1 - EASE_OUT_BOUNCE.ease(1 - v)),

    /**
     * Ease in out bounce easing @see <a href="https://easings.net/#easeInOutBounce">Ease in out bounce</a>
     */
    EASE_IN_OUT_BOUNCE(v -> v < 0.5 ? (1 - EASE_OUT_BOUNCE.ease(1 - 2 * v)) / 2 : (1 + EASE_OUT_BOUNCE.ease(2 * v - 1)) / 2);

    private final IEasing easing;

    /**
     * Create a new instance of the Easing
     * @param easing easing function
     */
    Easing(IEasing easing) {
        this.easing = easing;
    }

    /**
     * Ease the value
     * @param v Value to ease
     */
    @Override
    public double ease(Double v) {
        return easing.ease(v);
    }
}
