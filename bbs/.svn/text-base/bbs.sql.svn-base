/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.1.73-log : Database - bbs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bbs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bbs`;

/*Table structure for table `t_activecode` */

DROP TABLE IF EXISTS `t_activecode`;

CREATE TABLE `t_activecode` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `code` varchar(64) NOT NULL,
  `type` varchar(10) NOT NULL COMMENT 'signup:注册 reset:修改密码',
  `is_use` tinyint(2) NOT NULL DEFAULT '0',
  `expires_time` int(10) NOT NULL COMMENT '过期时间',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `t_activecode` */

/*Table structure for table `t_award` */

DROP TABLE IF EXISTS `t_award`;

CREATE TABLE `t_award` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户主键',
  `tid` int(11) DEFAULT NULL COMMENT '奖券主键',
  `prov` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `dist` varchar(30) DEFAULT NULL COMMENT '区县',
  `address` varchar(255) DEFAULT NULL COMMENT '详细收件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `genre` int(11) DEFAULT NULL COMMENT '类型（0实物、1话费）',
  `goods` varchar(255) DEFAULT NULL COMMENT '奖品',
  `name` varchar(20) DEFAULT NULL COMMENT '收件人',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `state` int(1) NOT NULL COMMENT '状态（0未处理、1已处理、2无效）',
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `t_award` */

/*Table structure for table `t_checkin` */

DROP TABLE IF EXISTS `t_checkin`;

CREATE TABLE `t_checkin` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `day` varchar(64) NOT NULL COMMENT '签到日期',
  `create_time` int(10) NOT NULL COMMENT '签到时间',
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `t_checkin` */

insert  into `t_checkin`(`id`,`uid`,`day`,`create_time`) values (29,31,'2016-11-22',1479798463),(30,35,'2016-11-23',1479864776),(31,33,'2016-11-23',1479867208),(32,2,'2016-11-23',1479867398),(33,3,'2016-11-23',1479867515),(34,32,'2016-11-23',1479867521),(35,8,'2016-11-23',1479867614),(36,40,'2016-11-23',1479867695);

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `cid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL COMMENT '评论人uid',
  `to_uid` int(10) NOT NULL COMMENT '被评论人uid',
  `tid` int(10) NOT NULL COMMENT '帖子id',
  `content` text NOT NULL COMMENT '评论内容',
  `device` varchar(255) DEFAULT 'pc' COMMENT '设备',
  `create_time` int(10) NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;

/*Data for the table `t_comment` */

insert  into `t_comment`(`cid`,`uid`,`to_uid`,`tid`,`content`,`device`,`create_time`) values (140,40,32,63,'<p style=\"box-sizing: border-box; -webkit-margin-before: 1em; -webkit-margin-after: 1em; margin-top: 0px; margin-bottom: 10px; font-family: &#39;Microsoft Yahei&#39;; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"box-sizing: border-box; font-weight: 700;\">您好<img src=\"http://img.baidu.com/hi/jx2/j_0046.gif\"/>我是票管</span><span style=\"font-weight: 700;\">家小梅很高兴为您服务，欢迎使用“</span><span style=\"font-weight: 700;\"><span style=\"color: rgb(255, 0, 0);\">票管家社区</span></span><span style=\"font-weight: 700;\">”平台咨询，关于您的问题回答如下：</span></p><p style=\"box-sizing: border-box; -webkit-margin-before: 1em; -webkit-margin-after: 1em; margin-top: 0px; margin-bottom: 10px; font-family: &#39;Microsoft Yahei&#39;; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255);\">&nbsp; &nbsp; &nbsp;<span style=\"font-weight: 700; box-sizing: border-box; text-decoration: underline; font-size: 16px; color: rgb(23, 54, 93);\">银行承兑汇票是商业汇票的一种。是由在承兑银行开立存款账户的存款人出票，向开户银行申请并经银行审查同意承兑的，保证在指定日期无条件支付确定的金额给收款人或持票人的票据。对出票人签发的商业汇票进行承兑是银行基于对出票人资信的认可而给予的信用支持！</span></p><p><br/></p>','Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36',1479804003);

/*Table structure for table `t_favorite` */

DROP TABLE IF EXISTS `t_favorite`;

CREATE TABLE `t_favorite` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL COMMENT 'topic:帖子 node:节点',
  `uid` int(10) NOT NULL,
  `event_id` int(10) NOT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;

/*Data for the table `t_favorite` */

