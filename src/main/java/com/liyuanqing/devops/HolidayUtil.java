package com.liyuanqing.devops;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 节假日工具类
 */
@Slf4j
public class HolidayUtil {

    /**
     * 发送get请求
     */
    private static String get(String url) {
        StringBuilder inputLine = new StringBuilder();
        String read;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setReadTimeout(30 * 1000);
            urlConnection.setConnectTimeout(30 * 1000);
            urlConnection.setRequestProperty("Charset", "UTF-8");
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            while ((read = in.readLine()) != null) {
                inputLine.append(read);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputLine.toString();
    }

    /**
     * 调用免费API查询全年工作日、周末、法定节假日、节假日调休补班数据
     * 1、调用 https://api.apihubs.cn/holiday/get?size=500&year=2021 查询全年日历（含周末）
     * 2、调用 https://timor.tech/api/holiday/year/2021 查询全年节假日、调休
     */
    public static ArrayList<HolidayVo> getAllHolidayByYear(String year) throws IOException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<HolidayVo> holidayVoList = new ArrayList<>();
        HashMap<String, HolidayVo> hashMap = new HashMap<>();


        //查询全年日历包含周末
        String allDayJson = HolidayUtil.get("https://api.apihubs.cn/holiday/get?size=500&year=" + year);
        System.out.println("allDayJson>>>>>>>>>>>>>>>>>" + allDayJson);
        ObjectMapper mapper = new ObjectMapper();
        Map allDayMap = mapper.readValue(allDayJson, Map.class);
        Map allDayData = (Map) allDayMap.get("data");
        List allDayDataList = (List) allDayData.get("list");
        allDayDataList.forEach((value) -> {
            HolidayVo holidayVo = new HolidayVo();

            Map dayVO = (Map) value;
            String YEAR = dayVO.get("year").toString();
            String MONTH = dayVO.get("month").toString().replace(YEAR, "");
            String DAY = dayVO.get("date").toString().replace(YEAR + MONTH, "");

            holidayVo.setData(YEAR + "-" + MONTH + "-" + DAY);
            String STATUS = "0";
            String msg = "工作日";
            if ("1".equals(dayVO.get("weekend").toString())) {
                STATUS = "1";
                msg = "周末";
            }
            holidayVo.setStatus(STATUS);
            holidayVo.setMsg(msg);

            hashMap.put(holidayVo.getData(), holidayVo);
        });
        //  API获取法定假日暂时没有维护，使用自己手写的方法吧
        if ("2026".equals(year)) {
            List<HolidayVo> holidayVos = new ArrayList<>();
            holidayVos.add(HolidayVo.builder().data("2026-01-01").status("2").msg("法定节假日(元旦)").build());
            holidayVos.add(HolidayVo.builder().data("2026-01-02").status("2").msg("法定节假日(元旦)").build());
            holidayVos.add(HolidayVo.builder().data("2026-01-03").status("2").msg("法定节假日(元旦)").build());
            holidayVos.add(HolidayVo.builder().data("2026-01-04").status("3").msg("节假日调休补班(元旦)").build());

            holidayVos.add(HolidayVo.builder().data("2026-02-15").status("2").msg("法定节假日(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-16").status("2").msg("法定节假日(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-17").status("2").msg("法定节假日(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-18").status("2").msg("法定节假日(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-19").status("2").msg("法定节假日(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-20").status("2").msg("法定节假日(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-21").status("2").msg("法定节假日(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-22").status("2").msg("法定节假日(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-23").status("2").msg("法定节假日(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-14").status("3").msg("节假日调休补班(春节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-02-28").status("3").msg("节假日调休补班(春节)").build());

            holidayVos.add(HolidayVo.builder().data("2026-04-04").status("2").msg("法定节假日(清明节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-04-05").status("2").msg("法定节假日(清明节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-04-06").status("2").msg("法定节假日(清明节)").build());

            holidayVos.add(HolidayVo.builder().data("2026-05-01").status("2").msg("法定节假日(劳动节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-05-02").status("2").msg("法定节假日(劳动节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-05-03").status("2").msg("法定节假日(劳动节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-05-04").status("2").msg("法定节假日(劳动节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-05-05").status("2").msg("法定节假日(劳动节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-05-09").status("3").msg("节假日调休补班(劳动节)").build());

            holidayVos.add(HolidayVo.builder().data("2026-06-19").status("2").msg("法定节假日(端午节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-06-20").status("2").msg("法定节假日(端午节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-06-21").status("2").msg("法定节假日(端午节)").build());

            holidayVos.add(HolidayVo.builder().data("2026-09-25").status("2").msg("法定节假日(中秋节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-09-26").status("2").msg("法定节假日(中秋节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-09-27").status("2").msg("法定节假日(中秋节)").build());

            holidayVos.add(HolidayVo.builder().data("2026-10-01").status("2").msg("法定节假日(国庆节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-10-02").status("2").msg("法定节假日(国庆节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-10-03").status("2").msg("法定节假日(国庆节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-10-04").status("2").msg("法定节假日(国庆节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-10-05").status("2").msg("法定节假日(国庆节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-10-06").status("2").msg("法定节假日(国庆节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-10-07").status("2").msg("法定节假日(国庆节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-09-20").status("3").msg("节假日调休补班(国庆节)").build());
            holidayVos.add(HolidayVo.builder().data("2026-10-10").status("3").msg("节假日调休补班(国庆节)").build());


            for (HolidayVo vo : holidayVos) {
                hashMap.replace(vo.getData(), vo);
            }
        } else {
            apiStatutoryHoliday(year, hashMap, mapper);
        }


        for (String key : hashMap.keySet()) {
            holidayVoList.add(hashMap.get(key));

        }

        //排序
        holidayVoList.sort((a, b) -> {
            try {
                return sf.parse(a.getData()).compareTo(sf.parse(b.getData()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        });

        return holidayVoList;
    }

    private static void apiStatutoryHoliday(String year, HashMap<String, HolidayVo> hashMap, ObjectMapper mapper) throws com.fasterxml.jackson.core.JsonProcessingException {
        //查询全年节假日、调休
        String holidayJson = HolidayUtil.get("https://timor.tech/api/holiday/year/" + year + "/");
        Map holidayMap = mapper.readValue(holidayJson, Map.class);
        LinkedHashMap holidayList = (LinkedHashMap) holidayMap.get("holiday");
        holidayList.forEach((key, value) -> {
            HolidayVo holidayVo = new HolidayVo();

            Map dayVO = (Map) value;
            String dateTime = dayVO.get("date").toString();

            holidayVo.setData(dateTime);
            String STATUS = "2";
            String msg = "法定节假日(" + dayVO.get("name").toString() + ")";
            if (!(boolean) dayVO.get("holiday")) {
                STATUS = "3";
                msg = "节假日调休补班(" + dayVO.get("target").toString() + ")";
            }
            holidayVo.setStatus(STATUS);
            holidayVo.setMsg(msg);

            hashMap.replace(holidayVo.getData(), holidayVo);
        });
    }

    public static void main(String[] args) {
        try {
            ArrayList<HolidayVo> HolidayVoList = HolidayUtil.getAllHolidayByYear("2021");
            System.err.println("全年完整数据：");
            for (HolidayVo HolidayVo : HolidayVoList) {
                System.err.println(HolidayVo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}