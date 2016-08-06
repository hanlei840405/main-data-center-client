package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.AccountDO;
import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Service("accountManager")
public class AccountManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(AccountManager.class);
    @Autowired
    private AccountMapper accountMapper;


    @Cacheable(cacheNames = "mdc:account", key = "'account.'.concat(#username)")
    public AccountDO getByUsername(String username) {
        AccountDO query = new AccountDO();
        query.setUsername(username);
        return accountMapper.getOne(query);
    }

    @Cacheable(cacheNames = "mdc:account", key = "'account.'.concat(#userId)")
    public AccountDO getByUserId(String userId) {
        AccountDO query = new AccountDO();
        UserDO queryUser = new UserDO();
        queryUser.setId(userId);
        query.setUser(queryUser);
        return accountMapper.getOne(query);
    }

    @Cacheable(value = "mdc:account", key = "'account.'.concat(#id)")
    public AccountDO get(String id) {
        return accountMapper.get(id);
    }

    public int insert(AccountDO AccountDO) {
        try {
            return accountMapper.insert(AccountDO);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOGGER.error("新增数据出错，参数：{}", AccountDO);
            return 0;
        }
    }

    public int batchInsert(List<AccountDO> ts) {
        try {
            return accountMapper.batchInsert(ts);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:account", allEntries = true)
    public int update(AccountDO AccountDO) {
        try {

            return accountMapper.update(AccountDO);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:account", allEntries = true)
    public int batchUpdate(List<AccountDO> ts) {
        try {

            return accountMapper.batchUpdate(ts);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:account", allEntries = true)
    public int delete(AccountDO AccountDO) {
        try {

            return accountMapper.delete(AccountDO);
        } catch (RuntimeException e) {
            return 0;
        }
    }

    @CacheEvict(cacheNames = "mdc:account", allEntries = true)
    public int batchDelete(List<AccountDO> ts, String modifier) {
        try {
            return accountMapper.batchDelete(ts, modifier);
        } catch (RuntimeException e) {
            return 0;
        }
    }
}
