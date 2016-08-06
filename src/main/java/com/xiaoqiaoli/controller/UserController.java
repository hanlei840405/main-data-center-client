package com.xiaoqiaoli.controller;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.domain.UserDO;
import com.xiaoqiaoli.service.UserLocalService;
import com.xiaoqiaoli.service.client.GenerateIdRemoteService;
import com.xiaoqiaoli.util.Constant;
import com.xiaoqiaoli.util.MD5Util;
import com.xiaoqiaoli.util.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserLocalService userService;

    @Autowired
    private GenerateIdRemoteService generateIdRemoteService;

    @Value("${ext.image.dir}")
    private String imageDir;

    @RequestMapping("/index")
    public String index(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, Model model) {
        Page<UserDO> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page = userService.localPage(page, null, null, null, null, null);
        model.addAttribute("page", page);
        return "user/index";
    }

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") String id, Model model) {
        UserDO user = userService.localGet(id);
        model.addAttribute("user", user);
        return "user/view";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") String id, Model model) {
        UserDO user = userService.localGet(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @Deprecated
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public
    @ResponseBody
    List<UserDO> page(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, UserDO user) {
        Page<UserDO> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return userService.localPage(page, user.getRealName(), user.getTelephone(), user.getQq(), user.getWx(), user.getWeiBo());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(@RequestParam("file") MultipartFile file, UserDO user, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                String photo = user.getRealName() + file.getOriginalFilename() + request.getRemoteHost();
                user.setPhoto(MD5Util.encode(photo, String.valueOf(System.currentTimeMillis())));
                Files.copy(file.getInputStream(), Paths.get(imageDir, user.getPhoto()));
            } catch (IOException e) {
                LOGGER.error("复制图片出现错误,参数：{}", user);
            }
        }

        Date createdAndModified = new Date();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setStatus(Constant.PERSISTENT_OBJECT_STATUS_ACTIVE);
        user.setCreator(principal.getUsername());
        user.setCreated(createdAndModified);
        user.setModifier(principal.getUsername());
        user.setModified(createdAndModified);
        user.setId(generateIdRemoteService.get(Constant.APPLICATION, Module.USER.name()));
        UserDO userDO = userService.insert(user);
        Map<String, Object> result = new HashMap<>();
        buildResponseStatus(userDO, result);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> update(MultipartFile file, UserDO user, HttpServletRequest request) {
        boolean replacePhoto = false;
        if (file != null && !file.isEmpty()) {
            try {
                String photo = user.getRealName() + file.getOriginalFilename() + request.getRemoteHost();
                user.setPhoto(MD5Util.encode(photo, String.valueOf(System.currentTimeMillis())));
                Files.copy(file.getInputStream(), Paths.get(imageDir, user.getPhoto()));
                replacePhoto = true;
            } catch (IOException e) {
                LOGGER.error("复制图片出现错误,参数：{}", user);
            }
        }

        UserDO exist = userService.localGet(user.getId());
        if (!replacePhoto) {
            user.setPhoto(exist.getPhoto());
        }
        BeanUtils.copyProperties(user, exist, "creator", "created");

        Date modified = new Date();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        exist.setModifier(principal.getUsername());
        exist.setModified(modified);
        UserDO userDO = userService.update(exist);
        Map<String, Object> result = new HashMap<>();
        buildResponseStatus(userDO, result);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> delete(@RequestParam("ids") String ids) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.batchDelete(ids.split(","));
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_SUCCESS);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_SUCCESS);
        } catch (RuntimeException e) {
            LOGGER.error("批量删除出现错误,参数：{}", ids);
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_FAILURE);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_FAILURE);
        }
        return result;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    /**
     * 返回状态码，状态信息和操作的对象
     *
     * @param userDO
     * @param result
     * @return
     */
    private Map<String, Object> buildResponseStatus(UserDO userDO, Map<String, Object> result) {
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
}
