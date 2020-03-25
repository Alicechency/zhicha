/**
 * 此文件包含以下功能:
 * 1.需要将后端请求到的数据格式拆分成key-value，因此首先声明了用于保存键值对的全局对象，全部对象的key为案件的caseId
 * 2.获取ES数据的请求函数getESData()
 * 3.拆分ES数据为key-value并且转换成用于ECharts的eventData格式的方法
 * 4.页面初始化加载的请求和时间日期设置
 * 5.警车位置数据全局变量声明、巡逻路线数据全局变量声明、关系图全局变量声明
 */


/**
 * 页面涉及到的全部URL，方便本地调试和线上修改
 */

// ES数据请求路径
var ES_URL = 'http://localhost:9200/cypo/_search';

// 关系图数据请求路径
var RELATION_DATA_URL = '/iinspection/map/loadCaseRelationShip';

// 图像路径
var IMG_URL = '/iinspection/img/';

// 警车位置数据请求路径
var CAR_POSITION_URL = './pc';

// 外来人口网格图数据请求路径
var OUTSIDER_URL = '/iinspection/map/loadOutsiders';

// 犯罪预测网格图数据请求路径
var CRIME_PREDICT_URL = '/iinspection/map/loadPredictionByDate';


/**
 * 全局键值对对象声明，各字段全部转换成key-value(key为caseId)
 * 目前只用到部分数据字段，可根据情况删减
 */

// 报警时间(_source.submiteDatetime)
var caseTime = {};

// 报警电话(_source.victim_tel)
var victimTel = {};

// 接警人员(_source.officer_name)
var officerName = {};

// 坐席号(_source.officer_seats_no)
var officerSeatsNo = {};

// 警情标题(_source.title)
var caseTitle = {};

// 处警单位派出所(_source.local_substation)
var localSubstation = {};

// 工作情况(_source.case_situation)
var caseSituation = {};

// 警情类别(_source.case_type数组)
var caseType = {};

// 街头类别(_source.block_type)
var blockType = {};

// 案情摘要(_source.case_abstract)
var caseAbstract = {};

// 反馈信息(_source.contents)
var feedback = {};

// 类别(_source.subtagname)
var subcaseType = {};

// 作案手段(_source.modus_operandi)
var modusOperandi = {};

// 点位(_source.PT)
var casePT = {};

// 经纬度(_source.location_point.lon, _source.location_point.lat)
var locationPoint = {};

// 直属地区派出所、中心警务站(_source.central_station), 大部分没有这个信息
var centralStation = {};

// 新疆人(_source.isxj)
var isxj = {};

// 车型(_source.vehicle_type)
var vehicleType = {};

// 事主身份(_source.identity)
var identity = {};

// 诈骗警情事主年龄(_source.age)
var age = {};


/**
 * 存储案情类型的集合：用于将案件按类别归类到不同的ECharts的series中
 */
var typeArr = [];


/**
 * 存储以毫秒为单位计算的案件时间：预存储，用于提高后续排序比较的性能
 */
var caseTimeWithMs = {};


/**
 * startTime、endTime、keyword三个全局变量唯一确定某一时刻的数据
 */
// 起始时间
var startTime = '';
// 终止时间
var endTime = '';
// 关键词
var keyword = '';


/**
 * 用于保存请求到的ES数据
 */
var ESData = {};


/**
 * ES数据按类别转换后的eventData
 */
var eventData = {};


/**
 * 警车位置散点图 - 声明警车位置数据变量
 */
var policeCarData = [];


/**
 * 巡逻路线数据全局变量
 */
var linesData = [];


/**
 * 关系图全局变量relationData
 */
var relationData = {};


/**
 * 外来人口数据
 */
var outsiderData = [];


/**
 * 犯罪预测数据
 */
var crimePredictData = [];


