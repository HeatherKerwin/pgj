[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='2'/]

<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left /]
   <!--右侧内容 -->
   <input type="hidden"  id="ddid" value="${id}" />
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 ti10 bcfaf7f7">商票订单</div>
        <!--银票订单-->
        <!--详情-->
        <div class="pl20 pr20 mb50 mt30">
            <div class="mt30 fb f18 lh20 bb3_e0e0e0">
                订单详情
            </div>
            <div class="mt30 bae0e0e0 mb20">
                <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
                    <div class="fl w212">商票订单号</div>
                    <div class="fl w115">总金额</div>
                    <div class="fl w67">背书</div>
                    <div class="fl w250">付款行</div>
                    <div class="fl w308">票据特征</div>
                </div>
                <div class="h190 bcwhite pt10 pb25">
                    <div class="fl h190 pl10 Rright">
                        <div class="bbe0e0e0 tc f16 h50 lh40">
                            <div class="fl w204 Rright h40 tl c3366cc f16" id="ddno">
                                
                            </div>
                            <div class="fl w115 Rright h40" ><span id="ddallmoney" style="color:red"></span>万元</div>
                            <div class="fl w67 Rright h40" id="ddendorse"></div>
                            <div class="fl w170 Rright h40 ti8 dian" id="ddbank"></div>
                        </div>
                        <div class="cb"></div>
                        <div class="tc f16 pt25 h90">
                            <div class="fl w201 Rright h90 tl">
                                <div class="mt20">贴现日期：<span id="ddstart"></span></div>
                                <div class="mt20">到期日期：<span id="ddend"></span></div>
                                <div class="mt20">计息天数：<span id="ddjxts"></span></div>
                            </div>
                            <div class="fl pl20 h90">
                                <div class="fl w50 h90">备注：</div>
                                <div class="fl w345 h90 tl lh20" id="ddremarks">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="fl w300">
                        <div class="tc lh35">
                            <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25 type">电票</span>
                            <span" class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25 need">需要上门</span>
                        </div>
                    </div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="mt30 fb f18 lh20 bb3_e0e0e0">
                <div class="fl">
                    票管家给您推荐的交易机构<span class="f12">（我们会在30分钟内陆续给您推送有报价的机构，您可以不用急着选定机构哦）</span>
                </div>
                <div class="fr f14">剩余时间：<span id="minute_show1" class="f18 cd43c33">30分00秒</span></div>
                <div class="cb"></div>
            </div>
            <div id="content">
            
            </div>
        </div>
        <!---->
    </div>
     <div class="cb"></div> 
</div>
[@main.right /]

