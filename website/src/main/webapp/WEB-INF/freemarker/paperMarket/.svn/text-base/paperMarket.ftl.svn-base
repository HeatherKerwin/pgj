[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/paperMarket/paperMarket.css"/>
[@main.header currentmenu='1'/]

<div class="w1200 bc">
	<!--菜单-->
	<div class="w1200 h52 bcwhite mt20 bc">
	    <ul class="fl f16 c2d2d2d lh50 tc TXtab oh">
	        <li class="w250 lh50 fl bre0e0e0 bbd43c33"><a href="javascript:void(0)" class="cd43c33 dsb">票据市场</a></li>
        	<li class="w250 lh50 fl bre0e0e0"><a href="${basePath}/inventory" class="c2d2d2d dsb">票据库存清单</a></li>
	    </ul>
	</div>
	
    <!--条件搜索区域-->
    <div class="paperSearch">
        <h2>票据市场</h2>
        <!--已筛选条件-->
        <div class="selected" id="condition"><label for="">筛选条件</label>
               <!--  <a href="javascript:void()" id="conditionMoney">金钱<span class="closeIcon"></span></a>
                <a href="javascript:void()" id="conditionTime">时间<span class="closeIcon"></span></a> -->
        </div>
        <!--选择搜索条件-->
        <ul class="selectDiv">
            <!--承兑行-->
            <!-- <li class="selectRow">
                <span class="title">承兑行</span>
                <ul class="RadioDiv">
                    <li>
                        <label for="bank1"><input type="checkbox" name="bank" id="bank1" class="selectCheckbox selectType2" value="1">国股</label>
                    </li>
                    <li>
                        <label for="bank2"><input type="checkbox" name="bank" id="bank2" class="selectCheckbox selectType2" value="2">城商</label>
                    </li>
                    <li>
                        <label for="bank3"><input type="checkbox" name="bank" id="bank3" class="selectCheckbox selectType2" value="3">其他</label>
                    </li>
                </ul>
            </li> -->
            <!--票面金额-->
            <li class="selectRow">
                <span class="title">票面金额</span>
                <!--选择-->
                <ul class="RadioDiv">
                    <li>
                        <label for="money1"><input type="radio" name="selectMoney" id="money1" class="selectCheckbox selectMoney" value="0">300万以下</label>
                    </li>
                    <li>
                        <label for="money2"><input type="radio" name="selectMoney" id="money2" class="selectCheckbox selectMoney" value="1">300万以上</label>
                    </li>
                </ul>
                <!--自定义-->
                <div class="custom">
                    <div class="numberDiv"><input type="number" name="" id="selectMinMoney">万</div>
                    ~
                    <div class="numberDiv"><input type="number" name="" id="selectMaxMoney">万</div>
                    <button id="selectMoney">确定</button>
                </div>
            </li>
            <!--剩余天数-->
            <li class="selectRow">
                <span class="title">剩余天数</span>
                <ul class="RadioDiv">
                    <li>
                        <label for="days1"><input type="radio" name="selectDays" id="days1" class="selectCheckbox selectDays" value="1">≤90天</label>
                    </li>
                    <li>
                        <label for="days2"><input type="radio" name="selectDays" id="days2" class="selectCheckbox selectDays" value="2">91-180天</label>
                    </li>
                    <li>
                        <label for="days3"><input type="radio" name="selectDays" id="days3" class="selectCheckbox selectDays" value="3">181天以上</label>
                    </li>
                </ul>
                <!--自定义-->
                <div class="custom">
                    <div class="numberDiv"><input type="number" name="" id="selectMinDays">天</div>
                    ~
                    <div class="numberDiv"><input type="number" name="" id="selectMaxDays">天</div>
                    <button id="selectDays">确定</button>
                </div>
            </li>
        </ul>
    </div>
    <!--列表展示-->
    <ul class="paperList" id="content">
        
    </ul>
    <div class="flex-row flex-direction-row-reverse">
    	<div id="pager"></div>
    </div>
</div>
<input type="hidden" value="" name="type2s" id="type2s"/>
<input type="hidden" value="" name="minMoney" id="minMoney"/>
<input type="hidden" value="" name="maxMoney" id="maxMoney"/>
<input type="hidden" value="" name="minDays" id="minDays"/>
<input type="hidden" value="" name="maxDays" id="maxDays"/>
[@main.footer/]
[/@main.body]
<script type="text/x-handlebars-template" id="ORDER">
{{#each list}}
	<li class="paperCon">
		<!--票据标签-->
		<div class="icon {{toClassOrderType order_type}}">{{toOrderType order_type}}</div>
		<!--票据信息展示-->
		<div class="showCon">
			<!--票据属性-->
			<div class="title">
				<span class="type1 {{toClassType1 type1}}">{{toNameType1 order_type type1}}</span>
				<span class="type2 {{toIsShowType2 order_type type1}}">{{toType2 type2}}</span>
			</div>
			<!--信息列表-->
			<ul class="paperShow">
				<li><label>票面金额：</label>{{toAllmoney allmoney order_type}}万元</li>
				<li><label>到 期 日 ：</label>{{formatDate endtime}}</li>
				<li><label>剩余天数：</label><span>{{toDays endtime}}</span>&nbsp;天</li>
				<li><label>发布时间：</label>{{create_time}}</li>
				<li><label>回头背书：</label>{{toBackEndorse back_endorse}}</li>
			</ul>
			<!--票据操作按钮-->
			<a class="paperBtn offerBtn {{toButton orderflag tag create_time dispId order_type 1}}" data-id="{{id}}" data-memberId="{{bns_member_id}}" data-type="{{order_type}}" data-num="1" target="_blank">我要报价</a>
			<a class="paperBtn transactionBtn {{toButton orderflag tag create_time dispId order_type 2}} details" data-id="{{id}}" data-type="{{order_type}}">交易中</a>
			<a class="paperBtn SuccessBtn {{toButton orderflag tag create_time dispId order_type 3}} details" data-id="{{id}}" data-type="{{order_type}}">交易完成</a>
			<a class="paperBtn SuccessBtn {{toButton orderflag tag create_time dispId order_type 4}} details" data-id="{{id}}" data-type="{{order_type}}">优先报价（推送人）</a>
			<a class="paperBtn offerBtn {{toButton orderflag tag create_time dispId order_type 5}}" data-id="{{id}}" data-memberId="{{bns_member_id}}" data-type="{{order_type}}" data-num="5">我要报价（推送）</a>
			<a class="paperBtn SuccessBtn {{toButton orderflag tag create_time dispId order_type 6}} details" data-id="{{id}}" data-type="{{order_type}}">已经报价</a>
		</div>
	</li>
{{/each}}
</script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/javascript">
/**
* 金额的转换
*/
Handlebars.registerHelper('toAllmoney', function(allmoney,orderType, options) {
	if(orderType == "DISCOUNTRECORDUNION"){
		return allmoney/10000;
	}else{
		return allmoney;
	}
});

/**
* 显示票据类型(银票，商票)
*/
Handlebars.registerHelper('toOrderType', function(orderType, options) {
	if(orderType == "DISCOUNTRECORD"){
		return "银";
	}else if(orderType == "DISCOUNTRECORDSP"){
		return "商";
	}else if(orderType == "DISCOUNTRECORDUNION"){
		return "跨平台";
	}
});

/**
* 显示票据类型样式(银票，商票)
*/
Handlebars.registerHelper('toClassOrderType', function(orderType, options) {
	if(orderType == "DISCOUNTRECORD"){
		return "redIcon";
	}else if(orderType == "DISCOUNTRECORDSP"){
		return "VioletIcon";
	}else if(orderType == "DISCOUNTRECORDUNION"){
		return "iconText";
	}
});

/**
* 显示票据类型(纸，电)
*/
Handlebars.registerHelper('toNameType1', function(orderType,type1, options) {
	if(orderType == "DISCOUNTRECORDUNION"){//商票不展示
		return "电票";
	}else{
		return getBA(type1);
	}
});

/**
* 显示票据类型样式(纸，电)
*/
Handlebars.registerHelper('toClassType1', function(type1, options) {
	if(type1 == 1){
		return "zhi";
	}else if(type1 == 2){
		return "dian";
	}
});

/**
 *是否展示承兑行 
 */
 Handlebars.registerHelper('toIsShowType2', function(orderType,type1, options) {
	if(orderType == "DISCOUNTRECORDSP"){//商票不展示
		return "none";
	}else{
		if(type1 == 2){//电票不展示
			return "none";
		}
	}
});

/* 
 * 承兑行
 */
Handlebars.registerHelper('toType2', function(type2, options) {
	if(type2!=null){
    	return getBank(type2);
    }
});

/* 
 * 是否回头票
 */
Handlebars.registerHelper('toBackEndorse', function(back_endorse, options) {
	if(back_endorse == 'T'){
    	return "是";
    }else if(back_endorse == 'F'){
    	return "否";
    }else{
    	return "--";
    }
});

/**
 * 时间格式化
 */
Handlebars.registerHelper('formatDate', function(num, options) {
    if(num!=null){
        num = num.replace(/-/g, "/");
        var d = new Date(num);
        return d.format('yyyy-MM-dd');
    }else{
        return "--";
    }
});

/**
 * 计算时间差
 */
Handlebars.registerHelper('toDays', function(endDate, options) {
	var startDate = new Date();
	startDate = Date.parse(startDate);
	endDate = Date.parse(endDate);
	var dateSpan = endDate - startDate;
	var tempDate = Math.abs(dateSpan);
	var iDays = Math.floor(tempDate / (24 * 3600 * 1000));
	return iDays;
});

/**
 * 订单按钮
 */
Handlebars.registerHelper('toButton', function(orderflag ,tag ,create_time ,dispId,orderType,num, options) {
	if(orderflag == 1){
		if(orderType == "DISCOUNTRECORDUNION"){
			if(num != 1){
				return "none";
			}
		}else{
			if(tag == "WAITTRADE_MINE"){
				if(num != 5){
					return "none";
				}
			}else if(tag == "WAITTRADE"){
				if(num == 2 || num == 3 || num == 5){
					return "none";
				}else{
					return overTime1(create_time ,num,dispId);
				}
			}
		}
	}else if(orderflag == 3){
		if(num != 3){
			return "none";
		}
	}else if(orderflag != 1 && orderflag != 3){
		if(num != 2){
			return "none";
		}
	}
});

var overTime;

/**
 * 选择承兑行类型
 */
$(".selectType2").click(function(){
	$("#type2s").val("");
	$(".conditionType").parent("a").remove();
	var html = '<a href="javascript:void()" class="">承兑行:';
	var type2 = '';
	$(".selectType2").each(function(){
		if($(this).attr("checked")){
			if($(this).val() == 1){
				html += '国股';
				type2 = '1';
			}else if($(this).val() == 2){
				if(type2.length>0){
					html += ',城商'
					type2 += ',2,8';
				}else{
					html += '城商';
					type2 += '2,8';
				}
			}else if($(this).val() == 3){
				if(type2.length>0){
					html += ',其他'
					type2 += ',3,4,5,6,7';
				}else{
					html += '其他';
					type2 += '3,4,5,6,7';
				}
			}
		}
	});
	html += '<span class="closeIcon conditionType" value="0"></span></a>';
	$("#type2s").val(type2);
	if(type2.length>0){
		$("#condition").append(html);
	}else{
		$("#condition").append("");
	}
	
	$(".conditionType").on("click",conditionType);
	search();
}); 

/**
 * 清除承兑行
 */
function conditionType(){
	$(this).parent("a").remove();
	$(".selectType2").attr("checked",false);
	$("#type2s").val("");
	search();
};

/**
 *  选择票面金额
 */
$(".selectMoney").click(function(){
	$("#minMoney").val("");
	$("#maxMoney").val("");
	$(".conditionMoney").parent("a").remove();
	var selectMoney = $("input:radio[name='selectMoney']:checked").val();
	var html = '<a href="javascript:void()" class="">票面金额:';
	if(selectMoney == 0){
		html += "300万以下";
		$("#maxMoney").val(300);
	}else if(selectMoney == 1){
		html += "300万以上";
		$("#minMoney").val(300);
	}
	html += '<span class="closeIcon conditionMoney" value="0"></span></a>';
	$("#condition").append(html);
	$(".conditionMoney").on("click",conditionMoney);
	search();
});

/**
 * 自定义金额
 */
$("#selectMoney").click(function(){
	var selectMinMoney = $("#selectMinMoney").val();
	var selectMaxMoney = $("#selectMaxMoney").val();
	if(parseFloat(selectMaxMoney)<parseFloat(selectMinMoney)){
		alert("请填入合适的金额");
		return ;
	}
	if(selectMaxMoney.length == 0 && selectMinMoney.length == 0){
		alert("请填入合适的金额");
		return ;
	}
	$(".selectMoney").attr("checked",false);
	$(".conditionMoney").parent("a").remove();
	$("#minMoney").val(selectMinMoney);
	$("#maxMoney").val(selectMaxMoney);
	var html = '<a href="javascript:void()" class="">票面金额:'+selectMinMoney+"~"+selectMaxMoney;
	html += '万<span class="closeIcon conditionMoney" value="0"></span></a>';
	$("#condition").append(html);
	$(".conditionMoney").on("click",conditionMoney);
	search();
});

/**
 * 清除票面金额
 */
function conditionMoney(){
	$(this).parent("a").remove();
	$(".selectMoney").attr("checked",false);
	$("#selectMinMoney").val("");
	$("#selectMaxMoney").val("");
	$("#minMoney").val("");
	$("#maxMoney").val("");
	search();
}

/**
 *  选择天数
 */
$(".selectDays").click(function(){
	$("#minDays").val("");
	$("#maxDays").val("");
	$(".conditionDays").parent("a").remove();
	var selectDays = $("input:radio[name='selectDays']:checked").val();
	var html = '<a href="javascript:void()" class="">剩余天数:';
	if(selectDays == 1){
		html += "90天以下";
		$("#maxDays").val(90);
	}else if(selectDays == 2){
		html += "90~180天";
		$("#minDays").val(90);
		$("#maxDays").val(180);
	}else if(selectDays == 3){
		html += "180天以上";
		$("#minDays").val(180);
	}
	html += '<span class="closeIcon conditionDays" value="0"></span></a>';
	$("#condition").append(html);
	$(".conditionDays").on("click",conditionDays);
	search();
});

/**
 * 自定义天数
 */
$("#selectDays").click(function(){
	var selectMinDays = $("#selectMinDays").val();
	var selectMaxDays = $("#selectMaxDays").val();
	if(parseInt(selectMaxDays)<parseInt(selectMinDays)){
		alert("请填入合适的天数");
		return ;
	}
	if(selectMaxDays.length == 0 && selectMinDays.length == 0){
		alert("请填入合适的天数");
	return ;
	}
	$(".selectDays").attr("checked",false);
	$(".conditionDays").parent("a").remove();
	$("#minDays").val(selectMinDays);
	$("#maxDays").val(selectMaxDays);
	var html = '<a href="javascript:void()" class="">剩余天数:'+selectMinDays+"~"+selectMaxDays;
	html += '天<span class="closeIcon conditionDays" value="0"></span></a>';
	$("#condition").append(html);
	$(".conditionDays").on("click",conditionDays);
	search();
});

/**
 * 清除天数
 */
function conditionDays(){
	$(this).parent("a").remove();
	$(".selectDays").attr("checked",false);
	$("#selectMinDays").val("");
	$("#selectMaxDays").val("");
	$("#minDays").val("");
	$("#maxDays").val("");
	search();
}
var orgId;
$(function(){
	checkAccounts();
	checkJd();
	search();
});

/**
 * 查询，获取订单列表
 */
function search(){
	$("#pager").html("");
	var type2s = $("#type2s").val();
	var minDays = $("#minDays").val();
	var maxDays = $("#maxDays").val();
	var minMoney = $("#minMoney").val();
	var maxMoney = $("#maxMoney").val();
	var data = {type2s:type2s,minDay:minDays,maxDay:maxDays,minMoney:minMoney,maxMoney:maxMoney,orderTypes:'DISCOUNTRECORDSP,DISCOUNTRECORD'};
	if(orgId != null && orgId.length>0){
		data.orgId = orgId;
	}else{
		data.orderflags = '3';
	}
	var pageIndex = 1;//分页
    $("#pager").sjAjaxPager({
    	url: "${bootAppPath}/dispatch/page/hall",
        pageIndex:pageIndex,
        pageSize:8,
        myTotalType:"data.data.data.page.total",
        searchParam: data,
        beforeSend: function () {
        	
        },success: function (data) {
        	console.log(data);
        	overTime = data.data.data.overTime;
       		var source = $("#ORDER").html();
            var template = Handlebars.compile(source);
            var html = template(data.data.data.page);
            $("#content").html(html);
            
            $(".offerBtn").on("click",market_bj);
            $(".details").on("click",openDetails);
        },complete: function(){
		},error:function(data){
        	alert("出现异常");
        }
    });
};

/**
 * 报价是否超时，能否报价
 */
function overTime1(create_time,num,dispId){
	var date1=new Date(); //当前时间 
	var date2 = new Date(create_time);//贴现时间
	var date3= date1.getTime()-date2.getTime()  //时间差的毫秒数
	//计算出相差秒数
	var seconds = Math.floor(date3/1000)
	var a = parseInt(seconds) - parseInt(overTime);
	if(a>=0){
		if(num == 1){
			if(dispId != null){
				return "none";
			}else{
				return "";
			}
		}else if(num == 4){
			return "none";
		}else if(num == 6){
			if(dispId != null){
				return "";
			}else{
				return "none";
			}
		}
	}else{
		if(num == 1){
			return "none";
		}else if(num == 4){
			if(dispId != null){
				return "none";
			}else{
				return "";
			}
		}else if(num == 6){
			if(dispId != null){
				return "";
			}else{
				return "none";
			}
		}
	}
};

/**
 * 跳转到报价页面
 */
function market_bj(){
	var id = $(this).attr("data-id");
	var bns_memberId = $(this).attr("data-memberId");
	var order_type = $(this).attr("data-type");
	var num = $(this).attr("data-num");
	
	if('${member.id}' == bns_memberId){
		alert("自己的订单不能报价！请选择其他订单。");
		return ;
	}
	
	var map = new Map();
	var url ;
	if(order_type == "DISCOUNTRECORDUNION"){
		url = "/paperMarket/platform_bj";
	}else{
		if(num == 1){
			url = "/paperMarket/market_bj";
		}else{
			url = "/hall/receiveOrder_bj";
		}
	}
	map.put("_PAGE", url);//必传
	map.put("id", id);
	map.put("bns_memberId", bns_memberId);
	map.put("order_type", order_type);
	_OPENPAGE_FORM(map);
};

/**
 * 跳转到订单详细信息
 */
function openDetails(){
	var id = $(this).attr("data-id");
	var order_type = $(this).attr("data-type");
	
	var map = new Map();
	map.put("_PAGE", "/paperMarket/paperMarketDetail");//必传
	map.put("discId", id);
	map.put("orderType", order_type);
	_OPENPAGE_FORM(map);
};

/**
 * @param type 0出票方   1资方（收票方）
 */
function checkAccounts(){
	var memberId = '${member.id}';
	var url = '${bootAppPath}/orginfo/rz';
	if(memberId == null || memberId == "")return ;
	var params = {memberId:memberId,type:"1"};
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		if (data.response == 'success') {
			console.log(data);
			var active = data.data;
			var cib = active.cib;
			var cibCheckState;
			if(active.info!=null){
				cibCheckState = active.info.cibCheckState;
			}
			if(active.info!=null && active.info_state == "PASS" && active.info.cibCheckState == 'PASS' && cib.status == 2){//已经成功开户
				orgId = '${orgId}';
			}
		}
	}
};

/**
 * @param type 0出票方   1资方（收票方）
 */
function checkJd(){
	var memberId = '${member.id}';
	var url = '${bootAppPath}/jdjr/cib/account';
	if(memberId == null || memberId == "")return ;
	var params = {memberId:memberId,type:1};
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		if (data.response == 'success') {
			var jdjr = data.data.jdjr;
			if(jdjr!=null&&jdjr.status==2&&jdjr.check_state=="PASS"){//开户成功
				orgId = '${orgId}';
			}
		}
	}
};
</script>
