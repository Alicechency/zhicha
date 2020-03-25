//获取类型图标
function getEchartsResult(num) {
	var myChart = echarts.init(document.getElementById('resultChart'));
	var option = {
		title : {
			x : 'left'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b}: {c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			y : 'bottom',
			data : [ '原告胜诉', '原告败诉', '再审' ,'撤诉' ]
		},		
		calculable : false,
		series : [ {
			name : '访问来源',
			type : 'pie',
			radius : '40%',
			avoidLabelOverlap : false,
			data : [ {
				value : num[0],
				name : '原告胜诉'
			}, {
				value : num[1],
				name : '原告败诉'
			}, {
				value : num[2],
				name : '再审'
			},{
				value : num[3],
				name : '撤诉'
			} ],
			roseType: 'radius',
			label : {
				normal : {
					show : true,
					position : 'left',
					formatter : '({d}%)'
				},
				emphasis : {
					show : false,
					textStyle : {
						fontSize : '30',
						fontWeight : 'bold'
					}
				}
			},
			labelLine : {
				normal : {
					show : true
				}
			},
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'left',
						formatter : '({d}%)'
					},
					labelLine : {
						show : true
					}
				}
			}
		} ],
		color : [ '#7CB0DA', '#EFCC00', '#BAE1EF','#B3BBBE' ]
	};
	myChart.setOption(option);
}
// 增加推荐律师部分的分页
/*function RecommendcallBackPagination() {
	//totalCount  总数量 showCount 展现的下标 limit 一页展现的数量
    var totalCount =252, showCount = 5, limit = 5;

	var caseIds=[];
	for(var i in casesData){
		caseIds.push(casesData[i].caseId);
	}
	var lawyerData=[];
	lawyerData=loadRecommendedLawyer(1,caseIds);
	RecommendcreateTable(1, limit, totalCount,lawyerData);
    $('#recommend-pagination').extendPagination({

        totalCount: totalCount,

        showCount: showCount,

        limit: limit,

        callback: function (curr, limit, totalCount) {
			var caseIds=[];
			for(var i in casesData){
				caseIds.push(casesData[i].caseId);
			}
			var lawyerData=[];
			lawyerData=loadRecommendedLawyer(curr,caseIds);
            RecommendcreateTable(curr, limit, totalCount,lawyerData);

        }

    });

}*/
// 增加推荐律师部分的表格
function RecommendcreateTable(currPage, limit, total, lawyerData) {

	var html = [], showNum = limit;

	if (total - (currPage * limit) < 0)
		showNum = total - ((currPage - 1) * limit);
	html.push('<table class="table table-striped table-hover" id="recommend-table">');
    html.push('<tr style="color:#999999;height:30px;"><th class="text-center " style="width:20%;padding-top: 10px; " >擅长领域</th><th class="text-center"  style="width:40%;padding-top: 10px; " >姓名</th><th class="text-center" style="width:20%;padding-top: 10px; " >胜诉率</th><th class="text-center" style="width:20%;padding-top: 10px; " >勾选对比</th></tr><tbody>');
	for (var i = 0; i < showNum && i < lawyerData.length; i++) {
		
		html.push(' <tr style="height: 30px;"><td class="text-center " style="padding-top: 10px;" >'+lawyerData[i].topic+'</td>');

		html.push('<td class="text-center lawyerName" style="padding-top: 10px;" onclick="getLawyerName($(this))">'
				+ lawyerData[i].name + '</td>');
		html.push(' <td class="text-center" style="padding-top: 10px;">'+lawyerData[i].winningPercentage+'%</td>');
		html.push(' <td class="text-center " style="padding-top: 10px;"><span id="lawyer-pick" style="background: inherit;border: none !important;"  ><img src="'+imgPath+'/not-pick.png" style="height: 18px;" onclick="changeColor($(this))"><span style="display: none;">'+lawyerData[i].lawyerId+'</span></span></td>');
		html.push('</tr>');

	}

	html.push('</tbody></table>');

	var mainObj = $('#recommendResult');

	mainObj.empty();

	mainObj.html(html.join(''));

}
var casesData = [];

