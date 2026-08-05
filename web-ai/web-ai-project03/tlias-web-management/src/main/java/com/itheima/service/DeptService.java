package com.itheima.service;

import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {

    //查询部门
    List<Dept> findAll();

    //根据id删除部门
    void deleteById(Integer id);

    //新增部门
    void add(Dept dept);

    //根据id查找部门
    Dept getById(Integer id);

    //根据id修改
    void update(Dept dept);
}
