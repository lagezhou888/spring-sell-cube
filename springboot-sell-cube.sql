/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : springboot-sell-cube

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 01/12/2020 20:08:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userId` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attachment
-- ----------------------------
INSERT INTO `attachment` VALUES (55, 'http://172.20.10.73:8080/api/upload/8d32e1ea-bf27-4098-98c6-f83c0a35e53c.jpg', '8d32e1ea-bf27-4098-98c6-f83c0a35e53c.jpg', 2);
INSERT INTO `attachment` VALUES (60, 'http://172.20.10.73:8080/api/upload/d7ed7966-e989-4c65-8561-3bd0cb66e356.png', 'd7ed7966-e989-4c65-8561-3bd0cb66e356.png', 4);
INSERT INTO `attachment` VALUES (61, 'http://172.20.10.73:8080/api/upload/8de41d70-f739-4338-844f-cfb5c42074e1.png', '8de41d70-f739-4338-844f-cfb5c42074e1.png', 5);
INSERT INTO `attachment` VALUES (92, 'http://localhost:8080/api/upload/4e7dda8c-ec43-4cf0-ae09-3a46b53e0385.png', '4e7dda8c-ec43-4cf0-ae09-3a46b53e0385.png', 1);

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家名称',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `pinyin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名拼音',
  `advanceNum` int(0) NULL DEFAULT NULL COMMENT '预定量',
  `receivedNum` int(0) NULL DEFAULT NULL COMMENT '已收量',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信用标签',
  `isAcquisition` bit(1) NULL DEFAULT NULL COMMENT '是否收购',
  `imgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES (1, '王五', 11, '13111111111', 'W', 2000, 1000, '靠谱', b'1', 'https://img.yzcdn.cn/vant/cat.jpeg');
INSERT INTO `business` VALUES (2, '啊啊', 12, '1322222222', 'A', 3000, 1000, '黑心', b'1', 'https://img.yzcdn.cn/vant/cat.jpeg');
INSERT INTO `business` VALUES (3, '王六', 13, '1333333333', 'W', 5000, 1000, '温柔', b'0', 'https://img.yzcdn.cn/vant/cat.jpeg');
INSERT INTO `business` VALUES (4, '赵六', 14, '13444444444', 'Z', 3000, 1600, '给力', b'0', 'https://img.yzcdn.cn/vant/cat.jpeg');
INSERT INTO `business` VALUES (5, '曹一', 30, '1543257561', 'C', 8000, 8000, '帅气逼人', b'1', 'https://img.yzcdn.cn/vant/cat.jpeg');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` int(0) NOT NULL COMMENT '主键',
  `countDownFinishTime` datetime(0) NULL DEFAULT NULL COMMENT '倒计时结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, '2020-11-30 13:46:47');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '曹广州', 30, '1', 'caogzh', '1', '15543257561');
INSERT INTO `user` VALUES (4, '牟强', 24, '1', 'muq', '1', '13844816591');
INSERT INTO `user` VALUES (5, '李智', 27, '1', 'liz', '1', '13394475083');

-- ----------------------------
-- Table structure for userasset
-- ----------------------------
DROP TABLE IF EXISTS `userasset`;
CREATE TABLE `userasset`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '水果名称',
  `isChecked` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否种植',
  `selectId` int(0) NULL DEFAULT NULL COMMENT '选择种植的水果种类ID',
  `selectName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选择种植的水果种类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userasset
-- ----------------------------
INSERT INTO `userasset` VALUES (45, 4, '苹果', '1', 1, '嘎啦');
INSERT INTO `userasset` VALUES (46, 4, '苹果', '1', 2, '红将军');
INSERT INTO `userasset` VALUES (47, 4, '苹果', '1', 3, '红富士');
INSERT INTO `userasset` VALUES (48, 5, '樱桃', '1', 1, '枣红');
INSERT INTO `userasset` VALUES (49, 5, '樱桃', '1', 2, '大紫');
INSERT INTO `userasset` VALUES (50, 5, '樱桃', '1', 8, '萨米脱');
INSERT INTO `userasset` VALUES (53, 1, '樱桃', '1', 1, '枣红');

SET FOREIGN_KEY_CHECKS = 1;
