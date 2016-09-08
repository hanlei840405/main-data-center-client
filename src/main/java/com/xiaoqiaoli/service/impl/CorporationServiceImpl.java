package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.dto.CorporationDTO;
import com.xiaoqiaoli.entity.Corporation;
import com.xiaoqiaoli.manager.CorporationManager;
import com.xiaoqiaoli.service.CorporationLocalService;
import com.xiaoqiaoli.service.client.CorporationRemoteService;
import com.xiaoqiaoli.vo.CorporationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//    @Cacheable(cacheNames = "mdc:corporation:username", key = "'/corporationService/remoteGetByAccount/'.concat(#username)")
    public CorporationDTO remoteGetByAccount(String username) {
        return null;
    }

    @Override
//    @Cacheable(cacheNames = "mdc:corporation:id", key = "'/corporationService/localGet/'.concat(#id)")
    public CorporationVO localGet(String id) {
        CorporationVO vo = new CorporationVO();
        BeanUtils.copyProperties(corporationManager.get(id), vo);
        return vo;
    }

    @Override
    public List<CorporationVO> localFindByIds(String[] ids) {
        List<CorporationVO> vos = new ArrayList<>();
        corporationManager.findByIds(ids).forEach(corporation -> {
            CorporationVO vo = new CorporationVO();
            BeanUtils.copyProperties(corporation, vo);
            vos.add(vo);
        });
        return vos;
    }

    @Override
//    @Cacheable(cacheNames = "mdc:corporation:name", key = "'/corporationService/localFindByName/'.concat(#name)")
    public List<CorporationVO> localFindByName(String name) {
        List<CorporationVO> vos = new ArrayList<>();
        corporationManager.findByName(name).forEach(corporation -> {
            CorporationVO vo = new CorporationVO();
            BeanUtils.copyProperties(corporation, vo);
            vos.add(vo);
        });
        return vos;
    }

    @Override
//    @Cacheable(cacheNames = "mdc:corporation:contact", key = "'/corporationService/localFindByContact/'.concat(#contact)")
    public List<CorporationVO> localFindByContact(String contact) {
        List<CorporationVO> vos = new ArrayList<>();
        corporationManager.findByContact(contact).forEach(corporation -> {
            CorporationVO vo = new CorporationVO();
            BeanUtils.copyProperties(corporation, vo);
            vos.add(vo);
        });
        return vos;
    }

    @Override
//    @Cacheable(cacheNames = "mdc:corporation:legalPerson", key = "'/corporationService/localFindByLegalPerson/'.concat(#legalPerson)")
    public List<CorporationVO> localFindByLegalPerson(String legalPerson) {
        List<CorporationVO> vos = new ArrayList<>();
        corporationManager.findByLegalPerson(legalPerson).forEach(corporation -> {
            CorporationVO vo = new CorporationVO();
            BeanUtils.copyProperties(corporation, vo);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public Page<CorporationVO> localPage(Pageable pageable, String name, String legalPerson, String contact) {
        List<CorporationVO> vos = new ArrayList<>();
        Page<Corporation> page = corporationManager.page(pageable, name, legalPerson, contact);
        page.forEach(corporation -> {
            CorporationVO vo = new CorporationVO();
            BeanUtils.copyProperties(corporation, vo);
            vos.add(vo);
        });
        return new PageImpl<>(vos, pageable, page.getTotalElements());
    }

    @Override
    public CorporationVO insert(CorporationVO corporationVO) {
        Corporation corporation = new Corporation();
        BeanUtils.copyProperties(corporationVO, corporation);
        corporation = corporationManager.save(corporation);
        BeanUtils.copyProperties(corporation, corporationVO);
        return corporationVO;
    }

    @Override
//    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public CorporationVO update(CorporationVO corporationVO) {
        Corporation corporation = new Corporation();
        BeanUtils.copyProperties(corporationVO, corporation);
        corporation = corporationManager.save(corporation);
        BeanUtils.copyProperties(corporation, corporationVO);
        return corporationVO;
    }

    @Override
//    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public void batchEnable(List<CorporationVO> corporationVOs) {
        List<Corporation> corporations = new ArrayList<>();
        corporationVOs.forEach(corporationVO -> {
            Corporation corporation = new Corporation();
            BeanUtils.copyProperties(corporationVO, corporation);
            corporations.add(corporation);
        });
        corporationManager.batch(corporations);
    }

    @Override
//    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public void batchDisable(List<CorporationVO> corporationVOs) {
        List<Corporation> corporations = new ArrayList<>();
        corporationVOs.forEach(corporationVO -> {
            Corporation corporation = new Corporation();
            BeanUtils.copyProperties(corporationVO, corporation);
            corporations.add(corporation);
        });
        corporationManager.batch(corporations);
    }

    @Override
//    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public CorporationVO delete(CorporationVO corporationVO) {
        Corporation corporation = new Corporation();
        BeanUtils.copyProperties(corporationVO, corporation);
        corporation = corporationManager.save(corporation);
        BeanUtils.copyProperties(corporation, corporationVO);
        return corporationVO;
    }

    @Override
//    @CacheEvict(cacheNames = {"mdc:corporation:username", "mdc:corporation:id", "mdc:corporation:name", "mdc:corporation:contact", "mdc:corporation:legalPerson"}, allEntries = true, beforeInvocation = true)
    public void batchDelete(List<CorporationVO> corporationVOs) {
        List<Corporation> corporations = new ArrayList<>();
        corporationVOs.forEach(corporationVO -> {
            Corporation corporation = new Corporation();
            BeanUtils.copyProperties(corporationVO, corporation);
            corporations.add(corporation);
        });
        corporationManager.batch(corporations);
    }

    @Override
//    @CacheEvict(cacheNames = "mdc:corporation", allEntries = true, beforeInvocation = true)
    public CorporationVO adjust(CorporationVO corporationVO) {
        Corporation corporation = new Corporation();
        BeanUtils.copyProperties(corporationVO, corporation);
        corporation = corporationManager.save(corporation);
        BeanUtils.copyProperties(corporation, corporationVO);
        return corporationVO;
    }

    @Override
//    @CacheEvict(cacheNames = "mdc:corporation", allEntries = true, beforeInvocation = true)
    public CorporationDTO remoteUpdate(CorporationDTO corporationDTO, String username) {
        // TODO 根据发起请求的用户查询企业信息，如果查询到，则进行更新操作
//        Corporation corporation = corporationManager.get(corporationDTO.get)
        return null;
    }
}
