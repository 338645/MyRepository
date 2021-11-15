/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : springdatabase

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2021-11-06 20:19:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `sid` int(5) NOT NULL AUTO_INCREMENT,
  `sname` varchar(20) DEFAULT NULL,
  `sage` int(3) DEFAULT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