<!--理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="window">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <!--所需材料-->
            <div class="pr t40 pr25 none" id="dataDiv">
                <div class="fb f20 ml30">所需材料：</div>
                <div class="lh40 tc f18">
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">贸易合同</div>
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">增值税发票</div>
                    <div class="fl w260 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">盖章（法人章、财务章、公章）</div>
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">法人身份证</div>
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">经办人身份证</div>
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">开户许可证</div>
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">营业执照</div>
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">税务登记证</div>
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">组织机构代码证</div>
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">贷款卡</div>
                    <div class="fl w150 h40 ba2_e0e0e0 br5 ml30 mr20 mt30">保函</div>
                    <div class="cb"></div>
                </div>
                <div class="w260 h40 ba2_e0e0e0 br5 mt30 tc lh40 ml30">以上所选纸质材料的复印件</div>
                <div class="w268 bc h44 mt53">
                    <input type="button" class="w268 h44 lh45 cwhite b0 br5 bce84c3d tc f18" value="确认">
                </div>
            </div>
            <!--确认机构-->
            <div class="pr t40 pl35 pr25 none" id="orgDiv">
                <div class="f20 fb">我确认选定以下机构进行交易：</div>
                <div class="bcw bae0e0e0 mt30 pl10 pr7 bcwhite">
                    <div class="pb25 xuxian">
                        <div class="w h16 lh16 mt25">
                            <div class="fl f14 c717583">推荐机构：</div>
                            <div class="fl f16 " id="jgcompay"></div>
                        </div>
                        <div class="w h16 lh16 mt25">
                            <div class="fl f14 c717583">贴现价格：</div>
                            <div class="fl f16" id="jgprice"></div>
                        </div>
                        <div class="w h16 lh16 mt25">
                            <div class="fl f14 c717583">参考贴现利息：</div>
                            <div class="fl f16" id="jglx"></div>
                        </div>
                    </div>
                    <div class="pb25">
                        <div class="w h16 lh16 mt25">
                            <div class="fl f14 c717583">联系人：</div>
                            <div class="fl f16" id="jgname"></div>
                        </div>
                        <div class="w h16 lh16 mt25">
                            <div class="fl f14 c717583">联系方式：</div>
                            <div class="fl f16" id="jgphone"></div>
                        </div>
                        <div class="w h16 lh16 mt10">（联系贴现机构时请说是在票据管家平台上看到的）</div>
                    </div>
                </div>
                <div class="tc lh44 mt65">
                	<input type="hidden" id="xzjgid" />
                	<input type="hidden" id="xzjgid2" />
                    <input type="button" id="chakanjg" class="w268 h44 bce84c3d br5 b0 cwhite f18" value="不，查看其他机构">
                    <input type="button" id="baocunjg" class="w268 h44 bce84c3d br5 b0 cwhite f18 ml30" value="确认">
                </div>
            </div>
            <div class="pr t170 l160 none tc none" id="orgDiv2">
                <div class="f24 fb tl">您已选定商票贴现机构</div>
                <div class="w f24 mt30 lh24">
                    <div class="fl w96 f18 tl">您可以进行</div>
                    <a href="#" class="fl f18 c3366cc ml25">交易跟踪</a>
                    <div class="cb"></div>
                </div>
                <div class="w f24 mt30 lh24">
                    <div class="fl w96 f18 tl">或者</div>
                    <a href="${basePath}/discountrecord/discount?ym=sp" class="fl f18 c3366cc ml25">再下一单</a>
                    <div class="cb"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/x-handlebars-template" id="DISCOUNTRECORD">
{{#each data}}
		<div class="mt20 bae0e0e0">
                <div class="tc pl10 pr10 pt25 bbe0e0e0">
                    <div class="pb25 xuxian">
                        <div class="fl w440 Rright">
                            <div class="fl w210 tl">
                                <div class="f14 lh20 c717583">
                                    <div class="fl w70">{{showTitle company current recommend}}</div>
                                    <div class="{{toTag jg 1}} fl h20 w60 ba1_00a5f2 c00a5f2 br3 tc">价格低</div>
									<div class="{{toTag pf 2}} fl h20 w60 ba1_00a5f2 c00a5f2 br3 tc">评分高</div>
									<div class="{{toTag jl 3}} fl h20 w60 ba1_00a5f2 c00a5f2 br3 tc">距离近</div>
									<div class="{{toTag zls 4}} fl h20 w60 ba1_00a5f2 c00a5f2 br3 tc">资料少</div>
                                    <div class="cb"></div>
                                </div>
                                <div class="f16 lh20 mt4">{{showCompany company current recommend}}</div>
                            </div>
                            <div class="fl f14 c717583 tl ml30">
                                <div class="">贴现价格：<span class="f16 c2d2d2d">{{toPrice price price1 price2 way type1}}</span></div>
                                <div class="mt25">参考贴现利息：<span class="f16 c2d2d2d">{{txlx}}元</span></div>
                            </div>
                            <div class="cb"></div>
                        </div>
                        <div class="fl ml20 {{toDoor need_todoor}}" >
                            <div class="fl {{toOrg org_id}}">
                                <i class="fl w9 h14 position"></i>
                                <div class="fl ml8 ">距离您<span class="jl">{{toDjl distance}}</span>公里</div>
                            </div>
                            <div class="fl ml80 tl {{toOrg org_id}}">
                                <div class="c717583 f14">上门费用：</div>
                                <div class="mt10"><span style="color:red">{{todoor_price}}</span>元</div>
                            </div>
                            <div class="fl ml80 tl {{toOrg org_id}}">
                                <div class="c717583 f14">多久能上门：</div>
                                <div class="mt10">{{toDate todoor_time}}</div>
                            </div>
                        </div>
                        <div class="cb"></div>
                    </div>
                    <div class="pb25 mt25 xuxian">
                        <div class="fl w440 Rright mr20 {{toNan orderflag org_id commentId}}">
                            <div class="fl w144 f14 c717583 tl">
                                联系人：<span class="f16 c2d2d2d">{{name}}</span>
                            </div>
                            <div class="fl f14 c717583 tl">
                                <div class="ti8">联系方式：<span class="f16 c2d2d2d">{{phone}}</span></div>
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
                    <div class="lh34 mb20">
                        <div class="fl fb mt20">所需资料：</div>
						<div class="bq">
							
						</div>
                        <div class="cb"></div>
                    </div>
                </div>
                <div class="w h70 bcf9f9f9 tc f18 pt20" >
                	<a class=" fr cd43c33 ba2_fc4d42 cp br3 dsb w166 h45 lh45 mr40 xzjg" data-id="{{dcrd_sp_id}}" data-current={{id}}>选这家机构</a>
           		</div>
            </div>
		<div class="cb"></div> 
{{/each}}
</script>

<script>
    //    更多材料
    $("#more").click(function(){
        $("#window").removeClass("none");
        $("#dataDiv").removeClass("none");
    });
    //    确认机构
    $("#organization1").click(function(){
        $("#window").removeClass("none");
        $("#orgDiv1").removeClass("none");
    });
    $("#organization2").click(function(){
        $("#window").removeClass("none");
        $("#orgDiv1").addClass("none");
        $("#orgDiv2").removeClass("none");
    });
//    关闭
    $(".redClose").click(function(){
        $("#window").addClass("none");
        $("#orgDiv1").addClass("none");
        $("#orgDiv2").addClass("none");
        $("#dataDiv").addClass("none");
    });

</script>
[@main.footer/]
[/@main.body]

<script type="text/javascript">
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
	Handlebars.registerHelper('toOrg', function(org_id,options) {
	    if(org_id==null){
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
	Handlebars.registerHelper('toDortid', function(current,options) {
		if(current!=null){
	   		return current.id;
		}	
	});
	Handlebars.registerHelper('toDjl', function(distance,options) {
		return distance.toFixed(2);
	});
	Handlebars.registerHelper('toJgyc', function(orderflag,options) {
		if(orderflag>1){
	   		return "none";
		}	
	});
	Handlebars.registerHelper('toNan', function(orderflag,org,com,options) {
	    if(orderflag != 3 || orderflag != 4){
	        return "none";
	    }else if(orderflag == 3 && com != null){
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
	    if(company!=null && $.trim(company)!=""){
	        return company;
	    }else if(current!=null){
	        return current.company;
	    }else if(recommend!=null){
	        return recommend.company;
	    }
	});
	Handlebars.registerHelper('toTag', function(current,tag,options) {
	    if(tag==1 && !current){
	        return "none";
	    }else if(tag==2 && !current){
	        return "none";
	    }else if(tag==3 && !current){
	        return "none";
	    }else if(tag==4 && !current){
	        return "none";
	    }
	});
	Handlebars.registerHelper('showTitle', function(company,current,recommend, options) {
	    if(company!=null && $.trim(company)!=""){
	        return "选择机构：";
	    }else if(current!=null){
	        return "选择机构：";
	    }else if(recommend!=null){
	        return "推荐机构：";
	    }
	});
	Handlebars.registerHelper('showPhone', function(phone,current,recommend, options) {
	    if(phone!=null && $.trim(phone)!=""){
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

	//页面初始化加载数据
	var xqid = $("#ddid").val();
	$.ajax({
		type: "POST",
	 	url: "${basePath}/discountordersp/list/org",
	 	data: {id:xqid},
	 	dataType:"json",
	 	success:function(data){
	 		if(data.response == "success"){
	 			$("#content").html("");
	 			var source = $("#DISCOUNTRECORD").html();
	            var template = Handlebars.compile(source);
	            var html = template(data);
	            $("#content").html(html);
	            
	            $(".more").live("click",more);
	            $(".xzjg").live("click",xzjg);
	            
	          	$(".bq").each(function(i){
	          		$(this).html(getNeedStuff1(data.data[i].need_stuff));
	          	});
	            
	            $("#ddno").text(data.disc.no);
	            $("#ddallmoney").text(data.disc.allmoney);
	            $("#ddendorse").text(data.disc.endorse);
	            $("#ddbank").text(data.disc.bank);
	            $("#ddremarks").text(data.disc.remarks);
	            $("#ddstart").text(new Date(data.disc.begintime).format('yyyy-MM-dd'));
	            $("#ddend").text(new Date(data.disc.endtime).format('yyyy-MM-dd'));
	            $("#ddjxts").text(data.jxtss);
	            
	            timer(data.timer);
	            
	 		}
		}
	 });
	
	function xzjg(){
		var id = $(this).attr("data-current");
		var id2 = $(this).attr("data-id");
		$("#window").removeClass("none");
	    $("#orgDiv").removeClass("none");
	    
	    $("#xzjgid").val(id);
	    $("#xzjgid2").val(id2);
	    $.ajax({
			type: "POST",
	     	url: "${basePath}/discountordersp/list/org/get",
	     	data: {dtboId:id},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
					$("#jgcompay").text(data.data.company);
					if(data.data.way==0){//月
                        if(data.data.price!=null)$("#jgprice").text("月利率"+data.data.price+"‰");
                        if(data.data.price1!=null)$("#jgprice").text($("#jgprice").text()+"+"+data.data.price1+"元");
                    }else if(data.data.way==2){
                        if(data.data.price!=null)$("#jgprice").text("年利率"+data.data.price+"%");
                        if(data.data.price1!=null)$("#jgprice").text($("#jgprice").text()+"+"+data.data.price1+"元");
                    }else if(data.data.way==1){//每十万
                        $("#jgprice").text("每十万"+data.data.price2+"元");
                    }
					$("#jglx").text(data.data.txlx+"元");
					$("#jgname").text(data.data.name);
					$("#jgphone").text(data.data.phone);
	     		}else{
	     			alert(data.msg)
	     		}
	    	}
		 });
	}
	
	//计时器
    function timer(intDiff){
        var _t = window.setInterval(function(){
            var day=0,
                    hour=0,
                    minute=0,
                    second=0;//时间默认值
            if(intDiff > 0){
                minute = Math.floor(intDiff / 60);
                second = Math.floor(intDiff) - (minute * 60);
            }
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            $('#minute_show1').html(minute+'分'+second+'秒');
            intDiff--;
        }, 1000);
    }

	$("#chakanjg").click(function(){
    	var id = $("#xzjgid2").val();
    	var map = new Map();
		map.put("_PAGE", "discountorder/discountorder_spjg");//必传
		map.put("id", id);
		_OPENPAGE_FORM(map);
    });
	
	$("#baocunjg").click(function(){
    	var id = $("#xzjgid").val();
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountordersp/select/org",
	     	data: {id:id},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			$("#window").addClass("none");
	     	        $("#dataDiv").addClass("none");
	     	        $("#orgDiv").addClass("none");
	     		}else{
	     			alert(data.msg)
	     		}
	    	}
		 })	
    });
	
	function more(){
		 $("#window").removeClass("none");
	     $("#dataDiv").removeClass("none");
	}
</script>
