package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    //查询学员列表
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("查询学员列表");
        PageResult<Student> pageResult= studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加学员");
        studentService.save(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询学员");
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学员信息:{}",student);
        studentService.update(student);
        return Result.success();
    }


    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除员工:{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("修改学员违纪信息id:{},score:{}",id,score);
        studentService.violation(id,score);
        return Result.success();
    }
}
