<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="<%=bp%>/dep/bootstrap-3.3.7/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="<%=bp%>/dep/sticky-footer-navbar.css" type="text/css" rel="stylesheet">
<link rel="icon" href="<%=bp%>/img/f.ico" mce_href="<%=bp%>/img/f.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=bp%>/img/f.ico" mce_href="<%=bp%>/img/f.ico" type="image/x-icon">
<link rel="bookmark" href="<%=bp%>/img/f.ico" mce_href="<%=bp%>/img/f.ico" type="image/x-icon">

<title>深度搜索</title>

</head>
<body>
	<!-- Static navbar -->
	<div class="navbar navbar-default" role="navigation" style="height: 40px;">
		<div class="container pull-left">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span>
					</button>
					<a href="<%=bp%>"> <img src="<%=bp%>/img/deepSearch_logo_s.png" class="navbar-brand" /></a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="javascript:;">搜索</a></li>
						<li>
							<li><a href="javascript:;">知识图</a></li>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
				<div></div>
			</div>
			<!--/.container-fluid -->
		</div>
	</div>
	<div class="container">
		<div id="img" style="margin-bottom: 10px; margin-left: -10px; text-align: center;">
			<img id="s_lg_img" src="<%=bp%>/img/deepSearch_logo_index.png">
		</div>
		<div class="form-inline" style="margin: 0 auto; text-align: center;">
			<div class="form-group">
				<input type="text" class="form-control" style="width: 450px" id="word">
			</div>
			<input id="search" type="button" class="btn btn-primary" style="width: 100px;" value="搜索">
		</div>
		<div style="text-align: center">
			<img src="<%=bp%>/img/kw_network.png" style="margin-top: 50px;">
		</div>
	</div>

	<div id="footer">
		<div class="container">
			<p class="text-muted" style="float: right">数据合作 | 关于我们</p>
		</div>
	</div>
	<script src="<%=bp%>/dep/jquery-3.2.1.min.js"></script>
	<script src="<%=bp%>/dep/bootstrap-3.3.7/bootstrap.min.js"></script>
	<script src="<%=bp%>/js/DUtils.js"></script>
	<script src="<%=bp%>/js/index.js"></script>
</body>
</html>