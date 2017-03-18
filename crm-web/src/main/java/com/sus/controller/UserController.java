package com.sus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String home() {
        return "login";
    }

}
