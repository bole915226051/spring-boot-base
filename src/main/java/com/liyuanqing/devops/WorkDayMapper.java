package com.liyuanqing.devops;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface WorkDayMapper {
    @Insert("insert work_day (date,state,remark) value (#{date},#{state},#{remark})")
    @Options(useGeneratedKeys = true)
    int insert(WorkDay workDay);

}
