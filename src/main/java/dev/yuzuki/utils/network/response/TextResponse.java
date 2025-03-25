package dev.yuzuki.utils.network.response;

import dev.yuzuki.utils.network.Response;

public class TextResponse extends Response<String> {
    public TextResponse(int code, long tookTime, String text) {
        super(code, tookTime, text);
    }
}
