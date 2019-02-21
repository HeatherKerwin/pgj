[#include "/common/setting.ftl"]
<html>
<head>
	<meta charset="UTF-8">
	<title>贴现计算器</title>
	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/hezuo/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pluPath}/validation-engine/css/validationEngine.jquery.css"/>
</head>
<body>
	<div class="w940 h420 bc bcwhite pr">
		<div class="w bcf4f4f4 h50 lh50 f24 ti50 fb">贴现计算器</div>
		<div class="w938 bae0e0e0 h370">
			<div class="w h50 lh50 f20 bcf4f4f4 tc cl">
				<div class="fl w_50">
					贴现利息（元）：<span class="cd43c33 f22" id="c_txlx">00.00</span>
				</div>
				<div class="fl w_50">
					贴现金额（万元）：<span class="cd43c33 f22" id="c_txje">00.00</span>
				</div>
			</div>
			<div class="w838 ml50 mr50 lh50">
				<div class="w cl h50">
					<div class="fl w_50">
						<div class="fl w100">票据属性：</div>
						<select class="fl mt5 select f18 ti10" id="c_type1">
							<option value="1">纸票</option>
							<option value="2" selected="selected">电票</option>
						</select>
					</div>
					<div class="fl w_50">
						<div class="fl w100">承兑行类型：</div>
						<select class="fl mt5 select f18 ti10" id="c_type2">
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
						<select class="fl mt5 select f18 ti10" id="c_orgCityList">
						</select>
					</div>
					<div id="c_years" class="fl w_50">
						<div class="fl w100">承兑期限：</div>
						<select class="fl mt5 select f18 ti10" id="c_acceptTime">
							<option value="0">半年期</option>
							<option value="1" selected="selected">一年期</option>
						</select>
					</div>
					<div class="fl w_50">
						<div class="fl w100">总金额：</div>
						<input id="c_allmoney" type="number" class="fl w200 h40 f18 ti10 bae0e0e0 br5 validate[required,custom[allprice]]" placeholder="请输入票据金额">
						<div class="fl ml10">万元</div>
					</div>
				</div>
				<div class="w cl h50">
					<div class="fl w_50">
						<div class="fl w100">贴现日期：</div>
						<input readonly="readonly" type="text" id="c_startDate" class="fl w200 h40 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请选择贴现日期">
					</div>
					<div class="fl w_50">
						<div class="fl w100">到期日期：</div>
						<input readonly="readonly" type="text" id="c_endDate" class="fl w200 h40 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请选择到期日期">
					</div>
				</div>
				<div class="w cl h50">
					<div class="fl w_50">
						<div class="fl w100">调整天数：</div>
						<input type="number" id="c_day" class="fl w200 h40 f18 ti10 bae0e0e0 br5 validate[maxSize[2],custom[integer1]]" placeholder="请输入天数">
						<div class="fl ml10">天</div>
						<input type="hidden" id="c_day-hidden" />
					</div>
					<div class="fl w_50">
						<div class="fl w100">计息天数：</div>
						<div class="fl f16">
							<span class="f18" id="c_jxts"></span>天
						</div>
					</div>
				</div>
				<div class="w cl h50">
					<div class="fl w_50">
						<div class="fl w100"><span id="yuexi">月息</span><span id="nianxi" class="none">年息</span><span id="meishiwan" class="none">每十万扣</span>：</div>
						<div class="fl h30 mt10 lh40">
							<div class="tc" id="month">
								<input id="rate" type="number" class="fl w60 h30 f18 ti10 bae0e0e0 br5 tc validate[custom[jisuan]]" placeholder="00.00">
								<div class="fl ml10 mr10">
									<span class="w16 mr20">‰</span>+
								</div>
								<input type="number" class="fl w60 h30 f18 ti10 bae0e0e0 br5 tc ml10 validate[custom[baojianumber]]" id="rate1" placeholder="0">
								<div class="fl ml10 mr10">元</div>
							</div>
							<div class="tc none" id="year">
								<input type="number" class="fl w60 h30 f18 ti10 bae0e0e0 br5 tc validate[custom[jisuan]]" placeholder="00.00" id="rateYear">
								<div class="fl ml10 mr10">
									<span class="f14 mr20 tc">% </span>
								</div>
							</div>
							<div class="tc none" id="ten">
								<input type="number" id="rate2" class="fl w60 h30 f18 ti10 bae0e0e0 br5 tc validate[custom[tennumber]]" placeholder="00.00">
								<div class="fl ml10 mr10">元</div>
							</div>
						</div>
					</div>
					<div class="fl w_50" id="shouxu">
						<div class="fl w100">手续费：</div>
						<input type="number" id="c_poundage" class="fl w200 h40 f18 ti10 bae0e0e0 br5 validate[custom[integer1]]" placeholder="请输入手续费">
						<div class="fl ml10">元</div>
					</div>
				</div>
				<div class="w cl mt5">
					<div class="fl w_50 tc">
						<input readonly="readonly" class="w200 h40 lh40 f18 bcf5f5f5 b0 br5 tc cp" onclick="c_clear();" value="清空">
					</div>
					<div class="fl w_50 tc">
						<input class="w200 h40 lh40 f18 bcd43c33 b0 br5 cwhite tc cp" value="计算" readonly="readonly" onclick="c_jisuan()" type="button">
					</div>
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
	loaddate();//初始化日期和城市
	
	$("#c_type1").change(function() {
		if ($("#c_type1").val() == 2) {
			$("#c_years").show();
			$("#citydiv").hide();
			var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		} else {
			var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
			$("#c_years").hide();
			$("#citydiv").show();
		}
		setPanel();//设置对应的票据价格
	});
	$("#c_allmoney").change(function() {
		if (!$("#c_allmoney").validationEngine("validate")) {
			$("#c_allmoney").focus();
			setTimeout(function() {
				$("#c_allmoney").validationEngine('hideAll');
			}, 1000);
			return;
		}
		setPanel();//设置对应的票据价格
	});

	$("#c_acceptTime").change(function() {
		if ($("#c_acceptTime").val() == 1) {
			var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		} else {
			var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}
		setPanel();
	});
});

