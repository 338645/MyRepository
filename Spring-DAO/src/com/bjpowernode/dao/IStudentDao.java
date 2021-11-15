package com.bjpowernode.dao;

import com.bjpowernode.beans.Student;

import java.util.List;

public interface IStudentDao {
    void insertStudent(Student student);

    void deleteById(int id);

    void updateStudent(Student student);

    List<String> selectAllStudentsNames();

    String selectStudentNameById(int id);

    List<Student> selectAllStudents();

    Student selectStudentById(int id);
}
