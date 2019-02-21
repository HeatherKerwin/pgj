[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='完成开户']
[@main.header currentmenu='1'/]
[#include "/common/boot/cib.ftl"]

    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/form.css">

<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
	<div class="fl w997 box-shadow bcwhite box-sizing bc pt30 pb30">
	    <!--标题-->
	    <ul class="h52 f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite rzTab">
		    <li class="w250 lh50 fl bre0e0e0">
		        <input type="radio" name="rzTab" id="jd" value="0" class="none"><a href="javascript:void(0);" class="c2d2d2d"><label
		            for="jd" class="dsb bbd43c33">京东管理</label></a>
		    </li>
		    <li class="w250 lh50 fl bre0e0e0">
		        <input type="radio" name="rzTab1" id="rzTab11" value="1" class="none"><a href="javascript:checkAuthentication(0,'','');" class="c2d2d2d"><label
		            for="rzTab1" class="dsb">兴业票方开户</label></a>
		    </li>
		    <li class="w250 lh50 fl bre0e0e0">
		        <input type="radio" name="rzTab1" id="rzTab21" value="2" class="none"><a href="javascript:checkAuthentication(1,'','');" class="c2d2d2d"><label
		            for="rzTab2" class="dsb">兴业资方开户</label></a>
		    </li>
		    <div id="statementBtn" class="fr c3366cc cp" style="margin-right: 20px">帮助文档</div>
		</ul>
	    <!--标题-->
	
	    <!--内容-->
	    <div class="clearfix mt50">
	        <!-- 营业执照信息+法人信息 -->
	        <div class="fl bae0e0e0 br3 box-sizing w380 pl14 pr14 pt30 pb20 ml70 c666666">
	            <!-- 营业执照信息 -->
	            <div>
	                <p class="bl3_e84c3d pl14 c2d2d2d">营业执照信息</p>
	                <ul class="formGroup pl14 f12 lh20">
	                    <li class="formItem">
	                        <label for="" class="w120">公司名称：</label>
	                        <input type="text" readonly value="" id="blicCompanyName">
	                    </li>
	                    <li class="formItem none blicUscc">
	                        <label for="" class="w120">统一社会信用代码：</label>
	                        <input type="text" readonly value="" id="blicUscc">
	                    </li>
	                    <li class="formItem none blicCardNo">
	                        <label for="" class="w120">注册号：</label>
	                        <input type="text" readonly value="" id="blicCardNo">
	                    </li>
	                </ul>
	            </div>
	            <!-- 营业执照信息 end -->
	
	            <!-- 法人信息 -->
	            <div>
	                <p class="bl3_e84c3d pl20 c2d2d2d">法人信息</p>
	                <ul class="formGroup pl14 f12 lh20">
	                    <li class="formItem">
	                        <label for="" class="w120">姓名：</label>
	                        <input type="text" readonly value="" id="lepName">
	                    </li>
	                    <li class="formItem">
	                        <label for="" class="w120">身份证号：</label>
	                        <input type="text" readonly value="" id="lepCardNo">
	                    </li>
	                    <li class="formItem">
	                        <label for="" class="w120">有效期至：</label>
	                        <input type="text" readonly value="" id="lepValidityEnd">
	                    </li>
	                </ul>
	            </div>
	            <!-- 法人信息 end -->
	
	        </div>
	        <!-- 营业执照信息+法人信息 end -->
	
	        <!-- 对公账户管理 -->
	        <div class="fl bae0e0e0 br3 box-sizing w380 pl14 pr14 pt30 pb20 ml50">
	            <p class="bl3_e84c3d pl20">对公账户管理</p>
	
	            <!-- 账户列表 -->
	            <ul class="oya" style="max-height: 390px;" id="content">
	                
	            </ul>
	            <!-- 账户列表 end -->
	
	            <!-- 新增 -->
	            <div class="mt20">
	                <a href="javascript:void(0)" class="c636363 box-shadow bcf8f8f8 dsb pt25 pb25" onclick="addCard();">
	                    <div class="tc">
	                        <img class="vm" src="https://img.utiexian.com/website/180903jingdong/kaihu/bankAdd.png" alt="新增" width="30" height="30">
	                        <p class="cc5c5c5 mt6">添加绑定对公账户</p>
	                    </div>
	                </a>
	            </div>
	            <!-- 新增 end -->
	
	        </div>
	        <!-- 对公账户管理 end -->
	    </div>
	    <!--内容-->
	</div>
</div>
<div class="cb"></div>

<!--弹窗-->
<div class="w h pf bc05 zi200 top none" id="maskWindow1">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w450 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="title">帮助文档</div>
                <lable for="closeIcon" id="closeBtns">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭" class="cp">
                    <button id="closeIcon" class="oln none"></button>
                </lable>
            </div>
            <!---->
            <div class="pl20 pr20">
                <!--开户声明-->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150">
                    <div class="flex-row flex-direction-column pl20 pr20 pt20">
                        <p>开户之前，需要准备什么资料？<br>
                            1、营业执照正本扫描件<br>
                            2、法定代表人的身份证正、反面扫描件<br>
                            3、对公银行账号<br>
                            收票方需关联指定银行对公账户 <span class="ce84c3d">（须开通电票功能并完成授权）</span>。京东目前已经达成合作的指定银行有：平安银行<a href="${basePath}/PAB.pdf" style="text-decoration:underline;" download>(授权流程下载)</a>、中信银行<a href="${basePath}/ECITIC.pdf" style="text-decoration:underline;" download>(授权流程下载)</a>。<br>
                            <span class="ce84c3d">*扫描件必须为彩色原件的扫描件。</span><br>
                            京东开户和认证时不区分开户角色，若用户绑卡若未绑定指定银行的对公账户，则无法使用京东户收票。<br>
                        </p>
                    </div>
                </div>
                <!-- 开户声明 end-->
            </div>
         </div>
    </div>
</div>

[@main.footer/]
[/@main.body]
<script type="text/javascript">
var memberId = "${member.id}";
$(function () {
    getInfo();
    inspectCib();
    getJdCradList1();
});

//关闭按钮
$("#closeBtns").click(function(){
    $("#maskWindow1").addClass("none");
});

//打开弹窗
$("#statementBtn").click(function(){
	$("#maskWindow1").removeClass("none");
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
				var cib = data.data.data.jdjr;
				if(cib.blicCardType == "USC"){
					$(".blicUscc").removeClass("none");
					$("#blicUscc").val(cib.blicUscc);
				}else{
					$(".blicCardNo").removeClass("none");
					$("#blicCardNo").val(cib.blicCardNo);
				}
				$("#cib_Id").val(cib.id);
				$("#blicCompanyName").val(cib.blicCompanyName);
				
				$("#lepName").val(cib.lepName);
				$("#lepCardNo").val(cib.lepCardNo);
				$("#lepValidityEnd").val(cib.lepValidityEnd);
			}
		}else{
			alert(data.data.msg);
		}
	}
}

