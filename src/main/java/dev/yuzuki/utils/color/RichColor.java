package dev.yuzuki.utils.color;

import java.awt.*;

/**
 * RichColor class for more color manipulation <br>
 * Usage:
 * <pre>
 *     {@code
 *     RichColor color = new RichColor(255, 255, 255);
 *     }
 * </pre>
 *
 * or
 *
 * <pre>
 *     {@code
 *     RichColor color = RichColor.of(255, 255, 255);
 *     }
 * </pre>
 *
 * Also, this class could use like builder pattern:
 *
 * <pre>
 *     {@code
 *     RichColor color = new RichColor().alpha(100).red(200).green(100).blue(55);
 *     }
 * </pre>
 *
 * @see Color
 */
public class RichColor extends Color {
    int value;

    /**
     * Create a new RichColor with default values (0, 0, 0, 255)
     */
    public RichColor() {
        this(0, 0, 0, 255);
    }

    /**
     * Create a new RichColor with the given ARGB value
     * @param argb the ARGB value
     */
    public RichColor(int argb) {
        this(
                (argb >> 16) & 0xFF,
                (argb >> 8) & 0xFF,
                argb & 0xFF,
                (argb >> 24) & 0xFF
        );
        this.value = argb;
    }

    /**
     * Create a new RichColor with the given Color
     * @param color the Color
     */
    public RichColor(Color color) {
        this(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.value = color.getRGB();
    }

    /**
     * Create a new RichColor with the given RGB values
     * @param r the red value
     * @param g the green value
     * @param b the blue value
     */
    public RichColor(int r, int g, int b) {
        this(r, g, b, 255);
        this.value = (r << 16) | (g << 8) | b;
    }

    /**
     * Create a new RichColor with the given RGBA values
     * @param r the red value
     * @param g the green value
     * @param b the blue value
     * @param a the alpha value
     */
    public RichColor(int r, int g, int b, int a) {
        super(r, g, b, a);
        this.value = (r << 16) | (g << 8) | b | (a << 24);
    }

    /**
     * Create a new RichColor with the given Color
     * @param color the Color
     * @return the RichColor
     */
    public static RichColor of(Color color) {
        return new RichColor(color);
    }

    /**
     * Create a new RichColor with the given ARGB value
     * @param argb the ARGB value
     * @return the RichColor
     */
    public static RichColor of(int argb) {
        return new RichColor(argb);
    }

    /**
     * Create a new RichColor with the given RGB values
     * @param r the red value
     * @param g the green value
     * @param b the blue value
     * @return the RichColor
     */
    public static RichColor of(int r, int g, int b) {
        return new RichColor(r, g, b);
    }

    /**
     * Create a new RichColor with the given RGBA values
     * @param r the red value
     * @param g the green value
     * @param b the blue value
     * @param a the alpha value
     * @return the RichColor
     */
    public static RichColor of(int r, int g, int b, int a) {
        return new RichColor(r, g, b, a);
    }

    /**
     * Set the alpha value of the color
     * @param a the alpha value
     * @return the updated RichColor
     */
    public RichColor alpha(int a) {
        testValue(a);
        this.value = (this.value & 0x00FFFFFF) | (a << 24);
        return this;
    }

    /**
     * Set the red value of the color
     * @param r the red value
     * @return the updated RichColor
     */
    public RichColor red(int r) {
        testValue(r);
        this.value = (this.value & 0xFF00FFFF) | (r << 16);
        return this;
    }

    /**
     * Set the green value of the color
     * @param g the green value
     * @return the updated RichColor
     */
    public RichColor green(int g) {
        testValue(g);
        this.value = (this.value & 0xFFFF00FF) | (g << 8);
        return this;
    }

    /**
     * Set the blue value of the color
     * @param b the blue value
     * @return the updated RichColor
     */
    public RichColor blue(int b) {
        testValue(b);
        this.value = (this.value & 0xFFFFFF00) | b;
        return this;
    }

    /**
     * Get the alpha value of the color
     * @return the alpha value
     */
    public int getAlpha() {
        return (this.value >> 24) & 0xFF;
    }

    /**
     * Get the red value of the color
     * @return the red value
     */
    public int getRed() {
        return (this.value >> 16) & 0xFF;
    }

    /**
     * Get the green value of the color
     * @return the green value
     */
    public int getGreen() {
        return (this.value >> 8) & 0xFF;
    }

    /**
     * Get the blue value of the color
     * @return the blue value
     */
    public int getBlue() {
        return this.value & 0xFF;
    }

    /**
     * Get the alpha value of the color as a float
     * @return the alpha value as a float
     */
    public float getAlphaF() {
        return getAlpha() / 255.0f;
    }

    /**
     * Get the red value of the color as a float
     * @return the red value as a float
     */
    public float getRedF() {
        return getRed() / 255.0f;
    }

    /**
     * Get the green value of the color as a float
     * @return the green value as a float
     */
    public float getGreenF() {
        return getGreen() / 255.0f;
    }

    /**
     * Get the blue value of the color as a float
     * @return the blue value as a float
     */
    public float getBlueF() {
        return getBlue() / 255.0f;
    }

    /**
     * Get the RGB value of the color
     * @return the RGB value
     */
    public int getRGB() {
        return this.value & 0x00FFFFFF;
    }

    /**
     * Get the ARGB value of the color
     * @return the ARGB value
     */
    public int getARGB() {
        return this.value;
    }

    /**
     * Get the HSB values of the color
     * @return an array containing the HSB values
     */
    public float[] getHSB() {
        return Color.RGBtoHSB(getRed(), getGreen(), getBlue(), null);
    }

    /**
     * Test if the value is between 0 and 255
     * @param value the value to test
     * @throws IllegalArgumentException if the value is not between 0 and 255
     */
    private void testValue(int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Value must be between 0 and 255");
        }
    }
}