package com.yawei.test;


import com.yawei.pojo.User;
import com.yawei.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class HQLTestCase {

    @Test
    public void testFindAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);

        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void testByWhere() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        //String hql = "from User where username=? and password=?";
        String hql = "from User where username=:name and password=:pwd";
        Query query = session.createQuery(hql);

       /* query.setParameter(0,"rose");
        query.setParameter(1,"123123");*/

        query.setParameter("name", "rose");
        query.setParameter("pwd", "123123");

        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void testUnique() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User where username=? and password=?";
        Query query = session.createQuery(hql);

        query.setParameter(0, "rose");
        query.setParameter(1, "123123");
        User user = (User) query.uniqueResult();
        System.out.println(user);

        session.getTransaction().commit();
    }

    @Test
    public void testByOneColumn() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select username from User";
        Query query = session.createQuery(hql);

        List<String> result = query.list();
        for (String string : result) {
            System.out.println(string);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testByColumns() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select id,username,password from User";
        Query query = session.createQuery(hql);

        List<Object[]> result = query.list();
        for (Object[] objects : result) {
            System.out.println(objects[0] + "->" + objects[1] + "pwd:" + objects[2]);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testCount() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select count(*) from User";
        Query query = session.createQuery(hql);

        Long result = (Long) query.uniqueResult();
        System.out.println("count:"+result);
        session.getTransaction().commit();
    }
    @Test
    public void testCountAndMax() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select count(*),max(id) from User";
        Query query = session.createQuery(hql);

        Object[] objects = (Object[]) query.uniqueResult();
        System.out.println("count:"+objects[0]);
        System.out.println("max:"+objects[1]);

        session.getTransaction().commit();
    }
    @Test
    public void testPage() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User order by id desc";
        Query query = session.createQuery(hql);

        query.setFirstResult(0);
        query.setMaxResults(3);
        List<User> userList = query.list();
        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
}
