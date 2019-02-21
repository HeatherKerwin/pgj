[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='企业预审']
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
<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>

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
			<!-- stepIndex=1 上传审核内容 -->
			<div id="reviewUpload" class="reviewUpload" style="display: black;">
				<p class="UploadTip">*以下所有文件均需上传加盖企业鲜章的图片。</p>

				<!-- 1.企业营业执照 -->
				<div class="reviewUploadRow">
					<div class="uploadLabel">1.企业营业执照（必传）</div>
					<div class="imgLabel">营业执照</div>
					<div class="uploadImgRow">
						<label for="file0" class="uploadImg" title="" onchange="uploadImgFile('file0','blicUrl','img0');">
							<input type="file" name="file" id="file0"/>
							<input type="hidden" name="blicUrl" id="blicUrl" />
							<img id="img0" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="企业营业执照">
						</label>
					</div>
					<div class="uploadImgTip">请上传原件的扫描件，或者加盖鲜章的复印件扫描件。</div>
				</div>
        		<!-- 1.企业营业执照 end -->

				<!-- 2. 法人代表身份证 -->
				<div class="reviewUploadRow">
					<div class="uploadLabel">2. 法人代表身份证（必传）</div>
					<div class="imgLabel">法人代表身份证</div>
					<div class="uploadImgRow">
						<label for="file1" class="uploadImg" title="上传法人代表身份证" onchange="uploadImgFile('file1','lepUrl','img1');">
							<input type="file" name="file" id="file1"/>
							<input type="hidden" name="lepUrl" id="lepUrl" />
							<img id="img1" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="法人代表身份证">
						</label>
					</div>
	          		<div class="uploadImgTip">在同一张纸上复印法人代表身份证正反面，加盖鲜章后，上传扫描件。</div>
        		</div>
        		<!-- 2. 法人代表身份证 end -->

				<!-- 3. 开户许可证 -->
				<div class="reviewUploadRow">
					<div class="uploadLabel">3. 开户许可证（必传）</div>
					<div class="imgLabel">开户许可证</div>
					<div class="uploadImgRow">
						<label for="file2" class="uploadImg" title="上传开户许可证" onchange="uploadImgFile('file2','occUrl','img2');">
							<input type="file" name="file" id="file2"/>
							<input type="hidden" name="occUrl" id="occUrl" />
							<img id="img2" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="上传开户许可证">
						</label>
					</div>
					<div class="uploadImgTip">请	上传原件的扫描件，或者加盖鲜章的复印件扫描件。</div>
				</div>
        		<!-- 3. 开户许可证 end -->

				<!-- 4. 机构信用代码证 -->
				<div class="reviewUploadRow">
					<div class="uploadLabel">4. 机构信用代码证（必传）</div>
					<div class="imgLabel">机构信用代码证</div>
					<div class="uploadImgRow">
						<label for="file3" class="uploadImg" title="上传机构信用代码证" onchange="uploadImgFile('file3','blicTrcUrl','img3');">
							<input type="file" name="file" id="file3"/>
							<input type="hidden" name="blicTrcUrl" id="blicTrcUrl" />
							<img id="img3" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="机构信用代码证">
						</label>
					</div>
					<div class="uploadImgTip">请上传原件的扫描件，或者加盖鲜章的复印件扫描件。</div>
				</div>
				<!-- 4. 机构信用代码证 end -->

				<!-- 5. 财务报告 -->
				<div class="reviewUploadRow">
					<div class="uploadLabel">5. 财务报告（必传）</div>
					<div class="imgLabel">近三年的财务报告如有审计报告请上传审计报告</div>
					<div class="uploadImgRow">
						<label for="file4_0" class="uploadImg" title="上传财务报告" onchange="uploadImgFile('file4_0','financialReportUrl0','img4_0');">
							<input type="file" name="file" id="file4_0"/>
							<input type="hidden" name="financialReportUrl0" id="financialReportUrl0" />
							<img id="img4_0" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="财务报告">
						</label>
						<label for="file4_1" class="uploadImg" title="上传财务报告" onchange="uploadImgFile('file4_1','financialReportUrl1','img4_1');">
							<input type="file" name="file" id="file4_1"/>
							<input type="hidden" name="financialReportUrl1" id="financialReportUrl1" />
							<img id="img4_1" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="财务报告">
						</label>
						<label for="file4_2" class="uploadImg" title="上传财务报告" onchange="uploadImgFile('file4_2','financialReportUrl2','img4_2');">
							<input type="file" name="file" id="file4_2"/>
							<input type="hidden" name="financialReportUrl2" id="financialReportUrl2" />
							<img id="img4_2" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="财务报告">
						</label>
					</div>
          			<div class="uploadImgTip">请上传加盖鲜章的财务报告扫描件，至少上传一张。</div>
				</div>
				<!-- 5. 财务报告 end -->

				<!--  6. 即期财务月报 -->
				<div class="reviewUploadRow">
					<div class="uploadLabel"> 6. 即期财务月报（必传）</div>
					<div class="imgLabel">当前月份上个月的财务月报</div>
					<div class="uploadImgRow">
						<label for="file5_0" class="uploadImg" title="上传即期财务月报" onchange="uploadImgFile('file5_0','financialReportMonthUrl0','img5_0');">
							<input type="file" name="file" id="file5_0"/>
							<input type="hidden" name="financialReportMonthUrl0" id="financialReportMonthUrl0" />
							<img id="img5_0" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="上传财务报告">
						</label>
						<label for="file5_1" class="uploadImg" title="上传即期财务月报" onchange="uploadImgFile('file5_1','financialReportMonthUrl1','img5_1');">
							<input type="file" name="file" id="file5_1"/>
							<input type="hidden" name="financialReportMonthUrl1" id="financialReportMonthUrl1" />
							<img id="img5_1" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="上传财务报告">
						</label>
						<label for="file5_2" class="uploadImg" title="上传即期财务月报" onchange="uploadImgFile('file5_2','financialReportMonthUrl2','img5_2');">
							<input type="file" name="file" id="file5_2"/>
							<input type="hidden" name="financialReportMonthUrl2" id="financialReportMonthUrl2" />
							<img id="img5_2" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="上传财务报告">
						</label>
					</div>
					<div class="uploadImgTip">请上传加盖鲜章的即期财务月报扫描件，至少上传一张。</div>
				</div>
				<!--  6. 即期财务月报 end -->

				<!-- 7. 验资报告 -->
				<div class="reviewUploadRow">
					<div class="uploadLabel">7. 验资报告（选传）</div>
					<div class="imgLabel">验资报告，需上传Word/PDF文档</div>
					<div class="uploadImgRow">
						<label for="capitalReportUrl" class="uploadImg" title="上传验资报告" onchange="uploadFile('capitalReportUrl');">
							<input type="file" name="file" id="capitalReportUrl"/>
							<input type="hidden" name="capitalReportUrlPath" id="capitalReportUrlPath"/>
							<img src="https://img.utiexian.com/website/daxiaopiao-img/review/wendang.png" id="capitalReportUrlImg" alt="上传验资报告">
							<p class="fileName" id="capitalReportUrlName"></p>
						</label>
						<p class="uploadText-Del" id="deletecapitalReportUrl">删除文档</p>
					</div>
				</div>
				<!-- 7. 验资报告 end -->

				<!-- 8. 公司章程 -->
				<div class="reviewUploadRow">
					<div class="uploadLabel">8. 公司章程（选传）</div>
					<div class="imgLabel">公司章程，需上传Word/PDF文档</div>
					<div class="uploadImgRow">
						<label for="coUrl" class="uploadImg" title="上传公司章程" onchange="uploadFile('coUrl');">
							<input type="file" name="file" id="coUrl"/>
							<input type="hidden" name="coUrlPath" id="coUrlPath"/>
							<img src="https://img.utiexian.com/website/daxiaopiao-img/review/wendang.png" id="coUrlImg" alt="上传公司章程">
							<p class="fileName" id="coUrlName"></p>
						</label>
						<p class="uploadText-Del" id="deletecoUrl">删除文档</p>
					</div>
				</div>
				<!-- 8. 公司章程 end -->
				<!--  -->
				<div class="reviewUploadRow">
					<div class="uploadUser">
						<label for=""><span style="color: red">* </span>公司名称：</label>
						<input type="text" id="company" class="inp-warning" placeholder="请输入公司名称">
					</div>
				</div>	
				<div class="uploadUser">
					<label for=""><span style="color: red">* </span>联系方式：</label>
					<input type="text" id="mobile" class="inp-warning" placeholder="请输入联系方式">
					
					<label for=""><span style="color: red">* </span>联系人：</label>
					<input type="text" id="contactName" class="inp-warning" placeholder="请输入联系人姓名">
				</div>

				<div class="uploadBtn"><button class="dangerBtn" onclick="save();">提交</button></div>
			</div>
      		<!-- 上传审核内容 end -->
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
<input type="hidden" id="draftId">
<script>
	var memberId = '${member.id}';
	$(function(){
		 getDraftExchangeCkeckInfo();
	});
	
  	/**
  	* 企业预审保存
  	*/
  	function save(){
  		if(memberId==null||memberId=="")return;
  		var blicUrl = $("#blicUrl").val();
  		var lepUrl = $("#lepUrl").val();
  		var occUrl = $("#occUrl").val();
  		var blicTrcUrl = $("#blicTrcUrl").val();
  		var company = $("#company").val();
  		
  		var financialReportUrl = $("#financialReportUrl0").val();
  		var financialReportUrl1 = $("#financialReportUrl1").val();
  		var financialReportUrl2 = $("#financialReportUrl2").val();
  		
  		var financialReportMonthUrl = $("#financialReportMonthUrl0").val();
  		var financialReportMonthUrl1 = $("#financialReportMonthUrl1").val();
  		var financialReportMonthUrl2 = $("#financialReportMonthUrl2").val();
  		
  		var capitalReportUrl = $("#capitalReportUrlPath").val();
  		var coUrl = $("#coUrlPath").val();
  		var contactName = $("#contactName").val();
  		var contactMobile = $("#mobile").val();
  		var id = $("#draftId").val();
  		
  		if(blicUrl==null||blicUrl==""){
  			layer.alert("请选择上传营业执照!");
  			return;
  		}
  		if(lepUrl==null||lepUrl==""){
  			layer.alert("请上传身份证照!");
  			return;
  		}
  		if(occUrl==null||occUrl==""){
  			layer.alert("请上传开户许可证!");
  			return;
  		}
  		if(blicTrcUrl==null||blicTrcUrl==""){
  			layer.alert("请上传机构信用代码证!");
  			return;
  		}
  		if(company==null||company==""){
  			$("#company").focus();
  			layer.alert("请填写公司名称!");
  			return;
  		}
  		if(financialReportUrl==null||financialReportUrl==""){
  			layer.alert("请上传财务报告!");
  			return;
  		}
  		
  		if(financialReportMonthUrl==null||financialReportMonthUrl==""){
  			layer.alert("请上传即期财务月报!");
  			return;
  		}
  		if(contactName==null||contactName==""){
  			layer.alert("请输入联系人名称!");
  			return;
  		}
  		if(contactMobile==null||contactMobile==""){
  			layer.alert("请输入联系方式!");
  			return;
  		}
  		if(financialReportUrl1!=null&&financialReportUrl1!=""){
  			financialReportUrl = financialReportUrl + ","+ financialReportUrl1; 
  		}
  		if(financialReportUrl2!=null&&financialReportUrl2!=""){
  			financialReportUrl = financialReportUrl + ","+ financialReportUrl2; 
  		}
  		if(financialReportMonthUrl1!=null&&financialReportMonthUrl1!=""){
  			financialReportMonthUrl = financialReportMonthUrl + ","+ financialReportMonthUrl1; 
  		}
  		if(financialReportMonthUrl2!=null&&financialReportMonthUrl2!=""){
  			financialReportMonthUrl = financialReportMonthUrl + ","+ financialReportMonthUrl2; 
  		}
  		var data={id:id,memberId:memberId,blicUrl:blicUrl,lepUrl:lepUrl,occUrl:occUrl,blicTrcUrl:blicTrcUrl,company:company,contactName:contactName,
  				contactMobile:contactMobile,financialReportUrl:financialReportUrl,financialReportMonthUrl:financialReportMonthUrl,
  				capitalReportUrl:capitalReportUrl,coUrl:coUrl};
  		layer.load(2);
  		$.ajax({
			url:"${bootAppPath}/draftexchangecheck/update",
			type:"POST",
			data:data,
			dataType:"json",
			success:function(data){
				console.log(data);
				layer.closeAll();
				if(data.data.response == 'success'){
					location.href = "${basePath}/bigsmall/qiye/xiangqing";
				}else{
					layer.alert(data.data.msg);
				}
			},error:function(json){
				layer.closeAll();
				console.log("提交企业预审失败！");
			}
		});
  	};

	/*
	* 上传图片营业执照
	*/
