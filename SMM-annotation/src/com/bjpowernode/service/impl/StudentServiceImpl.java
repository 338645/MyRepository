package com.bjpowernode.service.impl;

import com.bjpowernode.beans.Student;
import com.bjpowernode.dao.IStudentDao;
import com.bjpowernode.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("studentService")
public class StudentServiceImpl implements IStudentService {
    @Resource(name = "IStudentDao")
    private IStudentDao dao;

    public void setDao(IStudentDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void addStudent(Student student) {
        dao.insertStudent(student);
    }
}
