[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
     [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 h700 pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">更改密码</div>
        </div>
        <!--表单-->
        <div class="mt30 f14">
            <div class="pr h45 f14 lh44 mt30 zi10">
	            <label class="fl w129 h44 dsb lh45 mr10 tr" for="phone3">手机号</label>
	            <input type="text" id="phone" name="phone3" class="validate[required,custom[phone]]  fl w250 h44 bae0e0e0 br3 ti15" placeholder="请输入您的手机号"/>
	        </div>
            <div class="pr h45 f14 lh44 mt30 zi10">
	            <label class="fl w129 h44 dsb lh45 mr10 tr" for="imgCode2">图形验证码</label>
	            <input type="text" id="imgCode" name="imgCode2" class="validate[required] fl w120 h44 bae0e0e0 br3 ti15" placeholder="验证码"/>
	            <img id="dlimg" class="fl w120 h44 bae0e0e0 br3 ml10" src="${basePath}/member/image"/>
	            <a class="f14 c3366cc lh45 ml10" onclick="change()">换一个</a>
	        </div>
	        <div class="pr h45 f14 lh44 mt30 zi10">
	            <label class="fl w129 h44 dsb lh45 mr10 tr" for="phoneCode2">手机验证码</label>
	            <input type="text" id="phoneCode" name="phoneCode2" class="validate[required] fl w120 h44 bae0e0e0 br3 ti15" placeholder="手机验证码"/>
	            <input type="button" id="phoneBtn" class="fl w120 h44 bcf2f2f2 c727272 bae0e0e0 br3 ml10" value="获取验证码" onClick="yanzheng()">
	        </div>
	        <div class="pr h45 f14 lh44 mt30 zi10">
	            <label class="fl w129 h44 dsb lh45 mr10 tr" for="password2">设置密码</label>
	            <input type="password" id="password" maxlength="11" name="password2" class="validate[required],validate[minSize[6]] fl w250 h44 bae0e0e0 br3 ti15" placeholder="请设置您的密码"/>
	        </div>
            <div class="pr h45 f14 lh44 mt30 zi10">
                <label class="fl w129 h44 dsb lh45 mr10 tr" for="invite">确认密码</label>
                <input type="password" id="invite" name="invite" maxlength="11" class="validate[required] fl w250 h44 bae0e0e0 br3 ti15" placeholder="请输入确认密码"/>
            </div>
            <div class="cb"></div>
            <input type="button" value="确认修改" class="w246 h44 bce84c3d b0 br5 cwhite f16 lh45 tc bc mt30 ml140" onclick="changepwd()"/>
        </div>
    </div>
    <div class="cb"></div>
</div>
 [@main.right /]
[@main.footer/]
[/@main.body]

<script type="text/javascript">
/*
 * 验证手机号
 */
function checkMobile(str) {
	var telReg = !!str.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
	//如果手机号码不能通过验证
	if(telReg == false){
		alert("请输入正确的手机号！");
	    return false;
	} else {
		return true;
	}
}

/*
 * 修改密码
 */
function changepwd(tag){
	if($(tag).attr("disabled")){
		return;
	}
	var mobile = $("#phone").val();
	var code = $("#phoneCode").val();
	var pwd = $("#password").val();
	var invite = $("#invite").val();
	if(!$("#phone").validationEngine("validate")){
		$("#phone").focus();
		return;
	}
	if(!$("#phoneCode").validationEngine("validate")){
		$("#phoneCode").focus();
	    return;
	} 
	if(!$("#password").validationEngine("validate")){
		$("#password").focus();
		return;
	}
	if(pwd!=invite){
		$("#invite").validationEngine('showPrompt','* 两次密码不相同，请重新设置',null,null,true);
		setTimeout(function(){$("#invite").validationEngine('hide');},2000);
		$("#invite").val("");
		$("#invite").focus();
		return;
	}
	$(tag).attr("disabled","disabled");//按钮禁用
	$.ajax({
		url:"${basePath}/member/updatepwd",
		type:"POST",
		data:{"mobile":mobile,"code":code,"newpwd":pwd},
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				window.location.href="${basePath}";//跳到首页
			}else{
				if(data.msg == 'codeError'){
					$("#phoneCode").validationEngine('showPrompt','* 手机验证码不正确',null,null,true);
					setTimeout(function(){$("#phoneCode").validationEngine('hide');},2000);
				}else if(data.msg == 'phoneError'){
					$("#phone").validationEngine('showPrompt','* 手机号码不存在',null,null,true);
					setTimeout(function(){$("#phone").validationEngine('hide');},2000);
				}else{
					alert(data.msg);
				}
				$(tag).removeAttr("disabled");//按钮启用
			}
		}
	});
}


/*
 * 验证码倒计时
 */
var timeshow = 60;
var timer;
function showtime(t){
	$("#phoneBtn").attr({"disabled":"disabled"});
	var mobile = $("#phone").val();	
	timeshow = 60;
    if ($("#phone").validationEngine("validate")) {
    	$.ajax({
    		url:"${basePath}/member/sendcode",
    		type:"post",
    		data:{"mobile":mobile,"flag":"forget","myCode":$("#imgCode").val()},
    		dataType:"text",
    		success:function(data){
    			if(data=="success"){
    				timer = setInterval(show, 1000);
    			}else{
    				$("#phone").validationEngine('showPrompt','* '+data,null,null,true);
    				setTimeout(function(){$("#phone").validationEngine('hide');},3000);
    				$("#phoneBtn").removeAttr("disabled");
    			}
    		}
    	});
	} else {
		$("#phoneBtn").removeAttr("disabled");
	}
};

/**
* 短信倒计时
*/
function show() {
    if (timeshow <= 1) {
        clearInterval(timer);
    }
    timeshow--;
    $("#phoneBtn").val(timeshow+"s重新发送");
    if (timeshow == 0) {
		$("#phoneBtn").val("重新发送 ");
		$("#phoneBtn").removeAttr("disabled");
    }
};

/*
 * 重新发送
 */
function update_p(num,t) {		
    if(num == t) {
        document.getElementById("phoneBtn").value =" 重新发送 ";
        document.getElementById("phoneBtn").disabled="";
    }
    else {
        printnr = t-num;
		document.getElementById("phoneBtn").value =printnr +"s重新发送";
    }
}

/*
 * 换一张图形码
 */
function change(){
	$("#dlimg").attr("src","${basePath}/member/image?s="+new Date());
}
function yanzheng(){
	var imgCode = $("#imgCode").val();
	if(!$("#imgCode").validationEngine("validate")){
		return;
	}
	$.ajax({
		url:"${basePath}/member/yzimage",
		type:"post",
		data:{"imgCode":imgCode},
		dataType:"text",
		success:function(data){
			if("success"==data){
				showtime(60);
			}else{
				$("#imgCode").validationEngine('showPrompt','* 验证码不正确',null,null,true);
				setTimeout(function(){$("#imgCode").validationEngine('hide');},3000);
			}
		}
	});
}
</script>
<!--foot-->
