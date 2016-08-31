package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.Organization;
import com.xiaoqiaoli.repository.BaseMapper;
import com.xiaoqiaoli.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/8/12.
 */
@Component
public class OrganizationManager extends BaseManager<Organization, String> {
    @Autowired
    private OrganizationRepository organizationMapper;

    @Override
    BaseMapper<Organization, String> getBaseMapper() {
        return organizationMapper;
    }

    /**
     * 根据部门code查询
     *
     * @param code
     * @return
     */
    public Organization getByCode(String code) {
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
    public List<Organization> findByCorporation(String corporationId) {
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
    public List<Organization> findByParent(String parentId) {
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
    public List<Organization> findByUsername(String username) {
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
    public List<Organization> findByFullCode(String fullCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("fullCode", fullCode);
        return find(params);
    }
}
