package com.yawei.test;


import com.yawei.pojo.User;
import com.yawei.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;

public class HibernateTestCase {

    @Test
    public void testSave() {
        //Hibernate 4.3
        Configuration configuration = new Configuration().configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(registry);


        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setUsername("刘德华");
        user.setPassword("100100");
        session.save(user);
        transaction.commit();
    }

    @Test
    public void testFindById() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 16);
        System.out.println(user.getUsername());
        session.getTransaction().commit();
    }

    @Test
    public void testupdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 16);
        user.setUsername("林心如");
        System.out.println(user.getUsername());
        session.getTransaction().commit();
    }

    @Test
    public void testDel() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 16);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Test
    public void testFindAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        for(User user:userList){
            System.out.println(user.getId()+"->"+user.getUsername());
        }
        session.getTransaction().commit();
    }
}
