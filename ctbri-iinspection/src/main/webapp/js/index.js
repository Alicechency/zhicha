$(function() {
	// 加载案件数字信息
	loadCaseCount();
	// 加载嫌疑人数字信息
	loadSuspectCount();
	//加载标签组
	loadTags();
	//初始化加载所有案件信息
	loadAllCases(1);
	//案件点击事件
	$("#showCases").bind("click",function(){
		loadAllCases(1);
	});
	/*//加载未读信件
	$("#loadUndoCase").bind("click",function(){
		loadUndoCase(1);
	});*/
	//加载等级信件
	$("a[name='loadCase']").each(function(){
		$(this).bind("click",function(){
			var level = $(this).attr("level");
			loadLevelCases(1,level);
		});
	});
	//查询
	$("#search").bind("click",function(){
		loadSearch(1);
	});
	//上一页
	$("#preview").bind("click",function() {
		var pageNum = parseInt($("#current").val());
		var sumPage = parseInt($("#sum").val());
		if (1 < pageNum && pageNum <= sumPage) {
			var flag = $("#flag").val();
			if ("allCases" == flag) {
				loadAllCases(pageNum - 1);
			} else if ("undo" == flag) {
				loadUndoCase(pageNum - 1);
			} else if ("level" == flag) {
				var level = $("#level").val();
				loadLevelCases(pageNum - 1, level);
			} else if ("tag" == flag) {
				var tagName = $("#tagName").val();
				loadTagCases(pageNum - 1, tagName);
			} else if ("search" == flag) {
				loadSearchCases(pageNum - 1);
			} else if ("allSuspects" == flag) {
				loadAllSuspects(pageNum - 1);
			} else if ("warning" == flag) {
				var level = $("#level").val();
				loadWarningLevelSuspect(pageNum - 1, level);
			}
		}
	});
	//下一页
	$("#next").bind("click",function(){
		var pageNum = parseInt($("#current").val());
		var sumPage = parseInt($("#sum").val());
		if (1 <= pageNum && pageNum < sumPage) {
			var flag = $("#flag").val();
			if ("allCases" == flag) {
				loadAllCases(pageNum + 1);
			} else if ("undo" == flag) {
				loadUndoCase(pageNum + 1);
			} else if ("level" == flag) {
				var level = $("#level").val();
				loadLevelCases(pageNum + 1, level);
			} else if ("tag" == flag) {
				var tagName = $("#tagName").val();
				loadTagCases(pageNum + 1, tagName);
			} else if ("search" == flag) {
				loadSearchCases(pageNum + 1);
			} else if ("allSuspects" == flag) {
				loadAllSuspects(pageNum + 1);
			} else if ("warning" == flag) {
				var level = $("#level").val();
				loadWarningLevelSuspect(pageNum + 1, level);
			}
		}
	});
	//搜索案情上一页与下一页
	$("#searchCasePreview").bind("click",function(){
		var pageNum = parseInt($("#current").val());
		var sumPage = parseInt($("#sum").val());
		if (1 < pageNum && pageNum <= sumPage) {
			searchCases(pageNum - 1);
		}
	});
	$("#searchCaseNext").bind("click",function(){
		var pageNum = parseInt($("#current").val());
		var sumPage = parseInt($("#sum").val());
		if (1 <= pageNum && pageNum < sumPage) {
			searchCases(pageNum + 1);
		}
	});
	//搜索嫌疑人上一页与下一页
	$("#searchSuspectPreview").bind("click",function(){
		var pageNum = parseInt($("#current").val());
		var sumPage = parseInt($("#sum").val());
		if (1 < pageNum && pageNum <= sumPage) {
			searchSuspects(pageNum - 1);
		}
	});
	$("#searchSuspectNext").bind("click",function(){
		var pageNum = parseInt($("#current").val());
		var sumPage = parseInt($("#sum").val());
		if (1 <= pageNum && pageNum < sumPage) {
			searchSuspects(pageNum + 1);
		}
	});
	//嫌疑人点击事件
	$("#showAllSuspects").bind("click",function(){
		loadAllSuspects(1);
		$("#flag").attr("value","allSuspects");
	});
	//预警级别点击事件
	$("li[name='showWarning']").bind("click",function(){
		var level = $(this).attr("level");
		loadWarningLevelSuspect(1,level);
		$("#flag").attr("value","warning");
		$("#level").attr("value",level);
	});
	
});

