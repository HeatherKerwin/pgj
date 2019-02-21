[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='订单大厅']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
[@main.header currentmenu='1'/]

<!--接单大厅 -->
<div class="flex flex-direction-column mt25 w1200 bc bcwhite">
    <img src="${image_url}/website/receive/receive.png" alt="" class="w ha">
    <!--切换tab-->
    <ul class="flex-row flex-direction-row lh50 h52 f16 c2d2d2d lh50 bae0e0e0 tc orderTab">
        <li class="w166 bre0e0e0 bbd43c33">
            <input type="radio" name="orderTab" id="market1" class="none"><a href="#" class="cd43c33"><label for="market1" class="dsb">接单大厅</label></a>
        </li>
        <li class="w166 bre0e0e0">
            <input type="radio" name="orderTab" id="market2" class="none"><a href="#" class="c2d2d2d"><label for="market2" class="dsb">交易大厅</label></a>
        </li>
    </ul>
    <!--没有订单-->
    <div class="flex mt25 w1200 bc bcwhite h500 flex-justify-center flex-alignItems-center noOrder none">
        <div class="flex-row">
            <img src="${image_url}/website/discount/noOrder.png" alt="" width="140" height="179">
        </div>
    </div>
    <div class="w1200 bc bcwhite box-sizing box-shadow mt20 pl30 pr30 pt30 pb30" id="orders">
        <!-- 列表信息 -->

        <!-- 表头 -->
        <div class="clearfix oh bcf9f9f9 c717583 tc box-sizing bae0e0e0 f16 lh45">
            <div class="fl w190">票面金额</div>
            <div class="fl w130">贴现日期</div>
            <div class="fl w190">到期日期/计息天数</div>
            <div class="fl w80">背书手数</div>
            <div class="fl w238">承兑行</div>
            <div class="fl w150">一口价</div>
            <div class="fl w160">操作</div>
        </div>

        <!-- 信息 -->
        <div id="content">
        	
        </div>
    </div>
    <!-- 列表信息 end-->
