[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='法人信息']
[@main.header currentmenu='1'/]

    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/form.css">

    <link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/cropper.min.css">
    <link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/ImgCropping.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/yonyou/authentication.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/yonyou/mask.css">

    <script type="text/javascript" src="https://static.utiexian.com/js/ImgCropping/cropper.min.js"></script>

<div class="w1200 ha bc mt20 mb20">
	<!--左侧菜单-->
	[@main.left remark='1' /]
	<!--左侧菜单end-->
	<!--右侧菜单-->
	<div class="fl w997 box-shadow bcwhite box-sizing bc pl50 pr50 pt30 pb30">
	    <!-- 标题 -->
	    <div class="clearfix bbe0e0e0 pb10">
	        <div class="fl">开户管理</div>
	        <div id="statementBtn" class="fr c3366cc cp">开户声明</div>
	    </div>
	    <!-- 标题 end -->
	
	    <!-- 开户进度 -->
	    <div class="clearfix w570 bc oh pt30 pb30">
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng1.png" alt="申请开户">
	        </div>
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng2-1.png" alt="审核">
	        </div>
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng3-1.png" alt="完成开户">
	        </div>
	    </div>
	    <!-- 开户进度 end -->
	
	    <!-- 内容 -->
	    <div class="w570 bc">
	        <ul class="formGroup">
	            <li class="formItem f14 c2d2d2d lh20">
	                <label class="w130">证件类型：</label>
	                <a href="javacript:void(0);" id="examplesBtn" class="fr c3366cc">查看示例</a>
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <div class="fl ml130 clearfix oh">
	                    <label for="replaceImg1" class="fl dsb cp">
	                        <button id="replaceImg1" onclick="replaceImg(1);" style="display: none">身份证正面</button>
	                        <img src="https://img.utiexian.com/website/180903jingdong/kaihu/ID-card-up.png" id="img1" width="171" height="129" alt="身份证正面">
	                    	<input type="hidden" id="path_img1" />
	                    </label>
	                </div>
	
	                <div class="fl ml20 clearfix oh">
	                    <label for="replaceImg2" class="fl dsb cp">
	                        <button id="replaceImg2" onclick="replaceImg(2);" style="display: none">身份证反面</button>
	                        <img src="https://img.utiexian.com/website/180903jingdong/kaihu/ID-card-down.png" id="img2" width="171" height="129" alt="身份证反面">
	                        <input type="hidden" id="path_img2" />
	                    </label>
	                </div>
	            </li>
	
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">姓名：</label>
	                <input type="text" id="lepName" class="w360 validate[required]" placeholder="请输入营业执照上的法定代表人">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">身份证号：</label>
	                <input type="text" id="lepCardNo" class="w360 validate[required,custom[IdCard]]" placeholder="请输入营业执照上的法定代表人的身份证号">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">有期限至：</label>
	                <label for="yingyeqixian0" class="labelRadio">
	                    <input type="radio" name="lepLongTerm" id="yingyeqixian0" value="0" class="radio3">
	                    长期
	                </label>
	                <label for="yingyeqixian1" class="labelRadio">
	                    <input type="radio" name="lepLongTerm" id="yingyeqixian1" value="1" class="radio3" checked>
	                    固定期限
	                </label>
	                <input type="text" id="lepValidityEnd" class="w200 ml6 validate[required]" placeholder="请输入法人省份证上的截止日期">
	            </li>
	            <li class="formItem">
	                <button type="button" onclick="next();">下一步</button>
	            </li>
	        </ul>
	    </div>
	    <!-- 内容 end -->
	</div>
</div>
<div class="cb"></div>

<div class="w h pf bc05 zi10 top" style="display: none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="maskTitle">开户声明</div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn" class="dsb tc w20 cp">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!-- 开户声明 -->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150" style="display: none" id="statementWindow">
                    <div class="flex-row flex-direction-column pl20 pr20 pt20">
                        <p>开户之前，需要准备什么资料？<br>
                            1、营业执照正本扫描件<br>
                            2、法定代表人的身份证正、反面扫描件<br>
                            3、对公银行账号<br>
                            收票方需关联指定银行对公账户 <span class="ce84c3d">（须开通电票功能并完成授权）</span>。京东目前已经达成合作的指定银行有：平安银行<a href="${basePath}/PAB.pdf" style="text-decoration:underline;" download>(授权流程下载)</a>、中信银行<a href="${basePath}/ECITIC.pdf" style="text-decoration:underline;" download>(授权流程下载)</a>。<br>
                            <span class="ce84c3d">*扫描件必须为彩色原件的扫描件。</span><br>
                            京东开户和认证时不区分开户角色，若用户绑卡若未绑定指定银行的对公账户，则无法使用京东户收票。<br>
                        </p>
                    </div>
                    <div class="flex-row flex-align-self-center lh35 mt20">
                        <input type="button" value="确定" class="w140 h35 ba bad43c33 bce84c3d cwhite br3 cp closeBtn">
                    </div>
                </div>
                <!-- 开户声明 end-->

                <!-- 查看示例 -->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150 pt20 lh40" style="display: none" id="examplesWindow">
                    <p class="tl w pl65">正确示例</p>
                    <img src="https://img.utiexian.com/website/180903jingdong/kaihu/shenfenzhengone.png" alt="正确示例">
                    <p class="tl w pl65 mt20">错误示例</p>
                    <img src="https://img.utiexian.com/website/180903jingdong/kaihu/shenfenzhengtwo.png" alt="错误示例">
                </div>
                <!-- 查看示例 end -->
            </div>
        </div>
    </div>
