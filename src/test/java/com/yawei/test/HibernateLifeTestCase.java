package com.yawei.test;


import com.yawei.pojo.User;
import com.yawei.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class HibernateLifeTestCase {

    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("www");
        user.setPassword("456456");

        session.persist(user);
        System.out.println(user.getId());
        /*session.save(user);
        System.out.println(user.getId());*/

        session.getTransaction().commit();
    }

    @Test
    public void testByGet() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 20);
        //System.out.println(user.getUsername());

        session.getTransaction().commit();
        Assert.assertNull(user);
    }

    @Test
    public void testByLoad() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.load(User.class, 20);
        //System.out.println(user.getUsername());
        System.out.println(user == null);
        session.getTransaction().commit();
    }

    @Test
    public void testUpdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 19);

        session.getTransaction().commit();

        user.setUsername("王石");
        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.update(user);
        session1.getTransaction().commit();
    }

    @Test
    public void testSaveOrUpdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("刘强东");
        user.setPassword("101202");

        session.saveOrUpdate(user);

        session.getTransaction().commit();
        /*user.setUsername("马云");
        Session session1=HibernateUtil.getSession();
        session1.beginTransaction();

        session1.saveOrUpdate(user);
        session1.getTransaction().commit();*/

    }

    @Test
    public void testMerge() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 22);

        session.getTransaction().commit();

        user.setUsername("马云");
        Session session1=HibernateUtil.getSession();
        session1.beginTransaction();

        session1.merge(user);
        session1.getTransaction().commit();

    }
    @Test
    public void testDel() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 17);

        session.getTransaction().commit();

        Session session1=HibernateUtil.getSession();
        session1.beginTransaction();

        session1.delete(user);
        session1.getTransaction().commit();

    }
    @Test
    public void testClear() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 22);
        user.setUsername("章泽天");
        session.flush();//将对象的变化立即同步到数据库中

        session.getTransaction().commit();

    }
}
