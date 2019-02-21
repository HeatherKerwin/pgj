[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
<link rel="stylesheet" href="https://static.utiexian.com/css/website/orderList-180606.css">
[@main.header currentmenu='1'/]

<!--订单大厅 -->
<div class="flex flex-direction-column mt25 w1200 bc bcwhite">
    <img src="https://img.utiexian.com/website/discount/dingdandating.png" alt="" class="w ha">
    <!--没有订单-->
    <div class="flex mt25 w1200 bc bcwhite h500 flex-justify-center flex-alignItems-center noOrder">
        <div class="flex-row">
            <img src="https://img.utiexian.com/website/discount/noOrder.png" alt="" width="140" height="179">
        </div>
    </div>
    <!--有订单-->
    <div class="orderList orderList-md none" id="orderName">
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

<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>添加绑定对公账户</div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--取消订单-->
                <div class="flex-row flex-direction-column w lh30 none" id="cancelWindow">
                    <div class="flex-row flex-justify-space-between h34 mt30">
                        <div class="fb tr">请选择取消理由：</div>
                        <select class="w400 bae0e0e0 br3 ti10 select400 h35" id="reason">
                            <option value="0">票面信息输入有误</option>
                            <option value="1">只为熟悉流程和询问价格</option>
                            <option value="2">价格不合适</option>
                            <option value="3">已提前出票</option>
                            <option value="4">其它</option>
                        </select>
                    </div>
                    <textarea class="h120 bae0e0e0 br3 pt10 pr10 pl10 mt20 none" id="reasonOther" placeholder="请输入不少于15字的理由。"></textarea>
                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt40 mb20">
                        <input type="button" value="确定取消订单" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 mr20">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>	      
[@main.footer/]
[/@main.body]

<script type="text/x-handlebars-template" id="ORDER">
{{#each list}}
	<ul class="orderList_show details" data-id="{{id}}" data-orderflag="{{orderflag}}" data-type="{{order_type}}">
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
	                        <div>{{toOrderState orderflag deposit_state type1 c_id}}</div>
	                        <!--时间-->
	                        <div class="{{toOrdertime deposit_state}}">
	                            <img src="https://img.utiexian.com/website/180606/clockIcon.png">
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
	                        <div style="width: 150px" class="ce84c3d RrightLine">{{allmoney}}万元</div>
	                        <div style="width: 130px" class="RrightLine">{{begintime}}</div>
	                        <div style="width: 160px" class="RrightLine">{{formatDate endtime}}/{{jxts}}天</div>
	                        <div style="width: 130px" class="RrightLine">{{toEndorse endorse}}</div>
	                        <div style="width: 180px" class="RrightLine">{{toBank bank}}</div>
	                        <div style="width: 200px" >{{toTxje txje}}/{{toRate price}}</div>
	                    </div>
	                    <!--备注-->
	                    <div class="remarks">备注：<span class="bold">{{memberother}}</span></div>
	                </div>
	                <!--操作-->
	                <div class="operation">
	                    <a href="javascript:void(0)" data-id="{{id}}" data-orderflag="{{orderflag}}" data-type="{{order_type}}" class="details redBtn goDetails {{toOperation orderflag deposit_state create_time type1 sum 1}}">确认收款</a>
	                    <a href="javascript:void(0)" data-id="{{id}}" data-orderflag="{{orderflag}}" data-type="{{order_type}}" class="details grayBtn goDetails {{toOperation orderflag deposit_state create_time type1 sum 2}}">取消订单</a>
	                    <a href="javascript:void(0)" data-id="{{id}}" data-orderflag="{{orderflag}}" data-type="{{order_type}}" class="details redBtn goDetails {{toOperation orderflag deposit_state create_time type1 sum 3}}">确认背书</a>
	                    <a href="javascript:void(0)" data-id="{{id}}" data-orderflag="{{orderflag}}" data-type="{{order_type}}" class="details redBtn goDetails {{toOperation orderflag deposit_state create_time type1 sum 4}}">去看看>></a>
	                </div>
	            </div>
	        </div>
	    </li>
	</ul>
</div>
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
Handlebars.registerHelper('toOperation', function(orderflag, deposit_state ,create_time,type1,sum,num, options) {
   	if(orderflag == 1){
   		if(deposit_state == 1){
   			if(num != 2 && num != 4){
   				return "none";
   			}else{
   				if(num==4&&sum<1){
   					return "none";
   				}
   			}
   		}
    }else if(orderflag == 4){
    	if(num != 2){
    		return "none";
    	}
    }else if(orderflag == 2){
    	if(num == 1 || num==4){
    		return "none";
    	}
    }else if(orderflag == 7){
    	if(num != 2){
    		return "none";
    	}
    }else if(orderflag == 5){
    	if(num != 1){
    		return "none";
    	}
    }
});
</script>	
<script type="text/javascript">
	var memberId = ${member.id};
	
	/**
	* 页面初始化 加载数据
	*/
	function loadDate(){
		var data = {memberId:memberId};
		
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/order/hall",
	        pageIndex:pageIndex,
	        pageSize:10,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	console.log(data);
	        	if(data.data.data.list.length >0){
	        		document.body.scrollTop = document.documentElement.scrollTop = 0;
	        		$(".noOrder").addClass("none");
	        		$("#orderName").removeClass("none");
	        		var source = $("#ORDER").html();
		            var template = Handlebars.compile(source);
		            var html = template(data.data.data);
		            $("#content").html(html);
		            
		            $(".details").live("click",details);
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
	
	function overtime(create_time){
		var date1 = new Date();//'2017-9-26 15:36:00'
		var date2 = new Date(create_time); 

		date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
		var s1 = date1.getTime();
		var s2 = date2.getTime(); 
		
		var intDiff = Math.floor((s2 - s1)/1000); 
		return intDiff;
	}
	/**
	* 跳转到详情
	*/
	function details(){
		var id =  $(this).attr("data-id");
		var orderflag =  $(this).attr("data-orderflag");
		var order_type = $(this).attr("data-type");
		var map = new Map();
		var url = '/discountorder/discountorder';
		if(order_type == 'DISCOUNTRECORD'){
			url = '/discountorder/discountorder';
		}else if(order_type == 'DISCOUNTRECORDSP'){
			url = '/discountorder/discountordersp';
		}
		map.put("_PAGE", url);//必传
		map.put("orderflag", orderflag);
		map.put("id", id);
		_OPENPAGE_FORM(map);
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

	$(document).ready(function() {
		loadDate();
	});
	//关闭按钮
	$("#closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#endorseWindow").addClass("none"); //背书
	    $("#selectionWindow").addClass("none"); //选择机构
	    $("#appealWindow").addClass("none"); //申诉
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
</script>
