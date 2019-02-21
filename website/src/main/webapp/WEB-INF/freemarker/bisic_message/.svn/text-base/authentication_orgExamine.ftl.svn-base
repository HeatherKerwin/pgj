[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[#include "/common/boot/cib.ftl"]
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--已有开户：审核-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
        [@main.cib_tool /]
        <!--审核不通过-->
		<div class="flex flex-direction-column w480 h100 bcf7f7f6 bae0e0e0 br3 mt26 bc none" id="nopass">
		    <div class="flex-row flex-justify-end">
		        <label for="shClose" class="b0 mt10 mr10">
		            <img src="${imagePath}website/PJGJ/authentication/closeIcon.png" alt="关闭">
		            <button id="shClose" class="oln"></button>
		        </label>
		    </div>
		    <div class="flex-row flex-align-center ml40 mt3">
		        <div><img src="${imagePath}website/PJGJ/authentication/failIcon.png" alt=""></div>
		        <div class="flex-direction-column ml25">
		            <div class="f18 cd43c33">审核失败，请重新申请！</div>
		            <div class="f14 c979797 mt8">请填写正确的信息！</div>
		        </div>
		    </div>
		</div>
        <!--票方开户-->
        <div class="pl20 pr20 f14">
            <!--申请开户：审核-->
            <div class="flex flex-direction-column w lh34">
                <div class="flex-row flex-justify-center mt35">
                    <img src="${imagePath}website/PJGJ/account/sqkhIcon0.png" alt="申请开户">
                    <img src="${imagePath}website/PJGJ/account/shIcon1.png" alt="审核" class="ml5">
                    <img src="${imagePath}website/PJGJ/account/wckhIcon0.png" alt="完成开户" class="ml5">
                </div>
                <div class="flex flex-justify-space-between w pt20">
	                <!-- 左侧 -->
	                <div class="flex-col-6 pl50">
		                <!--企业法人营业执照-->
		                <div class="flex-row flex-direction-column mt25">
		                    <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                        <div>企业法人营业执照</div>
		                        <div class="toggle">
		                            <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16" class="mt16">
		                        </div>
		                    </div>
		                    <div class="flex-row flex-direction-column pl7 pr7 toggleCon">
		                        <!-- <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">公司名称：</div>
		                            <p class="w460">请输入公司名称</p><input type="text" id="company" class="w460 bae0e0e0 br3 ti10 validate[required] update none" placeholder="请输入公司名称">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">注册号：</div>
		                            <p class="w460">请输入营业执照上的统一社会信用代码</p><input id="regNum" type="text" class="w460 bae0e0e0 br3 ti10 validate[required,custom[regNum]] update none" placeholder="请输入营业执照上的统一社会信用代码">
		                        </div> -->
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">公司类型：</div>
		                            <p class="w300">请输入营业执照上公司类型</p><input type="text" id="bizLicenceType" class="w300 bae0e0e0 br3 ti10 validate[required] update none" placeholder="请输入营业执照上公司类型">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">住所：</div>
		                            <p class="w300 lh20">请输入营业执照上的住所</p><input type="text" id="bizLicenceAddr" class="w300 bae0e0e0 br3 ti10 validate[required] update none" placeholder="请输入营业执照上的住所">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">成立日期：</div>
		                            <p class="w300">请输入营业执照上的成立日期</p><input type="text" id="bizLicenceFoundedDate" class="w300 bae0e0e0 br3 ti10 validate[required] update none" placeholder="请输入营业执照上的成立日期（格式：xxxx-xx-xx）">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">法人姓名：</div>
		                            <p class="w300">请输入营业执照上的法定代表人</p><input type="text" id="bizLicenceLegalName" class="w300 bae0e0e0 br3 ti10 validate[required] update none" placeholder="请输入营业执照上的法定代表人">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">身份证号：</div>
		                            <p class="w300">请输入营业执照上的法定代表人身份证</p><input type="text" id="legalCertNo" class="w300 bae0e0e0 br3 ti10 validate[required,custom[IdCard]] update none" placeholder="请输入营业执照上的法定代表人身份证">
		                        </div>
		                    </div>
		                </div>
		
		                <!--银行信息-->
		                <div class="flex-row flex-direction-column mt25">
		                    <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                        <div>银行信息</div>
		                        <div class="toggle">
		                            <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16" class="mt16">
		                        </div>
		                    </div>
		                    <div class="flex-row flex-direction-column pl7 pr7 toggleCon">
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">开户行支行号：</div>
		                            <p class="w300">这里填充下拉选择数据</p>
		                            <input type="text" class="w300 bae0e0e0 br3 ti10 bankBtn update none" readonly id="bankstr" placeholder="请选择开户行行号">
		                        	<input type="hidden" id="bankNo"/>
		                        	<input type="hidden" id="CnapsCode"/>
		                        	<input type="hidden" id="bankAcctBankBranch"/>
		                        </div>
		                        
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">账号：</div>
		                            <p class="w300">请填写对应的卡号</p>
		                            <input type="number" id="bankAcctAcctNo" class="w300 bae0e0e0 br3 ti10 validate[required] update none" placeholder="请填写对应的卡号">
		                        </div>
		                        <div class="flex-row h34 mt10">
		                            <div class="flex-col-3">账户名：</div>
		                            <p class="w300">请填写对应的账户名</p>
		                            <input type="text" id="bankAcctAcctName" class="w300 bae0e0e0 br3 ti10 validate[required] update none" placeholder="请填写对应的账户名">
		                        </div>
		                    </div>
		                </div>
		
		                <!--其他-->
		                <div class="flex-row flex-direction-column mt25">
		                    <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                        <div>其他</div>
		                        <div class="toggle">
		                            <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16" class="mt16">
		                        </div>
		                    </div>
		                    <div class="flex-row flex-direction-column pr40 toggleCon">
		                        <!--经办人-->
		                        <div class="flex-row flex-direction-column mt10">
		                            <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                                <div>经办人</div>
		                                <div class="toggle">
		                                    <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16" class="mt16">
		                                </div>
		                            </div>
		                            <div class="flex-row flex-direction-column pl7 pr40 toggleCon">
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">姓名：</div>
		                                    <div class="w270">请输入经办人名称</div><input type="text" id="name" class="w270 bae0e0e0 br3 ti10 validate[required] update none" placeholder="请输入经办人名称">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">身份证号：</div>
		                                    <div class="w270">请输入经办人身份证号码</div><input type="text" id="IdCard" class="w270 bae0e0e0 br3 ti10 validate[required,custom[IdCard]] update none" placeholder="请输入经办人身份证号码">
		                                </div>
		                            </div>
		                        </div>
		
		                        <!--财务-->
		                        <div class="flex-row flex-direction-column mt10">
		                            <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                                <div>财务</div>
		                                <div class="toggle">
		                                    <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
		                                </div>
		                            </div>
		                            <div class="flex-row flex-direction-column pl7 pr40 toggleCon">
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">姓名：</div>
		                                    <p class="w270">请输入财务姓名</p><input type="text" id="treasurerName" class="w270 bae0e0e0 br3 ti10 validate[required] update none" placeholder="请输入财务姓名">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">身份证号：</div>
		                                    <p class="w270">请输入财务身份证号码</p><input type="text" id="treasurerCertNo" class="w270 bae0e0e0 br3 ti10 validate[required,custom[IdCard]] update none" placeholder="请输入财务身份证号码">
		                                </div>
		                            </div>
		                        </div>
		
		                        <!--机构信用代码-->
		                        <div class="flex-row flex-direction-column mt10">
		                            <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                                <div>机构信用代码</div>
		                                <div class="toggle">
		                                    <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
		                                </div>
		                            </div>
		                            <div class="flex-row flex-direction-column pl7 pr40 toggleCon">
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">代码：</div>
		                                    <p class="w270">请输入代码</p><input type="text" id="orgCreditCodeCertCode" class="w270 bae0e0e0 br3 ti10 validate[required,custom[regNum]] update none" placeholder="请输入代码（选填）">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">名称：</div>
		                                    <p class="w270">请输入公司名称</p><input type="text" id="orgCreditCodeCertName" class="w270 bae0e0e0 br3 ti10 update none" placeholder="请输入公司名称（选填）">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">地址：</div>
		                                    <p class="w270">请输入机构信用代码的地址</p><input type="text" id="orgCreditCodeCertAddr" class="w270 bae0e0e0 br3 ti10 update none" placeholder="请输入机构信用代码的地址（选填）">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">有效期：</div>
		                                    <p class="w270">请输入有效期</p><input type="text" id="orgCreditCodeCertExpiredDate" class="w270 bae0e0e0 br3 ti10 update none" placeholder="请输入有效期（选填）">
		                                </div>
		                            </div>
		                        </div>
		
		                        <!--开户许可证-->
		                        <div class="flex-row flex-direction-column mt10">
		                            <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                                <div>开户许可证</div>
		                                <div class="toggle">
		                                    <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
		                                </div>
		                            </div>
		                            <div class="flex-row flex-direction-column pl7 pr40 toggleCon">
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">编码：</div>
		                                    <p class="w270">请输入开户许可证编号</p><input type="text" id="acctPermitNo" class="w270 bae0e0e0 br3 ti10 update none" placeholder="请输入开户许可证编号（选填）">
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">核准号：</div>
		                                    <p class="w270">请输入开户许可证核准号</p><input type="number" id="acctPermitPermitNo" class="w270 bae0e0e0 br3 ti10 update none" placeholder="请输入开户许可证核准号（选填）">
		                                </div>
		                            </div>
		                        </div>
		
		                        <!--税务登记处-->
		                        <div class="flex-row flex-direction-column mt10">
		                            <div class="flex-row flex-justify-space-between h40 bcf4f4f4 lh40 f16 pl10 pr10">
		                                <div>税务登记处</div>
		                                <div class="toggle">
		                                    <img src="${imagePath}website/discount/down.png" alt="" width="20" height="12" class="mt16">
		                                </div>
		                            </div>
		                            <div class="flex-row flex-direction-column pl7 pr40 toggleCon">
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">国税字号：</div>
		                                    <p class="w270">请输入国税字号</p>
		                                    <input type="text" id="taxCertGovTaxNo" class="w270 bae0e0e0 br3 ti10 update none update none"/>
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">地税字号：</div>
		                                    <p class="w270">请输入地税字号</p><input type="text" id="taxCertLocalTaxNo" class="w270 bae0e0e0 br3 ti10 update none"/>
		                                </div>
		                                <div class="flex-row h34 mt10">
		                                    <div class="flex-col-3">纳税人名称：</div>
		                                    <p class="w270">请输入纳税人名称</p><input type="text" id="taxCertName" class="w270 bae0e0e0 br3 ti10 update none">
		                                </div>
		                            </div>
		                        </div>
		                        <!--税务登记处-->
		                        <div class="flex-row flex-direction-column mt10">
		                        	<input id="email" type="hidden"/><!-- 邮箱 -->
		                        	<input id="phone" type="hidden"/><!-- 联系人手机号 -->
		                        	<input id="company" type="hidden"/><!-- 公司名-->
		                        	<input id="regNum" type="hidden"/><!-- 企业信用代码 -->
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
		                    <label for="fileToUpload" class="w156 ml150 mt30 dsb cp" disabled><img id="img1" class="w156 h160 reupload" src="${imagePath}website/PJGJ/account/upload.png" alt="">
	                            <input type="file" class="none updateimg" id="fileToUpload" disabled><input type="hidden" name="imgpath1" id="imgpath1">
	                            <div>营业执照</div>
	                        </label>
	                        
	                        <label for="fileToUpload4" class="w156 ml150 mt30 dsb cp"><img id="img4" class="w156 h160 reupload" src="${imagePath}website/PJGJ/account/upload.png" alt="">
	                            <input type="file" class="none updateimg" id="fileToUpload4" disabled><input type="hidden" name="imgpath4" id="imgpath4">
	                            <div>法人身份证正面</div>
	                        </label>
	                        <label for="fileToUpload5" class="w156 ml150 mt30 dsb cp"><img id="img5" class="w156 h160 reupload" src="${imagePath}website/PJGJ/account/upload.png" alt="">
	                            <input type="file" class="none updateimg" id="fileToUpload5" disabled><input type="hidden" name="imgpath5" id="imgpath5">
	                            <div>法人身份证反面</div>
	                        </label>
		                </div>
	                </div>
	                <!-- 右侧end -->
	            </div>
                <div class="mt30 pl30">*以上信息若为空，平台将保存为“-”并传给兴业数金。</div>
                <div class="flex-row w mt26 flex-justify-center">
                    <label class="flex-row w110 bce84c3d bad43c33 h34 br3 btn1 opacity flex-justify-center flex-alignItems-center">
                        <img src="${imagePath}website/PJGJ/account/time.png" alt="" width="17" height="17" class="mr5">
                        <input type="button" class="bce84c3d cwhite b0 lh34" value="审核中" disabled>
                    </label>
                    
                    <input type="button" class="w110 bce84c3d bad43c33 cwhite br3 h34 lh34 ml20 btn1" onclick="updateInfo()" value="修改信息">
                    <input type="button" class="w110 bce84c3d bad43c33 cwhite br3 h34 lh34 ml20 none update" onclick="submit()" value="提交修改">
                    <input type="button" class="w110 bce84c3d bad43c33 cwhite br3 h34 lh34 ml20 btn2" value="重新开户">
                </div>
            </div>

        </div>

    </div>
    <div class="cb"></div>
</div>
[@main.right /]
<!--弹窗-->
<div class="w h pf bc05 zi200 top none" id="maskWindow1">
	<!--loading-->
    <div class="flex flex-alignItems-center flex-justify-center w h none loading">
        <div class="flex-row flex-direction-column w590 br3 pb20">
			<div class="pl20 pr20">
                <div class="w tc mt10 mb10">
		        	<img alt="" src="${image_url}/website/discount/loading.gif">
		        </div>
			</div>
		</div>
	</div>
</div>
[@main.footer/]
[/@main.body]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script>
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
		loadData();
		var img_upload=document.getElementById("fileToUpload");
		var img_upload4=document.getElementById("fileToUpload4");
		var img_upload5=document.getElementById("fileToUpload5");
		// 添加功能出发监听事件
		img_upload.addEventListener('change',readFile, false);
		img_upload4.addEventListener('change',readFile,false);
		img_upload5.addEventListener('change',readFile,false);
	});
	
	/**
	* boot 项目的图片上传
	*/
	function readFile(){
		$("#maskWindow1").removeClass("none");
		$(".loading").removeClass("none");
		var that = this;
		var file=this.files[0];
		if(!/image\/\w+/.test(file.type)){
			alert("请确保文件为图像类型");
			return false;
		}
		var reader=new FileReader();
		reader.readAsDataURL(file);
		reader.onload=function(){
			var index = this.result.indexOf(",");
			var base64Image =  this.result.substr(index+1);
			var url = '${bootAppPath}/upload/image';
			var params = {base64Image:base64Image};
			var data = bootPost(url,params);
			console.log(data);
			if(data != null){
				$("#maskWindow1").addClass("none");
				$(".loading").addClass("none");
				if(data.data.response == 'success'){
					$(that).prev().attr("src","${bootPic}/"+data.data.data.base64Image);
					$(that).next().val(data.data.data.base64Image);
					var ocrInfo = data.data.data.ocrInfo;
					if(typeof(ocrInfo) != "undefined" || ocrInfo != null){
						if(ocrInfo.bizLicenceNameMap != null){
							$("#company").val(ocrInfo.bizLicenceNameMap.bizLicenceName);
						}
						if(ocrInfo.bizLicenceRegisteredNoMap != null){
							$("#regNum").val(ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo);
							checkIsAccount();
						}
						if(ocrInfo.bizLicenceLegalNameMap != null){
							$("#bizLicenceLegalName").val(ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName);
						}
						if(ocrInfo.bizLicenceAddrMap != null){
							$("#bizLicenceAddr").val(ocrInfo.bizLicenceAddrMap.bizLicenceAddr);
						}
						if(ocrInfo.bizLicenceFoundedDateMap != null){
							$("#bizLicenceFoundedDate").val(ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate);
						}
						if(ocrInfo.idMap != null){
							$("#legalCertNo").val(ocrInfo.idMap.id);
						}
					}
				}
			}
		}
	}
	
	function updateInfo(){
		$(this).addClass("none");;
		$(".update").prev().addClass("none");
		$(".update").removeClass("none");
		$(".updateimg").attr("disabled",false);
	}
	
	function loadData(type){
		var params = {memberId:memberId,id:'${cibId}'};
		console.log(params);
		var url = "${bootAppPath}/cib/get";
		var res = bootPost(url,params);
		if(res!=null){
			console.log(res);
			var data = res.data;
			if (data.response == 'success') {
		        var openaccount = data.data;
		        if(openaccount.status == 1){//审核失败
					$(".toggle").html('<img src="${imagePath}website/discount/up.png" alt="" width="20" height="12">');
					$(".toggle").parent().next(".toggleCon").slideUp(100);
		        	$("#nopass").removeClass("none");
		        	$(".btn1").addClass("none");
		        	$(".btn2").removeClass("none");
		        }else{
		        	$(".btn2").addClass("none");
		        }
		        $("#company").val(openaccount.name);
		        $("#bankAcctAcctNo").val(openaccount.bankAcctAcctNo);//银行信息账号
		        $("#bankAcctAcctNo").prev().text(openaccount.bankAcctAcctNo);
		        $("#bankAcctAcctName").val(openaccount.bankAcctAcctName);//银行信息账户名
		        $("#bankAcctAcctName").prev().text(openaccount.bankAcctAcctName);
		        $("#regNum").val(openaccount.bizLicenceRegisteredNo);//信用代码
		        $("#bizLicenceType").val(openaccount.bizLicenceType);//营业执照上的公司类型
		        $("#bizLicenceType").prev().text(openaccount.bizLicenceType);
		        $("#bizLicenceAddr").val(openaccount.bizLicenceAddr);//营业执照上的住所
		        $("#bizLicenceAddr").prev().text(openaccount.bizLicenceAddr);
		        $("#bizLicenceLegalName").val(openaccount.bizLicenceLegalName);//营业执照上的法人代表
		        $("#bizLicenceLegalName").prev().text(openaccount.bizLicenceLegalName);
		        $("#bizLicenceFoundedDate").val(openaccount.bizLicenceFoundedDate);//营业执照上的成立日期
		        $("#bizLicenceFoundedDate").prev().text(openaccount.bizLicenceFoundedDate);
		        $("#legalCertNo").val(openaccount.legalCertNo);//营业执照上的法人代表身份证
		        $("#legalCertNo").prev().text(openaccount.legalCertNo);
		        $("#IdCard").val(openaccount.agentCertNo);//经办人身份证号
		        $("#IdCard").prev().text(openaccount.agentCertNo);
		        $("#treasurerName").val(openaccount.treasurerName);//财务姓名
		        $("#treasurerName").prev().text(openaccount.treasurerName);
		        $("#treasurerCertNo").val(openaccount.treasurerCertNo);//财务身份证号
		        $("#treasurerCertNo").prev().text(openaccount.treasurerCertNo);
		        $("#name").val(openaccount.contactName);//经办人的姓名
		        $("#name").prev().text(openaccount.contactName);
		        $("#phone").val(openaccount.contactMobile);//经办人手机号
		        $("#phone").prev().text(openaccount.contactMobile);
		        $("#email").val(openaccount.email);
		        $("#orgCreditCodeCertCode").val(openaccount.orgCreditCodeCertCode);//机构信用代码
		        $("#orgCreditCodeCertCode").prev().text(openaccount.orgCreditCodeCertCode);
		        $("#orgCreditCodeCertName").val(openaccount.orgCreditCodeCertName);//机构信用代码名称
		        $("#orgCreditCodeCertName").prev().text(openaccount.orgCreditCodeCertName);
		        $("#orgCreditCodeCertAddr").val(openaccount.orgCreditCodeCertAddr);//机构信用代码地址
		        $("#orgCreditCodeCertAddr").prev().text(openaccount.orgCreditCodeCertAddr);
		        $("#orgCreditCodeCertExpiredDate").val(openaccount.orgCreditCodeCertExpiredDate);//机构信用代码有效期
		        $("#orgCreditCodeCertExpiredDate").prev().text(openaccount.orgCreditCodeCertExpiredDate);
		        $("#taxCertGovTaxNo").val(openaccount.taxCertGovTaxNo);//国税字号
		        $("#taxCertGovTaxNo").prev().text(openaccount.taxCertGovTaxNo);
		        $("#taxCertLocalTaxNo").val(openaccount.taxCertLocalTaxNo);//地税字号
		        $("#taxCertLocalTaxNo").prev().text(openaccount.taxCertLocalTaxNo);
		        $("#taxCertName").val(openaccount.taxCertName);//纳税人名称
		        $("#taxCertName").prev().text(openaccount.taxCertName);
		        $("#acctPermitNo").val(openaccount.acctPermitNo);//开户许可证编码
		        $("#acctPermitNo").prev().text(openaccount.acctPermitNo);
		        $("#acctPermitPermitNo").val(openaccount.acctPermitPermitNo);//核准号
		        $("#acctPermitPermitNo").prev().text(openaccount.acctPermitPermitNo);
		        $("#bankNo").val(openaccount.bankAcctBankNo);//开户行行号
		        $("#bankAcctBankBranch").val(openaccount.bankAcctBankBranch);//开户行名称
		        $("#CnapsCode").val(openaccount.bankAcctCnapsCode);//开户行支行号
		        $("#bankstr").val(openaccount.bankAcctBankBranch+"("+openaccount.bankAcctCnapsCode+")");
		        $("#bankstr").prev().text(openaccount.bankAcctBankBranch+"("+openaccount.bankAcctCnapsCode+")");
		        $("#img1").attr("src",'${bootPic}'+openaccount.imgPath20);
		        $("#img4").attr("src",'${bootPic}'+openaccount.imgPathA1);
		        $("#img5").attr("src",'${bootPic}'+openaccount.imgPathA2);
		        $("#imgpath1").val(openaccount.imgPath20);
		        $("#imgpath4").val(openaccount.imgPathA1);
		        $("#imgpath5").val(openaccount.imgPathA2);
		        if(openaccount.status == 1|| this.info_state == 'NOPASS'){
		          /* this.bingDing = true;
		          this.bingDings = false; */
		        }
		      } else {
		        //this.apiSev.itip(data.msg);
		      }
		}
	}
	function submit(){
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
		if(!$("#bankNo").validationEngine("validate")){
    		$("#bankNo").focus();
    		setTimeout(function(){$("#bankNo").validationEngine('hideAll');},1000);
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
		if(!$("#name").validationEngine("validate")){
    		$("#name").focus();
    		setTimeout(function(){$("#name").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#IdCard").validationEngine("validate")){
    		$("#IdCard").focus();
    		setTimeout(function(){$("#IdCard").validationEngine('hideAll');},1000);
    		return;
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
		var params={
			id:${cibId},
			memberId:memberId,
			type:role,
			name:$("#company").val(),//企业名称
			address:$("#bizLicenceAddr").val(),//联系人公司地址
			bankAcctBankNo: $("#bankNo").val(),//企业开户行行号
			bankAcctAcctNo:$("#bankAcctAcctNo").val(),//卡号
			bankAcctAcctName:$("#bankAcctAcctName").val(),//
			bankAcctCnapsCode: $("#CnapsCode").val(),//支行行号
			bankAcctBankBranch:$("#bankAcctBankBranch").val(),//支行名称
			bizLicenceRegisteredNo:$("#regNum").val(),//企业信用代码
			bizLicenceName:$("#company").val(),//企业许可证公司名
			bizLicenceType:$("#bizLicenceType").val(),//营业执照上的公司类型
			bizLicenceAddr:$("#bizLicenceAddr").val(),//营业执照上的住所
			bizLicenceLegalName:$("#bizLicenceLegalName").val(),//法人姓名
			bizLicenceFoundedDate:$("#bizLicenceFoundedDate").val(),//成立日期
			legalCertNo:$("#legalCertNo").val(),
			agentCertNo:$("#IdCard").val(),//经办人身份证号码
			treasurerName:$("#treasurerName").val(),//财务姓名
			treasurerCertNo:$("#treasurerCertNo").val(),//财务身份证号码
			orgCodeCertCode:$("#regNum").val(),
			orgCodeCertName:$("#company").val(),
			orgCodeCertType:$("#bizLicenceType").val(),
			contactName:$("#name").val(),//经办人姓名
			contactMobile:$("#phone").val(),//经办人手机号
			email:$("#email").val(),
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
		var url = "${bootAppPath}/cib/save";
		var res = bootPost(url,params);
		if(res!=null){
			var data = res.data;
			console.log(data);
			if(data.response == 'success'){
				//您提交审核的开户信息已经修改
				alert("您提交审核的开户信息已经修改");
				loadData();
				$(this).addClass("none");;
				$(".update").prev().removeClass("none");
				$(".update").addClass("none");
				$(".updateimg").attr("disabled",true);
			}else{
				alert(data.msg);
			}
		}
	}
	
	$(".btn2").click(function (){
		var map = new Map();
		map.put("_PAGE", "bisic_message/authentication_open");//必传
		map.put("role", role);
		_OPENPAGE_FORM(map);
	})
</script>

