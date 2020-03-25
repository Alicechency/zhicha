/**
 * 嫌疑人轨迹图相关
 */


// 轨迹图线路数据
/*var coords = [
    [113.317568, 23.135959],
    [113.317783, 23.135278],
    [113.321489, 23.135432],
    [113.321525, 23.135282],
    [113.321592, 23.13517],
    [113.321799, 23.135083],
    [113.322158, 23.135141],
    [113.322374, 23.135378],
    [113.321992, 23.135847],
    [113.322243, 23.13949],
    [113.322396, 23.1397],
    [113.328163, 23.13943],
    [113.334945, 23.139033],
    [113.334658, 23.133217],
    [113.33313, 23.13222],
    [113.328755, 23.137588]
];

// 轨迹图关键点数据
var positionData = [{
    name: '2017-04-01 12:00:00',
    value: [113.317568, 23.135959, 'otherInfo']
}, {
    name: '2017-04-02 12:00:00',
    value: [113.317783, 23.135278, 'otherInfo']
}, {
    name: '2017-04-03 12:00:00',
    value: [113.321489, 23.135432, 'otherInfo']
}, {
    name: '2017-04-04 12:00:00',
    value: [113.321525, 23.135282, 'otherInfo']
}, {
    name: '2017-04-05 12:00:00',
    value: [113.321592, 23.13517, 'otherInfo']
}, {
    name: '2017-04-06 12:00:00',
    value: [113.321799, 23.135083, 'otherInfo']
}, {
    name: '2017-04-07 12:00:00',
    value: [113.322158, 23.135141, 'otherInfo']
}, {
    name: '2017-04-08 12:00:00',
    value: [113.322374, 23.135378, 'otherInfo']
}, {
    name: '2017-04-09 12:00:00',
    value: [113.321992, 23.135847, 'otherInfo']
}, {
    name: '2017-04-10 12:00:00',
    value: [113.322243, 23.13949, 'otherInfo']
}, {
    name: '2017-04-11 12:00:00',
    value: [113.322396, 23.1397, 'otherInfo']
}, {
    name: '2017-04-12 12:00:00',
    value: [113.328163, 23.13943, 'otherInfo']
}, {
    name: '2017-04-13 12:00:00',
    value: [113.334945, 23.139033, 'otherInfo']
}, {
    name: '2017-04-14 12:00:00',
    value: [113.334658, 23.133217, 'otherInfo']
}, {
    name: '2017-04-15 12:00:00',
    value: [113.33313, 23.13222, 'otherInfo']
}, {
    name: '2017-04-16 12:00:00',
    value: [113.328755, 23.137588, 'otherInfo']
}, {
    name: '2017-04-18 12:00:00',
    value: [113.328755, 23.137588, 'otherInfo']
}, {
    name: '2017-04-19 12:00:00',
    value: [113.328755, 23.137588, 'otherInfo']
}, {
    name: '2017-04-20 12:00:00',
    value: [113.328755, 23.137588, 'otherInfo']
}];*/


/**
 * 对具有相同位置的postionData元素进行去重，合并时间
 * @param  {对象数组} positionData [name为时间，value为经纬度]
 * @return {对象数组}              [去重以后的postionData]
 */
function removeDuplicationOfPositionData(positionData) {
    var resPositionData = [];
    // 建立一个key为经纬度字符串，value为positionData对象数组的映射用于去重
    var position2ArrayMap = {};
    for (var i = 0; i < positionData.length; i++) {
        var positionString = '' + positionData[i].value[0] + positionData[i].value[1];
        if (position2ArrayMap[positionString] === undefined) {
            position2ArrayMap[positionString] = [];
        }
        position2ArrayMap[positionString].push(positionData[i]);
    }
    // 迭代map整理name的格式
    for (var each in position2ArrayMap) {
        if (position2ArrayMap[each].length === 1) {
            resPositionData.push(position2ArrayMap[each][0]);
        } else {
            var newPositionData = {};
            newPositionData.name = position2ArrayMap[each][0].name;
            for (var i = 1; i < position2ArrayMap[each].length; i++) {
                newPositionData.name = newPositionData.name + '\n' + position2ArrayMap[each][i].name;
            }
            newPositionData.value = position2ArrayMap[each][0].value;
            resPositionData.push(newPositionData);
        }
    }
    return resPositionData;
}


/**
 * 根据路线的全部坐标点计算路线外切矩形的中心点即地图定位的中心点
 * @param  {二维数组} coords [coords[i][0]为经度，coords[i][1]为维度]
 * @return {数组}           [中心点坐标]
 */