$("#c_day").blur(function() {
	var day = $("#c_day").val();
	var dayold = $("#c_day-hidden").val();
	var reg = /^[1-9][0-9]*$/;//正整数
	if (!reg.test(day)) {
		alert("请输入正整数调整日期");
		$("#c_day").val(dayold);//重置
	} else {
		$("#c_day-hidden").val(day);
		var days = $("#c_jxts").html();
		$("#c_jxts").html(Number(days) - Number(dayold) + Number(day));
	}
});

function loaddate() {//初始化日期和城市
	$("#c_startDate").val(fullyear + "-" + month + "-" + date);//计算器
	$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//计算器
	setCityList();
}

//计算器的票据价格类型
function setPanel() {
	var type = $("#c_type1").val();
	var money = $("#c_allmoney").val();
	if (type == 2) {//电票只显示年息
		$("#rateYear").addClass("validate[required]");
		$("#rate").removeClass("validate[required]");
		$("#rate2").removeClass("validate[required]");
		$("#rate1").removeClass("validate[required]");
		$("#nianxi").show();
		$("#yuexi").hide();
		$("#meishiwan").hide();
		$("#month").addClass("none");
		$("#year").removeClass("none");
		$("#ten").addClass("none");
		$("#Interest2").attr("checked", "checked");
	} else if (type == 1 && money < 500 && money != "") {//纸票500万以下显示每十万
		$("#nianxi").hide();
		$("#yuexi").hide();
		$("#meishiwan").show();
		$("#month").addClass("none");
		$("#year").addClass("none");
		$("#ten").removeClass("none");
		$("#Interest3").attr("checked", "checked");
		$("#rate2").addClass("validate[required]");
		$("#rate").removeClass("validate[required]");
		$("#rateYear").removeClass("validate[required]");
		$("#rate1").removeClass("validate[required]");
	} else if (type == 1 && (money >= 500 || money == "")) {//纸票500玩以上显示月息
		$("#nianxi").hide();
		$("#yuexi").show();
		$("#meishiwan").hide();
		$("#month").removeClass("none");
		$("#year").addClass("none");
		$("#ten").addClass("none");
		$("#Interest1").attr("checked", "checked");
		$("#rate").addClass("validate[required]");
		$("#rateYear").removeClass("validate[required]");
		$("#rate2").removeClass("validate[required]");
		$("#rate1").addClass("validate[required]");
	}
	initExcel();
}

/**
 * 当城市改变的时候，清空原本的数据
 */
$("#c_orgCityList").change(function() {
	c_clear();
});

function initExcel() {
	if ($("#c_allmoney").val() == "")return;
	
	var cityId = $("#c_orgCityList").val();
	var type1 = $("#c_type1").val();
	var type2 = $("#c_type2").val();
	var start = $("#c_startDate").val();
	var end = $("#c_endDate").val();
	var allmoney = $("#c_allmoney").val();
	var acceptTime = $("#c_acceptTime").val();
	if (type1 == 2) {//电票默认报价城市为10000（后期全国主键应该是1）
		cityId = 10000;
	}
	$.ajax({
		url : "${basePath}/homepage/calculator/init",
		type : "post",
		data : {
			"type2" : type2,
			"type1" : type1,
			"cityId" : cityId,
			"limit" : acceptTime,
			"allmoney" : allmoney,
			"start" : start,
			"end" : end,
		},
		dataType : "json",
		success : function(data) {
			if (data.response == "success") {
				$("#c_day").val(data.tzts);
				$("#c_day-hidden").val(data.tzts);

				if ($("#c_type1").val() == 2) {
					if (data.rate != null && data.rate != ""
							&& data.rate != "--") {
						$("#rateYear").val(data.rate);
					}
				} else {
					if (data.rate != null && data.rate != ""
							&& data.rate != "--") {
						if ($("#c_allmoney").val() >= 500) {
							$("#rate").val(data.rate);//月（年）利率
						} else {
							$("#rate2").val(data.rate2);//每十万贴息
						}
					}
				}
				$("#c_jxts").text(data.jxts);//计息天数
				
				var txje = getTXJE(allmoney, data.txlx, $("#c_poundage").val());
				$("#c_txje").text(txje);//贴现金额
				$("#c_txlx").text(data.txlx);//贴现利息
			}
		}
	});
}

