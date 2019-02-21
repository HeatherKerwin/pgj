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
   		<input type="hidden"  id="ddid" value="${id}" />
        <div class="fl">
            <img src="${image_url}/website/discount/state12.png" width="165" height="30">
            <p class="cbac6fd mt10">确认贴现订单</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state23.png" width="165" height="30">
            <p class="c3366cc mt10">交易跟踪</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state31.png" width="165" height="30">
            <p class="ccccccc mt10">评价机构</p>
        </div>
    </div>
    <div class="cb"></div>
    <div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0">
        已完成
    </div>
	<div id="content">
	
	</div>    
</div>
  [@main.right /]
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
		<div class="bbe0e0e0 mb20">
		    <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
		        <div class="fl w232">商票订单号</div>
		        <div class="fl w155">总金额</div>
		        <div class="fl w105">背书</div>
		        <div class="fl w334">承兑企业</div>
		        <div class="fl w312">票据特征</div>
		    </div>
		    <div class="h210 bcwhite pt25 pb25">
		        <div class="fl w827 Rright">
		            <div class="bbe0e0e0 tc f16 h65 lh40">
		                <div class="fl w222 Rright h40 tl ml10 ti8">
		                    {{no}}
		                </div>
		                <div class="fl w155 Rright h40"><span style="color:red">{{allmoney}}</span>万元</div>
		                <div class="fl w105 Rright h40">{{endorse}}手</div>
		                <div class="fl w334 h40 ti8 dian">{{bank}}</div>
		            </div>
		            <div class="cb"></div>
		            <div class="tc f16 pt25 h120">
		                <div class="fl w220 Rright h120 tl ml10">
		                    <div class="ti8">开票日期：<span>{{formatDate printtime}}</span></div>
		                    <div class="ti8 mt16">贴现日期：<span>{{formatDate begintime}}</span></div>
		                    <div class="ti8 mt16">到期日期：<span>{{formatDate endtime}}</span></div>
		                    <div class="ti8 mt16">计息天数：<span class="jxts">{{jxts}}</span>天</div>
		                </div>
		                <div class="fl pl40 h123">
		                    <div class="fl w50 h120">备注：</div>
		                    <div class="fl w460 h120 tl lh20">{{remarks}}
		                    </div>
		                </div>
		            </div>
		            <div class="cb"></div>
		        </div>
		        <div class="fl w300">
		            <div class="tc lh35">
		                <span class="c00a5f2 dsb w96 h35 br10 ba00a5f2 bc mt25">{{toType type1}}</span>
		                <span class="{{toDoor need_todoor}} c00a5f2 dsb w96 h35 br10 ba00a5f2 bc mt25">需要上门</span>
		            </div>
		        </div>
		    </div>
		    <div class="cb"></div>
		</div>
		<div class="tc pl10 pr10 bbe0e0e0 {{hasOrg org_id current recommend}}">
		    <div class="pb25 xuxian">
		        <div class="fl w480 Rright">
		            <div class="fl w201 tl">
		                <div class="f14 c717583">交易机构：</div>
		                <div class="f16 lh20 mt3">{{showCompany company current recommend}}</div>
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
		                    <div class="mt10"><span style="color:red">{{pjprice}}</sspan></div>
		                </div>
		                <div class="fl w105">
		                    <div class="c717583 f14">服务态度</div>
		                    <div class="mt10"><span style="color:red">{{pjservice}}</sspan></div>
		                </div>
		                <div class="fl w105">
		                    <div class="c717583 f14">打款速度</div>
		                    <div class="mt10"><span style="color:red">{{pjspeed}}</sspan></div>
		                </div>
		            </div>
		        </div>
		        <div class="cb"></div>
		    </div>
		    <div class="lh34 mt20 mb20 tc">
		        <div class="fl fb">所需资料：</div>
		        <div class="bq">
							
				</div>
		        <div class="cb"></div>
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
       	 	<a href="${basePath}/discountrecord/discount?ym=sp"  class="fr cd43c33 ba2_fc4d42 br3 dsb w166 h45 lh45 mr40">再来一单</a>
    	</div>
		<div class="cb"></div>
{{/each}}
</script>

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
	Handlebars.registerHelper('toPhone',function(phone,options){
		return hideMobile(phone);
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
	            
	 		}
		}
	 });

</script>

