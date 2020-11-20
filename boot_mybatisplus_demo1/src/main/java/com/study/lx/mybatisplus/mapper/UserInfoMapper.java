package com.study.lx.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.study.lx.mybatisplus.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rockyu
 * @since 2020-11-18
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select * from user_info ${ew.customSqlSegment}")
    List<UserInfo> selectAll(@Param(Constants.WRAPPER)Wrapper<UserInfo> wrapper);
}
