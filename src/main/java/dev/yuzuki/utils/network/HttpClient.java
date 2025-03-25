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

public class HttpClient {

    private static int TIMEOUT = 5000;

    public static void setTimeout(int timeout) {
        TIMEOUT = timeout;
    }

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

    public static TemporaryResponse sendRequest(Request request) {
        long start = System.currentTimeMillis();
        Pair<Integer, ByteArrayOutputStream> data = send(request);
        long tookTime = System.currentTimeMillis() - start;
        if (data == null) {
            return null;
        }
        return new TemporaryResponse(data.getK(), tookTime, data.getV());
    }

    private static class Pair<K, V> {
        private final K k;
        private final V v;

        public Pair(K k, V v) {
            this.k = k;
            this.v = v;
        }

        public K getK() {
            return k;
        }

        public V getV() {
            return v;
        }
    }

    public static class TemporaryResponse extends Response<ByteArrayOutputStream> {
        public TemporaryResponse(int code, long tookTime, ByteArrayOutputStream data) {
            super(code, tookTime, data);
        }

        public BinaryResponse toBinaryResponse() {
            return new BinaryResponse(getCode(), getTookTime(), get().toByteArray());
        }

        public TextResponse toTextResponse() {
            return new TextResponse(getCode(), getTookTime(), get().toString());
        }

        public ImageResponse toImageResponse() {
            try {
                return new ImageResponse(getCode(), getTookTime(), ImageIO.read(new ByteArrayInputStream(get().toByteArray())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
