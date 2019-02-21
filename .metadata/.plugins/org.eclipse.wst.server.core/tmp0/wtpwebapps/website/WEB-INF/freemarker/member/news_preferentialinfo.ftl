[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
[@main.right /]

<div class="w1200 ha bc mt20 mb20">
	[@main.left remark='1' /]
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">消息中心</div>
        </div>
        <ul class="h52 f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite newsTab">
            <li class="w165 lh50 fl bre0e0e0">
                <input type="radio" name="newsTab" id="query2" value="1" class="none"><a href="#" class="c2d2d2d"><label for="query2" class="dsb newsTab1">订单消息</label></a>
            </li>
            <li class="w165 lh50 fl bre0e0e0">
                <input type="radio" name="newsTab" id="query2" value="2" class="none"><a href="#" class="c2d2d2d"><label for="query2" class="dsb newsTab1">查询查复</label></a>
            </li>
            <li class="w165 lh50 fl bre0e0e0">
                <input type="radio" name="newsTab" id="query2" value="3" class="none"><a href="#" class="c2d2d2d"><label for="query2" class="dsb newsTab1">系统消息</label></a>
            </li>
            <li class="w165 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="newsTab" id="query2" value="4" class="none"><a href="#" class="c2d2d2d"><label for="query2" class="dsb newsTab1">优惠消息</label></a>
            </li>
            <li class="w166 lh50 fl bre0e0e0">
                <input type="radio" name="newsTab" id="query2" value="5" class="none"><a href="#" class="c2d2d2d"><label for="query2" class="dsb newsTab1">票据提醒</label></a>
            </li>
        </ul>
        <div id="content" class="bcwhite bte0e0e0 ble0e0e0 bre0e0e0 ml20 mr20 mt30">
        
        </div>
        <div class="bc">
        	<div id="pager"></div>
        </div>
    </div>
	<div class="cb"></div>
</div>
[@main.footer/]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="ORDER">
{{#each results}}
<div class="ha bae0e0e0 br5 pt20 pb20 pl20 pr20 mt20 ml20 mr20">
	<div class="f18 bbe0e0e0">{{createTime}}</div>
	<div class="cc43d33 f18">{{title}}</div>
	<div class="wa ha bc h200 tc">
		<a class="{{toUrl url}}" href="{{url}}" target="_blank">
			<img src="{{imgPath imgPath}}" alt="{{title}}" class="w500 ha mt10 bc">
		</a>
		<span class="{{toUrl2 url}}">
			<img src="{{imgPath imgPath}}" alt="{{title}}" class="w500 ha mt10 bc">
		</span>
	</div>
</div>
{{/each}}
</script>
<script type="text/javascript">
Handlebars.registerHelper('imgPath', function(num, options) {
    if(num!=null){
    	return "${imagePath}"+num;
    }
});
Handlebars.registerHelper('toUrl', function(url, options) {
    if(url == null || url == ""){
    	return "none";
    }
});
Handlebars.registerHelper('toUrl2', function(url, options) {
    if(url!= null && url!=""){
    	return "none";
    }
});
$(document).ready(function() {
    loadData(0);
    wwwPath = "${basePath}";
	actionLog(wwwPath,"action136");
});

/*
 * 分页获取列表
 */
var pageIndex = 1;//分页
function loadData(num){
	$('#pager').sjAjaxPager({
        url: "${basePath}/systeminfo/preferentialinfo",
        pageIndex:1,
        pageSize:10,
        searchParam: {
        },beforeSend: function () {
        },success: function (data) {
        	var source = $("#ORDER").html();
            var template = Handlebars.compile(source);
            var html = template(data.data);
            $("#content").html(html);
        },complete: function(){
        },error:function(){
        	
        }
    });
}

$(".newsTab1").click(function () {
	if($(this).parent().prev().val() == 1){
		window.location.href = "${basePath}/systeminfo/list";
	}else if($(this).parent().prev().val() == 2){
		window.location.href = "${basePath}/systeminfo/list/inquiryreply";
	}else if($(this).parent().prev().val() == 3){
		window.location.href = "${basePath}/systeminfo/list/system";
	}else if($(this).parent().prev().val() == 4){
		window.location.href = "${basePath}/systeminfo/list/preferentialinfo";
	}else if($(this).parent().prev().val() == 5){
		window.location.href = "${basePath}/systeminfo/list/notice";
	}
});
</script>
[/@main.body]