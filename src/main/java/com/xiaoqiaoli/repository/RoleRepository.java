package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface RoleRepository extends BaseRepository<Role, String> {

    Role findTopByCode(String code);

    @Query("select r from com.xiaoqiaoli.entity.Role r join r.accounts account where account.username = ?1")
    List<Role> findByUsername(String username);
//    int connectAccount(Role roleDO);
//
//    int disConnectAccount(@Param("roleId") String roleId);
}
