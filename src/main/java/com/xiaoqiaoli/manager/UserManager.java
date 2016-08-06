package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Component
public class UserManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserManager.class);
    @Autowired
    private UserMapper userMapper;

    @Cacheable(cacheNames = "mdc:user", key = "'user'.concat(#realName)")
    public List<UserDO> findByRealName(String realName) {
        return userMapper.findByRealName(realName);
    }

    public List<UserDO> findByMultiIds(String[] ids) {
        return userMapper.findByMultiIds(ids);
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

    @Cacheable(value = "mdc:user", key = "'user.'.concat(#id)")
    public UserDO get(String id) {
        return userMapper.get(id);
    }

    public int insert(UserDO UserDO) {
        try {
            return userMapper.insert(UserDO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("新增数据出错，参数：{}", UserDO);
            return 0;
        }
    }

    public int batchInsert(List<UserDO> ts) {
        try {
            return userMapper.batchInsert(ts);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:user", key = "'user.'.concat(#userDO.id)")
    public int update(UserDO userDO) {
        try {
            return userMapper.update(userDO);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:user", allEntries = true)
    public int batchUpdate(List<UserDO> ts) {
        try {
            return userMapper.batchUpdate(ts);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:user", key = "'user.'.concat(#userDO.id)")
    public int delete(UserDO UserDO) {
        try {
            return userMapper.delete(UserDO);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:user", allEntries = true)
    public int batchDelete(List<UserDO> ts, String modifier) {
        try {
            return userMapper.batchDelete(ts, modifier);
        } catch (RuntimeException e) {
            return 0;
        }
    }
}
