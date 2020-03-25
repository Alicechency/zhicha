-- detail info for suspects, original data
drop table  if exists dw_supervisorycontrol;
CREATE TABLE `dw_supervisorycontrol` (
  `序号` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `积累` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `列管` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `预警信息编号` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `原预警信息编号` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `重点人员编号` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `重点人员姓名` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `公民身份号码` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `预警级别` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `原预警级别` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `预警级别变化` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `重点人员类别标记` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `重点人员细类` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `动态信息类别` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `活动发生时间` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `活动发生地详址` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `所属社会场所` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `活动相关信息` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `市局部署时间` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `市局部署意见` longblob,
  `分局签收时间` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `分局部署人` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `分局部署时间` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `分局部署意见` longblob,
  `主责单位` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `主责单位签收时间` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `主责单位反馈时间` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `主责单位反馈目标发现状态` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `主责单位反馈采取处置措施` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `主责单位反馈处置结果` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `主责单位反馈管控措施` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `主责单位反馈处置经过描述` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心第一次上报时间` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心上报人姓名` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心最后一次上报目标发现状态` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心最后一次上报采取处置措施` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心最后一次上报处置结果` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心最后一次上报管控措施` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心最后一次上报处置经过描述` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心签收是否超时` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心部署是否超时` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `中心上报是否超时` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `主责单位签收是否超时` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `主责单位反馈是否超时` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `情报信息中心上报次数` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `吸毒查获尿检信息` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `现场查获物品信息` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `活动发生地详址经度` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `活动发生地详址纬度` varchar(255) COLLATE utf8_bin DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- related with dw_crimedata 
-- protential case relationship
drop table  if exists dim_crime_relationship;
CREATE TABLE `dim_crime_relationship` (
  `crime_id` bigint(20) DEFAULT NULL,
  `casetype` int(1) DEFAULT NULL,
  `casevalues` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `distance` float DEFAULT NULL,
  `crid` int(11) NOT NULL AUTO_INCREMENT,
  `nearcrime_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`crid`),
  KEY `index_crime_relationship` (`crime_id`)
) ENGINE=InnoDB AUTO_INCREMENT=161323 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

create index index_dim_crime_relationship on dim_crime_relationship (crime_id);