/**
 * 两次Ajax请求获取ES数据：第一次请求只返回十条数据，因此仅用于获取命中的长度；第二次用于获取全部数据
 * 没有返回值，直接修改了全局变量ESData
 * @param  {String} startTime [开始时间]
 * @param  {String} endTime   [结束时间]
 * @param  {String} keyword   [关键词]
 */
function getESData(startTime, endTime, keyword) {
    // 保存数据长度
    var totalLength = 0;
    // 给起止时间加上秒，补全格式
    startTime = startTime + ":00";
    endTime = endTime + ":00";
    // 关键词为空时，加载这一时间段的全部数据
    if (keyword === '') {
        // 第一次请求获取长度
        $.ajax({
            type: "POST",
            url: ES_URL,
            data: JSON.stringify({
                "query": {
                    "range": {
                        "submiteDatetime": {
                            "gte": startTime,
                            "lte": endTime
                        }
                    }
                }
            }),
            dataType: "json",
            contentType: 'application/json',
            async: false,
            success: function(res) {
                totalLength = res.hits.total;
            }
        });

        // 第二次请求获取全部数据
        $.ajax({
            type: "POST",
            url: ES_URL + "?size=" + totalLength,
            data: JSON.stringify({
                "query": {
                    "range": {
                        "submiteDatetime": {
                            "gte": startTime,
                            "lte": endTime
                        }
                    }
                }
            }),
            dataType: "json",
            contentType: 'application/json',
            async: false,
            success: function(res) {
                ESData = res.hits.hits;
            }
        });
    }
    // 关键词不为空，请求对应时间段内对应关键词的数据 
    else {
        // 第一次请求获取长度
        $.ajax({
            type: "POST",
            url: ES_URL,
            data: JSON.stringify({
                "query": {
                    "bool": {
                        "must": {
                            "multi_match": {
                                "query": keyword,
                                "fields": ["contents", "title", "case_abstract ", "tagname", "modus_operandi", "subtagname"]
                            }
                        },
                        "filter": {
                            "range": {
                                "submiteDatetime": {
                                    "gte": startTime,
                                    "lte": endTime
                                }
                            }
                        }
                    }
                }
            }),
            dataType: "json",
            contentType: 'application/json',
            async: false,
            success: function(res) {
                totalLength = res.hits.total;
            }
        });
        // 第二次请求获取全部数据
        $.ajax({
            type: "POST",
            url: ES_URL + "?size=" + totalLength,
            data: JSON.stringify({
                "query": {
                    "bool": {
                        "must": {
                            "multi_match": {
                                "query": keyword,
                                "fields": ["contents", "title", "case_abstract ", "tagname", "modus_operandi", "subtagname"]
                            }
                        },
                        "filter": {
                            "range": {
                                "submiteDatetime": {
                                    "gte": startTime,
                                    "lte": endTime
                                }
                            }
                        }
                    }
                }
            }),
            dataType: "json",
            contentType: 'application/json',
            async: false,
            success: function(res) {
                ESData = res.hits.hits;
            }
        });
    }
}


/**
 * 判断字符串是否均为中文，是则返回true，若含有其他字符(英文等)返回false
 * @param  {String}  str [要判断的字符串]
 * @return {Boolean}     [返回布尔值]
 */
function isStrAllChinese(str) {
    var re = /[^\u4e00-\u9fa5]/;
    if (re.test(str)) return false;
    return true;
}


/**
 * 将ESData转换成eventData
 * @param  {对象} ESData [description]
 * @return {对象数组}     [返回eventData]
 */
