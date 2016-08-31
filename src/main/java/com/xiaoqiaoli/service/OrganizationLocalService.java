package com.xiaoqiaoli.service;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.entity.Organization;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/12.
 */
public interface OrganizationLocalService {
    Organization localGet(String id);

    Organization localGetByCode(String code);

    List<Organization> localFindByCorporation(String corporationId);

    List<Organization> localFindByParent(String parentId);

    List<Organization> localFindByUsername(String username);

    List<Organization> localFindByFullCode(String fullCode);

    Page<Organization> localPage(Page<Organization> page, String parentId);

    Organization insert(Organization corporationDO);

    Organization update(Organization corporationDO);

    int delete(String id);

    int batchDelete(String[] ids);
}
