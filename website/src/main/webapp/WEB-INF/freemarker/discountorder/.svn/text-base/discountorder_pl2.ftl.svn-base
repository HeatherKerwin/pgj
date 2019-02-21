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
    <div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0">
        待交易
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
            <!--取消订单理由-->
            <div class="pr h500 t45 none" id="reasonDiv">
                <div class="ml25 f16 lh40">
                    <div class="fl w138 c7d7d7d">请选择取消理由：</div>
                    <input type="hidden" id="qxid"  />
                    <input type="hidden" id="qxzt"  />
                    <select class="fl w320 h40 select320 ti8" id="cancel">
                        <option value="0">票面信息输入有误</option>
                        <option value="1">只为熟悉流程和询问价格</option>
                        <option value="2">价格不合适</option>
                        <option value="3">已通过其他方式出票</option>
                        <option value="4">其他</option>
                    </select>
                </div>
                <!-- 其他-->
                <textarea placeholder="请您输入不少于十五字的理由！" type="text" class="w500 h210 bae0e0e0 mt20 ml160 ti8 pt15 none" id="reason1_div"></textarea>
                <!--票据有问题-->
                <input type="button" value="确认" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b30 l_50 ml-130" id="infor">
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
		                    <div class="fl w204 Rright h40 tl c3366cc f16 xzym" data-no="{{no}}" data-orderflag="{{orderflag}}" data-commentId="{{commentId}}">
                                {{no}}
                            </div>
		                    <div class="fl w210 Rright h90 lh30">
		                        <div class=""><span style="color:red">{{allmoney}}</span>万元</div>
		                        <div class="">票面最小金额为<span style="color:red">{{min_money}}</span>万元</div>
		                        <div class="">票面最大金额为<span style="color:red">{{max_money}}</span>万元</div>
		                    </div>
		                    <div class="fl w100 Rright h90 lh30"><span style="color:red">{{amount}}张</span></div>
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
        </div>
    </div>
    <div class="cb"></div>
    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20">
        <a href="#" class="fr c2d2d2d ba2_e0e0e0 br3 dsb w166 h45 lh45 mr40 reason" data-no="{{no}}" data-orderflag="{{orderflag}}">取消订单</a>
    </div>
    <div class="cb"></div>
	<input type="hidden" id="optcancel" value="{{orderflag}}">
{{/each}}
</script>

<script type="text/javascript">

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
		        
		        $(".reason").live("click",qxdd);
		        
		    },complete: function(){
		    },error:function(){
		    	
		    }
		});
	}

	$(function(){
		csh();
	})
</script>

<script>
	function qxdd(){
		if($("#optcancel")==0){
			alert("页面已经过期");
			location.href = "${basePath}/discountorder/discount?ym=pl";
			return;
		}
		$("#qxid").val($(this).attr("data-no"));
		$("#qxzt").val($(this).attr("data-orderflag"));
		$("#window").removeClass("none");
	    $("#reasonDiv").removeClass("none");
	}
	
    //    填写理由
    $("#cancel").change(function(){
        var value = $(this).val();
        if(value==4){
            $("#reason1_div").removeClass("none");
        }else{
            $("#reason1_div").addClass("none");
        }
    });
    
    $("#infor").click(function(){
    	if($("#infor").attr("disabled")){
			return;
		}
    	var id = $("#qxid").val();
    	var currentState = $("#qxzt").val();
    	var cancel = $("#cancel").val();
    	var reason = $("#reason1_div").val();
    	if(cancel==4 && reason==""){
    		alert("请输入取消订单的理由");
    		return ;
    	}
    	if(cancel==4 && $.trim(reason).length<15){
    		alert("请填写15字的理由");
    		return;
    	}
    	$("#infor").attr("disabled","disabled");//按钮禁用
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountorderpl/cancel",
	     	data: {id:id,currentState:currentState,cancel:cancel,reason:reason},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			$("#infor").removeAttr("disabled");//按钮启用
	     			$("#window").addClass("none");
	     	        $("#reasonDiv").addClass("none");
	     	        $("#finishDiv").addClass("none");
	     	        $("#evaluateDiv1").addClass("none");
	     	        $("#evaluateDiv2").addClass("none");
	     	        location.href = "${basePath}/discountorder/discount?ym=pl";
	     		}else{
	     			$("#infor").removeAttr("disabled");//按钮启用
	     			alert(data.msg)
	     		}
	     		
	    	}
		 })	
    });
    
    $(".redClose").click(function(){
        $("#window").addClass("none");
        $("#reasonDiv").addClass("none");
        $("#finishDiv").addClass("none");
        $("#evaluateDiv1").addClass("none");
        $("#evaluateDiv2").addClass("none");
    });
</script>
