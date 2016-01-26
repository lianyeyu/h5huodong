/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : p3-jw

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-12-22 18:17:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_act_jiugongge
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_jiugongge`;
CREATE TABLE `wx_act_jiugongge` (
  `id` varchar(100) NOT NULL COMMENT '键主',
  `title` varchar(400) DEFAULT NULL COMMENT '活动名称',
  `description` text COMMENT '活动描述',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `banner` varchar(100) DEFAULT NULL COMMENT '背景图',
  `count` int(10) DEFAULT NULL COMMENT '抽奖次数',
  `hdurl` varchar(200) DEFAULT NULL COMMENT '入口地址',
  `foucs_user_can_join` varchar(1) DEFAULT '0' COMMENT '是否关注可参加',
  `binding_mobile_can_join` varchar(1) DEFAULT '0' COMMENT '是否绑定手机可参加',
  `num_per_day` int(11) DEFAULT '0' COMMENT '每日抽奖次数',
  `prize_status` varchar(1) DEFAULT '0' COMMENT '是否中奖可参与 0：中奖可继续参与 1:中奖不可参与',
  `jwid` varchar(64) DEFAULT NULL COMMENT '微信原始id',
  `project_code` varchar(32) DEFAULT NULL COMMENT '活动编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='九宫格活动表';

-- ----------------------------
-- Records of wx_act_jiugongge
-- ----------------------------
INSERT INTO `wx_act_jiugongge` VALUES ('402880ee5127c20a0151283904170012', '幸运九宫格', '九宫格活动', '2016-01-16 12:02:38', '2016-06-06 12:02:41', null, '25', 'http://wx.jeecg.com/jeewx/weixinLinksucaiController.do?link&id=8a792db35148806801514881188a0001&actId=402880ee5127c20a0151283904170012&jwid=gh_f268aa85d1c7', '1', '0', '0', '0', 'gh_f268aa85d1c7', null);


-- ----------------------------
-- Table structure for wx_act_jiugongge_awards
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_jiugongge_awards`;
CREATE TABLE `wx_act_jiugongge_awards` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `awards_name` varchar(200) DEFAULT NULL COMMENT '奖项名称',
  `jwid` varchar(64) DEFAULT NULL,
  `awards_value` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_awardsjwid` (`jwid`,`awards_value`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖项表';

-- ----------------------------
-- Records of wx_act_jiugongge_awards
-- ----------------------------
INSERT INTO `wx_act_jiugongge_awards` VALUES ('402880ee5127c20a0151281efbd00006', '一等奖', 'gh_f268aa85d1c7', '1');
INSERT INTO `wx_act_jiugongge_awards` VALUES ('402880ee5127c20a0151281f395f0007', '二等奖', 'gh_f268aa85d1c7', '2');
INSERT INTO `wx_act_jiugongge_awards` VALUES ('402880ee5127c20a0151281f69740008', '三等奖', 'gh_f268aa85d1c7', '3');
INSERT INTO `wx_act_jiugongge_awards` VALUES ('402880ee5127c20a0151281f8d290009', '四等奖', 'gh_f268aa85d1c7', '4');
INSERT INTO `wx_act_jiugongge_awards` VALUES ('402880ee5127c20a0151281fc42d000a', '五等奖', 'gh_f268aa85d1c7', '5');
INSERT INTO `wx_act_jiugongge_awards` VALUES ('402880ee5127c20a0151281fe96b000b', '六等奖', 'gh_f268aa85d1c7', '6');

-- ----------------------------
-- Table structure for wx_act_jiugongge_prizes
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_jiugongge_prizes`;
CREATE TABLE `wx_act_jiugongge_prizes` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `name` varchar(200) DEFAULT NULL COMMENT '奖品名称',
  `img` varchar(50) DEFAULT NULL COMMENT '奖品图片',
  `jwid` varchar(64) DEFAULT NULL COMMENT '微信原始id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖品表';

