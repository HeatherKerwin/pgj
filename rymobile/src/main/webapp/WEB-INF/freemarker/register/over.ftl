<!doctype html>
<html>
<head>
<title>注册成功</title>
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
.qw1 {
	width: 96%;
	margin: 5%  2% .5em;
	font-size: 1.5em;
	color: #FF0;
	text-align: center;
	letter-spacing: -0.1em;
}
.cg2 {
	width: 100%;
	height: 150px;
	margin:0 0 ;
	background: url(https://img.utiexian.com/rymobile/hongbao/jiayou.png) no-repeat center;
	background-size: 100% 100%;
}
.cg3 {
	width: 80%;
	font-size: 1.3em;
	padding: .5em 1em;
	background-image: -moz-linear-gradient(top, #fee670, #fbcb4a); /* Firefox */
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #fbcb4a), color-stop(1, #fee670)); /* Saf4+, Chrome */
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fbcb4a', endColorstr='#fee670', GradientType='0'); /* IE*/
	border: solid .1em #FF8a00;
	border-radius: .5em;
	margin: .5em 10% 1em;
}
.cg4 {
	width: 80%;
	margin: 0 10%;
	font-size:.9em;
	line-height:1.2em;
	text-align:.6em
}
</style>
</head>
<script>
function down(){
	addLog(localStorage["memberId"],"b4");
	window.location.href='https://app.utiexian.com/down.html';
}
</script>
<body>
	<div class="qw1">
		<p>啊哦~您来晚了</p>
		<p>红包已领取完毕</p>
	</div>
	
	<div class="cg2">
	</div>
	<input type="button" onclick="down();"  class="cg3" value="请下载App关注下期活动">
	<div class="cg4">
		<p>规则说明：</p>
            <p>
                1.可多次分享，对方一旦注册即双方都拿20元贴现金
            </p>
            <p>
                2.活动的最终解释权归票据管家所有。
            </p>
	</div>
</body>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/rymobile/config.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/rymobile/addRegisterGuanggao.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		addLog(localStorage["memberId"],"p4");
	})
	
	
</script>



</html>