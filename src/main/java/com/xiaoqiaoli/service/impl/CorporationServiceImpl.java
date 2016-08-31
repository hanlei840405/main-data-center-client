package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.dto.CorporationDTO;
import com.xiaoqiaoli.entity.Account;
import com.xiaoqiaoli.entity.Corporation;
import com.xiaoqiaoli.manager.AccountManager;
import com.xiaoqiaoli.manager.CorporationManager;
import com.xiaoqiaoli.service.CorporationLocalService;
import com.xiaoqiaoli.service.client.CorporationRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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

    @Autowired
    private AccountManager accountManager;

    @Override
    @Cacheable(cacheNames = "mdc:corporation:username", key = "'/corporationService/remoteGetByAccount/'.concat(#username)")
    public CorporationDTO remoteGetByAccount(String username) {
        return null;
    }

    @Override
    @Cacheable(cacheNames = "mdc:corporation:id", key = "'/corporationService/localGet/'.concat(#id)")
    public Corporation localGet(String id) {
        return corporationManager.get(id);
    }

    @Override
    @Cacheable(cacheNames = "mdc:corporation:name", key = "'/corporationService/localFindByName/'.concat(#name)")
    public List<Corporation> localFindByName(String name) {
        return corporationManager.findByName(name);
    }

    @Override
    @Cacheable(cacheNames = "mdc:corporation:contact", key = "'/corporationService/localFindByContact/'.concat(#contact)")
    public List<Corporation> localFindByContact(String contact) {
        return corporationManager.findByContact(contact);
    }

    @Override
    @Cacheable(cacheNames = "mdc:corporation:legalPerson", key = "'/corporationService/localFindByLegalPerson/'.concat(#legalPerson)")
    public List<Corporation> localFindByLegalPerson(String legalPerson) {
        return corporationManager.findByLegalPerson(legalPerson);
    }

    @Override
    public Page<Corporation> localPage(Page<Corporation> page, String payload) {
        return null;
    }

    @Override
    public Corporation insert(Corporation corporation) {
        return corporationManager.save(corporation);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public Corporation update(Corporation corporation) {
        return corporationManager.save(corporation);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public int batchEnable(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account modifier = accountManager.getByUsername(principal.getUsername());
        return corporationManager.enableOrDisable(ids, modifier, "3");
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public int batchDisable(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account modifier = accountManager.getByUsername(principal.getUsername());
        return corporationManager.enableOrDisable(ids, modifier, "1");
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public int delete(String id) {
        return corporationManager.delete(localGet(id));
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public int batchDelete(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return corporationManager.batchDelete(corporationManager.findByMultiIds(ids), principal.getUsername());
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation", allEntries = true, beforeInvocation = true)
    public int adjust(Corporation corporationDO) {
        return corporationManager.adjust(corporationDO);
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation", allEntries = true, beforeInvocation = true)
    public CorporationDTO remoteUpdate(CorporationDTO corporationDTO, String username) {
        return null;
    }
}
