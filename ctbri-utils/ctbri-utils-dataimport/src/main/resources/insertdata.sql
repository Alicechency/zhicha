-- truncate tables 
truncate table dim_crime_relationship;
truncate table dim_crime_brief;
truncate table dim_crime_brief_nearcasebyhour;

-- insert dim_crime_relationship
insert into dim_crime_relationship
select DISTINCT a.`警情序号` as 'crime_id','1' as 'caseytpe',b.`标题` as 'casevalues',1/(abs(TIMESTAMPDIFF(hour,a.`报警时间`, b.`报警时间`))*st_distance(point(a.`经度`,a.`纬度`),point(b.`经度`,b.`纬度`))+1) as distince,null,b.`警情序号` as 'nearcrime_id'
from (dw_crimedata a,dw_crimedata b)
where 
st_distance(point(a.`经度`,a.`纬度`),point(b.`经度`,b.`纬度`))*111.195<1.5 and a.`警情类别3`=b.`警情类别3` and abs(TIMESTAMPDIFF(hour,a.`报警时间`, b.`报警时间`))<96 and a.`警情序号`!=b.`警情序号`;

-- create t_dw_supervisorycontrol
drop  table  if exists t_dw_supervisorycontrol;
create table t_dw_supervisorycontrol as
select b.`重点人员姓名`,
case
when b.`重点人员细类` ='钻窗入室盗窃案件人员' then'入室盗窃'
when b.`重点人员细类` ='溜门入室盗窃案件人员' then'入室盗窃'
when b.`重点人员细类` ='入室盗窃案' then'入室盗窃'
when b.`重点人员细类` ='入室盗窃保险柜案件' then'入室盗窃'
when b.`重点人员细类` ='入户抢劫案' then'入室盗窃'
when b.`重点人员细类` ='盗窃路财案' then'其他盗窃'
when b.`重点人员细类` ='强奸案' then'强奸'
when b.`重点人员细类` ='奸淫幼女案' then'强奸'
when b.`重点人员细类` ='盗窃案' then'扒窃'
when b.`重点人员细类` ='扒窃案' then'扒窃'
when b.`重点人员细类` ='故意伤害案' then'打架'
when b.`重点人员细类` ='吸毒人员' then'扰民'
when b.`重点人员细类` ='制贩毒人员' then'扰民'
when b.`重点人员细类` ='卖淫前科人员' then'扰民'
when b.`重点人员细类` ='有精神病背景重点人员' then'扰民'
when b.`重点人员细类` ='以危险方法危害公共安全案' then'扰民'
when b.`重点人员细类` ='有潜在暴力倾向精神病人' then'扰民'
when b.`重点人员细类` ='轻微滋事精神病人' then'扰民'
when b.`重点人员细类` ='以其他危险方法危害公共安全案' then'扰民'
when b.`重点人员细类` ='涉枪前科人员' then'扰民'
when b.`重点人员细类` ='容留他人吸毒案' then'扰民'
when b.`重点人员细类` ='参加黑社会性质组织案' then'扰民'
when b.`重点人员细类` ='组织、领导黑社会性质组织案' then'扰民'
when b.`重点人员细类` ='贩卖毒品案' then'扰民'
when b.`重点人员细类` ='抢劫案' then'抢劫'
when b.`重点人员细类` ='拦路抢劫案件人员' then'抢劫'
when b.`重点人员细类` ='抢夺案' then'抢劫'
when b.`重点人员细类` ='入室抢劫案件' then'抢劫'
when b.`重点人员细类` ='撬门入室盗窃案件人员' then'抢劫'
when b.`重点人员细类` ='拦路抢劫案' then'抢劫'
when b.`重点人员细类` ='非法吸收公众存款案' then'故意损坏公共财物'
when b.`重点人员细类` ='非法获取国家秘密案' then'故意损坏公共财物'
when b.`重点人员细类` ='破坏电力设备案' then'故意损坏公共财物'
when b.`重点人员细类` ='放火案' then'故意损坏公共财物'
when b.`重点人员细类` ='破坏共有电信设备案' then'故意损坏公共财物'
when b.`重点人员细类` ='盗掘古墓葬案' then'故意损坏公共财物'
when b.`重点人员细类` ='破坏交通设施案' then'故意损坏公共财物'
when b.`重点人员细类` ='破坏易燃易爆设备案' then'故意损坏公共财物'
when b.`重点人员细类` ='破坏交通工具案' then'故意损坏公共财物'
when b.`重点人员细类` ='盗掘古文化遗址案' then'故意损坏公共财物'
when b.`重点人员细类` ='破坏广播电视设备案' then'故意损坏公共财物'
when b.`重点人员细类` ='敲诈勒索案' then'敲诈勒索'
when b.`重点人员细类` ='绑架案' then'敲诈勒索'
when b.`重点人员细类` ='故意杀人案' then'杀人'
when b.`重点人员细类` ='盗窃运输物资案' then'盗窃工地'
when b.`重点人员细类` ='盗窃汽车案' then'盗窃机动车'
when b.`重点人员细类` ='抢劫出租汽车案' then'盗窃机动车'
when b.`重点人员细类` ='诈骗案' then'诈骗'
when b.`重点人员细类` ='信用卡诈骗案' then'诈骗'
when b.`重点人员细类` ='街头诈骗案件' then'诈骗'
when b.`重点人员细类` ='合同诈骗案' then'诈骗'
when b.`重点人员细类` ='伪造，变造公文，证件，印章案' then'诈骗'
when b.`重点人员细类` ='票据诈骗案' then'诈骗'
when b.`重点人员细类` ='招摇撞骗案' then'诈骗'
when b.`重点人员细类` ='买卖公文证件印章案' then'诈骗'
when b.`重点人员细类` ='贷款诈骗案' then'诈骗'
when b.`重点人员细类` ='金融诈骗案' then'诈骗'
when b.`重点人员细类` ='保险诈骗案' then'诈骗'
when b.`重点人员细类` ='集资诈骗案' then'诈骗'
when b.`重点人员细类` ='使用假币违法犯罪案件' then'诈骗'
when b.`重点人员细类` ='金融凭证诈骗案' then'诈骗'
when b.`重点人员细类` ='聚众斗殴案' then'闹事'
when b.`重点人员细类` ='治安重点上访人员' then'闹事'
when b.`重点人员细类` ='运送他人偷越国（边）境案' then'闹事'
when b.`重点人员细类` ='拐卖妇女儿童案' then'闹事'
when b.`重点人员细类` ='非法持有枪支弹药案' then'闹事'
when b.`重点人员细类` ='组织他人偷越国（边）境案' then'闹事'
when b.`重点人员细类` ='非法制造枪支弹药案' then'闹事'
when b.`重点人员细类` ='非法买卖枪支弹药案' then'闹事'
when b.`重点人员细类` ='聚众扰乱社会秩序案' then'闹事'
when b.`重点人员细类` ='非法买卖爆炸物案' then'闹事'
when b.`重点人员细类` ='运输毒品案' then'闹事'
when b.`重点人员细类` ='非法持有毒品案' then'闹事'
when b.`重点人员细类` ='非法制造爆炸物案' then'闹事'
when b.`重点人员细类` ='窝藏，转移，隐瞒，毒品毒赃案' then'闹事'
when b.`重点人员细类` ='非法私藏枪支弹药案' then'闹事'
when b.`重点人员细类` ='聚众扰乱公共场所秩序案' then'闹事'
when b.`重点人员细类` ='聚众扰乱交通秩序案' then'闹事'
when b.`重点人员细类` ='非法买卖制毒物品案' then'闹事'
when b.`重点人员细类` ='非法运输爆炸物案' then'闹事'
when b.`重点人员细类` ='非法拘禁案' then'骚扰'
when b.`重点人员细类` ='强制猥亵，侮辱妇女案' then'骚扰'
when b.`重点人员细类` ='猥亵儿童案' then'骚扰'
when b.`重点人员细类` ='侵犯人身权利案' then'骚扰'
else '' end as `重点人员细类`,b.`活动发生时间`,b.`活动发生地详址经度`,b.`活动发生地详址纬度`
from dw_supervisorycontrol b;

