 [#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
     [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 bcwhite bae0e0e0">
	    <div class="flex flex-direction-column w997 bcwhite">
	        <div class="flex-row flex-justify-space-between bcfaf7f7 h34 lh34">
	            <div class="ml10">交易记录</div>
	        </div>
	    </div>
	    <div class="flex flex-direction-column w pt30">
	        <ul class="flex-row flex-direction-row w h52 lh50 f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite depositTab">
	            <li class="flex-col-4 bre0e0e0 bbd43c33">
	                <input type="radio" name="depositTab" id="deposit1" value="1" class="none" checked><a href="#" class="cd43c33"><label for="deposit1" class="dsb depositTab1">充值记录</label></a>
	            </li>
	            <li class="flex-col-4 bre0e0e0">
	                <input type="radio" name="depositTab" id="deposit2" value="2" class="none"><a href="#" class="c2d2d2d"><label for="deposit2" class="dsb depositTab1">提现记录</label></a>
	            </li>
	            <li class="flex-col-4 bre0e0e0">
	                <input type="radio" name="depositTab" id="deposit3" value="3" class="none"><a href="#" class="c2d2d2d"><label for="deposit3" class="dsb depositTab1">交易记录</label></a>
	            </li>
	        </ul>
	        <div class="main pl20 pr20">
	        <div class="flex-row flex-direction-column pt30 w">
	            <!--充值记录-->
	            <div class="flex-row flex-direction-column w flex-direction-row bte0e0e0 ble0e0e0 bre0e0e0 lh50 tc czRecord">
	                <div class="flex-row w bbe0e0e0 bcf9f9f9">
	                    <div class="flex-col-3">充值金额</div>
	                    <div class="flex-col-3 ble0e0e0 bre0e0e0">充值方式</div>
	                    <div class="flex-col-3 ble0e0e0 bre0e0e0">充值结果</div>
	                    <div class="flex-col-3">操作时间</div>
	                </div>
	                <div id="content1"></div>
	            </div>
	            <!--提现记录-->
	            <div class="flex-row flex-direction-column w flex-direction-row bte0e0e0 ble0e0e0 bre0e0e0 lh50 tc txRecord none">
	                <div class="flex-row w bbe0e0e0 bcf9f9f9">
	                    <div class="flex-col-3">提现金额</div>
	                    <div class="flex-col-3 ble0e0e0 bre0e0e0">提现方式</div>
	                    <div class="flex-col-3 ble0e0e0 bre0e0e0">提现结果</div>
	                    <div class="flex-col-3">操作时间</div>
	                </div>
	               	<div id="content2"></div>
	            </div>
	            <!--交易记录-->
	            <div class="flex-row flex-direction-column w flex-direction-row bte0e0e0 ble0e0e0 bre0e0e0 lh50 tc bzjRecord none">
	                <div class="flex-row w bbe0e0e0 bcf9f9f9">
	                	<div class="flex-col-4">订单号</div>
	                    <div class="flex-col-4">金额</div>
	                    <div class="flex-col-4  ble0e0e0 bre0e0e0">用途</div>
	                    <div class="flex-col-4">操作时间</div>
	                </div>
	                <div id="content3"></div>    
	            </div>
	        </div>
	        <div  id="pager"></div>
	        </div>
	    </div>
	</div>
    <div class="cb"></div>
</div>
  [@main.right /]
[@main.footer/]
[/@main.body]

<link rel="stylesheet" type="text/css" href="${pluPath}/ajaxPager/page.css"/>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>

<script type="text/x-handlebars-template" id="CZRECORD">
{{#each list}}
	<div class="flex-row w bbe0e0e0">
		<div class="flex-col-3">{{money}}元</div>
		<div class="flex-col-3 ble0e0e0 bre0e0e0">{{toWay way}}</div>
		<div class="flex-col-3 bre0e0e0 bre0e0e0 c049e5b">{{toState accountLogState}}</div>
		<div class="flex-col-3">{{createTime}}</div>
	</div>
{{/each}}
</script>

<script type="text/x-handlebars-template" id="TXRECORD">
{{#each list}}
	<div class="flex-row w bbe0e0e0">
		<div class="flex-col-3">{{money}}元</div>
		<div class="flex-col-3 ble0e0e0 bre0e0e0">{{way}}</div>
		<div class="flex-col-3 bre0e0e0 bre0e0e0 c049e5b">{{toState1 accountLogState}}</div>
		<div class="flex-col-3">{{createTime}}</div>
	</div>
{{/each}}
</script>

<script type="text/x-handlebars-template" id="BZJRECORD">
{{#each list}}
	<div class="flex-row w bbe0e0e0">
		<div class="flex-col-3">{{no}}</div>
		<div class="flex-col-3 ble0e0e0 bre0e0e0">{{money}}元</div>
		<div class="flex-col-3 ble0e0e0 bre0e0e0">{{toPurpose accountLogType}}</div>
		<div class="flex-col-3">{{createTime}}</div>
	</div>
{{/each}}
</script>

<script>

Handlebars.registerHelper('toState', function(accountLogState,options) {
    if(accountLogState=='WAITIN'){
        return "充值中";
    }else if(accountLogState=='FAILED'){
        return "充值失败";
    }else if(accountLogState=='SUCCESS'){
        return "充值成功";
    }else if(accountLogState=='CANCEL'){
        return "取消";
    }
});

Handlebars.registerHelper('toState1', function(accountLogState,options) {
    if(accountLogState=='PENDING'){
        return "待入账";
    }else if(accountLogState=='FAILED'){
        return "提现失败";
    }else if(accountLogState=='SUCCESS'){
        return "提现成功";
    }else if(accountLogState=='CANCEL'){
        return "取消";
    }
});

Handlebars.registerHelper('toWay', function(way,options) {
    if(way=='CZ_BILL99'){
        return "快钱";
    }else if(way=='CZ_BAOFOO'){
        return "宝付";
    }else if(way=='CZ_ALIPAY'){
        return "支付宝";
    }
});

Handlebars.registerHelper('toPurpose', function(accountLogType,options) {
    if(accountLogType=='ORDERIN'){
        return "收到保证金";
    }else if(accountLogType=='ORDEROUT'){
        return "支出保证金";
    }else if(accountLogType=='INQUIRYREPLYOUT'){
        return "查询查复";
    }else if(accountLogType=='INQUIRYREPLYBACK'){
        return "查询查复退款";
    }else if(accountLogType=='SERVER_IN'){
        return "服务费（退还）";
    }else if(accountLogType=='SERVER_OUT'){
        return "服务费（支出）";
    }else if(accountLogType=='VIP'){
        return "购买会员";
    }else if(accountLogType=='ECONTRACT_IN'){
        return "电子签章费（退还）";
    }else if(accountLogType=='ECONTRACT_OUT'){
        return "电子签章费（支出）";
    }
});
	var memberId = ${member.id};
	$(function(){
		loadDate();
	});	
	
	/**
	* 初始化页面加载数据
	*/
	function loadDate(){
		$("#pager").html("");
		var deposit;
		$("input[name='depositTab']").each(function(){
			if($(this).attr("checked") == "checked"){
				deposit = $(this).val();
			}
		});
		var params = {"memberId":memberId};
		if(deposit ==1 ){
			$(".czRecord").removeClass("none");
			$(".txRecord").addClass("none");
			$(".bzjRecord").addClass("none");
			params.type = "CZ";
		}else if(deposit == 2){
			$(".czRecord").addClass("none");
			$(".txRecord").removeClass("none");
			$(".bzjRecord").addClass("none");
			params.type = "TX";
		}else if(deposit == 3){
			$(".czRecord").addClass("none");
			$(".txRecord").addClass("none");
			$(".bzjRecord").removeClass("none");
			params.type = "USE";
		}
		
		var url = '${bootAppPath}/accountlog/page';
		var pageIndex = 1;//分页
		
		$('#pager').sjAjaxPager({
	        url: url,
	        pageIndex:pageIndex,
	        myTotalType:"BOOT",
	        searchParam: params,
	        beforeSend: function () {
	        },success: function (data) {
	        	var source = null;
	    		if(deposit == 1){
	    			source = $("#CZRECORD").html();
	    		}else if(deposit == 2){
	    			source = $("#TXRECORD").html();
	    		}else if(deposit == 3){
	    			source = $("#BZJRECORD").html();
	    		}
	            var template = Handlebars.compile(source);
	            var html = template(data.data.data);
	            console.log(data.data.data);	
	            $("#content"+deposit).html(html);
	        },complete: function(){
	        },error:function(data){
	        	if(data.state == 403){
	        		bootLogin(url,params,false,true)
	        	}else{
	        		alert("出现异常");
	        	}
	        }
	    });
	}
	
//      选择tab
        $(".depositTab1").click(function () {
        	$(".depositTab li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
        	$(".depositTab li").removeClass("bbd43c33");
        	$(".depositTab li").children("label").removeClass("f20");
            $(this).parent().prev().attr("checked",true) 
            $(this).parents("li").addClass("bbd43c33");
            $(this).parents("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
            loadDate();
            
        });
</script>