// 增加同类案件部分的分页
/*function CasecallBackPagination() {
	// totalCount 总数量 showCount 展现的下标 limit 一页展现的数量
	var totalCount = 252, showCount = 5, limit = 5;

	casesData = loadSimilarCase(1, casesData);
	
	CasecreateTable(1, limit, totalCount, casesData);

	$('#case-pagination').extendPagination({

		totalCount : totalCount,

		showCount : showCount,

		limit : limit,

		callback : function(curr, limit, totalCount) {
			casesData = loadSimilarCase(curr, casesData);
			CasecreateTable(curr, limit, totalCount, casesData);

		}

	});

}*/
// 增加页面同类案件表格
function CasecreateTable(currPage, limit, total, casesData) {

	var html = [], showNum = limit;

	if (total - (currPage * limit) < 0)
		showNum = total - ((currPage - 1) * limit);

	html.push('<table class="table table-striped table-hover" id="case-table">');
    html.push('<tr style="color: #999999;height:30px !important;"><th class="text-center" style="width:24%;padding-top: 10px; " >日期</th><th class="text-center" style="width:62%;padding-top: 10px; ;" >标题</th><th class="text-center" style="width:14%;padding-top: 10px; " >结果</th></tr><tbody>');
	for (var i = 0; i < showNum && i < casesData.length; i++) {

		html.push(' <tr style="height: 30px;"><td class="text-center" style="padding-top: 10px;">' + casesData[i].date
						+ '</td>');

		html.push('<td class="text-center" style="padding-top: 10px;" onclick="getCaseId($(this))">' + casesData[i].title +'<span style="display: none" >'+','+casesData[i].caseId+'</span>'+'</td>');

		html.push(' <td style="padding-top: 10px;"><span ><button class="btn btn-default" id="case-file" type="button" style="border: none;background: inherit;" onmouseover="showDetail($(this))" onmouseout="hideDetail()"><span style="display: none">'+casesData[i].caseId+'</span><img src="'+imgPath+'/file.png"    style="width:30px;height:20px;padding-right: 10px; "></button></span>'
						+ casesData[i].judgeResultInfo + '</td>');
		
		html.push('</tr>');

	}

	html.push('</tbody></table>');

	var mainObj = $('#CaseResult');

	mainObj.empty();

	mainObj.html(html.join(''));

}
//搜索按钮绑定点击事件
$('#searchButton').click(function() {
	loadKeyWord();
	//加载同类案件
	loadSimilarCase(1);
	//加载饼状图
	loadPieChart();
	
});
//点击律师加载律师
$("#rec-btn").click(function() {
	$("#same-btn").css("color", "#666666").css("text-decoration", "none");
	$("#rec-btn").css("color", "#4e80ff").css("text-decoration", "underline");
	$("#case-table").remove();
	$("#case-pagination").hide();
	$("#recommend-pagination").show();
	LAWYERID=[];
	//将案件的id作为参数传给律师页
	var caseIds=[];
	for(var i in casesData){
		caseIds.push(casesData[i].caseId);
	}
	//加载律师数据
	loadRecommendedLawyer(1,caseIds);
	//加载律师雷达图
	showLawyerRadar();
});
//点击案件加载案件
$("#same-btn").click(function() {
	$("#rec-btn").css("color", "#666666").css("text-decoration", "none");
	$("#same-btn").css("color", "#4e80ff").css("text-decoration", "underline");
	$("#recommend-table").remove();
	$("#recommend-pagination").hide();
	$("#case-pagination").show();
	loadSimilarCase(1);
});
//默认加载案件图
$(function() {
	$("#rec-btn").css("color", "#666666").css("text-decoration", "none");
	$("#same-btn").css("color", "#4e80ff").css("text-decoration", "underline");
	$("#recommend-table").remove();
	$("#recommend-pagination").hide();
	$("#case-pagination").show();
});
function showDetail(obj) {
	positionBody();
	//console.log(y);
	$("#case-detail").css("display", "block").css("top", y);
	var judgementStr="";
	//获得案件的id
	var caseId = obj.text();
	for(var c in casesData) {
		if(caseId==casesData[c].caseId){
			judgementStr = casesData[c].judgement;
			break;
		}
	}
	$("#prompt-content").html(judgementStr);
	
}
function hideDetail(e) {
	$("#case-detail").css("display", "none");
}
var x, y, top;
// 当需求为获得的坐标值相对于body时，用：
function positionBody(event) {
	var event = event || window.event;
	var obj = event.target;
	// 获得相对于body定位的横标值；
	// x=event.clientX;
	// 获得相对于body定位的纵标值；
	y = $(obj).offset().top;
}
// 设置回车触发搜索按钮
// 回车提交搜索请求
$('#search-input').keydown(function(event) {
   
    if (event.keyCode === 13) {
        $('#searchButton').click();
        //阻止浏览器的默认行为
        event.preventDefault();
    }
});
//点击律师姓名得到律师的名字，用于加载律师关系图
function getLawyerName(obj){
	var name = obj.text();
	loadRelationGraph(name);
}
//得到案件的id
function getCaseId(obj) {
	var caseStr = obj.text().split(',');
	var caseId = caseStr[caseStr.length-1];
	loadCaseRelationGraph(caseId);
}

//记录当前选择的律师的集合
var LAWYERID=[];
//勾选按钮方法
function changeColor(obj) {
	if (obj.attr("src") == imgPath+"/not-pick.png") {
		obj.attr("src", imgPath+"/picking.png");
		LAWYERID.push(obj.next().text());
		loadRadarData(LAWYERID);
	} else {
		obj.attr("src", imgPath+"/not-pick.png");
		var removeIndex;
		for(var i in LAWYERID){
			if(LAWYERID[i]==obj.next().text()){
				removeIndex=i;
			}
		}
		LAWYERID.splice(removeIndex,1);
		loadRadarData(LAWYERID);
	}
}

