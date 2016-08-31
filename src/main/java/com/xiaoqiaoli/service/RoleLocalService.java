package com.xiaoqiaoli.service;

import com.xiaoqiaoli.entity.Role;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/19.
 */
public interface RoleLocalService {
    Role get(String id);

    Role getByCode(String code);

    List<Role> findByName(String name);

    List<Role> findByUsername(String username);

    int insert(Role roleDO);

    int update(Role roleDO);

    int delete(Role roleDO);

    int batchDelete(String[] ids);

    int connectAccount(Role roleDO);

    int disConnectAccount(String roleId);
}
