package com.xiaoqiaoli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoqiaoli.entity.User;
import com.xiaoqiaoli.dto.UserDTO;
import com.xiaoqiaoli.manager.UserManager;
import com.xiaoqiaoli.service.UserLocalService;
import com.xiaoqiaoli.service.client.UserRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Service("userService")
public class UserServiceImpl implements UserLocalService, UserRemoteService {

    @Autowired
    private UserManager userManager;

    @Override
    @Cacheable(cacheNames = "mdc:user:username", key = "'/userService/remoteGetByAccount/'.concat(#username)")
    public UserDTO remoteGetByAccount(String username) {
        return null;
    }

    @Override
    @Cacheable(cacheNames = "mdc:user:id", key = "'/userService/remoteGetByAccount/'.concat(#id)")
    public User localGet(String id) {
        return userManager.get(id);
    }

    @Override
    @Cacheable(cacheNames = "mdc:user:realName", key = "'/userService/remoteGetByAccount/'.concat(#realName)")
    public List<User> localFindByRealName(String realName) {
        return userManager.findByRealName(realName);
    }

    @Override
    @Cacheable(cacheNames = "mdc:user:email", key = "'/userService/remoteGetByAccount/'.concat(#email)")
    public User localGetByEmail(String email) {
        return userManager.getByEmail(email);
    }

    @Override
    @Cacheable(cacheNames = "mdc:user:telephone", key = "'/userService/remoteGetByAccount/'.concat(#telephone)")
    public User localGetByTelephone(String telephone) {
        return userManager.getByTelephone(telephone);
    }

    @Override
    @Cacheable(cacheNames = "mdc:user:qq", key = "'/userService/remoteGetByAccount/'.concat(#qq)")
    public User localGetByQq(String qq) {
        return userManager.getByQq(qq);
    }

    @Override
    @Cacheable(cacheNames = "mdc:user:wx", key = "'/userService/remoteGetByAccount/'.concat(#wx)")
    public User localGetByWx(String wx) {
        return userManager.getByWx(wx);
    }

    @Override
    @Cacheable(cacheNames = "mdc:user:weiBo", key = "'/userService/remoteGetByAccount/'.concat(#weiBo)")
    public User localGetByWeiBo(String weiBo) {
        return userManager.getByWeiBo(weiBo);
    }

    @Override
    public Page<User> localPage(Page<User> page, String realName, String telephone, String qq, String wx, String weiBo, String corporationId, String organizationId) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        Page<User> userDOs = (Page<User>) userManager.findByParams(realName, telephone, qq, wx, weiBo, corporationId, organizationId);
        return userDOs;
    }

    @Override
    @Transactional
    public User insert(User userDO) {
        int result = userManager.insert(userDO);
        if (result > 0) {
            return userManager.get(userDO.getId());
        }
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"mdc:user:username", "mdc:user:mail", "mdc:user:telephone", "mdc:user:qq", "mdc:user:wx", "mdc:user:weiBo", "mdc:user:realName", "mdc:user:weiBo"}, allEntries = true)
    public User update(User userDO) {
        int result = userManager.update(userDO);
        if (result > 0) {
            return userManager.get(userDO.getId());
        }
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"mdc:user:username", "mdc:user:mail", "mdc:user:telephone", "mdc:user:qq", "mdc:user:wx", "mdc:user:weiBo", "mdc:user:realName", "mdc:user:weiBo"}, allEntries = true)
    public int delete(String id) {
        return userManager.delete(localGet(id));
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"mdc:user:username", "mdc:user:mail", "mdc:user:telephone", "mdc:user:qq", "mdc:user:wx", "mdc:user:weiBo", "mdc:user:realName", "mdc:user:weiBo"}, allEntries = true)
    public int batchDelete(String[] ids) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userManager.batchDelete(userManager.findByMultiIds(ids), principal.getUsername());
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"mdc:user:username", "mdc:user:mail", "mdc:user:telephone", "mdc:user:qq", "mdc:user:wx", "mdc:user:weiBo", "mdc:user:realName", "mdc:user:weiBo"}, allEntries = true)
    public int disConnectRole(String userId) {
        return userManager.disConnectRole(userId);
    }

}
