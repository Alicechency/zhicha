<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
	<title>智察- 智能关系洞察系统</title>
	<!-- 标题栏图标 -->
    <link rel="icon" href="/iinspection/img/deepSearch_logo_s.png" type="image/x-icon">
    <!-- 收藏夹图标 -->
    <link rel="shortcut icon" href="/iinspection/img/deepSearch_logo_s.png" type="image/x-icon">
	<!-- Bootstrap -->
    <link rel="stylesheet" href="<%=bp%>/dep/bootstrap-3.3.7/bootstrap.min.css">
    <!-- font-awesome -->
    <link rel="stylesheet" href="<%=bp%>/dep/font-awesome-3.2.1.min.css">
    <!-- 导致样式冲突 -->
	<link rel="stylesheet" href="<%=bp%>/dep/AdminLTE.min.css">
	<!-- 暂时没用的 -->
	<link rel="stylesheet" href="<%=bp%>/css/morris.css">
	<link rel="stylesheet" href="<%=bp%>/dep/sticky-footer-navbar.css">
    <link rel="stylesheet" href="<%=bp%>/css/common.css">

	<script src="<%=bp%>/dep/jquery-3.2.1.min.js"></script>
    <script src="<%=bp%>/dep/bootstrap-3.3.7/bootstrap.min.js"></script>
    <script src="<%=bp%>/dep/echarts-3.5.3/echarts.min.js"></script>
	<script src="<%=bp%>/js/macarons.js"></script>
	<script src="<%=bp%>/js/JWUtils.js"></script>
	<script src="<%=bp%>/js/graph.js"></script>
</head>
<body>
	<input id="word" type="hidden" value="${word}">
	<input type="hidden" id="current" value="">
	<input type="hidden" id="sum" value="">
	<div id="nav" class="navbar navbar-default custom-font" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="">
                    <img src="/iinspection/img/deepSearch_logo_s.png" class="navbar-brand"></a>
                <a href="/iinspection" class="navbar-brand page-scroll"> 智察 - 智能关系洞察系统</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/iinspection">警情</a></li>
                    <li><a href="/iinspection/forward/graph">关联</a></li>
                    <li><a href="/iinspection/forward/map">地图</a></li>
                    <li><a href="/iinspection/forward/opinions">舆情</a></li>
                </ul>
                <form action = "/iinspection/forward/graph" method="get">
					<input name="word" type="text" class="form-control pull-left" id="search-input" placeholder="${word}">
					<button type="submit" class="btn btn-primary" id="search-btn">搜索</button>
				</form>
            </div>
        </div>
    </div>
	<div class="container-fluid" style="padding-top: 10px">
		<div class="col-md-3">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">相关嫌疑人信息</h3>
					<div class="pull-right">
						<span id="suspectRecordsNums"></span> 
						<div class="btn-group">
							<button id="preview" type="button" class="btn btn-default btn-sm">
								<i class="glyphicon glyphicon-chevron-left"></i>
							</button>
							<button id="next" type="button" class="btn btn-default btn-sm">
								<i class="glyphicon glyphicon-chevron-right"></i>
							</button>
						</div>
						<!-- /.btn-group -->
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body" id="suspectsList">
					<!-- <p class="text-muted" ><span class="label label-danger">1</span>详情</p> -->
				</div>
				<!-- /.box-body -->
			</div>
		</div>
		<div class="col-md-9" id="map">
			<div class="box-header with-border">
				<h3 class="box-title">关联图谱</h3>
			</div>
			<div id="importantPerson" style="border: 0px; height: 760px; width: 100%;"></div>
		</div>
		<jsp:include page="detail.jsp"/>
		<div id="footer">
			<div class="container">
				<p class="text-muted" style="float: right">关于我们</p>
			</div>
		</div>
	</div>
</body>
</html>