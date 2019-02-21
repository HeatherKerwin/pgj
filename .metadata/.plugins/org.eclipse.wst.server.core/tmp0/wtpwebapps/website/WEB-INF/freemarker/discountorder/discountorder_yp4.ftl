[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='2'/]

<!--确认下单-->
<div class="mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态-->
   	<input type="hidden" id="id"  value="${id}" />
    <div class="w654 bc f12 tc">
        <div class="fl">
            <img src="${image_url}/website/discount/state12.png" width="165" height="30">
            <p class="c3366cc mt10">确认贴现订单</p>
        </div> 
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state22.png" width="165" height="30">
            <p class="cbac6fd mt10">付押金保障交易</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state32.png" width="165" height="30">
            <p class="ccccccc mt10">交易跟踪</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state43.png" width="165" height="30">
            <p class="ccccccc mt10">评价机构</p>
        </div>
    </div>
    <div class="cb"></div>
    <!--选择地址-->
    <div id="content">

    </div>
</div>
  [@main.right /]
<div class="w h pf bc05 zi10 top none" id="window">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
        	<!-- 编辑评价-->
            <div class="pr t40 pl25 pr25 none" id="evaluateDiv1">
                <div class="w700">
                	<input  type="hidden" id="dcrdId" />
                    <div class="f16 xuxian pb10">
                        <div class="fl c7d7d7d">交易机构：</div>
                        <div class="fl" id="jyjg"></div>
                        <div class="cb"></div>
                    </div>
                    <div class="pb10 xuxian">
                        <div class="mt10 c7d7d7d">订单简表：</div>
                        <ul class="bae0e0e0 bcwhite tc pt10 pb10 mt10">
                            <li class="fl w232 Rright">
                                <div class="f14 c717583"></div>
                                <div class="w f16 mt10" id="bns_no"></div>
                            </li>
                            <li class="fl w120 Rright">
                                <div class="f14 c717583">总金额</div>
                                <div class="w f16 mt10" id="allmoney"></div>
                            </li>
                            <li class="fl w75 Rright">
                                <div class="f14 c717583">背书</div>
                                <div class="w f16 mt10" id="shou"></div>
                            </li>
                            <li class="fl w180 Rright">
                                <div class="f14 c717583">付款行</div>
                                <div class="w f16 mt10" id="bank"></div>
                            </li>
                            <li class="fl w90">
                                <div class="f14 c717583">保证金</div>
                                <div class="w f16 mt10" id="deposit"></div>
                            </li>
                            <div class="cb"></div>
                        </ul>
                    </div>
                    <div class="pb10">
                        <div class="mt10 c7d7d7d">发表评价：</div>
                        <textarea style="resize:none;" maxlength="140" class="fl ti8 w700 h100 bae0e0e0 bcwhite mt12 f14 pt10" name="content" id="contents" placeholder="请畅所欲言吧......"></textarea>
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
                    <a class="fl f18 c3366cc ml25">交易跟踪</a>
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
<script type="text/x-handlebars-template" id="DISCOUNTRECORD">
{{#each results}}
<!--选择地址-->
<div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0">
    待评价
</div>
<div class="fb lh180 cblack mt30 bbe0e0e0">
    贴现地址
</div>
<div class="mt30 c2d2d2d">
    <div class="fl">{{address}}（{{dname}}） </div>
    <div class="fl c808080 ml20">{{membermobile}} </div>
</div>
<div class="cb"></div>
<div class="fb lh180 cblack mt30 bbe0e0e0">
    订单信息
</div>
<div class="mt30 bte0e0e0 ble0e0e0 bre0e0e0 mb20">
    <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
        <div class="fl w232">银票订单号</div>
        <div class="fl w155">总金额</div>
        <div class="fl w105">背书</div>
        <div class="fl w212">付款行</div>
        <div class="fl w122">保证金</div>
        <div class="fl w312">票据特征</div>
    </div>
    <div class="h190 bcwhite pt10 pb25 bbe0e0e0">
        <div class="fl w827 h190 pl10 Rright">
            <div class="bbe0e0e0 tc f16 h50 lh40">
                <div class="fl w220 Rright h40">
                    {{ordernumber}}
                </div>
                <div class="fl w155 Rright h40"><span style="color:red">{{allmoney}}</span>万元</div>
                <div class="fl w105 Rright h40">{{endorse}}手</div>
                <div class="fl w210 Rright h40 ti8 dian">{{bank}}</div>
                <div class="fl w120 h50 h40"><span style="color:red">{{deposit}}</span>元</div>
            </div>
            <div class="cb"></div>
            <div class="tc f16 pt25 h90">
                <div class="fl w220 Rright h90 tl">
                    <div class="ml30">贴现日期：<span>{{formatDate begintime}}</span></div>
                    <div class="ml30 mt20">到期日期：<span>{{formatDate endtime}}</span></div>
                    <div class="ml30 mt20">计息天数：<span class="jxts">{{jxts}}</span></div>
                </div>
                <div class="fl pl40 h90">
                    <div class="fl w50 h90">备注：</div>
                    <div class="fl w460 h90 tl lh20">{{memberother}}
                    </div>
                </div>
            </div>
        </div>
        <div class="fl w300">
            <div class="tc lh35">
                <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">{{toType type1}}</span>
                <span class="{{toTicket flaw_ticket}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">瑕疵票</span>
                <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">{{toType2 type2}}</span>
                <span class="{{toDoor need_todoor}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">需要上门</span>
            </div>
        </div>
    </div>
    <div class="{{hasOrg org_id}} tc pl10 pr10 pt25 bbe0e0e0">
        <div class="pb25 xuxian">
            <div class="fl w480 Rright">
                <div class="fl w201 tl">
                    <div class="f14 c717583">交易机构：</div>
                    <div class="f16 lh20 mt3">{{company}}</div>
                </div>
                <div class="fl f14 c717583 tl ml30">
                    <div class="">贴现价格：<span class="f16 c2d2d2d">{{toPrice rate rate1 rate2 way type1}}</span></div>
                    <div class="mt25">参考贴现利息：<span class="f16 c2d2d2d">{{txlx}}元</span></div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="fl ml20 {{toDoor need_todoor}}">
                <div class="fl">
                    <i class="fl w9 h14 position"></i>
                    <div class="fl ml8">距离您<span class="jl">{{distance}}</span>公里</div>
                </div>
                <div class="fl ml80 tl">
                    <div class="c717583 f14">上门费用：</div>
                    <div class="mt10"><span style="color:red">{{todoor_price}}</span>元</div>
                </div>
                <div class="fl ml80 tl">
                    <div class="c717583 f14">多久能上门：</div>
                    <div class="mt10">{{toDate todoor_time}}</div>
                </div>
            </div>
            <div class="cb"></div>
        </div>
        <div class="pb25 mt25">
            <div class="fl w480 Rright mr20">
                <div class="fl w144 f14 c717583 tl">
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
</div>
<div class="cb"></div>
</div>
<!--票面-->
<div class="bae0e0e0 mt20 pb25 {{toPicpath has_ticket}}">
    <div class="pl10 h50 lh50 c717583 bcf9f9f9 bbe0e0e0">票面</div>
    <img src="${imagePath}{{picpath}}" class="w860 h230 bc mt25 ml143" >
</div>
<div class="cb"></div>
<div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20">
	<a href="#" data-id="{{id}}" data-orderflag="{{orderflag}}" class="fr cfc4d42 ba2_fc4d42 cp br3 dsb w166 h45 lh45 mr40 qpj">去评价</a>
</div>
<div class="cb"></div>
<input type="hidden" id="optassess" value="{{commentId}}">
{{/each}}
</script>

<script type="text/javascript">
	Handlebars.registerHelper('toPhone',function(phone,options){
		return hideMobile(phone);
	});
	Handlebars.registerHelper('toDate', function(todoor_time,options) {
	   if(todoor_time == 0){
		   return "2小时以内";
	   }else if(todoor_time == 1){
		   return "4小时以内";
	   }else if(todoor_time == 2){
		   return "6小时以内";
	   }else if(todoor_time == 3){
		   return "8小时以内";
	   }else if(todoor_time == 4){
		   return "8小时以上";
	   }
	});
	Handlebars.registerHelper('toPicpath', function(hasTicket,options) {
	   if(hasTicket==1){
		   return "none";
	   }
	});
	Handlebars.registerHelper('toSml', function(sml,options) {
	    if(sml==null || sml ==""){
	        return "--";
	    }else {
	        return sml+"%";
	    }
	});
	Handlebars.registerHelper('toShow', function(orderflag,options) {
	    if(orderflag ==1){
	        return "none";
	    }
	});
	Handlebars.registerHelper('toEnum', function(orderflag,deposit_state,com,options) {
	    if(orderflag!=null && orderflag==1 && deposit_state==0){
	        return "待支付";
	    }else if((orderflag==1 || orderflag==4)  && deposit_state==2  ){
	        return "交易中";
	    }else if(orderflag == 3 && com == null){
	    	return "待评价";
	    }else if(orderflag!=null && orderflag==3){
	        return "已完成";
	    }else if(orderflag==0){
	    	return "无效订单";
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
	
	/* 
	 * 格式化银行
	 */
	Handlebars.registerHelper('toType2', function(num, options) {
	    if(num!=null){
	    	return getBank(num);
	    }
	});
	
	Handlebars.registerHelper('hasOrg', function(num, options) {
	    if(num==null){
	        return "none";
	    }
	});
	Handlebars.registerHelper('toPrice', function(rate,rate1,rate2,way,type1, options) {
	    return getPriceYp(rate,rate1,rate2,way,type1);
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
</script>

<script type="text/javascript">
	//页面初始化加载数据
	var xqid = $("#id").val();
	$.ajax({
		type: "POST",
	 	url: "${basePath}/discountorder/list",
	 	data: {xqid:xqid},
	 	dataType:"json",
	 	success:function(data){
	 		if(data.response == "success"){
	 			$("#content").html("");
	 			var source = $("#DISCOUNTRECORD").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data);
	            $("#content").html(html);
	            
	        	 $(".qpj").live("click",qpj);
	 		}
		}
	 });	
	 
	 $(".redClose").click(function(){
	    $("#window").addClass("none");
	    $("#reasonDiv").addClass("none");
	    $("#finishDiv").addClass("none");
	    $("#evaluateDiv1").addClass("none");
	    $("#evaluateDiv2").addClass("none");
	 });
	 
	 function qpj(){
		 if($("#optassess").val()>0){
			 alert("页面已经过期");
			 location.href = "${basePath}/discountorder/discount?ym=yp";
			 return ;
		 }
        var id = $(this).attr("data-id");
        $.ajax({
			type: "POST",
	     	url: "${basePath}/discountrecord/get",
	     	data: {id:id},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
					$("#jyjg").text(data.data.company);
					$("#bns_no").text(data.data.ordernumber);
					$("#allmoney").text(data.data.allmoney+"万元");
					$("#shou").text(data.data.endorse+"手");
					$("#bank").text(data.data.bank);
					$("#deposit").text(data.data.deposit+"元");
					$("#dcrdId").val(data.data.id);
	     		}else{
	     			alert(data.msg)
	     		}
	    	}
		 });
        $("#window").removeClass("none");
        $("#evaluateDiv1").removeClass("none");
	}
	 
	//   确认评价显示隐藏
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
    	}content
    	var dcrdId = $("#dcrdId").val();
    	$("#evaluate2").attr("disabled","disabled");//按钮禁用
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountorder/comments/save",
	     	data: {dcrdId:dcrdId,isToDoor:isToDoor,price:price,service:service,speed:speed,
	     		content:content,type:0},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			$("#window").removeClass("none");
	     	        $("#evaluateDiv1").addClass("none");
	     	        $("#evaluateDiv3").removeClass("none");
	     	        csh();
	     		}else{
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
        location.href = "${basePath}/discountorder/discount?ym=yp";
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
