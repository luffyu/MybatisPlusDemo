package com.study.lx.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.study.lx.mybatisplus.mapper")
public class BootMybatisplusDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisplusDemo1Application.class, args);
    }

}
