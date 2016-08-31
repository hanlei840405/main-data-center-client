package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String>, PagingAndSortingRepository<Role, String> {

//    int connectAccount(Role roleDO);
//
//    int disConnectAccount(@Param("roleId") String roleId);
}
