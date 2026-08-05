package com.itheima.controller;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("获取员工职位数据");
        return Result.success(reportService.getEmpJobData());
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("获取员工性别数据");
        return Result.success(reportService.getEmpGenderData());
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("获取班级人数数据");
        return Result.success(reportService.getStudentCountData());
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("获取学员学历信息");
        return Result.success(reportService.getStudentDegreeData());
    }

}
