package com.itheima.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private Integer gender;
    private LocalDate begin;
    private LocalDate end;
}
