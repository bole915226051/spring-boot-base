package com.liyuanqing.myBatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DemoMapper {

    List<Map<String,Object>> selectByMap(@Param("map") Map<String, List<Integer>> map);

}
