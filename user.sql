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

 Date: 22/11/2022 22:17:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `avater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `birthday` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生年月日',
  `sex` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `region` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `disabled` int(10) UNSIGNED ZEROFILL NULL DEFAULT 0000000000 COMMENT '是否禁用，0表示不不禁用，1表示禁用',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('且听风铃', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:54:22', '2021-06-17 23:31:54', '且听风铃', '15302686947', '275018723@qq.com', '/static/user/avater/且听风铃.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('初晓微芒', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:47:01', '2020-01-11 18:47:03', '初晓微芒', '15302686947', '275018723@qq.com', '/static/user/avater/初晓微芒.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('半夏时光', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:46:09', '2020-01-11 18:46:12', '半夏时光', '15302686947', '275018723@qq.com', '/static/user/avater/半夏时光.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('半岛弥音', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-18 18:41:52', '2020-01-11 18:41:57', '半岛弥音', '15302686947', '275018723@qq.com', '/static/user/avater/半岛弥音.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('吴尽吴穷', 'e10adc3949ba59abbe56e057f20f883e', '2019-08-19 00:59:28', '2019-08-19 00:59:32', '吴尽吴穷', '15302686947', '275018723@qq.com', '/static/user/avater/吴怨吴悔.jpg', '1990-10-8', '男', 'public', '无穷无尽的爱', NULL, 0000000000);
INSERT INTO `user` VALUES ('吴忧吴虑', 'e10adc3949ba59abbe56e057f20f883e', '2019-08-13 21:01:56', '2019-08-13 21:02:02', '吴忧吴虑', '15302686947', '275018723@qq.com', '/static/user/avater/吴怨吴悔.jpg', '1990-10-8', '男', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('吴怨吴悔', 'e10adc3949ba59abbe56e057f20f883e', '2019-08-13 00:00:00', '2019-08-13 00:00:00', '吴怨吴悔', '15302686947', '275018723@qq.com', '/static/user/avater/吴怨吴悔.jpg', '1990-10-8', '男', 'admin', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('吴时吴刻', 'e10adc3949ba59abbe56e057f20f883e', '2019-08-12 00:00:00', '2019-08-13 00:00:00', '吴时吴刻', '15302686947', '275018723@qq.com', '/static/user/avater/吴时吴刻.jpg', '1990-10-8', '男', 'public', '无时无刻不想你', NULL, 0000000000);
INSERT INTO `user` VALUES ('夕颜泪痕', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:55:53', '2020-01-11 18:55:56', '夕颜泪痕', '15302686947', '275018723@qq.com', '/static/user/avater/夕颜泪痕.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('孤影倾城', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:49:08', '2020-01-11 18:49:11', '孤影倾城', '15302686947', '275018723@qq.com', '/static/user/avater/孤影倾城.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('归去如风', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:49:43', '2020-01-11 18:49:47', '归去如风', '15302686947', '275018723@qq.com', '/static/user/avater/归去如风.jpg', '1990-10-8', '男', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('灯火阑珊', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:47:39', '2020-01-11 18:47:42', '灯火阑珊', '15302686947', '275018723@qq.com', '/static/user/avater/灯火阑珊.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('离殇荡情', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:50:58', '2020-01-11 18:51:01', '离殇荡情', '15302686947', '275018723@qq.com', '/static/user/avater/离殇荡情.jpeg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('秋水天长', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:55:00', '2020-01-11 18:55:03', '秋水天长', '15302686947', '275018723@qq.com', '/static/user/avater/秋水天长.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('空城旧梦', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:50:23', '2020-01-11 18:50:26', '空城旧梦', '15302686947', '275018723@qq.com', '/static/user/avater/空城旧梦.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('落寞雨季', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:53:02', '2020-01-11 18:53:06', '落寞雨季', '15302686947', '275018723@qq.com', '/static/user/avater/落寞雨季.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('落落清欢', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:52:15', '2020-01-11 18:52:18', '落落清欢', '15302686947', '275018723@qq.com', '/static/user/avater/落落清欢.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('逆夏光年', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:53:40', '2020-01-11 18:53:42', '逆夏光年', '15302686947', '275018723@qq.com', '/static/user/avater/逆夏光年.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('離別的抽泣', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:51:33', '2020-01-11 18:51:35', '離別的抽泣', '15302686947', '275018723@qq.com', '/static/user/avater/離別的抽泣.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('雨晨清风', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:57:53', '2020-01-11 18:57:56', '雨晨清风', '15302686947', '275018723@qq.com', '/static/user/avater/雨晨清风.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);
INSERT INTO `user` VALUES ('飞颜尘雪', 'e10adc3949ba59abbe56e057f20f883e', '2020-01-11 18:48:28', '2020-01-11 18:48:31', '飞颜尘雪', '15302686947', '275018723@qq.com', '/static/user/avater/飞颜尘雪.jpg', '1990-10-8', '女', 'public', '无怨，有悔', NULL, 0000000000);

SET FOREIGN_KEY_CHECKS = 1;
