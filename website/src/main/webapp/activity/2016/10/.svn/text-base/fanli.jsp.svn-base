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
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<title>票据管家--座谈会</title>
	<link rel="shortcut icon" href="https://img.utiexian.com/common/icon/32.png">
	<link rel="icon" href="https://img.utiexian.com/common/icon/32.png" sizes="32x32">
	<link rel="icon" href="https://img.utiexian.com/common/icon/192.png" sizes="192x192">
	<link rel="apple-touch-icon-precomposed" href="https://img.utiexian.com/common/icon/180.png">
	<meta name="msapplication-TileImage" content="https://img.utiexian.com/common/icon/270.png">
	
	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css"/>
	 <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css"/>
	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/header.css"/>
	 	    
	 	<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>	    
		<script type="text/javascript" src="https://static.utiexian.com/js/website/enum.js"></script>
		<script type="text/javascript" src="https://static.utiexian.com/js/website/config.js"></script>
</head>
<body>
<header class="w h160 bcf8f8f8">
	<div class="w h40 bc1c1313">
		<div class="w1200 bc h40 f14 lh40">
			<div id="has_member" style="display:none;">
				<p id="member_name" class="fl cwhite mr15">
					您好，欢迎来到票据管家
				</p>
				<a href="<%=basePath%>/member/logout" class="fl cwhite">退出</a>
			</div>
			<div id="null_member">
				<p class="fl cwhite mr15">
	           		  您好，欢迎来到票据管家
	            </p>
				<a href="<%=basePath%>/login" class="fl cd43c33 mr10">登录</a>
	           	<span class="fl cd43c33 mr15">|</span>
	            <a href="<%=basePath%>/login" class="fl cd43c33 mr15">注册</a>
			</div>
			<div class="fr">
				<ul class="fl cwhite">
					<li class="fl mr15"><a href="<%=basePath%>/bisicmessage/information" class="fl cwhite mr9">我的票据管家</a><i class="fl Hright"></i></li>
					<li class="fl mr15"><a href="<%=basePath%>/discountorder/discount" class="fl cwhite mr9">我的订单</a><i class="fl Hright"></i></li>
					<li class="fl mr15"><a href="<%=basePath%>/systeminfo/list" class="fl cwhite mr9">消息中心<span id="message" style="color:red;font-size:12px;"></span></a><i class="fl Hright"></i></li>
					<li class="fl mr15"><a href="<%=basePath%>/help" class="fl cwhite mr9">帮助中心</a><i class="fl Hright"></i></li>
				</ul>
				<!-- 登录/退出-->
				<p class="fl cwhite">客服热线：400-067-0002（接听时间：工作日09：00-17：30）</p>
			</div>
		</div>
	</div>
	<div class="w h120 bcwhite">
		<div class="w1200 bc h120">
			<a href="<%=basePath%>/index.html"><img src="https://img.utiexian.com/website/header/logo.png" alt="" class="fl mt35 mr20"></a>
			<ul class="fl h120 f20 tc Htab">
				<li class="fl mt65 ml60"><a href="<%=basePath%>/index.html" class="c2d2d2d">首页</a></li>
				<li class="fl mt65 ml50"><a href="<%=basePath%>/discountrecord/assess" class="c2d2d2d">我要贴现</a></li>
				<li class="fl mt65 ml50" id="colorOffer" onmousemove="openOffer(this)" onmouseout="closeOffer(this)">
					<a>报价收票<img src="https://img.utiexian.com/website/header/open.png" class="w15 h7 ml8" id="imgOffer" /></a>
					<ul class="pa w120 lh30 f14 zi11 br3 bad43c33 none" id="offerList">
						<li><a class="w120 h30 dsb bcwhite c2d2d2d zi13" style="border-radius:3px 3px 0 0 " href="<%=basePath%>/price/price">我要报价</a></li>
						<li><a class="w120 h30 dsb bcwhite c2d2d2d zi13" style="border-radius: 0 0 3px 3px" href="<%=basePath%>/hall/index">我要收票</a></li>
					</ul>
				</li>
				<li class="fl mt65 ml50"><a href="<%=basePath%>/zhangbu/index" class="c2d2d2d">票据管理</a></li>
				<li class="fl mt65 ml50" id="colorTool" onmousemove="openTool(this)" onmouseout="closeTool(this)">
					<a>工具<img src="https://img.utiexian.com/website/header/open.png" class="w15 h7 ml8" id="imgTool" /></a>
					<ul class="pa w120 lh30 f14 zi11 br3 bad43c33 none" id="toolList">
						<li><a class="w120 h30 dsb bcwhite c2d2d2d zi13" style="border-radius:3px 3px 0 0 " href="<%=basePath%>/homepage/tool/inquiry">我要询价</a></li>
						<li><a class="w120 h30 dsb bcwhite c2d2d2d zi13" href="<%=basePath%>/homepage/tool/calculator">贴现计算器</a></li>
						<li><a class="w120 h30 dsb bcwhite c2d2d2d zi13" href="<%=basePath%>/inquiryreply/inquiryreply">银票查询查复</a></li>
						<li><a class="w120 h30 dsb bcwhite c2d2d2d zi13" href="<%=basePath%>/homepage/tool/lianhang">联行号查询</a></li>
						<li><a class="w120 h30 dsb bcwhite c2d2d2d zi13" href="<%=basePath%>/homepage/tool/gongcui">公催查询</a></li>
						<li><a class="w120 h30 dsb bcwhite c2d2d2d zi13" style="border-radius: 0 0 3px 3px" href="<%=basePath%>/homepage/tool/shibor">Shibor查询</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</header>

