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
	            <li>
	                <p class="ml120 w340 c868585" id="tips">您已提交开户申请绑定，请耐心等待审核。</p>
	            </li>
	            <li class="formItem">
	                <button type="button" class="none" id="btn">重新开户</button>
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
    getInfo();
});

/**
 * 加载开户的信息
 */
function getInfo(){
	var url = '${bootAppPath}/jdjrbind/get/jdjr/jdjrbind';
	var params = {memberId:memberId};
	var data = bootPost(url,params);
	if(data != null){
		console.log(data);
		if(data.data.response == 'success'){
			if(data.data.data!=null){
				var jdjr = data.data.data.jdjr;
				var jdjrBind = data.data.data.jdjrBind;
				$("#blicCompanyName").val(jdjr.blicCompanyName);
				$("#occBankAccount").val(jdjr.occBankAccount);
				if(jdjrBind!=null&&jdjrBind.checkState!=""&&jdjrBind.checkState=="NOPASS"){
					$("#btn").removeClass("none");
					$("#tips").html("你的开户申请绑定未通过，你可以重新开户！");
				}
			}
		}else{
			alert(data.data.msg);
		}
	}
}

/**
 * 用户重新开户
 */
$("#btn").click(function(){
	location.href="${basePath}/jd/apply/open/account";
});
</script>