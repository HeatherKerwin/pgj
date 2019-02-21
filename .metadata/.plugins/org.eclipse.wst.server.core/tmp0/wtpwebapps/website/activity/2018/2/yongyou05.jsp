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

<!-- 十年企业我们帮客户带来的既得利益，我们帮你带来什么 -->
<div class="flex flex-justify-center w pt25 pb50">
	<div class="flex-row flex-direction-column w1200 c666666 bcwhite pt30 pb40">
		<!-- 对话主题 -->
		<div class="f16 w tc c2d2d2d">十年企业我们帮客户带来的既得利益，我们帮你带来什么 </div>
		
		<!-- 对话内容 -->
		<div class="flex-row flex-direction-column pl50 pr50 pt40 pb40 f14 lh24">
			<!-- 01 -->
			<div class="flex-row flex-direction-row pr50">
				<img src="https://img.utiexian.com/website/activity/2018/2/zeng.png" width="70" height="70" class="flex-row">
				<div class="flex flex-direction-column ml15">
					<div class="f16 c2d2d2d">主持人</div>
					<div><span class="pl14 pr14 lh27 c606060 bceeeeee tc mt6 mb5 dsib">票据管家CEO 曾攀云</span></div>
					<div>非常感谢因为有了用友，我们才会有更大的机会。我们做企业过程当中碰到一个问题，过去、现在、未来。未来我们可能考虑的更多，比如说票据管家，我们要让贴现更容易。所以我们要做的是当下为您节省每一分成本。用友今年30岁，潘总是14岁，我们还是刚出生没多久小朋友。我特别想向大家请教这样的问题，十年企业我们帮客户带来的既得利益，我们帮你带来什么。30年的企业可能改变客户的认知。50年呢？欧洲、美国、日本都有很多百年企业，他们的初心是怎样在这个方向当中做成指引的呢？这个问题比较大，我没有资格回答这个问题。我们请傅总来回答一些。</div>
				</div>
			</div>
			<!-- 02 -->
			<div class="flex-row flex-direction-row flex-justify-end mt35 pl50">
				<div class="flex-row flex-direction-column tr mr15">
					<div class="f16 c2d2d2d">参会者</div>
					<div><span class="pl14 pr14 lh27 c606060 bceeeeee tc mt6 mb5 dsib">用友网络副总裁  傅毅</span></div>
					<div class="tl">用友确实经历过三个阶段的发展，从最早期的财务软件开始，通过先进产品与技术来改变整个中国会计行业业务模式，这是我们最早的初衷，我们通过财务软件来改变整个行业的业务问题。那个时候可能用友提出的理念是属于面向于功能、面向于产品的层次。到了第二个阶段我们做ERP，ERP解决企业内部信息化问题。那个时候用友上了一个层级，用友做ERP的时候，过去做财务软件的时候营销方式主要是讲功能、做培训，帮助企业使用，到了ERP的时候就不行了，你如果只是讲功能做培训适应不了业务发展。第二阶段用友做ERP、信息化、咨询加方法论，引入了很多方法论，比如说顾问营销方法论、企业整体规划、企业整体模式的方式，所以第二阶段用友从自身的理念，我们又做了一个提升。用友更多考虑为企业的管理创新、商业管理做好服务。是这样的定位。<br><br>
