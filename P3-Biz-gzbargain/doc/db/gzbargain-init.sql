/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50037
Source Host           : localhost:3306
Source Database       : p3-jw

Target Server Type    : MYSQL
Target Server Version : 50037
File Encoding         : 65001

Date: 2015-10-10 19:01:59
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `wx_act_bargain`
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_bargain`;
CREATE TABLE `wx_act_bargain` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `act_name` varchar(100) NOT NULL COMMENT '砍价活动名称',
  `act_detail` varchar(500) default NULL COMMENT '活动描述',
  `act_rule` varchar(1000) default NULL COMMENT '活动规则',
  `begain_time` datetime default NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT ' 活动结束时间',
  `product_num` int(11) NOT NULL COMMENT '产品数量',
  `product_unit` varchar(10) NOT NULL COMMENT '产品单位',
  `product_name` varchar(255) NOT NULL COMMENT '产品名称',
  `product_price` decimal(11,2) NOT NULL COMMENT '产品价格',
  `product_img` varchar(255) DEFAULT NULL COMMENT '产品图片',
  `foucs_user_can_join` varchar(1) default NULL COMMENT '是否关注用户参与',
  `show_rate` int(11) NOT NULL COMMENT '展示比例',
  `create_time` datetime default NULL COMMENT '创建时间',
  `product_remain_num` int(11) NOT NULL COMMENT '产品剩余数量',
  `cut_min_price` decimal(11,2) DEFAULT NULL COMMENT '砍价最低金额',
  `template` varchar(32) DEFAULT NULL COMMENT '模版简称',
  `hdurl` varchar(255) DEFAULT NULL COMMENT '入口地址',
  `jwid` varchar(64) NOT NULL COMMENT '对应微信平台原始id',
  `project_code` varchar(32) DEFAULT NULL COMMENT '活动编码',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砍价活动表';

-- ----------------------------
-- Table structure for `wx_act_bargain_awards`
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_bargain_awards`;
CREATE TABLE `wx_act_bargain_awards` (
  `id` varchar(32) NOT NULL,
  `act_id` varchar(32) NOT NULL COMMENT '活动id',
  `awards_seq` int(11) NOT NULL COMMENT '奖品序号',
  `openid` varchar(100) NOT NULL COMMENT '兑奖人openid',
  `nickname` varchar(200) default NULL COMMENT '兑奖人昵称',
  `real_name` varchar(100) default NULL COMMENT '真实姓名',
  `mobile` varchar(50) default NULL COMMENT '手机号',
  `address` varchar(200) default NULL COMMENT '详细地址',
  `awards_code` varchar(64) NOT NULL COMMENT '兑奖码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `jwid` varchar(64) NOT NULL COMMENT '对应微信平台原始id',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_actid_awardsseq` USING BTREE (`act_id`,`awards_seq`),
  UNIQUE KEY `uniq_actid_openid` (`act_id`,`openid`),
  KEY `idx_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='领取奖品记录表';

-- ----------------------------
-- Records of wx_act_bargain_awards
-- ----------------------------

-- ----------------------------
-- Table structure for `wx_act_bargain_record`
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_bargain_record`;
CREATE TABLE `wx_act_bargain_record` (
  `id` varchar(32) NOT NULL COMMENT '记录id',
  `registration_id` varchar(32) NOT NULL COMMENT '报名id',
  `openid` varchar(100) NOT NULL COMMENT '砍价人openid',
  `nickname` varchar(200) NOT NULL COMMENT '砍价人昵称',
  `cut_price` decimal(11,2) NOT NULL COMMENT '砍掉价格',
  `curr_price` decimal(11,2) NOT NULL COMMENT '砍后价格',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `jwid` varchar(64) NOT NULL COMMENT '对应微信平台原始id',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_registrationid_openid` USING BTREE (`registration_id`,`openid`),
  KEY `idx_registrationid` (`registration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砍价帮砍记录表';

-- ----------------------------
-- Records of wx_act_bargain_record
-- ----------------------------

-- ----------------------------
-- Table structure for `wx_act_bargain_registration`
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_bargain_registration`;
CREATE TABLE `wx_act_bargain_registration` (
  `id` varchar(32) NOT NULL,
  `act_id` varchar(32) NOT NULL COMMENT '活动id',
  `openid` varchar(100) NOT NULL COMMENT '活动参与人openid',
  `nickname` varchar(200) NOT NULL COMMENT '活动参与人昵称',
  `head` varchar(200) DEFAULT NULL COMMENT '活动参与人头像',
  `product_name` varchar(255) NOT NULL COMMENT '产品名称',
  `product_price` decimal(11,2) NOT NULL COMMENT '产品价格',
  `product_new_price` decimal(11,2) NOT NULL COMMENT '砍后价格',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime default NULL COMMENT '更新时间',
  `awards_status` varchar(2) default '0' COMMENT '领奖状态0:未领奖;1:已领奖',
  `jwid` varchar(64) NOT NULL COMMENT '对应微信平台原始id',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_actid_openid` USING BTREE (`act_id`,`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砍价报名表';

INSERT INTO `wx_act_bargain` (`id`, `act_name`, `act_detail`, `act_rule`, `begain_time`, `end_time`, `product_num`, `product_unit`, `product_name`, `product_price`, `product_img`, `foucs_user_can_join`, `show_rate`, `create_time`, `product_remain_num`, `cut_min_price`, `template`, `hdurl`, `jwid`, `project_code`) VALUES ('ff808081520ad05601520c4c9acc0011', '新年砍价活动', '1', '10,20,0', '2016-01-01 19:00:50', '2016-02-28 19:00:53', '20', '部', 'iphone6s', '2999.00', '1', '0', '1', NULL, '20', '1999.00', 'co', 'http://wx.jeecg.com/jeewx/weixinLinksucaiController.do?link&id=ff808081520c192c01520c31b0af001b&actId=ff808081520ad05601520c4c9acc0011&jwid=gh_f268aa85d1c7', 'gh_f268aa85d1c7', 'gzbargain');

INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b090012', 'index.title', '全民砍价', '首页标题', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2015-12-25 10:15:12');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b0b0013', 'index.img.banner', 'banner1.jpg?v=1.0.1', '首页banner', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2016-01-01 19:45:36');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b0d0014', 'index.actDetail', '<dl class=\"hdxq_conter_nr\">\n	          <dd class=\"hdxq_wz\"> \n	              <span><b>如此高端的活动只给铁杆粉</b>：参与本活动前需关注“H5活动汇”公众号。</span> \n	              <span><b>我们拼的是手快和人气</b>最先砍到底价的前20名用户可以底价买到iPhone6s一部。</span> \n	              <span>来，po几张iPhone6s的美图</span>\n	            <div class=\"cp_img1_form\"><img src=\"http://img30.360buyimg.com/jgsq-productsoa/jfs/t1990/14/391467317/381809/6e9933ae/5603d37eN4701ca83.jpg\" /></div>\n	            	          </dd>\n	        </dl>\n	        <dl class=\"hdjs_conter_nr\">\n	          <dd class=\"hdjs_conter_nr_wz_list\"> \n	          	<span>活动时间</span>\n	          	<span> 2016年1月1日 00:00 — 2月 28日 00:00</span> \n	          </dd>\n	          <dd class=\"hdjs_conter_nr_wz_list\">\n	         		<span>活动玩法</span>\n	          		 <span> 商品的初始价格为5788元。每个用户可随机帮别人砍掉10-20元的价格。</span> \n	          </dd>\n	          <dl class=\"hdjs_conter_nr_wz_list\">\n	          	<span>奖励</span>\n	            <span> 活动期间最先砍到底价的前20名用户可以底价买到iPhone6s一部；</span>\n	          </dl>\n	          <dd class=\"hdjs_conter_nr_wz_list\">\n				  <span>特别提醒</span>\n		          <span>1. 必须通过关注“H5活动汇”才能参加该活动；</span> \n		          <span>2. 活动期间每个微信ID仅能为另一个微信ID砍价一次；</span> \n		          <span>3. 作弊刷票者将被取消活动资格。</span>\n		          <span><br>本活动为捷微测试活动，欢迎体验。最终解释权归捷微团队。</span>\n	          </dd>\n	        </dl>', '活动规则、详情，介绍等', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2016-01-10 14:41:48');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b110015', 'index.recevieTip', '恭喜您获得华为iPhone6s手机一部！请填写真实的姓名、手机号和收货详细地址，工作人员会尽快为您发货。', '中奖提示语，显示于页面上部和兑奖信息上方。', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2015-12-25 18:34:23');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b120016', 'fxindex.title', '砍啊', '帮砍页面标题', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b130017', 'index.recevieSuccessTip', '您已成功领取奖品！', '填写兑奖信息成功的提示语', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b130018', 'index.tip.shareFriendCircle', '2016年，兄弟们帮我砍价吧。', '【分享】分享到朋友圈的内容', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2016-01-07 21:20:03');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b140019', 'index.img.share', 'http://www.h5huodong.com/content/gzbargain/co/img/banner1.jpg', '【分享】分享图标', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2016-01-04 18:50:56');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b17001a', 'index.tip.shareFriendTitle', '全民砍价', '【分享】发送给好友的标题', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2015-12-30 11:58:11');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b18001b', 'index.tip.shareFriend', '兄弟姐妹们帮我砍价吧。', '【分享】发送给好友的内容', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2015-12-30 11:57:32');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b19001c', 'fxindex.recevieTip', '已成功领取奖品。点击“我要参加”，等你来领奖。', '分享页好友已领奖内容（显示于页面上部）', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2015-12-25 18:33:41');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b19001d', 'fxindex.tip.bargained', '您已经帮好友砍过价了，不能重复砍价！', '已砍过价提示语', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b1a001e', 'index.notCutMin', '您尚未砍到底价，请继续努力！', '未砍至底价领奖提示', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b1b001f', 'index.noRecevied', '奖品已抢光，请关注后续活动。', '未领取到奖品', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b1c0020', 'fxindex.tip.received', '用户已领奖，请勿砍价。', '帮砍时已领奖', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b1c0021', 'fxindex.tip.cutMin', '已砍至底价，请勿再砍。', '帮砍时已砍至底价，无需再砍。', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b1d0022', 'index.style.bgcolor', '#8F0802', '页面背景颜色', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2016-01-01 19:48:02');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b1e0023', 'statistics', 'var _hmt = _hmt || [];\n(function() {\n  var hm = document.createElement(\"script\");\n  hm.src = \"//hm.baidu.com/hm.js?8e37bf5be2b9827bc5af38b321f6bb38\";\n  var s = document.getElementsByTagName(\"script\")[0]; \n  s.parentNode.insertBefore(hm, s);\n})();', '统计代码', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b1f0024', 'index.tip.qrcode', '关注微信公众号后才能参与活动<br>长按识别二维码关注', '关注二维码提示语', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2015-12-27 15:54:00');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff808081520ad05601520c4c9b200025', 'index.img.qrcode', 'qrcode-h5.png', '二维码图片', 'ff808081520ad05601520c4c9acc0011', NULL, NULL, '2016-01-04 19:01:33', NULL, '2016-01-10 15:17:04');
