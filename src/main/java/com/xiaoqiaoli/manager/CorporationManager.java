package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.CorporationDO;
import com.xiaoqiaoli.mapper.BaseMapper;
import com.xiaoqiaoli.mapper.CorporationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/8/7.
 */
@Component
public class CorporationManager extends BaseManager<CorporationDO, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(CorporationManager.class);

    @Autowired
    private CorporationMapper corporationMapper;

    @Override
    BaseMapper<CorporationDO, String> getBaseMapper() {
        threadLocal.set(corporationMapper);
        return threadLocal.get();
    }

    /**
     * 根据企业名称查询入驻企业信息
     *
     * @param name
     * @return
     */
    public List<CorporationDO> findByName(String name) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", name);
        return this.find(queryParams);
    }

    /**
     * 根据联系人查询入驻企业信息
     *
     * @param contact
     * @return
     */
    public List<CorporationDO> findByContact(String contact) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("contact", contact);
        return this.find(queryParams);
    }

    /**
     * 根据法人查询入驻企业信息
     *
     * @param legalPerson
     * @return
     */
    public List<CorporationDO> findByLegalPerson(String legalPerson) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("legalPerson", legalPerson);
        return this.find(queryParams);
    }

    /**
     * 根据企业名称,联系人,法人联合查询入驻企业信息
     *
     * @param name
     * @param contact
     * @param legalPerson
     * @return
     */
    public List<CorporationDO> findByParams(String name, String contact, String legalPerson) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", name);
        queryParams.put("contact", contact);
        queryParams.put("legalPerson", legalPerson);
        return this.find(queryParams);
    }

    /**
     * 入驻企业变为状态正常
     *
     * @param ids
     * @param modifier
     * @return
     */
    public int enable(String[] ids, String modifier) {
        try {
            List<CorporationDO> corporationDOs = findByMultiIds(ids);
            Map<String, Object> params = new HashMap<>();
            params.put("collection", corporationDOs);
            params.put("modifier", modifier);
            return corporationMapper.batchEnable(params);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("入驻企业变为状态正常失败,参数为:{},{}", ids, modifier);
            return 0;
        }
    }

    /**
     * 将入驻企业状态改变为欠费禁用
     *
     * @param ids
     * @param modifier
     * @return
     */
    public int disable(String[] ids, String modifier) {
        try {
            List<CorporationDO> corporationDOs = findByMultiIds(ids);
            Map<String, Object> params = new HashMap<>();
            params.put("collection", corporationDOs);
            params.put("modifier", modifier);
            return corporationMapper.batchDisable(params);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("将入驻企业状态改变为欠费禁用失败,参数为:{},{}", ids, modifier);
            return 0;
        }
    }

    /**
     * 根据入驻企业缴费情况，调整至相应会员级别
     *
     * @param corporationDO
     * @return
     */
    public int adjust(CorporationDO corporationDO) {
        try {
            return corporationMapper.adjust(corporationDO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("调整入驻企业等级失败,参数为:{},{}", corporationDO);
            return 0;
        }
    }
}
