[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家-工具-询价"]
[@main.header currentmenu='2'/]
<div class="wrapper">
<div class="bcwhite p10" id="show">
		<a name="top"></a>
        <ul class="row tac font16">
            <li class="flex1 br">
                <div id = "showInfo1">每10万贴现:(买断)</div>
                <div class="cred mt10" id="showInfo2">--</div>
            </li>
            <li class="flex1">
                <div id = "showInfo3">您的贴现利息:(买断)</div>
                <div class="cred mt10" id="showInfo4">--</div>
            </li>
        </ul>
        <div class="cgray font12 mt10">大票涉及特殊行业、瑕疵票、不足月票、二查票价格另议</div>
    </div>

    <form action="" class="form mt10">
    	<input type="hidden" id="memberid" value="${member.id}"/>
        <ul>
            <li>
                <div class="formTitle">票据属性：</div>
                <ul class="choseBtn">
                    <li><input type="radio" name="type" value="2" id="dian" class="radio" checked/><label for="dian" class="type1_opt_css">电票</label></li>
                    <li><input type="radio" name="type" value="1" id="zhi" class="radio"/><label for="zhi" class="type1_opt_css">纸票</label></li>
                </ul>
            </li>
            <li class="XSdiv">
                <div class="formTitle">承兑期限：</div>
                <ul class="choseBtn">
                    <li><input type="radio" name="limit" value="1" id="year" class="radio" checked/><label for="year" class="type2_opt_css">一年期</label></li>
                    <li><input type="radio" name="limit" value="0" id="half" class="radio" /><label for="half" class="type2_opt_css">半年期</label></li>
                </ul>
            </li>
            <li>
                <div class="formTitle">承兑行：</div>
                <select id="type1">
                    <option value="1" selected>国股</option>
                    <option value="8">大商</option>
                    <option value="2">小商</option>
                    <option value="3">外资</option>
                    <option value="4">农商</option>
                    <option value="5">农合</option>
                    <option value="6">农信</option>
                    <option value="7">村镇</option>
                </select>
            </li>
            <li class="interest4 hide">
                <div class="formTitle"><span class="cred">*</span>交易城市：</div>
                <span class="mr10" id="gr_zone_ids" data-id="10000"></span><a href="#" id="zone_ids" class="cred font12">更改</a>
            </li>
            <li>
                <div class="formTitle"><span class="cred">*</span>单张票据金额：</div>
                <input type="number" name="" id="allmoney" placeholder="0" class="w50"/>
                <span>万元</span>
            </li>
            <li>
                <div class="formTitle">贴现日期：</div>
                <input type="date" id="startDate" onchange="calculatorDay();"/>
            </li>
            <li>
                <div class="formTitle">到期日期：</div>
                <input type="date" id="endDate" onchange="calculatorDay();"/>
            </li>
            <li>
                <div class="formTitle">调整天数：</div>
                <span id="day">0</span>天
            </li>
            <li>
                <div class="formTitle">计息天数：</div>
                <span id="days">0</span>天
            </li>
        </ul>

        <div class="formBtn1 row mt10 mb10">
            <div class="flex1"><input type="button" onclick="xunjia();" class="red" value="询价"/></div>
            <div class="flex1"><input type="button" onclick="downLoadApp();" class="red" value="贴现"/></div>
        </div>
    </form>
</div>
<link rel="stylesheet" href="${staticPath}/css/rywap/form.css"/>
<link href="${staticPath}/css/rywap/city.css" rel="stylesheet" type="text/css" />
<script src="${staticPath}/js/rywap/wx-place.js"></script>
<script type="text/javascript">
	var memberId = $("memberid").val();//${member.id};//获取登录用户主键
	//初始化日期
	var begintimelong = Date.parse(new Date());
	var begintime = new Date(begintimelong);
	var fullyear = begintime.getFullYear();
	//获取完整的年份(4位,1970-????)
	var month = begintime.getMonth() + 1;
	//获取当前月份(0-11,0代表1月)
	var date = begintime.getDate();
	//获取当前日(1-31)
	
	var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
	var endfullyear = endtime.getFullYear();
	//获取完整的年份(4位,1970-????)
	var endmonth = endtime.getMonth() + 1;
	//获取当前月份(0-11,0代表1月)
	var enddate = endtime.getDate();
	//获取当前日(1-31)
	
	var endYearTime = new Date(begintimelong + 365 * 24 * 60 * 60 * 1000);
	var endYearfullyear = endYearTime.getFullYear();
	//获取完整的年份(4位,1970-????)
	var endYearmonth = endYearTime.getMonth() + 1;
	//获取当前月份(0-11,0代表1月)
	var endYeardate = endYearTime.getDate();
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
	if (endYearmonth < 10) {
		endYearmonth = "0" + endYearmonth;
	}
	if (endYeardate < 10) {
		endYeardate = "0" + endYeardate;
	}
	//计算计息天数
	function calculatorDay(){
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var type6;
		$(".type1_opt_css").each(function(){
			if($(this).prev().attr("checked") == "checked"){
				type6 = $(this).prev().val();
			}
		});
		$.ajax({
			url:"${basePath}/wap/tool/jixidate",
			type:"post",
			data:{"startDate":startDate,"endDate":endDate,"type6":type6},
			dataType:"json",
			success:function(data){
	            $("#day").html(data.tzts);
	            $("#days").html(data.jxts);
			}
		});
	}
	$(document).ready(function() {
	  	$("#startDate").val(fullyear + "-" + month + "-" + date);
		$("#endDate").val(endYearfullyear + "-" + endYearmonth + "-" + endYeardate);
		calculatorDay();
	});
	$("input[name='type']").change(function () {
		if($(this).val() == 1){//纸票
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);
			$("#zhi").attr("checked",true);
			$("#dian").attr("checked",false);
			$(".XSdiv").addClass("hide");
			calculatorDay();
			var allmoney = $("#allmoney").val();
		}else if($(this).val() == 2){//电票
			$("#endDate").val(endYearfullyear + "-" + endYearmonth + "-" + endYeardate);
			$("#zhi").attr("checked",false);
			$("#dian").attr("checked",true);
		    $(".XSdiv").removeClass("hide");
		    calculatorDay();
	    }
	});
	$("input[name='limit']").change(function () {
		if($(this).val() == 0){//半年期
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);
			$("#half").attr("checked",true);
			$("#year").attr("checked",false);
			calculatorDay();
		}else if($(this).val() == 1){//一年期
			$("#endDate").val(endYearfullyear + "-" + endYearmonth + "-" + endYeardate);
			$("#half").attr("checked",false);
			$("#year").attr("checked",true);
			calculatorDay();
		}
	});
	function xunjia(){
		$('.wrapper').animate({scrollTop:0},500);
	    var type6;//票据属性
	    $(".type1_opt_css").each(function(){
			if($(this).prev().attr("checked") == "checked"){
				type6 = $(this).prev().val();
			}
		});
	    var type1 = $("#type1").val();//承兑行
	    var cityId = 1;//默认全国id
	    var type5="";//月份
	    var limit;//承兑期限
	    var allmoney= $("#allmoney").val(); //总金额
	    $(".type2_opt_css").each(function(){
			if($(this).prev().attr("checked") == "checked"){
				limit = $(this).prev().val();
			}
		});
	    var dates = $("#days").text();//
		if (Number(allmoney) < 500) {//大票没有票据属性
			if (Number(dates) <= 90) {
				type5 = '1';
			} else if (Number(dates) <= 178) {
				type5 = '2';
			} else {
				type5 = '3';
			}
		}
		if(type6 == 1){
			type5 = '2';
		}
		if (allmoney == '') {
			myToast("请输入票据金额");
			return;
	    }
		$.ajax({
			url:BASEPATH+'/wap/tool/xunjia2',
			data:{"type6": type6,
		          "type1": type1,
		          "type5": type5,
		          "cityId": cityId,
		          "limit": limit,
		          "allmoney": allmoney,
		          "dates": dates,
		          "memberId": memberId},
		    dataType:"json",
		    success:function(res){//这儿
		    	window.scrollBy(0,-10);
		    	if (res.response == "success") {
		            var result = res.data;
		            var price = result.price;
		            var price1 = result.price1;
		            var price2 = result.price2;
		            var price3 = res.txlx;
		            if (type6 == 1) {//纸票
					    if (result.type2 == 1) {
					    	$("#showInfo1").html('当前价格-月利率:(买断)');
					    	if (price == '--' || price == '') {
					    		$("#showInfo2").html('--');
					    		myToast("暂无该选项数据");
					    	} else {
					    		$("#showInfo2").html(price + "‰");
						    }
					    } else {
					    	$("#showInfo1").html('每10万贴息:(买断)');
						    if (price2 == '--' || price2 == '') {
						    	myToast("暂无该选项数据");
						    	$("#showInfo2").html('--');
						    } else {
						    	$("#showInfo2").html(price2 + "元");
					    	}
					    }
					    $("#showInfo3").html("您的贴现利息:(买断)");
				    } else {//电票
				    	$("#showInfo1").html('当前价格-年利 率');
					    if (price == '--' || price == '') {
					    	myToast("暂无该选项数据");
					    	$("#showInfo2").html('--');
					    } else {
					    	$("#showInfo2").html(price + "%");
						}
					}
					if (price3 != null && price3 != "" && price3 != '--') {
						$("#showInfo3").html("您的贴息利息:(买断)");
						$("#showInfo4").html(price3 + "元");
				    } else {
				    	$("#showInfo3").html("您的贴息利息:(买断)");
						$("#showInfo4").html("--元");  
				    }
			    } else {
			    	$("#showInfo2").html("--");
			    	myToast(res.msg);
			    }
		    }      
		});
	}
	
	/**
     * 跳转至贴现页面
     */
    function tiexian(){
        var type1 = $('input[name="type6"]:checked').val();//票据类型（对应页面内id=type6）
        var type2 = $("#type1").val();//承兑行类型（对应页面内id=type1）
        var allmoney = $("#allmoney").val();

        localStorage["TEMP-TYPE1"] = type1;
        localStorage["TEMP-TYPE2"] = type2;
        localStorage["TEMP-ALLMONEY"] = allmoney;
        window.location.href= BASEPATH+'/wap/discountrecord';
    }
</script>
[@main.footer/]
[/@main.body]