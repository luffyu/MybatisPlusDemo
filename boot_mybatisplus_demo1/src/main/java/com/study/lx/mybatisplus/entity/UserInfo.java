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
 * @since 2020-11-18
 */
@Data
@TableName("user_info")
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer userAge;

    /**
     * 邮箱
     */
    @TableField(condition = SqlCondition.LIKE)
    private String email;

    /**
     * 版本号
     */
    @Version
    private Integer version;

    /**
     * 管理id
     */
    private Integer roleId;

    /**
     * 创建时间
     */
    @TableField(update = "now()")
    private LocalDateTime createTime;

    /**
     * 逻辑删除状态 0表示没有删除 1表示已经删除
     */
    //@TableLogic
    private Integer delFlag;


}
