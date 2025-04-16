package dev.yuzuki.utils.mouse;

public class Bounds {
    public static boolean isInBounds(int x, int y, int x1, int y1, int x2, int y2) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    public static boolean isInBoundsWH(int x, int y, int x1, int y1, int width, int height) {
        return isInBounds(x, y, x1, y1, x1 + width, y1 + height);
    }
}
