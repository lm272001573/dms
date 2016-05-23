/**用户角色关联表*/
DROP TABLE IF EXISTS `uc_user_role`;
CREATE TABLE `uc_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户号',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/**系统菜单表*/
DROP TABLE IF EXISTS `uc_menu`;
CREATE TABLE `uc_menu` (
  `id` int(11) NOT NULL COMMENT '菜单ID',
  `text` varchar(128) DEFAULT NULL COMMENT '菜单标题',
  `action` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `pid` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `res_code` varchar(32) DEFAULT NULL COMMENT '权限编码',
  `icon` varchar(32) DEFAULT NULL COMMENT '图片',
  `sort_code` int(3) DEFAULT NULL COMMENT '排序码',
  KEY `menu_idx2` (`res_code`),
  KEY `menu_idx` (`id`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

/**用户密码表*/
DROP TABLE IF EXISTS `uc_password`;
CREATE TABLE `uc_password` (
  `userid` varchar(64) NOT NULL COMMENT '用户ID',
  `password` varchar(256) DEFAULT NULL COMMENT '密码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户密码表';

/**权限表*/
DROP TABLE IF EXISTS `uc_permission`;
CREATE TABLE `uc_permission` (
  `res_code` varchar(32) NOT NULL COMMENT '权限编码',
  `res_name` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `ress_content` varchar(255) DEFAULT NULL COMMENT '权限内容',
  `status` varchar(1) DEFAULT NULL COMMENT '使用状态',
  `res_type` varchar(32) DEFAULT NULL COMMENT '权限级别',
  `res_sequence` int(11) DEFAULT NULL COMMENT '权限序号',
  `res_parent` varchar(32) DEFAULT NULL COMMENT '父权限编码',
  KEY `menu_idx` (`res_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

/**角色表*/
DROP TABLE IF EXISTS `uc_role`;
CREATE TABLE `uc_role` (
  `id` int(11) NOT NULL COMMENT '角色ID',
  `name` varchar(128) NOT NULL COMMENT '角色名称',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '角色状态 0:删除 1，正常'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/**角色资源表*/
DROP TABLE IF EXISTS `uc_role_permission`;
CREATE TABLE `uc_role_permission` (
  `role_code` varchar(32) NOT NULL COMMENT '角色编码',
  `res_code` varchar(32) DEFAULT NULL COMMENT '权限编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';

/**管理台参数表*/
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter` (
  `prm_code` varchar(30) NOT NULL COMMENT '参数代码',
  `prm_value` varchar(30) NOT NULL COMMENT '参数值',
  `prm_lang` varchar(10) NOT NULL COMMENT '参数语种  ZH_CN:中文 EN_US:英文',
  `prm_name` varchar(60) DEFAULT NULL COMMENT '参数名称',
  `prm_showmsg` varchar(1000) DEFAULT NULL COMMENT '参数显示信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理台参数表';

