package com.xiaoqiaoli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hanlei6 on 2016/7/18.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
//        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//        if (csrfToken != null) {
//            model.addAttribute("_csrf",csrfToken);
//        }
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError() {
        return "redirect:/error";
    }
}
