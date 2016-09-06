package com.xiaoqiaoli.service;

import com.xiaoqiaoli.entity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/12.
 */
public interface OrganizationLocalService {
    Organization localGet(String id);

    Organization localGetByCode(String code);

    List<Organization> localFindByIds(String[] ids);

    List<Organization> localFindByCorporation(String corporationId);

    List<Organization> localFindByParent(String parentId);

    List<Organization> localFindByManager(String username);

    List<Organization> localFindByFullCode(String fullCode);

    Page<Organization> localPage(Pageable pageable, String parentId);

    Organization insert(Organization corporationDO);

    Organization update(Organization corporationDO);

    Organization delete(Organization corporationDO);

    void batchDelete(List<Organization> organizations);
}
