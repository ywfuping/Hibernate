package com.yawei.test;


import com.yawei.pojo.User;
import com.yawei.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

public class CriteriaTestCase {


    @Test
    public void testFindAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        List<User> userList = criteria.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void testByWhere() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        //criteria.add(Restrictions.eq("username","rose"));

        //in
        //criteria.add(Restrictions.in("username",new Object[]{"rose","马云"}));

        //or
        criteria.add(Restrictions.or(Restrictions.eq("username", "rose"), Restrictions.eq("username", "马云")));

        //or
       /* Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("username","rose"));
        disjunction.add(Restrictions.eq("username","马云"));

        criteria.add(disjunction);*/

        //like
        criteria.add(Restrictions.like("username", "马", MatchMode.ANYWHERE));

        List<User> userList = criteria.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void testUnique() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username","林心如"));
        User user = (User) criteria.uniqueResult();
        System.out.println(user);


        session.getTransaction().commit();
    }

    @Test
    public void testCount(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));

        criteria.setProjection(projectionList);
        Object[] objects= (Object[]) criteria.uniqueResult();
        System.out.println("count:"+objects[0]);
        System.out.println("max:"+objects[1]);

        session.getTransaction().commit();
    }

    @Test
    public void testOrder(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.addOrder(Order.desc("id"));

        List<User> userList = criteria.list();
        for(User user:userList){
            System.out.println(user);
        }

        session.getTransaction().commit();

    }
    @Test
    public void testPage(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.addOrder(Order.desc("id"));

        criteria.setFirstResult(3);
        criteria.setMaxResults(3);

        List<User> userList = criteria.list();
        for(User user:userList){
            System.out.println(user);
        }

        session.getTransaction().commit();

    }
}
