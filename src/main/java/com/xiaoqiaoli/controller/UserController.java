package com.xiaoqiaoli.controller;

import com.xiaoqiaoli.entity.Account;
import com.xiaoqiaoli.entity.User;
import com.xiaoqiaoli.service.AccountLocalService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
public class UserController extends BaseController<User> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserLocalService userService;

    @Autowired
    private GenerateIdRemoteService generateIdRemoteService;

    @Autowired
    private AccountLocalService accountService;

    @Value("${ext.image.dir}")
    private String imageDir;

    @RequestMapping("/index")
    public String index() {
        return "user/index";
    }

    @RequestMapping("/select")
    public String select(String organizationId, Model model) {
        model.addAttribute("model", organizationId);
        return "user/select";
    }

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") String id, Model model) {
        User user = userService.localGet(id);
        model.addAttribute("user", user);
        return "user/view";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") String id, Model model) {
        User user = userService.localGet(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @RequestMapping(value = "/page")
    public
    @ResponseBody
    Page<User> page(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, String corporationId, String organizationId) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        return userService.localPage(pageable, corporationId, organizationId);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(@RequestParam("file") MultipartFile file, User user, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                String photo = user.getRealName() + file.getOriginalFilename() + request.getRemoteHost();
                user.setPhoto(MD5Util.encode(photo, String.valueOf(System.currentTimeMillis())));
                Files.copy(file.getInputStream(), Paths.get(imageDir, user.getPhoto()));
            } catch (IOException e) {
                LOGGER.error("上传图片出现错误,参数：{}", user);
            }
        }

        Date createdAndModified = new Date();

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.localGetByUsername(principal.getUsername());
        user.setStatus(Constant.PERSISTENT_OBJECT_STATUS_ACTIVE);
        user.setCreator(account);
        user.setCreated(createdAndModified);
        user.setModifier(account);
        user.setModified(createdAndModified);
        user.setId(generateIdRemoteService.get(Constant.APPLICATION, Module.USER.name()));
        User userDO = userService.insert(user);
        Map<String, Object> result = new HashMap<>();
        buildResponseStatus(userDO, result);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> update(MultipartFile file, User user, HttpServletRequest request) {
        if (file != null && !file.isEmpty()) {
            try {
                String photo = user.getRealName() + file.getOriginalFilename() + request.getRemoteHost();
                user.setPhoto(MD5Util.encode(photo, String.valueOf(System.currentTimeMillis())));
                Files.copy(file.getInputStream(), Paths.get(imageDir, user.getPhoto()));
            } catch (IOException e) {
                LOGGER.error("上传图片出现错误,参数：{}", user);
            }
        }

        User exist = userService.localGet(user.getId());
        if (StringUtils.isEmpty(user.getPhoto())) {
            user.setPhoto(exist.getPhoto());
        }
        BeanUtils.copyProperties(user, exist, "creator", "created");

        Date modified = new Date();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.localGetByUsername(principal.getUsername());
        exist.setModifier(account);
        exist.setModified(modified);
        User userDO = userService.update(exist);
        Map<String, Object> result = new HashMap<>();
        buildResponseStatus(userDO, result);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> delete(@RequestParam("ids") String ids) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.localGetByUsername(principal.getUsername());
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.localFindByIds(ids.split(","));
        users.forEach(user -> {
            user.setModified(new Date());
            user.setModifier(account);
            user.setStatus("0");
        });
        try {
            userService.batchDelete(users);
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
}
