package com.bjpowernode.handlers;

import com.bjpowernode.beans.Student;
import com.bjpowernode.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentXmlController {
    @Autowired
    @Qualifier("studentService")
    private IStudentService service;

    public void setService(IStudentService service) {
        this.service = service;
    }

    @RequestMapping("/register.do")
    public String handleRequest(String name, String age) {
        Student student = new Student(name, Integer.valueOf(age));
        service.addStudent(student);
        return "WEB-INF/jsp/welcome.jsp";
    }
}
