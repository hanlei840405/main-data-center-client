package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.domain.AccountDO;
import com.xiaoqiaoli.domain.RoleDO;
import com.xiaoqiaoli.service.AccountLocalService;
import com.xiaoqiaoli.service.RoleLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/18.
 */
@Service
public class ChocolateUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountLocalService accountService;

    @Autowired
    private RoleLocalService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDO accountDO = accountService.localGetByUsername(username);
        if (ObjectUtils.isEmpty(accountDO)) {
            throw new UsernameNotFoundException("用户[" + username + "] 不存在");
        }
        if (ObjectUtils.isEmpty(accountDO.getUser())) {
            throw new UsernameNotFoundException("用户[" + username + "] 未绑定人员信息");
        }
        List<RoleDO> roleDOs = roleService.findByUsername(username);
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();
        roleDOs.forEach(roleDO -> auths.add(new SimpleGrantedAuthority(roleDO.getCode())));

        return new User(accountDO.getUsername(),accountDO.getPassword(), auths);
    }
}
