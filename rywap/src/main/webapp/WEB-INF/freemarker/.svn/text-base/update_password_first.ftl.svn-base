[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='0' title ="密码重置"/]
<div class="wrapper mt44 p0">
    <div class="p10">
        <div class="bcwhite p10">
            <input type="number" name="mobile" id="mobile" class="Int" placeholder="请输入手机号"/>
        </div>
        <p class="font12 cred mt10">请输入注册的手机号码。</p>
    </div>
    <a class="Btn mt10" href="javascript:void(0)">
        <input type="button" id="next" value="下一步"/>
    </a>
</div>

<form action="${basePath}/wap/member/update/password/page" id="from1" method="post">
	<input name="update_mobile" id="update_mobile" type="hidden"/>
</form>

<script type="text/javascript" src="${staticPath}/js/rywap/function.js"></script>
<script type="text/javascript">

/**
 * 点击下一步，验证手机号码，发送验证码
 * 成功进入修改页面
 */
$("#next").click(function(){
	var mobile = $("#mobile").val();
	var p = validateMobile(mobile);
	//如果手机号码不能通过验证
	if(!p){
		myToast("手机号码格式错误");
	    return ;
	}
	$.ajax({
		url:BASEPATH + "/wap/member/sendcode",
		type:"post",
		data:{"mobile":mobile,"flag":"forget"},
		dataType:"json",
		success:function(data){
			if(data.response == "success"){
				$("#update_mobile").val(mobile);
				$("#from1").submit();
			}else{
				myError(data.msg);
			}
        }
	 });
	
});
</script>

[/@main.body]