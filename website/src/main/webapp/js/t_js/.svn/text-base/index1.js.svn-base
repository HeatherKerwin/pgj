// JavaScript Document

/*验证*/
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
function update_p(num,t) {		
	if(num == t) {
		document.getElementById("phone_btn1").value =" 重新发送 ";
	}
	else {
		printnr = t-num;
	   // document.myform.phone.value =  printnr +"s重新发送";
		document.getElementById("phone_btn1").value =printnr +"s重新发送";
	}
}



//返回顶部
function b() {
	h = $(window).height(),
	t = $(document).scrollTop(),
	t > h ? $("#moquu_top").show() : $("#moquu_top").hide()
}
$(document).ready(function() {
	b(),
	$("#moquu_top").click(function() {
		$(document).scrollTop(0)
	})
}),
$(window).scroll(function() {
	b()
});

//弹窗显示隐藏
//打开注册弹窗tc tc1
function openTC1(){
	$(".tc").show();
	$(".tc1").show();
	$(".xy-con").hide();
};
//关闭tc1 打开tc2
function closeTC1(){
	$(".tc1").hide();
	$(".tc2").show();
	$(".xy-con").hide();
};
//关闭弹窗tc tc2
function closeTC2(){
	$(".tc2").hide();
	$(".tc").hide();
	$(".xy-con").hide();
};
//关闭tc2打开注册弹窗tc1
function closeTC3(){
	$(".tc2").hide();
	$(".tc1").show();
	$(".xy-con").hide();
};
//打开协议
function openXY(){
	$(".tc").show();
	$(".xy-con").show();
};
//关闭协议
function closeXY(){
	if($(".tc1").is(':hidden')){
		$(".tc").hide();
	}
	$(".xy-con").hide();
};

//注册成功--打开
function openSM(){
	$(".sm").show();
};
//注册成功--关闭
function closeSM(){
	$(".sm").hide();
};
//登录成功--打开
function openSM1(){
	$(".sm1").show();
};
//登录成功--关闭
function closeSM1(){
	$(".sm1").hide();
};