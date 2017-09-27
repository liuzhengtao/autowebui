CREATE DATABASE IF NOT EXISTS seleniumui DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE seleniumui;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for slnm_case
-- ----------------------------
DROP TABLE IF EXISTS `slnm_case`;
CREATE TABLE `slnm_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `casecode` varchar(32) NOT NULL COMMENT '用例代码,唯一',
  `casename` varchar(64) NOT NULL COMMENT '用例名称',
  `drivertype` int(11) NOT NULL DEFAULT '1' COMMENT '驱动类型 1-IE 2-Firefox 3-Chrome',
  `driverpath` varchar(128) DEFAULT NULL COMMENT '驱动路径',
  `browserpath` varchar(128) DEFAULT NULL COMMENT '浏览器安装路径',
  `isquitbrowser` int(11) DEFAULT NULL COMMENT '是否退出浏览器 0 or null-否 1-是',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `optcode` varchar(32) DEFAULT NULL COMMENT '操作员代码',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `modifiedtime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_slnm_case_casecode` (`casecode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='测试用例信息';

-- ----------------------------
-- Records of slnm_case
-- ----------------------------
INSERT INTO `slnm_case` VALUES ('1', '0001', 'ludan_login', '3', null, 'C:\\Program Files (x86)\\Google\\Chrome\\Application', '0', '', 'system', '2017-09-25 09:20:19', '2017-09-25 14:25:54');

