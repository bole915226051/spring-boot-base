package com.liyuanqing;

import com.alibaba.fastjson.JSON;
import com.github.javafaker.Faker;
import com.liyuanqing.myBatis.entity.User;
import com.liyuanqing.myBatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;
import java.util.Random;

@SpringBootApplication
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
