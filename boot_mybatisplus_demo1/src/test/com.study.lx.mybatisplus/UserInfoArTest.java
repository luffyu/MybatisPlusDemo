package com.study.lx.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.lx.mybatisplus.entity.UserInfoAr;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author luffyu
 * Created on 2020/11/21
 */
@Slf4j
@SpringBootTest
public class UserInfoArTest {



    @Test
    public void insertTest(){
        UserInfoAr userInfoAr = new UserInfoAr();
        userInfoAr.setUserName("rock");
        userInfoAr.setAge(26);
        userInfoAr.setCreateTime(LocalDateTime.now());
        userInfoAr.setUpdateTime(LocalDateTime.now());
        userInfoAr.setManagerId(1);
        userInfoAr.setEmail("rockyu@lx.com");

        userInfoAr.insert();
    }


    @Test
    public void selectTest(){
        UserInfoAr userInfoAr = new UserInfoAr();
        userInfoAr.setUserId(1);
        userInfoAr.selectById();
    }

    @Test
    public void selectTest2(){
        LambdaQueryWrapper<UserInfoAr> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInfoAr::getUserName,"rock");
        UserInfoAr userInfoArs = new UserInfoAr().selectOne(lambdaQueryWrapper);
        log.info("结果：{}",userInfoArs);
    }
}