/**
 * 装载查询结果集
 * 
 * @param word
 * @returns
 */
function loadSearch(pageNum) {
	$("#messagesDetail").hide();
	$("#suspectDetail").hide();
	$("#messages").hide();
	searchCases(pageNum);
	searchSuspects(pageNum);
	$("#searchInfo").show();
}

/**
 * 查询案情
 * 
 * @param pageNum
 * @returns
 */
function searchCases(pageNum){
	var word = $("#word").val();
	if (word != null && word != "") {
		//查询案情
		var url = "/iinspection/case/searchCases";
		var data = {
			pageNum : pageNum,
			word : word	
		};
		var successCallback = function(data){
			$("#current").attr("value",data.detail.pageResult.currentPage);
			$("#sum").attr("value",data.detail.pageResult.sumPage);
			//装载搜索案情
			var pageResult = data.detail.pageResult;
			if (pageResult.sumPage != 0) {
				if (pageResult.currentPage == pageResult.sumPage) {
					$("#caseRecordsNums").text(((pageResult.currentPage - 1) * pageResult.perPage + 1) + "-" + pageResult.total + "/" + pageResult.total);
				} else {
					$("#caseRecordsNums").text(((pageResult.currentPage - 1) * pageResult.perPage + 1) + "-" + pageResult.currentPage * pageResult.perPage + "/" + pageResult.total);
				}
				var cases = pageResult.records;
				var tbody = $("#searchCases");
				tbody.children().remove();
				if (cases != null && cases.length != 0) {
					for (var i = 0; i < cases.length; i++) {
						if(cases[i] != null){
							tbody.append("<tr></tr>");
							var tr = $("#searchCases>tr").eq(i);
							tr.append("<td><div class='icheckbox_flat-blue' aria-checked='false' aria-disabled='false' style='position: relative;'> <input style='position: absolute; opacity: 0;' type='checkbox'> <ins class='iCheck-helper' style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);'></ins></div></td>");
							tr.append("<td><a href='javascript:;'>"+cases[i].author+"</a></td>");
							tr.append("<td><a href='javascript:;' name='showDetail' caseId='"+cases[i].id+"'>"+cases[i].title+"</a></td>");
							tr.append("<td class='mailbox-attachment'></td>");
							tr.append("<td class='mailbox-date' width='10%'>"+cases[i].level+"</td>");
						}
					}
				}
				$("a[name='showDetail']").each(function(){
					$(this).bind("click",function(){
						var caseId = $(this).attr("caseId");
						
						//装载信件详情
						loadCaseDetail(caseId);
						//加载案情简介
						loadCaseBrief(caseId);
						//加载图表信息
						loadCaseChart(caseId);
						
						//加载多证据指向
						loadCaseRelationShip(caseId);
						//
						//关闭详情事件
						$("#hideCaseDetail").unbind("click");
						$("#hideCaseDetail").bind("click",function(){
							$("#messagesDetail").hide();
							$("#searchInfo").show();
						});
						$("#messages").hide();
						$("#searchInfo").hide();
						$("#suspectDetail").hide();
						$("#messagesDetail").show();
					});
				});
			} else {
				$("#caseRecordsNums").text("");
				var tbody = $("#searchCases");
				tbody.children().remove();
				tbody.append("<tr><td><span>暂无信息!</span></td></tr>");
			}
		};
		JWUtils.ajax(url, true, data, successCallback, null);
	}
}

/**
 * 查询嫌疑人
 * 
 * @param pageNum
 * @returns
 */
