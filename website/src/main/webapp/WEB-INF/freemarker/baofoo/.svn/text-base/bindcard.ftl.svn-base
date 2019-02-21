[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='宝付支付-银行卡绑定']
[@main.header currentmenu='1' topindex="0"/]

<style type="text/css">
input[type=radio].radio3{
    background: url("${image_url}/website/img/radio2.png") no-repeat center;
    background-size: 100%;
    background-position: 0 -16px;
}
input[type=radio].radio3:checked {
    background: url("${image_url}/website/img/radio2.png") center no-repeat;
    background-size: 100%;
    background-position: 0 0;
}
</style>
<div class="w h100 bcwhite">
    <div class="w1200 bc h100 pr">
        <img src="${image_url}/website/baofoo/baofu-logo.png" class="fl mt17" width="152" height="63">
        <a id="toPay" href="${basePath}/account/baofoo/pay?orderNo=${orderNo}" class="fr cd43c33 mr20 lh100 f18">去支付>></a>
    </div>
</div>
<div class="w1200 bc mt20">
    <div class="w1200 bc mt20 mb20 bcwhite">
        <div class="w bcf4f4f4 h100 lh100 f36 ti65 fb">银行卡绑定</div>
        <div class="w1198 bae0e0e0">
            <div class="ml100 mr100">
                <div class="wa mt30 lh40">
                    <div class="fl w100 ml65">票据属性：</div>
                    <div class="fl w102 Interestdiv1">
                        <input type="radio" name="card_type" id="Interest1" value="0" class="fl w16 h16 mt12 radio3" checked="checked">
                        <label class="fl ml10 ce84c3d" for="Interest1">借记卡</label>
                    </div>
                    <div class="fl w102 Interestdiv1">
                        <input type="radio" name="card_type" id="Interest2" value="1" class="fl w16 h16 mt12 radio3">
                        <label class="fl ml10 ce84c3d" for="Interest2">信用卡</label>
                    </div>
                    <div class="cb"></div>
                </div>
                <div class="wa mt30 lh40">
                    <label class="fl w100 ml65" for="pay_code">开户行：</label>
                    <select id="pay_code" name="pay_code" class="fl w245 h40 select245 f18 ti25">
                        <option value="ICBC">中国工商银行</option>
						<option value="ABC">中国农业银行</option>
						<option value="CCB">中国建设银行</option>
						<option value="BOC">中国银行</option>
						<option value="BCOM">中国交通银行</option>
						<option value="CIB">兴业银行</option>
						<option value="CITIC">中信银行</option>
						<option value="CEB">中国光大银行</option>
						<option value="PAB">平安银行</option>
						<option value="PSBC">中国邮政储蓄银行</option>
						<option value="SHB">上海银行</option>
						<option value="SPDB">浦东发展银行</option>
						<option value="CMBC">中国民生银行</option>
						<option value="CMB">招商银行</option>
						<option value="GDB">广发银行</option>
                    </select>
                    <div class="cb"></div>
                </div>
                <div class="wa mt30 lh40">
                    <label class="fl w100 ml65" for="acc_no">卡号：</label>
                    <input id="acc_no" name="acc_no" type="text" class="fl w245 h40 f18 ti25 bae0e0e0 br5" placeholder="请输入银行卡号">
                    <div class="cb"></div>
                </div>
                <div class="wa mt30 lh40">
                    <label class="fl w100 ml65" for="id_holder">姓名：</label>
                    <input id="id_holder" name="id_holder" type="text" class="fl w245 h40 f18 ti25 bae0e0e0 br5" placeholder="请输入姓名">
                    <div class="cb"></div>
                </div>
                <div class="wa mt30 lh40">
                    <label class="fl w100 ml65" for="IDcard">身份证号：</label>
                    <input id="id_card" name="id_card" type="text" class="fl w245 h40 f18 ti25 bae0e0e0 br5" placeholder="请输入身份证号">
                    <div class="cb"></div>
                </div>
                <div id="CARD_XYK" class="none">
                    <div class="wa mt30 lh40">
                        <label class="fl w100 ml65" for="valid_date">有效期：</label>
                        <input id="valid_date" name="valid_date" type="text" class="fl w245 h40 f18 ti25 bae0e0e0 br5" size="4" maxlength="4" placeholder="请输入银行卡有效期">
                        <div class="fl ml10 cd43c33">信用卡输入格式 YYMM， 例：20年08月格式为2008</div>
                        <div class="cb"></div>
                    </div>
                    <div class="wa mt30 lh40">
                        <label class="fl w100 ml65" for="cvv">安全码：</label>
                        <input id="cvv" name="cvv" type="text" class="fl w245 h40 f18 ti25 bae0e0e0 br5" size="3" maxlength="3" placeholder="请输入银行卡安全码">
                        <div class="fl ml10 cd43c33">信用卡必输，银行卡背后最后三位数字</div>
                        <div class="cb"></div>
                    </div>
                </div>
                <div class="wa mt30 lh40">
                    <label class="fl w100 ml65" for="mobile">手机号：</label>
                    <input id="mobile" name="mobile" type="text" class="fl w245 h40 f18 ti25 bae0e0e0 br5" size="11" maxlength="11" placeholder="请输入手机号">
                    <div class="cb"></div>
                </div>
                <div class="wa mt30 lh40">
                    <label class="fl w100 ml65" for="sms_code">验证码：</label>
                    <input type="text" id="sms_code" name="sms_code" class="fl w245 h40 f18 ti25 bae0e0e0 br5" size="6" maxlength="6" placeholder="手机验证码">
                    <input type="button" id="btn" name="btn" class="fl w120 h44 bcf2f2f2 c727272 bae0e0e0 br3 ml10 cp" value="获取验证码">
                    <div class="cb"></div>
                </div>
                <div class="wa mt30 mb30 lh60 bte0e0e0">
                	<input type="hidden" id="user_id" name="user_id" value="${memberId}"/>
                	<input type="hidden" id="orderNo" name="orderNo" value="${orderNo}"/>
                	<input type="hidden" id="unique_code" name="unique_code" value=""/>
                	[#if memberId!=null && memberId!='']
	                    <input class="w250 h60 bcd43c33 b0 br5 cwhite tc f24 cp fr mt20" type="button" id="s-btn" value="绑定" onclick="bindCard();">
                	[#else]
	                    <input class="w250 h60 b0 br5 cwhite tc f24 cp fr mt20" type="button" disabled="disabled" id="s-btn" value="绑定">
                	[/#if]
                    <div class="cb"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var wait = 60;
	var Timestamp;
	function time(o) {
		var type = $("input:radio[name='card_type']:checked").val();
		if ($.trim($("#acc_no").val())=="") {
			alert("请输入银行卡号！");
			return false;
		}
		if ($.trim($("#id_holder").val())=="") {
			alert("请输入姓名！");
			return;
		}
		if ($.trim($("#id_card").val())=="") {
			alert("请输入身份证号！");
			return false;
		}
		if(type=="1"){//信用卡
			if ($.trim($("#valid_date").val())=="") {
				alert("请输入身份证号！");
				return false;
			}
			if ($.trim($("#cvv").val())=="") {
				alert("请输入身份证号！");
				return false;
			}
		}
		if ($.trim($("#mobile").val())=="") {
			alert("请输入手机号！");
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
				url : "${basePath}/account/baofoo/baofoo1",
				datatype : "json",
				data : params,
				success : function(data) {
					var res = jQuery.parseJSON(data);
					if (res.resp_code == "0000") {
						$("#unique_code").val(res.unique_code); 
						alert("短信发送成功！");
						wait = 59;
						setTimeout(function () { time(o); }, 1000);
					} else {
						alert("短信发送失败【 " + res.resp_msg + " 】");
						o.removeAttribute("disabled");
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
				},
				error : function() {
					alert("请求异常！");
				}
			});
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
		alert("请输入银行卡号！");
		return false;
	}
	if ($.trim($("#id_holder").val())=="") {
		alert("请输入姓名！");
		return;
	}
	if ($.trim($("#id_card").val())=="") {
		alert("请输入身份证号！");
		return false;
	}
	if($("input:radio[name='card_type']:checked").val()=="1"){//信用卡
		if ($.trim($("#valid_date").val())=="") {
			alert("请输入身份证号！");
			return false;
		}
		if ($.trim($("#cvv").val())=="") {
			alert("请输入身份证号！");
			return false;
		}
	}
	if ($.trim($("#mobile").val())=="") {
		alert("请输入手机号！");
		return;
	}
	if ($.trim($("#sms_code").val())=="") {
		alert("请输入验证码！");
		return false;
	}
	if($.trim($("#unique_code").val())==""){
		alert("本次提交不被允许！您好像没有获取验证码");
		return false;
	}
	var params = {
		unique_code : $.trim($("#unique_code").val()),
		sms_code : $.trim($("#sms_code").val())
	};
	$.ajax({
		type : "POST",
		url : "${basePath}/account/baofoo2",
		datatype : "json",
		data : params,
		success : function(data) {
			var res = jQuery.parseJSON(data);
			if(res!=null && res!="" && res.resp_code == "0000") {
				window.location.href = $('#toPay').attr("href");//绑定成功跳转支付页面
			}else if(res!=null && res!=""){
				alert("请求失败【 " + res.resp_msg + " 】");
			}else{
				alert("请求异常！");
			}
		}
	});
}

$("input:radio[name='card_type']").change(function() {
    var temp = $(this).val();
    if(temp==0){//已开出
        $("#CARD_XYK").addClass("none");
    	$("#valid_date").val("");
    	$("#cvv").val("");
    }else{//未开出
        $("#CARD_XYK").removeClass("none");
    }
});
</script>
[@main.footer/]
[/@main.body]