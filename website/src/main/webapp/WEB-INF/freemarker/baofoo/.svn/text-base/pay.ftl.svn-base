[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='宝付支付-支付']
[@main.header currentmenu='1' topindex="0"/]

<div class="w h100 bcwhite">
    <div class="w1200 bc h100 pr">
        <img src="${image_url}/website/baofoo/baofu-logo.png" class="fl mt17" width="152" height="63">
        <div class="fl ml25 f14 lh18 c2d2d2d mt25 ble0e0e0 pl20">
            <div class="">资金用途：<span>${name}</span></div>
            <div class="">订单编号：<span>${orderNo}</span></div>
            <div class="">订单时间：<span>${orderDate}</span></div>
        </div>
        <div class="fr mr20 mt25 fb">
            <div class="f18">应付金额</div>
            <div class="">
                <span class="f30 lh30 cd43c33 mr10">${money}</span>元
            </div>
        </div>
    </div>
</div>
<div class="w1200 bc mt20 mb30">
	<form name="R01" id="R01" method="post" action="${basePath}/account/baofoo/baofoo6">
    <div class="w bcwhite pl25 pr25 pt20 pb20">
        <div class="fb">快捷支付</div>
        <div class="h2 bccccccc mt16"></div>
        <!--选择银行-->
        <div class="pb20 pl30 bcf7f7f6 bae0e0e0 mt20">
            [#if card??]
	        [#list card as c]
	        	<div class="CARDSELECT w334 h90 bae0e0e0 bcwhite fl mt20 mr40">
	                <input type="radio" name="card" value="${c.bind_id}">
	                <div class="pr">
	                    <a href="javascript:void(0);" onclick="removeCard(this);" data-id="${c.bind_id}" class="pa t-30 l320 dsb"><img src="${image_url}/website/baofoo/bank-delete.png" width="26" height="26" class="dsb"></a>
	                    <div class="tc mt16">
	                        <div class="fl">
	                            <img src="${image_url}/website/baofoo/${getBankImg(c.bank_code)}" width="232" height="45" class="b0 ml10" />
	                            <div class="c595959 f14">${c.card_no}</div>
	                        </div>
	                        <div class="fl bc778ffd f14 cwhite w65 h21 ml15 mt25">${c.bank_name}</div>
	                    </div>
	                </div>
	            </div>
	        [/#list]
	        [/#if]
            <div class="w334 h90 bcwhite fl mt20 mr40" style="border: 1px dashed #e0e0e0;">
                <a href="${basePath}/account/baofoo/bindcard?orderNo=${orderNo}" class="dsb tc">
                    <img src="${image_url}/website/baofoo/bank-add.png" width="38" height="38" class="mt16 dsb bc">
                    <div class="c2d2d2d lh27">绑定银行卡</div>
                </a>
            </div>
            <div class="cb"></div>
        </div>
        <!--验证码-->
        <div class="pt20 pb20 pl30 bcf7f7f6 bae0e0e0 mt20 lh40">
	        <label class="fl w100" for="sms_code">短信验证：</label>
	        <input type="text" id="sms_code" name="sms_code" class="fl w245 h40 f18 ti25 bae0e0e0 br5" size="6" maxlength="6" placeholder="手机验证码">
	        <input type="button" id="btn" name="btn" class="fl w120 h44 bcf2f2f2 c727272 bae0e0e0 br3 ml10 cp" value="点击获取">
	        <div class="cb"></div>
        </div>
        <div class="wa mt30 mb30 lh60">
        	<input type="hidden" id="user_id" name="user_id" value="${memberId}"/>
        	<input type="hidden" id="txn_amt" name="txn_amt" value="${money}"/>
        	<input type="hidden" id="session_id" name="session_id" value="${queryOrderNo}"/>
        	<input type="hidden" id="trans_id" name="trans_id" value="${orderNo}"/>
        	<input type="hidden" id="business_no" name="business_no" value=""/>
        	<input type="hidden" id="device_id" name="device_id" value=""/>
        	<input type="hidden" id="bind_id" name="bind_id" value=""/>
            <input type="submit" name="Submit" class="w250 h60 b0 br5 cwhite tc f24 cp fr" disabled="disabled" value="正在初始化...">
            <div class="cb"></div>
        </div>
    </div>
    </form>
</div>
<script id="deviceScript" type="text/javascript" src="https://fk.baofoo.com/js/member/getDevice.js">${strId}</script>
<script type="text/javascript">
$(document).ready(function () {
	initZhiwen();//初始化设备指纹
	
    var wait = 60;
    function time(o) {
        if ($.trim($("#bind_id").val()) == "") {
            alert("请选择本次支付的银行卡！");
            return;
        }
        if (wait == 0) {
            o.value = "获取验证码";
            o.removeAttribute("disabled");
            wait = 60;
            return;
        }
        if (wait == 60) {
            o.setAttribute("disabled", true);
            o.value = "获取验证码";
            htmlobj = $.ajax({ type: "POST",
                url: "${basePath}/account/baofoo5",
                datatype: "xml",
                data: {
                	bind_id: $.trim($("#bind_id").val()),
                    user_id: $.trim($("#user_id").val()),
                    txn_amt: $.trim($("#txn_amt").val()),
                    trans_id: $.trim($("#trans_id").val()),
                    device_id: $.trim($("#device_id").val())
                },
                success: function (data) {
                	var parsedJson = jQuery.parseJSON(data);
                    if (parsedJson.resp_code == "0000") {
                    	$("#business_no").val(parsedJson.business_no);
                        alert("短信发送成功！");
                    	wait = 59;
                        setTimeout(function () { time(o); }, 1000);
                    } else {
                    	o.removeAttribute("disabled");
                        alert("短信发送失败!错误码："+ parsedJson.resp_code +"【 "+parsedJson.resp_msg+" 】");
                    }
                },
                error: function () {
                    alert("服务器请求异常！");
                } 
            });
        } else {
            o.value = "重新发送(" + wait + ")";
            wait--;
            setTimeout(function () { time(o); }, 1000);
        }
    }
    $("#btn").click(function () { time(this); });
    $("#R01").submit(function (e) {
        if ($.trim($("#sms_code").val()) == "") {
            alert("请输入验证码！");
            return false;
        }
        if ($.trim($("#bind_id").val()) == "") {
            alert("请选择本次支付的银行卡！");
            return false;
        }
        $("input:submit[name='Submit']").removeClass("bcd43c33").attr("disabled","true").val("加载中...");//提交后禁用按钮
    });
});

/**
 * 初始化设备指纹
 */
function initZhiwen(){
	var t1 = window.setInterval(function(){
		$.ajax({ type: "POST",
	        url: "${basePath}/account/zhiwen",
	        datatype: "json",
	        data: {
	        	session_id: $.trim($("#session_id").val())
	        },
	        success: function (data) {
	        	var res = jQuery.parseJSON(data);
	        	var d_id = res.device_id;
	        	if(d_id!=null && $.trim(d_id)!=""){
	        		window.clearInterval(t1);
	        	}
	        	$("#device_id").val(d_id);
	        	$("input:submit[name='Submit']").addClass("bcd43c33").removeAttr("disabled").val("立即支付");//提交后禁用按钮
	        },
	        error: function () {
	            alert("请求数据加密异常！");
	        } 
	    });
	},2000);
}

/**
 * 解除绑定卡
 */
function removeCard(tag){
	if(confirm("解除绑定该卡？")){
		var bind_id = $(tag).attr("data-id");
		$.ajax({
			type: "POST",
	        url: "${basePath}/account/baofoo3",
	        datatype: "json",
	        data: {
	        	type: "3",
	        	bind_id: $.trim(bind_id),
	        	user_id: $.trim($("#user_id").val())
	        },
	        success: function (data) {
	        	$(tag).parent().parent().remove();
	        	if(bind_id==($.trim($("#bind_id").val()))){//如果删除的是当前选中的卡要清空bind_id
	        		$("#bind_id").val("");
	        	}
	        },
	        error: function () {
	            alert("请求数据加密异常！");
	        } 
	    });
	}
}

$(".CARDSELECT").click(function() {
    var ra = $(this).find("input");
    $("input:radio[name='card']").removeAttr("checked").prop('checked',false);
    $("input:radio[name='card'][value='"+ ra.val() +"']").attr("checked",'true').prop('checked',true);
    $(".CARDSELECT").not(this).removeClass("bad43c33").addClass("bae0e0e0");
    $(this).removeClass("bae0e0e0").addClass("bad43c33");
    
    $("#bind_id").val(ra.val());
});
</script>
[@main.footer/]
[/@main.body]