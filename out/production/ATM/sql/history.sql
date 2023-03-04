/*
 Navicat Premium Data Transfer

 Source Server         : Kylesql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : atm

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 07/05/2020 17:28:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` int(0) NOT NULL,
  `time` datetime(0) NOT NULL,
  `accountID` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES (1, '用户存款', 100000, '2020-05-07 09:15:09', 1);
INSERT INTO `history` VALUES (2, '用户转账', 100000, '2020-05-07 09:15:29', 1);
INSERT INTO `history` VALUES (3, '查看记录', 10000000, '2020-05-07 09:15:34', 1);
INSERT INTO `history` VALUES (4, '查看记录', 10000000, '2020-05-07 09:15:51', 1);
INSERT INTO `history` VALUES (5, '用户存款', 100, '2020-05-07 09:15:55', 1);
INSERT INTO `history` VALUES (6, '用户存款', 200, '2020-05-07 09:15:56', 1);
INSERT INTO `history` VALUES (7, '用户存款', 500, '2020-05-07 09:15:57', 1);
INSERT INTO `history` VALUES (8, '用户存款', 1000, '2020-05-07 09:15:59', 1);
INSERT INTO `history` VALUES (9, '用户存款', 5000, '2020-05-07 09:15:59', 1);
INSERT INTO `history` VALUES (10, '用户存款', 10000, '2020-05-07 09:16:00', 1);
INSERT INTO `history` VALUES (11, '查询余额', 10000000, '2020-05-07 09:16:01', 1);
INSERT INTO `history` VALUES (12, '用户取款', 100, '2020-05-07 09:16:06', 1);
INSERT INTO `history` VALUES (13, '用户取款', 200, '2020-05-07 09:16:06', 1);
INSERT INTO `history` VALUES (14, '用户取款', 500, '2020-05-07 09:16:07', 1);
INSERT INTO `history` VALUES (15, '用户取款', 1000, '2020-05-07 09:16:07', 1);
INSERT INTO `history` VALUES (16, '用户取款', 5000, '2020-05-07 09:16:07', 1);
INSERT INTO `history` VALUES (17, '用户取款', 10000, '2020-05-07 09:16:08', 1);
INSERT INTO `history` VALUES (18, '查看记录', 10000000, '2020-05-07 09:16:10', 1);

SET FOREIGN_KEY_CHECKS = 1;
