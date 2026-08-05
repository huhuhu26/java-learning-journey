package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryString;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryString clazzQueryString);

    void save(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void delete(Integer id);

    List<Clazz> getClazz();
}
