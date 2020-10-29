package com.khanh.common_task;

import com.khanh.message.TraceableRequest;
import com.khanh.task.Task;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;


public abstract class HttpTask<T extends TraceableRequest> extends Task<T> {
    private static AsyncHttpClient httpClient;

    protected AsyncHttpClient getHttpClient() {
        if (httpClient == null) {
            int maxConnections = 100;
            int maxConnectionsPerHost = 100;
            DefaultAsyncHttpClientConfig.Builder config = config()
                    .setMaxConnections(maxConnections)
                    .setMaxConnectionsPerHost(maxConnectionsPerHost)
                    .setKeepAlive(true)
                    .setRequestTimeout(18000)
                    .setConnectTimeout(60000);
            httpClient = asyncHttpClient(config);
        }
        return httpClient;
    }

}
