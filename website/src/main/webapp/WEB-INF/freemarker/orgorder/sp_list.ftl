[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-商票订单']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
<link rel="stylesheet" href="https://static.utiexian.com/css/website/orderList-180606.css">
[@main.header currentmenu='1'/]

<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1'/]
    <!--右侧内容 -->
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 ti10 bcfaf7f7">商票订单</div>
        <!--银票订单-->
        <!--订单tab-->
        <ul class="h52 f16 c2d2d2d lh50 bae0e0e0 tc bcwhite orderTab">
            <li class="w197 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="orderTab" id="order1" class="none" checked="checked" value="0"><a href="#" class="cd43c33"><label for="order1" class="dsb cp orderTab_css">全部订单</label></a>
            </li>
            <li class="w197 lh50 fl bre0e0e0 pr zi10">
                <input type="radio" name="orderTab" id="order3" class="none" value="1"><a href="#" class="c2d2d2d"><label for="order3" class="dsb cp orderTab_css">交易中</label></a>
            	<div class="w20 h20 br30 bcfc4d42 cwhite lh20 f14 tc pa t13 r30 zi13 none" id="list2"></div>
            </li>
            <li class="w197 lh50 fl bre0e0e0">
                <input type="radio" name="orderTab" id="order4" class="none" value="2"><a href="#" class="c2d2d2d"><label for="order4" class="dsb cp orderTab_css">待评价</label></a>
            </li>
            <li class="w197 lh50 fl bre0e0e0">
                <input type="radio" name="orderTab" id="order4" class="none" value="3"><a href="#" class="c2d2d2d"><label for="order4" class="dsb cp orderTab_css">已完成</label></a>
            </li>
            <li class="w197 lh50 fl bre0e0e0">
                <input type="radio" name="orderTab" id="order5" class="none" value="4"><a href="#" class="c2d2d2d"><label for="order5" class="dsb cp orderTab_css">无效订单</label></a>
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

<!--理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="reason">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <div class="h70"></div>
            <div class="ml25 f16 lh40">
                <div class="fl w138 c7d7d7d">请选择取消理由：</div>
                <select class="fl w320 h40 select320 ti8" id="cancel">
                    <option value="1">额度不够</option>
                    <option value="2">不在业务范围内</option>
                    <option value="3">银行大额支付系统已关闭</option>
                    <option value="4">其他</option>
                </select>
            </div>
            <div class="cb"></div>
            <!-- 其他-->
            <textarea placeholder="请您输入不少于十五字的理由！" class="w500 h210 bae0e0e0 mt30 ml160 ti8 pt15 none" id="reason1_div"></textarea>
            <div class="cb"></div>
            <input type="button" id="cancelButton" value="确认" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b60 l_50 ml-130 cp" onclick="cancelDtbo()">
        </div>
    </div>
</div>
[@main.right /]

<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="DISTRIBUTEORDERSP">
{{#each list}}
	<ul class="orderList_show goDetails" data-id="{{dist_id}}">
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
	                    <img src="https://img.utiexian.com/website/180606/shangdian.png" alt="电票电票"  class="{{toType1State type1 1}}">
						<img src="https://img.utiexian.com/website/180606/shangzhi.png" alt="电票纸票" class="{{toType1State type1 2}}">
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
	                        <div>{{toOrderState state deposit_state type1 c_id cib_cancel}}</div>
	                        <!--时间-->
	                        <div class="{{toOrdertime deposit_state}}">
	                       		<img src="https://img.utiexian.com/website/180606/clockIcon.png" width="18" height="18">
	                       		<span class="minute_show{{id}}">{{toTime create_time id deposit_state orderflag}}</span>
	                   		</div>
	                   		<!--提示-->
	                   		<div class="">{{toPrompt state deposit_state type1 c_id id cib_cancel}}
								<span class="{{toOrgsum orderflag sum}}">已有<span class="cd43c33" id="orgnum">{{sum}}</span>家机构报价<a href="javascript:void(0)" class="">去看看>></a></span>
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
	                        <div style="width: 150px" class="RrightLine">{{endtime}}/{{jxts}}天</div>
	                        <div style="width: 120px" class="RrightLine">{{toEndorse endorse}}</div>
	                        <div style="width: 150px" class="RrightLine">{{toBank bank}}</div>
	                        <div style="width: 140px" >{{toTxje txje}}/{{toRate price}}</div>
	                    </div>
	                    <!--备注-->
	                    <div class="remarks">备注：<span class="bold">{{memberother}}</span></div>
	                </div>
	                <!--操作-->
	                <div class="operation">
	                    <a href="javascript:void(0)" data-id="{{dist_id}}" class="goDetails grayBtn {{toButton state c_id cib_cancel 1}}" id="evaluateBtn">取消订单</a>
                		<a href="javascript:void(0)" data-id="{{dist_id}}" class="goDetails redBtn {{toButton state c_id cib_cancel 2}}" id="finish">打款</a>
                		<a href="javascript:void(0)" data-id="{{dist_id}}" class="goDetails redBtn {{toButton state c_id cib_cancel 3}}" id="pay">签收背书</a>
                		<a href="javascript:void(0)" data-id="{{dist_id}}" class="goDetails redBtn {{toButton state c_id cib_cancel 4}}" id="cancel">去评价</a>
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
Handlebars.registerHelper('toOrderState', function(orderflag ,deposit_state,type1, c_id,cib_cancel, options) {
    if(orderflag == 1){
    	return "等待机构选择";
    }else if(orderflag == 4){
    	if(cib_cancel != null){
    		if(cib_cancel.orgTime != null && cib_cancel.bnsTime != null){
    			return "双方取消订单，订单处理中！";
    		}else if(cib_cancel.orgTime != null){
    			return "资方申请取消订单";
    		}else if(cib_cancel.bnsTime != null){
    			return "票方申请取消订单";
    		}
    	}else{
    		return "处于打款状态！";
    	}
    }else if(orderflag == 2){
    	if(cib_cancel != null){
    		if(cib_cancel.orgTime != null && cib_cancel.bnsTime != null){
    			return "双方取消订单，订单处理中！";
    		}else if(cib_cancel.orgTime != null){
    			return "资方申请取消订单";
    		}else if(cib_cancel.bnsTime != null){
    			return "票方申请取消订单";
    		}
    	}else{
	    	return "企业背书中";
    	}
    }else if(orderflag == 7){
    	if(cib_cancel != null){
    		if(cib_cancel.orgTime != null && cib_cancel.bnsTime != null){
    			return "双方取消订单，订单处理中！";
    		}else if(cib_cancel.orgTime != null){
    			return "资方申请取消订单";
    		}else if(cib_cancel.bnsTime != null){
    			return "票方申请取消订单";
    		}
    	}else{
	    	return "签收背书";
    	}
    }else if(orderflag == 5){
    	return "等待机构确认收款";
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
Handlebars.registerHelper('toPrompt', function(orderflag, deposit_state ,type1,c_id,id,cib_cancel, options) {
    if(orderflag == 1){
    	return "等待机构选择";
    }else if(orderflag == 4){
    	if(cib_cancel != null){
    		if(cib_cancel.orgTime != null && cib_cancel.bnsTime != null){
    			return "双方取消订单，订单处理中！";
    		}else if(cib_cancel.orgTime != null){
    			return "资方申请取消订单";
    		}else if(cib_cancel.bnsTime != null){
    			return "票方申请取消订单";
    		}
    	}else{
    		return "处于打款状态！";
    	}
    }else if(orderflag == 2){
    	if(cib_cancel != null){
    		if(cib_cancel.orgTime != null && cib_cancel.bnsTime != null){
    			return "双方取消订单，订单处理中！";
    		}else if(cib_cancel.orgTime != null){
    			return "资方申请取消订单";
    		}else if(cib_cancel.bnsTime != null){
    			return "票方申请取消订单";
    		}
    	}else{
	    	return "已付款，等待企业背书！";
    	}
    }else if(orderflag == 7){
    	if(cib_cancel != null){
    		if(cib_cancel.orgTime != null && cib_cancel.bnsTime != null){
    			return "双方取消订单，订单处理中！";
    		}else if(cib_cancel.orgTime != null){
    			return "资方申请取消订单";
    		}else if(cib_cancel.bnsTime != null){
    			return "票方申请取消订单";
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

/**
* 按钮的操作显示
*/
Handlebars.registerHelper('toButton', function(orderflag,c_id,cib_cancel,num,options) {
	if(orderflag == 1){
		return "none";
	}else if(orderflag == 4){
		if(num == 3 || num == 4){
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
			}else{
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
			}else{
				if(num != 1 && num != 3){
					return "none";
				}
			}
		}else{
			if(num != 1 && num != 3){
				return "none";
			}
		}
	}else if(orderflag == 5){
		return "none";
	}else if(orderflag == 3){
		if(c_id == null){
			if(num != 4){
				return "none";
			}
		}else{
			return "none";
		}
	}else if(orderflag == 0){
		return "none";
	}
});
	
	var orgId = ${orgId};
	/*
	 * 页面初始化
	 */
	$(document).ready(function() {
		loadDate();
	});

	/**
	* 页面初始化加载数据
	*/
	function loadDate(){
		var org = 0;
		org = $('input[name="orderTab"]:checked').val();
		var data = {};
		if(org == 0){
			data = {orgId:orgId,orderType:'DISTRIBUTEORDERSP'};
		}else if(org == 1){
			data = {orgId:orgId,orderType:'DISTRIBUTEORDERSP',orderflags:'1,2,4,5,7'};
		}else if(org == 2){
			data = {orgId:orgId,orderType:'DISTRIBUTEORDERSP',orderflags:3,comment:1};
		}else if(org == 3){
			data = {orgId:orgId,orderType:'DISTRIBUTEORDERSP',orderflags:3,comment:0};
		}else if(org == 4){
			data = {orgId:orgId,orderType:'DISTRIBUTEORDERSP',orderflags:0};
		}
		
	    var start = $("#start").val();
	    var end = $("#end").val();
	    var minMoney = $("#minMoney").val();
	    var maxMoney = $("#maxMoney").val();
	    
		var bank = $("#bank").val();
		if(bank != null){
			data.bank = bank;
		}

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
	        url: "${bootAppPath}/order/list/dist",
	        pageIndex:pageIndex,
	        pageSize:10,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	document.body.scrollTop = document.documentElement.scrollTop = 0;
        		var source = $("#DISTRIBUTEORDERSP").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data.data);
	            $("#content").html(html);
        		
	            $(".goDetails").live("click",details);
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
	
	/*
	 * 订单状态切换
	 */
	$(".orderTab_css").click(function(){
		$(".orderTab li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
		$(".orderTab li").removeClass("bbd43c33");
		$(".orderTab li").children("label").removeClass("f20");
		$(this).parent().prev().attr("checked",true);
	    $(this).parents("li").addClass("bbd43c33");
	    $(this).parents("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
	    loadDate();
	});

	/*
	 * 条件搜索
	 */
	function search(){
		loadDate();
	}
	
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

		date2.setMinutes(date2.getMinutes() + 30 , date2.getSeconds(), 0);
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
    
    /**
	* 跳转到详情页面
	*/
	function details(){
		var id = $(this).attr("data-id");
		
		var map = new Map();
		map.put("_PAGE", "/hall/receiveOrderSp");
		map.put("id", id);
		_OPENPAGE_FORM(map);
	};
	
//日历
!function(){
    laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
}();
//日期范围限制
//贴现日期
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
//到期日期
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
</script>
[@main.footer/]
[/@main.body]