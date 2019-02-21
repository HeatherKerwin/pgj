[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[#include "/common/boot/cib.ftl"]
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--新开户：第一状态-第二步-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
    	[@main.cib_tool /]
        <!--票方开户-->
        <div class="pl20 pr20 f14">

            <!--申请开户：第二步-->
            <div class="flex flex-direction-column w lh34">
                <div class="flex-row flex-justify-center mt35">
                    <img src="${imagePath}website/PJGJ/account/sqkhIcon0.png" alt="申请开户">
                    <img src="${imagePath}website/PJGJ/account/shIcon0.png" alt="审核" class="ml5">
                    <img src="${imagePath}website/PJGJ/account/wckhIcon0.png" alt="完成开户" class="ml5">
                </div>
                <div class="flex flex-justify-space-between w pt20">
	                <!-- 左侧 -->
	                <div class="flex-col-6 pl50">
		                <!--企业法人营业执照-->
		                <div class="flex-row flex-direction-column w">
		                    <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                        <div>企业法人营业执照</div>
		                        <div class="toggle">
		                            <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
		                        </div>
		                    </div>
		                    <div class="flex-row flex-direction-column pl7 pr7 toggleCon">
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">公司类型：</div>
		                            <input type="text" id="bizLicenceType" maxlength='25' class="w300 bae0e0e0 br3 ti10 validate[required]" placeholder="请输入营业执照上公司类型">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">住所：</div>
		                            <input type="text" id="bizLicenceAddr" value="${bizLicenceAddr}" maxlength='60' class="w300 bae0e0e0 br3 ti10 validate[required]" placeholder="请输入营业执照上的住所">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">成立日期：</div>
		                            <input type="text" id="bizLicenceFoundedDate" value="${bizLicenceFoundedDate}" class="w300 bae0e0e0 br3 ti10 validate[required]" placeholder="请输入营业执照上的成立日期">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">法人姓名：</div>
		                            <input type="text" id="bizLicenceLegalName" value="${bizLicenceLegalName}" maxlength='10' class="w300 bae0e0e0 br3 ti10 validate[required]" placeholder="请输入营业执照上的法定代表人">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">身份证号：</div>
		                            <input type="text" id="legalCertNo" value="${legalCertNo}" class="w300 bae0e0e0 br3 ti10 validate[required,custom[IdCard]]" placeholder="请输入营业执照上的法定代表人身份证">
		                        </div>
		                    </div>
		                </div>
		
		                <!--银行信息-->
		                <div class="flex-row flex-direction-column mt25 w">
		                    <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                        <div>银行信息</div>
		                        <div class="toggle">
		                            <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
		                        </div>
		                    </div>
		                    <div class="flex-row flex-direction-column pl7 pr7 toggleCon">
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">开户行行号：</div>
		                            <input type="text" class="w300 bae0e0e0 br3 ti10 bankBtn validate[required]" readonly id="bankstr" placeholder="请选择开户行行号">
		                        	<input type="hidden" id="bankNo"/>
		                        	<input type="hidden" id="CnapsCode"/>
		                        	<input type="hidden" id="bankAcctBankBranch"/>
		                        </div>
		                        
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">账号：</div>
		                            <input type="text" maxlength='25' id="bankAcctAcctNo" class="w300 bae0e0e0 br3 ti10 validate[required]" placeholder="请填写对应的卡号">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">账户名：</div>
		                            <input type="text" id="bankAcctAcctName" value="${company}" class="w300 bae0e0e0 br3 ti10 validate[required]" placeholder="请填写对应的账户名">
		                        </div>
		                    </div>
		                </div>
		
		                <!--其他-->
		                <div class="flex-row flex-direction-column mt25 w">
		                    <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                        <div>其他</div>
		                        <div class="toggle">
		                            <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
		                        </div>
		                    </div>
		                    <div class="flex-row flex-direction-column pr40 toggleCon">
		                        <!--财务-->
		                        <div class="flex-row flex-direction-column mt10 ">
		                            <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                                <div>财务</div>
		                                <div class="toggle">
		                                    <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
		                                </div>
		                            </div>
		                            <div class="flex-row flex-direction-column pl7 pr40 toggleCon">
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">姓名：</div>
		                                    <input type="text" id="treasurerName" maxlength='10' value="${name}" class="w300 bae0e0e0 br3 ti10 validate[required]" placeholder="请输入财务姓名">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">身份证号：</div>
		                                    <input type="text" id="treasurerCertNo" value="${IdCard}" class="w300 bae0e0e0 br3 ti10 validate[required,custom[IdCard]]" placeholder="请输入财务身份证号码">
		                                </div>
		                            </div>
		                        </div>
		
		                        <!--机构信用代码-->
		                        <div class="flex-row flex-direction-column mt10">
		                            <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                                <div>机构信用代码（非必填）</div>
		                                <div class="toggle1">
		                                    <img src="${imagePath}website/discount/up.png" alt="" width="20" height="12" class="mt16">
		                                </div>
		                            </div>
		                            <div class="flex-row flex-direction-column pl7 pr40 toggleCon1" style="display:none">
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">代码：</div>
		                                    <input type="text" id="orgCreditCodeCertCode" maxlength='18' class="w280 bae0e0e0 br3 ti10 validate[required,custom[regNum]]" placeholder="请输入代码（选填）">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">名称：</div>
		                                    <input type="text" id="orgCreditCodeCertName" maxlength='60' value="${company}" class="w280 bae0e0e0 br3 ti10" placeholder="请输入公司名称（选填）">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">地址：</div>
		                                    <input type="text" id="orgCreditCodeCertAddr" maxlength='60' class="w280 bae0e0e0 br3 ti10" placeholder="请输入机构信用代码的地址（选填）">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">有效期：</div>
		                                    <input type="number" id="orgCreditCodeCertExpiredDate" maxlength='18' class="w280 bae0e0e0 br3 ti10" placeholder="请输入有效期（选填）">
		                                </div>
		                            </div>
		                        </div>
		
		                        <!--开户许可证-->
		                        <div class="flex-row flex-direction-column mt10 ">
		                            <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                                <div>开户许可证（非必填）</div>
		                                <div class="toggle1">
		                                    <img src="${imagePath}website/discount/up.png" alt="" width="20" height="12" class="mt16">
		                                </div>
		                            </div>
		                            <div class="flex-row flex-direction-column pl7 pr40 toggleCon1" style="display:none">
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">编码：</div>
		                                    <input type="text" id="acctPermitNo" maxlength='13' class="w280 bae0e0e0 br3 ti10" placeholder="请输入开户许可证编号（选填）">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">核准号：</div>
		                                    <input type="number" id="acctPermitPermitNo" maxlength='14' class="w280 bae0e0e0 br3 ti10" placeholder="请输入开户许可证核准号（选填）">
		                                </div>
		                            </div>
		                        </div>
		
		                        <!--税务登记处-->
		                        <div class="flex-row flex-direction-column mt10 ">
		                            <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                                <div>税务登记处（非必填）</div>
		                                <div class="toggle1">
		                                    <img src="${imagePath}website/discount/up.png" alt="" width="20" height="12" class="mt16">
		                                </div>
		                            </div>
		                            <div class="flex-row flex-direction-column pl7 pr40 toggleCon1" style="display:none">
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">国税字号：</div>
		                                    <input type="text" id="taxCertGovTaxNo" value="${regNum}" class="w280 bae0e0e0 br3 ti10">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">地税字号：</div>
		                                    <input type="text" id="taxCertLocalTaxNo" value="${regNum}" class="w280 bae0e0e0 br3 ti10">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">纳税人名称：</div>
		                                    <input type="text" id="taxCertName"  value="${company}" class="w280 bae0e0e0 br3 ti10">
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                </div>
	                </div>
	                <!-- 左侧end -->
	                <!-- 右侧 -->
	                <div class="flex-col-6">
	                	<!--证件上传-->
		                <div class="flex-row flex-direction-column flex-justify-center w tc">
		                    <div class="f18 w">证件上传<span class="cd43c33 f16">（可点击修改）</span></div>
		                    <input type="hidden" id="role"/>
		                    <input type="hidden" id="cib_id"/>
		                    <label for="img1" class="w156 ml150 mt30 dsb cp"><img id="img1" class="w156 h160 reupload" src="${bootPic+imgpath1}" alt="">
	                            <input type="hidden" name="imgpath1" id="imgpath1" value="${imgpath1}">
	                            <div>营业执照</div>
	                        </label>
	                        
	                        <label for="img4" class="w156 ml150 mt30 dsb cp"><img id="img4" class="w156 h160 reupload" src="${bootPic+imgpath4}" alt="">
	                            <input type="hidden" name="imgpath4" id="imgpath4" value="${imgpath4}">
	                            <div>法人身份证正面</div>
	                        </label>
	                        <label for="img5" class="w156 ml150 mt30 dsb cp"><img id="img5" class="w156 h160 reupload" src="${bootPic+imgpath5}" alt="">
	                            <input type="hidden" name="imgpath5" id="imgpath5" value="${imgpath5}">
	                            <div>法人身份证反面</div>
	                        </label>
		                </div>
	                </div>
	                <!-- 右侧end -->
                </div>

                <div class="mt30 pl30">*以上信息若为空，平台将保存为“-”并传给兴业数金。</div>
                <div class="flex-row w mt26 flex-justify-center">
                    <input type="button" class="w110 bce84c3d bad43c33 cwhite br3 h34 lh34" onclick="nextStep();" value="下一步"/>
                </div>
            </div>
        </div>
    </div>
    <div class="cb"></div>
</div>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		if(orgState == "T"){
			$("#rzTab2").next().children().text("资方账户")
		}else{
			$("#rzTab2").next().children().text("资方开户")
		}
		if(bnsState == "T"){
			$("#rzTab1").next().children().text("票方账户")
		}else{
			$("#rzTab1").next().children().text("票方开户")
		}
	});
	
	$(".reupload").click(function (){
		var map = new Map();
		map.put("_PAGE", "bisic_message/authentication_open");//必传
		map.put("imgpath1", $("#imgpath1").val());
		map.put("imgpath4", $("#imgpath4").val());
		map.put("imgpath5", $("#imgpath5").val());
		map.put("name", "${name}");
		map.put("phone", "${phone}");
		map.put("IdCard", "${IdCard}");
		map.put("email", "${email}");
		map.put("role", ${role});
		map.put("company", "${company}");
		map.put("regNum", "${regNum}");
		map.put("bizLicenceAddr", "${bizLicenceAddr}");
		map.put("bizLicenceFoundedDate", "${bizLicenceFoundedDate}");
		map.put("bizLicenceLegalName", "${bizLicenceLegalName}");
		map.put("legalCertNo", "${legalCertNo}");
		_OPENPAGE_FORM(map);
	});
	
	function nextStep(){
		if(!$("#bizLicenceType").validationEngine("validate")){
    		$("#bizLicenceType").focus();
    		setTimeout(function(){$("#bizLicenceType").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#bizLicenceAddr").validationEngine("validate")){
    		$("#bizLicenceAddr").focus();
    		setTimeout(function(){$("#bizLicenceAddr").validationEngine('hideAll');},1000);
    		return;
    	} 
		if(!$("#bizLicenceFoundedDate").validationEngine("validate")){
    		$("#bizLicenceFoundedDate").focus();
    		setTimeout(function(){$("#bizLicenceFoundedDate").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#bizLicenceLegalName").validationEngine("validate")){
    		$("#bizLicenceLegalName").focus();
    		setTimeout(function(){$("#bizLicenceLegalName").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#legalCertNo").validationEngine("validate")){
    		$("#legalCertNo").focus();
    		setTimeout(function(){$("#legalCertNo").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#bankstr").validationEngine("validate")){
    		$("#bankstr").focus();
    		setTimeout(function(){$("#bankstr").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#bankAcctAcctNo").validationEngine("validate")){
    		$("#bankAcctAcctNo").focus();
    		setTimeout(function(){$("#bankAcctAcctNo").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#bankAcctAcctName").validationEngine("validate")){
    		$("#bankAcctAcctName").focus();
    		setTimeout(function(){$("#bankAcctAcctName").validationEngine('hideAll');},1000);
    		return;
    	}
		if(role==1){
			if($("#bankNo").val() != "309391000011"){
			    alert("请选择兴业银行兴业支行");
			    return;
			}
		}
		if(!$("#treasurerName").validationEngine("validate")){
    		$("#treasurerName").focus();
    		setTimeout(function(){$("#treasurerName").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#treasurerCertNo").validationEngine("validate")){
    		$("#treasurerCertNo").focus();
    		setTimeout(function(){$("#treasurerCertNo").validationEngine('hideAll');},1000);
    		return;
    	}
		if($("#imgpath1").val() == ''){
			alert("请上传清晰的营业执照正面照片");
			return;
		}
		if($("#imgpath4").val() == ''){
			alert("点击上传身份证正面");
			return;
		}
		if($("#imgpath5").val() == ''){
			alert("点击上传身份证反面");
			return;
		}
		var params={
			memberId:memberId,
			type:role,
			name:"${company}",//企业名称
			address:$("#bizLicenceAddr").val(),//联系人公司地址
			bankAcctBankNo: $("#bankNo").val(),//企业开户行行号
			bankAcctAcctNo:$("#bankAcctAcctNo").val(),//卡号
			bankAcctAcctName:$("#bankAcctAcctName").val(),//
			bankAcctCnapsCode: $("#CnapsCode").val(),//支行行号
			bankAcctBankBranch:$("#bankAcctBankBranch").val(),//支行名称
			bizLicenceRegisteredNo:"${regNum}",//企业信用代码
			bizLicenceName:"${company}",//企业许可证公司名
			bizLicenceType:$("#bizLicenceType").val(),//营业执照上的公司类型
			bizLicenceAddr:$("#bizLicenceAddr").val(),//营业执照上的住所
			bizLicenceLegalName:$("#bizLicenceLegalName").val(),//法人姓名
			bizLicenceFoundedDate:$("#bizLicenceFoundedDate").val(),//成立日期
			legalCertNo:$("#legalCertNo").val(),
			agentCertNo:'${IdCard}',//经办人身份证号码
			treasurerName:$("#treasurerName").val(),//财务姓名
			treasurerCertNo:$("#treasurerCertNo").val(),//财务身份证号码
			orgCodeCertCode:"${regNum}",
			orgCodeCertName:"${company}",
			orgCodeCertType:$("#bizLicenceType").val(),
			contactName:'${name}',//经办人姓名
			contactMobile:'${phone}',//经办人手机号
			email:"${email}",
			imgPathA1:$("#imgpath4").val(),
			imgPathA2:$("#imgpath5").val(),
			imgPath20:$("#imgpath1").val()
		};
		if($("#orgCreditCodeCertCode").val() == ""){
			params.orgCreditCodeCertCode = '-';
		}else {
			params.orgCreditCodeCertCode = $("#orgCreditCodeCertCode").val();
		}
		if($("#orgCreditCodeCertName").val() == ""){
			params.orgCreditCodeCertName = '-';
		}else {
			params.orgCreditCodeCertName = $("#orgCreditCodeCertName").val();
		}
		if($("#orgCreditCodeCertAddr").val() == ""){
			params.orgCreditCodeCertAddr = '-';
		}else {
			params.orgCreditCodeCertAddr = $("#orgCreditCodeCertAddr").val();
		}
		if($("#orgCreditCodeCertExpiredDate").val() == ""){
			params.orgCreditCodeCertExpiredDate = '-';
		}else {
			params.orgCreditCodeCertExpiredDate = $("#orgCreditCodeCertExpiredDate").val();
		}
		if($("#taxCertGovTaxNo").val() == ""){
			params.taxCertGovTaxNo = '-';
		}else {
			params.taxCertGovTaxNo = $("#taxCertGovTaxNo").val();
		}
		if($("#taxCertLocalTaxNo").val() == ""){
			params.taxCertLocalTaxNo = '-';
		}else {
			params.taxCertLocalTaxNo = $("#taxCertLocalTaxNo").val();
		}
		if($("#taxCertName").val() == ""){
			params.taxCertName = '-';
		}else {
			params.taxCertName = $("#taxCertName").val();
		}
		if($("#acctPermitNo").val() == ""){
			params.acctPermitNo = '-';
		}else {
			params.acctPermitNo = $("#acctPermitNo").val();
		}
		if($("#acctPermitPermitNo").val() == ""){
			params.acctPermitPermitNo = '-';
		}else {
			params.acctPermitPermitNo = $("#acctPermitPermitNo").val();
		}
		console.log(params);
		var url = "${bootAppPath}/cib/save";
		var res = bootPost(url,params);
		console.log(res);
		if(res != null){
			var data = res.data;
			console.log("----------------------------------------------");
			console.log(data);
			if(data.response == 'success'){
				var map = new Map();
				map.put("_PAGE", "bisic_message/authentication_orgExamine");//必传
				map.put("cibId", data.data.id);
				map.put("role", role);
				_OPENPAGE_FORM(map);
			}
		}
	}
	$(".toggle1").toggle(function(){
	     $(this).html('<img src="https://img.utiexian.com/website/discount/down.png" alt="" width="20" height="12" class="mt16">');
	     $(this).parent().next(".toggleCon1").slideDown(100);
	 },function(){
	     $(this).html('<img src="https://img.utiexian.com/website/discount/up.png" alt="" width="20" height="12" class="mt16">');
	     $(this).parent().next(".toggleCon1").slideUp(100);
	 });
</script>
[@main.right/]
[@main.footer/]
[/@main.body]