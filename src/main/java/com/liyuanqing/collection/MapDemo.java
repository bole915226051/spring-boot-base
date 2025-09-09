package com.liyuanqing.collection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class MapDemo {
    public static void main(String[] args) {
        String jsonInput = "[{\"A\":\"a\",\"B\":\"b\",\"C\":[{\"CA\":\"ca\",\"CB\":\"cb\",\"DD\":[{\"DA\":\"da\",\"DB\":\"db\",\"DC\":\"dc\"}]}]},{\"A\":\"a\",\"B\":\"b\",\"C\":[{\"CA\":\"ca\",\"CB\":\"cb\",\"DD\":[{\"DA\":\"da\",\"DB\":\"db\",\"DC\":\"dc\"}]}]},{\"A\":\"a\",\"B\":\"b\",\"C\":[{\"CA\":\"ca\",\"CB\":\"cb\",\"DD\":[{\"DA\":\"da\",\"DB\":\"db\",\"DC\":\"dc\"}]}]},{\"A\":\"a\",\"B\":\"b\",\"C\":[{\"CA\":\"ca\",\"CB\":\"cb\",\"DD\":[{\"DA\":\"da\",\"DB\":\"db\",\"DC\":\"dc\"}]}]}]";

        JSONArray jsonArray = JSON.parseArray(jsonInput);
        List<Map<String, Object>> flattenedList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            flattenedList.add(flatten(jsonObject));
        }

        // 打印扁平化后的结果
        for (Map<String, Object> map : flattenedList) {
            System.out.println(map);
        }
    }


    private static Map<String, Object> flatten(JSONObject jsonObject) {
        Map<String, Object> flattenedMap = new HashMap<>();

        Iterator<String> iterator = jsonObject.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                flattenedMap.putAll(flatten((JSONObject) value));
            } else if (value instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<>();
                for (int j = 0; j < ((JSONArray) value).size(); j++) {
                    list.add(flatten(((JSONArray) value).getJSONObject(j)));
                }
                flattenedMap.put(key, list);
            } else {
                flattenedMap.put(key, value);
            }
        }
        return flattenedMap;
    }

}
