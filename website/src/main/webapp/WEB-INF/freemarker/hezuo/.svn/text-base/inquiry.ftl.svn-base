[#include "/common/setting.ftl"]
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>银票询价</title>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/hezuo/main.css"/>
    <link rel="stylesheet" href="${pluPath}/validation-engine/css/validationEngine.jquery.css" type="text/css"/>
</head>
<body>
	<div class="w940 h420 bc bcwhite pr">
	    <div class="w bcf4f4f4 h50 lh50 f24 ti50 fb">银票询价</div>
	    <div class="w938 bae0e0e0 h370">
	        <div class="w h50 lh50 f20 bcf4f4f4 tc cl">
	            <div class="fl w_50">
	                <span id="showinfo1">每10万贴息:(买断)：</span><span class="cd43c33 f22" id="showinfo2">--</span>
	            </div>
	            <div class="fl w_50">
	                <span id="showinfo3">您的贴现利息:(买断)</span><span class="cd43c33 f22" id="showinfo4">--</span>
	            </div>
	        </div>
	        <div class="w838 ml50 mr50 lh50 mt10">
	            <div class="w cl h50">
	                <div class="fl w_50">
	                    <div class="fl w100">票据属性：</div>
	                    <select id="type6" class="fl mt5 select f18 ti10">
	                        <option value="1">纸票</option>
	                        <option value="2" selected="selected">电票</option>
	                    </select>
	                </div>
	                <div class="fl w_50">
	                    <div class="fl w120">承兑行类型：</div>
	                    <select id="type1" class="fl mt5 select f18 ti10" id="type1">
	                        <option value="1" selected="selected">国股</option>
	                        <option value="8">大商</option>
	                        <option value="2">小商</option>
	                        <option value="3">外资</option>
	                        <option value="4">农商</option>
	                        <option value="5">农合</option>
	                        <option value="6">农信</option>
	                        <option value="7">村镇</option>
	                    </select>
	                </div>
	            </div>
	            <div class="w cl h50">
	                <div class="fl w_50 none" id="citydiv">
	                    <div class="fl w100">交易城市：</div>
	                    <select id="orgCityList" class="fl mt10 select f18 ti10">
	                    </select>
	                </div>
	                <div id="years" class="fl w_50">
		            	<div class="fl w100">承兑期限：</div>
		                <select id="limit" class="fl mt5 select f18 ti10">
		                    <option value="0" >半年期</option>
		                    <option value="1" selected="selected">一年期</option>
		                </select>
	            	</div>
	                <div class="fl w_50">
	                    <div class="fl w120">单张票据金额：</div>
	                    <input id="allmoney" type="number" class="fl w200 h40 mt5 f18 ti10 bae0e0e0 br5 validate[required,custom[allprice]]" placeholder="请输入票据金额">
	                </div>
	            </div>
	            <div class="w cl h50">
	                <div class="fl w_50">
	                    <div class="fl w100">贴现日期：</div>
	                    <input id="startDate" type="text" class="fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请选择贴现日期" readonly="readonly">
	                </div>
	                <div class="fl w_50">
	                    <div class="fl w120">到期日期：</div>
	                    <input id="endDate" type="text" class="fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请选择到期日期" readonly="readonly">
	                </div>
	            </div>
	            <div class="w cl h50">
	                <div class="fl w_50">
	                    <div class="fl w100">调整天数：</div>
	                    <input type="number" id="day" class="fl w200 h40 mt5 f18 ti10 bae0e0e0 br5 validate[maxSize[2],custom[integer1]]" placeholder="请输入天数">
	                    <div class="fl ml10">天</div><input type="hidden" id="day-hidden"/>
	                </div>
	                <div class="fl w_50">
	                    <div class="fl w120">计息天数：</div>
	                    <div class="fl f16"><span id="days" class="f18">183</span>天</div>
	                </div>
	            </div>
	            <div class="w tc mt20"> 
	                 <input class="w200 h40 lh40 f18 bcd43c33 b0 br5 cwhite tc cp" readonly="readonly" value="询价" onclick="xunjia();">
	            </div>
	        </div>
	    </div>
	</div>
</body>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/plugins/data/laydate.js"></script>
<script type="text/javascript" src="${pluPath}/validation-engine/js/languages/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${pluPath}/validation-engine/js/jquery.validationEngine.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	loaddate();
});

