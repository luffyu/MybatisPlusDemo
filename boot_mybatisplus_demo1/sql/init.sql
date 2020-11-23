
CREATE TABLE `user_info` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `age` smallint(3) NOT NULL COMMENT '年龄',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `role_id` varchar(50) NOT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '逻辑删除状态 0表示没有删除 1表示已经删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `role_info` (
  `id` bigint(11)  NOT NULL  COMMENT 'id',
  `role_id` int(11) unsigned NOT NULL COMMENT '角色id',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '逻辑删除状态 0表示没有删除 1表示已经删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;