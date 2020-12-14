package com.yky.timedTask;


import com.yky.pojo.TodayHeadlines;
import com.yky.service.ReptileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: yky
 * @CreateTime: 2020-10-17
 * @Description: 定时任务，定时爬取网站数据
 */
@Component
@Slf4j          //日志注解
public class ReptileTimeTask {

    @Autowired
    private ReptileService reptileService;

    /**
     * 关键字
     */
    private final String KEYWORD = "网络安全";

    /**
     * 定时任务3
     */
    @Scheduled(cron = "0 55 10 * * ?") //定时任务定时时间配置
    public void crawlSina3(){
        log.info("开始爬取数据");
        for (int i=1;i<=7;i++){
            reptileService.parseContent(KEYWORD, i + "");
        }
        log.info("爬取结束");
    }


    /**
     * 测试：定时任务1
     */
    @Scheduled(cron = "0 18 9 * * ?") //定时任务定时时间配置
    public void crawlSina(){
        log.info("开始爬取数据1");
        for (int i=1;i<=5;i++){
            reptileService.parseContentBase(KEYWORD,i+"");
        }
    }

    /**
     * 测试：定时任务2
     */
    @Scheduled(cron = "0 20 9 * * ?") //定时任务定时时间配置
    public void crawlSina2(){
        log.info("开始爬取数据2");
        for (int i=1;i<=5;i++){
            reptileService.parseContentEs(KEYWORD, i + "");
        }
    }
}
