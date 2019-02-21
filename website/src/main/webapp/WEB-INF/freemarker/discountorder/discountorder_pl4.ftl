[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='2'/]

<!--确认下单-->
<div class="mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态-->
    <div class="w491 bc f12 tc">
    	<input type="hidden" value="${id}" id="id">
        <div class="fl">
            <img src="${image_url}/website/discount/state12.png" width="165" height="30">
            <p class="cbac6fd mt10">确认贴现订单</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state22.png" width="165" height="30">
            <p class="cbac6fd mt10">交易跟踪</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state33.png" width="165" height="30">
            <p class="c3366cc mt10">评价机构</p>
        </div>
    </div>
    <div class="cb"></div>
    <div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0">
        待评价
    </div>
	<div id="content">
	
	</div>
</div>
  [@main.right /]
<!--理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="window">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <!-- 编辑评价-->
            <div class="pr t25 pl25 pr25 none" id="evaluateDiv1">
                <div class="w700">
                	<input  type="hidden" id="dcrdId" />
                    <div class="f16 xuxian pb10">
                        <div class="fl c7d7d7d">交易机构：</div>
                        <div class="fl" id="pjcompany"></div>
                        <div class="cb"></div>
                    </div>
                    <div class="pb10 xuxian">
                        <div class="mt10 c7d7d7d">订单简表：</div>
                        <ul class="bae0e0e0 bcwhite tc pt10 pb10 mt10">
                            <li class="fl w232  Rright">
		                        <div class="f14  c717583">批量订单号</div>
		                        <div class="w f16 lh40 mt8 qrno" id="pjno"></div>
		                    </li>
		                    <li class="fl w120  Rright">
		                        <div class="f14  c717583">总金额</div>
		                        <div class="w f16 lh40 mt8" id="pjallmoney"></div>
		                    </li>
		                    <li class="fl w75  Rright">
		                        <div class="f14  c717583">张数</div>
		                        <div class="w f16 lh40 mt8" id="pjnum"></div>
		                    </li>
		                    <li class="fl w270 ">
		                        <div class="f14  c717583">天数</div>
		                        <div class="f16 lh20 mt8">最短<span class="pjduan"></span>天</div>
		                        <div class="f16 lh20">最长<span class="pjchang"></span>天</div>
		                    </li>
		                    <input id="dcrdId" type="hidden" />
                            <div class="cb"></div>
                        </ul>
                    </div>
                    <div class="pb10">
                        <div class="mt10 c7d7d7d">发表评价：</div>
                        <textarea style="resize:none" maxlength="140" class="fl ti8 w700 h100 bae0e0e0 bcwhite mt12 f14 pt10" name="" id="contents" placeholder="请畅所欲言吧......"></textarea>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">是否上门</div>
                        <ul class="fl lh27 ml30">
                            <li class="fl mr20 h21"><input type="radio" id="yes" class="fl radio1 w21 h21" name="isToDoor" value="1" checked="checked"><label class="fl ml10" for="yes">是</label></li>
                            <li class="fl h21"><input type="radio" id="no" class="fl radio1 w21 h21" name="isToDoor" value="0"><label class="fl ml10" for="no">否</label></li>
                        </ul>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">价格真实</div>
                        <div class="fl ml30">
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="price" value="1"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="price" value="2"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="price" value="3"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="price" value="4"/>
                            </div>
                            <div class="fl w25 h24 star2 estimate">
                                <input type="radio" name="price" value="5"/>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">服务态度</div>
                        <div class="fl ml30">
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="service" value="1"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="service" value="2"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="service" value="3"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="service" value="4"/>
                            </div>
                            <div class="fl w25 h24 star2 estimate">
                                <input type="radio" name="service" value="5"/>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">打款速度</div>
                        <div class="fl ml30">
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="speed" value="1"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="speed" value="2"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="speed" value="3"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="speed" value="4"/>
                            </div>
                            <div class="fl w25 h24 star2 estimate">
                                <input type="radio" name="speed" value="5"/>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                </div>
                <input type="button" class="w268 h45 lh45 cwhite br5 b0 bcfc4d42 bc mt25 ml243 f18" value="发表评价" id="evaluate2">
            </div>
            <!-- 评价成功-->
            <div class="pr t170 none tc none" id="evaluateDiv3">
                <div class="f24 fb">您已评论成功， 感谢您使用票管家贴现</div>
                <div class="f24 mt30 lh24">
                    <div class="fl f18 ml160">您可以进行</div>
                    <a href="${basePath}/discountrecord/discount?ym=pl" class="fl f18 c3366cc ml25">再下一单</a>
                </div>
                <div class="w268 bc mt65">
                    <input type="button" class="w268 h45 lh45 cwhite br5 b0 bcfc4d42 bc mt25 f18" value="暂时不需要"  id="evaluate4">
                </div>
            </div>
        </div>

    </div>
