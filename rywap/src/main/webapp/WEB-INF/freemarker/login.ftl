[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='0' title ="登陆"/]

<link rel="stylesheet" href="${staticPath}/css/rywap/login.css"/>
<script type="text/javascript" src="${staticPath}/js/rywap/function.js"></script>

<!--容器-->
<div class="wrapper mt44 p0 bcwhite">
    <!--logo-->
    <div class="loginLogo"></div>
    <!--登录方式-->
    <div class="loginTab">
        <!-- tab标签-->
        <div class="title_bar">
            <ul id="loginTAB" class="tabTitle tac row">
                <li class="flex1 selectbar"><a href="#" id="Tab0">快速登录</a></li>
                <li class="flex1"><a href="#" id="Tab1">账号登录</a></li>
            </ul>
        </div>
        <!-- tab内容-->
        <div class="swiper-container tabCon">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <ul class="loginForm">
                        <li>
                            <img src="https://img.utiexian.com/rywap/login/shoujihao.png" alt="手机号码" class="formIcon"/>
                            <input type="number" name="mobile" id="mobile" class="formInt" placeholder="请输入手机号码" maxlength="11"/>
                            <input type="button" id = "codesend" value="获取验证码"/>
                        </li>
                        <li>
                            <img src="https://img.utiexian.com/rywap/login/yanzhengma.png" alt="密码" class="formIcon"/>
                            <input type="number" name="" id="code" class="formInt" placeholder="请输入验证码" maxlength="11"/>
                        </li>
                    </ul>
                </div>
                <div class="swiper-slide">
                    <ul class="loginForm">
                        <li>
                            <img src="https://img.utiexian.com/rywap/login/shoujihao.png" alt="手机号码" class="formIcon"/>
                            <input type="number" name="mobile1" id="mobile1" class="formInt" placeholder="请输入手机号码" maxlength="11"/>
                        </li>
                        <li>
                            <img src="https://img.utiexian.com/rywap/login/Password.png" alt="密码" class="formIcon"/>
                            <input type="password" name="pwd" id="pwd" class="formInt" placeholder="请输入密码" maxlength="11"/>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
	    <!-- 选择协议-->
	    <div class="loginCheck">
        <div class="checkIcon onCheck">
            <input name="need_inv" type="checkbox" checked="checked" class="radioclass" value="1">
        </div>
        <lable>我已阅读并同意<a href="${basePath }/wap/member/agreement/page" class="cred">《票管家用户协议》</a></lable>
    </div>
	    <!---->
	    <div class="Btn mt10">
	    	<input type="hidden" name= "login_way" id="login_way" value="0">
	        <input type="button" id="btn" value="登录"/>
	    </div>
		<input type="hidden" id="redirect_url" value="${redirect_url}" name="redirect_url"/>
	    <div class="p10 mt10">
	        <a href="${basePath }/wap/member/password/page" class="fl">找回密码</a>
	        <a href="${basePath }/wap/member/register/page" class="fr">去注册</a>
	    </div>
	</div>
</div>

<script>
$(function(){
	$("#codesend").click("tap", codesend);//获取验证码
	$("#btn").click("tap", login);//登陆
});
var index;
var page = 'loginTAB';
var as = document.getElementById(page).getElementsByTagName('a');
var mySwiper = new Swiper('.tabCon',{
    loop: false,
    onSlideChangeStart: function(swiper){
        $("#Tab"+swiper.activeIndex).parent().addClass("selectbar");
        for (var i = 0; i <= as.length; i++) {
            if(i!=swiper.activeIndex)
            {
                $("#Tab"+i).parent().removeClass();
            }
        }
    }
});
for (var i = 0; i <= as.length; i++) {
    (function() {
        var j = i;
        $('#Tab'+j).click(function(){
        	$("#login_way").val(j);
            mySwiper.slideTo(j, 500, false);

            for (var i = 0; i <= as.length; i++) {
                $("#Tab"+i).parent().removeClass();
            }
            $("#Tab"+j).parent().addClass("selectbar");
        });
    })();
}

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
		data:{"mobile":mobile,"flag":"quicklogin"},
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
};

/**
 * 提交,根据login_way的取值判断
 * 0 快速登陆，1 密码登陆
 */
function login(){
	var login_way = $("#login_way").val();
	var url ;
	var mobile ; 
	var code = $("#code").val();
	var pwd = $("#pwd").val();
	var data = {};
	var redirect_url = $("#redirect_url").val();
	if(login_way == 0){
		mobile = $("#mobile").val();
		url = BASEPATH + "/wap/login/quicklogin";
		data = {"mobile":mobile,"code":code,"redirect_url":redirect_url};
	}else if(login_way == 1){
		mobile = $("#mobile1").val();
		url = BASEPATH + "/wap/login";
		data = {"mobile":mobile,"pwd":pwd,"redirect_url":redirect_url};
	}
	$.ajax({
		url:url,
		type:"post",
		data:data,
		dataType:"json",
		success:function(data){
			if(data.response == "success"){
				var redirect_url = data.redirect_url;
				if($.trim(redirect_url).length>0 && redirect_url != null){
					window.location.href = BASEPATH  + redirect_url;
				}else{
					window.location.href = BASEPATH + "/wap/index";
				}
			}else{
				myError(data.msg);
			}
        }
	});
	
};
/**
 * 登陆
 */
</script>
[/@main.body]