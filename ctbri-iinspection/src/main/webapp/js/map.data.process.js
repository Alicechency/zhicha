/**
 * 本文件包含数据向ECharts图表需要的格式转换的相关函数：
 * 1.警车位置数据转换convertPoliceCarJsonData
 * 2.案件散点数据格式转换convertEventData2Series: 将eventData转换成按类别分类的散点图series
 * 3.热力图数据格式转换convertFreqData
 * 4.巡逻路线数据格式转换
 * 5.关系图数据格式转换函数createRelationGraphNodes、createRelationGraphLinks
 *   关系图配置函数createRelationGraphOption
 */


/**
 * 转换后端传递的警车位置Json数据
 * @param  {对象数组} data [每个数组元素由id,longitude,latitude组成]
 * @return {对象数组}      [用于在地图上显示警车位置]
 */
function convertPoliceCarJsonData(data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = [data[i]['longitude'], data[i]['latitude']];
        if (geoCoord) {
            res.push({
                name: data[i]['id'],
                value: geoCoord
            });
        }
    }
    return res;
}


/**
 * 将案情数据转换成按类别的series散点数据
 * @param  {对象数组} data   [事件数据eventData]
 * @param  {对象数组} series [echarts.option中的mySeries]
 */
function convertEventData2Series(data, series) {
    var srs = {};
    var i = 0;
    // 将data中每一类的案件数据导入到一个series对象中
    for (var each in data) {
        srs = {
            // id用于清除案情按钮
            id: 'eventDataCategory' + i,
            type: 'scatter',
            // name用于legend
            name: each,
            coordinateSystem: 'bmap',
            // data是案情散点数据
            data: data[each],
            // 散点图散点点大小
            symbolSize: 13,
            label: {
                normal: {
                    show: false
                },
                // hover时显示的文字
                emphasis: {
                    show: true,
                    position: 'right',
                    formatter: function(params) {
                        return caseAbstract[params.value[2]];
                    },
                    textStyle: {
                        fontSize: 14
                    }
                }
            },
            itemStyle: {
                normal: {
                    // 改变散点颜色, colorList中的颜色循环显示
                    color: function(params) {
                        var colorList = [
                            '#334D5D', '#41B29D', '#EFCA43',
                            '#E37B3B', '#DA5844', '#FD4263',
                            '#C8C9A6', '#3EAFB3'
                        ];
                        return colorList[params.seriesIndex % colorList.length];
                    }
                }
            },
            // tips文字信息(ECharts原生在百度地图上有bug，已使用了自己重构的)
            tooltip: {
                show: false,
                position: function(pos, params, dom, rect, size) {
                    // 鼠标在左侧时 tooltip 显示到右侧，鼠标在右侧时 tooltip 显示到左侧。
                    // var obj = { top: pos[1] };
                    // obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 5;
                    // return obj;
                },

                formatter: function(params) {
                    return params.name + ': ' + caseAbstract[params.value[2]];
                }
            }
        };
        series.push(srs);
        i++;
    }
}


/**
 * 转换热力图频度数据
 * @param  {对象数组} data [eventData[类别]，即对应类别的事件列表]
 * @return {二维数组}      [行对应不同的数据项,列分别为经度、维度、频度]
 */
function convertFreqData(data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        // 热力频度均赋值为10
        res.push([data[i].value[0], data[i].value[1], 10]);
    }
    return res;
}


/**
 * 巡逻路线图数据转换代码块
 * 其中lineData为map.data.line.js中声明的模拟数据
 */
(function() {
    var hStep = 300 / (lineData.length - 1);
    linesData = [].concat.apply([], lineData.map(function(line, idx) {
        var prevPt;
        var points = [];
        for (var i = 0; i < line.length; i += 2) {
            var pt = [line[i], line[i + 1]];
            points.push([pt[0] / 1e4, pt[1] / 1e4]);
        }
        return {
            coords: points,
            lineStyle: {
                normal: {
                    color: echarts.color.modifyHSL('#5A94DF', Math.round(hStep * idx))
                }
            }
        };
    }));
}());


