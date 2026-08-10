package com.itheima.service;

import com.itheima.pojo.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    //分页查询
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    //根据id批量删除
    void delete(List<Integer> ids);

    //根据id回显
    Emp get(Integer id);

    //修改信息
    void update(Emp emp);

    List<Emp> getList();

    LoginInfo login(Emp emp);
}


