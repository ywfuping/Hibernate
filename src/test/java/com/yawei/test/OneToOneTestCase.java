package com.yawei.test;


import com.yawei.pojo.Card;
import com.yawei.pojo.Person;
import com.yawei.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

public class OneToOneTestCase {

    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = new Person();
        person.setName("wang");

        Card card = new Card();
        card.setCardname("101");
        card.setPerson(person);

        session.save(person);
        session.save(card);

        session.getTransaction().commit();
    }

    @Test
    public void testFindPerson() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = (Person) session.get(Person.class, 10);
        System.out.println(person.getName());
        System.out.println(person.getCard().getCardname());

        session.getTransaction().commit();
    }
    @Test
    public void testFindCard() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Card card = (Card) session.get(Card.class, 10);
        System.out.println(card.getCardname());
        System.out.println(card.getPerson().getName());

        session.getTransaction().commit();
    }
    @Test
    public void tesDelCard() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Card card = (Card) session.get(Card.class, 10);
        session.delete(card);

        session.getTransaction().commit();
    }
    @Test
    public void tesDelPerson() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = (Person) session.get(Person.class, 11);
        session.delete(person);

        session.getTransaction().commit();
    }
}
