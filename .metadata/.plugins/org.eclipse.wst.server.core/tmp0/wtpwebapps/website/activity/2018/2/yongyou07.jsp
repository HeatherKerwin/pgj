<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ry.core.util.Constant"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%
	String path = Constant.properties.getProperty("baseUrl");
	String basePath = "";
	if(StringUtils.isNotBlank(path))basePath = path;
	//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
    <title>票据管家--2018用友生态伙伴大会圆桌对话</title>
    <link rel="shortcut icon" href="https://img.utiexian.com/common/icon/32.png">
    <link rel="icon" href="https://img.utiexian.com/common/icon/32.png" sizes="32x32">
    <link rel="icon" href="https://img.utiexian.com/common/icon/192.png" sizes="192x192">
    <link rel="apple-touch-icon-precomposed" href="https://img.utiexian.com/common/icon/180.png">
    <meta name="msapplication-TileImage" content="https://img.utiexian.com/common/icon/270.png">
    
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css"/>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css"/>
	
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/header.css"/>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/global.css" />
    
	<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>	 
</head>

<body>
<!-- 头部  -->
<div class="w ha bcf8f8f8" >
     <div class="w1200 bc h83 lh83">
        <a href="https://www.utiexian.com/index.html"><img src="https://img.utiexian.com/website/header/logo2.png" alt="" class="fl mt20 mr20"/></a>
        <ul class="fr f20">
            <li class="fl"><a href="https://www.utiexian.com/index.html" class="c2d2d2d">返回首页</a></li>
        </ul>
    </div>
</div>

<!-- 最后 -->
<div class="flex flex-justify-center w pt25 pb50">
	<div class="flex-row flex-direction-column w1200 c666666 bcwhite pt30 pb40">
		<!-- 对话主题 -->
		<div class="f16 w tc c2d2d2d">最后</div>
		
		<!-- 对话内容 -->
		<div class="flex-row flex-direction-column pl50 pr50 pt40 pb40 f14 lh24">
			<!-- 01 -->
			<div class="flex-row flex-direction-row pr50">
				<div class="flex-row w70">
					<img src="https://img.utiexian.com/website/activity/2018/2/zeng.png" width="70" height="70">
				</div>
				<div class="flex flex-direction-column ml15">
					<div class="f16 c2d2d2d">主持人</div>
					<div><span class="pl14 pr14 lh27 c606060 bceeeeee tc mt6 mb5 dsib">票据管家CEO 曾攀云</span></div>
					<div>
你看我是女生，女生最缺乏的是安全感。张总干的事情是请把您的安全感交给我，这是最暖心的一句话。<br><br>
今天我们圆桌对话讨论了很多问题，由我们是谁，我们为什么来，我们怎么做。我相信在座的伙伴受益匪浅，在台上的几位受益匪浅，非常感谢用友给我们这样的平台，忠心希望我们为用友带来新的价值，在用友帮助下走的更远更好，诞生更多的百年企业。<br><br>
谢谢各位！<br><br>
感谢五位论坛嘉宾的精彩分享。我们到了说再见的时候，用友将以持续与开放的心态，欢迎生态伙伴的加盟，也生态伙伴对用友云的大力支持。<br>
谢谢大家，今天的论坛到此结束。</div>
				</div>
			</div>
			<!-- 02 -->
			<div class="flex-row flex-wrap flex-justify-space-around w612 bc">
				<div class="flex-row flex-direction-column flex-justify-center mt50">
					<img src="https://img.utiexian.com/website/activity/2018/2/jiti.png" width="300" height="200" class="br3">
				</div>
				<div class="flex-row flex-direction-column flex-justify-center mt50 ml10">
					<img src="https://img.utiexian.com/website/activity/2018/2/heyinger.png" width="300" height="200" class="br3">
				</div>
				<div class="flex-row flex-direction-column flex-justify-center mt10">
					<img src="https://img.utiexian.com/website/activity/2018/2/heyingsan.jpg" width="300" height="200" class="br3">
				</div>
				<div class="flex-row flex-direction-column flex-justify-center mt10 ml10">
					<img src="https://img.utiexian.com/website/activity/2018/2/heyingsi.png" width="300" height="200" class="br3">
				</div>
			</div>
		</div>
		
		<!-- 分页 -->
		<div class="flex-row flex-direction-row w456 bc tc lh35">
			<a href="<%=basePath%>/activity/2018/2/yongyou06.jsp" class="bae0e0e0 w88 c979797 disb cp">上一页</a>
			<div class="flex-row flex-direction-row flex-justify-space-around w">
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou02.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">1</a>
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou03.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">2</a>
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou04.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">3</a>
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou05.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">4</a>
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou06.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">5</a>
				<a style="width: 35px;"  class="w35 h35 bae84c3d ce84c3d disb cp">6</a>
			</div>
			<a class="bae0e0e0 cwhite w88 bccccccc disb">下一页</a>
		</div>
	</div>
