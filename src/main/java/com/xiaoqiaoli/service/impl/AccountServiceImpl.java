package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.domain.AccountDO;
import com.xiaoqiaoli.manager.AccountManager;
import com.xiaoqiaoli.service.AccountLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountLocalService {

    @Autowired
    private AccountManager accountManager;

    @Override
    public AccountDO localGet(String id) {
        return accountManager.get(id);
    }

    @Override
    public AccountDO localGetByUsername(String username) {
        return accountManager.getByUsername(username);
    }

    @Override
    public AccountDO localGetByUserId(String id) {
        return accountManager.getByUserId(id);
    }
}
