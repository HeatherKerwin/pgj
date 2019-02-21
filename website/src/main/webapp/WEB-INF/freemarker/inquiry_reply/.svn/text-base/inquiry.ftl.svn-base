[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='1'/]
<div class="w1200 bc mt20 mb20 bcwhite">
    <div class="w bcf4f4f4 h100 lh100 f36 ti65 fb">银票查询查复</div>
    <div class="w1198 bae0e0e0 h608">
        <div class="ml100 mr100">
            <div class="wa mt30 lh40">
                <div class="fl w_50">
                    <div class="fl w100 ml65">票号：</div>
                    <input type="text" id="draftNo"  class="validate[required,custom[draftNo]]  fl w245 h40 f18 ti25 bae0e0e0 br5" placeholder="请输入汇票票号">
                </div>
                <div class="fl w_50">
                    <div class="fl w100">票据金额：</div>
                    <input type="text" id="money"  class="validate[required,custom[allprice]]  fl w245 h40 f18 ti25 bae0e0e0 br5" placeholder="请输入金额">
                    <div class="fl ml10">万元</div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40">
                <div class="fl w_50">
                    <div class="fl w100 ml65">出票人：</div>
                    <input type="text" id="drawer" maxlength="30" class="validate[required,maxSize[30]] fl w245 h40 f18 ti25 bae0e0e0 br5" placeholder="请输入出票人全称">
                </div>
                <div class="fl w_50">
                    <div class="fl w100">收款人：</div>
                    <input type="text" id="payee" maxlength="30" class="validate[required,maxSize[30]] fl w245 h40 f18 ti25 bae0e0e0 br5" placeholder="请输入收款人全称">
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40">
                <div class="fl w_50">
                    <div class="fl w100 ml65">承兑行全称：</div>
                    <input type="text" id="bank" maxlength="30" class="validate[required,maxSize[30]] fl w245 h40 f18 ti25 bae0e0e0 br5" placeholder="请输入承兑行全称">
                </div>
                <div class="fl w_50">
                    <div class="fl w100">承兑行号：</div>
                    <input type="text" id="bankNo" maxlength="12" class="validate[required,maxSize[12]] fl w245 h40 f18 ti25 bae0e0e0 br5" placeholder="请输入承兑行号">
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40 xuxian pb30">
                <div class="fl w_50">
                    <div class="fl w100 ml65">出票日期：</div>
                    <input type="text" id="start" class="fl w245 h40 f18 ti25 bcf5f5f5 bae0e0e0 br5" placeholder="请选择贴现日期" readonly="readonly">
                </div>
                 <input type="hidden" name="start" />
                <div class="fl w_50">
                    <div class="fl w100">到期日期：</div>
                    <input type="text" id="end" class="fl w245 h40 f18 ti25 bcf5f5f5 bae0e0e0 br5" placeholder="请选择贴现日期" readonly="readonly">
                </div>
                 <input type="hidden" name="end" />
                <div class="cb"></div>
            </div>
            <div class="w ml65 mt30 cd43c33 f12">*请注意，每次查询查复都会在大额支付系统中留下查询记录</div>
            <div class="w250 mt65 bc">
                <input type="button" class="w250 h60 lh60 bcd43c33 b0 br5 cwhite tc f24 cp" value="生成订单" onclick="save();" id="pay_save">
            </div>
        </div>
    </div>
</div>
[@main.right /]

