//绘制律师雷达图
function showLawyerRadar(data) {
	var myChart = echarts.init(document.getElementById("let"));
	var option = createRadarGraphOption(data);
	myChart.setOption(option);
}
//得到律师雷达图的option
function createRadarGraphOption(data) {
	var lawyerNames = [];
	var radarName = "";
	//图例的名称
	for ( var law in data) {
		lawyerNames.push(data[law].lawyerName);
		radarName += data[law].lawyerName;
		radarName += 'vs';
	}
	var indicators = [ '欠款', '劳动、劳务', '银行债务', '工程项目', '交通事故', '二审、再审', '合同纠纷',
			'婚姻问题', '房屋、土地', '医疗' ];
	var radarData = [];
	//遍历每一个律师
	for ( var law in data) {
		var obj = {};
		obj.name = data[law].lawyerName
		obj.value = [];
				
		for ( var i in indicators) {
			var c = 0;
			for (c in data[law].caseNames) {
				if (data[law].caseNames[c] == indicators[i]) {
					var value = data[law].nums[c];
					obj.value.push(value);
					break;
				}
			}
			if (c == data[law].caseNames.length - 1) {
				var value = 0;
				obj.value.push(value);
			}
		}
		radarData.push(obj);
	}
	console.log(JSON.stringify(radarData));
	
	var option = {
		title: {
			text: '律师雷达图'
	    },
	    tooltip: {},
	    legend: {
	    	orient: 'vertical',
	        right : '40%',
	        top : '50%',
	        data: lawyerNames
	    },
	    radar: {
	        // shape: 'circle',
	    	center: ['40%', '50%'],
			radius: 80,
	        indicator: [
	           { name: '欠款', max: 4},
	           { name: '劳动、劳务', max: 4},
	           { name: '银行债务', max: 4},
	           { name: '工程项目', max: 4},		   
	           { name: '交通事故', max: 4},
	           { name: '二审、再审', max: 4},
			   { name: '合同纠纷', max: 4},
			   { name: '婚姻问题', max: 4},
			   { name: '房屋、土地', max: 4},
			   { name: '医疗', max: 4}
	        ]
	    },
	    series: [{
	        name: radarName,
	        type: 'radar',
	        areaStyle: {normal: {}},
	        data : radarData,/*[
	            {
	                value : [4300, 10000, 28000, 35000, 50000, 19000,19000,19000, 19000, 19000],
	                name : '张三'
	            },
	             {
	                value : [5000, 14000, 28000, 31000, 42000, 21000, 21000, 21000, 21000,21000],
	                name : '李四'
	            }
	        ]*/
	    }],
		color : [ '#FBC3AA', '#BFDEF1' ]
	 };
	//console.log(JSON.stringify());
    return option;
}