insert  into `t_favorite`(`id`,`type`,`uid`,`event_id`,`create_time`) values (167,'love',26,63,1479804782);

/*Table structure for table `t_link` */

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `url` varchar(100) NOT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_link` */

/*Table structure for table `t_node` */

DROP TABLE IF EXISTS `t_node`;

CREATE TABLE `t_node` (
  `nid` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) NOT NULL DEFAULT '0' COMMENT '父节点id',
  `title` varchar(30) DEFAULT NULL COMMENT '节点名称',
  `description` varchar(255) DEFAULT NULL COMMENT '节点描述',
  `slug` varchar(50) NOT NULL COMMENT '节点英文简写',
  `pic` varchar(100) DEFAULT NULL COMMENT '节点图片',
  `topics` int(10) DEFAULT '0' COMMENT '帖子数',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  `update_time` int(10) NOT NULL,
  `is_del` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `t_node` */

insert  into `t_node`(`nid`,`pid`,`title`,`description`,`slug`,`pic`,`topics`,`create_time`,`update_time`,`is_del`) values (1,0,'票据管家社区','票据天地','all','',0,1457933734,1478086294,0),(7,1,'票据天天乐','社区活动•新闻头条','joyful','upload/image/20161122/20161122101125747_q5F5oziLqm.png',1,1459519229,1479780686,0),(8,1,'票据咨询','票据新闻•疑问解答','advice','upload/image/20161122/20161122101115209_c3KuMrM0gr.png',1,1459519247,1479780676,0),(48,1,'自由交流','疑难杂票•融资交流•畅所欲言','talk','upload/image/20161122/20161122101135201_Q1lglsPIFL.png',4,1461218252,1479780696,0);

/*Table structure for table `t_node_user` */

DROP TABLE IF EXISTS `t_node_user`;

CREATE TABLE `t_node_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL COMMENT '用户主键',
  `nid` int(10) NOT NULL COMMENT '板块',
  `create_time` int(10) NOT NULL COMMENT '签到时间',
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_node_user` */

insert  into `t_node_user`(`id`,`uid`,`nid`,`create_time`) values (3,26,8,1478152072);

/*Table structure for table `t_notice` */

DROP TABLE IF EXISTS `t_notice`;

CREATE TABLE `t_notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `to_uid` int(10) NOT NULL,
  `event_id` int(10) NOT NULL,
  `is_read` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

/*Data for the table `t_notice` */

insert  into `t_notice`(`id`,`type`,`uid`,`to_uid`,`event_id`,`is_read`,`create_time`) values (59,'comment',40,32,140,1,1479804003);

/*Table structure for table `t_openid` */

DROP TABLE IF EXISTS `t_openid`;

