[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家-查询查复"]
[@main.header currentmenu='2'/]

<div class="wrapper">
<!--容器-->
    <form action="" class="form">
        <ul>
            <li>
                <div class="formTitle">票号：</div>
                <input type="number" id="draftNo" placeholder="请输入汇票票号"/>
            </li>
            <li>
                <div class="formTitle">金额：</div>
                <input type="number" id="money" class="w100" placeholder="请输入金额"/>
                <span>万元</span>
            </li>
            <li>
                <div class="formTitle">出票人：</div>
                <input type="text" id="drawer" placeholder="请输入出票人全称"/>
            </li>
            <li>
                <div class="formTitle">收款人：</div>
                <input type="text" id="payee" placeholder="请输入收款人全称"/>
            </li>
            <li>
                <div class="formTitle">承兑行全称：</div>
                <input type="text" id="bank" placeholder="请输入承兑行全称"/>
            </li>
            <li>
                <div class="formTitle">承兑行号：</div>
                <input type="number" id="bankNo" placeholder="请输入承兑行号"/>
            </li>
            <li>
                <div class="formTitle">出票日期：</div>
                <input type="date" class="w200" id="startDate"/>
            </li>
            <li>
                <div class="formTitle">到期日期：</div>
                <input type="date" id="endDate" class="w200"/>
            </li>
        </ul>

        <ul class="mt10">
            <li>
                <div class="formTitle">付款方式：</div>
                <select name="" id="pay_Way" class="">
                    <option value="3" selected="selected">快钱支付</option>
                    <option value="4">宝付支付</option>
                </select>
            </li>
        </ul>

        <ul class="mt10">
            <li>
                <div class="formTitle">付款金额：</div>
                <input type="number" value="30" id="payMoney" readonly="readonly" placeholder="请输入付款金额"/></li>
        </ul>

        <ul class="mt10">
            <li>
                <div class="formTitle">是否需要发票：</div>
                <ul class="choseBtn">
                    <li><input type="radio" name="invoice" value="0" id="zhi" class="radio" /><label for="zhi" >是</label></li>
                    <li><input type="radio" name="invoice" value="1" id="dian" class="radio" checked="checked" /><label for="dian" class="radioSelect">否</label></li>
                </ul>
            </li>
            <div id="invoiceCON" class="hide" >
				<li>
				    <div class="formTitle">发票抬头：</div>
				    <select name="" id="invoiceHeader">
				        <option value="0" selected="selected">个人</option>
				        <option value="1">企业</option>
				    </select>
				</li>
				<li>
				    <div class="formTitle">发票类型：</div>
				    <span id = "invoiceType">普通发票</soap>
				</li>
				<li id="companyName">
				    <div class="formTitle">公司名称：</div>
				    <input type="text" id="title1" placeholder="请输入公司名称"/>
				    <input id="title0" value="个人" type="hidden" >
				</li>
				<li>
				    <div class="formTitle">发票内容：</div>
				    <span id="content">服务费</span>
				</li>
				<li>
				    <div class="formTitle">寄送方式：</div>
				    <select id="sendWay">
				        <option value="0" selected="selected">平邮</option>
				        <option value="1">快递到付</option>
				    </select>
				</li>
				<li>
				    <div class="formTitle">收件人姓名：</div>
				    <input type="text" id="name" placeholder="请输入收件人姓名"/>
				</li>
				<li>
				    <div class="formTitle">收件人电话：</div>
				    <input type="tel" id="phone" placeholder="请输入收件人电话"/>
				</li>
				<li class="bnone">
				    <div class="formTitle">寄送地址：</div>
				    <select id="prov" onchange="selectC();">
				    </select>
				    <select id="city" onchange="selectD();">
				    </select>
				</li>
				<li>
				    <div class="formTitle">&nbsp;</div>
				    <select id="dist">
				    </select>
				</li>
				<li>
				    <div class="formTitle">详细地址：</div>
				    <input type="text" id="address" placeholder="请填写详细地址"/>
				</li>
            </div>
        </ul>

        <p class="cred mt10 font12 tac">*请注意，每次查票过程都会在大额支付系统中留下查询记录</p>

        <a class="formBtn" href="javascript:void(0);"><input type="button" onclick="save();" value="确认支付"/></a>
    </form>
</div>
<link rel="stylesheet" href="${staticPath}/css/rywap/form.css"/>
<script type="text/javascript" src="${staticPath}/js/rywap/base64.js"></script>
<script>
	
	var memberId = ${member.id};//${member.id};//获取登录用户主键
 	var free = false;//首单免费
	//初始化日期
	var begintimelong = Date.parse(new Date());
	var begintime = new Date(begintimelong);
	var fullyear = begintime.getFullYear();
	//获取完整的年份(4位,1970-????)
	var month = begintime.getMonth() + 1;
	//获取当前月份(0-11,0代表1月)
	var date = begintime.getDate();
	//获取当前日(1-31)
	
	var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
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
	
	$(function(){
		var member_id = ${member.id};//获取登录用户主键
		
		loadDate();
		$("#startDate").val(fullyear + "-" + month + "-" + date);
		$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);
		var no = '${inquiryreply.no}';
		var draftNo1 = '${inquiryreply.draftNo}';
		var money1 = '${inquiryreply.money}';
		var drawer1 = '${inquiryreply.drawer}';
		var payee1 = '${inquiryreply.payee}';
		var bank1 = '${inquiryreply.bank}';
		var bankNo1 = '${inquiryreply.bankNo}';
		var start1 = '${inquiryreply.startDate}';
		var end1 = '${inquiryreply.endDate}';
		if(no.length>0) {
			var draftNo = $("#draftNo").val(draftNo1);
			var money = $("#money").val(money1);
			var drawer = $("#drawer").val(drawer1);
			var payee = $("#payee").val(payee1);
			var bank = $("#bank").val(bank1);
			var bankNo = $("#bankNo").val(bankNo1);
			var start = $("#startDate").val(formatDateTimeYmd(start1));
			var end=$("#endDate").val(formatDateTimeYmd(end1));
		}else{
		}
	});
    /*是否需要开发票*/
    $("input[name='invoice']").on('click',function(){
        var thisIndex = $("input[name='invoice']:checked").val();
        if ("1" == thisIndex) {
            $("#invoiceCON").addClass("hide");
        } else{
            $("#invoiceCON").removeClass("hide");
        }
    })

    /*选择发票抬头*/
    $("#invoiceHeader").on('click', function () {
        var thisIndex = $("#invoiceHeader").val();
        if ("0" == thisIndex) {
            $("#companyName").removeClass("hide");
        } else{
            $("#companyName").addClass("hide");
        }
    })
    
	/**
	*	初始化数据
	*/
	function loadDate(){
		$.ajax({
			url:"${basePath}/wap/inquiryreply/initCity",
			type:"post",
			dataType:"json",
			success:function(data){
				var result = data.data;
				initCity(data);//初始化省市区
				if(data.free){//首单免费
                    free = data.free;
                    $("#free").html("￥0.0");
                    $("#payMoney").val(0);
                }
	        }
		 });
    };
    
    
    /**
    *	初始化城市
    */
    function initCity(obj){
    	p = obj.p;
        c = obj.c;
        d = obj.d;
        var phtml = "";
        for(var i=0;i<p.length;i++){
            var provice = p[i];
            phtml += "<option value='"+provice.id+"'>"+provice.name+"</option>";
        }
        $("#prov").html(phtml);
        selectC();
    }
    
    /**
     * 选择市
     */
    function selectC(){
        var p = $("#prov").val();
        var chtml = "";
        for(var i=0;i<c.length;i++){
            if(p==(c[i].parentId)){
                chtml += "<option value='"+c[i].id+"'>"+c[i].name+"</option>";
            }
        }
        $("#city").html(chtml);
        selectD();
    }
    
    /**
     * 选择区
     */
    function selectD(){
        var c = $("#city").val();
        var dhtml = "";
        for(var i=0;i<d.length;i++){
            if(c==(d[i].parentId)){
                dhtml += "<option value='"+d[i].id+"'>"+d[i].name+"</option>";
            }
        }
        $("#dist").html(dhtml);
    }
    
    function save(){
    	var draftNo = $("#draftNo").val();
		 var  re = /^(\d{16}|\d{30})$/;
	     var result= draftNo.match(re);
	     var money = $("#money").val();
		 var drawer = $("#drawer").val();
		 var payee = $("#payee").val();
		 var bank = $("#bank").val();
		 var bankNo = $("#bankNo").val();
	     if(!result){
	         myToast("你输入的票号位数不对,请仔细核对！");
	         return ;
	     }
	     if($.trim(money).length <= 0 || $.trim(money).length > 10){
	    	 myToast("请仔细核对输入的金额");
	    	 return ;
	     }
		 if($.trim(drawer).length <= 0){
			myToast("出票人不能为空")
			return ;
		 }
		 if($.trim(payee).length <= 0){
			myToast("收款人不能为空");
			return ;
		 }
		 if($.trim(bank).length <= 0){
			myToast("承兑行全称不能为空");
			return ;
		 }
		 if($.trim(bankNo).length <= 0){
			myToast("承兑号不能为空");
			return ;
		 }
		
		 var startDate = $("#startDate").val();
		 var endDate = $("#endDate").val();
		 var payMoney = $("#payMoney").val();
		 var payWay = $("#pay_Way").val();
		 var needInvoice = $("input[name='invoice']:checked").val();
		 
		 var titleType = $(".billtype").val();
		 var sendWay = $("#sendWay").val();
		 var title;
		 var invoiceType = $("#invoiceType").val();
		 var content = $("#content").val();
		 var name = $("#name").val();
		 var phone = $("#phone").val();
		 var prov = $("#prov").val();
		 var city = $("#city").val();
		 var dist = $("#dist").val();
		 var address = $("#address").val();
		 if(needInvoice==0){
			 var title1 = $("#title1").val();
	    	 if(titleType==1 && $.trim(title1).length <= 0){
	    		 myToast("公司名称不能为空");
	    		 return;
	    	 }
	    	 if($.trim(name).length <= 0){
	    		myToast("收件人不能为空");
	    		return;
	    	 }
	    	 if($.trim(phone).length <= 0){
	    		myToast("联系方式不能为空");
	    		return;
	    	 }
	    	 if($.trim(address).length <= 0){
	    		myToast("详细地址不能为空")
	    		return;
	    	 }
	    	 if(titleType==1){
	    		title=$("#title1").val();
	    	 }else{
	    		title=$("#title0").val();
	    	 }
    	 }
		 var URL = "${basePath}/wap/inquiryreply/save";
		
		 var no = '${inquiryreply.no}';
		 if(no.length>0){
			free = true;
			URL = "${basePath}/inquiryreply/savaupdate";
		 }
		 $.ajax({
	    		url: URL,
	    		type:"post",
	    		async: false, 
	    		data:{"memberId":memberId,"draftNo":draftNo,"money":money,"drawer":drawer,"payee":payee,"bank":bank,"bankNo":bankNo,"no":no,
	    			"start":startDate,"end":endDate,"payMoney":payMoney,"payWay":payWay,"needInvoice":needInvoice, "orgType":"0", "titleType":titleType,
	    			"sendWay":sendWay,"title":title,"invoiceType":invoiceType,"content":content,"name":name,"phone":phone,"prov":prov,"city":city,"address":address,"dist":dist,
	    			"qudao":"WECHAT",
	    		},
	    		dataType:"json",
	    		success:function(data){
	    			var payWay = $("#pay_Way").val();
	    			if (data.response == 'success'){
	    				if(data.no!=null && $.trim(data.no)!=""){
	    					if(free){
	    						window.location.href="${basePath}/inquiryreply/list/serach";
	    					}else{
	    						var safari = false;
	    					    if(isSafari=navigator.userAgent.indexOf("Safari")>0) {   
	    					    	safari = true; 
	    					    } 
	    						var _URL = '';
	    						if(payWay==3){//快钱
    						        var money = $("#payMoney").val();//付款金额
    						        if(safari){
    									window.location.href = "${appPath}"+'/app/inquiryReply/pay?orderAmount='+money+'&memberId='+memberId+'&orderId='+data.no;
    									return;
    								}else{
    									 _URL = "${appPath}"+'/app/inquiryReply/pay?orderAmount='+money+'&memberId='+memberId+'&orderId='+data.no;
    								}
                                }else if(payWay==4){//宝付
                                	_URL = "${mobilePath}"+'/baofoo/pay?orderNo='+data.no+'&type=0';
                                }
	    						window.location.href = BASEPATH+"/wap/pay?url=" + new Base64().encode(_URL);
	    					}
	    				}
	    			}else{
	    				myToast(data.msg);
	    				return;
	    			}
	    		}
	    	})
    }
</script>
[@main.footer/]
[/@main.body]