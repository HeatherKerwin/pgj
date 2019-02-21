[#include "/common/setting.ftl"]
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>银票查询查复</title>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/hezuo/main.css"/>
    <link rel="stylesheet" href="${pluPath}/validation-engine/css/validationEngine.jquery.css" type="text/css"/>
</head>
<body>
	<div class="w940 h420 bc bcwhite pr">
	    <div class="w bcf4f4f4 h50 lh50 f24 ti50 fb">银票查询查复</div>
	    <div class="w938 bae0e0e0 h370">
	    	<div class="_LOGINED w h50 lh50 f20 bcf4f4f4 tc cl">
	            <div class="fl w_33 tc">
                    <input type="text" id="mobile" maxlength="11" class="validate[required,custom[phone]]  w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请输入您的手机号">
                </div>
                <div class="fl w_33">
                    <input type="text" id="code" maxlength="4" class="validate[required]  fl w150 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请输入验证码">
                    <input type="button" id="codeBtn" class="fl w120 h40 lh40 f18 bcd43c33 b0 br5 cwhite tc cp mt5 ml10" value="获取验证码" onclick="showtime(60);">
                </div>
                <div class="fr w_33">
                	<input type="button" class="w200 h40 lh40 f18 bcd43c33 b0 br5 cwhite tc cp mt5" value="获取查询记录" onclick="queryList();">
                </div>
	        </div>
	        <div class="w838 ml50 mr50 lh50">
	            <div class="w cl h50">
	                <div class="fl w_50">
	                    <div class="fl w100">票号：</div>
	                    <input type="text" id="draftNo" maxlength="30" class="validate[required,custom[draftNo]]  fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请输入16或30位票号">
	                </div>
	                <div class="fl w_50">
	                    <div class="fl w100">票据金额：</div>
	                    <input type="text" id="money" class="validate[required]  fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请输入票据金额">
	                    <div class="fl ml10">万元</div>
	                </div>
	            </div>
	            <div class="w cl h50">
	                <div class="fl w_50">
	                    <div class="fl w100">出票人：</div>
	                    <input type="text" id="drawer" maxlength="30" class="validate[required,maxSize[30]] fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请输入出票人全称">
	                </div>
	                <div class="fl w_50">
	                    <div class="fl w100">收款人：</div>
	                    <input type="text" id="payee" maxlength="30" class="validate[required,maxSize[30]] fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请输入收款人全称">
	                </div>
	            </div>
	            <div class="w cl h50">
	                <div class="fl w_50">
	                    <div class="fl w100">承兑行全称：</div>
	                    <input type="text" id="bank" maxlength="30" class="validate[required,maxSize[30]] fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请输入承兑行全称">
	                </div>
	                <div class="fl w_50">
	                    <div class="fl w100">承兑行号：</div>
	                    <input type="text" id="bankNo" maxlength="12" class="validate[required,maxSize[12]] fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请输入承兑行号">
	                </div>
	            </div>
	            <div class="w cl h50">
	                <div class="fl w_50">
	                    <div class="fl w100">出票日期：</div>
	                    <input type="text" id="start" class="fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请选择贴现日期" readonly="readonly">
	                </div>
	                 <input type="hidden" name="start"/>
	                <div class="fl w_50">
	                    <div class="fl w100">到期日期：</div>
	                    <input type="text" id="end" class="fl w200 h40 mt5 f18 ti10 bcf5f5f5 bae0e0e0 br5" placeholder="请选择贴现日期" readonly="readonly">
	                </div>
	                 <input type="hidden" name="end"/>
	            </div>
	            <div class="w cd43c33 f12">*请注意，每次查询查复都会在大额支付系统中留下查询记录</div>
	            <div class="w tc">
	                <input type="button" disabled class="w200 h40 lh40 f18 bccccccc b0 br5 cwhite tc cp" value="生成订单" onclick="save();" id="pay_save">
	            </div>
	        </div>
	    </div>
	</div>
</body>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/plugins/data/laydate.js"></script>
<script type="text/javascript" src="${pluPath}/validation-engine/js/languages/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${pluPath}/validation-engine/js/jquery.validationEngine.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#bankNo").keyup(function(){
		var temp = $(this).val();
		$(this).val(temp.replace(/[^0-9]/g,''));
	});
	$("#draftNo").keyup(function(){
		var temp = $(this).val();
		$(this).val(temp.replace(/[^0-9]/g,''));
	});
	initJiejia();
});