function searchSuspects(pageNum){
	var word = $("#word").val();
	if (word != null && word != "") {
		//查询案情
		var url = "/iinspection/suspect/searchSuspects";
		var data = {
			pageNum : pageNum,
			word : word	
		};
		var successCallback = function(data){
			$("#current").attr("value",data.detail.pageResult.pageNum);
			$("#sum").attr("value",data.detail.pageResult.pages);
			var pageInfo = data.detail.pageResult;
			if (pageInfo.pages != 0) {
				if (pageInfo.pages == pageInfo.pageNum) {
					$("#suspectRecordsNums").text(((pageInfo.pageNum - 1) * pageInfo.pageSize + 1) + "-" + pageInfo.total + "/" + pageInfo.total);
				} else {
					$("#suspectRecordsNums").text(((pageInfo.pageNum - 1) * pageInfo.pageSize + 1) + "-" + pageInfo.pageNum * pageInfo.pageSize + "/" + pageInfo.total);
				}
				var suspects = data.detail.pageResult.list;
				var tbody = $("#searchSuspects");
				tbody.children().remove();
				if (suspects.length != 0) {
					for (var i = 0; i < suspects.length; i++) {
						tbody.append("<tr></tr>");
						var tr = $("#searchSuspects>tr").eq(i);
						tr.append("<td><div class='icheckbox_flat-blue' aria-checked='false' aria-disabled='false' style='position: relative;'> <input style='position: absolute; opacity: 0;' type='checkbox'> <ins class='iCheck-helper' style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);'></ins></div></td>");
						tr.append("<td><a href='javascript:;'></a></td>");
						tr.append("<td><a href='javascript:;' name='showSuspectDetail' suspectId='"+suspects[i].id+"'>"+suspects[i].importantPersonName+"</a></td>");
						tr.append("<td><a href='javascript:;' name='showSuspectDetail' suspectId='"+suspects[i].id+"'>"+suspects[i].importantPersonNo+"</a></td>");
						tr.append("<td><a href='javascript:;' name='showSuspectDetail' suspectId='"+suspects[i].id+"'>"+suspects[i].importantPersonDetail+"</a></td>");
						tr.append("<td class='mailbox-attachment'></td>");
						tr.append("<td class='mailbox-date' width='10%'>"+suspects[i].warningInfo+"</td>");
					}
				}
				$("a[name='showSuspectDetail']").each(function(){
					$(this).bind("click",function(){
						var suspectId = $(this).attr("suspectId");
						var suspectName = $(this).text(); 
						//加载嫌疑人详情信息
						loadSuspectDetail(suspectId);
						//加载关联人员
						loadSuspectRelationship(suspectName);
						//关闭详情事件
						$("#hideSuspectDetail").unbind("click");
						$("#hideSuspectDetail").bind("click",function(){
							$("#suspectDetail").hide();
							$("#searchInfo").show();
						});
						$("#messages").hide();
						$("#searchInfo").hide();
						$("#messagesDetail").hide();
						$("#suspectDetail").show();
					});
				});
			} else {
				$("#suspectRecordsNums").text("");
				var tbody = $("#searchSuspects");
				tbody.children().remove();
				tbody.append("<tr><td><span>暂无信息!</span></td></tr>");
			}
		}
		JWUtils.ajax(url, true, data, successCallback, null);
	}
}

/**
 * 装载等级信件信息
 * 
 * @param pageNum
 * @param level
 * @returns
 */
function loadLevelCases(pageNum,level){
	var url = "/iinspection/case/loadLevelCases";
	var data = {
		pageNum : pageNum,
		level : level
	};
	var successCallback = function(data){
		loadCaseDatas(data);
		$("#flag").attr("value","level");
		$("#level").attr("value",level);
	};
	JWUtils.ajax(url, true, data, successCallback, null);
}

/**
 * 装载未处理案件信息
 * 
 * @param pageNum
 * @returns
 */
function loadUndoCase(pageNum){
	var url = "/JWLetter/case/loadUndoCases";
	var data = {
		pageNum : pageNum
	};
	var successCallback = function(data){
		loadCaseDatas(data);
		$("#flag").attr("value","undo");
	};
	JWUtils.ajax(url, true, data, successCallback, null);
}

/**
 * 加载标签组
 */
function loadTags(){
	var url = "/iinspection/case/loadTags";
	var successCallback = function(data){
		var tags = data.detail.tags;
		var tagUl = $("#tags");
		tagUl.children().remove();
		if (tags != null && tags.length != 0) {
			for (var i = 0; i < tags.length; i++) {
				tagUl.append("<li><a href='javascript:;' tagName='"+tags[i].tagName+"' name='loadTagCases'><i class='"+tags[i].tagClass+"'></i><font>"+tags[i].tagName+"</font></a></li>");
			}
		}
		$("a[name='loadTagCases']").each(function(){
			$(this).bind("click",function(){
				var tagName = $(this).attr("tagName");
				loadTagCases(1,tagName);
			});
		});
	};
	JWUtils.ajax(url, true, null, successCallback, null);
}

/**
 * 装载标签案件信息
 * 
 * @param pageNum
 * @param tagName
 * @returns
 */
