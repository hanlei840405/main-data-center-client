package com.xiaoqiaoli.service;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.entity.User;

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

    Page<User> localPage(Page<User> page, String realName, String telephone, String qq, String wx, String weiBo, String corporationId, String organizationId);

    User insert(User userDO);

    User update(User userDO);

    int delete(String id);

    int batchDelete(String[] ids);

    int disConnectRole(String userId);

}
