package com.xiaoqiaoli.service;

import com.xiaoqiaoli.domain.RoleDO;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/19.
 */
public interface RoleLocalService {
    RoleDO get(String id);

    RoleDO getByCode(String code);

    List<RoleDO> findByName(String name);

    List<RoleDO> findByUsername(String username);

    int insert(RoleDO roleDO);

    int batchInsert(List<RoleDO> roleDOs);

    int update(RoleDO roleDO);

    int batchUpdate(List<RoleDO> roleDOs);

    int delete(RoleDO roleDO);

    int batchDelete(List<RoleDO> roleDOs);

    int connectAccount(RoleDO roleDO);

    int disConnectAccount(String roleId);
}
