package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String>, PagingAndSortingRepository<Account, String> {
    //    List<Account> findByMultiIds(Map<String, Object> params);
    Account findByUsername(String username);

    Account findByUserId(String id);
}
