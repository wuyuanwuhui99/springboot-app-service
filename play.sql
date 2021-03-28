/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 60011
 Source Host           : localhost:3306
 Source Schema         : play

 Target Server Type    : MySQL
 Target Server Version : 60011
 File Encoding         : 65001

 Date: 28/03/2021 21:06:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节名称',
  `visitors` int(8) NOT NULL COMMENT '访问人数',
  `catalog` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目录',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '播放地址',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `course_id` int(11) NULL DEFAULT NULL COMMENT '课程id',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节链接',
  `need_pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是要登录，0：立即观看，1：登录观看，2：支付观看',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41429 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for chapter_log
-- ----------------------------
DROP TABLE IF EXISTS `chapter_log`;
CREATE TABLE `chapter_log`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节名称',
  `visitors` int(8) NOT NULL COMMENT '访问人数',
  `catalog` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目录',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '播放地址',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `course_id` int(11) NULL DEFAULT NULL COMMENT '课程id',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节链接',
  `need_pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否需要登录，0：立即观看，1：登录观看，2：支付观看',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id，在这个表中没有使用到，为了跟日志表保持一致',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `summary` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程简介',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\n教程程度',
  `software_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '软件版本',
  `required_base` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所需基础',
  `suitable_for_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适合人群',
  `total_chapter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节数量',
  `learner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学过的人',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推出时间',
  `teacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师',
  `qualification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师资质',
  `course_describe` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程描述',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大类',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源码',
  `material` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '素材',
  `blackboard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '板书',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程连接',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程图片地址',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地图片',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id，在这个表中没有使用到，为了跟日志表保持一致',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 856 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for course_copy
-- ----------------------------
DROP TABLE IF EXISTS `course_copy`;
CREATE TABLE `course_copy`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `summary` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程简介',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\n教程程度',
  `software_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '软件版本',
  `required_base` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所需基础',
  `suitable_for_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适合人群',
  `total_chapter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节数量',
  `learner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学过的人',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推出时间',
  `teacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师',
  `qualification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师资质',
  `course_describe` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程描述',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大类',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源码',
  `material` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '素材',
  `blackboard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '板书',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程连接',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程图片地址',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地图片地址',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for course_log
-- ----------------------------
DROP TABLE IF EXISTS `course_log`;
CREATE TABLE `course_log`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `summary` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程简介',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\n教程程度',
  `software_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '软件版本',
  `required_base` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所需基础',
  `suitable_for_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适合人群',
  `total_chapter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节数量',
  `learner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学过的人',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推出时间',
  `teacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师',
  `qualification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师资质',
  `course_describe` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程描述',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大类',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源码',
  `material` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '素材',
  `blackboard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '板书',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程连接',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程图片地址',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地图片地址',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id，在这个表中没有使用到，为了跟日志表保持一致',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for douyin
-- ----------------------------
DROP TABLE IF EXISTS `douyin`;
CREATE TABLE `douyin`  (
  `id` int(11) NOT NULL,
  `albummid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `duration` int(11) NULL DEFAULT NULL COMMENT '播放时长，单位秒',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌曲图片',
  `mid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌名',
  `singer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌手',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌曲播放地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `timer` int(11) NULL DEFAULT 0 COMMENT '播放次数',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `kugou_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '酷狗音乐url地址',
  `play_mode` enum('kugou','qq','local') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用酷狗音乐的url或使用QQ音乐的url',
  `other_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他的url地址',
  `local_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地的url地址',
  `disabled` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0表示不禁用，1表示禁用',
  `lyric` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '歌词',
  `local_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地图片的地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言',
  `chapters_num` int(10) NULL DEFAULT NULL COMMENT '章节数量',
  `frequency` int(11) NULL DEFAULT NULL COMMENT '阅读人数',
  `collection` int(11) NULL DEFAULT NULL COMMENT '收藏次数',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源',
  `source_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 来源的url',
  `sharer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分享人',
  `share_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分享人地址',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阅读地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地图片地址',
  `label` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大分类',
  `rank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排名',
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小分类',
  `category_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类url地址',
  `category_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类网络图片地址',
  `category_img_local` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类本地图片地址',
  `category_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 836 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ebook_banner
