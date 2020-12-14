package com.yky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yky.mapper.TodayMapper;
import com.yky.pojo.TodayHeadlines;
import com.yky.service.TodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yky
 * @CreateTime: 2020-10-18
 * @Description: 今日新闻ServiceImpl
 */
@Service
public class TodayServiceImpl implements TodayService {

    @Autowired(required = false)
    private BaseMapper<TodayHeadlines> baseMapper;

    @Override
    public List<TodayHeadlines> listToday(Integer pageNo,Integer pageSize) {
        Page<TodayHeadlines> page = new Page<>(pageNo,pageSize);
        QueryWrapper<TodayHeadlines> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gre_time");
        Page<TodayHeadlines> page1 = baseMapper.selectPage(page, wrapper);
        List<TodayHeadlines> records = page1.getRecords();
        return records;
    }

}
