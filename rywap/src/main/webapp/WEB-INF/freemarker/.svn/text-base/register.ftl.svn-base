[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='0' title ="注册"/]

<link rel="stylesheet" href="${staticPath}/css/rywap/login.css"/>
<script type="text/javascript" src="${staticPath}/js/rywap/function.js"></script>
<div class="wrapper mt44 p0">
    <div class="p10">
        <!--手机号-->
        <div class="bcwhite p10 mt10">
            <input type="tel" name="mobile" id="mobile" class="Int" placeholder="请输入手机号" maxlength="11"/>
        </div>
        <!--验证码-->
        <div class="bcwhite row p10">
            <div class="flex2">
                <input type="number" name="code" id="code" class="Int" placeholder="请输入收到的验证码"/>
            </div>
            <div class="flex1">
                <input type="button" value="获取验证码" id="codesend" class="yanBtn"/>
            </div>
        </div>
        <!--密码-->
        <div class="bcwhite p10 mt10">
            <input type="password" name="pwd" id="pwd" class="Int" placeholder="请输入6~11位数字字母组成的登录密码"/>
        </div>
        <!--推荐人-->
        <div class="bcwhite p10 mt10">
            <input type="number" name="recommendpeople" id="recommendpeople" class="Int" placeholder="推荐码（选填）"/>
        </div>

        <!-- 选择协议-->
        <div class="loginCheck">
            <div class="checkIcon onCheck">
                <input name="need_inv" type="checkbox" id="agreement" checked="checked" class="radioclass" value="1">
            </div>
            <lable>我已阅读并同意<a href="${basePath }/wap/member/agreement/page" class="cred">《票管家用户协议》</a></lable>
        </div>
    </div>

    <!--提交按钮-->
    <a class="Btn mt10" href="javascript:void(0)">
        <input type="button" id="register" value="提交"/>
    </a>
    <a href="${basePath }/wap/login/page" class="cred font12 w100p tar dib mt10 p10">去登录 》</a>
</div>    
<script type="text/javascript">
$(function(){
	$("#codesend").click("tap", codesend);
});

var timeshow;
/**
 * 点击获取验证码
 */
function codesend(){
    var mobile = $("#mobile").val();
    var p = validateMobile(mobile);//验证手机
    if(!p){
    	myToast('你输入的手机不符合格式');
        return;
    }
    timeshow = 60;
    $.ajax({
		url:BASEPATH + "/wap/member/sendcode",
		type:"post",
		data:{"mobile":mobile,"flag":"reg"},
		dataType:"json",
		success:function(data){
			if(data.response == "success"){
				$('#codesend').val(timeshow+" S");
				$("#codesend").attr({"disabled":"disabled"});
				$("#codesend").css({"background-color":"#DDDDDD","color":"#dc4c33"});
				for(i=1;i<=timeshow;i++) {
					window.setTimeout("update_p(" + i + ","+timeshow+")", i * 1000);
				}
			}else{
				myError(data.msg);
			}
        }
	 });
}

/**
 *  验证码倒计时
 */
function update_p(num,t) {	
    if(num == t) {
    	$('#codesend').val("重新发送 ");
    	$("#codesend").css({"background-color":"red","color":"#FFFFFF"});
        document.getElementById("codesend").disabled="";
    }
    else {
 		$('#codesend').val(t-num+" s重新发送");
    }
}

/**
 * 提交注册信息
 */
$("#register").click(function(){
	var mobile = $("#mobile").val();
	var code = $("#code").val();
	var pwd = $("#pwd").val();
	var recommendpeople = $("#recommendpeople").val();
	var p = validateMobile(mobile);//验证手机
    if(!p){
    	myToast('你输入的手机不符合格式');
        return;
    }else if($.trim(code).length != 4){
    	myToast('你输入的验证码不对');
        return;
    }else if($.trim(pwd).length<6 || $.trim(pwd).length>11){
    	myToast('你输入的密码长度不对');
        return;
    }else if($("#agreement").attr("checked") != "checked"){
		myToast("请同意票管家协议");
		return;
	}
    $.ajax({
		url:BASEPATH + "/wap/member/register/new",
		type:"post",
		data:{"mobile":mobile,"code":code,"pwd":pwd,"recommendpeople":recommendpeople},
		dataType:"json",
		success:function(data){
			if(data.response == "success"){
				mySuccess(data.msg,success);
			}else{
				myError(data.msg);
			}
        }
	 });
});
/**
 * 注册成功跳转
 */
function success(){
	window.location.href = BASEPATH + "/wap/login/page";
}
</script>

[/@main.body]