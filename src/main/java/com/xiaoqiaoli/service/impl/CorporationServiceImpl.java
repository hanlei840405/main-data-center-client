package com.xiaoqiaoli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoqiaoli.domain.CorporationDO;
import com.xiaoqiaoli.dto.CorporationDTO;
import com.xiaoqiaoli.manager.CorporationManager;
import com.xiaoqiaoli.service.CorporationLocalService;
import com.xiaoqiaoli.service.client.CorporationRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/8/7.
 */
@Service("corporationService")
public class CorporationServiceImpl implements CorporationRemoteService, CorporationLocalService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CorporationManager.class);

    @Autowired
    private CorporationManager corporationManager;

    @Override
    public CorporationDO localGet(String id) {
        return corporationManager.get(id);
    }

    @Override
    public List<CorporationDO> localFindByName(String name) {
        return corporationManager.find(name, null, null);
    }

    @Override
    public List<CorporationDO> localFindByContact(String contact) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("contact", contact);
        return corporationManager.find(null, contact, null);
    }

    @Override
    public List<CorporationDO> localFindByLegalPerson(String legalPerson) {
        return corporationManager.find(null, null, legalPerson);
    }

    @Override
    public Page<CorporationDO> localPage(Page<CorporationDO> page, String name, String contact, String legalPerson) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Page<CorporationDO> corporationDOs = (Page<CorporationDO>) corporationManager.find(name, contact, legalPerson);
        return corporationDOs;
    }

    @Override
    public CorporationDO insert(CorporationDO corporationDO) {
        int result = corporationManager.insert(corporationDO);
        if (result > 0) {
            return corporationManager.get(corporationDO.getId());
        }
        return null;
    }

    @Override
    public CorporationDO update(CorporationDO corporationDO) {
        int result = corporationManager.update(corporationDO);
        if (result > 0) {
            return corporationManager.get(corporationDO.getId());
        }
        return null;
    }

    @Override
    public void batchEnable(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        corporationManager.enable(ids, principal.getUsername());
    }

    @Override
    public void batchDisable(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        corporationManager.disable(ids, principal.getUsername());
    }

    @Override
    public void delete(String id) {
        corporationManager.delete(localGet(id));
    }

    @Override
    public void batchDelete(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        corporationManager.batchDelete(ids, principal.getUsername());
    }

    @Override
    public CorporationDTO remoteGetByAccount(String username) {
        return null;
    }

    @Override
    public CorporationDTO remoteUpdate(CorporationDTO corporationDTO, String username) {
        return null;
    }
}
