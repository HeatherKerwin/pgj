<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    [#include '/common/setting.ftl']
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <title>宝付-绑卡</title>
    <link rel="shortcut icon" href="https://m.utiexian.com/favicon.ico"/>
    <link href="https://static.utiexian.com/css/rymobile/baofoo.css" type="text/css" rel="stylesheet">
    <script src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
</head>
<body>
    <!--内容-->
    <div class="nav">
        <!--选择银行卡类型-->
        <div class="w bankChose">
            <div class="bankChose-l fl">
                <input type="radio" class="bankChose-radio radioItem fl" id="card_type1" name="card_type" checked value="0">
                <label for="card_type1" class="fl">借记卡</label>
            </div>
            <div class="bankChose-r fl">
                <input type="radio" class="bankChose-radio radioItem fl" id="card_type2" name="card_type" value="1">
                <label for="card_type2" class="fl">信用卡</label>
            </div>
        </div>
        <!--表单-->
        <div class="w form">
            <ul class="list">
                <li id="open_bank">
                    <div class="fl">开户行</div>
                    <img id="select_card" src="https://img.utiexian.com/rymobile/baofoo/icon-down.png" class="fr" style="height: 2rem;margin-left: 0.5rem;margin-top: 1rem;"/>
                    <input id="bank-input" type="text" class="fr" placeholder="请选择" disabled readonly>
                </li>
                <li>
                    <div class="fl">卡号</div>
                    <input type="text" id="acc_no" name="acc_no" class="fr" placeholder="请输入卡号">
                </li>
                <li>
                    <div class="fl">姓名</div>
                    <input type="text" id="id_holder" name="id_holder" class="fr" placeholder="请输入姓名">
                </li>
                <li>
                    <div class="fl">身份证号</div>
                    <input type="text" id="id_card" name="id_card" class="fr" placeholder="请输入身份证号">
                </li>
                <li id="credit1" class="none">
                    <div class="fl">有效期</div>
                    <input type="text" id="valid_date" name="valid_date" class="fr" maxlength="4" style="width:60%" placeholder="例如： 20年08月格式为2008">
                </li>
                <li id="credit2" class="none">
                    <div class="fl">安全码</div>
                    <img src="https://img.utiexian.com/rymobile/baofoo/bank-icon.png"  class="fl bankIcon">
                    <input type="text" id="cvv" name="cvv" class="fr" maxlength="3" placeholder="请输入信用卡背面最后三位数字" style="width:60%">
                </li>
                <li>
                    <div class="fl">手机号</div>
                    <input type="text" id="mobile" name="mobile" class="fr" maxlength="11" placeholder="请输入手机号">
                </li>
                <li>
                    <div class="fl">验证码</div>
                    <input id="btn" name="btn" type="button" class="fr phoneCode" value="点击获取" style="background:#d43c33 !important;">
                    <input type="text" id="sms_code" name="sms_code" class="fr" maxlength="6" placeholder="请输入验证码">
                </li>
            </ul>
        </div>
        <!--绑定按钮-->
        <input type="hidden" id="user_id" name="user_id" value="${order.memberId}"/>
       	<input type="hidden" id="type" name="type" value="${order.type}"/>
       	<input type="hidden" id="orderNo" name="orderNo" value="${order.orderNo}"/>
       	<input type="hidden" id="unique_code" name="unique_code" value=""/>
        <input class="btn" type="button" value="绑定" onclick="bindCard();">
        <a style="margin: 0px 5%;float:right;" href="${basePath}/baofoo/pay?orderNo=${order.orderNo}&type=${order.type}">去支付></a>
    </div>

    <!--弹窗-->
    <div class="maskWindow none" id="maskWindow">
        <!-- 输入提示-->
        <div class="mask-prompt none" id="bank-alert"> 
            <div class="prompt-title">提示文字</div>
            <div class="prompt-btn" id="prompt-know">知道了</div>
        </div>
        <!-- 选择银行卡-->
        <div class="mask-bank none" id="bank-window" >
            <ul>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank0" name="pay_code" value="ICBC">
                    <label for="bank0" class="fl"><img src="https://img.utiexian.com/rymobile/baofoo/bank-ICBC.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank1" name="pay_code" value="ABC">
                    <label for="bank1"><img src="https://img.utiexian.com/rymobile/baofoo/bank-ABC.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank2" name="pay_code" value="CCB">
                    <label for="bank2"><img src="https://img.utiexian.com/rymobile/baofoo/bank-CCB.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank3" name="pay_code" value="BOC">
                    <label for="bank3"><img src="https://img.utiexian.com/rymobile/baofoo/bank-BOC.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank4" name="pay_code" value="BCOM">
                    <label for="bank4"><img src="https://img.utiexian.com/rymobile/baofoo/bank-BCOM.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank5" name="pay_code" value="CIB">
                    <label for="bank5"><img src="https://img.utiexian.com/rymobile/baofoo/bank-CIB.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank6" name="pay_code" value="CITIC">
                    <label for="bank6"><img src="https://img.utiexian.com/rymobile/baofoo/bank-CITIC.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank7" name="pay_code" value="CEB">
                    <label for="bank7"><img src="https://img.utiexian.com/rymobile/baofoo/bank-CEB.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank8" name="pay_code" value="PAB">
                    <label for="bank8"><img src="https://img.utiexian.com/rymobile/baofoo/bank-PAB.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank9" name="pay_code" value="PSBC">
                    <label for="bank9"><img src="https://img.utiexian.com/rymobile/baofoo/bank-PSBC.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank10" name="pay_code" value="SHB">
                    <label for="bank10"><img src="https://img.utiexian.com/rymobile/baofoo/bank-SHB.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank11" name="pay_code" value="SPDB">
                    <label for="bank11"><img src="https://img.utiexian.com/rymobile/baofoo/bank-SPDB.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank12" name="pay_code" value="CMBC">
                    <label for="bank12"><img src="https://img.utiexian.com/rymobile/baofoo/bank-CMBC.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank13" name="pay_code" value="CMB">
                    <label for="bank13"><img src="https://img.utiexian.com/rymobile/baofoo/bank-CMB.png" class="bank-img"/></label>
                </li>
                <li>
                    <input type="radio" class="bankChose-radio fl" id="bank14" name="pay_code" value="GDB">
                    <label for="bank14"><img src="https://img.utiexian.com/rymobile/baofoo/bank-GDB.png" class="bank-img"/></label>
                </li>
            </ul>
        </div>
    </div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	var wait = 60;
	var Timestamp;
	function time(o) {
		var type = $("input:radio[name='card_type']:checked").val();
		if ($.trim($("#acc_no").val())=="") {
			myAlert("请输入银行卡号！");
			return false;
		}
		if ($.trim($("#id_holder").val())=="") {
			myAlert("请输入姓名！");
			return;
		}
		if ($.trim($("#id_card").val())=="") {
			myAlert("请输入身份证号！");
			return false;
		}
		if(type=="1"){//信用卡
			if ($.trim($("#valid_date").val())=="") {
				myAlert("请输入身份证号！");
				return false;
			}
			if ($.trim($("#cvv").val())=="") {
				myAlert("请输入身份证号！");
				return false;
			}
		}
		if ($.trim($("#mobile").val())=="") {
			myAlert("请输入手机号！");
			return;
		}
		if (wait == 0) {
			o.value = "获取验证码";
			o.removeAttribute("disabled");
			wait = 60;
			return;
		}
		if (wait == 60) {
			var params = {
				mobile : $.trim($("#mobile").val()),
				acc_no : $.trim($("#acc_no").val()),
				id_card : $.trim($("#id_card").val()),
				id_holder : $.trim($("#id_holder").val()),
				pay_code : $.trim($("#pay_code").val()),
				trans_id : $.trim($("#trans_id").val()),
				user_id : $.trim($("#user_id").val())
			};
			if(type=="1"){//信用卡
				params.valid_date = $("#valid_date").val();
				params.cvv = $("#cvv").val();
			}
			
			Timestamp = "TIDNET" + Date.parse(new Date());
			$("#trans_id").val(Timestamp);
			o.setAttribute("disabled", true);
			o.value = "获取验证码";
			var htmlobj = $.ajax({
				type : "POST",
				url : "${basePath}/baofoo/baofoo1",
				datatype : "json",
				data : params,
				success : function(data) {
					var res = jQuery.parseJSON(data);
					if (res.resp_code == "0000") {
						$("#unique_code").val(res.unique_code); 
						myAlert("短信发送成功！");
					} else {
						myAlert("短信发送失败【 " + res.resp_msg + " 】");
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
				},
				error : function() {
					myAlert("请求异常！");
				}
			});
			wait = 59;
			setTimeout(function() {
				time(o);
			}, 1000);
		} else {
			o.value = "重新发送(" + wait + ")";
			wait--;
			setTimeout(function() {
				time(o);
			}, 1000);
		}
	}
	$("#btn").click(function() {
		time(this);
	});
});

