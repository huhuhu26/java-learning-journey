package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result Page(EmpQueryParam empQueryParam){
    log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    public Result getList(){
        log.info("获取全部员工");
        return Result.success(empService.getList());
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("根据id删除: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //查询回显
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("根据id回显：{}",id);
        Emp emp = empService.get(id);
        return Result.success(emp);
    }

    //修改信息
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
