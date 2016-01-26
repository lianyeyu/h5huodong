/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : p3-jw

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-01-08 13:45:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_act_commonftb
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_commonftb`;
CREATE TABLE `wx_act_commonftb` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) NOT NULL COMMENT '子活动名称',
  `binner` varchar(100) DEFAULT NULL COMMENT '子活动标题图片',
  `confirm` varchar(30) DEFAULT NULL COMMENT '子活动确认选择提示语',
  `foucs_user_can_join` varchar(1) DEFAULT '0' COMMENT '是否关注用户参与 0否1是',
  `binding_mobile_can_join` varchar(1) DEFAULT '0' COMMENT '是否绑定手机可领奖 0否1是',
  `act_rule` varchar(1000) NOT NULL COMMENT '子活动规则',
  `begain_time` datetime NOT NULL COMMENT '子活动开始时间',
  `end_time` datetime NOT NULL COMMENT '子 活动结束时间',
  `product_num` int(11) NOT NULL COMMENT '产品数量',
  `product_unit` varchar(10) DEFAULT NULL COMMENT '产品单位',
  `product_name` varchar(255) NOT NULL COMMENT '产品名称',
  `product_price` decimal(11,2) NOT NULL COMMENT '产品价格',
  `product_img` varchar(255) DEFAULT NULL COMMENT '产品图片',
  `product_remain_num` int(11) NOT NULL COMMENT '产品剩余数量',
  `awards_end_time` datetime DEFAULT NULL COMMENT '兑奖截止日期',
  `coupon_start_time` datetime DEFAULT NULL COMMENT '券开始时间',
  `coupon_end_time` datetime DEFAULT NULL COMMENT '券结束时间',
  `cut_min_price` decimal(11,2) DEFAULT NULL COMMENT '砍价最低金额',
  `if_cut_min` varchar(1) DEFAULT '0' COMMENT '是否砍到最底价',
  `coupon_level` varchar(64) DEFAULT NULL COMMENT '卡券档位',
  `round_type` varchar(2) DEFAULT '0' COMMENT '取整方式（0向下取整1向上取整）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `jwid` varchar(64) NOT NULL COMMENT '微信平台原始id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砍价子活动表';

-- ----------------------------
-- Records of wx_act_commonftb
-- ----------------------------
INSERT INTO `wx_act_commonftb` VALUES ('402880ee00151283904171', '斧头帮·香主', 'cp_title1.png', '初出茅庐闯天下，这个香主我要定了！', '1', '1', '4,8,0', '2016-01-16 11:36:31', '2016-06-06 23:59:59', '100', '台', 'iPhone6s<span class=\'ftb_cp_yj\'>（16G）</span>', '5299.00', 'cp_img3g_96.png', '92', '2016-02-06 23:59:59', '2015-11-30 23:59:59', '2016-02-06 23:59:59', '4639.00', '0', '660', '0', '2016-01-07 12:22:59', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb` VALUES ('402880ee00151283904172', '斧头帮·堂主', 'cp_title2.png', '一把板斧走江湖，堂主非我莫属！', '0', '0', '8,12,0', '2016-01-16 11:36:31', '2016-06-06 23:59:59', '100', '台', 'iPhone6s(16G)', '5299.00', 'cp_img1g.png', '100', '2016-02-06 23:59:59', '2015-11-30 23:59:59', '2016-02-06 23:59:59', '4239.00', '0', '1060', '0', null, 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb` VALUES ('402880eea0151283904173', '斧头帮·长老', 'cp_title3.png', '风雨江湖任我行，斧头长老霸气临！', '0', '0', '12,18,0', '2016-01-16 11:36:31', '2016-06-06 23:59:59', '100', '台', 'iPhone6s(16G)', '5299.00', 'cp_img3g_109.png', '100', '2016-02-06 23:59:59', '2015-11-30 23:59:59', '2016-02-06 23:59:59', '3639.00', '0', '1660', '0', null, 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb` VALUES ('402880eeca015128390412', '斧头帮·帮主', 'cp_title4.png', '兄弟齐心砍出道，帮主之名震天下！', '0', '0', '18,24,0', '2016-01-16 11:36:31', '2016-06-06 23:59:59', '100', '台', 'iPhone6s(16G)', '5299.00', 'cp_img4g.png', '100', '2016-02-06 23:59:59', '2015-11-30 23:59:59', '2016-02-06 23:59:59', '3039.00', '0', '2260', '0', '2016-01-05 15:30:48', 'gh_f268aa85d1c7');

-- ----------------------------
-- Table structure for wx_act_commonftb_awards
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_commonftb_awards`;
CREATE TABLE `wx_act_commonftb_awards` (
  `id` varchar(32) NOT NULL,
  `act_id` varchar(32) NOT NULL COMMENT '活动id',
  `awards_seq` int(11) NOT NULL COMMENT '奖品序号',
  `openid` varchar(100) NOT NULL COMMENT '兑奖人openid',
  `nickname` varchar(200) DEFAULT NULL COMMENT '兑奖人昵称',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `coupon_id` varchar(64) DEFAULT NULL COMMENT '卡券id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `jwid` varchar(64) NOT NULL COMMENT '对应微信平台原始id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_actid_awardsseq` (`act_id`,`awards_seq`) USING BTREE,
  UNIQUE KEY `uniq_actid_openid` (`act_id`,`openid`),
  UNIQUE KEY `uniq_couponid` (`coupon_id`) USING BTREE,
  KEY `idx_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='领取奖品记录表';

-- ----------------------------
-- Records of wx_act_commonftb_awards
-- ----------------------------

-- ----------------------------
-- Table structure for wx_act_commonftb_coupon
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_commonftb_coupon`;
CREATE TABLE `wx_act_commonftb_coupon` (
  `id` varchar(32) NOT NULL,
  `act_id` varchar(32) DEFAULT NULL COMMENT '活动id(子活动)',
  `card_id` varchar(64) NOT NULL COMMENT '卡券ID',
  `card_psd` varchar(64) NOT NULL COMMENT '卡券密码',
  `card_type` varchar(255) DEFAULT NULL COMMENT '卡券类型 CASH：代金券类型',
  `brand_name` varchar(36) DEFAULT NULL COMMENT '商户名字',
  `title` varchar(27) DEFAULT NULL COMMENT '卡券名',
  `least_cost` decimal(11,2) DEFAULT NULL COMMENT '代金券专用，表示起用金额（单位为元）',
  `reduce_cost` decimal(11,2) DEFAULT NULL COMMENT '代金券专用，表示减免金额。（单位为元）',
  `status` varchar(2) DEFAULT '0' COMMENT '状态（0:未领取，1:已领取）',
  `jwid` varchar(64) DEFAULT NULL COMMENT '微信原始id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_actid_cardid` (`card_id`,`act_id`) USING BTREE,
  KEY `idx_actid` (`act_id`),
  KEY `idx_cardid` (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡券配置表';

-- ----------------------------
-- Records of wx_act_commonftb_coupon
-- ----------------------------
INSERT INTO `wx_act_commonftb_coupon` VALUES ('1', '402880ee00151283904171', '1088100600112000', '1073752317429716331', 'CASH', 'H5活动汇', '', '0.00', '660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('10', '402880ee00151283904171', '1088100656100009', '1362055373421666503', 'CASH', 'H5活动汇', '', '0.00', '660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('100', '402880ee00151283904171', '1088100660100099', '1066066499103328391', 'CASH', 'H5活动汇', '', '0.00', '660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('101', '402880ee00151283904171', '1088100660100100', '1476970276118746525', 'CASH', 'H5活动汇', '', '0.00', '660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('102', '402880ee00151283904171', '1088100610100101', '2364377753485278823', 'CASH', 'H5活动汇', '', '0.00', '660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('103', '402880ee00151283904171', '1088100660100102', '6053541931138472440', 'CASH', 'H5活动汇', '', '0.00', '660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('105', '402880ee00151283904172', '1088100660100104', '1267870752169883302', 'CASH', 'H5活动汇', '', '0.00', '1060.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('106', '402880ee00151283904172', '1088100630100105', '3604967472182449766', 'CASH', 'H5活动汇', '', '0.00', '1060.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('107', '402880ee00151283904172', '1088100660100106', '1504296811107069015', 'CASH', 'H5活动汇', '', '0.00', '1060.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('108', '402880ee00151283904172', '1088100660100107', '5154896358673355458', 'CASH', 'H5活动汇', '', '0.00', '1060.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('11', '402880ee00151283904172', '1088100660100010', '8262773634346760277', 'CASH', 'H5活动汇', '', '0.00', '1060.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('110', '402880ee00151283904172', '1088100660100109', '7686020119126570399', 'CASH', 'H5活动汇', '', '0.00', '1060.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('111', '402880eea0151283904173', '1088100660100110', '4323641713139569171', 'CASH', 'H5活动汇', '', '0.00', '1660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('112', '402880eea0151283904173', '1088100660100111', '5550342322305958743', 'CASH', 'H5活动汇', '', '0.00', '1660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('113', '402880eea0151283904173', '1088100660100112', '1198113362612441601', 'CASH', 'H5活动汇', '', '0.00', '1660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('114', '402880eea0151283904173', '1088100620100113', '1727891876103277776', 'CASH', 'H5活动汇', '', '0.00', '1660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('115', '402880eea0151283904173', '1088100660100114', '4283614810359914512', 'CASH', 'H5活动汇', '', '0.00', '1660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('116', '402880eea0151283904173', '1088100612100115', '5078744793137186983', 'CASH', 'H5活动汇', '', '0.00', '1660.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('118', '402880eeca015128390412', '1088100660100117', '5903329071158719692', 'CASH', 'H5活动汇', '', '0.00', '2260.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('119', '402880eeca015128390412', '1088100660100118', '1810142104671306216', 'CASH', 'H5活动汇', '', '0.00', '2260.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('12', '402880eeca015128390412', '1088100660100011', '1189949538190820628', 'CASH', 'H5活动汇', '', '0.00', '2260.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('120', '402880eeca015128390412', '1088100660100119', '1954572832150385419', 'CASH', 'H5活动汇', '', '0.00', '2260.00', '0', 'gh_f268aa85d1c7');
INSERT INTO `wx_act_commonftb_coupon` VALUES ('121', '402880eeca015128390412', '1088100661200120', '1638457123922647047', 'CASH', 'H5活动汇', '', '0.00', '2260.00', '0', 'gh_f268aa85d1c7');
-- ----------------------------
-- Table structure for wx_act_commonftb_main
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_commonftb_main`;
CREATE TABLE `wx_act_commonftb_main` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `act_name` varchar(100) NOT NULL COMMENT '砍价活动名称',
  `begain_time` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime DEFAULT NULL COMMENT ' 活动结束时间',
  `many_can_join` varchar(1) DEFAULT '0' COMMENT '是否只能参加一个子活动 0否1是',
  `hdurl` varchar(255) DEFAULT NULL COMMENT '入口地址',
  `template` varchar(10) DEFAULT NULL COMMENT '模版(省份简称)',
  `jwid` varchar(64) NOT NULL COMMENT '微信公众号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '所属项目编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主活动表';

-- ----------------------------
-- Records of wx_act_commonftb_main
-- ----------------------------
INSERT INTO `wx_act_commonftb_main` VALUES ('402880ee513747290151378f1b5a521', '斧头帮', '2016-01-16 12:40:48', '2016-06-06 17:40:52', '1', 'http://wx.jeecg.com/jeewx/weixinLinksucaiController.do?link&id=ff80808152107b460152108e9e630036&mainActId=402880ee513747290151378f1b5a521&jwid=gh_f268aa85d1c7', '', 'gh_f268aa85d1c7', 'commonftb');

-- ----------------------------
-- Table structure for wx_act_commonftb_record
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_commonftb_record`;
CREATE TABLE `wx_act_commonftb_record` (
  `id` varchar(32) NOT NULL COMMENT '记录id',
  `registration_id` varchar(32) NOT NULL COMMENT '报名id',
  `openid` varchar(100) NOT NULL COMMENT '砍价人openid',
  `nickname` varchar(200) NOT NULL COMMENT '砍价人昵称',
  `cut_price` decimal(11,2) NOT NULL COMMENT '砍掉价格',
  `curr_price` decimal(11,2) NOT NULL COMMENT '砍后价格',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `jwid` varchar(64) NOT NULL COMMENT '对应微信平台原始id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_registrationid_openid` (`registration_id`,`openid`) USING BTREE,
  KEY `idx_registrationid` (`registration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮砍记录表';

-- ----------------------------
-- Records of wx_act_commonftb_record
-- ----------------------------

-- ----------------------------
-- Table structure for wx_act_commonftb_registration
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_commonftb_registration`;
CREATE TABLE `wx_act_commonftb_registration` (
  `id` varchar(32) NOT NULL,
  `act_id` varchar(32) NOT NULL COMMENT '活动id',
  `openid` varchar(100) NOT NULL COMMENT '活动所属人openid',
  `nickname` varchar(200) NOT NULL COMMENT '活动所属人昵称',
  `head` varchar(200) DEFAULT NULL COMMENT '会员头像',
  `product_name` varchar(255) NOT NULL COMMENT '产品名称',
  `product_price` decimal(11,2) NOT NULL COMMENT '产品价格',
  `product_new_price` decimal(11,2) NOT NULL COMMENT '砍后价格',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `awards_status` varchar(2) DEFAULT '0' COMMENT '领奖状态0:未领奖;1:已领奖',
  `jwid` varchar(64) NOT NULL COMMENT '对应微信平台原始id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_actid_openid` (`act_id`,`openid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砍价报名表';

-- ----------------------------
-- Records of wx_act_commonftb_registration
-- ----------------------------

-- ----------------------------
-- Table structure for wx_act_commonftb_relation
-- ----------------------------
DROP TABLE IF EXISTS `wx_act_commonftb_relation`;
CREATE TABLE `wx_act_commonftb_relation` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `main_act_id` varchar(32) NOT NULL COMMENT '主活动编码',
  `act_id` varchar(32) NOT NULL COMMENT '活动编码',
  `act_sort` int(11) DEFAULT NULL COMMENT '活动排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_mainactid` (`main_act_id`),
  KEY `idx_actid` (`act_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主活动明细活动配置';

-- ----------------------------
-- Records of wx_act_commonftb_relation
-- ----------------------------
INSERT INTO `wx_act_commonftb_relation` VALUES ('1', '402880ee513747290151378f1b5a521', '402880ee00151283904171', '1', '2015-11-30 17:48:19');
INSERT INTO `wx_act_commonftb_relation` VALUES ('2', '402880ee513747290151378f1b5a521', '402880ee00151283904172', '2', '2015-11-30 17:48:37');
INSERT INTO `wx_act_commonftb_relation` VALUES ('3', '402880ee513747290151378f1b5a521', '402880eea0151283904173', '3', '2015-11-30 17:48:47');
INSERT INTO `wx_act_commonftb_relation` VALUES ('4', '402880ee513747290151378f1b5a521', '402880eeca015128390412', '4', '2015-11-30 17:48:58');




INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152154beacf0000', 'home.title', '入伙斧头帮', '首页标题', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 12:57:22', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152154cdb8d0001', 'information.title', '我的斧头帮', '个人信息页标题', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 12:58:24', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152154dc3400002', 'bhykj.title', '亲友团', '帮好友砍价页面标题', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 12:59:23', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152154ec8790003', 'detail.title', '活动说明', '活动说明页面标题', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:00:30', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea01521550a7cd0004', 'detail.part1', '           <p class=\"detail_ggxx_title\">一、<span>特权活动时间：</span>2015年12月12-24日</p><br />\n           <p class=\"detail_ggxx_title\">二、<span>特权活动对象：</span>仅限江苏省内用户参加，需关注官方微信<i>“沃江苏”。</i></p><br />\n           <p class=\"detail_ggxx_title\">三、<span>特权活动玩法：</span></p>\n           <p class=\"detail_ggxx_list_word\">\n               <span class=\"detail_ggxx_xmfh\">1、</span>\n               <i class=\"detail_ggxx_xmnr\">选中心仪的特权产品，点击“我要砍价”，选择“请人帮砍”分享朋友圈或邀请好友帮忙砍价；</i>\n           </p>\n           <p class=\"detail_ggxx_list_word\">\n               <span class=\"detail_ggxx_xmfh\">2、</span>\n               <i class=\"detail_ggxx_xmnr\">当砍至底价时，点击“立即兑换”，系统将发放对应优惠额的购机现金券密码至参与活动的手机号（温馨提示：记得提前绑定沃江苏的手机号哦）；</i>\n           </p>\n           <p class=\"detail_ggxx_list_word\">\n               <span class=\"detail_ggxx_xmfh\">3、</span>\n               <i class=\"detail_ggxx_xmnr\">领到购机现金券后可查看“用券宝典”，点击“立即购买”进入专属购机页面，即可以特权价购买iPhone 6s。</i>\n           </p>\n           <p class=\"detail_ggxx_jsq\">友情提醒：购机现金券仅限新入网用户在2015年12月12-24日使用。</p>', '活动说明', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:02:33', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea01521556cecb0005', 'index.img.shareShade', 'fxpyq_img.png', '分享到朋友圈的遮罩图片', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:09:16', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea015215580d100006', 'index.img.erweima', 'erwm_img.jpg', '关注二维码图片', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:10:38', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea015215590cdf0007', 'index.tip.noFocus', '参与活动要先关注微信公众号“xxx”哦~', '未关注的提示语', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:11:43', NULL, '2016-1-6 17:40:54');
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152155a17de0008', 'index.tip.noProduct', '活动太火爆，商品已被抢光<br />后台紧急补货中，大侠请稍后再来~', '商品数量为0', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:12:51', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152155b73660009', 'index.tip.shareFriendCircle', '斧头帮拉你入伙，一起来砍，3039元砍出iPhone 6s！', '分享到朋友圈的文字', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:14:20', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152155cca8f000a', 'index.tip.shareFriendDesc', '兄弟，帮我砍上一斧如何？iPhone 6S靠你了！', '分享给好友的文字', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:15:48', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152155d4c08000b', 'index.tip.shareFriendTile', '斧头帮', '分享给好友的标题', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:16:21', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152155fb55d000c', 'index.img.share', '分享小图片', 'http://h5.jeewx.com/content/commonftb/default/img/fxxtp_img.jpg', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:18:59', NULL, '2016-1-6 14:35:38');
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea015215608346000d', 'information.tip.cutMinPrice', '大侠，已经砍到底价啦！<br />快去领购机现金券吧！', '已经看到低价提示语', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:19:52', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea015215613c82000e', 'information.tip.noCutMinPrice', '尚未砍到底价，大侠仍需努<br />继续发动小伙伴们帮忙吧！', '没有砍到低价提示语', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:20:40', NULL, '2016-1-6 16:10:01');
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea01521561d524000f', 'information.tip.noBindPhone', '请在“xxx”公众号<br />回复手机号码进行绑定<br />才能参与活动哦！', '未绑定手机提示语', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:21:19', NULL, '2016-1-6 17:40:35');
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea015215631cc80010', 'controller.shortMessage', '恭喜您获得%s元现金券，密码为%s，专享%s砍价活动优惠，过期作废，此券仅限使用一次，可自己使用或转赠他人。', '发送短信的文案', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:22:43', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152156416e80011', 'bhykj.tip.nextbargain', '板斧已坏<br />大侠休息吧！', '再次为好友砍价提示语', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:23:47', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea015215647d5f0012', 'bhykj.tip.lastbargain', '神功已成<br />小心走火入魔', '已经砍到低价提示语', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:24:13', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea015215651ac20013', 'detail.img', 'hdsm_tab.png', '活动说明的图片', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:24:53', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152156aef3a0014', 'controller.couponExpire', '现金券兑换已过截止日期', '现金券兑换已过截止日期提示语', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:31:15', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee52154bea0152156bd6590015', 'controller.noCoupon', '大侠，您来晚了，券已领完', '券已领完提示语', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 13:32:14', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee5215ed0a015215ed0a020000', 'controller.alreadyRecieveAwards', '您已经领取了奖品，不能参与砍价活动', '如果活动设置了中奖不可继续参与，对应的已经中过奖的提示语', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 15:53:22', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee5215ed0a015216077dcb0001', 'statistics', 'var a;', '统计脚本', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-6 16:22:15', NULL, NULL);
INSERT INTO `jw_system_act_txt` VALUES ('402880ee522086ba01522086ba9e0000', 'home.linkBtnName', '活动链接', '连接按钮名称', '402880ee513747290151378f1b5a521', NULL, NULL, '2016-1-8 17:17:26', NULL, NULL);




INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('28', '斧头帮通用版活动管理', '', '0', '', '', '', '5', '1', '', '0');
INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('2801', '主活动', '', '0', '主活动', '/commonftb/back/wxActCommonftbMain/list.do', '28', '1', '2', 'Y', '0');
INSERT INTO `jw_system_auth` (`auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`) VALUES ('2802', '子活动', '', '0', '子活动', '/commonftb/back/wxActCommonftb/list.do', '28', '2', '2', 'Y', '0');
