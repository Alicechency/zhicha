$(function() {
	loadGraph($("#word").val());
	$("#search").bind("click",function(){
		var word = $("input[name='word']").val();
		loadGraph(word);
	});
	//搜索嫌疑人上一页与下一页
	$("#preview").bind("click",function(){
		var pageNum = parseInt($("#current").val());
		var sumPage = parseInt($("#sum").val());
		if (1 < pageNum && pageNum <= sumPage) {
			if(suspectIds!=null){
				loadSuspectRelationCases(suspectIds,pageNum-1);
			}
		}
	});
	$("#next").bind("click",function(){
		var pageNum = parseInt($("#current").val());
		var sumPage = parseInt($("#sum").val());
		if (1 <= pageNum && pageNum < sumPage) {
			if(suspectIds!=null){
				loadSuspectRelationCases(suspectIds,pageNum+1);
			}
		}
	});
	
})
//全局变量存储搜索出的嫌疑犯id列表
var suspectIds ;

function loadGraph(word){
	var url = "/iinspection/relation/loadSuspectRelationship";
	var data = {
		word : word,
		firstLimit : 10,
		secondLimit : 5
	};
	var successCallback = function(data) {
		if (data.detail.supervisoryAssociations != null) {
			var categoryName = data.detail.supervisoryAssociations.categoryName;
			var categories = data.detail.supervisoryAssociations.categories;
			var links = data.detail.supervisoryAssociations.links;
			var nodes = data.detail.supervisoryAssociations.nodes;
			suspectIds = data.detail.supervisoryAssociations.suspectIds;
			loadSuspectRelationCases(suspectIds,1);
			JWUtils.graph.createSimpleGraph("importantPerson", categoryName, categories, links, nodes);
		}
	};
	JWUtils.ajax(url, false, data, successCallback, null);
}

function loadSuspectRelationCases(suspectIds,pageNum){
	var url = "/iinspection/suspect/loadSuspectRelationCases";
	var data = {
		suspectIds:suspectIds,
		pageNum:pageNum
	};
	var successCallback = function(data) {
		loadRelatedSuspects(data);
	};
	JWUtils.ajax(url, false, data, successCallback, null);
}

//装载嫌疑人信息
function loadRelatedSuspects( data ){
	$("#current").attr("value",data.detail.pageResult.pageNum);
	$("#sum").attr("value",data.detail.pageResult.pages);
	var pageResult = data.detail.pageResult;
	if (pageResult.sumPage != 0) {
		if (pageResult.pageNum == pageResult.pages) {
			$("#suspectRecordsNums").text(((pageResult.pageNum - 1) * pageResult.pageSize + 1) + "-" + pageResult.total + "/" + pageResult.total);
		} else {
			$("#suspectRecordsNums").text(((pageResult.pageNum - 1) * pageResult.pageSize + 1) + "-" + pageResult.pageNum * pageResult.pageSize + "/" + pageResult.total);
		}
		var records = data.detail.pageResult.list;
		var tbody = $("#suspectsList");
		tbody.children().remove();
		tbody.append("<table class='table'></table>");
		var tcontent = $("#suspectsList > table");
		if (records != null && records.length != 0) {
			for (var i = 0; i < records.length; i++) {
				tcontent.append("<tr></tr>");
				var tr = $("#suspectsList table >tr").eq(i);
				tr.append("<td width='10%' ><span class='label "+getClassType(records[i].warningLevel)+"'>"+records[i].warningLevel+"</span></td>");
				tr.append("<td width='20%'><a href='javascript:;'>"+records[i].importantPersonName+"</a></td>");
				tr.append("<td class='mailbox-date' width='70%'><a href='javascript:;'>"+records[i].importantPersonDetail+"</a></td>");
			}
		}
	}else {
		$("#suspectRecordsNums").text("");
		var tbody = $("#suspectsList");
		tbody.children().remove();
		tbody.append("<table class='table'><tr><td><span>暂无信息!</span></td></tr></table>");
	}
}

//犯罪级别与颜色转换
function getClassType(num){
	switch(num)
	{
	case 1:
		return 'label-primary';
	  break;
	case 2:
		return 'definecolor';
	  break;
	case 3:
		return 'label-danger';
	  break;
	case 4:
		return 'label-warning';
	  break;
	default:
		return 'label-danger'
	}
}