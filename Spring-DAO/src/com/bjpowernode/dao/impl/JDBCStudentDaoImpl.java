package com.bjpowernode.dao.impl;

import com.bjpowernode.beans.Student;
import com.bjpowernode.beans.StudentRowMapper;
import com.bjpowernode.dao.IStudentDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class JDBCStudentDaoImpl extends JdbcDaoSupport implements IStudentDao {


    @Override
    public void insertStudent(Student student) {
        String sql = "insert into t_student(sname,sage,score) values (?,?,?)";
        int update = this.getJdbcTemplate().update(sql, student.getSname(), student.getSage(), student.getScore());
        if (update == 0) {
            throw new RuntimeException("插入失败");
        } else {
            System.out.println("插入成功");
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from t_student where sid=?";
        int update = this.getJdbcTemplate().update(sql, id);
        if (update == 0) {
            throw new RuntimeException("删除失败");
        } else {
            System.out.println("删除成功");
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "update t_student set sname=?,sage=?,score=? where sid=?";
        int update = this.getJdbcTemplate().update(sql, student.getSname(), student.getSage(), student.getScore(), student.getSid());
        if (update == 0) {
            throw new RuntimeException("更新失败");
        } else {
            System.out.println("更新成功");
        }
    }

    @Override
    public List<String> selectAllStudentsNames() {
        String sql = "select sname from t_student";
        List<String> names = this.getJdbcTemplate().queryForList(sql, String.class);
        return names;
    }

    @Override
    public String selectStudentNameById(int id) {
        String sql = "select sname from t_student where sid = ?";
        String name = this.getJdbcTemplate().queryForObject(sql, String.class, id);
        return name;
    }

    @Override
    public List<Student> selectAllStudents() {
        String sql = "select sid,sname,sage,score from t_student";
        List<Student> students = this.getJdbcTemplate().query(sql, new StudentRowMapper());
        return students;
    }

    @Override
    public Student selectStudentById(int id) {
        String sql = "select sid,sname,sage,score from t_student where sid = ?";
        Student student = this.getJdbcTemplate().queryForObject(sql, new StudentRowMapper(), id);
        return student;
    }
}
