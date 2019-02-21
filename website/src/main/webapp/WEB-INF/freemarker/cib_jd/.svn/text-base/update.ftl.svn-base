[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='申请开户修改']
[@main.header currentmenu='1'/]
	<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/cropper.min.css">
	<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/ImgCropping.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/form.css">
    
        <link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/cropper.min.css">
    <link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/ImgCropping.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/yonyou/authentication.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/yonyou/mask.css">
    
    <script type="text/javascript" src="https://static.utiexian.com/js/website/tab.js"></script>
    <script type="text/javascript" src="https://static.utiexian.com/js/ImgCropping/cropper.min.js"></script>
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /] 
	<div class="fl w997 box-shadow bcwhite box-sizing bc pl50 pr50 pt30 pb30">
	    <!-- 标题 -->
	    <div class="clearfix bbe0e0e0 pb10">
	        <div class="fl">开户管理</div>
	        <a href="javascript:void(0);" class="fr c3366cc">开户声明</a>
	    </div>
	    <!-- 标题 end -->
	
	    <!-- 开户进度 -->
	    <div class="clearfix w570 ml225 oh pt30 pb30">
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng1.png" alt="申请开户">
	        </div>
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng2-2.png" alt="审核">
	        </div>
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng3-1.png" alt="完成开户">
	        </div>
	    </div>
	    <!-- 开户进度 end -->
	
	    <div class="w570 bc">
	        <div class="bl3_e84c3d pl10">营业执照</div>
	        <ul class="formGroup f14 c2d2d2d lh20">
	            <li class="formItem">
	                <div class="fl clearfix oh">
	                    <label for="replaceImg" class="fl dsb cp">
	                        <button id="replaceImg" onclick="replaceImg(1);" style="display: none">营业执照</button>
	                        <img id="blicUrlA" src="https://img.utiexian.com/website/180903jingdong/kaihu/upload.png" id="img" width="171" height="129" alt="营业执照">
	                        <input type="hidden" id="path_img" />
	                    </label>
	                </div>
	            </li>
	            <li class="formItem">
	                <label class="w130">公司名称：</label>
	                <input type="text"  value="" id="blicCompanyName">
	            </li>
	            <li class="formItem blicUscc">
	                <label class="w130">统一社会信用代码：</label>
	                <input type="text"  value="" id="blicUscc" class="w320">
	            </li>
	            <li class="formItem blicCardNo">
	                <label class="w130">注册号：</label>
	                <input type="text"  value="" id="blicCardNo">
	            </li>
	            <li class="formItem blicObaCardNo">
	                <label class="w130">税字号：</label>
	                <input type="text"  value="" id="blicObaCardNo">
	            </li>
	            <li class="formItem blicTrcCardNo">
	                <label class="w130">组织机构：</label>
	                <input type="text"  value="" id="blicTrcCardNo">
	            </li>
	            <li class="formItem">
	                <label for="" class="w130">住所：</label>
	                <select class="easyui-combobox w148 select148 provice" name="typeOne" id="provice" onchange="choose2(this)">
	                    
	                </select>
	                <select class="easyui-combobox w148 select148 ml20 city" name="typeTwo" id="city">
	                    
	                </select>
	                <input type="text" id="blicAddress" class="w360 ml130 mt10 blicAddress" placeholder="请输入营业执照上的住所">
	            </li>
	            <li class="formItem">
	                <label class="w130">经营范围：</label>
	                <textarea cols="5" id="blicScope" class="w360 h70 blicScope" style="overflow-y: scroll;" placeholder=""></textarea>
	            </li>
	            <li class="formItem">
	                <label for="" class="w130">营业期限至：</label>
	                <label for="yingyeqixian0" class="labelRadio">
	                    <input type="radio" name="blicValidityEnd" value="0" id="yingyeqixian0" class="radio3">
	                    长期
	                </label>
	                <label for="yingyeqixian1" class="labelRadio">
	                    <input type="radio" name="blicValidityEnd" value="1" id="yingyeqixian1" class="radio3" checked>
	                    固定期限
	                </label>
	                <input type="text" id="blicValidityEnd" class="w200 ml6 blicValidityEnd" placeholder="请输入营业执照上的截止日期">
	            </li>
	        </ul>
	        
	        <div class="bl3_e84c3d pl10">法人信息</div>
	        <ul class="formGroup f14 c2d2d2d lh20">
	            <li class="formItem">
	                <div class="fl clearfix oh">
	                    <label for="replaceImg1" class="fl dsb cp">
	                        <button id="replaceImg1" onclick="replaceImg(2);" style="display: none">身份证正面</button>
	                        <img id="lepUrlA" src="https://img.utiexian.com/website/180903jingdong/kaihu/upload.png" id="img" width="171" height="129" alt="身份证正面">
	                        <input type="hidden" id="path_img1" />
	                    </label>
	                </div>
	              
	                <div class="fl ml20">
	                    <label for="replaceImg2" class="dsb cp">
	                        <button id="replaceImg2" onclick="replaceImg(3);" style="display: none">身份证反面</button>
	                        <img id="lepUrlB" src="https://img.utiexian.com/website/180903jingdong/kaihu/upload.png" id="img" width="171" height="129" alt="身份证反面">
	                        <input type="hidden" id="path_img2" />
	                    </label>
	                </div>
	            </li>
	            <li class="formItem">
	                <label class="w130">姓名：</label>
	                <input type="text" value="" id="lepName">
	            </li>
	            <li class="formItem ">
	                <label class="w130">身份证号：</label>
	                <input type="text" value="" id="lepCardNo">
	            </li>
	            <li class="formItem">
	                <label for="" class="w130">有效期至：</label>
	                <label for="yingyeqixianA0" class="labelRadio">
	                    <input type="radio" name="lepValidityEnd" value="0" id="yingyeqixianA0" class="radio3">
	                    长期
	                </label>
	                <label for="yingyeqixianA1" class="labelRadio">
	                    <input type="radio" name="lepValidityEnd" value="1" id="yingyeqixianA1" class="radio3" checked>
	                    固定期限
	                </label>
	                <input type="text" id="lepValidityEnd" class="w200 ml6 blicValidityEnd" placeholder="请输入营业执照上的截止日期">
	            </li>
	        </ul>
	        
	        <div class="bl3_e84c3d pl10">银行卡信息</div>
	        <p class="f12 ce84c3d pt10">只有关联指定银行对公账户方可收票。京东目前已经达成合作的指定银行有：平安银行、中信银行。
	        </p>
	        <ul class="formGroup f14 c2d2d2d lh20">
	            <li class="formItem">
	                <label class="w130">开户行：</label>
	                <select class="w320 select320 selectBank" id="selectBank" oninput="selectBank(this);"></select>
	            </li>
	            <li class="formItem ">
	                <label class="w130">开户支行：</label>
	                <input type="text" class="w320 souchBank" value="" readonly id="occBankChildName1" onclick="lianProvice()">
	            </li>
	            <li class="formItem">
	                <label class="w130">账号：</label>
	                <input type="text" value="" id="occBankAccount">
	            </li>
	            <li class="formItem">
	                <button type="button" onclick="save();">保存修改</button>
	            </li>
	        </ul>
	    </div>
	    <div id="souchBank" class="bcwhite w360 pl10 pr10 box-shadow pa f14 lh30 none">
	        <div class="bbe0e0e0">请选择联行号</div>
	        <ul class="clearfix oh bbe0e0e0 ble0e0e0 mt10 tab-head">
	            <li class="fl pl20 pr20 bte0e0e0 bre0e0e0 cp selected lian_provice" onclick="changeTab(1)">省级</li>
	            <li class="fl pl20 pr20 bte0e0e0 bre0e0e0 cp none lian_city" onclick="changeTab(2)">市级</li>
	            <li class="fl pl20 pr20 bte0e0e0 bre0e0e0 cp none lian_bank" onclick="changeTab(3)">分行名</li>
	        </ul>
	        <ul class="tab-contents h200">
	            <li class="tab-content h oya myScrollbar" id="provice_content">
	                <ul class="tc f12 clearfix oh mt10" id="provice_zhi">
	                    
	                </ul>
	            </li>
	            <li class="tab-content h oya myScrollbar none" id="city_content">
	                <ul class="tc f12 clearfix oh mt10" id="city_zhi">
	                    
	                </ul>
	            </li>
	            <li class="tab-content h oya myScrollbar none" id="name_content">
	                <div class="clearfix bae0e0e0 br3 box-sizing pl10 pr10 h35 ml10 mr10 mt10"><input type="text" class="fl b0 w290 h30"><img class="fl w20 h20 mt6" src="https://img.utiexian.com/website/180903jingdong/kaihu/souch.png"></div>
	                <ul class="" id="name">
	                    
	                </ul>
	            </li>
	        </ul>
	    </div>
	</div>
