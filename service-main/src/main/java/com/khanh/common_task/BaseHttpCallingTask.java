package com.khanh.common_task;

import com.khanh.message.HttpSubmitInfo;
import com.khanh.message.StandardRequestMsg;
import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.Handler;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.asynchttpclient.util.HttpConstants;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class BaseHttpCallingTask<T extends StandardRequestMsg> extends HttpTask<T> {

    protected void submitToPartner(StandardRequestMsg input, HttpSubmitInfo httpSubmitInfo, Handler<StandardRequestMsg> whenDone) {
        RequestBuilder requestBuilder = new RequestBuilder().setUrl(httpSubmitInfo.getUrl());

        Map<String, String> headers = httpSubmitInfo.getRequestHeader();
        if (headers != null
                && !headers.isEmpty()) {
            for (String key : httpSubmitInfo.getRequestHeader().keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }

        Map<String, String> parameters = httpSubmitInfo.getRequestParameters();
        if (parameters != null
                && !parameters.isEmpty()) {
            for (String key : httpSubmitInfo.getRequestParameters().keySet()) {
                requestBuilder.addQueryParam(key, parameters.get(key));
            }
        }

        if (httpSubmitInfo.getReadTimeout() != 0) {
            requestBuilder.setReadTimeout(httpSubmitInfo.getReadTimeout());
        }

        if (httpSubmitInfo.getRequestTimeout() != 0) {
            requestBuilder.setRequestTimeout(httpSubmitInfo.getRequestTimeout());
        }


        if (httpSubmitInfo.getCookies() != null) {
            requestBuilder.setCookies(httpSubmitInfo.getCookies());
        }

        requestBuilder.setMethod(httpSubmitInfo.getMethod().isEmpty() ? HttpConstants.Methods.POST : httpSubmitInfo.getMethod());
        requestBuilder.setBody(httpSubmitInfo.getRequestBody());

        getHttpClient().executeRequest(requestBuilder, new AsyncCompletionHandler<Object>() {
            @Override
            public Object onCompleted(Response response) {
                httpSubmitInfo.setResponseStatusCode(response.getStatusCode());
                httpSubmitInfo.setResponseBody(response.getResponseBody(StandardCharsets.UTF_8));
                System.out.println(httpSubmitInfo.getResponseBody());
                HttpHeaders responseHeaders = response.getHeaders();
                if (responseHeaders != null && responseHeaders.entries() != null) {
                    responseHeaders.entries().forEach(entry -> httpSubmitInfo.addResponseHeader(entry.getKey(), entry.getValue()));
                }
                whenDone.handle(input);
                return null;
            }

            @Override
            public void onThrowable(Throwable t) {
                whenDone.handle(input);
            }
        });
    }
}