function getCenterPoint(coords) {
    var centerPoint = [];
    if (coords.length !== 0) {
        var minLon = coords[0][0];
        var maxLon = coords[0][0];
        var minLat = coords[0][1];
        var maxLat = coords[0][1];
        for (var i = 1; i < coords.length; i++) {
            if (coords[i][0] < minLon) {
                minLon = coords[i][0];
            } else if (coords[i][0] > maxLon) {
                maxLon = coords[i][0];
            }
            if (coords[i][1] < minLat) {
                minLat = coords[i][1];
            } else if (coords[i][1] > maxLat) {
                maxLat = coords[i][1];
            }
        }
        // 地图定位的中心点以左上角为基准，原因不明，因此以左上角为基础外加减矩形宽高的一半修正
        // centerPoint = [minLon - (maxLon - minLon) / 2, maxLat + (maxLat - minLat) / 2];
        centerPoint = [(maxLon + minLon) / 2, (maxLat + minLat) / 2];
    }
    return centerPoint;
}


/**
 * 绘制轨迹图
 * @param  {二维数组} coords       [轨迹坐标]
 * @param  {对象数组} positionData [轨迹散点]
 */
function initTraceMap(coords, positionData) {
    var chart = echarts.init(document.getElementById('trace-graph'));

    option = {
        bmap: {
            center: [104.40, 30.67],
            roam: true
        },
        // 线路用lines，标记位置用effectScatter或scatter
        series: [{
            type: 'lines',
            name: '多人轨迹',
            coordinateSystem: 'bmap',
            polyline: true,
            effect: {
                show: true,
                period: 20,
                delay: 0,
                constantSpeed: 0,
                symbol: 'circle',
                symbolSize: 15,
                color: '#f00',
                trailLength: 0,
                loop: true
            },
            symbol: ['path://M64,0C39.699,0,20,19.699,20,44s44,84,44,84s44-59.699,44-84S88.301,0,64,0z M28,44C28,24.148,44.148,8,64,8s36,16.148,36,36c0,13.828-20.008,47.211-36,70.238C48.008,91.211,28,57.828,28,44z M64,24c-11.047,0-20,8.953-20,20s8.953,20,20,20s20-8.953,20-20S75.047,24,64,24z M64,56c-6.617,0-12-5.383-12-12s5.383-12,12-12s12,5.383,12,12S70.617,56,64,56z', 'path://M64,0C39.699,0,20,19.699,20,44s44,84,44,84s44-59.699,44-84S88.301,0,64,0z M28,44C28,24.148,44.148,8,64,8s36,16.148,36,36c0,13.828-20.008,47.211-36,70.238C48.008,91.211,28,57.828,28,44z M64,24c-11.047,0-20,8.953-20,20s8.953,20,20,20s20-8.953,20-20S75.047,24,64,24z M64,56c-6.617,0-12-5.383-12-12s5.383-12,12-12s12,5.383,12,12S70.617,56,64,56z'],
            symbolSize: [10, 10],
            lineStyle: {
                normal: {
                    color: '#f00',
                    width: 3,
                    type: 'solid',
                    shadowBlur: 10,
                    shadowColor: 'rgba(0, 0, 0, 0.5)',
                    shadowOffsetX: 5,
                    shadowOffsetY: 5,
                    opacity: 0.6,
                },
                emphasis: {

                }
            },
            data: [{
                name: '活动路线1',
                coords: coords,
                lineStyle: {
                    normal: {

                    },
                    emphasis: {

                    }
                }
            }],
            zlevel: 1,
            animation: false
        }, {
            type: 'effectScatter',
            name: '轨迹结点',
            showEffectOn: 'emphasis',
            rippleEffect: {
                period: 4,
                scale: 2.5,
                brushType: 'stroke'
            },
            coordinateSystem: 'bmap',
            // symbol: 'path://M64,0C39.699,0,20,19.699,20,44s44,84,44,84s44-59.699,44-84S88.301,0,64,0z M28,44C28,24.148,44.148,8,64,8s36,16.148,36,36c0,13.828-20.008,47.211-36,70.238C48.008,91.211,28,57.828,28,44z M64,24c-11.047,0-20,8.953-20,20s8.953,20,20,20s20-8.953,20-20S75.047,24,64,24z M64,56c-6.617,0-12-5.383-12-12s5.383-12,12-12s12,5.383,12,12S70.617,56,64,56z',
            symbol: 'circle',
            symbolSize: 10,
            symbolRotate: 0,
            // symbolOffset: [0, -12],
            label: {
                normal: {
                    show: false,
                    position: 'top',
                    formatter: '{b}',
                    textStyle: {
                        color: '#ddb926',
                        fontSize: 16
                    }
                },
                emphasis: {
                    show: true,
                    position: 'top',
                    formatter: '{b}',
                    textStyle: {
                        color: '#ddb926',
                        fontSize: 16
                    }
                }

            },
            itemStyle: {
                normal: {
                    color: '#ddb926'
                },
                emphasis: {

                }
            },
            data: removeDuplicationOfPositionData(positionData),
            zlevel: 1
        }]
    };

    chart.setOption(option);
    var centerPoint = getCenterPoint(coords);
    var point = new BMap.Point(centerPoint[0], centerPoint[1]);
    var map = chart.getModel().getComponent('bmap').getBMap();
    map.centerAndZoom(point, 15);
    map.enableScrollWheelZoom(true);
    /*map.setMapStyle({
        style: 'midnight'
    });*/
}
