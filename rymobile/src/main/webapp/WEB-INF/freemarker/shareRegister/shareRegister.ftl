<!doctype html>
<html>
<head>
<title>分享好友注册界面</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<style>
	* { padding: 0; margin: 0; }
	body{
		background:#FFF;
	}
	.fl{
		float:left;
	}
	.head{
		background-color:#d43c33;
		height:2.1em;
	}
	.head-l{
		width:10%;
		text-align:center;
	}
	.head-l img{
		height: 1.2em;
		width: 0.7em;
		margin-top:0.4em;
	}
	.head-t{
		width:70%;
		background-color:#d43c33;
		padding: .4em 1em .4em;
		margin: 0;
		font-size: 1.0625em;
		color:#FFF;
		text-align: center;
	}
	.con{
		width: 92%;
		height: 40%;
		margin: 6% 4%;
	}
	.con img{
		width:100%;
		height:auto；
	}
	.int{
		width:94%;
		height:2em;
		margin:0 3% 6%;
		border-bottom:#e7e7e7 1px solid;
	}
	.w-50{
		width:40%;}
	.int-l{
		margin:0 2%;
	}
	.int-r{
		border:0;
		height:2.2em;
		line-height:2.2em;
		margin-top:-.5em;
	}
	.int-b{
		width:6em;
		height:2.6em;
		background:url(https://img.utiexian.com/rymobile/fxzc2.png) no-repeat center;
		background-size:100% auto;
		border:0;
		margin-top:-.8em;
		margin-left:2%;
		font-size:1em;
		color:#fff;
		}
	.foot{
		width:90%;
		margin:5%;
		background:#d43c33;
		background-size:100% auto;
		border:0;
		border-radius:.6em;
		font-size:1em;
		color:#FFF;
		padding:.6em 0;
	}
</style>
</head>
<body>
	<div class="head">
		<div class="head-l fl"><img src="https://img.utiexian.com/rymobile/head-l.png" /></div>
		<div class="head-t fl">注册</div>
	</div>
	<div class="con"><img src="https://img.utiexian.com/rymobile/fxzc1.png"/></div>
	
	<div class="int">
		<div class="int-l fl">手机号</div>
		<input class="int-r fl" type="text" placeholder="请输入手机号" id="phone" maxlength="11" onblur="checkMobile(this.value)">
	</div>
	<div class="int">
		<div class="int-l fl">验证码</div>
		<input class="int-r fl w-50" type="text" id="code" name="code" placeholder="请输入验证码">
		<input class="int-b fl" type="button" value="获取验证码" id="phone_btn1" name="phone_btn" onClick="getCode()">
		<input type="hidden" id="invitationCode" name="invitationCode" value="${invitationCode}">
	</div>
	<input type = "hidden" id="codeHidden" value="${memberId}">
    
	<input class="foot" id="registerBtn" type="button" value="快速注册" onclick="save()">
   
   <script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
   <script type="text/javascript">
    function save(){
    	$("#registerBtn").attr("disabled", true);
		$(".foot").css("background", "#CFCFCF");
    	var mobile = $("#phone").val();
    	var code = $("#code").val();
    	var invitationCode = $("#invitationCode").val();
    	$.ajax({
    		url: '../register/registerToDo.htm',
    		type: 'POST',
    		dataType: 'json',
    		data: {
    			'mobile': mobile,
    			'code': code,
    			'invitationCode': invitationCode
    		},
    		success: function(result) {
    			var data = eval(result);
    			if (data.state == 'success'){
    				window.location.href = "../register/success.htm";
    			} else {
    				alert(data.msg);
    				$("#registerBtn").attr("disabled", false);
    				$(".foot").css("background", "#d43c33");
    			}
    		}
    	});
    }
   
	function getCode() {
		var mobile = $("#phone").val();
		$.ajax({
			url:"../register/sendcode.htm",
			type:"post",
			data:{"mobile":mobile},
			dataType:"text",
			success:function(data){
				if(data=="error"){
					alert("发送失败，请稍后...");
				} else if (data == "timeout"){
					alert("发送失败，请刷新页面...");
				} else {
					showtime(60);
				}
			}
		});
	}

	function showtime(t){
		var phoneNo = document.getElementById("phone").value;
        if (checkPhone(phoneNo)) {
			document.getElementById("phone_btn1").disabled=true;
			for(i=1;i<=t;i++) {
				window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
			}
			window.setTimeout(function () {
				document.getElementById("phone_btn1").disabled=false;
			}, t * 1000 + 1000);
		} else {
			alert("请输入正确的手机号！");
		}
	}
	
	function phone(){
		var x=document.getElementById("phone").value;
		document.getElementById("phone").value=x.toUpperCase();
	}
	
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

	function checkPhone(str) {
		var telReg = !!str.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
		//如果手机号码不能通过验证
		if(telReg == false){
		    return false;
		} else {
			return true;
		}
	}
	
    function update_p(num,t) {		
        if(num == t) {
            document.getElementById("phone_btn1").value =" 重新发送 ";
        } else {
            printnr = t-num;
			document.getElementById("phone_btn1").value =printnr +"s重新发送";
        }
    }
</script>
</body>
</html>