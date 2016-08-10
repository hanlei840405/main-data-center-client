package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.domain.AccountDO;
import com.xiaoqiaoli.mapper.AccountMapper;
import com.xiaoqiaoli.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Service("accountManager")
public class AccountManager extends BaseManager<AccountDO,String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(AccountManager.class);
    @Autowired
    private AccountMapper accountMapper;

    @Override
    BaseMapper<AccountDO, String> getBaseMapper() {
        threadLocal.set(accountMapper);
        return threadLocal.get();
    }

    /**
     * 根据username查询账号
     * @param username
     * @return
     */
    public AccountDO getByUsername(String username) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("username",username);
        return this.getOne(queryParams);
    }

    /**
     * 根据用户ID查询账号
     * @param userId
     * @return
     */
    public AccountDO getByUserId(String userId) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("userId",userId);
        return this.getOne(queryParams);
    }

}
