[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家-工具-计算器"]
[@main.header currentmenu='2'/]
<div class="wrapper">
<form action="" class="form">
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
        <li>
            <div class="formTitle"><span class="cred">*</span>总金额：</div>
            <input type="number" id="allmoney" onblur="interestMoney();" placeholder="0" class="w100"/>
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
            <input type="number" id="day" readonly="readonly" placeholder="0" class="w100"/>天
        </li>
        <li class="interest1 hide">
            <div class="formTitle"><span class="cred">*</span>月利率：</div>
            <input type="number" step="0.01" id="rate" oninput="changeM();" class="w50" placeholder="0.000"/>
            <span class="mr10">‰ +</span>
            <input type="number" id="rate1" oninput="changeP();" class="w50" placeholder="0"/>
        </li>
        <li class="interest2 hide">
            <div class="formTitle"><span class="cred">*</span>每10万贴息：</div>
            <input type="number" oninput="changeT();" id="rate2" placeholder="0.00" class="w50"/>
            <span>元</span>
        </li>
        <li class="interest3">
            <div class="formTitle"><span class="cred">*</span>年利率：</div>
            <input type="text" id="rateYear" placeholder="0.000" class="w50"/>
            <span>%</span>
        </li>
    </ul>

    <div class="row p10 mt10 formBtn1">
        <div class="flex1 mr10"><input type="button" onclick="qingkong();" value="清空"/></div>
        <div class="flex1"><input type="button" onclick="jisuan();" value="计算" class="red"/></div>
    </div>
    <ul>
        <li>
            <div class="formTitle">计息天数：</div>
            <span id="days">0</span>天
        </li>
        <li>
            <div class="formTitle">贴现利息：</div>
            <span id="c_txlx">0.00 </span>元
        </li>
        <li>
            <div class="formTitle">贴现金额：</div>
            <span id="c_txje">0.00 </span>万元
        </li>
    </ul>
    <div class="formBtn"><input type="button" onclick="downLoadApp();" value="立即贴现"/></div>
</form>
</div>
<link rel="stylesheet" href="${staticPath}/css/rywap/form.css"/>
<script type="text/javascript">
var memberId = null;
$(document).ready(function() {
	memberId = $("memberid").val();;//${member.id}获取登录用户主键
	
  	$("#startDate").val(fullyear + "-" + month + "-" + date);
	$("#endDate").val(endYearfullyear + "-" + endYearmonth + "-" + endYeardate);
	calculatorDay();
});
	
var begintimelong = Date.parse(new Date());//初始化日期
var begintime = new Date(begintimelong);
var fullyear = begintime.getFullYear();

var month = begintime.getMonth() + 1;//获取完整的年份(4位,1970-????)
var date = begintime.getDate();//获取当前月份(0-11,0代表1月)

var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);//获取当前日(1-31)
var endfullyear = endtime.getFullYear();
var endmonth = endtime.getMonth() + 1;//获取完整的年份(4位,1970-????)
var enddate = endtime.getDate();//获取当前月份(0-11,0代表1月)

var endYearTime = new Date(begintimelong + 365 * 24 * 60 * 60 * 1000);//获取当前日(1-31)
var endYearfullyear = endYearTime.getFullYear();

var endYearmonth = endYearTime.getMonth() + 1;//获取完整的年份(4位,1970-????)
var endYeardate = endYearTime.getDate();//获取当前月份(0-11,0代表1月)
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
               $("#day").val(data.tzts);
               $("#days").html(data.jxts);
		}
	});
}
	
