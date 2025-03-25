package dev.yuzuki.utils.network;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private String url;
    private String method;
    private Map<String, String> headers;
    private String body;
    private File file;
    private String fileFieldName;

    public Request() {
        this.url = null;
        this.method = null;
        this.headers = new HashMap<>();
        this.body = null;
        this.file = null;
        this.fileFieldName = null;
    }

    public Request(String url, String method) {
        this.url = url;
        this.method = method.toUpperCase();
        this.headers = new HashMap<>();
        this.body = null;
        this.file = null;
        this.fileFieldName = null;
    }

    public Request(String url, String method, String body) {
        this(url, method);
        this.body = body;
    }

    public Request(String url, String method, File file, String fileFieldName) {
        this(url, method);
        this.file = file;
        this.fileFieldName = fileFieldName;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFieldName() {
        return fileFieldName;
    }

    public void setFileFieldName(String fileFieldName) {
        this.fileFieldName = fileFieldName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Request request = new Request();

        public Builder url(String url) {
            request.setUrl(url);
            return this;
        }

        public Builder method(Method method) {
            request.setMethod(method.getMethod());
            return this;
        }

        public Builder header(String key, String value) {
            request.addHeader(key, value);
            return this;
        }

        public Builder body(String body) {
            request.setBody(body);
            return this;
        }

        public Builder file(File file, String fieldName) {
            request.setFile(file);
            request.setFileFieldName(fieldName);
            return this;
        }

        public Request build() {
            if (request.getUrl() == null) throw new IllegalArgumentException("URL must be set");
            if (request.getMethod() == null) throw new IllegalArgumentException("Method must be set");
            return request;
        }
    }

    public enum Method {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE"),
        PATCH("PATCH"),
        HEAD("HEAD"),
        OPTIONS("OPTIONS"),
        TRACE("TRACE");

        final String method;

        Method(String method) {
            this.method = method;
        }

        public String getMethod() {
            return method;
        }
    }
}
