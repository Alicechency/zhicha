<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <title>智察 - 智能关系洞察系统</title>
    <!-- 标题栏图标 -->
    <link rel="shortcut icon" href="/iinspection/img/deepSearch_logo_s.png" type="image/x-icon">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="/iinspection/dep/bootstrap-3.3.7/bootstrap.min.css">
    <!-- font-awesome -->
    <link rel="stylesheet" href="/iinspection/dep/font-awesome-3.2.1.min.css">
    <!-- 百度地图离线样式 -->
    <link rel="stylesheet" href="/iinspection/dep/bmap/bmap.css">
    <!-- 阿里巴巴iconfont矢量图标库 -->
    <link rel="stylesheet" href="/iinspection/dep/iconfont/iconfont.css">
    <!-- Bootstrap日期时间选择器插件 -->
    <link rel="stylesheet" href="/iinspection/dep/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css">
    <!-- 自定义样式 -->
    <!-- 导航栏通用样式 -->
    <link rel="stylesheet" href="/iinspection/css/common.css">
    <!-- 地图页面自定义样式 -->
    <link rel="stylesheet" href="/iinspection/css/map.css">
    <!-- 时间轴样式 -->
    <!-- <link rel="stylesheet" href="dep/timeline/css/normalize.min.css" /> -->
    <link rel="stylesheet" href="/iinspection/dep/timeline/css/ion.rangeSlider.css" />
    <link rel="stylesheet" href="/iinspection/dep/timeline/css/ion.rangeSlider.skinNice.css" />
</head>

