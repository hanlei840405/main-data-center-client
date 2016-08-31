package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.Account;
import com.xiaoqiaoli.entity.Corporation;
import com.xiaoqiaoli.repository.CorporationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by hanlei6 on 2016/8/7.
 */
@Component
public class CorporationManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(CorporationManager.class);

    @Autowired
    private CorporationRepository corporationRepository;

    /**
     * 根据id查询企业信息
     *
     * @param id
     * @return
     */
    public Corporation get(String id) {
        return corporationRepository.findOne(id);
    }

    /**
     * 根据企业名称查询入驻企业信息
     *
     * @param name
     * @return
     */
    public List<Corporation> findByName(String name) {
        return corporationRepository.findByNameLike(name);
    }

    /**
     * 根据联系人查询入驻企业信息
     *
     * @param contact
     * @return
     */
    public List<Corporation> findByContact(String contact) {
        return corporationRepository.findByContactLike(contact);
    }

    /**
     * 根据法人查询入驻企业信息
     *
     * @param legalPerson
     * @return
     */
    public List<Corporation> findByLegalPerson(String legalPerson) {
        return corporationRepository.findByLegalPersonLike(legalPerson);
    }

    /**
     * 入驻企业变为状态正常/欠费禁用
     *
     * @param ids
     * @param modifier
     * @return
     */
    public int enableOrDisable(String[] ids, Account modifier, String status) {
        try {
            List<Corporation> corporations = corporationRepository.findByIdIn(ids);
            for (Corporation corporation : corporations) {
                corporation.setStatus(status);
                corporation.setModifier(modifier);
                corporation.setModified(new Date());
            }
            corporationRepository.save(corporations);
            return 1;
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("入驻企业变为状态正常失败,参数为:{},{},{}", ids, modifier, status);
            return 0;
        }
    }

    public Corporation save(Corporation corporation) {
        try {
            return corporationRepository.save(corporation);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("入驻企业保存失败,参数为:{}", corporation);
            return null;
        }
    }
}