var begintimelong = Date.parse(new Date());
var begintime = new Date(begintimelong);
var fullyear = begintime.getFullYear();//获取完整的年份(4位,1970-????)
var month = begintime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
var date = begintime.getDate();//获取当前日(1-31)

var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
var endfullyear = endtime.getFullYear();//获取完整的年份(4位,1970-????)
var endmonth = endtime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
var enddate = endtime.getDate();//获取当前日(1-31)
if (month < 10) {
	month = "0" + month;
}
if (date < 10) {
	date = "0" + date;
}
if (endmonth < 10) {
	endmonth = "0" + endmonth;
}
if (enddate < 10) {
	enddate = "0" + enddate;
}

$("#type6").change(function() {
	if ($("#type6").val() == 2) {
		$("#years").show();
		$("#citydiv").hide();
		var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
		var endfullyear = endtime.getFullYear();//获取完整的年份(4位,1970-????)
		var endmonth = endtime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
		var enddate = endtime.getDate();
		$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
	} else {
		var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
		var endfullyear = endtime.getFullYear();//获取完整的年份(4位,1970-????)
		var endmonth = endtime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
		var enddate = endtime.getDate();
		$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		$("#years").hide();
		$("#citydiv").show();
	}
	jixidate();
});
		
$("#day").blur(function() {
	var day = $("#day").val();
	var dayold = $("#day-hidden").val();
	if (!$("#day").validationEngine("validate")) {
		$("#day").focus();
		setTimeout(function() {
			$("#day").validationEngine('hideAll');
		}, 1000);
		$("#day").val(dayold);//重置
		return;
	} else {
		$("#day-hidden").val(day);
		var days = $("#days").html();
		$("#days").html(Number(days) - Number(dayold) + Number(day));
	}
});
	
//计息天数
function jixidate() {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var type6 = $("#type6").val();//纸票1、电票2
	$.ajax({
		url : "${basePath}/homepage/xunjia/jixidate",
		type : "post",
		data : {
			"startDate" : startDate,
			"endDate" : endDate,
			"type6" : type6
		},
		dataType : "json",
		success : function(data) {
			$("#days").html(data.jxts);
			$("#day-hidden").val(data.tzts);
			$("#day").val(data.tzts);
		}
	});
}
	
function loaddate() {
	$("#startDate").val(fullyear + "-" + month + "-" + date);
	$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);
	jixidate();
	setCityList();
}
	
$("#limit").change(function() {
	if ($("#limit").val() == 1) {
		var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
		var endfullyear = endtime.getFullYear();
		//获取完整的年份(4位,1970-????)
		var endmonth = endtime.getMonth() + 1;
		//获取当前月份(0-11,0代表1月)
		var enddate = endtime.getDate();
		$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
	} else {
		var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
		var endfullyear = endtime.getFullYear();
		//获取完整的年份(4位,1970-????)
		var endmonth = endtime.getMonth() + 1;
		//获取当前月份(0-11,0代表1月)
		var enddate = endtime.getDate();
		$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
	}
	jixidate();
});
	
//设置报价城市下拉框
function setCityList() {
	$.ajax({
		url : '${basePath}/homepage/xunjia/cityid',
		type : 'POST',
		dataType : 'json',
		data : {},
		success : function(result) {
			var data = eval(result);
			var orgCityList = data.orgCityList;
			if (orgCityList != null) {
				var str = '';
				$("#orgCityList").text('');
				for ( var i in orgCityList) {
					var orgCity = orgCityList[i];
					if (orgCity.cityid != null && orgCity.name != null) {
						str += "<option value='" + orgCity.cityid + "'>" + orgCity.name + "</option>";
					}
				}
				$("#orgCityList").append(str);
			}
		}
	});
}
	