<body>
    <!-- 用于语音助手通信的标签 -->
    <input id="word" type="hidden" name="" value="${word}">
    <input id="layer" type="hidden" name="" value="${layer}">
    <!-- 导航栏 -->
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
                <div>
                    <input name="word" type="text" class="form-control pull-left" id="search-input" placeholder="请输入关键词">
                    <button type="submit" class="btn btn-primary pull-left" id="search-btn">搜索</button>
                </div>
                <div id="police-station-menu">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="choice">默认<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">默认</a></li>
                                <li><a href="#">麦子店派出所</a></li>
                                <li><a href="#">三里屯派出所</a></li>
                            </ul>
                        </li>
                    </ul>
                    <img src="/iinspection/img/head.png" class="pull-right">
                </div>
            </div>
        </div>
    </div>
    <!-- 控制面板 -->
    <div id="ctl-board" class="clearfix">
        <!-- 加载各类图的控制按钮组-向左浮动 -->
        <div id="btn-grp" class="pull-left">
            <span title="案情信息"><img src="/iinspection/img/event_load.png" class="btn-clear" id="btn-eventctl" alt="案情信息"></span>
			<span title="摄像头"><img src="/iinspection/img/camera_clear.png" class="btn-load" id="btn-camera" alt="摄像头"></span>
            <span title="警车位置信息"><img src="/iinspection/img/position_clear.png" class="btn-load" id="btn-positionctl" alt="警车位置信息"></span>
            <span title="巡逻路线信息"><img src="/iinspection/img/line_clear.png" class="btn-load" id="btn-linectl" alt="巡逻路线信息"></span>
            <span title="外来人口信息"><img src="/iinspection/img/Outsider_clear.png" class="btn-load" id="btn-outsider" alt="外来人口信息"></span>
            <span title="犯罪预测信息"><img src="/iinspection/img/CrimePredict_clear.png" class="btn-load" id="btn-crime-predict" alt="犯罪预测信息"></span>
			
        </div>
        <!-- 时间选择模块+热力图选择-向右浮动 -->
        <div class="pull-right clearfix">
            <form id="sel-heatmap" class="form-group pull-left">
                <label for="heatmap-select">案情热力图：</label>
                <select id="heatmap-select" class="form-control"></select>
            </form>
            <div id="time-ctl" class="form-group form-inline pull-right">
                <span class="input-group">
                <input size="16" type="text" value="" class="form-control content cursor-pointer" id="startTime" readonly>
                <span class="input-group-addon add-on"><i class="iconfont icon-iconset0112"></i></span>
                </span>
                <span class="link">至</span>
                <span class="input-group">
                <input size="16" type="text" value="" class="form-control content cursor-pointer" id="endTime" readonly>
                <span class="input-group-addon add-on"><i class="iconfont icon-iconset0112"></i></span>
                </span>
            </div>
        </div>
    </div>
    <!-- 地图主模块 -->
    <div id="main" class="container-fluid clearfix">
        <!-- 地图 -->
        <div id="container"></div>
        <!-- 侧边栏 -->
        <div id="side-panel">
            <!-- 侧边栏图表模块 -->
            <div id="side-chart" class="panel panel-primary">
                <!-- 侧边栏图表标题 -->
                <i class="iconfont icon-close"></i>
                <div class="panel-heading">
                    <h3 class="panel-title"></h3>
                </div>
                <!-- 侧边栏图表主体 -->
                <div class="panel-body">
                    <!-- 侧边栏图表容器 -->
                    <div id="side-chart-container"></div>
                    <!-- 点击警徽显示信息的容器 -->
                    <div id="side-station-info"></div>
                </div>
            </div>
            <!-- 侧边栏信息 -->
            <div id="side-list" class="panel panel-primary">
                <!-- 侧边栏信息标题 -->
                <div class="panel-heading">
                    <h3 class="panel-title"></h3>
                </div>
                <!-- 侧边栏信息主体 -->
                <div class="panel-body"></div>
            </div>
             <!-- 侧边栏警情摘要 -->
            <div id="side-abstract" class="panel panel-primary">
                <!-- 侧边栏信息标题 -->
                <div class="panel-heading">
                    <h3 class="panel-title"></h3>
                </div>
                <!-- 侧边栏信息主体 -->
                <div class="panel-body">				
				</div>
				<!-- 摘要图标 -->
				<div id="case-chart-container"></div>
            </div>
            <!-- 侧边栏案件详情 -->
            <div id="side-detail" class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"></h3>
                </div>
                <div class="panel-body"></div>
            </div>
        </div>
        <!-- Logo -->
        <div id="map-logo"><img src="/iinspection/img/deepSearch_logo_s.png"></div>
        <!-- 散点鼠标hover的tip -->
        <div id="map-scatter-tip"></div>
    </div>
    <!-- 外来人口热力图按钮的时间选择模块 -->
    <div id="outsider-time-choose" class="form-group form-inline">
	<div class="form-group time-line">
        <span class="input-group">
        <input id="outsider-date" size="16" type="text" value="" class="form-control content cursor-pointer" readonly><span class="input-group-addon add-on"><i class="iconfont icon-iconset0112"></i></span>
        </span>
        <div id="outsider-time-line"></div>
	</div>
    </div>
    <!-- 犯罪预测图按钮的选择模块 -->
    <div id="crime-predict-choose" class="form-group form-inline">
        <span class="input-group">
        <input size="16" type="text" value="" class="form-control content cursor-pointer" readonly><span class="input-group-addon add-on"><i class="iconfont icon-iconset0112"></i></span>
        </span>
        <select class="form-control">
            <option value="0">请选择预测类型</option>
            <option value="1">查无此事</option>
            <option value="2">盗窃</option>
            <option value="3">反映</option>
            <option value="4">故意损坏公共财物</option>
            <option value="5">火警</option>
            <option value="6">交通问题</option>
            <option value="7">纠纷</option>
            <option value="8">举报</option>
            <option value="9">闹事</option>
            <option value="10">强奸</option>
            <option value="11">敲诈勒索</option>
            <option value="12">求助</option>
            <option value="13">扰民</option>
            <option value="14">杀人</option>
            <option value="15">社情</option>
            <option value="16">刑事</option>
            <option value="17">诈骗</option>
            <option value="18">治安</option>
            <option value="19">重复报警</option>
        </select>
        <div id="crime-predict-btngrp">
            <button class="btn btn-default" type="submit">确定</button>
            <button class="btn btn-default" type="submit">取消</button>
        </div>
    </div>
    <!-- jQuery -->
    <script src="/iinspection/dep/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap -->
    <script src="/iinspection/dep/bootstrap-3.3.7/bootstrap.min.js"></script>
    <!-- Bootstrap-datetimepicker -->
    <script src="/iinspection/dep/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script src="/iinspection/dep/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <!-- 时间轴插件 -->
    <script src="/iinspection/dep/timeline/js/ion.rangeSlider.js"></script>
    <!-- 百度地图离线API -->
    <!-- <script src="/iinspection/js/apiv1.3.min.js"></script> -->
	<script src="/iinspection/dep/bmap/baidumapv2/baidumap_offline_v2_load.js"></script>
    <script src="/iinspection/dep/bmap/AreaRestriction.min.js"></script>
	
	<!-- <script src="dep/bmap/draw_zhhpch.js"></script> -->
	<!-- <script src="dep/bmap/drawbycanvas_evhm1h.js"></script> -->
	<!-- <script src="dep/bmap/drawbysvg_xzmng4.js"></script> -->
	<!-- <script src="dep/bmap/drawbyvml_rscvqi.js"></script> -->
	<!-- <script src="dep/bmap/poly_bs55zb.js"></script> -->
	<!-- <script src="dep/bmap/vectordrawlib_qb0sq4.js"></script> -->
    <!-- 百度地图在线API 离线版去掉此API-->
     <script src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
    <!-- ECharts -->
    <script src="/iinspection/dep/echarts-3.5.3/echarts.min.js"></script>
    <script src="/iinspection/dep/echarts-3.5.3/extension/bmap.min.js"></script>
    <script src="/iinspection/dep/echarts-3.5.3/extension/dataTool.min.js"></script>
    <!-- Mapv 百度地图的API要放在Mapv之前引入-->
    <script src="/iinspection/dep/mapv-2.0.12.min.js"></script>
    <!-- 其他js,保持以下引入顺序 -->
    <!-- 巡逻路线模拟数据 -->
    <script src="/iinspection/data/map.data.line.js"></script>
    <!-- 其他功能函数 -->
    <script src="/iinspection/js/map.data.js"></script>
    <script src="/iinspection/js/map.data.process.js"></script>
    <script src="/iinspection/js/echarts.option.js"></script>
    <script src="/iinspection/js/bmap.option.js"></script>
    <script src="/iinspection/js/map.event.js"></script>
</body>

</html>