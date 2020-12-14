package com.yky.controller;

import com.yky.pojo.TodayHeadlines;
import com.yky.service.ReptileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @Author: yky
 * @CreateTime: 2020-10-17
 * @Description: 爬虫
 */
@RestController
public class ReptileController {

    @Autowired
    private ReptileService reptileService;

    @GetMapping("/parse/{keyword}/{page}")
    public List<TodayHeadlines> parse(@PathVariable("keyword") String keyword,
                                      @PathVariable("page")String page) throws IOException {
        return reptileService.parseContent(keyword,page);
    }

}
