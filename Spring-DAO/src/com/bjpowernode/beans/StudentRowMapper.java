package com.bjpowernode.beans;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    /**
     * rs:当查询出总的结果集后，框架会自动遍历这个结果集，每遍历一行都会存放到
     * 这个方法参数rs中,这个方法中的rs只有一行的数据。
     */
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        //System.out.println(rowNum);
        Student student = new Student(rs.getString("sname"), rs.getInt("sage"), rs.getDouble("score"));
        student.setSid(rs.getInt("sid"));
        return student;
    }
}
