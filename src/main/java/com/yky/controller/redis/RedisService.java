package com.yky.controller.redis;

/**
 * @Author: yky
 * @CreateTime: 2020-10-27
 * @Description: Redis操作
 */
public interface RedisService {

    /**
     * 判断key是否存在
     */
    Boolean hasKey(String key);

}
