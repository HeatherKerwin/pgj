[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-我的查询查复']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='1'/]

<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1'/]
    <!--右侧内容 -->
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">查询查复</div>
            <a class="fr mr20 c3366cc none" href="${basePath}/inquiryreply/inquiryreply">在线查询查复</a>
        </div>
        <!--银票订单-->
        <!--订单tab-->
        <ul class="h52 f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite queryTab">
            <li class="w166 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="queryTab" id="query1" class="none" checked="checked" value="0"><a href="#" class="cd43c33" onclick="loadData(0)"><label for="query1" class="dsb cp queryTab_css">全部订单</label></a>
            </li>
            <li class="w165 lh50 fl bre0e0e0">
                <input type="radio" name="queryTab" id="query2" class="none" value="1"><a href="#" class="c2d2d2d" onclick="loadData(1)"><label for="query2" class="dsb cp queryTab_css">待支付</label></a>
            </li>
            <li class="w165 lh50 fl bre0e0e0">
                <input type="radio" name="queryTab" id="query3" class="none" value="2"><a href="#" class="c2d2d2d" onclick="loadData(2)"><label for="query3" class="dsb cp queryTab_css">反馈中</label></a>
            </li>
            <li class="w165 lh50 fl bre0e0e0">
                <input type="radio" name="queryTab" id="query4" class="none" value="3"><a href="#" class="c2d2d2d" onclick="loadData(3)"><label for="query4" class="dsb cp queryTab_css">已完成</label></a>
            </li>
        </ul>
        <!--条件-->
        <div class="pl20 mt30 ml20 mr20 f14 bae0e0e0">
            <div class="mt20">
                <div class="fl w65 fb lh30">时间</div>
                <div class="fl">
                    <input class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline" id="start" readonly="readonly">
                    <label class="fl w30 h27 rili" for="start"></label>
                    <img src="${image_url}/website/manage/xian.png" width="34" height="1" class="fl ml20 mr20 mt13">
                    <input class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline" id="end" readonly="readonly">
                    <label class="fl w30 h27 rili" for="end"></label>
                </div>
                <div class="cb"></div>
            </div>
            <div class="mt20">
                <div class="fl w65 fb lh30">票号</div>
                <input type="text" class="fl w355 h30 lh30 bae0e0e0 f14 ti10" placeholder="请输入关键字" id="bank_no">
                <div class="cb"></div>
            </div>
            <div class="mt20 mb20">
                <div class="fl w65 fb lh30">票据金额</div>
                <div class="fl lh30">
                    <div class="fl">从</div>
                    <input type="text" class="fl w148 h29 bae0e0e0 br5 ml6 ti10" id="minMoney">
                    <div class="fl ml6">至</div>
                    <input type="text" class="fl w148 h29 bae0e0e0 br5 ml6 ti10" id="maxMoney">
                    <div class="fl ml6">万元</div>
                </div>
                <input type="button" class="fl w110 h34 lh34 bce84c3d b0 br5 cwhite ml40" value="搜索" onclick="search()">
                <div class="cb"></div>
            </div>
        </div>
        <!--列表循环-->
        <div class="mb50" id="content">
            
        </div>
        <!--到这里结束复制列表-->
        <div class="bc">
        	<div id="pager"></div>
        </div>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]

