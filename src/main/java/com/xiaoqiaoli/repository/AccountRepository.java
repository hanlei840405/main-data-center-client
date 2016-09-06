package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface AccountRepository extends BaseRepository<Account, String> {
    //    List<Account> findByMultiIds(Map<String, Object> params);
    Account findByUsername(String username);

    Account findByUserId(String id);
}
