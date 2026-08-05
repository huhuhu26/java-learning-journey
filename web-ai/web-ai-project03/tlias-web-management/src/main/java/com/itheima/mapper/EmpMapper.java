package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Mapper

public interface EmpMapper {

    //查询总人数
    //@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    //public long count();

    //分页查询
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize}")

    public List<Emp> list(EmpQueryParam empQueryParam);

    //保存员工基本信息
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    //根据id更新员工基本信息

    void updateById(Emp emp);

    //获取员工职位信息
    List<Map<String,Object>> countEmpJobData();

    //获取员工性别信息
    List<Map<String,Object>> countEmpGenderData();

    //获取全部员工
    @Select("select * from emp")
    List<Emp> getList();

    //根据dept_id获取员工数量
    @Select("select count(*) from emp where dept_id = #{id}")
    public Integer getByDeptId(Integer id);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
