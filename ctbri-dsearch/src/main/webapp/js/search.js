$(function(){
	
	//初始化加载查询信息
	initLoadSearchResult();
	
});

function initLoadSearchResult(){
	var word = $("#word").val();
	if (word != null && word != "") {
		var url = "/dsearch/search/simpleSearch";
		var data = {
			word : word	
		};
		var successCallback = function(data){
			
		};
		JWUtils.ajax(url, true, data, successCallback, null);
	}
}