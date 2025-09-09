package com.liyuanqing;

import com.liyuanqing.myBatis.mapper.DemoMapper;
import com.liyuanqing.myBatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//  启用异步
@EnableAsync
@Slf4j
@MapperScan("com.liyuanqing")
public class BaseApplication implements ApplicationRunner {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DemoMapper demoMapper;

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


    }
}
