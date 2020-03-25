$(function(){
	init();
	//加载关键词
	loadKeyWord();
	//加载同类案件
	loadSimilarCase(1);
	//加载饼状图
	loadPieChart();
});
//加载饼状图
function loadPieChart(){
	var url = "/ctuiinspection/case/generateSimilarIds";
	var search_word = document.getElementById('search-input').value;
	var data = {
		search_word : search_word,
	};
	var successCallback = function(data) {
		var num = data.detail.nums;
		getEchartsResult(num);
	};
	Utils.ajax(url, true, data, successCallback, null);
}
// 将index页面的输入值传到成都页面
function init() {
	var thisURL = decodeURI(document.URL);
	var getval = thisURL.split('?')[1];
	// 得到传过来的搜索值
	var showval = getval.split("=")[1];
	document.getElementById('search-input').value = showval;
}


// 加载关键词
function loadKeyWord() {
	var search_word = document.getElementById('search-input').value;
	var url = "/ctuiinspection/case/loadKeyword";
	var data = {
		search_word : search_word,
	}
	var successCallback = function(data) {
		var keywords = data.detail.keywords;
		$("#keywords").children().remove();
		for (var i = 0; i < keywords.length; i++) {
			var url = "/ctuiinspection/forward/searchInfo?searchWord="+keywords[i];
			$("#keywords").append("<li ><a href='"+url+"'>"+keywords[i]+"</a></li>");
		}
	};
	Utils.ajax(url, true, data, successCallback, null);
}

// 加载同类案件
function loadSimilarCase(curr) {
	var url = "/ctuiinspection/case/loadSimilarCase";
	var page_num = curr;
	var search_word = document.getElementById('search-input').value;
	var data = {
		search_word : search_word,
		page_num : page_num
	};
	var successCallback = function(data) {
		casesData = data.detail.cases;
		// totalCount 总数量 showCount 展现的下标 limit 一页展现的数量
		var totalCount = data.page.total;
		var showCount = 5;
		var limit = data.page.perPage;
		CasecreateTable(1, limit, totalCount, data.detail.cases);
		//默认加载第一个案件的关系图
		loadCaseRelationGraph(casesData[0].caseId);
		$('#case-pagination').extendPagination({
			totalCount : totalCount,
			showCount : showCount,
			limit : limit,
			callback : function(curr, limit, totalCount) {
				var url = "/ctuiinspection/case/loadSimilarCase";
				var page_num = curr;
				var search_word = document.getElementById('search-input').value;
				var data = {
					search_word : search_word,
					page_num : page_num
				};
				var successCallback = function(data) {
					casesData = data.detail.cases;
					CasecreateTable(curr, limit, totalCount, data.detail.cases);
					//默认加载第一个案件的关系图
					loadCaseRelationGraph(casesData[0].caseId);
				}
				Utils.ajax(url, true, data, successCallback, null);
			}
			
		});
	};
	Utils.ajax(url, true, data, successCallback, null);
}
// 加载推荐律师
function loadRecommendedLawyer(page_num, case_id) {
	var url = "/ctuiinspection/lawyer/loadRecommendedLawyer";
	var data = {
		case_id : case_id,
		page_num : page_num
	};
	
	var lawyerData = [];
	var successCallback = function(data) {
		lawyerData = data.detail.lawyers;
		// totalCount 总数量 showCount 展现的下标 limit 一页展现的数量
		var totalCount = data.page.total;
		var showCount = 5;
		//var limit = data.page.perPage;
		var limit = 10;
		LAWYERID=[];
		RecommendcreateTable(1, limit, totalCount, data.detail.lawyers);
		$('#recommend-pagination').extendPagination({
			totalCount : totalCount,
			showCount : showCount,
			limit : limit,
			callback : function(curr, limit, totalCount) {
				var url = "/ctuiinspection/lawyer/loadRecommendedLawyer";
				var data = {
					case_id : case_id,
					page_num : curr
				};
				var successCallback = function(data){
					LAWYERID=[];
					RecommendcreateTable(curr, limit, totalCount, data.detail.lawyers);
				}
				Utils.ajax(url, true, data, successCallback, null);
			}
		});
	};
	Utils.ajax(url, true, data, successCallback, null);
}

// 加载关系图
function loadRelationGraph(name) {
	var url = "/ctuiinspection/lawyer/loadRelationGraph";
	var data = {
		name : name,
	};
	var relateData = [];
	var successCallback = function(data) {
		relateData = data.detail.lawyerRelationship;
		showRelationChart(relateData);
	};
	Utils.ajax(url, true, data, successCallback, null);
}
//加载案件关系图
function loadCaseRelationGraph(caseId) {
	var url = "/ctuiinspection/case/generateCaseRelationship";
	var data = {
		//案件id
		caseId : caseId,
	};
	var successCallback = function(data) {
		var caseRelateData = data.detail.caseRealtionship;
		showCaseRelationChart(caseRelateData);
	};
	Utils.ajax(url, true, data, successCallback, null);
}

//加载雷达图
function loadRadarData(lawyerId){
	var url = "/ctuiinspection/lawyer/loadLawyerRadar";
	var data = {
		//律师id集合
		lawyer_ids : lawyerId,
	};
	var successCallback = function(data) {
		showLawyerRadar(data.detail.lawyerRadars);
	};
	Utils.ajax(url, true, data, successCallback, null);
}