function tenTom(ten, jxts) {
	if (jxts == null)return;
	var res = Number(ten);
	var result = res / Number(jxts) / 100000 * 1000 * 30;
	return result.toFixed(2);
}

function getTXJE(allmoney, txlx, poundage) {
	var res = (Number(allmoney) * 10000) - Number(txlx);
	if (poundage != null)res -= Number(poundage);
	return (res / 10000).toFixed(2);
}

function c_jisuan() {
	var type1 = $("#c_type1").val();
	var type2 = $("#c_type2").val();
	var start = $("#c_startDate").val();
	var end = $("#c_endDate").val();
	var allmoney = $("#c_allmoney").val();
	var rate1 = $("#rate1").val();
	var jxts = $("#c_jxts").text();
	var rate2 = $("#rate2").val();
	var rate = $("#rateYear").val();//年息 电票
	if (!$("#c_allmoney").validationEngine("validate")) {
		$("#c_allmoney").focus();
		setTimeout(function() {
			$("#c_allmoney").validationEngine('hideAll');
		}, 1000);
		return;
	}
	if (!$("#c_poundage").validationEngine("validate")) {
		$("#c_poundage").focus();
		setTimeout(function() {
			$("#c_poundage").validationEngine('hideAll');
		}, 1000);
		return;
	}
	if (type1 == 2) {//电票
		if (!$("#rateYear").validationEngine("validate")) {
			$("#rateYear").focus();
			setTimeout(function() {
				$("#rateYear").validationEngine('hideAll');
			}, 1000);
			return;
		}
	} else if (type1 == 1 && Number(allmoney) >= 500) {
		if (!$("#rate").validationEngine("validate")) {
			$("#rate").focus();
			setTimeout(function() {
				$("#rate").validationEngine('hideAll');
			}, 1000);
			return;
		}
		rate = $("#rate").val();//月利率 纸票大票
	} else {
		if (!$("#rate2").validationEngine("validate")) {
			$("#rate2").focus();
			setTimeout(function() {
				$("#rate2").validationEngine('hideAll');
			}, 1000);
			return;
		}
		rate2 = tenTom(rate2, jxts);
	}
	$.ajax({
		url : "${basePath}/homepage/calculator1",
		type : "post",
		data : {
			"type2" : type2,
			"type1" : type1,
			"rate" : rate,
			"rate1" : rate1,
			"rate2" : rate2,
			"allmoney" : allmoney,
			"start" : start,
			"end" : end,
			"jxts" : jxts
		},
		dataType : "json",
		success : function(data) {
			if (data.response == "success") {
				$("#c_txlx").text(data.txlx);//贴现利息
				var allmoney = $("#c_allmoney").val();
				var poundage = $("#c_poundage").val();
				var txje = getTXJE(allmoney, data.txlx, poundage);
				$("#c_txje").text(txje);//贴现金额
			}
		}
	});
}

//设置城市下拉框
function setCityList() {
	$.ajax({
		url : '${basePath}/homepage/xunjia/cityid',
		type : 'POST',
		dataType : 'json',
		data : {},
		success : function(data) {
			var orgCityList = data.orgCityList;
			if (orgCityList != null) {
				var str = '';
				$("#c_orgCityList").text('');
				for ( var i in orgCityList) {
					var orgCity = orgCityList[i];
					if (orgCity.cityid != null && orgCity.name != null) {
						str += "<option value='" + orgCity.cityid + "'>"
								+ orgCity.name + "</option>";
					}
				}
				$("#c_orgCityList").append(str);
			}
		}
	});
}

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
//日历
!function() {
	laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
}();

var start1 = {
	elem : '#c_startDate',
	format : 'YYYY-MM-DD',
	min : '1900-01-01', //设定最小日期
	max : '2099-06-16', //最大日期
	istime : true,
	istoday : false,
	choose : function(datas) {
		initExcel();
		end1.min = datas; //开始日选好后，重置结束日的最小日期
		end1.start = datas; //将结束日的初始值设定为开始日
	}
};
//到期日期
var end1 = {
	elem : '#c_endDate',
	format : 'YYYY-MM-DD',
	min : laydate.now(),
	max : '2099-06-16',
	istime : true,
	istoday : false,
	choose : function(datas) {
		initExcel();
		start1.max = datas; //结束日选好后，充值开始日的最大日期
	}
};
laydate(start1);
laydate(end1);

function c_clear() {
	$("#c_jxts").text("");
	$("#c_allmoney").val("");
	$("#c_day").val("0");
	$("#c_txlx").text("");
	$("#c_txje").text("");
	$("#c_poundage").val("");
	$("#rate").val("");
	$("#rate1").val("");
	$("#rate2").val("");
	$("#rateYear").val("");
}
</script>
</html>