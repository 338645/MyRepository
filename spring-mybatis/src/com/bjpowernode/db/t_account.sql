/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : springdatabase

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2021-11-08 17:56:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_account`
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `aid` int(5) NOT NULL AUTO_INCREMENT,
  `aname` varchar(20) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('1', '张三', '10000');
INSERT INTO `t_account` VALUES ('2', '李四', '2000');
