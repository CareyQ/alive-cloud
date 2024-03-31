package com.careyq.alive.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
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

    @Value("${es.username}")
    private String username;

    @Value("${es.password}")
    private String password;

    private final ObjectMapper objectMapper;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        RestClient client = RestClient.builder(HttpHost.create(host))
                .setHttpClientConfigCallback(e -> e.setDefaultCredentialsProvider(credentialsProvider))
                .build();

        RestClientTransport transport = new RestClientTransport(client, new JacksonJsonpMapper(objectMapper));
        return new ElasticsearchClient(transport);
    }
}
