package com.careyq.alive.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class SearchTests {

    @Autowired
    private ElasticsearchClient esClient;

    @Test
    public void indexData() throws IOException {
        User user = new User();
        IndexRequest<User> request = IndexRequest.of(i -> i
                .index("users")
                .id("1")
                .document(user));
        IndexResponse response = esClient.index(request);
        System.out.println(response);
    }

    @Test
    public void test() {
        System.out.println(esClient);
    }

    @Data
    class User {
        private String name;
        private Integer age;
    }
}
