package com.xiaoqiaoli.service.impl;

import com.xiaoqiaoli.entity.Account;
import com.xiaoqiaoli.entity.Role;
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
        Account account = accountService.localGetByUsername(username);
        if (ObjectUtils.isEmpty(account)) {
            throw new UsernameNotFoundException("用户[" + username + "] 不存在");
        }
        if (ObjectUtils.isEmpty(account.getUser())) {
            throw new UsernameNotFoundException("用户[" + username + "] 未绑定人员信息");
        }
//        List<Role> roleDOs = roleService.findByUsername(username);
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();
//        roleDOs.forEach(roleDO -> auths.add(new SimpleGrantedAuthority(roleDO.getCode())));

        return new User(account.getUsername(),account.getPassword(), auths);
    }
}