/**
 * 创建关系图的nodes数据
 * @param  {JSON对象} relationData [请求返回的relationData(过滤掉前几级key)]
 * @return {对象数组}               [关系图配置项中的nodes(或data)]
 */
function createRelationGraphNodes(relationData) {
    var nodes = [];
    // 创建案件ID结点(中心结点)
    nodes.push({
        name: '案件' + relationData.id,
        symbolSize: 40,
        itemStyle: {
            normal: {
                color: '#c23531'
            }
        }
    });
    // 创建事主身份结点
    nodes.push({
        name: '事主身份',
        category: '事主身份',
        symbolSize: 30
    });
    // 创建附近案件结点
    nodes.push({
        name: '附近案件',
        category: '附近案件',
        symbolSize: 30
    });
    // 创建嫌疑人结点
    nodes.push({
        name: '嫌疑人',
        category: '嫌疑人',
        symbolSize: 30
    });
    if (relationData.identity) {
        // 添加事主身份子结点
        nodes.push({
            name: relationData.identity,
            value: relationData.id,
            category: '事主身份',
            symbolSize: 20
        });
    }
    // 添加附近案件子结点
    var i = 0;
    for (i = 0; i < 5 && i < relationData.nearCaseValues.length; i++) {
        nodes.push({
            name: relationData.nearCaseValues[i] + '(' + relationData.nearCaseIds[i] + ')',
            value: relationData.nearCaseIds[i],
            category: '附近案件',
            symbolSize: 20
        });
    }
    // 添加嫌疑人子结点
    for (i = 0; i < 5 && i < relationData.suspectValues.length; i++) {
        nodes.push({
            name: relationData.suspectValues[i],
            category: '嫌疑人',
            symbolSize: 20
        });
    }
    return nodes;
}


/**
 * 创建关系图的links数组
 * @param  {JSON对象} relationData [请求返回的relationData(过滤掉前几级key)]
 * @return {对象数组}               [关系图配置项中的links(或edges)]
 */
function createRelationGraphLinks(relationData) {
    var links = [];
    // 连接案件ID中心结点与下一级子结点
    links.push({
        source: '案件' + relationData.id,
        target: '事主身份',
        value: 28
    });
    links.push({
        source: '案件' + relationData.id,
        target: '附近案件',
        value: 30
    });
    links.push({
        source: '案件' + relationData.id,
        target: '嫌疑人',
        value: 30
    });
    // 连接事主身份和下一级子结点
    if (relationData.identity) {
        links.push({
            source: '事主身份',
            target: relationData.identity,
            value: 30
        });
    }
    // 连接附近案件和下一级子结点
    var i = 0;
    for (i = 0; i < 5 && i < relationData.nearCaseValues.length; i++) {
        links.push({
            source: '附近案件',
            target: relationData.nearCaseValues[i] + '(' + relationData.nearCaseIds[i] + ')',
            value: (function() {
                var distance = relationData.suspectDistances[i];
                if (distance >= 0 && distance <= 10) {
                    return distance + 1;
                } else {
                    return distance / 10 + distance % 10 + 10;
                }
            }())
        });
    }
    // 连接嫌疑人和下一级子结点
    for (i = 0; i < 5 && i < relationData.suspectValues.length; i++) {
        links.push({
            source: '嫌疑人',
            target: relationData.suspectValues[i],
            value: (function() {
                var distance = relationData.suspectDistances[i];
                if (distance >= 0 && distance <= 10) {
                    return distance + 1;
                } else {
                    return distance / 10 + distance % 10 + 10;
                }
            }())
        })
    }
    return links;
}


/**
 * 创建关系图配置对象
 * @param  {JSON对象} relationData [请求返回的relationData(过滤掉前几级key)]
 * @return {option对象}            [关系图配置对象]
 */
