package com.liyuanqing.myBatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkDay {
    private Long id;
    private String date;
    private String state;
    private String remark;
}
