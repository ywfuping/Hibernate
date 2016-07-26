package com.yawei.test;


import com.yawei.pojo.Dept;
import com.yawei.pojo.Employee;
import com.yawei.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class OneToManyTestCase {

    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Dept dept = new Dept();
        dept.setDeptname("组织部");

        Employee employee1 = new Employee();
        employee1.setEmpname("loui");
        employee1.setDept(dept);

        Employee employee2 = new Employee();
        employee2.setEmpname("hah");
        employee2.setDept(dept);

        session.save(dept);
        session.save(employee1);
        session.save(employee2);

        session.getTransaction().commit();
    }

    @Test
    public void testFindDept() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Dept dept = (Dept) session.get(Dept.class, 15);
        System.out.println(dept.getDeptname());

        Set<Employee> employeeList = dept.getEmployeeSet();
        for (Employee employee : employeeList) {
            System.out.println(employee.getEmpname());
        }

        session.getTransaction().commit();
    }

    @Test
    public void testFindEmployee() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Employee employee = (Employee) session.get(Employee.class, 29);
        System.out.println(employee.getEmpname());
        Dept dept = employee.getDept();
        System.out.println(dept.getDeptname());

        session.getTransaction().commit();
    }
    @Test
    public void testFindEmployeeAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        //在多的一方配置fetch:join避免N+1问题
        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> employeeList = criteria.list();

        for (Employee employee :employeeList){
            System.out.println(employee.getEmpname()+"->"+employee.getDept().getDeptname());
        }

        session.getTransaction().commit();
    }
    @Test
    public void testDeleteDept() {

        //在一的一方删除时，会自动删除关联的多的一方  设置cascade="delete"
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Dept dept = (Dept) session.get(Dept.class,16);
        session.delete(dept);

        session.getTransaction().commit();
    }
}
