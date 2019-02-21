[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='企业预审-审核中']
[@main.header currentmenu='1' topindex='2'/]

<link href="${cssPath}/website/daxiaopiao-190109/reste.css" rel="stylesheet">
<link href="${pluPath}/data/need/laydate.css" rel="stylesheet">
<link href="${pluPath}/data/skins/yahui/laydate.css" rel="stylesheet" id="LayDateSkin">
<link href="${cssPath}/website/daxiaopiao-190109/common.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/reviewTab.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/review.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/review-qiye.css" rel="stylesheet">

<script type="text/javascript" src="${comPath}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<body>
	<div class="reviewWarp">
		<!-- 切换 -->
		<div class="reviewTab clearfix">
			<div>
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/shehe.png" alt="企业预审">
				<p>企业预审</p>
			</div>
			<div onclick="bigSmall();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/dapiao.png" alt="大票换小票">
				<p>大票换小票</p>
			</div>
			<div onclick="smallBig();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/xiaopiao.png" alt="小票换大票">
				<p>小票换大票</p>
			</div>
			<div onclick="order();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/dingdan.png" alt="换票订单">
				<p>换票订单</p>
			</div>
		</div>
		<!-- 切换 end -->

		<!-- content -->
		<div class="reviewCon">
			<!-- 企业预审 -->
			<div>
				<!-- stepIndex=2 审核中 -->
				<div id="reviewing" class="reviewing" style="display: black;">
					<div class="resultLoading clearfix">
						<div class="clearfix">
							<img id="loadingIcon">
							<span id="loadingTitle"></span>
						</div>
					</div>
					<!-- 拷贝此处 -->
					<div class="shenheResultCon">
						<div class="resultList clearfix">
							<div class="resultItem">
								<div class="resultItemLabel">
									<p>1. 企业营业执照</p>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="blicUrlEnlarge"><img id="blicUrl" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
							</div>
							<div class="resultItem">
								<div class="resultItemLabel">
									<p>2. 法人代表身份证</p>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="lepUrlEnlarge"><img id="lepUrl" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
							</div>
							<div class="resultItem">
								<div class="resultItemLabel">
									<p>3. 开户许可证</p>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="occUrlEnlarge"><img id="occUrl" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
							</div>
							<div class="resultItem">
								<div class="resultItemLabel">
									<p>4. 机构信用代码证</p>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="blicTrcUrlEnlarge"><img id="blicTrcUrl" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
							</div>
							<div class="resultItem">
								<div class="resultItemLabel">
										<p>5. 财务报告</p>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="financialReportUrlEnlarge0"><img id="financialReportUrl0" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="financialReportUrlEnlarge1"><img id="financialReportUrl1" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="financialReportUrlEnlarge2"><img id="financialReportUrl2" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
							</div>
							<div class="resultItem">
								<div class="resultItemLabel">
									<p>6. 即期财务月报</p>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="financialReportMonthUrlEnlarge0"><img id="financialReportMonthUrl0" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="financialReportMonthUrlEnlarge1"><img id="financialReportMonthUrl1" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="financialReportMonthUrlEnlarge2"><img id="financialReportMonthUrl2" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadImg.png"></a>
								</div>
							</div>
							<div class="resultItem">
								<div class="resultItemLabel">
									<p>7. 验资报告</p>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="capitalReportUrlEnlarge"><img id="capitalReportUrl" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadText.png"></a>
								</div>
							</div>
							<div class="resultItem">
								<div class="resultItemLabel">
									<p>8. 公司章程</p>
								</div>
								<div class="resultItemImg">
									<a href="javascript:void(0);" target="_blank" id="coUrlEnlarge"><img id="coUrl" src="https://img.utiexian.com/website/daxiaopiao-img/review/uploadText.png"></a>
								</div>
							</div>
						</div>

						<div class="result-user">
							<div>
								<label for="">公司名称：<span id="company">--</span></label>
							</div>
							<div>
								<label for="">联系人：<span id="contactName">--</span></label>
							</div>
							<div>
								<label for="">联系方式：<span id="contactMobile">--</span></label>
							</div>
						</div>
					</div>
					<!-- 拷贝此处 end -->

					<!-- 操作 -->
					<div class="resultBtn" style="display:none">
						<button class="dangerBtn"></button>
					</div>
					<!-- 操作 end -->
				</div>
				<!-- 审核中 end -->
			</div>
			<!-- 企业预审 end -->
		</div>
		<!-- content end -->
		<!-- sidebar -->
		<div class="reviewSidebar">
			<p>换票<br>说明</p>
		</div>
	<!-- sidebar end -->
	</div>
