package com.yky.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: yky
 * @CreateTime: 2020-10-18
 * @Description: 实现字段自动填充
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    //插入时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("greTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
