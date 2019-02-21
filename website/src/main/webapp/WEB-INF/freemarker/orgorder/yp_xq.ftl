[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-银票订单详情']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='2'/]

<!--贴现表单-->
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
        <div class="fl c3366cc ml20 none" id="noNeed">企业未要求上门</div>
        <div class="fl cd43c33 ml20 none" id="need">企业要求上门</div>
    </div>
    <div class="cb"></div>
    <div id="needTodoorPanel" class="none">
    <div class="mt30 todoor">
        <div class="fl">上门费用：</div>
        <div class="fl ml10" id="todoor_price"></div>
    </div>
    <div class="cb"></div>
    <div class="mt30 todoor">
        <div class="fl">预估上门时间：</div>
        <div class="fl ml10" id="todoor_time"></div>
    </div>
    <div class="cb"></div>
    </div>
    <div class="fb lh180 cblack mt30 bbe0e0e0">
        确认订单信息
    </div>
    <div class="mt30 mb20">
        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
            <div class="fl w232">银票订单号</div>
            <div class="fl w155">总金额</div>
            <div class="fl w105">背书</div>
            <div class="fl w212">付款行</div>
            <div class="fl w122">保证金</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="h190 bcwhite bbe0e0e0 pt10 pb25">
            <div class="fl w827 h190 pl10 Rright">
                <div class="bbe0e0e0 tc f16 h50 lh40">
                    <div class="fl w220 Rright h40" id="ordernumber">
                    </div>
                    <div class="fl w155 Rright h40" id="allmoney"></div>
                    <div class="fl w105 Rright h40" id="endorse"></div>
                    <div class="fl w210 Rright h40 ti8 dian" id="bank"></div>
                    <div class="fl w120 h50 h40"><span id="deposit" class="cd43c33"></span>元</div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h90">
                    <div class="fl w220 Rright h90 tl">
                        <div class="ml30">贴现日期：<span id="begintime"></span></div>
                        <div class="ml30 mt20">到期日期：<span id="endtime"></span></div>
                        <div class="ml30 mt20">计息天数：<span id="jxts"></span></div>
                    </div>
                    <div class="fl pl40 h90">
                        <div class="fl w50 h90">备注：</div>
                        <div class="fl w460 h90 tl lh20" id="memberother">
                        </div>
                    </div>
                </div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="type1"></span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="flaw_ticket"></span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="type2"></span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="needTodoor"></span>
                </div>
            </div>
        </div>
    </div>
    <!--价格微调-->
    <div class="mt20 bae0e0e0 pl10 pb25 mb13">
        <div class="mt20">
            <div class="fl tl w148 fb">今日参考贴现价格：</div>
            <div class="fl" id="priceShow"></div>
        </div>
        <div class="cb"></div>
        <div class="mt25">
            <div class="fl tl w148 fb">您调整后的价格：</div>
            <div class="fl" id="priceShow1"></div>
        </div>
        <div class="cb"></div>
    </div>
    <!--票面-->
    <div class="bae0e0e0 mt20 pb25 mb13" id="piaomian">
        <div class="pl10 h50 lh50 c717583 bcf9f9f9 bbe0e0e0">票面</div>
        <div id="picture"></div>
    </div>
    <div class="cb"></div>
    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20 mb20" id="button">
        <a href="javascript:void(0)" class="fr cd43c33 bad43c33 br3 dsb w238 h45 lh45 mr40" id="topay" onclick="accept()">
            去支付<span id="minute_show">(--分--秒)</span>
        </a>
        <a href="javascript:void(0)" class="fr c2d2d2d bae0e0e0 br3 dsb w166 h45 lh45 mr40" id="cancle" onclick="tan()">取消订单</a>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]
