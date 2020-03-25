<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="UTF-8">
<title>智察 - 智能关系洞察系统</title>
<!-- 标题栏图标 -->
<link rel="icon" href="<%=bp%>/img/deepSearch_logo_s.png" type="image/x-icon">
<!-- 收藏夹图标 -->
<link rel="shortcut icon" href="<%=bp%>/img/deepSearch_logo_s.png" type="image/x-icon">
<!-- Bootstrap -->
<link rel="stylesheet" href="<%=bp%>/dep/bootstrap-3.3.7/bootstrap.min.css">
<!-- 百度地图离线样式 -->
<link rel="stylesheet" href="<%=bp%>/dep/bmap/bmap.css">
<!-- font-awesome -->
<link rel="stylesheet" href="<%=bp%>/dep/font-awesome-3.2.1.min.css">
<!-- 其他依赖 -->
<link rel="stylesheet" href="<%=bp%>/dep/AdminLTE.min.css">
<link rel="stylesheet" href="<%=bp%>/dep/sticky-footer-navbar.css">
<!-- 自定义样式 -->
<link rel="stylesheet" href="<%=bp%>/css/common.css">
<link rel="stylesheet" href="<%=bp%>/css/index.css">
</head>

<body>
	<input type="hidden" id="flag" value="">
	<input type="hidden" id="level" value="">
	<input type="hidden" id="tagName" value="">
	<input type="hidden" id="current" value="">
	<input type="hidden" id="sum" value="">
	<input type="hidden" id="caseBriefAndCaseChart" value="false">
	<div id="nav" class="navbar navbar-default custom-font" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a href="/iinspection"> <img src="<%=bp%>/img/deepSearch_logo_s.png" class="navbar-brand"></a> 
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
					<input name="word" type="text" class="form-control pull-left" id="word" placeholder="请输入关键词">
					<button class="btn btn-primary pull-left" id="search">搜索</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid" style="padding-top: 10px">
		<div class="row">
			<div class="col-md-3">
				<div class="box box-solid">
					<div class="box-header with-border">
						<h3 class="box-title">文件夹</h3>
						<div class="box-tools">
							<button type="button" class="btn btn-box-tool" data-widget="collapse">
								<i class="glyphicon glyphicon-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body no-padding">
						<ul class="nav nav-pills nav-stacked" id="ul">
							<li id="showCases" class="active">
								<a href="javascript:;">
									<i class="glyphicon glyphicon-inbox"></i> 全部警情 
									<span id="CaseCount" class="label label-primary pull-right"></span>
								</a>
							</li>
							<li id="showUndo">
								<a href="javascript:;" id="loadUndoCase">
									<i class="glyphicon glyphicon-list-alt"></i> 未处理警情 
									<span id="undoCaseCount" class="label label-warning pull-right"></span>
								</a>
							</li>
							<li id="showOneCase">
								<a href="javascript:;" level="0" name="loadCase"> 
									<i class="glyphicon glyphicon-book"></i> 刑事类警情 
									<span id="levelOneCaseCount" class="label label-warning pull-right"></span>
								</a>
							</li>
							<li id="showTwoCase">
								<a href="javascript:;" level="1" name="loadCase"> 
									<i class="glyphicon glyphicon-filter"></i> 治安类警情
									<span id="levelTwoCaseCount" class="label label-warning pull-right"></span>
								</a>
							</li>
							<li id="showThreeCase">
								<a href="javascript:;" level="2" name="loadCase"> 
									<i class="glyphicon glyphicon-trash"></i> 纠纷类警情
									<span id="levelThreeCaseCount" class="label label-warning pull-right"></span>
								</a>
							</li>
							<li id="showFourCase">
								<a href="javascript:;" level="3" name="loadCase"> 
									<i class="glyphicon glyphicon-trash"></i> 民意类警情 
									<span id="levelFourCaseCount" class="label label-warning pull-right"></span>
								</a>
							</li>
							<li id="showAllSuspects" class="active">
								<a href="javascript:;">
									<i class="glyphicon glyphicon-user"></i> 常控人员
									<span id="suspectCount" class="label label-primary pull-right"></span>
								</a>
							</li>
							<li name="showWarning" level="1">
								<a href="javascript:;">
									<div class="oneLevel"></div> 红色预警 
									<span id="suspectCountRed" class="label label-danger pull-right"></span>
								</a>
							</li>
							<li name="showWarning" level="2">
								<a href="javascript:;">
									<div class="twoLevel"></div> 橙色预警 
									<span id="suspectCountOrange" class="label label-warning pull-right"></span>
								</a>
							</li>
							<li name="showWarning" level="3">
								<a href="javascript:;">
									<div class="thridLevel"></div> 黄色预警 
									<span id="suspectCountYellow" class="label definecolor pull-right"></span>
								</a>
							</li>
							<li name="showWarning" level="4">
								<a href="javascript:;">
									<div class="forthLevel"></div> 蓝色预警 
									<span id="suspectCountBlue" class="label label-primary pull-right"></span>
								</a>
							</li>
							<li id="showSearch"><a href="javascript:;"> 
								<i class="glyphicon glyphicon-search"></i> 搜索结果 
								<span id="searchCount" class="label label-primary pull-right"></span>
								</a>
							</li>
						</ul>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /. box -->
				<!-- <div class="box box-solid">
					<div class="box-header with-border">
						<h3 class="box-title">标签</h3>
						<div class="box-tools">
							<button type="button" class="btn btn-box-tool" data-widget="collapse">
								<i class="glyphicon glyphicon-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body no-padding">
						<ul class="nav nav-pills nav-stacked" id="tags">
						</ul>
					</div>
					/.box-body
				</div> -->
				<!-- /.box -->
				<div class="box box-solid">
					<div class="box-header with-border">
						<h3 class="box-title">处理进度</h3>
						<div class="box-tools">
							<button type="button" class="btn btn-box-tool" data-widget="collapse">
								<i class="glyphicon glyphicon-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body no-padding">
						<table class="table table-condensed">
							<tbody>
								<tr>
									<th style="width: 10px">#</th>
									<th>任务</th>
									<th>进度</th>
									<th style="width: 40px">总量</th>
								</tr>
								<tr>
									<td>1.</td>
									<td>总警情量</td>
									<td>
										<div class="progress progress-xs">
											<div class="progress-bar progress-bar-danger"
												style="width: 100%"></div>
										</div>
									</td>
									<td><span class="badge bg-red">1000</span></td>
								</tr>
								<tr>
									<td>2.</td>
									<td>已处理量</td>
									<td>
										<div class="progress progress-xs">
											<div class="progress-bar progress-bar-yellow"
												style="width: 60%"></div>
										</div>
									</td>
									<td><span class="badge bg-yellow">600</span></td>
								</tr>
								<tr>
									<td>3.</td>
									<td>流程执行中</td>
									<td>
										<div class="progress progress-xs progress-striped active">
											<div class="progress-bar progress-bar-primary"
												style="width: 30%"></div>
										</div>
									</td>
									<td><span class="badge bg-light-blue">300</span></td>
								</tr>
								<tr>
									<td>4.</td>
									<td>已证伪</td>
									<td>
										<div class="progress progress-xs progress-striped active">
											<div class="progress-bar progress-bar-success"
												style="width: 10%"></div>
										</div>
									</td>
									<td><span class="badge bg-green">100</span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
				</div>
				<a style="position: absolute; right: 0;" onClick="showStatisc()">>>>了解更多</a>
			</div>
			<!-- /.col -->
			<div class="col-md-9" id="messages">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 id="messageTitle" class="box-title">全部警情</h3>
						<!-- /.box-tools -->
					</div>
					<!-- /.box-header -->
					<div class="box-body no-padding">
						<div class="mailbox-controls">
							<!-- /.btn-group -->
							<button id="refresh" type="button" class="btn btn-default btn-sm">
								<i class="glyphicon glyphicon-refresh"></i>
							</button>
							<div class="pull-right">
								<span id="recordsNums"></span>
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
							<!-- /.pull-right -->
						</div>
						<div class="table-responsive mailbox-messages">
							<table class="table table-hover table-striped">
								<tbody id="datas"></tbody>
							</table>
							<!-- /.table -->
						</div>
					</div>
					<!-- /.mail-box-messages -->
				</div>
				<!-- /.box-body -->
				<ul class="pagination pull-right">
				</ul>
			</div>
			<div id="messagesDetail" class="col-md-9" style="display: none">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">警情正文</h3>
						<div class="box-tools pull-right">
							<button id="hideCaseDetail" type="button" class="btn btn-default btn-sm">
								<i class="glyphicon glyphicon-remove"></i>
							</button>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							
							<!-- /.mailbox-read-info -->
							<div class="mailbox-controls with-border text-center">
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-sm"
										data-toggle="tooltip" data-container="body" title="Delete">
										<i class="glyphicon glyphicon-trash"></i>
									</button>
									<button type="button" class="btn btn-default btn-sm"
										data-toggle="tooltip" data-container="body" title="Reply">
										<i class="glyphicon glyphicon-chevron-left"></i>
									</button>
									<button type="button" class="btn btn-default btn-sm"
										data-toggle="tooltip" data-container="body" title="Forward">
										<i class="glyphicon glyphicon-chevron-right"></i>
									</button>
								</div>
								<!-- /.btn-group -->
								<button type="button" class="btn btn-default btn-sm"
									data-toggle="tooltip" title="" data-original-title="Print">
									<i class="glyphicon glyphicon-print"></i>
								</button>
							</div>
							<div class="col-md-12">
	                    		<div class="box-header with-border" id="messagesTitle">
									<h3 class="box-title" ></h3>
								</div>
								
							</div>
							
							<!-- /.mailbox-controls -->
							<div class="mailbox-read-message" id="content"></div>
							<!-- /.mailbox-read-message -->
						</div>
						<!-- /.box-body -->
						<div class="col-md-12" id="case_brief_all" >
                    		<div class="box-header with-border">
								<h3 class="box-title">警情概要</h3>
							</div>
							<div id="case_brief">
							
							</div>
							<div id="case_chart" style="height:180px;width:900px">
							
							</div>
							
						</div>
						<!-- /.box-footer -->
						<div class="col-md-12">
                    		<div class="box-header with-border" >
								<h3 class="box-title">警情关系</h3>
							</div>
							<div id="let" style="border: 0px; height: 460px; width: 950px;"></div>
						</div>
						<!-- /.box-footer -->
					</div>
					<!-- /. box -->
				</div>
			</div>
			<div id="suspectDetail" class="col-md-9" style="display: none">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">常控人员信息</h3>
                        <div class="box-tools pull-right">
                            <button id="hideSuspectDetail" type="button" class="btn btn-default btn-sm">
                                <i class="glyphicon glyphicon-remove"></i>
                            </button>
                        </div>
                        <!-- /.box-header -->
                        <div class="mailbox-read-info" id="title" style="padding-left: 10px;padding-bottom: 0px;">
                            <h4>基本信息</h4>
                        </div>
                        <!-- /.mailbox-read-info -->
                        <div class="media">
                            <div class="media-body">
                                <ul class="personinfo" id="suspectInfo"></ul>
                            </div>
                            <div class="media-right">
                                <a href="javascript:;">
                                    <img class="media-object" data-src="holder.js/64x64" alt="64x64" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+PCEtLQpTb3VyY2UgVVJMOiBob2xkZXIuanMvNjR4NjQKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNWJkOGRhMWIyNiB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1YmQ4ZGExYjI2Ij48cmVjdCB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSIxNCIgeT0iMzYuNSI+NjR4NjQ8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" data-holder-rendered="true" style="width: 64px; height: 64px;">
                                </a>
                            </div>
                        </div>
                        <!-- /.mailbox-controls -->
                        <div class="mailbox-read-info" id="title" style="padding-left: 10px;padding-bottom: 0px;">
                            <h4>行为轨迹</h4></div>
                        <div class="media-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>活动发生时间</th>
                                        <th>活动发生地详址</th>
                                        <th>所属社会场所</th>
                                        <th>活动相关信息</th>
                                    </tr>
                                </thead>
                                <tbody id="tracks"></tbody>
                            </table>
                        </div>
                        <div class="pull-right"><a href="javascript:;">了解更多</a></div>
                        <div id="trace-graph"></div>
                        <!-- /.mailbox-read-message -->
                        <!-- /.box-body -->
                        <!-- /.box-footer -->
                        <div id="important_person" class="mailbox-read-info" style="padding-left: 10px;padding-bottom: 0px;" id="title">
                            <h4>关联重点人员</h4></div>
                        <div class="col-md-12">
                            <div id="importantPerson" class="pull-left"></div>
                            <div id="graphInfo" class="pull-right know" style="background-color: gainsboro; display: none" >
                                <img src="<%=bp%>/img/close.svg" class="pull-right" id="close">
                                <div class="media" class="suspectInfo">
                                    <div class="media-top" style="padding: 40px;">
                                        <a href="#">
                                            <img class="media-object" data-src="holder.js/64x64" alt="64x64" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+PCEtLQpTb3VyY2UgVVJMOiBob2xkZXIuanMvNjR4NjQKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNWJkOGRhMWIyNiB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1YmQ4ZGExYjI2Ij48cmVjdCB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSIxNCIgeT0iMzYuNSI+NjR4NjQ8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" data-holder-rendered="true" style="width: 100px; height: 100px;">
                                        </a>
                                    </div>
                                    <div class="media-body">
                                        <ul class="personinfo" id="suspectBaseInfo"></ul>
                                    </div>
                                </div>
                                <div class="pull-right knowMore"><a>了解更多</a></div>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-footer -->
                    <!-- /. box -->
                </div>
            </div>
            <div id="searchInfo" class="col-md-9" style="display: none" value="0">
               <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 id="title2" class="box-title">警情</h3>
                        <!-- /.box-tools -->
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body no-padding">
                        <div class="mailbox-controls">
                            <!-- /.btn-group -->
                            <button id="refresh" type="button" class="btn btn-default btn-sm">
								<i class="glyphicon glyphicon-refresh"></i>
							</button>
                            <div class="pull-right">
                                <span id="caseRecordsNums"></span>
                                <div class="btn-group">
                                    <button id="searchCasePreview" type="button" class="btn btn-default btn-sm">
                                        <i class="glyphicon glyphicon-chevron-left"></i>
                                    </button>
                                    <button id="searchCaseNext" type="button" class="btn btn-default btn-sm">
                                        <i class="glyphicon glyphicon-chevron-right"></i>
                                    </button>
                                </div>
                                <!-- /.btn-group -->
                            </div>
                            <!-- /.pull-right -->
                        </div>
                        <div class="table-responsive mailbox-messages">
                            <table class="table table-hover table-striped">
                                <tbody id="searchCases"></tbody>
                            </table>
                        </div>    <!-- /.table -->
                    </div>
                    <div class="box-header with-border next">
                        <h3 id="title3" class="box-title">嫌疑人</h3>
                        <!-- /.box-tools -->
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body no-padding">
                        <div class="mailbox-controls">
                            <!-- /.btn-group -->
                            <button id="refresh" type="button" class="btn btn-default btn-sm">
								<i class="glyphicon glyphicon-refresh"></i>
							</button>
                            <div class="pull-right">
                                <span id="suspectRecordsNums"></span>
                                <div class="btn-group">
                                    <button id="searchSuspectPreview" type="button" class="btn btn-default btn-sm">
                                        <i class="glyphicon glyphicon-chevron-left"></i>
                                    </button>
                                    <button id="searchSuspectNext" type="button" class="btn btn-default btn-sm">
                                        <i class="glyphicon glyphicon-chevron-right"></i>
                                    </button>
                                </div>
                                <!-- /.btn-group -->
                            </div>
                            <!-- /.pull-right -->
                        </div>
                        <div class="table-responsive mailbox-messages">
                            <table class="table table-hover table-striped">
                                <tbody id="searchSuspects"></tbody>
                            </table>
                            <!-- /.table -->
                        </div>
                    </div>
                </div>
            </div>
		</div>
	</div>
	</div>
	<!-- /.col -->
	</div>
	<div id="footer">
		<div class="container">
			<p class="text-muted" style="float: right">关于我们</p>
		</div>
	</div>
	<script src="<%=bp%>/dep/jquery-3.2.1.min.js"></script>
	<script src="<%=bp%>/dep/bootstrap-3.3.7/bootstrap.min.js"></script>
	<script src="<%=bp%>/dep/echarts-3.5.3/echarts.min.js"></script>
	<script src="<%=bp%>/dep/echarts-3.5.3/extension/bmap.min.js"></script>
    <script src="<%=bp%>/js/apiv1.3.min.js"></script>
    <script src="<%=bp%>/dep/bmap/AreaRestriction.min.js"></script>
	<script src="<%=bp%>/js/graph.trace.js"></script>
	<script src="<%=bp%>/js/evidence.js"></script>
	<script src="<%=bp%>/js/JWUtils.js"></script>
	<script src="<%=bp%>/js/index.js"></script>
</body>

</html>