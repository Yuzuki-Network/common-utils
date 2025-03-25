package dev.yuzuki.utils.network.response;

import dev.yuzuki.utils.network.Response;

import java.awt.image.BufferedImage;

/**
 * Represents an image response from a network request.
 * This class extends the generic Response class with a BufferedImage as the data type.
 */
public class ImageResponse extends Response<BufferedImage> {

    /**
     * Constructs a new ImageResponse with the specified status code, time taken, and image data.
     *
     * @param code the HTTP status code of the response
     * @param tookTime the time taken to receive the response
     * @param image the image data of the response
     */
    public ImageResponse(int code, long tookTime, BufferedImage image) {
        super(code, tookTime, image);
    }
}