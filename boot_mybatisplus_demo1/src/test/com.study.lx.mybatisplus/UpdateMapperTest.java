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
public class UpdateMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;


    /**
     * table的结构
     `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
     `user_name` varchar(50) NOT NULL COMMENT '用户名称',
     `age` smallint(3) NOT NULL COMMENT '年龄',
     `email` varchar(50) NOT NULL COMMENT '邮箱',
     `manager_id` int(11) NOT NULL COMMENT '上级id',
     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
     `update_time` datetime DEFAULT NULL COMMENT '修改时间',
     `del_flag` int(11) DEFAULT '0' COMMENT '逻辑删除状态 0表示没有删除 1表示已经删除',
     `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
     */


    @Test
    public void updateDemo1(){
        UserInfo userInfo = userInfoMapper.selectById(1);
        userInfoMapper.updateById(userInfo);
    }
//
//
//    @Test
//    public void updateDemo2(){
//        UserInfo userInfo = userInfoMapper.selectById(1);
//        userInfo.setCreateTime(LocalDateTime.now());
//        userInfoMapper.updateById(userInfo);
//    }
//
//
    @Test
    public void init(){
        for (int i=0;i<10;i++){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("name"+i);
            userInfo.setAge(18 + new Random().nextInt(10));
            userInfo.setCreateTime(LocalDateTime.now());
            userInfo.setUpdateTime(LocalDateTime.now());
            userInfo.setRoleId(1);
            userInfo.setEmail(userInfo.getUserName() + "@lx.com");
            userInfoMapper.insert(userInfo);
        }
    }


}
