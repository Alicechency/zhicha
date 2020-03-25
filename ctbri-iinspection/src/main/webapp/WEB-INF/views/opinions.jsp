<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<title>智察 - 智能关系洞察系统</title>

<!-- 标题栏图标 -->
<link rel="icon" href="/JWLetter/img/deepSearch_logo_s.png" type="image/x-icon">
<!-- 收藏夹图标 -->
<link rel="shortcut icon" href="/JWLetter/img/deepSearch_logo_s.png" type="image/x-icon">
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
<link rel="stylesheet" href="<%=bp%>/css/opinions.css">
</head>

<body>
	<div class="navbar navbar-default custom-font" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="">
                    <img src="/JWLetter/img/deepSearch_logo_s.png" class="navbar-brand"></a>
                <a href="/JWLetter" class="navbar-brand page-scroll"> 智察 - 智能关系洞察系统</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/JWLetter">警情</a></li>
                    <li><a href="/JWLetter/forward/graph">关联</a></li>
                    <li><a href="/JWLetter/forward/map">地图</a></li>
                    <li><a href="/JWLetter/forward/opinions">舆情</a></li>
                </ul>
                <div>
                    <input name="word" type="text" class="form-control pull-left" id="word" placeholder="请输入关键词">
                    <button type="submit" class="btn btn-primary pull-left" id="search">搜索</button>
                </div>
            </div>
        </div>
    </div>
	<div class="container-fluid">
		<!-- 信息统计栏 -->
		<div class="row">
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="info-box">
					<span class="info-box-icon"
						style="background: #60C5C8 url('<%=bp%>/img/icon/趋势.png') no-repeat center;"></span>
					<div class="info-box-content"
						style="position: absolute; right: 120px; margin: 0 auto; top: 7px; text-align: center;">
						<span><b>舆情总量</b></span> <br>
						<span>111781</span>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="info-box">
					<span class="info-box-icon bg-aqua"
						style="background: #60C5C8 url('<%=bp%>/img/icon/赞同.png') no-repeat center"></span>
					<div class="info-box-content"
						style="position: absolute; right: 120px; margin: 0 auto; top: 7px; text-align: center;">
						<span><b>41325</b></span> <br>
						<span>正面信息</span>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="info-box">
					<span class="info-box-icon bg-aqua"
						style="background: #b3a3da url('<%=bp%>/img/icon/中立.png') no-repeat center"></span>
					<div class="info-box-content"
						style="position: absolute; right: 120px; margin: 0 auto; top: 7px; text-align: center;">
						<span><b>66118</b></span> <br>
						<span>中立信息</span>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="info-box">
					<span class="info-box-icon bg-aqua"
						style="background: #9cb460 url('<%=bp%>/img/icon/反对.png') no-repeat center"></span>
					<div class="info-box-content"
						style="position: absolute; right: 120px; margin: 0 auto; top: 7px; text-align: center;">
						<span><b>4338</b></span> <br>
						<span>负面信息</span>
					</div>
				</div>
			</div>
		</div>
		<!-- 全网情绪走势图 -->
		<div id="chart-moodTrend"></div>
		<!-- 全网声量走势图 -->
		<div class="col-md-6 col-sm-12 col-xs-12">
			<h4>全网声量走势</h4>
			<hr>
			<div id="chart-volumeTrend"></div>
		</div>
		<!-- 负面舆情 -->
		<div class="col-md-6 col-sm-12 col-xs-12">
			<h4>负面舆情</h4>
			<hr>
			<div id="negativeOpinion">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://www.takefoto.cn/viewnews-1098929.html">运河放生280条鱼
						网友:别让好心做了坏事儿</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://www.henan100.com/edu/2017/693302.shtml">【沉思】父爱不能承受之“重”:为儿换车
						省钱引争吵</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://epaper.anhuinews.com/html/ahfzb/20170317/article_3544720.shtml">“全能神”变异组织“父神教”的淫乱生活（下）</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://news.p2peye.com/article-493678-1.html">3.15打假曝光，五大理财骗局你不得不看！</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://sn.ifeng.com/a/20170317/5472549_0.shtml">我的旅游投诉有人管吗？</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://sd.people.com.cn/n2/2017/0317/c172826-29870550.html">我的旅游投诉有人管吗？</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://m.010lf.com/news/2017/03/17/187130.html">男子误将家庭纠纷当拐卖儿童
						过失致人死被免刑罚</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://www.xzrbw.com/info/1124/172362_1.htm">尼泊尔女孩天生只见“半边脸”，被村落遗弃，几番手术后见真容</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://edu.chinaso.com/jyzx/detail/20170317/1000200032981001489717923018139465_1.html">父爱不能承受之“重”：为儿换车
						省钱引争吵</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
				<img src="<%=bp%>/img/icon/负.png" height="20" width="20"><span
					class="titleList"><a
					href="http://blog.cnfol.com/dahaishipan/article/1489721022-124282179.html">机构算瞎了！</a></span>
				<hr
					style="border: 0; border-bottom: 1px dashed #eee; margin-top: 3px;">
			</div>
		</div>
		<div class="col-md-6 col-sm-12 col-xs-12">
			<h4>来源分类</h4>
			<hr>
			<div id="sourceType" style="width: 100%; height: 450px"></div>
		</div>
		<div class="col-md-6 col-sm-12 col-xs-12">
			<h4>媒体友好程度</h4>
			<hr>
			<div id="mediaFriend" style="width: 100%; height: 450px"></div>
		</div>
		<div class="col-md-6 col-sm-12 col-xs-12">
			<h4>网页搜索结果</h4>
			<hr>
			<div id="searchResult" style="width: 100%; height: 90%">
				<table class="table table-bordered table-hover">
					<thead>
						<td></td>
						<td>快照时间</td>
						<td>标题</td>
						<td>url</td>
					</thead>
					<tbody>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/正.png" width="18px"
								height="18px"></td>
							<td>2017-03-16 17:21</td>
							<td width="50%">四川省雅安市芦山县投资经营管理有限责任公司平安路安居房前期物业管理服务采购项目公开招标采购公告更正公告</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9f65cb4a8c8507ed4fece763105392230e54f72b6984985f35918448e435061e5a2eb3f9713f40518690616703ac4c5eebed3678340327b29edf883d9fe1d03c6ad567627f0bf04005a56db8ba4332b62b875b99b86996ad873084ded2c4a85444cb23120a80e7fa2d1715ba7880172694d58e49654860b1fa4315e8297d3ee827&p=9039c64ad4d82dff57ee876216&newp=9a759a4697d501fc57ef82204f53d8265f15ed6438c3864e1290c408d23f061d4866e0bf2d241703d0c1777347c2080ba8ff612e614236f7&user=baidu&fm=sc&query=%D3%DF%C7%E9%BC%E0%B2%E2%B2%CE%B1%EA%B9%AB%B8%E6&qid=9305daf300063e43&p1=14">查看</a></td>
						</tr>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/负.png" width="18px"
								height="18px"></td>
							<td>2017-03-16 17:18</td>
							<td>四川省阿坝藏族羌族自治州汶川县汶川县妇幼保健计划生育服务中心医疗设备及办公设备采购项目公开招标采购预公告更正公告</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9d78d513d9d437ae4f9be0697c13c011124381132ba7a10208d6843893732c41506793ac57220775a4d27d1716df4a4b9d832173471450bc8cbbf85dadb1855f2b9c60742e13dc0754910eaeb85b388465d54de9d851b2e9b878d9ebc5d3a8050e954e4d2ac2a6960a55419f72f01632e6a49855155513baea3562e859006bc561&p=8b2a9715d9c044e50eaef8394952&newp=c374c64ad48c03b549bd9b7d085992695912c10e3bc3864e1290c408d23f061d4866e0bf2d241703d0c1777347c2080ba8ff612e614c62ad8a&user=baidu&fm=sc&query=%D3%DF%C7%E9%BC%E0%B2%E2%B2%CE%B1%EA%B9%AB%B8%E6&qid=9305daf300063e43&p1=12">查看</a></td>
						</tr>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/正.png" width="18px"
								height="18px"></td>
							<td>2017-01-15 20:36</td>
							<td>贡嘎山下当年的“无电乡”通电两年来 带动当地旅游收入</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9f65cb4a8c8507ed4fece76310578c374313d33f2bd7a7542e89cd5f93130a1c1871e3cc767e0d508791377a01ad4a59e8f12b75300927b098c2825dddccc86e70d633712d5cd04e05a218b8ba4432c050872aeeb868e1adf04384ded5c4a85144cb23120c83e7fb511765c978831026e2d68e381e4864b0&p=8e759a41d49812a05abc85221e57&newp=b439c54ad2c15fb90be29634135292695912c10e3addc44324b9d71fd325001c1b69e3b823281603d4c6786c15e9241dbdb239256b553ea1&user=baidu&fm=sc&query=%C3%C0%B5%C4+%B5%E7%D1%B9%C1%A6%B9%F8&qid=a1dd1e4a0004ecde&p1=24">查看</a></td>
						</tr>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/中.png" width="18px"
								height="18px"></td>
							<td>2017-03-16 16:17</td>
							<td>贡嘎山下当年的“无电乡”通电两年来 带动当地旅游收入</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9f65cb4a8c8507ed4fece76310578c374313d33f2bd7a7542e89cd5f93130a1c1871e3cc767e0d508791377a01ad4a59e8f12b75300927b098c2825dddccc86e70d633712d5cd04e05a218b8ba4432c050872aeeb868e1adf04384ded5c4a85144cb23120c83e7fb511765c978831026e2d68e381e4864b0&p=8e759a41d49812a05abc85221e57&newp=b439c54ad2c15fb90be29634135292695912c10e3addc44324b9d71fd325001c1b69e3b823281603d4c6786c15e9241dbdb239256b553ea1&user=baidu&fm=sc&query=%C3%C0%B5%C4+%B5%E7%D1%B9%C1%A6%B9%F8&qid=a1dd1e4a0004ecde&p1=24">查看</a></td>
						</tr>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/负.png" width="18px"
								height="18px"></td>
							<td>2017-03-16 15:37</td>
							<td>淘宝司能旗舰店 美歌手MV中现枪杀总能画面 特司能普称其该去坐牢</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9f65cb4a8c8507ed4fece7631046973b4a0297634a96974968d4e419ce3b4c413037bfa67a221207d6c77d6405eb0f18acb47d236b4164ed9dcc8941dbbb963f2fff666e3643d855578e59f9c45154c037e158feae69f0caf725e2a8c5d3ae4322ce44720b9780fd4d7314dd6e800341e4b1ee3b&p=9f769a47c09804ff57eb90234851&newp=8b2a9715d9c545dd1fbaf86f7f5792695912c10e3dd1c44324b9d71fd325001c1b69e3b823281603d4c6786c15e9241dbdb239256b55&user=baidu&fm=sc&query=%C3%C0%B5%C4+%C6%EC%BD%A2%B5%EA&qid=9dedf8b20005687e&p1=27">查看</a></td>
						</tr>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/负.png" width="18px"
								height="18px"></td>
							<td>2017-03-16 14:49</td>
							<td>广东肇庆警方破获一起特大贩毒案 抓获7名嫌犯</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9f65cb4a8c8507ed4fece763105392230e54f7226d9194027fa3c215cc7907160227fee574670d04d1c6796500b20f5ce8f33370360024a09cbc894bddbe982d289f27433146c01e4cc75cf28b102ad6509b4d9eae0e97bee741e3b9d3a3c82458dd24046df0f09c290703cb1fe76436f4d19c5f642c07ccec27648f4e065888504a&p=80759a41d29513bc0be296394a4c&newp=8b2a975686cc45ab1bb7d46d4b4792695912c10e3bd1c44324b9d71fd325001c1b69e3b823281603d4c6786c15e9241dbdb239256b5533&user=baidu&fm=sc&query=%B9%E3%B6%AB%B9%E3%B2%A5%B5%E7%CA%D3%CC%A8&qid=d126775a0002278f&p1=12">查看</a></td>
						</tr>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/正.png" width="18px"
								height="18px"></td>
							<td>2017-03-16 13:29</td>
							<td>四川皇家网络科技有限公司打造的全新生态经济圈正式扬帆起航</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9f65cb4a8c8507ed4fece763105392230e54f7296c86984c68d4e419cf790d1c1831a2fd7c735a19d3c77f6203ae4a58e9f73372321420c0c18ed714c9fecf6879873045070bf24405a319b8bb3232c050872a9db86fedad803984dfd9&p=882a944187904ead12a4cf265041&newp=8e7a8d16d9c119e903bd9b7f094a92695912c10e37d4c44324b9d71fd325001c1b69e3b823281603d4c6786c15e9241dbdb239256b5578f1c0&user=baidu&fm=sc&query=%CE%F7%B2%BF%BF%D8%B9%C9&qid=e5e4e3200007aa69&p1=16">查看</a></td>
						</tr>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/中.png" width="18px"
								height="18px"></td>
							<td>2017-03-16 11:04</td>
							<td>四川：57批次网络商品质量抽检不合格 涉及天猫等平台</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9d78d513d9d430ae4f9c96690c66c0161e43f1652bd7a1020ea5843fe2732a405015e9af60624e0b89833a2516ae3a41f7b16d236d4473eb8cc8ff0b8ce6cc3f2fff76692f0b8636438f04fa9e0c60dc209259ecad18fabdf73894a8d8d8d84353bd094325de&p=c449dd548c904ead08e29475134e&newp=c2338e16d9c109e012bd9b7e067a92695912c10e36d1c44324b9d71fd325001c1b69e3b823281603d4c6786c15e9241dbdb239256b5568f8d1&user=baidu&fm=sc&query=%C3%C0%B5%C4+%CC%EC%C3%A8&qid=f8c9dfbf000544a4&p1=10">查看</a></td>
						</tr>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/正.png" width="18px"
								height="18px"></td>
							<td>2017-03-16 02:23</td>
							<td>第21集团军原军长曹益民调任西部战区陆军参谋长</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9d78d513d9c342b8599d830f7b01a6610d13c03d338d96533dc3923b8e790e12506694e4747c4313d3b22d3b1ce9131cbda36065377523b19dca8b4dd8b88528598b3034061f914165895ff095&p=9836c316d9c115bc08e2973c45&newp=9867c64ad49f5ff408e2972b1153d8265f15ed6438c3864e1290c408d23f061d4866e0bf2d241703d0c1777347c2080ba8ff612e615f3eec&user=baidu&fm=sc&query=21%CD%C5&qid=c1aeae7c0005e04a&p1=6">查看</a></td>
						</tr>
						<tr>
							<td width="5%"><img src="<%=bp%>/img/icon/负.png" width="18px"
								height="18px"></td>
							<td>2017-03-16 01:54</td>
							<td>四川中翼机电有限科技公司业务齐头并进，好事连连</td>
							<td><a
								href="http://cache.baiducontent.com/c?m=9d78d513d9d430ac4f9ee5697c15c015684381132ba7a4020ed08438e2732b305321a3e52878564291d27d141cb20c19afe73605765268fcc39e9f4aaaeace3573df6275671cf10348910eaebf047e9737912ceaaf0ee7beab6484afa4d2d454&p=8b2a975e899809ff57e993685f47&newp=8f72d416d9c111a05cead02f585492695912c10e3bd6c44324b9d71fd325001c1b69e3b823281603d4c6786c15e9241dbdb239256b55&user=baidu&fm=sc&query=%C1%AC%C1%AC%BF%C6%BC%BC&qid=c1d423c80003feef&p1=19">查看</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md-6 col-sm-12 col-xs-12">
			<h4>舆情信息实时统计</h4>
			<hr>
			<div id="InfoStatic" style="width: 100%; height: 500px">
				<table class="table table-bordered table-hover">
					<thead>
						<td></td>
						<td>今天</td>
						<td>一周</td>
						<td>两周</td>
						<td>30天</td>
					</thead>
					<tbody>
						<tr>
							<td>新闻</td>
							<td>4311</td>
							<td>94304</td>
							<td>186549</td>
							<td>260290</td>
						</tr>
						<tr>
							<td>论坛</td>
							<td>78</td>
							<td>3479</td>
							<td>6814</td>
							<td>11012</td>
						</tr>
						<tr>
							<td>微博</td>
							<td>306</td>
							<td>3267</td>
							<td>7157</td>
							<td>9999</td>
						</tr>
						<tr>
							<td>微信</td>
							<td>0</td>
							<td>1466</td>
							<td>2691</td>
							<td>5219</td>
						</tr>
						<tr>
							<td>博客</td>
							<td>63</td>
							<td>1088</td>
							<td>2116</td>
							<td>2889</td>
						</tr>
						<tr>
							<td>贴吧</td>
							<td>9</td>
							<td>137</td>
							<td>392</td>
							<td>681</td>
						</tr>
						<tr>
							<td>问答</td>
							<td>0</td>
							<td>36</td>
							<td>98</td>
							<td>319</td>
						</tr>
						<tr>
							<td>传统纸媒</td>
							<td>225</td>
							<td>12721</td>
							<td>19194</td>
							<td>28785</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="container">
			<p class="text-muted" style="float: right">关于我们</p>
		</div>
	</div>
	<script src="<%=bp%>/dep/jquery-3.2.1.min.js"></script>
	<script src="<%=bp%>/dep/bootstrap-3.3.7/bootstrap.min.js"></script>
	<script src="<%=bp%>/dep/echarts-3.5.3/echarts.min.js"></script>
	<script src="<%=bp%>/js/data.js"></script>
	<script src="<%=bp%>/js/opinions.js"></script>
</body>

</html>
