[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='申请开户审核']
[@main.header currentmenu='1'/]

    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/form.css">
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /] 
	<div class="fl w997 box-shadow bcwhite box-sizing bc pl50 pr50 pt30 pb30">
	    <!-- 标题 -->
	    <div class="clearfix">
	        <ul class="fl f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite rzTab oh">
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
			</ul>
	    </div>
	    <!-- 标题 end -->
	
	    <!-- 开户进度 -->
	    <div class="clearfix w570 ml225 oh pt30 pb30">
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng1.png" alt="申请开户">
	        </div>
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng2-2.png" alt="审核">
	        </div>
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng3-1.png" alt="完成开户">
	        </div>
	    </div>
	    <!-- 开户进度 end -->
	
	    <div class="w570 bc">
	        <div class="bl3_e84c3d pl10">营业执照</div>
	        <ul class="formGroup f14 c2d2d2d lh20">
	            <li class="formItem">
	                <img class="ml130 w100" id="blicUrlA" src="https://img.utiexian.com/website/180903jingdong/kaihu/yingyezhizhao.png" alt="营业执照">
	            </li>
	            <li class="formItem">
	                <label class="w130">公司名称：</label>
	                <input type="text" readonly value="" id="blicCompanyName" class="w320">
	            </li>
	            <li class="formItem blicUscc">
	                <label class="w130">统一社会信用代码：</label>
	                <input type="text" readonly value="" id="blicUscc">
	            </li>
	            <li class="formItem blicCardNo">
	                <label class="w130">注册号：</label>
	                <input type="text" readonly value="" id="blicCardNo">
	            </li>
	            <li class="formItem blicObaCardNo">
	                <label class="w130">税字号：</label>
	                <input type="text" readonly value="" id="blicObaCardNo">
	            </li>
	            <li class="formItem blicTrcCardNo">
	                <label class="w130">组织机构：</label>
	                <input type="text" readonly value="" id="blicTrcCardNo">
	            </li>
	            <li class="formItem">
	                <label class="w130">住所：</label>
	                <input type="text" readonly value="" id="blicAddress" class="w360">
	            </li>
	            <li class="formItem">
	                <label class="w130">经营范围：</label>
	                <textarea cols="5" readonly id="blicScope" class="w360 h70 blicScope" style="overflow-y: scroll;" placeholder=""></textarea>
	            </li>
	            <li class="formItem">
	                <label class="w130">营业期限至：</label>
	                <input type="text" readonly value="" id="blicValidityEnd">
	            </li>
	        </ul>
	        <div class="bl3_e84c3d pl10">法人信息</div>
	        <ul class="formGroup f14 c2d2d2d lh20">
	            <li class="formItem">
	                <img id="lepUrlA" src="https://img.utiexian.com/website/180903jingdong/kaihu/upload.png" class="ml130" width="171" height="129" alt="身份证正面">
	                <img id="lepUrlB" src="https://img.utiexian.com/website/180903jingdong/kaihu/upload.png" class="ml20" width="171" height="129" alt="身份证反面">
	            </li>
	            <li class="formItem">
	                <label class="w130">姓名：</label>
	                <input type="text" readonly value="" id="lepName">
	            </li>
	            <li class="formItem ">
	                <label class="w130">身份证号：</label>
	                <input type="text" readonly value="" id="lepCardNo">
	            </li>
	            <li class="formItem">
	                <label class="w130">有效期至：</label>
	                <input type="text" readonly value="" id="lepValidityEnd">
	            </li>
	        </ul>
	        
	        <div class="bl3_e84c3d pl10">银行卡信息</div>
	        <ul class="formGroup f14 c2d2d2d lh20">
	            <li class="formItem">
	                <label class="w130">开户行：</label>
	                <input type="text" readonly value="" id="occBankName">
	            </li>
	            <li class="formItem ">
	                <label class="w130">开户支行：</label>
	                <input type="text" class="w320" readonly value="" id="occBankChildName">
	            </li>
	            <li class="formItem">
	                <label class="w130">账号：</label>
	                <input type="text" readonly value="" id="occBankAccount">
	            </li>
	            <li class="formItem">
	                <a href="${basePath}/jd/update"><button type="button">修改</button></a>
	            </li>
	        </ul>
	    </div>
	</div>
</div>
<div class="cb"></div>
<script type="text/javascript">
var memberId = '${member.id}';
$(function(){
	getInfo();
});

/**
 * 获取信息
 */
function getInfo(){
	var url = '${bootAppPath}/jdjr/get';
	var params = {memberId:memberId};
	var data = bootPost(url,params);
	if(data != null){
		console.log(data);
		if(data.data.response == 'success'){
			if(data.data.data!=null){
				var cib = data.data.data;
				$("#blicUrlA").attr("src","${bootPic}/"+cib.blicUrlA);
				$("#blicCompanyName").val(cib.blicCompanyName);
				var blicCardType = cib.blicCardType;
				if(blicCardType!=null){
					if(blicCardType == "USC"){//统一社会信用代码
						if(cib.blicUscc!=null){
							$("#blicUscc").val(cib.blicUscc);
							$(".blicUscc").removeClass("none");
							$(".blicCardNo").addClass("none");
							$(".blicObaCardNo").addClass("none");
							$(".blicTrcCardNo").addClass("none");
						}
					}else{//多证合一
						$(".blicUscc").addClass("none");
						$(".blicCardNo").removeClass("none");
						$(".blicObaCardNo").removeClass("none");
						$(".blicTrcCardNo").removeClass("none");
						$("#blicCardNo").val(cib.blicCardNo);
						$("#blicObaCardNo").val(cib.blicObaCardNo);
						$("#blicTrcCardNo").val(cib.blicTrcCardNo);
					}
				}
				$("#blicAddress").val(cib.blicAddress);
				$("#blicScope").val(cib.blicScope);
				$("#blicValidityEnd").val(cib.blicValidityEnd);
				
				$("#lepUrlA").attr("src","${bootPic}/"+cib.lepUrlA);
				$("#lepUrlB").attr("src","${bootPic}/"+cib.lepUrlB);
				$("#lepName").val(cib.lepName);
				$("#lepCardNo").val(cib.lepCardNo);
				$("#lepValidityEnd").val(cib.lepValidityEnd);
				
				$("#occBankAccount").val(cib.occBankAccount);
				$("#occBankName").val(cib.occBankName);
				$("#occBankChildName").val(cib.occBankChildCode +" "+ cib.occBankChildName);
			}
		}else{
			alert(data.data.msg);
		}
	}
};
</script>
[@main.footer/]
[/@main.body]