</div>
	
<!--  -->
<footer class="w h450 cababab f16">
	<!-- 底部标签-->
	<div class="w bc363636 h60">
		<ul class="w970 bc cwhite f16 h60">
			<li class="fl mr30"><a href="<%=basePath%>/aboutus/choice/suggest" class="fl mr30 cababab lh60">关于我们</a><i class="fr Fright mt21" ></i></li>
			<li class="fl mr30"><a href="<%=basePath%>/join?num=1" class="fl mr30 cababab lh60">加入我们</a><i class="fr Fright mt21" ></i></li>
			<li class="fl mr30"><a href="<%=basePath%>/discountrecord/assess" class="fl mr30 cababab lh60">我要贴现</a><i class="fr Fright mt21" ></i></li>
			<li class="fl mr30"><a href="<%=basePath%>/hall/index" class="fl mr30 cababab lh60">报价收票</a><i class="fr Fright mt21" ></i></li>
			<li class="fl mr30"><a href="<%=basePath%>/help" class="fl mr30 cababab lh60">帮助中心</a><i class="fr Fright mt21" ></i></li>
			<li class="fl mr30"><a href="<%=basePath%>/news/index" class="fl mr30 cababab lh60">市场信息</a><i class="fr Fright mt21" ></i></li>
			<li class="fl mr30"><a href="<%=basePath%>/xieyi" class="fl mr30 cababab lh60">用户协议</a><i class="fr Fright mt21" ></i></li>
			<li class="fl"><a href="<%=basePath%>/statement" class="fl mr30 cababab lh60">免责声明</a></li>
		</ul>
	</div>
	<!-- 底部信息-->
	<div class="w h390 bc404040">
		<div class="w1200 bc">
			<div class="fl mt120 mt73">
				<p class="fb">联系我们</p>
				<ul class="mt30">
					<li class="mt16"><i class="Ficon Ficon1 vm mr16"></i>电话：400-067-0002</li>
					<li class="mt16"><i class="Ficon Ficon2 vm mr16"></i>邮箱：ryfinance@ryfinance.com</li>
					<li class="mt16"><i class="Ficon Ficon3 vm mr16"></i>网址：www.utiexian.com</li>
					<li class="mt16"><i class="Ficon Ficon4 mr16"></i>地址：上海市虹口区东大名路501白玉兰广场1207</li>
				</ul>
			</div>
			<div class="fr mt53">
				<div class="fl mr89">
					<img src="https://img.utiexian.com/website/foot/app.png">
					<p class="tc lh200 mt20">扫描二维码<br>下载APP</p>
				</div>
				<div class="fl mr120">
					<img src="https://img.utiexian.com/website/foot/app.png">
					<p class="tc lh200 mt20">扫描二维码<br>下载APP</p>
				</div>
			</div>
		</div>
		<div class="cb"></div>
		<p class="tc mt59">© Copyright 2015 上海票管家金融服务有限公司  <a style="color:#a8a8a8;" href="http://www.miitbeian.gov.cn" target="_blank">沪ICP备16003046号</a></p>
	</div>
</footer>
</body>
</html>