package com.xiaoqiaoli.controller;

import com.xiaoqiaoli.entity.Account;
import com.xiaoqiaoli.manager.AccountManager;
import com.xiaoqiaoli.service.AccountLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Controller
public class HomeController {

    @Autowired
    private AccountLocalService accountService;

    @Autowired
    private AccountManager accountManager;

    @RequestMapping("/")
    public String home(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
//        UserDO userDO = userService.localGet(id);
        System.out.println(webAuthenticationDetails.getSessionId());
        Account accountDO = accountService.localGetByUsername(user.getUsername());
        model.addAttribute("user", accountDO.getUser());
        return "index";
    }

    @RequestMapping("/clearCache")
    public String clearCache() {
        return "index";
    }
}
