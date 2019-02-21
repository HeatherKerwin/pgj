[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[#include "/common/boot/cib.ftl"]
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--已有开户：绑定完成-->
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
                    <img src="${imagePath}website/PJGJ/account/bdwcIcon1.png" alt="绑定完成" class="flex-col-4">
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
						<label class="flex-row w110 bce84c3d bad43c33 h34 br3 flex-justify-center flex-alignItems-center btn2">
						    <input type="button" class="bce84c3d cwhite b0 lh34" onclick="binDing();" value="查看开户信息"/>
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
	});
	/**
	*前往开户信息
	*/
	
	function binDing(){
		localStorage["FLAG"] = '';
		checkAccount('${role}','');
	}
	
	function openaccountPage(){
		var params = {
			memberId:${member.id},
			type:localStorage["ORGTYPE"]
		}
		$.ajax({
			url:"${basePath}/orginfo/rz",
			type:"POST",
			data:data,
			dataType:'json',
			success:function(res){
				console.log(res);
			},
			error:function(err){
				
			}
				
		})
	}
</script>

