package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.Account;
import com.xiaoqiaoli.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Service
public class AccountManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(AccountManager.class);
    @Autowired
    private AccountRepository accountRepository;

    /**
     * 根据id查询账号
     *
     * @param id
     * @return
     */
    public Account get(String id) {
        return accountRepository.findOne(id);
    }

    /**
     * 根据username查询账号
     *
     * @param username
     * @return
     */
    public Account getByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    /**
     * 根据用户ID查询账号
     *
     * @param userId
     * @return
     */
    public Account getByUserId(String userId) {
        return accountRepository.findByUserId(userId);
    }

}