<!--理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="reason">
    <div class="w800 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose"></div>

        <div class="w792 h600 pr t4 l4 bcf5f5f5 zi12">
            <div class="h20"></div>
            <div class="ml25 f16 lh40">
                <div class="fl w184 c7d7d7d">请选择取消理由：</div>
                <select class="fl w320 h40 select320 ti8" id="cancel1">
                    <option value="1">额度不够</option>
                    <option value="2">票据有问题</option>
                    <option value="3">不在业务范围内</option>
                    <option value="4">银行大额支付系统已关闭</option>
                    <option value="5">其他</option>
                </select>
            </div>
            <div class="cb"></div>
            <!-- 其他-->
            <textarea placeholder="请您输入不少于十五字的理由！" type="text" class="w500 h210 bae0e0e0 mt20 ml209 ti8 pt15 none" id="reason1_div"></textarea>
            <div class="cb"></div>
            <!--票据有问题-->
            <div class="ml25 mt16 none" id="reason2_div">
                <div class="mt16 f16 lh40">
                    <div class="fl w184 c7d7d7d">请选择验票失败理由：</div>
                    <select class="fl w320 h40 select320 ti8" id="cancel2">
                        <option value="1">票面信息不完整</option>
                        <option value="2">票据不真实</option>
                        <option value="3">出票人印章与出票人不符合</option>
                        <option value="4">印章不清晰</option>
                        <option value="5">被背书人填写不正确</option>
                    </select>
                </div>
                <div class="cb"></div>
                <div class="c7d7d7d mt16">上传验票失败凭证<span class="ml20 cd43c33">(最多上传6张)</span></div>
                <div class="w750 h365 mt16 bae0e0e0 bcwhite">
                    <div id="addImg1" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg1','picpath1')"></div>
                        <img id="img1" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath1" style="display:none;"/>
                    </div>
                    <div id="addImg2" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg2','picpath2')"></div>
                        <img id="img2" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath2" style="display:none;"/>
                    </div>
                    <div id="addImg3" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg3','picpath3')"></div>
                        <img id="img3" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath3" style="display:none;"/>
                    </div>
                    <div id="addImg4" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg4','picpath4')"></div>
                        <img id="img4" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath4" style="display:none;"/>
                    </div>
                    <div id="addImg5" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg5','picpath5')"></div>
                        <img id="img5" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath5" style="display:none;"/>
                    </div>					 
                    <div id="addImg6" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg6','picpath6')"></div>
                        <img id="img6" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath6" style="display:none;"/>
                    </div>
                    <div id="images" class="fl mt20 ml25 pr">
                        <p><img width="200" height="144" src="${image_url}/website/receive/phone2.png" onclick="fileSelect()"></p>
                        <div class="wa">
		                	<input type="file" name="fileToUpload" id="fileToUpload" class="fl w200 h24">
		                    <input type="button"  class="fl w50 h24 ml15" value="上传" id="" onclick="fileSelected('img','picpath','fileToUpload');" >
		                    <input type="hidden" class="fl" name="picpath" id="picpath"  style="display:none;">
		                    <div class="cb"></div>
		                </div>
                    </div>
                    <div class="cb"></div>
                </div>
            </div>
            <input type="hidden" id="bns_no" value=""/>
            <input type="button" value="确认" id="cancelButton" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b20 l_50 ml-130 cp" onclick="cancelDtbo()">
        </div>
    </div>
</div>

<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>
<script type="text/javascript">
/*
 * 页面初始化
 */
$(function(){
	loadData();
})

/*
 * 删除图片
 */
function deleteImg(addImg,picpath,num){
		$("#"+addImg).addClass("none");
		$("#"+picpath).val("");
		$("#images").removeClass("none");
}

/*
 * 上传图片
 */
function fileSelected(targetId, targetInputId,uploadInputId,images) {	
	var uploadId="ryUpload";	
	if(uploadInputId==undefined){
		var fileName=jQuery("#wokeUpload").val();
	}else{
		var fileName=jQuery("#"+uploadInputId).val();
		uploadId=uploadInputId;
	}
	
	var strtype=fileName.substring(fileName.length-3);
	strtype=strtype.toLowerCase();
	if (strtype!="jpg"&&strtype!="gif"&&strtype!="png"){
		alert("请上传JPG、GIF、PNG格式的图片！");
		return false;
	}
	$.ajaxFileUpload({
		url : '${basePath}/uploadpic',
		secureuri : false,
		dataType : 'json',
		data : {
		},
		fileElementId : uploadId,
		success : function(data) {
			if(data=="error"){
				alert("上传失败！");
			}else{
				for(var i=1;i<=6;i++){
					if($("#picpath" + i).val()=="" || $("#img" + i).attr("src")==null){
						$("#img" + i).attr("src", data)==null;
						$("#picpath" + i).val(data);
						$("#addImg" + i).removeClass("none");
						return;
					}
					if(i==5){
						$("#images").addClass("none");
					}
				}
			}
		},
		error : function() {
			alert("上传失败！");
		}
	});
}

