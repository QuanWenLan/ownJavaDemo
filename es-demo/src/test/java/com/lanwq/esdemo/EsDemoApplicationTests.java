package com.lanwq.esdemo;

import com.alibaba.fastjson.JSON;
import com.lanwq.esdemo.bean.User;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootTest
class EsDemoApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        System.out.println(client);
    }


    /**
     * 测试存储数据到 ES。
     * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.6/java-rest-high-document-index.html
     * */
    @Test
    public void testIndexData() throws IOException {
        IndexRequest request = new IndexRequest("users");
        request.id("1"); // 文档的 id

        //构造 User 对象
        User user = new User();
        user.setUserName("PassJava");
        user.setAge("18");
        user.setGender("Man");

        //User 对象转为 JSON 数据
        String jsonString = JSON.toJSONString(user);

        // JSON 数据放入 request 中
        request.source(jsonString, XContentType.JSON);

        // 执行插入操作
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response);
        // IndexResponse[index=users,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]


    }

    // 示例：搜索 bank 索引，address 字段中包含 big 的所有人的年龄分布 ( 前 10 条 ) 以及平均年龄，以及平均薪资。

    /**
     * GET bank/_search
     * {
     *   "query": {
     *     "match": {
     *       "address": "mill"
     *  }
     *   },
     *   "aggs": {
     *     "ageAggr": {
     *       "terms": {
     *         "field": "age",
     *         "size": 10
     *        }
     *     },
     *     "ageAvg": {
     *       "avg": {
     *         "field": "age"
     *       }
     *     },
     *     "balanceAvg": {
     *       "avg": {
     *         "field": "balance"
     *       }
     *    }
     *  }
     * }
     */
    @Test
    public void testComplexIndexData() throws IOException {
        SearchRequest request = new SearchRequest();
        // 指定索引
        request.indices("bank");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // address 中包含 big 的所有人
        sourceBuilder.query(QueryBuilders.matchQuery("address", "road"));
        // 按照年龄分布进行聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        sourceBuilder.aggregation(ageAgg);
        // 计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        sourceBuilder.aggregation(balanceAvg);
        System.out.println("检索参数" + sourceBuilder.toString());
        System.out.println("=============");

        request.source(sourceBuilder);

        // 2、执行检索
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // 3、分析结果
        System.out.println(response.toString());
        // 3.1）获取查到的数据。
        SearchHits hits = response.getHits();
        // 3.2）获取真正命中的结果
        SearchHit[] searchHits = hits.getHits();
        // 3.3）遍历命中结果
        for (SearchHit hit: searchHits) {
            String hitStr = hit.getSourceAsString();
            BankMember bankMember = JSON.parseObject(hitStr, BankMember.class);
            System.out.println(bankMember);
        }
        // 3.4）获取聚合信息
        // 参考文档：https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-search.html
        Aggregations aggregations = response.getAggregations();
        Terms ageAgg1 = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("用户年龄： " + keyAsString + " 人数：" + bucket.getDocCount());
        }

        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("平均薪资：" + balanceAvg1.getValue());
    }

    @ToString
    @Data
    static class BankMember {
        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;
    }
}
