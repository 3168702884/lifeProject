/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : classic

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 04/05/2022 03:28:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for access
-- ----------------------------
DROP TABLE IF EXISTS `access`;
CREATE TABLE `access`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `access` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `described` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of access
-- ----------------------------
INSERT INTO `access` VALUES ('0', 'admin', '管理员', 0);
INSERT INTO `access` VALUES ('1', 'user', '普通用户', 0);
INSERT INTO `access` VALUES ('2', 'visitor', '游客', 0);

-- ----------------------------
-- Table structure for graphic_text
-- ----------------------------
DROP TABLE IF EXISTS `graphic_text`;
CREATE TABLE `graphic_text`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一ID',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图文类别',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图文标题',
  `imgadress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `introduce` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `detail_text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细介绍',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of graphic_text
-- ----------------------------
INSERT INTO `graphic_text` VALUES ('0', '风格实例', '原木风格', 'https://pic1.zhimg.com/v2-e8575794e4f0af2fb760fc426eeaae3b_1440w.jpg?source=172ae18b', '木质纹理相呼应 质朴自然', '原木风是以原木材料的运用为标志元素的风格。 原木和日式、北欧、现代等风格结合，可以演变出以下5种原木风： 白色原木、灰色原木、黑白原木、淡彩原木、现代原木。 这5种原木风的硬装基本相似：1、地板采用自然的原木色，相对平静舒缓，温润又克制。', 0);
INSERT INTO `graphic_text` VALUES ('1', '风格实例', '北欧风格', 'https://pic3.zhimg.com/v2-51b92226bd2b546cdb5ccc573f438844_b.jpg', '北欧风格起源于斯堪的纳维亚地区的设计风格，因此也被称为“斯堪的纳维亚风格”。具有简约、自然、人性化的特点。', NULL, 0);
INSERT INTO `graphic_text` VALUES ('10', '无障碍', '无障碍家居装修要注意什么', NULL, NULL, '01 无障碍地面\r\n室内地面平坦，没有门槛绊脚，卫生间、客厅、卧室的地面没有凹凸不平，避免落差台阶，方便老人进出。\r\n02 地面防滑\r\n地面防滑重中之重，老人一不小心摔跤很容易造成重大的损伤，地面尽量采用防滑地砖，或者铺设木板。\r\n03 扶手\r\n在楼梯、过道、卫生间的淋浴区、和马桶边安装扶手，方便行动不便的老人，使用有扶手的椅子，让老人家可以借助扶手站起来。\r\n04 卫生间设计\r\n卫生间地面一定要防滑，在卫生间门口放置防滑垫，做到干湿分离，淋浴区安装壁挂的折叠淋浴凳子，节省空间同时方便老人家洗澡。', 0);
INSERT INTO `graphic_text` VALUES ('11', '定制客户', '定制客户1', NULL, NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('12', '过程', '设计过程', NULL, NULL, '设计者通过空间布局、形式设计、软装材质、灯光布置及细节处理等方式为业主营造具有特点的居家环境，控制色调给人以温馨或静谧等的居家之感', 0);
INSERT INTO `graphic_text` VALUES ('13', '过程', '装修过程1', NULL, '储存空间的长期规划', '在装修过程中，我们可能不会太考虑后期的日常生活中的储物功能，所以等到入住后才会发现。最好是在装修前要考虑到室内装修中的存放功能。', 0);
INSERT INTO `graphic_text` VALUES ('14', '过程', '装修过程2', NULL, '统一选材', '在家居装修中用的材料和家具的选择，由于款式好看，往往会选择不同种类的木材，最后摆放室内的时候，就会发现混乱不堪的局面，最好挑选颜色单一，品种单一的木材家具。', 0);
INSERT INTO `graphic_text` VALUES ('15', '过程', '装修过程3', NULL, '智能家居', '现在很多别墅内部装修都采用智能家居，但在一般的家居装修中，智能家居的安装量相对较小。虽然现在可能不需要安装，但将来使用起来会非常方便。而且，以后安装也很麻烦', 1);
INSERT INTO `graphic_text` VALUES ('1501904204036931586', '风格实例', '北欧', 'https://pic1.zhimg.com/v2-e8575794e4f0af2fb760fc426eeaae3b_1440w.jpg?source=172ae18b', '北欧', '北欧', 0);
INSERT INTO `graphic_text` VALUES ('16', '菜单', '风格实例', NULL, NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('17', '菜单', '设计方案', NULL, NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('18', '菜单', '设计过程Tips', NULL, NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('19', '菜单', '无障碍定制', NULL, NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('3', '风格实例', '极简风格', 'https://pic3.zhimg.com/80/v2-2122f6fb9bdd4b84f48eb1bbedb025fa_720w.jpg', '极简设计的设计核心理念同样是希望通过简约的设计给人们带来更好的使用感，即简约但富有意义', '222', 1);
INSERT INTO `graphic_text` VALUES ('4', '设计方案', '优雅法式 法式风格', 'http://tencentjiaju.img-cn-beijing.aliyuncs.com/20190211/h280w211-5c6124f64e25b.jpg', NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('5', '设计方案', '纯净北欧范 ', 'http://tencentjiaju.img-cn-beijing.aliyuncs.com/20180123/h267w201-5a66eaee32079.jpg', NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('6', '设计方案', '棕榈泉 “精致生活”贯穿于设计始末', 'http://tencentjiaju.img-cn-beijing.aliyuncs.com/20181015/h280w211-5bc44869dcdf7.jpg', NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('7', '设计方案', '中式茶楼 去繁存简的设计手法', 'http://tencentjiaju.img-cn-beijing.aliyuncs.com/20180307/h267w201-5a9f60b708e9f.jpg', NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('8', '轮播', '轮播1', 'https://zos.alipayobjects.com/rmsportal/hzPBTkqtFpLlWCi.jpg', NULL, NULL, 0);
INSERT INTO `graphic_text` VALUES ('9', '轮播', '轮播2', 'https://zos.alipayobjects.com/rmsportal/xHxWkcvaIcuAdQl.jpg', NULL, NULL, 0);

-- ----------------------------
-- Table structure for only_text
-- ----------------------------
DROP TABLE IF EXISTS `only_text`;
CREATE TABLE `only_text`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文本类别',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文本标题',
  `introduce` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `detail_text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细介绍',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of only_text
-- ----------------------------
INSERT INTO `only_text` VALUES (1, '\r\n设计过程', '\r\n设计过程', '', '设计者通过空间布局、形式设计、软装材质、灯光布置及细节处理等方式为业主营造具有特点的居家环境，控制色调给人以温馨或静谧等的居家之感', 0);
INSERT INTO `only_text` VALUES (2, '装修过程', '储存空间的长期规划', '', '在装修过程中，我们可能不会太考虑后期的日常生活中的储物功能，所以等到入住后才会发现。最好是在装修前要考虑到室内装修中的存放功能。', 0);
INSERT INTO `only_text` VALUES (3, '装修过程', '统一选材', NULL, NULL, 0);
INSERT INTO `only_text` VALUES (4, '装修过程', '智能家居', NULL, '现在很多别墅内部装修都采用智能家居，但在一般的家居装修中，智能家居的安装量相对较小。虽然现在可能不需要安装，但将来使用起来会非常方便。而且，以后安装也很麻烦', 0);
INSERT INTO `only_text` VALUES (5, '装修过程', '装修过程', NULL, NULL, 0);

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1470112743750664197 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', 0, NULL, NULL);
INSERT INTO `test_user` VALUES (2, 'root', 12, 'root@qq.com', 0, NULL, '2021-12-13 03:31:17');
INSERT INTO `test_user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', 0, NULL, NULL);
INSERT INTO `test_user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com', 0, NULL, NULL);
INSERT INTO `test_user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', 0, NULL, NULL);
INSERT INTO `test_user` VALUES (6, 'root', 12, 'root@qq.com', 1, NULL, NULL);
INSERT INTO `test_user` VALUES (1470111338566516737, 'gzx', 18, '2803708553@qq.com', 0, NULL, NULL);
INSERT INTO `test_user` VALUES (1470111651507736578, 'zx', 18, '2803708553@qq.com', 0, NULL, '2021-12-13 03:21:46');
INSERT INTO `test_user` VALUES (1470112743750664195, 'ggzx', 18, '2803708553@qq.com', 0, '2021-12-13 03:29:47', NULL);
INSERT INTO `test_user` VALUES (1470112743750664196, 'aaa', 18, '2803708553@qq.com', 0, '2021-12-19 17:59:06', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `access` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1472519821244559361', 'gzx', '123', 'admin', 0, '2021-12-19 18:50:58', NULL);
INSERT INTO `user` VALUES ('1498995191124602881', '飒飒', '111', 'visitor', 0, NULL, '2022-03-03 10:36:20');
INSERT INTO `user` VALUES ('1499267264849920002', 'sssss', 'ddsss', 'visitor', 0, NULL, NULL);
INSERT INTO `user` VALUES ('1499298671731163137', 'ssp', 'sspp', 'visitor', 0, NULL, '2022-03-10 20:13:31');
INSERT INTO `user` VALUES ('1501904914833047553', '嗯嗯', '222', 'user', 0, '2022-03-10 20:56:50', NULL);
INSERT INTO `user` VALUES ('2', 'user', '1233', 'user', 1, NULL, '2022-03-10 20:55:57');
INSERT INTO `user` VALUES ('3', 'test', '11111', 'user', 1, NULL, '2022-03-02 20:10:17');

SET FOREIGN_KEY_CHECKS = 1;
