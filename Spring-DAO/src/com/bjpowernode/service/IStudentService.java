package com.bjpowernode.service;

import com.bjpowernode.beans.Student;

import java.util.List;

public interface IStudentService {
    void addStudent(Student student);

    void removeById(int id);

    void updateStudent(Student student);

    List<String> getAllStudentsNames();

    String getStudentNameById(int id);

    List<Student> selectAllStudents();

    Student selectStudentById(int id);
}
