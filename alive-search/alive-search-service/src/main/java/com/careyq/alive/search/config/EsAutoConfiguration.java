package com.careyq.alive.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ES 配置
 *
 * @author CareyQ
 */
@Configuration
@RequiredArgsConstructor
public class EsAutoConfiguration {

    @Value("${es.host}")
    private String host;

    private final ObjectMapper objectMapper;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient client = RestClient.builder(HttpHost.create(host)).build();

        RestClientTransport transport = new RestClientTransport(client, new JacksonJsonpMapper(objectMapper));
        return new ElasticsearchClient(transport);
    }
}
