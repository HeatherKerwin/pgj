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
<section class="w1200 bc mt20 mb20 pb30 bcwhite">
	<!-- 广告图-->
	<img src="https://img.utiexian.com/website/zuotanhui/ZTbanner.jpg" class="w ha">
	<div class="wa ml30 mr30">
		<!-- 表单-->
		<ul class="wa mt30 pl20 pr20 bae0e0e0 lh34 pt30 pb30 br5">
			<li class="wa h45 xuxian">
				<label for="company" class="fl w150">企业名称<span style="color:red"> *</span></label>
				<input type="text" id="company" class="fl w500 h34 bae0e0e0 br5">
			</li>
			<li class="wa h45 xuxian mt20">
				<label for="name" class="fl w150">联系人<span style="color:red"> *</span></label>
				<input type="text" id="name" class="fl w500 h34 bae0e0e0 br5">
			</li>
			<li class="wa h45 xuxian mt20">
				<label for="phone" class="fl w150">联系方式<span style="color:red"> *</span></label>
				<input type="text" id="phone" class="fl w500 h34 bae0e0e0 br5">
			</li>
			<li class="wa h45 xuxian mt20">
				<label for="code" class="fl w150">验证码<span style="color:red"> *</span></label>
				<input type="text" id="code" class="fl w390 h34 bae0e0e0 br5">
				<input type="button" value="获取验证码" class="fl w100 h35 b0 bce84c3d br5 ml10 cwhite" id="phone_btn1" name="phone_btn" onclick="showtime(60);"/>
			</li>
			<li class="wa h45 xuxian mt20">
				<label for="address" class="fl w150">所在地</label>
				<input type="text" id="address" class="fl w500 h34 bae0e0e0 br5">
			</li>
			<div class="cb"></div>
			<!-- 提交资料说明-->
			<p class="wa mt20">
				资料提交成功后，我们的客服会在1个工作日内与您联系，请您耐心等候<br>如果有其他问题欢迎直接沟通我们的客服：<br>客服电话：400-067-0002
			</p>
			<div class="w245 bc">
				<input type="button" value="提交" class="w245 h42 lh42 b0 bce84c3d cwhite f18 br5 mt30 cp" onclick="save();">
			</div>
		</ul>
		<!-- 活动信息-->
		<div class="w mt20 lh40">
			<div><span class="fb">活动时间：</span>2016年11月25日星期五14：00</div>
			<div><span class="fb">活动地址：</span>上海浦东新区陆家嘴环路1288号凯宾斯基大酒店二楼哈瓦那厅</div>
			<div class="w">
				<img src="https://img.utiexian.com/website/zuotanhui/hotel.jpg" class="w556 ha fl">
				<img src="https://img.utiexian.com/website/zuotanhui/map.jpg" class="w556 ha fr">
				<div class="cb"></div>
			</div>
			<p class="fb mt10">交通信息：</p>
			<p class="lh30">地铁：地铁2号线陆家嘴站2号口出步行600米<br>
			公交车：119,792,818,浦东15路,607路,陆家嘴金融城3路公交泰东路<br>
			渡口站；774,779,798,799,85,971,992,陆家嘴金融城4路陆家嘴地铁站<br>
			停车位：酒店有停车场
			</p>
			<p><span class="fb">活动流程：</span>1 来宾签到</p>
			<p class="lh30">2 发布会启动<br>
			3 新品详解<br>
			4 两位神秘嘉宾发表演讲<br>
			5 圆桌会议<br>
			6 答谢致辞</p>
		</div>
	</div>
</section>
	<!-- 弹窗-->
<section class="w h pf bc05 zi10 top" id="ZTtcdiv" style="display:none;">
	<div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
		<!--关闭按钮-->
		<div class="w37 h37 pa t-15 r-15 zi13 redClose cp"></div>

		<div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
			<div class="pr t80">
				<img id="qrcode" src="" class="w300 h300 dsb bc">
				<p class="w f30 lh45 tc mt20">请您拍照并保存此<span class="fb">二维码</span><br>或者凭<span class="fb">手机号</span>入场<br>谢谢</p>
			</div>
		</div>
	</div>
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
					$("#message").text(data.message);
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

//验证
function phone(){
	var x=document.getElementById("phone").value;
	document.getElementById("phone").value=x.toUpperCase();
}
function checkMobile(str) {
	var telReg = !!str.match(/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$)/);
	//如果手机号码不能通过验证
	if(telReg == false){
		alert("请输入正确的手机号！");
		return false;
	} else {
		return true;
	}
}
function showtime(t){
	//验证时间（活动过期限制）
	var flag = new Date("2016-11-25");
	var now = new Date();
	if(flag<now){
		alert("该活动已结束，敬请期待下一期！");
        return false;
	}
	
	var phone = $("#phone").val();
	if (checkMobile(phone)) {
		$.getJSON(mobilePath+"/discussion/sendcode.htm?jsonCallback=?",{"phone":phone},
		    function (data) {
				if("success"==data.response){
					document.getElementById("phone_btn1").disabled=true;
					for(i=1;i<=t;i++) {
						window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
					}
					window.setTimeout(function () {
						document.getElementById("phone_btn1").disabled=false;
					}, t * 1000 + 1000);
					alert(data.msg);
				}else{
					alert(data.msg);
				}
		    }
		);
	}
}
function update_p(num,t) {
	if(num == t) {
		document.getElementById("phone_btn1").value = " 重新发送 ";
	}else {
		printnr = t-num;
		document.getElementById("phone_btn1").value = printnr +"s重新发送";
	}
}

function save(){
	//验证时间（活动过期限制）
	var flag = new Date("2016-11-25");
	var now = new Date();
	if(flag<now){
		alert("该活动已结束，敬请期待下一期！");
        return false;
	}
	
	var company = $("#company").val();
	var name = $("#name").val();
	var phone = $("#phone").val();
	var code = $("#code").val();
	var address = $("#address").val();
	if(company==null || company.trim()==""){
		alert("请输入企业名称");
		return null;
	}
	if(name==null || name.trim()==""){
		alert("请输入联系人");
		return null;
	}
	if(phone==null || phone.trim()==""){
		alert("请输入联系方式");
		return null;
	}
	if(code==null || code.trim()==""){
		alert("请输入验证码");
		return null;
	}

	$.ajax({
		url:mobilePath+"/discussion/create.htm?jsonCallback=?",
		type:"POST",
		data:{"company":company,"name":name,"phone":phone,"address":address,"code":code},
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				var result = data.data;
				$("#qrcode").attr("src",data.imgPath+result.imgPath);//二维码图片访问路径
				$("#ZTtcdiv").show();
			}else{
				alert(data.msg);
			}
		}
	});
}
$("#TC_open").click(function(){
	$("#ZTtcdiv").removeClass("none");
});
$(".redClose").click(function(){
	$("#ZTtcdiv").addClass("none");
});
</script>
</html>