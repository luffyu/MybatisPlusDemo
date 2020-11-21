package com.study.lx.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.study.lx.mybatis.mapper")
@SpringBootApplication
public class BootMybatisDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisDemo1Application.class, args);
    }

}
