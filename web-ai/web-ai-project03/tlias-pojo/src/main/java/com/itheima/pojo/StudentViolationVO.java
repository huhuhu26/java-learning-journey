package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentViolationVO {
    private Integer violationCount;
    private Integer violationScore;
    private Integer id;
}