var begintimelong = Date.parse(new Date());
var begintime = new Date(begintimelong);
var fullyear = begintime.getFullYear();//获取完整的年份(4位,1970-????)
var month = begintime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
var date = begintime.getDate();//获取当前日(1-31)

var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
var endfullyear = endtime.getFullYear();//获取完整的年份(4位,1970-????)
var endmonth = endtime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
var enddate = endtime.getDate();//获取当前日(1-31)

if (month < 10) {
    month = "0" + month;
}
if (date < 10) {
    date = "0" + date;
}
if (endmonth < 10) {
    endmonth = "0" + endmonth;
}
if (enddate < 10) {
    enddate = "0" + enddate;
}
$("#start").val(fullyear + "-" + month + "-" + date);
$("#end").val(endfullyear + "-" + endmonth + "-" + enddate);

/*
 * 发送验证码倒计时
 */
function showtime(t){
	$("#codeBtn").attr({"disabled":"disabled"});
	var mobile = $("#mobile").val();
	if(!$("#mobile").validationEngine("validate")){
		$("#mobile").focus();
		setTimeout(function(){$("#mobile").validationEngine('hideAll');},1000);
		$("#codeBtn").removeAttr("disabled");
		return ;
	}
   	$.ajax({
   		url:"${basePath}/hezuo/sendcode",
   		type:"post",
   		data:{"mobile":mobile},
   		dataType:"text",
   		success:function(data){
   			if(data=="success"){
   				for(var i=1;i<=t;i++) {
   					window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
   				}
   			}else{
   				$("#codeBtn").removeAttr("disabled");
   			}
   		}
   	});
}

function update_p(num,t) {		
    if(num == t) {
        document.getElementById("codeBtn").value =" 重新发送 ";
        document.getElementById("codeBtn").disabled="";
    }
    else {
        printnr = t-num;
		document.getElementById("codeBtn").value =printnr +"s重新发送";
    }
}

/**
 * 初始化节假日提示（假日期间提交将延迟受理）
 */
function initJiejia(){
	$.ajax({
		type: "POST",
     	url: "${basePath}/hezuo/notice",
     	data: {},
     	dataType:"json",
     	success:function(data){
     		if(data.response != "success"){
     			$("#pay_save").removeAttr("disabled");//按钮启用
     			$("#pay_save").removeClass("bccccccc").addClass("bcd43c33");
     		}
    	}
	});
}

/**
 * 查看列表
 */
function queryList(){
	if(!$("#mobile").validationEngine("validate")){
		$("#mobile").focus();
		setTimeout(function(){$("#mobile").validationEngine('hideAll');},1000);
		return;
	}
	if(!$("#code").validationEngine("validate")){
		$("#code").focus();
		setTimeout(function(){$("#code").validationEngine('hideAll');},1000);
		return;
	}
	var mobile = $("#mobile").val();
	var code = $("#code").val();
	$.ajax({
   		url:"${basePath}/hezuo/get/member",
   		type:"post",
   		data:{mobile:mobile,code:code},
   		dataType:"json",
   		success:function(data){
   			if (data.response == 'success'){
   				var member = data.data;
   				if(member!=null){
   					window.location.href="${basePath}/hezuo/query/list?mid=" + member.id;
   				}
   			}else{
   				alert(data.msg);
   			}
   		}
   	});
}

/**
 * 保存查询查复
 */
