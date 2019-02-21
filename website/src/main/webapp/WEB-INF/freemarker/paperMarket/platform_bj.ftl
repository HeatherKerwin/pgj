[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='1'/]

<!--商票报价 -->
<div class="flex flex-direction-column mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态类型-->
    <div class="flex-row flex-justify-space-between bbe0e0e0 h27 lh18 mt30">
        <div class="flex-row flex-direction-row">
            <span class="cd43c33 f18 fb orderType"></span>
            <div class="ml30 place none"><img src="https://img.utiexian.com/website/receive/position.png" class="h16 mr3 mt3"><span id="place"></span></div>
        </div>
        <div class="flex-row flex-direction-row">
            <div class="c717583">下单日期:<span class="begintime"></span></div>
        </div>
    </div>

    <!--订单详情-->
    <div class="flex-row flex-direction-column w bae0e0e0 mt10 ordershang pr">
    	<!--票据市场：回头票标志-->
		<div class="ticketReturn pa t70 l_50 none"><img src="${imagePath}/website/paperMarket/returnTicket.png" alt="回头票"></div>
        <div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 tc bbe0e0e0 c717583">
            <div class="w232">流水号</div>
            <div class="w155">票面金额</div>
            <div class="w320">票号</div>
            <div class="w105">背书</div>
            <div class="w216">承兑方</div>
            <div class="w122">承兑行号</div>
        </div>
        <div class="flex-row flex-direction-column Rright">
            <div class="flex-row flex-direction-row tc lh45 pt10 pb10 bbe0e0e0">
                <div class="w232 Rright inquirySerialNo"></div>
                <div class="w155 cd43c33 Rright"><span class="allmoney"></span>万元</div>
                <div class="w320 Rright"><span class="draftNo"></span></div>
                <div class="w105 Rright"><span class="endorseCount"></span>手</div>
                <div class="w216 Rright bankName"></div>
                <div class="w122 bankNo"></div>
            </div>
            <div class="flex-row flex-justify-space-between w pt10 pb10">
                <div class="flex-row flex-direction-column w212 pl20 lh30 Rright">
                    <div>到期日期：<span class="endtime"></span></div>
                </div>
                <div class="flex-row flex-direction-row w599 flex-align-self-baseline">
                    <div class="w60 ml20">备注：</div>
                    <p class="w500 mr20 memberother"></p>
                </div>
            </div>
        </div>
    </div>
	<div class="flex-row flex-direction-row lh24 pt10 pb8 pl10 cd43c33 f12 mt10" style="border: 1px #d43c33 dotted ;">
              跨平台票，为兴业数金平台推送的来自兴业数金或其他平台的票源，由于该票源的票方并未在票据管家注册开户，票据管家无法获取该票方的信誉评分，亦无需支付保证金，因此，报价跨平台票的资方也不收取保证金，同时，资方需自行承担交易风险。
    </div>
    <div class="lh42 bbe0e0e0 w mt10 fb">
        我的报价
    </div>
    <div class="flex-row flex-direction-column w bae0e0e0 mt10">
        <!--订单超时-我的报价-->
        <div class="flex-row flex-direction-column w lh50">
            <div class="flex-row flex-direction-row w tc bcf9f9f9 bbe0e0e0 c717583">
                <div class="flex-col-4">共扣息</div>
                <div class="flex-col-4">每十万贴息</div>
                <div class="flex-col-4">年利率</div>
                <div class="flex-col-4">票款</div>
                <div class="flex-col-4 cp c3366cc appraisalFee societe">兴业数金鉴证服务费</div>
                <div class="flex-col-4 appraisalFee">实付金额</div>
            </div>
            <div class="flex-row flex-direction-row w tc pt10 pb10">
                <div class="flex-col-4 Rright"><span><input type="text" placeholder="请输入金额" class="w90 b0 h30 lh30 bae0e0e0 ti8 money validate[required],validate[custom[onlyNumberSp]]" onblur="paymoney();"></span>元</div>
                <div class="flex-col-4 cd43c33 Rright"><span class="txlx"></span>元</div>
                <div class="flex-col-4 Rright"><span class="rate"></span>%</div>
                <div class="flex-col-4 Rright"><span id="paid"></span>元</div>
                <input type="hidden" value="" id="txje">
                <div class="flex-col-4 cp Rright appraisalFee societe"><span id="appraisalFee"></span>元</div>
                <div class="flex-col-4 appraisalFee"><span id="actualMoney"></span>元</div>
            </div>
        </div>
    </div>

    <div class="flex-row flex-justify-space-between bae0e0e0 pl20 pt20 pb20 pr30 lh30 mt20">
	    <div class="flex-row flex-direction-column w_50 Rright">
            <div class="flex-row signatureFee">
				<div class="w120">电子签章费：</div>
	          	<div class="cd43c33"><span id="signatureFee">5</span>元<span class="c727272 ml15">（代兴业数金收取）</span></div>
			</div>
			<div class="flex-row">
	            <div class="w120">撮合服务费：</div>
	            <div class="cd43c33"><span class="fee noVip none"></span><span style="text-decoration:line-through;" class="fee none isVip"></span>元<span class="c727272 ml15">（票管家收取）</span></div>
	            <div class="cd43c33 isVip none"><img src="https://img.utiexian.com/website/PJGJ/redPackets/yearVpIcon.png" alt=""> 会员免费</div>
	        </div>
	      	<div class="flex-row none" id="needCoupon">
	          <div class="w120">红包：</div>
	          <div class="flex-row flex-direction-row">
	              <span class="cbfbfbf  none noCoupon">无可用红包</span>
	              <div class="flex-row cd43c33 unl flex-alignItems-center none haveCoupon" id="openCoupon"><img src="https://img.utiexian.com/website/discount/redPacketsIcon.png" alt="" width="15" height="16" class="mr5"><span id="couponNum"></span></div>
	              <a class="unl c3366cc ml15 cp" href="${basePath }/bisicmessage/vipMember">（成为会员享优惠）</a>
	          </div>
	      	</div>
	      	<div class="flex-row">
	            <div class="w120">实付：</div>
	            <div class="cd43c33"><span class="actualPay"></span>元</div>
	        </div>
	    </div>
	    <div class="flex-row flex-direction-row">
		    <div class="c717583">票面：<span id="promptStr" class="c717583">无票面</span></div>
		    <div id="picpath" class="flex-row flex-direction-row w480 flex-justify-space-around none"></div>
		</div>
    </div>
    <!-- 提示与操作按钮 -->
    <div class="flex-row flex-justify-space-between w bcf9f9f9 lh50 bae0e0e0 mt20">
        <div class="flex-row flex-direction-row">
            <div class="f18 ml8 mr10 cd43c33">截止日期：<span class="inquiryEnd"></span></div>
        </div>
        <div class="flex-row">
            <div class="mr10">钱包余额：</div>
            <div class="cd43c33 mr20"><span id="money" class="fb mr5 f18"></span>元</div>
            <div class="mr10">实付：</div>
            <div class="cd43c33 mr20"><span class="actualPay" class="fb mr5 f18"></span>元</div>
            <input type="hidden" value="" id="did">
	        <input type="hidden" value="" id="is_designated">
	        <input type="hidden" value="" id="orderId">
	        <input type="hidden" value="" id="type1">
	        <input type="hidden" value="" id="need_todoor">
	        <div class="flex-row flex-direction-row-reverse">
	            <button class="bcd43c33 bad43c33 cwhite w150 f18 cp" id="confirmPrice">确认报价</button>
	        </div>
        </div>
    </div>
