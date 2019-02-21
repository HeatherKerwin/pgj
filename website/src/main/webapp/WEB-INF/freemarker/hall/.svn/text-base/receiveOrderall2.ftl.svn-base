[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
<link rel="stylesheet" href="https://static.utiexian.com/css/website/orderList-180606.css">
[@main.header currentmenu='1'/]

<!--交易大厅 -->
<div class="flex flex-direction-column mt25 w1200 bc bcwhite">
    <img src="${image_url}/website/receive/receive.png" alt="" class="w ha">
    <!--切换tab-->
    <ul class="flex-row flex-direction-row lh50 h52 f16 c2d2d2d lh50 bae0e0e0 tc orderTab">
        <li class="w166 bre0e0e0">
            <input type="radio" name="orderTab" id="market1" class="none"><a href="#" class="c2d2d2d"><label for="market1" class="dsb">接单大厅</label></a>
        </li>
        <li class="w166 bre0e0e0 bbd43c33">
            <input type="radio" name="orderTab" id="market2" class="none"><a href="#" class="cd43c33"><label for="market2" class="dsb">交易大厅</label></a>
        </li>
    </ul>
    <!--没有订单-->
    <div class="flex mt25 w1200 bc bcwhite h500 flex-justify-center flex-alignItems-center noOrder">
        <div class="flex-row">
            <img src="${image_url}/website/discount/noOrder.png" alt="" width="140" height="179">
        </div>
    </div>
    <!--有订单-->
    <div class="orderList orderList-md none" id="orders">
	    <div class="orderList_con">
	        <!--列表标签-->
	        <ul class="listLabel">
	            <li style="width: 150px">票面金额</li>
	            <li style="width: 130px">贴现日期</li>
	            <li style="width: 160px">到期日期/计息天数</li>
	            <li style="width: 130px">背书手数</li>
	            <li style="width: 180px">承兑人</li>
	            <li style="width: 200px">成交金额/年利率</li>
	            <li style="width: 205px">操作</li>
	        </ul>
	        <div id="content">
	        	
	        </div>
	        <div class="flex-row flex-direction-row-reverse">
		    	<div id="pager"></div>
		    </div>
	    </div>
	</div>
</div>	
[@main.footer/]
[/@main.body]
<script type="text/x-handlebars-template" id="ORDER">
{{#each list}}
	<ul class="orderList_show goDetails" data-id="{{dist_id}}" data-type="{{order_type}}">
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
	                    <img src="https://img.utiexian.com/website/180606/shangdian.png" alt="商票电票" class="{{toType1State order_type type1 1}}">
						<img src="https://img.utiexian.com/website/180606/shangzhi.png" alt="商票纸票"  class="{{toType1State order_type type1 2}}">
						<img src="https://img.utiexian.com/website/180606/yindian.png" alt="银票电票"  class="{{toType1State order_type type1 3}}">
						<img src="https://img.utiexian.com/website/180606/yinzhi.png" alt="银票纸票" class="{{toType1State order_type type1 4}}">
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
	                        <div></div>
	                        <!--时间-->
	                        <div class="{{toOrdertime deposit_state}}">
	                            <img src="https://img.utiexian.com/website/180606/clockIcon.png">
	                            <span class="minute_show{{id}}">{{toTime create_time id deposit_state orderflag}}</span>
	                        </div>
	                        <!--提示-->
	                        <div class="">{{toPrompt state deposit_state type1 c_id id cib_cancel}}</div>
	                    </div>
	                </div>
	            </div>
	            <!--订单具体信息、操作、备注-->
	            <div class="detailCon">
	                <div class="detailInformation RrightLine">
	                    <!--票面信息-->
	                    <div class="ticketsValue">
	                        <div style="width: 150px" class="ce84c3d RrightLine">{{allmoney}}万元</div>
	                        <div style="width: 130px" class="RrightLine">{{begintime}}</div>
	                        <div style="width: 160px" class="RrightLine">{{endtime}}/{{jxts}}天</div>
	                        <div style="width: 130px" class="RrightLine">{{toEndorse endorse}}</div>
	                        <div style="width: 180px" class="RrightLine">{{toBank bank}}</div>
	                        <div style="width: 200px" >{{toTxje txje}}/{{toRate price}}</div>
	                    </div>
	                    <!--备注-->
	                    <div class="remarks">备注：<span class="bold">{{memberother}}</span></div>
	                </div>
	                <!--操作-->
	                <div class="operation">
	                    <a href="javascript:void(0)" data-id="{{dist_id}}" data-type="{{order_type}}" class="grayBtn goDetails {{toButton state cib_cancel 1}}">取消订单</a>
	                    <a href="javascript:void(0)" data-id="{{dist_id}}" data-type="{{order_type}}" class="redBtn goDetails {{toButton state cib_cancel 2}}">打款</a>
	                    <a href="javascript:void(0)" data-id="{{dist_id}}" data-type="{{order_type}}" class="redBtn goDetails {{toButton state cib_cancel 3}}">确认背书</a>
	                </div>
	            </div>
	        </div>
	    </li>
	</ul>
{{/each}}
</script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
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
 * 显示提示语
 */
Handlebars.registerHelper('toPrompt', function(orderflag, deposit_state ,type1,c_id,id,cib_cancel, options) {
    if(orderflag == 1){
    	return "等待机构选择";
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
    		return "处于打款状态！";
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
	    	return "已付款，等待企业背书！";
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
	    	return "企业已上传背书，等待签收背书！";
    	}
    }else if(orderflag == 5){
    	return "已确认，等待企业收款！";
    }else if(orderflag == 3){
    	if(c_id != null && c_id > 0){
    		return "订单已完成！";
    	}else{
    		return "交易完成，请企业进行评价！";
    	}
    }else if(orderflag == 0){
		return "无效订单";
    }
});

/**
* 按钮的操作显示
*/
Handlebars.registerHelper('toButton', function(orderflag,cib_cancel,num, options) {
	if(orderflag == 1){
		return "none";
	}else if(orderflag == 4){
		if(num == 3){
			return "none";
		}
	}else if(orderflag == 2){
		if(cib_cancel != null){
			if(cib_cancel.orgTime != null){
				return "none";
			}else if(cib_cancel.bnsTime != null){
				if(num != 1){
					return "none";
				}
			}
		}else{
			if(num != 1){
				return "none";
			}
		}
	}else if(orderflag == 7){
		if(cib_cancel != null){
			if(cib_cancel.orgTime != null){
				return "none";
			}else if(cib_cancel.bnsTime != null){
				if(num != 1){
					return "none";
				}
			}
		}else{
			if(num == 2){
				return "none";
			}
		}
	}else if(orderflag == 5){
		return "none";
	}
});

	var orgId =  ${orgId};
	$(function(){
	    loadDate();
	});
	
	/**
	* 页面初始化加载数据
	*/
	function loadDate(){
		var data = {orgId:orgId};
		
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/dispatch/tradelist",
	        pageIndex:pageIndex,
	        pageSize:10,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	document.body.scrollTop = document.documentElement.scrollTop = 0;
	        	console.log(data);
	        	if(data.data.data.list.length >0){
	        		$(".noOrder").addClass("none");
	        		$("#orders").removeClass("none");
	        		var source = $("#ORDER").html();
		            var template = Handlebars.compile(source);
		            var html = template(data.data.data);
		            $("#content").html(html);
		            
		            $(".goDetails").live("click",details);
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
	
	/*选择tab*/
	$("input[name='orderTab']").change(function () {
		location.href = "${basePath}/hall/index";
	});
	
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
    
	//关闭按钮
	$("#closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#cancelWindow").addClass("none"); //取消
	});
	
	//拒绝订单理由显示隐藏
	$("#cancelBtn").click(function(){
	    $("#maskWindow").removeClass("none");
	    $("#cancelWindow").removeClass("none");
	});
	//    填写理由
	$("#reason").change(function(){
	    var value = $(this).val();
	    if(value=="4"){
	        $("#reasonOther").removeClass("none");
	    }else{
	        $("#reasonOther").addClass("none");
	    }
	});
	
	/**
	* 跳转到详情页面
	*/
	function details(){
		var id = $(this).attr("data-id");
		var orderType = $(this).attr("data-type");
		
		var map = new Map();
		if(orderType == "DISCOUNTRECORD"){
			map.put("_PAGE", "/hall/receiveOrder");//必传
		}else if(orderType == "DISCOUNTRECORDSP"){
			map.put("_PAGE", "/hall/receiveOrderSp");//必传
		}else{
			alert("出现异常，联系客服");
			return ;
		}
		
		map.put("id", id);
		_OPENPAGE_FORM(map);
	};
</script>
