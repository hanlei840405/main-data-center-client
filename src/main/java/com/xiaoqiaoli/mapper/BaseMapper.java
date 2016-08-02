package com.xiaoqiaoli.mapper;

import com.xiaoqiaoli.domain.RoleDO;
import com.xiaoqiaoli.domain.UserDO;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/16.
 */
public interface BaseMapper<T,V extends Serializable> {

    T get(@Param("id") V id);

    T getOne(T t);

    List<T> find(Map<String,Object> params);

    int delete(T t);

    int update(T t);

    int insert(T t);

    int batchInsert(List<T> ts);

    int batchUpdate(List<T> ts);

    int batchDelete(List<T> ts);
}
