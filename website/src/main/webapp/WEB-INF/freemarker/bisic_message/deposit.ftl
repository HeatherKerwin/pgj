 [#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]

<!--消息中心-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
     [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">消息中心</div>
        </div>
        <!--订单tab-->
        <ul class="h52 f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite depositTab">
            <li class="w250 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="depositTab" id="deposit1" value="1" class="none" checked><a href="#" class="cd43c33"><label for="deposit1" class="dsb depositTab1">持票企业</label></a>
            </li>
            <li class="w250 lh50 fl bre0e0e0">
                <input type="radio" name="depositTab" id="deposit2" value="2" class="none"><a href="#" class="c2d2d2d"><label for="deposit2" class="dsb depositTab1">贴现机构</label></a>
            </li>
        </ul>
        <input type="hidden" value="0" id="optpage" />
        <!--保证金-->
        <div class="mt30 ml20">
            <div class="f18 lh24 mb13">保证金总金额：<span class="cd43c33 f24" id="alldeposit">¥0.0</span></div>
            <!--列表-->
            <div id="content"></div>
            <div id="pager"></div>
        </div>
    </div>
    <div class="cb"></div>
</div>
  [@main.right /]
[@main.footer/]
[/@main.body]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>

<script type="text/x-handlebars-template" id="DISCOUNTRECORD">
{{#each results}}
	<div class="mr20 mt20 bae0e0e0">
         <div class="w bbe0e0e0 bcf9f9f9 c717583 h50 lh50 tc">
             <div class="fl w232">银票订单号</div>
             <div class="fl w155">总金额</div>
             <div class="fl w108">背书</div>
             <div class="fl w216">付款行</div>
             <div class="fl w122">您的保证金</div>
             <div class="fl w122">对方保证金</div>
         </div>
         <div class="w bcwhite h42 lh42 pt10 pb10 tc">
             <div class="fl w232 ti10 tl Rright"><a class="c3366cc optpage" data-no="{{no}}">{{no}}</a></div>
             <div class="fl w155 Rright"><span style="color:red">{{allmoney}}</span>万元</div>
             <div class="fl w108 Rright">{{endorse}}</div>
             <div class="fl w216 Rright">{{bank}}</div>
             <div class="fl w122 Rright"><span style="color:red">{{qydeposit}}</span>元</div>
             <div class="fl w122"><span style="color:red">{{jgdeposit}}</span>元</div>
         </div>
     </div>
{{/each}}
</script>

<script>

	$(function(){
		loadDate();
	});
	
	/**
	* 初始化页面加载数据
	*/
	function loadDate(){
		var deposit = 1;
		$("input[name='depositTab']").each(function(){
			if($(this).attr("checked") == "checked"){
				deposit = $(this).val();
			}
		});
		org = $('input[name="orderTab"]:checked').val();
		if(deposit ==1 ){
			wwwPath = "${basePath}";
			actionLog(wwwPath,"action53");//统计企业保证金
			var pageIndex = 1;//分页
		    $('#pager').sjAjaxPager({
		        url: "${basePath}/bisicmessagemember/bnsbaojinlist",
		        searchParam:{},
		        beforeSend: function () {
		        	
		        },success: function (data) {
		        	$("#content").html("");
		        	var source = $("#DISCOUNTRECORD").html();
		            var template = Handlebars.compile(source);
		            var html = template(data.data);
		            $("#content").html(html);
		            $("#alldeposit").text("￥"+data.alldeposit);
		            
		            $("#optpage").val(1);
		            
		            $(".optpage").live("click",optpage);
		        },complete: function(){
		        },error:function(){
		        	
		        }
		    });
		}else if(deposit == 2){
			wwwPath = "${basePath}";
			actionLog(wwwPath,"action_53");//统计机构保证金
			var pageIndex = 1;//分页
		    $('#pager').sjAjaxPager({
		        url: "${basePath}/bisicmessagemember/jigoubaozhengjin",
		        searchParam:{},
		        beforeSend: function () {
		        	
		        },success: function (data) {
		        	$("#content").html("");
		        	var source = $("#DISCOUNTRECORD").html();
		            var template = Handlebars.compile(source);
		            var html = template(data.data);
		            $("#content").html(html);
		            $("#optpage").val(2);
		            
		            $("#alldeposit").text("￥"+data.alldeposit);
		            $(".optpage").live("click",optpage);
		        },complete: function(){
		        },error:function(){
		        	
		        }
		    });
		}
	};

	/**
	* 跳转到详情页面
	*/
	function optpage(){
		var optpage = $("#optpage").val();
		var no = $(this).attr("data-no");
		if(optpage == 1){
			location.href = "${basePath}/discountorder/optpage?no="+no;
		}else if(optpage == 2){
			location.href = "${basePath}/distributeorder/detail?no="+no;
		}else{
			location.href = "${basePath}/discountorderpl/optpage?no="+no;
		}
	};
	
//        选择tab
        $(".depositTab1").click(function () {
        	$(".depositTab li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
        	$(".depositTab li").removeClass("bbd43c33");
        	$(".depositTab li").children("label").removeClass("f20");
            $(this).parent().prev().attr("checked",true) 
            $(this).parents("li").addClass("bbd43c33");
            $(this).parents("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
            loadDate();
            
        });
</script>
