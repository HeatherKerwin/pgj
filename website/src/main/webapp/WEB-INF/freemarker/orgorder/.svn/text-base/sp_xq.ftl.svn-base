[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-商票订单详情']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='2'/]

<!--表单-->
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
            <div class="fl w232">商票订单号</div>
            <div class="fl w155">总金额</div>
            <div class="fl w105">背书</div>
            <div class="fl w334">承兑企业</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="h210 bcwhite pt25 pb25">
            <div class="fl w827 Rright">
                <div class="bbe0e0e0 tc f16 h65 lh40">
                    <div class="fl w222 Rright h40 tl ml10 ti8" id="jgno">
                    </div>
                    <div class="fl w155 Rright h40" id="allmoney"></div>
                    <div class="fl w105 Rright h40" id="endorse"></div>
                    <div class="fl w334 h40 ti8 dian" id="bank"></div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h120">
                    <div class="fl w220 Rright h120 tl ml10">
                        <div class="ti8">开票日期：<span id="printtime"></span></div>
                        <div class="ti8 mt16">贴现日期：<span id="begintime"></span></div>
                        <div class="ti8 mt16">到期日期：<span id="endtime"></span></div>
                        <div class="ti8 mt16">计息天数：<span id="jxts"></span>天</div>
                    </div>
                    <div class="fl pl40 h123">
                        <div class="fl w50 h120">备注：</div>
                        <div class="fl w460 h120 tl lh20" id="remarks">
                        </div>
                    </div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    <span class="c00a5f2 dsb w96 h35 br10 ba00a5f2 bc mt25" id="type1"></span>
                    <span class="c00a5f2 dsb w96 h35 br10 ba00a5f2 bc mt25" id="needTodoor"></span>
                </div>
            </div>
        </div>
        <div class="cb"></div>
    </div>
    <!--报价-->
    <div class="mt20 bae0e0e0 pl10 pb25">
        <div class="fl w390 mt25 Rright Interestdiv1">
            <div class="tl fb f16">您的报价：</div>
            <div class="mt25 lh27 ml40" id="yue">
                <div class="fl w129">月息：</div>
                <div class="fl tc" id="price"></div>
                <div class="cb"></div>
            </div>
            <div class="mt25 lh27 ml40" id="nian">
                <div class="fl w129">年息：</div>
                <div class="fl tc" id="price1"></div>
                <div class="cb"></div>
            </div>
            <div class="mt25 lh27 ml40" id="ten">
                <div class="fl w129">每十万扣：</div>
                <div class="fl tc" id="price2"></div>
                <div class="cb"></div>
            </div>
        </div>
        <div class="fl pl40 mt25">
            <div class="f16 fb">您已选择持票企业所需提供的资料</div>
            <div class="w620 mt40" id="stuff">
            </div>
        </div>
        <div class="cb"></div>
    </div>
    <!--票面-->
    <div class="bae0e0e0 mt20 pb25 mb20" id="piaomian">
        <div class="pl10 h50 lh50 c717583 bcf9f9f9 bbe0e0e0">票面</div>
        <div id="picture"></div>
    </div>
    <div class="cb"></div>
    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20 mb20" id="button">
        <a href="#" class="fr c2d2d2d bae0e0e0 br3 dsb w166 h45 lh45 mr40" id="cancle" onclick="tan()">取消订单</a>
    </div>
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
		url:"${basePath}/distributeordersp/get",
		type:"POST",
		data:{"no":no},
		dataType:"json",
		success:function(data){
			if(data.response=='success'){
				if(data.printtime!=null)$("#printtime").text(data.printtime);
				if(data.begintime!=null)$("#begintime").text(data.begintime);
                if(data.endtime!=null)$("#endtime").text(data.endtime);
				var data = data.data;
				var state = data.state;
                if(state==2){
                	$("#zhuangtai").text("交易中"); 
                	$("#mobile").text(data.member_mobile);
                }else if(state==3){
                	$("#zhuangtai").text("已完成");
                	$("#button").addClass("none");
                	$("#mobile").text(hideMobile(data.member_mobile));
                }else if(state==0 || state==-1 || state==-2){
                	$("#zhuangtai").text("无效订单");
                	$("#button").addClass("none");
                	$("#mobile").html(hideMobile(data.member_mobile));
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
				$("#allmoney").html('<span class="cd43c33">'+data.allmoney+'</span>万元');
				$("#jgno").text(data.distNo);
				if(data.bank!=null)$("#bank").text(data.bank);
				if(data.endorse!=null)$("#endorse").text(data.endorse);
                if(data.jxts!=null)$("#jxts").text(data.jxts);
                $("#state").val(data.state);
                $("#remarks").text(data.remarks);
                var way = data.way;
			    if(way==null) way=0;//默认月息报价
                if(way==0){//方式A：月息+参数
                    var html = "";
                	if(data.price == null){
                		html +="--";
                	}else{
                		html +=data.price+"‰";
                	}
                    if(data.price1!=null){
                        html += "&nbsp;+&nbsp;"+data.price1+"元";
                    }
                    if(1==data.type1){
                    	$("#yue").removeClass("none");
                        $("#nian").addClass("none");
                        $("#price").html(html);
                    }else{
                    	$("#nian").removeClass("none");
                        $("#yue").addClass("none");
                        $("#price1").html(html);
                    }
                    $("#ten").addClass("none");
                }else if(way==1){//方式B：每十万贴现成本
                    var html = data.price2+"元";
                    $("#price2").html(html);
                    $("#ten").removeClass("none");
                    $("#yue").addClass("none");
                    $("#nian").addClass("none");
                }else if(way==2){//年息+参数
                	var html = "";
                	if(data.price == null){
                		html +="--";
                	}else{
                		html +=data.price+"%";
                	}
                	if(data.price1!=null){
                        html += "&nbsp;+&nbsp;"+data.price1+"元";
                    }
                	$("#nian").removeClass("none");
                    $("#yue").addClass("none");
                    $("#ten").addClass("none");
                    $("#price1").html(html);
                }
			    var stuff = data.need_stuff;
			    if(stuff!=null){
			    	var html = getNeedStuff(stuff);
			    	$("#stuff").html(html);
			    }
			    if(data.picpath!=null){
                	var pic=data.picpath.split(',');
        	 		var imgs = "";
					for(var i=0;i<pic.length;i++){
						var img = pic[i];
						if(img!=null && img!=""){
							imgs += '<img src="${imagePath}'+img+'" class="w860 h230 bc mt25 ml143"/>';       	 		
						}  
    	 			}
    	 			$("#picture").html(imgs);
    	 			$("#piaomian").removeClass("none");
                }else{
                	$("#piaomian").addClass("none");
                }
			}
		}
	});
}

/*
 * 商票取消订单
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
    	url:"${basePath}/distributeordersp/cancel",
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