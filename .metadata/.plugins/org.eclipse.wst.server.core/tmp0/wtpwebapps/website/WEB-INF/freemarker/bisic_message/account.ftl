 [#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--我的钱包-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
     [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 h688 bcwhite bae0e0e0">
    <div class="flex flex-direction-column w997 bcwhite">
        <div class="flex-row flex-justify-space-between bcfaf7f7 h34 lh34">
            <div class="ml10">我的钱包</div>
            <div class="c3366cc mr40" onclick="accountRecord()">交易记录</div>
        </div>
    </div>
    <div class="flex-row w700 flex-direction-column pt70 pl133">
        <div class="flex-row w flex-alignItems-baseline">
            <div class="flex-col-4 flex-direction-column tl flex-justify-center">
                <div class="cd43c33 f24"><span class="f36" id="money"></span>元</div>
                <div class="f18 mt13">钱包余额</div>
            </div>
            <div class="flex-col-4 flex-direction-column tc">
                <div class="cd43c33 f24"><span class="f36" id="disc_deposit"></span>元</div>
                <div class="f18 mt13">出票保证金</div>
            </div>
            <div class="flex-col-4 flex-direction-column tr">
                <div class="cd43c33 f24"><span class="f36" id="dist_deposit"></span>元</div>
                <div class="f18 mt13">收票保证金</div>
            </div>
        </div>
        <div class="flex-row flex-direction-row flex-justify-space-between w mt53">
        	<div class="flex-row flex-direction-column w320 h280 pl10 pr10" style="box-shadow: 1px 1px 5px #ddd;">
	            <div class="h42 f18 lh42 bbe0e0e0">充值</div>
	            <div class="flex-row flex-alignItems-center w mt20">
	                <label class="mr40">充值金额</label>
	                <input type="number" name="pay_money" id="pay_money" class="w165 ti8 h30 lh30 br3 bae0e0e0 validate[required],validate[custom[onlyNumberSp]]"  placeholder="请输入金额"><span
	                    class="ml10">元</span></div>
	            <div class="flex-row flex-alignItems-center w mt13">
	                <label class="mr40">充值方式</label>
	                <select class="w165 h30 lh30 br3 bae0e0e0 ti8 select148" id="pay_type">
	                	<option value="ALIPAY">支付宝</option>
	                    <option value="BILL99">快钱支付</option>
	                    <option value="BAOFOO">宝付支付</option>
	                </select>
	            </div>
	            <div class="flex-row flex-alignItems-center w mt30">
	                <input type="button" value="立即支付" onclick="pay()" class="w165 h30 f16 lh30 bcd43c33 bad43c33 cwhite br3 mt30 ml70">
	            </div>
            </div>
            <div class="flex-row flex-direction-column w320 f14 lh20 pl10 pr10 h280" style="box-shadow: 1px 1px 5px #ddd;">
            	<div class="h42 f18 lh42 mb5 bbe0e0e0">注意事项</div>
            	<div class="flex-row flex-alignItems-center w">
	                <input type="button" value="提现到支付宝" onclick="tx(1)" class="w130 h30 f16 lh30 bcd43c33 bad43c33 cwhite br3 mt7 mb5 m50">
	                <input type="button" value="提现到银行卡" onclick="tx(2)" class="w130 h30 f16 lh30 bcd43c33 bad43c33 cwhite br3 mt7 mb5 ml50">
	            </div>
            	<p>各渠道充值的资金，提现时通过原渠道退回<a class="dsib c00a5f2 cp unl tixianshili" href="javascript:void(0)">(查看示例)</a>。各渠道退款时间如下（票管家两个工作日内审核）：		
				<p>支付宝：余额支付，当天返还。银行卡支付，1-2个工作日返还。</p>
				<p>宝付：3个工作日内返还。</p>
				<p>快钱：7-15个工作日返还。</p>
            </div>
        </div>
    </div>
</div>
    <div class="cb"></div>
</div>

<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>提现</div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn" class="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭" class="cp">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--钱包余额-->
                <div class="flex-row flex-direction-column pl50 hmin150" id="content">
                    <div class="flex-row mt20">
                        钱包余额：<span id="balance"></span>
                    </div>
                    <div class="flex-row mt16">
                        <input type="number" id="tx_money" class="bae0e0e0 br3 h30 w300 ti8 validate[required]" placeholder="请输入提现金额">
                    </div>
                    <div class="flex-row mt16 bank none">
                        <input type="text" id="cardNumber" class="bae0e0e0 br3 h30 w300 ti8 validate[required]" placeholder="请输入银行卡号">
                    </div>
                    <div class="flex-row mt16 alipay none">
                        <input type="text" id="alipayCardNumber" class="bae0e0e0 br3 h30 w300 ti8 validate[required]" placeholder="请输入支付宝账号">
                    </div>
                    <div class="flex-row mt16 alipay none">
                        <input type="text" id="alipayCardUserName" class="bae0e0e0 br3 h30 w300 ti8 validate[required]" placeholder="请输入支付宝实名认证姓名">
                    </div>
                    <div class="flex-row mt16 flex-direction-column bank none">
                        <input id="cardBank" type="text" class="bae0e0e0 br3 h30 w300 ti8 validate[required]" placeholder="请输入开户行名称">
                    </div>
                    <div class="flex-row mt16 bank none">
                        <input type="text" id="cardUserName" class="bae0e0e0 br3 h30 w300 ti8 validate[required]" placeholder="请输入账户名">
                    </div>
                    <div class="flex-row mt16">
                        <input type="text" id="code" class="bae0e0e0 br3 h30 w200 ti8 validate[required,maxSize[4]]" placeholder="请输入验证码">
                        <input type="button" id="codeBtn" class="bae0e0e0 br3 h30 w100 ml12" value="获取验证码">
                        <div class="cd43c33 f13 mt10">&nbsp;&nbsp;*短信发送到${member.mobile}</div>
                    </div>
                    <div class="flex-row flex-align-self-center lh30 mt20">
                        <input type="button" id="confirm_tx" value="提现" class="w140 h30 ba bad43c33 bcd43c33 cwhite br3 cp">
                    </div>
                </div>
                <!--温馨提示-->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150 none" id="prompt">
                    <div class="flex-row flex-direction-column pl20 pr20">
                        <div class="fb tc mt25 f18">温馨提示</div>
                        <div class="mt20">提现将于两个工作日之内到账，请注意查收。
                        </div>
                    </div>
                    <div class="flex-row flex-align-self-center lh30 mt20">
                        <input type="button" value="确定" class="closeBtn w140 h30 ba bad43c33 bcd43c33 cwhite br3 cp closeBtn">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="w h pf bc05 zi10 top none" id="tixian">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w800 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="title"></div>
                <!--关闭按钮-->
                <label for="closeIcon" class="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭" class="cp">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!-- 提现示例 -->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150">
                	<img src="https://img.utiexian.com/website/PJGJ/account/lizi.jpg" class="w">
                </div>
            </div>
        </div>
     </div>
</div>

<input id="payType" type="hidden">
[@main.right /]
[@main.footer/]
[/@main.body]

<link rel="stylesheet" type="text/css" href="${pluPath}/ajaxPager/page.css"/>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>

<script>
	var memberId = ${member.id};
	$(function(){
		loadDate();
	});
	
	/**
	* 用户进行提现
	*/
	function tx(type){
		var money = $("#money").html();
		if(money == 0){
			alert("余额为零，无法提现。");
			return ;
		}
		$("#balance").html(money+"元");
		
		if(type==1){
			$(".alipay").removeClass("none");
			$(".bank").addClass("none");
		}else if(type==2){
			$(".bank").removeClass("none");
			$(".alipay").addClass("none");
		}
		$("#payType").val(type);
		$("#maskWindow").removeClass("none");
		$("#content").removeClass("none");
	}
	
	/**
	* 用户确认体现
	*/
	$("#confirm_tx").click(function(){
		var type = $("#payType").val();
		var code = $("#code").val();
		var cardBank = $("#cardBank").val();
		var cardUserName = "";
		var cardNumber = "";
		var money = $("#tx_money").val();
		var way = "";
		if(parseInt(money) <= 1 ){
			alert("提现金额不能小于1");
			return;
		}
		if(parseInt(money) > parseInt($("#balance").text())){
			alert("提现金额不能超过余额");
			return;
		}
		if(!$("#tx_money").validationEngine("validate")){
			$("#tx_money").focus();
			return ;
		}
		
		if(type==2){
			if(!$("#cardBank").validationEngine("validate")){
				$("#cardBank").focus();
				return ;
			}
			
			if(!$("#cardUserName").validationEngine("validate")){
				$("#cardUserName").focus();
				return ;
			}
			
			if(!$("#cardNumber").validationEngine("validate")){
				$("#cardNumber").focus();
				return ;
			}
			cardNumber = $("#cardNumber").val();
			cardUserName = $("#cardUserName").val();
			way = "TX_OFFLINE_BANK";
		}else{
			if(!$("#alipayCardNumber").validationEngine("validate")){
				$("#alipayCardNumber").focus();
				return ;
			}
			if(!$("#alipayCardUserName").validationEngine("validate")){
				$("#alipayCardUserName").focus();
				return ;
			}
			cardNumber = $("#alipayCardNumber").val();
			cardUserName = $("#alipayCardUserName").val();
			way = "TX_OFFLINE_ALIPAY";
		}
		
		if(!$("#code").validationEngine("validate")){
			$("#code").focus();
			return ;
		}
		
		var mobile = ${member.mobile};
		var url = '${bootAppPath}/accountlog/save/tx';
		var params = {code:code,memberId:memberId,cardBank:cardBank,cardUserName:cardUserName,cardNumber:cardNumber,money:money,mobile:mobile,type:type};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				alert("提现申请已提交后台审核，请耐心等待！");
				$("#maskWindow").addClass("none");
				$("#prompt").addClass("none");
				$("#content").addClass("none");
				loadDate();
			}else{
				alert(data.data.msg);
			}
		};
	});
	
	//确认发送验证码
	var timeshow = 60;
	var timer;
	$("#codeBtn").click(function(){
	    var mobile = ${member.mobile};
	    var url = '${bootAppPath}/send/sms';
		var params = {mobile:mobile,type:'TX'};
		timeshow = 60;
		$("#codeBtn").attr({"disabled":"disabled"});
		var data = bootPost(url,params);
		console.log(data);
		if(data.response == 'success'){
			timer = setInterval(show, 1000);
		}else{
	    	$("#codeBtn").removeAttr("disabled");//按钮启用
		}
	});
	
	/**
	* 短信倒计时
	*/
	function show() {
	    if (timeshow <= 1) {
	        clearInterval(timer);
	    }
	    timeshow--;
	    $("#codeBtn").val(timeshow+"s重新发送");
	    if (timeshow == 0) {
			$("#codeBtn").val("重新发送 ");
			$("#codeBtn").removeAttr("disabled");
	    }
	};
	
	/**
	* 提现示例
	*/
	$(".tixianshili").click(function(){
		$("#tixian").removeClass("none");
		$("#title").html("提现示例");
	});
	
	/**
	* 用户关闭弹窗
	*/
	$(".closeBtn").click(function(){
		$("#maskWindow").addClass("none");
		$("#prompt").addClass("none");
		$("#content").addClass("none");
		$("#tixian").addClass("none");
	});
	
	/**
	* 初始化页面加载数据
	*/
	function loadDate(){
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
				
				if(data.data.data.disc_deposit != null){
					$("#disc_deposit").html(data.data.data.disc_deposit);
				}else{
					$("#disc_deposit").html(0);
				}
				
				if(data.data.data.dist_deposit != null){
					$("#dist_deposit").html(data.data.data.dist_deposit);
				}else{
					$("#dist_deposit").html(0);
				}
			}else{
				alert("获取数据失败");
			}
		}
	};
	
	/**
	*押金充值
	*/
	function pay(){
		if(!$("#pay_money").validationEngine("validate")){
			setTimeout(function(){$(".formError").hide();},2000);
			$("#pay_money").focus();
			return ;
		}
		
		var pay_type = $("#pay_type").val();
		var pay_money = $("#pay_money").val();
		if(pay_money == 0){
			$("#pay_money").validationEngine('showPrompt','* 充值金额不能为0',null,null,true);
			setTimeout(function(){$(".formError").hide();},2000);
			return ;
		}
		if(pay_type == null ||  $.trim(pay_type)==""){
			alert("请选择充值方式");
			return ;
		}
		
		window.open('${basePath}/account/pay?money='+pay_money+'&memberId='+memberId+'&type='+pay_type);
	};
	
	/**
	* 充值记录跳转
	*/
	function accountRecord(){
		window.location.href= '${basePath}/bisicmessage/accountRecord';
	};
</script>
