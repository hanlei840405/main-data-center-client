package com.xiaoqiaoli.service;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.domain.OrganizationDO;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/12.
 */
public interface OrganizationLocalService {
    OrganizationDO localGet(String id);

    OrganizationDO localGetByCode(String code);

    List<OrganizationDO> localFindByCorporation(String corporationId);

    List<OrganizationDO> localFindByParent(String parentId);

    List<OrganizationDO> localFindByUsername(String username);

    List<OrganizationDO> localFindByFullCode(String fullCode);

    Page<OrganizationDO> localPage(Page<OrganizationDO> page, String parentId);

    OrganizationDO insert(OrganizationDO corporationDO);

    OrganizationDO update(OrganizationDO corporationDO);

    int delete(String id);

    int batchDelete(String[] ids);
}