$("input[name='type']").change(function () {
   	if($(this).val() == 1){//纸票
   		$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);
   		$("#zhi").attr("checked",true);
   		$("#dian").attr("checked",false);
   		$(".XSdiv").addClass("hide");
   		calculatorDay();
   		var allmoney = $("#allmoney").val();
		$(".interest1").removeClass("hide");//月利率
	    $(".interest2").removeClass("hide");//每十万贴息
   		$(".interest3").addClass("hide");//年利率
   	}else if($(this).val() == 2){//电票
   		$("#endDate").val(endYearfullyear + "-" + endYearmonth + "-" + endYeardate);
   		$("#zhi").attr("checked",false);
   		$("#dian").attr("checked",true);
   	    $(".XSdiv").removeClass("hide");
   	    $(".interest1").addClass("hide");
   	    $(".interest2").addClass("hide");
   	    $(".interest3").removeClass("hide");
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

/**
	* 显示月利息，每10万，还是年利息
  */
function interestMoney(){
	var allmoney = $("#allmoney").val();
	var type6;//票据类型
	$(".type1_opt_css").each(function(){
		if($(this).prev().attr("checked") == "checked"){
			type6 = $(this).prev().val();
		}
	});
	if(allmoney == "")return;
	var cityId = 1;
	var type1 = $("#type1").val();//承兑行
	var start = $("#startDate").val();
	var end = $("#endDate").val();
	var acceptTime ;
	$(".type2_opt_css").each(function(){
		if($(this).prev().attr("checked") == "checked"){
			acceptTime = $(this).prev().val();
		}
	});
	
	$.ajax({
		url:"${basePath}/wap/tool/calculator/init",
		type:"post",
		data:{"type1":type1,"type6":type6,"cityId":cityId,"limit":acceptTime,"allmoney":allmoney,"start":start,"end":end},
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				$("#day").val(data.tzts);
	            if(type6==2){
                       if(data.rate!=null && data.rate!="" && data.rate!="--"){
                           $("#rate").val(yTom(data.rate));//月（年）利率
                           $("#rate1").val(data.rate1);//参数
                           $("#rate2").val(data.rate2);//每十万贴息
                           $("#rateYear").val(data.rate);
                       }
                   }else{
                       if(data.rate!=null && data.rate!="" && data.rate!="--"){
                           $("#rate").val(data.rate);//月（年）利率
                           $("#rate1").val(data.rate1);//参数
                           $("#rate2").val(data.rate2);//每十万贴息
                           $("#rateYear").val(mToy(data.rate));
                       }
                   }
                   $("#days").html(data.jxts);//计息天数
				if(data.txlx!=null && data.txlx!=""){
					$("#c_txlx").html(data.txlx);//贴现利息
					var allmoney = $("#allmoney").val();
					var txje = getTXJE(allmoney,data.txlx);
					$("#c_txje").html(txje);//贴现金额
				}
			}
		}	
     });
}
 	
	/**
	 * 清空
	 */
	function qingkong(){
		$("#startDate").val(fullyear + "-" + month + "-" + date);
		$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);
		calculatorDay();
		$("#c_txlx").text(0.00);//贴现利息
		$("#c_txje").text(0.00);//贴现金额
		$("#allmoney").val("");
		$("#rateYear").val("");
		$("#rate").val("");
		$("#rate1").val("");
		$("#rate2").val("");
	}

/**
 * 年利率转月利率 
 */
function yTom(y){
    if(y==null)return y;
    return (y/12*10).toFixed(2);
}
	
/**
 * 每十万贴息 转 月利率
 * @param {Object} ten 每十万贴息
 * @param {Object} p 参数
 */
function tenTom(ten,p,jxts){
    if(jxts==null)return;
    var res = 0;
    if(p!=null)res = Number(ten)-Number(p);
    var result = res/Number(jxts)/100000*1000*30;
    return result.toFixed(2);
}
 
/**
 * 计算贴现金额（根据总金额、贴现利息、手续费）  
 * @param {Object} allmoney 总金额
 * @param {Object} txlx 贴现利息
 */
function getTXJE(allmoney,txlx){
    var res = (Number(allmoney)*10000)-Number(txlx);
    return (res/10000).toFixed(2);
}
	
function changeP(){//改变参数
	var jxts = $("#days").text();
	
	var rate = $("#rate").val();
	var rate1 = $("#rate1").val();
	$("#rate2").val(mToten(rate,rate1,jxts));
}

function changeT(){//改变每十万贴息
	var jxts = $("#days").text();
	
	var rate1 = $("#rate1").val();
	var rate2 = $("#rate2").val();
	var rate = tenTom(rate2,rate1,jxts);
	if(rate<0){
		$("#rate").val("");
	}else{
		$("#rate").val(rate);
	}
}

