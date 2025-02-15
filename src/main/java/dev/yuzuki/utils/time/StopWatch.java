package dev.yuzuki.utils.time;

/**
 * StopWatch class for measuring running time
 */
public class StopWatch {

    /**
     * Measure the running time of the given runnable
     * @param runnable Runnable to measure
     * @return Running time in nanoseconds
     */
    public static long measure(Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        return System.nanoTime() - start;
    }
}
