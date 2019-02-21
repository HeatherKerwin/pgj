[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='2'/]

<!--确认下单-->
<div class="mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态-->
    <input type="hidden"  value="${member.id}" />
   	<input type="hidden"  id="id" value="${id}" />
    <div class="w654 bc f12 tc">
        <div class="fl">
            <img src="${image_url}/website/discount/state12.png" width="165" height="30">
            <p class="c3366cc mt10">确认贴现订单</p>
        </div> 
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state23.png" width="165" height="30">
            <p class="cbac6fd mt10">付押金保障交易</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state31.png" width="165" height="30">
            <p class="ccccccc mt10">交易跟踪</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state41.png" width="165" height="30">
            <p class="ccccccc mt10">评价机构</p>
        </div>
    </div>
    <div class="cb"></div>
    <!--选择地址-->
    <div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0">
        待支付
    </div>
    <div class="fb lh180 cblack mt30 bbe0e0e0">
        贴现地址
    </div>
    <div class="mt30 c2d2d2d xianshidz" >
        <div class="fl" id="dz"> </div>
        <div class="fl c808080 ml20" id="phone"> </div>
    </div>
    <div class="mt30 c2d2d2d chaolian " style="display:none">
       <div class="fl"><span style="color:red">添加地址</span> </div>
    </div>
    <div class="cb"></div>
    <div class="fb lh180 cblack mt30 bbe0e0e0">
        订单信息
    </div>
    <div class="mt30 bae0e0e0 mb20">
        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
            <div class="fl w232">银票订单号</div>
            <div class="fl w155">总金额</div>
            <div class="fl w105">背书</div>
            <div class="fl w212">付款行</div>
            <div class="fl w122">保证金</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="h190 bcwhite pt10 pb25">
            <div class="fl w827 h190 pl10 Rright">
                <div class="bbe0e0e0 tc f16 h50 lh40">
                    <div class="fl w220 Rright h40" id="ordernum">
                       
                    </div>
                    <input type="hidden" id="bns_no" />
                    <input type="hidden" id="bzj" />
                    <div class="fl w155 Rright h40" ><span style="color:red" id="allmoney">0</span></div>
                    <div class="fl w105 Rright h40" id="endorse" >0手</div>
                    <div class="fl w210 Rright h40 ti8 dian" id="bank"></div>
                    <div class="fl w120 h50 h40"><span style="color:red" id="deposit">0</span></div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h90">
                    <div class="fl w220 Rright h90 tl">
                        <div class="ml30">贴现日期：<span id="start"></span></div>
                        <div class="ml30 mt20">到期日期：<span id="end"></span></div>
                    </div>
                    <div class="fl pl40 h90">
                        <div class="fl w50 h90">备注：</div>
                        <div class="fl w460 h90 tl lh20" id="beizhu">
                        </div>
                    </div>
                </div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="zp">纸票</span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="dp">电票</span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="xc">瑕疵票</span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="type2">国股</span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="sm" >需要上门</span>
                </div>
            </div>
        </div>
    </div>
        <div class="cb"></div>
    <!--票面-->
    <div class="bae0e0e0 mt20 pb25 pic">
        <div class="pl10 h50 lh50 c717583 bcf9f9f9 bbe0e0e0">票面</div>
        <img id="picpath" src="${image_url}/website/discount/1.png" class="w860 h230 bc mt25 ml143" />
    </div>
    <div class="cb"></div>
    <!--支付方式-->
    <div class="bae0e0e0 mt20 pl10 bcf9f9f9 h50 f16 lh50">
        <div class="fl ">保证金支付方式：</div>
        <!--<p class="fl">支付宝</p>-->
        <img src="https://img.utiexian.com/website/img/logo-kuaiqian.png" class="fl w60 mt10" />
        <input type="hidden" id="payWay" name="payWay" value="3" />
    </div>
    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20">
        <a href="#" class="fr cd43c33 ba2_fc4d42 br3 cp dsb w166 h45 lh45 mr40" id="qrzf">确认支付</a>
        <a href="#" class="fr c2d2d2d ba2_e0e0e0 br3 cp dsb w166 h45 lh45 mr40" id="qxdd">取消订单</a>
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
		   <!--取消订单理由-->
	       <div class="pr h500 t45 none" id="reasonDiv">
	           <div class="ml25 f16 lh40">
	               <div class="fl w138 c7d7d7d">请选择取消理由：</div>
	               <input type="hidden" id="qxid"  />
	               <input type="hidden" id="qxzt" value="1" />
	               <select class="fl w320 h40 select320 ti8" id="cancel">
	                   <option value="0">票面信息输入有误</option>
	                   <option value="1">只为熟悉流程和询问价格</option>
	                   <option value="2">价格不合适</option>
	                   <option value="3">已通过其他方式出票</option>
	                   <option value="4">其他</option>
	               </select>
	           </div>
	           <!-- 其他-->
	           <textarea style='resize: none;' placeholder="请您输入不少于十五字的理由！" type="text" class="w500 h210 bae0e0e0 mt20 ml160 ti8 pt15 none" id="reason1_div"></textarea>
	           <!--票据有问题-->
	           <input type="button" value="确认" class="w260 h45 lh45 bc cp bcfc4d42 b0 br5 f18 cwhite pa b30 l_50 ml-130" id="infor">
	       </div>
	    </div>
	 </div> 
