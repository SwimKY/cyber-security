package com.yky.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yky.pojo.TodayHeadlines;
import com.yky.service.ReptileService;
import com.yky.utils.SinaWeiboHtmlParseUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yky
 * @CreateTime: 2020-10-17
 * @Description: 爬虫Service层
 */
@Slf4j
@Service
@Transactional
public class ReptileServiceImpl implements ReptileService {

    @Autowired
    private BaseMapper<TodayHeadlines> baseMapper;

    //es高级客户端
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public List<TodayHeadlines> parseContent(String keyword, String page) {
        List<TodayHeadlines> todayHeadlines = new ArrayList<>();
        try {
            todayHeadlines = SinaWeiboHtmlParseUtils.parseSina(keyword, page);
            //获取数据后插入数据库
            for (TodayHeadlines todayHeadline : todayHeadlines) {
                if (todayHeadline != null) baseMapper.insert(todayHeadline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //数据放入es
        if (todayHeadlines != null && todayHeadlines.size() != 0) insertEs(todayHeadlines);
        return todayHeadlines;
    }

    @Override
    public List<TodayHeadlines> parseContentBase(String keyword, String page) {
        List<TodayHeadlines> todayHeadlines = new ArrayList<>();
        try {
            todayHeadlines = SinaWeiboHtmlParseUtils.parseSina(keyword, page);
            //获取数据后插入数据库
            for (TodayHeadlines todayHeadline : todayHeadlines) {
                if (todayHeadline != null) baseMapper.insert(todayHeadline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todayHeadlines;
    }

    @Override
    public List<TodayHeadlines> parseContentEs(String keyword, String page) {
        List<TodayHeadlines> todayHeadlines = new ArrayList<>();
        try {
            todayHeadlines = SinaWeiboHtmlParseUtils.parseSina(keyword, page);
            insertEs(todayHeadlines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todayHeadlines;
    }

    @Override
    public List<TodayHeadlines> searchKeyWord(String keyword, String page) {
        try {
            return SinaWeiboHtmlParseUtils.parseSina(keyword, page);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean insertEs(List<TodayHeadlines> todayHeadlines) {
        if (todayHeadlines == null || todayHeadlines.size() == 0) return true;
        //把查询的数据放入到es中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (int i = 0; i < todayHeadlines.size(); i++) {
            if (todayHeadlines.get(i) == null) continue;
            else {
                bulkRequest.add(
                        new IndexRequest("czh")
                                .source(JSON.toJSONString(todayHeadlines.get(i)), XContentType.JSON)
                );
            }
        }
        try {
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
