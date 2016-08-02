package com.xiaoqiaoli.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.mapper.BaseMapper;
import com.xiaoqiaoli.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Component
public class UserManager extends BaseManager<UserDO, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserManager.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseMapper<UserDO, String> baseMapper() {
        return userMapper;
    }

    @Cacheable(cacheNames = "mdc:user", key = "'user'.concat(#realName)")
    public List<UserDO> findByRealName(String realName) {
        return userMapper.findByRealName(realName);
    }

    @Cacheable(cacheNames = "mdc:user", key = "'user'.concat(#email)")
    public UserDO getByEmail(String email) {
        UserDO query = new UserDO();
        query.setEmail(email);
        return userMapper.getOne(query);
    }

    @Cacheable(cacheNames = "mdc:user", key = "'user'.concat(#telephone)")
    public UserDO getByTelephone(String telephone) {
        UserDO query = new UserDO();
        query.setTelephone(telephone);
        return userMapper.getOne(query);
    }

    @Cacheable(cacheNames = "mdc:user", key = "'user'.concat(#qq)")
    public UserDO getByQq(String qq) {
        UserDO query = new UserDO();
        query.setQq(qq);
        return userMapper.getOne(query);
    }

    @Cacheable(cacheNames = "mdc:user", key = "'user'.concat(#wx)")
    public UserDO getByWx(String wx) {
        UserDO query = new UserDO();
        query.setWx(wx);
        return userMapper.getOne(query);
    }

    @Cacheable(cacheNames = "mdc:user", key = "'user'.concat(#weiBo)")
    public UserDO getByWeiBo(String weiBo) {
        UserDO query = new UserDO();
        query.setWeiBo(weiBo);
        return userMapper.getOne(query);
    }

    public List<UserDO> find(String realName, String telephone, String qq, String wx, String weiBo) {
        Map<String, Object> params = new HashMap<>();
        params.put("realName", realName);
        params.put("telephone", telephone);
        params.put("qq", qq);
        params.put("wx", wx);
        params.put("weiBo", weiBo);
        return userMapper.find(params);
    }

    @Override
    @CacheEvict(cacheNames = "mdc:user", allEntries = true)
    public void deleteCache() {
        String uuid = UUID.randomUUID().toString();
        try {
            LOGGER.info("编号:{},清除缓存,时间:{},参数为:mdc:user", uuid, new Date());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
