/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : p3-jw

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-12-25 15:43:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_act_shaketicket_award
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_shaketicket_award`;
CREATE TABLE `wx_act_shaketicket_award` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `is_card` varchar(2) DEFAULT NULL COMMENT '是否卡券0 是1 否',
  `awards_name` varchar(64) DEFAULT NULL COMMENT '奖品名称',
  `owner` varchar(64) DEFAULT NULL COMMENT '发奖公司',
  `card_id` varchar(64) DEFAULT NULL COMMENT '卡券id',
  `img` varchar(100) DEFAULT NULL COMMENT '奖品图片',
  `jwid` varchar(64) DEFAULT NULL COMMENT '微信公众号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖项表';

-- ----------------------------
-- Records of wx_act_shaketicket_award
-- ----------------------------
INSERT INTO `wx_act_shaketicket_award` VALUES ('402880ee5127c20a0151281efbd00016', '1', '北京国炬现金抵用券', '北京国炬', 'pDltNwW02RH4RuUu5iQ_uHOxSLbY', null, 'gh_f268aa85d1c7');

-- ----------------------------
-- Table structure for wx_act_shaketicket_config
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_shaketicket_config`;
CREATE TABLE `wx_act_shaketicket_config` (
  `id` varchar(32) NOT NULL COMMENT '活动ID',
  `act_id` varchar(32) NOT NULL COMMENT '所属活动',
  `award_id` varchar(32) NOT NULL,
  `probability` double(8,3) DEFAULT NULL COMMENT '中奖概率',
  `amount` int(10) DEFAULT NULL COMMENT '总数量',
  `remain_num` int(10) DEFAULT NULL COMMENT '剩余数量',
  `jwid` varchar(64) DEFAULT NULL COMMENT '微信公众号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动奖项配置表';

-- ----------------------------
-- Records of wx_act_shaketicket_config
-- ----------------------------
INSERT INTO `wx_act_shaketicket_config` VALUES ('402880ee5127c21a0151281efbd12310', '402880ee51cd37910151cd4702720001', '402880ee5127c20a0151281efbd00016', '1.000', '100', '100', 'gh_f268aa85d1c7');

-- ----------------------------
-- Table structure for wx_act_shaketicket_home
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_shaketicket_home`;
CREATE TABLE `wx_act_shaketicket_home` (
  `id` varchar(100) NOT NULL COMMENT '键主',
  `act_name` varchar(400) DEFAULT NULL COMMENT '活动名称',
  `active_flag` varchar(2) DEFAULT NULL COMMENT '启用状态（0：停用；1：激活）',
  `template` varchar(100) DEFAULT NULL COMMENT '模版简称',
  `count` int(10) DEFAULT NULL COMMENT '抽奖次数',
  `num_per_day` int(11) DEFAULT '0' COMMENT '每日抽奖次数',
  `hdurl` varchar(200) DEFAULT NULL COMMENT '入口地址',
  `foucs_user_can_join` varchar(1) DEFAULT '0' COMMENT '是否关注可参加 0否1是',
  `binding_mobile_can_join` varchar(1) DEFAULT '0' COMMENT '是否绑定手机可参加 0否1是',
  `win_can_join` varchar(1) DEFAULT '0' COMMENT '是否中奖可参与 0：中奖可继续参与 1:中奖不可参与',
  `jwid` varchar(64) NOT NULL COMMENT '微信原始id',
  `project_code` varchar(32) NOT NULL COMMENT '所属项目编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='摇一摇活动表';

-- ----------------------------
-- Records of wx_act_shaketicket_home
-- ----------------------------
INSERT INTO `wx_act_shaketicket_home` VALUES ('402880ee51cd37910151cd4702720001', '摇一摇', '1', 'default', '1', '0', 'http://wx.jeecg.com/jeewx/weixinLinksucaiController.do?link&id=ff80808151d26aca0151d80bfebc044f&actId=402880ee51cd37910151cd4702720001&jwid=gh_f268aa85d1c7', '0', '0', '1', 'gh_f268aa85d1c7', 'shaketicket');

-- ----------------------------
-- Table structure for wx_act_shaketicket_record
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_shaketicket_record`;
CREATE TABLE `wx_act_shaketicket_record` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `act_id` varchar(32) NOT NULL COMMENT '活动ID',
  `award_id` varchar(64) DEFAULT NULL COMMENT '奖品ID',
  `openid` varchar(100) NOT NULL COMMENT '微信openid',
  `awards_seq` int(11) NOT NULL COMMENT '奖品序号',
  `draw_status` varchar(2) NOT NULL COMMENT '抽奖状态 0 未中奖 1已中奖',
  `receive_status` varchar(2) DEFAULT NULL COMMENT '领取状态 0 未领取 1已领取',
  `draw_time` datetime DEFAULT NULL COMMENT '抽奖时间',
  `receive_time` datetime DEFAULT NULL,
  `jwid` varchar(64) DEFAULT NULL COMMENT '微信公众号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_actid_awardsseq` (`act_id`,`awards_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抽奖记录表';


INSERT INTO `jw_system_act_txt` VALUES ('402880ee51d3506a0151d38209a8000c', 'controller.exception.nocount', '您的抽奖次数已经用完', '总次数用完', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-24 18:21:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51d3506a0151d3829cba000d', 'controller.exception.nownocount', '您今日抽奖次数已经用完', '今日抽奖次数用万', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-24 18:22:11', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51d3506a0151d3832f6c000e', 'index.title', '摇一摇', '标题', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-24 18:22:48', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51d3506a0151d3852f76000f', 'detail', '摇一摇中卡券活动，活动时间:2015年1月1日', '活动说明', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-24 18:24:59', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51d3506a0151d3875d550010', 'index.tip.nobindingphone', '请绑定手机号', '没绑定手机号', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-24 18:27:22', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51d3506a0151d387e7320011', 'index.tip.nofocus', '请先关注', '请先关注', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-24 18:27:57', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee51d3506a0151d38c437c0012', 'controller.exception.winnotcanjoin', '中奖不可继续参与', '中奖不可继续参与', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-24 18:32:43', NULL, NULL);
INSERT INTO `jw_system_act_txt`  VALUES ('402880ee51d7fd1f0151d82a2c900025', 'index.tip.sharefriendcircle', '摇一摇中大奖啦', '分享到朋友圈的文案', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-25 16:03:41', NULL, NULL);
INSERT INTO `jw_system_act_txt`  VALUES ('402880ee51d7fd1f0151d82ab77b0026', 'index.img.share', 'http://h5.jeewx.com/content/shaketicket/default/img/picurl-shake.jpg', '分享小图', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-25 16:04:16', NULL, '2015-12-25 16:06:42');
INSERT INTO `jw_system_act_txt`  VALUES ('402880ee51d7fd1f0151d82ba0d20027', 'index.tip.sharefriend', '摇一摇中大奖啦', '分享给好友的文案', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-25 16:05:16', NULL, NULL);
INSERT INTO `jw_system_act_txt`  VALUES ('402880ee51d7fd1f0055d83ba1d20027', 'index.tip.sharefriendtitle', '快来摇一摇', '分享给好友的标题', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-25 16:05:16', NULL, NULL);
INSERT INTO  jw_system_act_txt VALUES ('402880ee51555f50151f23e63310003', 'statistics', 'var _hmt = _hmt || [];\n(function() {\n  var hm = document.createElement(\"script\");\n  hm.src = \"//hm.baidu.com/hm.js?8e37bf5be2b9827bc5af38b321f6bb38\";\n  var s = document.getElementsByTagName(\"script\")[0]; \n  s.parentNode.insertBefore(hm, s);\n})();', '统计脚本', '402880ee51cd37910151cd4702720001', NULL, NULL, '2015-12-30 17:35:53', NULL, NULL);


INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('27', '摇一摇活动管理', '', '0', '', '', '', '5', '1', '', '0');
INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('2701', '活动配置', '', '0', '摇一摇活动', '/shaketicket/back/wxActShaketicketHome/list.do', '27', '1', '2', 'Y', '0');
INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('2702', '奖品配置', '', '0', '奖品配置', '/shaketicket/back/wxActShaketicketAward/list.do', '27', '2', '2', 'Y', '0');
