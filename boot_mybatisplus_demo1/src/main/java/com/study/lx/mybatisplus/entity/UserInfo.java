package com.study.lx.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author rockyu
 * @since 2020-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "user_info",keepGlobalPrefix = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     * WHERE email=?
     * WHERE email LIKE CONCAT('%',?,'%')
     */
    @TableField(condition = SqlCondition.LIKE)
    private String email;

    /**
     * 邮箱
     */
    //@TableField(value = "role_id",condition = SqlCondition.LIKE)
    private Integer roleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(update = "now()")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除状态 0表示没有删除 1表示已经删除
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 版本号
     */
    @Version
    private Integer version;


    @TableField(exist = false)
    private LocalDateTime sysTime;

}
