package com.lanwq.esdemo.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * @author Lan
 * @createTime 2024-03-19  21:26
 * https://www.cnblogs.com/ZhuChangwu/p/11150374.html
 * @Document
 * 作用在类，标记实体类为文档对象
 * indexName：对应索引库名称---- 数据库名
 * type：对应在索引库中的类型---- 表名（后面废弃了）
 * shards：分片数量，默认5
 * replicas：副本数量，默认1
 * @Id
 * 作用在成员变量id上
 * 标记一个字段作为id主键(这个id别写错了,不然程序都启动不起来)
 * @Field
 * 作用在成员变量，标记为文档的字段，并指定字段映射属性：
 * type：字段类型，取值是枚举：FieldType
 * index：是否索引，布尔类型，默认是true
 * store：是否存储，布尔类型，默认是false
 * analyzer：分词器名称
 **/
@Data
@Setting(shards = 1, replicas = 1)
@Document(indexName = "product")
public class Product {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String name;

    /**
     *分类
     */
    @Field(type = FieldType.Keyword, index = true)
    private String category;

    /**
     * 品牌
     */
    @Field(type = FieldType.Keyword)
    private String brand;

    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    private Double price;

    /**
     * 图片地址
     */
    @Field(type = FieldType.Keyword, index = false)
    private String images;

    // 指定存储时和检索时使用的分词器是同一个
    // index=true 表示索引
    // 是否索引, 就是看这个字段是否能被搜索, 比如: 如果对整篇文章建立了索引,那么从文章中任意抽出一段来,都可以搜索出这个文章
    // 是否分词, 就是表示搜索的时候,是整体匹配还是单词匹配  比如: 如果不分词的话,搜索时,一个词不一样,都搜索不出来结果
    /**
     * 是否存储, 就是,是否在页面上展示 , 但是在es中默认字段值已经存储在_source 字段里， 也是能检索出原始字段的
     */
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    /**
     * analyzer 存进去的时候,按这个分词
     * searchAnalyzer: 搜索时,按这个分词
     * 产品内容介绍
     */
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;

    /**
     * 状态，是否停用
     */
    @Field(index = true, type = FieldType.Keyword)
    private String state;
}