function loadTagCases(pageNum, tagName) {
	var url = "/iinspection/case/loadTagCases";
	var data = {
		pageNum : pageNum,
		tagName : tagName
	};
	var successCallback = function(data) {
		loadCaseDatas(data);
		$("#flag").attr("value", "tag");
		$("#tagName").attr("value", tagName);
	};
	JWUtils.ajax(url, true, data, successCallback, null);
}

/**
 * 加载所有案件
 * 
 * @param pageNum
 * @returns
 */
function loadAllCases(pageNum){
	var url = "/iinspection/case/loadAllCases";
	var data = {
		pageNum : pageNum
	};
	var successCallback = function(data){
		loadCaseDatas(data);
		$("#flag").attr("value","allCases");
	};
	JWUtils.ajax(url, false, data, successCallback, null);//更改为同步请求方式
}

/**
 * 加载案件数字信息
 * 
 * @returns
 */
function loadCaseCount() {
	var url = "/iinspection/case/loadCaseCounts";
	var successCallback = function(data) {
		var caseCount = data.detail.caseCount;
		var levelOneCaseCount = data.detail.levelOneCaseCount;
		var levelTwoCaseCount = data.detail.levelTwoCaseCount;
		var levelThreeCaseCount = data.detail.levelThreeCaseCount;
		var levelFourCaseCount = data.detail.levelFourCaseCount;
		$("#caseCount").text(caseCount);
		$("#levelOneCaseCount").text(levelOneCaseCount);
		$("#levelTwoCaseCount").text(levelTwoCaseCount);
		$("#levelThreeCaseCount").text(levelThreeCaseCount);
		$("#levelFourCaseCount").text(levelFourCaseCount);
	};
	JWUtils.ajax(url, true, null, successCallback, null);
}

/**
 * 加载嫌疑人数字信息
 * 
 * @returns
 */
function loadSuspectCount() {
	var url = "/iinspection/suspect/loadAllSuspectCounts";
	var successCallback = function(data) {
		var suspectCount = data.detail.suspectCount;
		var suspectCountBlue = data.detail.suspectCountBlue;
		var suspectCountYellow = data.detail.suspectCountYellow;
		var suspectCountRed = data.detail.suspectCountRed;
		var suspectCountOrange = data.detail.suspectCountOrange;
		$("#suspectCount").text(suspectCount);
		$("#suspectCountBlue").text(suspectCountBlue);
		$("#suspectCountYellow").text(suspectCountYellow);
		$("#suspectCountRed").text(suspectCountRed);
		$("#suspectCountOrange").text(suspectCountOrange);
	};
	JWUtils.ajax(url, true, null, successCallback, null);
}

/**
 * 加载所有嫌疑人信息
 * 
 * @param pageNum
 * @returns
 */
function loadAllSuspects(pageNum) {
	var url = "/iinspection/suspect/loadAllSuspects";
	var data = {
		pageNum : pageNum
	};
	var successCallback = function(data) {
		loadSuspectDatas(data);
		$("#flag").attr("value", "allSuspects");
	};
	JWUtils.ajax(url, false, data, successCallback, null);// 更改为同步请求方式
}

/**
 * 加载不同级别嫌疑人
 * 
 * @param pageNum
 * @param warningLevel
 * @returns
 */
function loadWarningLevelSuspect(pageNum, warningLevel) {
	var url = "/iinspection/suspect/loadWarningLevelSuspects";
	var data = {
		pageNum : pageNum,
		warningLevel : warningLevel
	};
	var successCallback = function(data) {
		loadSuspectDatas(data);
		$("#flag").attr("value", "warning");
		$("#level").attr("value", warningLevel);
	};
	JWUtils.ajax(url, false, data, successCallback, null);
}

/**
 * 加载案情信息
 * 
 * @param data
 * @returns
 */
