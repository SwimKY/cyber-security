package com.yky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yky.pojo.TodayHeadlines;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Author: yky
 * @CreateTime: 2020-10-17
 * @Description: 爬虫数据插入数据库
 */
@Mapper
public interface ReptileMapper extends BaseMapper<TodayHeadlines> {
}
