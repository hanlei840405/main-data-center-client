package com.xiaoqiaoli.controller;

import com.xiaoqiaoli.util.Constant;

import java.util.Map;

/**
 * Created by hanlei6 on 2016/8/7.
 */
public class BaseController<T> {

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
