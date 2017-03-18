package com.sus.service;

import com.sus.dao.RoleDao;
import com.sus.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
