package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.CorporationDO;
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
public class CorporationManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(CorporationManager.class);

    @Autowired
    private CorporationMapper corporationMapper;

    public CorporationDO get(String id) {
        return corporationMapper.get(id);
    }

    public List<CorporationDO> findByMultiIds(String[] ids) {
        return corporationMapper.findByMultiIds(ids);
    }

    public List<CorporationDO> find(String name, String contact, String legalPername) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", name);
        queryParams.put("contact", contact);
        queryParams.put("legalPername", legalPername);
        return corporationMapper.find(queryParams);
    }

    public int insert(CorporationDO corporationDO) {
        try {
            return corporationMapper.insert(corporationDO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("新增数据出错,参数为:{}", corporationDO);
            return 0;
        }
    }

    public int update(CorporationDO corporationDO) {
        try {
            return corporationMapper.update(corporationDO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("修改数据出错,参数为:{}", corporationDO);
            return 0;
        }
    }

    public int enable(String[] ids, String modifier) {
        try {
            return corporationMapper.batchEnable(findByMultiIds(ids), modifier);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("修改数据出错,参数为:{},{}", ids, modifier);
            return 0;
        }
    }

    public int disable(String[] ids, String modifier) {
        try {
            return corporationMapper.batchDisable(findByMultiIds(ids), modifier);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("修改数据出错,参数为:{},{}", ids, modifier);
            return 0;
        }
    }

    public int delete(CorporationDO corporationDO) {
        try {
            return corporationMapper.delete(corporationDO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("删除数据出错,参数为:{}", corporationDO);
            return 0;
        }
    }

    public int batchDelete(String[] ids, String modifier) {
        try {
            return corporationMapper.batchDelete(findByMultiIds(ids), modifier);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("删除数据出错,参数为:{},{}", ids, modifier);
            return 0;
        }
    }
}
