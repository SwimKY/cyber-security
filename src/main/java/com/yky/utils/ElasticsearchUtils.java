package com.yky.utils;


import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yky
 * @CreateTime: 2020-10-18
 * @Description: elasticsearch检索工具类
 */
@Service
public class ElasticsearchUtils {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 2、获取数据实现搜素功能
     *
     * @param keywords 关键字
     * @param pageNo   页数
     * @param pageSize 每页条数
     * @return
     */
    public List<Map<String, Object>> searchPage(String keywords, Integer pageNo, Integer pageSize) throws IOException {
        if (pageNo <= 1) pageNo = 1;
        //条件搜索
        SearchRequest searchRequest = new SearchRequest();//"czh"
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //分页
        sourceBuilder.from((pageNo - 1) * pageSize);//从哪个索引开始搜索
        sourceBuilder.size(pageSize);//每次查询的条数

        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("content", keywords);
        sourceBuilder.query(matchQuery);
        //精准匹配
        //TermQueryBuilder termQuery = QueryBuilders.termQuery("content", keywords);
        //sourceBuilder.query(termQuery);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("content");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        highlightBuilder.requireFieldMatch(false);//关闭多个高亮
        sourceBuilder.highlighter(highlightBuilder);
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //获取结果遍历，放入list集合
        List<Map<String, Object>> goodList = new ArrayList<>();
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit documentFields : hits) {
            //解析高亮的字段 ==> 把原来字段替换成高亮字段
            Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            HighlightField title = highlightFields.get("content");
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();//原来的结果
            if (title != null) {
                Text[] fragments = title.fragments();
                String newTitle = "";
                for (Text text : fragments) {
                    newTitle += text;
                }
                //替换
                sourceAsMap.put("content",newTitle);
            }
            goodList.add(sourceAsMap);
        }
        return goodList;
    }

}
