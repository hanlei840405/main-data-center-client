package com.xiaoqiaoli.service;

import com.xiaoqiaoli.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/14.
 */
public interface UserLocalService {
    User localGet(String id);

    List<User> localFindByRealName(String realName);

    User localGetByEmail(String email);

    User localGetByTelephone(String telephone);

    User localGetByQq(String qq);

    User localGetByWx(String wx);

    User localGetByWeiBo(String weiBo);

    Page<User> localPage(Pageable pageable, String corporationId, String organizationId);

    User insert(User user);

    User update(User user);

    User delete(User user);

    void batchDelete(List<User> users);

    List<User> localFindByIds(String[] ids);
}
