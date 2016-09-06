package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.dto.CorporationDTO;
import com.xiaoqiaoli.entity.Corporation;
import com.xiaoqiaoli.manager.CorporationManager;
import com.xiaoqiaoli.service.CorporationLocalService;
import com.xiaoqiaoli.service.client.CorporationRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Corporation localGet(String id) {
        return corporationManager.get(id);
    }

    @Override
    public List<Corporation> localFindByIds(String[] ids) {
        return corporationManager.findByIds(ids);
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
    public Page<Corporation> localPage(Pageable pageable, String name, String legalPerson, String contact) {
        return corporationManager.page(pageable, name, legalPerson, contact);
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
    public void batchEnable(List<Corporation> corporations) {
        corporationManager.batch(corporations);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public void batchDisable(List<Corporation> corporations) {
        corporationManager.batch(corporations);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public Corporation delete(Corporation corporation) {
        return corporationManager.save(corporation);
    }

    @Override
    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public void batchDelete(List<Corporation> corporations) {
        corporationManager.batch(corporations);
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation", allEntries = true, beforeInvocation = true)
    public Corporation adjust(Corporation corporation) {
        return corporationManager.save(corporation);
    }

    @Override
    @CacheEvict(cacheNames = "mdc:corporation", allEntries = true, beforeInvocation = true)
    public CorporationDTO remoteUpdate(CorporationDTO corporationDTO, String username) {
        // TODO 根据发起请求的用户查询企业信息，如果查询到，则进行更新操作
//        Corporation corporation = corporationManager.get(corporationDTO.get)
        return null;
    }
}