CREATE TABLE `t_openid` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `open_id` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_openid` (`open_id`,`type`,`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_openid` */

/*Table structure for table `t_pointlog` */

DROP TABLE IF EXISTS `t_pointlog`;

CREATE TABLE `t_pointlog` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL COMMENT '用户主键（得分者）',
  `hid` int(10) DEFAULT NULL COMMENT '用户主键（触发者）',
  `score` int(10) DEFAULT NULL COMMENT '分数',
  `fun` varchar(64) DEFAULT NULL COMMENT '事件',
  `fk_id` int(10) DEFAULT NULL COMMENT '外键（帖子主键）',
  `create_time` int(10) NOT NULL COMMENT '签到时间',
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=186 DEFAULT CHARSET=utf8;

/*Data for the table `t_pointlog` */

insert  into `t_pointlog`(`id`,`uid`,`hid`,`score`,`fun`,`fk_id`,`create_time`) values (1,26,26,10620,'QIANDAO',NULL,1478229375),(2,1,1,1560,'QIANDAO',NULL,1478229375),(173,26,26,10,'DIANZAN',63,1479804782),(172,32,26,20,'HUIFU',63,1479804003),(157,31,31,10,'DIANZAN',58,1479795044),(171,32,26,20,'HUIFU',63,1479803665),(156,31,31,50,'JINGHUA',58,1479795043),(155,31,31,100,'FATIE',58,1479795041),(154,31,35,20,'HUIFU',57,1479794883),(158,31,31,100,'FATIE',59,1479795127),(159,31,31,50,'JINGHUA',59,1479795129),(160,31,31,10,'DIANZAN',59,1479795130),(161,31,31,100,'FATIE',60,1479795168),(58,31,31,2340,'QIANDAO',NULL,1479647210),(65,32,32,3140,'QIANDAO',NULL,1479707642),(170,32,32,100,'FATIE',63,1479802249),(80,33,33,1260,'QIANDAO',NULL,1479710674),(166,31,31,10,'DIANZAN',61,1479795253),(167,35,35,10,'QIANDAO',NULL,1479797329),(168,35,35,100,'FATIE',62,1479798297),(169,31,31,10,'QIANDAO',NULL,1479798463),(116,35,35,2190,'QIANDAO',NULL,1479776334),(117,2,2,950,'QIANDAO',NULL,1479777151),(118,3,3,850,'QIANDAO',NULL,1479777180),(162,31,31,50,'JINGHUA',60,1479795170),(163,31,31,10,'DIANZAN',60,1479795171),(164,31,31,100,'FATIE',61,1479795251),(165,31,31,50,'JINGHUA',61,1479795252),(150,31,31,100,'QIANDAO',57,1479794725),(185,40,40,100,'FATIE',68,1479867944),(184,8,8,100,'FATIE',67,1479867711),(183,40,40,10,'QIANDAO',NULL,1479867695),(182,35,35,100,'FATIE',66,1479867665),(181,8,8,10,'QIANDAO',NULL,1479867614),(180,3,3,100,'FATIE',65,1479867571),(179,32,32,10,'QIANDAO',NULL,1479867521),(178,3,3,10,'QIANDAO',NULL,1479867515),(177,2,2,10,'QIANDAO',NULL,1479867398),(176,33,33,100,'FATIE',64,1479867350),(175,33,33,10,'QIANDAO',NULL,1479867208),(174,35,35,10,'QIANDAO',NULL,1479864776);

/*Table structure for table `t_settings` */

DROP TABLE IF EXISTS `t_settings`;

CREATE TABLE `t_settings` (
  `skey` varchar(50) NOT NULL,
  `svalue` text,
  PRIMARY KEY (`skey`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_settings` */

insert  into `t_settings`(`skey`,`svalue`) values ('comment_count','1'),('site_description','票据管家交流社区是一个旨在促进票据人士对票据的交流平台。'),('site_keywords','票据管家社区,票据交流,收票'),('site_title','票据管家'),('topic_count','6'),('user_count','30'),('allow_signup','true');

/*Table structure for table `t_ticket` */

DROP TABLE IF EXISTS `t_ticket`;

CREATE TABLE `t_ticket` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` varchar(20) DEFAULT NULL COMMENT '用户主键',
  `source` int(1) DEFAULT NULL COMMENT '来源（0注册、1邀请用户）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `state` int(1) NOT NULL COMMENT '状态（0未使用、1已使用、2无效）',
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `t_ticket` */

/*Table structure for table `t_topic` */

DROP TABLE IF EXISTS `t_topic`;

CREATE TABLE `t_topic` (
  `tid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL COMMENT '发布人',
  `nid` int(10) NOT NULL COMMENT '所属节点',
  `title` varchar(50) DEFAULT '' COMMENT '帖子标题',
  `content` text COMMENT '帖子内容',
  `is_top` tinyint(2) DEFAULT '0' COMMENT '是否置顶',
  `is_essence` tinyint(2) DEFAULT '0' COMMENT '是否精华帖',
  `weight` double(10,2) DEFAULT '0.00' COMMENT '帖子权重',
  `create_time` int(10) NOT NULL COMMENT '帖子创建时间',
  `update_time` int(10) NOT NULL COMMENT '最后更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:正常 2:删除',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

/*Data for the table `t_topic` */

