package com.yky.controller;

import com.yky.mapper.ReptileMapperUtils;
import com.yky.pojo.TodayHeadlines;
import com.yky.service.ReptileService;
import com.yky.utils.ElasticsearchUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: yky
 * @CreateTime: 2020-10-18
 * @Description: 查询Controller
 */
@Slf4j
@RestController
@RequestMapping("/search")
public class ElasticsearchController {

    @Autowired
    private ElasticsearchUtils elasticsearchUtils;

    /**
     * 怕从获取的数据存放数据库
     */
    @Autowired
    private ReptileMapperUtils reptileMapperUtils;

    @Autowired
    private ReptileService reptileService;

    @PostMapping("post")
    public String search(String keyword) {
        return "你好"+keyword;
    }

    /**
     * 搜索controller
     *
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/{keyword}/{pageNo}/{pageSize}")
    public List<TodayHeadlines> search(@PathVariable("keyword") String keyword,
                                       @PathVariable("pageNo") Integer pageNo,
                                       @PathVariable("pageSize") Integer pageSize) {
        if (keyword == null) keyword = "网络安全";
        if (pageNo == null || pageNo <= 1) pageNo = 1;
        if (pageSize != 10) pageSize = 18;
        List<TodayHeadlines> todayHeadlines = null;
        try {
            List<Map<String, Object>> maps = elasticsearchUtils.searchPage(keyword, pageNo, pageSize);
            //搜引擎中没有数据，从数据库中查询获取
            if (maps == null || maps.size() == 0) {
                // == todo ==从数据库获取速度太慢考虑去除或者优化
                log.info("数据来源于数据库");
                todayHeadlines = reptileMapperUtils.getListPageSina(keyword,pageNo,pageSize);
            } else {
                log.info("数据来源于es");
                todayHeadlines = dataProcessing(maps);
            }
            //如果数据库和搜索引擎都没有相关信息，直接熊新浪获取
            if (todayHeadlines == null || todayHeadlines.size() == 0) {
                log.info("数据来源于新浪网");
                todayHeadlines = reptileService.searchKeyWord(keyword, pageNo.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todayHeadlines;
    }


    /**
     * 对数据进行封装处理
     */
    private List<TodayHeadlines> dataProcessing(List<Map<String, Object>> maps) {
        List<TodayHeadlines> todayHeadlines = new ArrayList<>();

        for (Map<String, Object> map : maps) {
            TodayHeadlines todayHeadlinesObject = new TodayHeadlines();
            //对致进行封装
            todayHeadlinesObject.setTitle((String) map.get("title"));
            todayHeadlinesObject.setImgIlink((String) map.get("imgIlink"));
            todayHeadlinesObject.setContent((String) map.get("content"));
            todayHeadlinesObject.setTime((String) map.get("time"));
            todayHeadlinesObject.setLink((String) map.get("link"));
            //todayHeadlinesObject.setGreTime(new Date());
            //放入集合
            todayHeadlines.add(todayHeadlinesObject);
        }
        return todayHeadlines;
    }

}
