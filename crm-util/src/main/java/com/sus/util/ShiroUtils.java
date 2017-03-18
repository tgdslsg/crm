package com.sus.util;
import com.sus.pojo.User;
import org.apache.shiro.SecurityUtils;

/**
 * Created by tgdsl on 2017/3/18.
 */
public class ShiroUtils {
    public static String getCurrentRealName(){
        return getCurrentUser().getRealName();
    }
    public static User getCurrentUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();//shiro获取当前shiro管理的当前用户
    }
}