insert  into `t_topic`(`tid`,`uid`,`nid`,`title`,`content`,`is_top`,`is_essence`,`weight`,`create_time`,`update_time`,`status`) values (63,32,8,'什么是银行承兑汇票？','<p><strong>谁能帮我回答一下这个问题啊？万分感谢</strong></p><p><br/></p>',0,0,453.19,1479802249,1479802249,1),(64,33,48,'商票咨询','<p>一张海南航空公司开的5000万商票，手续材料都备齐，有意者可先议价</p>',0,0,453.94,1479867350,1479867350,1),(65,3,48,'小额银票','<p>十万的浦东发展银行开出四个月到期无背书，带价商谈</p>',0,0,453.95,1479867571,1479867571,1),(66,35,48,'小额贷款积分获取','<p>公司因业务需求需要50万资金贷款，积分不够，带路径详谈</p>',0,0,453.95,1479867665,1479867665,1),(67,8,7,'美国当选总统特朗普宣布将在就职首日退出TPP','<p style=\"text-indent:32px\">中新社华盛顿11月21日电 (记者 张蔚然)美国当选总统特朗普当地时间21日对外宣布就职首日即将施行的六大行政措施，包括发布关于美国退出跨太平洋伙伴关系协定(TPP)的通知。</p><p style=\"text-indent:32px\">特朗普当天发表视频讲话，面向全美发布其政策计划。他说，目前政府过渡团队正平稳高效运转，很多有能力和天分的人将被纳入新一届政府，他的执政日程将建立在“美国优先”这一核心原则基础上。</p><p style=\"text-indent:32px\">特朗普宣布了就职首日即将施行的六大行政措施。第一，在贸易问题上，他将正式发布美国关于退出TPP的通知。TPP是美国的“潜在灾难”，政府将重新谈判公正的双边贸易协定，将就业和产业带回美国。</p><p style=\"text-indent:32px\">第二，在能源问题上，他将取消关于能源生产的一些“扼杀就业”的限制措施，包括取消对清洁煤炭生产的限制，创造数百万高薪就业岗位。</p><p style=\"text-indent:32px\">第三，在监管问题上，他将制定新的规则，规定政府每制定一个新规，就必须废除两个旧规定。</p><p style=\"text-indent:32px\">第四，在国家安全问题上，他将要求国防部和美军参联会主席制定全面计划，保护美国关键基础设施免遭包括网络攻击在内的攻击。</p><p style=\"text-indent:32px\">第五，在移民问题上，他将要求劳工部调查签证项目滥用问题，签证滥用削弱了美国工人利益。</p><p style=\"text-indent:32px\">第六，在伦理改革问题上，他将禁止美国行政部门官员在离职后的5年内从事游说工作，并终身禁止行政部门官员代表外国政府游说。</p><p><span style=\"font-size:14px;font-family:宋体\">美国媒体普遍注意到，特朗普宣布的六大措施基本在外界预料之内，由总统签署行政命令即可实现，无需国会批准。在美墨边境</span><span style=\"font-size:14px;font-family:&#39;Calibri&#39;,&#39;sans-serif&#39;\">“</span><span style=\"font-size:14px;font-family:宋体\">筑墙</span><span style=\"font-size:14px;font-family:&#39;Calibri&#39;,&#39;sans-serif&#39;\">”</span><span style=\"font-size:14px;font-family:宋体\">、废除奥巴马医改法案、投入</span><span style=\"font-size:14px;font-family:&#39;Calibri&#39;,&#39;sans-serif&#39;\">1</span><span style=\"font-size:14px;font-family:宋体\">万亿美元建造基础设施等竞选期间频繁提及的议题未出现在首日行政措施中，这些复杂的议题都需要国会提供预算。</span></p><p><br/></p>',0,0,453.95,1479867711,1479867711,1),(68,40,48,'瑕疵票处理','<p>承兑行印章不清晰，求处理方法带价面议</p>',0,0,453.95,1479867943,1479867943,1);

/*Table structure for table `t_topiccount` */

DROP TABLE IF EXISTS `t_topiccount`;

