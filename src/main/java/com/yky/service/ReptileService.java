package com.yky.service;

import com.yky.pojo.TodayHeadlines;

import java.util.List;

/**
 * @Author: yky
 * @CreateTime: 2020-10-17
 * @Description: TODO
 */
public interface ReptileService {
    /**
     * 实现关键字搜索该方法数据会存入数据库、es
     */
    List<TodayHeadlines> parseContent(String keyword, String page);

    /**
     * 爬取数据后只存放到数据库
     *
     * @param keyword
     * @param page
     * @return
     */
    List<TodayHeadlines> parseContentBase(String keyword, String page);

    /**
     * 爬取数据后存放到es
     *
     * @param keyword
     * @param page
     * @return
     */
    List<TodayHeadlines> parseContentEs(String keyword, String page);

    /**
     * 关键字搜索，当数据库es中没有数据时，直接从新浪获取
     *
     * @param keyword
     * @param page
     * @return
     */
    List<TodayHeadlines> searchKeyWord(String keyword, String page);
}
