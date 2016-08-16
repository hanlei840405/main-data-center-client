package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.mapper.BaseMapper;
import com.xiaoqiaoli.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Component
public class UserManager extends BaseManager<UserDO, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserManager.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    BaseMapper<UserDO, String> getBaseMapper() {
        threadLocal.set(userMapper);
        return threadLocal.get();
    }

    public UserDO getByEmail(String email) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("email", email);
        return getOne(queryParams);
    }

    public UserDO getByTelephone(String telephone) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("telephone", telephone);
        return getOne(queryParams);
    }

    public UserDO getByQq(String qq) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("qq", qq);
        return getOne(queryParams);
    }

    public UserDO getByWx(String wx) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("wx", wx);
        return getOne(queryParams);
    }

    public UserDO getByWeiBo(String weiBo) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("weiBo", weiBo);
        return getOne(queryParams);
    }

    public List<UserDO> findByRealName(String realName) {
        Map<String, Object> params = new HashMap<>();
        params.put("realName", realName);
        return find(params);
    }

    public List<UserDO> findByParams(String realName, String telephone, String qq, String wx, String weiBo, String corporationId, String organizationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("realName", realName);
        params.put("telephone", telephone);
        params.put("qq", qq);
        params.put("wx", wx);
        params.put("weiBo", weiBo);
        params.put("corporationId", corporationId);
        params.put("organizationId", organizationId);
        return find(params);
    }

    public int disConnectRole(String userId) {
        return userMapper.disConnectRole(userId);
    }
}
