package dev.yuzuki.utils.network.response;

import dev.yuzuki.utils.network.Response;

public class BinaryResponse extends Response<byte[]> {
    public BinaryResponse(int code, long tookTime, byte[] data) {
        super(code, tookTime, data);
    }
}
