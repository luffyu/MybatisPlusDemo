-- auto-generated definition
use study_demo1;
create table user_info
(
    id          int(11) unsigned auto_increment comment '自增id'
        primary key,
    user_name   varchar(50)             not null comment '用户名称',
    user_age    smallint(3)             not null comment '年龄',
    email    varchar(50)             not null comment '邮箱',
    version     int          default 0  not null comment '版本号',
    role_id  int default '' null comment '管理id',
    create_time datetime                null comment '创建时间',
    del_flag    int          default 0  null comment '逻辑删除状态 0表示没有删除 1表示已经删除'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table role_info
(
    id          int(11) unsigned auto_increment comment '自增id'
        primary key,
    role_name   varchar(50)             not null comment '角色名称',
    roke_key    varchar(50)             not null comment '角色key',
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



