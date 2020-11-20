package com.study.lx.mybatisplus;

import com.study.lx.mybatisplus.entity.UserInfo;
import com.study.lx.mybatisplus.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author rockyu
 * @date 2020/11/16 18:29
 */
@Slf4j
@SpringBootTest
public class UpdateMapperService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    /**
     * table的结构
     *
         id          int(11) unsigned auto_increment comment '自增id'
         primary key,
         user_name   varchar(50)             not null comment '用户名称',
         user_age    smallint(3)             not null comment '年龄',
         email    varchar(50)             not null comment '邮箱',
         version     int          default 0  not null comment '版本号',
         role_id  varchar(255) default '' null comment '管理id',
         create_time datetime                null comment '创建时间',
         del_flag    int          default 0  null comment '逻辑删除状态 0表示没有删除 1表示已经删除'
     */



    @Test
    public void updateDemo1(){
        UserInfo userInfo = userInfoMapper.selectById(1);
        userInfoMapper.updateById(userInfo);
    }


    @Test
    public void updateDemo2(){
        UserInfo userInfo = userInfoMapper.selectById(1);
        userInfo.setCreateTime(LocalDateTime.now());
        userInfoMapper.updateById(userInfo);
    }


    @Test
    public void init(){
        for (int i=0;i<10;i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("name"+i);
            userInfo.setUserAge(new Random().nextInt(100));
            userInfo.setCreateTime(LocalDateTime.now());
            userInfo.setRoleId(1);
            userInfo.setEmail(userInfo.getUserName() + "@lx.com");
            userInfoMapper.insert(userInfo);
        }
    }


}