-- insert dim_crime_relationship
insert into dim_crime_relationship
select distinct a.`警情序号`as 'crime_id','2' as 'caseytpe', c.`重点人员姓名`as 'casevalues',1/(st_distance(point(c.`活动发生地详址经度`,c.`活动发生地详址纬度`),point(a.`经度`,a.`纬度`))*abs(TIMESTAMPDIFF(hour,a.`报警时间`,c.`活动发生时间`))+1) as 'distince',null,0
from dw_crimedata a
join t_dw_supervisorycontrol c on 
abs(TIMESTAMPDIFF(hour,a.`报警时间`,c.`活动发生时间`))<48 and 
c.`重点人员细类`=a.`警情类别3`  
and st_distance(point(c.`活动发生地详址经度`,c.`活动发生地详址纬度`),point(a.`经度`,a.`纬度`))*111.195<5
where c.`活动发生地详址经度` is not null and a.`经度` is not null;

-- insert dim_crime_relationship
insert into dim_crime_relationship
select distinct a.`警情序号`as 'crime_id',3 as casetype,a.`事主` as casevalues, 1 as distince,null,0
from dw_crimedata a;

-- case abstract generated from dw_crimedata, statistical methods
-- insert into dim_crime_brief
insert into dim_crime_brief
select a.`警情序号` crime_id, concat(
a.`报警时间`,'发生在',a.`点位`,
case when b.SimilarCase>0 then concat('附近1.5公里范围，96小时内，共发现类似案件',b.SimilarCase,'起，占该区域总案件量的',case when b.SimilarCase*100/c.CateNums>100 then 100 else b.SimilarCase*100/c.CateNums end,'%，其主要案件多发时段为：
')
else'' end) crime_brief,b.SimilarCase*100/c.CateNums debug
from dw_crimedata a
left join (
select `crime_id`,count(*) as SimilarCase
from dim_crime_relationship
group by `crime_id`
) b on a.`警情序号`=b.`crime_id` 
left join (
select `警情类别3`,`出警派出所`,count(*) CateNums
from dw_crimedata
group by `警情类别3`,`出警派出所`
) c on c.`警情类别3`=a.`警情类别3` and c.`出警派出所`=a.`出警派出所`
;

-- find related cases based-on time
insert into dim_crime_brief_nearcasebyhour
select e.`crime_id`,d.ByHour,sum(ByHourCount) SumByHourCount
from
(
select `警情序号` ,date_format(`报警时间`,'%H') ByHour,count(*) ByHourCount
from dw_crimedata
group by `警情序号`,date_format(`报警时间`,'%H')
) d 
join dim_crime_relationship e on e.`nearcrime_id`=d.`警情序号`
group by e.`crime_id`,d.ByHour;


--  -------------------------------------------------------------------------------
--  -------------------------------------------------------------------------------
--  -------------------------------------------------------------------------------
-- Part2. supervisory control
-- Number all suspects categories and generate base info table
-- Tables in this session: dim_supervisory_association, dim_suspect_base_info
-- Naming rule: dw: data warehouse; dim: dimension; t: temp; viw: view; fun: function
--  -------------------------------------------------------------------------------
--  -------------------------------------------------------------------------------
--  -------------------------------------------------------------------------------

-- truncate tables
truncate table dim_supervisory_association;
truncate table dim_suspect_base_info;

-- insert dim_supervisory_association
insert into dim_supervisory_association
select null,a.`重点人员编号` start_person_id,a.`重点人员姓名` start_person_name, case
when a.`重点人员细类` like'%盗窃案%' then'1'
when a.`重点人员细类` like'%文娱场所从业人员%' then'2'
when a.`重点人员细类` like'%故意伤害案%' then'3'
when a.`重点人员细类` like'%吸毒人员%' then'4'
when a.`重点人员细类` like'%赌博前科人员%' then'5'
when a.`重点人员细类` like'%抢劫案%' then'6'
when a.`重点人员细类` like'%诈骗案%' then'7'
when a.`重点人员细类` like'%钻窗入室盗窃案件人员%' then'8'
when a.`重点人员细类` like'%拦路抢劫案件人员%' then'9'
when a.`重点人员细类` like'%聚众斗殴案%' then'10'
when a.`重点人员细类` like'%强奸案%' then'11'
when a.`重点人员细类` like'%制贩毒人员%' then'12'
when a.`重点人员细类` like'%敲诈勒索案%' then'13'
when a.`重点人员细类` like'%非法拘禁案%' then'14'
when a.`重点人员细类` like'%卖淫前科人员%' then'15'
when a.`重点人员细类` like'%有精神病背景重点人员%' then'16'
when a.`重点人员细类` like'%信用卡诈骗案%' then'17'
when a.`重点人员细类` like'%溜门入室盗窃案件人员%' then'18'
when a.`重点人员细类` like'%抢夺案%' then'19'
when a.`重点人员细类` like'%街头诈骗案件%' then'20'
when a.`重点人员细类` like'%合同诈骗案%' then'21'
when a.`重点人员细类` like'%入室盗窃案%' then'22'
when a.`重点人员细类` like'%以危险方法危害公共安全案%' then'23'
when a.`重点人员细类` like'%伪造，变造公文，证件，印章案%' then'24'
when a.`重点人员细类` like'%肇事肇祸精神病人%' then'25'
when a.`重点人员细类` like'%入室抢劫案件%' then'26'
when a.`重点人员细类` like'%有潜在暴力倾向精神病人%' then'27'
when a.`重点人员细类` like'%绑架案%' then'28'
when a.`重点人员细类` like'%强制猥亵，侮辱妇女案%' then'29'
when a.`重点人员细类` like'%撬门入室盗窃案件人员%' then'30'
when a.`重点人员细类` like'%在逃人员%' then'31'
when a.`重点人员细类` like'%治安重点上访人员%' then'32'
when a.`重点人员细类` like'%非法吸收公众存款案%' then'33'
when a.`重点人员细类` like'%轻微滋事精神病人%' then'34'
when a.`重点人员细类` like'%以其他危险方法危害公共安全案%' then'35'
when a.`重点人员细类` like'%票据诈骗案%' then'36'
when a.`重点人员细类` like'%涉枪前科人员%' then'37'
when a.`重点人员细类` like'%招摇撞骗案%' then'38'
when a.`重点人员细类` like'%买卖公文证件印章案%' then'39'
when a.`重点人员细类` like'%贷款诈骗案%' then'40'
when a.`重点人员细类` like'%故意杀人案%' then'41'
when a.`重点人员细类` like'%非法获取国家秘密案%' then'42'
when a.`重点人员细类` like'%破坏电力设备案%' then'43'
when a.`重点人员细类` like'%运送他人偷越国（边）境案%' then'44'
when a.`重点人员细类` like'%拐卖妇女儿童案%' then'45'
when a.`重点人员细类` like'%非法持有枪支弹药案%' then'46'
when a.`重点人员细类` like'%组织他人偷越国（边）境案%' then'47'
when a.`重点人员细类` like'%容留他人吸毒案%' then'48'
when a.`重点人员细类` like'%拦路抢劫案%' then'49'
when a.`重点人员细类` like'%猥亵儿童案%' then'50'
when a.`重点人员细类` like'%金融诈骗案%' then'51'
when a.`重点人员细类` like'%50201060142%' then'52'
when a.`重点人员细类` like'%参加黑社会性质组织案%' then'53'
when a.`重点人员细类` like'%放火案%' then'54'
when a.`重点人员细类` like'%组织、领导黑社会性质组织案%' then'55'
when a.`重点人员细类` like'%贩卖毒品案%' then'56'
when a.`重点人员细类` like'%破坏共有电信设备案%' then'57'
when a.`重点人员细类` like'%保险诈骗案%' then'58'
when a.`重点人员细类` like'%非法制造枪支弹药案%' then'59'
when a.`重点人员细类` like'%盗掘古墓葬案%' then'60'
when a.`重点人员细类` like'%非法买卖枪支弹药案%' then'61'
when a.`重点人员细类` like'%聚众扰乱社会秩序案%' then'62'
when a.`重点人员细类` like'%扒窃案%' then'63'
when a.`重点人员细类` like'%集资诈骗案%' then'64'
when a.`重点人员细类` like'%非法买卖爆炸物案%' then'65'
when a.`重点人员细类` like'%运输毒品案%' then'66'
when a.`重点人员细类` like'%非法持有毒品案%' then'67'
when a.`重点人员细类` like'%偷越国（边）境案%' then'68'
when a.`重点人员细类` like'%盗窃路财案%' then'69'
when a.`重点人员细类` like'%盗窃汽车案%' then'70'
when a.`重点人员细类` like'%非法制造爆炸物案%' then'71'
when a.`重点人员细类` like'%窝藏，转移，隐瞒，毒品毒赃案%' then'72'
when a.`重点人员细类` like'%使用假币违法犯罪案件%' then'73'
when a.`重点人员细类` like'%非法私藏枪支弹药案%' then'74'
when a.`重点人员细类` like'%侵犯人身权利案%' then'75'
when a.`重点人员细类` like'%金融凭证诈骗案%' then'76'
when a.`重点人员细类` like'%破坏交通设施案%' then'77'
when a.`重点人员细类` like'%入室盗窃保险柜案件%' then'78'
when a.`重点人员细类` like'%擅自发行股票、公司企业债券案%' then'79'
when a.`重点人员细类` like'%50301020600%' then'80'
when a.`重点人员细类` like'%入户抢劫案%' then'81'
when a.`重点人员细类` like'%聚众扰乱公共场所秩序案%' then'82'
when a.`重点人员细类` like'%聚众扰乱交通秩序案%' then'83'
when a.`重点人员细类` like'%盗窃运输物资案%' then'84'
when a.`重点人员细类` like'%非法买卖制毒物品案%' then'85'
when a.`重点人员细类` like'%非法运输爆炸物案%' then'86'
when a.`重点人员细类` like'%奸淫幼女案%' then'87'
when a.`重点人员细类` like'%破坏易燃易爆设备案%' then'88'
when a.`重点人员细类` like'%破坏交通工具案%' then'89'
when a.`重点人员细类` like'%出售假币案%' then'90'
when a.`重点人员细类` like'%非法储存爆炸物案%' then'91'
when a.`重点人员细类` like'%传授犯罪方法案%' then'92'
when a.`重点人员细类` like'%盗掘古文化遗址案%' then'93'
when a.`重点人员细类` like'%抢劫出租汽车案%' then'94'
when a.`重点人员细类` like'%破坏广播电视设备案%' then'95'
else 0 end start_category,b.`重点人员编号` end_person_id,b.`重点人员姓名` end_person_name, case
when b.`重点人员细类` like'%盗窃案%' then'1'
when b.`重点人员细类` like'%文娱场所从业人员%' then'2'
when b.`重点人员细类` like'%故意伤害案%' then'3'
when b.`重点人员细类` like'%吸毒人员%' then'4'
when b.`重点人员细类` like'%赌博前科人员%' then'5'
when b.`重点人员细类` like'%抢劫案%' then'6'
when b.`重点人员细类` like'%诈骗案%' then'7'
when b.`重点人员细类` like'%钻窗入室盗窃案件人员%' then'8'
when b.`重点人员细类` like'%拦路抢劫案件人员%' then'9'
when b.`重点人员细类` like'%聚众斗殴案%' then'10'
when b.`重点人员细类` like'%强奸案%' then'11'
when b.`重点人员细类` like'%制贩毒人员%' then'12'
when b.`重点人员细类` like'%敲诈勒索案%' then'13'
when b.`重点人员细类` like'%非法拘禁案%' then'14'
when b.`重点人员细类` like'%卖淫前科人员%' then'15'
when b.`重点人员细类` like'%有精神病背景重点人员%' then'16'
when b.`重点人员细类` like'%信用卡诈骗案%' then'17'
when b.`重点人员细类` like'%溜门入室盗窃案件人员%' then'18'
when b.`重点人员细类` like'%抢夺案%' then'19'
when b.`重点人员细类` like'%街头诈骗案件%' then'20'
when b.`重点人员细类` like'%合同诈骗案%' then'21'
when b.`重点人员细类` like'%入室盗窃案%' then'22'
when b.`重点人员细类` like'%以危险方法危害公共安全案%' then'23'
when b.`重点人员细类` like'%伪造，变造公文，证件，印章案%' then'24'
when b.`重点人员细类` like'%肇事肇祸精神病人%' then'25'
when b.`重点人员细类` like'%入室抢劫案件%' then'26'
when b.`重点人员细类` like'%有潜在暴力倾向精神病人%' then'27'
when b.`重点人员细类` like'%绑架案%' then'28'
when b.`重点人员细类` like'%强制猥亵，侮辱妇女案%' then'29'
when b.`重点人员细类` like'%撬门入室盗窃案件人员%' then'30'
when b.`重点人员细类` like'%在逃人员%' then'31'
when b.`重点人员细类` like'%治安重点上访人员%' then'32'
when b.`重点人员细类` like'%非法吸收公众存款案%' then'33'
when b.`重点人员细类` like'%轻微滋事精神病人%' then'34'
when b.`重点人员细类` like'%以其他危险方法危害公共安全案%' then'35'
when b.`重点人员细类` like'%票据诈骗案%' then'36'
when b.`重点人员细类` like'%涉枪前科人员%' then'37'
when b.`重点人员细类` like'%招摇撞骗案%' then'38'
when b.`重点人员细类` like'%买卖公文证件印章案%' then'39'
when b.`重点人员细类` like'%贷款诈骗案%' then'40'
when b.`重点人员细类` like'%故意杀人案%' then'41'
when b.`重点人员细类` like'%非法获取国家秘密案%' then'42'
when b.`重点人员细类` like'%破坏电力设备案%' then'43'
when b.`重点人员细类` like'%运送他人偷越国（边）境案%' then'44'
when b.`重点人员细类` like'%拐卖妇女儿童案%' then'45'
when b.`重点人员细类` like'%非法持有枪支弹药案%' then'46'
when b.`重点人员细类` like'%组织他人偷越国（边）境案%' then'47'
when b.`重点人员细类` like'%容留他人吸毒案%' then'48'
when b.`重点人员细类` like'%拦路抢劫案%' then'49'
when b.`重点人员细类` like'%猥亵儿童案%' then'50'
when b.`重点人员细类` like'%金融诈骗案%' then'51'
when b.`重点人员细类` like'%50201060142%' then'52'
when b.`重点人员细类` like'%参加黑社会性质组织案%' then'53'
when b.`重点人员细类` like'%放火案%' then'54'
when b.`重点人员细类` like'%组织、领导黑社会性质组织案%' then'55'
when b.`重点人员细类` like'%贩卖毒品案%' then'56'
when b.`重点人员细类` like'%破坏共有电信设备案%' then'57'
when b.`重点人员细类` like'%保险诈骗案%' then'58'
when b.`重点人员细类` like'%非法制造枪支弹药案%' then'59'
when b.`重点人员细类` like'%盗掘古墓葬案%' then'60'
when b.`重点人员细类` like'%非法买卖枪支弹药案%' then'61'
when b.`重点人员细类` like'%聚众扰乱社会秩序案%' then'62'
when b.`重点人员细类` like'%扒窃案%' then'63'
when b.`重点人员细类` like'%集资诈骗案%' then'64'
when b.`重点人员细类` like'%非法买卖爆炸物案%' then'65'
when b.`重点人员细类` like'%运输毒品案%' then'66'
when b.`重点人员细类` like'%非法持有毒品案%' then'67'
when b.`重点人员细类` like'%偷越国（边）境案%' then'68'
when b.`重点人员细类` like'%盗窃路财案%' then'69'
when b.`重点人员细类` like'%盗窃汽车案%' then'70'
when b.`重点人员细类` like'%非法制造爆炸物案%' then'71'
when b.`重点人员细类` like'%窝藏，转移，隐瞒，毒品毒赃案%' then'72'
when b.`重点人员细类` like'%使用假币违法犯罪案件%' then'73'
when b.`重点人员细类` like'%非法私藏枪支弹药案%' then'74'
when b.`重点人员细类` like'%侵犯人身权利案%' then'75'
when b.`重点人员细类` like'%金融凭证诈骗案%' then'76'
when b.`重点人员细类` like'%破坏交通设施案%' then'77'
when b.`重点人员细类` like'%入室盗窃保险柜案件%' then'78'
when b.`重点人员细类` like'%擅自发行股票、公司企业债券案%' then'79'
when b.`重点人员细类` like'%50301020600%' then'80'
when b.`重点人员细类` like'%入户抢劫案%' then'81'
when b.`重点人员细类` like'%聚众扰乱公共场所秩序案%' then'82'
when b.`重点人员细类` like'%聚众扰乱交通秩序案%' then'83'
when b.`重点人员细类` like'%盗窃运输物资案%' then'84'
when b.`重点人员细类` like'%非法买卖制毒物品案%' then'85'
when b.`重点人员细类` like'%非法运输爆炸物案%' then'86'
when b.`重点人员细类` like'%奸淫幼女案%' then'87'
when b.`重点人员细类` like'%破坏易燃易爆设备案%' then'88'
when b.`重点人员细类` like'%破坏交通工具案%' then'89'
when b.`重点人员细类` like'%出售假币案%' then'90'
when b.`重点人员细类` like'%非法储存爆炸物案%' then'91'
when b.`重点人员细类` like'%传授犯罪方法案%' then'92'
when b.`重点人员细类` like'%盗掘古文化遗址案%' then'93'
when b.`重点人员细类` like'%抢劫出租汽车案%' then'94'
when b.`重点人员细类` like'%破坏广播电视设备案%' then'95'
else 0 end end_category,count(*) frequency
from dw_supervisorycontrol a
join dw_supervisorycontrol b on a.活动发生地详址=b.活动发生地详址 and 
DATEDIFF(a.活动发生时间,b.活动发生时间)=1 and a.`重点人员编号`!=b.`重点人员编号`
group by a.`重点人员编号` ,a.`重点人员姓名` ,case
when a.`重点人员细类` like'%盗窃案%' then'1'
when a.`重点人员细类` like'%文娱场所从业人员%' then'2'
when a.`重点人员细类` like'%故意伤害案%' then'3'
when a.`重点人员细类` like'%吸毒人员%' then'4'
when a.`重点人员细类` like'%赌博前科人员%' then'5'
when a.`重点人员细类` like'%抢劫案%' then'6'
when a.`重点人员细类` like'%诈骗案%' then'7'
when a.`重点人员细类` like'%钻窗入室盗窃案件人员%' then'8'
when a.`重点人员细类` like'%拦路抢劫案件人员%' then'9'
when a.`重点人员细类` like'%聚众斗殴案%' then'10'
when a.`重点人员细类` like'%强奸案%' then'11'
when a.`重点人员细类` like'%制贩毒人员%' then'12'
when a.`重点人员细类` like'%敲诈勒索案%' then'13'
when a.`重点人员细类` like'%非法拘禁案%' then'14'
when a.`重点人员细类` like'%卖淫前科人员%' then'15'
when a.`重点人员细类` like'%有精神病背景重点人员%' then'16'
when a.`重点人员细类` like'%信用卡诈骗案%' then'17'
when a.`重点人员细类` like'%溜门入室盗窃案件人员%' then'18'
when a.`重点人员细类` like'%抢夺案%' then'19'
when a.`重点人员细类` like'%街头诈骗案件%' then'20'
when a.`重点人员细类` like'%合同诈骗案%' then'21'
when a.`重点人员细类` like'%入室盗窃案%' then'22'
when a.`重点人员细类` like'%以危险方法危害公共安全案%' then'23'
when a.`重点人员细类` like'%伪造，变造公文，证件，印章案%' then'24'
when a.`重点人员细类` like'%肇事肇祸精神病人%' then'25'
when a.`重点人员细类` like'%入室抢劫案件%' then'26'
when a.`重点人员细类` like'%有潜在暴力倾向精神病人%' then'27'
when a.`重点人员细类` like'%绑架案%' then'28'
when a.`重点人员细类` like'%强制猥亵，侮辱妇女案%' then'29'
when a.`重点人员细类` like'%撬门入室盗窃案件人员%' then'30'
when a.`重点人员细类` like'%在逃人员%' then'31'
when a.`重点人员细类` like'%治安重点上访人员%' then'32'
when a.`重点人员细类` like'%非法吸收公众存款案%' then'33'
when a.`重点人员细类` like'%轻微滋事精神病人%' then'34'
when a.`重点人员细类` like'%以其他危险方法危害公共安全案%' then'35'
when a.`重点人员细类` like'%票据诈骗案%' then'36'
when a.`重点人员细类` like'%涉枪前科人员%' then'37'
when a.`重点人员细类` like'%招摇撞骗案%' then'38'
when a.`重点人员细类` like'%买卖公文证件印章案%' then'39'
when a.`重点人员细类` like'%贷款诈骗案%' then'40'
when a.`重点人员细类` like'%故意杀人案%' then'41'
when a.`重点人员细类` like'%非法获取国家秘密案%' then'42'
when a.`重点人员细类` like'%破坏电力设备案%' then'43'
when a.`重点人员细类` like'%运送他人偷越国（边）境案%' then'44'
when a.`重点人员细类` like'%拐卖妇女儿童案%' then'45'
when a.`重点人员细类` like'%非法持有枪支弹药案%' then'46'
when a.`重点人员细类` like'%组织他人偷越国（边）境案%' then'47'
when a.`重点人员细类` like'%容留他人吸毒案%' then'48'
when a.`重点人员细类` like'%拦路抢劫案%' then'49'
when a.`重点人员细类` like'%猥亵儿童案%' then'50'
when a.`重点人员细类` like'%金融诈骗案%' then'51'
when a.`重点人员细类` like'%50201060142%' then'52'
when a.`重点人员细类` like'%参加黑社会性质组织案%' then'53'
when a.`重点人员细类` like'%放火案%' then'54'
when a.`重点人员细类` like'%组织、领导黑社会性质组织案%' then'55'
when a.`重点人员细类` like'%贩卖毒品案%' then'56'
when a.`重点人员细类` like'%破坏共有电信设备案%' then'57'
when a.`重点人员细类` like'%保险诈骗案%' then'58'
when a.`重点人员细类` like'%非法制造枪支弹药案%' then'59'
when a.`重点人员细类` like'%盗掘古墓葬案%' then'60'
when a.`重点人员细类` like'%非法买卖枪支弹药案%' then'61'
when a.`重点人员细类` like'%聚众扰乱社会秩序案%' then'62'
when a.`重点人员细类` like'%扒窃案%' then'63'
when a.`重点人员细类` like'%集资诈骗案%' then'64'
when a.`重点人员细类` like'%非法买卖爆炸物案%' then'65'
when a.`重点人员细类` like'%运输毒品案%' then'66'
when a.`重点人员细类` like'%非法持有毒品案%' then'67'
when a.`重点人员细类` like'%偷越国（边）境案%' then'68'
when a.`重点人员细类` like'%盗窃路财案%' then'69'
when a.`重点人员细类` like'%盗窃汽车案%' then'70'
when a.`重点人员细类` like'%非法制造爆炸物案%' then'71'
when a.`重点人员细类` like'%窝藏，转移，隐瞒，毒品毒赃案%' then'72'
when a.`重点人员细类` like'%使用假币违法犯罪案件%' then'73'
when a.`重点人员细类` like'%非法私藏枪支弹药案%' then'74'
when a.`重点人员细类` like'%侵犯人身权利案%' then'75'
when a.`重点人员细类` like'%金融凭证诈骗案%' then'76'
when a.`重点人员细类` like'%破坏交通设施案%' then'77'
when a.`重点人员细类` like'%入室盗窃保险柜案件%' then'78'
when a.`重点人员细类` like'%擅自发行股票、公司企业债券案%' then'79'
when a.`重点人员细类` like'%50301020600%' then'80'
when a.`重点人员细类` like'%入户抢劫案%' then'81'
when a.`重点人员细类` like'%聚众扰乱公共场所秩序案%' then'82'
when a.`重点人员细类` like'%聚众扰乱交通秩序案%' then'83'
when a.`重点人员细类` like'%盗窃运输物资案%' then'84'
when a.`重点人员细类` like'%非法买卖制毒物品案%' then'85'
when a.`重点人员细类` like'%非法运输爆炸物案%' then'86'
when a.`重点人员细类` like'%奸淫幼女案%' then'87'
when a.`重点人员细类` like'%破坏易燃易爆设备案%' then'88'
when a.`重点人员细类` like'%破坏交通工具案%' then'89'
when a.`重点人员细类` like'%出售假币案%' then'90'
when a.`重点人员细类` like'%非法储存爆炸物案%' then'91'
when a.`重点人员细类` like'%传授犯罪方法案%' then'92'
when a.`重点人员细类` like'%盗掘古文化遗址案%' then'93'
when a.`重点人员细类` like'%抢劫出租汽车案%' then'94'
when a.`重点人员细类` like'%破坏广播电视设备案%' then'95'
else 0 end,b.`重点人员编号` ,b.`重点人员姓名` ,case
when b.`重点人员细类` like'%盗窃案%' then'1'
when b.`重点人员细类` like'%文娱场所从业人员%' then'2'
when b.`重点人员细类` like'%故意伤害案%' then'3'
when b.`重点人员细类` like'%吸毒人员%' then'4'
when b.`重点人员细类` like'%赌博前科人员%' then'5'
when b.`重点人员细类` like'%抢劫案%' then'6'
when b.`重点人员细类` like'%诈骗案%' then'7'
when b.`重点人员细类` like'%钻窗入室盗窃案件人员%' then'8'
when b.`重点人员细类` like'%拦路抢劫案件人员%' then'9'
when b.`重点人员细类` like'%聚众斗殴案%' then'10'
when b.`重点人员细类` like'%强奸案%' then'11'
when b.`重点人员细类` like'%制贩毒人员%' then'12'
when b.`重点人员细类` like'%敲诈勒索案%' then'13'
when b.`重点人员细类` like'%非法拘禁案%' then'14'
when b.`重点人员细类` like'%卖淫前科人员%' then'15'
when b.`重点人员细类` like'%有精神病背景重点人员%' then'16'
when b.`重点人员细类` like'%信用卡诈骗案%' then'17'
when b.`重点人员细类` like'%溜门入室盗窃案件人员%' then'18'
when b.`重点人员细类` like'%抢夺案%' then'19'
when b.`重点人员细类` like'%街头诈骗案件%' then'20'
when b.`重点人员细类` like'%合同诈骗案%' then'21'
when b.`重点人员细类` like'%入室盗窃案%' then'22'
when b.`重点人员细类` like'%以危险方法危害公共安全案%' then'23'
when b.`重点人员细类` like'%伪造，变造公文，证件，印章案%' then'24'
when b.`重点人员细类` like'%肇事肇祸精神病人%' then'25'
when b.`重点人员细类` like'%入室抢劫案件%' then'26'
when b.`重点人员细类` like'%有潜在暴力倾向精神病人%' then'27'
when b.`重点人员细类` like'%绑架案%' then'28'
when b.`重点人员细类` like'%强制猥亵，侮辱妇女案%' then'29'
when b.`重点人员细类` like'%撬门入室盗窃案件人员%' then'30'
when b.`重点人员细类` like'%在逃人员%' then'31'
when b.`重点人员细类` like'%治安重点上访人员%' then'32'
when b.`重点人员细类` like'%非法吸收公众存款案%' then'33'
when b.`重点人员细类` like'%轻微滋事精神病人%' then'34'
when b.`重点人员细类` like'%以其他危险方法危害公共安全案%' then'35'
when b.`重点人员细类` like'%票据诈骗案%' then'36'
when b.`重点人员细类` like'%涉枪前科人员%' then'37'
when b.`重点人员细类` like'%招摇撞骗案%' then'38'
when b.`重点人员细类` like'%买卖公文证件印章案%' then'39'
when b.`重点人员细类` like'%贷款诈骗案%' then'40'
when b.`重点人员细类` like'%故意杀人案%' then'41'
when b.`重点人员细类` like'%非法获取国家秘密案%' then'42'
when b.`重点人员细类` like'%破坏电力设备案%' then'43'
when b.`重点人员细类` like'%运送他人偷越国（边）境案%' then'44'
when b.`重点人员细类` like'%拐卖妇女儿童案%' then'45'
when b.`重点人员细类` like'%非法持有枪支弹药案%' then'46'
when b.`重点人员细类` like'%组织他人偷越国（边）境案%' then'47'
when b.`重点人员细类` like'%容留他人吸毒案%' then'48'
when b.`重点人员细类` like'%拦路抢劫案%' then'49'
when b.`重点人员细类` like'%猥亵儿童案%' then'50'
when b.`重点人员细类` like'%金融诈骗案%' then'51'
when b.`重点人员细类` like'%50201060142%' then'52'
when b.`重点人员细类` like'%参加黑社会性质组织案%' then'53'
when b.`重点人员细类` like'%放火案%' then'54'
when b.`重点人员细类` like'%组织、领导黑社会性质组织案%' then'55'
when b.`重点人员细类` like'%贩卖毒品案%' then'56'
when b.`重点人员细类` like'%破坏共有电信设备案%' then'57'
when b.`重点人员细类` like'%保险诈骗案%' then'58'
when b.`重点人员细类` like'%非法制造枪支弹药案%' then'59'
when b.`重点人员细类` like'%盗掘古墓葬案%' then'60'
when b.`重点人员细类` like'%非法买卖枪支弹药案%' then'61'
when b.`重点人员细类` like'%聚众扰乱社会秩序案%' then'62'
when b.`重点人员细类` like'%扒窃案%' then'63'
when b.`重点人员细类` like'%集资诈骗案%' then'64'
when b.`重点人员细类` like'%非法买卖爆炸物案%' then'65'
when b.`重点人员细类` like'%运输毒品案%' then'66'
when b.`重点人员细类` like'%非法持有毒品案%' then'67'
when b.`重点人员细类` like'%偷越国（边）境案%' then'68'
when b.`重点人员细类` like'%盗窃路财案%' then'69'
when b.`重点人员细类` like'%盗窃汽车案%' then'70'
when b.`重点人员细类` like'%非法制造爆炸物案%' then'71'
when b.`重点人员细类` like'%窝藏，转移，隐瞒，毒品毒赃案%' then'72'
when b.`重点人员细类` like'%使用假币违法犯罪案件%' then'73'
when b.`重点人员细类` like'%非法私藏枪支弹药案%' then'74'
when b.`重点人员细类` like'%侵犯人身权利案%' then'75'
when b.`重点人员细类` like'%金融凭证诈骗案%' then'76'
when b.`重点人员细类` like'%破坏交通设施案%' then'77'
when b.`重点人员细类` like'%入室盗窃保险柜案件%' then'78'
when b.`重点人员细类` like'%擅自发行股票、公司企业债券案%' then'79'
when b.`重点人员细类` like'%50301020600%' then'80'
when b.`重点人员细类` like'%入户抢劫案%' then'81'
when b.`重点人员细类` like'%聚众扰乱公共场所秩序案%' then'82'
when b.`重点人员细类` like'%聚众扰乱交通秩序案%' then'83'
when b.`重点人员细类` like'%盗窃运输物资案%' then'84'
when b.`重点人员细类` like'%非法买卖制毒物品案%' then'85'
when b.`重点人员细类` like'%非法运输爆炸物案%' then'86'
when b.`重点人员细类` like'%奸淫幼女案%' then'87'
when b.`重点人员细类` like'%破坏易燃易爆设备案%' then'88'
when b.`重点人员细类` like'%破坏交通工具案%' then'89'
when b.`重点人员细类` like'%出售假币案%' then'90'
when b.`重点人员细类` like'%非法储存爆炸物案%' then'91'
when b.`重点人员细类` like'%传授犯罪方法案%' then'92'
when b.`重点人员细类` like'%盗掘古文化遗址案%' then'93'
when b.`重点人员细类` like'%抢劫出租汽车案%' then'94'
when b.`重点人员细类` like'%破坏广播电视设备案%' then'95'
else 0 end
having count(*)>1;

-- create t_dim_suspect_base_info
drop table if EXISTS t_dim_suspect_base_info;
create table t_dim_suspect_base_info as
(select `a`.`重点人员编号` AS `重点人员编号`,`a`.`重点人员姓名` AS `重点人员姓名`,
  `a`.`公民身份号码` AS `公民身份号码`,`a`.`重点人员细类` AS `重点人员细类`,
  `a`.`预警级别` AS `预警级别`,`a`.`活动发生时间` AS `活动发生时间` 
from `dw_supervisorycontrol` `a` 
group by `a`.`重点人员编号`,`a`.`重点人员姓名`,`a`.`公民身份号码`,`a`.`重点人员细类`,
`a`.`预警级别`,`a`.`活动发生时间` order by `a`.`活动发生时间` desc);

-- insert dim_suspect_base_info
insert into dim_suspect_base_info
select distinct null,a.`重点人员编号` as important_person_no, a.`重点人员姓名` as important_person_name,a.`公民身份号码` identity_no,max(a.`重点人员细类`) as important_person_detail,min(
case when a.`预警级别`='红色' then 1
 when a.`预警级别`='橙色' then 2
 when a.`预警级别`='黄色' then 3
 when a.`预警级别`='蓝色' then 4
else 0 end ) as warning_level
from t_dim_suspect_base_info a
-- left join base_info_cache b on a.`重点人员编号`=b.`重点人员编号` and a.`活动发生时间` >= b.`活动发生时间`
group by a.`重点人员编号` , a.`重点人员姓名` ,a.`公民身份号码` ;

-- insert dim_supervisory_detail
insert into dim_supervisory_detail
select distinct null,`a`.`重点人员编号` important_person_no,`a`.`活动发生时间` activity_date,`a`.`活动发生地详址` activity_location,`a`.`所属社会场所` social_location,`a`.`活动相关信息` activity_detail,`a`.`市局部署意见` suggestion,`a`.`活动发生地详址经度` activity_longtitude,`a`.`活动发生地详址纬度` activity_latitude
FROM `dw_supervisorycontrol` `a`

--  -------------------------------------------------------------------------------
--  -------------------------------------------------------------------------------
--  -------------------------------------------------------------------------------
-- Part3. Other tables
-- The table in this session: dim_crime_prediction, dim_outsider, dim_tag
-- Naming rule: dw: data warehouse; dim: dimension; t: temp; viw: view; fun: function
-- Need to create those table manually
--  -------------------------------------------------------------------------------
--  -------------------------------------------------------------------------------
--  -------------------------------------------------------------------------------

truncate table t_dim_suspect_base_info
truncate table t_dw_supervisorycontrol