package com.itheima.Exception;


import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Handler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.info("程序出错了",e);
        return Result.error("出错了，请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.info("程序出错了",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");

    }

    @ExceptionHandler
    public Result handleClassHasStudentException(ClassHasStudentException c){
        log.info("班级与学生关联异常",c);
        return Result.error(c.getMessage());
    }

    @ExceptionHandler
    public Result handleDeptHasEmpException(DeptHasEmpException d){
        log.info("部门与员工关联异常",d);
        return Result.error(d.getMessage());
    }
}
