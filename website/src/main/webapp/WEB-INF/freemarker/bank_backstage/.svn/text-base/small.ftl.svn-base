[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='小票审核']
<link href="${cssPath}/website/daxiaopiao-190109/reste.css" rel="stylesheet">
<link href="${pluPath}/data/need/laydate.css" rel="stylesheet">
<link href="${pluPath}/data/skins/yahui/laydate.css" rel="stylesheet" id="LayDateSkin">
<link href="${cssPath}/website/daxiaopiao-190109/index.css" rel="stylesheet">
<link href="${cssPath}/yonyou/mask.css" rel="stylesheet">

<script type="text/javascript" src="${comPath}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>

	<!-- backstage -->
	<div class="backstage">
		<!-- menu -->
		<div class="backstageMenu">
			<div class="menu-con clearfix">
				<!-- logo -->
				<a class="menu-logo" href="${basePath}/index">
					<img src="https://img.utiexian.com/website/daxiaopiao-img/logo.png" alt="logo">
				</a>
				<!-- logo END -->
	
				<!-- tab -->
				<div class="backstageTab">
					<ul class="clearfix backstageTab">
						<li>
							<label for="backstage1">
								<input name="backstageTab" type="radio" value="1" id="backstage1">企业预审
							</label>
						</li>
						<li>
							<label for="backstage2">
								<input name="backstageTab" type="radio" value="2" id="backstage2">大票审核
							</label>
						</li>
						<li class="actived">
							<label for="backstage3">
								<input name="backstageTab" type="radio" value="3" id="backstage3">小票审核
							</label>
						</li>
					</ul>
				</div>
		        <!-- tab END -->
			</div>
	    </div>
	    <!-- menu ebd -->
	
		<!-- form -->
	    <div class="backstageForm">
			<!-- option -->
			<div class="form-option clearfix">
				<div class="option-con">
					<label for="">票面金额</label>
					<input type="text" id="query_money" class="inp-warning">
				</div>
				<div class="option-con">
					<label for="">承兑行</label>
					<input type="text" id="query_bank" class="inp-warning">
				</div>
				<div class="option-con">
					<label for="">审核状态</label>
					<select id="query_state" class="select select148 w200 h30 pl10">
						<option value="">请选择</option>
						<option value="PENDING">待审核</option>
						<option value="FAILED">不可置换</option>
						<option value="SUCCESS">可置换</option>
					</select>
				</div>
				<div class="option-con">
					<label for="">到期日</label>
					<input type="text" id="first" class="inp-warning" readonly>
					<span class="riliIcon">
						<img src="https://img.utiexian.com/common/others/rili.png" alt="">
					</span>
	          		<span>-</span>
					<input type="text" id="end" class="inp-warning" readonly>
					<span class="riliIcon">
						<img src="https://img.utiexian.com/common/others/rili.png" alt="">
					</span>
				</div>
				<div class="option-con">
					<label for="">企业状态</label>
					<select id="query_check_state" class="select select148 w200 h30 pl10">
						<option value="">请选择</option>
						<option value="NONE">未上传 </option>
						<option value="PENDING">待审核</option>
						<option value="CHECK_FAILED">预审失败</option>
						<option value="AUTH">待开户</option>
						<option value="AUTH_FAILED">开户失败</option>
						<option value="SUCCESS">成功</option>
					</select>
				</div>
				<div class="option-con">
					<input type="button" class="inp-warning" onclick="loadDate();" style="color:white;background:red;" readonly value="搜索">
				</div>
			</div>
			<!-- option end -->

			<!-- list -->
			<!-- 小票审核 xpshList -->
			<div class="backtageList xpshList">
				<div class="list-label clearfix">
					<div>联系人</div>
					<div>联系方式</div>
					<div>总金额</div>
					<div>承兑行</div>
					<div>置换日期</div>
					<div>最晚到期日</div>
					<div>张数</div>
					<div>换票状态</div>
					<div>企业状态</div>
					<div>操作</div>
				</div>
				<!-- con -->
				<div class="list-con" id="content">
					
				</div>
				<!-- con end -->
			</div>
			<!-- 小票审核 end -->
			<!-- list end -->
			<!-- pages -->
			<div class="pages" id="pager"></div>
			<!-- pages end -->
		</div>
		<!-- form end -->
	</div>
	<!-- backstage -->

	<!-- mask -->
	<div class="maskWindow" style="display: none;">
		<div class="maskBg"></div>
		<div class="maskWrap maskWrapSmall">
			<!-- maskTop -->
			<div class="maskTop">
				<div class="maskTitle"></div>
				<a class="downloadAll" id="download-btn">打包下载</a>
				<button class="maskCloseIcon" onclick="closeMask()">×</button>
			</div>
			<!-- maskTop -->

			<!-- maskCon -->
			<div class="maskCon">
				<div class="maskDiv">
					<!-- 小票换大票审核 -->
					<div class="backstageMask" id="xiaopiaoMask" style="display: none;">
						<!-- 企业信息 -->
						<div class="qiyeForm">
							<div class="qiyeRow">
								<div class="qiyeRow-child">
									<label>企业名称</label>
									<div class="qiyeFormInp">
										<input type="text" id="company" value="--" class="inp-warning">
									</div>
								</div>
								<div class="qiyeRow-child">
									<label>企业状态</label>
									<div class="qiyeFormInp">
										<input type="text" id="check_state" value="--" class="inp-warning">
									</div>
								</div>
							</div>
							<div class="qiyeRow clearfix">
								<div class="qiyeRow-child">
									<label>联系人</label>
									<div class="qiyeFormInp">
										<input type="text" id="contactName" value="--" class="inp-warning">
									</div>
								</div>
								<div class="qiyeRow-child">
									<label>联系方式</label>
									<div class="qiyeFormInp">
										<input type="text" id="contactMobile" value="--" class="inp-warning">
									</div>
								</div>
							</div>
						</div>
						<!-- 企业信息 end -->

						<!-- 票面信息 -->
						<div class="piaoForm">
							<div class="piaoLabel clearfix">
								<div>票面金额</div>
								<div>承兑行</div>
								<div>置换日期</div>
								<div>到期日</div>
								<div>剩余天数</div>
								<div>票面图片</div>
							</div>
							<div class="piaoCon" id="content2">
								
							</div>
						</div>
						<!-- 票面信息 end -->

						<p><span>换票需求：</span><span id="exchangeDemand"></span></p>

						<div class="downloadMenu">
							<label>贸易合同</label>
							<p id="contractUrl">
							</p>
						</div>

						<!-- 操作 -->
						<div class="maskOperation" style="display: none">
							<div class="firstOperation">
								<button class="successBtn" value="审核通过" onclick="success();">审核通过</button>
								<button class="failBtn" value="审核不通过" onclick="failed();">审核不通过</button>
							</div>
						</div>
						<!-- 操作 end -->
					</div>
					<!-- 小票换大票审核 end -->
				</div>
			</div>
			<!-- maskCon end -->
		</div>
	</div>
	<!-- mask end -->