/* 	$("#file0").change(function(){
		var reader = new FileReader();
		reader.onload = function (evt) {
			var base64url = evt.target.result;
			readFile(base64url,"img0","blicUrl");
		}
		reader.readAsDataURL(this.files[0]);
	}); */
	
	/*
	* 法人代表身份证
	*/
/* 	$("#file1").change(function(){
		var reader = new FileReader();
		reader.onload = function (evt) {
			var base64url = evt.target.result;
			readFile(base64url,"img1","lepUrl");
		}
		reader.readAsDataURL(this.files[0]);
	}); */
	
	/*
	* 开户许可证
	*/
	/* $("#file2").change(function(){
		var reader = new FileReader();
		reader.onload = function (evt) {
			var base64url = evt.target.result;
			readFile(base64url,"img2","occUrl");
		}
		reader.readAsDataURL(this.files[0]);
	}); */
	
	/*
	* 机构信用代码证
	*/
/* 	$("#file3").change(function(){
		var reader = new FileReader();
		reader.onload = function (evt) {
			var base64url = evt.target.result;
			readFile(base64url,"img3","blicTrcUrl");
		}
		reader.readAsDataURL(this.files[0]);
	}); */
	
	/*
	* 财务报告
	*/
	/* function finance(file,index){
		var reader = new FileReader();
		reader.onload = function (evt) {
			var base64url = evt.target.result;
			readFile(base64url,"img4_"+index,"financialReportUrl"+index);
		}
		reader.readAsDataURL(file.files[0]);
	} */
	
	/*
	* 即期财务月报
	*/
	/* function monthlyReport(file,index){
		var reader = new FileReader();
		reader.onload = function (evt) {
			var base64url = evt.target.result;
			readFile(base64url,"img5_"+index,"financialReportMonthUrl"+index);
		}
		reader.readAsDataURL(file.files[0]);
	} */
  
	/**
	* base64url 图片64位
	* name 展示图片的img的名称
	* pathName 保存图片的地址
	* boot 项目的图片上传
	*/