</div>

[@main.footer/]
[/@main.body]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="DISCOUNTRECORD">
{{#each results}}
			<div class="fb lh180 cblack mt30 bbe0e0e0">
		       	 贴现地址
		    </div>
		    <div class="mt30 c2d2d2d">
		        <div class="fl">{{address}}（{{member_name}}） </div>
		        <div class="fl c808080 ml20">{{member_mobile}}</div>
		    </div>
		    <div class="cb"></div>
		    <div class="fb lh180 cblack mt30 bbe0e0e0">
		        订单信息
		    </div>
			<div class="mt30 bte0e0e0 ble0e0e0 bre0e0e0 mb20">
	   			 <div class="f14">
	        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
	            <div class="fl w220">批量订单号</div>
	            <div class="fl w210">票据总金额</div>
	            <div class="fl w100">票据总数量</div>
	            <div class="fl w125">票据到期天数</div>
	            <div class="fl w300">票据特征</div>
	        </div>
	        <div class="cb"></div>
	        <div class="h265 bcwhite bbe0e0e0 pt25 pb25">
	            <div class="fl w655 Rright">
	                <div class="bbe0e0e0 tc f16 h90 pb25">
	                    <div class="fl w204 Rright h40 tl c3366cc f18 fb xzym" data-no="{{no}}" data-orderflag="{{orderflag}}" data-commentId="{{commentId}}">
                               {{no}}
                           </div>
	                    <div class="fl w210 Rright h90 lh30">
	                        <div class=""><span style="color:red">{{allmoney}}</span>万元</div>
	                        <div class="">票面最小金额为<span style="color:red">{{min_money}}</span>万元</div>
	                        <div class="">票面最大金额为<span style="color:red">{{max_money}}</span>万元</div>
	                    </div>
	                    <div class="fl w100 Rright h90 lh30"><span style="color:red">{{amount}}</span>张</div>
	                    <div class="fl w125 h90 lh30">
	                        <div class="">最短<span style="color:red">{{min_expiry_day}}</span>天</div>
	                        <div class="">最长<span style="color:red">{{max_expiry_day}}</span>天</div>
	                    </div>
	                </div>
	                <div class="cb"></div>
	                <div class="tc f16 pt25 h90">
	                    <div class="fl w210 Rright h110 tl ml10">
	                        <div class="ti8">包含承兑行：</div>
	                        <ul class="ti8 mt16 lh35 lis" >
	                        </ul>
	                    </div>
	                    <div class="fl pl20 h90">
	                        <div class="fl w50 h90">备注：</div>
	                        <div class="fl w345 h90 tl lh20">{{remarks}}
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="fl w300">
	                <div class="tc lh35">
	                    <a href="#" class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">{{toType type1}}</a>
	                    <a href="#" class="{{toTicket flaw_ticket}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">瑕疵票</a>
	                    <a href="#" class="{{toDoor need_todoor}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">需要上门</a>
	                </div>
	            </div>
	        </div>
	        <div class="cb"></div>
	        <div class="h65 lh65 ti8 ml10 mr10 xuxian">
	            订单有效期至：<span>{{formatDate endtime}}</span>
	        </div>
	        <div class="cb"></div>
	        <div class="w f14 c717583 mt25 pb25 ml10 mr10 xuxian {{hasOrg org_id}}">
	            推荐机构：<span class="f16 c2d2d2d">{{company}}</span>
	        </div>
	        <div class="pb25 bbe0e0e0 mt25 {{hasOrg org_id}}">
	            <div class="fl w440 Rright mr20">
	                <div class="fl w144 f14 c717583 tl ml10">
	                    联系人：<span class="f16 c2d2d2d">{{name}}</span>
	                </div>
	                <div class="fl f14 c717583 tl">
	                    <div class="ti8">联系方式：<span class="f16 c2d2d2d">{{toPhone phone}}</span></div>
	                    <div class="f12 mt10 c2d2d2d">（联系贴现机构时请说是在票据管家平台上看到的）</div>
	                </div>
	                <div class="cb"></div>
	            </div>
	            <div class="fl">
	                <div class="fl">评价：</div>
	                <div class="fl w420 tc">
	                    <div class="fl w105">
	                        <div class="c717583 f14">上门率</div>
	                        <div class="mt10"><span style="color:red">{{pjdoor}}</span></div>
	                    </div>
	                    <div class="fl w105">
	                        <div class="c717583 f14">价格真实</div>
	                        <div class="mt10"><span style="color:red">{{pjprice}}</span></div>
	                    </div>
	                    <div class="fl w105">
	                        <div class="c717583 f14">服务态度</div>
	                        <div class="mt10"><span style="color:red">{{pjservice}}</span></div>
	                    </div>
	                    <div class="fl w105">
	                        <div class="c717583 f14">打款速度</div>
	                        <div class="mt10"><span style="color:red">{{pjspeed}}</span></div>
	                    </div>
	                </div>
	            </div>
	            <div class="cb"></div>
	        </div>
	        <div class="w h70 bbe0e0e0 bcf9f9f9 tc f18 pt20">
	            <a href="#" data-no="{{no}}" class="fr cd43c33 ba2_fc4d42 br3 dsb w166 h45 lh45 mr40 evaluate1" id="evaluate1">去评价</a>
	        </div>
	    </div>
	</div>
	<input type="hidden" id="optassess" value="{{commentId}}">
{{/each}}
</script>

<script type="text/javascript">
	Handlebars.registerHelper('toPhone',function(phone,options){
		return hideMobile(phone);
	});
	Handlebars.registerHelper('toSml', function(sml,options) {
	    if(sml==null || sml ==""){
	        return "--";
	    }else {
	        return sml+"%";
	    }
	});
	Handlebars.registerHelper('toShow', function(orderflag,com,num,options) {
		 if(1 == orderflag){
	        if(num ==1 || num == 2){
	            return "none";
	        }
	    }else if(2 == orderflag){
	        if(num == 1 ){
	            return "none";
	        }
	    }else if(3 == orderflag){
	        if(num == 1  && com != null){//待评论
	            return "none";
	        }else if(num != 1){
	        	return "none";
	        }
	    }else if(0 == orderflag || -2 == orderflag || -1 == orderflag){
	    	return "none";
	    }
	    return null;
	    
	});
	Handlebars.registerHelper('toEnum', function(orderflag,com,options) {
		if(com==null && orderflag==3){
	        return "待评价";
	    }else{
	        return getBnsStatePL(orderflag,false);
	    }
	});
	Handlebars.registerHelper('formatDate', function(num, options) {
	    if(num!=null){
	        num = num.replace(/-/g, "/");
	        var d = new Date(num);
	        return d.format('yyyy-MM-dd');
	    }else{
	        return "--";
	    }
	});
	Handlebars.registerHelper('toType', function(num, options) {
	    if(num==1){
	        return "纸票";
	    }else{
	        return "电票";
	    }
	});
	
	Handlebars.registerHelper('toPrice', function(rate,rate1,rate2,way,type1, options) {
	    return getPriceYp(rate,rate1,rate2,way,type1);
	});

	/* 
	 * 格式化上门需求
	 */
	Handlebars.registerHelper('toDoor', function(num, options) {
	    if(num!=null){
	    	if(num==0){
	    		return "none";
	    	}
	    }
	});
	
	/* 
	 * 格式化瑕疵票
	 */
	Handlebars.registerHelper('toTicket', function(num, options) {
	    if(num!=null){
	    	if(num==1){
	    		return "none";
	    	}
	    }
	});
	
	Handlebars.registerHelper('hasOrg', function(num, options) {
	    if(num==null){
	        return "none";
	    }
	});
</script>

<script type="text/javascript">

	function csh(){
		var xqid = $("#id").val();
		var data = {};
		data.xqid=xqid;
		var pageIndex = 1;//分页
		$('#pager').sjAjaxPager({
			
		    url: "${basePath}/discountorderpl/list",
		    searchParam:data,
		    beforeSend: function () {
		    	
		    },success: function (data) {
		    	var source = $("#DISCOUNTRECORD").html();
		        var template = Handlebars.compile(source);
		        var html = template(data.data);
		        $("#content").html(html);
		        
		        $(".lis").each(function(i){
		        	$(this).html(getType2pl(data.data.results[i].type2,true));
		        })
		        
		        $(".evaluate1").live("click",qpj);
		        
		    },complete: function(){
		    },error:function(){
		    	
		    }
		});
	}
	
	$(function(){
		csh();
	});	

	function qpj(){
		 if($("#optassess").val()>0){
			 alert("页面已经过期");
			 location.href = "${basePath}/discountorder/discount?ym=pl";
			 return ;
		 }
		$("#window").removeClass("none");
	    $("#evaluateDiv1").removeClass("none");
	    var id = $(this).attr("data-no");
	    $.ajax({
			type: "POST",
	     	url: "${basePath}/discountorderpl/get",
	     	data: {id:id},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
					$("#pjcompany").text(data.data.company);
					$("#pjno").text(data.data.no);
					$("#pjallmoney").text(data.data.allmoney+"万元");
					$("#pjnum").text(data.data.amount+"张");
					$(".pjchang").text(data.data.max_expiry_day);
					$(".pjduan").text(data.data.min_expiry_day);
					$("#dcrdId").val(data.data.no);
	     		}else{
	     			alert(data.msg)
	     		}
	    	}
		 });
	}