function save(){
	if(!$("#mobile").validationEngine("validate")){
		$("#mobile").focus();
		setTimeout(function(){$("#mobile").validationEngine('hideAll');},1000);
		return;
	}
	if(!$("#code").validationEngine("validate")){
		$("#code").focus();
		setTimeout(function(){$("#code").validationEngine('hideAll');},1000);
		return;
	}
	if(!$("#draftNo").validationEngine("validate")){
		$("#draftNo").focus();
		setTimeout(function(){$("#draftNo").validationEngine('hideAll');},1000);
		return ;
	}
	if(!$("#money").validationEngine("validate")){
		$("#money").focus();
		setTimeout(function(){$("#money").validationEngine('hideAll');},1000);
		return ;
	}
	if(!$("#drawer").validationEngine("validate")){
		$("#drawer").focus();
		setTimeout(function(){$("#drawer").validationEngine('hideAll');},1000);
		return ;
	}
	if(!$("#payee").validationEngine("validate")){
		$("#payee").focus();
		setTimeout(function(){$("#payee").validationEngine('hideAll');},1000);
		return ;
	}
	if(!$("#bank").validationEngine("validate")){
		$("#bank").focus();
		setTimeout(function(){$("#bank").validationEngine('hideAll');},1000);
		return ;
	}
	if(!$("#bankNo").validationEngine("validate")){
		$("#bankNo").focus();
		setTimeout(function(){$("#bankNo").validationEngine('hideAll');},1000);
		return ;
	}
	
	$("#pay_save").attr("disabled","disabled");
	
	var mobile = $("#mobile").val();
	var code = $("#code").val();
	var draftNo = $("#draftNo").val();
	var money = $("#money").val();
	var drawer = $("#drawer").val();
	var payee = $("#payee").val();
	var bank = $("#bank").val();
	var bankNo = $("#bankNo").val();
	var start = $("#start").val();
	var end = $("#end").val();
	$.ajax({
   		url:"${basePath}/hezuo/save",
   		type:"post",
   		data:{"mobile":mobile,"code":code,"draftNo":draftNo,"money":money,"drawer":drawer,"payee":payee,"bank":bank,"bankNo":bankNo,
   			"start":start,"end":end, "qudao":"PC",
   		},
   		dataType:"json",
   		success:function(data){
   			if (data.response == 'success'){
   				if(data.payState!=null && data.payState==1){
   					window.location.href="${basePath}/hezuo/query/list?mid=" + data.member.id;
   				}else{
   					window.location.href="${basePath}/hezuo/query/pay?no=" + data.no;
   				}
   			}else{
   				alert(data.msg);
   			}
   			$("#pay_save").removeAttr("disabled");//按钮启用
   		}
   	});
}

/**
 * 支付暂时免费，调整支付
 */
function payFree(){
	var draftNo = $("#draftNo").val();
	var money = $("#money").val();
	var drawer = $("#drawer").val();
	var payee = $("#payee").val();
	var bank = $("#bank").val();
	var bankNo = $("#bankNo").val();
	var start= $("#start").val();
	var end = $("#end").val();
    $.ajax({
   		url:"${basePath}/hezuo/save",
   		type:"post",
   		data:{"memberId":"${member.id}","draftNo":draftNo,"money":money,"drawer":drawer,"payee":payee,"bank":bank,"bankNo":bankNo,
   			"start":start,"end":end, "qudao":"PC",
   		},
   		dataType:"json",
   		success:function(data){
   			if (data.response == 'success'){
   				if(data.no!=null && $.trim(data.no)!=""){
   					window.location.href="${basePath}/inquiryreply/success";
   				}
   			}else{
   				alert(data.msg);
   			}
   			$("#pay_save").removeAttr("disabled");//按钮启用
   		}
   	});
}

!function(){
    laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
}();
var start = {
    elem: '#start',
    format: 'YYYY-MM-DD',
    min: '1900-01-01', //设定最小日期
    max: '2099-06-16', //最大日期
    istime: true,
    istoday: false,
    choose: function(datas){
        end.min = datas; //开始日选好后，重置结束日的最小日期
        end.start = datas //将结束日的初始值设定为开始日
    }
};
var end = {
    elem: '#end',
    format: 'YYYY-MM-DD',
    min: laydate.now(),
    max: '2099-06-16',
    istime: true,
    istoday: false,
    choose: function(datas){
        start.max = datas; //结束日选好后，充值开始日的最大日期
    }
};
laydate(start);
laydate(end);
</script>
</html>