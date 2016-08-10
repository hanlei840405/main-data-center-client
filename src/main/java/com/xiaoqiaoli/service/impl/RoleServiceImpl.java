package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.domain.RoleDO;
import com.xiaoqiaoli.manager.RoleManager;
import com.xiaoqiaoli.service.RoleLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/19.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleLocalService {

    @Autowired
    private RoleManager roleManager;

    @Override
    @Cacheable(cacheNames = "mdc:role:id", key = "'/roleService/get/'.concat(#id)")
    public RoleDO get(String id) {
        return roleManager.get(id);
    }

    @Override
    @Cacheable(cacheNames = "mdc:role:code", key = "'/roleService/getByCode/'.concat(#code)")
    public RoleDO getByCode(String code) {
        return roleManager.getByCode(code);
    }

    @Override
    @Cacheable(cacheNames = "mdc:role:name", key = "'/roleService/findByName/'.concat(#name)")
    public List<RoleDO> findByName(String name) {
        return roleManager.findByName(name);
    }

    @Override
    @Cacheable(cacheNames = "mdc:role:username", key = "'/roleService/findByUsername/'.concat(#username)")
    public List<RoleDO> findByUsername(String username) {
        return roleManager.findByUsername(username);
    }

    @Override
    public int insert(RoleDO roleDO) {
        return roleManager.insert(roleDO);
    }

    @Override
    public int batchInsert(List<RoleDO> roleDOs) {
        return roleManager.batchInsert(roleDOs);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public int update(RoleDO roleDO) {
        return roleManager.update(roleDO);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public int batchUpdate(List<RoleDO> roleDOs) {
        return roleManager.batchUpdate(roleDOs);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public int delete(RoleDO roleDO) {
        return roleManager.delete(roleDO);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public int batchDelete(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return roleManager.batchDelete(roleManager.findByMultiIds(ids), principal.getUsername());
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public int connectAccount(RoleDO roleDO) {
        return roleManager.connectAccount(roleDO);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public int disConnectAccount(String roleId) {
        return roleManager.disConnectAccount(roleId);
    }
}
