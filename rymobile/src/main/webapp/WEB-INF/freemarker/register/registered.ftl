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
.ys1 {
	width: 76%;
	margin: 20% 2% 1em 2%;
	font-size: 1.5em;
	color: #FF0;
	text-align: right;
	letter-spacing: -0.1em;
	float:left;
}
.ys2{
	width:20%;
	height:4em;
	background:url(https://img.utiexian.com/rymobile/hongbao/pass1.1.png) no-repeat center;
	background-size:100% 100%;
	float:left;
}
.ys3 {
	width: 90%;
	height: 250px;
	margin: 1em 5%;
	background: url(https://img.utiexian.com/rymobile/hongbao/dk3.png) no-repeat center;
	background-size: 100% 100%;
}
.ys4 {
	width: 80%;
	margin: 0 10%;
	font-size:.9em;
	line-height:1.2em;
	text-align:.6em
}
</style>
<script type="text/javascript">
var token = localStorage["share_token"];
if(!token || token==undefined){
	window.location.href = "../app/register.htm?code=${invitationCode}";
}else{
	localStorage["share_token"] = '';
}
</script>
</head>
<body>
	<div class="ys1">
		<p>您已经是我们的注册用户啦</p>
		<p>点击右上角分享给您的好友</p>
	</div>
	<div class="ys2">
	</div>
	<div class="clear"></div>
	<div class="ys3">
	</div>
	<div class="ys4">
		<p>规则说明：</p>
            <p>
                1.可多次分享，对方一旦注册即双方都拿20元贴现金
            </p>
            <p>
                2.活动的最终解释权归票据管家所有。
            </p>
	</div>
	<input type = "hidden" id="codeHidden" value="${memberId}">
</body>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/rymobile/config.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/rymobile/addRegisterGuanggao.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		localStorage["memberId"] = $("#codeHidden").val();
		addLog(localStorage["memberId"],"p3");
	})
</script>
</html>