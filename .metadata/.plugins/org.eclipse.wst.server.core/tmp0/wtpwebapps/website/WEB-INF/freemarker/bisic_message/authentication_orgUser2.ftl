[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[#include "/common/boot/cib.ftl"]
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--已有开户：绑定审核-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
    	[@main.cib_tool/]
        <!--票方开户-->
        <div class="pl200 pr200 f14">
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
            <!--绑定审核-->
            <div class="flex flex-direction-column w lh34">
                <div class="flex-row mt35 w435 flex-align-self-center">
                    <img src="${imagePath}website/PJGJ/account/sqbdIcon0.png" alt="申请绑定" class="flex-col-4">
                    <img src="${imagePath}website/PJGJ/account/bdshIcon1.png" alt="绑定审核" class="flex-col-4">
                    <img src="${imagePath}website/PJGJ/account/bdwcIcon0.png" alt="绑定完成" >
                </div>
                <div class="flex-row flex-direction-column pl7 pr7">
                    <div class="flex-row h34 mt35">
                        <div class="flex-col-3">公司名称：</div>
                        <p class="w460" id="company"></p>
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">注册号：</div>
                        <p class="w460" id="regNum"></p>
                    </div>
                    <div class="flex-row w mt26 flex-justify-center">
                            <label class="flex-row w110 bce84c3d bad43c33 h34 br3 opacity flex-justify-center flex-alignItems-center btn1">
                                <img src="${imagePath}website/PJGJ/account/time.png" alt="" width="17" height="17" class="mr5">
                                <input type="button" class="bce84c3d cwhite b0 lh34" value="审核中" disabled/>
                            </label>
                            <label class="flex-row w110 bce84c3d bad43c33 h34 br3 flex-justify-center flex-alignItems-center none btn2">
                                <input type="button" class="bce84c3d cwhite b0 lh34" value="重新开户"/>
                            </label>
                    </div>
                </div>

            </div>
			</div>
        </div>
    </div>
    <div class="cb"></div>
</div>
  [@main.right /]
 
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
		$("#company").text("${name}");
		$("#regNum").text("${bizLicenceRegisteredNo}");
		console.log("-----------------");
		openAccountPage();
	});
	
	function openAccountPage() {
		var params = {
			memberId: ${member.id},
			type: ${role},
		};
		var url = "${bootAppPath}/orginfo/rz";
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			if (data.response == 'success') {
				console.log(data);
				var cib = data.data.cib;
				$("#company").text(data.data.info.company);
				$("#regNum").text(data.data.info.blNumber);
		        if (data.data.info.cibCheckState == 'PENDING') {
					$(".btn1").removeClass("none");
					$(".btn2").addClass("none");
					$("#nopass").addClass("none");
		        }
		        if (data.data.info.cibCheckState == 'NOPASS') {
					$(".btn1").addClass("none");
					$(".btn2").removeClass("none");
					$("#nopass").removeClass("none");
		        }
			} else {
		        alert(data.msg);
			}
		}
	}
	$(".btn2").click(function (){
		var map = new Map();
		map.put("_PAGE", "bisic_message/authentication_open");//必传
		map.put("role", ${role});
		_OPENPAGE_FORM(map);
	})
	
	$("#shClose").click(function (){
		$("#nopass").addClass("none");
	})
	    
</script>

