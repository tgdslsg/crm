package com.sus.dao;

import com.sus.pojo.Document;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tgdsl on 2017/3/18.
 */
@Repository
public class DocumentDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    public List<Document> findDocumentByFid(Integer fid){
        Criteria criteria = getSession().createCriteria(Document.class);
        criteria.add(Restrictions.eq("fid",fid));
        return criteria.list();
    }

    public void save(Document document) {
       getSession().saveOrUpdate(document);
    }



}