function loadCaseDatas(data){
	// 隐藏信息
	$("#messagesDetail").hide();
	$("#suspectDetail").hide();
	$("#searchInfo").hide();
	//修改名称
	$("#messageTitle").text("所有警情")
	$("#current").attr("value",data.detail.pageResult.currentPage);
	$("#sum").attr("value",data.detail.pageResult.sumPage);
	var pageResult = data.detail.pageResult;
	if (pageResult.sumPage != 0) {
		if (pageResult.currentPage == pageResult.sumPage) {
			$("#recordsNums").text(((pageResult.currentPage - 1) * pageResult.perPage + 1) + "-" + pageResult.total + "/" + pageResult.total);
		} else {
			$("#recordsNums").text(((pageResult.currentPage - 1) * pageResult.perPage + 1) + "-" + pageResult.currentPage * pageResult.perPage + "/" + pageResult.total);
		}
		var cases = pageResult.records;
		var tbody = $("#datas");
		tbody.children().remove();
		if (cases != null && cases.length != 0) {
			for (var i = 0; i < cases.length; i++) {
				if(cases[i] != null){
					tbody.append("<tr></tr>");
					var tr = $("#datas>tr").eq(i);
					tr.append("<td class='col-md-1'></td>")
					tr.append("<td class='col-md-2'><a href='javascript:;'>"+cases[i].author+"</a></td>");
					tr.append("<td><a href='javascript:;' name='showDetail' caseId='"+cases[i].id+"'>"+cases[i].title+"</a></td>");
					tr.append("<td class='mailbox-attachment'></td>");
					tr.append("<td class='mailbox-date' width='10%'>"+cases[i].level+"</td>");
				}
			}
		}
		$("a[name='showDetail']").each(function(){
			$(this).bind("click",function(){
				var caseId = $(this).attr("caseId");
				
				//装载信件详情
				loadCaseDetail(caseId);
				//加载案情简介
				loadCaseBrief(caseId);
				//加载图表信息
				loadCaseChart(caseId);
				
				//加载多证据指向
				loadCaseRelationShip(caseId);
				//关闭详情事件
				$("#hideCaseDetail").unbind("click");
				$("#hideCaseDetail").bind("click",function(){
					$("#messagesDetail").hide();
					$("#messages").show();
				});
				$("#messages").hide();
				$("#searchInfo").hide();
				$("#suspectDetail").hide();
				$("#messagesDetail").show();
			});
		});
	} else {
		$("#recordsNums").text("");
		var tbody = $("#datas");
		tbody.children().remove();
		tbody.append("<tr><td><span>暂无信息!</span></td></tr>");
	}
	$("#messages").show();
}

/**
 * 加载嫌疑人信息
 * 
 * @param data
 * @returns
 */
function loadSuspectDatas(data){
	// 隐藏信息
	$("#messagesDetail").hide();
	$("#suspectDetail").hide();
	$("#searchInfo").hide();
	//修改名称
	$("#messageTitle").text("常控人员")
	$("#current").attr("value",data.detail.pageResult.pageNum);
	$("#sum").attr("value",data.detail.pageResult.pages);
	var pageResult = data.detail.pageResult;
	if (pageResult.sumPage != 0) {
		if (pageResult.pageNum == pageResult.pages) {
			$("#recordsNums").text(((pageResult.pageNum - 1) * pageResult.pageSize + 1) + "-" + pageResult.total + "/" + pageResult.total);
		} else {
			$("#recordsNums").text(((pageResult.pageNum - 1) * pageResult.pageSize + 1) + "-" + pageResult.pageNum * pageResult.pageSize + "/" + pageResult.total);
		}
		var suspects = pageResult.list;
		var tbody = $("#datas");
		tbody.children().remove();
		if (suspects.length != 0) {
			for (var i = 0; i < suspects.length; i++) {
				tbody.append("<tr></tr>");
				var tr = $("#datas>tr").eq(i);
				tr.append("<td class='col-md-1'></td>");
				tr.append("<td class='col-md-2'><a href='javascript:;' name='showSuspectDetail' suspectId='"+suspects[i].id+"'>"+suspects[i].importantPersonName+"</a></td>");
				tr.append("<td class='col-md-3'><a href='javascript:;'>"+suspects[i].importantPersonNo+"</a></td>");
				tr.append("<td><a href='javascript:;'>"+suspects[i].importantPersonDetail+"</a></td>");
				tr.append("<td class='mailbox-attachment'></td>");
				tr.append("<td class='mailbox-date' width='10%'>"+suspects[i].warningInfo+"</td>");
			}
		}
		$("a[name='showSuspectDetail']").each(function(){
			$(this).bind("click",function(){
				var suspectId = $(this).attr("suspectId");
				var suspectName = $(this).text(); 
				//加载嫌疑人详情信息
				loadSuspectDetail(suspectId);
				//加载关联人员
				loadSuspectRelationship(suspectName);
				//关闭详情事件
				$("#hideSuspectDetail").unbind("click");
				$("#hideSuspectDetail").bind("click",function(){
					$("#suspectDetail").hide();
					$("#messages").show();
				});
				$("#messages").hide();
				$("#searchInfo").hide();
				$("#messagesDetail").hide();
				$("#graphInfo").hide();
				$("#suspectDetail").show();
			});
		});
	} else {
		$("#recordsNums").text("");
		var tbody = $("#datas");
		tbody.children().remove();
		tbody.append("<tr><td><span>暂无信息!</span></td></tr>");
	}
	$("#messages").show();
}

