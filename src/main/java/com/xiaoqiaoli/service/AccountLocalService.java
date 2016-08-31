package com.xiaoqiaoli.service;

import com.xiaoqiaoli.entity.Account;

/**
 * Created by hanlei6 on 2016/7/14.
 */
public interface AccountLocalService {

    Account localGet(String id);

    Account localGetByUsername(String username);

    Account localGetByUserId(String userId);
}
