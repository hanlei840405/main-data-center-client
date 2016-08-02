package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.domain.RoleDO;
import com.xiaoqiaoli.manager.RoleManager;
import com.xiaoqiaoli.service.RoleLocalService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RoleDO get(String id) {
        return roleManager.get(id);
    }

    @Override
    public RoleDO getByCode(String code) {
        return roleManager.getByCode(code);
    }

    @Override
    public List<RoleDO> findByName(String name) {
        return roleManager.findByName(name);
    }

    @Override
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
    public int update(RoleDO roleDO) {
        return roleManager.update(roleDO);
    }

    @Override
    public int batchUpdate(List<RoleDO> roleDOs) {
        return roleManager.batchUpdate(roleDOs);
    }

    @Override
    public int delete(RoleDO roleDO) {
        return roleManager.delete(roleDO);
    }

    @Override
    public int batchDelete(List<RoleDO> roleDOs) {
        return roleManager.batchDelete(roleDOs);
    }

    @Override
    public int connectAccount(RoleDO roleDO) {
        return roleManager.connectAccount(roleDO);
    }

    @Override
    public int disConnectAccount(String roleId) {
        return roleManager.disConnectAccount(roleId);
    }
}
