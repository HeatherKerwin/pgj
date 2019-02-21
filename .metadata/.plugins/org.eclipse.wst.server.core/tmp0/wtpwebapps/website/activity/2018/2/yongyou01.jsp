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

<!-- 内容 -->
<div class="w bcwhite flex flex-direction-column flex-justify-center tc">
	<!-- banner -->
	<img src="https://img.utiexian.com/website/activity/2018/2/banner.jpg" class="w ha bc">
	
	<!-- 如何走好生态共赢之路 -->
	<div class="flex flex-justify-center w bcf5f5f5">
		<div class="flex-row flex-direction-column w1200  tc c666666 pt50 pb50">
			<div class="f18 w">如何走好生态共赢之路</div>
			<div class="w30 h2 bce84c3d bc mt16"></div>
			<div class="flex-row flex-wrap flex-justify-space-around w ">
				<div class="flex-row flex-direction-column flex-justify-center mt50">
					<img src="https://img.utiexian.com/website/activity/2018/2/zengzong.png" width="350" height="230" class="br3">
					<div class="mt16 f14">主持人  曾攀云</div>
				</div>
				
				<div class="flex-row flex-direction-column flex-justify-center mt50">
					<img src="https://img.utiexian.com/website/activity/2018/2/pan.png" width="350" height="230" class="br3">
					<div class="mt16 f14">对话嘉宾  潘良波
</div>
				</div>
				
				<div class="flex-row flex-direction-column flex-justify-center mt50">
					<img src="https://img.utiexian.com/website/activity/2018/2/yu.png" width="350" height="230" class="br3">
					<div class="mt16 f14">对话嘉宾  于光辉</div>
				</div>
				
				<div class="flex-row flex-direction-column flex-justify-center mt50">
					<img src="https://img.utiexian.com/website/activity/2018/2/fu.png" width="350" height="230" class="br3">
					<div class="mt16 f14">对话嘉宾  傅毅</div>
				</div>
				
				<div class="flex-row flex-direction-column flex-justify-center mt50">
					<img src="https://img.utiexian.com/website/activity/2018/2/zhang.png" width="350" height="230" class="br3">
					<div class="mt16 f14">对话嘉宾  张聪
</div>
				</div>
				
				<div class="flex-row flex-direction-column flex-justify-center mt50">
					<img src="https://img.utiexian.com/website/activity/2018/2/heyingyi.png" width="350" height="230" class="br3">
					<div class="mt16 f14">集体</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 会议内容讨论 -->
	<div class="flex flex-justify-center w">
		<div class="flex-row flex-direction-column w1200 c666666 pt50 pb75">
			<div class="f18 w tc">会议内容讨论</div>
			<div class="w30 h2 bce84c3d bc mt16"></div>
			<div class="f16 lh30">
				<p>主持人：很荣幸成为今天咱们圆桌会议的主持人，我先自我介绍一下，我是票据管家的创始人曾攀云。今天屋外非常寒冷，我们的会场内大家是热情高涨。</p>
				<p class="ti32 f16 lh30 mt40">云的概念我印象当中是2006年被第一个提出，在此之前没有云说法的时候，很多企业在自己的细分领域非常专注做着自己精准的业务。很多走在前面的企业发现一个问题，走着走着发现有些客户有弱相关性的服务需求，这些弱相关性服务需求是我们迅速的赶上，补齐，还是淡化过去呢？所以大家在想，怎么办。如果自己做不现实，因为客户的弱相关性需求是分布式发布的，我不可能什么都做。而且包括客户的竞争性、流失性都在。</p>
				<p class="ti32 f16 lh30 mt40">我们现在在想，是不是大家应该共同把资源集合起来，把市场做的更大，饼画的足够大的时候，即使占了一小块，我们也吃的很饱了。我本身是一个互联网新人，主办方给了我三个问题，我是谁，我从哪里，我去到哪？第一个问题，面对变化我们该如何清晰的自我定位，并且快速反应，塑造一个强有力的迎接挑战？第二个问题是我们面对互联网新融合的时候，应该怎么把繁琐做减法，产生的效果做加法。第三个问题，其实每个人都在想不忘初心，初心是一个词，听上去好像很容易，但是是一种力量，是发自内心的源泉，如何真正做到不忘初心...<a href="<%=basePath%>/activity/2018/2/yongyou02.jsp" class="c3366cc cp">[查看详情]</a></p>
			</div>	
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