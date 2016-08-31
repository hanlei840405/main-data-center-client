package com.xiaoqiaoli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoqiaoli.entity.Organization;
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
    public Organization localGet(String id) {
        return organizationManager.get(id);
    }

    @Override
    public Organization localGetByCode(String code) {
        return organizationManager.getByCode(code);
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
    public List<Organization> localFindByUsername(String username) {
        return organizationManager.findByUsername(username);
    }

    @Override
    public List<Organization> localFindByFullCode(String fullCode) {
        return organizationManager.findByFullCode(fullCode);
    }

    @Override
    public Page<Organization> localPage(Page<Organization> page, String parentId) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Page<Organization> organizationDOs = (Page<Organization>) organizationManager.findByParent(parentId);
        return organizationDOs;
    }

    @Override
    public Organization insert(Organization organizationDO) {
        int result = organizationManager.insert(organizationDO);
        if (result > 0) {
            return organizationManager.get(organizationDO.getId());
        }
        return null;
    }

    @Override
    public Organization update(Organization organizationDO) {
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
