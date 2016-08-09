package com.xiaoqiaoli.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/16.
 */
public interface BaseMapper<T, V extends Serializable> {

    T get(V id);

    T getOne(Map<String, Object> params);

    List<T> findByMultiIds(Map<String, Object> params);

    List<T> find(Map<String, Object> params);

    int delete(T t);

    int update(T t);

    int insert(T t);

    int batchInsert(Map<String, Object> params);

    int batchUpdate(Map<String, Object> params);

    int batchDelete(Map<String, Object> params);
}