/* 	function readFile(base64url,name,pathName){
		if(base64url == null && base64url == ""){
			return ;
		}
		layer.load(2);
		var index = base64url.indexOf(",");
		var base64Image = base64url.substr(index+1);
		$.ajax({
			url:"${bootAppPath}/upload/image",
			type:"POST",
			data:{base64Image:base64Image,ocrGenre:null},
			dataType:"json",
			success:function(data){
				layer.closeAll();
				if(data.data.response == 'success'){
					$("#"+name).attr("src","${imagePath}"+data.data.data.base64Image);
					$("#"+pathName).val(data.data.data.base64Image);
				}else{
					layer.alert(data.data.msg);
				}
			},error:function(json){
				layer.closeAll();
				console.log("图片上传失败！");
			}
		});
	}; */
	
	/**
	* 图片上传
	*/
	function uploadImgFile(file,name,img){
		var fileName = $("#"+file).val();
		if(fileName == "" || fileName == null){
			return;
		}
		var suffix = fileName.lastIndexOf('.');
		var suffixName = fileName.substr(suffix).toLowerCase();
		if(suffixName.indexOf("jpg") == -1 && suffixName.indexOf("png") == -1){
			layer.alert("请按照要求上传");
			return ;
		}else{
			layer.load(2);
			var data = {"memberId":memberId};
			$.ajaxFileUpload({
				url : '${basePath}/upload/file/save',
				secureuri : false,
				dataType : 'JSON',
				data : data,
				fileElementId : file,
				success : function(data) {
					layer.closeAll();
					$("#"+img).attr("src","${imagePath}"+data.filePath);
					$("#"+name).val(data.filePath);
				},error : function() {
					layer.closeAll();
					layer.alert("发生异常！");
				}
			});
		}
	}
	
	/**
	* 上传文件
	*/
	function uploadFile(id){
		var fileName = $("#"+id).val();
		if(fileName == ""){
			return;
		}
		var suffix = fileName.lastIndexOf('.');
		var suffixName = fileName.substr(suffix).toLowerCase();
		if(suffixName.indexOf("pdf") == -1 && suffixName.indexOf("docx") == -1 && suffixName.indexOf("doc") == -1){
			layer.alert("请按照要求上传");
			return ;
		}else{
			var data = {"memberId":1};
			$.ajaxFileUpload({
				url : '${basePath}/upload/file/save',
				secureuri : false,
				dataType : 'JSON',
				data : data,
				fileElementId : id,
				success : function(data) {
					$("#"+id+"Name").html(data.fileName);
					$("#"+id+"Path").val(data.filePath);
					$("#"+id+"Img").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/wendangAfter.png");
				},error : function() {
					layer.alert("发生异常！");
				}
			});
		}
	}
	
	/**
	* 删除验资报告的文档
	*/
	$("#deletecapitalReportUrl").click(function(){
		$("#capitalReportUrlName").html("");
		$("#capitalReportUrlPath").val("");
		$("#capitalReportUrlImg").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/wendang.png");
	});
	
	/**
	* 删除公司章程的文档
	*/
	$("#deletecoUrl").click(function(){
		$("#coUrlName").html("");
		$("#coUrlPath").val("");
		$("#coUrlImg").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/wendang.png");
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
					$("#draftId").val(data.id);
					$("#company").val(data.company);
					$("#contactName").val(data.contactName);
					$("#mobile").val(data.contactMobile);
					
					$("#img0").attr("src","${imagePath}"+data.blicUrl);
					$("#blicUrl").val(data.blicUrl);
					$("#img1").attr("src","${imagePath}"+data.lepUrl);
					$("#lepUrl").val(data.lepUrl);
					$("#img2").attr("src","${imagePath}"+data.occUrl);
					$("#occUrl").val(data.occUrl);
					$("#img3").attr("src","${imagePath}"+data.blicTrcUrl);
					$("#blicTrcUrl").val(data.blicTrcUrl);
					
					if(data.capitalReportUrl!=null&&data.capitalReportUrl!=""){
						$("#capitalReportUrlPath").val(data.capitalReportUrl);
						$("#capitalReportUrlImg").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/wendangAfter.png");
					}
					
					if(data.coUrl!=null&&data.coUrl!=""){
						$("#coUrlPath").val(data.coUrl);
						$("#coUrlImg").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/wendangAfter.png");
					}
					
					var financialReportUrl = data.financialReportUrl;
					var financialReportUrlArry= new Array(); //定义一数组 
					financialReportUrlArry = financialReportUrl.split(","); //字符分割 
					for (i=0;i<financialReportUrlArry.length;i++){ 
						$("#img4_"+[i]).attr("src","${imagePath}"+financialReportUrlArry[i]);
						$("#financialReportUrl"+[i]).val(financialReportUrlArry[i]);
					}
					
					var financialReportMonthUrl = data.financialReportMonthUrl;
					var financialReportMonthUrlArry= new Array(); //定义一数组 
					financialReportMonthUrlArry = financialReportMonthUrl.split(","); //字符分割 
					for (i=0;i<financialReportMonthUrlArry.length;i++){ 
						$("#img5_"+[i]).attr("src","${imagePath}"+financialReportMonthUrlArry[i]);
						$("#financialReportMonthUrl"+[i]).val(financialReportMonthUrlArry[i]);
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