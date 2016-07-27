package com.yawei.test;


import com.yawei.pojo.Task;
import com.yawei.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.junit.Test;

public class UUIDTestCase {

    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = new Task();
        task.setTitle("task2");

        session.save(task);

        session.getTransaction().commit();
    }

    @Test
    public void testFindById() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class, "8a8082e454bc3f1b0154bc3f1f6a0000");
        //session.clear();//清空一级缓存
        //session.evict(task);//清空指定的对象
        /*Task task1 = (Task) session.get(Task.class, "8a8082e454bc3f1b0154bc3f1f6a0000");
        System.out.println(task.getTitle());
        System.out.println(task1.getTitle());*/

        session.getTransaction().commit();

       /* Cache cache = HibernateUtil.getSessionFactory().getCache();
        cache.evictEntityRegion(Task.class);
        cache.evictAllRegions();*/

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();
        Task task1 = (Task) session1.get(Task.class, "8a8082e454bc3f1b0154bc3f1f6a0000");
        System.out.println(task1.getTitle());

        session1.getTransaction().commit();
    }
}
