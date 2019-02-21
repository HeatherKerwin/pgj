[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/cropper.min.css">
<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/ImgCropping.css">
[@main.header currentmenu='1' topindex='2'/]

<!--贴现输入表单-->

<div class="mt16 w1200 bc">
<input type=hidden name="membermobile" value="${member.mobile}">
    <div class="w1200 h52 bcwhite">
        <ul class="f16 c2d2d2d lh50 bae0e0e0 tc TXtab">
            <li id="yptx_d" class="w250 lh50 fl bre0e0e0 bbd43c33 cd43c33">银票电票</li>
            <li id="yptx_z" class="w250 lh50 fl bre0e0e0">银票纸票</li>
            <li id="sptx_d" class="w250 lh50 fl bre0e0e0">商票电票</li>
            <li id="sptx_z" class="w250 lh50 fl bre0e0e0">商票纸票</li>
        </ul>
    </div>
    <div class="mt12 bc bae0e0e0 bcwhite pl20 pr20 pb15 f14 c2d2d2d">
        <div class="w mt20 pb8 pl100 tupian">
            <p>请上传正面的清晰票面图片<span class="cd43c33">（图片上传后可点击修改）</span>，电子汇票请使用电脑截图，不可遮挡右上角票号。上传图片成功后，系统会自动识别您的相关信息。</p>
            <div class="w">
            	<label for="replaceImg" class="mt30 w240 dsib cp">
					<button id="replaceImg" style="display: none">请上传票据正面</button>
					<img id="finalImg" src="${image_url}/website/discount/photo.png" width="240" height="150" alt="请上传票据正面">
					<input type="hidden" name="picpath" id="picpath"  style="display:none;">
				</label>
            </div>
        </div>
        <div class="w h45 xuxian none">
            <div class="fl tl w100 fb lh45"></div>
            <span class="pt10 fl lh27 cd43c33">请认真核对以下票据信息，如信息有误请手动修改.</span>
        </div>
        <div class="w h45 xuxian SXdiv none">
            <div class="fl tl w100 fb lh45">承兑期限：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="half" class="none" name="acceptTime" value="0"><label class="fl tc w60 h27 br3 acceptTime_opt_css" for="half">半年期</label></li>
                <li class="fl"><input type="radio" id="year" class="none" name="acceptTime" value="1" checked><label class="fl tc w60 h27 br3 cwhite bce84c3d acceptTime_opt_css" for="year">一年期</label></li>
            </ul>
        </div>
        <div class="w h45 xuxian none">
            <div class="fl tl w100 fb lh45">票面金额：</div>
            <input id="allmoney" name="allmoney" onchange="jixidate();" maxlength="13" type="text" placeholder="请输入金额" class="fl w129 h27 lh27 bae0e0e0 mt8 ti8 validate[required],validate[custom[number]],validate[custom[money]]">
            <div class="fl f14 lh45 ml10">万元</div>
        </div>
        <div class="w h45 xuxian draftNo none">
            <div class="fl tl w100 fb lh45">票号：</div>
            <input id="draftNo" name="draftNo" maxlength="30" type="text" placeholder="请输入票号" class="fl w320 h27 lh27 bae0e0e0 mt8 ti8 validate[required,custom[orderDraftNo]]">
        </div>
        <div class="w h45 xuxian none">
            <div class="fl tl w100 fb lh45">承兑行：</div>
            <input id="bank" name="bank" type="text" class="fl w320 h27 lh27 bae0e0e0 mt8 ti8 validate[required]">
        </div>
        <div class="w h45 xuxian cnapsCode none">
            <div class="fl tl w100 fb lh45">承兑行号：</div>
            <input id="cnapsCode" name="cnapsCode" maxlength="12" type="text" placeholder="请输入开户行号" class="fl w320 h27 lh27 bae0e0e0 mt8 ti8 validate[required,custom[cnapsCode]]">
        </div>
        <div class="w h45 xuxian none">
            <div class="fl tl w100 fb lh45">承兑行类型：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="guogu" class="none" name="type2" value="1"><label class="fl tc w46 h27 br3 type2_opt_css" for="guogu">国股</label></li>
               	<li class="fl mr20"><input type="radio" id="dashang" class="none" name="type2" value="8"><label class="fl tc w46 h27 br3 type2_opt_css" for="dashang">大商</label></li>
                <li class="fl mr20"><input type="radio" id="xiaoshang" class="none" name="type2" value="2"><label class="fl tc w46 h27 br3 type2_opt_css" for="xiaoshang">小商</label></li>
                <li class="fl mr20"><input type="radio" id="waizi" class="none" name="type2" value="3"><label class="fl tc w46 h27 br3 type2_opt_css" for="waizi">外资</label></li>
                <li class="fl mr20"><input type="radio" id="nongshang" class="none" name="type2" value="4"><label class="fl tc w46 h27 br3 type2_opt_css" for="nongshang">农商</label></li>
                <li class="fl mr20"><input type="radio" id="nonghe" class="none" name="type2" value="5"><label class="fl tc w46 h27 br3 type2_opt_css" for="nonghe">农合</label></li>
                <li class="fl mr20"><input type="radio" id="nongxin" class="none" name="type2" value="6"><label class="fl tc w46 h27 br3 type2_opt_css" for="nongxin">农信</label></li>
                <li class="fl mr20"><input type="radio" id="cunzhen" class="none" name="type2" value="7"><label class="fl tc w46 h27 br3 type2_opt_css" for="cunzhen">村镇</label></li>
            </ul>
        </div>
        <div class="w h45 xuxian none">
            <div class="fl tl w100 fb lh45">交易日期：</div>
            <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 mt8 ti8 inline bcwhite" id="start" />
            <label class="fl w30 h27 rili mt8" for="start"></label>
            <input type="hidden" name="begintime" />
        </div>
        <div class="w h45 xuxian none">
            <div class="fl tl w100 fb lh45">到期日期：</div>
            <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 mt8 ti8 inline bcwhite" id="end" />
            <label class="fl w30 h27 rili mt8" for="end"></label>
            <input type="hidden" name="endtime" />
        </div>
        <div class="w h45 lh45 xuxian none">
            <div class="fl tl w100 fb">计息天数：</div>
            <p id="jxts"  class="fl">365天</p>
            <input type="hidden" name="jxts" value="365" />
        </div>
        <div class="w h45 xuxian lh45 none">
            <div class="fl tl w100 fb">参考利息：</div>
            <p id="salepriceid" class="fl">0元</p>
            <input type="hidden" name="salepriceid" value="0" />
        </div>
        <div class="w h130 xuxian none">
            <div class="fl tl w100 fb lh45">备注（选填）：</div>
            <textarea id="memberother" name="memberother" maxlength="200" style='resize: none;' class="fl ti8 w500 h100 bae0e0e0 bcwhite mt8 f14 pt10" name="" placeholder="备注最多可输入200个字......"></textarea>
        </div>
        <div class="w h45 xuxian none">
            <div class="fl tl w100 fb lh45">回头票：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes4" class="none" name="backEndorse" value="T"><label class="fl tc w46 h27 br3 returnTicket_opt_css" for="yes4">是</label></li>
                <li class="fl"><input type="radio" id="no4" class="none" name="backEndorse" value="F"><label class="fl tc w46 h27 br3 returnTicket_opt_css" for="no4">否</label></li>
            </ul>
            <label class="fl tl fb lh45">指票据背书中出现回头背书现象的票据，如A公司背给B公司后，B公司又背给了A公司。</label>
        </div>
        <div class="w h45 xuxian none">
            <div class="fl tl w100 fb lh45">支持一口价：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes5" class="none" name="buyout" value="T"><label class="fl tc w46 h27 br3 buyout_css" for="yes5">是</label></li>
                <li class="fl"><input type="radio" id="no5" class="none" name="buyout" value="F" checked><label class="fl tc w46 h27 br3 buyout_css cwhite bce84c3d" for="no5">否</label></li>
            </ul>
            <div class="fl lh45 ml20"><span class="cd43c33">*</span>买断价格即为您交易完成后实际收到的票款。</div>
        </div>
        <div class="w pt20 pb20 oh none buyoutDiv">
        	<div class="fl w150 h150 tc bce84c3d cwhite f18 ml100" style="border-radius: 50%;">
        		<div class=" w pt20 lh50">买断价格<span class="f14">(万元)</span></div>
        		<input type="number" id="buyoutPrice" oninput="buyoutPrice();" class="w100 h35 lh35 cwhite ti20 bcn bae0e0e0 br3 validate[custom[number]],validate[required]">
        	</div>
        	<div class="fl ml80 mt65">
        		<img src="${image_url}/website/discount/jiantou.png" width="19" height="14">
        	</div>
        	<div class="fl w250 h150 ml80 f18 lh35 pl20 pr20 fb" style="border: 1px dashed #d43c33;">
        		<div class="mt20">共扣息：<span id="discount"></span></div>
        		<div >每十万：<span id="price2"></span></div>
        		<div >年利率：<span id="price"></span></div>
        	</div>
        </div>
    </div>
    <div class="h50 bcf9f9f9 ble0e0e0 bre0e0e0 bbe0e0e0 lh50 pl20">
        <input type="button" id="button" value="生成订单" class="fr f18 cwhite bcd43c33 w166 lh50 b0" />
    </div>
    <div class="w h40 bcwhite ble0e0e0 bre0e0e0 bbe0e0e0">
    </div>
</div>
  [@main.right /]
<!--提示弹窗-->
<div class="wa pf t400 r90 zi10">
    <!--问题-->
    <div class="w190 pr top z12">
        <div class="w190 h30 f14 lh30 bc778ffd cwhite tc">常见问题</div>
        <p class="wa f12 pt25 pb20 lh30 c2d2d2d bcwhite cp pl10 dsb" id="problem">
            1. 银票贴现步骤？<br>2. 为什么要缴纳保证金？<br>3. 保证金何时退还？
        </p>
        <div class="w190 bcf7f7f6 f12 lh30 pt25 pb20">
            <div class="ml10">如有其他问题，请详见</div>
            <div class="ml10">【帮助中心】-【常见问题】</div>
        </div>
    </div>
    <div class="w500 ha pa t-40 r190 zi13 answer c777777 pt10 pb10 pl10 pr25 none" id="answer">
        <h2 class="f16 fb mt6">1. 银票贴现步骤？</h2>
        <p class="f14 ti20 lh18 mt10"> 用户在可以先在平台利用询价功能获取单一价格后，可以使用这个指导价格去市场进行议价，也可以直接在票据管家平台进行银票贴现，在询价菜单底部有直接生成订单的按钮或者在首页上也有贴现的直接跳转按钮都可以进行相关操作，双方付完保证金后，生成相关订单，票据管家收到订单后经过后台智能运算，会立即派送给最符合条件的一家机构，机构在10分钟内可以微调价格后进行接单，一旦机构接单后会联系持票用户进行线下交易。</p>
        <h2 class="f16 fb mt10">2. 为什么要缴纳保证金？</h2>
        <p class="f14 ti20 lh18 mt10">贴现过程中为了保证交易的顺利进行贴现方和机构方都会付一笔资金放到平台上，交易顺利进行保证金会流转到双方账户，而如果出现虚假报价，虚假票面而影响交易质量保证金将作为赔偿补偿到对方账户上（比如贴现方票据信息造假则补偿给机构方，机构方虚假报价则补偿到贴现方）。</p>
        <h2 class="f16 fb mt10">3. 保证金何时退还？</h2>
        <p class="f14 ti20 lh18 mt10 mb5">交易顺利进行后保证金会重新流转到双方账户。</p>
    </div>
</div>

<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>提示</div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--选择机构-->
                <div class="flex-row flex-direction-column w lh30 ">
				<span id="recognitionContent"></span>
                <div class="flex-row flex-justify-center lh30 mt30">
                    <input type="button" value="使用原图" id="old" class="w150 h35 ba bad43c33 bcd43c33 mr20 cwhite br3">
                    <input type="button" value="重新上传" id="new" class="w150 h35 ba bad43c33 bcd43c33 cwhite br3">
                </div>
                </div>
			</div>
		</div>
	</div>
</div>

<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow1">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 br3 pb20">
			<div class="pl20 pr20">
                <div class="w tc mt10 mb10">
		        	<img alt="" src="${image_url}/website/discount/recognition1.gif">
		        </div>
			</div>
		</div>
	</div>
</div>

<!--图片裁剪框 start-->
<div style="display: none;" class="tailoring-container">
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
<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${jsPath}/discount.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/ImgCropping/cropper.min.js"></script>

<style>
 .addressList:HOVER{
	border: 1px solid #d43c33;
} 
</style>
<script type="text/javascript">
	var memberId = ${member.id};
	$(function(){
		loadData();
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
    $("#replaceImg").on("click",function () {
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
        closeTailor();//关闭裁剪框
        $("#maskWindow1").removeClass("none");
		if(imgUrl == null && imgUrl == ""){
			return ;
		}
			
		setTimeout(function(){
			var index = imgUrl.indexOf(",");
			var base64Image = imgUrl.substr(index+1);
			var url = '${bootAppPath}/upload/image';
			var params = {base64Image:base64Image,ocrGenre:'DRAFT'};
			var data = bootPost(url,params);
			console.log(data);
			if(data != null){
				if(data.data.response == 'success'){
					var ocrInfo = data.data.data.ocrInfo
					if(ocrInfo != null){
						if(ocrInfo.bankNameMap != null){
							$("#bank").val(ocrInfo.bankNameMap.bankName);
						}
						if(ocrInfo.bankNoMap != null){
							$("#cnapsCode").val(ocrInfo.bankNoMap.bankNo);
						}
						if(ocrInfo.endDateMap != null){
							$("#end").val(ocrInfo.endDateMap.endDate);
							$("input[name='endtime']").val(ocrInfo.endDateMap.endDate);
						}
						if(ocrInfo.moneyMap != null){
							$("#allmoney").val(ocrInfo.moneyMap.money/10000);
						}
						if(ocrInfo.noMap != null){
							$("#draftNo").val(ocrInfo.noMap.no);
						}
						if(ocrInfo.type1Map != null){
							$(".type2_opt_css").each(function(){
								$(this).prev().attr("checked",false);
								$(this).removeClass("cwhite bce84c3d");
								if($(this).prev().val() == ocrInfo.type1Map.type2){
									$(this).prev().attr("checked",true);
									$(this).addClass("cwhite bce84c3d");
								}
							});
						}else{
							$("#guogu").next().addClass("cwhite bce84c3d");
							$("#guogu").attr("checked",true);
						}
					}
					$("#maskWindow1").addClass("none");
					$(".xuxian").removeClass("none");
					$("#finalImg").attr("src","${bootPic}"+data.data.data.base64Image);
					$("#picpath").val(data.data.data.base64Image);
					jixidate();
				}
			}
		},100);
	};
	
	//生成订单
	$("#button").click(function(){
		if($("input[type='button']").attr("disabled")){
			return;
		}
		if(!$("#allmoney").validationEngine("validate")){
			$("#allmoney").focus();
			return ;
		};
		
		if(!$("#draftNo").validationEngine("validate")){
			$("#draftNo").focus();
			return ;
		};
	
		if(!$("#cnapsCode").validationEngine("validate")){
			$("#cnapsCode").focus();
			return ;
		};
		
		if(!$("#bank").validationEngine("validate")){
			$("#bank").focus();
			return ;
		};
		
		if($.trim($("#picpath").val()).length == 0 || $("#picpath").val() == null){
			alert("请选择上传的票面");
			return ;
		};
		
		var buyout = $("input:radio[name='buyout']:checked").val();//一口价
 		var allmoney = $("#allmoney").val();
     	var buyoutPrice = $("#buyoutPrice").val();
     	var money = allmoney*10000;
     	var txje = buyoutPrice*10000;
     	var salepriceid = $("input[name='salepriceid']").val();
     	var discount = parseInt(money) - parseInt(txje);
     	if(buyout == "T"){
     		if(salepriceid != null && parseInt(salepriceid)>0){
         		if((parseFloat(salepriceid)-parseFloat(money*0.001))>discount){
         			alert("不能超出参考利息的千分之一");
         			return ;
         		}
         		if((parseFloat(salepriceid)+parseFloat(money*0.001))<discount){
         			alert("不能超出参考利息的千分之一");
         			return ;
         		}
         	}
     	}
		
		var map = new Map();
		var type1 = 2;//$("input:radio[name='type1']:checked").val();
		var type2 ;
		$(".type2_opt_css").each(function(){
    		if($(this).prev().attr("checked") == "checked"){
    			type2 = $(this).prev().val();
    		}
    	})
		var allmoney = $("#allmoney").val();
		var endtime = $("input[name='endtime']").val();
		var begintime = $("input[name='begintime']").val();
		var acceptTime = $("input:radio[name='acceptTime']:checked").val();//期限
		var jxts = $("input[name='jxts']").val();
		var endorse = $("#endorse").val();
		var backEndorse = $("input:radio[name='backEndorse']:checked").val();//是否是回头票
		var memberother = $("#memberother").val();
		var draftNo = $("#draftNo").val();
		var cnapsCode = $("#cnapsCode").val();
		var picpath = $("#picpath").val();
		var bank = $("#bank").val();
		
		if(backEndorse==null||backEndorse==""){
			layer.alert("请选择票据是否是回头票");
			return ;
		}
		
		var reg=/^1\d{29}$/;
	    if(!reg.exec(draftNo)){//校验是否是银票
	    	layer.alert("您的票号不符合银票规则！");
	 		return;
	   	}
		
		var url = '${bootAppPath}/discountrecord/tiexian';
		var params = {type1:type1,type2:type2,begintime:begintime,endtime:endtime,backEndorse:backEndorse,acceptTime:acceptTime,
				picpath:picpath,draftNo:draftNo,cnapsCode:cnapsCode,allmoney:allmoney,memberother:memberother,jxts:jxts,memberid:memberId,bank:bank};
		
		if(buyout == "T"){
			if(!$("#buyoutPrice").validationEngine("validate")){
				$("#buyoutPrice").focus();
				return ;
			};
			var buyoutPrice = $("#buyoutPrice").val();
			params.buyoutPrice = (buyoutPrice*10000).toFixed(2);
			map.put("buyout", buyoutPrice);//一口价
		}
		
		var inventoryId = "${inventoryId}";
		if(inventoryId != null && inventoryId !=""){
			params.inventoryId = inventoryId;
		}
		$("input[type='button']").attr("disabled","disabled");//按钮禁用
		var data = bootPost(url,params);
		if(data.data.response == 'success'){
			map.put("discId", data.data.data.id);//类型
		}else{
			alert(data.data.msg);
		 	return;
		}

		map.put("_PAGE", "/discountrecord/discount_yp2");//必传
		map.put("ym", "yp");
		_OPENPAGE_FORM(map);
		
	});

    //日历
    !function(){
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
    }();
    //日期范围限制
	//贴现日期
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD',
        min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16', //最大日期
        istime: true,//时分秒
        isclear: false,//清空
        istoday: false,//今天
        choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
            jixidate();
        }
    };
	//到期日期
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD',
        min: laydate.now(),
        max: '2099-06-16',
        istime: true,//时分秒
        isclear: false,//清空
        istoday: false,//今天
        choose: function(datas){
            start.max = datas; //结束日选好后，充值开始日的最大日期
            jixidate();
        }
    };
    laydate(start);
    laydate(end);
    
    //计息天数
    function jixidate(){
    	var type1 = 2;
    	var type2 ;
    	$(".type2_opt_css").each(function(){
    		if($(this).prev().attr("checked") == "checked"){
    			type2 = $(this).prev().val();
    		}
    	})
    	var start = $("#start").val();
    	var end = $("#end").val();
    	
    	$("input[name='begintime']").val(start);
    	$("input[name='endtime']").val(end);

    	var allmoney = $("#allmoney").val();
    	
    	if($.trim(allmoney).length == 0){
    		alert("金额不能为空");
    		return ;
    	}
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/excel/price",
	     	data: {type1:type1,type2:type2,startDate:start,endDate:end,allmoneys:allmoney},
	     	dataType:"json",
	     	success:function(retValue){
	     		var data = retValue.jxts;
	     		var salepriceid=0;
	     		if(parseInt(allmoney) >= 500){
	     			salepriceid = retValue.rate;
	     		}else{
	     			salepriceid = retValue.rate2;
	     		}
	     		var lx = retValue.txlx;
	     		if(data == 'undefined' || typeof(data) == 'undefined'){
	     			$("#jxts").html(0+"天");
		     		$("input[name='jxts']").val(0);
	     		}else{
	     			$("#jxts").html(data+"天");
		     		$("input[name='jxts']").val(data);
	     		}
	     		if(lx != null && lx != ""){
	     			$("#salepriceid").html(lx);
		     		$("input[name='salepriceid']").val(lx);
	     		}
	    	}
		 })	
    };

	//回头票
    $(".returnTicket_opt_css").click(function () {
    	 if ($(this).prev().val() == "T") {
             $("#yes4").parents("li").children("label").addClass("cwhite bce84c3d");
             $("#no4").parents("li").children("label").removeClass("cwhite bce84c3d");
             $("#yes4").attr("checked",true);
             $("#no4").attr("checked",false);
		}else{
			$("#no4").parents("li").children("label").addClass("cwhite bce84c3d");
           	$("#yes4").parents("li").children("label").removeClass("cwhite bce84c3d");
           	$("#no4").attr("checked",true);
           	$("#yes4").attr("checked",false);
		}
	});
	
  //一口价
    $(".buyout_css").click(function () {
    	 if ($(this).prev().val() == "T") {
             $("#yes5").parents("li").children("label").addClass("cwhite bce84c3d");
             $("#no5").parents("li").children("label").removeClass("cwhite bce84c3d");
             $("#yes5").attr("checked",true);
             $("#no5").attr("checked",false);
             $(".buyoutDiv").removeClass("none");
		}else{
			$("#no5").parents("li").children("label").addClass("cwhite bce84c3d");
           	$("#yes5").parents("li").children("label").removeClass("cwhite bce84c3d");
           	$("#no5").attr("checked",true);
           	$("#yes5").attr("checked",false);
           	$(".buyoutDiv").addClass("none");
		}
	});
	
    $(".type2_opt_css").click(function(){
    	var type2 = $(this).prev().val();
		$(".type2_opt_css").each(function(){
			$(this).prev().attr("checked",false);
			$(this).removeClass("cwhite bce84c3d");
			if($(this).prev().val() == type2){
				$(this).prev().attr("checked",true);
				$(this).addClass("cwhite bce84c3d");
			}
		});
	});
	
	//期限
    $(".acceptTime_opt_css").click(function () {
		if ($(this).prev().val() == "0") {
             $("#half").parents("li").children("label").addClass("cwhite bce84c3d");
             $("#year").parents("li").children("label").removeClass("cwhite bce84c3d");
             $("#half").attr("checked",true);
             $("#year").attr("checked",false);
		}
		else{
           $("#year").parents("li").children("label").addClass("cwhite bce84c3d");
           $("#half").parents("li").children("label").removeClass("cwhite bce84c3d");
           $("#year").attr("checked",true);
           $("#half").attr("checked",false);
		}
    });  

    // radio鼠标触发事件
    $(".TXradio").mouseover(function(){
        $(".TXradio").addClass("bcd43c33");
    });
    $("p").mouseout(function(){
        $(".TXradio").removeClass("bcd43c33");
    });
    
    /**
     * 选择页面跳转
     */
    $("#yptx_d").click(function(){
 		checkAccount(0,'/discountrecord/discount_yp1');
 	});
 	$("#sptx_d").click(function(){
 		checkAccount(0,'/discountrecord/discount_sp1');
 	});
 	$("#yptx_z").click(function(){
 		checkAccount(0,'/discountrecord/discount_yp_zhi');
 	});
 	$("#sptx_z").click(function(){
 		checkAccount(0,'/discountrecord/discount_sp_zhi');
 	});
    
    //加载数据
	function loadData(){
    	var type1 = '${shuxing}';
    	var acceptTime = '${acceptTime}';
    	var type2 = '${type1}';
    	var memberother = '${accountdesc}';
    	var allmoney = '${allmoney}';
    	var days = '${days}';
    	$("#allmoney").val(allmoney);
    	
    	if(days != null && days != ""){
    		$("#jxts").html(days+"天");
        	$("input[name='jxts']").val(days);
    	}else{
    		$("#jxts").html(183+"天");
        	$("input[name='jxts']").val(183);
    	}
    	
    	var begintime = '${begintime}';
    	var endtime = '${endtime}';
    	
    	if($.trim(begintime).length >0){
    		$("#start").val(begintime);
        	$("#begintime").val(begintime);
    	}
    	if($.trim(endtime).length >0){
    		$("#end").val(endtime);
        	$("#endtime").val(endtime);
    	}
    	
    	$("#memberother").val(memberother);
		if(acceptTime=="0"){
			$("#half").attr("checked","checked");
			$("#year").removeAttr("checked");
			$("#half").next().addClass("cwhite bce84c3d");
			$("#year").next().removeClass("cwhite bce84c3d");
		}else if(acceptTime=="1"){
			$("#half").removeAttr("checked");
			$("#year").attr("checked","checked");
			$("#half").next().removeClass("cwhite bce84c3d");
			$("#year").next().addClass("cwhite bce84c3d");
		}
	    var now = new Date();
		var fullyear = now.getFullYear();
	   	//获取完整的年份(4位,1970-????)
		var month = now.getMonth() + 1;
		//获取当前月份(0-11,0代表1月)
		var date = now.getDate();
		//获取当前日(1-31)
		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}	
		$("#start").val(fullyear+"-"+month+"-"+date);
		$("input[name='begintime']").val(fullyear+"-"+month+"-"+date);
		
		var begintimelong = Date.parse(new Date());
		var endtime12 = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
	    var endfullyear = endtime12.getFullYear();
	    //获取完整的年份(4位,1970-????)
	    var endmonth = endtime12.getMonth() + 1;
	    //获取当前月份(0-11,0代表1月)
	    var enddate = endtime12.getDate();
		//获取当前日(1-31)
		if (endmonth < 10) {
			endmonth = "0" + endmonth;
		}
		if (enddate < 10) {
			enddate = "0" + enddate;
		}
		$("#end").val(endfullyear+"-"+endmonth+"-"+enddate);
		$("input[name='endtime']").val(endfullyear+"-"+endmonth+"-"+enddate);
	}	
    //    问题答案
    $("div #problem").mouseover(function(){
        $("#answer").fadeIn("slow");
    });
    $("div #problem").mouseout(function(){
        $("#answer").fadeOut("slow");
    });
    
    /**
    * 一口价
    */ 
    function buyoutPrice(){
    	if(!$("#buyoutPrice").validationEngine("validate")){
			$("#buyoutPrice").focus();
			return ;
		};
		var buyout = $("input:radio[name='buyout']:checked").val();//一口价
		var allmoney = $("#allmoney").val();
    	var buyoutPrice = $("#buyoutPrice").val();
    	var jxts = $("input[name='jxts']").val();
    	var money = allmoney*10000;
    	var txje = buyoutPrice*10000;
    	var discount = parseInt(money) - parseInt(txje);
    	
    	var params = {money:money,txje:txje,jxts:jxts};
		var url = '${bootAppPath}/dispatch/fee';
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				$("#discount").html(discount+"元");
				$("#price2").html(data.data.data.price2+"元");
				$("#price").html(data.data.data.price+"%");
			}else{
				alert(data.data.msg);
			}
		}
    };
</script>
[@main.footer/]
[/@main.body]