<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="INQUIRY">
{{#each results}}
<div class="ha mt30 ml20 mr20 bb3_e0e0e0 cd43c33">
    	{{toEnum pay_state result}}
</div>
<div class="ha mt30 ml20 mr20 bae0e0e0 bcwhite">
	 <div class="w ha bbe0e0e0">
	    <div class="fl w610 bre0e0e0">
	        <div class="w h50 lh50 f14 c717583 bcf9f9f9 tc bbe0e0e0">
	            <div class="fl w220">查询号</div>
	            <div class="fl w290">票号</div>
	            <div class="fl w100">票据金额</div>
	        </div>
	        <div class="ml10 mr10 pt10 pb10 f16 lh45 tc xuxian">
	            <a href="${basePath}/inquiryreply/detail?no={{no}}"><div class="fl w210 h44 Rright c3366cc tl">{{no}}</div></a>
	            <div class="fl w290 h44 Rright">{{draft_no}}</div>
	            <div class="fl w90 h44">{{money}}万元</div>
	            <div class="cb"></div>
	        </div>
	        <div class="w pt25 pb25 f16 tc">
	            <div class="fl w220 h52 ml10 Rright tl">
	                <div class="">出票日期：<span>{{formatDate start_date}}</span></div>
	                <div class="mt19">到期日期：<span>{{formatDate end_date}}</span></div>
	            </div>
	            <div class="fl h52 ml20 tl" style="display:none">
	                <div class="">支付方式：<span>支付宝</span></div>
	                <div class="mt19">费用：<span>{{pay_money}}元</span></div>
	            </div>
	            <div class="cb"></div>
	        </div>
	    </div>
	    <div class="fl w344 bcwhite">
	        <div class="fl w_50 mt16">
	            <div class="tc f14 c717583">出票人</div>
	            <div class="h60 mt10 ml15 mr15 f14 lh20 tc">{{drawer}}</div>
	        </div>
	        <div class="fl w_50 mt16">
	            <div class="tc f14 c717583">承兑行号</div>
	            <div class="h60 mt10 ml15 mr15 f14 lh20 tc">{{bank_no}}</div>
	        </div>
	        <div class="fl w_50 mt16">
	            <div class="tc f14 c717583">收款人</div>
	            <div class="h60 mt10 ml15 mr15 f14 lh20 tc">{{payee}}</div>
	        </div>
	        <div class="fl w_50 mt16">
	            <div class="tc f14 c717583">承兑行全称</div>
	            <div class="h60 mt10 ml15 mr15 f14 lh20 tc">{{bank}}</div>
	        </div>
	    </div>
	    <div class="cb"></div>
	</div>
	<div class="{{needInvoice need_invoice}} w bcf9f9f9 pt25 pb25">
	    <div class="ml10 fb">发票信息：</div>
	    <div class="w tc mt25">
	        <div class="fl ml10 mr60">
	            <div class="f14 c717583">发票抬头</div>
	            <div class="f16 mt10">{{toType title_type}}</div>
	        </div>
	        <div class="fl mr60">
	            <div class="f14 c717583">发票类型</div>
	            <div class="f16 mt10">{{invoice_type}}</div>
	        </div>
	        <div class="fl mr60">
	            <div class="f14 c717583">发票内容</div>
	            <div class="f16 mt10">{{content}}</div>
	        </div>
	        <div class="fl mr60">
	            <div class="f14 c717583">寄送方式</div>
	            <div class="f16 mt10">{{sendWay send_way}}</div>
	        </div>
	        <div class="fl tl">
	            <div class="f14 c717583">寄送地址</div>
				<div class="f16 mt10 w460 ha">
	            <div class="fl mr5" id="region{{fk_id}}">{{toAddress prov city dist fk_id}}</div>
				<span class="fl">{{address}}</span></div>
	        </div>
	    </div>
	    <div class="cb"></div>
	</div>
	<div class="w h70 bte0e0e0 bcf9f9f9 tc pt15 f18">
        <a href="javascript:void(0)" data-no="{{no}}" class="fr cwhite bcd43c33 br3 dsb w166 h45 lh45 mr40 {{toEditState edit_state}} update">修改</a>
    </div>
</div>
{{/each}}
</script>
<script type="text/javascript">
/* 
 * 页面初始化 
 */
$(document).ready(function() {
    $("input[name='queryTab']").change(function () {
        $(this).parents("ul").children("li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
        $(this).parents("ul").children("li").removeClass("bbd43c33");
        $(this).parents("ul").children("li").children("label").removeClass("f20");
        if ($(this).attr("checked") == "checked") {
            $(this).parent("li").addClass("bbd43c33");
            $(this).parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
        }
    })
    loadData(0)
});

/*
 * 订单状态切换
 */
$(".queryTab_css").click(function(){
	$(".queryTab li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
	$(".queryTab li").removeClass("bbd43c33");
	$(".queryTab li").children("label").removeClass("f20");
	$(this).parent().prev().attr("checked",true);
    $(this).parents("li").addClass("bbd43c33");
    $(this).parents("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
});

/* 
 * 格式化日期 
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

/* 
 * 是否允许修改
 */
Handlebars.registerHelper('toEditState', function(editState, options) {
    if(editState!=null){
    	if(editState != 0){//不允许先修改
    		return "none";
    	}
    }else{
    	return "none";
    }
});

/* 
 * 是否需要发票
 */
Handlebars.registerHelper('needInvoice', function(num, options) {
    if(num!=null){
    	if(num==1){//不需要发票
    		return "none";
    	}
    }
});

/* 
 * 发票抬头
 */
Handlebars.registerHelper('toType', function(num, options) {
    if(num!=null){
    	if(num==0){
    		return "个人";
    	}else{
    		return "企业";
    	}
    }
});

/* 
 * 寄送方式
 */
Handlebars.registerHelper('sendWay', function(num, options) {
    if(num!=null){
    	if(num==0){
    		return "平邮";
    	}else{
    		return "顺丰到付";
    	}
    }
});

/* 
 * 订单状态
 */
Handlebars.registerHelper('toEnum', function(payState,result,options) {
    if(payState==0){
    	return "待支付";
    }else if(payState==1 && result== null){
    	return "反馈中";
    }else if(payState==1 && result!= null){
    	return "已完成";
    }else if(payState==2){
    	return "已退款";
    }
});

/* 
 * 格式化地址
 */
Handlebars.registerHelper('toAddress', function(provId,cityId,distId,fk_id) {
    if(provId!=null && cityId!=null && distId!=null){
    	return initPCD(provId,cityId,distId,fk_id)
    }
});


/*
 * 条件搜索
 */
function search(){
	var num = 0;
	$("input[name='queryTab']").each(function(){
		if($(this).attr("checked") == "checked"){
			num = $(this).val();
		}
	});
	loadData(num);
}

/*
 * 加载数据
 */
function loadData(num){
	var pageIndex = 1;//分页
    var start = $("#start").val();
    var end = $("#end").val();
    var bank_no = $("#bank_no").val();
    var minMoney = $("#minMoney").val();
    var maxMoney = $("#maxMoney").val();
	$('#pager').sjAjaxPager({
        url: "${basePath}/inquiryreply/list/serach",
        pageIndex:1,
        pageSize:10,
        searchParam: {
        	num:num,
        	draftNo:bank_no,
        	start:start,
        	end:end,
        	minMoney:minMoney,
        	maxMoney:maxMoney,
        },beforeSend: function () {
        },success: function (data) {
        	var source = $("#INQUIRY").html();
            var template = Handlebars.compile(source);
            var html = template(data.data);
            $("#content").html(html);
            
            $(".update").live("click",update);
        },complete: function(){
        },error:function(){
        	
        }
    });
}

/**
 * 点击修改，跳转到保存的页面
 */
function update(){
	var no = $(this).attr("data-no");
	location.href = "${basePath}/inquiryreply/update?no="+no;
}

/*
 * 初始化省市区
 */
function initPCD(provId,cityId,distId,fk_id){
	if(provId==null){
		return;
	}
	if(cityId==null){
		return;
	}
	if(distId==null){
		return;
	}
	$.ajax({
		url:"${basePath}/inquiryreply/init/region",
		type:"POST",
		data:{"provId":provId,"cityId":cityId,"distId":distId},
		dataType:"json",
		success:function(data){
			$("#region"+fk_id).text(data.prov+" "+data.city+" "+data.dist); 
		}
	});
}

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