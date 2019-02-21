[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
[@main.header currentmenu='1' topindex="0"/]
  [@main.right /]
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark ='1'/]
    <div class="flex flex-direction-column w997 hmin688 bcwhite">
    <!--切换tab-->
    <ul class="flex-row flex-direction-row lh50 h52 f16 c2d2d2d lh50 bae0e0e0 tc orderTab">
        <li class="w166 bre0e0e0 bbd43c33">
            <input type="radio" name="commentsTab" value="1" checked="checked" id="market1" class="none"><a href="#" class="cd43c33"><label for="market1" class="dsb">企业评分</label></a>
        </li>
        <li class="w166 bre0e0e0">
            <input type="radio" name="commentsTab" value="0" id="market2" class="none"><a href="#" class="c2d2d2d"><label for="market2" class="dsb">机构评分</label></a>
        </li>
    </ul>

    <!--内容-->
    <div class="flex flex-direction-column pl25 pr25 pb25">
        <!--评分框-->
        <div class="flex-row flex-direction-row flex-justify-space-between c727272 lh30 tc pt20">
            <div class="flex-col-5 bae0e0e0 bcf9f9f9 mr10 pt20 pb20">
                <div class="flex-row w flex-direction-row">
                    <div class="flex-col-6 bns">
                        <div class="flex-row flex-direction-column">
                            <div>背书速度</div>
                            <div class="cd43c33 endorseTime">--</div>
                        </div>
                    </div>

                    <div class="flex-col-6 org none">
                        <div class="flex-row flex-direction-column">
                            <div>打款速度</div>
                            <div class="cd43c33 advanceTime">--</div>
                        </div>
                    </div>
                    <div class="flex-col-6 org none">
                        <div class="flex-row flex-direction-column">
                            <div>历史报价持续性</div>
                            <div class="cd43c33 priceDurative">--</div>
                        </div>
                    </div>
					<div class="flex-col-4 bns">
                        <div class="flex-row flex-direction-column">
                            <div>票据真实</div>
                            <div class="cd43c33 realBill">--</div>
                        </div>
                    </div>
                    <div class="flex-col-4 org none">
                        <div class="flex-row flex-direction-column">
                            <div>价格真实</div>
                            <div class="cd43c33 Realprice">--</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="flex-col-7 bae0e0e0 bcf9f9f9 mr10 pt20 pb20 ">
                <div class="flex-row w flex-direction-row">
                    <div class="flex-col-6">
                        <div class="flex-row flex-direction-column">
                            <div>订单成交率</div>
                            <div class="cd43c33 singleRate">--</div>
                        </div>
                    </div>
                    <div class="flex-col-4">
                        <div class="flex-row flex-direction-column">
                            <div>服务态度</div>
                            <div class="c049e5b service">--</div>
                        </div>
                    </div>
                    <div class="flex-col-4">
                        <div class="flex-row flex-direction-column">
                            <div>确认效率</div>
                            <div class="cd43c33 speed">--</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--列表-->
        <div class="bbd43c33 mt30 lh30">所有来自<span id="roleStr">机构</span>的评价</div>
            <!--从这里开始复制列表-->
	        <div class="flex-row flex-direction-column c727272 lh30 mt20" id="content">
	        </div>
            <!--到这里结束复制列表-->
        	<div id="pager"></div>
	    </div>
