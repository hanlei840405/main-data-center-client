package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.entity.Organization;
import com.xiaoqiaoli.manager.OrganizationManager;
import com.xiaoqiaoli.service.OrganizationLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/12.
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationLocalService {

    @Autowired
    private OrganizationManager organizationManager;

    @Override
    public Organization localGet(String id) {
        return organizationManager.get(id);
    }

    @Override
    public Organization localGetByCode(String code) {
        return organizationManager.getByCode(code);
    }

    @Override
    public List<Organization> localFindByIds(String[] ids) {
        return organizationManager.findByIds(ids);
    }

    @Override
    public List<Organization> localFindByCorporation(String corporationId) {
        return organizationManager.findByCorporation(corporationId);
    }

    @Override
    public List<Organization> localFindByParent(String parentId) {
        return organizationManager.findByParent(parentId);
    }

    @Override
    public List<Organization> localFindByManager(String username) {
        return organizationManager.findByManager(username);
    }

    @Override
    public List<Organization> localFindByFullCode(String fullCode) {
        return organizationManager.findByFullCode(fullCode);
    }

    @Override
    public Page<Organization> localPage(Pageable pageable, String parentId) {
        Page<Organization> organizationDOs = organizationManager.page(pageable, parentId);
        return organizationDOs;
    }

    @Override
    public Organization insert(Organization organizationDO) {
        return organizationManager.save(organizationDO);
    }

    @Override
    public Organization update(Organization organizationDO) {
        return organizationManager.save(organizationDO);
    }

    @Override
    public Organization delete(Organization organizationDO) {
        return organizationManager.save(organizationDO);
    }

    @Override
    public void batchDelete(List<Organization> organizations) {
        organizationManager.batch(organizations);
    }
}