function bindCard(){
	if ($.trim($("#acc_no").val())=="") {
		myAlert("请输入银行卡号！");
		return false;
	}
	if ($.trim($("#id_holder").val())=="") {
		myAlert("请输入姓名！");
		return;
	}
	if ($.trim($("#id_card").val())=="") {
		myAlert("请输入身份证号！");
		return false;
	}
	if($("input:radio[name='card_type']:checked").val()=="1"){//信用卡
		if ($.trim($("#valid_date").val())=="") {
			myAlert("请输入身份证号！");
			return false;
		}
		if ($.trim($("#cvv").val())=="") {
			myAlert("请输入身份证号！");
			return false;
		}
	}
	if ($.trim($("#mobile").val())=="") {
		myAlert("请输入手机号！");
		return;
	}
	if ($.trim($("#sms_code").val())=="") {
		myAlert("请输入验证码！");
		return false;
	}
	
	var params = {
		unique_code : $.trim($("#unique_code").val()),
		sms_code : $.trim($("#sms_code").val())
	};
	$.ajax({
		type : "POST",
		url : "${basePath}/baofoo/baofoo2",
		datatype : "json",
		data : params,
		success : function(data) {
			var res = jQuery.parseJSON(data);
			if(res!=null && res!="" && res.resp_code == "0000") {
				myConfirm("T","绑定成功！");
			}else if(res!=null && res!=""){
				myAlert("请求失败【 " + res.resp_msg + " 】");
			}else{
				myAlert("请求异常！");
			}
		}
	});
}

