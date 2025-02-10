package dev.yuzuki.utils.time;

public class StopWatch {
    public static long measure(Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        return System.nanoTime() - start;
    }
}