</body>
<script>
	var memberId = '${member.id}';
	$( function(){
		getDraftExchangeCkeckInfo();
	});
  
	/**
	* 获取企业预审的信息
	*/
	function getDraftExchangeCkeckInfo(){
		$.ajax({
			url:"${bootAppPath}/draftexchangecheck/get/by/memberid",
			type:"POST",
			data:{memberId:memberId},
			dataType:"json",
			success:function(data){
				console.log(data);
				if(data.data.response == 'success'){
					var data = data.data.data;
					if(data.state == "PENDING"){
					      $("#loadingIcon").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/dengdai.png");
					      $("#loadingTitle").html("企业预审中，请耐心等待~");
					      
					      $(".dangerBtn").html("修改并重新上传");
					      $(".resultBtn").css('display','block');
					}else if(data.state == "AUTH"){
						 $("#loadingIcon").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/dengdai.png");
					     $("#loadingTitle").html("已完成初审，正在开户中。。。");
					}else if(data.state == "CHECK_FAILED"){
						$("#loadingIcon").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/shibai.png");
				        $("#loadingTitle").html("初审未通过！");
				        
				        $(".dangerBtn").html("重新提交");
					    $(".resultBtn").css('display','block');
					}else if(data.state == "SUCCESS"){
						 $("#loadingIcon").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/tongguo.png");
					     $("#loadingTitle").html("已开户成功，您可以开始置换大小票啦~");
					}else if(data.state == "AUTH_FAILED"){
						 $("#loadingIcon").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/shibai.png");
					     $("#loadingTitle").html("开户失败");
					     
					     $(".dangerBtn").html("重新上传");
						 $(".resultBtn").css('display','block');
					}
					$("#company").html(data.company);
					$("#contactName").html(data.contactName);
					$("#contactMobile").html(data.contactMobile);
					
					$("#blicUrl").attr("src","${imagePath}"+data.blicUrl);
					$("#blicUrlEnlarge").attr("href","${imagePath}"+data.blicUrl);
					$("#lepUrl").attr("src","${imagePath}"+data.lepUrl);
					$("#lepUrlEnlarge").attr("href","${imagePath}"+data.lepUrl);
					$("#occUrl").attr("src","${imagePath}"+data.occUrl);
					$("#occUrlEnlarge").attr("href","${imagePath}"+data.occUrl);
					$("#blicTrcUrl").attr("src","${imagePath}"+data.blicTrcUrl);
					$("#blicTrcUrlEnlarge").attr("href","${imagePath}"+data.blicTrcUrl);
					
					if(data.capitalReportUrl!=null&&data.capitalReportUrl!=""){
						$("#capitalReportUrl").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/wendangAfter.png");
						$("#capitalReportUrlEnlarge").attr("href","${imagePath}"+data.capitalReportUrl);
					}
					
					if(data.coUrl!=null&&data.coUrl!=""){
						$("#coUrl").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/wendangAfter.png");
						$("#coUrlEnlarge").attr("href","${imagePath}"+data.coUrl);
					}
					
					var financialReportUrl = data.financialReportUrl;
					var financialReportUrlArry= new Array(); //定义一数组 
					financialReportUrlArry = financialReportUrl.split(","); //字符分割 
					for (i=0;i<financialReportUrlArry.length;i++){ 
						$("#financialReportUrl"+[i]).attr("src","${imagePath}"+financialReportUrlArry[i]);
						$("#financialReportUrlEnlarge"+[i]).attr("href","${imagePath}"+financialReportUrlArry[i]);
					}
					
					var financialReportMonthUrl = data.financialReportMonthUrl;
					var financialReportMonthUrlArry= new Array(); //定义一数组 
					financialReportMonthUrlArry = financialReportMonthUrl.split(","); //字符分割 
					for (i=0;i<financialReportMonthUrlArry.length;i++){ 
						$("#financialReportMonthUrl"+[i]).attr("src","${imagePath}"+financialReportMonthUrlArry[i]);
						$("#financialReportMonthUrlEnlarge"+[i]).attr("href","${imagePath}"+financialReportMonthUrlArry[i]);
					}
				}else{
					layer.alert(data.data.msg);
				}
			},error:function(json){
				layer.alert("获取企业预审信息失败！");
			}
		});
	}
	
	/**
	* 修改信息，重新预审跳转
	*/
	$(".dangerBtn").click(function(){
		location.href = "${basePath}/bigsmall/qiye/update";
	});
	
	/**
	 *  大换小的跳转
	 */
	function bigSmall(){
		window.location.href ="${basePath}/bigsmall/big";
	}
	
	/**
	 *  小换大的跳转
	 */
	function smallBig(){
		window.location.href ="${basePath}/bigsmall/small";
	}
	
	/**
	 *  大小票的订单的跳转
	 */
	function order(){
		window.location.href ="${basePath}/bigsmall/big/list";
	}
	
	/**
	* 换票说明
	*/
	$(".reviewSidebar").click(function(){
		window.location.href ="${basePath}/bigsmall/explain";
	});
</script>
[@main.footer/]
[/@main.body]