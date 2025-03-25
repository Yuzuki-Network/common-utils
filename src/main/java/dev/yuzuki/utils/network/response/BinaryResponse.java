package dev.yuzuki.utils.network.response;

import dev.yuzuki.utils.network.Response;

/**
 * Represents a binary response from a network request.
 * This class extends the generic Response class with a byte array as the data type.
 */
public class BinaryResponse extends Response<byte[]> {

    /**
     * Constructs a new BinaryResponse with the specified status code, time taken, and data.
     *
     * @param code the HTTP status code of the response
     * @param tookTime the time taken to receive the response
     * @param data the binary data of the response
     */
    public BinaryResponse(int code, long tookTime, byte[] data) {
        super(code, tookTime, data);
    }
}