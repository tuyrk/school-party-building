/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : party

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-31 14:45:39
*/

CREATE DATABASE IF NOT EXISTS party;
USE party;
SET NAMES utf8;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL COMMENT '讨论类型',
  `obj_id` int(11) DEFAULT NULL COMMENT '讨论对象ID',
  `username` varchar(40) NOT NULL COMMENT '姓名',
  `headshot` varchar(40) DEFAULT NULL COMMENT '头像',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `content` tinytext NOT NULL COMMENT '评论内容',
  `image` varchar(240) DEFAULT NULL COMMENT '评论图片',
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='讨论交流副表';

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` int(11) NOT NULL COMMENT '等级',
  `cover` varchar(40) NOT NULL COMMENT '封面',
  `name` varchar(40) NOT NULL COMMENT '课程名',
  `teacher` varchar(40) DEFAULT NULL COMMENT '讲师',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '在学人数',
  `video` varchar(40) NOT NULL COMMENT '视频',
  `chapter` tinytext COMMENT '章节',
  `details` tinytext COMMENT '详情',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经典课程';

-- ----------------------------
-- Table structure for discuss
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `headshot` varchar(40) NOT NULL COMMENT '头像',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `content` tinytext NOT NULL COMMENT '内容',
  `image` varchar(240) DEFAULT NULL COMMENT '图片',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `count2` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `discuss_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='讨论交流主表';

-- ----------------------------
-- Table structure for elective
-- ----------------------------
DROP TABLE IF EXISTS `elective`;
CREATE TABLE `elective` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID\n',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='选课表';

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(60) NOT NULL COMMENT '标题',
  `headshot` varchar(255) NOT NULL COMMENT '头像',
  `content` text NOT NULL COMMENT '内容',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `source` varchar(40) DEFAULT NULL COMMENT '来源',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `news_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热点关注';

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL COMMENT '名字',
  `title` varchar(40) NOT NULL COMMENT '头衔',
  `headshot` varchar(40) NOT NULL COMMENT '头像链接',
  `description` text NOT NULL COMMENT '内容描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `person_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人物长廊';

-- ----------------------------
-- Table structure for social
-- ----------------------------
DROP TABLE IF EXISTS `social`;
CREATE TABLE `social` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(60) NOT NULL COMMENT '标题',
  `headshot` varchar(40) NOT NULL COMMENT '头像',
  `content` text NOT NULL COMMENT '社会实践内容',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `social_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社会实践';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `headshot` varchar(50) DEFAULT '/img/headshot/0.jfif' COMMENT '頭像',
  `username` varchar(40) DEFAULT NULL COMMENT '用户名',
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `name` varchar(40) DEFAULT NULL COMMENT '姓名',
  `birth` date DEFAULT NULL COMMENT '出生年月',
  `nation` varchar(40) DEFAULT NULL COMMENT '民族',
  `political` varchar(40) DEFAULT NULL COMMENT '政治面貌',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `mail` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `school` varchar(40) DEFAULT NULL COMMENT '学校',
  `college` varchar(40) DEFAULT NULL COMMENT '学院',
  `classes` varchar(40) DEFAULT NULL COMMENT '班级',
  `positive` date DEFAULT NULL COMMENT '确定为积极分子时间',
  `prepare` date DEFAULT NULL COMMENT '转预备时间',
  `official` date DEFAULT NULL COMMENT '转正时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userinfo_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人信息';
