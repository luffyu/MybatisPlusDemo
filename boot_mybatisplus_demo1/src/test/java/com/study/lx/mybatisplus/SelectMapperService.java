package com.study.lx.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.lx.mybatisplus.entity.UserInfo;
import com.study.lx.mybatisplus.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author rockyu
 * @date 2020/11/16 18:29
 */
@Slf4j
@SpringBootTest
public class SelectMapperService {

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


    /**
     * 例子1
     * select *
     * from user_info
     * where uid = 1
     *
     */
    @Test
    public void selectDemo1(){
        UserInfo userInfo = userInfoMapper.selectById(1);
        log.info("结果 >>>" + userInfo);
    }



    /**
     * 例子2
     * select *
     * from user_info
     * where email like "%lx%" and user_age between 20 and 22
     * order by user_age desc
     *
     */
    @Test
    public void selectDemo2(){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("email","lx")
                .between("user_age",20,22)
                .orderByDesc("user_age");
        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);
        log.info("结果 >>>"+ userInfos);
    }
    @Test
    public void selectDemo3(){
        //LambdaQueryWrapper 的写法
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(UserInfo::getEmail,"lx")
                .between(UserInfo::getUserAge,20,22)
                .orderByDesc(UserInfo::getUserAge);
        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);
        log.info("结果 >>>" + userInfos);
    }


    /**
     * 例子: 查询 邮箱包含"lx" 或者 年龄大于20岁  并且 角色名称为 后端 的 各年龄人数，并按照人数倒序排序
         select user_age,count(1) as num
         from user_info
         where (email like "%lx%" or user_age > 20) and role_id in (
             select id
             from role_info
             where role_name = '后端'
            )
         group by user_age
         order by num desc
     */
    @Test
    public void selectDemo4(){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_age","count(1) as num")
                .and(i -> i.like("email","lx").or().ge("user_age",20))
                .inSql("role_id","select id from role_info where role_name = '后端' ")
                .groupBy("user_age")
                .orderByDesc("num");
        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
        log.info("结果 >>>" + userInfos);
    }


    /**
     * 例子: 一个having的例子
     */
    @Test
    public void selectDemo5(){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_age","count(1) as num")
                .and(i -> i.like("email","lx").or().ge("user_age",20))
                .inSql("role_id","select id from role_info where role_name = '后端' ")
                .groupBy("user_age")
                .orderByDesc("num");
        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
        log.info("结果 >>>" + userInfos);
    }



    /**
     * 不使用select
     */
    @Test
    public void selectDemo6(){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(UserInfo.class,i-> !i.getColumn().equals("version"))
                .eq("id",1);
        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
        log.info("结果 >>>" + userInfos);
    }


    /**
     * 实体类
     */
    @Test
    public void selectDemo7(){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("lx");
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>(userInfo);
        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
        log.info("结果 >>>" + userInfos);
    }

    /**
     * 实体类
     */
    @Test
    public void selectDemo8(){
        Map<String,Object> map = new HashMap<>();
        map.put("user_name","lx");
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(map);
        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
        log.info("结果 >>>" + userInfos);
    }


    /**
     * 实体类
     */
    @Test
    public void selectDemo9(){

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",1);
        List<UserInfo> result = userInfoMapper.selectAll(queryWrapper);
        log.info("结果 >>>" + result);
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
