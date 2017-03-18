package com.sus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping("/")
    public String loginValidate(String userName,String password) {

        return null;
    }

}
