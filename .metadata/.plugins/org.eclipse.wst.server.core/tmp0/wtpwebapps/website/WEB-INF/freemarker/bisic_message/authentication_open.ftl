[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/cropper.min.css">
<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/ImgCropping.css">
[#include "/common/boot/cib.ftl"]
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--开户第一个页面：判断是否已有账户-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
    	[@main.cib_tool/]
        <!--票方开户-->
        <div class="pl200 pr200 f14">
            <!--审核不通过-->
            <div class="flex flex-direction-column w480 h100 bcf7f7f6 bae0e0e0 br3 mt26 bc none">
                <div class="flex-row flex-justify-end">
                    <label for="shClose" class="b0 mt10 mr10">
                        <img src="${imagePath}website/PJGJ/authentication/closeIcon.png" alt="关闭">
                        <button id="shClose" class="oln none"></button>
                    </label>
                </div>
                <div class="flex-row flex-align-center ml40 mt3 none">
                    <div><img src="${imagePath}website/PJGJ/authentication/failIcon.png" alt=""></div>
                    <div class="flex-direction-column ml25">
                        <div class="f18 cd43c33">审核失败，请重新申请！</div>
                        <div class="f14 c979797 mt8">请填写正确的信息！</div>
                    </div>
                </div>
            </div>
            <!--申请开户：第一步-->
            <div class="flex flex-direction-column w lh34" id="tubiao">
                <div class="flex-row mt35">
                    <img src="${imagePath}website/PJGJ/account/sqkhIcon0.png" alt="申请开户">
                    <img src="${imagePath}website/PJGJ/account/shIcon0.png" alt="审核" class="ml5">
                    <img src="${imagePath}website/PJGJ/account/wckhIcon0.png" alt="完成开户" class="ml5">
                </div>
                
                <!--证件上传-->
                <div class="flex-row flex-direction-column mt10">
                    <div class="flex-row pl30 mt25 f18">证件上传：</div>
                    <div class="flex-row flex-wrap flex-justify-start w530 f16 tc pl25">
                        <label for="replaceImg1" id="l1" class="w156 ml15 mt30 cp"><img id="img1" class="w156 h160" src="${imagePath}website/PJGJ/account/upload.png" alt="">
                            <button id="replaceImg1" style="display: none" class="replaceImg"></button><input type="hidden" name="imgpath1" id="imgpath1">
                           	<input type="hidden" id="bizLicenceAddr" value="${bizLicenceAddr }">
                           	<input type="hidden" id="bizLicenceFoundedDate" value="${bizLicenceFoundedDate }">
                            <input type="hidden" id="bizLicenceLegalName" value="${bizLicenceLegalName }">
                            <div>营业执照</div>
                        </label>
                        <label for="replaceImg4" id="l4" class="w156 ml15 mt30 cp"><img id="img4" class="w156 h160" src="${imagePath}website/PJGJ/account/upload.png" alt="">
                            <button id="replaceImg4" style="display: none" class="replaceImg"></button><input type="hidden" name="imgpath4" id="imgpath4">
                            <input type="hidden" id="legalCertNo" value="${legalCertNo}">
                            <div>法人身份证正面</div>
                        </label>
                        <label for="replaceImg5" id="l5" class="w156 ml15 mt30 cp"><img id="img5" class="w156 h160" src="${imagePath}website/PJGJ/account/upload.png" alt="">
                            <button id="replaceImg5" style="display: none" class="replaceImg"></button><input type="hidden" name="imgpath5" id="imgpath5">
                            <div>法人身份证反面</div>
                        </label>
                    </div>
                </div>
                <div class="flex-row flex-direction-column pl7 pr7 none openAccountDiv2  mt10">
                <h3 class="cd43c33">请认真核对以下开户信息，如信息有误或遗漏请手动补充修改</h3>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">公司名称：</div>
                        <input type="text" id="company" value="${company}" maxlength="60" class="w460 bae0e0e0 br3 ti10 validate[required]" placeholder="请输入公司名称">
                    </div>
                    <div class="flex-row h34 mt10">
                        <div class="flex-col-3">注册号：</div>
                        <input type="text" id="regNum" value="${regNum}" maxlength="18" onchange="checkIsAccount()" class="w460 bae0e0e0 br3 ti10 validate[required,custom[regNum]]" placeholder="请输入营业执照上的统一社会信用代码">
                    </div>
                </div>
                <div class="flex-row flex-direction-column none openAccountDiv2">
                    <div class="flex-row h24 lh24 mt20 bl3_d43c33">
                        <div class="flex-col-3 fb ml10">企业经办人</div>
                    </div>
                    <div class="flex-row flex-direction-column pl7 pr7">
                        <div class="flex-row h34 mt10">
                            <div class="flex-col-3">经办人姓名：</div>
                            <input type="text" id="name" value="${name}" class="w460 bae0e0e0 br3 ti10 validate[required]" placeholder="请填写开户经办人姓名">
                        </div>
                        <div class="flex-row h34 mt10">
                            <div class="flex-col-3">手机号：</div>
                            <input type="tel" id="phone" disabled class="w460 bae0e0e0 br3 ti10 validate[required,custom[phone]]" value="${member.mobile}" placeholder="请输入经办人手机号">
                        </div>
                        <div class="flex-row h34 mt10">
                            <div class="flex-col-3">身份证号：</div>
                            <input type="text" id="IdCard" value="${IdCard}" class="w460 bae0e0e0 br3 ti10 validate[required,custom[IdCard]]" placeholder="请输入经办人身份证号">
                        </div>
                        <div class="flex-row h34 mt10">
                            <div class="flex-col-3">邮箱号：</div>
                            <input type="email" id="email" value="${email}" maxlength="30" class="w460 bae0e0e0 br3 ti10 validate[required,custom[email]]" placeholder="请输入经办人邮箱号">
                        </div>
                        <div class="flex-row w mt26 flex-justify-center">
                            <input type="button" class="w110 bce84c3d bad43c33 cwhite br3 h34 lh34" onclick="nextStep();" value="下一步">
                        </div>
                    </div>
                </div>
            </div>
           
            </div>
        </div>

    </div>
    <div class="cb"></div>
</div>
[@main.right /]
<!--弹窗-->
<div class="w h pf bc05 zi200 top none" id="maskWindow1">
	<!--loading-->
    <div class="flex flex-alignItems-center flex-justify-center w h none loading">
        <div class="flex-row flex-direction-column w590 br3 pb20">
			<div class="pl20 pr20">
                <div class="w tc mt10 mb10">
		        	<img alt="" src="${image_url}/website/discount/recognition1.gif">
		        </div>
			</div>
		</div>
	</div>
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="title"> 开户声明</div>
                <!--关闭按钮
                <lable for="closeIcon" id="closeBtn1">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭" class="cp">
                    <button id="closeIcon" class="oln none"></button>
                </lable>
                -->
            </div>
            <!---->
            <div class="pl20 pr20">
                <!--企业-->
                <div class="flex-row flex-direction-column w lh30 mt20 none" id="bnsWindow">
                    <div class="flex-row pl20 flex-direction-column">
						<div>开户之前，需要准备什么资料？</div>
						<div>1、营业执照正本扫描件</div>
						<div>2、法定代表人的身份证正、反面扫描件</div>
						<div>3、对公银行账号（<span class="cd43c33 dsib">出票方</span>可关联全国任一银行对公账户，<span class="cd43c33 dsib">收票方</span>需关联兴业银行对公账户，并开通电票功能和网上采购员权限）</div>
                    </div>
                    <div class="f14 cd43c33 pl20">*扫描件必须为彩色原件的扫描件</div>
                    <!--按钮操作-->
                    <div class="flex-row flex-direction-column pl20 none bnsbutton">
						<div class="w oh lh18">----------------------------------------------------------------------------</div>
						<div class="flex-row flex-direction-row flex-justify-space-between lh35">
						 	<div>我只需要出票（卖票），目前没有收票的需求</div>
						 	<input type="button" value="票方开户" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 discountBtn cp">
                    	</div>
                    </div>
                    <div class="flex-row flex-direction-column pl20 none orgbutton">
						<div class="w oh lh18">----------------------------------------------------------------------------</div>
						<div class="flex-row flex-direction-row flex-justify-space-between lh35">
							<div>我需要收票，如果有需要也会出票</div>
							<input type="button" value="资方开户" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 discountBtn1 cp">
						</div>
                    </div>
                    <div class="flex-row flex-direction-column pl20 f13 lh20">
                    	<div class="w oh lh18">---------------------------------------------------------------------------------------------</div>
						<div>温馨提示：</div>
						<div>使用“收票方（资方）”角色的核心功能（如报价、收票），须绑定兴业银行对公账户（开通电票功能和<span class="cd43c33">网上采购员</span>权限）才可以收票，请确认您公司是否已有兴业银行对公账户并开通相关业务。</div>
                    </div>
                </div>
            </div>
         </div>
    </div>
</div>
<!--图片裁剪框 start-->
<div style="display: none" class="tailoring-container">
	<input type="hidden" id="currentImg" >
    <div class="black-cloth" onClick="closeTailor(this)"></div>
    <div class="tailoring-content">
		<div class="tailoring-content-one">
		    <label title="上传图片" for="chooseImg" class="l-btn choose-btn">
		        <input type="file" accept="image/jpg,image/jpeg,image/png" name="file" id="chooseImg" class="hidden" onChange="selectImg(this)">
		        选择图片
		    </label>
		    <div class="tailoring-text">
			    <span>若图片不需要裁剪，请点击</span>
			    <img src="${image_url}/website/discount/handClicks.png">
			    <a href="#" onclick="OriginalPic()">使用原图</a>
		    </div>
		    <div class="close-tailoring"  onclick="closeTailor(this)">×</div>
		</div>
		<div class="tailoring-content-two">
		    <div class="tailoring-box-parcel">
		        <img id="tailoringImg">
		    </div>
		</div>
		<div class="tailoring-content-three">
		    <button class="l-btn cropper-reset-btn">复位</button>
		    <button class="l-btn cropper-rotate-btn">旋转</button>
		    <button class="l-btn cropper-scaleX-btn">换向</button>
		    <button class="l-btn sureCut" id="sureCut">确定裁剪</button>
		</div>
	</div>
</div>
<!--图片裁剪框 end-->
<script type="text/javascript" src="https://static.utiexian.com/js/ImgCropping/cropper.min.js"></script>
[@main.footer/]
[/@main.body]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${jsPath}/map.js"></script>

<script>
	var role;
	var memberId = '${member.id}';
	var imgpath1;
	var imgpath4;
	var imgpath5;
	$(document).ready(function () {
		imgpath1 = '${imgpath1}';
		imgpath4 = '${imgpath4}';
		imgpath5 = '${imgpath5}';
		$("#imgpath1").val(imgpath1);
		$("#imgpath4").val(imgpath4);
		$("#imgpath5").val(imgpath5);
		if(imgpath1 != "" && imgpath4 != "" && imgpath5 != ""){
			$("#img1").attr("src",'${bootPic}'+imgpath1);
			$("#img4").attr("src",'${bootPic}'+imgpath4);
			$("#img5").attr("src",'${bootPic}'+imgpath5);
			$(".openAccountDiv2").removeClass("none");
		}else{
			$("#maskWindow1").removeClass("none");
			$("#bnsWindow").removeClass("none"); //企业
			role = ${role};
			if(role == 0){
				$(".bnsbutton").removeClass("none");
				var url = '${bootAppPath}/orginfo/rz';
				var params = {memberId:memberId,type:1};
				var res = bootPost(url,params);
				if(res != null){
					var data = res.data;
					if (data.response == 'success') {
						var active = data.data;
						var cib = active.cib;
						if(cib.name == null || cib.status==7){
							$(".orgbutton").removeClass("none");
						}
					}
				}
			}else if(role == 1){
				$(".orgbutton").removeClass("none");
				var url = '${bootAppPath}/orginfo/rz';
				var params = {memberId:memberId,type:0};
				var res = bootPost(url,params);
				if(res != null){
					var data = res.data;
					if (data.response == 'success') {
						var active = data.data;
						var cib = active.cib;
						if(cib.name == null || cib.status==7){
							$(".bnsbutton").removeClass("none");
						}
					}
				}
			}
		}
	});
	
	//弹出框水平垂直居中
    (window.onresize = function () {
    	var win_height = window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight;
        var win_width = window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth;
        if (win_width <= 768){
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight())/2,
                "left": 0
            });
        }else{
            $(".tailoring-content").css({
                "top": (win_height - $(".tailoring-content").outerHeight())/2,
                "left": (win_width - $(".tailoring-content").outerWidth())/2
            });
        }
    })();

	
    //弹出图片裁剪框
    $(".replaceImg").click(function () {
    	if($(this).attr("id") == "replaceImg1"){
    		$("#currentImg").val("BIZLICENCE");
    	}else if($(this).attr("id") == "replaceImg4"){
    		$("#currentImg").val("IDCARD");
    	}else if($(this).attr("id") == "replaceImg5"){
    		$("#currentImg").val("");
    	}
        $(".tailoring-container").toggle();
    });
    
    //图像上传
    function selectImg(file) {
        if (!file.files || !file.files[0]){
            return;
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            var replaceSrc = evt.target.result;
            //更换cropper的图片
            $('#tailoringImg').cropper('replace', replaceSrc,false);//默认false，适应高度，不失真
        }
        reader.readAsDataURL(file.files[0]);
    };
    
    //cropper图片裁剪
    $('#tailoringImg').cropper({
        guides: false,  //裁剪框的虚线(九宫格)
        autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
        movable: false, //是否允许移动图片
        dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
        movable: true,  //是否允许移动剪裁框
        resizable: true,  //是否允许改变裁剪框的大小
        zoomable: true,  //是否允许缩放图片大小
        mouseWheelZoom: true,  //是否允许通过鼠标滚轮来缩放图片
        touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
        rotatable: true,  //是否允许旋转图片
        crop: function(e) {
            // 输出结果数据裁剪图像。
        }
    });
    
    //旋转
    $(".cropper-rotate-btn").on("click",function () {
        $('#tailoringImg').cropper("rotate", 45);
    });
    
    //复位
    $(".cropper-reset-btn").on("click",function () {
        $('#tailoringImg').cropper("reset");
    });
    
    //换向
    var flagX = true;
    $(".cropper-scaleX-btn").on("click",function () {
        if(flagX){
            $('#tailoringImg').cropper("scaleX", -1);
            flagX = false;
        }else{
            $('#tailoringImg').cropper("scaleX", 1);
            flagX = true;
        }
        flagX != flagX;
    });

    //裁剪后的处理
    $("#sureCut").on("click",function () {
        if ($("#tailoringImg").attr("src") == null ){
        	alert("请先选择图片");
            return ;
        }else{
            var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式

            readFile(base64url);
        }
    });
    
    //关闭裁剪框
    function closeTailor() {
        $(".tailoring-container").toggle();
    }
    
    /**
	* 使用选择的图片，未裁剪
	*/
	function OriginalPic(){
		if ($("#tailoringImg").attr("src") == null ){
			alert("请先选择图片");
            return ;
		}else{
			var base64url = $('#tailoringImg').attr("src");
			readFile(base64url);
		}
	};
	
	/**
	* boot 项目的图片上传
	*/
	function readFile(imgUrl){
		$(".loading").removeClass("none");
        $("#maskWindow1").removeClass("none");
		closeTailor();//关闭裁剪框
        var currentImg;
        if($("#currentImg").val()!=""){
        	currentImg = $("#currentImg").val();
        }
		if(imgUrl == null && imgUrl == ""){
			return ;
		}
		setTimeout(function(){
			var index = imgUrl.indexOf(",");
			var base64Image = imgUrl.substr(index+1);
			var url = '${bootAppPath}/upload/image';
			var params = {base64Image:base64Image,ocrGenre:currentImg};
			var data = bootPost(url,params);
			console.log(data);
			if(data != null){
				$(".loading").addClass("none");
				if(data.data.response == 'success'){
					$("#maskWindow1").addClass("none");
					var ocrInfo = data.data.data.ocrInfo;
					if(typeof(ocrInfo) != "undefined" && ocrInfo != null){
						if(ocrInfo.bizLicenceNameMap != null){
							$("#company").val(ocrInfo.bizLicenceNameMap.bizLicenceName);
						}
						if(ocrInfo.bizLicenceRegisteredNoMap != null){
							$("#regNum").val(ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo);
							checkIsAccount();
						}
						if(ocrInfo.bizLicenceLegalNameMap != null){
							$("#bizLicenceLegalName").val(ocrInfo.bizLicenceLegalNameMap.bizLicenceLegalName);
						}
						if(ocrInfo.bizLicenceAddrMap != null){
							$("#bizLicenceAddr").val(ocrInfo.bizLicenceAddrMap.bizLicenceAddr);
						}
						if(ocrInfo.bizLicenceFoundedDateMap != null){
							$("#bizLicenceFoundedDate").val(ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate);
						}
						if(ocrInfo.idMap != null){
							$("#legalCertNo").val(ocrInfo.idMap.id);
						}
					}else{
						$(".openAccountDiv2").removeClass('none');
					}
					if(currentImg == "BIZLICENCE"){
						$("#replaceImg1").prev().attr("src","${bootPic}/"+data.data.data.base64Image);
						$('#replaceImg1').next().val(data.data.data.base64Image);
					}else if(currentImg == "IDCARD"){
						$("#replaceImg4").prev().attr("src","${bootPic}/"+data.data.data.base64Image);
						$('#replaceImg4').next().val(data.data.data.base64Image);
					}else{
						$("#replaceImg5").prev().attr("src","${bootPic}/"+data.data.data.base64Image);
						$('#replaceImg5').next().val(data.data.data.base64Image);
					}
				}else{
					alert(data.data.msg);
				}
			}
		});
	}
	//关闭按钮
	$(".discountBtn1").click(function(){
		$("#maskWindow1").addClass("none");
		$(".loading").addClass("none"); //机构
		$("#bnsWindow").addClass("none"); //机构
		$("#rzTab1").next().children().text("票方开户");
		$("#rzTab2").next().children().text("资方开户");
		$("#rzTab1").parent("li").removeClass("bbd43c33");
		$("#rzTab2").parent("li").addClass("bbd43c33");
		$("#rzTab1").next().children("label").removeClass("cd43c33");
		$("#rzTab2").next().children("label").addClass("cd43c33");
		role = 1;
	});
	
	//关闭按钮
	$(".discountBtn").click(function(){
		$("#maskWindow1").addClass("none");
		$(".loading").addClass("none");
		$("#bnsWindow").addClass("none"); //企业
		$("#rzTab1").next().children().text("票方开户")
		$("#rzTab2").next().children().text("资方开户")
		$("#rzTab1").parent("li").addClass("bbd43c33");
		$("#rzTab2").parent("li").removeClass("bbd43c33");
		$("#rzTab1").next().children("label").addClass("cd43c33");
		$("#rzTab2").next().children("label").removeClass("cd43c33");
		role = 0;
	});
	
	/**
	*检查是否已经开过户
	*/
	function checkIsAccount(){
		console.log("fresh");
		if(!$("#company").validationEngine("validate")){
    		$("#company").focus();
    		setTimeout(function(){$("#company").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#regNum").validationEngine("validate")){
    		$("#regNum").focus();
    		setTimeout(function(){$("#regNum").validationEngine('hideAll');},1000);
    		return;
    	}
		var regNum = $("#regNum").val();
		var url = '${bootAppPath}/cib/get/same';
		var params = {memberId:memberId,bizLicenceRegisteredNo:regNum};
		var res = bootPost(url,params);
		console.log(res);
		if(res != null){
			var data = res.data;
			if (data.response == 'success') {
				if (data.data.type == 0 ) {//已经开户的角色是企业
					if(role == 1 ){//当前选择的角色是机构，去开户
						$(".openAccountDiv2").removeClass('none');
					}else{//是企业，去绑定
						console.log("当前开户的角色是企业，去绑定");
						var map = new Map();
						map.put("_PAGE", "bisic_message/authentication_orgUser1");//必传
						map.put("regNum", regNum);
						map.put("company", data.data.name);
						map.put("cibId", data.data.id);
						map.put("role", role);
						_OPENPAGE_FORM(map);
					}
				}else if(data.data.type == 1){//已经开户的角色是机构直接去绑定
					var map = new Map();
					map.put("_PAGE", "bisic_message/authentication_orgUser1");//必传
					map.put("regNum", regNum);
					map.put("cibId", data.data.id);
					map.put("company", data.data.name);
					map.put("role", role);
					_OPENPAGE_FORM(map);
		        }else {//两边都没开户去开户
					$(".openAccountDiv2").removeClass('none');
				}
			} else {
				alert(data.msg);
			  	//弹出提示信息
			}
		}
	}
	
	function nextStep(){
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
		if(!$("#name").validationEngine("validate")){
    		$("#name").focus();
    		setTimeout(function(){$("#name").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#phone").validationEngine("validate")){
    		$("#phone").focus();
    		setTimeout(function(){$("#phone").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#IdCard").validationEngine("validate")){
    		$("#IdCard").focus();
    		setTimeout(function(){$("#IdCard").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#email").validationEngine("validate")){
    		$("#email").focus();
    		setTimeout(function(){$("#email").validationEngine('hideAll');},1000);
    		return;
    	}
    	var company = $('#company').val();//公司名称
    	var regNum = $('#regNum').val();
    	var name = $('#name').val();
    	var phone = $('#phone').val();
    	var idCard = $('#IdCard').val();
    	var email = $('#email').val();
    	var imgpath1 = $('#imgpath1').val();
    	var imgpath4 = $('#imgpath4').val();
    	var imgpath5 = $('#imgpath5').val();
    	var bizLicenceAddr = $('#bizLicenceAddr').val();
    	var bizLicenceFoundedDate = $('#bizLicenceFoundedDate').val();
    	var bizLicenceLegalName = $('#bizLicenceLegalName').val();
    	var legalCertNo = $('#legalCertNo').val();
    	var map = new Map();
		map.put("_PAGE", "bisic_message/authentication_open2");//必传
		map.put("role", role);
		map.put("company", company);
		map.put("regNum", regNum);
		map.put("name", name);
		map.put("phone", phone);
		map.put("IdCard", idCard);
		map.put("email", email);
		map.put("imgpath1", imgpath1);
		map.put("imgpath4", imgpath4);
		map.put("imgpath5", imgpath5);
		map.put("bizLicenceAddr", bizLicenceAddr);
		map.put("bizLicenceFoundedDate", bizLicenceFoundedDate);
		map.put("bizLicenceLegalName", bizLicenceLegalName);
		map.put("legalCertNo", legalCertNo);
		console.log(map);
		_OPENPAGE_FORM(map);
	}
	
</script>