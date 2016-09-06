package com.xiaoqiaoli.service;

import com.xiaoqiaoli.entity.Role;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/19.
 */
public interface RoleLocalService {
    Role get(String id);

    Role getByCode(String code);

    List<Role> findByUsername(String username);

    Role insert(Role roleDO);

    Role update(Role roleDO);

    Role delete(Role roleDO);

    void batchDelete(List<Role> roles);

    Role connectAccount(Role role);

    Role disConnectAccount(Role role);
}
