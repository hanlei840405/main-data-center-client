package com.xiaoqiaoli.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.dto.Page;
import com.xiaoqiaoli.dto.UserDTO;
import com.xiaoqiaoli.manager.UserManager;
import com.xiaoqiaoli.service.UserLocalService;
import com.xiaoqiaoli.service.client.UserRemoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDTO remoteGet(String id) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(localGet(id), userDTO);
        return userDTO;
    }

    @Override
    public UserDO localGet(String id) {
//        UserDO userDO = new UserDO();
//        userDO.setId(id);
//        userDO.setBirthday(new Date());
//        userDO.setEmail("jesse.18@163.com");
//        userDO.setLevel(Level.A);
//        userDO.setQq("79627595");
//        userDO.setSex("M");
//        userDO.setRealName("韩磊");
//        userDO.setTelephone("18615267773");
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
        PageHelper.startPage(page.getStartRow(), page.getRowCount());
        List<UserDO> userDOs = userManager.find(realName,telephone,qq,wx,weiBo);
        page.setRows(userDOs);
        return page;
    }

    @Override
    public UserDO insert(UserDO userDO) {
        int result = userManager.insert(userDO);
        if(result > 0) {
            return userManager.get(userDO.getId());
        }
        return null;
    }

    @Override
    public UserDO update(UserDO userDO) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