</div>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="ORDER">
{{#each list}}
	<ul>
		<li class="bae0e0e0 mt20 f16 lh45 box-shadow1 goPrice" data-v="{{disc_v_acct_acct_no}}" data-b="{{disc_bind_id}}" data-memberId="{{bns_member_id}}" data-id="{{id}}" data-type="{{order_type}}">
			<div class="bcf9f9f9 bbe0e0e0 box-sizing box-sizing c717583 pr h45 clearfix">
				<img src="https://img.utiexian.com/website/180606/shangdian.png" alt="商票电票" class="pa left top {{toType1State order_type type1 1}}">
				<img src="https://img.utiexian.com/website/180606/shangzhi.png" alt="商票纸票"  class="pa left top {{toType1State order_type type1 2}}">
				<img src="https://img.utiexian.com/website/180606/yindian.png" alt="银票电票"  class="pa left top {{toType1State order_type type1 3}}">
				<img src="https://img.utiexian.com/website/180606/yinzhi.png" alt="银票纸票" class="pa left top {{toType1State order_type type1 4}}">
	
				<div class="fl pl50">票号 <span>{{toDraft type1 draft_no ordernumber}}</span></div>
				<img class="fl dsib h20 mt15 ml10 {{toSource disc_bind_id}}" src="https://img.utiexian.com/website/180903jingdong/order/jingdong-small.png" alt="">
				<img class="fl dsib h20 mt15 ml10 {{toSource disc_v_acct_acct_no}}" src="https://img.utiexian.com/website/180903jingdong/order/xingye-small.png" alt="">
				<div class="fr f14 ce84c3d mr20 cp {{toOrdertime deposit_state}}">
					<img class="vm dsib mr5" src="https://img.utiexian.com/website/180606/clockIcon.png" alt="倒计时">
					<span class="minute_show{{id}}">{{toTime create_time id deposit_state orderflag}}</span>
				</div>
			</div>
			<div class="w clearfix">
				<div class="fl oh clearfix Rright box-sizing pl10 pr10">
					<div class="clearfix oh bbe0e0e0 c666666 pt10 pb8 lh57 tc">
						<div class="fl w180 Rright ce84c3d">{{allmoney}}万元</div>
						<div class="fl w130 Rright">{{begintime}}</div>
						<div class="fl w190 Rright">{{endtime}}/{{jxts}}天</div>
						<div class="fl w80 Rright">{{toEndorse endorse}}</div>
						<div class="fl w238 Rright oh wsn toe" title="bank">
							{{toBank bank}}
						</div>
						<div class="fl w140">{{toBuyoutPrice buyout_price}}元</div>
					</div>
					<p class="lh40 c666666">备注：{{memberother}}</p>
				</div>
				<div class="fl w160 tc lh30">
					<button type="button" class="w100 bcwhite bae0e0e0 br3 c606060 lh30 dsib  mt20 cp">去报价</button>
					<button type="button" class="w100 bcwhite bae84c3d br3 ce84c3d lh30 dsib  mt20 cp {{toBuyout buyout_price}}">一口价</button>
				</div>
			</div>
		</li>
	</ul>
{{/each}}
</script>
<script type="text/javascript">
/**
* 票据类型
*/
Handlebars.registerHelper('toType1State', function(orderType,type1,num, options) {
    if(orderType == "DISCOUNTRECORD"){
    	if(type1 == 1){
    		if(num != 4){
    			return "none";
    		}
    	}else{
    		if(num != 3){
    			return "none";
    		}
    	}
    }else{
    	if(type1 == 1){
    		if(num != 2){
    			return "none";
    		}
    	}else{
    		if(num != 1){
    			return "none";
    		}
    	}
    }
});

/**
* 票号还是订单号
*/
Handlebars.registerHelper('toType1', function(type1, options) {
    if(type1 == 1){
    	return "订单号：";
    }else{
        return "票号：";
    }
});

/**
* 展示票号还是订单号
*/
Handlebars.registerHelper('toDraft', function(type1,draft_no,ordernumber, options) {
    if(type1 == 1){
    	return ordernumber;
    }else{
        return draft_no;
    }
});

/**
* 贴现金额
*/
Handlebars.registerHelper('toTxje', function(txje, options) {
    if(txje != null){
    	return txje;
    }else{
        return "--";
    }
});

/**
* 年利率
*/
Handlebars.registerHelper('toRate', function(price, options) {
    if(price != null){
    	return price+"%";
    }else{
        return "--";
    }
});

/**
* 背书手数
*/
Handlebars.registerHelper('toEndorse', function(endorse, options) {
    if(endorse != null){
    	return endorse+"手";
    }else{
    	return "--";
    }
});

/**
* 承兑行
*/
Handlebars.registerHelper('toBank', function(bank, options) {
    if(bank != null){
    	return bank;
    }else{
        return "--";
    }
});

/**
* 是否是回头票
*/
Handlebars.registerHelper('toBackEndorse', function(back_endorse, options) {
    if(back_endorse == "T"){
    	return "";
    }else{
    	return "none";
    }
});

/* 
 * 标题提示语
 */
Handlebars.registerHelper('toPrompt', function(deposit_state,orderflag , options) {
    if(deposit_state != 1 || orderflag != 1){
    	return "none";
    }
});

/* 
 * 时间倒计时是否显示
 */
Handlebars.registerHelper('toOrdertime', function(deposit_state, options) {
	if(deposit_state != 1){
		return "none";
	}
});

/* 
 * 展示电票或纸票倒计时
 */
Handlebars.registerHelper('toTime', function(create_time,id,deposit_state,orderflag, options) {
	_timer(create_time,id,deposit_state,orderflag);
});

/* 
 * 展示一口价
 */
Handlebars.registerHelper('toBuyoutPrice', function(buyoutPrice, options) {
	if(buyoutPrice != null && buyoutPrice != ""){
		return buyoutPrice;
	}else{
		return "--/--";
	}
});

/* 
 * 一口价的按钮
 */
Handlebars.registerHelper('toBuyout', function(buyoutPrice, options) {
	if(buyoutPrice == null || buyoutPrice == ""){
		return "none";
	}
});


/* 
 * 下单来源
 */
Handlebars.registerHelper('toSource', function(source, options) {
	if(source == null || source == ""){
		return "none";
	}
});

	var orgId =  ${orgId};
	var jd_status = false;
	var cib_status = false;
	$(function(){
	    loadDate();
	    loadJdCibStatus();
	});
	
	/**
	* 页面初始化加载数据
	*/
	function loadDate(){
		var data = {orgId:orgId};
		
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/dispatch/halllist",
	        pageIndex:pageIndex,
	        pageSize:10,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	console.log(data);
	        	document.body.scrollTop = document.documentElement.scrollTop = 0;
	        	if(data.data.data.list.length >0){
	        		$(".noOrder").addClass("none");
	        		$("#orders").removeClass("none");
	        		var source = $("#ORDER").html();
		            var template = Handlebars.compile(source);
		            var html = template(data.data.data);
		            $("#content").html(html);
		            
		            $(".goPrice").live("click",goPrice);
	        	}
	        	
	        },complete: function(){
	        },error:function(data){
	        	if(data.state == 403){
	        		bootLogin(url,params,false,true)
	        	}else{
	        		alert("出现异常");
	        	}
	        }
	    });
	};
	
	/**
	* 倒计时
	*/
	var timerArr = new Array();
    function _timer(create_time,id,state){
    	if(state == 0){
			clearInterval(_t);
			$('.minute_show'+id).html('('+0+'分'+0+'秒'+')');
			return;
		}
    	
    	var date1 = new Date();//'2017-9-26 15:36:00'
		var date2 = new Date(create_time); 

		date2.setMinutes(date2.getMinutes() + 90 , date2.getSeconds(), 0);
		var s1 = date1.getTime();
		var s2 = date2.getTime(); 
		
		var intDiff = Math.floor((s2 - s1)/1000); 
		
        var _t = window.setInterval(function(){
        	timerArr[timerArr.length] = _t;
            var day=0,
                    hour=0,
                    minute=0,
                    second=0;//时间默认值
            if(intDiff > 0){
                minute = Math.floor(intDiff / 60);
                second = Math.floor(intDiff) - (minute * 60);
            }
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            $('.minute_show'+id).html('('+minute+'分'+second+'秒'+')');
            if(intDiff<=0){
                clearInterval(_t);
            }
            intDiff--;
        }, 1000);
    };
	
	/*选择tab*/
	$("input[name='orderTab']").change(function () {
		location.href = "${basePath}/hall/index2";
	});
    
    /**
    * 跳转到详情
    */
    function goPrice(){
    	var bind_id = $(this).attr("data-b");
    	var v_acct_acct_no = $(this).attr("data-v");
    	if(bind_id!=null&& bind_id!=""&&v_acct_acct_no!=null&&v_acct_acct_no!=""){//下单用户已经开通京东和兴业，接单用户可以直接下单
    	}else if(bind_id != null && bind_id != ""){//下单用户开通京东账户
			if(!jd_status){//接单用户开通京东用户
    			alert("您还没有开通京东账户，不能接京东的订单");
				return;
    		}
    	}else if(v_acct_acct_no != null && v_acct_acct_no != ""){//下单用户已经开通京东账户
    		if(!cib_status){//接单用户开通京东用户
    			alert("您还没有开通兴业账户，不能接兴业的订单");
    			return;
    		}
    	}
    	
    	var id = $(this).attr("data-id");
		var bns_memberId = $(this).attr("data-memberId");
    	var order_type = $(this).attr("data-type");
    	var map = new Map();
		map.put("_PAGE", "/hall/receiveOrder_bj");//必传
		map.put("id", id);
		map.put("bns_memberId", bns_memberId);
		map.put("order_type", order_type);
		_OPENPAGE_FORM(map);
    };
    
    /**
    * 加载京东兴业开户的状态
    */
    function loadJdCibStatus(){
    	var url = '${bootAppPath}/jdjr/cib/account';
    	var params = {memberId:memberId,type:1};
    	var res = bootPost(url,params);
    	if(res != null){
    		var data = res.data;
    		if (data.response == 'success') {
    			var jdjr = data.data.jdjr;
    			var cib = data.data.cib;
   				if(jdjr!=null&&jdjr.status==2&&jdjr.check_state=="PASS"){
   					jd_status = true;
   				}
   				if(cib!=null&&cib.status==2&&cib.cib_check_state=="PASS"){
   					cib_status = true;
   				}
    		}
    	}
    }
</script>
[@main.footer/]
[/@main.body]