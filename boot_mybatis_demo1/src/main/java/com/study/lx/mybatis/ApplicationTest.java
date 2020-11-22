package com.study.lx.mybatis;

import com.study.lx.mybatis.mapper.UserInfoMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author luffyu
 * Created on 2020/11/22
 */
@Component
public class ApplicationTest implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();

        UserInfoMapper userInfoMapper = (UserInfoMapper)applicationContext.getBean("userInfoMapper");

        userInfoMapper.queryById(1);
    }
}
