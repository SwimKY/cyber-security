package com.yky.service;

import com.yky.pojo.TodayHeadlines;

import java.util.List;

/**
 * @Author: yky
 * @CreateTime: 2020-10-18
 * @Description: 获取今日新闻
 */
public interface TodayService {

    List<TodayHeadlines> listToday(Integer pageNo,Integer pageSize);

}