今天到了用友云，王总又发生了变化，既然是云，是社会的问题，是生态的问题。所以用友再次提升我们的理念，我们要为这个社会创造更大的价值，要为这个产业创造更大的价值，要成为产业的服务平台。过去用友考虑为企业创造企业，第二阶段是为业务为这个部门创造价值，今天是用友为产业创造价值。所以用友30年经历三个阶段，也是王总在不断的升级。不是用友自身的角度，也不是企业的角度，是产业。所以为什么推出用友云，要有用友的模式，就是大家一起，我们通过产业整合帮助企业，把产业伙伴带动起来。今天王总在整个业务布局当中，在我们的营销维度、产品维度、产业维度，任何一个层次的业务都要思考，研发考虑生态维度没有等等。大家放心用友公司走的比较稳健，大公司一旦战略上下了战略，就会在执行上坚决执行，主要是考核、资源、预算、投入，就要坚决执行。用友一定会落地这个事。</div>
				</div>
				<div class="flex-row w70">
					<img src="https://img.utiexian.com/website/activity/2018/2/fuyi.png" width="70" height="70">
				</div>
			</div>
			<!-- 03 -->
			<div class="flex-row flex-direction-row mt35 pr50">
				<div class="flex-row w70">
					<img src="https://img.utiexian.com/website/activity/2018/2/zeng.png" width="70" height="70">
				</div>
				<div class="flex flex-direction-column ml15">
					<div class="f16 c2d2d2d">主持人</div>
					<div><span class="pl14 pr14 lh27 c606060 bceeeeee tc mt6 mb5 dsib">票据管家CEO 曾攀云</span></div>
					<div>我听下来傅总少说了一点，但是一定是用友的初心，帮助中国更多50年甚至上百年的企业出现。所以我们也很希望在用友带领下能够成长为一棵大树。像潘总企业14年了，这里面也经历了很精彩的故事。</div>
				</div>
			</div>
			<!-- 04 -->
			<div class="flex-row flex-direction-row flex-justify-end mt35 pl50">
				<div class="flex-row flex-direction-column tr mr15">
					<div class="f16 c2d2d2d">参会者</div>
					<div><span class="pl14 pr14 lh27 c606060 bceeeeee tc mt6 mb5 dsib">北京金万维科技有限公司助理总裁 潘良波</span></div>
					<div class="tl">用友带动了行业很多企业的发展，像今天看到友创团，成功的带领一批又一批。这个话题可能有发言权，在B2B圈里面一帮朋友笑话我们，我们公司叫小老头创业公司。但是我也挺佩服，最早罗总阐述了创业公司的想法，我在公司待了十几年，跟罗总做到现在，在国内我们也做的比较靠前。这个过程我觉得挺让佩服罗总一件事情，就是14年，从我最开始进公司想法定位，到现在十多年没有变化，很清晰，很坚持，就是围绕企业的信息化这一块做事情，但是我绝对不去碰管理软件，我给大家做做周边的服务，我给大家配合。这样的话我们开放，跟谁合作，绝对不跟大家拼。杨总这个产品跟我们最近做的，大家切入点不一样，说明行业出现更多的公司，这个公司大家都是对的，千亿级公司都是没有问题。这么多优秀的企业做这件事情，说明这件事情方法是对的。其二，大家可以互相比肩跑，大家都会好。大家在这个行业里面切入的不一样，以后我们围绕我们原来的想法做，这肯定是不会变的。我们态度很开放，我们所有的产品都是开放的API，欢迎跟大家任何的产品做对接，只要有需求，我们一起来做。因为企业的需求我们只做一小点，横向我们没有开放，愿意跟大家一起合作。希望在用友带领下，跟老大哥们一起把这件事情做好，可能我们就值了。</div>
				</div>
				<div class="flex-row w70">
					<img src="https://img.utiexian.com/website/activity/2018/2/panliangbo.png" width="70" height="70">
				</div>
			</div>
		</div>
		
		<!-- 分页 -->
		<div class="flex-row flex-direction-row w456 bc tc lh35">
			<a href="<%=basePath%>/activity/2018/2/yongyou04.jsp" class="bae0e0e0 w88 c979797 disb cp">上一页</a>
			<div class="flex-row flex-direction-row flex-justify-space-around w">
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou02.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">1</a>
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou03.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">2</a>
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou04.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">3</a>
				<a style="width: 35px;"  class="w35 h35 bae84c3d ce84c3d cp">4</a>
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou06.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">5</a>
				<a style="width: 35px;"  href="<%=basePath%>/activity/2018/2/yongyou07.jsp" class="w35 h35 bae0e0e0 c979797 disb cp">6</a>
			</div>
			<a href="<%=basePath%>/activity/2018/2/yongyou06.jsp" class="bae0e0e0 w88 c979797 disb cp">下一页</a>
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