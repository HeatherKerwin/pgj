[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
<style>
    /*红包右侧锯齿边框*/
    .rbDashed{
        position:relative;
        width:30%;
        height:100%;
        display:inline-block;
        border-radius: 3px 0 0 3px;
    }
    .rbDashed:after{
        content: "";
        position: absolute;
        top: -20px;
        display: block;
        width:10px;
        height: 100%;
        margin-top: 20px;
        background-size: 20px 10px;
    }
    .rbDashed:after{
        right: -10px;
        background-position: 100% 15%;
        background-image: linear-gradient(-45deg, #fff 25%, transparent 25%, transparent),
        linear-gradient(-135deg, #fff 25%, transparent 25%, transparent),
        linear-gradient(-45deg, transparent 75%, #fff 75%),
        linear-gradient(-135deg, transparent 75%, #fff 75%);
    }
    /* 可用红包 */
    .rbDashed1:after{background-color: #fc4d42;}
    /* 过期红包 */
    .rbDashed2:after{background-color: #777777;}

	/* 鼠标选择效果  */
    .cpH:hover{
        border: 1px solid #fc4d42;
        box-shadow: 2px 3px 3px #555;
    }
</style>

<!--我的红包-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
    	<div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">我的红包</div>
        </div>
	    <div class="flex w hmin688 flex-direction-column">
	    	<!-- 选择不同状态红包 -->
	    	<ul class="flex-row flex-direction-row h52 f16 c2d2d2d lh50 bae0e0e0 tc bcwhite orderTab">
	            <li class="w166 lh50 bre0e0e0 bbd43c33">
	                <input type="radio" name="redTab" value="1" checked id="redTab1" class="none"><a href="#" class="cd43c33"><label for="redTab1" class="dsb">可用红包</label></a>
	            </li>
	            <li class="w166 lh50 bre0e0e0">
	                <input type="radio" name="redTab" value="2" id="redTab2" class="none"><a href="#" class="c2d2d2d"><label for="redTab2" class="dsb">无效红包</label></a>
	            </li>
	        </ul>
	        
	        <!--无红包-->
	        <div class="flex-row w hmin688 flex-alignItems-center flex-justify-center nocoupon none">
	            <div class="flex-row flex-direction-column">
	                <img src="https://img.utiexian.com/website/PJGJ/redPackets/noRedPackets.png" alt="暂无红包可用" width="140" height="140">
	                <p class="c7d7d7d mt20">您目前没有可用红包</p>
	            </div>
	        </div>
	
	        <!--红包-->
	        <div class="flex-row flex-direction-row flex-wrap pl14 none" id="content"></div>
			<div class="flex-row flex-direction-row-reverse">
				<div id="pager"></div>
			</div>
	    </div>
    </div>
    <div class="cb"></div>
</div>

<script type="text/x-handlebars-template" id="MYCOUPON">
{{#each list}}
	<div class="flex-row flex-direction-row w308 h100 bae0e0e0 cp cpH mr16 mt25 br3">
		<div class="w105 flex-row h flex-direction-column tc bcfc4d42 rbDashed rbDashed1">
			<div class="cwhite f20 mt10">¥<span class="f36 ml3">{{money}}</span></div>
			<div class="flex-row flex-justify-center f14 bcwhite cfc4d42 br3 lh22 mt10 w80">满<span>20</span>元可用</div>
		</div>
		<div class="flex-row h flex-direction-column pl20">
			<div class="mt25">{{toCouponType couponType}}</div>
			<div class="mt10 f12 c7d7d7d"><span>{{formatDate endDate}}</span>到期</div>
		</div>
	</div>
{{/each}}
</script>

<script type="text/x-handlebars-template" id="INVALIDCOUPON">
{{#each list}}
	<div class="flex-row flex-direction-row w308 h100 bae0e0e0 mr16 mt25 pr">
		<div class="w105 flex-row h flex-direction-column tc bc777777 rbDashed rbDashed2">
			<div class="cwhite f20 mt10">¥<span class="f36 ml3">{{money}}</span></div>
			<div class="flex-row flex-justify-center f14 bcwhite br3 lh22 mt10 w80">满<span>20</span>元可用</div>
		</div>
		<div class="flex-row h flex-direction-column pl20">
			<div class="mt25">{{toCouponType couponType}}</div>
			<div class="mt10 f12 c7d7d7d"><span>{{formatDate endDate}}</span>到期</div>
		</div>
		<img src="https://img.utiexian.com/website/PJGJ/redPackets/overdue.png" alt="" width="99" height="73" class="pa t-10 r-10 zi10">
	</div>
{{/each}}
</script>

[@main.right /]
[@main.footer/]
[/@main.body]

<link rel="stylesheet" type="text/css" href="${pluPath}/ajaxPager/page.css"/>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>

<script type="text/javascript">
	/**
	* 红包类型格式化
	*/
	Handlebars.registerHelper('toCouponType', function(couponType,options) {
	    if(couponType=='GENERAL'){
	        return "交易通用红包";
	    }else if(couponType=='DISC'){
	        return "交易红包";
	    }else if(couponType=='DIST'){
	        return "接单红包";
	    }else if(couponType=='INQUIRYREPLY'){
	        return "查询查复红包";
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

	//选择tab
	$("input[name='redTab']").change(function () {
	    $(this).parents("ul").children("li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
	    $(this).parents("ul").children("li").removeClass("bbd43c33");
	    $(this).parents("ul").children("li").children("label").removeClass("f20");
	    if ($(this).attr("checked") == "checked") {
	        $(this).parent("li").addClass("bbd43c33");
	        $(this).parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
	        loadDate();
	    }
	});

	$(function(){
		loadDate();
	});
	
	/**
	*加载红包
	*/
	function loadDate(){
		var coupon = $("input:radio[name='redTab']:checked").val();
		var params = {"memberId":memberId};
		if(coupon == 1){
			params.couponState = "UNUSED";
		}else if(coupon == 2){
			params.couponState = "INVALID";
		}else{
			params.couponState = "UNUSED";
		}
		
		var url = '${bootAppPath}/coupon/page';
		var pageIndex = 1;//分页
		$('#pager').sjAjaxPager({
	        url: url,
	        pageIndex:pageIndex,
	        myTotalType:"BOOT",
	        pageSize:9,
	        searchParam: params,
	        beforeSend: function () {
	        },success: function (data) {
	        	$("#pager").html("");
	        	if(data.data.data.list.length>0){
	        		$("#content").removeClass("none");
	        		$(".nocoupon").addClass("none");
	        		
	        		var source = null;
		    		if(coupon == 1){
		    			source = $("#MYCOUPON").html();
		    		}else if(coupon == 2){
		    			source = $("#INVALIDCOUPON").html();
		    		}else{
		    			source = $("#MYCOUPON").html();
		    		}
		            var template = Handlebars.compile(source);
		            var html = template(data.data.data);
		            $("#content").html(html);
	        	}else{
	        		$(".nocoupon").removeClass("none");
	        		$("#content").addClass("none");
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
</script>