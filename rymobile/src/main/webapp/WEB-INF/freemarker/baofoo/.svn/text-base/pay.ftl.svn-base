<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    [#include '/common/setting.ftl']
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <title>宝付-支付</title>
    <link rel="shortcut icon" href="https://m.utiexian.com/favicon.ico"/>
    <link href="https://static.utiexian.com/css/rymobile/baofoo.css" type="text/css" rel="stylesheet">
    <script src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
</head>
<body>
    <!--内容-->
    <form name="R01" id="R01" method="post" action="${basePath}/baofoo/baofoo6">
    <div class="nav">
        <!--订单金额-->
        <div class="w form form-pay">
            <ul class="list">
                <li id="toggle-pay">
                	<div class="fl">应付金额</div>
                    <div class="fl pay-money">${order.money}元</div>
                    <img src="https://img.utiexian.com/rymobile/baofoo/icon-down-red.png" class="fr choseIcon" id="toggle-img" />
                </li>
            </ul>
            <ul class="list-pay none" id="list-pay">
                <li>
                    <div class="fl">资金用途：</div>
                    <div class="fl">${order.name}</div>
                </li>
                <li>
                    <div class="fl">订单编号：</div>
                    <div class="fl">${order.orderNo}</div>
                </li>
                <li>
                    <div class="fl">订单时间：</div>
                    <div class="fl">${order.orderDate}</div>
                </li>
            </ul>
        </div>

        <!-- 选择银行卡-->
        <div class="w form">
            <ul class="list-pay-chose">
            	[#if card??]
	        	[#list card as c]
		        	<li>
	                    <input type="radio" class="bankChose-radio pay-choseRadio fl radioItem" id="card-${c_index}" name="card" value="${c.bind_id}">
	                    <label for="card-${c_index}" class="fl">
	                        <img src="https://img.utiexian.com/rymobile/baofoo/bankIcon-${c.bank_code}.png" class="pay-choseIcon fl">
	                        <div class="fl bank-text">
	                            <div>${c.bank_name}<span class="">卡种</span></div>
	                            <div>${c.card_no}</div>
	                        </div>
	                    </label>
	                    <div class="fr pay-delete" data-id="${c.bind_id}">删除</div>
	                </li>
	        	[/#list]
	        	[/#if]
            </ul>
            <a class="bank-add" href="${basePath}/baofoo/bindcard?orderNo=${order.orderNo}&type=${order.type}">
                <img src="https://img.utiexian.com/rymobile/baofoo/bank-add.png" class="fl">
                <span class="fl">再绑定一个新卡</span>
            </a>
        </div>
        <div class="w form">
            <ul class="list">
                <li>
                    <div class="fl">验证码</div>
                    <input id="btn" name="btn" type="button" class="fr phoneCode" value="点击获取" style="background:#d43c33 !important;">
                    <input type="text" id="sms_code" name="sms_code" class="fr" maxlength="6" placeholder="手机验证码">
                </li>
            </ul>
        </div>
        <input type="hidden" id="user_id" name="user_id" value="${order.memberId}"/>
       	<input type="hidden" id="txn_amt" name="txn_amt" value="${order.money}"/>
       	<input type="hidden" id="session_id" name="session_id" value="${queryOrderNo}"/>
       	<input type="hidden" id="trans_id" name="trans_id" value="${orderNo}"/>
       	<input type="hidden" id="type" name="type" value="${order.type}"/>
       	<input type="hidden" id="business_no" name="business_no" value=""/>
       	<input type="hidden" id="device_id" name="device_id" value=""/>
       	<input type="hidden" id="bind_id" name="bind_id" value=""/>
        <input class="btn-no" name="Submit" type="submit" disabled="disabled" value="正在初始化...">
    </div>
    </form>
</body>
<script id="deviceScript" type="text/javascript" src="https://fk.baofoo.com/js/member/getDevice.js">${strId}</script>
<script type="text/javascript">
$(document).ready(function () {
	initZhiwen();//初始化设备指纹
	
    var wait = 60;
    function time(o) {
        if ($.trim($("#bind_id").val()) == "") {
            alert("请输入绑定标识！");
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
            var htmlobj = $.ajax({ type: "POST",
                url: "${basePath}/baofoo/baofoo5",
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
                    } else {
                        alert("短信发送失败!错误码："+ parsedJson.resp_code +"【 "+parsedJson.resp_msg+" 】");
                    }
                },
                error: function () {
                    alert("服务器请求异常！");
                } 
            });
            wait = 59;
            setTimeout(function () { time(o); }, 1000);
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
        $("input:submit[name='Submit']").removeClass("btn").addClass("btn-no").attr("disabled","true").val("加载中...");//提交后禁用按钮
    });
});

/**
 * 初始化设备指纹
 */
function initZhiwen(){
	var _max = 7;//最大请求数量
	var _size = 0;
	var t1 = window.setInterval(function(){
		$.ajax({ type: "POST",
	        url: "${basePath}/baofoo/zhiwen",
	        datatype: "json",
	        data: {
	        	session_id: $.trim($("#session_id").val())
	        },
	        success: function (data) {
	        	var res = jQuery.parseJSON(data);
	        	if(res!=null && res.device_id!=null && $.trim(res.device_id)!=""){
		        	$("#device_id").val(res.device_id);
		        	$("input:submit[name='Submit']").removeClass("btn-no").addClass("btn").removeAttr("disabled").val("立即支付");//提交后禁用按钮
	        		window.clearInterval(t1);
	        	}
	        	if(_size>=_max)window.clearInterval(t1);
	        	_size++;
	        },
	        error: function () {
	        	if(_size>=_max)window.clearInterval(t1);
	        	_size++;
	        } 
	    });
	},2200);
}

/**
 * 解除绑定卡
 */
 $(".pay-delete").on("click",function () {
	if(confirm("解除绑定该卡？")){
		var bind_id = $(this).attr("data-id");
		$(this).parent().remove();
    	if(bind_id==($.trim($("#bind_id").val()))){//如果删除的是当前选中的卡要清空bind_id
    		$("#bind_id").val("");
    	}
		$.ajax({
			type: "POST",
	        url: "${basePath}/baofoo/baofoo3",
	        datatype: "json",
	        data: {
	        	type: "3",
	        	bind_id: $.trim(bind_id),
	        	user_id: $.trim($("#user_id").val())
	        },
	        success: function (data) {
	        	$(this).parent().remove();
	        	if(bind_id==($.trim($("#bind_id").val()))){//如果删除的是当前选中的卡要清空bind_id
	        		$("#bind_id").val("");
	        	}
	        },
	        error: function () {
	            alert("请求数据加密异常！");
	        } 
	    });
	}
});

//查看金额详情
$("#toggle-pay").toggle(function(){
        $("#toggle-img").attr("src","https://img.utiexian.com/rymobile/baofoo/icon-up-red.png");
        $("#list-pay").removeClass("none");
    },function(){
        $("#toggle-img").attr("src","https://img.utiexian.com/rymobile/baofoo/icon-down-red.png");
        $("#list-pay").addClass("none");
    }
);

//选择卡种
$("input:radio[name='card']").change(function () {
	var temp = $(this).val();
	$("#bind_id").val(temp);
});
</script>
</html>