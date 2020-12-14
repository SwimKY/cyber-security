package com.yky.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yky
 * @CreateTime: 2020-10-17
 * @Description: Elasticsearch配置
 */
@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.config.hostname}")
    private String hostname;

    @Value("${elasticsearch.config.port}")
    private Integer port;

    @Value("${elasticsearch.config.scheme}")
    private String scheme;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostname, port, scheme)));
        return client;
    }
}