</div>
<div class="cb"></div>

<!-- 上传证件弹窗 -->
<!--证件识别中-->
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
</div>
<!--弹窗-->

<!--图片裁剪框 start-->
<div style="display: none" class="tailoring-container">
    <div class="black-cloth" onClick="closeTailor(this)"></div>
    <div class="tailoring-content">
        <div class="tailoring-content-one">
            <label title="上传图片" for="chooseImg" class="l-btn choose-btn">
                <input type="file" accept="image/jpg,image/jpeg,image/png" name="file" id="chooseImg" class="hidden" onChange="selectImg(this)">
                选择图片
            </label>
            <div class="tailoring-text">
                <span>若图片不需要裁剪，请点击</span>
                <img src="https://img.utiexian.com/yonyou/handClicks.png">
                <a href="javascript:void(0);" onclick="OriginalPic()">使用原图</a>
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

<input type="hidden" id="index">
<input type="hidden" id="occBankName">
<input type="hidden" id="occBankCnap">
<input type="hidden" id="occBankChildName">
<input type="hidden" id="occBankChildCode">
<input type="hidden" id="blicCardType">
<input type="hidden" id="lepCardType">
<!--图片裁剪框 end-->
<!-- 上传证件弹窗 end -->
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript">
var memberId = '${member.id}';
$(function(){
	getInfo();
	$("#souchBank").css({"top": $(".souchBank").offset().top, "left":$(".souchBank").offset().left });
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
function replaceImg(num) {
	$("#index").val(num);
    $(".tailoring-container").toggle();
}

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
}
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
* boot 项目的图片上传
*/
function readFile(imgUrl){
	$(".loading").removeClass("none");
    $("#maskWindow1").removeClass("none");
	closeTailor();//关闭裁剪框
	if(imgUrl == null && imgUrl == ""){
		return ;
	}
	setTimeout(function(){
		var index = imgUrl.indexOf(",");
		var base64Image = imgUrl.substr(index+1);
		var url = '${bootAppPath}/upload/image/quality';
		var params = {base64Image:base64Image,ocrGenre:"BIZLICENCE",maxKb:2000};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			$(".loading").addClass("none");
			if(data.data.response == 'success'){
				$("#maskWindow1").addClass("none");
				var ocrInfo = data.data.data.ocrInfo;
				
				var num = $("#index").val();
				if(num == 1){
					$("#blicUrlA").attr("src","${bootPic}/"+data.data.data.base64Image);
					$('#path_img').val(data.data.data.base64Image);
					if(ocrInfo!=null){
						if(ocrInfo.bizLicenceNameMap != null){//公司名称
							$("#blicCompanyName").val(ocrInfo.bizLicenceNameMap.bizLicenceName);
						}
						if(ocrInfo.bizLicenceAddrMap != null){//住址
							$("#blicAddress").val(ocrInfo.bizLicenceAddrMap.bizLicenceAddr);
						}
						if(ocrInfo.blicScopeMap != null){//经营范围
							$("#blicScope").val(ocrInfo.blicScopeMap.blicScope);
						}
						if(ocrInfo.bizLicenceFoundedDateMap != null){//到期日期
							$("#blicValidityEnd").val(ocrInfo.bizLicenceFoundedDateMap.bizLicenceFoundedDate);
						}
						if(ocrInfo.bizLicenceRegisteredNoMap != null){//注册号（社会统一代码）
							$("#blicUscc").val(ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo);
						}
					}
				}else if(num == 2){
					$("#lepUrlA").attr("src","${bootPic}/"+data.data.data.base64Image);
					$('#path_img1').val(data.data.data.base64Image);
					if(ocrInfo!=null){
						if(ocrInfo.nameMap != null){
							$("#lepName").val(ocrInfo.nameMap.name);//省份证人姓名
						}
						if(ocrInfo.idMap != null){
							$("#lepCardNo").val(ocrInfo.idMap.id);//省份证
						}
						if(ocrInfo.birthMap != null){
							$("#lepValidityEnd").val(ocrInfo.birthMap.birth);//到期日期
						}
					}
				}else if(num == 3){
					$("#lepUrlB").attr("src","${bootPic}/"+data.data.data.base64Image);
					$('#path_img2').val(data.data.data.base64Image);
				}
				
			}else{
				alert(data.data.msg);
			}
		}
	});
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
 * 获取信息
 */
function getInfo(){
	var url = '${bootAppPath}/jdjr/get';
	var params = {memberId:memberId};
	var data = bootPost(url,params);
	if(data != null){
		console.log(data);
		if(data.data.response == 'success'){
			if(data.data.data!=null){
				var cib = data.data.data;
				
				var blicCardType = cib.blicCardType;
				$("#blicCardType").val(blicCardType);
				$("#lepCardType").val(cib.lepCardType);
				if(blicCardType!=null){
					if(blicCardType == "USC"){//统一社会信用代码
						if(cib.blicUscc!=null){
							$("#blicUscc").val(cib.blicUscc);
							$(".blicUscc").removeClass("none");
							$(".blicCardNo").addClass("none");
							$(".blicObaCardNo").addClass("none");
							$(".blicTrcCardNo").addClass("none");
						}
					}else{//多证合一
						$(".blicUscc").addClass("none");
						$(".blicCardNo").removeClass("none");
						$(".blicObaCardNo").removeClass("none");
						$(".blicTrcCardNo").removeClass("none");
						$("#blicCardNo").val(cib.blicCardNo);
						$("#blicObaCardNo").val(cib.blicObaCardNo);
						$("#blicTrcCardNo").val(cib.blicTrcCardNo);
					}
				}
				
				loadProvice(cib.blicProvince,cib.blicCity);
				
				$("#blicUrlA").attr("src","${bootPic}/"+cib.blicUrlA);
				$("#path_img").val(cib.blicUrlA);
				$("#blicCompanyName").val(cib.blicCompanyName);
				
				$("#blicAddress").val(cib.blicAddress);
				$("#blicScope").val(cib.blicScope);
				if(cib.blicLongTerm == "true"){
					$("#yingyeqixian0").attr("checked",true);
					$("#yingyeqixian1").attr("checked",false);
					$("#blicValidityEnd").hide();
				}else{
					$("#blicValidityEnd").val(cib.blicValidityEnd);
					$("#yingyeqixian0").attr("checked",false);
					$("#yingyeqixian1").attr("checked",true);
					$("#blicValidityEnd").show();
				}
				
				$("#lepUrlA").attr("src","${bootPic}/"+cib.lepUrlA);
				$("#path_img1").val(cib.lepUrlA);
				$("#lepUrlB").attr("src","${bootPic}/"+cib.lepUrlB);
				$("#path_img2").val(cib.lepUrlB);
				$("#lepName").val(cib.lepName);
				$("#lepCardNo").val(cib.lepCardNo);
				if(cib.lepLongTerm == "true"){
					$("#yingyeqixianA0").attr("checked",true);
					$("#yingyeqixianA1").attr("checked",false);
					$("#lepValidityEnd").hide();
				}else{
					$("#lepValidityEnd").val(cib.lepValidityEnd);
					$("#yingyeqixianA0").attr("checked",false);
					$("#yingyeqixianA1").attr("checked",true);
					$("#lepValidityEnd").show();
				}
				if(cib.occBankName!=null){
					loadBank(cib.occBankCnap);
				}else{
					loadBank(0);
				}
				$("#occBankAccount").val(cib.occBankAccount);
				$("#occBankChildName1").val(cib.occBankChildCode +" "+ cib.occBankChildName);
				
				$("#occBankName").val(cib.occBankName);
				$("#occBankCnap").val(cib.occBankCnap);
				$("#occBankChildCode").val(cib.occBankChildCode);
				$("#occBankChildName").val(cib.occBankChildName);
			}
		}else{
			alert(data.data.msg);
		}
	}
};

/**
* 加载省份
*/
var citylist;//根据省份选择城市
function loadProvice(pname,cname){
	$.ajax({
		url:"${basePath}/homepage/provicecity",
		type:"post",
		data:{},
		dataType:"json",
		success:function(data){
			var data = eval(data);
			if(data.response=='success'){
				var provicelist = data.provicelist;
				var provicehtml = "<option value='-1'>请选择省份</option>";
				for(var i=0;i<provicelist.length;i++){
					var provice = provicelist[i];
					if(provice.name == pname){
						provicehtml += "<option selected='selected' value='"+provice.name+"'>"+provice.name+"</option>";
					}else{
						provicehtml += "<option value='"+provice.name+"'>"+provice.name+"</option>";
					}
				}
				citylist = data.citylist;
				$("#provice").html(provicehtml);
				loadCity(pname,cname);
			}
		}
	});
}

/**
 * 加载城市
 */
function loadCity(pname,cname){
	var cityhtml = "<option value='-1'>请选择城市</option>";
	for(var i=0;i<citylist.length;i++){
		if(citylist[i].pname==pname){
			if(citylist[i].name == cname){
				cityhtml += "<option selected='selected' value='"+citylist[i].name+"'>"+citylist[i].name+"</option>";
			}else{
				cityhtml += "<option value='"+citylist[i].name+"'>"+citylist[i].name+"</option>";
			}
		}
	}
	$("#city").html(cityhtml);
};

/**
* 省份改变联动
*/
function choose2(obj){
	var cibType = $("input:radio[name='cibType']:checked").val();//（营业执照类型）
	if (obj != null && "" != obj && -1 != $(obj).val()) {
		var cityhtml = "<option value=''>请选择城市</option>";
		for(var i=0;i<citylist.length;i++){
			if(citylist[i].pname==$(obj).val()){
				cityhtml += "<option value='"+citylist[i].name+"'>"+citylist[i].name+"</option>";
			}
		}
		
		$("#city").html(cityhtml);
		$("#city").show();
	}else{
		$(".city").hide();
	}
}

/**
* 营业时间的选择
*/
$("input[name='blicValidityEnd']").click(function(){
	var type = $("input:radio[name='blicValidityEnd']:checked").val();//（营业执照类型）
	if(type == 0){
		$("#blicValidityEnd").hide();
	}else{
		$("#blicValidityEnd").show();
	}
});

/**
* 省份证时间的选择
*/
$("input[name='lepValidityEnd']").click(function(){
	var type = $("input:radio[name='lepValidityEnd']:checked").val();//（营业执照类型）
	if(type == 0){
		$("#lepValidityEnd").hide();
	}else{
		$("#lepValidityEnd").show();
	}
});

/**
* 加载银行
*/
function loadBank(code){
	var url = '${bootAppPath}/jdjr/querybankinfo';
	var params ;
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			if(data.data.data!=null){
				var html = "<option value='-1'>请选择开户行</option>";
				for(var i =0;i<data.data.data.list.length;i++){
					if(data.data.data.list[i].bankCode == code){
						html += "<option selected='selected' value='"+data.data.data.list[i].bankCode+"'>"+data.data.data.list[i].bankName+"</option>";
					}else{
						html += "<option value='"+data.data.data.list[i].bankCode+"'>"+data.data.data.list[i].bankName+"</option>";
					}
				}
				
				$("#selectBank").html(html);
			}
		}else{
			alert(data.data.msg);
		}
	}
 };
 
