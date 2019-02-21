[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='企业预审-审核中']
<link href="${cssPath}/website/daxiaopiao-190109/reste.css" rel="stylesheet">
<link href="${pluPath}/data/need/laydate.css" rel="stylesheet">
<link href="${pluPath}/data/skins/yahui/laydate.css" rel="stylesheet" id="LayDateSkin">
<link href="${cssPath}/website/daxiaopiao-190109/index.css" rel="stylesheet">
<link href="${cssPath}/yonyou/mask.css" rel="stylesheet">

<script type="text/javascript" src="${comPath}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<body>
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
						<li class="actived">
							<label for="backstage1">
								<input name="backstageTab" type="radio" value="1" id="backstage1">企业预审
							</label>
						</li>
						<li>
							<label for="backstage2">
								<input name="backstageTab" type="radio" value="2" id="backstage2">大票审核
							</label>
						</li>
						<li>
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
					<label for="">企业名称：</label>
					<input type="text" id="company" class="inp-warning">
				</div>
				<div class="option-con">
					<label for="">联系人</label>
					<input type="text" id="name" class="inp-warning">
				</div>
				<div class="option-con">
					<label for="">审核状态</label>
					<select id="state" class="select select148 w200 h30 pl10">
						<option value="">请选择</option>
						<option value="PENDING">待审核</option>
						<option value="CHECK_FAILED">预审失败</option>
						<option value="AUTH">待开户</option>
						<option value="AUTH_FAILED">开户失败</option>
						<option value="SUCCESS">成功</option>
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
					<input type="button" class="inp-warning" onclick="loadDate();" style="color:white;background:red;" readonly value="搜索">
				</div>
			</div>
			<!-- option end -->
	
			<!-- list -->
			<!-- 企业预审 qyysList -->
			<div class="backtageList qyysList" >
				<div class="list-label clearfix">
		          <div>企业名称</div>
		          <div>联系人</div>
		          <div>联系方式</div>
		          <div>审核状态</div>
		          <div>申请时间</div>
		          <div>操作</div>
		        </div>
	
				<!-- con -->
				<div class="list-con" id="content">
					
				</div>
	        	<!-- con end -->
			</div>
			<!-- 企业预审 end -->
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
					<!-- 企业预审 -->
					<div class="backstageMask" id="qyysMask" style="display: none;">
						<!-- 企业信息 -->
						<div class="qyysMaskRow">
							<div class="qiyeImgRow clearfix">
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="blicUrl">
									<p>企业营业执照</p>
								</div>
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="lepUrl">
									<p>法人代表身份证</p>
								</div>
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="occUrl">
									<p>开户许可证</p>
								</div>
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="blicTrcUrl">
									<p>机构信用代码证</p>
								</div>
							</div>
						</div>
						<!-- 企业信息 end -->
			
						<div class="qyysMaskRow">
							<div class="qiyeLabel">财务报告</div>
							<div class="qiyeImgRow clearfix">
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="financialReportUrl0">
								</div>
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="financialReportUrl1">
		                		</div>
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="financialReportUrl2">
								</div>
							</div>
						</div>
		
						<div class="qyysMaskRow">
							<div class="qiyeLabel">即期财务月报</div>
							<div class="qiyeImgRow clearfix">
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="financialReportMonthUrl0">
								</div>
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="financialReportMonthUrl1">
								</div>          
								<div class="qiyeImgItem">
									<img src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png" id="financialReportMonthUrl2">
								</div>
							</div>
						</div>
		
						<div class="qyysMaskRow">
							<div class="qiyeLabel">验资报告<span class="" id="capitalReportUrl">未上传</span></div>
						</div>
		
						<div class="qyysMaskRow">
							<div class="qiyeLabel">公司章程<span class="" id="coUrl">未上传</span></div>
						</div>
		
						<!-- 操作 -->
						<div class="maskOperation">
							<div class="firstOperation" id="qiyeOperation1" style="display: none;">
								<button name="successBtn" class="successBtn" onclick="auth()" value="初审通过">初审通过</button>
								<button name="failBtn" class="failBtn" onclick="checkfailed()" value="初审不通过">初审不通过</button>
							</div>
		
		
							<div class="nextOperation" id="qiyeOperation2" style="display: none;">
								<button class="successBtn" onclick="success();">开户成功</button>
								<button class="failBtn" onclick="authfailed();">开户失败</button>
							</div>
						</div>
		            	<!-- 操作 end -->
					</div>
					<!-- 企业预审 end -->       
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
<script type="text/x-handlebars-template" id="COMPANYLIST">
{{#each list}}
	<div class="list-row clearfix">
		<div>{{company}}</div>
		<div>{{contactName}}</div>
		<div>{{contactMobile}}</div>
		<div>{{toState state}}</div>
		<div>{{formatDate createTime}}</div>
		<div onclick="openMask('{{id}}')" class="text-primary">查看详情</div>
	</div>
{{/each}}
</script>

<script type="text/javascript">
/* 
 * 审核状态
 */
Handlebars.registerHelper('toState', function(state, options) {
	if(state == "PENDING"){
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

	var memberId = '${member.id}';
	$(function(){
		loadDate();
    });
	
	/**
	* 页面初始化 加载数据
	*/
	function loadDate(){
		var data = {operateId:memberId};
		var company = $("#company").val();
		var name = $("#name").val();
		var state = $("#state").val();
		var first = $("#first").val();
		var end = $("#end").val();
		if(company!=null&&company!=""){
			data.company= company;
		}
		if(name!=null&&name!=""){
			data.contactName= name;
		}
		if(state!=null&&state!=""){
			data.state= state;
		}
		if(first!=null&&first!=""){
			data.first= first;
		}
		if(end!=null&&end!=""){
			data.end= end;
		}
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/draftexchangecheck/page",
	        pageSize:5,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
        		var source = $("#COMPANYLIST").html();
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
	* tab切换（企业预审，大票审核，小跑审核）
	*/
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
    
	function openMask(id){//打开弹窗
		layer.load(2);
		$.ajax({
			url:"${bootAppPath}/draftexchangecheck/get",
			type:"POST",
			data:{operateId:memberId,id:id},
			dataType:"json",
			success:function(data){
				$(".maskWindow").fadeIn(100);
				$("#qyysMask").fadeIn(100);
				layer.closeAll();
				console.log(data);
				if(data.data.response == 'success'){
					$("#download-btn").attr("href","${bootAppPath}/out/draftexchangecheck/"+id+"/down.zip");
					var data = data.data.data;
					$("#draftId").val(data.id);
					var zhuangti = "企业信息<span class='";
					if(data.state == "PENDING"){
						zhuangti += "text-danger'>（待审核）</span>";
						$("#qiyeOperation1").fadeIn();
					}else if(data.state == "AUTH"){
						zhuangti += "text-success'>（初审通过,待开户）</span>";
						$("#qiyeOperation2").fadeIn();
					}else if(data.state == "CHECK_FAILED"){
						zhuangti += "text-success'>（预审失败）</span>";
					}else if(data.state == "AUTH_FAILED"){
						zhuangti += "text-danger'>（开户失败）</span>";
					}else if(data.state == "SUCCESS"){
						zhuangti += "text-success'>（开户成功）</span>";
					}
					$(".maskTitle").html(zhuangti);
					
					if(data.capitalReportUrl!=null&&data.capitalReportUrl!=""){
						$("#capitalReportUrl").html("已上传");
						$("#capitalReportUrl").addClass('text-success');
					}else{
						$("#capitalReportUrl").html("未上传");
						$("#capitalReportUrl").addClass('text-danger');
					}
					
					if(data.coUrl!=null&&data.coUrl!=""){
						$("#coUrl").html("已上传");
						$("#coUrl").addClass('text-success');
					}else{
						$("#coUrl").html("未上传");
						$("#coUrl").addClass('text-danger');
					}
					
					$("#blicUrl").attr("src","${imagePath}"+data.blicUrl);
					$("#lepUrl").attr("src","${imagePath}"+data.lepUrl);
					$("#occUrl").attr("src","${imagePath}"+data.occUrl);
					$("#blicTrcUrl").attr("src","${imagePath}"+data.blicTrcUrl);
					
					var financialReportUrl = data.financialReportUrl;
					var financialReportUrlArry= new Array(); //定义一数组 
					financialReportUrlArry = financialReportUrl.split(","); //字符分割 
					for (i=0;i<financialReportUrlArry.length;i++){ 
						$("#financialReportUrl"+[i]).attr("src","${imagePath}"+financialReportUrlArry[i]);
					}
					
					var financialReportMonthUrl = data.financialReportMonthUrl;
					var financialReportMonthUrlArry= new Array(); //定义一数组 
					financialReportMonthUrlArry = financialReportMonthUrl.split(","); //字符分割 
					for (i=0;i<financialReportMonthUrlArry.length;i++){ 
						$("#financialReportMonthUrl"+[i]).attr("src","${imagePath}"+financialReportMonthUrlArry[i]);
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
        $(".maskWindow").fadeOut(100);
        $("#qyysMask").fadeOut(100);
    };
	
    /**
    * 初审不通过
    */
    function checkfailed(){
    	var id = $("#draftId").val();
    	layer.load(2);
    	$.ajax({
			url:"${bootAppPath}/draftexchangecheck/update/checkfailed",
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
				layer.alert("企业信息审核失败！");
				layer.closeAll();
			}
		});
    }
    
	/**
	* 初审通过
	*/
	function auth(){
		var id = $("#draftId").val();
    	layer.load(2);
    	$.ajax({
			url:"${bootAppPath}/draftexchangecheck/update/auth",
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
				layer.alert("企业信息审核失败！");
				layer.closeAll();
			}
		});
	}
	
	/**
	* 初审通过
	*/
	function success(){
		var id = $("#draftId").val();
    	layer.load(2);
    	$.ajax({
			url:"${bootAppPath}/draftexchangecheck/update/success",
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
				layer.alert("企业信息审核失败！");
				layer.closeAll();
			}
		});
	}
	
	/**
	* 初审不通过
	*/
	function authfailed(){
		var id = $("#draftId").val();
    	layer.load(2);
    	$.ajax({
			url:"${bootAppPath}/draftexchangecheck/update/authfailed",
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
				layer.alert("企业信息审核失败！");
				layer.closeAll();
			}
		});
	}
</script>
[/@main.body]