[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]

<style>
.mark{
	margin-top:-15px;
	margin-bottom:20px;
	color:gray;
	font-size:9px;
}
</style>
<div class="content mt16 w1200 bc mb20 pl20 pr20 pb75 bcwhite">
	<form id="thisForm" action="${basePath}/test/pay/do" class="alipayform" method="POST" target="_blank">
		<div class="element" style="margin-top: 60px;">
			<div class="legend">支付宝即时到账交易接口快速通道</div>
		</div>
		<div class="element">
			<div class="etitle">商户订单号:</div>
			<div class="einput">
				<input type="text" name="out_trade_no" id="out_trade_no">
			</div>
			<br>
			<div class="mark">注意：商户订单号(out_trade_no).必填(建议是英文字母和数字,不能含有特殊字符)</div>
		</div>

		<div class="element">
			<div class="etitle">商品名称:</div>
			<div class="einput">
				<input type="text" name="subject" value="这是一个虚拟商品（测试）" id="subject">
			</div>
			<br>
			<div class="mark">注意：产品名称(subject)，必填(建议中文，英文，数字，不能含有特殊字符)</div>
		</div>
		<div class="element">
			<div class="etitle">付款金额:</div>
			<div class="einput">
				<input type="text" name="total_fee" id="total_fee" value="0.01">
			</div>
			<br>
			<div class="mark">注意：付款金额(total_fee)，必填(格式如：1.00,请精确到分)</div>
		</div>
		<div class="element">
			<div class="etitle">商品描述:</div>
			<div class="einput">
				<input type="text" name="body" id="body" value="这是商品描述（测试）">
			</div>
			<br>
			<div class="mark">注意：商品描述(body)，选填(建议中文，英文，数字，不能含有特殊字符)</div>
		</div>
		<div class="element">
			<input type="button" class="alisubmit" value="确认支付" onclick="toSubmit();">
		</div>
	</form>
	
	<br>
	<input type="button" class="alisubmit" value="打开页面传值" onclick="myOpenPage();">
</div>


<div id="my-panle" class="w220 pa zi10" style="top:40%;left:40%;display:none;">
	<div class="pa w20 h20 t-10 r-10 TXclose zi13"></div>
	<div class="w220 h30 f14 lh30 bc778ffd cwhite tc">提示</div>
	<div class="w220 bcf7f7f6 f12 lh30 pt25 pb20" style="background-color:#CFCFCF;">
		<div class="ml10" onclick="payState('T');">支付成功</div>
		<div class="ml10" onclick="payState('F');">支付失败</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var r = "A";
	for(var i=0;i<20;i++){
		r += Math.floor(Math.random()*10);
	}
	$("#out_trade_no").val(r);
});

function toSubmit(){
	$("#my-panle").show();
	$("#thisForm").submit();
}

/**
 * 支付验证
 * 注：[成功跳转列表、失败则可以继续支付]具体怎么做自己结合业务和天猫等支付方式在做调整
 */
function payState(){
	var no = $("#out_trade_no").val();
	$.ajax({
		type : 'post',
		url : '${basePath}/test/query',
		data : {"out_trade_no":no},
		dataType : "json",
		success : function(data) {
			if(data.is_success=="F"){
				alert("查询状态："+data.is_success);
			}else{
				alert("查询状态："+data.is_success+" "+data.trade_status+" "+data.buyer_email);
			}
		},
		error : function() {
			layer.alert("网络异常！");
		}
	});
}

/**
 * 这是测试页面
 */
function myOpenPage(){
	var map = new Map();
	map.put("_PAGE", "test");//必传
	map.put("name", "张三");
	map.put("pwd","123456");
	_OPENPAGE_FORM(map);
}
</script>

[@main.footer/]
[/@main.body]