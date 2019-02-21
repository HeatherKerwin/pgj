[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[#include "/common/boot/cib.ftl"]
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<style>
.shadow{ box-shadow:-1px 1px 3px 2px #eee;}
.listHover:HOVER{
	border: 1px solid #d43c33;
}
</style>

<!--新开户：第一状态-第二步-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
    [@main.cib_tool /]
    <!--票方开户-->
    <div class="pl50 pr50 f14 pt30 flex flex-justify-space-between">
        <!--我的开户信息-->
        <div class="flex-row flex-direction-column w400 lh34">
        	<div class="f16 mt10">我的开户信息：</div>
            <!--企业法人营业执照-->
            <div class="flex-row flex-direction-column mt10 ">
                <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
                    <div>企业法人营业执照</div>
                    <div class="toggle">
                        <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
                    </div>
                </div>
                <div class="flex-row flex-direction-column pl7 pr7 toggleCon">
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">公司名称：</div>
                        <p id="name" class="w250">--</p>
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">注册号：</div>
                        <p id="bizLicenceRegisteredNo" class="w250">--</p>
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">法人姓名：</div>
                        <p id="bizLicenceLegalName" class="w250">--</p>
                    </div>
                </div>
            </div>

            <!--银行信息-->
            <div class="flex-row flex-direction-column mt25">
                <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
                    <div>银行信息</div>
                    <div class="toggle">
                        <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
                    </div>
                </div>
                <div class="flex-row flex-direction-column pl7 pr7 toggleCon">
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">开户行行号：</div>
                        <p id="bankAcctCnapsCode" class="w250">--</p>
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">账户名：</div>
                        <p id="showbankAcctAcctName" class="w250">--</p>
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">账号：</div>
                        <p id="bankAcctAcctNo" class="w250">--</p>
                    </div>
                </div>
            </div>

            <!--其他-->
            <div class="flex-row flex-direction-column mt10">
                <div class="flex-row h34 mt10">
                        <div class="flex-col-3">经办人：</div>
                        <p id="contactName" class="w250">--</p>
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">手机号：</div>
                        <p id="contactMobile" class="w250">--</p>
                    </div>
            </div>
        </div>
        
        <!-- 执剑人电子账户 -->
        <div class="flex-row flex-direction-column w370 pl20 h260 lh34 pb20 shadow org">
        	<div class="f16 mt10">执剑人电子账户：</div>
        	<p class="cd43c33 f12 w280 lh18 mt10 pl10 pr10">您的登录用户名为：<span class="username"></span>，第一次登录请重置密码后再登录。</p>
	        <div class="flex-row flex-direction-row mt20">
                <div class="flex-row flex-justify-center flex-alignItems-center">
                    <img src="${imagePath}website/PJGJ/account/moneyIcon.png" alt="" class="mr5">
                    账户余额（元）
                </div>
                <div class="c00a5f2 balance f16 ml10 balance">0.00
                </div>
            </div>
            <div class="flex-row flex-direction-row mt20">
                <div class="flex-row flex-justify-center flex-alignItems-center">
                    <img src="${imagePath}website/PJGJ/account/lockIcon.png" alt="" class="mr5">
                    冻结资金（元）
                </div>
                <div class="cd43c33 frozenCapital f16 ml10 frozenCapital">0.00
                </div>
            </div>
	        <div class="flex-row w flex-justify-center mt35">
	            <input type="button" class="w110 bce84c3d bad43c33 cwhite br3 h34 lh34" onclick="Showall();" value="登录执剑人">
	        </div>
        </div>
        
        <!-- 180116 -->
        <!-- 出票对公账号 -->
        <div class="flex-row flex-direction-column pl30 pr30 shadow lh30 ml16 bns w340 pb20 none">
        	<img src="http://img.lanrentuku.com/img/allimg/1212/5-121204193R0-50.gif" class="w30 h30 none"/>
        	<div class="f16 mt10">出票对公账户管理：</div>
        	<p class="cd43c33 f12 w280 lh18 mt10 pl10 pr10">您的登录用户名为：<span class="username"></span>，第一次登录请重置密码后再登录。</p>
        	<ul class="flex-row flex-direction-column mt10" id="cibBankList">
                
            </ul>
            <!-- add -->
            <div class="flex-row h60 mb16">
                <label for="bank_add" class="flex-row flex-alignItems-center flex-justify-center w300 bae0e0e0 bcwhite listHover cp">
                    <input type="button" id="bank_add" class="none">
                    <img src="https://img.utiexian.com/website/discount/jia1.png" alt="bank_add" width="30" height="30">添加
                </label>
            </div>
            <!-- add_item -->
            <div class="flex-row flex-direction-column pt15 pb15 w340 mb16 bae0e0e0 bcwhite lh34 add none">
                <!--申请绑卡-->
                <div class="flex-row flex-direction-column applyFor">
                    <input type="button" value="请选择开户行" class="bae0e0e0 ti10 h34 mt10 ml20 w270 bankBtn cp" readonly id="bankstr">
                    <input type="hidden" id="bankNo"/>
                   	<input type="hidden" id="CnapsCode"/>
                   	<input type="hidden" id="bankAcctBankBranch"/>
                    <input type="number" id="AcctNo" placeholder="请输入对公账户账号" class="bae0e0e0 ti10 h34 mt10 ml20 w270">
                    <input type="text" id="bankAcctAcctName" placeholder="请输入账户名" class="bae0e0e0 ti10 h34 mt10 ml20 w270">
                    <p class="cd43c33 f12 lh18 mt10 pl10 pr10">*请添加常用账户，绑定对公银行账户不超过四个。每次贴现时，需要选择贴现票据的最后一手背书银行账号，该账号同时也是本次贴现交易的收款账号！</p>
                    <input type="button" value="申请绑卡" onclick="applyForToCard()" class="bad43c33 cwhite h34 w150 bc mt16 bcd43c33 br3">
                </div>
                <!--完成绑卡-->
                <div class="flex-row flex-direction-column completeBind none">
                    <div class="ml20">
                        户名：<span id="bankUsername"></span>
                    </div>
                    <div class="ml20">
                        账号：<span id="no"></span>
                    </div>
                    <div class="ml20">
                        <input type="number" placeholder="请填写该银行卡收到的小额款项" oninput="checkMoney();" class="bae0e0e0 ti10 h34 mt10 w270 mr10 money">元
                    </div>
                    <input type="hidden" id="cibBankId"/>
                    <p class="cd43c33 f12 lh18 mt10 pl10 pr10">已提交开户申请，申请绑定的银行账号里会收到一笔小额款项，将收到的金额填写上，最后点击“完成绑卡”，即可完成绑卡。</p>
                    <!--小额款项未输入了不可点击（灰色）-->
                    <input type="button" value="完成绑卡" id="finishAccount" class="bae0e0e0 bcf9f9f9 c2d2d2d h34 w150 bc mt16 br3" disabled>
                </div>
            </div>
            <div class="flex-row w flex-justify-center mt35">
	            <input type="button" class="w110 bcwhite bad43c33 cd43c33 br3 h34 lh34 ml30 tx cp" onclick="Showall();" value="登录执剑人">
	        </div>
         </div>
        <!-- 180116 -->
        
    </div>
        
    </div>
    <div class="cb"></div>
</div>
  [@main.right /]
<!--弹窗-->
<div class="w h pf bc05 zi200 top none" id="sendEmailWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w385 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="title">发送合同至您的邮箱</div>
                <lable for="closeIcon" id="closeBtn1">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭" class="cp">
                    <button id="closeIcon" class="oln none"></button>
                </lable>
            </div>
            <!---->
            <div class="pl20 pr20">
                <div class="flex-row flex-direction-column w lh30 mt20">
                    <div class="flex-row flex-direction-column pl20">
					 	<input type="email" id="email" maxlength="30" class="w300 h30 bae0e0e0 br3 ti10 validate[required,custom[email]]" placeholder="请输入经办人邮箱号">
                    	<input type="hidden" id="corpNo"/>
                    </div>
                    <div class="flex-row flex-direction-column pl20">
						<input type="button" onclick="sendEmail();" value="发送" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 ml100 mt10 discountBtn1 cp">
                    </div>
                </div>
            </div>
         </div>
    </div>
</div>
[@main.footer/]
[/@main.body]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>

<script>
//只要进了这个页面的就是至少有一方开户了的
	$(document).ready(function () {
		$("#showContract").removeClass("none");
		if(role == 1){//资方	
			$("#rzTab2").next().children().text("资方账户")
			if(bnsState=='T'){
				$("#rzTab1").next().children().text("票方账户")
			}else{
				$("#rzTab1").next().children().text("票方开户")
			}
			$(".org").removeClass("none");
			$(".bns").addClass("none");
		}else{
			loadCibBankList();
			$("#rzTab1").next().children().text("票方账户")
			if(orgState=='T'){
				$("#rzTab2").next().children().text("资方账户")
			}else{
				$("#rzTab2").next().children().text("资方开户")
			}
			$(".org").addClass("none");
			$(".bns").removeClass("none");
		}
		loadData();
		Show();
	});
	
	function loadData(){
		var url = '${bootAppPath}/orginfo/rz';
		var params = {
			type:role,
			memberId:memberId
		};
		var res = bootPost(url,params);
		if(res!=null){
			var data = res.data;
			if (data.response == 'success') {
				var cib = data.data.cib;
				$("#corpNo").val(cib.corpNo);
				$("#name").text(cib.name);
				$("#bizLicenceRegisteredNo").text(cib.bizLicenceRegisteredNo);
				$("#bizLicenceLegalName").text(cib.bizLicenceLegalName);
				$("#bankAcctCnapsCode").text(cib.bankAcctCnapsCode);
				$("#showbankAcctAcctName").text(cib.bankAcctAcctName);
				$("#bankAcctAcctNo").text(cib.bankAcctAcctNo);
				$("#contactName").text(cib.contactName);
				$("#contactMobile").text(cib.contactMobile);
			}else{
				alert(data.msg);
			}
		}
	}
	
	function Show(){
		var params = {
			memberId:memberId,
			type:role,
		};
		var url = "${bootAppPath}/cib/corp/query";
		var res = bootPost(url,params);
		console.log(res);
		if(res!=null){
			var data = res.data;
			if(data.response == "success"){
				if(typeof(data.data.error_msg)!="undefined"){
					alert(data.data.error_msg+"，请联系客服");
				}else{
					var corp = data.data.corp;
					if(typeof(data.data.cib)!="undefined"){
						var contactMobile = data.data.cib.contactMobile;
						var corp_no = data.data.cib.corpNo;
						$(".username").text(contactMobile);
					}
					if(corp==null) {
						$(".balance").text('0.00');  //可用余额
						$(".frozenCapital").text('0.00');  //可用余额
					}else {
						$(".balance").text(corp.v_acct.balance);  //可用余额
						$(".frozenCapital").text(corp.v_acct.frozen_balance); 
					}
				}
				
			}else{
				alert(data.msg);
			}
		}
	}
	
	/**
	 * 充值
	 */
	function Showall(){
		var newTab = window.open("${basePath}/loading");
		setTimeout(function(){
			var params = {
				memberId:memberId,
				type:role,
			};
			var url = "${bootAppPath}/cib/corp/query";
			var res = bootPost(url,params);
			if(res!=null){
				var data = res.data;
				if(data.response == "success"){
					newTab.location.href = data.data.login_url;
				}else{
					alert(data.msg);
				}
			}
		},100);
		
	}
	
	function checkMoney(){
		if($(".money").val()>0){
			$("#finishAccount").addClass("bad43c33 bcd43c33 cwhite");
			$("#finishAccount").removeClass("bae0e0e0 bcf9f9f9 c2d2d2d");
			$("#finishAccount").attr("disabled",false);
		}else{
			$("#finishAccount").removeClass("bad43c33 bcd43c33 cwhite");
			$("#finishAccount").addClass("bae0e0e0 bcf9f9f9 c2d2d2d");
			$("#finishAccount").attr("disabled",true);
		}
	}
	
	/**
	 * 加载多账户列表
	 */
	function loadCibBankList(){
		var params = {
			  memberId:memberId,
			  type:role,
			};
		console.log(params);
		var url = "${bootAppPath}/cibbank/list";
		var res = bootPost(url,params);
		console.log(res);
		if(res!=null){
			var data = res.data;
			if(data.response == "success"){
				if(typeof(data.data)!="undefined"){
					if(data.data.length>4){
						$("#bank_add").parent().parent().addClass("none");
					}
					var html = "";
					for(var i=0;i<data.data.length;i++){
						var stateStr = "";
						if(data.data[i].status == 0){
							stateStr = '无效';
						}else if(data.data[i].status == 1){
							stateStr = '正常';
						}else if(data.data[i].status == 2){
							stateStr = '锁定';
						}else if(data.data[i].status == 5){
							$("#bank_add").parent().parent().addClass("none");
							stateStr = '待鉴定';
						}else if(data.data[i].status == 6){
							stateStr = '鉴定失败';
						}else{
							stateStr = '无效';
						}
						var a = data.data[i].bankAcctAcctNo.substring(0,4);
						var b = data.data[i].bankAcctAcctNo.length - 3;
						var c = data.data[i].bankAcctAcctNo.substring(b);
						if(data.data[i].status == 5){
							html += '<li class="flex-row flex-direction-column w300 h60 mb16 bae0e0e0 listHover cp" onclick=chooseBank('+data.data[i].id+')>';
						}else{
							html += '<li class="flex-row flex-direction-column w300 h60 mb16 bae0e0e0">';
						}
						html += '<div class="flex-row flex-direction-row flex-justify-space-between bcf9f9f9 pl10 pr10">';
						html += '<div>'+stateStr+'</div>';
						html += '<div>'+data.data[i].bankAcctBankBranch+'</div>';
						html += '</div>';
						html += '<div class="flex-row flex-direction-row flex-justify-space-between bcwhite f14 c717583 pl10 pr10">';
						html += '<div>'+a + "***" + c+'</div>';
						html += '<div>'+data.data[i].bankAcctAcctName+'</div>';
						html += '</div>';
						html += '</li>';
					}
					$("#cibBankList").html("");
					$("#cibBankList").append(html);
				}
			}
		}
	}
	//申请绑定
	function applyForToCard(){
		var bankAcctBankNo = $("#bankNo").val();
		var bankAcctCnapsCode = $("#CnapsCode").val();
		var bankAcctBankBranch = $("#bankAcctBankBranch").val();
		var bankAcctAcctNo = $("#AcctNo").val();
		var bankAcctAcctName = $("#bankAcctAcctName").val();
		var params = {
			memberId:memberId,
			type:role,
			bankAcctBankNo :bankAcctBankNo,
			bankAcctCnapsCode :bankAcctCnapsCode,
			bankAcctBankBranch :bankAcctBankBranch,
			bankAcctAcctNo :bankAcctAcctNo,
			bankAcctAcctName :bankAcctAcctName,
		};
		console.log(params);
		var url = "${bootAppPath}/cibbank/save";
		var res = bootPost(url,params);
		console.log(res);
		if(res!=null){
			var data = res.data;
			if(data.response == "success"){
				if(typeof(data.data.error_msg)!="undefined"){
					alert(data.data.error_msg);
				}else{
					$("cibBankId").val(data.data.cibBankId);
					$("#bankUsername").text(bankAcctAcctName);
					$("#no").text(bankAcctAcctNo);
					$(".applyFor").addClass("none");
					$(".completeBind").removeClass("none");
				}
				
				
			}
		}
	}
	$("#bank_add").click(function(){
		$("#bank_add").parent().parent().addClass("none");
		$(".add").removeClass("none");
		$(".tx").addClass("none");
	});
	//点击未通过鉴定的银行账户时
	function chooseBank(id){
		$("#cibBankId").val(id);
		var params = {
			memberId:memberId,
			id:id,
		};
		var url = "${bootAppPath}/cibbank/get";
		var res = bootPost(url,params);
		console.log(res);
		if(res!=null){
			var data = res.data;
			if(data.response == "success"){
				$("#bankUsername").text(data.data.bankAcctAcctName);
				$("#no").text(data.data.bankAcctAcctNo);
				$(".add").removeClass("none");
				$(".applyFor").addClass("none");
				$(".tx").addClass("none");
				$("#bank_add").parent().parent().addClass("none");
				$(".completeBind").removeClass("none");
			}
		}
	}
	
	$("#finishAccount").click(function(){
		$(".completeBind").addClass("none");
		$(".add").addClass("none");
		$(".tx").removeClass("none");
		loadCibBankList();
		var params = {
				memberId:memberId,
				cibBankId:$("#cibBankId").val(),
				amt:$(".money").val(),
			};
			var url = "${bootAppPath}/cibbank/authenticate";
			var res = bootPost(url,params);
			console.log(res);
			if(res!=null){
				var data = res.data;
				if(data.response == "success"){
					if(typeof(data.data.error_msg)!="undefined"){
						alert(data.data.error_msg);
					}else if(typeof(data.data.return_msg)!="undefined"){
						alert(data.data.return_msg);
					}else if (data.data.cibBank != null && data.data.cibBank != '') {
						if (data.data.cibBank.status == 5) {
						    alert("您输入的小额鉴定金额有误,您还有" + data.data.authenticate_order.left_count + "机会可操作");
						}
					}else{
						if(data.data.cibBank.status != 2){
							alert("鉴定失败，可联系客服咨询");
						}else{
							alert("鉴定成功");
						}
					}
				}else{
					alert(data.msg);
				}
			} 
	});
	
	//关闭弹窗
	$("#closeBtn1").click(function(){
		$("#sendEmailWindow").addClass("none");
	});
	
	$("#showContract").click(function(){
		$("#sendEmailWindow").removeClass("none");
	});
	
	function sendEmail(){
		if(!$("#email").validationEngine("validate")){
    		$("#email").focus();
    		setTimeout(function(){$("#email").validationEngine('hideAll');},1000);
    		return;
    	}
		var params = {
				no:$("#corpNo").val(),
				email:$("#email").val(),
				isCib:true,
			};
		var url = "${bootAppPath}/cib/econtract/email";
		var res = bootPost(url,params);
		console.log(res);
		if(res!=null){
			$("#sendEmailWindow").addClass("none");
			alert(res.data.msg);
		}
	}
	
</script>

