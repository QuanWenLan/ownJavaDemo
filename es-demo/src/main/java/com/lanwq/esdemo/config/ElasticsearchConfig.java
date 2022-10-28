package com.lanwq.esdemo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vin lan
 * @className ElasticsearchConfig
 * @description
 * @createTime 2022-10-26  12:02
 **/
@Configuration
public class ElasticsearchConfig {

    // 给容器注册一个 RestHighLevelClient，用来操作 ES
    // 参考官方文档：https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.9/java-rest-high-getting-started-initialization.html
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("42.193.160.246", 9200, "http")));
    }
}
