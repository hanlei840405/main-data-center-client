package com.xiaoqiaoli.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoqiaoli.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

/**
 * Created by hanlei6 on 2016/8/7.
 */
public abstract class BaseController<T> {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Value("${avatar.url}")
    String avatarUrl;

    @Value("${avatar.key}")
    String avatarKey;

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 返回状态码，状态信息和操作的对象
     *
     * @param t
     * @param result
     * @return
     */
    Map<String, Object> buildResponseStatus(T t, Map<String, Object> result) {
        if (t != null) {
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_SUCCESS);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_SUCCESS);
        } else {
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_FAILURE);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_FAILURE);
        }
        result.put(Constant.RETURN_MAP_KEY_OBJECT, t);
        return result;
    }



}
