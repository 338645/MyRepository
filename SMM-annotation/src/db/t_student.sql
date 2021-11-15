/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : spring_web_database

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2021-11-14 19:43:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