<input type="hidden" id="draftId" />
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/javascript" src="${basePath}/js/layer/layer.js"></script>
<script type="text/x-handlebars-template" id="SMALLLIST">
{{#each list}}
	<div class="list-row clearfix">
		<div>{{toContactName contact_name}}</div>
		<div>{{mobile}}</div>
		<div>{{toMoney detail}}</div>
		<div>{{toBank detail}}</div>
		<div>{{formatDate exchange_date}}</div>
		<div>{{formatDate max_endtime}}</div>
		<div>{{toNumber detail}}</div>
		<div>{{toState state}}</div>
		<div>{{toCheckState check_state}}</div>
		<div onclick="openMask('{{id}}')" class="text-primary">查看详情</div>
	</div>
{{/each}}
</script>
<script type="text/x-handlebars-template" id="SMALLINFOLIST">
{{#each detail}}
	<div class="piaoConRow piaoList clearfix">
		<div>{{money}}</div>
		<div>{{bank}}</div>
		<div>{{formatDate printDate}}</div>
		<div>{{formatDate endDate}}</div>
		<div>{{toDays endDate}}</div>
		<div><a href="${imagePath}{{draftUrl}}" target="_blank">查看</a></div>
	</div>
{{/each}}
</script>
<script type="text/javascript">
/* 
 * 联系人名称
 */
Handlebars.registerHelper('toContactName', function(contact_name, options) {
	if(contact_name!=null){
		return contact_name;
	}else{
		return "--";
	}
});

/**
*	时间格式化
*/
Handlebars.registerHelper('formatDate', function(date, options) {
    if(date!=null){
    	date = date.replace(/-/g, "/");
        var d = new Date(date);
        return d.format('yyyy-MM-dd');
    }else{
        return "--";
    }
});

/* 
 * 订单金额
 */
Handlebars.registerHelper('toMoney', function(detail, options) {
	if(detail!=null){
		var allmoney=0;
		for (var i = 0; i < detail.length; i++) {
			allmoney += detail[i].money;
		}
		return allmoney;
	}else{
		return "--";
	}
});

/* 
 * 展示承兑行
 */
Handlebars.registerHelper('toBank', function(detail, options) {
	if(detail.length>0){
		return detail[0].bank;
	}else{
		return "--";
	}
});

/* 
 * 展示到期日
 */
Handlebars.registerHelper('toEndDate', function(detail, options) {
	if(detail.length>0){
		return detail[0].endDate;
	}else{
		return "--";
	}
});

/* 
 * 获取张数
 */
Handlebars.registerHelper('toNumber', function(detail, options) {
	if(detail.length>0){
		return detail.length;
	}else{
		return "--";
	}
});

/* 
 * 订单状态
 */
Handlebars.registerHelper('toState', function(state, options) {
	if(state == "PENDING"){
		return "待审核";
	}else if(state == "FAILED"){
		return "不可置换";
	}else if(state == "SUCCESS"){
		return "可置换";
	}
});

/* 
 * 企业状态
 */
Handlebars.registerHelper('toCheckState', function(state, options) {
	if(state == "NONE"){
		return "未上传";
	}else if(state == "PENDING"){
		return "待审核";
	}else if(state == "AUTH"){
		return "初审通过,待开户";
	}else if(state == "CHECK_FAILED"){
		return "预审失败";
	}else if(state == "AUTH_FAILED"){
		return "开户失败";
	}else if(state == "SUCCESS"){
		return "开户成功";
	}
});


/* 
 * 获取剩余天数
 */
Handlebars.registerHelper('toDays', function(endDate, options) {
	if(endDate!=null||endDate!=""){
		return surplusDay(endDate);
	}else{
		return "--";
	}
});
	var memberId = '${member.id}';
    $(function(){
    	loadDate();
	});
    
    /**
	* 页面初始化 加载数据
	*/
	function loadDate(){
    	layer.load(2);
		var data = {operateId:memberId,type:"S2B"};
		var money = $("#query_money").val();
		var bank = $("#query_bank").val();
		var state = $("#query_state").val();
		var first = $("#first").val();
		var end = $("#end").val();
		var check_state = $("#query_check_state").val();
		if(money!=null&&money!=""){
			data.money= money;
		}
		if(bank!=null&&bank!=""){
			data.bank= bank;
		}
		if(state!=null&&state!=""){
			data.state= state;
		}
		if(check_state!=null&&check_state!=""){
			data.checkState= check_state;
		}
		if(first!=null&&first!=""){
			data.first= first;
		}
		if(end!=null&&end!=""){
			data.end= end;
		}
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/draftexchange/page/info/forbank",
	        pageSize:5,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	layer.closeAll();
	        	console.log(data);
        		var source = $("#SMALLLIST").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data.data);
	            $("#content").html(html);
	            
	        },complete: function(){
	        },error:function(data){
	        	layer.closeAll();
	        	layer.alert("获取信息失败");
	        }
	    });
	};

    $("input[name='backstageTab']").click(function () {
    	var index = $(this).val();
        if(index==1){
        	window.location.href = "${basePath}/bank/sizeticket/backstage/enterprise";
        }else if(index==2){
        	window.location.href = "${basePath}/bank/sizeticket/backstage/big";
        }else if(index==3){
        	window.location.href = "${basePath}/bank/sizeticket/backstage/small";
        }
    });

    //日历
    !function(){
        laydate.skin('yahui');//切换皮肤，请查看skins下面皮肤库
    }();
	var first = {
		elem: '#first',
		format: 'YYYY-MM-DD',
		max: '2099-06-16', //最大日期
		choose: function(datas){
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
		}
	};
	
	var end = {
		elem: '#end',
		format: 'YYYY-MM-DD',
		max: '2099-06-16', //最大日期
		choose: function(datas){
			first.max = datas;
		}
	};
	
	laydate(first);
	laydate(end);
	
	/**
 	* 打开弹窗
 	*/
    function openMask(id){
        layer.load(2);
		$.ajax({
			url:"${bootAppPath}/draftexchange/get/info/forbank",
			type:"POST",
			data:{operateId:memberId,id:id},
			dataType:"json",
			success:function(data){
				$(".maskWindow").fadeIn(1000);
		        $("#xiaopiaoMask").fadeIn(1000);
		        $(".maskTitle").html("小票换大票审核");
				layer.closeAll();
				console.log(data);
				if(data.data.response == 'success'){
					$("#download-btn").attr("href","${bootAppPath}/out/draftexchange/"+id+"/down.zip");
					
					var draftExchange = data.data.data.draftExchange;
					if(draftExchange!=null){
						if(draftExchange.state=="PENDING"){
							$(".maskOperation").fadeIn(100);
						}
						$("#draftId").val(draftExchange.id);
						$("#exchangeDate").html(draftExchange.exchange_date);
						$("#exchangeDemand").html(draftExchange.exchange_demand);
						
						var contractUrl = draftExchange.contract_url;
						var contractUrlArry= new Array(); //定义一数组 
						contractUrlArry = contractUrl.split(","); //字符分割 
						$("#contractUrl").append("<span>贸易合同共"+contractUrlArry.length+"份，如需查看，请下载！</span>");
						
						if(draftExchange.check_state == "PENDING"){
						     $("#check_state").val("待审核！");
						}else if(draftExchange.check_state == "AUTH"){
							$("#check_state").val("初审通过，等待开户！");
						}else if(draftExchange.check_state == "CHECK_FAILED"){
					        $("#check_state").val("初审未通过！");
						}else if(draftExchange.check_state == "SUCCESS"){
						     $("#check_state").val("开户成功！");
						}else if(draftExchange.check_state == "AUTH_FAILED"){
						     $("#check_state").val("开户失败！");
						}else if(draftExchange.check_state == "NONE"){
						     $("#check_state").val("未上传预审！");
						}
						$("#company").val(draftExchange.company);
						$("#contactName").val(draftExchange.contact_name);
						$("#contactMobile").val(draftExchange.mobile);
					}
					
					if(data.data.data!=null&&data.data.data.detail.length>0){
						
						var source = $("#SMALLINFOLIST").html();
			            var template = Handlebars.compile(source);
			            var html = template(data.data.data);
			            $("#content2").html(html);
					}
				}else{
					layer.alert(data.data.msg);
					layer.closeAll();
				}
			},error:function(json){
				layer.alert("获取企业信息失败！");
				layer.closeAll();
			}
		});
    };

    function closeMask(){//关闭弹窗
        $(".maskWindow").fadeOut(1000);
        $("#xiaopiaoMask").fadeOut(1000);
        $(".maskOperation").fadeOut(100);
        clean();
    };
    
    /**
    * 清空
    */
    function clean(){
    	$("#money").html("");
		$("#bank").html("");
		$("#endDate").html("");
		$("#draftUrl").attr("href","javascript:void(0);");
		$("#days").html("");
		
		$("#draftId").val("");
		$("#exchangeDate").html("");
		$("#exchangeDemand").html("");
		$("#contractUrl").html("");
		
		$("#check_state").val("--");
		$("#company").val("--");
		$("#contactName").val("--");
		$("#contactMobile").val("--");
    }
    
    /**
     * 审核不通过
     */
     function failed(){
     	layer.load(2);
     	var id = $("#draftId").val();
     	$.ajax({
 			url:"${bootAppPath}/draftexchange/update/failed",
 			type:"POST",
 			data:{operateId:memberId,id:id},
 			dataType:"json",
 			success:function(data){
 				layer.closeAll();
 				console.log(data);
 				if(data.data.response == 'success'){
 					closeMask();
 					location.reload();
 				}else{
 					layer.closeAll();
 					layer.alert(data.data.msg);
 				}
 			},error:function(json){
 				layer.closeAll();
 				layer.alert("审核信息失败！");
 			}
 		});
     }
     
     /**
 	* 审核通过
 	*/
 	function success(){
 		layer.load(2);
      	var id = $("#draftId").val();
      	$.ajax({
 			url:"${bootAppPath}/draftexchange/update/success",
 			type:"POST",
 			data:{operateId:memberId,id:id},
 			dataType:"json",
 			success:function(data){
 				layer.closeAll();
 				console.log(data);
 				if(data.data.response == 'success'){
 					closeMask();
 					location.reload();
 				}else{
 					layer.closeAll();
 					layer.alert(data.data.msg);
 				}
 			},error:function(json){
 				layer.closeAll();
 				layer.alert("审核信息失败！");
 			}
 		});
      }
    
    /**
     * 获取剩余天数
     */
    function surplusDay(end){
    	 var date = new Date();//开始时间
         var seperator1 = "-";
         var year = date.getFullYear();
         var month = date.getMonth() + 1;
         var strDate = date.getDate();
         if (month >= 1 && month <= 9) {
             month = "0" + month;
         }
         if (strDate >= 0 && strDate <= 9) {
             strDate = "0" + strDate;
         }
         var start = year + seperator1 + month + seperator1 + strDate;
    	
    	var date2 = new Date(start);//开始时间
    	var date3 = new Date(end);//结束时间

    	var date4=date3.getTime()-date2.getTime(); //时间差的毫秒数
    	//计算出相差天数
    	var days=Math.floor(date4/(24*3600*1000));
    	if(days==null||days==""){
    		days=0;
    	}
    	return days;
    }
</script>
[/@main.body]