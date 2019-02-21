[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='2'/]

<!--确认下单-->
<div class="mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态-->
   	<input type="hidden" id="id" value="${id}" />
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
            <img src="${image_url}/website/discount/state41.png" width="165" height="30">
            <p class="ccccccc mt10">评价机构</p>
        </div>
    </div>
    <div class="cb"></div>
   
   	<div id="content">
   	
   	</div>
</div>
  [@main.right /]
[@main.footer/]
[/@main.body]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>

<script type="text/x-handlebars-template" id="DISCOUNTRECORD">
{{#each results}}
<!--选择地址-->
<div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0">
    无效订单
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
            <div class="fl w480 Rright mr20 {{toNan orderflag org_id commentId}}">
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
	Handlebars.registerHelper('toSml', function(sml,options) {
	    if(sml==null || sml ==""){
	        return "--";
	    }else {
	        return sml+"%";
	    }
	});
	Handlebars.registerHelper('toNan', function(orderflag,org,com,options) {
	    if(orderflag != 3 || orderflag != 4){
	        return "none";
	    }else if(orderflag == 3 && com != null){
	        return "none";
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

<script>
	//页面初始化加载数据
	var xqid = $("#id").val();
	$.ajax({
		type: "POST",
	 	url: "${basePath}/discountorder/list",
	 	data: {xqid:xqid},
	 	dataType:"json",
	 	success:function(data){
	 		if(data.response == "success"){
	 			var source = $("#DISCOUNTRECORD").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data);
	            $("#content").html(html);
	 		}
		}
	 });	
</script>
