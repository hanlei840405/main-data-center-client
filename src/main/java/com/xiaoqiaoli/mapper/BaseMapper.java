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
public interface BaseMapper<T, V extends Serializable> {

    T get(@Param("id") V id);

    T getOne(@Param("t") T t);

    List<T> find(Map<String, Object> params);

    int delete(@Param("t") T t);

    int update(@Param("t") T t);

    int insert(@Param("t") T t);

    int batchInsert(@Param("ts") List<T> ts);

    int batchUpdate(@Param("ts") List<T> ts);

    int batchDelete(@Param("ts") List<T> ts, @Param("modifier") String modifier);
}
