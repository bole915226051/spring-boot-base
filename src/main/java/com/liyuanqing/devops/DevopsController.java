package com.liyuanqing.devops;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/devops")
@Slf4j
public class DevopsController {
    @Resource
    WorkDayMapper workDayMapper;

    /**
     * devops登记工时，时间需要判断，工作日/节假日。
     * 该接口功能用于调用第三方平台获取一年的 工作日/节假日。
     *
     * @param year
     * @return
     */
    @GetMapping("/createWorkDay")
    public boolean createWorkDay(@RequestParam("year") String year) {
        try {

            ArrayList<HolidayVo> holidayVos = HolidayUtil.getAllHolidayByYear(year);
            for (HolidayVo holidayVo : holidayVos) {
                WorkDay workDay = new WorkDay();
                workDay.setDate(holidayVo.getData().replace("-", ""));
                if (holidayVo.getStatus().equals("0") || holidayVo.getStatus().equals("3")) {
                    workDay.setState("work");
                } else {
                    workDay.setState("rest");
                }
                workDay.setRemark(holidayVo.getMsg());
                workDayMapper.insert(workDay);
                System.out.printf(workDay.getDate() + "|");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