CREATE TABLE `t_topiccount` (
  `tid` int(10) NOT NULL,
  `views` int(10) DEFAULT '0',
  `loves` int(10) DEFAULT '0',
  `favorites` int(10) DEFAULT '0',
  `comments` int(10) DEFAULT '0',
  `sinks` int(10) DEFAULT '0',
  `create_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_topiccount` */

insert  into `t_topiccount`(`tid`,`views`,`loves`,`favorites`,`comments`,`sinks`,`create_time`) values (63,19,1,0,2,0,1479802249),(64,1,0,0,0,0,1479867350),(65,1,0,0,0,0,1479867571),(66,1,0,0,0,0,1479867665),(67,2,0,0,0,0,1479867711),(68,2,0,0,0,0,1479867943);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `pass_word` varchar(32) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `create_time` int(10) NOT NULL COMMENT '创建时间',
  `update_time` int(10) NOT NULL COMMENT '最后一次操作时间',
  `role_id` tinyint(2) DEFAULT '5' COMMENT '5:普通用户 2:管理员 1:系统管理员',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:待激活 1:正常 2：删除',
  `member_id` int(11) DEFAULT NULL COMMENT 'APP用户主键',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`uid`,`login_name`,`pass_word`,`avatar`,`email`,`create_time`,`update_time`,`role_id`,`status`,`member_id`) values (1,'chengxiaojing','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1410944818,1410944818,5,1,NULL),(2,'miaohui','e10adc3949ba59abbe56e057f20f883e','upload/image/20161123/20161123102303239_MM4c13jMsx.jpg',NULL,1410944818,1410944818,5,1,NULL),(3,'zhangying','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1410944818,1410944818,5,1,NULL),(8,'renhuidong','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1460650525,1460650525,5,1,NULL),(10,'tangzhiwen','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1460682181,1460682181,5,1,NULL),(19,'wangkai','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1461054229,1461054229,5,1,NULL),(26,'wangkaixuan','e10adc3949ba59abbe56e057f20f883e','upload/image/20161122/20161122215958215_9XgqUhPfKN.jpg','1397009898@qq.com',1476777296,1476777296,1,1,NULL),(31,'hushaoqing','e10adc3949ba59abbe56e057f20f883e','upload/image/20161119/20161119101723673_RwQE5oDUYr.png','hushaoqing@ryfinance.com',1479521699,1479521699,1,1,NULL),(32,'wenhui','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479707628,1479707628,1,1,NULL),(33,'yuhao','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(35,'kehengcheng','e10adc3949ba59abbe56e057f20f883e','upload/image/20161122/20161122220219887_45ozRQyRRN.jpg',NULL,1479717403,1479717403,5,1,NULL),(40,'meiyanyan','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(41,'hanbin','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(42,'shanyangchun','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(43,'zhaohongshun','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(44,'hongning','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(45,'zhangyulei','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(47,'mingtian','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(48,'yongcheng','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(49,'jinjin','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(50,'zhongtai','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(51,'fengfeng','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(52,'dining','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(53,'guoji','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(54,'kunyi','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(55,'dachang','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(56,'songyi','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(57,'yongheng','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(58,'futie','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL),(59,'zhaobei','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,1479710574,1479710574,5,1,NULL);

/*Table structure for table `t_userinfo` */

DROP TABLE IF EXISTS `t_userinfo`;

CREATE TABLE `t_userinfo` (
  `uid` int(10) NOT NULL,
  `nick_name` varchar(30) DEFAULT NULL,
  `jobs` varchar(100) DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL,
  `github` varchar(20) DEFAULT NULL,
  `weibo` varchar(50) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `instructions` text,
  `company` varchar(50) DEFAULT NULL COMMENT '企业名称',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信',
  `qq` varchar(50) DEFAULT NULL COMMENT 'qq',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_userinfo` */

insert  into `t_userinfo`(`uid`,`nick_name`,`jobs`,`web_site`,`github`,`weibo`,`location`,`signature`,`instructions`,`company`,`phone`,`wechat`,`qq`,`name`) values (1,'静静','Web Dev!',NULL,NULL,NULL,'上海','个性无需签名','...',NULL,NULL,NULL,NULL,NULL),(2,'帅比杰克','网络公关',NULL,NULL,NULL,'上海','我就是帅',NULL,NULL,NULL,NULL,NULL,NULL),(3,'美少女','打酱油的',NULL,NULL,NULL,'上海','biubiubiu～',NULL,NULL,NULL,NULL,NULL,NULL),(8,'任会东',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'老唐',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'王爵',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,'杜甫','炼金师','https://www.utiexian.com',NULL,'3889126642','上海','彼此深爱，各自孤独','介绍','上海票据管家责任有限公司','13818994981','1397009898','1397009898','王大锤'),(31,'动感超人','刺侯',NULL,NULL,NULL,'上海','呵呵',NULL,'艾欧尼亚蘑菇制造厂','50965066','提莫队长',NULL,'提莫队长'),(32,'票管家社区--小慧',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,'票管家社区--小昊',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,'悦耳的拖拉机声',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'柯家四少'),(40,'票管家社区--小梅',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,'票管家社区--小彬',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,'票管家社区--小单',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,'赵洪顺',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,'洪宁',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,'张毓雷',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,'上海明田工贸有限公司',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,'勇城国际',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,'金金铜件',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,'上海中泰不锈钢有限公司',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,'泰州市峰峰金属制品有限公司',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,'上海迪宁贸易有限公司',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,'国际有色金属',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'上海坤熠金属材料有限公司',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,'大长钢贸',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,'松祎国际贸易',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,'上海永恒铁钢贸有限公司',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(58,'上海富铁港贸有限公司',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(59,'兆北钢材市场',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_userlog` */

DROP TABLE IF EXISTS `t_userlog`;

CREATE TABLE `t_userlog` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `action` varchar(100) NOT NULL,
  `content` text,
  `ip_addr` varchar(50) DEFAULT NULL,
  `create_time` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=347 DEFAULT CHARSET=utf8;

/*Data for the table `t_userlog` */

insert  into `t_userlog`(`id`,`uid`,`action`,`content`,`ip_addr`,`create_time`) values (263,32,'add_topic','<p><strong>谁能帮我回答一下这个问题啊？万分感谢</strong></p><p><br/></p>','180.166.201.178',1479802249),(264,32,'signin','wenhui','180.166.201.178',1479802388),(265,26,'signin','wangkaixuan','180.166.201.178',1479802486),(266,26,'signin','wangkaixuan','192.168.1.110',1479802525),(267,32,'signin','wenhui','180.166.201.178',1479802566),(268,26,'signin','wangkaixuan','180.166.201.178',1479802616),(269,26,'signin','wangkaixuan','180.166.201.178',1479802630),(270,26,'signin','wangkaixuan','192.168.1.110',1479803510),(271,26,'add_comment','<p><strong>您好<img src=\"http://img.baidu.com/hi/jx2/j_0046.gif\"/>我是票管家小梅很高兴为您服务，欢迎使用“票管家社区”平台咨询，关于您的问题回答如下：</strong></p><p>&nbsp;</p><p>&nbsp; &nbsp; &nbsp;<span style=\"text-decoration: underline;\"><strong>银行承兑汇票是商业汇票的一种。是由在承兑银行开立存款账户的存款人出票，向开户银行申请并经银行审查同意承兑的，保证在指定日期无条件支付确定的金额给收款人或持票人的票据。对出票人签发的商业汇票进行承兑是银行基于对出票人资信的认可而给予的信用支持！</strong></span></p><p><br/></p>','192.168.1.110',1479803665),(272,26,'add_comment','<p style=\"box-sizing: border-box; -webkit-margin-before: 1em; -webkit-margin-after: 1em; margin-top: 0px; margin-bottom: 10px; font-family: &#39;Microsoft Yahei&#39;; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"box-sizing: border-box; font-weight: 700;\">您好<img src=\"http://img.baidu.com/hi/jx2/j_0046.gif\"/>我是票管</span><span style=\"font-weight: 700;\">家小梅很高兴为您服务，欢迎使用“</span><span style=\"font-weight: 700;\"><span style=\"color: rgb(255, 0, 0);\">票管家社区</span></span><span style=\"font-weight: 700;\">”平台咨询，关于您的问题回答如下：</span></p><p style=\"box-sizing: border-box; -webkit-margin-before: 1em; -webkit-margin-after: 1em; margin-top: 0px; margin-bottom: 10px; font-family: &#39;Microsoft Yahei&#39;; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255);\">&nbsp; &nbsp; &nbsp;<span style=\"font-weight: 700; box-sizing: border-box; text-decoration: underline; font-size: 16px; color: rgb(23, 54, 93);\">银行承兑汇票是商业汇票的一种。是由在承兑银行开立存款账户的存款人出票，向开户银行申请并经银行审查同意承兑的，保证在指定日期无条件支付确定的金额给收款人或持票人的票据。对出票人签发的商业汇票进行承兑是银行基于对出票人资信的认可而给予的信用支持！</span></p><p><br/></p>','192.168.1.110',1479804003),(273,32,'signin','wenhui','180.166.201.178',1479806055),(274,2,'signin','miaohui','180.166.201.178',1479806806),(275,32,'signin','wenhui','180.166.201.178',1479807015),(276,2,'signin','miaohui','180.166.201.178',1479807029),(277,26,'signin','wangkaixuan','192.168.1.110',1479807083),(278,46,'signup','13818994981','192.168.1.110',1479807141),(279,46,'signin','13818994981','192.168.1.110',1479807141),(280,26,'signin','wangkaixuan','192.168.1.110',1479823133),(293,2,'signin','miaohui','192.168.1.110',1479865972),(314,33,'add_topic','<p>一张海南航空公司开的5000万商票，手续材料都备齐，有意者可先议价</p>','180.166.201.178',1479867350),(315,26,'signin','wangkaixuan','192.168.1.110',1479867197),(316,26,'signin','wangkaixuan','192.168.1.110',1479867212),(317,26,'signin','wangkaixuan','192.168.1.110',1479867328),(318,3,'signin','zhangying','180.166.201.178',1479867506),(319,3,'add_topic','<p>十万的浦东发展银行开出四个月到期无背书，带价商谈</p>','180.166.201.178',1479867571),(320,35,'signin','kehengcheng','180.166.201.178',1479867593),(321,8,'signin','renhuidong','180.166.201.178',1479867608),(322,35,'add_topic','<p>公司因业务需求需要50万资金贷款，积分不够，带路径详谈</p>','180.166.201.178',1479867665),(323,40,'signin','meiyanyan','180.166.201.178',1479867692),(324,26,'signin','wangkaixuan','192.168.1.110',1479867533),(325,8,'add_topic','<p style=\"text-indent:32px\">中新社华盛顿11月21日电 (记者 张蔚然)美国当选总统特朗普当地时间21日对外宣布就职首日即将施行的六大行政措施，包括发布关于美国退出跨太平洋伙伴关系协定(TPP)的通知。</p><p style=\"text-indent:32px\">特朗普当天发表视频讲话，面向全美发布其政策计划。他说，目前政府过渡团队正平稳高效运转，很多有能力和天分的人将被纳入新一届政府，他的执政日程将建立在“美国优先”这一核心原则基础上。</p><p style=\"text-indent:32px\">特朗普宣布了就职首日即将施行的六大行政措施。第一，在贸易问题上，他将正式发布美国关于退出TPP的通知。TPP是美国的“潜在灾难”，政府将重新谈判公正的双边贸易协定，将就业和产业带回美国。</p><p style=\"text-indent:32px\">第二，在能源问题上，他将取消关于能源生产的一些“扼杀就业”的限制措施，包括取消对清洁煤炭生产的限制，创造数百万高薪就业岗位。</p><p style=\"text-indent:32px\">第三，在监管问题上，他将制定新的规则，规定政府每制定一个新规，就必须废除两个旧规定。</p><p style=\"text-indent:32px\">第四，在国家安全问题上，他将要求国防部和美军参联会主席制定全面计划，保护美国关键基础设施免遭包括网络攻击在内的攻击。</p><p style=\"text-indent:32px\">第五，在移民问题上，他将要求劳工部调查签证项目滥用问题，签证滥用削弱了美国工人利益。</p><p style=\"text-indent:32px\">第六，在伦理改革问题上，他将禁止美国行政部门官员在离职后的5年内从事游说工作，并终身禁止行政部门官员代表外国政府游说。</p><p><span style=\"font-size:14px;font-family:宋体\">美国媒体普遍注意到，特朗普宣布的六大措施基本在外界预料之内，由总统签署行政命令即可实现，无需国会批准。在美墨边境</span><span style=\"font-size:14px;font-family:&#39;Calibri&#39;,&#39;sans-serif&#39;\">“</span><span style=\"font-size:14px;font-family:宋体\">筑墙</span><span style=\"font-size:14px;font-family:&#39;Calibri&#39;,&#39;sans-serif&#39;\">”</span><span style=\"font-size:14px;font-family:宋体\">、废除奥巴马医改法案、投入</span><span style=\"font-size:14px;font-family:&#39;Calibri&#39;,&#39;sans-serif&#39;\">1</span><span style=\"font-size:14px;font-family:宋体\">万亿美元建造基础设施等竞选期间频繁提及的议题未出现在首日行政措施中，这些复杂的议题都需要国会提供预算。</span></p><p><br/></p>','180.166.201.178',1479867711),(326,26,'signin','wangkaixuan','180.166.201.178',1479867839),(327,40,'add_topic','<p>承兑行印章不清晰，求处理方法带价面议</p>','180.166.201.178',1479867944),(346,26,'signin','wangkaixuan','192.168.1.110',1479869346);



/* ========以下为2018-03-15新增内容====================================================================================================== */


ALTER TABLE t_topic ADD attach_type INT(1) COMMENT '附加内容查看要求（1:登录可见 2：回复可见）';
ALTER TABLE t_topic ADD attach_content TEXT COMMENT '附加内容';


/* ========以下为2018-06-04新增内容====================================================================================================== */


ALTER TABLE t_userlog MODIFY uid INT(11) NULL COMMENT '用户主键';


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
