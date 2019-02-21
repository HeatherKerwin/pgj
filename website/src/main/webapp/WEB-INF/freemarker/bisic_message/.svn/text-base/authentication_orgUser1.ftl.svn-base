[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[#include "/common/boot/cib.ftl"]
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--已有开户：申请绑定-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
        [@main.cib_tool /]
        <!--票方开户-->
        <div class="pl200 pr200 f14">

            <!--申请绑定-->
            <div class="flex flex-direction-column w lh34">
                <div class="flex-row mt35 w435 flex-align-self-center">
                    <img src="${imagePath}website/PJGJ/account/sqbdIcon0.png" alt="申请绑定" class="flex-col-4">
                    <img src="${imagePath}website/PJGJ/account/bdshIcon0.png" alt="绑定审核" class="flex-col-4">
                    <img src="${imagePath}website/PJGJ/account/bdwcIcon0.png" alt="绑定完成" >
                </div>
                <div class="flex-row flex-direction-column pl7 pr7">
                    <div class="flex-row h34 mt35">
                        <div class="flex-col-3">公司名称：</div>
                        <p class="w460">${company}</p>
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">注册号：</div>
                        <p class="w460">${regNum}</p>
                    </div>
                    <div class="flex-row w mt26 flex-justify-center">
                        <p class=""><span>${company}</span> 已开户！</p>
                    </div>
                    <div class="flex-row h34 mt35">
                        <div class="flex-col-3">业务员姓名：</div>
                        <input type="text" id="name" class="w460 bae0e0e0 br3 ti10 validate[required]" placeholder="请填写业务员姓名">
                    </div>
                    <div class="flex-row w mt26 flex-justify-center">
                        <input type="button" class="w110 bce84c3d bad43c33 cwhite br3 h34 lh34" onclick="applyForBind();" value="申请绑定账户">
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
	});
	/*
	*申请绑定
	*/
	function applyForBind(){
		if(!$("#name").validationEngine("validate")){
    		$("#name").focus();
    		setTimeout(function(){$("#name").validationEngine('hideAll');},1000);
    		return;
    	}
		var name = $("#name").val();
		var params = {
			cibId: ${cibId},
			memberId: memberId,
			name:name,
			type: role,
		};
		console.log(params);
		var url = "${bootAppPath}/cib/corp/bind";
		var res = bootPost(url,params);
		if(res!=null){
			console.log(res);
			var data = res.data;
			if(data.response=="success"){
				if (data.response == 'success') {
					localStorage["FLAG"] = "1";
					var map = new Map();
					map.put("_PAGE", "bisic_message/authentication_orgUser2");//必传
					map.put("regNum", '${regNum}');
					map.put("role", role);
					_OPENPAGE_FORM(map);
				} else {
					alert(data.msg);
				}
			}
		}
	}
</script>

