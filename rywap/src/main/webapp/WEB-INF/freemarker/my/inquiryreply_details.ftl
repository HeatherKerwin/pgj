[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='3'/]

<link rel="stylesheet" href="${staticPath}/css/rywap/list.css"/>

<!--容器-->
<div class="wrapper">
    <ul class="List">
        <li>
            <div class="listLabel">票号：</div>
            <div class="listData" id="draftNo"></div>
        </li>
        <li>
            <div class="listLabel">金额：</div>
            <div class="listData"><span id="money"></span>万元</div>
        </li>
        <li>
            <div class="listLabel">出票人：</div>
            <div class="listData" id="drawer"></div>
        </li>
        <li>
            <div class="listLabel">收款人：</div>
            <div class="listData" id="payee"></div>
        </li>
        <li>
            <div class="listLabel">承兑行全称：</div>
            <div class="listData" id="bank"></div>
        </li>
        <li>
            <div class="listLabel">承兑行号：</div>
            <div class="listData" id="bankNo"></div>
        </li>
        <li>
            <div class="listLabel">出票日期：</div>
            <div class="listData" id="startDate"></div>
        </li>
        <li>
            <div class="listLabel">到期日期：</div>
            <div class="listData" id="endDate"></div>
        </li>
        <li>
            <div class="listLabel">查询查复：</div>
            <div class="listData" id="jyh"></div>
        </li>
        <li>
            <div class="listLabel">下单日期：</div>
            <div class="listData" id="frit"></div>
        </li>
        <li>
            <div class="listLabel">订单状态：</div>
            <div class="listData" id="type"></div>
        </li>
        <li class="h90">
            <div class="listLabel">验票结果：</div>
            <div class="listTextarea" id="jieguo"></div>
        </li>
    </ul>
    <a class="listBtn" href="javascript:viod()">
        <input onclick="inquiryreply();" type="button" value="再查一笔" />
    </a>
</div>    
<input type="hidden" id="inquiryId" value="${id}">
<script type="text/javascript">
/**
*  加载数据
*/
$(function(){
	loadDate();
});

/**
*	初始化数据
*/
function loadDate(){
	var no = $("#inquiryId").val();
	$.ajax({
		url:"${basePath}/wap/inquiryreply/get",
		type:"post",
		data:{id:no},
		dataType:"json",
		success:function(data){
			$("#startDate").html(data.startDate);
			$("#endDate").html(data.endDate);
			var data = data.data;
			$("#draftNo").text(data.draft_no);
			$("#money").text(data.money);
			$("#drawer").text(data.drawer);
			$("#payee").text(data.payee);
			$("#bank").text(data.bank);
			$("#bankNo").text(data.bank_no);
			$("#jyh").text(data.no);
			var payState = data.pay_state;
			var result = data.result;
			
			if(data.edit_state == 0){
				$(".update").removeClass("none");
				$(".update").val(data.no);
			}
			
			if(payState==0){
				$("#type").text("待支付");
		    }else if(payState==1 && result== null){
		    	$("#type").text("反馈中");
		    }else if(payState==1 && result!= null){
		    	$("#type").text("已完成");
		    }else if(payState==2){
		    	$("#type").text("已退款");
		    }
			$("#frit").text(data.create_time);
			$("#jieguo").text(result);
        }
	 });
};
</script>

[@main.footer/]
[/@main.body]