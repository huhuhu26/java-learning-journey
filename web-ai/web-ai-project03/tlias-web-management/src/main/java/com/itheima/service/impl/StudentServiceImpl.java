package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());

        List<Student> studentList = studentMapper.list(studentQueryParam);

        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Student student) {
        studentMapper.save(student);
    }

    @Override
    public Student getById(Integer id) {
        //根据id查询
        Student student = studentMapper.getById(id);
        return student;
    }

    @Override
    public void update(Student student) {
        //修改学员信息
        studentMapper.update(student);

    }

    @Override
    public void delete(List<Integer> ids) {
        //根据id删除员工
        studentMapper.delete(ids);
    }

    @Transactional
    @Override
    public void violation(Integer id, Integer score) {
        //查询原来的违纪信息
        StudentViolationVO vo = studentMapper.getViolation(id);
        vo.setViolationCount(vo.getViolationCount()+1);
        vo.setViolationScore(vo.getViolationScore()+score);
        vo.setId(id);
        //保存现在的违纪信息
        studentMapper.saveViolation(vo);
    }
}
