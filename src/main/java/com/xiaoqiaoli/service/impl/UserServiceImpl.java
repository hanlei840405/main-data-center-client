package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.dto.UserDTO;
import com.xiaoqiaoli.entity.User;
import com.xiaoqiaoli.manager.UserManager;
import com.xiaoqiaoli.service.UserLocalService;
import com.xiaoqiaoli.service.client.UserRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<User> localPage(Pageable pageable, String corporationId, String organizationId) {
        return userManager.page(pageable, corporationId, organizationId);
    }

    @Override
    @Transactional
    public User insert(User user) {
        return userManager.insert(user);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"mdc:user:username", "mdc:user:mail", "mdc:user:telephone", "mdc:user:qq", "mdc:user:wx", "mdc:user:weiBo", "mdc:user:realName", "mdc:user:weiBo"}, allEntries = true)
    public User update(User user) {
        return userManager.update(user);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"mdc:user:username", "mdc:user:mail", "mdc:user:telephone", "mdc:user:qq", "mdc:user:wx", "mdc:user:weiBo", "mdc:user:realName", "mdc:user:weiBo"}, allEntries = true)
    public User delete(User user) {
        return userManager.delete(user);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"mdc:user:username", "mdc:user:mail", "mdc:user:telephone", "mdc:user:qq", "mdc:user:wx", "mdc:user:weiBo", "mdc:user:realName", "mdc:user:weiBo"}, allEntries = true)
    public void batchDelete(List<User> users) {
        userManager.batch(users);
    }

    @Override
    public List<User> localFindByIds(String[] ids) {
        return userManager.findByIds(ids);
    }
}
