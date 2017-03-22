package com.sus.controller;

import com.sus.pojo.User;
import com.sus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String home() {
        return "login";
    }

    @GetMapping("/validate/newUser/username")
    @ResponseBody
    public boolean validateUserName(String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            return true;
        }
        return false;
    }


}
