package com.yawei.test;


import com.yawei.pojo.Student;
import com.yawei.pojo.Teacher;
import com.yawei.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ManyToManyTestCase {

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher teacher1 = new Teacher();
        teacher1.setTeaname("liu");

        Teacher teacher2 = new Teacher();
        teacher2.setTeaname("wang");

        Student student1 = new Student();
        student1.setStuname("da");

        Student student2 = new Student();
        student2.setStuname("xiao");

        Set<Teacher> teacherSet = new HashSet<>();
        teacherSet.add(teacher1);
        teacherSet.add(teacher2);

        student1.setTeacherSet(teacherSet);
        student2.setTeacherSet(teacherSet);

        session.save(teacher1);
        session.save(teacher2);
        session.save(student1);
        session.save(student2);

        session.getTransaction().commit();
    }

    @Test
    public void testFindTeacher(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher teacher = (Teacher) session.get(Teacher.class,15);
        System.out.println(teacher.getTeaname());

        Set<Student> studentSet = teacher.getStudentSet();
        for(Student student:studentSet){
            System.out.println(student.getId()+":"+student.getStuname());
        }

        session.getTransaction().commit();
    }
}
