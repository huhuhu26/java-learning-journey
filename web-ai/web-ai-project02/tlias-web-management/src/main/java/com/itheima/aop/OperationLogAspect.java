package com.itheima.aop;


import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        //计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - start;

        //构建日志实体
        OperateLog log = new OperateLog();
        //获取当前用户id
        log.setOperateEmpId(getCurrentUserId());

        log.setOperateTime(LocalDateTime.now());

        log.setClassName(joinPoint.getTarget().getClass().getName());
        log.setMethodName(joinPoint.getSignature().getName());
        //传入参数
        log.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        log.setReturnValue(result != null ? result.toString() : null);
        log.setCostTime(costTime);

        //保存日志
        operateLogMapper.insert(log);

        return result;
    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}
