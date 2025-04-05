package dev.yuzuki.utils.time;

public class Timer {
    private long startTime;

    public Timer() {
        this.startTime = System.currentTimeMillis();
    }

    public boolean passed(long time) {
        return System.currentTimeMillis() - startTime >= time;
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
    }
}
