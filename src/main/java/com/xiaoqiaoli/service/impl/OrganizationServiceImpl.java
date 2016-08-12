package com.xiaoqiaoli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoqiaoli.domain.OrganizationDO;
import com.xiaoqiaoli.manager.OrganizationManager;
import com.xiaoqiaoli.service.OrganizationLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    public OrganizationDO localGet(String id) {
        return organizationManager.get(id);
    }

    @Override
    public OrganizationDO localGetByCode(String code) {
        return organizationManager.getByCode(code);
    }

    @Override
    public List<OrganizationDO> localFindByCorporation(String corporationId) {
        return organizationManager.findByCorporation(corporationId);
    }

    @Override
    public List<OrganizationDO> localFindByParent(String parentId) {
        return organizationManager.findByParent(parentId);
    }

    @Override
    public List<OrganizationDO> localFindByUsername(String username) {
        return organizationManager.findByUsername(username);
    }

    @Override
    public List<OrganizationDO> localFindByFullCode(String fullCode) {
        return organizationManager.findByFullCode(fullCode);
    }

    @Override
    public Page<OrganizationDO> localPage(Page<OrganizationDO> page, String parentId) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Page<OrganizationDO> organizationDOs = (Page<OrganizationDO>) organizationManager.findByParent(parentId);
        return organizationDOs;
    }

    @Override
    public OrganizationDO insert(OrganizationDO organizationDO) {
        int result = organizationManager.insert(organizationDO);
        if (result > 0) {
            return organizationManager.get(organizationDO.getId());
        }
        return null;
    }

    @Override
    public OrganizationDO update(OrganizationDO organizationDO) {
        int result = organizationManager.update(organizationDO);
        if (result > 0) {
            return organizationManager.get(organizationDO.getId());
        }
        return null;
    }

    @Override
    public int delete(String id) {
        return organizationManager.delete(localGet(id));
    }

    @Override
    public int batchDelete(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return organizationManager.batchDelete(organizationManager.findByMultiIds(ids), principal.getUsername());
    }
}
