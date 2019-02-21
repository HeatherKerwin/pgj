[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='申请开户']
[@main.header currentmenu='1'/]

	<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/cropper.min.css">
	<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/ImgCropping.css">

	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/form.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/yonyou/authentication.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/yonyou/mask.css">
    
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="https://static.utiexian.com/js/ImgCropping/cropper.min.js"></script>

<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
	<div class="fl w997 box-shadow bcwhite box-sizing bc pl50 pr50 pt30 pb30">
	    <!-- 标题 -->
	    <div class="clearfix">
	        <ul class="fl f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite rzTab oh">
			    <li class="w250 lh50 fl bre0e0e0">
			        <input type="radio" name="rzTab" id="jd" value="0" class="none"><a href="javascript:void(0);" class="c2d2d2d"><label
			            for="jd" class="dsb bbd43c33">京东管理</label></a>
			    </li>
			    <li class="w250 lh50 fl bre0e0e0">
			        <input type="radio" name="rzTab1" id="rzTab11" value="1" class="none"><a href="javascript:checkAuthentication(0,'','');" class="c2d2d2d"><label
			            for="rzTab1" class="dsb">兴业票方开户</label></a>
			    </li>
			    <li class="w250 lh50 fl bre0e0e0">
			        <input type="radio" name="rzTab1" id="rzTab21" value="2" class="none"><a href="javascript:checkAuthentication(1,'','');" class="c2d2d2d"><label
			            for="rzTab2" class="dsb">兴业资方开户</label></a>
			    </li>
			</ul>
	        <div id="statementBtn" class="fr lh50 c3366cc cp">开户声明</div>
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
	        <ul class="formGroup pb0">
	            <li class="formItem f14 c2d2d2d lh20" id="certificatesType">
	                <label class="w130">证件类型：</label>
	                <label for="type1" class="labelRadio">
	                    <input type="radio" name="cibType" id="type1" value="0" class="radio3" checked>
	                    统一社会信用代码证
	                </label>
	                <label for="type2" class="labelRadio">
	                    <input type="radio" name="cibType" id="type2" value="1" class="radio3" >
	                    多证合一
	                </label>
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label class="w130">营业执照：</label>
	                <div class="fl clearfix oh">
	                    <label for="replaceImg1" class="fl dsb cp">
	                        <button id="replaceImg1" onclick="replaceImg();" style="display: none">身份证正面</button>
	                        <img src="https://img.utiexian.com/website/180903jingdong/kaihu/upload.png" id="img" width="171" height="129" alt="身份证正面">
	                        <input type="hidden" id="path_img" />
	                    </label>
	                    <a href="javacript:void(0);" id="examplesBtn" class="fl c3366cc ml10 mt110">查看示例</a>
	                    <p class="fl dsb w220 ce84c3d ml10 mt110" style="display:none" id="prompt">该营业执照已开户，请直接绑定。</p>
	                </div>
	            </li>
	        </ul>
	
	        <ul class="formGroup pt0" style="display:none" id="xinyong">
	        	<li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">公司名称：</label>
	                <input type="text" id="blicCompanyName1" class="w360 blicCompanyName validate[required]" placeholder="请与营业执照保持一致">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">统一社会信用代码：</label>
	                <input type="text" id="blicUscc" maxlength="18" class="w360 bizLicenceRegisteredNoMap validate[required]" placeholder="请输入营业执照上的统一社会信用代码" oninput="checkBlicUscc();">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">住所：</label>
	                <select class="easyui-combobox w148 select148 provice" name="typeOne" id="provice1" onchange="choose2(this)">
	                    
	                </select>
	                <select class="easyui-combobox w148 select148 ml20 city" name="typeTwo" id="city1">
	                    
	                </select>
	                <input type="text" id="blicAddress1" class="w360 ml130 mt10 blicAddress validate[required]" placeholder="请输入营业执照上的住所">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">经营范围：</label>
	                <textarea id="blicScope1" cols="5" class="w360 h60 blicScope validate[required]" style="overflow-y: scroll;" placeholder=""></textarea>
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">营业期限至：</label>
	                <label for="yingyeqixianA0" class="labelRadio">
	                    <input type="radio" name="yingyeqixian1" value="0" id="yingyeqixianA0" class="radio3">
	                    长期
	                </label>
	                <label for="yingyeqixianA1" class="labelRadio">
	                    <input type="radio" name="yingyeqixian1" value="1" id="yingyeqixianA1" class="radio3" checked>
	                    固定期限
	                </label>
	                <input type="text" id="blicValidityEnd1" class="w200 ml6 blicValidityEnd validate[required]" placeholder="请输入营业执照上的截止日期">
	            </li>
	            <li class="formItem">
	                <button type="button" class="next" onclick="next(1);">下一步</button>
	                <button type="button" class="binding" style="display: none" onclick="binding();">绑定开户</button>
	            </li>
	        </ul>
	
	        <ul class="formGroup pt0" style="display: none" id="various">
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">名称：</label>
	                <input type="text" class="w360 blicCompanyName validate[required]" id="blicCompanyName2" placeholder="请输入营业执照上的名称">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">注册号/等级码：</label>
	                <input type="text" id="blicCardNo" class="w360 bizLicenceRegisteredNoMap validate[required]" placeholder="请输入注册号/等级码" oninput="checkBlicCardNo();">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">税字号：</label>
	                <input type="text" id="blicObaCardNo" class="w360 validate[required]" placeholder="请输入税字号">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">组织机构代码：</label>
	                <input type="text" id="blicTrcCardNo" class="w360 validate[required]" maxlength="10" placeholder="请输入组织机构代码">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">住所：</label>
	                <select class="easyui-combobox w148 select148 provice" name="typeOne" id="provice2" onchange="choose2(this)">
	                </select>
	                <select class="easyui-combobox w148 select148 ml20 city" name="typeTwo" id="city2">
	                </select>
	
	                <input type="text" id="blicAddress2" class="w360 ml130 mt10 blicAddress validate[required]" placeholder="请输入营业执照上的住所">
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">经营范围：</label>
	                <textarea cols="5" id="blicScope2" class="w360 h60 blicScope validate[required]" style="overflow-y: scroll;" placeholder=""></textarea>
	            </li>
	            <li class="formItem f14 c2d2d2d lh20">
	                <label for="" class="w130">营业期限至：</label>
	                <label for="yingyeqixianB0" class="labelRadio">
	                    <input type="radio" name="yingyeqixian2" id="yingyeqixianB0" value="0" class="radio3">
	                    长期
	                </label>
	                <label for="yingyeqixianB1" class="labelRadio">
	                    <input type="radio" name="yingyeqixian2" id="yingyeqixianB1" value="1" class="radio3" checked>
	                    固定期限
	                </label>
	                <input type="text" id="blicValidityEnd2" class="w200 ml6 blicValidityEnd validate[required]" placeholder="请输入营业执照上的截止日期">
	            </li>
	            <li class="formItem">
	                <button type="button" class="next" onclick="next(2);">下一步</button>
	                <button type="button" class="binding" style="display: none" onclick="binding();">绑定开户</button>
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
                <div id="maskTitle"></div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn" class="dsb tc w20 cp">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--开户声明-->
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
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150 pt20" style="display: none" id="examplesWindow">
                    <img src="https://img.utiexian.com/website/180903jingdong/kaihu/yingyezhizhao.png" alt="示例">
                </div>
                <!-- 查看示例 end -->
            </div>
        </div>
    </div>
