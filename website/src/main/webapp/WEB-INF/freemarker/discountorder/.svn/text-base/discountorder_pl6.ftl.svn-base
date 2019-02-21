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
            <img src="${image_url}/website/discount/state23.png" width="165" height="30">
            <p class="c3366cc mt10">交易跟踪</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state31.png" width="165" height="30">
            <p class="ccccccc mt10">评价机构</p>
        </div>
    </div>
    <div class="cb"></div>
    <div class="f18 fb lh180 c717583 mt30 bbe0e0e0">
        无效订单
    </div>
    <div id="content">
    
    </div>
    
</div>
  [@main.right /]
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
	                    <div class="fl w204 Rright h40 tl c3366cc f16 xzym" data-no="{{no}}" data-orderflag="{{orderflag}}" data-commentId="{{commentId}}">
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
	    </div>
	</div>
{{/each}}
</script>

<script type="text/javascript">
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
	Handlebars.registerHelper('toPhone',function(phone,options){
		return hideMobile(phone);
	});
	Handlebars.registerHelper('toNan', function(orderflag,org,com,options) {
	    if(orderflag != 3 || orderflag != 4){
	        return "none";
	    }else if(orderflag == 3 && com != null){
	        return "none";
	    }
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
		        
		    },complete: function(){
		    },error:function(){
		    	
		    }
		});
	}
	
	$(function(){
		csh();
	});	

</script>