</div>	      
[@main.footer/]
[/@main.body]
<script type="text/javascript">
	$("#qxdd").click(function(){
		$("#window").removeClass("none");
		$("#reasonDiv").removeClass("none");
	});
	
	$("#cancel").change(function(){
        var value = $(this).val();
        if(value==4){
            $("#reason1_div").removeClass("none");
        }else{
            $("#reason1_div").addClass("none");
        }
    });
	
	 $(".redClose").click(function(){
        $("#window").addClass("none");
        $("#reasonDiv").addClass("none");
    });
	 
	 /**
	 * 取消订单
	 */
	 $("#infor").click(function(){
    	var id = $("#id").val();
    	var currentState = $("#qxzt").val();
    	var cancel = $("#cancel").val();
    	var reason = $("#reason1_div").val();
    	if(cancel != 4){
    		reason = $("#cancel").find("option").not(function(){ return !this.selected }).text();//拒绝原因（文本）
    	}
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountorder/update",
	     	data: {id:id,currentState:currentState,cancel:cancel,reason:reason},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			//跳转到所有订单的页面
	     			location.href = "${basePath}/discountorder/discount?ym=yp";
	     		}else{
	     			alert(data.msg)
	     		}
	    	}
		 });	
    });
	 
	 /**
	 * 去支付金额
	 */
	$("#qrzf").click(function(){
 	   var bnsno = $("#bns_no").val();
 	   location.href = "${basePath}/discountorder/payconfirmorder?no="+bnsno;
	});
	
	 /**
	 *  添加地址的确认页面
	 */
	$(".chaolian").click(function(){
 	   var bnsno = $("#bns_no").val();
  	   location.href = "${basePath}/discountorder/payconfirmorder?no="+bnsno;
 	});
</script>


<script type="text/javascript">
	$(function(){
		var id =$("#id").val();
		$.ajax({
			type: "POST",
		 	url: "${basePath}/discountorder/target",
		 	data: {id:id},
		 	dataType:"json",
		 	success:function(data){
		 		if(data.response == "success"){
		 			
		 			$("#ordernum").text(data.data.ordernumber);
		 			$("#bns_no").val(data.data.ordernumber);
		 			$("#allmoney").text(data.data.allmoney+"万元");
		 			$("#endorse").text(data.data.endorse+"手");
		 			$("#bank").text(data.data.bank);
		 			$("#deposit").text(data.data.deposit+"元");
		 			$("#start").text(data.data.begintime.substr(0,10));
		 			$("#end").text(data.data.endtime.substr(0,10));
		 			$("#beizhu").text(data.data.memberother);
		 			
		 			$("#bzj").val(data.data.deposit);
		 			
		 			if(data.data.address == null){
		 				$(".xianshidz").hide();
		 				$(".chaolian").show();
		 			}else{
		 				$("#dz").text(data.data.address+"("+data.data.membername+")")
			 			$("#phone").text(data.data.membermobile);
		 			}
		 			
		 			if(data.data.picpath!=null && data.data.picpath != ""){
		 				$("#picpath").attr("src","${imagePath}"+data.data.picpath);
		 			}else{
		 				$(".pic").addClass("none");
		 			}
		 			
		 			if(data.data.type1==1){
		 				$("#dp").addClass("none");
		 			}else{
		 				$("#zp").addClass("none");
		 			}
		 			if(data.data.needTodoor==0){
		 				$("#sm").addClass("none");
		 			}
		 			if(data.data.flawTicket==1){
		 				$("#xc").addClass("none");
		 			}
		 			if(data.data.type2 == 1){
		 				$("#type2").text("国股");
		 			}
		 			if(data.data.type2 == 2){
		 				$("#type2").text("小商");
		 			}
		 			if(data.data.type2 == 3){
		 				$("#type2").text("外资");
		 			}
		 			if(data.data.type2 == 4){
		 				$("#type2").text("农商");
		 			}
		 			if(data.data.type2 == 5){
		 				$("#type2").text("农合");
		 			}
		 			if(data.data.type2 == 6){
		 				$("#type2").text("农信");
		 			}
		 			if(data.data.type2 == 7){
		 				$("#type2").text("村镇");
		 			}
		 			if(data.data.type2 == 8){
		 				$("#type2").text("大商");
		 			}
		 		
		 		}else{
		 			alert(data.msg)
		 		}
		 		
			}
		 })	
	})	

</script>
