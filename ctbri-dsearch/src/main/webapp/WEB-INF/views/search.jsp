<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String bp = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="icon" href="<%=bp%>/img/f.ico" mce_href="<%=bp%>/img/f.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=bp%>/img/f.ico" mce_href="<%=bp%>/img/f.ico" type="image/x-icon">
<link rel="bookmark" href="<%=bp%>/img/f.ico" mce_href="<%=bp%>/img/f.ico" type="image/x-icon">
<link href="<%=bp%>/dep/bootstrap-3.3.7/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="<%=bp%>/dep/sticky-footer-navbar.css" type="text/css" rel="stylesheet">
<link href="<%=bp%>/css/search.css" type="text/css" rel="stylesheet">

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
					<a href="/dsearch"> <img src="<%=bp%>/img/deepSearch_logo_s.png" class="navbar-brand" />
					</a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="/dsearch">搜索</a></li>
						<li><a href="javascript:;">知识图</a></li>
					</ul>
					<div class="navbar-form" >
						<input type="text" class="form-control" style="width: 420px" id="word" placeholder="${word}">
						<button class="btn btn-primary" style="width: 100px">搜索</button>
					</div>
				</div>
				<!--/.nav-collapse -->
				<div></div>
			</div>
			<!--/.container-fluid -->
		</div>
	</div>

	<div class="container">
		<!-- 左侧显示部分 -->
		<div id="result" class="col-lg-8">
			<div class="result c-container" style="margin-left: 5%;">
				<div class="row">
					<h3 class="col-md-9 col-sm-8">
						<a href="javascript:;" target="_blank">题目</a>
					</h3>
				</div>
				<div>
					<ul class="wrap">
						<li>
							<div class="content all-content" style="display: none;">
								<div class="c-abstract">内容1</div>
							</div>
							<div class="content part-content" style="height: 42px; overflow: hidden;">
								<div class="c-abstract">内容1</div>
							</div>
							<div class="label">
								<span> 相关标签:</span>
								<a href="javascript:;" target="_blank" class="tag">asdsad</a>
							</div>
							<div class="sign unfold" style="display: inline">更多...</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div id="content_right" class="cr-content col-lg-8" style="width: 32%; margin-top: 0%;">
			<div id="person1" style="height: 250px; width: 300px; margin-top: 10px;"></div>
			<div class="row" style="width: 100%" style="position:relative;">
				<div class="text-center">
					<div class="col-lg-9 text-center">
						<h4 class="section-heading">相关公众号</h4>
						<table class="table table-hover">
							<tr><td align="left"><a target="_blank" href="">公众号1</a></td></tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer" class="col-lg-12">
		<div class="container">
			<p class="text-muted" style="float: right">数据合作 | 关于我们</p>
		</div>
	</div>

	<script src="<%=bp%>/dep/jquery-3.2.1.min.js"></script>
	<script src="<%=bp%>/dep/bootstrap-3.3.7/bootstrap.min.js"></script>
	<script src="<%=bp%>/dep/echarts-3.5.3/echarts.min.js"></script>
	<script src="<%=bp%>/js/DUtils.js"></script>
	<script src="<%=bp%>/js/search.js"></script>
</body>