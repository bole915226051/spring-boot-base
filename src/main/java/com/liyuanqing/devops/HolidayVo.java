package com.liyuanqing.devops;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HolidayVo {
    private String data;//日期

    private String status;//状态：0工作日/1周末/2法定节假日/3节假日调休补班

    private String msg;//描述
}
