package com.xzl.http.client;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2020/7/29 9:59
 * @version: v0.0.1
 * @history:
 */
@Configuration
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientAutoConfiguration {
    private  HttpClientProperties httpClientProperties;

    public HttpClientAutoConfiguration(HttpClientProperties httpClientProperties) {
        this.httpClientProperties = httpClientProperties;
    }
    @Bean
    @ConditionalOnMissingBean(HttpClient.class)
    public HttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httpClientProperties.getConnectTimeout())
                .setSocketTimeout(httpClientProperties.getSocketTimeout()).build();

        HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .setUserAgent(httpClientProperties.getAgent()).setMaxConnPerRoute(httpClientProperties.getMaxPerRoute())
                .setConnectionReuseStrategy(new NoConnectionReuseStrategy()).build();
        return httpClient;
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }
}
