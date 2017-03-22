package com.sus.service;

import com.sus.dao.UserDao;
import com.sus.exception.ServiceException;
import com.sus.pojo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/18.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Value("${password.salt}")
    private String salt;

    @Transactional(readOnly = true)
    public List<User> findByParams(Map<String, Object> params) {
        return userDao.findByParams(params);
    }

    public Long count() {
        return userDao.count();
    }

    public Long countByParam(String keyword) {
        return userDao.countByParam(keyword);
    }

    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    public void save(User user) throws ServiceException {

        user.setPassword(DigestUtils.md5Hex(user.getPassword() + salt));
        user.setEnable(User.STATE_TRUE);
        userDao.saveOrUpdate(user);
        Integer id = user.getId();
        if(id == null) {
            throw new ServiceException("服务器异常，用户存储失败！");
        }
    }

    public void delById(Integer userId) {
        if(userId != null) {
            User user = userDao.findById(userId);
            if(user != null) {
                //TODO 删除用户日志
                //删除用户信息
                userDao.delete(userId);
            } else {
                throw new ServiceException("参数错误，该用户不存在，删除失败！");
            }
        } else {
            throw new ServiceException("参数错误，删除失败！");
        }
    }

    public void resetPwd(Integer userId) {
        if(userId != null) {
            User user = userDao.findById(userId);
            if(user != null) {
                user.setPassword(DigestUtils.md5Hex(000000 + salt));
                userDao.saveOrUpdate(user);
            } else {
                throw new ServiceException("该用户不存在，密码重置失败！");
            }
        } else {
            throw new ServiceException("参数错误，密码重置失败！");
        }
    }

    public User findById(Integer userId) {

        if(userId != null) {
            User user = userDao.findById(userId);
            if(user != null) {
                return user;
            } else {
                throw new ServiceException("该用户不存在！");
            }
        } else {
            throw new ServiceException("参数错误！");
        }

    }
    public void saveOrUpdate(User user) {
        if(user != null) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword() + salt));
            userDao.saveOrUpdate(user);
        } else {
            throw new ServiceException("该用户不存在！");
        }
    }
}
