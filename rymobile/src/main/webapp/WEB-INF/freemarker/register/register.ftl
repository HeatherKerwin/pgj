<!doctype html>
<html>
<head>
<title>注册领红包</title>
<link rel="shortcut icon" href="https://m.utiexian.com/favicon.ico"/>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<style>
* {
	margin: 0;
	padding: 0
}
html {
	width: 100%;
	font-family: 'Heiti SC', 'Microsoft YaHei';
	/*font-size: 100px;*/
	outline: 0;
	-webkit-text-size-adjust: none;
}
body {
	width: 100%;
	margin: 0;
	color: #fff;
	-webkit-user-select: none;
	position: relative;
	/*background: #fff;*/
	background-color: #f74b33;
	z-index: 1
}
/*@font-face {
	font-family: hyjinchangtij;
	src:url(fonts/HYJinChangTiJ.ttf),
	url(fonts/hyjinchangtij.eot),
	url(fonts/hyjinchangtij.svg),
	url(fonts/hyjinchangtij.woff); /* IE9 
} */
a:hover, a:link, a:visited, a {
	color: inherit;
	text-decoration: none;
}
ul, li { list-style: none }
.clear {
	clear: both;
	height: 0;
	display: block
}
.dk1 {
	width: 98%;
	margin: 1em 1%;
	font-size: 1.4em;
	color: #FF0;
	text-align: center;
	letter-spacing: -0.1em;
}
.dk2 {
	width: 96%;
	font-family:'hyjinchangtij';
	margin: -0.8em 2% 0 2%;
}
.dk2 img{
	width:100%;
	height:auto;
}
	
.dk2 p {
	width: 45%;
	float: left;
	text-align: center;
	font-size: 1.2em;
	color: #333;
}
.dk3 {
	width: 100%;
	height: 150px;
	margin: 0 0 1em;
	background: url(https://img.utiexian.com/rymobile/hongbao/tiexian.png) no-repeat center;
	background-size: 100% 100%;
}
.ck4 {
	width: 80%;
	font-size: 1.2em;
	padding: .5em  0;
	background-image: -moz-linear-gradient(top, #fee670, #fbcb4a); /* Firefox */
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #fbcb4a), color-stop(1, #fee670)); /* Saf4+, Chrome */
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fbcb4a', endColorstr='#fee670', GradientType='0'); /* IE*/
	border: solid .1em #FF8a00;
	border-radius: .5em;
	margin: .5em 10% 1em;
	text-align:center;
}
.dk5 {
	width: 94%;
	margin: 0 3% 2em;
	font-size:.8em;
	line-height:1.2em;
	text-align:.6em
}
form {
	margin: 0  0 0 5%;
	width: 95%;
}
label {
	width:22%;
	font-size: 1em;
	color: #181818;
	line-height: 2.4em;
	margin-right:1%;
}
.c_code_msg1{
	width:70%;
	float:left;
	color:#ccc;
	margin-right:2%;
}
.c_code_msg2{
	width:40%;
	float:left;
	color:#ccc;
	margin-right:2%;
}
input {
	height: 2.4em;
	border-style: none;
	border-radius: .3em;
	padding: 0 ;
	border: 1px solid #C8C8C8;
	outline: none;
}
.msgs {
	display: inline-block;
	width: 30%;
	color: #fff;
	font-size: .8em;
	border: 1px solid #0697DA;
	text-align: center;
	height: 2em;
	line-height: 2em;
	background: #0697DA;
	cursor: pointer;
	border-radius: .3em;
	margin-top:.3em;
}
form .msgs1 {
	background: #E6E6E6;
	color: #818080;
	border: 1px solid #CCCCCC;
}
</style>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function () {
	localStorage["share_token"] = true;
	//获取短信验证码
	var validCode=true;
	$(".msgs").click (function() {
		var mobile = $("#mobile").val();
		var time=60;
		var code=$(this);
		if (validCode) {
			validCode=false;
			code.addClass("msgs1");
			if(!checkMobile(mobile)){
				alert("手机号码不正确!");
				code.removeClass("msgs1");
				validCode = true;
				return;
			}
			$.ajax({
				url:"../app/sendcode.htm",
				type:"post",
				data:{"mobile":mobile},
				dataType:"text",
				success:function(data){
					if(data=="error"){
						alert("发送失败，请稍后...");
						code.removeClass("msgs1");
						validCode = true;
					}else{
						var t=setInterval(function(){
							time--;
							code.html(time+"秒");
							if (time==0) {
								clearInterval(t);
								code.html("重新获取");
								validCode=true;
								code.removeClass("msgs1");
							}
						},1000);
					}
				}
			});
		}
	});
});

//验证手机号码
function checkMobile(str) {
	var telReg = !!str.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
	//如果手机号码不能通过验证
	return telReg;
}

function save(){//表单请求
	addLog(localStorage["memberId"],"b1");
	var mobile = $("#mobile").val();
	var code = $("#code").val();
	var invitationCode = $("#invitationCode").val();
	if(mobile==null || mobile.trim()==""){
		alert("请输入手机号！");
		return false;
	}
	if(code==null || code.trim()==""){
		alert("请输入验证码！");
		return false;
	}
	$("#my-form").submit();
}
</script>
</head>
<body>
	<div class="dk1">
		您的好友邀请您使用票据管家贴现啦！
	</div>
	<div class="dk2">
		<img src="https://img.utiexian.com/rymobile/hongbao/font.png"/>
	</div>
	<div class="clear"></div>
	<div class="dk3"></div>
	<form id="my-form" action="../app/registerToDo.htm" method="post">
		<label style="float:left;">
			手
			机
			号
		</label>
		<input type="text" id="mobile" name="mobile" class="c_code_msg1" value="" placeholder="请输入手机号">
		<div style="clear:both"></div>
		<label style="float:left;">
			验
			证
			码
		</label>
		<input type="text" id="code" name="code" class="c_code_msg2" value="" placeholder="请输入验证码">
		<input type="hidden" id="invitationCode" name="invitationCode" value="${invitationCode}">
		<span class="msgs">
			获取短信验证码
		</span>
	</form>
	<input type="button" onclick="save();" class="ck4" value="快速注册领取20元贴现金">
	<div class="dk5">
		<p>规则说明：</p>
        <p>
            1.可多次分享，对方一旦注册即双方都拿20元贴现金
        </p>
        <p>
            2.活动的最终解释权归票据管家所有。
        </p>
	</div>
	<div class="ck6">
	</div>
	<input type = "hidden" id="codeHidden" value="${memberId}">
</body>
<script type="text/javascript" src="https://static.utiexian.com/js/rymobile/config.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/rymobile/addRegisterGuanggao.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		localStorage["memberId"] = $("#codeHidden").val();
		addLog(localStorage["memberId"],"p1");
	})
</script>
</html>