-- ----------------------------
DROP TABLE IF EXISTS `ebook_banner`;
CREATE TABLE `ebook_banner`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行的路径',
  `loacal_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地图片地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_used` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否使用中',
  `rank` int(255) NULL DEFAULT NULL COMMENT '排名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ebook_chapter
-- ----------------------------
DROP TABLE IF EXISTS `ebook_chapter`;
CREATE TABLE `ebook_chapter`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `book_id` int(10) NULL DEFAULT NULL COMMENT '书籍的id',
  `chapter_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节的名称',
  `chapter_url` varchar(1000) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '章节的链接地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39721 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ebook_likes
-- ----------------------------
DROP TABLE IF EXISTS `ebook_likes`;
CREATE TABLE `ebook_likes`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍的url',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联的书籍的名称',
  `book_id` int(10) NULL DEFAULT NULL COMMENT '关联的书籍的id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37535 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ebook_relation
-- ----------------------------
DROP TABLE IF EXISTS `ebook_relation`;
CREATE TABLE `ebook_relation`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地图片地址',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联的书籍名称',
  `book_id` int(10) NULL DEFAULT NULL COMMENT '关联的书籍的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35028 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for favorite_movie
-- ----------------------------
DROP TABLE IF EXISTS `favorite_movie`;
CREATE TABLE `favorite_movie`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导演',
  `star` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主演',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `country_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国家/语言',
  `viewing_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '观看状态	',
  `release_time` date NULL DEFAULT NULL COMMENT '上映时间',
  `plot` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '剧情',
  `update_time` date NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影名称',
  `is_recommend` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否推荐，0:不推荐，1:推荐',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影海报',
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源名称，本地，骑士影院，爱奇艺',
  `source_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本地图片',
  `url` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '播放地址',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '播放集数',
  `original_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '源地址',
  `item_group` int(11) NULL DEFAULT NULL COMMENT '播放源分组0',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简单描述',
  `target_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接地址',
  `use_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0代表未使用，1表示正在使用，是banner和carousel图的才有',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评分',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类目，值为banner首屏，carousel：滚动轮播',
  `rank` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排名',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名，这这个表不需要，为了跟记录叫和收藏表的结构一致',
  `star_group` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '演员图片和演员姓名'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for favorite_music
-- ----------------------------
DROP TABLE IF EXISTS `favorite_music`;
CREATE TABLE `favorite_music`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 主键',
  `id` int(11) NOT NULL,
  `albummid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `duration` int(11) NULL DEFAULT NULL COMMENT '播放时长，单位秒',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌曲图片',
  `mid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌名',
  `singer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌手',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌曲播放地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `timer` int(11) NULL DEFAULT NULL COMMENT '播放次数',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `kugou_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '酷狗音乐url地址',
  `play_mode` enum('kugou','qq','local') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用酷狗音乐的url或使用QQ音乐的url',
  `other_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他的url地址',
  `local_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地的url地址',
  `disabled` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0表示不禁用，1表示禁用',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `lyric` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '歌词',
  `local_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地的图片',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for learn_banner
-- ----------------------------
DROP TABLE IF EXISTS `learn_banner`;
CREATE TABLE `learn_banner`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址',
  `disabled` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否禁用，0：否，1：是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `headers` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求头',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `params` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `query_string` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url上面的参数',
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求结果',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '请求开始时间',
  `run_time` int(10) NOT NULL COMMENT '耗时',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口描述',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '请求结束时间',
  `oparation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求类型，get,post,put',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用的id',
  `app_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用的名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2667 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `movie_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导演',
  `star` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主演',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `country_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国家/语言',
  `viewing_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '观看状态	',
  `release_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上映时间',
  `plot` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '剧情',
  `update_time` date NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影名称',
  `is_recommend` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否推荐，0:不推荐，1:推荐',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影海报',
  `classify` enum('电影','电视剧','动漫','新片库','福利','午夜','恐怖','综艺','其他') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源名称，本地，骑士影院，爱奇艺',
  `source_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本地图片',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签',
  `original_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '源地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简单描述',
  `target_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接地址',
  `use_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0代表未使用，1表示正在使用，是banner和carousel图的才有',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评分',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类目，值为banner首屏，carousel：滚动轮播',
  `rank` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排名',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名，这这个表不需要，为了跟记录叫和收藏表的结构一致',
  `douban_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '对应豆瓣网的地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14816 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for movie_category
-- ----------------------------
DROP TABLE IF EXISTS `movie_category`;
CREATE TABLE `movie_category`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `classify` enum('电影','电视剧','动漫','新片库','福利','午夜','恐怖','综艺','其他') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类目，值为banner首屏，carousel：滚动轮播',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '使用状态状态，1表示显示，0表示隐藏',
  `page_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面名称',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for movie_favorite
