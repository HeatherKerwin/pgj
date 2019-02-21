 [#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<style>
    *{box-sizing: border-box}
    .orderTo.active , .orderTo:hover{
        border: 1px solid #d43c33;
        color: #d43c33;
    }
</style>
<!--我的钱包-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
     [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 h688 bcwhite bae0e0e0">
        <div class="flex flex-direction-column w997 bcwhite">
            <div class="flex-row flex-justify-space-between bcfaf7f7 h34 lh34">
                <div class="ml10">我的库存</div>
            </div>
        </div>
        <div class="flex-row flex-direction-column pl20 pr20 pt20">
            <!--清单列表-->
            <div class="w btae0e0e0 blae0e0e0 brae0e0e0 bc">
                <ul class="w bbe0e0e0 bcf9f9f9 c7a7a7a tc h40 lh40 f14 oh">
                    <li class="fl bre0e0e0 w100">票据金额(万元)</li>
                    <li class="fl bre0e0e0 w150">承兑行/承兑企业</li>
                    <li class="fl bre0e0e0 w100">出票日</li>
                    <li class="fl bre0e0e0 w100">到期日</li>
                    <li class="fl bre0e0e0 w150">备注</li>
                    <li class="fl bre0e0e0 w90">带价</li>
                    <li class="fl bre0e0e0 w90">热度</li>
                    <li class="fl w170">操作</li>
                </ul>
                <!--列表内容  -->
                <div id="content">
                </div>
                <div class="flex-row flex-direction-row-reverse">
			    	<div id="pager"></div>
			    </div>
            </div>
        </div>
    </div>
    <div class="cb"></div>
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
                <!--修改-->
                <div class="flex-row flex-direction-column w tc lh30" id="changeWindow" style="display: none">
                    <div class="flex-row lh30 mt16">
                        <div class="flex-col-6">
                            <div class="flex-row">
                                <label class="w100 tl">票面金额(万元)</label>
                                <input type="number" id="update_allmoney" class="w160 h30 bae0e0e0 validate[custom[Okmoney]],validate[required],validate[custom[number]],validate[custom[money]]">
                            </div>
                        </div>
                        <div class="flex-col-6">
                            <div class="flex-row">
                                <label class="w100 tl pl20">承兑方</label>
                                <input type="text" id="update_bank" class="w160 h30 bae0e0e0">
                            </div>
                        </div>
                    </div>
                    <div class="flex-row lh30 mt16">
                        <div class="flex-col-6">
                            <div class="flex-row">
                                <label class="w100 tl">出票日</label>
                                <input type="text" class="w120 h30 bae0e0e0" id="startDate" readonly="readonly">
                                <span class="w30 h27 rili ml10 mt3"></span>
                            </div>
                        </div>
                        <div class="flex-col-6">
                            <div class="flex-row">
                                <label class="w100 tl pl20">到期日</label>
                                <input type="text" class="w120 h30 bae0e0e0" id="endDate" readonly="readonly">
                                <span class="w30 h27 rili ml10 mt3"></span>
                            </div>
                        </div>
                    </div>
                    <div class="flex-row lh30 mt16">
                        <div class="flex-col-6">
                            <div class="flex-row">
                                <label class="w100 tl">带价</label>
                                <input type="text" id="update_price" class="w160 h30 bae0e0e0">
                            </div>
                        </div>
                        <div class="flex-col-6">
                            <div class="flex-row">
                                <label class="w100 tl pl20">备注</label>
                                <input type="text" id="update_remarks" class="w160 h30 bae0e0e0">
                            </div>
                        </div>
                    </div>

                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt40 mb20">
                        <input type="button" value="保存" id="save" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 cp">
                    </div>
                </div>

                <!--确认库存票清单-->

                <!--拍票-->
                <div class="flex-row flex-direction-column w tc lh30" id="orderWindow" style="display: none">
                    <p class="mt30 f20">请选择改票的票据类型，并前往相应的票据类型出下单</p>
                    <div class="flex-row flex-justify-space-around lh30 tc mt20">
                        <div class="w150 h30 bae0e0e0 c727272 cp orderTo" data-url="/discountrecord/discount_yp1">银票下单</div>
                    </div>
                    <div class="flex-row flex-justify-space-around lh30 tc mt20">
                        <div class="w150 h30 bae0e0e0 c727272 cp orderTo" data-url="/discountrecord/discount_sp1">商票下单</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id ="inventoryId"/>
[@main.right /]
[@main.footer/]
[/@main.body]

<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="INVENTORYORDER">
{{#each list}}
	<ul class="w bcwhite tc f14 lh40">
		<li class="bbe0e0e0 h40">
			<div class="fl bre0e0e0 w100">{{toAllmoney allmoney}}</div>
			<div class="fl bre0e0e0 w150 oh wsn toe" title="{{bank}}">{{toBank bank}}</div>
			<div class="fl bre0e0e0 w100">{{formatDate printtime}}</div>
			<div class="fl bre0e0e0 w100">{{formatDate endtime}}</div>
			<div class="fl bre0e0e0 w150 oh wsn toe" title="{{remarks}}">{{toRemarks remarks}}</div>
			<div class="fl bre0e0e0 w90 oh wsn toe">{{toPrice price}}</div>
			<div class="fl bre0e0e0 w90 cd43c33">{{toAmount amount}}</div>
			<div class="fl w170">
				<div class="c00a5f2 ml15 fl cp changeBtn" onclick="update({{id}})">修改</div>
				<div class="c00a5f2 ml10 fl cp deleteBtn" onclick="del({{id}})">删除</div>
				<div class="w60 h24 lh24 ml15 mt8 bad43c33 cd43c33 br3 cp fl orderBtn" onclick="orderBtn({{id}});">去下单</div>
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

	var memberId = '${member.id}';
	$(document).ready(function(){
		search();
	});
	
	/**
	* 加载数据
	*/
	function search(){
		if(memberId == null || memberId == "")return;
		$("#pager").html("");
		var data = {memberId:memberId};
		
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	    	url: "${bootAppPath}/inventory/page",
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
	* 用户修改订单
	*/
	function update(id){
		$("#inventoryId").val(id);
	    var url = '${bootAppPath}/inventory/get';
		var params = {id:id};
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			if (data.response == 'success') {
				$("#title").html("修改");
			    $("#maskWindow").show(500);
			    $("#changeWindow").show(500);
			    var inventory = data.data;
			    $("#update_allmoney").val(inventory.allmoney);
			    $("#update_bank").val(inventory.bank);
			    if(inventory.printtime!=null){
			    	$("#startDate").val(new Date(inventory.printtime).format("yyyy-MM-dd"));
			    }
			    if(inventory.endtime!=null){
			    	$("#endDate").val(new Date(inventory.endtime).format("yyyy-MM-dd"));
			    }
			    $("#update_remarks").val(inventory.remarks);
			    $("#update_price").val(inventory.price);
			}else{
				alert(data.msg);
			}
		}
	};
	
	/**
	* 删除订单
	*/
	function del(id){
		if (confirm("确定要删除该订单么？")){
			var url = '${bootAppPath}/inventory/del';
			var params = {id:id};
			var res = bootPost(url,params);
			if(res != null){
				var data = res.data;
				if (data.response == 'success') {
					alert("删除成功");
					search();
				}else{
					alert(data.msg);
				}
			}
		}
	};
	
	/**
	* 用户修改保存
	*/
	$("#save").click(function(){
		if(!$("#update_allmoney").validationEngine("validate")){
			$("#update_allmoney").focus();
			return ;
		};
		
		var id = $("#inventoryId").val();
		var allmoney = $("#update_allmoney").val();
	    var bank = $("#update_bank").val();
	    var printtime = $("#startDate").val();
	    var endtime = $("#endDate").val();
	    var remarks = $("#update_remarks").val();
	    var price = $("#update_price").val();
	    var url = '${bootAppPath}/inventory/update';
		var params = {memberId:memberId,id:id,allmoney:allmoney,bank:bank,printtime:printtime,endtime:endtime,remarks:remarks,price:price};
		
		var res = bootPost(url,params);
		if(res != null){
			var data = res.data;
			if (data.response == 'success') {
				alert("修改成功");
				$("#title").html("");
			    $("#maskWindow").hide();
			    $("#changeWindow").hide();
			    $("#orderWindow").hide();
			    search();
			}else{
				alert(data.msg);
			}
		}
	});
	
	/**
	* 去下单
	*/
	function orderBtn(id){
	    $("#title").html("修改");
	    $("#maskWindow").show(500);
	    $("#orderWindow").show(500);
	    
	    $("#inventoryId").val(id);
	};
	
	/**
	* 用户选择下单
	*/
	$(".orderTo").click(function(){
		var inventoryId = $("#inventoryId").val();
		var url = $(this).attr("data-url");
		discountAuthentication(url,inventoryId);
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
		istime : false,
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
		istime : false,
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
	    $("#changeWindow").hide();
	    $("#orderWindow").hide();
	});
</script>
