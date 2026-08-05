package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryString;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    ClazzService clazzService;

    @GetMapping
    public Result Page(ClazzQueryString clazzQueryString){
        log.info("分页查询:{}",clazzQueryString);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryString);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    public Result getClazz(){
        log.info("获取所有班级");
        return Result.success(clazzService.getClazz());
    }

    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班级{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result save(@PathVariable Integer id){
        log.info("根据id查询:{}",id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除班级:{}",id);
        clazzService.delete(id);
        return Result.success();
    }
}
