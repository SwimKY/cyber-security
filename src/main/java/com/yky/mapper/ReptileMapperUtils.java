package com.yky.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yky.pojo.TodayHeadlines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: yky
 * @CreateTime: 2020-10-18
 * @Description: 方便一次插入多条数据，建立怕从工具类
 */
@Component
public class ReptileMapperUtils {

    @Autowired
    private BaseMapper<TodayHeadlines> baseMapper;

    public boolean insertList(List<TodayHeadlines> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        for (TodayHeadlines todayHeadlines : list) {
            baseMapper.insert(todayHeadlines);
        }
        return true;
    }


    /**
     * 从数据库中分页查询出数据
     *
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<TodayHeadlines> getListPageSina(String keyword, Integer pageNo, Integer pageSize) {
        Page<TodayHeadlines> page = new Page<>(pageNo, pageSize);
        QueryWrapper<TodayHeadlines> wrapper = new QueryWrapper<>();
        wrapper.like("title", keyword);
        wrapper.orderByDesc("gre_time");
        Page<TodayHeadlines> todayHeadlinesPage = baseMapper.selectPage(page, wrapper);
        List<TodayHeadlines> records = todayHeadlinesPage.getRecords();
        return records;
    }
}