</div>
<!-- 上传证件弹窗 -->

<!-- 京东协议的弹窗  -->
<div class="w h pf bc05 zi10 top" style="display: bliack" id="maskJdWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="maskTitle">京东协议</div>
                <!--关闭按钮-->
                <label for="jdCloseIcon" id="jdCloseBtn" class="dsb tc w20 cp">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="jdCloseIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <div class="flex-row flex-direction-column w" style="display: block">
	                <div class="flex-row w box-sizing pl20 pr20 pt20 h300 oya myScrollbar">
	                	<iframe src="${bootAppPath}/jdjr/login?memberId=${member.id}"  frameborder="0" id="external-frame" class="w h oya myScrollbar"></iframe>
                	</div>
                	<div class="flex-row flex-justify-center w bte0e0e0 lh35 pt20 mt20">
                        <input type="button" value="下一步" onclick="protocol();" class="w140 h35 ba bad43c33 bce84c3d cwhite br3 cp">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 京东协议的弹窗 end -->

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
<!--图片裁剪框 end-->
<!-- 上传证件弹窗 end -->
<input type="hidden" id="jdjrId"> 
[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script>
    $(function () {
        $(".city").hide(); //初始化的时候第二个下拉列表隐藏
        loadProvice();
    });
    
    /**
    * 京东协议
    */
    function protocol(){
    	var url = '${bootAppPath}/jdjr/login/check';
		var params = {memberId:memberId};
		var res = bootPost(url,params);
		if(res != null){
			console.log(res);
			var data = res.data;
			if (data.response == 'success') {
				$("#maskJdWindow").fadeOut(500); /*内容区域*/
			}else{
				alert(data.msg);
			}
		}
    };

    /*关闭所有弹窗*/
    $("#closeBtn ,.closeBtn").click(function(){
        $("#maskWindow").fadeOut(500); /*内容区域*/
        $("#maskFooter").fadeOut(500); /*底部按钮区域*/
        $("#statementWindow").fadeOut(500); /*开户声明区域*/
        $("#examplesWindow").fadeOut(500); /*查看示例区域*/
    });
    /**
    * 关闭京东协议弹窗
    */
    $("#jdCloseBtn ,.jdCloseIcon").click(function(){
    	$("#maskJdWindow").fadeOut(500); /*内容区域*/
    	checkAuthentication(0,'','');
    });

    /*-----打开弹窗-----*/
    //开户声明
    $("#statementBtn").click(function(){
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
    function replaceImg() {
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
					$("#certificatesType").hide();
					
					$("#img").attr("src","${bootPic}/"+data.data.data.base64Image);
					$('#path_img').val(data.data.data.base64Image);
					
					var cibType = $("input:radio[name='cibType']:checked").val();//（营业执照类型）
					if(cibType == 0){//统一社会信用代码证
						$("#xinyong").fadeIn(500);
					}else{//多证合一
						$("#various").fadeIn(500);
					}
					$("#maskWindow1").addClass("none");
					var ocrInfo = data.data.data.ocrInfo;
					if(typeof(ocrInfo) != "undefined" && ocrInfo != null){
						if(ocrInfo.bizLicenceNameMap != null){//公司名称
							$(".blicCompanyName").val(ocrInfo.bizLicenceNameMap.bizLicenceName);
						}
						if(ocrInfo.bizLicenceAddrMap != null){//住址
							$(".blicAddress").val(ocrInfo.bizLicenceAddrMap.bizLicenceAddr);
						}
						if(ocrInfo.blicScopeMap != null){//经营范围
							$(".blicScope").val(ocrInfo.blicScopeMap.blicScope);
						}
						if(ocrInfo.bizLicenceFoundedDateMap != null){//到期日期
							$(".blicValidityEnd").val();
						}
						
						if(ocrInfo.bizLicenceRegisteredNoMap != null){//注册号（社会统一代码）
							$(".bizLicenceRegisteredNoMap").val(ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo);
							
						}
						var no = ocrInfo.bizLicenceRegisteredNoMap.bizLicenceRegisteredNo
						if(cibType == 0){//统一社会信用代码证
							checkIsAccount(no,null);//校验是否已经开户
						}else{//多证合一
							checkIsAccount(null,no);//校验是否已经开户
						}
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
	* 加载省份
	*/
	var citylist;//根据省份选择城市
	function loadProvice(){
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
						provicehtml += "<option value='"+provice.name+"'>"+provice.name+"</option>";
					}
					citylist = data.citylist;
					$(".provice").html(provicehtml);
				}
			}
		});
	}

	/**
	* 省份改变联动
	*/
	function choose2(obj){
		var cibType = $("input:radio[name='cibType']:checked").val();//（营业执照类型）
		if (obj != null && "" != obj && -1 != $(obj).val()) {
			var cityhtml = "<option value='-1'>请选择城市</option>";
			for(var i=0;i<citylist.length;i++){
				if(citylist[i].pname==$(obj).val()){
					cityhtml += "<option value='"+citylist[i].name+"'>"+citylist[i].name+"</option>";
				}
			}
			
			if(cibType == 0){//统一社会信用代码证
				$("#city1").html(cityhtml);
				$("#city1").show();
			}else{//多证合一
				$("#city2").html(cityhtml);
				$("#city2").show();
			}
		}else{
			$(".city").hide();
		}
	}
	
	/**
	* 营业时间的选择（社会统一）
	*/
	$("input[name='yingyeqixian1']").click(function(){
		var type = $("input:radio[name='yingyeqixian1']:checked").val();//（营业执照类型）
		if(type == 0){
			$("#blicValidityEnd1").hide();
		}else{
			$("#blicValidityEnd1").show();
		}
	});

	/**
	* 营业时间的选择（多证合一）
	*/
	$("input[name='yingyeqixian2']").click(function(){
		var type = $("input:radio[name='yingyeqixian2']:checked").val();//（营业执照类型）
		if(type == 0){
			$("#blicValidityEnd2").hide();
		}else{
			$("#blicValidityEnd2").show();
		}
	});
	
	/**
	* 检查是否使用统一社会信用代码开户
	*/
	function checkBlicUscc(){
		var blicUscc = $("#blicUscc").val();
		var url = '${bootAppPath}/jdjr/query';
		var params = {blicUscc:blicUscc,blicCardNo:""};
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			if (data.response == 'success') {
				if(data.data.total!=null && data.data.total>0){
					$(".binding").show();
					$(".next").hide();
					
					$("#jdjrId").val(data.data.list[0].id);
				}else{
					$(".binding").hide();
					$(".next").show();
				}
			}else{
				alert(data.msg);
			}
		}
	}
	
	/**
	* 检查是否使用注册号开户
	*/
	function checkBlicCardNo(){
		var blicCardNo = $("#blicCardNo").val();
		var url = '${bootAppPath}/jdjr/query';
		var params = {blicUscc:"",blicCardNo:blicCardNo};
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			if (data.response == 'success') {
				if(data.data.total!=null && data.data.total>0){
					$(".binding").show();
					$(".next").hide();
					
					$("#jdjrId").val(data.data.list[0].id);
				}else{
					$(".binding").hide();
					$(".next").show();
				}
			}else{
				alert(data.msg);
			}
		}
	}
	
	/**
	*检查是否已经开过户
	*/
	function checkIsAccount(blicUscc,blicCardNo){
		var url = '${bootAppPath}/jdjr/query';
		var params = {blicUscc:blicUscc,blicCardNo:blicCardNo};
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			if (data.response == 'success') {
				if(data.data.total!=null && data.data.total>0){
					$(".binding").show();
					$(".next").hide();
					
					$("#jdjrId").val(data.data.list[0].id);
				}else{
					$(".binding").hide();
					$(".next").show();
				}
			}else{
				alert(data.msg);
			}
		}
	};
	
	/**
	* 下一步
	*/
	function next(num){
		var cibType = $("input:radio[name='cibType']:checked").val();//（营业执照类型）
		var blicCompanyName = $("#blicCompanyName"+num).val();//公司名称（名称）
		var blicUscc = $("#blicUscc").val();//统一社会信用代码
		var provice = $("#provice"+num).val();//省份
		var city = $("#city"+num).val();//城市
		var blicAddress = $("#blicAddress"+num).val();//详细住址
		var blicScope = $("#blicScope"+num).val();//经营范围
		var yingyeqixian = $("input:radio[name='yingyeqixian"+num+"']:checked").val();//（营业期限）
		var blicValidityEnd = $("#blicValidityEnd"+num).val();//到期日期
		var blicCardNo = $("#blicCardNo").val();//注册号
		var blicObaCardNo = $("#blicObaCardNo").val();//税字号
		var blicTrcCardNo = $("#blicTrcCardNo").val();//组织机构
		var blicUrlA = $("#path_img").val();//图片
		if(blicUrlA==null || blicUrlA == ""){
			alert("图片有问题，请重新识别！");
			return ;
		}
		
		if(!$("#blicCompanyName"+num).validationEngine("validate")){
			$("#blicCompanyName"+num).focus();
			return ;
		}
		
		if(!$("#blicAddress"+num).validationEngine("validate")){
			$("#blicAddress"+num).focus();
			return ;
		}
		
		if(provice == null || provice == -1){
			alert("请选择省份")
			return ;
		}
		
		if(city == null || city == -1){
			alert("请选择城市")
			return ;
		}
		
		if(!$("#blicScope"+num).validationEngine("validate")){
			$("#blicScope"+num).focus();
			return ;
		}
		
		if(cibType == 0){
			if(!$("#blicUscc").validationEngine("validate")){
				$("#blicUscc").focus();
				return ;
			}
			
			localStorage.setItem("blicCardType","USC");
			localStorage.setItem("blicUscc",blicUscc);
		}else{
			if(!$("#blicCardNo").validationEngine("validate")){
				$("#blicCardNo").focus();
				return ;
			}
			if(!$("#blicObaCardNo").validationEngine("validate")){
				$("#blicObaCardNo").focus();
				return ;
			}
			if(!$("#blicTrcCardNo").validationEngine("validate")){
				$("#blicTrcCardNo").focus();
				return ;
			}
			localStorage.setItem("blicCardType","OCI");
			localStorage.setItem("blicCardNo",blicCardNo);
			localStorage.setItem("blicObaCardNo",blicObaCardNo);
			localStorage.setItem("blicTrcCardNo",blicTrcCardNo);
		}
		
		if(yingyeqixian == 0){
			localStorage.setItem("blicLongTerm",true);
			localStorage.setItem("blicValidityEnd","长期");
		}else{
			if(!$("#blicValidityEnd"+num).validationEngine("validate")){
				$("#blicValidityEnd"+num).focus();
				return ;
			}
			localStorage.setItem("blicLongTerm",false);
			blicValidityEnd = blicValidityEnd.replace('/','-').replace('/','-');
			blicValidityEnd = blicValidityEnd.replace('年','-').replace('月','-').replace('日','');
			localStorage.setItem("blicValidityEnd",blicValidityEnd);
		}
		
		localStorage.setItem("blicCompanyName",blicCompanyName);
		localStorage.setItem("blicProvince",provice);
		localStorage.setItem("blicCity",city);
		localStorage.setItem("blicAddress",blicAddress);
		localStorage.setItem("blicScope",blicScope);
		localStorage.setItem("blicUrlA",blicUrlA);//图片 
		
		location.href = "${basePath}/jd/apply/open/person";
	};
	
	/**
	* 绑定
	*/
	function binding(){
		var url = '${bootAppPath}/jdjrbind/save';
		var jdjrId = $("#jdjrId").val();
 		var params = {jdjrId:jdjrId,memberId:memberId};
 		var data = bootPost(url,params);
 		if(data != null){
 			if(data.data.response == 'success'){
 				if(data.data.data!=null){
 					location.href ="${basePath}/jd/bind/examine";
 				}
 			}else{
 				alert(data.data.msg);
 			}
 		}
	};
	
	//日历
    !function(){
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
    }();
    //日期范围限制
    var blicValidityEnd1 = {
        elem: '#blicValidityEnd1',
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
    
    var blicValidityEnd2 = {
        elem: '#blicValidityEnd2',
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
    laydate(blicValidityEnd1);
    laydate(blicValidityEnd2);
</script>
</html>