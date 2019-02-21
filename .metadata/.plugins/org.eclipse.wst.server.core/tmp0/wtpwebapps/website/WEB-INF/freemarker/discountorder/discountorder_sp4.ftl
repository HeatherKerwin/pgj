[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='2'/]

<!--确认下单-->
<div class="mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态-->
   	<input type="hidden"  id="ddid" value="${id}" />
    <div class="w491 bc f12 tc">
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
                                <div class="f14 c717583">商票订单号</div>
                                <div class="w f16 mt10" id="ddh"></div>
                            </li>
                            <li class="fl w120 Rright">
                                <div class="f14 c717583">总金额</div>
                                <div class="w f16 mt10" id="allmoney"></div>
                            </li>
                            <li class="fl w75 Rright">
                                <div class="f14 c717583">背书</div>
                                <div class="w f16 mt10" id="endorse"></div>
                            </li>
                            <li class="fl w270">
                                <div class="f14 c717583">承兑企业</div>
                                <div class="w f16 mt10" id="bank"></div>
                            </li>
                            <div class="cb"></div>
                        </ul>
                    </div>
                    <div class="pb10">
                        <div class="mt10 c7d7d7d">发表评价：</div>
                        <textarea style="resize:none" maxlength="140" class="fl ti8 w700 h100 bae0e0e0 bcwhite mt12 f14 pt10" id="pj" name="" placeholder="请畅所欲言吧......"></textarea>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">是否上门</div>
                        <ul class="fl lh27 ml30">
                            <li class="fl mr20 h21"><input type="radio" id="yes" class="fl radio1 w21 h21" name="isToDoor" value="" checked="checked"><label class="fl ml10" for="yes">是</label></li>
                            <li class="fl h21"><input type="radio" id="no" class="fl radio1 w21 h21" name="isToDoor" value=""><label class="fl ml10" for="no">否</label></li>
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
                <div class="w f24 mt30 lh24">
                    <div class="fl w96 f18 ml160 tl">您可以进行</div>
                    <a href="${basePath}/discountrecord/discount?ym=sp" class="fl f18 c3366cc ml25">再下一单</a>
                    <div class="cb"></div>
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
        <div class="fb lh180 cblack mt30 bbe0e0e0">
			贴现地址
		</div>
		<div class="mt30 c2d2d2d">
		<div class="fl">{{address}}（{{member_name}}） </div>
		<div class="fl c808080 ml20">{{member_mobile}} </div>
		</div>
		<div class="cb"></div>
		<div class="fb lh180 cblack mt30 bbe0e0e0">
		订单信息
		</div>
            <div class="mt30 bte0e0e0 ble0e0e0 bre0e0e0 mb20">
                <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
                    <div class="fl w212">商票订单号</div>
                    <div class="fl w115">总金额</div>
                    <div class="fl w67">背书</div>
                    <div class="fl w250">承兑企业</div>
                    <div class="fl w308">票据特征</div>
                </div>
                <div class="h190 bcwhite bbe0e0e0 pt10 pb25">
                    <div class="fl h190 pl10 Rright">
                        <div class="bbe0e0e0 tc f16 h50 lh40">
                            <div class="fl w204 Rright h40 tl c3366cc f16 xzym"data-id={{no}} data-orderflag="{{orderflag}}" data-commentId="{{commentId}}">
                                {{no}}
                            </div>
                            <div class="fl w115 Rright h40"><span style="color:red">{{allmoney}}</span>万元</div>
                            <div class="fl w67 Rright h40">{{endorse}}手</div>
                            <div class="fl w250 Rright h40 ti8 dian">{{bank}}</div>
                        </div>
                        <div class="cb"></div>
                        <div class="tc f16 pt25 h90">
                            <div class="fl w201 Rright h90 tl">
                                <div class="mt20">贴现日期：{{formatDate begintime}}</div>
                                <div class="mt20">到期日期：{{formatDate endtime}}</div>
								<div class="mt20">计息天数：<span class="jxts">{{jxts}}</span>天</div>
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
                            <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">{{toType type1}}</span>
                            <span class="{{toDoor need_todoor}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">需要上门</span>
                        </div>
                    </div>
                </div>
                <div class="{{hasOrg org_id current recommend}} tc pl10 pr10 pt25 bbe0e0e0">
                    <div class="pb25 xuxian">
                        <div class="fl w440 Rright">
                            <div class="fl w201 tl">
                                <div class="f14 lh20 c717583">
                                    <div class="fl w70">{{showTitle company current recommend}}</div>
                                    <div class="fl h20 w60 ba1_00a5f2 c00a5f2 br3 tc">
										<div class="">{{toTag current recommend 1}}</div>
                    					<div class="">{{toTag current recommend 2}}</div>
                    					<div class="">{{toTag current recommend 3}}</div>
                    					<div class="">{{toTag current recommend 4}}</div>
									</div>
                                    <div class="cb"></div>
                                </div>
									<div class="f16 lh20 mt4">{{showCompany company current recommend}}</div>
                            </div>
                            <div class="fl f14 c717583 tl ml30">
                                <div class="mt25">贴现价格：<span class="f16 c2d2d2d">{{toPrice rate rate1 rate2 way type1}}</span></div>
                                <div class="mt25">参考贴现利息：<span class="f16 c2d2d2d">{{txlx}}元</span></div>
                            </div>
                            <div class="cb"></div>
                        </div>
                        <div class="fl ml20 {{toDoor need_todoor}}">
                            <div class="fl">
                                <i class="fl w9 h14 position"></i>
                                <div class="fl ml8">距离您<span class="jl">{{distances}}</span>公里</div>
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
                    <div class="pb25 mt25 xuxian">
                        <div class="fl w440 Rright mr20">
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
                    <div class="lh34 mt20 mb20">
                        <div class="fl fb">所需资料：</div>
                        <div class="bq">
							
						</div>
                        <div class="cb"></div>
                    </div>
                </div>
				<!--票面-->
				<div class="bae0e0e0 mt20 pb25 {{toPicpath has_ticket}}">
				<div class="pl10 h50 lh50 c717583 bcf9f9f9 bbe0e0e0">票面</div>
					<img src="${imagePath}{{picpath}}" class="w860 h230 bc mt25 ml143" >
				</div>
				<div class="cb"></div>
                <div class="w h70 bbe0e0e0 bcf9f9f9 tc f18 pt20">
                    <a href="javascript:(void);" data-id="{{id}}" class="cp fr cd43c33 ba2_fc4d42 br3 dsb w166 h45 lh45 mr40 evaluate1" id="evaluate1">去评价</a>
                </div>
            </div>
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
	Handlebars.registerHelper('toDortid', function(current,options) {
		if(current!=null){
	   		return current.id;
		}	
	});
	Handlebars.registerHelper('toJgyc', function(orderflag,options) {
		if(orderflag>1){
	   		return "none";
		}	
	});
	Handlebars.registerHelper('toShow', function(orderflag,com,num,options) {
		 if(1 == orderflag){
	        if(num ==2 || num == 3){
	            return "none";
	        }
	    }else if(2 == orderflag){
	        if(num == 2 || num == 4 ){
	            return "none";
	        }
	    }else if(3 == orderflag){
	        if(num == 2  && com != null){//待评论
	            return "none";
	        }else if(num != 2){
	        	return "none";
	        }
	    }else if(0 == orderflag){
	    	return "none";
	    }
	    return null;
	    
	});
	Handlebars.registerHelper('toEnum', function(orderflag,com,options) {
		if(com==null && orderflag==3){
	        return "待评价";
	    }else{
	        return getBnsStateSP(orderflag,false);
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
		if(way==0){//月
            var temp = "";
            if(rate!=null)temp += rate+"‰";
            if(rate1!=null)temp += " + "+rate1+"元";
            return "月利率:"+temp;
        }else if(way==2){
            var temp = "";
            if(rate!=null)temp += rate+"%";
            if(rate1!=null)temp += " + "+rate1+"元";
            return "年利率:"+temp;
        }else if(way==1){//每十万
            return "每十万"+rate2+"元";
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
	
	Handlebars.registerHelper('hasOrg', function(orgId,current,recommend, options) {
	    if(orgId==null && recommend==null && current==null){
	        return "none";
	    }
	});
	
	Handlebars.registerHelper('showCompany', function(company,current,recommend, options) {
	    if(company!=null && $.trim(company).length!=0){
	        return company;
	    }else if(current!=null){
	        return current.company;
	    }else if(recommend!=null){
	        return recommend.company;
	    }
	});
	Handlebars.registerHelper('toTag', function(current,recommend,tag,options) {
	    var temp = null;
	    if(current!=null){
	        temp = current;
	    }else if(recommend!=null){
	        temp = recommend;
	    }
	    if(tag==1 && temp!=null && temp.jg){
	        return "价格低";
	    }else if(tag==2 && temp!=null && temp.pf){
	        return "评分高";
	    }else if(tag==3 && temp!=null && temp.jl){
	        return "距离近";
	    }else if(tag==4 && temp!=null && temp.zls){
	        return "资料少";
	    }
	});
	Handlebars.registerHelper('showTitle', function(company,current,recommend, options) {
	    if(company!=null && $.trim(company).length!=0){
	        return "选择机构：";
	    }else if(current!=null){
	        return "选择机构：";
	    }else if(recommend!=null){
	        return "推荐机构：";
	    }
	});
	Handlebars.registerHelper('showPhone', function(phone,current,recommend, options) {
	    if(phone!=null && $.trim(phone).length!=0){
	        return phone;
	    }else if(current!=null){
	        return current.phone;
	    }else if(recommend!=null){
	        return recommend.phone;
	    }
	});
	Handlebars.registerHelper('showBtn', function(num, options) {
	    if(num!=null){
	        return "更换机构";
	    }else{
	        return "选择机构";
	    }
	});
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
    	var content = $("#pj").val();
    	if($.trim(content).length == 0 || content == null || content == ""){
    		alert("请填写评论后在提交");
    		return ;
    	}
    	var dcrdId = $("#dcrdId").val();
    	$("#evaluate2").attr("disabled","disabled");//按钮禁用
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountorder/comments/save",
	     	data: {dcrdId:dcrdId,isToDoor:isToDoor,price:price,service:service,speed:speed,
	     		content:content,type:1},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			$("#evaluate2").removeAttr("disabled");//按钮启用
	     			$("#window").removeClass("none");
	     	        $("#evaluateDiv1").addClass("none");
	     	        $("#evaluateDiv3").removeClass("none");
	     	        location.href = "${basePath}/discountorder/discount?ym=sp";
	     		}else{
	     			$("#evaluate2").removeAttr("disabled");//按钮启用
	     			alert(data.msg)
	     		}
	    	}
		 })	
    	
        $("#window").removeClass("none");
        $("#evaluateDiv1").addClass("none");
        $("#evaluateDiv3").removeClass("none");
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

