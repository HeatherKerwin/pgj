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
                <div class="flex-col-4">年利率</div>
                <div class="flex-col-4">每十万贴息</div>
                <div class="flex-col-4 appraisalFee">实付金额</div>
            </div>
            <div class="flex-row flex-direction-row w tc pt10 pb10">
                <div class="flex-col-4 Rright"><span id="txje"></span>元</div>
                <div class="flex-col-4 Rright"><span id="rate"></span>%</div>
                <div class="flex-col-4 cd43c33 Rright"><span class="price2"></span>元</div>
                <div class="flex-col-4 appraisalFee"><span id="actualMoney"></span>元</div>
            </div>
        </div>
    </div>

    <!-- 提示与操作按钮 -->
    <div class="flex-row flex-justify-space-between w bcf9f9f9 lh50 bae0e0e0 mt20">
        <div class="flex-row flex-direction-row">
            <div class="f18 ml8 mr10 cd43c33">截止日期：<span class="inquiryEnd"></span></div>
        </div>
        <div class="flex-row">
	        <div class="flex-row flex-direction-row-reverse">
				<button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="SeePayBtn">支付票款</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="endorseBtn">完成交易</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="cancelBtn">取消订单</button>
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
                <label for="closeIcon" id="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--取消订单-->
                <div class="flex-row flex-direction-column w lh30 none" id="cancelWindow">
                    <div class="flex-row flex-justify-space-between h34 mt30">
                        <div class="fb tr">请选择取消理由：</div>
                        <select class="w400 bae0e0e0 br3 ti10 select400 h35" id="reason">
                        	<option value="1">资金不足</option>
                            <option value="2">票面信息输入有误</option>
                            <option value="3">不在业务范围内</option>
                            <option value="4">已提前出票</option>
                            <option value="5">其它</option>
                        </select>
                    </div>
                    <textarea class="h120 bae0e0e0 br3 pt10 pr10 pl10 mt20 none validate[required]" id="reasonOther" maxlength="100" placeholder="最多100字的理由。"></textarea>
                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt40 mb20">
                        <input type="button" value="确定取消订单" id="confirm_reason" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 mr20">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<input type="hidden" value="" id="distId"/>
[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>

<script type="text/javascript">
	var id = ${id};
	var memberId = ${member.id};
	var orgId = ${orgId};
	var cib_login_url_org;//兴业登录的路径机构端
	
    $(function(){
    	loadDate();
    	seePayBtn();
    });
	
	/**
	*展示订单
	*/
	function loadDate(){
		var url = '${bootAppPath}/distributeorderunion/get';
		var params = {id:id};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				console.log(data);
				var data = data.data.data;
				
				$("#type1").val(data.type);
				$(".inquirySerialNo").html(data.quote_serial_no);
				$(".allmoney").html(data.amt/10000);
				$(".draftNo").html(data.draft_no);
				$(".endorseCount").html(data.endorse_count);
				$(".bankNo").html(data.bank_no);
				
				if(data.type == 1){
					$(".bankName").html(data.bank_name);
				}else if(data.type == 2){
					$(".bankName").html(data.acct_name);
				}
				
				data.expiry_date = data.expiry_date.replace(/-/g, "/");
		        var expiryDate = new Date(data.expiry_date);
				$(".endtime").html(expiryDate.format('yyyy-MM-dd'));
				
				data.create_time = data.create_time.replace(/-/g, "/");
		        var createTime = new Date(data.create_time);
				$(".begintime").html(createTime.format('yyyy-MM-dd'));
				
				$(".memberother").html(data.order_desc);
				$(".inquiryEnd").html(data.inquiry_end);
				
				$("#orderId").val(data.id);
				if(data.back_endorse == "Y"){
					$(".ticketReturn").removeClass("none");
				}
				
				$("#txje").html(data.amt-data.quoted_price+data.fee);
				$("#rate").html(data.quoted_rate);
				$(".price2").html(((data.amt-data.quoted_price+data.fee)/(data.amt/10000)*10).toFixed(2));
				$("#actualMoney").html(data.quoted_price);
				
				if(data.state == 4){
					$("#SeePayBtn").removeClass("none");
					$("#cancelBtn").removeClass("none");
					$("#endorseBtn").removeClass("none");
				}else if(data.state == 2){
					$("#cancelBtn").removeClass("none");
					$("#endorseBtn").removeClass("none");
				}if(data.state ==7){
					$("#cancelBtn").removeClass("none");
					$("#endorseBtn").removeClass("none");
				}
				
				$("#distId").val(data.id);
			}
		}
	};
	
	/**
	* 查看支付票款
	*/
	$("#SeePayBtn").click(function(){
		if(cib_login_url_org != null && cib_login_url_org != ""){
			window.open(cib_login_url_org);
		}
	});
	
	/**
	* 获取机构兴业的登录路径
	*/
	function seePayBtn(){
		var url = '${bootAppPath}/cib/corp/query';
		var params = {memberId:memberId,type:1};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				cib_login_url_org = data.data.data.login_url;
			}else{
				alert(data.data.msg);
			}
		}
	};
	
	/**
	* 确认签收背书
	*/
	$("#endorseBtn").click(function(){
		var id = $("#distId").val();
		
		var url = '${bootAppPath}/distributeorderunion/finish';
		var params = {id:id};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				if(data.data.data.return_msg != null){
					alert("已提交订单完成申请，请稍后查看订单状态。" + data.data.data.return_msg);
					return ;
				}
				if(data.data.data.error_msg != null){
					alert("已提交订单完成申请，请稍后查看订单状态。" + data.data.data.error_msg);
					return ;
				}
				if(data.data.data.biz_order.status != null && data.data.data.biz_order.status == "28"){
					 alert('确认签收成功');
					 location.href = "${basePath}/distributeorderunion/list";
				}else{
					alert("已提交订单完成申请，请稍后查看订单状态。");
				}
			}else{
				alert(data.data.msg);
			}
		}
	});
	
	/**
	* 取消订单弹窗
	*/
	$("#cancelBtn").click(function(){
		$("#maskWindow").removeClass("none");
		$("#cancelWindow").removeClass("none");
	});
	
	/**
	* 确认取消
	*/
	$("#confirm_reason").click(function(){
		var id = $("#distId").val();
		
		var url = '${bootAppPath}/distributeorderunion/cancel';
		var params = {id:id};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				if(data.data.data.return_msg != null){
					alert("已提交订单取消申请，请稍后查看订单状态。" + data.data.data.return_msg);
					return ;
				}
				if(data.data.data.error_msg != null){
					alert("已提交订单取消申请，请稍后查看订单状态。" + data.data.data.error_msg);
					return ;
				}
				if(data.data.data.biz_order != null){
					if(data.data.data.biz_order.status != null && data.data.data.biz_order.status == "29"){
						 alert('取消成功');
						 location.href = "${basePath}/distributeorderunion/list";
					}else{
						alert("已提交订单取消申请，请稍后查看订单状态。");
					}
				}else{
					alert(data.data.data.error_msg+"，请联系客服！");
				}
			}else{
				alert(data.data.msg);
			}
		}
	});
	
	//关闭按钮
	$("#closeBtn, .closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#cancelWindow").addClass("none"); //取消
	});
</script>