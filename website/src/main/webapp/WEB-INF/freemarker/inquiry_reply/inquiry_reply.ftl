[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
[@main.header currentmenu='1'/]
 [@main.right /]
 <div class="w1200 bc mt20 mb20 bcwhite">
 <div class="flex flex-direction-column bae0e0e0">
        <div class="fb lh180 cblack mt10 bbe0e0e0 f20 pl10">
            支付查询费用
        </div>
        <div class="flex flex-direction-column pl30 f16 lh30 w800">
            <div class="flex-row flex-direction-row mt13">
                <div class="w80 mr10">押金余额：</div>
                <div><span id="money"></span>元</div>
                <div class="ml20">
                    <input type="number" name="code" id="code" placeholder="输入验证码" class="bae0e0e0 bcwhite h30 ti8 w200 validate[required,maxSize[4]]">
                    <input type="button" id="codeBtn" value="获取验证码" class="bae0e0e0 bcwhite cp h30 w100 ml5">
                </div>
                <a class="c3366cc cp ml30" href="${basePath}/bisicmessage/deposit">补充押金</a>
            </div>
            <div class="flex-row flex-direction-row mt13">
                <div class="w80 mr10">支付金额：</div>
                <input type="hidden" id="payMoney" value="20.00"/>
                <p class="" id="free">20</p>
            </div>
            <div class="flex-row flex-direction-row mt30">
                我们将为您提供查询查复服务，并以短信方式通知您结果。
            </div>
            <div class="flex-row flex-direction-row mt6 cd43c33">
                *各出票行反馈时间有所差异。请耐心等待结果，一般为下单成功后的1-2个工作日。
            </div>
            <input type="button" value="确认支付" id="pay_save" readonly="readonly" onclick="pay_save();" class="fr f18 cwhite bcd43c33 w166 lh45 b0 br5 bc mt20 mb30" />
        </div>
    </div>
</div>

<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>发送验证码</div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭"class="closeBtn">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--选择机构-->
            <div class="pl20 pr20">
            	<!--重置押金-->
	            <div class="flex-row flex-direction-column w lh30 none" id="codeWindow">
	                <div class="flex-row pl105 mt40"><span id="phone">验证码将发送到  ${member.mobile}</span>
	                </div>
	                <!--按钮操作-->
	                <div class="flex-row flex-direction-row-reverse lh30 mt30">
	                    <input type="button" value="确定" id="phoneCodeBtn" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20">
	                    <input type="button" value="取消" class="w90 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn">
	                </div>
	            </div>
            </div>
         </div>
    </div>
</div>
 
<script type="text/javascript">
	
	var free = false;//首单免费
    var ir_no = null;//新产生的未付款订单(编号)
    var ir_id = null;//新产生的未付款订单(主键)
	var memberId="${member.id}";
    $(".needInvoice").click(function () {
        $(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
        $(this).find("input").attr("checked",true);
        $(this).children("label").addClass("cwhite bce84c3d");
        if($(this).find("input").val()==1){//否
        	$("#fapiao").hide();
        }else if($(this).find("input").val()==0){//是
        	$("#fapiao").show();
        }
    })
    $(".titleType").click(function () {
        $(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
        $(this).find("input").attr("checked",true);
        $(this).children("label").addClass("cwhite bce84c3d");
        if($(this).find("input").val()==1){//企业
        	$("#companyname").show();
        }else if($(this).find("input").val()==0){//个人
        	$("#companyname").hide();
        }
    })
    $(".sendWay").click(function () {
        $(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
        $(this).find("input").attr("checked",true);
        $(this).children("label").addClass("cwhite bce84c3d");
    })
    
    $(document).ready(function() {
    	initData();
    	getMoney();
	});
    
    /**
    * 加载账户的余额
    */
    function getMoney(){
    	var url = "${bootAppPath}/account/by/member";
    	var params = {memberId:memberId};
    	var data = bootPost(url,params);
		if(data.data.response == 'success'){
			$("#money").html(data.data.data.money);
		}else{
			alert(data.data.msg);
		}
    };
    
    //初始化部分数据
	function initData(){
		 $.ajax({
			url:"${bootAppPath}/inquiryreply/init",
			type:"post",
			data:{memberId:memberId},
			dataType:"json",
			success:function(data){
				if(data.data.data.free){//首单免费
					free = data.data.data.free;
					$("#free").html("￥0.0");
  					$("#payMoney").val(0);
				}else{
					$("#free").html("￥"+data.data.data.money);
  					$("#payMoney").val(data.data.data.money);
				}
			}
		});
	};
    
	/**
	* 生成查询查复订单
	*/
    function pay_save(){
		if(!$("#code").validationEngine("validate")){
			$("#code").focus();
			return ;
		}
		
		var payMoney = $("#payMoney").val();
		var money = $("#money").html();
		if(parseInt(payMoney)>parseInt(money)){
			alert("余额不足，请充值！");
			return ;
		}
		var mobile = ${member.mobile};
		var code = $("#code").val(); 
    	var url = "${bootAppPath}/inquiryreply/save";
    	var params = {"memberId":memberId,"draftNo":"${draftNo}","money":"${money}","drawer":"${drawer}","payee":"${payee}","bank":"${bank}","bankNo":"${bankNo}",
			"start":"${startDate}","end":"${endDate}","payMoney":payMoney,"payWay":5,"needInvoice":1, "orgType":"0","code":code,mobile:mobile};
    	
    	$("#pay_save").attr("disabled","disabled");//按钮禁用
    	var data = bootPost(url,params);
		if(data.data.response == 'success'){
			alert("支付成功");
			location.href = "${basePath}/inquiryreply/list";
			$("#pay_save").removeAttr("disabled");//按钮启用
		}else{
			alert(data.data.msg);
			$("#pay_save").removeAttr("disabled");//按钮启用
		}
    };
    
  //发送验证码
	$("#codeBtn").click(function(){
	    $("#maskWindow").removeClass("none");
	    $("#codeWindow").removeClass("none"); //发送验证码
	    $("#title").html("发送短信");
	});
	
	//确认发送验证码
	var timeshow = 60;
	var timer ;
	$("#phoneCodeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#codeWindow").addClass("none"); //发送验证码
	    var mobile = ${member.mobile};
	    var url = '${bootAppPath}/send/sms';
		var params = {mobile:mobile,type:'INQUIRY'};
		timeshow = 60;
		var data = bootPost(url,params);
		console.log(data);
		if(data.response == 'success'){
			timer = setInterval(show, 1000);
		}else{
			alert(data.data.msg);
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
	    	$("#codeBtn").removeAttr("disabled");//按钮启用
        }
    };

    /**
	 *  点击交易跟踪
	 */
	 $("#paysuccess").click(function(){
	 	location.href = "${basePath}/inquiryreply/list";
	 });
	
    /**
    *关闭弹窗
    */
    $(".closeBtn").click(function(){
		$("#maskWindow").addClass("none");
 	    $("#codeWindow").addClass("none");
    });
</script>

[@main.footer/]
[/@main.body]