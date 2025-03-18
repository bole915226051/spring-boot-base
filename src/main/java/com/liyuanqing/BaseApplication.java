package com.liyuanqing;

import com.liyuanqing.myBatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
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
public class BaseApplication implements ApplicationRunner {
    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // demo代码提交123,ttst
        log.info("这是打印的日志docker run -p 8082:8082 -d mac-base:{}", Thread.currentThread().getName());
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
