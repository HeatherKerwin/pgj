[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-查询查复详情']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='1'/]

<!--查询查复表单-->
<div class="f14 mt16 w1150 bc bcwhite bae0e0e0 mt20 mb80 pl25 pr25">
    <div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0" id="zhuangtai">
    </div>
    <div class="fb lh180 cblack mt30 bbe0e0e0">
        确认订单信息
    </div>
    <div class="ha mt30 bae0e0e0 bcwhite">
        <div class="w ha bbe0e0e0">
            <div class="fl w700 bre0e0e0">
                <div class="w h50 lh50 f14 c717583 bcf9f9f9 tc bbe0e0e0">
                    <div class="fl w250">查询号</div>
                    <div class="fl w300">票号</div>
                    <div class="fl w150">票据金额</div>
                </div>
                <div class="ml10 mr10 pt10 pb10 f16 lh45 tc xuxian">
                    <div class="fl w240 h44 Rright c3366cc tl" id="no"></div>
                    <div class="fl w300 h44 Rright" id="draft_no"></div>
                    <div class="fl w140 h44" id="money"></div>
                    <div class="cb"></div>
                </div>
                <div class="w pt25 pb25 f16 tc">
                    <div class="fl w240 h52 ml10 Rright tl">
                        <div class="">出票日期：<span id="start_date"></span></div>
                        <div class="mt19">到期日期：<span id="end_date"></span></div>
                    </div>
                    <div class="fl h52 ml20 tl" style="display:none">
                        <div class="">支付方式：<span>支付宝</span></div>
                        <div class="mt19">费用：<span id="pay_money"></span></div>
                    </div>
                    <div class="cb"></div>
                </div>
            </div>
            <div class="fl w447 bcwhite">
                <div class="fl w_50 mt16">
                    <div class="tc f14 c717583">出票人</div>
                    <div class="h60 mt10 ml15 mr15 f14 lh20 tc" id="drawer"></div>
                </div>
                <div class="fl w_50 mt16">
                    <div class="tc f14 c717583">承兑行号</div>
                    <div class="h60 mt10 ml15 mr15 f14 lh20 tc" id="bank_no"></div>
                </div>
                <div class="fl w_50 mt16">
                    <div class="tc f14 c717583">收款人</div>
                    <div class="h60 mt10 ml15 mr15 f14 lh20 tc" id="payee"></div>
                </div>
                <div class="fl w_50 mt16">
                    <div class="tc f14 c717583">承兑行全称</div>
                    <div class="h60 mt10 ml15 mr15 f14 lh20 tc" id="bank"></div>
                </div>
            </div>
            <div class="cb"></div>
        </div>
        <div class="w bcf9f9f9 pt25 pb25 none" id="fapiao">
            <div class="ml10 fb">发票信息：</div>
            <div class="w tc mt25">
                <div class="fl ml10 mr60">
                    <div class="f14 c717583">发票抬头</div>
                    <div class="f16 mt10" id="title_type"></div>
                </div>
                <div class="fl mr60">
                    <div class="f14 c717583">发票类型</div>
                    <div class="f16 mt10" id="invoice_type"></div>
                </div>
                <div class="fl mr60">
                    <div class="f14 c717583">发票内容</div>
                    <div class="f16 mt10" id="content"></div>
                </div>
                <div class="fl mr60">
                    <div class="f14 c717583">寄送方式</div>
                    <div class="f16 mt10" id="send_way"></div>
                </div>
                <div class="fl tl">
                    <div class="f14 c717583">寄送地址</div>
                    <div class="f16 mt10 w650 ha">
                    	<div class="fl mr5" id="PCD"></div>
                    	<span id="address" class="fl"></span>
                	</div>
                </div>
            </div>
            <div class="cb"></div>
        </div>
    </div>
    <div class="w pt20 pb20 lh20 bae0e0e0 bcf9f9f9 f16 mt20 mb13" id="yanpiao">
        <div class="fl w90 fb ti10">验票结果:</div>
        <div class="fl w998 lh24" id="result">我们将尽快以短信方式通知您查询查复结果，但由于各出票行反馈时间有所差异，一般为1-2个工作日内，请耐心等待结果。</div>
        <div class="cb"></div>
    </div>
    <div class="mb13 none" id="send">
        <div class="fb lh180 cblack mt30 bbe0e0e0">
            发票寄送情况
        </div>
        <div class="h108 bae0e0e0 mt30 pl10">
            <div class="mt25">寄送方式：<span id="express_way"></span></div>
            <div class="mt30">寄送单号：<span id="express_no"></span></div>
        </div>
    </div>
    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 mt20 mb20 none" id="wancheng">
        <a href="${basePath}/inquiryreply/inquiryreply" class="fr cfc4d42 ba2_fc4d42 br3 dsb w166 h45 lh45 mr40 f18 none">再查一笔</a>
    </div>
    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20 mb20" >
    	<a href="javascript:void(0)" class="fr cfc4d42 ba2_fc4d42 br3 dsb w166 h45 lh45 mr40" id="update" onclick="update();">修改</a>
        <a href="javascript:void(0)" class="fr cfc4d42 ba2_fc4d42 br3 dsb w166 h45 lh45 mr40" onclick="toPay()" id="daizhifu">确认支付</a>
    </div>
    <div class="cb"></div>
<!--     <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20 mb20"> -->
<!--         <a href="#" class="fr cfc4d42 ba2_fc4d42 br3 dsb w166 h45 lh45 mr40">修改</a> -->
<!--     </div> -->
</div>
[@main.right /]
<script type="text/javascript">
/*
 * 页面初始化
 */
$(function(){
	loadData();
})
/*
 * 加载数据
 */
function loadData(){
	var no = '${no}';
	$.ajax({
		url:"${basePath}/inquiryreply/get",
		type:"POST",
		data:{"no":no},
		dataType:"json",
		success:function(data){
			if(data.response == 'success'){
				$("#start_date").text(data.startDate);
				$("#end_date").text(data.endDate);
				var data = data.data;
				var payState = data.pay_state;
				var result = data.result;
				if(payState==0){
			    	$("#zhuangtai").text("待支付");
			    	$("#daizhifu").removeClass("none");
			    	$("#wancheng").addClass("none");
			    	$("#yanpiao").addClass("none");
			    }else if(payState==1 && result== null){
			    	$("#zhuangtai").text("反馈中");
			    	$("#daizhifu").addClass("none");
			    	$("#wancheng").addClass("none");
			    }else if(payState==1 && result!= null){
			    	$("#zhuangtai").text("已完成");
			    	$("#daizhifu").addClass("none");
			    	$("#wancheng").removeClass("none");
			    	$("#result").text(data.result);
			    }else if(payState==2){
			    	$("#zhuangtai").text("已退款");
			    	$("#daizhifu").addClass("none");
			    	$("#wancheng").addClass("none");
			    	$("#result").text("订单取消");			
			    }
				$("#no").text(data.no);
				$("#draft_no").text(data.draft_no);
				$("#money").text(data.money+"万元");
				$("#pay_money").text(data.pay_money+"元");
				$("#drawer").text(data.drawer);
				$("#payee").text(data.payee);
				$("#bank").text(data.bank);
				$("#bank_no").text(data.bank_no);
				var needInvoice = data.need_invoice;
				if(needInvoice==0){
					$("#fapiao").removeClass("none");
					$("#send").removeClass("none");
					if(result==null){
						$("#send").addClass("none");
					}
					if(payState==1 && result!=null){
						$("#send").removeClass("none");
					}
					if(data.title_type == 0){
						$("#title_type").text("个人");
					}else{
						$("#title_type").text("企业");
					}
					$("#invoice_type").text(data.invoice_type);
					$("#content").text(data.content);
					if(data.send_way == 0){
						$("#send_way").text("平邮");
					}else{
						$("#send_way").text("顺丰到付");
					}
					if(data.express_way == 0){
						$("#express_way").text("平邮");
					}else{
						$("#express_way").text("顺丰到付");
					}
					$("#express_no").text(data.express_no);
					$("#address").text(data.address);
					initPCD(data.prov,data.city,data.dist);
				}else{
					$("#fapiao").addClass("none");
					$("#send").addClass("none");
				}
				if(data.edit_state != null){
					if(data.edit_state != 0){//不允许先修改
						$("#update").addClass("none");
					}
				}else{
					$("#update").addClass("none");
				}
			}
		}
	});
}

/**
 * 点击修改，跳转到保存的页面
 */
function update(){
	var no = $("#no").text();
	location.href = "${basePath}/inquiryreply/update?no="+no;
}

/*
 * 初始化省市区
 */
function initPCD(provId,cityId,distId){
	if(provId==null){
		return;
	}
	if(cityId==null){
		return;
	}
	if(distId==null){
		return;
	}
	$.ajax({
		url:"${basePath}/inquiryreply/init/region",
		type:"POST",
		data:{"provId":provId,"cityId":cityId,"distId":distId},
		dataType:"json",
		success:function(data){
			$("#PCD").text(data.prov+" "+data.city+" "+data.dist); 
		}
	});
}


/*
 * 去支付
 */
function toPay(){
	var no = $("#no").text();
//	location.href = "${basePath}/inquiryreply/paydo?deposit=20"+"&bnsNo="+no;
	window.open("${basePath}/inquirypay/bill99pay?deposit= 20 " + "&bnsNo=" +no);
}

/*
 * 去支付(暂时支付免费)
 */
function toPay1(){
	var no = $("#no").text();
	$.ajax({
		url:"${basePath}/inquiryreply/updatepay",
		type:"POST",
		data:{"no":no},
		dataType:"json",
		success:function(data){
			if(data.response == "success"){
				window.location.href="${basePath}/inquiryreply/success";
			}else{
				alert(data.msg);
			}	
		}
	});
}

	/**
	 *  点击支付成功
	 */
	 $("#paysuccess").click(function(){
	 	 var bnsno = $("#no").text();
	 	 $.ajax({
		 		type: "POST",
		      	url: "${basePath}/discountrecord/bill99query",
		      	data: {bnsno:bnsno},
		      	dataType:"json",
		      	success:function(data){
		      		if(data.data != ""){
		      			if(data.data[0].payResult == "10"){
							location.href = "${basePath}/inquiryreply/success";
						}else{
							location.href = "${basePath}/inquiryreply/detail?no="+bnsno;
						}
		      		}else{
		      			location.href = "${basePath}/inquiryreply/detail?no="+bnsno;
		      		}
		      	}
		 });
	 });
	 
	 /**
	 *  点击支付失败
	 */
	$("#payfail").click(function(){
		var bnsno = $("#no").text();
		$.ajax({
		 		type: "POST",
		      	url: "${basePath}/discountrecord/bill99query",
		      	data: {bnsno:bnsno},
		      	dataType:"json",
		      	success:function(data){
		      		if(data.data != ""){
		      			if(data.data[0].payResult == "10"){
							location.href = "${basePath}/inquiryreply/success";
						}else{
							location.href = "${basePath}/inquiryreply/detail?no="+bnsno;
						}
		      		}else{
		      			location.href = "${basePath}/inquiryreply/detail?no="+bnsno;
		      		}
		      	}
		 });
	 });
</script>
<!--foot-->
[@main.footer/]
[/@main.body]