package com.liyuanqing;

import com.liyuanqing.date.HolidayUtil;
import com.liyuanqing.date.HolidayVo;
import com.liyuanqing.myBatis.entity.WorkDay;
import com.liyuanqing.myBatis.mapper.UserMapper;
import com.liyuanqing.myBatis.mapper.WorkDayMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//  启用异步
@EnableAsync
@Slf4j
public class BaseApplication implements ApplicationRunner {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkDayMapper workDayMapper;

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        log.info("这是打印的日志docker run -p 8082:8082 -d mac-base:{}", Thread.currentThread().getName());
        // demo代码提交123,ttst
//        Faker faker = Faker.instance(new Random());
//        int count = userMapper.insert(User.builder()
//                .username(faker.name().fullName())
//                .phone(faker.phoneNumber().cellPhone())
//                .build());
//        log.info("插入数据条数:count={}", count);
//        List<User> userList = userMapper.selectList();
//        log.info("查询数据:{}", JSON.toJSONString(userList));
        ArrayList<HolidayVo> holidayVos = HolidayUtil.getAllHolidayByYear("2021");
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

    }
}
