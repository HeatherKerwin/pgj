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
    <title>票据管家--座谈会</title>
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
<section class="w ha bcf5f5f5">
    <div class="w videoBanner"></div>
    <div class="w1200 ha bc">
        <h2 class="f40 fb tc mt59">活动现场</h2>
        <p class="f20 mt20 c606060 tc">金融、钢贸、建材等行业的各路小伙伴欢聚一堂，媒体朋友也纷纷赶到现场，主讲嘉宾纵情演说，现场气氛积极活泼。</p>
        <img src="https://img.utiexian.com/website/video/videoIcon.jpg" width="101" height="3" class="bc dsb mt35">
        <div class="w1100 ha bc mt40">
            <div class="fl w260 ml30">
                <img src="https://img.utiexian.com/website/video/photo1.png" width="253" height="347">
                <img src="https://img.utiexian.com/website/video/photo2.png" width="253" height="169" class="mt6">
                <img src="https://img.utiexian.com/website/video/photo3.png" width="253" height="169" class="mt6">
            </div>
            <div class="fl w520">
                <!--；视频-->
                <div class="w">
                    <object poster="https://img.utiexian.com/website/video/photo4.png"  data="//imgcache.qq.com/tencentvideo_v1/playerv3/TencentPlayer.swf?max_age=86400&amp;v=20161128" width="513" height="347" id="3552688aed4473550fae5c3cf39d2089" name="3552688aed4473550fae5c3cf39d2089">
                    	<param name="allowScriptAccess" value="always"> 
                    	<param name="movie" value="//imgcache.qq.com/tencentvideo_v1/playerv3/TencentPlayer.swf?max_age=86400&amp;v=20161128"> 
                    	<param name="quality" value="high"> 
                    	<param name="allowFullScreen" value="true"> 
                    	<param name="play" value="true"> 
                    	<param name="wmode" value="window"> 
                    	<param name="flashvars" value="vid=w0351c4q97q&amp;autoplay=1&amp;volume=50&amp;searchbar=0&amp;showcfg=1&amp;showend=0&amp;openbc=0&amp;list=2&amp;pay=0&amp;shownext=1&amp;share=1&amp;bullet=1&amp;theater=1&amp;skin=https://imgcache.qq.com/minivideo_v1/vd/res/skins/TencentPlayerSkinV5.swf&amp;switch2h5=0&amp;bulletinput=0&amp;attstart=&amp;defnpayver=1&amp;fmt=auto&amp;vstart=0&amp;ptag=www_zhibo8_cc&amp;guid=d388fedd224184fd843d6b460214ba52&amp;mbid=c35ae26dc861ecbe1991abada56e71df&amp;fakefull=1&amp;title=票管家APP创始人曾攀云新版发布演讲&amp;advbullet=1&amp;vend=145&amp;columnId=&amp;rcd_info="> 
                    	<param name="type" value="application/x-shockwave-flash"> 
                    	<param name="pluginspage" value="http://get.adobe.com/cn/flashplayer/"> 
                    	<param name="bgcolor" value="#000000"> 
                   		<div class="txp_overlay_expired">   
                   			<div class="txp_expired_title">陛下，您的Flash插件已过期，无法播放视频了</div>   
                   			<div class="txp_expired_hint">建议您点击下面链接，进入视频观看</div>   
                   			<div class="txp_expired_choice">     
                   				<div class="txp_option txp_option_flash">       
                   					<a href="http://www.adobe.com/go/getflashplayer" target="_blank" class="txp_btn_option" title="升级Flash插件"></a>       
                   					<a href="http://www.adobe.com/go/getflashplayer" target="_blank" class="txp_option_text">升级 Flash 插件</a>     
                   				</div>        
                   			</div> 
                   		</div>  
                   	</object>
                </div>
                <div class="w mt6">
                    <div class="w_50 fl">
                        <img src="https://img.utiexian.com/website/video/photo5.png" width="253" height="348">
                    </div>
                    <div class="w_50 fl">
                        <img src="https://img.utiexian.com/website/video/photo6.png" width="253" height="170">
                        <img src="https://img.utiexian.com/website/video/photo7.png" width="252" height="170" class="mt4">
                        <div class="cb"></div>
                    </div>
                </div>
            </div>
            <div class="w260 fl">
                <img src="https://img.utiexian.com/website/video/photo8.png" width="253" height="170">
                <img src="https://img.utiexian.com/website/video/photo9.png" width="252" height="264" class="mt4">
                <img src="https://img.utiexian.com/website/video/photo10.png" width="252" height="254" class="mt4">
            </div>
            <div class="cb"></div>
            <a href="http://weibo.com/tv/l/CZGdt0RNvzVc0ekW" target="view_window" class="f20 c0a5cb9 mt65 mb50 dsb ti32">票据管家创始人开场演讲：http://weibo.com/tv/l/CZGdt0RNvzVc0ekW</a>
            <a href="https://v.qq.com/x/page/w0351c4q97q.html" target="view_window" class="f20 c0a5cb9 mt65 mb50 dsb ti32">票据管家全场回顾：http://weibo.com/tv/l/CZGdt0RNvzVc0ekW</a>
        </div>
    </div>
    <div class="w ha bcwhite pt50 pb50">
        <div class="w1200 ha bc">
            <h2 class="f40 fb tc">媒体报道</h2>
            <p class="f20 mt20 tc c606060">票管家企业发展高峰论坛在沪召开</p>
            <img src="https://img.utiexian.com/website/video/videoIcon.jpg" width="101" height="3" class="bc dsb mt35">
            <div class="w ha mt73">
                <img src="https://img.utiexian.com/website/video/media1.jpg" width="393" height="152" class="fl">
                <img src="https://img.utiexian.com/website/video/media2.jpg" width="393" height="152" class="fl ml10">
                <img src="https://img.utiexian.com/website/video/media3.jpg" width="393" height="152" class="fr">
                <div class="cb"></div>
            </div>
            <div class="w700 ha bc mt35">
                <img src="https://img.utiexian.com/website/video/media4.jpg" width="344" height="222" class="fl">
                <img src="https://img.utiexian.com/website/video/media5.jpg" width="344" height="222" class="fr">
                <div class="cb"></div>
            </div>
            <p class="f16 c606060 lh35 ti32 mt59">
                由上海票管家金融服务有限公司主办，宁波银行协办的“立于大潮，共谋企业之生存”企业发展高峰论坛25日在上海举行。
            </p>
            <p class="f16 c606060 lh35 ti32">
                中小微企业何以在当下经济环境下寻找机会，谋求新发展，是当今的热门话题之一。此次论坛以探讨“于当今经济大潮下，企业应如何顺应时代生存，如何解决票据疑难，如何以更低成本贴现，如何面对以电票为主流发展趋势的票据界的影响等问题”为主，由票据管家团队携手宁波银行及国内知名票据专家，为到场嘉宾解答了新时代的产业新理论，提供了新思路。
            </p>
            <p class="f16 c606060 lh35 ti32">
                来自企业方的资料显示，上海票管家金融服务有限公司位于上海陆家嘴金融区，企业致力于打造全国最专业的金融机构间可交易资产撮合平台，主营商业承兑汇票贴现撮合、银行承兑汇票贴现撮合、以及从事金融业务流程外包等。票据管家APP，是专门针对票据客户贴现需求推出的一个平台，集在线询价、贴现及票据管理等功能于一身。
            </p>
            <p class="f16 c606060 lh35 ti32">
                论坛以票据管家APP新版本上线仪式启动，随后请到两位重要嘉宾到场演讲，并进行了深度对话。
        </p>
            <p class="f16 c606060 lh35 ti32">
                论坛上专家就于以微小力量帮助改变不利于中小微实体企业的经济现状，结合中小微企业的特点和实际困难，为其提供机会聚集讨论并促进业务交流，如何发现问题，解决问题进行了互动交流。
        </p>
            <p class="f16 c606060 lh35 ti32">
                此次活动总有超过百家企业、近150人参与。
            </p>
        </div>
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