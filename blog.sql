/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-06-01 10:51:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentID` int(11) NOT NULL AUTO_INCREMENT,
  `replyUserID` int(11) NOT NULL,
  `paperID` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`commentID`),
  KEY `paperID` (`paperID`),
  KEY `replyUserID` (`replyUserID`),
  CONSTRAINT `paperID` FOREIGN KEY (`paperID`) REFERENCES `paper` (`paperID`),
  CONSTRAINT `replyUserID` FOREIGN KEY (`replyUserID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `paperID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `loadURL` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `abstract` varchar(255) DEFAULT NULL,
  `content` varchar(10240) NOT NULL,
  `state` int(11) NOT NULL,
  `review` varchar(255) DEFAULT NULL,
  `readTimes` int(11) DEFAULT '0',
  PRIMARY KEY (`paperID`),
  KEY `userID` (`userID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of paper
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `userImage` varchar(255) DEFAULT NULL,
  `regTime` datetime NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `captcha` varchar(255) DEFAULT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '445630956', 'bwang', '123456', '1', null, '2017-06-01 00:12:50', null, null, '0');
