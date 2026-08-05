package com.itheima.service;

import com.itheima.pojo.ClazzQueryString;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void save(Student student);

    Student getById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void violation(Integer id,Integer score);
}
