[#include "/common/setting.ftl"]
[#include "/common/function.ftl"]
[#macro body title='票据管家' clazz='']

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh">
	<head lang="en">
	    <title>${title}</title>
		<meta charset="utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui" name="viewport"/>
		<meta content="black" name="apple-mobile-web-app-status-bar-style"/>
		<meta content="yes" name="apple-mobile-web-app-capable"/>
		<meta content="yes" name="apple-touch-fullscreen"/>
		<meta content="telephone=no,email=no" name="format-detection"/>
		<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport"/>
		
		<meta name="screen-orientation" content="portrait"/><!-- uc强制竖屏 -->
		<meta name="full-screen" content="yes"/><!-- UC强制全屏 -->
		<meta name="browsermode" content="application"/><!-- UC应用模式 -->
		<meta name="x5-orientation" content="portrait"/><!-- QQ强制竖屏 -->
		<meta name="x5-fullscreen" content="true"/><!-- QQ强制全屏 -->
		<meta name="x5-page-mode" content="app"/><!-- QQ应用模式 -->
		
		<link rel="apple-touch-icon" href="${imagePath}/common/icon/32.png"/><!-- icon图标 -->
		<link rel="Shortcut Icon" href="${imagePath}/common/icon/32.png" type="image/x-icon"/>
		
		<link rel="stylesheet" href="${staticPath}/css/rywap/head.css"/>
		<link rel="stylesheet" href="${staticPath}/css/rywap/wap.css"/>
		<link rel="stylesheet" href="${staticPath}/css/rywap/index.css"/>
		<link rel="stylesheet" href="${staticPath}/css/rywap/foot.css"/>
		<link rel="stylesheet" href="${staticPath}/css/rywap/swiper.min.css"/>
		<link rel="stylesheet" href="${staticPath}/css/rywap/chart.css"/>
		<link rel="stylesheet" href="${staticPath}/css/rywap/window.css"/>
		
		<script type="text/javascript" src="${staticPath}/js/rywap/jquery.min.js"></script>
		<script type="text/javascript" src="${staticPath}/js/rywap/index.js"></script>
		<script type="text/javascript" src="${staticPath}/js/rywap/swiper.min.js"></script>
		<script type="text/javascript" src="${staticPath}/js/rywap/chart.js"></script>
		<script type="text/javascript" src="${staticPath}/js/rywap/function.js"></script>
		<script type="text/javascript" src="${staticPath}/js/rywap/maskWindow.js"></script>
		
		<script type="text/javascript">
		$(document).ready(function() {
			BASEPATH = "${basePath}";
		});
		</script>
	</head>
	<body>
		[#nested/]
	</body>
</html>
[/#macro]

<!-- 这是头部 -->
[#macro header currentmenu='0' title='' refresh=false]
	[#if title!=null && title!='']
		<header class="header">
		    [#if refresh==true]
		    	<a class="head-left-back" href="javascript:void(0);" onclick="self.location=document.referrer;"></a>
		    [#else]
		    	<a class="head-left-back" href="javascript:void(0);" onclick="window.history.back();"></a>
		    [/#if]
		    <h2 class="head-title">${title?default('票据管家')}</h2>
		    <a class="head-right" href="https://m.utiexian.com/app/download.html">APP下载</a>
		</header>
	[#else]
		<header class="header">
			<a class="head-left-logo" href="#"></a>
			<a class="head-left-back hide" href="#"></a>
			<h2 class="head-title hide">title</h2>
			<a class="head-right" href="https://m.utiexian.com/app/download.html">APP下载</a>
		</header>
		<div class="choseTab tac">
			<ul class="row">
			    <li class="btnTab flex1"><a href="${basePath}/wap/index" [#if currentmenu=="1"]class="cred"[/#if]>主页</a></li>
			    <li class="btnTab flex1"><a href="${basePath}/wap/tool" [#if currentmenu=="2"]class="cred"[/#if]>工具</a></li>
			    <li class="btnTab flex1"><a href="${basePath}/wap/inquiryreply/my/page" [#if currentmenu=="3"]class="cred"[/#if]>我</a></li>
			    <li class="btnTab flex1"><a href="javascript:void(0);" class="more">●●●</a></li>
			</ul>
		</div>
		<div class="moreTab tac hide">
			<ul>
			    <li onclick="inquiry();"><a href="javascript:void(0);">银票询价</a></li>
			    <li onclick="calculator();"><a href="javascript:void(0);">计算器</a></li>
			    <li onclick="downLoadApp();"><a href="javascript:void(0);">查询查复</a></li>
			    <li onclick="urge();"><a href="javascript:void(0);">公催查询</a></li>
			</ul>
		</div>
	[/#if]
[/#macro]

<!-- 这是底部 -->
[#macro footer]
<footer class="footer">
    <ul class="row">
    	[#if member??]
	        <li class="flex1 w100p">
	        	<a href="${basePath}/wap/inquiryreply/my/page" class="outH dib w100p">
	        	[#if member.username??]
	        		${member.username}
	        	[#elseif member.mobile??]
	        		${hideMobile(member.mobile)}
	        	[#else]
	        		个人中心
	        	[/#if]
	        	</a>
	        </li>
	        <li class="flex1 w100p" onclick="logout();"><a href="javascript:void(0);" class="outH dib w100p">退出</a></li>
    	[#else]
	        <li class="flex1 w100p" onclick="login();"><a href="javascript:void(0);" class="outH dib w100p">登录</a></li>
	        <li class="flex1 w100p" onclick="register();"><a href="javascript:void(0);" class="outH dib w100p">注册</a></li>
    	[/#if]
        <li class="flex1 w100p" onclick="inquiry();"><a href="javascript:void(0);" class="outH dib w100p">询价</a></li>
        <li class="flex1 w100p" ><a href="${basePath}/wap/tool" class="outH dib w100p">工具</a></li>
    </ul>
</footer>
[/#macro]