function cvtESData2eventData() {
    // 初始化全部全局变量
    // 报警时间(_source.submiteDatetime)
    caseTime = {};

    // 报警电话(_source.victim_tel)
    victimTel = {};

    // 接警人员(_source.officer_name)
    officerName = {};

    // 坐席号(_source.officer_seats_no)
    officerSeatsNo = {};

    // 警情标题(_source.title)
    caseTitle = {};

    // 处警单位派出所(_source.local_substation)
    localSubstation = {};

    // 工作情况(_source.case_situation)
    caseSituation = {};

    // 警情类别(_source.case_type数组)
    caseType = {};

    // 街头类别(_source.block_type)
    blockType = {};

    // 案情摘要(_source.case_abstract)
    caseAbstract = {};

    // 反馈信息(_source.contents)
    feedback = {};

    // 类别(_source.subtagname)
    subcaseType = {};

    // 作案手段(_source.modus_operandi)
    modusOperandi = {};

    // 点位(_source.PT)
    casePT = {};

    // 经纬度(_source.location_point.lon, _source.location_point.lat)
    locationPoint = {};

    // 直属地区派出所、中心警务站(_source.central_station), 大部分没有这个信息
    centralStation = {};

    // 新疆人(_source.isxj)
    isxj = {};

    // 车型(_source.vehicle_type)
    vehicleType = {};

    // 事主身份(_source.identity)
    identity = {};

    // 诈骗警情事主年龄(_source.age)
    age = {};

    // 存储案情类别
    typeArr = [];

    // 存储以毫秒计算的时间，用于比较
    caseTimeWithMs = {};

    // 某些关键属性不存在时将该属性赋值undefined，同时将数据拆分
    // 确定最终需要哪些数据时重构此部分
    for (var i = 0; i < ESData.length; i++) {
        // 拆分各类属性
        var caseId = ESData[i]._source.id;
        if (ESData[i]._source.submiteDatetime !== undefined) {
            caseTime[caseId] = ESData[i]._source.submiteDatetime;
        } else {
            caseTime[caseId] = undefined;
        }
        if (ESData[i]._source.victim_tel !== undefined) {
            victimTel[caseId] = ESData[i]._source.victim_tel;
        } else {
            victimTel[caseId] = undefined;
        }
        if (ESData[i]._source.officer_name !== undefined) {
            officerName[caseId] = ESData[i]._source.officer_name;
        } else {
            officerName[caseId] = undefined;
        }
        if (ESData[i]._source.officer_seats_no !== undefined) {
            officerSeatsNo[caseId] = ESData[i]._source.officer_seats_no;
        } else {
            officerSeatsNo[caseId] = undefined;
        }
        if (ESData[i]._source.title !== undefined) {
            caseTitle[caseId] = ESData[i]._source.title;
        } else {
            caseTitle[caseId] = undefined;
        }
        if (ESData[i]._source.local_substation !== undefined) {
            localSubstation[caseId] = ESData[i]._source.local_substation;

        } else {
            localSubstation[caseId] = undefined;
        }
        if (ESData[i]._source.case_situation !== undefined) {
            caseSituation[caseId] = ESData[i]._source.case_situation;
        }
        if (ESData[i]._source.case_type !== undefined) {
            caseType[caseId] = ESData[i]._source.case_type;
        } else {
            caseType[caseId] = undefined;
        }
        if (ESData[i]._source.block_type !== undefined) {
            blockType[caseId] = ESData[i]._source.block_type;
        }
        // ES的bug：case_abstract后面加了一个空格，因此只能使用方括号取到值
        if (ESData[i]._source['case_abstract '] !== undefined) {
            caseAbstract[caseId] = ESData[i]._source['case_abstract '];
        } else {
            caseAbstract[caseId] = undefined;
        }
        if (ESData[i]._source.contents !== undefined) {
            feedback[caseId] = ESData[i]._source.contents;
        } else {
            feedback[caseId] = undefined;
        }
        if (ESData[i]._source.subtagname !== undefined) {
            subcaseType[caseId] = ESData[i]._source.subtagname;
        } else {
            subcaseType[caseId] = undefined;
        }
        if (ESData[i]._source.modus_operandi !== undefined) {
            modusOperandi[caseId] = ESData[i]._source.modus_operandi;
        }
        if (ESData[i]._source.PT !== undefined) {
            casePT[caseId] = ESData[i]._source.PT;
        }
        if (ESData[i]._source.location_point !== undefined) {
            locationPoint[caseId] = ESData[i]._source.location_point;
        } else {
            locationPoint[caseId] = undefined;
        }
        if (ESData[i]._source.central_station !== undefined) {
            centralStation[caseId] = ESData[i]._source.central_station;
        }
        if (ESData[i]._source.isxj !== undefined) {
            isxj[caseId] = ESData[i]._source.isxj;
        }
        if (ESData[i]._source.vehicle_type !== undefined) {
            vehicleType[caseId] = ESData[i]._source.vehicle_type;
        }
        if (ESData[i]._source.identity !== undefined) {
            identity[caseId] = ESData[i]._source.identity;
        } else {
            identity[caseId] = undefined;
        }
        if (ESData[i]._source.age !== undefined) {
            age[caseId] = ESData[i]._source.age;
        }

        // 计算类别类别集合
        if (isStrAllChinese(ESData[i]._source.subtagname) &&
            typeArr.indexOf(ESData[i]._source.subtagname) === -1) {
            typeArr.push(ESData[i]._source.subtagname);
        }

        // 计算Date.parse后的时间对象
        caseTimeWithMs[caseId] = Date.parse(ESData[i]._source.submiteDatetime);
    }

    // 调用createEventData函数，将ESData转换成按类别分类的eventData
    return createEventData(typeArr, startTime, endTime);
}