</script>

<script type="text/javascript">
    //    去编辑评价显示隐藏
    $("#evaluate1").click(function(){
        $("#window").removeClass("none");
        $("#evaluateDiv1").removeClass("none");
    });
    //    确认评价显示隐藏
    $("#evaluate2").click(function(){
    	if($("#evaluate2").attr("disabled")){
			return;
		}
    	var isToDoor ;
    	$("input[name='isToDoor']").each(function(){
    		if($(this).attr("checked") == "checked"){
    			isToDoor = $(this).val();
    		}
    	})
    	var price ;
    	$("input[name='price']").each(function(){
    		if($(this).attr("checked") == "checked"){
    			price = $(this).val();
    		}
    	})
    	var service ;
    	$("input[name='service']").each(function(){
    		if($(this).attr("checked") == "checked"){
    			service = $(this).val();
    		}
    	})
    	var speed ;
    	$("input[name='speed']").each(function(){
    		if($(this).attr("checked") == "checked"){
    			speed = $(this).val();
    		}
    	})
    	var content = $("#contents").val();
    	if($.trim(content).length==0 || content == null || content == ""){
    		alert("请填写评论后在提交");
    		return ;
    	}
    	var dcrdId = $("#dcrdId").val();
    	$("#evaluate2").attr("disabled","disabled");//按钮禁用
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountorderpl/comments/save",
	     	data: {no:dcrdId,isToDoor:isToDoor,price:price,service:service,speed:speed,
	     		content:content,type:2},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			$("#evaluate2").removeAttr("disabled");//按钮启用
	     			$("#window").removeClass("none");
	     	        $("#evaluateDiv1").addClass("none");
	     	        $("#evaluateDiv3").removeClass("none");
	     	        location.href = "${basePath}/discountorder/discount?ym=pl";
	     		}else{
	     			$("#evaluate2").removeAttr("disabled");//按钮启用
	     			alert(data.msg)
	     		}
	    	}
		 })	
    });
    //    评价成功显示隐藏
    $("#evaluate3").click(function(){
        $("#window").removeClass("none");
        $("#evaluateDiv2").addClass("none");
        $("#evaluateDiv3").removeClass("none");
    });
    $("#evaluate4").click(function(){
        $("#window").addClass("none");
        $("#evaluateDiv3").addClass("none");
    });
    $(".redClose").click(function(){
        $("#window").addClass("none");
        $("#orgDiv").addClass("none");
        $("#evaluateDiv1").addClass("none");
        $("#evaluateDiv2").addClass("none");
    });

    //    评价星级
    $(".estimate").click(function(){
        var o = $(this);
        o.children().attr('checked', 'checked');
        with(o){
            removeClass("star2").addClass("star1");
            nextAll().removeClass("star1").addClass("star2");
            prevAll().removeClass("star2").addClass("star1");
        }
    });
</script>
