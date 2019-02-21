<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>APP下载界面</title>
<link rel="shortcut icon" type="image/x-icon" href="https://img.utiexian.com/common/icon/32.png" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/public.css">
<link rel="stylesheet" type="text/css" href="../css/style4.css">

<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
</head>
<body>
<!-- <div class="ewm_tp"><img src="images/12345.jpg" /></div> -->
<div class="top">
	<div class="top_l fl">
    </div>
    <div class="top_c fl">
    	<div class="top_c_1">
        票据管家
        </div>
        <div class="top_c_2">
        让贴现更容易
        </div>
    </div>
    <div class="top_r1 fl">
    	<img src="https://img.utiexian.com/ryapp/down/topr1.png">
    </div>
</div>

<!--焦点图-->


<div class="main">
<div class="main_visual">
  <div class="flicking_con">
  	<a href="#">1</a><a href="#">2</a>
  </div>
  <div class="main_image">
    <ul>
      <li><a><img class="banner" src="${info.banner1}"></a></li>
      <li><a><img class="banner" src="${info.banner2}"></a></li>
    </ul>
  </div>
</div>
</div>
<div class="clearfix"></div>

<!--con-->
<div class="con">
	<p class="con_1">【基本信息】</p>
    <p class="con_22">
    作者：上海票管家金融服务有限公司<br/>版本：${info.version}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大小：${info.size} <br/>语言：中文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更新时间：${info.shijian}<br/>
系统：${info.xitong}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    </p>
	<p class="con_1">【更新内容】</p>
    <p class="con_22">
    ${info.content?replace(' ', '<br/>' )}
    </p>
    <p class="con_1">【应用介绍】</p>
    <p class="con_2">
    ${info.jieshao}
    </p>
</div>
<div class="foot">
&nbsp; 
</div>

<script type="text/javascript" src="../js/jquery.touchSlider.js"></script>
<script type="text/javascript" src="../js/jquery.event.drag-1.5.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	$(".main_visual").hover(function(){
		$("#btn_prev,#btn_next").fadeIn()
	},function(){
		$("#btn_prev,#btn_next").fadeOut()
	});
	
	$dragBln = false;
	
	$(".main_image").touchSlider({
		flexible : true,
		speed : 200,
		btn_prev : $("#btn_prev"),
		btn_next : $("#btn_next"),
		paging : $(".flicking_con a"),
		counter : function (e){
			$(".flicking_con a").removeClass("on").eq(e.current-1).addClass("on");
		}
	});
	
	$(".main_image").bind("mousedown", function() {
		$dragBln = false;
	});
	
	$(".main_image").bind("dragstart", function() {
		$dragBln = true;
	});
	
	$(".main_image a").click(function(){
		if($dragBln) {
			return false;
		}
	});
	
	timer = setInterval(function(){
		$("#btn_next").click();
	}, 5000);
	
	$(".main_visual").hover(function(){
		clearInterval(timer);
	},function(){
		timer = setInterval(function(){
			$("#btn_next").click();
		},5000);
	});
	
	$(".main_image").bind("touchstart",function(){
		clearInterval(timer);
	}).bind("touchend", function(){
		timer = setInterval(function(){
			$("#btn_next").click();
		}, 5000);
	});
	
});

</script>

</body>
</html>