</div>
</body>
</div>

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="COMMENTS">
{{#each list}}
	<div class="flex-row flex-direction-column bae0e0e0 bcf9f9f9 pl20 pr20 pt20 pb20 mb20">
	    <div class="flex-row flex-direction-row flex-alignItems-start">
	        <div class="flex-col-2">评价内容</div>
	        <div class="flex-col-10 flex-wrap content">{{content}}</div>
	    </div>
	    <div class="flex-row flex-direction-row">
	        <div class="flex-col-2">评价联系</div>
	        <div class="flex-col-10 flex-wrap mobile">{{encryptMobile membermobile}}</div>
	    </div>
	    <div class="flex-row flex-direction-row mt10">
	        <div class="flex-col-2">评价时间</div>
	        <div class="flex-col-10 flex-wrap createTime">{{createtime}}</div>
	    </div>
	</div>
{{/each}}
</script>
<script type="text/javascript">
var memberId = '${member.id}';
/**
* 加密手机号
*/
Handlebars.registerHelper('encryptMobile', function(mobile, options) {
    return hideMobile(mobile);
});
$(document).ready(function() {
	LoadGrade();
	LoadContent();
    /*选择tab*/
    $("input[name='commentsTab']").change(function () {
        $(this).parents("ul").children("li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
        $(this).parents("ul").children("li").removeClass("bbd43c33");
        $(this).parents("ul").children("li").children("label").removeClass("f20");
        if ($(this).attr("checked") == "checked") {
        	$(this).parent("li").addClass("bbd43c33");
            $(this).parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
        	var role = $("input[name='commentsTab']:checked").val();
        	console.log(role);
        	if(role == "1"){//查看机构对企业的评分
        		$("#roleStr").text("机构");
        		$(".org").addClass("none");
        		$(".bns").removeClass("none");
        	}else if(role == "0"){//查看企业对机构评分
        		$("#roleStr").text("企业");
        		$(".bns").addClass("none");
        		$(".org").removeClass("none");
        	}
        	LoadGrade();
        	LoadContent();
        }
    })
})
/**
 * 加载评分
 */
function LoadGrade(){
	var role = $("input[name='commentsTab']:checked").val();
	var params = {
		memberId:memberId,
		role:role
	};
	console.log(params);
	var url = '${bootAppPath}/comments/get';
	var res = bootPost(url,params);
	if(res != null){
		console.log(res);
		if (res.data.response == "success") {
            var thisData = res.data.data;
            var singleRate = (thisData.singleRate*100).toFixed(2)+'%';//订单成交率
            var speed = thisData.speed != '--'?thisData.speed.toFixed(2):thisData.speed//确认效率
           	var service = thisData.service != '--'?thisData.service.toFixed(2):thisData.service;//服务态度
            $(".singleRate").text(singleRate);
            $(".speed").text(speed);
            $(".service").text(service);
            
           	if(role == 1){//机构对企业的评分
           		var price = thisData.price !='--'? thisData.price.toFixed(2):thisData.price;//票据真实
                var t = thisData.endorseTime;
                var endorseTime = Math.floor(t/60)+"分"+(t%60/100).toFixed(2).slice(-2)+'秒';//背书时间
                $(".price").text(price);
                $(".endorseTime").text(endorseTime);
           	}else if(role == 0){//企业对机构的评分
           		var t = thisData.advanceTime;
           		var advanceTime = Math.floor(t/60)+"分"+(t%60/100).toFixed(2).slice(-2)+'秒';//打款速度
                var priceDurative = ((thisData.priceDurative)*100).toFixed(0)+'%';//历史报价持续性
                var realBill = thisData.price !='--'? thisData.price.toFixed(2):thisData.price;//价格真实
                $(".advanceTime").text(advanceTime);
                $(".priceDurative").text(priceDurative);
                $(".realBill").text(realBill);
           	}
		}
	}
}
/**
 * 加载评论
 */
function LoadContent(){
	var role = $("input[name='commentsTab']:checked").val();
	var params = {
		memberId:memberId,
		role:role
	};
	var pageIndex = 1;//分页
	$("#content").html("");
	$("#pager").html("");
	
    $("#pager").sjAjaxPager({
        url: "${bootAppPath}/comments/get/discinfo",
        pageIndex:pageIndex,
        pageSize:2,
        myTotalType:"BOOT",
        searchParam: params,
        beforeSend: function () {
        	
        },success: function (res) {
        	console.log(res.data.data);
        	var source = $("#COMMENTS").html();
            var template = Handlebars.compile(source);
            var html = template(res.data.data);
            $("#content").html(html);
        },complete: function(){
        },error:function(data){
        	if(data.state == 403){
        		bootLogin(url,params,false,true)
        	}else{
        		alert("出现异常");
        	}
        }
    });
}
</script>
[@main.footer/]
[/@main.body]