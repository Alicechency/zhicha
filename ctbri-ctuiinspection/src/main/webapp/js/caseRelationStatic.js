//绘制案件关系图，不需要扩展

//绘制关系图
function showCaseRelationChart(data){
	var myChart = echarts.init(document.getElementById("let"));
	var relationData = data;
	var option = createCaseRelationGraphOption(relationData);
	//console.log(JSON.stringify(relationData));
	myChart.setOption(option);
}

function createCaseRelationGraphNodes(relationData) {
    var nodes = [];
    var names = [];

    // 添加附近案件子结点
    var i = 0;
    for (i = 0; i < 50 && i < relationData.length; i++) {
    	for( var node in relationData[i]) {
    		if($.inArray(relationData[i][node].name, names)==-1){  //判断名字是否重复，重复就不添加进节点
    			nodes.push({
                    name: relationData[i][node].name,
                    value: relationData[i][node].id,
                    category: relationData[i][node].category,
                    symbolSize: 20
                });
        		names.push(relationData[i][node].name);
    		}   		   		
    	}
    }
    return nodes;
}


/**
 * [创建关系图的links数组]
 * @param  {JSON对象} relationData [请求返回的relationData(过滤掉前几级key)]
 * @return {对象数组}               [关系图配置项中的links(或edges)]
 */
function createCaseRelationGraphLinks(relationData) {
    var links = [];
    
    // 连接附近案件和下一级子结点
    var i = 0;
    for (i = 0; i < 50 && i < relationData.length; i++) {
    	for( var node=0; node<relationData[i].length; node++) {
    		if(node==relationData[i].length-1) break;//结束了
    		//console.log(JSON.stringify(relationData[i].casedata[node+1].name));
    		links.push({
                source: relationData[i][node].name,
                target: relationData[i][node+1].name,
                value: 30
            });
    	}       
    }
    return links;
}

//得到关系图的option
function createCaseRelationGraphOption(relationData) {
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
            data: ['相关人员', '案件','律师']
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
            //draggable: true,
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
                'name': '相关人员'
            }, {
                'name': '案件'
            },{
                'name': '律师'
            }],
            nodes: createCaseRelationGraphNodes(relationData),
            links: createCaseRelationGraphLinks(relationData),
            tooltip: {}
        }],
        animationDuration: 3000,
        animationEasingUpdate: 'quinticInOut'
    };
	//console.log(JSON.stringify(option.series[0].nodes));
    return option;
}

