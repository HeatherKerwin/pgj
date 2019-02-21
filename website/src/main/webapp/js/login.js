
$(function(){
//密码登录
	$('#login1').click(function(){
		$('#login2').removeClass("cd43c33").addClass('c2d2d2d');
		$('#login1').removeClass("c2d2d2d").addClass('cd43c33');
		$('#switch_bottom').animate({left:'0px',width:'230px'});
		$('#login2Div').addClass("none");
		$('#login1Div').removeClass("none");
	});
//        快速登录
	$('#login2').click(function(){
		$('#login2').removeClass("c2d2d2d").addClass('cd43c33');
		$('#login1').removeClass("cd43c33").addClass('c2d2d2d');
		$('#switch_bottom').animate({left:'230px',width:'230px'});

		$('#login2Div').removeClass("none");
		$('#login1Div').addClass("none");
	});
	if(getParam("a")=='0')
	{
		$('#login2').trigger('click');
	}

});

function logintab(){
	scrollTo(0);
	$('#login1').removeClass("cd43c33").addClass('c2d2d2d');
	$('#login2').removeClass("c2d2d2d").addClass('cd43c33');
	$('#switch_bottom').animate({left:'230px',width:'96px'});
	$('#login2Div').addClass("none");
	$('#login1Div').removeClass("none");

}


//根据参数名获得该参数 pname等于想要的参数名
function getParam(pname) {
	var params = location.search.substr(1); // 获取参数 平且去掉？
	var ArrParam = params.split('&');
	if (ArrParam.length == 1) {
		//只有一个参数的情况
		return params.split('=')[1];
	}
	else {
		//多个参数参数的情况
		for (var i = 0; i < ArrParam.length; i++) {
			if (ArrParam[i].split('=')[0] == pname) {
				return ArrParam[i].split('=')[1];
			}
		}
	}
}


//	验证
function phone(){
	var x=document.getElementById("phone1").value;
	document.getElementById("phone1").value=x.toUpperCase();
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
function showtime(t){
	var phoneNo = document.getElementById("phone1").value;
	if (checkPhone(phoneNo)) {
		document.getElementById("phoneBtn1").disabled=true;
		for(i=1;i<=t;i++) {
			window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
		}
		window.setTimeout(function () {
			document.getElementById("phoneBtn1").disabled=false;
		}, t * 1000 + 1000);
	} else {
		alert("请输入正确的手机号！");
	}
}
function update_p(num,t) {
	if(num == t) {
		document.getElementById("phoneBtn1").value =" 重新发送 ";
	}
	else {
		printnr = t-num;
		document.getElementById("phoneBtn1").value =printnr +"s重新发送";
	}
}
