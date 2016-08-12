package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.OrganizationDO;
import com.xiaoqiaoli.mapper.BaseMapper;
import com.xiaoqiaoli.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/8/12.
 */
@Component
public class OrganizationManager extends BaseManager<OrganizationDO, String> {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    BaseMapper<OrganizationDO, String> getBaseMapper() {
        return organizationMapper;
    }

    /**
     * 根据部门code查询
     *
     * @param code
     * @return
     */
    public OrganizationDO getByCode(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        return getOne(params);
    }

    /**
     * 根据企业查询
     *
     * @param corporationId
     * @return
     */
    public List<OrganizationDO> findByCorporation(String corporationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("corporationId", corporationId);
        return find(params);
    }

    /**
     * 根据上级部门code查询
     *
     * @param parentId
     * @return
     */
    public List<OrganizationDO> findByParent(String parentId) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", parentId);
        return find(params);
    }

    /**
     * 根据负责人账号查询
     *
     * @param username
     * @return
     */
    public List<OrganizationDO> findByUsername(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        return find(params);
    }

    /**
     * 根据全路径编号查询
     *
     * @param fullCode
     * @return
     */
    public List<OrganizationDO> findByFullCode(String fullCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("fullCode", fullCode);
        return find(params);
    }
}