-- ----------------------------
-- Records of wx_act_jiugongge_prizes
-- ----------------------------
INSERT INTO `wx_act_jiugongge_prizes` VALUES ('402880ee5127c20a01512823001d000c', 'iphone6plus', 'sy2_03.jpg', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_jiugongge_prizes` VALUES ('402880ee5127c20a01512823417c000d', 'iPhone6s', 'sy2_05.jpg', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_jiugongge_prizes` VALUES ('402880ee5127c20a015128237268000e', '华为', 'sy2_09.jpg', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_jiugongge_prizes` VALUES ('402880ee5127c20a01512823a265000f', '小米', 'sy2_10.jpg', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_jiugongge_prizes` VALUES ('402880ee5127c20a01512824448a0010', '电影票', 'movie.jpg', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_jiugongge_prizes` VALUES ('402880ee5127c20a01512824a8600011', 'U盘', 'up.jpg', 'gh_f268aa85d1c7');

-- ----------------------------
-- Table structure for wx_act_jiugongge_record
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_jiugongge_record`;
CREATE TABLE `wx_act_jiugongge_record` (
  `id` varchar(32) NOT NULL COMMENT '记录id',
  `act_id` varchar(32) DEFAULT NULL,
  `openid` varchar(100) NOT NULL COMMENT 'openid',
  `nickname` varchar(200) NOT NULL COMMENT '昵称',
  `recieve_time` datetime NOT NULL COMMENT '中奖时间',
  `awards_id` varchar(36) DEFAULT NULL COMMENT '奖项',
  `realname` varchar(30) DEFAULT NULL COMMENT '收货人姓名',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `address` varchar(15) DEFAULT NULL COMMENT '地址',
  `seq` int(10) DEFAULT NULL COMMENT '抽奖序号',
  `jwid` varchar(64) NOT NULL COMMENT '对应微信平台原始id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_actid_seq` (`act_id`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抽奖记录表';

-- ----------------------------
-- Records of wx_act_jiugongge_record
-- ----------------------------

-- ----------------------------
-- Table structure for wx_act_jiugongge_registration
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_jiugongge_registration`;
CREATE TABLE `wx_act_jiugongge_registration` (
  `id` varchar(32) NOT NULL,
  `act_id` varchar(32) NOT NULL COMMENT '活动id',
  `openid` varchar(100) NOT NULL COMMENT '活动所属人openid',
  `nickname` varchar(200) NOT NULL COMMENT '活动所属人昵称',
  `awards_num` int(10) DEFAULT '0' COMMENT '抽奖次数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `awards_status` varchar(2) DEFAULT '0' COMMENT '抽奖状态0:未抽奖;1:已抽奖;',
  `jwid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='九宫格活动报名表';

-- ----------------------------
-- Records of wx_act_jiugongge_registration
-- ----------------------------

-- ----------------------------
-- Table structure for wx_act_jiugongge_relation
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_jiugongge_relation`;
CREATE TABLE `wx_act_jiugongge_relation` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `prize_id` varchar(36) DEFAULT NULL,
  `act_id` varchar(36) DEFAULT NULL,
  `jwid` varchar(64) DEFAULT NULL,
  `award_id` varchar(36) DEFAULT NULL,
  `amount` int(10) DEFAULT NULL COMMENT '数量',
  `remain_num` int(10) DEFAULT NULL COMMENT '剩余数量',
  `probability` double(5,2) DEFAULT '0.00' COMMENT '概率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动奖品明细表';

-- ----------------------------
-- Records of wx_act_jiugongge_relation
-- ----------------------------
INSERT INTO `wx_act_jiugongge_relation` VALUES ('402880ee5127c20a0151283904320013', '402880ee5127c20a01512823001d000c', '402880ee5127c20a0151283904170012', 'gh_f268aa85d1c7', '402880ee5127c20a0151281efbd00006', '1', '1', '0.07');
INSERT INTO `wx_act_jiugongge_relation` VALUES ('402880ee5127c20a0151283904350014', '402880ee5127c20a01512823417c000d', '402880ee5127c20a0151283904170012', 'gh_f268aa85d1c7', '402880ee5127c20a0151281f395f0007', '2', '2', '0.09');
INSERT INTO `wx_act_jiugongge_relation` VALUES ('402880ee5127c20a0151283904360015', '402880ee5127c20a015128237268000e', '402880ee5127c20a0151283904170012', 'gh_f268aa85d1c7', '402880ee5127c20a0151281f69740008', '3', '3', '0.10');
INSERT INTO `wx_act_jiugongge_relation` VALUES ('402880ee5127c20a0151283904370016', '402880ee5127c20a01512823a265000f', '402880ee5127c20a0151283904170012', 'gh_f268aa85d1c7', '402880ee5127c20a0151281f8d290009', '4', '4', '0.15');
INSERT INTO `wx_act_jiugongge_relation` VALUES ('402880ee5127c20a0151283904380017', '402880ee5127c20a01512824448a0010', '402880ee5127c20a0151283904170012', 'gh_f268aa85d1c7', '402880ee5127c20a0151281fc42d000a', '5', '5', '0.20');
INSERT INTO `wx_act_jiugongge_relation` VALUES ('402880ee5127c20a0151283904390018', '402880ee5127c20a01512824a8600011', '402880ee5127c20a0151283904170012', 'gh_f268aa85d1c7', '402880ee5127c20a0151281fe96b000b', '6', '6', '0.20');



-- ----------------------------
-- 系统文本
-- ----------------------------
INSERT INTO `jw_system_act_txt` VALUES ('402880ee5146cebf015146e54e74000b', 'index.tip.noBindPhone', '请在公众号回复手机号进行绑定', '没有绑定手机号提示语', '402880ee5127c20a0151283904170012', NULL,NULL, '2015-11-27 11:03:31', NULL, '2015-11-27 11:04:51');
INSERT INTO `jw_system_act_txt` VALUES ('402880ee5146cebf015146ea058b000c', 'index.tip.noFocus', '关注才能参与抽奖游戏哦~', '未关注提示语', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-11-27 11:08:40', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3bb41360001', 'index.title', '幸运九宫格', '首页标题', '402880ee5127c20a0151283904170012', NULL,NULL, '2015-12-15 11:42:16', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3bba59f0002', 'myprizes.title', '我的奖品', '我的奖品页标题', '402880ee5127c20a0151283904170012', NULL,NULL, '2015-12-15 11:42:42', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3bbfff80003', 'winners.title', '获奖名单', '获奖名单页标题	', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-12-15 11:43:05', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3bc82270004', 'index.tip.shareFriendCircle', '快来抽取属于你的奖品吧~', '分享到朋友圈的文字', '402880ee5127c20a0151283904170012', NULL,NULL, '2015-12-15 11:43:39', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a2bcf5480005', 'index.tip.shareFriendTitle', '幸运九宫格', '分享给好友的标题', '402880ee5127c20a0151283904170012', NULL,NULL, '2015-12-15 11:44:09', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3bcf7460005', 'index.tip.shareFriend', '快来抽取属于你的奖品吧~', '分享给好友的文字', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-12-15 11:44:09', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3bd55c50006', 'index.img.share', 'http://h5.jeewx.com/content/jiugongge/img/fx_img.jpg', '分享小图	', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-12-15 11:44:33', NULL, '2015-12-15 12:02:24');
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3bdb6590007', 'index.tip.ylj', '领奖成功，请到我的奖品中查看', '已领奖提示	', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-12-15 11:44:57', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3be4f8c0008', 'index.actdesc', '亲，请点击进入九宫格抽奖活动页面，祝您好运哦！<br/>活动时间:2015-11-12至2016-06-01<br/><strong>兑奖请联系我们，电话138********</strong>', '活动说明', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-12-15 11:45:37', NULL, '2015-12-22 14:55:13');
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3bf91c60009', 'prelink', 'http://www.guojusoft.com', '预设链接', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-12-15 11:46:59', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a3ba7e0151a3d2e888000a', 'index.img.erweima', 'rwm.jpg', '二维码', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-12-15 12:08:07', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a8fdd50151a9049ce20001', 'controller.exception.nocount', '您的抽奖次数已经用完，不能再次抽奖。', '抽奖次数用完提示语', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-12-16 12:20:30', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51a8fdd50151a906cdeb0002', 'controller.exception.nownocount', '您今日抽奖次数已经用完，请明日再来。', '今日抽奖次数用完提示语', '402880ee5127c20a0151283904170012',NULL, NULL, '2015-12-16 12:22:54', NULL, NULL);
INSERT INTO jw_system_act_txt VALUES ('402880ee51f238950151f23e63310003', 'statistics', 'var _hmt = _hmt || [];\n(function() {\n  var hm = document.createElement(\"script\");\n  hm.src = \"//hm.baidu.com/hm.js?8e37bf5be2b9827bc5af38b321f6bb38\";\n  var s = document.getElementsByTagName(\"script\")[0]; \n  s.parentNode.insertBefore(hm, s);\n})();', '统计脚本', '402880ee5127c20a0151283904170012', NULL,NULL, NULL, '2015-12-30 17:35:53', NULL, NULL);

INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('26', '九宫格活动管理', '', '0', '', '', '', '4', '1', '', '0');
INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('2601', '九宫格活动', '', '0', '九宫格活动', '/jiugongge/back/wxActJiugongge/list.do', '26', '1', '2', 'Y', '0');
INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('2602', '奖项配置', '', '0', '奖项配置', '/jiugongge/back/wxActJiugonggeAwards/list.do', '26', '2', '2', 'Y', '0');
INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('2603', '奖品配置', '', '0', '奖品配置', '/jiugongge/back/wxActJiugonggePrizes/list.do', '26', '3', '2', 'Y', '0');

