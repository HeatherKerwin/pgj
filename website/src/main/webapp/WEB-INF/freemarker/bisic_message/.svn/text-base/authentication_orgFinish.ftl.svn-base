[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[#include "/common/boot/cib.ftl"]
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--已有开户：完成开户小额贷款-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
        [@main.cib_tool /]
        <!--票方开户-->
        <div class="pl200 pr200 f14">

            <!--申请开户：审核-->
            <div class="flex flex-direction-column w lh34">
                <div class="flex-row mt35">
                    <img src="${imagePath}website/PJGJ/account/sqkhIcon0.png" alt="申请开户">
                    <img src="${imagePath}website/PJGJ/account/shIcon2.png" alt="审核" class="ml5">
                    <img src="${imagePath}website/PJGJ/account/wckhIcon1.png" alt="完成开户" class="ml5">
                </div>
                <div class="flex-row flex-direction-column pl7 pr7">
                    <div class="flex-row h34 mt35">
                        <div class="flex-col-3">户名：</div>
                        <p class="w460">${bankAcctAcctName }</p>
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">账号：</div>
                        <p class="w460">${bankAcctAcctNo }</p>
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">金额：</div>
                        <div class="w460">
                            <input type="number" id="money" oninput="checkMoney();" class="w200 bae0e0e0 br3 ti10 h34" placeholder="请输入金额">
                            <span class="ml8">元</span>
                        </div>
                    </div>
                    <div class="flex-row w mt26">
                        <div class="flex-col-3"></div>
                        <p class="w460 f13 lh20">客服已帮您提交开户申请，关联银行账号里会收到一笔小额款项，将收到的金额填写上，最后点击“完成开户”，即可完成开户。</p>
                    </div>
                    <div class="flex-row w mt26 flex-justify-center">
                        <input id="finishAccount" type="button" class="w110 bce84c3d bad43c33 opacity cwhite br3 h34 lh34" value="完成开户" disabled>
                    </div>
                    <p class="f12">如果您长时间没有收到小额鉴定的打款，请联系客服 tel:4000670002</span>
                </div>
            </div>

        </div>

    </div>
    <div class="cb"></div>
</div>
  [@main.right /]
 <!--弹窗-->
<div class="w h pf bc05 zi200 top none" id="maskWindow1">
    <div class="flex flex-alignItems-center flex-justify-center bc" style="height:80%;">
        <div class="flex-row flex-direction-column w h bcwhite bae0e0e0 br3 pb20 mt100">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="title">小额鉴定成功，请签署《授权委托书》和《报价承诺函》完成开户</div>
	            <div class="flex-row flex-direction-row-reverse lh30 mt30 h60">
	                <input type="button" id="accept" value="签署同意" onclick="finishAccount();" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20">
	                <input type="button" id="cancel" value="取消" class="w90 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn">
	            </div>
            </div>
            <!---->
            <div class="pl20 pr20" style="height:85%;">
                <iframe style="width:100%;height:100%;" src="${basePath}/authorization?type=${role}&cibId=${cibId}"></iframe>
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
	//  选择tab
	});
	
	//关闭按钮
	$("#cancel").click(function(){
		$("#maskWindow1").addClass("none");
	});
	
	//关闭按钮
	$("#finishAccount").click(function(){
		$("#maskWindow1").removeClass("none");
	});
	
	function checkMoney(){
		if($("#money").val()>0){
			$("#finishAccount").removeClass("opacity");
			$("#finishAccount").attr("disabled",false);
		}else{
			$("#finishAccount").addClass("opacity");
			$("#finishAccount").attr("disabled",true);
		}
	}
	
	function finishAccount(){
		$("#maskWindow1").addClass("none");
		var params = {amt:$("#money").val(),cibId:'${cibId}',memberId:memberId};
		console.log(params);
		var url = "${bootAppPath}/cib/corp/authenticate";
		var res = bootPost(url,params);
		if(res!=null){
			var data = res.data;
			console.log(res);
			if(data.response == "success"){
				if (data.data.corp != null && data.data.corp != '') {
					if (data.data.corp.status == 5) {
						alert("您输入的小额鉴定金额有误,您还有" + data.data.authenticate_order.left_count + "次机会可操作");
						return;
					}
					if (data.data.corp.status == 6) {
						alert("鉴定失败,请联系客服");
						return;
					}
					if (data.data.corp.status == 2) {
						alert("鉴定成功");
						checkAccount(role,'');
					}
				}
			}else{
				alert(data.msg);
			}
		}
	}
	
	
</script>

