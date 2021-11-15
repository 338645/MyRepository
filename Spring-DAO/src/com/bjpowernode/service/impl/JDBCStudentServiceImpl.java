package com.bjpowernode.service.impl;

import com.bjpowernode.beans.Student;
import com.bjpowernode.dao.IStudentDao;
import com.bjpowernode.service.IStudentService;

import java.util.List;

public class JDBCStudentServiceImpl implements IStudentService {
    private IStudentDao dao;

    public void setDao(IStudentDao dao) {
        this.dao = dao;
    }

    @Override
    public void addStudent(Student student) {
        dao.insertStudent(student);
    }

    @Override
    public void removeById(int id) {
        dao.deleteById(id);
    }

    @Override
    public void updateStudent(Student student) {
        dao.updateStudent(student);
    }

    @Override
    public List<String> getAllStudentsNames() {
        List<String> names = dao.selectAllStudentsNames();
        return names;
    }

    @Override
    public String getStudentNameById(int id) {
        String name = dao.selectStudentNameById(id);
        return name;
    }

    @Override
    public List<Student> selectAllStudents() {
        List<Student> students = dao.selectAllStudents();
        return students;
    }

    @Override
    public Student selectStudentById(int id) {
        Student student = dao.selectStudentById(id);
        return student;
    }
}
