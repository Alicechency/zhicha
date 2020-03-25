<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>智查成都</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link rel="stylesheet" href="<%=bp%>/dep/bootstrap.min.css">
    <link rel="stylesheet" href="<%=bp%>/css/Chengdu.css">
</head>
<body>
<div id="main">
    <!--搜索框-->
    <div id="nav" class="navbar navbar-default custom-font " style="height: 40px !important;" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="">
                    <img src="<%=bp%>/img/deepSearch_logo_s.png" class="navbar-brand"></a>
                <a href="" class="navbar-brand page-scroll"> 智察 -成都</a>
            </div>

            <div>
                <form class="navbar-form navbar-left" role="search">
                    <div class="input-group " style="width: 330px;">
                        <input type="text" class="form-control " id="search-input" placeholder="">
                        <span class="input-group-btn">
                            <button id="searchButton" class="btn btn-default" type="button" >
                                <img src="<%=bp%>/img/search-white.png" style="height: 19px;">
                            </button>
                        </span>
                    </div>
                </form>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav" id='keywords'>
                </ul>
            </div>
        </div>
    </div>
    <!--echarts关系图表-->

        <div id="let" >
        </div>
    <!--类型判断结果-->
    <div id="estimateResult" class="container" style="padding-left:0;padding-right: 0;width: 100%;height:50%;box-shadow: 0px -1px #F9F9F9;">
        <div class="row" id="ResultLeft" >
            <div style="background: #ffffff">
            <div class="col-md-3" id="th-first" style="padding-left:60px;padding-top:15px; height: 50px">该类型判决结果</div>
            <div class="col-md-2" style="text-align: center;  height: 50px;padding-top:15px;font-weight: bold;border-left: 3px solid #f6f6f6 !important;"><a id="rec-btn">推荐律师</a></div>
            <div class="col-md-2" style="text-align: center; height: 50px;padding-top:15px;font-weight: bold;"><a id="same-btn">同类案件</a></div>
            <div class="col-md-2" style="text-align: center; height: 50px;padding-top:15px;font-weight: bold;"><a>功能占位</a></div>
            <div class="col-md-2 col-md-offset-1 " style="text-align: center ; height: 50px;padding-top:5px;padding-left:-15px;">
                <form class="" role="search">
                    <div class="input-group " >
                        <input type="text" class="form-control " placeholder="">
                        <span class="input-group-btn">
                            <button id="search-Button" class="btn btn-default" type="button" >
                                <img src="<%=bp%>/img/search-blue.png" style=" height: 19px;">
                            </button>
                        </span>
                    </div>
                </form>
            </div>
            </div>
            <div class="col-md-3 " id="resultChart-bg"  style="height: 50%;border-top: 0.5px solid #DDDDDD">
                <div id="resultChart" style="padding-left: 10px;padding-bottom: 20px;"></div>
            </div>
            <div class="col-md-9 " style=" height: 50%;margin-left: -15px;">
                <div id="case-Result" >
                    <!--推荐区域-->
                    <div id="recommend">
                        <div id="recommendResult"></div>
                        <div id="recommend-pagination" ></div>
                    </div>
                    <!--类似案件-->
                    <div id="sameCase" class="package" >
                        <div id="CaseResult">
                        </div>
                        <div id="case-pagination" ></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
<div id="case-detail">
    <div id="background-prompt">
        <p >判决详情</p>
    </div>
    <div id="prompt-content">合同纠纷经讨论，原告败诉，内容说明如下:</div>
</div>


<input type="hidden" id="searchWord" value="${searchWord}">
<script type="text/javascript" src="<%=bp%>/dep/jquery-2.1.1.js"></script>
<script type="text/javascript" src="<%=bp%>/dep/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=bp%>/dep/echarts.common.min.js" ></script>
<script type="text/javascript" src="<%=bp%>/dep/extendPagination.js"></script>
<script type="text/javascript" src="<%=bp%>/js/Utils.js"></script>
<script type="text/javascript" src="<%=bp%>/js/case.js"></script>
<script type="text/javascript" src="<%=bp%>/js/Event.js"></script>
<script type="text/javascript" src="<%=bp%>/js/request.js"></script>
<%-- <script type="text/javascript" src="<%=bp%>/js/caseRelation.js"></script> --%>
<script type="text/javascript" src="<%=bp%>/js/radar.js"></script>

<script type="text/javascript" src="<%=bp%>/js/caseRelationStatic.js"></script>
<%-- <script type="text/javascript" src="<%=bp%>/js/relationData.js"></script> --%>


<script type="text/javascript">
	//图片目录
	var imgPath="<%=bp%>"+"/img";
</script>
</body>
</html>