/**
* 选择银行
*/
function selectBank(obj){
	var options=$("#selectBank option:selected"); //获取选中的项
	$("#occBankName").val(options.text());
	$("#occBankCnap").val(options.val());
	$("#souchBank").addClass("none");
}

/**
* 用户加载联行号省份
*/
function lianProvice(){
	var bankCode = $("#selectBank").val();
	if(bankCode == -1){
		alert("请先选择开户行！")
		return;
	}
	$("#souchBank").removeClass("none");
	$(".lian_provice").html("省份");
	$(".lian_provice").removeClass("none");
	$(".lian_city").addClass("none");
	$(".lian_bank").addClass("none");
	
	$("#provice_content").removeClass("none");
	$("#city_content").addClass("none");
	$("#name_content").addClass("none");
	
	var url = '${bootAppPath}/jdjr/queryprovince';
		var params = {};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				if(data.data.data!=null){
					var html = "";
					for(var i =0;i<data.data.data.list.length;i++){
						html += "<li class='fl wmin90 cp' onclick=provice('"+data.data.data.list[i].provinceCode+"','"+data.data.data.list[i].provinceName+"')>"+data.data.data.list[i].provinceName+"</li>";
					}
					$("#provice_zhi").html(html);
				}
			}else{
				alert(data.data.msg);
			}
		}
}

