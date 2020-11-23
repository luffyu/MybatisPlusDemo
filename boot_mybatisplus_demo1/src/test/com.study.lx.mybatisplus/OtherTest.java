package com.study.lx.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.lx.mybatisplus.entity.UserInfo;
import com.study.lx.mybatisplus.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author rockyu
 * @date 2020/11/23 16:17
 */
@Slf4j
@SpringBootTest
public class OtherTest {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Test
    public void test1(){
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(UserInfo::getAge,18);
        List<UserInfo> list = userInfoMapper.selectAll(lambdaQueryWrapper);
        log.info("结果>>" + list);
    }

}
