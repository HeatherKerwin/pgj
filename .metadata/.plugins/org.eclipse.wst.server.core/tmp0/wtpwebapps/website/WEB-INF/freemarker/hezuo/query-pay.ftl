[#include "/common/setting.ftl"]
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>银票询价</title>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/hezuo/main.css"/>
</head>
<body>
	<div class="w940 h420 bc bcwhite pr">
		<div class="w bcf4f4f4 h50 lh50 f24 ti50 fb">银票查询查复支付</div>
	    <div class="w938 bae0e0e0 h370">
	    	<div class="w838 ml50 mr50 lh50">
	            <div class="w h50 cl mt20 bbe0e0e0">
	                <div class="fl">保证金支付方式：</div>
	      			<input type="radio" name="payWay" id="payWay1" class="fl w12 h12 mt20 ml20 TXradio radio" checked="checked" value="3">
			        <label for="payWay1"><img src="http://img.utiexian.com/website/img/logo-kuaiqian.png" class="fl w60 mt10 ml5"/></label>
			        <input type="radio" name="payWay" id="payWay2" class="fl w12 h12 mt20 ml20 TXradio radio" value="4">
			        <label for="payWay2"><img src="http://img.utiexian.com/website/baofoo/baofu-logo.png" class="fl w85 mt10 ml5"/></label>
	            </div>
	            <div class="w h50 cl bbe0e0e0">
	                <div class="fl tl w100">支付金额：</div>
	                <input type="hidden" id="payMoney" value="30.00"/>
	                <p class="fl" id="free">¥30</p>
	            </div>
	            <div class="w tc mt50">
                	<input type="button" class="w200 h40 lh40 f18 bcd43c33 b0 br5 cwhite tc cp" id="pay_save" readonly="readonly" value="确认支付" onclick="pay_save();">
            	</div>
            </div>
        </div>
	</div>
	<div class="maskWindow none" id="window-pay">
	    <div class="maskWindowCon">
            <div class="w tc">
                <div class="f26 fb mt50">温馨提示</div>
                <div class="f20 mt20 lh50">请您在新打开的支付页面上进行支付。<br>支付完成前，请不要关闭该窗口。</div>
                <div class="w bc mt50">
                    <input type="button" id="paysuccess" class="w200 h50 bcd43c33 b0 br5 cwhite tc f22 cp" value="订单跟踪">
                </div>
            </div>
	    </div>
	</div>
	<input type="hidden" id="no" value="${result.no}">
</body>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
var memberId = null;
$(document).ready(function() {
	memberId = "${result.member_id}";
});

/**
 * 保存订单
 */
function pay_save(){
	var no = $("#no").val();
    var payWay = $("input:radio[name='payWay']:checked").val();//支付方式
    $("#pay_save").attr({"disabled":"disabled"});
    $.ajax({
   		url:"${basePath}/hezuo/update",
   		type:"post",
   		async: false,
   		data:{no:no,payWay:payWay},
   		dataType:"json",
   		success:function(data){
   			if(data.response=="success"){
  				$("#window-pay").removeClass("none");
  				if(payWay==3){//块钱支付
  					window.open("${basePath}/inquirypay/bill99pay?memberId=" + memberId + "&deposit=30" + "&bnsNo=" + no);
  				}else if(payWay==4){//宝付支付
  					window.open("${basePath}/baofoo/pay?orderNo="+no+"&type=0");
  				}
   			}else{
   				alert(data.msg);
   			}
   			$("#pay_save").removeAttr("disabled");
   		}
   	});
}
   
/**
 * 点击交易跟踪
 */
$("#paysuccess").click(function(){
	location.href = "${basePath}/hezuo/query/list?mid=" + memberId;
});
</script>
</html>