/**
*用户选择省份
*/
function provice(code,name){
	$("#lian_provice").val(code);
	$(".lian_provice").html(name);
	$(".lian_city").html("城市");
	lianCity(code);
};
 
/**
 * 用户加载联行号城市
 */
function lianCity(code){
	var url = '${bootAppPath}/jdjr/querycity';
	var params = {provinceCode:code};
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			$(".lian_city").removeClass("none");
			$("#city_content").removeClass("none");
			$("#provice_content").addClass("none");
			console.log(data);
			if(data.data.data!=null){
				var html = "";
				for(var i =0;i<data.data.data.list.length;i++){
					html += "<li class='fl wmin90 cp' onclick=city('"+data.data.data.list[i].cityCode+"','"+data.data.data.list[i].cityName+"')>"+data.data.data.list[i].cityName+"</li>";
				}
				$("#city_zhi").html(html);
			}
		}else{
			alert(data.data.msg);
		}
	}
}

/**
*用户选择城市
*/
function city(code,name){
	$("#lian_city").val(code);
	$(".lian_city").html(name);
	lianBank(code);
};

/**
 * 用户加载联行号支行
 */
function lianBank(code){
	var url = '${bootAppPath}/jdjr/querybankcnapsinfo';
	var bankCode = $("#selectBank").val();
	var params = {bankCode:bankCode,cityCode:code};
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			$(".lian_bank").removeClass("none");
			$("#name_content").removeClass("none");
			$("#city_content").addClass("none");
			console.log(data);
			if(data.data.data!=null){
				var html = "";
				for(var i =0;i<data.data.data.list.length;i++){
					html += "<li onclick=selectBranch('"+data.data.data.list[i].bankCnapsCode+"','"+data.data.data.list[i].bankCnapsName+"')>"+data.data.data.list[i].bankCnapsName+"</li>";
				}
				$("#name").html(html);
			}
		}else{
			alert(data.data.msg);
		}
	}
}

 /**
  * 选择支行
  */
