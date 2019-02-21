[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='完成开户']
[@main.header currentmenu='1'/]

    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/form.css">

<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
	<div class="fl w997 box-shadow bcwhite box-sizing bc pl50 pr50 pt30 pb30">
	    <!-- 标题 -->
	    <div class="clearfix bbe0e0e0 pb10">
	        <div class="fl">开户管理</div>
	    </div>
	    <!-- 标题 end -->
	
	    <div class="w570 bc">
	        <ul class="formGroup f14 c2d2d2d lh20">
	            <li class="formItem">
	                <label class="w120">户名：</label>
	                <input type="text" id="blicCompanyName" readonly value="">
	            </li>
	            <li class="formItem ">
	                <label class="w120">账户：</label>
	                <input type="text" class="w340" id="occBankAccount" readonly value="">
	            </li>
	            <li class="formItem">
	                <label class="w120">小额款项：</label>
	                <input type="number" id="money" class="w340" placeholder="请输入该银行卡收到的小额款项" oninput="money();">
	                <span class="formUnit">元</span>
	            </li>
	            <li>
	                <p class="ml120 w340 c868585">客服已帮您提交开户申请，关联银行账号里会收到一笔小额款项，将收到的金额填写上，最后点击“完成开户”，即可完成开户。</p>
	            </li>
	            <li class="formItem">
	                <button type="button" id="btn" onclick="save();" disabled>完成开户</button>
	            </li>
	        </ul>
	    </div>
	</div>
</div>
<div class="cb"></div>
[@main.footer/]
[/@main.body]
<script type="text/javascript">
var memberId = "${member.id}";
$(function () {
	var blicCompanyName = localStorage.getItem("blicCompanyName");
	var bankAccountNo = localStorage.getItem("bankAccountNo");
	
	$("#blicCompanyName").val(blicCompanyName);
	$("#occBankAccount").val(bankAccountNo);
});

/**
 * 用户输入金额
 */
function money(){
	var money = $("#money").val();
	if(money.length>0){
		$("#btn").attr("disabled",false);
	}else{
		$("#btn").attr("disabled",true);
	}
}

/**
 * 保存小额鉴定
 */
function save(){
	var amount = $("#money").val();
	var payOrderId = localStorage.getItem("payOrderId");
	var url = '${bootAppPath}/jdjrcard/verifyAgencyDefpay';
	var params = {payOrderId:payOrderId,amount:(amount*100),memberId:memberId};
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			location.href = "${basePath}/jd/success";
		}else{
			alert(data.data.msg);
		}
	}
}
</script>