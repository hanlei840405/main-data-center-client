package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.AccountDO;
import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.mapper.AccountMapper;
import com.xiaoqiaoli.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Service("accountManager")
public class AccountManager extends BaseManager<AccountDO, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(AccountManager.class);
    @Autowired
    private AccountMapper accountMapper;

    @Override
    protected BaseMapper<AccountDO, String> baseMapper() {
        return accountMapper;
    }

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

    @Override
    @CacheEvict(cacheNames = "mdc:account", allEntries = true)
    public void deleteCache() {
        String uuid = UUID.randomUUID().toString();
        try {
            LOGGER.info("编号:{},清除缓存,时间:{},参数为:mdc:account", uuid, new Date());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
