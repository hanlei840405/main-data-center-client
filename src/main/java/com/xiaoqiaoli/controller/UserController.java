package com.xiaoqiaoli.controller;

import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.dto.Page;
import com.xiaoqiaoli.enums.Level;
import com.xiaoqiaoli.service.UserLocalService;
import com.xiaoqiaoli.service.client.GenerateIdRemoteService;
import com.xiaoqiaoli.util.Constant;
import com.xiaoqiaoli.util.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserLocalService userService;

    @Autowired
    private GenerateIdRemoteService generateIdRemoteService;

    @Value("${file.image.dir}")
    private String imageDir;

    @RequestMapping("/index")
    public String index() {
        return "user/index";
    }

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public
    @ResponseBody
    Page<UserDO> page(@RequestParam("current") int current, @RequestParam("rowCount") int rowCount, UserDO user) {
        Page<UserDO> page = new Page<>();
        page.setCurrent(current);
        page.setRowCount(rowCount);
        page = userService.localPage(page, user.getRealName(), user.getTelephone(), user.getQq(), user.getWx(), user.getWeiBo());
        return page;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(@RequestParam("photo") MultipartFile file, UserDO user) {
        if (!file.isEmpty()) {
            try {
                user.setPhoto(imageDir.concat(file.getOriginalFilename()));
                Files.copy(file.getInputStream(), Paths.get(imageDir, file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Date createdAndModified = new Date();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setStatus(Constant.PERSISTENT_OBJECT_STATUS_ACTIVE);
        user.setLevel(Level.A);
        user.setCreator(principal.getUsername());
        user.setCreated(createdAndModified);
        user.setModifier(principal.getUsername());
        user.setModified(createdAndModified);
        user.setId(generateIdRemoteService.get(Constant.APPLICATION, Module.USER.name()));
        UserDO userDO = userService.insert(user);
        Map<String, Object> result = new HashMap<>();
        if (userDO != null) {
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_SUCCESS);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_SUCCESS);
        } else {
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_FAILURE);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_FAILURE);
        }
        result.put(Constant.RETURN_MAP_KEY_OBJECT, userDO);
        return result;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