/**
 * 
 * @returns
 */
function loadSuspectDetail(suspectId){
	var url = "/iinspection/suspect/loadSuspectDetail";
	var data = {
		suspectId : suspectId
	};
	var successCallback = function(data) {
		//装载嫌疑人详情信息
		if (data.detail.suspectDetail != null) {
			var suspect = data.detail.suspectDetail.suspect;
			$("#suspectInfo").children().remove();;
			$("#suspectInfo").append("<li>重点人员姓名："+suspect.importantPersonName+"</li>");
			$("#suspectInfo").append("<li>重点人员编号："+suspect.importantPersonNo+"</li>");
			$("#suspectInfo").append("<li>重点人员细类："+suspect.importantPersonDetail+"</li>");
			$("#suspectInfo").append("<li>公民身份证号码："+suspect.identityNo+"</li>");
			$("#suspectInfo").append("<li>预警级别："+suspect.warningInfo+"</li>");
			var detail = suspect.suspectDetails;
			if(detail != null){
				var tbody = $("#tracks");
				tbody.children().remove();
				if (detail.length != 0) {
					for (var i = 0; i < detail.length; i++) {
						tbody.append("<tr></tr>");
						var tr = $("#tracks>tr").eq(i);
						tr.append("<td style='width:184px;'>"+detail[i].activityDate+"</td>");
						tr.append("<td style='width:233px;'>"+detail[i].activityLocation+"</td>");
						tr.append("<td style='width:180px;'>"+detail[i].socialLocation+"</td>");
						tr.append("<td style='width:176px;'>"+detail[i].activityDetail+"</td>");
					}
				}
			$("#suspectBaseInfo").hide();
			} else {
				var tbody = $("#tracks");
				tbody.children().remove();
				tbody.append("<td>暂无相关信息!</td>");
			}
			//装载嫌疑人轨迹信息
			if (data.detail.suspectDetail.coords != null 
					&& data.detail.suspectDetail.coords.length > 1 
					&& data.detail.suspectDetail.positions != null) {
				initTraceMap(data.detail.suspectDetail.coords, data.detail.suspectDetail.positions);
				$("#trace-graph").show();
			} else {
				$("#trace-graph").hide();
			}
		} else {
			$("ul[name='suspectInfo']").each(function(){
				$(this).children().remove();;
				$(this).append("<li>暂无相关信息!</li>");
			});
		}
	};
	JWUtils.ajax(url, true, data, successCallback, null);
}

/**
 * 装载案情详情信息
 * 
 * @param caseId
 * @returns
 */
function loadCaseDetail(caseId){
	var url = "/iinspection/case/loadCaseDetail";
	var data = {
		caseId : caseId
	};
	var successCallback = function(data){
		var cas = data.detail.cas;
		$("#messagesTitle > h3").text(cas.title);
		$("#content").html(cas.content);
	};
	JWUtils.ajax(url, true, data, successCallback, null);//加载案情详情
}

/**
 * 加载案情简要信息
 * 
 * @param caseId
 * @returns
 */
function loadCaseBrief(caseId){
	var url = "/iinspection/case/loadCaseBrief";
	var data = {
		caseId : caseId
	};
	var successCallback = function(data){
		var cas = data.detail.cas;
		if(cas!=null && cas.length!=0){
			$("#case_brief").show();
			$("#case_brief").html(cas.crimeBrief);
			$("#caseBriefAndCaseChart").val('true');
			$("#case_brief_all").attr("hasContent","true");
		}else{
			$("#case_brief").hide();
			if($("#caseBriefAndCaseChart") == false){
				$("#caseBriefAndCaseChart").val('false') ;	
			}
		}
		checkCaseContent();
	};
	JWUtils.ajax(url, true, data, successCallback, null);//加载案情详情
}

/**
 * 加载案情时间图表信息
 * 
 * @param caseId
 */
