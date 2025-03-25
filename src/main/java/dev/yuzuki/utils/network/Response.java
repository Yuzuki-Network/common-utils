package dev.yuzuki.utils.network;

public class Response<T> {
    private final int code;
    private final long tookTime;
    private final T t;

    public Response(int code, long tookTime, T t) {
        this.code = code;
        this.tookTime = tookTime;
        this.t = t;
    }

    public int getCode() {
        return code;
    }

    public long getTookTime() {
        return tookTime;
    }

    public T get() {
        return t;
    }

    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }
}
