package com.bjpowernode.test;

import com.bjpowernode.beans.Student;
import com.bjpowernode.service.IStudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest1 {
    private ApplicationContext ac;
    private String resources;

    @Before
    public void Before() {
        resources = "spring-dao.xml";
        ac = new ClassPathXmlApplicationContext(resources);
    }

    @Test
    public void Test01() {
        IStudentService service = (IStudentService) ac.getBean("studentService");
        Student jack = new Student("jack", 25, 87);
        service.addStudent(jack);
        Student mike = new Student("mike", 25, 87);
        service.addStudent(mike);
        Student lucy = new Student("lucy", 25, 87);
        service.addStudent(lucy);
        //service.removeById(11);
        lucy.setScore(99);
        lucy.setSid(3);
        service.updateStudent(lucy);
    }

    @Test
    public void Test02() {
        IStudentService service = (IStudentService) ac.getBean("studentService");
        List<String> names = service.getAllStudentsNames();
        System.out.println(names);
        String name = service.getStudentNameById(2);
        System.out.println(name);
    }

    @Test
    public void Test03() {
        IStudentService service = (IStudentService) ac.getBean("studentService");
        List<Student> students = service.selectAllStudents();
        System.out.println(students);
    }

}
