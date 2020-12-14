package com.yky.controller;

import com.yky.pojo.TodayHeadlines;
import com.yky.service.TodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yky
 * @CreateTime: 2020-10-18
 * @Description: 获取今日新闻
 */
@RestController
@RequestMapping("/today")
public class RequestController {

    @Autowired
    private TodayService todayService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String REIDS_KEY = "cyber_security";

    @GetMapping("/list/{pageNo}/{pageSize}")
    public List<TodayHeadlines> list(@PathVariable("pageNo") Integer pageNo,
                                     @PathVariable("pageSize") Integer pageSize) {
        if (pageNo == null || pageNo <= 1) pageNo = 1;
        if (pageSize == null || pageSize != 10) pageSize = 10;
        List<TodayHeadlines> todayHeadlines = null;
        /**
         * 首先从Redis中获取，如果Redis中没有从数据库中获取返回数据并放入Redis中
         * cyber_security
         */
        todayHeadlines = (List<TodayHeadlines>) redisTemplate.opsForValue().get("cyber_security");
        //redis中没有数据
        if (redisTemplate.hasKey(REIDS_KEY) || todayHeadlines == null || todayHeadlines.size() == 0) {
            //从数据库中获取数据
            todayHeadlines = todayService.listToday(pageNo, pageSize);
            //存入Redis，设置过期时间为两小时
            redisTemplate.opsForValue().set(REIDS_KEY, todayHeadlines,7200L, TimeUnit.SECONDS);
        }
        return todayHeadlines;
    }

}
