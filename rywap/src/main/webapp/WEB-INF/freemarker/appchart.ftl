<!DOCTYPE html>
<html>
[#include "/common/setting.ftl"]
<head lang="en">
    <meta charset="UTF-8">
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
	
    <title>票据指数</title>
    <link rel="stylesheet" href="${staticPath}/css/rywap/index.css"/>
    <link rel="stylesheet" href="${staticPath}/css/rywap/chart.css"/>
    <link rel="stylesheet" href="${staticPath}/css/rywap/wap.css"/>
    <link rel="stylesheet" href="${staticPath}/css/rywap/head.css"/>
    <link rel="stylesheet" href="${staticPath}/css/rywap/swiper.min.css"/>
</head>
<style>
html, body {background: #fff}
</style>
<body>
	<!--票据指数 -->
    <div class="w100p bcwhite">
		<!-- tab标签-->
		<div class="title_bar">
			<ul id="pagenavi1" class="tabTitle tac row">
				<li class="SETCHART flex1 selectbar" data-type="2"><a href="javascript:void(0);" id="item0">一个月</a></li>
				<li class="SETCHART flex1" data-type="3"><a href="javascript:void(0);" id="item1">三个月</a></li>
			</ul>
		</div>
		<!-- tab内容-->
		<div class="swiper-container tabCon zhishuCon">
			<canvas id="myChart" style="width: 100%;height: 300px"></canvas>
		</div>
	</div>
</body>

<script type="text/javascript" src="${staticPath}/js/rywap/jquery.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/rywap/chartjs/Chart.bundle.js"></script>
<script type="text/javascript" src="${staticPath}/js/rywap/chartjs/utils.js"></script>
<script type="text/javascript" src="${staticPath}/js/rywap/swiper.min.js"></script>
<script type="text/javascript">
var ctx = document.getElementById("myChart").getContext("2d");
$(document).ready(function(){
	getBillQuota(2);//获取票据指数（类型2表示一个月，具体规则参考后台实体类）
});

/**
 * 获取票据指数（_t）
 * @param 类型（一个月2、三个月3）
 */
function getBillQuota(_t){
	$.ajax({
		url:"${basePath}/wap/index/billquota",
		data:{type:_t},
		type:"POST",
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				setChart(data.data);
			}
		}
	});
}

/**
 * 填充票据指数图表
 */
function setChart(data){
	var config = {
	    type: 'line',
	    data: {
	        labels: data.xData,
	        datasets: [{
	            label: "纸票",
	            backgroundColor: window.chartColors.red,
	            borderColor: window.chartColors.red,
	            data: data.zhi,
	            fill: false,
	        }, {
	            label: "电票",
	            fill: false,
	            backgroundColor: window.chartColors.blue,
	            borderColor: window.chartColors.blue,
	            data: data.dian,
	        }]
	    },
	    options: {
	        responsive: true,
	        elements: {
				line: {
					tension: 0.001
				}
			},
	        tooltips: {
	            mode: 'index',
	            intersect: false,
	        },
	        hover: {
	            mode: 'nearest',
	            intersect: true
	        },
	        scales: {
	            yAxes: [{
	                display: true,
	                scaleLabel: {
	                    display: true,
	                    labelString: '转贴现利率（‰）'
	                }
	            }]
	        }
	    }
	};
	new Chart(ctx, config);
}

$(".SETCHART").bind("click",function(){
	if($(this).hasClass("selectbar"))return;
	$(".SETCHART").removeClass("selectbar");
	$(this).addClass("selectbar");
	
	getBillQuota($(this).attr("data-type"));
});
</script>

</html>