package com.study.lx.mybatis.mapper;


import com.study.lx.mybatis.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rockyu
 * @since 2020-11-21
 */
public interface UserInfoMapper {


    UserInfo queryById(@Param("userId")Integer userId);
}
