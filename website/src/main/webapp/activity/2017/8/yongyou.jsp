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
    <title>票据管家--用友云</title>
    <link rel="shortcut icon" href="https://img.utiexian.com/common/icon/32.png">
    <link rel="icon" href="https://img.utiexian.com/common/icon/32.png" sizes="32x32">
    <link rel="icon" href="https://img.utiexian.com/common/icon/192.png" sizes="192x192">
    <link rel="apple-touch-icon-precomposed" href="https://img.utiexian.com/common/icon/180.png">
    <meta name="msapplication-TileImage" content="https://img.utiexian.com/common/icon/270.png">
    
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css"/>
	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css"/>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/video.css"/>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/txp_flash.css">
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
			<a href="<%=basePath%>/index.html"><img src="https://img.utiexian.com/website/header/logo.png" alt="" class="fl mt50 mr5"></a>
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
<section class="w ha bcf5f5f5 pb50">
    <div class="w tc" style="background: url('https://img.utiexian.com/website/video/YYbanner.jpg');background-position: center;background-repeat:no-repeat; background-size:auto 100%; height: 714px;"></div>
    <div class="w1000 ha bc">
        <h2 class="f20 mt35">迈向云端，创变未来</h2>
        <img alt="图为乌镇互联网国际会展中心主会场" src="https://img.utiexian.com/website/video/YYxianchang.png" height="400" width="1000" class="mt30">
        <p class="f14 ca7a7a7">图为乌镇互联网国际会展中心主会场</p>
        <p class="f16 mt20 c606060 ti35">2017年8月19日，主题为“迈向云端 创变未来”的2017中国企业互联网大会在浙江乌镇成功召开，超过5000位来自各行业企业家与高管、专家与学者以及媒体代表参与本次盛会，共同探讨在数字营销与客服、智能制造、财务、人力资源、社交与协同办公、企业金融等企业服务领域的云上腾飞之路。</p>
        <h2 class="f24 mt50">活动现场</h2>
        <div class="w cl">
        	<div class="fl mt30 mr20">
        		<video controls="controls" loop="loop" autoplay="autoplay" poster="https://img.utiexian.com/website/video/YYzhibo.png" width="490">
				  <source src="https://img.utiexian.com/website/video/1ed87171_2c8d_11e6_a637_1051721bdaaaa.131471478113369873.1503126008.mp4" type="video/mp4" />
				设备不支持
				</video>
	        </div>
        	<img alt="" src="https://img.utiexian.com/website/video/YYxianchang1.png" class="fl mt30" width="490" height="276">
        </div>
        <img alt="" src="https://img.utiexian.com/website/video/YYxainchang2.png" height="325" width="1000" class="mt20">
        <a href="https://v.qq.com/x/page/t0540xh8g8s.html" target="view_window" class="f20 c0a5cb9 mt40 dsb ti10">视频文件的链接是：https://v.qq.com/x/page/t0540xh8g8s.html </a>
        <p class="f16 mt30 c606060 ti35">票据管家创始人曾攀云女士受邀参加“用友云生态圆桌派系列直播”，畅聊票据管家存在的关键价值，即如何利用互联网模式解决市场信息不对称及信任问题，从而为客户快速、安全、低成本得解决票据贴现问题。
        </p><p class="f16 mt10 c606060 ti35">
        本次论坛汇聚了中国最具实力和最具创新的互联网公司，包括<span class="cd43c33">阿里云，百度，网易，华为，360</span>等在内的知名企业代表参与了此次大会，并共同聚首用友云生态伙伴分论坛，探讨如何通过企业服务融合，实现客户及生态共赢。
        </p>
        <div class="w cl">
        	<div class="w_50 fl">
        		<h2 class="f24 mt50">证书授权</h2>
        		<img alt="证书授权" src="https://img.utiexian.com/website/video/YYzhengshu.png" class="fl mt30 mb13" width="490" height="371">
        		<p class="f12 ca7a7a7">图为用友云授予票据管家“用友云市场产品与服务融合型伙伴”证书</p>
        		<p class="f16 c606060 mt20 ti32 lh24 mr10">票据管家被正式授牌成为首批用友云市场产品与服务融合型伙伴，这也标志着票据管家与用友的深度合作迈出了坚实一步，票据管家将与用友云产品友账表进行产品互嵌，为用友云的企业用户提供最全面的票据类服务。</p>
        	</div>
        	<div class="w_50 fl">
        		<h2 class="f24 mt50 ml10">入驻用友云市场</h2>
        		<img alt="入驻用友云市场" src="https://img.utiexian.com/website/video/YYruzhu.png" class="fl mt30 ml10 mb13" width="490" height="371" />
        		<p class="f12 ca7a7a7 ml10">图为票据管家成为首批入驻用友云市场的融合型伙伴</p>
        		<p class="f16 c606060 mt20 ti32 ml10 lh24">用友云将面向首批“用友云市场产品与服务融合型伙伴”开放销售渠道，首批融合型伙伴的产品与服务也是用友云的有利补充，通过这种方式，用友云将与生态伙伴实现最大程度的共赢。</p>
        	</div>
        	<div class="cb"></div>
        </div>
        <h2 class="f24 mt50">票据管家，安全护航</h2>
        <img alt="票据管家，安全护航" src="https://img.utiexian.com/website/video/YYhuhang.png" class="mt30" width="1000" height="325" />
        <p class="f16 c606060 mt50 ti32 lh26">票据管家致力于利用互联网模式解决传统票据市场信息不对称的痛点。</p>
        <p class="f16 c606060 ti32 lh26">
		票据管家实时收集市场多方报价并由后台科学的系统算法计算得出最优可成交的单一最优报价，此价格可为市场指导价供贴现企业议价，亦可直接在平台上下单寻求贴现。</p>
		<p class="f16 c606060 ti32 lh26">
		票据管家成功省去了企业前期询价比价的时间成本，并有效降低了企业贴现成本。后期，随着票据管家版本的迭代更新，票据管家将会与银行携手合作解决电票时代“先背书还是先打款“的信任问题，为平台用户交易的安全性保驾护航。</p>
        
    </div>
</section>
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
};
function closeOffer() {
	$("#imgOffer").attr("src","https://img.utiexian.com/website/header/open.png");
	$("#offerList").addClass("none");
};
//工具
function openTool() {
	$("#imgTool").attr("src","https://img.utiexian.com/website/header/down.png");
	$("#toolList").removeClass("none");
};
function closeTool() {
	$("#imgTool").attr("src","https://img.utiexian.com/website/header/open.png");
	$("#toolList").addClass("none");
};

//验证
function phone(){
	var x=document.getElementById("phone").value;
	document.getElementById("phone").value=x.toUpperCase();
};
function checkMobile(str) {
	var telReg = !!str.match(/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$)/);
	//如果手机号码不能通过验证
	if(telReg == false){
		alert("请输入正确的手机号！");
		return false;
	} else {
		return true;
	}
};
</script>
</html>