package com.itheima.mapper;

import com.itheima.pojo.ClazzQueryString;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.pojo.StudentViolationVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;



@Mapper
public interface StudentMapper {



    //根据班级id获取学生数
    @Select("select count(*) from student where student.clazz_id = #{id}")
    public Integer getByClazzId(Integer id);

    public List<Student> list(StudentQueryParam studentQueryParam);

    void save(Student student);

    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    @Select("select violation_count,violation_score from student where id = #{id}")
    StudentViolationVO getViolation(Integer id);

    @Update("update student set violation_count = #{violationCount},violation_score = #{violationScore} where id = #{id}")
    void saveViolation(StudentViolationVO vo);

    //获取班级人数统计
    List<Map<String, Object>> coutStudentData();

    List<Map<String, Object>> coutStudentDegreeData();
}
