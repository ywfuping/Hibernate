package com.yawei.test;


import com.yawei.pojo.Topic;
import com.yawei.pojo.TopicContent;
import com.yawei.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class OneToOne2TestCase {

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Topic topic = new Topic();
        topic.setTitle("你好，世界！");

        TopicContent topicContent = new TopicContent();
        topicContent.setContent("内容：喜欢这个功利的世界……");
        topic.setTopicContent(topicContent);

        session.save(topic);
        session.save(topicContent);

        session.getTransaction().commit();
    }
    @Test
    public void testFind(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Topic topic = (Topic) session.get(Topic.class,2);
        System.out.println(topic.getTitle());
        System.out.println(topic.getTopicContent().getContent());

        session.getTransaction().commit();
    }
}
