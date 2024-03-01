package com.takaibun.plexmetadatamanager.remote.plex;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PlexClient {

    /**
     * 默认连接池超时时间(60s)
     */
    private static final int DEFAULT_CONNECTION_POOLING_TIME_TO_LIVE_MILLISECONDS = 60000;

    private static final int DEFAULT_CONNECT_TIMEOUT_MILLISECONDS = 60000;
    private static final int DEFAULT_SOCKET_TIMEOUT_MILLISECONDS = 60000;

    private static final int DEFAULT_CONNECT_REQUEST_TIMEOUT_MILLISECONDS = 60000;

    private final String host;

    private final String token;

    private HttpClientBuilder httpClientBuilder;

    public PlexClient(String host, String token) {
        this.host = host;
        this.token = token;
        init();
    }

    private void init() {
        // 初始化连接池
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(DEFAULT_CONNECTION_POOLING_TIME_TO_LIVE_MILLISECONDS, TimeUnit.MILLISECONDS);
        poolingHttpClientConnectionManager.setMaxTotal(20);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(50);
        // 初始化默认请求头
        List<Header> defaultHeaders = new ArrayList<>(3) {{
            add(new BasicHeader("X-Plex-Token", token));
            add(new BasicHeader("Accept", "application/json"));
            add(new BasicHeader("Content-Type", "application/json"));
        }};
        // 初始化请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLISECONDS)
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT_MILLISECONDS)
                .setConnectionRequestTimeout(DEFAULT_CONNECT_REQUEST_TIMEOUT_MILLISECONDS)
                .build();
        // 初始化HttpClientBuilder
        this.httpClientBuilder = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultHeaders(defaultHeaders);
    }

    public boolean healthy() {
        HttpGet httpGet = new HttpGet(host);
        try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
            int statusCode = httpClient.execute(httpGet).getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK){
                return true;
            }
            if (statusCode == HttpStatus.SC_UNAUTHORIZED){
                log.warn("Health check failed due to 401 Unauthorized");
                return false;
            }
        } catch (IOException e) {
            log.warn("Health check failed due to IOException", e);
            return false;
        }
        return false;
    }
}
