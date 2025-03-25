package dev.yuzuki.utils.network.response;

import dev.yuzuki.utils.network.Response;

/**
 * Represents a text response from a network request.
 * This class extends the generic Response class with a String as the data type.
 */
public class TextResponse extends Response<String> {

    /**
     * Constructs a new TextResponse with the specified status code, time taken, and text data.
     *
     * @param code the HTTP status code of the response
     * @param tookTime the time taken to receive the response
     * @param text the text data of the response
     */
    public TextResponse(int code, long tookTime, String text) {
        super(code, tookTime, text);
    }
}