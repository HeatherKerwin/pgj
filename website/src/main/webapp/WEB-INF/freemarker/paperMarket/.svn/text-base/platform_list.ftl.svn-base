[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-银票订单']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='1'/]

<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1'/]
   	<!--右侧内容 -->
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 ti10 bcfaf7f7">跨平台订单</div>
        <!--银票订单-->
        <!--订单tab-->
      
        <!--从这里开始复制列表-->
        <div class="pl20 pr20 mb50 mt30" id="content">
        
        </div>
        <!--到这里结束复制列表-->
        <div class="bc">
        	<div id="pager"></div>
        </div>
    </div>
    <div class="cb"></div>
</div>

<input id="shddh" class="none" name="shddh"  />
[@main.right /]

<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="DISTRIBUTEORDER">
{{#each list}}
	<div class="flex-row w flex-justify-space-between bbe0e0e0 h27 lh18 mt30">
        <div class="flex-row flex-direction-row">
            <span class="cd43c33 f18 fb orderType">{{toTitle state}}</span>
            <div class="ml30 place none"><img src="https://img.utiexian.com/website/receive/position.png" class="h16 mr3 mt3"><span id="place"></span></div>
        </div>
        <div class="flex-row flex-direction-row">
            <div class="c717583">下单日期:<span class="begintime">{{formatDate create_time}}</span></div>
        </div>
    </div>

 	<!--订单详情-->
    <div class="flex-row flex-direction-column w bae0e0e0 mt10 ordershang pr goDetails" data-id="{{id}}">
    	<!--票据市场：回头票标志-->
		<div class="ticketReturn pa t70 l_50 none"><img src="${imagePath}/website/paperMarket/returnTicket.png" alt="回头票"></div>
        <div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 tc bbe0e0e0 c717583">
            <div class="w212">流水号</div>
            <div class="w115">票面金额</div>
            <div class="w308">票号</div>
            <div class="w80">背书</div>
            <div class="w120">承兑行</div>
            <div class="w120">承兑行号</div>
        </div>
        <div class="flex-row flex-direction-column Rright">
            <div class="flex-row flex-direction-row tc lh45 pt10 pb10 bbe0e0e0">
                <div class="w212 Rright inquirySerialNo">{{quote_serial_no}}</div>
                <div class="w115 cd43c33 Rright"><span class="allmoney">{{toAmt amt}}</span>万元</div>
                <div class="w308 Rright"><span class="draftNo">{{draft_no}}</span></div>
                <div class="w80 Rright"><span class="endorseCount">{{endorse_count}}</span>手</div>
                <div class="w120 Rright bankName">{{bank_name}}</div>
                <div class="w120 bankNo">{{bank_no}}</div>
            </div>
            <div class="flex-row flex-justify-space-between w pt10 pb10">
                <div class="flex-row flex-direction-column w212 pl20 lh30 Rright">
                    <div>到期日期：<span class="endtime">{{expiry_date}}</span></div>
                </div>
                <div class="flex-row flex-direction-row w599 flex-align-self-baseline">
                    <div class="w60 ml20">备注：</div>
                    <p class="w500 mr20 memberother">{{order_desc}}</p>
                </div>
            </div>
        </div>
		<div class="tc bte0e0e0 bbe0e0e0 f16 pl10 pt15 pb15 oh bcf9f9f9">
			<div class="fl f14 ml lh40" >截止日期：<span class="inquiryEnd">{{inquiry_end}}</span></div>
			<a href="javascript:void(0)" data-id="{{id}}" class="fr cd43c33 bad43c33 cp br3 dsb w140 h40 lh40 mr20 goDetails {{toButton state c_id cib_cancel 1}}">取消订单</a>
			<a href="javascript:void(0)" data-id="{{id}}" class="fr cd43c33 bad43c33 cp br3 dsb w140 h40 lh40 mr20 goDetails {{toButton state c_id cib_cancel 2}}">打款</a>
			<a href="javascript:void(0)" data-id="{{id}}" class="fr cd43c33 bad43c33 cp br3 dsb w140 h40 lh40 mr20 goDetails {{toButton state c_id cib_cancel 3}}">交易完成</a>
		</div>
    </div>
{{/each}}
</script>
<script type="text/javascript">
	/**
	* 按钮的操作显示
	*/
	Handlebars.registerHelper('toTitle', function(orderflag,options) {
		if(orderflag == 1){
			return "等待企业选择";
		}else if(orderflag == 3){
			return "交易完成";
		}if(orderflag == 0){
			return "无效订单";
		}else{
			return "交易中";
		}
	});
	
	/**
	* 按钮的操作显示
	*/
	Handlebars.registerHelper('toButton', function(orderflag,c_id,cib_cancel,num,options) {
		if(orderflag == 1){
			return "none";
		}else if(orderflag == 4){
		}else if(orderflag == 2){
		}else if(orderflag == 7){
		}else if(orderflag == 5){
			return "none";
		}else if(orderflag == 3){
			return "none";
		}else if(orderflag == 0){
			return "none";
		}
	});

	/**
	* 金额的转换
	*/
	Handlebars.registerHelper('toAmt', function(amt, options) {
		return amt/10000;
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
	
	var orgId = '${orgId}';
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
		
		$("#content").html("");
		$("#pager").html("");
		var data = {orgId:orgId}
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/distributeorderunion/page",
	        pageIndex:pageIndex,
	        pageSize:10,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	console.log(data);
        		var source = $("#DISTRIBUTEORDER").html();
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
	* 跳转到详情页面
	*/
	function details(){
		var id = $(this).attr("data-id");
		
		var map = new Map();
		map.put("_PAGE", "/paperMarket/platform_detail");
		map.put("id", id);
		_OPENPAGE_FORM(map);
	};
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
</script>
[@main.footer/]
[/@main.body]