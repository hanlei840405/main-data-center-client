package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.RoleDO;
import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/19.
 */
@Component
public class RoleManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(RoleManager.class);
    @Autowired
    private RoleMapper roleMapper;

    @Cacheable(cacheNames = "mdc:role", key = "'role.'.concat(#code)")
    public RoleDO getByCode(String code) {
        RoleDO query = new RoleDO();
        query.setCode(code);
        return roleMapper.getOne(query);
    }

    public List<RoleDO> findByMultiIds(String[] ids) {
        return roleMapper.findByMultiIds(ids);
    }

    @Cacheable(cacheNames = "mdc:role", key = "'role.'.concat(#name)")
    public List<RoleDO> findByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return roleMapper.find(params);
    }

    @Cacheable(cacheNames = "mdc:role", key = "'role.'.concat(#username)")
    public List<RoleDO> findByUsername(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        return roleMapper.find(params);
    }

    @CacheEvict(cacheNames = "mdc:role", allEntries = true)
    public int connectAccount(RoleDO roleDO) {
        return roleMapper.connectAccount(roleDO);
    }

    @CacheEvict(cacheNames = "mdc:role", allEntries = true)
    public int disConnectAccount(String roleId) {
        return roleMapper.disConnectAccount(roleId);
    }

    @Cacheable(value = "mdc:role", key = "'role.'.concat(#id)")
    public RoleDO get(String id) {
        return roleMapper.get(id);
    }

    public int insert(RoleDO RoleDO) {
        try {
            return roleMapper.insert(RoleDO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("新增数据出错，参数：{}", RoleDO);
            return 0;
        }
    }

    public int batchInsert(List<RoleDO> ts) {
        try {
            return roleMapper.batchInsert(ts);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:role", allEntries = true)
    public int update(RoleDO RoleDO) {
        try {

            return roleMapper.update(RoleDO);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:role", allEntries = true)
    public int batchUpdate(List<RoleDO> ts) {
        try {

            return roleMapper.batchUpdate(ts);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:role", allEntries = true)
    public int delete(RoleDO RoleDO) {
        try {

            return roleMapper.delete(RoleDO);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:role", allEntries = true)
    public int batchDelete(List<RoleDO> ts, String modifier) {
        try {
            return roleMapper.batchDelete(ts, modifier);
        } catch (RuntimeException e) {
            return 0;
        }
    }
}
