package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>, PagingAndSortingRepository<User, String> {

//    int disConnectRole(@Param("userId") String userId);
}
