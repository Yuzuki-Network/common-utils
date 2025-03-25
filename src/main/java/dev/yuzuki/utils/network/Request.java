package dev.yuzuki.utils.network;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an HTTP request with various attributes such as URL, method, headers, body, and file.
 */
public class Request {
    private String url;
    private String method;
    private Map<String, String> headers;
    private String body;
    private File file;
    private String fileFieldName;

    /**
     * Constructs an empty Request object.
     */
    public Request() {
        this.url = null;
        this.method = null;
        this.headers = new HashMap<>();
        this.body = null;
        this.file = null;
        this.fileFieldName = null;
    }

    /**
     * Constructs a Request object with the specified URL and method.
     *
     * @param url the URL of the request
     * @param method the HTTP method of the request
     */
    public Request(String url, String method) {
        this.url = url;
        this.method = method.toUpperCase();
        this.headers = new HashMap<>();
        this.body = null;
        this.file = null;
        this.fileFieldName = null;
    }

    /**
     * Constructs a Request object with the specified URL, method, and body.
     *
     * @param url the URL of the request
     * @param method the HTTP method of the request
     * @param body the body of the request
     */
    public Request(String url, String method, String body) {
        this(url, method);
        this.body = body;
    }

    /**
     * Constructs a Request object with the specified URL, method, file, and file field name.
     *
     * @param url the URL of the request
     * @param method the HTTP method of the request
     * @param file the file to be sent with the request
     * @param fileFieldName the field name for the file
     */
    public Request(String url, String method, File file, String fileFieldName) {
        this(url, method);
        this.file = file;
        this.fileFieldName = fileFieldName;
    }

    /**
     * Adds a header to the request.
     *
     * @param key the header name
     * @param value the header value
     */
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    /**
     * Returns the URL of the request.
     *
     * @return the URL of the request
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL of the request.
     *
     * @param url the URL to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns the HTTP method of the request.
     *
     * @return the HTTP method of the request
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the HTTP method of the request.
     *
     * @param method the HTTP method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Returns the headers of the request.
     *
     * @return the headers of the request
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets the headers of the request.
     *
     * @param headers the headers to set
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Returns the body of the request.
     *
     * @return the body of the request
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body of the request.
     *
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Returns the file to be sent with the request.
     *
     * @return the file to be sent with the request
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the file to be sent with the request.
     *
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Returns the field name for the file.
     *
     * @return the field name for the file
     */
    public String getFileFieldName() {
        return fileFieldName;
    }

    /**
     * Sets the field name for the file.
     *
     * @param fileFieldName the field name to set
     */
    public void setFileFieldName(String fileFieldName) {
        this.fileFieldName = fileFieldName;
    }

    /**
     * Returns a new Builder instance for constructing a Request object.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * A builder class for constructing Request objects.
     */
    public static class Builder {
        private final Request request = new Request();

        /**
         * Sets the URL of the request.
         *
         * @param url the URL to set
         * @return the Builder instance
         */
        public Builder url(String url) {
            request.setUrl(url);
            return this;
        }

        /**
         * Sets the HTTP method of the request.
         *
         * @param method the HTTP method to set
         * @return the Builder instance
         */
        public Builder method(Method method) {
            request.setMethod(method.getMethod());
            return this;
        }

        /**
         * Adds a header to the request.
         *
         * @param key the header name
         * @param value the header value
         * @return the Builder instance
         */
        public Builder header(String key, String value) {
            request.addHeader(key, value);
            return this;
        }

        /**
         * Sets the body of the request.
         *
         * @param body the body to set
         * @return the Builder instance
         */
        public Builder body(String body) {
            request.setBody(body);
            return this;
        }

        /**
         * Sets the file and field name for the request.
         *
         * @param file the file to set
         * @param fieldName the field name to set
         * @return the Builder instance
         */
        public Builder file(File file, String fieldName) {
            request.setFile(file);
            request.setFileFieldName(fieldName);
            return this;
        }

        /**
         * Builds and returns the Request object.
         *
         * @return the constructed Request object
         * @throws IllegalArgumentException if the URL or method is not set
         */
        public Request build() {
            if (request.getUrl() == null) throw new IllegalArgumentException("URL must be set");
            if (request.getMethod() == null) throw new IllegalArgumentException("Method must be set");
            return request;
        }
    }

    /**
     * Enum representing HTTP methods.
     */
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

        /**
         * Constructs a Method enum with the specified method name.
         *
         * @param method the method name
         */
        Method(String method) {
            this.method = method;
        }

        /**
         * Returns the method name.
         *
         * @return the method name
         */
        public String getMethod() {
            return method;
        }
    }
}