</div>

<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="title"></div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn" class="cp">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!-- 兴业数金鉴证服务费 -->
            <div class="flex-row flex-direction-column w lh30 none" id="societeWindow">
                <div class="flex-row pl10 pr10 mt40 flex-direction-column c545563 lh30">
	                <div class="flex-row flex-justify-space-between">
	                	<div>收费方：兴业数字金融股份有限公司</div>
	                	<div>单位：人民币（元）</div>
	                </div>
	                <div class="flex-row flex-direction-column tc bae0e0e0">
		                <div class="flex-row h40 lh40 fb f16 bbe0e0e0">
		                	<div class="flex-col-4 bre0e0e0">项目</div>
		                	<div class="flex-col-4 bre0e0e0">内容</div>
		                	<div class="flex-col-4">费用</div>
		                </div>
		                <div class="flex-row bbe0e0e0">
		                	<div class="flex-col-4 bre0e0e0">鉴证费用</div>
		                	<div class="flex-col-4 bre0e0e0">根据交易金额按比例收取</div>
		                	<div class="flex-col-4">0.6BP(最低6元最高300元封顶)</div>
		                </div>
		             
	                </div>
	                <div class="flex-row flex-justify-end ce84c3d">
	                	<div>1BP=0.01%</div>
	                </div>
	                <div></div>
                </div>
                <!--按钮操作-->
                <div class="flex-row flex-justify-center lh30 mt30">
                    <input type="button" value="知道了" class="w140 h35 ba bad43c33 bcd43c33 cwhite br3 cp closeBtn">
                </div>
            </div>
            <div class="pl20 pr20 none coupon">
	            <!--使用红包-->
		        <!--使用红包-->
		        <div class="flex-row flex-direction-row flex-justify-space-between flex-wrap h300 none" style="overflow: auto" id="couponWindow"></div>
			</div>	
            <!-- 确认报价 -->
            <div class="flex-row flex-direction-column w lh30 none" id="confirmPriceWindow">
                <div class="flex-row flex-justify-center mt40">
                票方将收到的票款为：
                	<span class="cd43c33 paid"></span>
                </div>
                <div class="flex-row flex-justify-center mt10">
               您的实际支付金额为：
                	<span class="c3366cc actualMoney"></span>
                </div>
                <!--按钮操作-->
                <div class="flex-row flex-justify-center lh30 mt30">
                    <input type="button" value="修改报价" class="w150 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn cp">
                    <input type="button" value="确认报价并支付" id="discountSuccess" onclick="confirmPrice();" class="w150 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 cp none">
                	<input type="button" value="余额不足，去充值" id="recharge" class="w150 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 cp none" >
                </div>
            </div>
         </div>
    </div>
