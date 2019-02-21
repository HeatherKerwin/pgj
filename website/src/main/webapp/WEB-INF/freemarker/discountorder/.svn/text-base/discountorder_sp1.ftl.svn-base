[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
<link rel="stylesheet" href="https://static.utiexian.com/css/website/orderList-180606.css">
[@main.header currentmenu='1'/]

<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
   <!--右侧内容 -->
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 ti10 bcfaf7f7">商票订单</div>
        <!--商票订单-->
        <!--订单tab-->
        <ul class="h52 f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite orderTab">
            <li class="w166 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="orderTab" id="order1" value="0" class="none" checked><a href="#" class="cd43c33"><label for="order1" class="dsb orderTab1">全部订单</label></a>
            </li>
            <li class="w165 lh50 fl bre0e0e0 pr zi10">
                <input type="radio" name="orderTab" id="order2" value="1" class="none"><a href="#" class="c2d2d2d"><label for="order2" class="dsb orderTab1">待交易</label></a>
            	<div class="w20 h20 br30 bcfc4d42 cwhite lh20 f14 tc pa t13 r30 zi13 badgepay none"></div>
            </li>
            <li class="w165 lh50 fl bre0e0e0 pr zi10">
                <input type="radio" name="orderTab" id="order3" value="2" class="none"><a href="#" class="c2d2d2d"><label for="order3" class="dsb orderTab1">交易中</label></a>
           		<div class="w20 h20 br30 bcfc4d42 cwhite lh20 f14 tc pa t13 r30 zi13 badgetrade none"></div>
            </li>
            <li class="w165 lh50 fl bre0e0e0 pr zi10">
                <input type="radio" name="orderTab" id="order4" value="3" class="none"><a href="#" class="c2d2d2d"><label for="order4" class="dsb orderTab1">待评价</label></a>
            	<div class="w20 h20 br30 bcfc4d42 cwhite lh20 f14 tc pa t13 r30 zi13 badgeevaluated none"></div>
            </li>
            <li class="w165 lh50 fl bre0e0e0">
                <input type="radio" name="orderTab" id="order5" value="4" class="none"><a href="#" class="c2d2d2d"><label for="order5" class="dsb orderTab1">已完成</label></a>
            </li>
            <li class="w166 lh50 fl">
                <input type="radio" name="orderTab" id="order6" value="5" class="none"><a href="#" class="c2d2d2d"><label for="order6" class="dsb orderTab1">无效订单</label></a>
            </li>
        </ul>
        <!--条件-->
        <div class="orderList orderList-sm" id="order">  
	        <div class="searchCon">
		        <div class="searchRow">
		            <div class="searchCol">
		                <div class="searchTitle">下单时间</div>
		                <div class="searchDate">
		                    <label for="start">
		                        <input type="text" name="searchDate" id="start" readonly>
		                        <img src="https://img.utiexian.com/common/others/rili.png" alt="">
		                    </label>
		                    <span>至</span>
		                    <label for="end">
		                        <input type="text" name="searchDate" id="end" readonly>
		                        <img src="https://img.utiexian.com/common/others/rili.png" alt="">
		                    </label>
		                </div>
		            </div>
		            <div class="searchCol">
		                <div class="searchTitle">票面金额</div>
		                <div class="searchMoney">
		                    <label>
		                        <input type="number" name="" id="minMoney">
		                    </label>
		                    <label>
		                        至<input type="number" name="" id="maxMoney">
		                    </label>
		                    <span>万元</span>
		                </div>
		            </div>
		            <div class="searchCol">
		                <button class="searchBtn" onclick="loadDate();">搜索</button>
		                <button class="clearBtn" onclick="empty();">清空</button>
		            </div>
		        </div>
		    </div>
	        <!--从这里开始复制列表-->
		    <div class="orderList_con">
		        <!--列表标签-->
		        <ul class="listLabel">
		            <li style="width: 120px">票面金额</li>
		            <li style="width: 120px">贴现日期</li>
		            <li style="width: 150px">到期日期/计息天数</li>
		            <li style="width: 120px">背书手数</li>
		            <li style="width: 150px">承兑人</li>
		            <li style="width: 140px">成交金额/年利率</li>
		            <li style="width: 149px">操作</li>
		        </ul>
		        <div id="content">
		        	
		        </div>
		        <div class="flex-row flex-direction-row-reverse">
			    	<div id="pager"></div>
			    </div>
		    </div>
		</div>
    </div>
    <div class="cb"></div> 
</div>
  [@main.right /]

[@main.footer/]
[/@main.body]
    
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>    
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="DISCOUNTRECORDSP">
{{#each list}}
	<ul class="orderList_show details" data-orderflag="{{orderflag}}" data-id="{{id}}">
	    <!--单个订单列表-->
	    <li>
	        <!--回头票-->
	        <div class="returnTicket {{toBackEndorse back_endorse}}"></div>
	        <!--订单信息-->
	        <div class="orderDetailCon">
	            <!--订单头部信息-->
	            <div class="detailHead">
	                <!--票据属性-电票-->
	                <div class="ticketType">
	                    <img src="https://img.utiexian.com/website/180606/shangdian.png" alt="银票电票"  class="{{toType1State type1 1}}">
						<img src="https://img.utiexian.com/website/180606/shangzhi.png" alt="银票纸票" class="{{toType1State type1 2}}">
	                </div>
	                <!--下单信息-->
	                <div class="detailHead_con">
	                    <!--左侧票号-->
	                    <div class="detailHead_left">
	                       {{toType1 type1}}
	                        <span>{{toDraft type1 draft_no ordernumber}}</span>
	                    </div>
	                    <!--右侧-->
	                    <div class="detailHead_right">
	                        <!--订单状态-->
	                        <div>{{toOrderState orderflag deposit_state type1 c_id}}</div>
	                        <!--时间-->
	                        <div class="{{toOrdertime deposit_state}}">
	                       		<img src="https://img.utiexian.com/website/180606/clockIcon.png" width="18" height="18">
	                       		<span class="minute_show{{id}}">{{toTime create_time id deposit_state orderflag}}</span>
	                   		</div>
	                   		<!--提示-->
	                   		<div class="">{{toPrompt orderflag deposit_state type1 c_id id sum create_time cib_cancel}}
								<span class="{{toOrgsum orderflag sum}}">已有<span class="cd43c33" id="orgnum">{{sum}}</span>家机构报价</span>
							</div>
	                    </div>
	                </div>
	            </div>
	            <!--订单具体信息、操作、备注-->
	            <div class="detailCon">
	                <div class="detailInformation RrightLine">
	                    <!--票面信息-->
	                    <div class="ticketsValue">
	                        <div style="width: 120px" class="ce84c3d RrightLine">{{allmoney}}万元</div>
	                        <div style="width: 120px" class="RrightLine">{{begintime}}</div>
	                        <div style="width: 150px" class="RrightLine">{{formatDate endtime}}/{{jxts}}天</div>
	                        <div style="width: 120px" class="RrightLine">{{toEndorse endorse}}</div>
	                        <div style="width: 150px" class="RrightLine">{{toBank bank}}</div>
	                        <div style="width: 140px" >{{toTxje txje}}/{{toRate price}}</div>
	                    </div>
	                    <!--备注-->
	                    <div class="remarks">备注：<span class="bold">{{memberother}}</span></div>
	                </div>
	                <!--操作-->
	                <div class="operation">
	                    <a href="javascript:void(0)" data-orderflag="{{orderflag}}" data-id="{{id}}" class="details redBtn {{toOperation orderflag deposit_state type1 c_id sum 1}}" id="evaluateBtn">去评价</a>
                		<a href="javascript:void(0)" data-orderflag="{{orderflag}}" data-id="{{id}}" class="details redBtn {{toOperation orderflag deposit_state type1 c_id sum 2}}" id="finish">确认已完成</a>
                		<a href="javascript:void(0)" data-orderflag="{{orderflag}}" data-id="{{id}}" class="details redBtn {{toOperation orderflag deposit_state type1 c_id sum 3}}" id="pay">去支付</a>
                		<a href="javascript:void(0)" data-orderflag="{{orderflag}}" data-id="{{id}}" class="details grayBtn {{toOperation orderflag deposit_state type1 c_id sum 4}}" id="cancel">取消订单</a>
              			<a href="javascript:void(0)" data-orderflag="{{orderflag}}" data-id="{{id}}" class="details redBtn {{toOperation orderflag deposit_state type1 c_id sum 5}}" id="endorse">确认背书</a>
                		<a href="javascript:void(0)" data-orderflag="{{orderflag}}" data-id="{{id}}" class="details redBtn {{toOperation orderflag deposit_state type1 c_id sum 6}}" id="gathering">确认收款</a>
                		<a href="javascript:void(0)" data-orderflag="{{orderflag}}" data-id="{{id}}" class="details redBtn {{toOperation orderflag deposit_state type1 c_id sum 7}}" id="gathering">去看看</a>
	                </div>
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
Handlebars.registerHelper('toType1State', function(type1,num, options) {
   	if(type1 == 1){
   		if(num != 2){
   			return "none";
   		}
   	}else{
   		if(num != 1){
   			return "none";
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
*	时间格式化
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
 * 订单标题
 */
Handlebars.registerHelper('toOrderState', function(orderflag ,deposit_state,type1, c_id, options) {
    if(orderflag == 1){
    	if(deposit_state == 0){
    		return "待支付";
    	}else if(deposit_state == 1){
    		return "派单中";
    	}
    }else if(orderflag == 4){
    	if(type1 == 1){
    		return "等待确认";
    	}else{
    		return "打款中";
    	}
    }else if(orderflag == 2){
    	if(type1 == 1){
    		return "等待确认";
    	}else{
    		return "背书中";
    	}
    }else if(orderflag == 7 || orderflag == 5){
    	return "收款中";
    }else if(orderflag == 3 && c_id == null){
    	return "待评价";
    }else if(orderflag == 3 && c_id != null){
    	return "已完成";
    }else if(orderflag == 0 || orderflag == -2){
    	return "无效订单";
    }
});

/*
 * 显示提示语
 */
Handlebars.registerHelper('toPrompt', function(orderflag, deposit_state ,type1,c_id,id,sum,create_time,cib_cancel, options) {
    if(orderflag == 1){
    	if(deposit_state == 1 && (sum == 0 || sum == null)){
    		return "机构正在报价，请耐心等待！";
    	}else if(deposit_state == 1 && sum != null && sum >0){
    		return "";
    	}
    }else if(orderflag == 4){
    	if(cib_cancel != null){
    		if(cib_cancel.orgTime != null && cib_cancel.bnsTime != null){
    			return "双方同意取消订单，订单处理中！";
    		}else if(cib_cancel.orgTime != null){
    			return "资方已经申请取消订单，如有异议，请联系客服！";
    		}else if(cib_cancel.bnsTime != null){
    			return "票方已经申请取消订单，如有异议，请联系客服！";
    		}
    	}else{
    		return "资方处于打款状态！";
    	}
    }else if(orderflag == 2){
    	if(cib_cancel != null){
    		if(cib_cancel.orgTime != null && cib_cancel.bnsTime != null){
    			return "双方同意取消订单，订单处理中！";
    		}else if(cib_cancel.orgTime != null){
    			return "资方已经申请取消订单，如有异议，请联系客服！";
    		}else if(cib_cancel.bnsTime != null){
    			return "票方已经申请取消订单，如有异议，请联系客服！";
    		}
    	}else{
    		return "资方已付款，请尽快背书！";
    	}
    }else if(orderflag == 7){
    	if(cib_cancel != null){
    		if(cib_cancel.orgTime != null && cib_cancel.bnsTime != null){
    			return "双方同意取消订单，订单处理中！";
    		}else if(cib_cancel.orgTime != null){
    			return "资方已经申请取消订单，如有异议，请联系客服！";
    		}else if(cib_cancel.bnsTime != null){
    			return "票方已经申请取消订单，如有异议，请联系客服！";
    		}
    	}else{
    		return "已上传背书，等待资方确认！";
    	}
    }else if(orderflag == 5){
    	return "资方已确认，票款已出，请查收并确认！";
    }
});

/* 
 * 展示报价机构数
 */
Handlebars.registerHelper('toOrgsum', function(orderflag,sum, options) {
	if(orderflag != 1 || sum == 0){
		return "none";
	}else if(orderflag == 1 || sum > 0){
		return "";
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
 * 显示按钮
 */
Handlebars.registerHelper('toOperation', function(orderflag, deposit_state ,type1,c_id,sum,num, options) {
	if(orderflag == 1){
		if(deposit_state == 1){
			if(num != 4 && num != 7){
				return "none";
			}else if(num==7 && sum<1){
				return "none";
			}
		}else{
			if(num !=3){
	    		return "none";
	    	}
		}
    }else if(orderflag == 4){
    	if(num != 4){
    		return "none";
    	}
    }else if(orderflag == 2){
    	if(num != 4 && num != 5){
    		return "none";
    	}
    }else if(orderflag == 7){
    	if(num != 4){
    		return "none";
    	}
    }else if(orderflag == 5){
    	if(num != 6){
    		return "none";
    	}
    }else if(orderflag == 3){
    	if(c_id != null && c_id >0){
    		return "none";
    	}else{
    		if(num != 1){
    			return "none";
    		}
    	}
    }else if(orderflag == 0){
    	return "none";
    }
});
	
	/**
	*选择机构是否隐藏
	*/
	function selectOrg(id,sum){
		$(".orgnum"+id).removeClass("none");
		$("#orgnum").html(sum);
	}

	var memberId = ${member.id};
	$(function(){
		loadDate();
	});
	
	/**
	* 页面初始化 加载数据
		*/
	function loadDate(){
		var org =0 ;
		org = $('input[name="orderTab"]:checked').val();
		var data = {};
		
		if(org == 0){//查询所有的订单
			data = {memberId:memberId,orderType:'DISCOUNTRECORDSP'};
	    }else if(org == 1){//查询待支付的订单
	    	data = {memberId:memberId,orderType:'DISCOUNTRECORDSP',depositState:0,orderflags:1};
	    }else if(org == 2){//查询交易中的订单
	    	data = {memberId:memberId,orderType:'DISCOUNTRECORDSP',depositState:1,orderflags:'1,2,4,5,7'};
	    }else if(org == 3){//查询待评价的订单
	    	data = {memberId:memberId,orderType:'DISCOUNTRECORDSP',orderflags:3,comment:1};
	    }else if(org == 4){//查询完成的订单
	    	data = {memberId:memberId,orderType:'DISCOUNTRECORDSP',orderflags:3,comment:0};
	    }else if(org == 5 ){//查询无效的订单
	    	data = {memberId:memberId,orderType:'DISCOUNTRECORDSP',orderflags:0};
	    }

		var bank = $("#ssbank").val();
		if(bank != null){
			data.bank = bank;
		}
		
		var start = $("#start").val();
		var end = $("#end").val();
		var maxMoney = $("#maxMoney").val();
		var minMoney = $("#minMoney").val();
		if(minMoney==""){
			minMoney = 0;
		}
		if(start!=""){
			data.start = start;
		}
		if(end!=""){
			data.end = end;
		}
		if(maxMoney!=""){
			data.maxMoney = maxMoney;
		}
		if(minMoney!=""){
			data.minMoney = minMoney;
		}
		
		$("#content").html("");
		$("#pager").html("");
		
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/order/list/disc",
	        pageIndex:pageIndex,
	        pageSize:5,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	document.body.scrollTop = document.documentElement.scrollTop = 0;
	        	var source = $("#DISCOUNTRECORDSP").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data.data);
	            $("#content").html(html);
	            
	            $(".details").live("click",details);
	            
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
	 * 清空查询条件
	 */
	function empty(){
		var start = $("#start").val("");
		var end = $("#end").val("");
		var maxMoney = $("#maxMoney").val("");
		var minMoney = $("#minMoney").val("");
		loadDate();
	};

	/**
	* 倒计时
	*/
	var timerArr = new Array();
	function _timer(create_time,id,deposit_state,orderflag){
		if(deposit_state == 0){//未支付保证金，不显示倒计时
			$(".ordertime"+id).addClass("none");
			return ;
		}
		if(orderflag == 0){
			clearInterval(_t);
			$('.minute_show'+id).html('('+0+'分'+0+'秒'+')');
			return ;
		}
		
		var date1 = new Date();//'2017-9-26 15:36:00'
		var date2 = new Date(create_time); 
	
		date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
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

		//选择tab
      $(".orderTab1").click(function () {
     	 $(".orderTab li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
     	 $(".orderTab li").removeClass("bbd43c33");
     	 $(".orderTab li").children("label").removeClass("f20");
        
         $(this).parent().prev().attr("checked",true);
         $(this).parents("li").addClass("bbd43c33");
         $(this).parents("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
         loadDate();
 	     badge(); 
     });
    //日历
    !function(){
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
    }();
    //日期范围限制
    //    贴现日期
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD',
        //min: laydate.now(), //设定最小日期为当前日期
        min: '1900-01-01', //设定最小日期
        max: '2099-06-16', //最大日期
        istime: true,
        istoday: false,
        choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    //    到期日期
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD',
        min: laydate.now(),
        max: '2099-06-16',
        istime: true,
        istoday: false,
        choose: function(datas){
            start.max = datas; //结束日选好后，充值开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);

    $("#sousuo").click(function(){
    	loadDate();
    });
    
	/**
	* 跳转到详情
	*/
	function details(){
		var id =  $(this).attr("data-id");
		var orderflag =  $(this).attr("data-orderflag");
		var map = new Map();
		map.put("_PAGE", "/discountorder/discountordersp");//必传
		map.put("orderflag", orderflag);
		map.put("id", id);
		_OPENPAGE_FORM(map);
	};
</script>
