package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.RoleDO;
import com.xiaoqiaoli.mapper.BaseMapper;
import com.xiaoqiaoli.mapper.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by hanlei6 on 2016/7/19.
 */
@Component
public class RoleManager extends BaseManager<RoleDO,String>{
    private final static Logger LOGGER = LoggerFactory.getLogger(AccountManager.class);
    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected BaseMapper<RoleDO, String> baseMapper() {
        return roleMapper;
    }

    @Cacheable(cacheNames = "mdc:role", key = "'role.'.concat(#code)")
    public RoleDO getByCode(String code) {
        RoleDO query = new RoleDO();
        query.setCode(code);
        return roleMapper.getOne(query);
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

    public int connectAccount(RoleDO roleDO) {
        deleteCache();
        return roleMapper.connectAccount(roleDO);
    }

    public int disConnectAccount(String roleId) {
        deleteCache();
        return roleMapper.disConnectAccount(roleId);
    }

    @Override
    @CacheEvict(cacheNames = "mdc:role", allEntries = true)
    public void deleteCache() {
        String uuid = UUID.randomUUID().toString();
        try {
            LOGGER.info("编号:{},清除缓存,时间:{},参数为:mdc:role", uuid, new Date());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
