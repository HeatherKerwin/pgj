[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl"]
[@main.body head=seoMap.jrdt]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/market.css"/>
[@main.header currentmenu='1'/]

<!--市场消息-->
<div class="mt16 w1200 bc mb20">
    [@main.news tabIndex='3'/]
    <div class="mt12 bc bae0e0e0 bcwhite pb15 f14 c2d2d2d">
        <!--搜索-->
        <div class="mt30 pl20 pb30 bbe0e0e0">
            <input type="text" placeholder="请输入关键字搜索" class="fl w510 h40 f18 lh40 ti8 babfbfbf mr10" id="keyword">
            <input type="button" class="fl w122 h44 lh40 f22 bcf2f2f2 bae0e0e0 cp" value="搜索" id="find">
            <div class="cb"></div>
        </div>
        <!--信息列表-->
        <div id="content">
    	
    	</div>
    	<div id="pager"></div>
    	<div class="cb"></div>
    </div>
</div>
[@main.right /]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="NEWS">
{{#each results}}
<ul class="pl20 pr20 SCXXlist">
	<li class="w mt40 pb40 xuxian">
	    <div class="fl">
	        <a href="${basePath}/news/detail/{{id}}"><img src="${imagePath}{{pic1}}" class="w190 h120"></a>
	    </div>
	    <div class="fl ml40 w910">
	        <a href="${basePath}/news/detail/{{id}}" class="c2d2d2d"><div class="f18">{{title}}</div></a>
	        <div class="f14 mt10">
	            <div class="fl">{{publishtimeStr}}</div>
	            <div class="fl ml6 w2 h16 bc2d2d2d"></div>
	            <div class="fl ml6">
	            	{{toType type}}
	            </div>
	        </div>
	        <div class="cb"></div>
	        <div class="f14 w910 h90 lh30 c7a7a7a mt10 pr oh">
	        	{{contentShow}}
	        </div>
	    </div>
	    <div class="cb"></div>
	</li>
</ul>
{{/each}}
</script>
<script type="text/javascript">
/*
 * 新闻类型
 */
Handlebars.registerHelper('toType', function(type, options) {
    if(type!=null){
    	return getNewsType(type);
    }
});

/*
 * 获取新闻列表
 */
var pageIndex = 1;//分页
function change(flag,keyword){
	$('#pager').sjAjaxPager({
        url: "${basePath}/news/search",
        pageIndex:pageIndex,
        searchParam: {
            type: flag,
            keyword:keyword
        },beforeSend: function () {
        },success: function (data) {
        	var source = $("#NEWS").html();
            var template = Handlebars.compile(source);
            var html = template(data.data);
            $("#content").html(html);
        },complete: function(){
        },error:function(){
        	
        }
    });
}
$("#find").click(function(){
	var keyword = $("#keyword").val();
    change(2,keyword); 
});

$(document).ready(function() {
	change(2,null);
});
</script>
[@main.footer/]
[/@main.body]