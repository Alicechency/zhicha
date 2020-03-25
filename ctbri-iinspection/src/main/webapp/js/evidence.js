/**
 * [创建关系图的nodes数据]
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
    for (i = 0; i < 10 && i < relationData.nearCaseValues.length; i++) {
        nodes.push({
            name: relationData.nearCaseValues[i] + '(' + relationData.nearCaseIds[i] + ')',
            value: relationData.nearCaseIds[i],
            category: '附近案件',
            symbolSize: 20
        });
    }
    // 添加嫌疑人子结点
    for (i = 0; i < 10 && i < relationData.suspectValues.length; i++) {
        nodes.push({
            name: relationData.suspectValues[i],
            category: '嫌疑人',
            symbolSize: 20
        });
    }
    return nodes;
}


/**
 * [创建关系图的links数组]
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
    for (i = 0; i < 10 && i < relationData.nearCaseValues.length; i++) {
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
    for (i = 0; i < 10 && i < relationData.suspectValues.length; i++) {
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
 * [创建关系图配置对象]
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
