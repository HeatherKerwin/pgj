[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.piaoju2]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1'/]

<!--打款收款指南-->
<div class="w1200 bc ha pb20 bcf9f9f9 bae0e0e0 mb20">
	[@main.secondaryheader topindex="2"/]
    <!--左菜单-->
    [@main.left remark='4' leftindex='8' /]
    <div class="fl w997 pb20 bcwhite bae0e0e0" style="min-height:700px;">
        <div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">打款收款指南</div>
        </div>
        <div class="w900 ha bc f15 c717583 lh27">
        	<h2 class="f26 tc c2d2d2d mt30">打款说明</h2>
        	<img alt="" src="${image_url}/website/help/piaojuxuyuan/pay/pay1.png" width="900" height="266" class="dsb mt20">
        	<p class="mt25">打款后，票款会自动划转到兴业票据资金流转专户，直到您确认背书签收后，才会发起票据校验，票据核验完毕确认已背书到您的绑定账户后，方可放款，交易过程双方取消订单，票款也会原路返还到资方账户。</p>
            <h2 class="f18 tc cblack mt25">打款指南</h2>
            <div class="bcd43c33 w25 h3 mt3 bc"></div>
            <p class="mt13">若充值时关闭了充值页面，您仍然可以在查看打款进度时，在打开的执剑人系统上继续进行充值。<br>说明：操作执剑人系统进行充值时需要<a href="javascript:void(0);" target="_bank" class="cd43c33 org_login_url">登录执剑人系统</a>，<span class="cd43c33">您的登录用户名为：<span id="orgUsername"></span>，第一次登录请重置密码后在登录。</p>
            <h2 class="f18 cblack mt35">1、登录“执剑人”系统</h2>
            <img alt="" src="${image_url}/website/help/piaojuxuyuan/pay/pay2.png" width="900" height="327" class="dsb mt13">
            <h2 class="f18 cblack mt35">2、点击“登录”按钮，进入系统页面</h2>
            <img alt="" src="${image_url}/website/help/piaojuxuyuan/pay/pay3.png" width="900" height="327" class="dsb mt13">
            <h2 class="f18 cblack mt35">3、点击“充值”按钮，进行充值</h2>
            <img alt="" src="${image_url}/website/help/piaojuxuyuan/pay/pay4.png" width="900" height="327" class="dsb mt13">
            <h2 class="f18 cblack mt35">打款流程</h2>
            <img alt="" src="${image_url}/website/help/piaojuxuyuan/pay/pay5.png" width="900" height="187" class="dsb mt13">
            <h2 class="f18 tc cblack mt25">提现指南</h2>
            <div class="bcd43c33 w25 h3 mt3 bc"></div>
            <p class="mt20">资方打款后，票款会自动划转到兴业票据资金流转专户，直到票据核验完毕确认已背书已被资方签收后，方可放款，交易过程双方取消订单，票方撤销背书后，票款才会原路返还到资方账户。</p>
            <p class="mt20">如需将票款提现，您可以在打开的执剑人系统上进行提现。</p>
			<p class="mt20">说明：操作执剑人系统进行提现时需要<a href="javascript:void(0); target="_bank" class="cd43c33 bns_login_url">登录执剑人系统</a>，<span class="cd43c33">您的登录用户名为：<span id="bnsUsername"></span>，第一次登录请重置密码后在登录。</p>
            <h2 class="f18 cblack mt35">当点击“提现”按钮时，弹出的提现弹窗</h2>
            <img alt="" src="${image_url}/website/help/piaojuxuyuan/pay/pay6.png" width="900" height="377" class="dsb mt13">
        </div>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]
<script type="text/javascript">
var memberId = '${member.id}';
$(document).ready(function (){
	console.log("haha");
	loadOrgInitPwd();
	loadBnsInitPwd();
});
/**
 * 加载机构方的初始密码
 */
function loadOrgInitPwd(){
	var params = {
		memberId:memberId,
		type:'1'
	};
	var url = "${bootAppPath}/cib/corp/query";
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		console.log(data);
		if(data.data!=null){
			if(data.data.login_url != null ||typeof(data.data.login_url)!="undefined"){
				$(".org_login_url").attr("href",data.data.login_url);
			}
			if(typeof(data.data.cib)!="undefined"){
				var contactMobile = data.data.cib.contactMobile;
				var corp_no = data.data.cib.corpNo;
				$("#orgUsername").text(contactMobile);
				$("#orgPwd").text(corp_no.substring((corp_no.length-6),corp_no.length));
			}
		}
	}
}
/**
 * 加载企业方的初始密码
 */
 function loadBnsInitPwd(){
	var params = {
		memberId:memberId,
		type:'0'
	};
	var url = "${bootAppPath}/cib/corp/query";
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		console.log(data);
		if(data.data.login_url != null ||typeof(data.data.login_url)!="undefined"){
			$(".bns_login_url").attr("href",data.data.login_url);
		}
		if(data.data!=null){
			if(typeof(data.data.cib)!="undefined"){
				var contactMobile = data.data.cib.contactMobile;
				var corp_no = data.data.cib.corpNo;
				$("#bnsUsername").text(contactMobile);
				$("#bnsPwd").text(corp_no.substring((corp_no.length-6),corp_no.length));
			}
		}
	}
}

</script>
<!--foot-->
[@main.footer/]
[/@main.body]