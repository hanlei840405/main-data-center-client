package com.xiaoqiaoli.controller;

import com.xiaoqiaoli.entity.User;
import com.xiaoqiaoli.service.UserLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hanlei6 on 2016/8/19.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserLocalService userService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Page<User> page(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, String corporationId, String organizationId) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        return userService.localPage(pageable, corporationId, organizationId);
    }
}
