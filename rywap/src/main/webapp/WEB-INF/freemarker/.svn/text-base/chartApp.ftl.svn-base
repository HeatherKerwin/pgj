[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据指数"]
<style>
.container{
	position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    }
</style>
<div class="chart">

<!--票据指数 -->
<div>
    <div class="w100p bcwhite">
		<!-- tab标签-->
		<div class="title_bar">
			<ul id="pagenavi1" class="tabTitle tac row">
				<li class="SETCHART flex1 selectbar" data-type="1"><a href="javascript:void(0);" id="item0">一个月</a></li>
				<li class="SETCHART flex1" data-type="3"><a href="javascript:void(0);" id="item1">三个月</a></li>
			</ul>
		</div>
		<!-- tab内容-->
		<div class="swiper-container tabCon zhishuCon">
			<!-- <canvas id="myChart" width="400" height="200"></canvas> -->
			<div id="container" style="min-width:360px;height:300px;margin-top: 10px"></div>
		</div>
	</div>
</div>


</div>
<script type="text/javascript" src="https://www.utiexian.com/js/highcharts/highcharts.js"></script>
<!--  <script type="text/javascript" src="${staticPath}/js/rywap/chartjs/Chart.bundle.js"></script>
<script type="text/javascript" src="${staticPath}/js/rywap/chartjs/utils.js"></script>-->
<script type="text/javascript">
/* var ctx = document.getElementById("myChart").getContext("2d"); */
$(document).ready(function(){
	//getBillQuota(2);//获取票据指数（类型2表示一个月，具体规则参考后台实体类）
	piaoju(1);
});

/**
 * 获取票据指数（_t）
 * @param 类型（一个月2、三个月3）
 */
/* function getBillQuota(_t){
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
} */

/**
 * 填充票据指数图表
 */
/* function setChart(data){
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
} */

$(".SETCHART").bind("click",function(){
	if($(this).hasClass("selectbar"))return;
	$(".SETCHART").removeClass("selectbar");
	$(this).addClass("selectbar");
	
	piaoju($(this).attr("data-type"));
});

//票据指数
function piaoju(months) {
	$.ajax({
		url: 'https://api.utiexian.com:8989/out/historyprice/chart',
		type: 'POST',
		dataType: 'json',
		data: {'months':months},
		success: function(data){
			if(data.data.response=="success")
			highcharts(data.data);
		}
	});
	
}

//票据指数
function highcharts(obj) { 
	var priceStr=String(obj.data.price);//原始字符串  
    var priceStrArr=priceStr.split(",");//分割成字符串数组  
    var priceIntArr=[];//保存转换后的整型字符串 
    priceStrArr.forEach(function(data,index,arr){  
    	priceIntArr.push(+data);  
    });  
    for(var i =0;i<priceIntArr.length;i++){
		  if(priceIntArr[i] == 0){
			priceIntArr[i] =null;
		  }
		}
    
    var price1Str=String(obj.data.price_1);//原始字符串  
    var price1StrArr=price1Str.split(",");//分割成字符串数组  
    var price1IntArr=[];//保存转换后的整型字符串 
    price1StrArr.forEach(function(data,index,arr){ 
    	price1IntArr.push(+data); 
    });
    for(var i =0;i<price1IntArr.length;i++){
		  if(price1IntArr[i] == 0){
			price1IntArr[i] =null;
		  }
		}
    
    var price2Str=String(obj.data.price_2);//原始字符串  
    var price2StrArr=price2Str.split(",");//分割成字符串数组  
    var price2IntArr=[];//保存转换后的整型字符串 
    price2StrArr.forEach(function(data,index,arr){  
    	price2IntArr.push(+data);  
    });
    /* price2IntArr=price2StrArr.map(function(data){
    	return +data;
   	}); */
    for(var i =0;i<price2IntArr.length;i++){
		  if(price2IntArr[i] == 0){
			price2IntArr[i] =null;
		  }
		}
	
    $('#container').highcharts({
        chart: {
            zoomType: 'xy'
        },
        title: {
            text: '国股年利率/转贴现利率 (‰)'
        },
        xAxis: [{
        	categories: eval(obj.data.dates),
            crosshair: true,
            tickInterval: 0
        }],
        yAxis: [{
            title: {
                text: ''
            },
            crosshair: true
        }],
        tooltip: {
            shared: true
        },
        credits : {
			text : 'www.utiexian.com',
			href : 'https://www.utiexian.com'
		},
		/*数据点设置*/
        plotOptions: {
            series: {
                marker: {
                	enabled: false, /*数据点是否显示*/
                },
            }
        },
        legend: {
            align: 'left',
            x: 120,
            verticalAlign: 'top',
            y: 20,
            floating: true
        },
        series: [{
            name: '2016',
            type: 'spline',
            data: price2IntArr,
            tooltip: {
                valueSuffix: '‰'
            },
            color:'#7790fe',
            lineWidth:0.5,
            connectNulls: true
        }, {
            name: '2017',
            type: 'spline',
            data: price1IntArr,
            tooltip: {
                valueSuffix: '‰'
            },
            color:'#3ad45a',
            lineWidth:0.5,
            connectNulls: true
        }, {
            name: '2018',
            type: 'spline',
            data: priceIntArr,
            tooltip: {
                valueSuffix: '‰'
            },
            color:'#d43c33',
            lineWidth: 2,
            connectNulls: true
        }]
    });
}; 
</script>
[/@main.body]