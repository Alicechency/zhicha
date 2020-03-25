<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en" style="height: 100%">
<head>
<meta charset="UTF-8">
<title>智察-成都</title>
<link rel="stylesheet" href="<%=bp%>/css/index.css">
<link rel="stylesheet" href="<%=bp%>/dep/bootstrap.min.css">
</head>
<body>

<div class="center-container">
            <div class="center-block">
                <img src="<%=bp%>/img/logo.png" alt="logo" >
                <p style="font-size: 24px">智察-成都</p>

                <div class="input-group" >
                    <input id="searchBar" type="text" class="form-control" >
                    <span class="input-group-btn">
                            <button id="searchButton" class="btn btn-default" type="button" style="color:white">
                               搜索
                            </button>
                    </span>
                </div>
            </div>
    </div>

    <img id="bg" src="<%=bp%>/img/index-background.png" alt=""  >
    <div style="text-align: center; width: 100%; position:fixed; bottom: 10px">
        <img src="<%=bp%>/img/logo.png" alt="" width="20px" height="20px"> <span style="font-size: 12px">Make AI Breakthrough</span>
    </div>

</body>
<script src="<%=bp%>/dep/jquery-2.1.1.js"></script>
<script type="text/javascript">
	$(function(){
		$("#searchButton").bind("click",function(){
			var searchWord = $("#searchBar").val();
			console.log()
			window.location.href="/ctuiinspection/forward/searchInfo?searchWord="+searchWord;
		});
	});
	
	$('#searchBar').keydown(function(event) {
	    if (event.keyCode === 13) {
	        $('#searchButton').click();
	      	//阻止浏览器的默认行为
		    event.preventDefault();
	    }
	});
</script>
</html>