-- case abstract
drop table  if exists dim_crime_brief;
CREATE TABLE `dim_crime_brief` (
  `crime_id` bigint(11) DEFAULT NULL,
  `crime_brief` text CHARACTER SET utf8 COLLATE utf8_bin,
  `debug` decimal(27,4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- related cases for one case that happend within an hour
drop table  if exists dim_crime_brief_nearcasebyhour;
CREATE TABLE `dim_crime_brief_nearcasebyhour` (
  `crime_id` bigint(20) DEFAULT NULL,
  `ByHour` varchar(7) DEFAULT NULL,
  `SumByHourCount` decimal(42,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- related with dw_supervisorycontrol
drop table  if exists dim_suspect_base_info;
CREATE TABLE `dim_suspect_base_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `important_person_no` varchar(255) DEFAULT NULL COMMENT '重点人员编号',
  `important_person_name` varchar(255) DEFAULT NULL COMMENT '重点人员姓名',
  `identity_no` varchar(255) DEFAULT NULL COMMENT '公民身份号码',
  `important_person_detail` varchar(255) DEFAULT NULL COMMENT '重点人员细类',
  `warning_level` int(1) DEFAULT NULL COMMENT '预警级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10296 DEFAULT CHARSET=utf8;

-- abstact info of suspects
drop table  if exists dim_supervisory_detail;
CREATE TABLE `dim_supervisory_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT ,
  `important_person_no` varchar(255) DEFAULT NULL ,
  `activity_date` varchar(255) DEFAULT NULL ,
  `activity_location` varchar(255) DEFAULT NULL ,
  `social_location` varchar(255) DEFAULT NULL ,
  `activity_detail` varchar(255) DEFAULT NULL ,
  `suggestion` varchar(1000) DEFAULT NULL ,
  `activity_longtitude` double DEFAULT NULL ,
  `activity_latitude` double DEFAULT NULL ,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21091 DEFAULT CHARSET=utf8;

-- protential relationship among suspects
drop table  if exists dim_supervisory_association;
CREATE TABLE `dim_supervisory_association` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_person_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `start_person_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `start_category` int(2) NOT NULL,
  `end_person_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `end_person_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `end_category` int(2) NOT NULL,
  `frequency` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4607 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- view for activities path
drop view  if exists viw_suspect_path;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viw_suspect_path`
AS SELECT
   `a`.`重点人员编号` AS `important_person_no`,
   `a`.`活动发生地详址` AS `activity_location`,
   `a`.`活动发生地详址经度` AS `activity_longitude`,
   `a`.`活动发生地详址纬度` AS `activity_latitude`,
   `a`.`活动发生时间` AS `activity_date`
FROM `dw_supervisorycontrol` `a`;

-- view for suspects detail info
drop view  if exists viw_suspect_activity;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viw_suspect_activity`
AS SELECT
   distinct `a`.`序号` AS `序号`,
   `a`.`积累` AS `积累`,
   `a`.`列管` AS `列管`,
   `a`.`预警信息编号` AS `预警信息编号`,
   `a`.`原预警信息编号` AS `原预警信息编号`,
   `a`.`预警级别` AS `预警级别`,
   `a`.`原预警级别` AS `原预警级别`,
   `a`.`预警级别变化` AS `预警级别变化`,
   `a`.`重点人员类别标记` AS `重点人员类别标记`,
   `a`.`动态信息类别` AS `动态信息类别`,
   `a`.`活动发生时间` AS `活动发生时间`,
   `a`.`活动发生地详址` AS `活动发生地详址`,
   `a`.`活动发生地详址经度` AS `活动发生地详址经度`,
   `a`.`活动发生地详址纬度` AS `活动发生地详址纬度`,
   `a`.`所属社会场所` AS `所属社会场所`,
   `a`.`活动相关信息` AS `活动相关信息`,
   `a`.`市局部署时间` AS `市局部署时间`,
   `a`.`市局部署意见` AS `市局部署意见`,
   `a`.`分局签收时间` AS `分局签收时间`,
   `a`.`分局部署人` AS `分局部署人`,
   `a`.`分局部署时间` AS `分局部署时间`,
   `a`.`分局部署意见` AS `分局部署意见`,
   `a`.`主责单位` AS `主责单位`,
   `a`.`主责单位签收时间` AS `主责单位签收时间`,
   `a`.`主责单位反馈时间` AS `主责单位反馈时间`,
   `a`.`主责单位反馈目标发现状态` AS `主责单位反馈目标发现状态`,
   `a`.`主责单位反馈采取处置措施` AS `主责单位反馈采取处置措施`,
   `a`.`主责单位反馈处置结果` AS `主责单位反馈处置结果`,
   `a`.`主责单位反馈管控措施` AS `主责单位反馈管控措施`,
   `a`.`主责单位反馈处置经过描述` AS `主责单位反馈处置经过描述`,
   `a`.`中心第一次上报时间` AS `中心第一次上报时间`,
   `a`.`中心上报人姓名` AS `中心上报人姓名`,
   `a`.`中心最后一次上报目标发现状态` AS `中心最后一次上报目标发现状态`,
   `a`.`中心最后一次上报采取处置措施` AS `中心最后一次上报采取处置措施`,
   `a`.`中心最后一次上报处置结果` AS `中心最后一次上报处置结果`,
   `a`.`中心最后一次上报管控措施` AS `中心最后一次上报管控措施`,
   `a`.`中心最后一次上报处置经过描述` AS `中心最后一次上报处置经过描述`,
   `a`.`中心签收是否超时` AS `中心签收是否超时`,
   `a`.`中心部署是否超时` AS `中心部署是否超时`,
   `a`.`中心上报是否超时` AS `中心上报是否超时`,
   `a`.`主责单位签收是否超时` AS `主责单位签收是否超时`,
   `a`.`主责单位反馈是否超时` AS `主责单位反馈是否超时`,
   `a`.`情报信息中心上报次数` AS `情报信息中心上报次数`,
   `a`.`吸毒查获尿检信息` AS `吸毒查获尿检信息`,
   `a`.`现场查获物品信息` AS `现场查获物品信息`
FROM `dw_supervisorycontrol` `a`;

-- pridict case location info
drop table  if exists dim_crime_prediction;
CREATE TABLE `dim_crime_prediction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `category` int(3) DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1062987 DEFAULT CHARSET=utf8;

-- table for outsider
drop table  if exists dim_outsider;
CREATE TABLE `dim_outsider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `unique_users` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8;

-- table for tag
drop table  if exists dim_tag;
CREATE TABLE `dim_tag` (
  `tag_id` double DEFAULT NULL,
  `tag_name` varchar(60) DEFAULT NULL,
  `tag_class` varchar(90) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;