/**
 * 检查兴业是否开户
 */
function inspectCib(){
	var url = '${bootAppPath}/orginfo/rz';
	if(memberId == null || memberId == ""){
		if(path != null && path != ""){
			location.href = "${basePath}/login";
		}
	}
	var params = {memberId:memberId,type:0};
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		if (data.response == 'success') {
			var active = data.data;
			var cib = active.cib;
			var cibCheckState;
			if(active.info!=null){
				cibCheckState = active.info.cibCheckState;
			}
			if((cibCheckState != "PENDING"&&cibCheckState != "PASS")||cibCheckState == "NOPASS"){//orgInfo的审核状态
				if(cib.status != 2){//没有开户
					$("#xingye").removeClass("none");
				}
			}else if(active.info.cibCheckState != 'PASS'){//已开户
				$("#xingye").removeClass("none");
			}
		}
	}
};

/**
 *  获取京东绑卡的列表(所有)
 */
 var cardList ;
function getJdCradList(){
	var url = '${bootAppPath}/jdjrcard/list';
	var params = {memberId:memberId};
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		console.log(data);
		if (data.response == 'success') {
			if(data.data != null && data.data.length>0){
				var html ="";
				var blicCompanyName = $("#blicCompanyName").val();
				for(var i =0;i<data.data.length;i++){
					var bo = true;
					if(cardList!=null&&cardList!=""){
						for(var l = 0;l<cardList.length;l++){
							if(data.data[i].bindId == cardList[l].bindId){
								bo = false;
								break;
							}
						}
					}
					if(bo){
						html += '<li class="mt16 pr zi12">';
						html += '<a href="#" class="c636363 box-shadow bcf8f8f8 dsb pl10 pr10 pt15 pb10"><div class="clearfix">';
						html += '<div class="fl"><img class="vm" src="https://img.utiexian.com/website/180903jingdong/kaihu/bankIcon.png" alt="bankIcon" width="30" height="30">';
						html += '<span class="fb" style="margin-left: 10px">'+data.data[i].bankName+'</span></div>';
						if(data.data[i].status == 0){
							html += '<button type="button" class="fl pt6 pb6 f12 pl10 pr10 bcwhite ba7c94fe c7c94fe ml20 cp" onclick="bindCard('+data.data[i].payOrderId+',\''+data.data[i].bankAccountNo+'\');">';
							html += '待打款</button></div><h3 class="f17 mt16 fb">'+data.data[i].bankAccountNo+'</h3>';
						}else if(data.data[i].status == 1){
							html += '<button type="button" class="fl pt6 pb6 f12 pl10 pr10 bcwhite ba7c94fe c7c94fe ml20 cp">';
							html += '正常</button></div><h3 class="f17 mt16 fb">'+data.data[i].bankAccountNo+'</h3>';
						}
						html += '<p class="cc5c5c5 f15 mt16">'+blicCompanyName+'</p></a></li>';
					}
					
				}
				$("#content").append(html);
			}
		}else{
			alert(data.msg);
		}
	}
}