function selectBranch(code,name){
  	$("#souchBank").addClass("none")
  	$("#occBankChildName1").val(code+":"+name)
  	$("#occBankChildName").val(name);
  	$("#occBankChildCode").val(code);
  }
 
 /**
 * 保存修改
 */
function save(){
	var blicCompanyName = $("#blicCompanyName").val();
	var blicProvince = $("#provice").val();
	var blicCity = $("#city").val();
	var blicAddress = $("#blicAddress").val();
	var blicScope = $("#blicScope").val();
	var blicLongTerm = $("input:radio[name='blicValidityEnd']:checked").val();
	var blicValidityEnd = $("#blicValidityEnd").val();
	var blicCardType = $("#blicCardType").val();
	var blicUscc = $("#blicUscc").val();
	var blicCardNo = $("#blicCardNo").val();
	var blicObaCardNo =	$("#blicObaCardNo").val();
	var blicTrcCardNo =	$("#blicTrcCardNo").val();
	var blicUrlA = $("#path_img").val();//图片 
	if(blicLongTerm ==0){
		blicLongTerm = "true";
		blicValidityEnd = "长期";
	}else{
		blicLongTerm = "false";
		blicValidityEnd = blicValidityEnd.replace('年','-').replace('月','-').replace('日','');
	}
	
	var lepLongTerm = $("input:radio[name='lepValidityEnd']:checked").val();
	var lepValidityEnd = $("#lepValidityEnd").val();
	var lepCardType = $("#lepCardType").val();
	var lepUrlA = $("#path_img1").val();
	var lepUrlB = $("#path_img2").val();
	var lepCardNo = $("#lepCardNo").val();
	var lepName = $("#lepName").val();
	if(lepLongTerm ==0){
		lepLongTerm = "true";
		lepValidityEnd = "长期";
	}else{
		lepLongTerm = "false";
		lepValidityEnd = lepValidityEnd.replace('/','-').replace('/','-');
	}
	
	var occBankAccount = $("#occBankAccount").val();
	var occBankName = $("#occBankName").val();
	var occBankCnap = $("#occBankCnap").val();
	var occBankChildName = $("#occBankChildName").val();
	var occBankChildCode = $("#occBankChildCode").val();

	var url = '${bootAppPath}/jdjr/save';
	var params = {memberId:memberId,blicCompanyName:blicCompanyName,blicProvince:blicProvince,blicCity:blicCity,blicAddress:blicAddress,
			blicScope:blicScope,blicLongTerm:blicLongTerm,blicValidityEnd:blicValidityEnd,blicCardType:blicCardType,blicUscc:blicUscc,
			blicCardNo:blicCardNo,blicObaCardNo:blicObaCardNo,blicTrcCardNo:blicTrcCardNo,blicUrlA:blicUrlA,lepLongTerm:lepLongTerm,
			lepValidityEnd:lepValidityEnd,lepCardType:lepCardType,lepUrlA:lepUrlA,lepUrlB:lepUrlB,lepCardNo:lepCardNo,lepName:lepName,
			occBankAccount:occBankAccount,occBankName:occBankName,occBankCnap:occBankCnap,occBankChildName:occBankChildName,
			occBankChildCode:occBankChildCode,companyType:"ENTER"};
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			location.href = "${basePath}/jd/examine";
		}else{
			alert(data.data.msg);
		}
	}
	 
 }
 
//日历
!function(){
    laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
}();
//日期范围限制
var lepValidityEnd = {
    elem: '#lepValidityEnd',
    format: 'YYYY-MM-DD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16', //最大日期
    istime: true,//时分秒
    isclear: false,//清空
    istoday: false,//今天
    choose: function(datas){
        end.min = datas; //开始日选好后，重置结束日的最小日期
        end.start = datas //将结束日的初始值设定为开始日
    }
};
var blicValidityEnd = {
    elem: '#blicValidityEnd',
    format: 'YYYY-MM-DD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16', //最大日期
    istime: true,//时分秒
    isclear: false,//清空
    istoday: false,//今天
    choose: function(datas){
        end.min = datas; //开始日选好后，重置结束日的最小日期
        end.start = datas //将结束日的初始值设定为开始日
    }
};
laydate(blicValidityEnd);
laydate(lepValidityEnd);
</script>
[@main.footer/]
[/@main.body]