<script type="text/javascript">

	//页面初始化加载数据
	var xqid = $("#ddid").val();
	$.ajax({
		type: "POST",
	 	url: "${basePath}/discountordersp/list",
	 	data: {xqid:xqid},
	 	dataType:"json",
	 	success:function(data){
	 		if(data.response == "success"){
	 			$("#content").html("");
	 			var source = $("#DISCOUNTRECORD").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data);
	            $("#content").html(html);
	            
	            $(".bq").each(function(i){
	          		$(this).html(getNeedStuff1(data.data.results[i].need_stuff));
	          	});
	            
	            $(".evaluate1").live("click",qpj);
	 		}
		}
	 });
	
	function qpj(){
		 if($("#optassess").val()>0){
			 alert("页面已经过期");
			 location.href = "${basePath}/discountorder/discount?ym=sp";
			 return ;
		 }
		$("#window").removeClass("none");
        $("#evaluateDiv1").removeClass("none");
        var id = $(this).attr("data-id");
        $.ajax({
			type: "POST",
	     	url: "${basePath}/discountordersp/get",
	     	data: {dcrdId:id},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
					$("#jyjg").text(data.data[0].company);
					$("#ddh").text(data.disc.no);
					$("#allmoney").text(data.disc.allmoney+"万元");
					$("#endorse").text(data.disc.endorse+"手");
					$("#bank").text(data.disc.bank);
					$("#deposit").text(data.disc.deposit+"元");
					$("#dcrdId").val(data.disc.id);
	     		}else{
	     			alert(data.msg)
	     		}
	    	}
		 });
       
	};

</script>
