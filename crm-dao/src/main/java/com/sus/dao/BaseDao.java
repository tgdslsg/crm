package com.sus.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
@Repository
public class BaseDao<T,PK extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class clazz;

    public BaseDao() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public T findById(PK id) {
       return (T)getSession().get(clazz,id);
    }

    public void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public List<T> findAll(String propertyName,String orderType) {
        Criteria criteria = getSession().createCriteria(clazz);
        if("desc".equals(orderType)) {
            criteria.addOrder(Order.desc(propertyName));
        } else {
            criteria.addOrder(Order.asc(propertyName));
        }
        return criteria.list();
    }

    public List<T> findAll() {
        Criteria criteria = getSession().createCriteria(clazz);
        return criteria.list();
    }

    public void delete(PK id) {
        getSession().delete(findById(id));
    }


}
