package dev.yuzuki.utils.network;

import dev.yuzuki.utils.network.response.BinaryResponse;
import dev.yuzuki.utils.network.response.ImageResponse;
import dev.yuzuki.utils.network.response.TextResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A utility class for making HTTP requests.
 */
public class HttpClient {

    /**
     * The timeout value for the HTTP connection in milliseconds.
     */
    private static int TIMEOUT = 5000;

    /**
     * Sets the timeout value for the HTTP connection.
     *
     * @param timeout the timeout value in milliseconds
     */
    public static void setTimeout(int timeout) {
        TIMEOUT = timeout;
    }

    /**
     * Sends an HTTP request and returns the response code and data.
     *
     * @param request the HTTP request to send
     * @return a Pair containing the response code and the response data
     */
    private static Pair<Integer, ByteArrayOutputStream> send(Request request) {
        try {
            URL url = new URL(request.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(request.getMethod());
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            request.getHeaders().forEach(connection::setRequestProperty);
            if (request.getBody() != null) {
                connection.setDoOutput(true);
                connection.getOutputStream().write(request.getBody().getBytes());
            }
            if (request.getFile() != null) {
                connection.setDoOutput(true);
                connection.getOutputStream().write(request.getFile().toPath().toAbsolutePath().toString().getBytes());
            }
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = connection.getInputStream().read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
                return new Pair<>(connection.getResponseCode(), outputStream);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Sends an HTTP request and returns a TemporaryResponse object.
     *
     * @param request the HTTP request to send
     * @return a TemporaryResponse object containing the response data
     */
    public static TemporaryResponse sendRequest(Request request) {
        long start = System.currentTimeMillis();
        Pair<Integer, ByteArrayOutputStream> data = send(request);
        long tookTime = System.currentTimeMillis() - start;
        if (data == null) {
            return null;
        }
        return new TemporaryResponse(data.getK(), tookTime, data.getV());
    }

    /**
     * A generic pair class to hold two related objects.
     *
     * @param <K> the type of the first object
     * @param <V> the type of the second object
     */
    private static class Pair<K, V> {
        private final K k;
        private final V v;

        /**
         * Constructs a new Pair with the specified objects.
         *
         * @param k the first object
         * @param v the second object
         */
        public Pair(K k, V v) {
            this.k = k;
            this.v = v;
        }

        /**
         * Returns the first object.
         *
         * @return the first object
         */
        public K getK() {
            return k;
        }

        /**
         * Returns the second object.
         *
         * @return the second object
         */
        public V getV() {
            return v;
        }
    }

    /**
     * A temporary response class to hold the response data before converting to a specific response type.
     */
    public static class TemporaryResponse extends Response<ByteArrayOutputStream> {

        /**
         * Constructs a new TemporaryResponse with the specified status code, time taken, and data.
         *
         * @param code the HTTP status code of the response
         * @param tookTime the time taken to receive the response
         * @param data the response data
         */
        public TemporaryResponse(int code, long tookTime, ByteArrayOutputStream data) {
            super(code, tookTime, data);
        }

        /**
         * Converts the temporary response to a BinaryResponse.
         *
         * @return a BinaryResponse object
         */
        public BinaryResponse toBinaryResponse() {
            return new BinaryResponse(getCode(), getTookTime(), get().toByteArray());
        }

        /**
         * Converts the temporary response to a TextResponse.
         *
         * @return a TextResponse object
         */
        public TextResponse toTextResponse() {
            return new TextResponse(getCode(), getTookTime(), get().toString());
        }

        /**
         * Converts the temporary response to an ImageResponse.
         *
         * @return an ImageResponse object
         * @throws RuntimeException if an I/O error occurs
         */
        public ImageResponse toImageResponse() {
            try {
                return new ImageResponse(getCode(), getTookTime(), ImageIO.read(new ByteArrayInputStream(get().toByteArray())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}