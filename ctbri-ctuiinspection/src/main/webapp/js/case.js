//绘制律师关系数据图
/*var data2={
	"sons":[
		{
			"id":4,
			"name":"李四",
			"category":"吸毒人员",
		},
		{
			"id":5,
			"name":"王五",
			"category":"抢劫案",
		},
	]
}
var data={
	"id":1,
    "name":"张三1",
	"category":"吸毒人员",
	"sons":[
		{
			"id":2,
			"name":"李四1",
			"crimeType":"吸毒人员",
		},
		{
			"id":3,
			"name":"王五1",
			"crimeType":"抢劫案",
		},
	]
}
*/
//绘制关系图,在切换律师时候的左侧
function showRelationChart(data){
	var myChart = echarts.init(document.getElementById("resultChart"));
	var relationData = data;
	var option = createRelationGraphOption(relationData);
	myChart.setOption(option);
	
	myChart.on('click', function (params) {
		var url = "/ctuiinspection/lawyer/loadRelationGraph";
		var data = {
			name : params.name,
		};
		var successCallback = function(data) {
			
			var relationData1 = data.detail.lawyerRelationship;;
			var option1 = option;
			
			//console.log(JSON.stringify(option.series[0].links));
			var nodes = option.series[0].nodes;
			var links = option.series[0].links;
			var newNodes = addRelationGraphNodes(relationData1);
			var newLinks = addRelationGraphLinks(params.name,relationData1);
			//console.log(JSON.stringify(newNodes[0].name));
			
			for(var i=0;i<newNodes.length;i++){
				var j=0;
				for(j=0; j<nodes.length; j++) {
					if(newNodes[i].name == nodes[j].name){
						break;
					}
				}
				if(j == nodes.length&& j != 0){
					option1.series[0].nodes.push(newNodes[i]);
				}		
			}
			for(var i=0;i<newLinks.length;i++){
				var j=0;
				for(j=0; j<links.length; j++) {
					if(newLinks[i].target == links[j].source || newLinks[i].target == links[j].target) {
						break;
					}
				}
				if(j == links.length&& j != 0){
					option1.series[0].links.push(newLinks[i]);
				}	
			}
			
			myChart.setOption(option1);
		};
		Utils.ajax(url, true, data, successCallback, null);
		
	    //alert(JSON.stringify(params.name));
		//alert(params.name);
	    
	});
}

function createRelationGraphNodes(relationData) {
    var nodes = [];
    // 创建嫌疑人结点
    nodes.push({
        name: relationData.name,
        category: relationData.category,
        symbolSize: 30
    });

    // 添加附近案件子结点
    var i = 0;
    for (i = 0; i < 50 && i < relationData.sons.length; i++) {
        nodes.push({
            name: relationData.sons[i].name,
            value: 20,
            category: relationData.sons[i].category,
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
    
    // 连接附近案件和下一级子结点
    var i = 0;
    for (i = 0; i < 50 && i < relationData.sons.length; i++) {
        links.push({
            source: relationData.name,
            target: relationData.sons[i].name,
            value: 30
        });
    }
    return links;
}


function addRelationGraphNodes(relationData) {
    var nodes = [];
    
    // 添加附近案件子结点
    var i = 0;
    for (i = 0; i < 50 && i < relationData.sons.length; i++) {
        nodes.push({
            name: relationData.sons[i].name,
            value: 20,
            category: relationData.sons[i].category,
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
function addRelationGraphLinks(name,relationData) {
    var links = [];
    
    var i = 0;
    for (i = 0; i < 50 && i < relationData.sons.length; i++) {
        links.push({
            source: name,
            target: relationData.sons[i].name,
            value: 30
        });
    }    
    return links;
}


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
            data: ['相关人员', '律师']
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
                'name': '律师'
            }],
            nodes: createRelationGraphNodes(relationData),
            links: createRelationGraphLinks(relationData),
            tooltip: {}
        }],
        animationDuration: 3000,
        animationEasingUpdate: 'quinticInOut'
    };
	//console.log(JSON.stringify(option.series[0].nodes));
    return option;
}

