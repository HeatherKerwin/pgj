[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-批量订单详情']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='2'/]

<!--贴现输入表单-->
<div class="f14 mt16 w1200 bc bcwhite bae0e0e0 mt20 mb80 pl25 pr25">
    <div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0" id="zhuangtai">
    </div>
    <div class="fb lh180 cblack mt30 bbe0e0e0">
        确认贴现地址
    </div>
    <div class="mt30 c2d2d2d">
        <div class="fl" id="address"></div>
        <div class="fl c808080 ml20" id="mobile"></div>
        <div class="fl 808080 ml60">
            <i class="fl w9 h14 position"></i>
            <div class="fl ml8" id="distance"></div>
        </div>
        <div class="fl c3366cc ml20" id="noNeed">企业未要求上门</div>
        <div class="fl cd43c33 ml20" id="need">企业要求上门</div>
    </div>
    <div class="cb"></div>
    <div id="needTodoorPanel" class="none">
	    <div class="mt30">
	        <div class="fl">上门费用：</div>
	        <div class="fl ml10" id="todoor_price"></div>
	    </div>
	    <div class="cb"></div>
	    <div class="mt30">
	        <div class="fl">预估上门时间：</div>
	        <div class="fl ml10" id="todoor_time"></div>
	    </div>
	    <div class="cb"></div>
    </div>
    <div class="fb lh180 cblack mt30 bbe0e0e0">
        确认订单信息
    </div>
    <div class="mt30 bae0e0e0 mb20">
        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
            <div class="fl w232">批量订单号</div>
            <div class="fl w260">票据总金额</div>
            <div class="fl w150">票据总数量</div>
            <div class="fl w184">票据到期天数</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="cb"></div>
        <div class="h265 bcwhite bbe0e0e0 pt25 pb25">
            <div class="fl w827 Rright">
                <div class="bbe0e0e0 tc f16 h90 pb25">
                    <div class="fl w222 Rright h90 tl ml10">
                        <div class="c3366cc ti8" id="jgno"></div>
                    </div>
                    <div class="fl w260 Rright h90 lh30">
                        <div class=""><span id="allmoney" class="cd43c33"></span>万元</div>
                        <div class="">票面最小金额为<span id="min_money" class="cd43c33"></span>万元</div>
                        <div class="">票面最大金额为<span id="max_money" class="cd43c33"></span>万元</div>
                    </div>
                    <div class="fl w150 Rright h90 lh30"><span id="amount" class="cd43c33"></span>张</div>
                    <div class="fl w184 h90 lh30">
                        <div class="">最短<span id="min_expiry_day" class="cd43c33"></span>天</div>
                        <div class="">最长<span id="max_expiry_day" class="cd43c33"></span>天</div>
                    </div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h90">
                    <div class="fl w220 Rright h110 tl ml10">
                        <div class="ti8">包含承兑行：</div>
                        <ul class="ti8 mt16 lh35" id="type2">
                        </ul>
                    </div>
                    <div class="fl pl40 h90">
                        <div class="fl w50 h90">备注：</div>
                        <div class="fl w460 h90 tl lh20" id="remarks">
                        </div>
                    </div>
                </div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="type1"></span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="flaw_ticket"></span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="needTodoor"></span>
                </div>
            </div>
        </div>
        <div class="cb"></div>
        <div class="h65 lh65 ml10 ti8">
            订单有效期至：<span id="endtime"></span>
        </div>
        <div class="cb"></div>
    </div>
    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20 mb20" id="button">
        <a href="#" class="fr c2d2d2d ba2_e0e0e0 br3 dsb w166 h45 lh45 mr40" onclick="tan()">取消订单</a>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]
<!--理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="reason">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <div class="h70"></div>
            <div class="ml25 f16 lh40">
                <div class="fl w138 c7d7d7d">请选择取消理由：</div>
                <select class="fl w320 h40 select320 ti8" id="cancel">
                    <option value="1">额度不够</option>
                    <option value="2">不在业务范围内</option>
                    <option value="3">银行大额支付系统已关闭</option>
                    <option value="4">其他</option>
                </select>
            </div>
            <div class="cb"></div>
            <!-- 其他-->
            <textarea placeholder="请您输入不少于十五字的理由！" type="text" class="w500 h210 bae0e0e0 mt30 ml160 ti8 pt15 none" id="reason1_div"></textarea>
            <div class="cb"></div>
            <input type="hidden" id="state" value=""/>
            <input type="button" value="确认" id="cancelButton" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b60 l_50 ml-130 cp" onclick="cancelDtbo()">
        </div>
    </div>
</div>
<script type="text/javascript">
/*
 * 页面初始化
 */
$(function(){
	loadData();
})

/*
 * 加载数据
 */
