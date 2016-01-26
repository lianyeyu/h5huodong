/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50037
Source Host           : localhost:3306
Source Database       : p3-base

Target Server Type    : MYSQL
Target Server Version : 50037
File Encoding         : 65001

Date: 2016-01-15 15:53:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jw_system_act
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_act`;
CREATE TABLE `jw_system_act` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `act_code` varchar(100) NOT NULL COMMENT '活动项目编码',
  `act_name` varchar(100) NOT NULL COMMENT '活动名称',
  `act_discribe` varchar(300) default NULL COMMENT '活动描述',
  `type` varchar(30) default NULL COMMENT '活动类型',
  `jwid` varchar(64) NOT NULL COMMENT '微信原始ID',
  `creat_name` varchar(50) default NULL COMMENT '创建人',
  `creat_time` datetime default NULL COMMENT '创建时间',
  `update_name` varchar(50) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_actcode` (`act_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动项目表';

-- ----------------------------
-- Records of jw_system_act
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_act_txt
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_act_txt`;
CREATE TABLE `jw_system_act_txt` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `code` varchar(100) NOT NULL COMMENT '文本编码',
  `content` varchar(2000) NOT NULL COMMENT '文本内容',
  `discribe` varchar(100) default NULL COMMENT '文本描述',
  `act_code` varchar(32) NOT NULL COMMENT '所属活动',
  `jwid` varchar(64) default NULL COMMENT '微信原始ID',
  `creat_name` varchar(50) default NULL COMMENT '创建人',
  `creat_time` datetime default NULL COMMENT '创建时间',
  `update_name` varchar(50) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_code_actcode` (`code`,`act_code`),
  KEY `idx_code_actcode` (`code`,`act_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统文本表';

-- ----------------------------
-- Records of jw_system_act_txt
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_auth
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_auth`;
CREATE TABLE `jw_system_auth` (
  `id` bigint(20) unsigned NOT NULL auto_increment COMMENT '序号',
  `auth_id` varchar(32)  NOT NULL default '' COMMENT '权限编码',
  `auth_name` varchar(100)  default NULL COMMENT '权限名称',
  `is_logs` char(2)  default NULL COMMENT '是否记录日志 0不记录 1记录',
  `auth_type` varchar(2)  default NULL COMMENT '权限类型 0:菜单;1:功能',
  `auth_desc` varchar(120)  default NULL COMMENT '权限说明',
  `auth_contr` varchar(256)  default NULL COMMENT '权限控制',
  `parent_auth_id` char(12)  default NULL COMMENT '上一级权限编码',
  `sort_no` int(11) default NULL COMMENT '排序',
  `biz_level` char(2)  default NULL COMMENT '层级',
  `leaf_ind` char(2)  default NULL COMMENT '是否叶子节点',
  `del_stat` char(2)  NOT NULL default '0' COMMENT '删除标志 0未删除 1已删除',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_authid` (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='运营系统权限表';

-- ----------------------------
-- Records of jw_system_auth
-- ----------------------------
INSERT INTO `jw_system_auth` VALUES ('1', '21', '系统管理', null, '0', null, null, null, '900', '1', 'Y', '0');
INSERT INTO `jw_system_auth` VALUES ('2', '2101', '用户管理', null, '0', null, '/system/back/jwSystemUser/list.do', '21', '1', '2', 'Y', '0');
INSERT INTO `jw_system_auth` VALUES ('3', '210101', '新增用户', null, '1', '新增用户', '/system/back/jwSystemUser/doAdd.do', '2101', null, null, null, '0');
INSERT INTO `jw_system_auth` VALUES ('4', '210102', '编辑用户', null, '1', '编辑用户', '/system/back/jwSystemUser/doEdit.do', '2101', null, null, null, '0');
INSERT INTO `jw_system_auth` VALUES ('5', '2102', '角色管理', null, '0', null, '/system/back/jwSystemRole/list.do', '21', '2', '2', 'Y', '0');
INSERT INTO `jw_system_auth` VALUES ('6', '210201', '新增角色', '', '1', '新增角色', '/system/back/jwSystemRole/doAdd.do', '2102', null, null, null, '0');
INSERT INTO `jw_system_auth` VALUES ('7', '210202', '编辑角色', '', '1', '编辑角色', '/system/back/jwSystemRole/doEdit.do', '2102', null, null, null, '0');
INSERT INTO `jw_system_auth` VALUES ('8', '210203', '角色授权', null, '1', '角色授权', '/system/back/jwSystemRole/editRoleAuth.do', '2102', null, null, null, '0');
INSERT INTO `jw_system_auth` VALUES ('9', '210204', '删除角色', '', '1', '删除角色', '/system/back/jwSystemRole/doDelete.do', '2102', null, null, null, '0');
INSERT INTO `jw_system_auth` VALUES ('10', '2103', '权限管理', null, '0', null, '/system/back/jwSystemAuth/list.do', '21', '3', '2', 'Y', '0');
INSERT INTO `jw_system_auth` VALUES ('11', '210301', '新增权限', null, '1', '新增权限', '/system/back/jwSystemAuth/doAdd.do', '2103', null, null, null, '0');
INSERT INTO `jw_system_auth` VALUES ('12', '210302', '编辑权限', null, '1', '编辑权限', '/system/back/jwSystemAuth/doEdit.do', '2103', null, null, null, '0');
INSERT INTO `jw_system_auth` VALUES ('31', '2104', '项目管理', '', '0', '', '/system/back/jwSystemProject/list.do', '21', '4', '2', 'Y', '0');
INSERT INTO `jw_system_auth` VALUES ('32', '2105', '公众号管理', '', '0', '', '/system/back/jwWebJwid/list.do', '21', '5', '2', 'Y', '0');

-- ----------------------------
-- Table structure for jw_system_auth_mutex
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_auth_mutex`;
CREATE TABLE `jw_system_auth_mutex` (
  `id` bigint(20) unsigned NOT NULL auto_increment COMMENT '序号',
  `auth_id` varchar(32) NOT NULL COMMENT '权限编码',
  `mutex_auth_id` varchar(32) NOT NULL COMMENT '互斥权限编码',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限互斥表';

-- ----------------------------
-- Records of jw_system_auth_mutex
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_logo_title
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_logo_title`;
CREATE TABLE `jw_system_logo_title` (
  `id` varchar(32) NOT NULL,
  `logo` varchar(100) default NULL COMMENT '系统的logo',
  `title1` varchar(100) default NULL COMMENT '系统名称',
  `title2` varchar(100) default NULL COMMENT '系统名称',
  `title3` varchar(100) default NULL COMMENT '系统名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统logo和title设置表';

-- ----------------------------
-- Records of jw_system_logo_title
-- ----------------------------
INSERT INTO `jw_system_logo_title` VALUES ('1', '捷微H5活动营销平台', '捷微H5活动营销平台', '', null);

-- ----------------------------
-- Table structure for jw_system_project
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_project`;
CREATE TABLE `jw_system_project` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `code` varchar(100) NOT NULL COMMENT '项目编码',
  `name` varchar(100) NOT NULL COMMENT '项目名称',
  `img` varchar(255) default NULL COMMENT '项目图片',
  `discribe` varchar(300) default NULL COMMENT '活动描述',
  `hdurl` varchar(200) default NULL COMMENT '入口地址',
  `application_type` varchar(10) default NULL COMMENT '应用类型',
  `creat_name` varchar(50) default NULL COMMENT '创建人',
  `creat_time` datetime default NULL COMMENT '创建时间',
  `update_name` varchar(50) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动项目管理表';

-- ----------------------------
-- Records of jw_system_project
-- ----------------------------
INSERT INTO `jw_system_project` VALUES ('402880ee51c40a030151c40a037f0000', 'jiugongge', '九宫格', 'jiugongge.jpg', '九宫格抽奖', 'http://www.jeewx.com/jeewx/weixinLinksucaiController.do?link&id=8a792db35148806801514881188a0001', '', null, '2015-12-21 18:16:09', null, null);
INSERT INTO `jw_system_project` VALUES ('402880ee51d7fd1f0151d80cd8610012', 'shaketicket', '摇一摇通用版', 'shaketicket.jpg', '摇一摇通用版', 'http://www.jeewx.com/jeewx/weixinLinksucaiController.do?link&id=ff80808151d26aca0151d80bfebc044f', '', null, '2015-12-25 15:31:39', null, null);
INSERT INTO `jw_system_project` VALUES ('402880ee52108c3a0152108c3a450000', 'commonftb', '斧头帮通用版', 'commonftb.jpg', '斧头帮通用版', 'http://www.jeewx.com/jeewx/weixinLinksucaiController.do?link&id=ff80808152107b460152108e9e630036', '', null, '2016-01-05 14:49:31', null, null);
INSERT INTO `jw_system_project` VALUES ('ff80808151cdf6f70151ce1902f20008', 'gzbargain', '砍价', 'gzbargain.jpg', '砍价，单商品', 'http://www.jeewx.com/jeewx/weixinLinksucaiController.do?link&id=ff808081520c192c01520c31b0af001b', '', null, '2015-12-23 01:08:44', null, null);

-- ----------------------------
-- Table structure for jw_system_role
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_role`;
CREATE TABLE `jw_system_role` (
  `id` bigint(20) unsigned NOT NULL auto_increment COMMENT '序号',
  `role_id` varchar(32)  NOT NULL default '' COMMENT '角色编码',
  `role_name` varchar(100)  default NULL COMMENT '角色名称',
  `crt_operator` varchar(64)  default NULL COMMENT '创建人',
  `crt_dt` datetime default NULL COMMENT '创建日期',
  `role_typ` char(4)  default NULL COMMENT '角色种类',
  `del_stat` char(2)  default NULL COMMENT '删除标志',
  `creator` varchar(64)  default NULL COMMENT '建立者',
  `editor` varchar(64)  default NULL COMMENT '编辑者',
  `create_dt` timestamp NULL default NULL COMMENT '建立日期',
  `edit_dt` timestamp NULL default NULL COMMENT '编辑日期',
  `last_edit_dt` timestamp NULL default NULL COMMENT '上次修改日期',
  `record_version` char(8)  default NULL COMMENT '版本号',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_roleid` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='运营角色表';

-- ----------------------------
-- Records of jw_system_role
-- ----------------------------
INSERT INTO `jw_system_role` VALUES ('1', '00', '系统管理员', 'admin', null, null, '0', 'admin', null, '2015-12-22 10:34:05', '2015-12-22 10:34:05', '2015-12-22 10:34:05', null);
INSERT INTO `jw_system_role` VALUES ('2', '01', '默认角色', 'admin', null, null, '0', 'admin', null, '2015-12-25 14:13:58', null, null, null);

-- ----------------------------
-- Table structure for jw_system_role_auth_rel
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_role_auth_rel`;
CREATE TABLE `jw_system_role_auth_rel` (
  `role_id` varchar(32)  NOT NULL default '' COMMENT '角色编码',
  `auth_id` varchar(32)  NOT NULL default '' COMMENT '权限编码',
  PRIMARY KEY  (`role_id`,`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='运营角色与权限表';

-- ----------------------------
-- Records of jw_system_role_auth_rel
-- ----------------------------
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '21');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2101');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210101');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210102');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2102');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210201');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210202');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210203');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210204');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2103');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210301');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210302');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2104');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2105');

-- ----------------------------
-- Table structure for jw_system_user
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_user`;
CREATE TABLE `jw_system_user` (
  `id` bigint(20) unsigned NOT NULL auto_increment COMMENT '序号',
  `user_id` varchar(32)  default '' COMMENT '用户编码',
  `user_name` varchar(32)  default NULL COMMENT '用户名称',
  `password` varchar(64)  default NULL COMMENT '密码',
  `user_erp_no` varchar(64)  default NULL COMMENT '用户ERP号',
  `user_typ` char(2)  default NULL COMMENT '用户种类',
  `ope_dep_id` char(12)  default NULL COMMENT '部门编码',
  `ope_phone_num` varchar(20)  default NULL COMMENT '手机电话',
  `email` varchar(64)  default NULL COMMENT '注册邮箱',
  `ope_email_authinfo` varchar(32)  default NULL COMMENT '邮箱认证信息',
  `user_icon` varchar(32)  default NULL COMMENT '用户头像',
  `ope_mobile_auth_ind` char(2)  default NULL COMMENT '手机是否认证',
  `ope_email_auth_ind` char(2)  default NULL COMMENT '邮箱是否认证',
  `id_num` char(20)  default NULL COMMENT '证件号码',
  `id_typ` char(2)  default NULL COMMENT '证件种类',
  `state` char(2)  default NULL COMMENT '状态',
  `user_stat` varchar(20)  default NULL COMMENT '用户状态 NORMAL:正常；INVALID：无效',
  `last_logn_dttm` datetime default NULL COMMENT '上次登录日期',
  `last_logn_ip` char(15)  default NULL COMMENT '上次登录IP',
  `ope_passwd_ind` char(2)  default NULL COMMENT '是否保持密码',
  `del_stat` char(2)  default NULL COMMENT '删除标志',
  `creator` varchar(64)  default NULL COMMENT '建立者',
  `editor` varchar(64)  default NULL COMMENT '编辑者',
  `create_dt` timestamp NULL default NULL COMMENT '建立日期',
  `edit_dt` timestamp NULL default NULL COMMENT '编辑日期',
  `last_edit_dt` timestamp NULL default NULL COMMENT '上次修改日期',
  `record_version` char(8)  default NULL COMMENT '版本号',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_userid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='运营用户表';

-- ----------------------------
-- Records of jw_system_user
-- ----------------------------
INSERT INTO `jw_system_user` VALUES ('1', 'admin', '系统管理员', 'e10adc3949ba59abbe56e057f20f883e', null, null, '', null, null, null, null, null, null, null, null, null, 'NORMAL', null, null, null, null, null, null, '2015-12-22 14:16:10', null, null, null);

-- ----------------------------
-- Table structure for jw_system_user_jwid
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_user_jwid`;
CREATE TABLE `jw_system_user_jwid` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '用户编码',
  `jwid` varchar(64) NOT NULL COMMENT '公众号',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq` (`user_id`,`jwid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信公众号字典表';

-- ----------------------------
-- Records of jw_system_user_jwid
-- ----------------------------
INSERT INTO `jw_system_user_jwid` VALUES ('4028138151e681e20151e681e3b50005', 'admin', 'gh_f268aa85d1c7');

-- ----------------------------
-- Table structure for jw_system_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_user_role_rel`;
CREATE TABLE `jw_system_user_role_rel` (
  `user_id` varchar(32)  NOT NULL default '' COMMENT '用户id',
  `role_id` varchar(32)  NOT NULL default '' COMMENT '角色id',
  PRIMARY KEY  (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='运营用户与角色';

-- ----------------------------
-- Records of jw_system_user_role_rel
-- ----------------------------
INSERT INTO `jw_system_user_role_rel` VALUES ('admin', '00');

-- ----------------------------
-- Table structure for jw_web_jwid
-- ----------------------------
DROP TABLE IF EXISTS `jw_web_jwid`;
CREATE TABLE `jw_web_jwid` (
  `id` varchar(32) NOT NULL,
  `jwid` varchar(64) NOT NULL COMMENT '公众号',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `application_type` varchar(10) default NULL COMMENT '应用类型',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_jwid` (`jwid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信公众号字典表';

-- ----------------------------
-- Records of jw_web_jwid
-- ----------------------------
INSERT INTO `jw_web_jwid` VALUES ('ff80808151f1628c0151f16470cb0006', 'gh_f268aa85d1c7', 'H5活动汇', null);
