[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='0' title ="修改密码"/]
<div class="wrapper mt44 p0">
    <div class="p10">
        <div class="bcwhite row p10">
            <div class="flex2">
                <input type="number" name="code" id="code" class="Int" placeholder="请输入收到的验证码"/>
            </div>
        </div>
        <div class="bcwhite p10 mt10">
            <input type="password" name="pwd" id="pwd" class="Int" placeholder="请输入6~11位数字字母组成的登录密码"/>
        </div>
        <div class="bcwhite p10 mt10">
            <input type="password" name="confirmpwd" id="confirmpwd" class="Int" placeholder="请输入密码"/>
        </div>
    </div>
    <a class="Btn mt10" href="javascript:void(0)">
        <input type="button" value="提交" id="update"/>
    </a>
    <input name="update_mobile" id="update_mobile" type="hidden" value="${update_mobile}"/>
</div>    
<script type="text/javascript">
/**
 * 点击提交，进行密码修改
 */
$("#update").click(function(){
	var code = $("#code").val();
	var pwd =$("#pwd").val();
	var confirmpwd = $("#confirmpwd").val();
	var mobile = $("#update_mobile").val();
	
	if ($.trim(code).length !=4) {
		myToast("验证码错误");
        return;
    }else if($.trim(mobile).length == 0){
        myToast("手机号码不能为空");
        return;
    }else if($.trim(pwd).length == 0 || $.trim(confirmpwd).length == 0){
        myToast("密码不能为空");
        return;
    }else if ($.trim(pwd).length < 6 || $.trim(pwd).length > 11) {
        myToast("密码长度不正确");
        return;
    }else if(confirmpwd != pwd){
        myToast("两次密码不一致");
        return;
    }else if (!checkPwd(pwd)) {
        myToast("您的密码输入格式不正确");
        return;
    }
	$.ajax({
		url:BASEPATH + "/wap/member/updatepwd",
		type:"post",
		data:{"mobile":mobile,"code":code,"pwd":pwd},
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
 * 验证密码
 */
function checkPwd(str) {
    var telReg = !!str.match(/^[a-zA-Z0-9]{6,16}$/g);
    return telReg;
}

/**
 * 注册成功跳转
 */
function success(){
	window.location.href = BASEPATH + "/wap/login/page";
}
</script>

[/@main.body]