function changeM(){//改变月利率
	var jxts = $("#days").text();
	var rate = $("#rate").val();
	var rate1 = $("#rate1").val();
	$("#rate2").val(mToten(rate,rate1,jxts));
}

/**
 * 月利率转 每十万贴息
 * @param {Object} m 月利息
 * @param {Object} p 参数
 */
function mToten(m,p,jxts){
    if(jxts==null)return;
    var res = Number(m)/30/1000*100000*Number(jxts);
    if(p!=null)res += Number(p);
    return res.toFixed(2);
}
/**
 * 计算
 */
function jisuan(){
	var isok=true;
	var type1 ;//票据属性
	$(".type1_opt_css").each(function(){
		if($(this).prev().attr("checked") == "checked"){
			type1 = $(this).prev().val();
		}
	});
	var type2 = $("#type2").val();//承兑行
	var start = $("#startDate").val();
	var end = $("#endDate").val();
	var allmoney = $("#allmoney").val();//总金额
	var acceptTime;
	$(".type2_opt_css").each(function(){
		if($(this).prev().attr("checked") == "checked"){
			acceptTime = $(this).prev().val();
		}
	});
	var rate1 = $("#rate1").val();
	var jxts=$("#days").text();
	var rate2=$("#rate2").val();
	var rate = $("#rateYear").val();//年息 电票
	if(type1==1 ){//纸票
		rate = $("#rate").val();
	}else if(type2==2){//电票
		if (Number(allmoney) >= 500) {
			rate = $("#rateYear").val();
		}else{
			rate = $("#rate").val();
		}
	}
	
	if(allmoney == ""){
		isok=false;
		myToast("总金额不能为空");
	}else if (type1 == 2 && rate == '') {//电票
		isok = false;
		myToast("年利率不能为空");
	}else if (type1 == 1 && rate == '') {//纸票
		isok = false;
		myToast("月利率不能为空");
	}

	if(isok){
	   	$.ajax({
			url:"${basePath}/wap/tool/reexcel/price",
			type:"post",
			data:{"type2":type2,"type1":type1,"rate":rate,"rate1":rate1,"rate2":rate2,"allmoney":allmoney,"start":start,"end":end,"jxts":jxts},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					 $("#c_txlx").text(data.txlx);//贴现利息
					 var allmoney = $("#allmoney").val();
		             var txje = getTXJE(allmoney,data.txlx);
		             $("#c_txje").text(txje);//贴现金额
				}
			}
		});
	}
}
  
/**
 * 点击提交按钮，调转到贴现的功能页面
 */
function tiexian(){
	if($.trim(memberId).length>0){
		var allmoney = $("#allmoney").val();
           if(allmoney==null || allmoney.trim()==""){
               myToast("请输入总金额");
               return;
           }
           var type1 ;//票据类型
           $(".type1_opt_css").each(function(){
			if($(this).prev().attr("checked") == "checked"){
				type1 = $(this).prev().val();
			}
		});
           var type2 = $("#type1").val();//承兑行类型
           var start = $("#startDate").val();
           var end = $("#endDate").val();
           var allmoney = $("#allmoney").val();
           var txlx = $("#c_txlx").val();
           var acceptTime;//承兑期限
		$(".type2_opt_css").each(function(){
			if($(this).prev().attr("checked") == "checked"){
				acceptTime = $(this).prev().val();
			}
		});
   
		localStorage["TEMP-TYPE1"] = type1;
		localStorage["TEMP-TYPE2"] = type2;
		localStorage["TEMP-START"] = start;
		localStorage["TEMP-END"] = end;
		localStorage["TEMP-ALLMONEY"] = allmoney;
		localStorage["TEMP-TXLX"] = txlx;
		localStorage["TEMP-ACCEPTTIME"] = acceptTime;
		window.location.href= '${basePath}/wap/discountrecord';
	}else{
		myToast("你还没有登陆，请去登陆");
	}
}
</script>
[@main.footer/]
[/@main.body]