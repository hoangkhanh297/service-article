package com.khanh.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpSubmitInfo {
    private String url;
    private String method;
    private Map<String, String> requestHeader;
    private String readableRequest;
    private String requestBody;
    private int requestTimeout;
    private int readTimeout;
    private int responseStatusCode;
    private String responseBody;
    private List cookies;
    private Map<String, String> responseHeader;
    private Map<String, String> requestParameters;

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

    public Map<String, String> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(Map<String, String> header) {
        this.requestHeader = header;
    }

    public String getReadableRequest() {
        return readableRequest;
    }

    public void setReadableRequest(String readableRequest) {
        this.readableRequest = readableRequest;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(int responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Map<String, String> getResponseHeader() {
        if (responseHeader == null) {
            responseHeader = new HashMap<>();
        }
        return responseHeader;
    }

    public void setResponseHeader(Map<String, String> responseHeader) {
        this.responseHeader = responseHeader;
    }

    public void addRequestHeader(String key, String value) {
        if (requestHeader == null) {
            requestHeader = new HashMap<>();
        }
        requestHeader.put(key, value);
    }

    public void addResponseHeader(String key, String value) {
        if (responseHeader == null) {
            responseHeader = new HashMap<>();
        }
        responseHeader.put(key, value);
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public void addRequestParameters(String key, String value) {
        if (requestParameters == null) {
            requestParameters = new HashMap<>();
        }
        requestParameters.put(key, value);
    }

    public List getCookies() {
        return cookies;
    }

    public void setCookies(List cookies) {
        this.cookies = cookies;
    }
}