function loadData(){
	var no = '${no}';
	$.ajax({
		url:"${basePath}/distributeorderpl/get",
		type:"POST",
		data:{"no":no},
		dataType:"json",
		success:function(data){
			if(data.response=='success'){
				if(data.endtime!=null)$("#endtime").text(data.endtime);
				var data = data.data;
				var state = data.state;
                if(state==1){
                	//$("#zhuangtai").text("待支付");
                }else if(state == 2){
                	$("#zhuangtai").text("交易中"); 
                	$("#mobile").text(data.member_mobile);
                }else if(state==3){
                	$("#zhuangtai").text("已完成");
                	$("#button").addClass("none");
                	$("#mobile").text(hideMobile(data.member_mobile));
                }else if(state==0 || state == -1 || state ==-2){
                	$("#zhuangtai").text("无效订单");
                	$("#button").addClass("none");
                	$("#mobile").text(hideMobile(data.member_mobile));
                }
                if(data.distance!=null)$("#distance").text("距离您"+data.distance.toFixed(3)+"公里");
                $("#address").text(data.place+" "+data.address);
				if(data.need_todoor==1){
					$("#need").removeClass("none");
					$("#noNeed").addClass("none");
	                $("#needTodoorPanel").removeClass("none");
	                if(data.todoor_price==null){
            	 		$("#todoor_price").text("");
            	 	}else{
            	 		$("#todoor_price").text(data.todoor_price+"元");
            	 	}
        	 		if(data.todoor_time==null){
        	 			$("#todoor_time").text("");
        	 		}else{
        	 			if(data.todoor_time==0){
        	 				$("#todoor_time").text("2小时以内");
        	 			}else if(data.todoor_time==1){
        	 				$("#todoor_time").text("4小时以内");
        	 			}else if(data.todoor_time==2){
        	 				$("#todoor_time").text("6小时以内");
        	 			}else if(data.todoor_time==3){
        	 				$("#todoor_time").text("8小时以内");
        	 			}else{
        	 				$("#todoor_time").text("8小时以上");
        	 			}
        	 		}
				}else{
					$("#need").addClass("none");
					$("#noNeed").removeClass("none");
	                $("#needTodoorPanel").addClass("none");
				}
				if(data.type1==1){
					$("#type1").text("纸票");
				}else{
					$("#type1").text("电票");
				}
			    if(data.need_todoor==1){//需要上门
					$("#needTodoor").text("需要上门");
					$("#needTodoor").removeClass("none");
				}else if(data.need_todoor==0){//不需要上门
					$("#needTodoor").addClass("none");
				}
			    if(data.flaw_ticket==0){
					$("#flaw_ticket").text("瑕疵票");
					$("#flaw_ticket").removeClass("none");
				}else{
					$("#flaw_ticket").addClass("none");
				}
				$("#allmoney").text(data.allmoney);
				$("#jgno").text(data.jgno);
                $("#remarks").text(data.remarks);
                $("#amount").text(data.amount);
                $("#max_expiry_day").text(data.max_expiry_day);
                $("#min_expiry_day").text(data.min_expiry_day);
                $("#max_money").text(data.max_money);
                $("#min_money").text(data.min_money);
                $("#state").val(data.state);
                var type2 = data.type2;
                if(type2!=null){
                	var values = new Array("","国股","大商","小商","三农","其它");
                	var html = '';
                    var t = type2.split(",");
                    for(var i=0;i<t.length;i++){
                        html += '<li class="fl mr30">'+values[t[i]]+'</li>';
                    }
                    $("#type2").html(html);
                }
			}
		}
	});
}

/*
 * 取消订单
 */
function cancelDtbo(){
	$("#cancelButton").attr("disabled","disabled");
	var jgno = '${no}';
	var state = $("#state").val();
    var reason = $("#cancel").find("option").not(function(){ return !this.selected }).text();//拒绝原因（文本）
    var lostCause = null;//其他理由
    var cancel = $("#cancel").val();
 	if(cancel==4){//其他
        lostCause = $("#reason1_div").val();
        reason = lostCause;
        if (lostCause.length < 15) {
            alert("请你输入不少于15字的理由");
            $("#cancelButton").removeAttr("disabled");
            return;
        }
    }else{
        lostCause = null;
    }
    var data = "no="+jgno+"&cancel="+cancel+"&reason="+reason+"&currentState="+state;
    $.ajax({
    	url:"${basePath}/distributeorderpl/cancel",
    	type:"POST",
    	data:data,
    	dataType:"json",
    	success:function(data){
    		if (data.response == 'success') {
    			alert("取消成功!");
    			$("#reason").addClass("none");
    			//取消订单后去哪个页面
    			loadData();
    			$("#cancelButton").removeAttr("disabled");
            }else{
                alert(data.msg);
                $("#cancelButton").removeAttr("disabled");
            }
    	},
    	error:function(data){
    		$("#cancelButton").removeAttr("disabled");
    	}
    });
}

/*
 * 拒绝理由弹框显示
 */
function tan(){
	$("#reason").removeClass("none");
}

/*
 * 拒绝订单理由隐藏
 */
$("#redClose").click(function(){
    $("#reason").addClass("none");
});
$("#cancel").change(function(){
    var value = $(this).val();
    if(value==4){
        $("#reason1_div").removeClass("none");
    }else{
        $("#reason1_div").addClass("none");
    }
});
</script>
<!--foot-->
[@main.footer/]
[/@main.body]