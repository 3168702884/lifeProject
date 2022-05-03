/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : wx-competition

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 04/05/2022 03:58:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goodsID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goodsName` char(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goodsIntroduction` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goodsAdress` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sendPhonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `sendID` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodImgAdress1` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goodImgAdress2` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodImgAdress3` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for jiaoyizhong
-- ----------------------------
DROP TABLE IF EXISTS `jiaoyizhong`;
CREATE TABLE `jiaoyizhong`  (
  `shangpin_ID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID',
  `shangpin` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品号',
  `buy_ID` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家ID',
  `send_ID` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖家ID',
  `buy_phonenumber` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家电话号码',
  `send_phonenumber` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖家点好号码',
  `jianjie` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品简介',
  `buy_signal` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家信号',
  `send_signal` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖家信号',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态',
  `imgadress1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for needshop
-- ----------------------------
DROP TABLE IF EXISTS `needshop`;
CREATE TABLE `needshop`  (
  `address` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shangpin` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jianjie` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_phonenumber` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_ID` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shangpin_ID` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgadress1` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendButtonStatu` enum('正在上架','有人联系','正在联系','已下架') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '正在上架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `goodsID` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goodsName` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `buyID` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sendID` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `buyPhonenumber` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sendPhonenumber` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goodsIntroduction` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `buySignal` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sendSignal` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` double NOT NULL,
  `count` int(11) NOT NULL,
  `status` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `schoolcode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`schoolcode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `shangpin_ID` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shangpin` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `jianjie` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `send_phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_ID` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `sendButtonStatu` enum('正在上架','有人联系','正在联系','已下架') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '正在上架',
  `imgadress1` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgadress2` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgadress3` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `wxID` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `universityname` char(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phonenumber` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
