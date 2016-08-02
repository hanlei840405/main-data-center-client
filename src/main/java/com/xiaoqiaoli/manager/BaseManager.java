package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.mapper.BaseMapper;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hanlei6 on 2016/7/19.
 */
public abstract class BaseManager<T,V extends Serializable> {

    protected abstract BaseMapper<T,V> baseMapper();

    @Cacheable(value = "mdc")
    public T get(V id) {
        return baseMapper().get(id);
    }

    public int insert(T t) {
        try {
            return baseMapper().insert(t);
        }catch (RuntimeException e) {
            return 0;
        }
    }

    public int batchInsert(List<T> ts) {
        try {
            return baseMapper().batchInsert(ts);
        }catch (RuntimeException e) {
            return 0;
        }
    }

    public int update(T t) {
        try {
            return baseMapper().update(t);
        }catch (RuntimeException e) {
            return 0;
        }
    }

    public int batchUpdate(List<T> ts) {
        try {
            return baseMapper().batchUpdate(ts);
        }catch (RuntimeException e) {
            return 0;
        }
    }

    public int delete(T t) {
        try {
            return baseMapper().delete(t);
        }catch (RuntimeException e) {
            return 0;
        }
    }

    public int batchDelete(List<T> ts) {
        try {
            return baseMapper().batchDelete(ts);
        }catch (RuntimeException e) {
            return 0;
        }
    }

    public abstract void deleteCache();
}