<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript">
	var memberId="${member.id}";
	
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
    $("#start").val(fullyear + "-" + month + "-" + date);
    $("#end").val(endfullyear + "-" + endmonth + "-" + enddate);
	
	$(document).ready(function() {
		$("#bankNo").keyup(function(){
			var temp = $(this).val();
			$(this).val(temp.replace(/[^0-9]/g,''));
		});
		$("#draftNo").keyup(function(){
			var temp = $(this).val();
			$(this).val(temp.replace(/[^0-9]/g,''));
		});
		loadDate();
	})
	
	/**
	*  初始化加载数据
	*/
	function loadDate(){
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
			var start = $("#start").val(start1);
			var end=$("#end").val(end1);
		}else{
		}
	}
	
	function save(){
		if(!$("#draftNo").validationEngine("validate")){
			$("#draftNo").focus();
			setTimeout(function(){$("#draftNo").validationEngine('hideAll');},1000);
			return ;
		}
		if(!$("#money").validationEngine("validate")){
			$("#money").focus();
			setTimeout(function(){$("#money").validationEngine('hideAll');},1000);
			return ;
		}
		if(!$("#drawer").validationEngine("validate")){
			$("#drawer").focus();
			setTimeout(function(){$("#drawer").validationEngine('hideAll');},1000);
			return ;
		}
		if(!$("#payee").validationEngine("validate")){
			$("#payee").focus();
			setTimeout(function(){$("#payee").validationEngine('hideAll');},1000);
			return ;
		}
		if(!$("#bank").validationEngine("validate")){
			$("#bank").focus();
			setTimeout(function(){$("#bank").validationEngine('hideAll');},1000);
			return ;
		}
		if(!$("#bankNo").validationEngine("validate")){
			$("#bankNo").focus();
			setTimeout(function(){$("#bankNo").validationEngine('hideAll');},1000);
			return ;
		}
		
		$("#pay_save").attr("disabled","disabled");
		
		var no = '${inquiryreply.no}';
		if(no.length>0){
			saveUpdate();
			return ;
		}
		
		var draftNo = $("#draftNo").val();
		var money = $("#money").val();
		var drawer = $("#drawer").val();
		var payee = $("#payee").val();
		var bank = $("#bank").val();
		var bankNo = $("#bankNo").val();
		var start = $("#start").val();
		var end = $("#end").val();
		
		var map = new Map();
		map.put("_PAGE", "inquiry_reply/inquiry_reply");//必传
		map.put("draftNo", draftNo);
		map.put("money", money);
		map.put("drawer", drawer);
		map.put("payee", payee);
		map.put("bank", bank);
		map.put("bankNo", bankNo);
		map.put("startDate", start);
		map.put("endDate", end);
		
		$.ajax({
			type: "POST",
	     	url: "${basePath}/discountrecord/jiejia",
	     	data: {},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			alert(data.msg);
	     			$("#pay_save").removeAttr("disabled");//按钮启用
	     		}else{//调整支付，暂时不用支付，直接生成订单
					_OPENPAGE_FORM(map);
	     		}
	    	}
		});
	 };
	
	function saveUpdate(){
		var no = '${inquiryreply.no}';
		var draftNo = $("#draftNo").val();
		var money = $("#money").val();
		var drawer = $("#drawer").val();
		var payee = $("#payee").val();
		var bank = $("#bank").val();
		var bankNo = $("#bankNo").val();
		var start = $("#start").val();
		var end = $("#end").val();
		$.ajax({
			type: "POST",
	     	url: "${basePath}/inquiryreply/savaupdate",
	     	data: {"no":no,"draftNo":draftNo,"money":money,"drawer":drawer,"payee":payee,"bank":bank,"bankNo":bankNo,"start":start,"end":end},
	     	dataType:"json",
	     	success:function(data){
	     		if (data.response == 'success'){
    				window.location.href="${basePath}/inquiryreply/success";
    			}else{
    				alert(data.msg);
    			}
	    	}
		});	
	};
	
	/*
    * 支付暂时免费，调整支付
	*/
	function payFree(){
		var draftNo=$("#draftNo").val();
		var money=$("#money").val();
		var drawer=$("#drawer").val();
		var payee=$("#payee").val();
		var bank=$("#bank").val();
		var bankNo=$("#bankNo").val();
		var start=$("#start").val();
		var end=$("#end").val();
        $.ajax({
    		url:"${basePath}/inquiryreply/save",
    		type:"post",
    		data:{"memberId":"${member.id}","draftNo":draftNo,"money":money,"drawer":drawer,"payee":payee,"bank":bank,"bankNo":bankNo,
    			"start":start,"end":end, "qudao":"PC",
    		},
    		dataType:"json",
    		success:function(data){
    			if (data.response == 'success'){
    				if(data.no!=null && $.trim(data.no)!=""){
    					window.location.href="${basePath}/inquiryreply/success";
    				}
    			}else{
    				alert(data.msg);
    			}
    			$("#pay_save").removeAttr("disabled");//按钮启用
    		}
    	})
		
	}
	
	//日历
    !function(){
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
    }();
    //日期范围限制
   //    贴现日期
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD',
        //min: laydate.now(), //设定最小日期为当前日期
        min: '1900-01-01', //设定最小日期
        max: '2099-06-16', //最大日期
        istime: true,
        istoday: false,
        choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    //    到期日期
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD',
        min: laydate.now(),
        max: '2099-06-16',
        istime: true,
        istoday: false,
        choose: function(datas){
            start.max = datas; //结束日选好后，充值开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
	
</script>
[@main.footer/]
[/@main.body]