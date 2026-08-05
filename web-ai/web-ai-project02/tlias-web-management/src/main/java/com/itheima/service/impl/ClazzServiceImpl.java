package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.Exception.ClassHasStudentException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryString;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryString clazzQueryString) {

        PageHelper.startPage(clazzQueryString.getPage(),clazzQueryString.getPageSize());

        List<Clazz> clazzList = clazzMapper.list(clazzQueryString);
        Page<Clazz> p = (Page<Clazz>) clazzList;

        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Clazz clazz) {
        //添加班级
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        //根据id查询
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        //修改班级数据
        clazzMapper.update(clazz);
    }

    @Override
    public void delete(Integer id) {
        //判断该班级下是否有学生
        if(studentMapper.getByClazzId(id) > 0){
            throw new ClassHasStudentException("对不起，该班级下有学生，不能直接删除");
        }
        //根据id删除班级
        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> getClazz() {
        return clazzMapper.getClazz();
    }
}
