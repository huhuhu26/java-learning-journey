package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryString;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {

    public List<Clazz> list(ClazzQueryString clazzQueryString);


    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) values " +
            "(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    void update(Clazz clazz);


    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);


    @Select("select * from clazz")
    List<Clazz> getClazz();


}