</div>
<input type="hidden" id="couponId" name="couponId">
<input type="hidden" id="disc_bind_id" name="disc_bind_id">
[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${jsPath}/number.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="COUPON">
{{#each coupon}}
	<div class="flex-row flex-direction-row w240 h80 bae0e0e0 cp cpH mt25 br3" onclick="selectCoupon({{id}},{{money}});">
		<div class="w90 flex-row h flex-direction-column tc bcfc4d42 rbDashed">
			<div class="cwhite f20 mt3">¥<span class="f30 ml3">{{money}}</span></div>
			<div class="bc f12 bcwhite cfc4d42 br3 lh22 mt6 w70">满<span>20</span>元可用</div>
		</div>
		<div class="flex-row h flex-direction-column pl20">
			<div class="mt16 f14">{{toCouponType couponType}}</div>
			<div class="mt10 f10 c7d7d7d"><span>{{formatDate endDate}}</span>到期</div>
		</div>
	</div>
{{/each}}
</script>
<script type="text/javascript">
	/**
	* 红包类型格式化
	*/
	Handlebars.registerHelper('toCouponType', function(couponType,options) {
	    if(couponType=='GENERAL'){
	        return "交易通用红包";
	    }else if(couponType=='DISC'){
	        return "交易红包";
	    }else if(couponType=='DIST'){
	        return "接单红包";
	    }else if(couponType=='INQUIRYREPLY'){
	        return "查询查复红包";
	    }
	});
	
	/**
	*	时间格式化
	*/
	Handlebars.registerHelper('formatDate', function(num, options) {
	    if(num!=null){
	        num = num.replace(/-/g, "/");
	        var d = new Date(num);
	        return d.format('yyyy-MM-dd');
	    }else{
	        return "--";
	    }
	});

	var id = ${id};
	var bns_memberId = '${bns_memberId}';
	var order_type = '${order_type}';
	var memberId = ${member.id};
	var orgId = ${orgId};
	
    $(function(){
    	loadDate();
    	getBalance();
    	loadVip();
    });
	
	/**
	*展示订单
	*/
	function loadDate(){
		var url = '${bootAppPath}/discountrecordunion/get';
		var params = {id:id};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				console.log(data);
				var data = data.data.data;
				
				$("#disc_bind_id").val(data.disc_bind_id);
				$("#type1").val(data.type);
				$(".inquirySerialNo").html(data.inquirySerialNo);
				$(".allmoney").html(data.amt/10000);
				$(".draftNo").html(data.draftNo);
				$(".endorseCount").html(data.endorseCount);
				$(".bankNo").html(data.bankNo);
				
				if(data.type == 1){
					$(".bankName").html(data.bankName);
				}else if(data.type == 2){
					$(".bankName").html(data.acctName);
				}
				
				data.expiryDate = data.expiryDate.replace(/-/g, "/");
		        var expiryDate = new Date(data.expiryDate);
				$(".endtime").html(expiryDate.format('yyyy-MM-dd'));
				
				data.createTime = data.createTime.replace(/-/g, "/");
		        var createTime = new Date(data.createTime);
				$(".begintime").html(createTime.format('yyyy-MM-dd'));
				
				$(".memberother").html(data.orderDesc);
				$(".inquiryEnd").html(data.inquiryEnd);
				
				$("#orderId").val(data.id);
				if(data.backEndorse == "Y"){
					$(".ticketReturn").removeClass("none");
				}
				
				if(data.orderFiles!=null && data.orderFiles!=""){
					console.log(data.orderFiles);
					var files = JSON.parse(data.orderFiles);
					var html = "";
					for(var i=0;i<files.length;i++){
						html += "<a href="+files[i].url+" target='_blank' class='dsib bae0e0e0 w120'><img src="+files[i].url+" alt='' style='width:100%;' height='170'></a>";
					}
					$("#picpath").append(html);
					$("#picpath").removeClass("none");
					$("#promptStr").addClass("none");
					
				}
			}
		}
	};
	
	/**
	* 计算实付金额
	*/
	function paymoney(){
		if(!$(".money").validationEngine("validate")){
			$(".money").focus();
			return ;
		}
		var money = $(".money").val();
		var allmoney = $(".allmoney").html();
		var type1 = $("#type1").val();
		
		if(parseInt(allmoney*10000)<parseInt(money)){
			alert("扣息不能大于票面金额")
			$(".money").val("");
			$("#paid").html("");
			return ;
		}
		var endDate = $(".endtime").html();
		var startDate = new Date();
		startDate = Date.parse(startDate);
		endDate = Date.parse(endDate);
		var dateSpan = endDate - startDate;
		var tempDate = Math.abs(dateSpan);
		var jxts = Math.floor(tempDate / (24 * 3600 * 1000));
		
		var receivables = (Number(allmoney).mul(10000)-money);
		$("#paid").html(fmoney(receivables,2));
		$(".paid").html(fmoney(receivables,2)+"元");
		$("#txje").val(receivables);
		
		var params = {money:(allmoney*10000),txje:receivables,jxts:jxts};
		var url = '${bootAppPath}/dispatch/fee';
		
		var disc_bind_id = $("#disc_bind_id").val();
		var draft_no = $("#draft_no").html();
		if(disc_bind_id !=null&&disc_bind_id!=""){
			params.draftNo = draft_no;
			params.bindId = disc_bind_id;
		}
		
		var data = bootPost(url,params);
		
		if(data != null){
			if(data.data.response == 'success'){
				var appraisalFee = data.data.data.fee;
				$("#appraisalFee").html(appraisalFee);
				var actualMoney = parseFloat(receivables) + parseFloat(appraisalFee);
				$("#txje").val(actualMoney);
				$("#actualMoney").html(fmoney(actualMoney,2));
				$(".actualMoney").html(fmoney(actualMoney,2)+"元");
				
				$(".rate").html(data.data.data.price);
				$(".txlx").html(data.data.data.price2);
			}else{
				alert(data.data.msg);
			}
		}
		
	};
	
	/**
	* 返回分秒
	*/
	function getTime(num){
		if(num == 0){
			return "--";
		}
		var minute = parseInt(num/60);
		var second = num % 60;
		return minute+"分"+second+"秒";
	};
	
	/**
	* 获取余额
	*/
	function getBalance(){
		var url = '${bootAppPath}/account/info/by/member';
		var params = {memberId:memberId};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				if(data.data.data.account.money != null){
					$("#money").html(data.data.data.account.money);
				}else{
					$("#money").html(0);
				}
			}
		}
	};
	
	/**
	* 机构确认报价
	*/
	function confirmPrice(){
		var txje = $("#txje").val();
		var orderId = $("#orderId").val();

		var url = '${bootAppPath}/distributeorderunion/save';
		var params = {dcrdUnionId:orderId,orgId:orgId,quotedPrice:txje};
		
		var couponId = $("#couponId").val();
		if(couponId != null && couponId != ""){//红包
			params.couponId = couponId;
		}

		var data = bootPost(url,params);
		if(data != null){
			if(data.data.data!=null && data.data.data.error_msg!=null){
				alert(data.data.data.error_msg);
				return;
			}
			if(data.data.data!=null && data.data.data.return_msg!=null){
				alert(data.data.data.return_msg);
				return;
			}
			if(data.data.response == 'success'){
				//跳转到列表
				location.href = "${basePath}/distributeorderunion/list";
			}else{
				alert(data.data.msg);
			}
		}
	};
	
	//关闭按钮
	$("#closeBtn , .closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#codeWindow").addClass("none"); //发送验证码
	    $("#societeWindow").addClass("none")//兴业数金
	    $("#confirmPriceWindow").addClass("none");//确认订单
	    $("#couponWindow").addClass("none");//红包
	    $("#depositExplain").addClass("none");//红包
	    $("#zoomWindow").addClass("none");//放大的图片
	});
	
	/**
	* 确认报价的弹窗
	*/
	$("#confirmPrice").click(function(){
		if(!$(".money").validationEngine("validate")){
			$(".money").focus();
			return ;
		}
		
		var money = $("#money").html();
		var deposit = $(".actualPay").html();

		if(parseInt(money) < parseInt(deposit)){
			$("#recharge").removeClass("none");
			$("#discountSuccess").addClass("none");
		}else{
			$("#recharge").addClass("none");
			$("#discountSuccess").removeClass("none");
		}
		
		$("#title").html("确认报价");
		$("#maskWindow").removeClass("none");
		$("#confirmPriceWindow").removeClass("none");
	});
	
	/**
	* 查看兴业数金的手续费的计算方式
	*/
	$(".societe").click(function(){
		$("#title").html("手续费计算公式");
		$("#maskWindow").removeClass("none");
		$("#societeWindow").removeClass("none");
	});
	
	/**
    * 打开红包弹窗
    */
    $("#openCoupon").click(function(){
    	$(".coupon").removeClass("none");
    	$("#maskWindow").removeClass("none");
    	$("#couponWindow").removeClass("none");
    	$("#title").html("选择红包");
    });
    
    /**
	* 加载用户是否为会员
	*/
	function loadVip(){
    	var url = "${bootAppPath}/vipmember/get/by/memberid";
    	var type1 = $("#type1").val();
    	var params = {memberId:memberId,vipType:0};
    	var data = bootPost(url,params);
    	if(data != null){
    		if(data.data.response == 'success'){
        		$(".fee").html(parseInt(data.data.data.fee));
    			if(data.data.data.vipMember != null){
    				$(".actualPay").html(parseInt($("#signatureFee").html()));
    				$(".isVip").removeClass("none");
    				$("#fee").html(parseInt(data.data.data.fee));
    			}else{
    				$(".noVip").removeClass("none");
    				$("#needCoupon").removeClass("none");
    				$(".actualPay").html(parseInt(data.data.data.fee)+parseInt($("#signatureFee").html()));
    				
    				if(data.data.data.coupon != null){
    					if(data.data.data.coupon.length >0){
    						$(".haveCoupon").removeClass("none");
        	        		$("#couponNum").html(data.data.data.coupon.length+"个可用红包");
        	        		var source = $("#COUPON").html();
        		            var template = Handlebars.compile(source);
        		            var html = template(data.data.data);
        		            $("#couponWindow").html(html);
    					}else{
    						$(".noCoupon").removeClass("none");
    					}
    				}else{
    					$(".noCoupon").removeClass("none");
    				}
    			}
			}else{
				alert(data.data.msg);
			}
    	}
	};
	
	/**
	* 选择要使用的红包
	*/
	function selectCoupon(id,money){
		$("#couponId").val(id);
		$("#couponNum").html("-"+money);
		
		$("#maskWindow").addClass("none");
		$("#couponWindow").addClass("none");//红包
		
		$(".coupon").addClass("none");
		
		var type1 = $("#type1").val();
		if(type1 == 1){
			$(".actualPay").html(parseInt(parseInt($(".fee").html())-parseInt(money)));
		}else{
			$(".actualPay").html(parseInt(parseInt($(".fee").html())+parseInt($("#signatureFee").html())-parseInt(money)));
		}
	};
	
	/**
	* 充值余额
	*/
	$("#recharge").click(function(){
		$(".money").val("");
		location.href = "${basePath}/bisicmessage/deposit";
	});
	
	/**
	* 格式化金额
	*/
	function fmoney(s, n) { 
		n = n > 0 && n <= 20 ? n : 2; 
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
		var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1]; 
		t = ""; 
		for (i = 0; i < l.length; i++) { 
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : ""); 
		} 
		return t.split("").reverse().join("") + "." + r; 
	};
</script>