<section class="w ha pb30" style="background: url(https://img.utiexian.com/website/fanli/fanliBG.png);background-size:100% auto">
	<!-- 广告图-->
	<img src="https://img.utiexian.com/website/fanli/fanli.png" class="w1000 bc ha dsb">
</section>

<!--foot-->
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
					<li class="mt16"><i class="Ficon Ficon4 mr16"></i>地址：上海市浦东新区陆家嘴环路166号未来资产大厦11C</li>
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
<script type="text/javascript">
$(document).ready(function(){  
	isLogin();
});

/**
 * 验证登录
 */
function isLogin(){
	$.ajax({
		url:"<%=basePath%>/islogin",
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.member!=null){
				var str = "您好，欢迎来到票据管家";
				if(data.member.username!=null){
					str = "您好，"+ data.member.username +"，欢迎来到票据管家";
				}else{
					str = "您好，"+ hideMobile(data.member.mobile) +"欢迎来到票据管家";
				}
				$("#member_name").text(str);
				$("#has_member").show();
				$("#null_member").hide();
				if(data.message!=null){
					$("#message").text("("+data.message+")");
				}
			}
		}
	});
}

//账户信息列表
$("ul.Htab li").each(function(){
	$(this).click(function(){
		$("ul.Htab li").children("a").removeClass("cd43c33").addClass("c2d2d2d");
		$("ul.Htab li").removeClass("MY-SELECT");
		$(this).children("a").removeClass("c2d2d2d").addClass("cd43c33");
		$(this).parent().addClass("MY-SELECT");
	})
});

//报价收票
function openOffer() {
	$("#imgOffer").attr("src","https://img.utiexian.com/website/header/down.png");
	$("#offerList").removeClass("none");
}
function closeOffer() {
	$("#imgOffer").attr("src","https://img.utiexian.com/website/header/open.png");
	$("#offerList").addClass("none");
}
//工具
function openTool() {
	$("#imgTool").attr("src","https://img.utiexian.com/website/header/down.png");
	$("#toolList").removeClass("none");
}
function closeTool() {
	$("#imgTool").attr("src","https://img.utiexian.com/website/header/open.png");
	$("#toolList").addClass("none");
}
</script>
</html>