/**
 * 将一段时间内的数据组合出按类别加载的散点数据
 * @param  {String} startTime [yyyy-MM-dd hh:00]
 * @param  {String} endTime   [yyyy-MM-dd hh:00]
 * @return {对象} [eventData,key: 案情类别; value: 事件列表; 
 * 事件列表中的对象为: {name: caseTitle, value: [经度, 纬度, caseId]}]
 */
function createEventData(typeArr, startTime, endTime) {
    var eventData = {};

    // 初始化数据结构
    for (i = 0; i < typeArr.length; i++) {
        eventData[typeArr[i]] = [];
    }

    startTime = Date.parse(startTime);
    endTime = Date.parse(endTime);

    // 事件按类别添加
    for (i = 0; i < ESData.length; i++) {
        var caseId = ESData[i]._source.id;
        if (isStrAllChinese(ESData[i]._source.subtagname) &&
            Date.parse(caseTime[caseId]) >= startTime &&
            Date.parse(caseTime[caseId]) <= endTime &&
            caseTitle[caseId] !== undefined &&
            locationPoint[caseId] !== undefined &&
            locationPoint[caseId].lon !== undefined &&
            locationPoint[caseId].lat !== undefined) {
            var temp = {};
            temp.name = caseTitle[caseId];
            temp.value = [locationPoint[caseId].lon, locationPoint[caseId].lat, caseId];
            eventData[subcaseType[caseId]].push(temp);
        }
    }

    return eventData;
}


/**
 * 页面初始时间设置与数据加载(IIFE方式)
 * 当前设置为默认最近的60天
 */
(function() {
    // endTime为当前时间，精确到小时
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var day = now.getDate();
    var hour = now.getHours();
    endTime = year + '-' + month + '-' + day + ' ' + hour + ':00';
    // startTime为当前时间向前推60天，86400000为一天的毫秒数,这里改为7天，因为数据量过大
    var before = new Date(now - 86400000 * 7);
    year = before.getFullYear();
    month = before.getMonth() + 1;
    day = before.getDate();
    startTime = year + '-' + month + '-' + day + ' 00:00';

    // 语音助手关键词的判断
    var assistantWord = $('#word').val();
    if (assistantWord !== '${word}' && assistantWord !== '') {
        keyword = assistantWord;
    }

    // 初始化加载ES数据
    getESData(startTime, endTime, keyword);
    eventData = cvtESData2eventData(ESData);
}());
