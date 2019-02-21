<!DOCTYPE html>
<html>
[#include "/common/setting.ftl"]
<head lang="en">
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta name="format-detection" content="telephone=no">
    <title></title>
</head>
<body>
<style>
body{ 
	font-size:62.5%;
	background: #fff
}  /*10/16*100%*/
.wap{
	height: 100%;
	line-height:1.5rem;
	font-size: 1rem
}
.fl{
	float: left;
}
.clearFloat::after {
     content: '.';
     display: block;
     height: 0;
     visibility: hidden;
     clear: both;
 }
 ul{
 padding-left: 0.5rem;
 }
 ul li{
 	float:left;
 	list-style:none; 
 	line-height: 1rem !important;
 	height: 1rem;
 	margin: 0.2rem 0.3rem
 }
 li div:FIRST-CHILD{
 	float:left;
 	width: 1rem;
 	height: 1rem;
 	margin-right: 0.2rem
 }
.select{
	width: 12rem;
	text-indent: 1rem;
	height: 1.8rem;
	margin-left: 0.3rem
}

.canvasDiv{
	text-align: center;
	margin-top: 1.2rem
}
canvas{
	width: 100% !important;
	height: auto !important;
	margin-top: 1rem
}
</style>
<div class="wap">
	<ul class="clearFloat">
		<li id='guogu' style="display:none"><div style="background: #83c5f7;"></div><div class="fl">国股</div></li>
		<li id='dashang' style="display:none"><div style="background: #f8d8b1;"></div><div class="fl">大商</div></li>
		<li id='xiaoshang' style="display:none"><div style="background: #fd999b;"></div><div class="fl">小商</div></li>
		<li id='waizi' style="display:none"><div style="background: #5db3f4;"></div><div class="fl">外资</div></li>
		<li id='nongshang' style="display:none"><div style="background: #a6e6e5;"></div><div class="fl">农商</div></li>
		<li id='nonghe' style="display:none"><div style="background: #f6cc5f;"></div><div class="fl">农合</div></li>
		<li id='nongxin' style="display:none"><div style="background: #7dcda6;"></div><div class="fl">农信</div></li>
		<li id='cunzhen' style="display:none"><div style="background: #d8a8e2;"></div><div class="fl">村镇</div></li>
	</ul>
	<div class="canvasDiv" style="border-bottom: 1px solid #e0e0e0; ">
		<div>贴现总额为<span style="color: #d43c33" id="amount">0</span>万元</div>
		<img style="display:none" src="https://img.utiexian.com/rywap/bingtu.png"/>
		<canvas style="display:none" id="chart-money" />
	</div>
	<div class="canvasDiv">
		<div>贴现利息为<span style="color: #d43c33" id="amount1">0</span>元</div>
		<img style="display:none" src="https://img.utiexian.com/rywap/bingtu.png"/>
		<canvas style="display:none" id="chart-interest" />
	</div>
</div>

</body>
<script type="text/javascript" src="${staticPath}/js/rywap/jquery.min.js"></script>
<!-- <script type="text/javascript" src="${staticPath}/js/rywap/chart.js"></script> -->
<script type="text/javascript" src="${staticPath}/js/rywap/Chart1.js"></script>
<script type="text/javascript">
	var memberId;
	var type;
	var belong;
	var start;
	var end;
	
	/* 贴现总额 */
	var moneyData = [];
	/* 贴现利息 */
	var interestData = [];
	$(document).ready(function(){
		memberId = '${memberId}';
		type = '${type}';//选择的时间
		belong = '${belong}';//角色 0出票  1收票
		setData();
	});
	function setData(){
		var data = {
			memberId:memberId,
			type:type,
			belong:belong
		};
		if(type == 4){
			data.start = '${start}';
			data.end = '${end}';
		}
		console.log(data);
		$.ajax({
			url:"${basePath}/app/zhangbu/tongji",
			type:"POST",
			data: data,
			dataType:"json",
			success:function(res){
				console.log(res);
				if(res.response=="success"){
					if(res.data.length == 0){
						$("canvas").prev().show();
						$("canvas").hide();
					}else{
						$("canvas").prev().hide();
						$("canvas").show();
						openPieChart(res);
						openPieChart1(res);
					}
				}else{
					console.log("faild");
				}
			}
		});
	}
	function getPieData(arr){
	    console.log("1111");
	    var guogu = arr[0];
	    var chengshang = arr[1];
	    var waizi = arr[2];
	    var nongshang = arr[3];
	    var nonghe = arr[4];
	    var nongxin = arr[5];
	    var cunzhen = arr[6];
	    var dashang = arr[7];

	    var i=0;
	    var res = new Array();
	    if(0!=guogu) {
	    	$("#guogu").show();
	        var obj = {
	            value: (guogu.toFixed(2)) * 100,
	            color : "#83c5f7",
    			highlight: "#FF5A5E",
    			label: "国股(%)"
	        };
	        res[i]= obj;
	        i++;
	    }
	    if(0!=dashang) {
	    	$("#dashang").show();
	        var obj = {
        		value: (dashang.toFixed(2))*100,
    			color:"#f8d8b1",
    			highlight: "#FF5A5E",
    			label: "大商(%)"
	        };
	        res[i]= obj;
	        i++;
	    }
	    if(0!=chengshang) {
	    	$("#xiaoshang").show();
	        var obj = {
        		value: (chengshang.toFixed(2))*100,
    			color:"#fd999b",
    			highlight: "#FF5A5E",
    			label: "小商(%)"
	        };
	        res[i]= obj;
	        i++;
	    }
	    if(0!=waizi) {
	    	$("#waizi").show();
	        var obj = {
        		value: (waizi.toFixed(2))*100,
	            color:"#5db3f4",
    			highlight: "#FF5A5E",
    			label: "外资(%)"
	        };
	        res[i]= obj;
	        i++;
	    }
	    if(0!=nongshang) {
	    	$("#nongshang").show();
	        var obj = {
				value: (nongshang.toFixed(2))*100,
				color:"#a6e6e5",
				highlight: "#FF5A5E",
				label: "农商(%)"
	        };
	        res[i]= obj;
	        i++;
	    }
	    if(0!=nonghe) {
	    	$("#nonghe").show();
	        var obj = {
        		value: (nonghe.toFixed(2))*100,
				color:"#f6cc5f",
				highlight: "#FF5A5E",
				label: "农合(%)"
	        };
	        res[i]= obj;
	        i++;
	    }
	    $("#nonxin").show();
	    if(0!=nongxin) {
	        var obj = {
        		value: (nongxin.toFixed(2))*100,
	            color:"#7dcda6",
				highlight: "#FF5A5E",
				label: "农信(%)"
	        };
	        res[i]= obj;
	        i++;
	    }
	    if(0!=cunzhen) {
	    	$("#cuzhen").show();
	        var obj = {
        		value: (cunzhen.toFixed(2))*100,
	            color:"#d8a8e2",
				highlight: "#FF5A5E",
				label: "村镇(%)"
	        };
	        res[i]= obj;
	        i++;
	    }
	    return res;
	}
	
	function openPieChart(data){
		if(data.allprice!=null)$("#amount").html(data.allprice.toFixed(2));
        var result = data.data;
        var guogu = 0;
        var chengshang = 0;
        var waizi = 0;
        var nongshang = 0;
        var nonghe = 0;
        var nongxin = 0;
        var cunzhen = 0;
        var dashang = 0;
        
        var allprice = data.allprice;
        for (i in result){
            var temp = result[i];
            var price = temp.allprice;
            if(1==temp.type1 && price!=null){
                guogu = price/allprice;
            }else if(2==temp.type1 && price!=null){
                chengshang = price/allprice;
            }else if(3==temp.type1 && price!=null){
                waizi = price/allprice;
            }else if(4==temp.type1 && price!=null){
                nongshang = price/allprice;
            }else if(5==temp.type1 && price!=null){
                nonghe = price/allprice;
            }else if(6==temp.type1 && price!=null){
                nongxin = price/allprice;
            }else if(7==temp.type1 && price!=null){
                cunzhen = price/allprice;
            }else if(8==temp.type1 && price!=null){
                dashang = price/allprice;
            }
        }
        moneyData[0]=guogu;
        moneyData[1]=chengshang;
        moneyData[2]=waizi;
        moneyData[3]=nongshang;
        moneyData[4]=nonghe;
        moneyData[5]=nongxin;
        moneyData[6]=cunzhen;
        moneyData[7]=dashang;
        var res = getPieData(moneyData);
        /* 贴现总额 */
        var money = document.getElementById("chart-money").getContext("2d");
		window.myPie = new Chart(money).Doughnut(res);
	}
	function openPieChart1(data){
        if(data.allprice!=null)$("#amount1").html(data.tiexianlixi.toFixed(2));
        var result = data.data;
        var guogu = 0;
        var chengshang = 0;
        var waizi = 0;
        var nongshang = 0;
        var nonghe = 0;
        var nongxin = 0;
        var cunzhen = 0;
        var dashang = 0;
        var tiexianlixi = data.tiexianlixi;
        for (i in result){
            var temp = result[i];
            var lixi = temp.tiexianlixi;
            if(1==temp.type1 && lixi!=null){
                guogu = lixi/tiexianlixi;
            }else if(2==temp.type1 && lixi!=null){
                chengshang = lixi/tiexianlixi;
            }else if(3==temp.type1 && lixi!=null){
                waizi = lixi/tiexianlixi;
            }else if(4==temp.type1 && lixi!=null){
                nongshang = lixi/tiexianlixi;
            }else if(5==temp.type1 && lixi!=null){
                nonghe = lixi/tiexianlixi;
            }else if(6==temp.type1 && lixi!=null){
                nongxin = lixi/tiexianlixi;
            }else if(7==temp.type1 && lixi!=null){
                cunzhen = lixi/tiexianlixi;
            }else if(8==temp.type1 && lixi!=null){
                dashang = lixi/tiexianlixi;
            }
        }
        interestData[0]=guogu;
        interestData[1]=chengshang;
        interestData[2]=waizi;
        interestData[3]=nongshang;
        interestData[4]=nonghe;
        interestData[5]=nongxin;
        interestData[6]=cunzhen;
        interestData[7]=dashang;
        var res = getPieData(interestData);
        /* 贴现利息 */
		var interest = document.getElementById("chart-interest").getContext("2d");
		window.myPie = new Chart(interest).Doughnut(res);
    }
	
</script>
</html>