/**
 *  获取京东绑卡的列表(可收票的)
 */
function getJdCradList1(){
	var url = '${bootAppPath}/jdjrcard/queryUserBindAccounts';
	var params = {memberId:memberId,type:2};
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		console.log(data);
		if (data.response == 'success') {
			if(data.data != null){
				if(data.data.accountList != null && data.data.count>0){
					var html = "";
					var blicCompanyName = $("#blicCompanyName").val();
					for(var i =0;i<data.data.count;i++){
						html += '<li class="mt16 pr zi12"><img class="pa top right zi10 cp" width="61" height="48" src="https://img.utiexian.com/website/180903jingdong/kaihu/keshoupiao.png" alt="可收票">';
						html += '<a href="#" class="c636363 box-shadow bcf8f8f8 dsb pl10 pr10 pt15 pb10"><div class="clearfix">';
						html += '<div class="fl"><img class="vm" src="https://img.utiexian.com/website/180903jingdong/kaihu/bankIcon.png" alt="bankIcon" width="30" height="30">';
						html += '<span class="fb" style="margin-left: 10px">'+data.data.accountList[i].bankName+'</span></div>';
						html += '<button type="submit" class="fl pt6 pb6 f12 pl10 pr10 bcwhite ba7c94fe c7c94fe ml20 cp">';
						html += '正常</button></div><h3 class="f17 mt16 fb">'+data.data.accountList[i].accountNo+'</h3>';
						html += '<p class="cc5c5c5 f15 mt16">'+blicCompanyName+'</p></a></li>';
					}
					$("#content").append(html);
				}
				cardList = data.data.accountList;
				getJdCradList(cardList);
			}else{
				getJdCradList(cardList);
			}
		}else{
			alert(data.msg);
		}
	}
}

/**
 * 绑定卡
 */
function addCard(){
	var blicCompanyName = $("#blicCompanyName").val();
	localStorage.setItem("blicCompanyName",blicCompanyName);
	location.href = "${basePath}/jd/bind/card";
}

function bindCard(payOrderId,bankAccountNo){
	var blicCompanyName = $("#blicCompanyName").val();
	localStorage.setItem("blicCompanyName",blicCompanyName);
	localStorage.setItem("payOrderId",payOrderId);
	localStorage.setItem("bankAccountNo",bankAccountNo);
	location.href ="${basePath}/jd/bind/small/money";
}
</script>