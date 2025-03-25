package dev.yuzuki.utils.network.response;

import dev.yuzuki.utils.network.Response;

import java.awt.image.BufferedImage;

public class ImageResponse extends Response<BufferedImage> {
    public ImageResponse(int code, long tookTime, BufferedImage image) {
        super(code, tookTime, image);
    }
}
