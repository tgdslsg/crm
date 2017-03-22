package com.sus.dao;

import com.sus.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sus.exception.ServiceException;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/18.
 */
@Repository
public class UserDao extends BaseDao<User,Integer> {

    public Long countByParam(String keyword) {


        if(StringUtils.isNotEmpty(keyword)) {
            return (Long) getSession().createCriteria(User.class).setProjection(Projections.rowCount()).add(Restrictions.or(
                    Restrictions.eq("userName",keyword),
                    Restrictions.eq("realName",keyword))).uniqueResult();
        } else {
            return count();
        }


    }

    public List<User> findByParams(Map params) {
        Criteria criteria = getSession().createCriteria(User.class);
        String keyword = (String) params.get("keyword");
        String length = (String) params.get("length");
        String start = (String) params.get("start");
        if(StringUtils.isNotEmpty(keyword)) {
            criteria.add(
                    Restrictions.or(
                            Restrictions.eq("userName",keyword),
                            Restrictions.eq("realName",keyword)));
            criteria.setFirstResult(Integer.parseInt(start));
            criteria.setMaxResults(Integer.parseInt(length));
            return criteria.list();
        } else {
            criteria.setFirstResult(Integer.parseInt(start));
            criteria.setMaxResults(Integer.parseInt(length));
            return criteria.list();
        }
    }

    public User findByUserName(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        if(StringUtils.isNotEmpty(username)) {
            return (User)criteria.add(Restrictions.eq("userName",username)).uniqueResult();
        } else {
            throw new ServiceException("数据错误，请输入账号");
        }
    }
}
