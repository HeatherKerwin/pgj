/**
 * 询问含回调（确定、取消）
 * @param msg
 * @param fun
 */
function myConfirm(msg,fun){
	var params = {
			alert:true,
			type:'CONFIRM',
			msg:msg,
			fun:fun
	}
	createPanle(params);
}

/**
 * 提示（1.8秒消失）
 * @param msg 提示内容
 */
var _TIME_TOAST;
function myToast(msg){
	$(".promptIcon-toast").remove();//销毁之前的
	clearTimeout(_TIME_TOAST);//清空之前的定时
	var params = {
		alert:true,
		type:'TOAST',
		msg:msg
	}
	createPanle(params);
	
	_TIME_TOAST = setTimeout(function(){
		$(".promptIcon-toast").remove();
	},1800);
}

/**
 * 错误提示
 * @param msg 提示内容
 */
function myError(msg){
	var params = {
		alert:true,
		type:'ERROR',
		msg:msg
	}
	createPanle(params);
}

/**
 * 错误提示
 * @param msg 提示内容
 * @param fun 回调方法
 */
function myError(msg,fun){
	var params = {
		alert:true,
		type:'ERROR',
		msg:msg,
		fun:fun
	}
	createPanle(params);
}

/**
 * 成功提示
 * @param msg 提示内容
 */
function mySuccess(msg){
	var params = {
		alert:true,
		type:'SUCCESS',
		msg:msg
	}
	createPanle(params);
}

/**
 * 成功提示
 * @param msg 提示信息
 * @param fun 回调方法
 */
function mySuccess(msg,fun){
	var params = {
		alert:true,
		type:'SUCCESS',
		msg:msg,
		fun:fun
	}
	createPanle(params);
}

//===================================================原始封装============================================================

/**
 * 遮罩提示弹窗
 * @param param
 * 注意事项：【功能参数有：alert常规提示、error错误提示、tel拨打电话、app下载APP】
 * 【辅助参数：msg提示信息、fun回调方法（注意该方法写在原页面即可）】
 */
function createPanle(param){
	var result = "";
	var msg = "";
	if(param.msg){
		msg = param.msg
	}
	if(param.alert && param.type=='TOAST'){
		result += "<div class='promptIcon-toast'>"+ msg +"</div>";
		$(document.body).append(result);
		return;
	}
	
	if(param.alert && param.type=='CONFIRM'){
		result = "<div class=\"maskWindow \">";
		result += "<div class=\"windowCon\"><div class='promptIcon'></div><div class=\"promptTitle\">"+msg+"</div><input type=\"button\" class=\"promptBtn\" value=\"确认\" onclick='promptBtn("+ param.fun +");'/><input type=\"button\" class=\"promptBtn\" value=\"取消\" onclick='promptBtn();'/></div>";
		result += "</div>";
		$(document.body).append(result);
		return;
	}
	
    result = "<div class=\"maskWindow \">";
    if(param.alert){//输入验证提示
    	var icon = "";//样式
		if(param.type=='SUCCESS'){
			icon = "promptIcon-success";
		}else if(param.type=='ERROR'){
			icon = "promptIcon";
		}
		result += "<div class=\"windowCon\"><div class='" + icon + "'></div><div class=\"promptTitle\">"+msg+"</div><input type=\"button\" class=\"promptBtn\" value=\"知道了\" onclick='promptBtn("+ param.fun +");'/></div>";
    }else if(param.tel){//拨打电话
        result += "<div class=\"windowTel\"><p>客服电话</p><p class=\"cred\">400-067-0002</p><p>是否拨打？</p><div><a href=\"tel:4000670002\" class=\"yesBtn\">是</a><a href=\"#\" class=\"noBtn\" onclick='noBtn();'>否</a></div></div>";
    }else if(param.app){//下载APP
        result += "<div class=\"WindowDownload\"><div class=\"closeDow\" onclick='notDownload()'></div><div class=\"conDow\"><h2>温馨提示</h2><p>请下载票据管家APP，享受票据贴现一站式服务。</p> <a href=\"https://m.utiexian.com/app/download.html\" class=\"dib\"><input type=\"button\" value=\"去下载APP\"/></a></div></div>";
    }
    result += "</div>";
    $(document.body).append(result);
}

/**
 * 不去下载APP
 */
function notDownload(){
    $(".maskWindow").addClass("hide");
    $(".WindowDownload").addClass("hide");
}

/**
 * 不拨打电话
 */
function noBtn(){
    $(".maskWindow").addClass("hide");
    $(".windowTel").addClass("hide");
}

/**
 * 文字提示知道了
 * @param fun 自定义方法（由页面传递来）
 */
function promptBtn(fun){
    $(".maskWindow").addClass("hide");
    $(".windowCon").addClass("hide");
    if(fun!=null && typeof(fun)=='function')fun.call();
}