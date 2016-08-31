package com.xiaoqiaoli.controller;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.entity.User;
import com.xiaoqiaoli.service.UserLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String,Object> page(@RequestParam("current") int current, @RequestParam("rowCount") int rowCount, int draw) {
        Page<User> page = new Page<>();
        page.setPageNum(current);
        page.setPageSize(rowCount);
        Page<User> userDOs = userService.localPage(page, null, null, null, null, null, null, null);
        Map<String,Object> result = new HashMap<>();
        result.put("current", current);
        result.put("rowCount", rowCount);
        result.put("total", userDOs.getTotal());
        result.put("rows", userDOs.getResult());
        return result;
    }
}