-- ----------------------------
-- Table structure for slnm_chapter
-- ----------------------------
DROP TABLE IF EXISTS `slnm_chapter`;
CREATE TABLE `slnm_chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `chaptercode` varchar(32) NOT NULL COMMENT '章节代码,唯一',
  `casecode` varchar(32) NOT NULL COMMENT '用例代码',
  `chaptername` varchar(64) NOT NULL COMMENT '章节名称',
  `isrun` int(11) DEFAULT '1' COMMENT '是否运行 0-否 1-是',
  `orderno` int(11) DEFAULT NULL COMMENT '排序号',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `optcode` varchar(32) DEFAULT NULL COMMENT '操作员代码',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `modifiedtime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_slnm_chapter_chaptercode` (`chaptercode`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='章节信息';

-- ----------------------------
-- Records of slnm_chapter
-- ----------------------------
INSERT INTO `slnm_chapter` VALUES ('2', '00002', '0001', 'LoginSuccess', '0', '1', '', 'system', '2017-09-25 10:09:20', '2017-09-25 15:14:02');

-- ----------------------------
-- Table structure for slnm_locator
-- ----------------------------
DROP TABLE IF EXISTS `slnm_locator`;
CREATE TABLE `slnm_locator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `locatorcode` varchar(32) NOT NULL COMMENT '定位器代码,唯一',
  `pagecode` varchar(32) NOT NULL COMMENT '页面代码',
  `locatorname` varchar(64) NOT NULL COMMENT '定位器名称',
  `bytype` varchar(32) NOT NULL COMMENT '查找规则类型 id|name|linkText|partialLinkText|cssSelector|xpath|className|tagName',
  `byvalue` varchar(256) NOT NULL COMMENT '查找规则值',
  `timeout` int(11) NOT NULL DEFAULT '3' COMMENT '超时时间(秒)',
  `waittime` double(11,2) NOT NULL DEFAULT '0.50' COMMENT '等待时间(秒)',
  `opttype` varchar(32) NOT NULL COMMENT '操作类型 click-点击(Click) type-输入(Type) clear-清空(Clear) switchToFrame-选择Frame exitFromFrame-退出Frame',
  `datavalue` varchar(256) DEFAULT NULL COMMENT '数据值',
  `orderno` int(11) DEFAULT NULL COMMENT '排序号',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `modifiedtime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_slnm_locator_locatorcode` (`locatorcode`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='元素定位器信息表';

-- ----------------------------
-- Records of slnm_locator
-- ----------------------------
INSERT INTO `slnm_locator` VALUES ('1', '00000001', '000002', 'username', 'id', 'username', '10', '0.20', 'type', 'kf1uat', '1', '', '2017-09-25 14:00:16', '2017-09-25 14:15:01');
INSERT INTO `slnm_locator` VALUES ('2', '00000002', '000002', 'password', 'id', 'password', '10', '0.20', 'type', 'Zd123456', '2', '', '2017-09-25 14:01:47', '2017-09-25 14:15:07');
INSERT INTO `slnm_locator` VALUES ('3', '00000003', '000002', 'submit', 'xpath', '//input[@type=\'123\']', '10', '0.20', 'click', '', '3', '', '2017-09-25 14:02:33', '2017-09-25 17:32:36');

-- ----------------------------
-- Table structure for slnm_locator_temp
-- ----------------------------
DROP TABLE IF EXISTS `slnm_locator_temp`;
CREATE TABLE `slnm_locator_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `locatorcode` varchar(32) NOT NULL COMMENT '定位器代码,唯一',
  `pagecode` varchar(32) NOT NULL COMMENT '页面代码',
  `locatorname` varchar(64) NOT NULL COMMENT '定位器名称',
  `bytype` varchar(32) NOT NULL COMMENT '查找规则类型 id|name|linkText|partialLinkText|cssSelector|xpath|className|tagName',
  `byvalue` varchar(256) NOT NULL COMMENT '查找规则值',
  `timeout` int(11) NOT NULL DEFAULT '3' COMMENT '超时时间(秒)',
  `waittime` double(11,2) NOT NULL DEFAULT '0.50' COMMENT '等待时间(秒)',
  `opttype` varchar(32) NOT NULL COMMENT '操作类型 click-点击(Click) type-输入(Type) clear-清空(Clear) switchToFrame-选择Frame exitFromFrame-退出Frame',
  `datavalue` varchar(256) DEFAULT NULL COMMENT '数据值',
  `orderno` int(11) DEFAULT NULL COMMENT '排序号',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `modifiedtime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_slnm_locator_temp_locatorcode` (`locatorcode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='元素定位器模板信息表';

-- ----------------------------
-- Records of slnm_locator_temp
-- ----------------------------
INSERT INTO `slnm_locator_temp` VALUES ('1', '00000001', '000001', 'username', 'id', 'username', '10', '0.20', 'type', 'kf1uat', '0', '', '2017-09-25 15:34:07', '2017-09-25 15:34:07');

-- ----------------------------
-- Table structure for slnm_page
-- ----------------------------
DROP TABLE IF EXISTS `slnm_page`;
CREATE TABLE `slnm_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `pagecode` varchar(32) NOT NULL COMMENT '页面代码,唯一',
  `casecode` varchar(32) DEFAULT NULL COMMENT '用例代码',
  `chaptercode` varchar(32) DEFAULT NULL COMMENT '章节代码',
  `pagename` varchar(64) NOT NULL COMMENT '页面名称',
  `pageurl` varchar(128) DEFAULT NULL COMMENT '页面url',
  `datafiletype` int(11) NOT NULL DEFAULT '1' COMMENT '数据文件类型 1-Excel文件 2-Xml文件',
  `datafilepath` varchar(128) DEFAULT NULL COMMENT '数据文件路径',
  `datafilename` varchar(64) DEFAULT NULL COMMENT '数据文件名',
  `isrun` int(11) DEFAULT '1' COMMENT '是否运行 0-否 1-是',
  `orderno` int(11) DEFAULT NULL COMMENT '排序号',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `optcode` varchar(32) DEFAULT NULL COMMENT '操作员代码',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `modifiedtime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_slnm_page_pagecode` (`pagecode`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='页面信息表';

-- ----------------------------
-- Records of slnm_page
-- ----------------------------
INSERT INTO `slnm_page` VALUES ('2', '000002', '0', '00002', 'PageLogin', 'http://nsso-uat.ezendai.com:8080/cas/login?service=http://cfs-uat.ezendai.com:8080/cfs-web-boss/shiro-cas', '1', '', '', '0', '1', '', 'system', '2017-09-25 11:43:07', '2017-09-25 14:22:05');

-- ----------------------------
-- Table structure for slnm_page_temp
-- ----------------------------
DROP TABLE IF EXISTS `slnm_page_temp`;
CREATE TABLE `slnm_page_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `pagecode` varchar(32) NOT NULL COMMENT '页面代码,唯一',
  `casecode` varchar(32) NOT NULL COMMENT '用例代码',
  `pagename` varchar(64) NOT NULL COMMENT '页面名称',
  `pageurl` varchar(128) DEFAULT NULL COMMENT '页面url',
  `datafiletype` int(11) NOT NULL DEFAULT '1' COMMENT '数据文件类型 1-Excel文件 2-Xml文件',
  `datafilepath` varchar(128) DEFAULT NULL COMMENT '数据文件路径',
  `datafilename` varchar(64) DEFAULT NULL COMMENT '数据文件名',
  `isrun` int(11) DEFAULT '1' COMMENT '是否运行 0-否 1-是',
  `orderno` int(11) DEFAULT NULL COMMENT '排序号',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `optcode` varchar(32) DEFAULT NULL COMMENT '操作员代码',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `modifiedtime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_slnm_page_temp_pagecode` (`pagecode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='页面模板信息表';

-- ----------------------------
-- Records of slnm_page_temp
-- ----------------------------
INSERT INTO `slnm_page_temp` VALUES ('1', '000001', '0', 'LoginPage', 'http://nsso-uat.ezendai.com:8080/cas/login?service=http://cfs-uat.ezendai.com:8080/cfs-web-boss/shiro-cas', '1', '', '', '1', '1', '', 'system', '2017-09-25 15:33:15', '2017-09-25 15:33:15');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `deptcode` varchar(32) NOT NULL COMMENT '部门代码,唯一',
  `pdeptcode` varchar(32) NOT NULL COMMENT '上级部门代码',
  `deptname` varchar(64) NOT NULL COMMENT '部门名称',
  `isuse` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用 0-否 1-是',
  `telno` varchar(32) DEFAULT NULL COMMENT '部门电话号码',
  `faxno` varchar(32) DEFAULT NULL COMMENT '部门传真号码',
  `email` varchar(32) DEFAULT NULL COMMENT '部门邮箱',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `optcode` varchar(32) DEFAULT NULL COMMENT '操作员代码',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `modifiedtime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_sys_dept_deptcode` (`deptcode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0000', '0', '管理员部门', '1', '05710000001', '', '', '', 'system', '2017-09-25 09:14:44', null);

-- ----------------------------
-- Table structure for sys_maxkeyvalue
-- ----------------------------
DROP TABLE IF EXISTS `sys_maxkeyvalue`;
CREATE TABLE `sys_maxkeyvalue` (
  `keyname` varchar(32) NOT NULL COMMENT '键名称',
  `keyprefix` varchar(32) DEFAULT NULL COMMENT '键前缀',
  `keyvalue` int(11) NOT NULL DEFAULT '1' COMMENT '键值',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `modifiedtime` datetime DEFAULT NULL COMMENT '修改时间',
  UNIQUE KEY `key_sys_maxkeyvalue_nameandprefix` (`keyname`,`keyprefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最大键值表';

-- ----------------------------
-- Records of sys_maxkeyvalue
-- ----------------------------
INSERT INTO `sys_maxkeyvalue` VALUES ('slnm_case', null, '1', null, '2017-09-25 09:20:19', null);
INSERT INTO `sys_maxkeyvalue` VALUES ('slnm_chapter', null, '3', null, '2017-09-25 09:49:14', '2017-09-25 15:28:29');
INSERT INTO `sys_maxkeyvalue` VALUES ('slnm_page', null, '3', null, '2017-09-25 09:52:21', '2017-09-25 15:35:34');
INSERT INTO `sys_maxkeyvalue` VALUES ('slnm_locator', null, '4', null, '2017-09-25 14:00:16', '2017-09-25 15:35:34');
INSERT INTO `sys_maxkeyvalue` VALUES ('slnm_page_temp', null, '1', null, '2017-09-25 15:33:15', null);
INSERT INTO `sys_maxkeyvalue` VALUES ('slnm_locator_temp', null, '1', null, '2017-09-25 15:34:07', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `mncode` varchar(32) NOT NULL COMMENT '菜单编号',
  `mdcode` varchar(32) NOT NULL COMMENT '模块编号',
  `mdname` varchar(64) NOT NULL COMMENT '模块名称',
  `mnname` varchar(64) NOT NULL COMMENT '菜单名称',
  `isuse` int(11) NOT NULL DEFAULT '0' COMMENT '是否使用 0-否 1-是',
  `url` varchar(128) DEFAULT NULL COMMENT '菜单url',
  `url0` varchar(512) DEFAULT NULL COMMENT '菜单选中url',
  `orderno` int(11) DEFAULT NULL COMMENT '排序号',
  `syscode` varchar(32) NOT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_sys_menu_mncode_syscode` (`mncode`,`syscode`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', 'S0011010', 'S00110', '基础设置', '用户管理', '1', '/user/list', '/user/list#/user/toAdd#/user/toEdit', '100', 'S001');
INSERT INTO `sys_menu` VALUES ('2', 'S0011020', 'S00110', '基础设置', '部门管理', '0', '/dept/list', '/dept/list#/dept/toAdd#/dept/toEdit', '50', 'S001');
INSERT INTO `sys_menu` VALUES ('3', 'S0012010', 'S00120', '用例管理', '用例管理', '1', '/slnmcase/list', '/slnmcase/list#/slnmcase/chapterlist', '100', 'S001');
INSERT INTO `sys_menu` VALUES ('4', 'S0012020', 'S00120', '用例管理', '页面模板管理', '1', '/slnmpagetemp/list', '/slnmpagetemp/list', '200', 'S001');

-- ----------------------------
-- Table structure for sys_menumx
-- ----------------------------
DROP TABLE IF EXISTS `sys_menumx`;
CREATE TABLE `sys_menumx` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `mnmxcode` varchar(32) NOT NULL COMMENT '菜单明细编号',
  `mncode` varchar(32) NOT NULL COMMENT '菜单编号',
  `contorller` varchar(64) DEFAULT NULL COMMENT '控制器访问路径',
  `method` varchar(64) DEFAULT NULL COMMENT '控制器方法',
  `authflag` int(11) DEFAULT '1' COMMENT '权限标识 0-不需要权限 1-需要权限',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `syscode` varchar(32) NOT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_sys_menumx_contorller_method_syscode` (`contorller`,`method`,`syscode`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COMMENT='系统菜单明细表';

-- ----------------------------
-- Records of sys_menumx
-- ----------------------------
INSERT INTO `sys_menumx` VALUES ('1', 'S0011010', 'S0011010', '/user', 'list', '1', '用户管理-用户列表', 'S001');
INSERT INTO `sys_menumx` VALUES ('2', 'S0011010', 'S0011010', '/user', 'toAdd', '1', '用户管理-新增用户页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('3', 'S0011010', 'S0011010', '/user', 'save', '1', '用户管理-新增', 'S001');
INSERT INTO `sys_menumx` VALUES ('4', 'S0011010', 'S0011010', '/user', 'toEdit', '1', '用户管理-编辑用户页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('5', 'S0011010', 'S0011010', '/user', 'update', '1', '用户管理-更新用户', 'S001');
INSERT INTO `sys_menumx` VALUES ('6', 'S0011010', 'S0011010', '/user', 'delete', '1', '用户管理-删除用户', 'S001');
INSERT INTO `sys_menumx` VALUES ('7', 'S0011020', 'S0011020', '/dept', 'list', '1', '部门管理-部门列表', 'S001');
INSERT INTO `sys_menumx` VALUES ('8', 'S0011020', 'S0011020', '/dept', 'toAdd', '1', '部门管理-新增部门页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('9', 'S0011020', 'S0011020', '/dept', 'save', '1', '部门管理-新增', 'S001');
INSERT INTO `sys_menumx` VALUES ('10', 'S0011020', 'S0011020', '/dept', 'toEdit', '1', '部门管理-编辑部门页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('11', 'S0011020', 'S0011020', '/dept', 'update', '1', '部门管理-更新部门', 'S001');
INSERT INTO `sys_menumx` VALUES ('12', 'S0011020', 'S0011020', '/dept', 'delete', '1', '部门管理-删除部门', 'S001');
INSERT INTO `sys_menumx` VALUES ('13', 'S0012010', 'S0012010', '/slnmcase', 'list', '1', '用例管理-用例列表', 'S001');
INSERT INTO `sys_menumx` VALUES ('14', 'S0012010', 'S0012010', '/slnmcase', 'toAdd', '1', '用例管理-新增用例页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('15', 'S0012010', 'S0012010', '/slnmcase', 'save', '1', '用例管理-新增用例', 'S001');
INSERT INTO `sys_menumx` VALUES ('16', 'S0012010', 'S0012010', '/slnmcase', 'toEdit', '1', '用例管理-编辑用例页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('17', 'S0012010', 'S0012010', '/slnmcase', 'update', '1', '用例管理-更新用例', 'S001');
INSERT INTO `sys_menumx` VALUES ('18', 'S0012010', 'S0012010', '/slnmcase', 'delete', '1', '用例管理-删除用例', 'S001');
INSERT INTO `sys_menumx` VALUES ('19', 'S0012010', 'S0012010', '/slnmcase', 'toAddPage', '1', '用例管理-新增页面页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('20', 'S0012010', 'S0012010', '/slnmcase', 'doAddPage', '1', '用例管理-新增页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('21', 'S0012010', 'S0012010', '/slnmcase', 'toEditPage', '1', '用例管理-编辑页面页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('22', 'S0012010', 'S0012010', '/slnmcase', 'doUpdatePage', '1', '用例管理-更新页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('23', 'S0012010', 'S0012010', '/slnmcase', 'toCopyPage', '1', '用例管理-复制页面页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('24', 'S0012010', 'S0012010', '/slnmcase', 'doCopyPage', '1', '用例管理-复制页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('25', 'S0012010', 'S0012010', '/slnmcase', 'doDeletePage', '1', '用例管理-删除页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('26', 'S0012010', 'S0012010', '/slnmcase', 'toAddLocator', '1', '用例管理-新增元素页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('27', 'S0012010', 'S0012010', '/slnmcase', 'doAddLocator', '1', '用例管理-新增元素', 'S001');
INSERT INTO `sys_menumx` VALUES ('28', 'S0012010', 'S0012010', '/slnmcase', 'toEditLocator', '1', '用例管理-编辑元素页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('29', 'S0012010', 'S0012010', '/slnmcase', 'doUpdateLocator', '1', '用例管理-更新元素', 'S001');
INSERT INTO `sys_menumx` VALUES ('30', 'S0012010', 'S0012010', '/slnmcase', 'doDeleteLocator', '1', '用例管理-删除元素', 'S001');
INSERT INTO `sys_menumx` VALUES ('31', 'S0012010', 'S0012010', '/slnmcase', 'exportCase2Xml', '1', '用例管理-导出用例至xml文件', 'S001');
INSERT INTO `sys_menumx` VALUES ('32', 'S0012010', 'S0012010', '/slnmcase', 'toImportCase', '1', '用例管理-导入用例页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('33', 'S0012010', 'S0012010', '/slnmcase', 'doImportCase', '1', '用例管理-导入用例', 'S001');
INSERT INTO `sys_menumx` VALUES ('34', 'S0012010', 'S0012010', '/slnmcase', 'createDataFile', '1', '用例管理-生成数据模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('35', 'S0012010', 'S0012010', '/slnmcase', 'changePageOrder', '1', '用例管理-移动页面顺序', 'S001');
INSERT INTO `sys_menumx` VALUES ('36', 'S0012010', 'S0012010', '/slnmcase', 'toCopyLocator', '1', '用例管理-复制元素页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('37', 'S0012010', 'S0012010', '/slnmcase', 'doCopyLocator', '1', '用例管理-复制元素', 'S001');
INSERT INTO `sys_menumx` VALUES ('38', 'S0012010', 'S0012010', '/slnmcase', 'toMoveLocator', '1', '用例管理-移动元素页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('39', 'S0012010', 'S0012010', '/slnmcase', 'doMoveLocator', '1', '用例管理-移动元素', 'S001');
INSERT INTO `sys_menumx` VALUES ('40', 'S0012010', 'S0012010', '/slnmcase', 'changeLocatorOrder', '1', '用例管理-移动元素顺序', 'S001');
INSERT INTO `sys_menumx` VALUES ('41', 'S0012020', 'S0012020', '/slnmpagetemp', 'list', '1', '页面模板管理-页面模板列表', 'S001');
INSERT INTO `sys_menumx` VALUES ('42', 'S0012020', 'S0012020', '/slnmpagetemp', 'toAdd', '1', '页面模板管理-新增页面模板页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('43', 'S0012020', 'S0012020', '/slnmpagetemp', 'save', '1', '页面模板管理-新增页面模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('44', 'S0012020', 'S0012020', '/slnmpagetemp', 'toEdit', '1', '页面模板管理-编辑页面模板页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('45', 'S0012020', 'S0012020', '/slnmpagetemp', 'update', '1', '页面模板管理-更新页面模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('46', 'S0012020', 'S0012020', '/slnmpagetemp', 'delete', '1', '页面模板管理-删除页面模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('47', 'S0012020', 'S0012020', '/slnmpagetemp', 'toCopyPage', '1', '页面模板管理-复制页面模板页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('48', 'S0012020', 'S0012020', '/slnmpagetemp', 'doCopyPage', '1', '页面模板管理-复制页面模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('49', 'S0012020', 'S0012020', '/slnmpagetemp', 'changePageOrder', '1', '页面模板管理-移动页面模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('50', 'S0012020', 'S0012020', '/slnmpagetemp', 'toAddLocator', '1', '页面模板管理-新增元素模板页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('51', 'S0012020', 'S0012020', '/slnmpagetemp', 'doAddLocator', '1', '页面模板管理-新增元素模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('52', 'S0012020', 'S0012020', '/slnmpagetemp', 'toEditLocator', '1', '页面模板管理-编辑元素模板页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('53', 'S0012020', 'S0012020', '/slnmpagetemp', 'doUpdateLocator', '1', '页面模板管理-更新元素模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('54', 'S0012020', 'S0012020', '/slnmpagetemp', 'toCopyLocator', '1', '页面模板管理-复制元素模板页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('55', 'S0012020', 'S0012020', '/slnmpagetemp', 'doCopyLocator', '1', '页面模板管理-复制元素模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('56', 'S0012020', 'S0012020', '/slnmpagetemp', 'toMoveLocator', '1', '页面模板管理-移动元素模板页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('57', 'S0012020', 'S0012020', '/slnmpagetemp', 'doMoveLocator', '1', '页面模板管理-移动元素模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('58', 'S0012020', 'S0012020', '/slnmpagetemp', 'doDeleteLocator', '1', '页面模板管理-删除元素模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('59', 'S0012020', 'S0012020', '/slnmpagetemp', 'changeLocatorOrder', '1', '页面模板管理-移动元素模板', 'S001');
INSERT INTO `sys_menumx` VALUES ('60', '0', '0', '/', '', '1', '首页', 'S001');
INSERT INTO `sys_menumx` VALUES ('61', '0', '0', '/login', 'index', '0', '登录页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('62', '0', '0', '/login', 'doLogin', '0', '登录', 'S001');
INSERT INTO `sys_menumx` VALUES ('63', '0', '0', '/login', 'doLogout', '0', '登出', 'S001');
INSERT INTO `sys_menumx` VALUES ('64', '0', '0', '/promptmessage', 'removemessage', '0', '清除提示消息', 'S001');
INSERT INTO `sys_menumx` VALUES ('65', '0', '0', '/noauth', 'index', '0', '无权限提示页面', 'S001');
INSERT INTO `sys_menumx` VALUES ('66', '0', '0', '/layui', 'upload', '1', 'layui文件上传-上传单个文件', 'S001');
INSERT INTO `sys_menumx` VALUES ('67', '0', '0', '/ajax/slnmcase', 'openBrowser', '1', '用例管理-打开浏览器Ajax', 'S001');
INSERT INTO `sys_menumx` VALUES ('68', '0', '0', '/ajax/slnmcase', 'doRunPage', '1', '用例管理-运行页面Ajax', 'S001');
INSERT INTO `sys_menumx` VALUES ('69', '0', '0', '/ajax/slnmcase', 'doRunCase', '1', '用例管理-运行用例Ajax', 'S001');
INSERT INTO `sys_menumx` VALUES ('70', '0', '0', '/ajax/slnmcase', 'setIsquitbrowser', '1', '用例管理-设置是否退出浏览器Ajax', 'S001');
INSERT INTO `sys_menumx` VALUES ('71', '0', '0', '/ajax/slnmcase', 'setIsRunPage', '1', '用例管理-设置页面是否运行Ajax', 'S001');
INSERT INTO `sys_menumx` VALUES ('72', '0', '0', '/', 'settheme', '1', '设置主题', 'S001');

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `mdcode` varchar(32) NOT NULL COMMENT '模块编号',
  `mdname` varchar(64) NOT NULL COMMENT '模块名称',
  `orderno` int(11) DEFAULT NULL COMMENT '排序号',
  `syscode` varchar(32) NOT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_sys_module_mdcode_syscode` (`mdcode`,`syscode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统模块表';

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES ('1', 'S00110', '基础设置', '100', 'S001');
INSERT INTO `sys_module` VALUES ('2', 'S00120', '用例管理', '50', 'S001');

-- ----------------------------
-- Table structure for sys_system
-- ----------------------------
DROP TABLE IF EXISTS `sys_system`;
CREATE TABLE `sys_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `syscode` varchar(32) NOT NULL COMMENT '系统编号',
  `sysname` varchar(64) NOT NULL COMMENT '系统名称',
  `url1` varchar(128) DEFAULT NULL COMMENT '默认主页url',
  `url2` varchar(128) DEFAULT NULL COMMENT '登录url',
  `url3` varchar(128) DEFAULT NULL COMMENT '无权限跳转url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_sys_system_syscode` (`syscode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统信息表';

-- ----------------------------
-- Records of sys_system
-- ----------------------------
INSERT INTO `sys_system` VALUES ('1', 'S001', 'autoWeb', '/', '/login/index', '/noauth/index');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `usercode` varchar(32) NOT NULL COMMENT '用户登录代码,唯一',
  `username` varchar(64) NOT NULL COMMENT '用户名称',
  `userpwd` varchar(128) NOT NULL COMMENT '用户密码',
  `isadmin` int(11) NOT NULL DEFAULT '0' COMMENT '是否管理员 0-否 1-是',
  `isuse` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用 0-否 1-是',
  `deptcode` varchar(32) NOT NULL COMMENT '部门编号',
  `telno` varchar(32) DEFAULT NULL COMMENT '用户电话号码',
  `mobileno` varchar(32) DEFAULT NULL COMMENT '用户手机号码',
  `email` varchar(32) DEFAULT NULL COMMENT '用户邮箱',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `optcode` varchar(32) DEFAULT NULL COMMENT '操作员代码',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_sys_user_usercode` (`usercode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'system', '系统管理员', '96e79218965eb72c92a549dd5a330112', '1', '1', '0000', '05710000001', '15600000001', '', '', 'system', '2017-09-25 09:14:45');
INSERT INTO `sys_user` VALUES ('2', 'YM10095', '???', 'd535b67afbcb8e597a6391fa184ca0a2', '1', '1', '0000', '13524868549', '13524868549', 'liuzt@yuminsoft.com', '????', 'system', '2017-09-25 09:29:36');

-- ----------------------------
-- Table structure for sys_userqx
-- ----------------------------
DROP TABLE IF EXISTS `sys_userqx`;
CREATE TABLE `sys_userqx` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键,唯一',
  `usercode` varchar(32) NOT NULL COMMENT '用户登录代码',
  `mncode` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  `contorller` varchar(64) DEFAULT NULL COMMENT '控制器访问路径',
  `method` varchar(64) DEFAULT NULL COMMENT '控制器方法',
  `syscode` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_sys_userqx_usercode_contorller_method_syscode` (`usercode`,`contorller`,`method`,`syscode`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of sys_userqx
-- ----------------------------
INSERT INTO `sys_userqx` VALUES ('1', 'system', 'S0011010', '/user', 'list', 'S001');
INSERT INTO `sys_userqx` VALUES ('2', 'system', 'S0012010', '/slnmcase', 'list', 'S001');
INSERT INTO `sys_userqx` VALUES ('3', 'system', 'S0012020', '/slnmpagetemp', 'list', 'S001');
INSERT INTO `sys_userqx` VALUES ('4', 'YM10095', 'S0012010', '/slnmcase', 'list', 'S001');
INSERT INTO `sys_userqx` VALUES ('5', 'YM10095', 'S0012010', '/slnmcase', 'toAdd', 'S001');
INSERT INTO `sys_userqx` VALUES ('6', 'YM10095', 'S0012010', '/slnmcase', 'save', 'S001');
INSERT INTO `sys_userqx` VALUES ('7', 'YM10095', 'S0012010', '/slnmcase', 'toEdit', 'S001');
INSERT INTO `sys_userqx` VALUES ('8', 'YM10095', 'S0012010', '/slnmcase', 'update', 'S001');
INSERT INTO `sys_userqx` VALUES ('9', 'YM10095', 'S0012010', '/slnmcase', 'delete', 'S001');
INSERT INTO `sys_userqx` VALUES ('10', 'YM10095', 'S0012010', '/slnmcase', 'toAddPage', 'S001');
INSERT INTO `sys_userqx` VALUES ('11', 'YM10095', 'S0012010', '/slnmcase', 'doAddPage', 'S001');
INSERT INTO `sys_userqx` VALUES ('12', 'YM10095', 'S0012010', '/slnmcase', 'toEditPage', 'S001');
INSERT INTO `sys_userqx` VALUES ('13', 'YM10095', 'S0012010', '/slnmcase', 'doUpdatePage', 'S001');
INSERT INTO `sys_userqx` VALUES ('14', 'YM10095', 'S0012010', '/slnmcase', 'toCopyPage', 'S001');
INSERT INTO `sys_userqx` VALUES ('15', 'YM10095', 'S0012010', '/slnmcase', 'doCopyPage', 'S001');
INSERT INTO `sys_userqx` VALUES ('16', 'YM10095', 'S0012010', '/slnmcase', 'doDeletePage', 'S001');
INSERT INTO `sys_userqx` VALUES ('17', 'YM10095', 'S0012010', '/slnmcase', 'toAddLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('18', 'YM10095', 'S0012010', '/slnmcase', 'doAddLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('19', 'YM10095', 'S0012010', '/slnmcase', 'toEditLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('20', 'YM10095', 'S0012010', '/slnmcase', 'doUpdateLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('21', 'YM10095', 'S0012010', '/slnmcase', 'doDeleteLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('22', 'YM10095', 'S0012010', '/slnmcase', 'exportCase2Xml', 'S001');
INSERT INTO `sys_userqx` VALUES ('23', 'YM10095', 'S0012010', '/slnmcase', 'toImportCase', 'S001');
INSERT INTO `sys_userqx` VALUES ('24', 'YM10095', 'S0012010', '/slnmcase', 'doImportCase', 'S001');
INSERT INTO `sys_userqx` VALUES ('25', 'YM10095', 'S0012010', '/slnmcase', 'createDataFile', 'S001');
INSERT INTO `sys_userqx` VALUES ('26', 'YM10095', 'S0012010', '/slnmcase', 'changePageOrder', 'S001');
INSERT INTO `sys_userqx` VALUES ('27', 'YM10095', 'S0012010', '/slnmcase', 'toCopyLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('28', 'YM10095', 'S0012010', '/slnmcase', 'doCopyLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('29', 'YM10095', 'S0012010', '/slnmcase', 'toMoveLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('30', 'YM10095', 'S0012010', '/slnmcase', 'doMoveLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('31', 'YM10095', 'S0012010', '/slnmcase', 'changeLocatorOrder', 'S001');
INSERT INTO `sys_userqx` VALUES ('32', 'YM10095', 'S0012020', '/slnmpagetemp', 'list', 'S001');
INSERT INTO `sys_userqx` VALUES ('33', 'YM10095', 'S0012020', '/slnmpagetemp', 'toAdd', 'S001');
INSERT INTO `sys_userqx` VALUES ('34', 'YM10095', 'S0012020', '/slnmpagetemp', 'save', 'S001');
INSERT INTO `sys_userqx` VALUES ('35', 'YM10095', 'S0012020', '/slnmpagetemp', 'toEdit', 'S001');
INSERT INTO `sys_userqx` VALUES ('36', 'YM10095', 'S0012020', '/slnmpagetemp', 'update', 'S001');
INSERT INTO `sys_userqx` VALUES ('37', 'YM10095', 'S0012020', '/slnmpagetemp', 'delete', 'S001');
INSERT INTO `sys_userqx` VALUES ('38', 'YM10095', 'S0012020', '/slnmpagetemp', 'toCopyPage', 'S001');
INSERT INTO `sys_userqx` VALUES ('39', 'YM10095', 'S0012020', '/slnmpagetemp', 'doCopyPage', 'S001');
INSERT INTO `sys_userqx` VALUES ('40', 'YM10095', 'S0012020', '/slnmpagetemp', 'changePageOrder', 'S001');
INSERT INTO `sys_userqx` VALUES ('41', 'YM10095', 'S0012020', '/slnmpagetemp', 'toAddLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('42', 'YM10095', 'S0012020', '/slnmpagetemp', 'doAddLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('43', 'YM10095', 'S0012020', '/slnmpagetemp', 'toEditLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('44', 'YM10095', 'S0012020', '/slnmpagetemp', 'doUpdateLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('45', 'YM10095', 'S0012020', '/slnmpagetemp', 'toCopyLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('46', 'YM10095', 'S0012020', '/slnmpagetemp', 'doCopyLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('47', 'YM10095', 'S0012020', '/slnmpagetemp', 'toMoveLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('48', 'YM10095', 'S0012020', '/slnmpagetemp', 'doMoveLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('49', 'YM10095', 'S0012020', '/slnmpagetemp', 'doDeleteLocator', 'S001');
INSERT INTO `sys_userqx` VALUES ('50', 'YM10095', 'S0012020', '/slnmpagetemp', 'changeLocatorOrder', 'S001');
INSERT INTO `sys_userqx` VALUES ('51', 'YM10095', 'S0011010', '/user', 'list', 'S001');
INSERT INTO `sys_userqx` VALUES ('52', 'YM10095', 'S0011010', '/user', 'toAdd', 'S001');
INSERT INTO `sys_userqx` VALUES ('53', 'YM10095', 'S0011010', '/user', 'save', 'S001');
INSERT INTO `sys_userqx` VALUES ('54', 'YM10095', 'S0011010', '/user', 'toEdit', 'S001');
INSERT INTO `sys_userqx` VALUES ('55', 'YM10095', 'S0011010', '/user', 'update', 'S001');
INSERT INTO `sys_userqx` VALUES ('56', 'YM10095', 'S0011010', '/user', 'delete', 'S001');
