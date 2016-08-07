package com.xiaoqiaoli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.dto.UserDTO;
import com.xiaoqiaoli.manager.UserManager;
import com.xiaoqiaoli.service.UserLocalService;
import com.xiaoqiaoli.service.client.UserRemoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Service("userService")
public class UserServiceImpl implements UserLocalService, UserRemoteService {

    @Autowired
    private UserManager userManager;

    @Override
    public UserDTO remoteGetByAccount(String username) {
        return null;
    }

    @Override
    public UserDO localGet(String id) {
        return userManager.get(id);
    }

    @Override
    public List<UserDO> localFindByRealName(String realName) {
        return userManager.findByRealName(realName);
    }

    @Override
    public UserDO localGetByEmail(String email) {
        return userManager.getByEmail(email);
    }

    @Override
    public UserDO localGetByTelephone(String telephone) {
        return userManager.getByTelephone(telephone);
    }

    @Override
    public UserDO localGetByQq(String qq) {
        return userManager.getByQq(qq);
    }

    @Override
    public UserDO localGetByWx(String wx) {
        return userManager.getByWx(wx);
    }

    @Override
    public UserDO localGetByWeiBo(String weiBo) {
        return userManager.getByWeiBo(weiBo);
    }

    @Override
    public Page<UserDO> localPage(Page<UserDO> page, String realName, String telephone, String qq, String wx, String weiBo) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Page<UserDO> userDOs = (Page<UserDO>) userManager.find(realName, telephone, qq, wx, weiBo);
        return userDOs;
    }

    @Override
    public UserDO insert(UserDO userDO) {
        int result = userManager.insert(userDO);
        if (result > 0) {
            return userManager.get(userDO.getId());
        }
        return null;
    }

    @Override
    public UserDO update(UserDO userDO) {
        int result = userManager.update(userDO);
        if (result > 0) {
            return userManager.get(userDO.getId());
        }
        return null;
    }

    @Override
    public void delete(String id) {
        userManager.delete(localGet(id));
    }

    @Override
    public void batchDelete(String[] ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userManager.batchDelete(userManager.findByMultiIds(ids), principal.getUsername());
    }

}
