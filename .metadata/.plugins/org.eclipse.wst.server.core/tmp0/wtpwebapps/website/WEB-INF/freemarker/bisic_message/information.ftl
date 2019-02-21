[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]

<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left  remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 h700 pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">基本信息</div>
        </div>
        <!--表单-->
        <form action="">
	        <div class="mt30 ml30">
	            <div class="w h34 lh86 mt30">
	                <div class="fl w90 fb">头像：</div>
	                <div class="fl wa ha">
		                <label class="wa" for="fileToUpload">
		                    <img class="w100 h100 dsb" id="img" src="${image_url}/website/PJGJ/information/head.png" >
                			<input type="file" name="fileToUpload" id="fileToUpload" class="none">
                			<input type="hidden" name="picpath" id="picpath"  style="display:none;">
		                    <div class="cb"></div>
		                </label>
	                </div>
	            </div>
	            <div class="cb"></div>
	            <div class="w h34 lh34 mt30">
	                <div class="fl w90 fb">用户名：</div>
	                <div class="fl" >${hideMobile(member.mobile)}</div>
	            </div>
	            <div class="w h34 lh34 mt30">
	                <div class="fl w90 fb">微信：</div>
	                <input type="text" id="weixin" maxlength="30" name="weixin" class="fl w312 h34 bae0e0e0 ti10" placeholder="请输入您的微信号">
	            </div>
	            <div class="w h34 lh34 mt30">
	                <div class="fl w90 fb">QQ：</div>
	                <input type="text" id="qq" name="qq" maxlength="12" class="validate[custom[number]] fl w312 h34 bae0e0e0 ti10" placeholder="请输入您QQ号">
	            </div>
	            <div class="w h34 lh34 mt30">
	                <div class="fl w90 fb">座机：</div>
	                <input type="text" id="zuoji" name="zuoji" class="validate[custom[studio]] fl w312 h34 bae0e0e0 ti10" placeholder="请输入您的座机号">
	            </div>
	            <div class="w h34 lh34 mt30">
	                <div class="fl w90 fb">职务：</div>
	                <input type="text" id="zhiwu" name="zhiwu" maxlength="30" class="fl w312 h34 bae0e0e0 ti10" placeholder="请输入您的职务">
	            </div>
	            <div class="cb"></div>
	            <input type="button" id="baocun" class="w246 h34 lh34 bce84c3d cwhite tv br5 b0 mt30 ml90 f14" value="保存">
	        </div>
       </form>
    </div>
    <div class="cb"></div>
</div>
  [@main.right /]
[@main.footer/]
[/@main.body]

<script type="text/javascript">
 	var memberId = ${member.id};
	$(function(){
		var img_upload=document.getElementById("fileToUpload");
		// 添加功能出发监听事件
		img_upload.addEventListener('change',readFile,false);
	});
		
	/**
	* boot 项目的图片上传
	*/
	function readFile(){
		var file=this.files[0];
		if(!/image\/\w+/.test(file.type)){ 
			alert("请确保文件为图像类型"); 
			return false; 
		}
		var reader=new FileReader();
		reader.readAsDataURL(file);
		reader.onload=function(){
			var index = this.result.indexOf(",");
			var base64Image =  this.result.substr(index+1);
			var url = '${bootAppPath}/upload/image';
			var params = {base64Image:base64Image};
			var data = bootPost(url,params);
			if(data != null){
				if(data.data.response == 'success'){
					$("#picpath").val(data.data.data.base64Image);
					$("#img").attr("src","${bootPic}"+data.data.data.base64Image);
					
					var picpath = data.data.data.base64Image;
					var url = '${bootAppPath}/member/save/headpic';
					var params = {headpic:base64Image,memberId:memberId};
					var data1 = bootPost(url,params);
					if(data1 != null){
						if(data1.data.response == 'success'){
							alert("更换成功");
						}else{
							alert(data1.data.msg);
						}
					};
				}
			}	
		}
	};
  
	/**
	* 保存基本信息
	*/
	$("#baocun").click(function(){
		if($("#baocun").attr("disabled")){
			return;
		}
		if(!$("#zuoji").validationEngine("validate")){
			$("#zuoji").focus();
			return;
		}
		if(!$("#qq").validationEngine("validate")){
			$("#qq").focus();
			return;
		}
		$("#baocun").attr("disabled","disabled");//按钮禁用
		var weixin = $("#weixin").val();
		var qq = $("#qq").val();
		var zuoji = $("#zuoji").val();
		var zhiwu = $("#zhiwu").val();
		
		var picpath = $("#picpath").val();

		$.ajax({
			type: "POST",
		  	url: "${basePath}/bisicmessagemember/member/update",
		  	data: {weixin:weixin,qq:qq,zuoji:zuoji,zhiwu:zhiwu,picpath:picpath},
		  	dataType:"json",
		  	success:function(data){
		  		if(data.state == "success"){
		  			wwwPath = "${basePath}";
		  			actionLog(wwwPath,"action35");//基本信息保存
		  			alert("保存信息成功");
			  		loadDate();
			  		$("#baocun").removeAttr("disabled");//按钮启用
		  		}else{
		  			$("#baocun").removeAttr("disabled");//按钮启用
		  		}
		 	}
		});
	});
	
	/**
	* 初始化加载数据的方法
	*/
	function loadDate(){
		$.ajax({
			type: "POST",
		  	url: "${basePath}/bisicmessagemember/searchmyinfobyid",
		  	data: {},
		  	dataType:"json",
		  	success:function(data){
		  		$("#weixin").val(data.data.weixin);
		  		$("#qq").val(data.data.qq);
		  		$("#zuoji").val(data.data.zuoji);
		  		$("#zhiwu").val(data.data.zhiwu);
		  		$("#picpath").val(data.data.headpic);
		  		if(data.data.headpic!=null){
		  			$("#img").attr("src","${bootPic}"+data.data.headpic);
		  		}
		 	}
		});
	};
	
	$(function(){
		loadDate();
	});
	
	//图片
function fileSelected(targetId, targetInputId,uploadInputId) {	
	var uploadId="ryUpload";	
	if(uploadInputId==undefined){
		var fileName=jQuery("#wokeUpload").val();
	}else{
		var fileName=jQuery("#"+uploadInputId).val();
		uploadId=uploadInputId;
	}
	var strtype=fileName.substring(fileName.length-3);
	strtype=strtype.toLowerCase();
	if (strtype!="jpg"&&strtype!="gif"&&strtype!="png"){
		alert("请上传JPG、GIF、PNG格式的图片！");
			return false;
	}
	$.ajaxFileUpload({
		url : '${basePath}/uploadpic',
		secureuri : false,
		dataType : 'json',
		data : {
			"targetDom" : "#"+targetId+",#"+targetInputId
		},
		fileElementId : uploadId,
		success : function(data) {
				if(data=="error"){
					alert("上传失败！");
				}else{
					$("#" + targetInputId).val(data);
					$("#" + targetId).attr("src", data);
				}
		},
		error : function() {
			alert("上传失败！");
		}
	});
}	
</script>
<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>