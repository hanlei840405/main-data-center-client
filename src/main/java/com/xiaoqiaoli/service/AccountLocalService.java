package com.xiaoqiaoli.service;

import com.xiaoqiaoli.domain.AccountDO;

/**
 * Created by hanlei6 on 2016/7/14.
 */
public interface AccountLocalService {

    AccountDO localGet(String id);

    AccountDO localGetByUsername(String username);

    AccountDO localGetByUserId(String userId);
}
