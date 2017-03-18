package com.sus.controller;

import com.sus.pojo.Role;
import com.sus.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public String adminHome() {
        List<Role> roles = roleService.findAll();
        return "/admin/userList";
    }



}
