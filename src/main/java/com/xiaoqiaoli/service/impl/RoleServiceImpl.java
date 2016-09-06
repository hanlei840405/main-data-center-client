package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.entity.Role;
import com.xiaoqiaoli.manager.RoleManager;
import com.xiaoqiaoli.service.RoleLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
//  @Cacheable(cacheNames = "mdc:role:id", key = "'/roleService/get/'.concat(#id)")
    public Role get(String id) {
        return roleManager.get(id);
    }

    @Override
//  @Cacheable(cacheNames = "mdc:role:code", key = "'/roleService/getByCode/'.concat(#code)")
    public Role getByCode(String code) {
        return roleManager.getByCode(code);
    }

    @Override
    //    @Cacheable(cacheNames = "mdc:role:username", key = "'/roleService/findByUsername/'.concat(#username)")
    public List<Role> findByUsername(String username) {
        return roleManager.findByUsername(username);
    }

    @Override
    public Role insert(Role role) {
        return roleManager.insert(role);
    }

    @Override
    //    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public Role update(Role role) {
        return roleManager.update(role);
    }

    @Override
    //    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public Role delete(Role role) {
        return roleManager.delete(role);
    }

    @Override
    //    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public void batchDelete(List<Role> roles) {
        roleManager.batch(roles);
    }

    @Override
    //    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public Role connectAccount(Role role) {
        return roleManager.connectAccount(role);
    }

    @Override
    //    @CacheEvict(cacheNames = {"mdc:role:id", "mdc:role:code", "mdc:role:name", "mdc:role:username"}, allEntries = true)
    public Role disConnectAccount(Role role) {
        return roleManager.disConnectAccount(role);
    }
}
