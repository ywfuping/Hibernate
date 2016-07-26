package com.yawei.test;


import com.yawei.pojo.User;
import com.yawei.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class NativeSQLTestCase {

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select * from t_user";
        SQLQuery query = session.createSQLQuery(sql);
        List<Object[]> result = query.list();
        for(Object[] objects : result){
            System.out.println(objects[0]+":"+objects[1]+":"+objects[2]);
        }


        session.getTransaction().commit();
    }
    @Test
    public void testFindById(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select * from t_user WHERE id=?";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0,43);
        Object[] objects = (Object[]) query.uniqueResult();
        System.out.println(objects[0]+":"+objects[1]+":"+objects[2]);

        session.getTransaction().commit();
    }
    @Test
    public void testFindByIdToUser(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select * from t_user WHERE id=?";
        SQLQuery query = session.createSQLQuery(sql).addEntity(User.class);
        query.setParameter(0,43);
        User user = (User) query.uniqueResult();
        System.out.println(user);

        session.getTransaction().commit();
    }
}
