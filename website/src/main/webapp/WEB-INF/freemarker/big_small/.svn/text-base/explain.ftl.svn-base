[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='换票说明']
[@main.header currentmenu='1' topindex='2'/]
<link href="${cssPath}/website/daxiaopiao-190109/reste.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/common.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/reviewTab.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/shuoming.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/review.css" rel="stylesheet">

<script type="text/javascript" src="${comPath}/jquery-1.8.3.min.js"></script>
	<div class="reviewWarp">

		<!-- 切换 -->
		<div class="reviewTab clearfix">
			<div onclick="enterprise();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/shehe.png" alt="企业预审">
				<p>企业预审</p>
			</div>
			<div onclick="bigSmall();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/dapiao.png" alt="大票换小票">
				<p>大票换小票</p>
			</div>
			<div onclick="smallBig();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/xiaopiao.png" alt="小票换大票">
				<p>小票换大票</p>
			</div>
			<div onclick="order();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/dingdan.png" alt="换票订单">
				<p>换票订单</p>
			</div>
		</div>
	<!-- 切换 end -->

	<div class="liucheng clearfix">
		<div class="liuchengTitle">
     		换票流程
		</div>
		<div class="liuchengCon clearfix">
			<div>
				<span>1</span> 企业预审
			</div>
			<div>
				<span>2</span> 银行开户
			</div>
			<div>
				<span>3</span> 提交换票申请
			</div>
			<div>
				<span>4</span> 电票系统进行换票操作
			</div>
		</div>
	</div>

	<!-- content -->
	<div class="reviewCon">
		<div class="shuoming">
			<h2>注意事项</h2>
			<p>
				1、换票业务目前仅限于实际经营地址在上海市的企业申请，其他城市暂未开通此业务。<br>
				<br>
				2、提交企业预审信息后，银行客户经理会致电联系企业的联系人，进行线下审核。若公司的注册资本在3000万元以下，需要前往宁波银行进行开户，或者支付200元上门费用，邀请银行上门开户，若公司的注册资本在3000万元以上，银行会免费上门开户。<br>
				<br>
				3、大小票互换业务是为了方便企业用户置换需要的票据所提供的业务。举例如下：若企业有一张100万的票据，而需要支付30万货款给A企业，支付70万货款给B企业，可以来平台上提交换票申请，将手上的100万票据换成两张票面分别为30万和70万的票据。<br>
				<br> 4、置换出的票据金额，必须小于或等于贸易合同上提及的货款金额，置换的票据张数，必须小于或等于贸易合同的份数。<br>
				<br> 5、换票业务置换到的票据到期日晚于当前待置换票据的到期日，错配期限最好超过一个月。<br>
				<br> 6、企业初审未通过时，任然可以提交置换请求，但未开户成功时，票据无法在银行完成置换。
			</p>
		</div>
	</div>
</div>
<script type="text/javascript">
/**
 *  大小票的跳转
 */
function enterprise(){
	$.ajax({
		url:"${bootAppPath}/draftexchangecheck/get/by/memberid",
		type:"POST",
		data:{memberId:memberId},
		dataType:"json",
		success:function(data){
			console.log(data);
			if(data.data.response == 'success'){
				var data = data.data.data;
				if(data!=null&&data!=""){
					if(data.state == "PENDING"){
						 window.location.href ="${basePath}/bigsmall/qiye/xiangqing";
					}else if(data.state == "AUTH"){
						 window.location.href ="${basePath}/bigsmall/qiye/xiangqing";
					}else if(data.state == "CHECK_FAILED"){
						window.location.href ="${basePath}/bigsmall/qiye/xiangqing";
					}else if(data.state == "SUCCESS"){
						window.location.href ="${basePath}/bigsmall/qiye/xiangqing";
					}else if(data.state == "AUTH_FAILED"){
						window.location.href ="${basePath}/bigsmall/qiye/xiangqing";
					}
				}else{
					 window.location.href ="${basePath}/bigsmall/qiye/yushen";
				}
			}else{
				layer.alert(data.data.msg);
			}
		},error:function(json){
			layer.alert("获取信息失败！");
		}
	});
}

/**
 *  大换小的跳转
 */
function bigSmall(){
	window.location.href ="${basePath}/bigsmall/big";
}

/**
 *  小换大的跳转
 */
function smallBig(){
	window.location.href ="${basePath}/bigsmall/small";
}

/**
 *  大小票的订单的跳转
 */
function order(){
	window.location.href ="${basePath}/bigsmall/big/list";
}
</script>
[/@main.body]