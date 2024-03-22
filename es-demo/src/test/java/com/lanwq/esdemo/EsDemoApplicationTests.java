package com.lanwq.esdemo;

import com.alibaba.fastjson.JSON;
import com.lanwq.esdemo.bean.Product;
import com.lanwq.esdemo.bean.User;
import com.lanwq.esdemo.staticdata.ItemData;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
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
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class EsDemoApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        System.out.println("RestHighLevelClient:" + client);
        // elasticsearchTemplate
        System.out.println("ElasticsearchRestTemplate: " + elasticsearchRestTemplate);
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

    // index item， 这个底层还是用的 RestHighLevelClient
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void testIndexProduct() {
        List<Product> products = ItemData.products;
        Iterable<Product> iterable = elasticsearchRestTemplate.save(products);

    }

//    https://www.cnblogs.com/ZhuChangwu/p/11150374.html

    /**
     * 根据id更新部分字段
     */
    @Test
    public void updateProductIndex() {
        Map<String, Object> params = new HashMap<>();
        //before:这是一款华为手机的产品介绍
        params.put("content", "这是一款华为手机的产品介绍。相信华为，相信中国！");
        Document document = Document.from(params);
        UpdateQuery updateQuery = UpdateQuery.builder("1")  // 1 是文档的ID
                .withDocument(document)
                .build();

        UpdateResponse result = elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("product"));
        System.out.println(JSON.toJSONString(result));  // 结果：{"result":"UPDATED"}
    }

    @Test
    public void getProduct() {
        Product product = elasticsearchRestTemplate.get("1", Product.class, IndexCoordinates.of("product"));
        System.out.println(JSON.toJSONString(product));
    }

    @Test
    public void delete() {
        String result = elasticsearchRestTemplate.delete("5", IndexCoordinates.of("product"));
        System.out.println(result);  // 结果："4"
    }

    @Test
    public void searchForPage() {

        /**
         * 分页加排序
         *  GET product/_search
         * {
         *   "query": {
         *     "bool": {
         *       "must": [
         *         {
         *           "match": {
         *             "content": "手机"
         *           }
         *         }
         *       ],
         *       "filter": [
         *         {
         *           "range": {
         *             "price": {
         *               "gte": 3000,
         *               "lte": 10000
         *             }
         *           }
         *         }
         *       ]
         *     }
         *   },
         *   "sort": [
         *     {
         *       "price": {
         *         "order": "desc"
         *       }
         *     }
         *   ]
         * }
         */
        Pageable pageable = PageRequest.of(0,2);  // page 从第 0 页开始
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("content", "手机"))
                .filter(QueryBuilders.rangeQuery("price").gte(3000));
        // 高亮
        HighlightBuilder.Field highlightField = new HighlightBuilder.Field("content")
                .preTags("<span>")
                .postTags("</span>");

        Query query = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withPageable(pageable)
                .withSorts(SortBuilders.fieldSort("price").order(SortOrder.DESC))
                .withHighlightFields(highlightField)
                .build();

        org.springframework.data.elasticsearch.core.SearchHits<Product> product =
                elasticsearchRestTemplate.search(query, Product.class, IndexCoordinates.of("product"));
        System.out.println(JSON.toJSONString(product));
    }
}
