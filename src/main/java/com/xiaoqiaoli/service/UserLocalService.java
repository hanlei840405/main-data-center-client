package com.xiaoqiaoli.service;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.domain.UserDO;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/14.
 */
public interface UserLocalService {
    UserDO localGet(String id);

    List<UserDO> localFindByRealName(String realName);

    UserDO localGetByEmail(String email);

    UserDO localGetByTelephone(String telephone);

    UserDO localGetByQq(String qq);

    UserDO localGetByWx(String wx);

    UserDO localGetByWeiBo(String weiBo);

    Page<UserDO> localPage(Page<UserDO> page, String realName, String telephone, String qq, String wx, String weiBo);

    UserDO insert(UserDO userDO);

    int batchInsert(List<UserDO> userDOs);

    UserDO update(UserDO userDO);

    int batchUpdate(List<UserDO> userDOs);

    int delete(String id);

    int batchDelete(String[] ids);

    int disConnectRole(String userId);

}
