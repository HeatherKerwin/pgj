[#include "/common/setting.ftl"]
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>联行号查询</title>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/hezuo/main.css"/>
   	<link rel="stylesheet" type="text/css" href="${pluPath}/validation-engine/css/validationEngine.jquery.css"/>
</head>
<body>
	<div class="w940 h420 bc bcwhite pr">
	<form name="R01" id="R01" method="post" action="${basePath}/hezuo/bank/list">
	    <div class="w bcf4f4f4 h50 lh50 f24 ti50 fb">联行号查询</div>
	    <div class="w938 bae0e0e0 h370">
	        <div class="w838 ml50 mr50 lh40">
	            <div class="w cl h40 mt10">
	                <div class="fl w100">银行名称：</div>
	                <select name="yinhang" class="fl select f18 ti10 validate[required]" id="yinhang">
		            	<option value=''>请选择银行</option>
		                <option value='城商行'>城商行</option>
		                <option value='城市信用社'>城市信用社</option>
		                <option value='工商银行'>工商银行</option>
		                <option value='光大银行'>光大银行</option>
		                <option value='广发银行'>广发银行</option>
		                <option value='合作联社'>合作联社</option>
		                <option value='合作银行'>合作银行</option>
		                <option value='华夏银行'>华夏银行</option>
		                <option value='建设银行'>建设银行</option>
		                <option value='交通银行'>交通银行</option>
		                <option value='民生银行'>民生银行</option>
		                <option value='农村信用社'>农村信用社</option>
		                <option value='农业发展'>农业发展</option>
		                <option value='农业银行'>农业银行</option>
		                <option value='商业银行'>商业银行</option>
		                <option value='深圳发展银行'>深圳发展银行</option>
		                <option value='信用合作社'>信用合作社</option>
		                <option value='兴业银行'>兴业银行</option>
		                <option value='邮储银行'>邮储银行</option>
		                <option value='招商银行'>招商银行</option>
		                <option value='中国人民银行'>中国人民银行</option>
		                <option value='中国银行'>中国银行</option>
		                <option value='中信银行'>中信银行</option>
	                </select>
	            </div>
	            <div class="w cl h40 mt10">
	                <div class="fl w100">所在省：</div>
	                <select name="provice" class="fl select f18 ti10 validate[required]" id="provice" onchange="choose2(this);">
	                </select>
	            </div>
	            <div class="w cl h40 mt10">
	                <div class="fl w100">所在市：</div>
	                <select name="city" class="fl select f18 ti10 validate[required]" id="city">
	                    <option value="">请选择城市</option>
	                </select>
	            </div>
	            <div class="w cl h40 mt10">
	                <div class="fl w100">关键字：</div>
	                <input name="keyword" type="text" class="fl w200 h40 f18 ti10 bae0e0e0 br5 validate[maxSize[10]]" placeholder="请输入查询条件" id="keyword">
	                <p class="fl f12 lh16 ml10">请输入关键字并点击查询按钮。关键字的输入方法：××银行海淀区支行世纪城分理处，<br>可以输入“xx银行 海淀 世纪城”作为关键字。多个关键字以空格隔开，区分先后顺序，<br>最多支持三个关键字。</p>
	            </div>
	            <div class="w cl h40 mt10">
	                <div class="fl w100">图形验证码：</div>
	                <input type="text" id="imgCode1" class="fl w200 h40 f18 ti10 bae0e0e0 br5 validate[required]" placeholder="验证码">
	                <img id="dlimg" class="fl w182 h40 f18 ti10 bae0e0e0 br3 ml10" src="${basePath}/member/image"/>
	                <a href="#" onclick="change('dlimg');" class="blue fl f18 c3366cc ml10 dsb" >换一个</a>
	            </div>
	            <div class="w tc mt10">
	            	<input type="button" class="w200 h40 lh40 f18 bcd43c33 b0 br5 cwhite tc cp" value="查询" onclick="yanzheng('imgCode1');">
	            </div>
	        </div>
	    </div>
    </form>
	</div>
</body>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pluPath}/validation-engine/js/languages/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${pluPath}/validation-engine/js/jquery.validationEngine.js"></script>
<script type="text/javascript">
var pageIndex = 1;//分页
var citylist;//根据省份选择城市
$(document).ready(function(){
	$.ajax({
		url:"${basePath}/homepage/provicecity",
		type:"post",
		data:{},
		dataType:"json",
		success:function(data){
			var data = eval(data);
			if(data.response=='success'){
				var provicelist = data.provicelist;
				var provicehtml = "<option value=''>请选择省份</option>";
				for(var i=0;i<provicelist.length;i++){
					var provice = provicelist[i];
					provicehtml += "<option value='"+provice.name+"'>"+provice.name+"</option>";
				}
				citylist = data.citylist;
				$("#provice").html(provicehtml);
			}
		}
	});

	$("#keyword").blur(function(){
		var array=new Array();
		var keyword=$("#keyword").val();
		var array=keyword.split(/\s+/g);
		if(array.length>3){
			alert("请输入3个以内的关键字");
			$("#keyword").val("");
			$("#keyword").focus();
		}
	});
	
	$("#R01").submit(function (e) {
		if(!$("#yinhang").validationEngine("validate")){
	   		$("#yinhang").focus();
	   		setTimeout(function(){$("#yinhang").validationEngine('hideAll');},1000);
	   		return;
	   	}
		if(!$("#provice").validationEngine("validate")){
	   		$("#provice").focus();
	   		setTimeout(function(){$("#provice").validationEngine('hideAll');},1000);
	   		return;
	   	}
		if(!$("#city").validationEngine("validate")){
	   		$("#city").focus();
	   		setTimeout(function(){$("#city").validationEngine('hideAll');},1000);
	   		return;
	   	}
		if(!$("#keyword").validationEngine("validate")){
	   		$("#keyword").focus();
	   		setTimeout(function(){$("#keyword").validationEngine('hideAll');},1000);
	   		return;
	   	}
    });
});

function choose2(obj){
	var cityhtml = "<option value=''>请选择城市</option>";
	for(var i=0;i<citylist.length;i++){
		if(citylist[i].pname==$(obj).val()){
			cityhtml += "<option value='"+citylist[i].name+"'>"+citylist[i].name+"</option>";
		}
	}
	$("#city").html(cityhtml);
}

/*
 * 换一张图形码
 */
function change(img){
	$("#"+img).attr("src","${basePath}/member/image?s="+new Date());
}

/*
 * 验证安全码
 */
function yanzheng(imgCode){
	var imgCode = $("#"+imgCode).val();
	if(!$("#imgCode1").validationEngine("validate")){
		$("#imgCode1").focus();
		return;
	}
	$.ajax({
		url:"${basePath}/member/yzimage",
		type:"post",
		data:{"imgCode":imgCode},
		dataType:"text",
		success:function(data){
			if("success"==data){
				$("#R01").submit();
			}else{
				$("#imgCode1").validationEngine('showPrompt','* 验证码不正确',null,null,true);
				setTimeout(function(){$("#imgCode1").validationEngine('hide');},3000);
			}
		}
	});
}
</script>
</html>