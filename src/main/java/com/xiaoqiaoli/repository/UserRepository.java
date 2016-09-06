package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface UserRepository extends BaseRepository<User, String> , JpaSpecificationExecutor {

    User findTopByEmail(String email);

    User findTopByQq(String qq);

    User findTopByTelephone(String telephone);

    User findTopByWx(String wx);

    User findTopByWeiBo(String weiBo);

    List<User> findByRealName(String realName);
}