function createRelationGraphOption(relationData) {
    var option = {
        color: ['#f57643', '#bda93a', '#61a0a8'],
        legend: [{
            textStyle: {
                color: '#ccc'
            },
            tooltip: {
                show: true
            },
            bottom: 0,
            data: ['事主身份', '附近案件', '嫌疑人']
        }],
        toolbox: {
            show: true,
            feature: {
                dataView: { show: false, readOnly: true },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        series: [{
            id: 'relationshipGraph',
            type: 'graph',
            name: 'relationship',
            layout: 'force',
            force: {
                repulsion: 450,
                edgeLength: [0, 30]
            },
            roam: true,
            draggable: true,
            focusNodeAdjacency: true,
            lineStyle: {
                normal: {
                    color: 'source',
                    curveness: 0,
                    type: "solid"
                }
            },
            label: {
                normal: {
                    show: true,
                    position: 'top',
                }
            },

            // 关系图数据
            categories: [{
                'name': '事主身份'
            }, {
                'name': '附近案件'
            }, {
                'name': '嫌疑人'
            }],
            nodes: createRelationGraphNodes(relationData),
            links: createRelationGraphLinks(relationData),

            tooltip: {}
        }],
        animationDuration: 3000,
        animationEasingUpdate: 'quinticInOut'
    };
    return option;
}


/**
 * 将请求到的外来人口数据转换成Mapv网格图所需的数据格式
 * @param  {对象数组} outsiderData [一个数组，数组中的每个对象格式为{latitude: 30.23123314, longitude: 120.213123, cityCode: xxxx}]
 * @return {对象数组}              [按照Mapv网格图官方文档中的格式转换]
 */
function cvtOutsiderData2MapvData(outsiderData) {
    var mapvData = [];
    for (var i = 0; i < outsiderData.length; i++) {
        if (outsiderData[i].longitude && outsiderData[i].latitude) {
            mapvData.push({
                geometry: {
                    type: 'Point',
                    coordinates: [outsiderData[i].longitude, outsiderData[i].latitude]
                },
                // 数据为一个外来人口一个坐标，count设置为1可以用来计人数
                // count: 1
                count: 10
            });
        }
    }
    return new mapv.DataSet(mapvData);
}


/**
 * 将请求到的犯罪预测数据转换成Mapv网格图所需的数据格式
 * @param  {对象数组} crimePredictData [一个数组，数组中的每个对象格式为{latitude: xxx, longitude: xxx, date: xxx, category: xxx, percentage: xxx}]
 * @return {对象数组}                  [按照Mapv网格图官方文档中的格式转换]
 */
function cvtCrimePredictData2MapvData(crimePredictData) {
    var mapvData = [];
    for (var i = 0; i < crimePredictData.length; i++) {
        if (crimePredictData[i].longitude && crimePredictData[i].latitude) {
            mapvData.push({
                geometry: {
                    type: 'Point',
                    coordinates: [crimePredictData[i].longitude, crimePredictData[i].latitude]
                },
                // 概率值乘以100再四舍五入取整
                count: Math.round(crimePredictData[i].percentage * 100)
            });
        }
    }
    return new mapv.DataSet(mapvData);
}

/**
 * 将请求到的犯罪预测数据转换成Mapv网格图所需的数据格式
 * @param  {对象数组} crimePredictData [一个数组，数组中的每个对象格式为{latitude: xxx, longitude: xxx, date: xxx, category: xxx, percentage: xxx}]
 * @return {对象数组}                  [按照Mapv网格图官方文档中的格式转换]
 */
function cvtCameraData2MapvData(cameraLocationData) {
    var mapvData = [];
    for (var i = 0; i < cameraLocationData.length; i++) {
        if (cameraLocationData[i].lng && cameraLocationData[i].lat) {
            mapvData.push({
                geometry: {
                    type: 'Point',
                    coordinates: [cameraLocationData[i].lng, cameraLocationData[i].lat]
                },
                // 概率值乘以100再四舍五入取整
                count: cameraLocationData[i].range
            });
        }
    }
    return new mapv.DataSet(mapvData);
}



function cvtOutsiderData2HeatmapData(outsiderData) {
    var res = [];
    for (var i = 0; i < outsiderData.length; i++) {
        res.push([outsiderData[i].longitude, outsiderData[i].latitude, outsiderData[i].uniqueUsers]);
    }
    return res;
}