-- ----------------------------
DROP TABLE IF EXISTS `movie_favorite`;
CREATE TABLE `movie_favorite`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `movie_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导演',
  `star` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主演',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `country_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国家/语言',
  `viewing_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '观看状态	',
  `release_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上映时间',
  `plot` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '剧情',
  `update_time` date NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影名称',
  `is_recommend` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否推荐，0:不推荐，1:推荐',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影海报',
  `classify` enum('电影','电视剧','动漫','新片库','福利','午夜','恐怖','综艺','其他') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源名称，本地，骑士影院，爱奇艺',
  `source_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本地图片',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签',
  `original_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '源地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简单描述',
  `target_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接地址',
  `use_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0代表未使用，1表示正在使用，是banner和carousel图的才有',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评分',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类目，值为banner首屏，carousel：滚动轮播',
  `rank` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排名',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名，这这个表不需要，为了跟记录叫和收藏表的结构一致',
  `douban_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '对应豆瓣网的地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for movie_network
-- ----------------------------
DROP TABLE IF EXISTS `movie_network`;
CREATE TABLE `movie_network`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `movie_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导演',
  `star` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主演',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `country_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国家/语言',
  `viewing_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '观看状态	',
  `release_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上映时间',
  `plot` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '剧情',
  `update_time` date NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影名称',
  `is_recommend` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否推荐，0:不推荐，1:推荐',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影海报',
  `classify` enum('电影','电视剧','动漫','新片库','福利','午夜','恐怖','综艺','其他') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源名称，本地，骑士影院，爱奇艺',
  `source_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本地图片',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签',
  `original_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '源地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简单描述',
  `target_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接地址',
  `use_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0代表未使用，1表示正在使用，是banner和carousel图的才有',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评分',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类目，值为banner首屏，carousel：滚动轮播',
  `rank` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排名',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名，这这个表不需要，为了跟记录叫和收藏表的结构一致',
  `douban_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '对应豆瓣网的地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21366 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for movie_play_record
-- ----------------------------
DROP TABLE IF EXISTS `movie_play_record`;
CREATE TABLE `movie_play_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `movie_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导演',
  `star` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主演',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `country_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国家/语言',
  `viewing_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '观看状态	',
  `release_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上映时间',
  `plot` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '剧情',
  `update_time` date NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影名称',
  `is_recommend` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否推荐，0:不推荐，1:推荐',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影海报',
  `classify` enum('电影','电视剧','动漫','新片库','福利','午夜','恐怖','综艺','其他') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源名称，本地，骑士影院，爱奇艺',
  `source_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本地图片',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签',
  `original_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '源地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简单描述',
  `target_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接地址',
  `use_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0代表未使用，1表示正在使用，是banner和carousel图的才有',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评分',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类目，值为banner首屏，carousel：滚动轮播',
  `rank` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排名',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名，这这个表不需要，为了跟记录叫和收藏表的结构一致',
  `douban_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '对应豆瓣网的地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for movie_stars
-- ----------------------------
DROP TABLE IF EXISTS `movie_stars`;
CREATE TABLE `movie_stars`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `star_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '演员名称',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '演员图片地址',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '演员本地图片地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `movie_id` int(10) NULL DEFAULT NULL COMMENT '对应电影的id',
  `movie_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应电影的名称',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '演员的豆瓣链接地址',
  `works` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代表作',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129642 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for movie_url
-- ----------------------------
DROP TABLE IF EXISTS `movie_url`;
CREATE TABLE `movie_url`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `movie_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电影名称',
  `movie_id` int(11) NULL DEFAULT NULL COMMENT '对应的电影的id',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源地址',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '播放地址',
  `play_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '播放分组，1, 2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 234866 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for movie_view_record
-- ----------------------------
DROP TABLE IF EXISTS `movie_view_record`;
CREATE TABLE `movie_view_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `movie_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导演',
  `star` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主演',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `country_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国家/语言',
  `viewing_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '观看状态	',
  `release_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上映时间',
  `plot` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '剧情',
  `update_time` date NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影名称',
  `is_recommend` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否推荐，0:不推荐，1:推荐',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影海报',
  `classify` enum('电影','电视剧','动漫','新片库','福利','午夜','恐怖','综艺','其他') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他',
  `source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源名称，本地，骑士影院，爱奇艺',
  `source_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `local_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本地图片',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签',
  `original_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '源地址',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简单描述',
  `target_href` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接地址',
  `use_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '0代表未使用，1表示正在使用，是banner和carousel图的才有',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评分',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类目，值为banner首屏，carousel：滚动轮播',
  `rank` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '排名',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名，这这个表不需要，为了跟记录叫和收藏表的结构一致',
  `douban_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '对应豆瓣网的地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qishi
