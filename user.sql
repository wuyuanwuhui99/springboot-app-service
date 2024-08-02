/*
 Navicat Premium Data Transfer

 Source Server         : 本机mysql
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : play

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 02/08/2024 23:53:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `avater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `birthday` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生年月日',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别，0:男，1:女',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `region` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `disabled` int(0) NULL DEFAULT 0 COMMENT '是否禁用，0表示不不禁用，1表示禁用',
  `permission` int(0) NULL DEFAULT 0 COMMENT '权限大小',
  PRIMARY KEY (`id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123', 'e10adc3949ba59abbe56e057f20f883e', '2024-01-20 00:44:24', '2024-01-20 00:44:24', '123', '123', '123', NULL, '2024-01-02', '0', NULL, '123', '123', 0, 0);
INSERT INTO `user` VALUES (2, '12345', 'e10adc3949ba59abbe56e057f20f883e', '2024-01-20 12:50:45', '2024-01-20 12:50:45', '123456', '123456', '123456', NULL, NULL, '0', NULL, '123456', '123456', 0, 0);
INSERT INTO `user` VALUES (3, '123456', 'e10adc3949ba59abbe56e057f20f883e', '2024-01-20 00:39:56', '2024-01-20 00:39:56', '123456', '123456', '', NULL, '2024-01-08', '0', NULL, '123456', '123456', 0, 0);
INSERT INTO `user` VALUES (4, '1234567', 'e10adc3949ba59abbe56e057f20f883e', '2024-01-21 14:14:45', '2024-01-21 14:14:45', '1234567', '1234567', '1234567', '', '1990-03-01', '0', NULL, '1234567', '1234567', 0, 0);
INSERT INTO `user` VALUES (5, 'qqqqqq', '343B1C4A3EA721B2D640FC8700DB0F36', '2024-04-24 01:13:18', '2024-04-24 01:13:18', 'qqqqqq', '123456', '123456', NULL, '1990-02-01', '0', NULL, NULL, '123456', 0, 0);
INSERT INTO `user` VALUES (6, 'wuyuanwuhui', 'e10adc3949ba59abbe56e057f20f883e', '2024-02-25 23:13:59', '2024-02-25 23:13:59', '123456', '', '', NULL, '', '', NULL, '', '', 0, 0);
INSERT INTO `user` VALUES (7, '且听风铃', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:54:22', '2024-01-21 10:56:13', '且听风铃2', '15302686947', '275018723@qq.com', '/static/user/avater/且听风铃.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (8, '初晓微芒', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:47:01', '2020-01-11 18:47:03', '初晓微芒', '15302686947', '275018723@qq.com', '/static/user/avater/初晓微芒.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (9, '半夏时光', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:46:09', '2020-01-11 18:46:12', '半夏时光', '15302686947', '275018723@qq.com', '/static/user/avater/半夏时光.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (10, '半岛弥音', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-18 18:41:52', '2020-01-11 18:41:57', '半岛弥音', '15302686947', '275018723@qq.com', '/static/user/avater/半岛弥音.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (11, '吴尽吴穷', 'e10adc3949ba59abbe56e057f20f883e', '2019-08-19 00:59:28', '2019-08-19 00:59:32', '吴尽吴穷', '15302686947', '275018723@qq.com', '/static/user/avater/吴怨吴悔.jpg', '1990-10-8', '0', 'public', '无穷无尽的爱', NULL, 0, 0);
INSERT INTO `user` VALUES (12, '吴忧吴虑', 'e10adc3949ba59abbe56e057f20f883e', '2019-08-13 21:01:56', '2019-08-13 21:02:02', '吴忧吴虑', '15302686947', '275018723@qq.com', '/static/user/avater/吴怨吴悔.jpg', '1990-10-8', '0', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (13, '吴怨吴悔', 'e10adc3949ba59abbe56e057f20f883e', '2019-08-13 00:00:00', '2024-07-31 23:15:30', '吴怨吴悔', '15302686947', '275018723@qq.com', '/static/user/avater/吴怨吴悔.jpg', '1990-10-10', '0', 'admin', '无怨，有悔', NULL, 0, 1);
INSERT INTO `user` VALUES (14, '吴时吴刻', 'e10adc3949ba59abbe56e057f20f883e', '2019-08-12 00:00:00', '2024-01-19 23:17:29', '吴时吴刻', '15302686947', '275018723@qq.com', '/static/user/avater/吴时吴刻.jpg', '1990-10-8', '0', 'public', '无时无刻不想你', NULL, 0, 0);
INSERT INTO `user` VALUES (15, '夕颜泪痕', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:55:53', '2020-01-11 18:55:56', '夕颜泪痕', '15302686947', '275018723@qq.com', '/static/user/avater/夕颜泪痕.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (16, '孤影倾城', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:49:08', '2024-02-25 23:27:01', '孤影倾城', '15302686947', '275018723@qq.com', '/static/user/avater/孤影倾城.jpg', '1990-10-8', '1', 'public', '无怨，有悔', '深圳', 0, 0);
INSERT INTO `user` VALUES (17, '归去如风', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:49:43', '2020-01-11 18:49:47', '归去如风', '15302686947', '275018723@qq.com', '/static/user/avater/归去如风.jpg', '1990-10-8', '0', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (18, '灯火阑珊', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:47:39', '2024-02-25 15:42:22', '灯火阑珊2', '15302686947', '275018723@qq.com', '/static/user/avater/灯火阑珊.jpg', '1989-10-08', '1', 'public', '无怨，有悔', '深圳', 0, 0);
INSERT INTO `user` VALUES (19, '离殇荡情', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:50:58', '2020-01-11 18:51:01', '离殇荡情', '15302686947', '275018723@qq.com', '/static/user/avater/离殇荡情.jpeg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (20, '秋水天长', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:55:00', '2024-04-21 17:29:57', '秋水天长2', '15302686947', '275018723@qq.com', '/static/user/avater/秋水天长.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (21, '空城旧梦', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:50:23', '2023-04-21 23:52:38', '空城旧梦', '15302686947', '275018723@qq.com', '/static/user/avater/空城旧梦.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (22, '落寞雨季', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:53:02', '2022-12-18 00:11:04', '落寞雨季', '15302686947', '275018723@qq.com', '/static/user/avater/落寞雨季.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (23, '落落清欢', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:52:15', '2024-04-25 22:40:20', '落落清欢', '15302686947', '275018723@qq.com', '/static/user/avater/落落清欢.jpg', '1990-10-8', '0', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (24, '逆夏光年', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:53:40', '2020-01-11 18:53:42', '逆夏光年', '15302686947', '275018723@qq.com', '/static/user/avater/逆夏光年.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (25, '離別的抽泣', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:51:33', '2024-02-23 21:27:02', '離別的抽泣3', '15302686947', '275018723@qq.com', '/static/user/avater/離別的抽泣.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (26, '雨晨清风', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:57:53', '2024-01-10 23:16:14', '雨晨清风', '15302686947', '275018723@qq.com', '/static/user/avater/雨晨清风.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);
INSERT INTO `user` VALUES (27, '飞颜尘雪', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:48:28', '2020-01-11 18:48:31', '飞颜尘雪', '15302686947', '275018723@qq.com', '/static/user/avater/飞颜尘雪.jpg', '1990-10-8', '1', 'public', '无怨，有悔', NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
