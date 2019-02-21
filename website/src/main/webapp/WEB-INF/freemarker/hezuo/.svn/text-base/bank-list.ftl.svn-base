[#include "/common/setting.ftl"]
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>联行号查询</title>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/hezuo/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pluPath}/ajaxPager/page.css"/>
</head>
<body>
	<div class="w940 h420 bc bcwhite pr">
	    <div class="w bcf4f4f4 h50 lh50 f24 ti50 fb">联行号查询</div>
	    <div class="w938 bae0e0e0 h370">
	        <div class="w900 bc bae0e0e0 mt10 tc" id="biaotou">
	            <div class="w h40 lh40 c717583 bcf9f9f9" >
	                <div class="fl w330 bre0e0e0">银行名称</div>
	                <div class="fl w118 bre0e0e0">支付联行号</div>
	                <div class="fl w330 bre0e0e0">地址</div>
	                <div class="fl w118">电话</div>
	            </div>
	        	<div id="content">
	        	</div>
	       	    <div id="pager"></div>
	        </div>
	    </div>
	</div>
	<input type="hidden" id="city" value="${city}">
	<input type="hidden" id="provice" value="${provice}">
	<input type="hidden" id="keyword" value="${keyword}">
	<input type="hidden" id="yinhang" value="${yinhang}">
</body>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="LIANHANG">
{{#each results}}
	<div class="w h40 cl bte0e0e0 f14 h40 lh20">
		<div class="fl w330 bre0e0e0 h40">{{yinhangdesc}}</div>
		<div class="fl w118 bre0e0e0 h40">{{lianhanghao}}</div>
		<div class="fl w330 bre0e0e0 h40">{{address}}</div>
		<div class="fl w118 h40">{{phone}}</div>
	</div>
{{/each}}
</script>
<script type="text/javascript">
var pageIndex = 1;//分页
$(document).ready(function(){
	lianhangSearch();
});

function lianhangSearch(){
	var city = $("#city").val();
	var provice = $("#provice").val();
	var keyword = $("#keyword").val();
	var yinhang = $("#yinhang").val();
	$('#pager').sjAjaxPager({
        url: "${basePath}/homepage/lianhang/search",
        pageIndex:1,
        pageSize:6,
        searchParam: {
        	city:city,
        	yinhang:yinhang,
        	keyword:keyword,
        	provice:provice
        },beforeSend: function () {
        },success: function (data) {
        	if(data.response=="success"){
	        	var source = $("#LIANHANG").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data);
	            $("#content").html(html);
        	}else{
        		alert(data.msg);
        	}
        },complete: function(){
        },error:function(){
        }
    });
}
</script>
</html>