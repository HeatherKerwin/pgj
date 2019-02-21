[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/paperMarket/paperMarket.css"/>
<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/cropper.min.css">
<link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/ImgCropping.css">
[@main.header currentmenu='1'/]
<style>
    *{box-sizing: border-box}
    .element::-webkit-scrollbar {display:none}
</style>
<div class="w1200 bc">
	<!--菜单-->
	<div class="w h52 bcwhite mt20">
	    <ul class="fl f16 c2d2d2d lh50 tc TXtab oh">
	        <li class="w250 lh50 fl bre0e0e0"><a href="${basePath}/paperMarket" class="c2d2d2d dsb">票据市场</a></li>
	        <li class="w250 lh50 fl bre0e0e0 bbd43c33"><a href="javascript:void(0)" class="cd43c33 dsb">票据库存清单</a></li>
	    </ul>
	    <div id="uploadBtn" class="fr cd43c33 f14 w125 br3 bad43c33 tc lh30 h30 mt10 mr20 cp">库存票清单上传</div>
	</div>
	
	<!--条件搜索区域-->
	<div class="bcwhite bte0e0e0 pl20 pr20 pt20">
	    <!--已筛选条件-->
	    <div class="selected" id="condition"><label for="">筛选条件</label>
	        
	    </div>
	    <!--选择搜索条件-->
	    <ul class="selectDiv">
	        <!--票面金额-->
	        <li class="selectRow">
	            <span class="title">票面金额</span>
	            <!--选择-->
	            <ul class="RadioDiv">
	                <li>
	                    <label for="money1"><input type="radio" name="selectMoney" id="money1" class="selectCheckbox selectMoney" value="0">300万以下</label>
	                </li>
	                <li>
	                    <label for="money2"><input type="radio" name="selectMoney" id="money2" class="selectCheckbox selectMoney" value="1">300万以上</label>
	                </li>
	            </ul>
	            <!--自定义-->
	            <div class="custom">
	                <div class="numberDiv"><input type="number" name="" id="selectMinMoney">万</div>
	                ~
	                <div class="numberDiv"><input type="number" name="" id="selectMaxMoney">万</div>
	                <button id="selectMoney">确定</button>
	            </div>
	        </li>
	    </ul>
	</div>
	
	<!--清单列表-->
	<div class="mt20 w1200 bte0e0e0">
	    <ul class="w bbe0e0e0 ble0e0e0 bre0e0e0 bcf9f9f9 c7a7a7a tc h40 lh40 f14 oh">
	        <li class="fl bre0e0e0 w100">票据金额(万元)</li>
	        <li class="fl bre0e0e0 w250">承兑行/承兑企业</li>
	        <li class="fl bre0e0e0 w150">出票日</li>
	        <li class="fl bre0e0e0 w150">到期日</li>
	        <li class="fl bre0e0e0 w250">备注</li>
	        <li class="fl bre0e0e0 w100">带价</li>
	        <li class="fl bre0e0e0 w100">热度</li>
	        <li class="fl w97">操作</li>
	    </ul>
	    <!--列表展示-->
	    <div id="content"></div>
	    <div class="flex-row flex-direction-row-reverse">
	    	<div id="pager"></div>
	    </div>
	</div>
</div>

<!--弹窗-->
<div class="w h pf bc05 zi10 top " id="maskWindow" style="display: none">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="title">提示</div>
                <!--关闭按钮-->
                <label class="cp" for="closeIcon" id="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--库存票清单上传-->
                <div class="flex-row flex-direction-column w tc lh30" id="chuanWindow" style="display: none">
                    <p class="mt30 f20">请上传Excel表格（请先下载表格模板）或者清单截图</p>
                    <!--上传的图片或者表格-->
                    <div class="flex-row flex-justify-center mt10">
                        <!--显示截图缩略图-->
                        <img src="https://img.utiexian.com/website/stock/excelIcon.png" width="72" height="72" alt="截图缩略图">
                        <!--显示Excel图标-->
                        <img src="https://img.utiexian.com/website/stock/excelIcon.png" width="72" height="72" alt="Excel图标">
                    </div>

                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt40 mb20">
                        <input type="button" value="上传截图" id="replaceImg" class="w120 h35 ba bae0e0e0 bcf9f9f9 c2d2d2d br3 mr20 cp">
                        <input type="file" value="上传表格" id="" class="w120 h35 ba bae0e0e0 bcf9f9f9 c2d2d2d br3 mr20 cp">
                        <input type="button" value="确认上传" id="isChuanBtn" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 cp ">
                        <a href="" class="c00a5f2 unl cp">下载表格模板</a>
                    </div>
                </div>

                <!--确认库存票清单-->
                <div class="flex-row flex-direction-column w tc lh30" id="isChuanWindow" style="display: none">
                    <!--清单表格-->
                    <div class="w bte0e0e0 ble0e0e0 bre0e0e0 mt20">
                        <ul class="flex-row flex-justify-center f12 bbe0e0e0">
                            <li class="w80 bre0e0e0">票据金额</li>
                            <li class="w100 bre0e0e0">承兑行/企业</li>
                            <li class="w80 bre0e0e0">出票日</li>
                            <li class="w80 bre0e0e0">到期日</li>
                            <li class="w90 bre0e0e0">备注</li>
                            <li class="w60 bre0e0e0">带价</li>
                            <li class="w54">操作</li>
                        </ul>
                        <!--列表展示-->
					    <div id="content1"></div>
                    </div>
                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt40 mb20">
                        <input type="button" value="确认" id="confirm"  class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 cp">
                    </div>
                </div>

                <!--拍票-->
                <div class="flex-row flex-direction-column w tc lh30" id="paiWindow" style="display: none">
                    <p class="mt30 f20">点击拍票后表示您对该票感兴趣，票方收到拍票信息后，会尽快将对应的票据上传并生成订单，请留意交易大厅的订单。</p>
                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt40 mb20">
                        <input type="button" value="确认拍票" id="confirmPai" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 cp">
                    </div>
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
<!--弹窗遮罩层-->
<div class="w h pf bc05 zi10 top none" id="maskWindow1">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 br3 pb20">
			<div class="pl20 pr20">
                <div class="w tc mt10 mb10">
		        	<img alt="" src="${image_url}/website/stock/uploading.gif">
		        </div>
			</div>
		</div>
	</div>
</div>

<input type="hidden" id ="inventoryId"/>
<input type="hidden" name="minMoney" id="minMoney"/>
<input type="hidden" name="maxMoney" id="maxMoney"/>
<!--图片裁剪框 end-->
<script type="text/javascript" src="https://static.utiexian.com/js/ImgCropping/cropper.min.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="INVENTORYORDER">
{{#each list}}
	<ul class="w bcwhite tc f14 lh40 ble0e0e0 bre0e0e0">
		<li class="bbe0e0e0 h40 oh">
			<div class="fl bre0e0e0 w100">{{toAllmoney allmoney}}</div>
			<div class="fl bre0e0e0 w250 oh wsn toe" title="{{bank}}">{{toBank bank}}</div>
			<div class="fl bre0e0e0 w150">{{toPrinttime printtime}}</div>
			<div class="fl bre0e0e0 w150">{{toEndtime endtime}}</div>
			<div class="fl bre0e0e0 w250 oh wsn toe" title="{{remarks}}">{{toRemarks remarks}}</div>
			<div class="fl bre0e0e0 w100">{{toPrice price}}</div>
			<div class="fl bre0e0e0 w100 cd43c33">{{toAmount amount}}</div>
			<div class="fl w97">
				<div class="w50 h24 lh24 ml25 mt6 bcff9d17 cwhite br3 cp paiBtn {{toIntent intent 1}}" onclick="paiBtn({{id}},{{member_id}})">拍</div>
				<div class="c7a7a7a {{toIntent intent 2}}">已拍</div>
				<div class="c049e5b {{toIntent intent 3}}">已生成订单</div>
			</div>
		</li>
	</ul>
{{/each}}
</script>
<script type="text/x-handlebars-template" id="SAVEINVENTORY">
{{#each data}}
	<ul class="w oya oxh element" style="max-height: 200px">
		<li class="w bbe0e0e0 h30 oh tc c727272">
			<input type="text" name="paiForm{{id}}" id="allmoney{{id}}" class="w80 bre0e0e0 b0 f9 lh30 fl tc" value="{{toAllmoney allmoney}}" readonly="readonly">
			<input type="text" name="paiForm{{id}}" id="bank{{id}}" class="w100 bre0e0e0 b0 f9 lh30 fl tc oh wsn toe" value="{{toBank bank}}" title="鼠标悬浮展示文字内容" readonly="readonly">
			<input type="text" name="paiForm{{id}}" id="printtime{{id}}" class="w80 bre0e0e0 b0 f9 lh30 fl tc" value="{{toPrinttime printtime}}" readonly="readonly">
			<input type="text" name="paiForm{{id}}" id="endtime{{id}}" class="w80 bre0e0e0 b0 f9 lh30 fl tc" value="{{toEndtime endtime}}" readonly="readonly">
			<input type="text" name="paiForm{{id}}" id="remarks{{id}}" class="w90 bre0e0e0 b0 f9 lh30 fl tc oh wsn toe" value="{{toRemarks remarks}}" title="鼠标悬浮展示文字内容" readonly="readonly">
			<input type="text" name="paiForm{{id}}" id="price{{id}}" class="w60 bre0e0e0 b0 f9 lh30 fl tc" value="{{toPrice price}}" readonly="readonly">
			<div class="w54 f10 fl">
				<div class="w35 h20 lh20 mt6 ml10 br3 bc778ffd cwhite cp" id="changeBtn{{id}}" onclick="changeForm({{id}});">修改</div>
				<div class="w35 h20 lh20 mt6 ml10 br3 bc778ffd cwhite cp none" id="saveBtn{{id}}" onclick="saveForm({{id}});">保存</div>
	        </div>
		</li>
	</ul>
{{/each}}
</script>
<script>
/**
* 票面金额的展示
*/
Handlebars.registerHelper('toAllmoney', function(allmoney,options) {
	if(allmoney != "" && allmoney != null){
		return allmoney;
	}else{
		return "--";
	}
});

/**
* 承兑行的展示
*/
Handlebars.registerHelper('toBank', function(bank,options) {
	if(bank != "" && bank != null){
		return bank;
	}else{
		return "--";
	}
});

/**
* 出票日期的展示
*/
Handlebars.registerHelper('toPrinttime', function(printtime,options) {
	if(printtime != "" && printtime != null){
		return printtime;
	}else{
		return "--";
	}
});

/**
* 到期日期的展示
*/
Handlebars.registerHelper('toEndtime', function(endtime,options) {
	if(endtime != "" && endtime != null){
		return endtime;
	}else{
		return "--";
	}
});

/**
* 备注的展示
*/
Handlebars.registerHelper('toRemarks', function(remarks,options) {
	if(remarks != "" && remarks != null){
		return remarks;
	}else{
		return "--";
	}
});

/**
* 价格的展示
*/
Handlebars.registerHelper('toPrice', function(price,options) {
	if(price != "" && price != null){
		return price;
	}else{
		return "--";
	}
});

/**
* 热度的展示
*/
Handlebars.registerHelper('toAmount', function(amount,options) {
	if(amount > 0){
		return amount +"人已拍";
	}else{
		return "待拍";
	}
});

/**
* 是否已拍
*/
Handlebars.registerHelper('toIntent', function(intent, num,options) {
	if(intent != null && intent == "LIKE"){
		if(num != 2){
			return "none";
		}
	}else{
		if(num != 1){
			return "none";
		}
	}
});

	var memberId = '${member.id}';
	$(document).ready(function(){
		search();
	});
	
	/**
	* 加载数据
	*/
	function search(){
		$("#pager").html("");
		var minMoney = $("#minMoney").val();
		var maxMoney = $("#maxMoney").val();
		var data = {minMoney:minMoney,maxMoney:maxMoney};
		if(memberId != null && memberId != ""){
			data.currentMId=memberId;
		}
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	    	url: "${bootAppPath}/inventory/hall",
	        pageIndex:pageIndex,
	        pageSize:10,
	        myTotalType:"data.data.data.total",
	        searchParam: data,
	        beforeSend: function () {
	        },success: function (data) {
	        	console.log(data);
	       		var source = $("#INVENTORYORDER").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data.data);
	            $("#content").html(html);
	            
	        },complete: function(){
			},error:function(data){
	        	alert("出现异常");
	        }
	    });
	};
	
	/**
	* 用户进行拍单
	*/
	function paiBtn(id,member_id){
		if(memberId == null || memberId == ""){
			alert("请先登录账号");
			return ;
		}
		if(memberId == member_id){
			alert("自己的订单，不能拍单！");
			return ;
		}
		
		$("#title").html("拍票");
        $("#maskWindow").show(500);
        $("#paiWindow").show(50);
        
        $("#inventoryId").val(id);
	};
	
	/**
	* 库存清单长传确认
	*/
	$("#confirm").click(function(){
		search();
		$("#title").html("");
        $("#maskWindow").hide();
        $("#chuanWindow").hide();
        $("#isChuanWindow").hide();
        $("#paiWindow").hide();
	});
	
	/**
	* 确认拍票
	*/
	$("#confirmPai").click(function(){
		var inventoryId = $("#inventoryId").val();
		
		var url = '${bootAppPath}/inventoryintent/save';
		var params = {memberId:memberId,inventoryId:inventoryId};
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			console.log(data);
			if (data.response == 'success') {
				alert("拍单成功");
				$("#title").html("");
		        $("#maskWindow").hide();
		        $("#chuanWindow").hide();
		        $("#isChuanWindow").hide();
		        $("#paiWindow").hide();
		        search();
			}else{
				alert(data.msg);
			}
		}
	});

	//初始化日期
	var begintimelong = Date.parse(new Date());
	var begintime = new Date(begintimelong);
	var fullyear = begintime.getFullYear();
	//获取完整的年份(4位,1970-????)
	var month = begintime.getMonth() + 1;
	//获取当前月份(0-11,0代表1月)
	var date = begintime.getDate();
	//获取当前日(1-31)
	
	var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
	var endfullyear = endtime.getFullYear();
	//获取完整的年份(4位,1970-????)
	var endmonth = endtime.getMonth() + 1;
	//获取当前月份(0-11,0代表1月)
	var enddate = endtime.getDate();
	//获取当前日(1-31)
	
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
	//日历
	!function() {
		laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
	}();
	//日期范围限制
	//    贴现日期
	var start = {
		elem : '#startDate',
		format : 'YYYY-MM-DD',
		//min: laydate.now(), //设定最小日期为当前日期
		min : '1900-01-01', //设定最小日期
		max : '2099-06-16', //最大日期
		istime : true,
		istoday : false,
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
		}
	};
	//    到期日期
	var end = {
		elem : '#endDate',
		format : 'YYYY-MM-DD',
		min : laydate.now(),
		max : '2099-06-16',
		istime : true,
		istoday : false,
		choose : function(datas) {
			start.max = datas; //结束日选好后，充值开始日的最大日期
		}
	};
	laydate(start);
	laydate(end);

    /*关闭所有弹窗*/
    $("#closeBtn , .closeBtn").click(function () {
        $("#title").html("");
        $("#maskWindow").hide();
        $("#chuanWindow").hide();
        $("#isChuanWindow").hide();
        $("#paiWindow").hide();
    });
    
    /*库存票清单上传*/
    $("#uploadBtn").click(function () {
    	if(memberId == null || memberId == ""){
    		alert("请先登录账号");
    		return ;
    	}
       // $("#title").html("上传清单");
       // $("#maskWindow").show(500);
       // $("#chuanWindow").show(500);
		$(".tailoring-container").toggle();
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
			var url = '${bootAppPath}/inventory/save';
			var params = {base64Image:base64Image,memberId:memberId};
			var data = bootPost(url,params);
			console.log(data);
			if(data != null){
				if(data.data.response == 'success'){
					var source = $("#SAVEINVENTORY").html();
		            var template = Handlebars.compile(source);
		            var html = template(data.data);
		            $("#content1").html(html);
		            
		            $("#maskWindow1").addClass("none");
		            
		            $("#title").html("确认清单(鼠标悬浮可查看超出文本)");
		            $("#maskWindow").show(500);
		            $("#chuanWindow").hide();
		            $("#isChuanWindow").show(500);
				}else{
					$("#maskWindow1").addClass("none");
					alert(data.data.msg);
				}
			}
		},100);
	};
	
    /*确认库存票清单*/
    $("#isChuanBtn").click(function () {
        $("#title").html("确认清单(鼠标悬浮可查看超出文本)");
        $("#maskWindow").show(500);
        $("#chuanWindow").hide();
        $("#isChuanWindow").show(500);
    });
    
    /*修改拍票*/
    function changeForm(id) {
        $('input[name=paiForm'+id+']').removeAttr("readonly");//去除input元素的readonly属性
        $('input[name=paiForm'+id+']').css('color','#808080');
        $('#changeBtn'+id).addClass('none');
        $('#saveBtn'+id).removeClass('none');
    };
    
    /*保存修改*/
    function saveForm(id) {
        $('input[name=paiForm'+id+']').attr("readonly","readonly")//将input元素设置为readonly
        $('input[name=paiForm'+id+']').css('color','#2d2d2d');
        $('#saveBtn'+id).addClass('none');
        $('#changeBtn'+id).removeClass('none');
        
		var allmoney = $("#allmoney"+id).val();	
		var bank = $("#bank"+id).val();	
		var printtime = $("#printtime"+id).val();	
		var endtime = $("#endtime"+id).val();	
		var remarks = $("#remarks"+id).val();	
		var price = $("#price"+id).val();
		
		var url = '${bootAppPath}/inventory/update';
		var params = {memberId:memberId,id:id,allmoney:allmoney,bank:bank,printtime:printtime,endtime:endtime,remarks:remarks,price:price};
		
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			if (data.response == 'success') {
				alert("修改成功");
			}else{
				alert(data.msg);
			}
		}
    };
    
    /**
     *  选择票面金额
     */
    $(".selectMoney").click(function(){
    	$("#minMoney").val("");
    	$("#maxMoney").val("");
    	$(".conditionMoney").parent("a").remove();
    	var selectMoney = $("input:radio[name='selectMoney']:checked").val();
    	var html = '<a href="javascript:void()" class="">票面金额:';
    	if(selectMoney == 0){
    		html += "300万以下";
    		$("#maxMoney").val(300);
    	}else if(selectMoney == 1){
    		html += "300万以上";
    		$("#minMoney").val(300);
    	}
    	html += '<span class="closeIcon conditionMoney" value="0"></span></a>';
    	$("#condition").append(html);
    	$(".conditionMoney").on("click",conditionMoney);
    	search();
    });

    /**
     * 自定义金额
     */
    $("#selectMoney").click(function(){
    	var selectMinMoney = $("#selectMinMoney").val();
    	var selectMaxMoney = $("#selectMaxMoney").val();
    	if(parseFloat(selectMaxMoney)<parseFloat(selectMinMoney)){
    		alert("请填入合适的金额");
    		return ;
    	}
    	if(selectMaxMoney.length == 0 && selectMinMoney.length == 0){
    		alert("请填入合适的金额");
    		return ;
    	}
    	$(".selectMoney").attr("checked",false);
    	$(".conditionMoney").parent("a").remove();
    	$("#minMoney").val(selectMinMoney);
    	$("#maxMoney").val(selectMaxMoney);
    	var html = '<a href="javascript:void()" class="">票面金额:'+selectMinMoney+"~"+selectMaxMoney;
    	html += '万<span class="closeIcon conditionMoney" value="0"></span></a>';
    	$("#condition").append(html);
    	$(".conditionMoney").on("click",conditionMoney);
    	search();
    });

    /**
     * 清除票面金额
     */
    function conditionMoney(){
    	$(this).parent("a").remove();
    	$(".selectMoney").attr("checked",false);
    	$("#selectMinMoney").val("");
    	$("#selectMaxMoney").val("");
    	$("#minMoney").val("");
    	$("#maxMoney").val("");
    	search();
    }
</script>
[@main.footer/]
[/@main.body]