package com.liyuanqing.base.map;

import com.alibaba.fastjson.JSON;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class SortMap {
    public static void main(String[] args) {
        String jsonString = "{\"content\":{\"bankCode\":\"6555\",\"instCode\":\"\",\"belongType\":\"00\",\"pageNo\":1,\"pageSize\":10},\"pageNo\":1,\"pageSize\":10}";
        HashMap<String, Object> map = JSON.parseObject(jsonString, HashMap.class);
        LinkedHashMap<String, Object> linkedHashMap =
                map.entrySet().stream()
                        .sorted(Comparator.comparing(
                                value -> JSON.toJSONString(value).length()))
                        .collect(Collectors.toMap(
                                v -> v.getKey(),
                                v -> v.getValue(),
                                (oldV, neweV) -> oldV,
                                LinkedHashMap::new
                        ));
        System.out.println(linkedHashMap);
    }
}