-- ----------------------------
DROP TABLE IF EXISTS `qishi`;
CREATE TABLE `qishi`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导演',
  `star` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主演',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `country_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国家/语言',
  `viewing_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '观看状态	',
  `release_time` date NULL DEFAULT NULL COMMENT '上映时间',
  `plot` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '剧情',
  `update_time` date NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影名称',
  `is_recommend` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否推荐，0:不推荐，1:推荐',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电影海报',
  `classify` enum('电影','电视剧','动漫','新片库','福利','午夜','恐怖','综艺','其他') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类 电影,电视剧,动漫,综艺,新片库,福利,午夜,恐怖,其他',
  `source` enum('local','qishi','aiqiyi') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '来源，local:本地，qishi：骑士影院，aiqiyi:爱奇艺',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for record_course
-- ----------------------------
DROP TABLE IF EXISTS `record_course`;
CREATE TABLE `record_course`  (
  `t_id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程简介',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\n教程程度',
  `software_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '软件版本',
  `required_base` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所需基础',
  `suitable_for_people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适合人群',
  `total_chapter` int(3) NULL DEFAULT NULL COMMENT '章节数量',
  `learner` int(8) NULL DEFAULT NULL COMMENT '学过的人',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推出时间',
  `teacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师',
  `qualification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师资质',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程描述',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大类',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源码',
  `material` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '素材',
  `blackboard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '板书',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程连接',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程图片地址',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for record_music
-- ----------------------------
DROP TABLE IF EXISTS `record_music`;
CREATE TABLE `record_music`  (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '  播放记录的id',
  `id` int(11) NULL DEFAULT NULL,
  `albummid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `duration` int(11) NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌名',
  `singer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌手',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '歌曲播放地址',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `timer` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '播放次数',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11863 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `birthday` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生年月日',
  `sex` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `secret_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '明文密码',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Function structure for get_role
-- ----------------------------
DROP FUNCTION IF EXISTS `get_role`;
delimiter ;;
CREATE FUNCTION `get_role`(user_id varchar(15))
 RETURNS int(11)
begin 
    declare u_role varchar(30);
    select role from user where userId=user_id into u_role;
    return u_role;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
