package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.Role;
import com.xiaoqiaoli.repository.BaseMapper;
import com.xiaoqiaoli.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/19.
 */
@Component
public class RoleManager extends BaseManager<Role, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(RoleManager.class);
    @Autowired
    private RoleRepository roleMapper;

    @Override
    BaseMapper<Role, String> getBaseMapper() {
        threadLocal.set(roleMapper);
        return threadLocal.get();
    }

    /**
     * 根据code查询角色信息
     *
     * @param code
     * @return
     */
    public Role getByCode(String code) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("code", code);
        return getOne(queryParams);
    }

    /**
     * 根据角色名称查询角色信息
     *
     * @param name
     * @return
     */
    public List<Role> findByName(String name) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", name);
        return find(queryParams);
    }

    /**
     * 根据账号查询用户角色信息
     *
     * @param username
     * @return
     */
    public List<Role> findByUsername(String username) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("username", username);
        return find(queryParams);
    }

    /**
     * 与账号关联
     *
     * @param roleDO
     * @return
     */
    public int connectAccount(Role roleDO) {
        return roleMapper.connectAccount(roleDO);
    }

    /**
     * 与账号解绑
     *
     * @param roleId
     * @return
     */
    public int disConnectAccount(String roleId) {
        return roleMapper.disConnectAccount(roleId);
    }
}
