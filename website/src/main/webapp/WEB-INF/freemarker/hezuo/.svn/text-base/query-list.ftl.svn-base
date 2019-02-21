[#include "/common/setting.ftl"]
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>查询查复列表</title>
    <link rel="stylesheet" type="text/css" href="${pluPath}/ajaxPager/page.css"/>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/hezuo/main.css"/>
</head>

<body>
	<div class="w940 h420 bc bcwhite pr">
		<div class="w bcf4f4f4 h50 lh50 f24 ti50 fb">银票查询查复</div>
		<div id="content">
		    <div align="center" class="f24" style="color:gray;margin-top:10%">
		    	您还没有查询查复订单!
		    </div>
		</div>
		<div class="bc">
			<div id="pager"></div>
		</div>
	</div>
</body>

<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/javascript" src="${jsPath}/enum.js"></script>
<script type="text/x-handlebars-template" id="INQUIRY">
{{#each results}}
<div class="ha mt10 ml50 cd43c33">
	{{toEnum pay_state result}}
</div>
<div class="ha mt10 w850 bc bae0e0e0 bcwhite">
	 <div class="w h145 bbe0e0e0 cl f16 cl">
	    <div class="fl w549 bre0e0e0">
	        <div class="w h50 lh50 f14 c717583 bcf9f9f9 tc bbe0e0e0 cl">
	            <div class="fl w200">查询号</div>
	            <div class="fl w225">票号</div>
	            <div class="fl w120">票据金额</div>
	        </div>
	        <div class="ml10 mr10 f14 h40 lh40 tc bbe0e0e0 cl">
	            <a target="_blank" href="${basePath}/inquiryreply/list" class="fl dsib"><div class="w200 h40 c3366cc">{{no}}</div></a>
	            <div class="fl w225 h40">{{draft_no}}</div>
	            <div class="fl w100 h40">{{money}}万元</div>
	        </div>
	        <div class="ml10 mr10 h50 lh20 tc cl f14">
	            <div class="fl w250 h40 mt5">
	                <div >出票日期：<span>{{formatDate start_date}}</span></div>
	                <div >到期日期：<span>{{formatDate end_date}}</span></div>
	            </div>
	        </div>
	    </div>
	    <div class="fl w300 bcwhite tc">
	        <div class="fl w_50 mt10">
	            <div class="c717583">出票人</div>
	            <div class="h40 lh20">{{drawer}}</div>
	        </div>
	        <div class="fl w_50 mt10">
	            <div class="c717583">承兑行号</div>
	            <div class="h40 lh20">{{bank_no}}</div>
	        </div>
	        <div class="fl w_50 mt10">
	            <div class="tc f14 c717583">收款人</div>
	            <div class="h40 lh20">{{payee}}</div>
	        </div>
	        <div class="fl w_50 mt10">
	            <div class="c717583">承兑行全称</div>
	            <div class="h40 ml15 mr15 lh20">{{bank}}</div>
	        </div>
	    </div>
	</div>
	<div class="w bcf4f4f4 h50 lh50 ti10 fb">验票结果：<span>{{result}}</span></div>
</div>
{{/each}}
</script>
<script type="text/javascript">
var memberId = "${memberId}";
$(document).ready(function() {
    loadData(null);
});

//格式化日期
Handlebars.registerHelper('formatDate', function(num, options) {
    if(num!=null){
        var d = new Date(num);
        return d.format('yyyy-MM-dd');
    }else{
        return "--";
    }
});

//订单状态
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
 * 加载数据
 */
function loadData(num){
	var pageIndex = 1;//分页
	$('#pager').sjAjaxPager({
        url: "${basePath}/hezuo/query/list/data",
        pageIndex:1,
        pageSize:1,
        searchParam: {
        	num:num,
        	memberId:memberId
        },beforeSend: function () {
        },success: function (data) {
        	var source = $("#INQUIRY").html();
            var template = Handlebars.compile(source);
            var html = template(data.data);
            if($.trim(html)!="")$("#content").html(html);
        },complete: function(){
        },error:function(){
        	
        }
    });
}
</script>
</html>