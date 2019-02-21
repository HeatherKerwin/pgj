[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[#include "/common/boot/cib.ftl"]
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--已有开户：绑定完成-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
    	[@main.cib_tool /]
        <!--票方开户-->
        <div class="pl200 pr200 f14">
            <!--申请开户：证件上传-->
            <div class="flex flex-direction-column w lh34">
                <div class="flex-row mt35">
                    <img src="${imagePath}website/PJGJ/account/sqkhIcon0.png" alt="申请开户" class="flex-col-3">
                    <img src="${imagePath}website/PJGJ/account/zjscIcon1.png" alt="证件上传" class="flex-col-3">
                    <img src="${imagePath}website/PJGJ/account/shIcon0.png" alt="审核" class="flex-col-3">
                    <img src="${imagePath}website/PJGJ/account/wckhIcon0.png" alt="完成开户">
                </div>
                <!--证件上传-->
                <div class="flex-row flex-direction-column mt10">
                    <div class="flex-row pl30 mt25 f18">证件上传：</div>
                    <input type="hidden" id="role"/>
                    <input type="hidden" id="cib_id"/>
                    <div class="flex-row flex-wrap flex-justify-start w530 f16 tc pl25">
                        <label for="fileToUpload" id="l1" class="w156 ml15 mt30 cp"><img id="img1" class="w156 h160" src="${imagePath}website/PJGJ/account/upload.png" alt="">
                            <input type="file" class="none" id="fileToUpload"><input type="hidden" name="imgpath1" id="imgpath1">
                            <div>营业执照</div>
                        </label>
                        <label for="fileToUpload1" id="l4" class="w156 ml15 mt30 cp"><img id="img4" class="w156 h160" src="${imagePath}website/PJGJ/account/upload.png" alt="">
                            <input type="file" class="none" id="fileToUpload1"><input type="hidden" name="imgpath4" id="imgpath4">
                            <div>法人身份证正面</div>
                        </label>
                        <label for="fileToUpload2" id="l5" class="w156 ml15 mt30 cp"><img id="img5" class="w156 h160" src="${imagePath}website/PJGJ/account/upload.png" alt="">
                            <input type="file" class="none" id="fileToUpload2"><input type="hidden" name="imgpath5" id="imgpath5">
                            <div>法人身份证反面</div>
                        </label>
                    </div>
                </div>


                <div class="flex-row w mt26 flex-justify-center">
                    <input type="button" class="w110 bce84c3d bad43c33 cwhite br3 h34 lh34" onclick="submit()" value="提交">
                </div>
            </div>

        </div>
    </div>
    <div class="cb"></div>
</div>
  [@main.right /]
 
[@main.footer/]
[/@main.body]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script>
	$(document).ready(function () {
		if(orgState == "T"){
			$("#rzTab2").next().children().text("资方账户")
		}else{
			$("#rzTab2").next().children().text("资方开户")
		}
		if(bnsState == "T"){
			$("#rzTab1").next().children().text("票方账户")
		}else{
			$("#rzTab1").next().children().text("票方开户")
		}
		loadImg();
		if(role==0){
			$("#l3").addClass("none");
		}
		var img_upload=document.getElementById("fileToUpload");
		var img_upload1=document.getElementById("fileToUpload1");
		var img_upload2=document.getElementById("fileToUpload2");
		// 添加功能出发监听事件
		img_upload.addEventListener('change',readFile, false);
		img_upload1.addEventListener('change',readFile,false);
		img_upload2.addEventListener('change',readFile,false);
		
	});
	
	/**
	* boot 项目的图片上传
	*/
	function readFile(){
		var that = this;
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
			console.log(data);
			if(data != null){
				if(data.data.response == 'success'){
					$(that).prev().attr("src","${bootPic}/"+data.data.data.base64Image);
					$(that).next().val(data.data.data.base64Image);
				}
			}
		}
	}
	
	function submit(){
		if($("#imgpath1").val() == ''){
			alert("请上传清晰的营业执照正面照片");
			return;
		}
		if($("#imgpath4").val() == ''){
			alert("点击上传身份证正面");
			return;
		}
		if($("#imgpath5").val() == ''){
			alert("点击上传身份证反面");
			return;
		}
		var params={
			memberId:memberId,
			imgPathA1:$("#imgpath4").val(),
			imgPathA2:$("#imgpath5").val(),
			imgPath20:$("#imgpath1").val(),
		};
		if($("#cib_id").val()!=null&&$("#cib_id").val()!=""){
			params.id = $("#cib_id").val();
		}
		
		var url = "${bootAppPath}/cib/update";
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			console.log(res);
			if (data.response == 'success') {
				var map = new Map();
				map.put("_PAGE", "bisic_message/authentication_orgExamine");//必传
				map.put("role", role);
				map.put("cibId", data.data.id);
				_OPENPAGE_FORM(map);
			} else {
				alert(data.msg);
			}
		}
	}
	
	function loadImg(){
		var params = {
			memberId:memberId,
			type:role
		};
		var url = "${bootAppPath}/orginfo/rz";
		var res = bootPost(url,params);
		if(res!=null){
			var data = res.data;
			if (data.response == 'success') {
				var cib = data.data.cib;
		        if( cib!=null && cib!=''){
		        	if(cib.type == 1){
		        		$("#rzTab2").parent("li").addClass("bbd43c33");
		        		$("#rzTab2").parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
		        	}else{
		        		$("#rzTab1").parent("li").addClass("bbd43c33");
		        		$("#rzTab1").parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
		        	}
					$("#cib_id").val(cib.id);
					if(cib.imgPathA1!=null&& cib.imgPathA1!=''){//身份证正面
						console.log("已进入图片");
						$("#img4").attr('src',"${bootPic}/"+data.data.cib.imgPathA1);
						$("#imgpath4").val(cib.imgPathA1);
					}
					if(cib.imgPathA2!=null&& cib.imgPathA2!=''){//身份证反面
						$("#img5").attr('src',"${bootPic}/"+data.data.cib.imgPathA2);
						$("#imgpath5").val(cib.imgPathA2);
					}
					if(cib.imgPath20!=null&& cib.imgPath20!=''){//营业执照
						$("#img1").attr('src',"${bootPic}/"+data.data.cib.imgPath20);
						$("#imgpath1").val(cib.imgPath20);
					}
		        }
			}
		}
	}
	
	
		
</script>

