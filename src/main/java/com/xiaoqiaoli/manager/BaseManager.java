package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/8/9.
 */
public abstract class BaseManager<T, V extends Serializable> {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseManager.class);

    abstract BaseMapper<T, V> getBaseMapper();

    /**
     * 根据主键ID查询
     *
     * @param id
     * @return
     */
    public T get(V id) {
        return getBaseMapper().get(id);
    }

    /**
     * 根据唯一属性查询
     *
     * @param queryParams
     * @return
     */
    T getOne(Map<String, Object> queryParams) {
        return getBaseMapper().getOne(queryParams);
    }

    public List<T> findByMultiIds(String[] ids) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("ids", ids);
        return getBaseMapper().findByMultiIds(queryParams);
    }

    /**
     * 根据条件查询
     *
     * @param queryParams
     * @return
     */
    List<T> find(Map<String, Object> queryParams) {
        return getBaseMapper().find(queryParams);
    }

    /**
     * 新增数据
     *
     * @param t
     * @return
     */
    public int insert(T t) {
        try {
            return getBaseMapper().insert(t);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("新增数据出错，参数：{}", t);
            return 0;
        }
    }

    /**
     * 批量新增
     *
     * @param collection
     * @return
     */
    public int batchInsert(List<T> collection) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("collection", collection);
            return getBaseMapper().batchInsert(params);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("批量新增数据出错，参数：{}", collection);
            return 0;
        }
    }

    /**
     * 修改数据
     *
     * @param t
     * @return
     */
    public int update(T t) {
        try {
            return getBaseMapper().update(t);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("修改数据出错，参数：{}", t);
            return 0;
        }
    }

    /**
     * 批量修改
     *
     * @param collection
     * @return
     */
    public int batchUpdate(List<T> collection) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("collection", collection);
            return getBaseMapper().batchUpdate(params);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("批量修改数据出错，参数：{}", collection);
            return 0;
        }
    }

    /**
     * 删除数据
     *
     * @param t
     * @return
     */
    public int delete(T t) {
        try {
            return getBaseMapper().delete(t);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("删除数据出错，参数：{}", t);
            return 0;
        }
    }

    /**
     * 批量删除
     *
     * @param collection
     * @param modifier
     * @return
     */
    public int batchDelete(List<T> collection, String modifier) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("collection", collection);
            params.put("modifier", modifier);
            return getBaseMapper().batchDelete(params);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("批量删除数据出错，参数：{}", collection, modifier);
            return 0;
        }
    }
}
