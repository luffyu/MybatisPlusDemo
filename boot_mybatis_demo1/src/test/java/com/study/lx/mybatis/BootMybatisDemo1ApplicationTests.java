package com.study.lx.mybatis;

import com.study.lx.mybatis.entity.UserInfo;
import com.study.lx.mybatis.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class BootMybatisDemo1ApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void contextLoads() {
        UserInfo userInfo = userInfoMapper.queryById(1);
        log.info("结果 >>> {}" ,userInfo);
    }

}
