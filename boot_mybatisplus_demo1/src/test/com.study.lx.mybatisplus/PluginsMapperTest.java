package com.study.lx.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.lx.mybatisplus.entity.UserInfo;
import com.study.lx.mybatisplus.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 插件掩饰
 *
 * @author luffyu
 * Created on 2020/11/21
 */
@Slf4j
@SpringBootTest
public class PluginsMapperTest {

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
    public void testPage(){
        //current 是当前页，size是一页的大小
        Page<UserInfo> page = new Page<>(1,2);
        //是否统计行数
        page.setSearchCount(false);

        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(UserInfo::getAge,10);
        userInfoMapper.selectPage(page,lambdaQueryWrapper);
        log.info("总记录数{}，当前页{}，总页数{}，当前页大小{}",page.getTotal(),page.getCurrent()
                ,page.getPages(),page.getSize());
        log.info("当前页结果：{}",page.getRecords());

    }


    /**
     * 测试乐观是插件
     */
    @Test
    public void testVersion(){
        UserInfo userInfo = userInfoMapper.selectById(1);
        userInfoMapper.updateById(userInfo);
    }


    /**
     * 测试逻辑删除
     */
    @Test
    public void testLogicDelete(){
        userInfoMapper.selectById(1);
        userInfoMapper.deleteById(1);
        userInfoMapper.selectById(1);
    }



    /**
     * 测试性能
     */
    @Test
    public void testPerformance(){
        userInfoMapper.selectById(1);
    }
}
