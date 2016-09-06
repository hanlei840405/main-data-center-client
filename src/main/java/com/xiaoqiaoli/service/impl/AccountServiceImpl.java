package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.entity.Account;
import com.xiaoqiaoli.manager.AccountManager;
import com.xiaoqiaoli.service.AccountLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountLocalService {

    @Autowired
    private AccountManager accountManager;

    @Override
//    @Cacheable(cacheNames = "mdc:account:id", key = "'/accountService/localGet/'.concat(#id)")
    public Account localGet(String id) {
        return accountManager.get(id);
    }

    @Override
//    @Cacheable(cacheNames = "mdc:account:username", key = "'/accountService/localGetByUsername/'.concat(#username)")
    public Account localGetByUsername(String username) {
        return accountManager.getByUsername(username);
    }

    @Override
//    @Cacheable(cacheNames = "mdc:account:userId", key = "'/accountService/localGetByUserId/'.concat(#userId)")
    public Account localGetByUserId(String userId) {
        return accountManager.getByUserId(userId);
    }
}