function loadCaseChart(caseId){
	var url = "/iinspection/case/loadCaseChart";
	var data = {
		caseId : caseId
	};
	var successCallback = function(data){
		var caseChart = data.detail.caseChart;
		if(caseChart!=null && caseChart.length){
			$("#case_chart").show();
			$("#case_chart").html(caseChart.length);
			var hours = new Array() ;
			var nums = new Array();
			for(var i=0;i<caseChart.length;i++){
				hours[i] = caseChart[i].byHour;
				nums[i] = caseChart[i].sumByHourCount;
			}
			if(hours !=null && nums != null){
				loadChartInfo(hours,nums);
			}
			$("#case_brief_all").attr("hasContent","true");
		}else{
			$("#case_chart").hide();
			if($("#caseBriefAndCaseChart") == false){
				$("#caseBriefAndCaseChart").val('false') ;	
			}
		}
		checkCaseContent();
	};
	JWUtils.ajax(url, true, data, successCallback, null);
}

function loadChartInfo(hours,nums){
	
	var myChart = echarts.init(document.getElementById('case_chart'));
	myChart.title = '警情时间信息';

	option = {
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : hours,
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'直接访问',
	            type:'bar',
	            //barWidth: '60%',
	            barWidth: 30,//固定柱子宽度
	            data:nums
	        }
	    ]
	};
	myChart.setOption(option);
	
}

/***
 * 检查内容
 */
function checkCaseContent(){
	
	if($("#caseBriefAndCaseChart").val() =="true"){
		$("#case_brief_all").show();
		$("#caseBriefAndCaseChart").val() =="false";
	}else{
		$("#case_brief_all").hide();
		$("#caseBriefAndCaseChart").val() =="false";
	}
	
}

/**
 * 加载案情多证据指向
 * 
 * @param caseId
 * @returns
 */
function loadCaseRelationShip(caseId) {
	var url = "/iinspection/map/loadCaseRelationShip";
	var data = {
		id : caseId,
	};
	var successCallback = function(data) {
		if (data.detail.crimeRelationships != null) {
			var myChart = echarts.init(document.getElementById("let"));
			var relationData = data.detail.crimeRelationships;
			var option = createRelationGraphOption(relationData);
			myChart.setOption(option);
			$("#let").show();
		} else {
			$("#let").hide();
			$("#multiple_evidence").hide();
		}
	};
	JWUtils.ajax(url, true, data, successCallback, null);
}

/**
 * 加载关联人员
 * 
 * @param suspectName
 * @returns
 */
function loadSuspectRelationship(suspectName) {
	var url = "/iinspection/relation/loadSuspectRelationship";
	var data = {
		word : suspectName,
		firstLimit : 5,
		secondLimit : 3
	};
	var successCallback = function(data) {
		if (data.detail.supervisoryAssociations != null) {
			var categoryName = data.detail.supervisoryAssociations.categoryName;
			var categories = data.detail.supervisoryAssociations.categories;
			var links = data.detail.supervisoryAssociations.links;
			var nodes = data.detail.supervisoryAssociations.nodes;
			JWUtils.graph.createSimpleGraph("importantPerson", categoryName, categories, links, nodes, graphClickFun);
			$("#importantPerson").show();
		} else {
			$("#importantPerson").hide();
			$("#important_person").hide();
		}
	};
	JWUtils.ajax(url, true, data, successCallback, null);
}

/**
 * 关联图的点击事件
 * 
 * @param params
 * @returns
 */
function graphClickFun(params) {
	var url = "/iinspection/suspect/loadSuspectBaseInfo";
	var data = {
		id : params.data.value
	};
	var successCallback = function(data) {
		var suspect = data.detail.suspect;
		$("#suspectBaseInfo").children().remove();;
		$("#suspectBaseInfo").append("<li>重点人员姓名："+suspect.importantPersonName+"</li>");
		$("#suspectBaseInfo").append("<li>重点人员编号："+suspect.importantPersonNo+"</li>");
		$("#suspectBaseInfo").append("<li>重点人员细类："+suspect.importantPersonDetail+"</li>");
		$("#suspectBaseInfo").append("<li>公民身份证号码："+suspect.identityNo+"</li>");
		$("#suspectBaseInfo").append("<li>预警级别："+suspect.warningInfo+"</li>");
		$("#suspectBaseInfo").show();
		$("#graphInfo").show();
	};
	JWUtils.ajax(url, true, data, successCallback, null);
}