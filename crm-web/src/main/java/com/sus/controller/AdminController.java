package com.sus.controller;

import com.google.common.collect.Maps;
import com.sus.dto.DataTablesResult;
import com.sus.dto.JsonResult;
import com.sus.exception.ServiceException;
import com.sus.pojo.Role;
import com.sus.pojo.User;
import com.sus.service.RoleService;
import com.sus.service.UserService;
import com.sus.util.StringUtilSelf;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/18.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String adminHome(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "admin/userlist";
    }

    /**
     * 用户列表，dataTables
     */
    @GetMapping("/users/load")
    @ResponseBody
    public DataTablesResult<User> loadUsers(HttpServletRequest req) {
        String draw = req.getParameter("draw");
        String start = req.getParameter("start");
        String length = req.getParameter("length");
        String keyword = req.getParameter("search[value]");

        keyword = StringUtilSelf.toUTF8(keyword);

        Map<String, Object> params = Maps.newHashMap();
        params.put("keyword", keyword);
        params.put("start", start);
        params.put("length", length);

        Long count = userService.count();
        Long filterCount = userService.countByParam(keyword);
        List<User> userList = userService.findByParams(params);

        return new DataTablesResult<>(draw, userList, count, filterCount);
    }

    /**
     * 新增用户
     */
    @PostMapping("/users/new")
    @ResponseBody
    public JsonResult saveNewUser(User user, String roleid) {
        Role role;
        if (StringUtils.isNumeric(roleid)) {
            role = roleService.findById(Integer.valueOf(roleid));
        } else {
            return new JsonResult(JsonResult.ERROR, "角色Id为空！");
        }
        try {
            user.setRole(role);
            userService.save(user);
            return new JsonResult(JsonResult.SUCCESS,"用户新增成功！");
        } catch (ServiceException e) {
            e.printStackTrace();
            return new JsonResult(JsonResult.ERROR, e.getMessage());
        }

    }

    /**
     * 删除用户
     */
    @PostMapping("/user/del")
    @ResponseBody
    public JsonResult userDel(Integer userId) {
        try {
            userService.delById(userId);
            return new JsonResult(JsonResult.SUCCESS);
        }catch (ServiceException e) {
            e.printStackTrace();
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }

    }

    /**
     * 重置密码
     * */
    @PostMapping("/user/resetPwd")
    @ResponseBody
    public JsonResult resetPwd(Integer userId) {
        try {
            userService.resetPwd(userId);
            return new JsonResult(JsonResult.SUCCESS);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }
    }

    /**
     * 获取User.json
     * */
    @GetMapping("/user/{userId:\\d+}.json")
    @ResponseBody
    public JsonResult userJson(@PathVariable Integer userId) {
        try {
            User user = userService.findById(Integer.valueOf(userId));
            return new JsonResult(JsonResult.SUCCESS,user);
        } catch(ServiceException e) {
            e.printStackTrace();
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }

    }

    /**
     * 修改用户
     * */
    @PostMapping("/user/edit")
    @ResponseBody
    public JsonResult editUser(User user,Integer roleid) {
        System.out.println(user);
        try {
            Role role = roleService.findById(roleid);
            user.setRole(role);
            userService.saveOrUpdate(user);
            return new JsonResult(JsonResult.SUCCESS,"修改成功！");
        }catch(ServiceException e) {
            e.printStackTrace();
            return new JsonResult(JsonResult.ERROR,e.getMessage());
        }
    }
}