</div>

<!-- 上传证件弹窗 -->
<!--证件识别中-->
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
<!--图片裁剪框 end-->
<!-- 上传证件弹窗 end -->

<input type="hidden" id="index" />
[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script>
    /*关闭所有弹窗*/
    $("#closeBtn ,.closeBtn").click(function(){
        $("#maskWindow").fadeOut(500); /*内容区域*/
        $("#maskFooter").fadeOut(500); /*底部按钮区域*/
        $("#statementWindow").fadeOut(500); /*开户声明区域*/
        $("#examplesWindow").fadeOut(500); /*查看示例区域*/
    });

    /*-----打开弹窗-----*/
    //开户声明
    $("#statementBtn").click(function(){
        console.log("hhhhh");
        $("#maskTitle").html("开户声明");
        $("#maskWindow").fadeIn(500);
        $("#statementWindow").fadeIn(500);
    });
    //查看示例
    $("#examplesBtn").click(function(){
        $("#maskTitle").html("查看示例");
        $("#maskWindow").fadeIn(500);
        $("#examplesWindow").fadeIn(500);
    });

    /* ------------------------------------ */
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
    function replaceImg(index) {
        $(".tailoring-container").toggle();
        $("#index").val(index);
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
            return false;
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
			var url = '${bootAppPath}/upload/image/quality';
			var params = {base64Image:base64Image,ocrGenre:"IDCARD",maxKb:2000};
			var data = bootPost(url,params);
			console.log(data);
			if(data != null){
				$(".loading").addClass("none");
				if(data.data.response == 'success'){
					$("#maskWindow1").addClass("none");
					var ocrInfo = data.data.data.ocrInfo;
					if(typeof(ocrInfo) != "undefined" && ocrInfo != null){
						if(ocrInfo.nameMap != null){
							$("#lepName").val(ocrInfo.nameMap.name);//省份证人姓名
						}
						if(ocrInfo.idMap != null){
							$("#lepCardNo").val(ocrInfo.idMap.id);//省份证
						}
					}
					var num = $("#index").val();
					if(num == 1){
						$("#img1").attr("src","${bootPic}/"+data.data.data.base64Image);
						$('#path_img1').val(data.data.data.base64Image);
					}else{
						$("#img2").attr("src","${bootPic}/"+data.data.data.base64Image);
						$('#path_img2').val(data.data.data.base64Image);
					}
				}else{
					alert(data.data.msg);
				}
			}
		});
	}
	
	/**
	* 到期
	*/
	$("input[name='lepLongTerm']").click(function(){
		if($(this).val() == 0){
			$("#lepValidityEnd").hide();
		}else{
			$("#lepValidityEnd").show();
		}
	});
	
	/**
	* 下一步
	*/
	function next(){
		var lepName = $("#lepName").val();//省份证人姓名
		var lepCardNo = $("#lepCardNo").val();//省份证
		var lepValidityEnd = $("#lepValidityEnd").val();//到期日期
		var lepLongTerm = $("input:radio[name='lepLongTerm']:checked").val();//（省份证到期类型）
		var lepUrlA = $("#path_img1").val();//图片
		var lepUrlB = $("#path_img2").val();//图片
		
		if(!$("#lepName").validationEngine("validate")){
			$("#lepName").focus();
			return ;
		}
		
		if(!$("#lepCardNo").validationEngine("validate")){
			$("#lepCardNo").focus();
			return ;
		}
		
	 	if(lepLongTerm == 0){
			localStorage.setItem("lepLongTerm",true);
			localStorage.setItem("lepValidityEnd","长期");
		}else{
			if(!$("#lepValidityEnd").validationEngine("validate")){
				$("#lepValidityEnd").focus();
				return ;
			}
			localStorage.setItem("lepLongTerm",false);
			lepValidityEnd = lepValidityEnd.replace('/','-').replace('/','-');
			lepValidityEnd = lepValidityEnd.replace('年','-').replace('月','-').replace('日','');
			localStorage.setItem("lepValidityEnd",lepValidityEnd);
		}

		localStorage.setItem("lepCardType","ID");
		localStorage.setItem("lepUrlA",lepUrlA);//图片a
		localStorage.setItem("lepUrlB",lepUrlB);//图片b
		localStorage.setItem("lepCardNo",lepCardNo);
		localStorage.setItem("lepName",lepName);
		
		location.href ="${basePath}/jd/apply/open/card";
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
    laydate(lepValidityEnd);
</script>