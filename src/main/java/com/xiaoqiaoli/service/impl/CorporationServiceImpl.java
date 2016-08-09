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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/7.
 */
@Service("corporationService")
public class CorporationServiceImpl implements CorporationRemoteService, CorporationLocalService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CorporationManager.class);

    @Autowired
    private CorporationManager corporationManager;

    @Override
    @Cacheable(cacheNames = "mdc:corporation:username", key = "'/corporationService/remoteGetByAccount/'.concat(#username)")
    public CorporationDTO remoteGetByAccount(String username) {
        return null;
    }

    @Override
    @Cacheable(cacheNames = "mdc:corporation:id", key = "'/corporationService/localGet/'.concat(#id)")
    public CorporationDO localGet(String id) {
        return corporationManager.get(id);
    }

    @Override
    @Cacheable(cacheNames = "mdc:corporation:name", key = "'/corporationService/localFindByName/'.concat(#name)")
    public List<CorporationDO> localFindByName(String name) {
        return corporationManager.findByName(name);
    }

    @Override
    @Cacheable(cacheNames = "mdc:corporation:contact", key = "'/corporationService/localFindByContact/'.concat(#contact)")
    public List<CorporationDO> localFindByContact(String contact) {
        return corporationManager.findByContact(contact);
    }

    @Override
    @Cacheable(cacheNames = "mdc:corporation:legalPerson", key = "'/corporationService/localFindByLegalPerson/'.concat(#legalPerson)")
    public List<CorporationDO> localFindByLegalPerson(String legalPerson) {
        return corporationManager.findByLegalPerson(legalPerson);
    }

    @Override
    public Page<CorporationDO> localPage(Page<CorporationDO> page, String name, String contact, String legalPerson) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Page<CorporationDO> corporationDOs = (Page<CorporationDO>) corporationManager.findByParams(name, contact, legalPerson);
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
    @CacheEvict(cacheNames = "mdc:corporation:username,mdc:corporation:id,mdc:corporation:name,mdc:corporation:contact,mdc:corporation:legalPerson", allEntries = true)
    public CorporationDO update(CorporationDO corporationDO) {
        int result = corporationManager.update(corporationDO);
        if (result > 0) {
            return corporationManager.get(corporationDO.getId());
        }
        return null;
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation:username,mdc:corporation:id,mdc:corporation:name,mdc:corporation:contact,mdc:corporation:legalPerson", allEntries = true)
    public int batchEnable(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return corporationManager.enable(ids, principal.getUsername());
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation:username,mdc:corporation:id,mdc:corporation:name,mdc:corporation:contact,mdc:corporation:legalPerson", allEntries = true)
    public int batchDisable(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return corporationManager.disable(ids, principal.getUsername());
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation:username,mdc:corporation:id,mdc:corporation:name,mdc:corporation:contact,mdc:corporation:legalPerson", allEntries = true)
    public int delete(String id) {
        return corporationManager.delete(localGet(id));
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation:username,mdc:corporation:id,mdc:corporation:name,mdc:corporation:contact,mdc:corporation:legalPerson", allEntries = true)
    public int batchDelete(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return corporationManager.batchDelete(corporationManager.findByMultiIds(ids), principal.getUsername());
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation:username,mdc:corporation:id,mdc:corporation:name,mdc:corporation:contact,mdc:corporation:legalPerson", allEntries = true)
    public int adjust(CorporationDO corporationDO) {
        return corporationManager.adjust(corporationDO);
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation:username,mdc:corporation:id,mdc:corporation:name,mdc:corporation:contact,mdc:corporation:legalPerson", allEntries = true)
    public CorporationDTO remoteUpdate(CorporationDTO corporationDTO, String username) {
        return null;
    }
}
