package dev.yuzuki.utils.animations;

import java.util.function.Consumer;

/**
 * Easing functions for animations
 * @see <a href="https://easings.net/">Easing functions</a>
 */
public enum Easing implements IEasing {
    LINEAR(Double::floatValue),
    EASE_IN_SINE(v -> (1 - Math.cos((v * Math.PI) / 2))),
    EASE_OUT_SINE(v -> Math.sin((v * Math.PI) / 2)),
    EASE_IN_OUT_SINE(v -> (-(Math.cos(Math.PI * v) - 1) / 2)),
    EASE_IN_QUAD(v -> (float) (v * v)),
    EASE_OUT_QUAD(v -> (float) (1 - (1 - v) * (1 - v))),
    EASE_IN_OUT_QUAD(v -> v < 0.5 ? (float) (2 * v * v) : (float) (1 - Math.pow(-2 * v + 2, 2) / 2)),
    EASE_IN_CUBIC(v -> (float) (v * v * v)),
    EASE_OUT_CUBIC(v -> (float) (1 - Math.pow(1 - v, 3))),
    EASE_IN_OUT_CUBIC(v -> v < 0.5 ? (float) (4 * v * v * v) : (float) (1 - Math.pow(-2 * v + 2, 3) / 2)),
    EASE_IN_QUART(v -> (float) (v * v * v * v)),
    EASE_OUT_QUART(v -> (float) (1 - Math.pow(1 - v, 4))),
    EASE_IN_OUT_QUART(v -> v < 0.5 ? (float) (8 * v * v * v * v) : (float) (1 - Math.pow(-2 * v + 2, 4) / 2)),
    EASE_IN_QUINT(v -> (float) (v * v * v * v * v)),
    EASE_OUT_QUINT(v -> (float) (1 - Math.pow(1 - v, 5))),
    EASE_IN_OUT_QUINT(v -> v < 0.5 ? (float) (16 * v * v * v * v * v) : (float) (1 - Math.pow(-2 * v + 2, 5) / 2)),
    EASE_IN_EXPO(v -> v == 0 ? 0 : (float) Math.pow(2, 10 * v - 10)),
    EASE_OUT_EXPO(v -> v == 1 ? 1 : (float) (1 - Math.pow(2, -10 * v))),
    EASE_IN_OUT_EXPO(v -> v == 0 ? 0 : v == 1 ? 1 : v < 0.5 ? (float) Math.pow(2, 20 * v - 10) / 2 : (float) (2 - Math.pow(2, -20 * v + 10) / 2)),
    EASE_IN_CIRC(v -> (float) (1 - Math.sqrt(1 - Math.pow(v, 2)))),
    EASE_OUT_CIRC(v -> (float) Math.sqrt(1 - Math.pow(v - 1, 2))),
    EASE_IN_OUT_CIRC(v -> v < 0.5 ? (float) ((1 - Math.sqrt(1 - Math.pow(2 * v, 2))) / 2) : (float) ((Math.sqrt(1 - Math.pow(-2 * v + 2, 2)) + 1) / 2)),
    EASE_IN_BACK(v -> (float) (v * v * (2.70158 * v - 1.70158))),
    EASE_OUT_BACK(v -> (float) ((float) 3.70158 * Math.pow(v - 1, 3) + 1.70158 * Math.pow(v - 1, 2))),
    EASE_IN_OUT_BACK(v -> v < 0.5 ? (float) (Math.pow(2 * v, 2) * ((2.5949095 + 1) * 2 * v - 2.5949095)) / 2 : (float) (Math.pow(2 * v - 2, 2) * ((2.5949095 + 1) * (v * 2 - 2) + 2.5949095) + 2) / 2),
    EASE_IN_ELASTIC(v -> v == 0 ? 0 : (float) (v == 1 ? 1 : -Math.pow(2, 10 * v - 10) * Math.sin((v * 10 - 10.75) * (2 * Math.PI) / 3))),
    EASE_OUT_ELASTIC(v -> v == 0 ? 0 : (float) (v == 1 ? 1 : Math.pow(2, -10 * v) + Math.sin((v * 10 - 0.75) * (2 * Math.PI) / 3))),
    EASE_IN_OUT_ELASTIC(v -> v == 0 ? 0 : (float) (v == 1 ? 1 : v < 0.5 ? -Math.pow(2, 20 * v - 10) * Math.sin((20 * v - 11.125) * (2 * Math.PI) / 4.5) / 2 : Math.pow(2, -20 * v + 10) * Math.sin((20 * v - 11.125) * (2 * Math.PI) / 4.5) / 2 + 1)),
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
    EASE_IN_BOUNCE(v -> 1 - EASE_OUT_BOUNCE.ease(1 - v)),
    EASE_IN_OUT_BOUNCE(v -> v < 0.5 ? (1 - EASE_OUT_BOUNCE.ease(1 - 2 * v)) / 2 : (1 + EASE_OUT_BOUNCE.ease(2 * v - 1)) / 2);

    private final IEasing easing;
    Easing(IEasing easing) {
        this.easing = easing;
    }

    @Override
    public double ease(Double v) {
        return easing.ease(v);
    }
}
