/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : springdatabase

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2021-11-08 17:56:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_stock`
-- ----------------------------
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock` (
  `sid` int(5) NOT NULL AUTO_INCREMENT,
  `sname` varchar(20) DEFAULT NULL,
  `count` int(5) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stock
-- ----------------------------
INSERT INTO `t_stock` VALUES ('1', '北京动力节点', '0', '1.5');
