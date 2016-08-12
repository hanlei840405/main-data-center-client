/*
Navicat MySQL Data Transfer

Source Server         : main-data-center
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : main_data_center

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-08-12 16:34:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mdc_account
-- ----------------------------
DROP TABLE IF EXISTS `mdc_account`;
CREATE TABLE `mdc_account` (
  `ID` varchar(26) NOT NULL,
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(32) DEFAULT NULL COMMENT '密码',
  `USER_ID` varchar(24) DEFAULT NULL COMMENT '人员信息外键',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态：0:停用，1:在用',
  `CREATOR` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFIER` varchar(50) DEFAULT NULL COMMENT '最后一次修改人ID',
  `MODIFIED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主数据-账户表';

-- ----------------------------
-- Table structure for mdc_account_role
-- ----------------------------
DROP TABLE IF EXISTS `mdc_account_role`;
CREATE TABLE `mdc_account_role` (
  `ACCOUNT_ID` varchar(26) DEFAULT NULL COMMENT '人员信息ID',
  `ROLE_ID` varchar(26) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员角色关系表';

-- ----------------------------
-- Table structure for mdc_corporation
-- ----------------------------
DROP TABLE IF EXISTS `mdc_corporation`;
CREATE TABLE `mdc_corporation` (
  `ID` varchar(33) NOT NULL COMMENT '主键ID',
  `CATEGORY` char(1) DEFAULT NULL COMMENT '0:个人, 1:企业',
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `TEL` varchar(20) DEFAULT NULL COMMENT '企业电话',
  `CONTACT` varchar(50) DEFAULT NULL COMMENT '企业联系人',
  `CONTACT_TELEPHONE` varchar(20) DEFAULT NULL COMMENT '企业联系人',
  `LEGAL_PERSON` varchar(50) DEFAULT NULL COMMENT '法人',
  `LEGAL_PERSON_TELEPHONE` varchar(20) DEFAULT NULL COMMENT '法人电话',
  `COUNTRY` varchar(20) DEFAULT NULL COMMENT '国家',
  `COUNTRY_CODE` varchar(6) DEFAULT NULL COMMENT '国家编号',
  `PROVINCE` varchar(20) DEFAULT NULL COMMENT '省、自治区、直辖市',
  `PROVINCE_CODE` varchar(100) DEFAULT NULL COMMENT '省、自治区、直辖市编号',
  `CITY` varchar(20) DEFAULT NULL COMMENT '市',
  `CITY_CODE` varchar(100) DEFAULT NULL COMMENT '市编号',
  `DISTRICT` varchar(20) DEFAULT NULL COMMENT '区',
  `DISTRICT_CODE` varchar(6) DEFAULT NULL COMMENT '区编号',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '企业地址',
  `CONTACT_ID_NUMBER` varchar(20) DEFAULT NULL COMMENT '企业联系人身份证号',
  `LEGAL_PERSON_ID_NUMBER` varchar(20) DEFAULT NULL COMMENT '法人身份证号',
  `BUSINESS_LICENCE` varchar(100) DEFAULT NULL COMMENT '营业执照号码',
  `TAX_REGISTRATION_CERTIFICATE` varchar(100) DEFAULT NULL COMMENT '税务登记号码',
  `ORGANIZATION_CODE_CERTIFICATE` varchar(100) DEFAULT NULL COMMENT '组织机构代码证',
  `ACCOUNT_OPENING_LICENSE` varchar(100) DEFAULT NULL COMMENT '银行开户证',
  `LOGO` varchar(32) DEFAULT NULL COMMENT '企业LOGO',
  `BUSINESS_LICENCE_COPY` varchar(32) DEFAULT NULL COMMENT '营业执照副本',
  `TAX_REGISTRATION_CERTIFICATE_COPY` varchar(32) DEFAULT NULL COMMENT '税务登记号副本',
  `ORGANIZATION_CODE_CERTIFICATE_COPY` varchar(32) DEFAULT NULL COMMENT '组织机构代码证副本',
  `ACCOUNT_OPENING_LICENSE_COPY` varchar(32) DEFAULT NULL COMMENT '银行开户副本',
  `LEGAL_PERSON_IDENTIFICATION_CARD_UP_COPY` varchar(32) DEFAULT NULL COMMENT '法人身份证正面',
  `LEGAL_PERSON_IDENTIFICATION_CARD_DOWN_COPY` varchar(32) DEFAULT NULL COMMENT '法人身份证背面',
  `CONTACT_IDENTIFICATION_CARD_UP_COPY` varchar(32) DEFAULT NULL COMMENT '企业联系人身份证正面',
  `CONTACT_IDENTIFICATION_CARD_DOWN_COPY` varchar(32) DEFAULT NULL COMMENT '企业联系人身份证背面',
  `LEVEL` char(1) DEFAULT NULL COMMENT '等级,A:普通会员,B:铜牌会员,C:银牌会员,D:金牌会员',
  `STATUS` char(1) DEFAULT NULL COMMENT '0:删除,1:欠费禁用,2:新建,3:正常',
  `CREATOR` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATED` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `MODIFIER` varchar(50) DEFAULT NULL COMMENT '修改人',
  `MODIFIED` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `VERSION` int(11) DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业表，category为0时，企业相关类的资质可不上传';

-- ----------------------------
-- Table structure for mdc_organization
-- ----------------------------
DROP TABLE IF EXISTS `mdc_organization`;
CREATE TABLE `mdc_organization` (
  `ID` varchar(34) NOT NULL COMMENT '主键',
  `CODE` varchar(8) DEFAULT NULL COMMENT '编号',
  `NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `FULL_CODE` varchar(255) DEFAULT NULL COMMENT '全路径编号',
  `FULL_NAME` varchar(255) DEFAULT NULL COMMENT '全路径名称',
  `LEVEL` char(1) DEFAULT NULL COMMENT '等级',
  `ORGANIZATION_ID` varchar(34) DEFAULT NULL COMMENT '上级部门id',
  `CORPORATION_ID` varchar(33) DEFAULT NULL COMMENT '隶属公司',
  `USER_ID` varchar(26) DEFAULT NULL COMMENT '部门负责人',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态,0:删除,1:正常',
  `CREATOR` varchar(50) DEFAULT NULL COMMENT '创建人',
  `CREATED` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `MODIFIER` varchar(50) DEFAULT NULL COMMENT '修改人',
  `MODIFIED` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Table structure for mdc_role
-- ----------------------------
DROP TABLE IF EXISTS `mdc_role`;
CREATE TABLE `mdc_role` (
  `ID` varchar(26) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态，0:删除，1:在用',
  `CREATOR` varchar(50) DEFAULT NULL COMMENT '创建人username',
  `CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFIER` varchar(50) DEFAULT NULL COMMENT '最后修改人username',
  `MODIFIED` datetime DEFAULT NULL COMMENT '最后修改时间',
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for mdc_user
-- ----------------------------
DROP TABLE IF EXISTS `mdc_user`;
CREATE TABLE `mdc_user` (
  `ID` varchar(26) NOT NULL,
  `REAl_NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `SEX` char(1) DEFAULT NULL COMMENT '性别',
  `BIRTHDAY` date DEFAULT NULL COMMENT '出生日期',
  `TELEPHONE` varchar(15) DEFAULT NULL COMMENT '电话号码',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `QQ` varchar(15) DEFAULT NULL COMMENT 'QQ',
  `WX` varchar(50) DEFAULT NULL COMMENT '微信',
  `WEI_BO` varchar(50) DEFAULT NULL COMMENT '微博',
  `PHOTO` varchar(50) DEFAULT NULL COMMENT '头像地址',
  `LEVEL` char(1) DEFAULT NULL COMMENT '等级',
  `CORPORATION_ID` varchar(33) DEFAULT NULL COMMENT '所属企业',
  `ORGANIZATION_ID` varchar(34) DEFAULT NULL COMMENT '所属组织机构',
  `STATUS` char(1) DEFAULT NULL COMMENT '状态：0:停用，1:在用',
  `CREATOR` varchar(50) DEFAULT NULL COMMENT '创建人ID',
  `CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFIER` varchar(50) DEFAULT NULL COMMENT '最后一次修改人ID',
  `MODIFIED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主数据-人员信息表';
SET FOREIGN_KEY_CHECKS=1;
