<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>APP下载界面</title>
<link rel="shortcut icon" type="image/x-icon" href="https://img.utiexian.com/common/icon/32.png" media="screen" />
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/ryapp/public.css">
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/ryapp/style.css">

<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/ryapp/config.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery.cookie.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/ryapp/clickCount.js"></script>

<script>
	function is_weixin(){  
		var ua = navigator.userAgent.toLowerCase();  
		if(ua.match(/MicroMessenger/i)=="micromessenger") {  
			return true;  
		} else {  
			return false;  
		}  
	}
	
	var browser = {
		versions: function() {
		var u = navigator.userAgent, app = navigator.appVersion;
		return {//移动终端浏览器版本信息 
		trident: u.indexOf('Trident') > -1, //IE内核
		presto: u.indexOf('Presto') > -1, //opera内核
		webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
		gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
		mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
		ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
		android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
		iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
		iPad: u.indexOf('iPad') > -1, //是否iPad
		webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
		};
		}(),
		language: (navigator.browserLanguage || navigator.language).toLowerCase()
	}
</script>

<script type="text/javascript">

function saveCross(style,code,href){
	var rurl = document.referrer;
	var referrerUrl = rurl; //来源地址
	var url = location.href; //当前地址
	var uuid = getUuid();
	if(setCookie("uuid", uuid) == ""){
		uuid = $.cookie("uuid");
	}
	$.ajax({
		type:"get",
		url:wwwPath + "/tg/clickCount/save",//从后台获取ip
		data:{"url":url,"style":style,"referrerUrl":referrerUrl,"code":code,"uuid":uuid},
		dataType : "jsonp",
		async:false
	});
	window.location = href;
}


	$(document).ready(function(){
		var style = getParam("style");//style代表pc，app
		var code = getParam("code");//sitestatistics.js获取url
		if(code != null && code != "" && code != undefined || style != null && style != "" && style != undefined){
			
			if(is_weixin()){
				
			}else{
				if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad) {
					saveCross(style,code,"https://itunes.apple.com/cn/app/id1001869394");//这里就是跟site项目进行数据处理
					/* window.location="https://itunes.apple.com/cn/app/id1001869394"; */
				}
				else if (browser.versions.android) {
					saveCross(style,code,"https://static.utiexian.com/andriod/piaojuguanjia.apk");
				/* 	window.location="https://static.utiexian.com/andriod/piaojuguanjia.apk"; */
				}
			}
		}else{
			if(is_weixin()){
				
			}else{
				if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad) {
					/* saveCross(style,code,"https://itunes.apple.com/cn/app/id1001869394");//这里就是跟site项目进行数据处理 */
					window.location="https://itunes.apple.com/cn/app/id1001869394"; 
				}
				else if (browser.versions.android) {
					/* saveCross(style,code,"https://static.utiexian.com/andriod/piaojuguanjia.apk"); */
				 	window.location="https://static.utiexian.com/andriod/piaojuguanjia.apk"; 
				}
			}
		}
	})
	
</script>
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

<script type="text/javascript" src="https://static.utiexian.com/js/ryapp/jquery.touchSlider.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/ryapp/jquery.event.drag-1.5.min.js"></script>
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
