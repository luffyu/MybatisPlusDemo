package com.study.lx.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.lx.mybatisplus.entity.UserInfo;
import com.study.lx.mybatisplus.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author rockyu
 * @date 2020/11/16 18:29
 */
@Slf4j
@SpringBootTest
public class SelectMapperTest {

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
        queryWrapper.like("email","@lx.com")
                .between("age",20,22)
                .orderByDesc("age");
        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);
        log.info("结果 >>>"+ userInfos);
    }
    @Test
    public void selectDemo3(){
        //LambdaQueryWrapper 的写法
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(UserInfo::getEmail,"lx")
                .between(UserInfo::getAge,20,22)
                .orderByDesc(UserInfo::getAge);
        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);
        log.info("结果 >>>" + userInfos);
    }


    /**
     * 例子: 查询一条 今天创建 并且 年龄大于25 or 小于20的用户信息
     select *
     from user_info
     where data_format(creat_time,'%Y-%m-%d') = '2020-11-21' and (age >= 25 or age <= 20)
     limit 1;
     */
    @Test
    public void selectDemo4(){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}","2020-11-21")
                .and(m -> m.ge("age",25).or().le("age",20))
                .last(" limit 1");
        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);
        log.info("结果 >>>" + userInfos);
    }



    /**
     * 例子: 查询 邮箱包含"lx" 或者 年龄大于20岁  并且 角色名称为 后端 的 各年龄人数，并按照人数倒序排序
         select distinct age,count(1) as num
         from user_info
         where (email like "%lx%" or user_age > 20) and role_id in (
             select id
             from role_info
             where role_name like '%java%'
            )
         group by user_age
         order by num desc
     */
    @Test
    public void selectDemo5(){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct age","count(1) as num")
                .and(i -> i.like("email","lx").or().ge("age",20))
                .inSql("role_id","select role_id from role_info where role_name like '%java%' ")
                .groupBy("age")
                .orderByDesc("num");
        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
        log.info("结果 >>>" + userInfos);
    }

//
//    /**
//     * 例子: 一个having的例子
//     */
//    @Test
//    public void selectDemo5(){
//        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("user_age","count(1) as num")
//                .and(i -> i.like("email","lx").or().ge("user_age",20))
//                .inSql("role_id","select id from role_info where role_name = '后端' ")
//                .groupBy("user_age")
//                .orderByDesc("num");
//        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
//        log.info("结果 >>>" + userInfos);
//    }
//
//
//
//    /**
//     * 不使用select
//     */
//    @Test
//    public void selectDemo6(){
//        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select(UserInfo.class,i-> !i.getColumn().equals("version"))
//                .eq("id",1);
//        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
//        log.info("结果 >>>" + userInfos);
//    }
//
//
//    /**
//     * 实体类
//     */
//    @Test
//    public void selectDemo7(){
//        UserInfo userInfo = new UserInfo();
//        userInfo.setEmail("lx");
//        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>(userInfo);
//        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
//        log.info("结果 >>>" + userInfos);
//    }
//
//    /**
//     * 实体类
//     */
//    @Test
//    public void selectDemo8(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("user_name","lx");
//        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.allEq(map);
//        List<Map<String, Object>> userInfos = userInfoMapper.selectMaps(queryWrapper);
//        log.info("结果 >>>" + userInfos);
//    }
//
//
//    /**
//     * 实体类
//     */
//    @Test
//    public void selectDemo9(){
//
//        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",1);
//        List<UserInfo> result = userInfoMapper.selectAll(queryWrapper);
//        log.info("结果 >>>" + result);
//    }


    /**
     * 实体类
     */
    @Test
    public void selectConditionDemo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("lx");
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>(userInfo);
        List<UserInfo> result = userInfoMapper.selectList(queryWrapper);
        log.info("结果 >>>" + result);
    }



}