//询价
function xunjia() {
	var limit = $("#limit").val();
	var type6 = $("#type6").val();//纸票1、电票2
	var type1 = $("#type1").val();//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
	var allmoney = $("#allmoney").val();//票据金额（对应type2 票据金额 :1:500以上 2:100-500 3:50-100  4:<50）
	if (!$("#allmoney").validationEngine("validate")) {
		$("#allmoney").focus();
		setTimeout(function() {
			$("#allmoney").validationEngine('hideAll');
		}, 1000);
		return;
	}
	var type5 = "";
	var dates = $("#days").html();

	if (Number(allmoney) < 500) {//大票没有票据属性
		if (Number(dates) <= 90) {
			type5 = '1';
		} else if (Number(dates) <= 178) {
			type5 = '2';
		} else {
			type5 = '3';
		}
	}
	var cityId = $("#orgCityList").val();
	if (cityId == null || $.trim(cityId) == "") {
		alert("城市获取失败");
		return;
	}

	if (type6 == 2) {//电票默认报价城市为10000（后期全国主键应该是1）
		cityId = 10000;
	}
	$.ajax({
		url : "${basePath}/homepage/xunjia/xunjia",
		type : "post",
		data : {
			"type6" : type6,
			"type1" : type1,
			"cityId" : cityId,
			"type3" : "1",
			"type5" : type5,
			"limit" : limit,
			"allmoney" : allmoney,
			"dates" : dates,
			"cityId" : cityId,
		},
		dataType : "json",
		success : function(data) {
			if (data.response == "success") {
				var result = data.data;
				var price = result.price;
				var price1 = result.price1;
				var price2 = result.price2;
				var price3 = data.txlx;
				if ($("#type6").val() == 1) {//纸票
					if (data.type2 == 1) {
						$("#showinfo1").html("当前价格-月利率:(买断)");
						if (price == '--' || price == '') {
							alert("暂无该选项数据");
							$("#showinfo2").html("--");
						} else {
							$("#showinfo2").html(price + "‰");
						}
					} else {
						$("#showinfo1").html("每10万贴息:(买断)");
						if (price2 == '--' || price2 == '') {
							alert("暂无该选项数据");
							$("#showinfo2").html("--");
						} else {
							$("#showinfo2").html(price2 + "元");
						}
					}
					$("#showinfo3").html("您的贴现利息:(买断)");
				} else {//电票
					$("#showinfo1").html("当前价格-年利 率：");
					if (price == '--' || price == '') {
						alert("暂无该选项数据");
						$("#showinfo2").html("--");
					} else {
						$("#showinfo2").html(price + "%");
					}
				}
				if (price3 != null && price3 != "" && price3 != '--') {
					$("#showinfo3").html("您的贴现利息:(买断)");
					$("#showinfo4").html(price3 + "元");
				} else {
					$("#showinfo3").html("您的贴现利息:(买断)");
					$("#showinfo4").html("--");
				}
			} else {
				$("#showinfo2").html("--");
				alert(data.msg);
			}
		}
	});
}
	
//日历插件
!function() {
	laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
}();
//日期范围限制
//    贴现日期
var start = {
	elem : '#startDate',
	format : 'YYYY-MM-DD',
	//min: laydate.now(), //设定最小日期为当前日期
	min : '1900-01-01', //设定最小日期
	max : '2099-06-16', //最大日期
	istime : true,
	istoday : false,
	choose : function(datas) {
		jixidate();
		end.min = datas; //开始日选好后，重置结束日的最小日期
		end.start = datas //将结束日的初始值设定为开始日
	}
};
//    到期日期
var end = {
	elem : '#endDate',
	format : 'YYYY-MM-DD',
	min : laydate.now(),
	max : '2099-06-16',
	istime : true,
	istoday : false,
	choose : function(datas) {
		jixidate();
		start.max = datas; //结束日选好后，充值开始日的最大日期
	}
};
laydate(start);
laydate(end);
</script>
</html>