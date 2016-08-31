package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.User;
import com.xiaoqiaoli.repository.BaseMapper;
import com.xiaoqiaoli.repository.UserRepository;
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
public class UserManager extends BaseManager<User, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserManager.class);
    @Autowired
    private UserRepository userMapper;

    @Override
    BaseMapper<User, String> getBaseMapper() {
        threadLocal.set(userMapper);
        return threadLocal.get();
    }

    public User getByEmail(String email) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("email", email);
        return getOne(queryParams);
    }

    public User getByTelephone(String telephone) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("telephone", telephone);
        return getOne(queryParams);
    }

    public User getByQq(String qq) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("qq", qq);
        return getOne(queryParams);
    }

    public User getByWx(String wx) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("wx", wx);
        return getOne(queryParams);
    }

    public User getByWeiBo(String weiBo) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("weiBo", weiBo);
        return getOne(queryParams);
    }

    public List<User> findByRealName(String realName) {
        Map<String, Object> params = new HashMap<>();
        params.put("realName", realName);
        return find(params);
    }

    public List<User> findByParams(String realName, String telephone, String qq, String wx, String weiBo, String corporationId, String organizationId) {
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