/*
 * 加载数据 
 */
function loadData(){
	var no = '${no}';
	$.ajax({
		url:"${basePath}/distributeorder/get",
		data:{"no":no},
		type:"POST",
		dataType:"json",
		success:function(data){
			if(data.response == "success"){
				var data = data.data;
                if(data.distance!=null)$("#distance").text("距离您"+data.distance.toFixed(3)+"公里");
                _timer(data.timer);//倒计时（支付）
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
				$("#allmoney").html('<span class="cd43c33">'+data.allmoney+'</span>万元');
				$("#ordernumber").text(data.no);
				if(data.bank!=null)$("#bank").text(data.bank);
				if(data.endorse!=null)$("#endorse").text(data.endorse+"手");
				if(data.begintime!=null)$("#begintime").text(data.begintime);
                if(data.endtime!=null)$("#endtime").text(data.endtime);
                if(data.jxts!=null)$("#jxts").text(data.jxts+"天");
                if(data.memberother!=null)$("#memberother").text(data.memberother);
                if(data.dtdeposit!=null){
                	$("#deposit").text(data.dtdeposit);
                }else{
                	$("#deposit").text("--");
                }
                
                if(data.type1==1){
					$("#type1").text("纸票");
				}else{
					$("#type1").text("电票");
				}
				if(data.type2==1){
					$("#type2").text("国股");
				}else if(data.type2==2){
					$("#type2").text("小商");
				}else if(data.type2==3){
					$("#type2").text("外资");
				}else if(data.type2==4){
					$("#type2").text("农商");
				}else if(data.type2==5){
					$("#type2").text("农合");
				}else if(data.type2==6){
					$("#type2").text("农信");
				}else if(data.type2==7){
					$("#type2").text("村镇");
				}else{
					$("#type2").text("大商");
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
                
                var deposit_state = data.depositState;
                var state = data.state;
                if(state==1 && deposit_state==0){
                	$("#zhuangtai").text("待支付");
                	$("#mobile").text(data.membermobile);
                }else if((state==1 && deposit_state==1) || state==2 || state==4 || state==5){
                	$("#zhuangtai").text("交易中"); 
                	$("#topay").addClass("none");
                	$("#mobile").text(data.membermobile);
                }else if(state==3){
                	$("#zhuangtai").text("已完成");
                	$("#button").addClass("none");
                	$("#mobile").text(hideMobile(data.membermobile));
                }else if(state==0 || state==-1 || state==-2){
                	$("#zhuangtai").text("无效订单");
                	$("#button").addClass("none");
                	$("#mobile").text(hideMobile(data.membermobile));
                }
                if(data.picpath!=null && data.picpath !=''){
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
                var way = data.way;
			    if(way==null) way=0;//默认利率报价
                $("#way").val(way);
                if(way==0){//方式A：月利率+参数
                	var html = "";
                	if(data.price == null){
                		html +="--";
                	}else{
                		html +=data.price;
                	}
                    if(1==data.type1){//纸票
                         html += "<span class='cd43c33'>‰</span>";
                    }else{
                        html += "<span class='cd43c33'>%</span>";
                    }
                    if(data.price1!=null){
                        html += "&nbsp;+&nbsp;"+data.price1+"元";
                    }
                    $("#priceShow").html(html);
                    $("#priceShow1").html(html);
                }else if(way==1){//方式B：每十万贴现成本
                    var html = data.price2+"元";
                    $("#priceShow").html(html);
                    $("#priceShow1").html(html);
                }
                $("#bns_no").val(data.bnsNo);
			}
		}
	});
}
var no = "${no}";//机构订单号
var currentState = null;//当前银票订单状态
function initData(no){
	$.ajax({
		url:"${basePath}/distributeorder/get",
		data:{"no":no},
		dataType:"json",
		success:function(data){
			currentState = data.data.state;
		}
	});
}

/*
 * 拒绝订单理由显示
 */
function tan(){
	$("#reason").removeClass("none");
	initData(no);
}
/*
 * 拒绝订单理由隐藏
 */
$("#redClose").click(function(){
	$("#reason").addClass("none");
});
$("#cancel1").change(function(){
    var value = $(this).val();
    if(value==2){
        $("#reason1_div").addClass("none");
        $("#reason2_div").removeClass("none");
    }else if(value==5){
        $("#reason1_div").removeClass("none");
        $("#reason2_div").addClass("none");
    }else{
        $("#reason1_div").addClass("none");
        $("#reason2_div").addClass("none");
    }
});

/*
 * 银票拒绝订单（或验票失败）
 */
function cancelDtbo(){
	$("#cancelButton").attr("disabled","disabled");
    var reason = $("#cancel1").find("option").not(function(){ return !this.selected }).text();//拒绝原因（文本）
    var lostCause = "";//其他理由
    var cancel1 = $("#cancel1").val();
    var cancel2 = $("#cancel2").val();
    
    var images = '';
    $("input[name='picpath']").each(function(i,o){
    	if(images!='')images+=",";
    	images+=$(o).val();
    });
    if(cancel1==2){//票据有问题
        if(images==null || images==""){
            alert("请上传图片");
            $("#cancelButton").removeAttr("disabled");
            return;
        }
        reason = $("#cancel2").find("option").not(function(){ return !this.selected }).text();
    }else if(cancel1==5){//其他
        lostCause = $("#reason1_div").val();
        reason = lostCause;
        cancel2 = null;
        if (lostCause.length < 15) {
            uexToast("请你输入不少于15字的理由");
            $("#cancelButton").removeAttr("disabled");
            return;
        }
    }else{
        cancel2 = null;
        lostCause = "";
    }
    var data="no="+no+"&cancel1="+cancel1+"&cancel2="+cancel2+"&reason="+
    			reason+"&lostCause="+lostCause+"&currentState="+currentState+"&images="+images;
    $.ajax({
    	url:"${basePath}/distributeorder/cancel",
    	type:"POST",
    	data:data,
    	dataType:"json",
    	success:function(data){
    		if (data.response == 'success') {
    			alert("取消成功!");
    			$("#reason").addClass("none");
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
 * 接受订单
 */
function accept(){
	if(timeOut)return;//超时不能再支付
	var deposit = $("#deposit").text();
	var bnsNo = $("#bns_no").val();
	if(deposit!=null && deposit!=0){
		toPay(deposit,bnsNo);
	}else{
		$.ajax({
    		url: '${basePath}/distributeorder/accept',
			type: 'POST',
			data: {'no':no,'bail':deposit},
			dataType: 'json',
			success: function(result){
				var data = eval(result);
				var msg = "操作成功！";
				if (data.response == 'failed') {
					msg = "操作失败，请稍候再试！";
				}
				var conf = confirm(msg);
                if (conf==true){
              		window.location.href="${basePath}/distributeorder/list";
                }else{
					return;
                }
			}	
    	});
	}
}

/**
 * 支付
 */
function toPay(deposit,bnsNo){
	location.href = "${basePath}/distributeorder/pay?deposit="+deposit+"&out_trade_no="+bnsNo;
}

/**
 * 倒计时 
 */
 var timeOut = false;
function _timer(intDiff){
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
        $('#minute_show').html(minute+'分'+second+'秒');
        if(intDiff<=0){
            clearInterval(_t);
            timeOut = true;//接单时间限制
        }
        intDiff--;
    }, 1000);
}
</script>
<!--foot-->
[@main.footer/]
[/@main.body]