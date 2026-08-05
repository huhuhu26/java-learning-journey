package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import com.itheima.service.impl.DeptServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //1查询列表

    @GetMapping
    public Result findAll(){
        log.info("查询部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //2删除部门

    //方式一 不常用
    /*@DeleteMapping("/depts")
    public Result Delete(HttpServletRequest request){
        String idstr = request.getParameter("id");
        int id = Integer.parseInt(idstr);
        System.out.println("根据id删除部门:" + id);
        return Result.success();
    }*/

    //方式二 不需要手动转换
    @Log
    @DeleteMapping
    public Result Delete(Integer id){
        log.info("根据id删除: {}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    //3添加部门
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门： {}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据id查找
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询：{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //根据id修改
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success(dept);
    }
}
