package dev.yuzuki.utils.network;

/**
 * Represents a generic network response.
 *
 * @param <T> the type of the response data
 */
public class Response<T> {
    private final int code;
    private final long tookTime;
    private final T t;

    /**
     * Constructs a new Response with the specified status code, time taken, and response data.
     *
     * @param code the HTTP status code of the response
     * @param tookTime the time taken to receive the response
     * @param t the response data
     */
    public Response(int code, long tookTime, T t) {
        this.code = code;
        this.tookTime = tookTime;
        this.t = t;
    }

    /**
     * Returns the HTTP status code of the response.
     *
     * @return the HTTP status code
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the time taken to receive the response.
     *
     * @return the time taken in milliseconds
     */
    public long getTookTime() {
        return tookTime;
    }

    /**
     * Returns the response data.
     *
     * @return the response data
     */
    public T get() {
        return t;
    }

    /**
     * Checks if the response is successful.
     *
     * @return true if the status code is in the range [200, 300), false otherwise
     */
    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }
}