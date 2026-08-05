package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id, name, create_time, update_time from dept order by update_time desc;")
    public List<Dept> findAll();

    //根据id删除部门
    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);

    //新增部门
    @Insert("insert into dept (name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    public void add(Dept dept);

    //根据id查找
    @Select("select * from dept where id = #{id}")
    public Dept getById(Integer id);

    //根据id修改
    @Update("update dept set name = #{name} , update_time = #{updateTime} where id = #{id}")
    public void update(Dept dept);


}