$("input:radio[name='pay_code']").change(function() {
    var temp = $(this).val();
    $("#select_card").attr("src","https://img.utiexian.com/rymobile/baofoo/bank-" + temp + ".png");
    $("#maskWindow").addClass("none");
    $("#bank-window").addClass("none");
    
    $("#bank-input").removeAttr("placeholder");
});

$("#bank-window").on('click', function(){
	$("#maskWindow").addClass("none");
    $("#bank-window").addClass("none");
});

//选择卡种
$("input:radio[name='card_type']").change(function () {
	var temp = $(this).val();
    if (temp == 0) {
        $("#credit1").addClass("none");
        $("#credit2").addClass("none");
        $("#valid_date").val("");
    	$("#cvv").val("");
    } else {
        $("#credit1").removeClass("none");
        $("#credit2").removeClass("none");
    }
});

//选择银行卡
$("#open_bank").on('click', function(){
    $("#maskWindow").removeClass("none");
    $("#bank-window").removeClass("none");
});

//禁止输入键盘
$("#bank-input").focus(function(){
    document.activeElement.blur();
});

function myAlert(msg){
	myConfirm("F",msg);
}

var _state = "F";
function myConfirm(o,msg){
	_state = o;
	$(".prompt-title").text(msg);
	$("#maskWindow").removeClass("none");
    $("#bank-alert").removeClass("none");
}

//知道了
$("#prompt-know").on('click', function(){
	if("T"==_state){
		window.location.href = "${basePath}/baofoo/pay?orderNo=${order.orderNo}&type=${order.type}";
	}
	$("#maskWindow").addClass("none");
    $("#bank-alert").addClass("none");
});
</script>
</html>