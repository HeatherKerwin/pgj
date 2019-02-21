[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='大换小']
[@main.header currentmenu='1' topindex='2'/]
<link href="${cssPath}/website/daxiaopiao-190109/reste.css" rel="stylesheet">
<link href="${pluPath}/data/skins/qianhuang/laydate.css" rel="stylesheet" id="LayDateSkin">
<link href="${cssPath}/website/daxiaopiao-190109/common.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/reviewTab.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/review.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/form.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/dapiao.css" rel="stylesheet">
<link href="${pluPath}/data/need/laydate.css" rel="stylesheet">
<link href="${pluPath}/data/skins/yahui/laydate.css" rel="stylesheet" id="LayDateSkin">

<script type="text/javascript" src="${comPath}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>
	<div class="reviewWarp">
		<!-- 切换 -->
		<div class="reviewTab clearfix">
			<div onclick="enterprise();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/shehe.png" alt="企业预审">
				<p>企业预审</p>
			</div>
			<div>
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
			<div class="topLabel">大票换小票</div>
			<div class="formCon">
				<div class="formRow">
					<p>
						图片必传
					</p>
				</div>

				<div class="formRow clearfix">
					<div class="formRow-con">
						<div class="form-col upload-file">
							<div class="uploadImgRow">
								<label for="draft" class="uploadImg" title="" onchange="uploadImgFile();"> 
									<input type="file" name="file" id="draft" />
									<input type="hidden" name="draftUrl" id="draftUrl"/>
									<img id="draftImg" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="票面">
								</label>
							</div>
						</div>
					</div>
				</div>

				<div class="formRow">
					<p class="small">
						<span>*</span>将票面信息填写在下方输入框内，图片文件（jpg，png），大小5M以内
					</p>
				</div>

				
					<div class="formRow clearfix">
						<div class="formRow-label"><span style="color: red">* </span>票面金额：</div>
						<div class="formRow-con">
							<div class="form-col inp">
								<input type="text" id="money" class="inp-warning" placeholder="请输入金额(单位：万元)">
							</div>
						</div>
						
					</div>
					
					<div class="formRow clearfix">
					<div class="formRow-label"><span style="color: red">* </span>承兑行：</div>
						<div class="formRow-con">
							<div class="form-col inp">
								<input type="text" id="bank" class="inp-warning" placeholder="请输入承兑行">
							</div>
						</div>
						</div>

					<div class="formRow clearfix">
						<div class="formRow-label"><span style="color: red">* </span>开票日期：</div>
						<div class="formRow-con">
							<div class="form-col shijian">
								<input type="text" id="printDate" class="inp-warning" placeholder="" readonly>
								<span></span>
							</div>
							<div class="form-col"><span style="color: red">* </span>到期日期</div>
							<div class="form-col shijian">
								<input type="text" id="end" class="inp-warning" placeholder="" readonly>
								<span></span>
							</div>
						</div>
					</div>

					<div class="formRow clearfix">
						<div class="formRow-label">&nbsp;&nbsp;&nbsp;剩余天数：</div>
						<div class="formRow-con">
							<div class="form-col"><span id="surplusDays">--</span>天</div>
						</div>
					</div>

					<div class="formRow clearfix">
						<div class="formRow-label"><span style="color: red">* </span>置换日期：</div>
						<div class="formRow-con">
							<div class="form-col shijian">
								<input type="text" id="exchangeDate" class="inp-warning" placeholder="" readonly>
								<span></span>
							</div>
						</div>
					</div>

					<div class="formRow">
						<p class="small">
							<span>*</span>置换的日期距离开票日期大于30天，通过票据管家递交换票申请，费率会有2BP的优惠。
						</p>
					</div>

					<div class="formRow clearfix">
						<div class="formRow-label"><span style="color: red">* </span>换票需求：</div>
						<div class="formRow-con">
							<div class="form-col textarea">
								<textarea id="exchangeDemand" class="inp-warning"
									placeholder="请填写换票的张数、金额、期限等要求。例如：需要置换3张10万元票据和一张20万票据，到期日在2019年7月1日-7月31日之间"
									onfocus="this.placeholder=''"
									onblur="this.placeholder='请填写换票的张数、金额、期限等要求。例如：需要置换3张10万元票据和一张20万票据，到期日在2019年7月1日-7月31日之间'"
									style="resize: none;"></textarea>
							</div>
						</div>
					</div>

					<div class="formRow clearfix">
						<div class="formRow-label"><span style="color: red">* </span>贸易合同：</div>
						<div class="formRow-con">
							<div class="form-col upload-file">
								<div class="uploadImgRow addContract">
									<label for="file0" class="uploadImg addContract-num" onchange="uploadFile('0');">
										<input type="file" name="file" id="file0"/> 
										<input type="hidden" name="contractUrl0" id="contractUrl0"/> 
										<img id="img0" src="https://img.utiexian.com/website/daxiaopiao-img/review/wendang.png" alt="贸易合同">
										<p class="fileName0"></p>
									</label>
								</div>
							</div>
						</div>
					</div>

					<div class="formRow">
						<p class="small" style="padding-left: 100px;">
							<span>*</span>至少上传一份合同 ，最多六份合同,文档格式Word/PDF。
						</p>
					</div>

					<div class="formBtn">
						<button class="dangerBtn" onclick="save();">提交审核</button>
					</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script>
//日历
!function(){
    laydate.skin('yahui');//切换皮肤，请查看skins下面皮肤库
}();
var printDate = {
	elem: '#printDate',
	format: 'YYYY-MM-DD',
	max: '2099-06-16', //最大日期
	choose: function(datas){
		end.start = datas //将结束日的初始值设定为开始日
	}
};

var end = {
	elem: '#end',
	format: 'YYYY-MM-DD',
	min : laydate.now(), //最小日期
	max: '2099-06-16', //最大日期
	choose: function(datas){
		printDate.max = datas;
		surplusDay();
	}
};

var exchangeDate = {
	elem: '#exchangeDate',
	format: 'YYYY-MM-DD',
	max: '2099-06-16', //最大日期
	choose: function(datas){
		
	}
};

laydate(printDate);
laydate(end);
laydate(exchangeDate);

var memberId = '${member.id}';
/*
* 上传票面图片
*/
/* $("#draft").change(function(){
	uploadFile("draft");
	return;
	var reader = new FileReader();
	console.log(this.files[0].type.indexOf("jpg") != -1);
	return ;
	reader.onload = function (evt) {
		var base64url = evt.target.result;
		readFile(base64url);
	}
	reader.readAsDataURL(this.files[0]);
});
 */
/**
* base64url 图片64位
* name 展示图片的img的名称
* pathName 保存图片的地址
* boot 项目的图片上传
*/
/* function readFile(base64url,name,pathName){
	if(base64url == null && base64url == ""){
		return ;
	}
		
	var index = base64url.indexOf(",");
	var base64Image = base64url.substr(index+1);
	layer.load(2);
	$.ajax({
		url:"${bootAppPath}/upload/image",
		type:"POST",
		data:{base64Image:base64Image,ocrGenre:""},
		dataType:"json",
		success:function(data){
			layer.closeAll();
			if(data.data.response == 'success'){
				console.log(data);
				$("#draftImg").attr("src","${bootPic}"+data.data.data.base64Image);
				$("#draftUrl").val(data.data.data.base64Image);
				
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
function uploadImgFile(){
	var draft = $("#draft").val();
	var suffix = draft.lastIndexOf('.')
	var suffixName = draft.substr(suffix)
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
			fileElementId : "draft",
			success : function(data) {
				layer.closeAll();
				$("#draftImg").attr("src","${imagePath}"+data.filePath);
				$("#draftUrl").val(data.filePath);
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
	var fileName = $("#file"+id).val();
	if(fileName == ""){
		return;
	}
	var suffix = fileName.lastIndexOf('.');
	var suffixName = fileName.substr(suffix).toLowerCase();
	if(suffixName.indexOf("pdf") == -1 && suffixName.indexOf("docx") == -1 && suffixName.indexOf("doc") == -1){
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
			fileElementId : "file"+id,
			success : function(data) {
				layer.closeAll();
				$(".fileName"+id).html(data.fileName);
				$("#contractUrl"+id).val(data.filePath);
				$("#img"+id).attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/wendangAfter.png");
				
				var contract_num = document.getElementsByClassName('addContract-num');
				if(contract_num.length<6){
					addContractUrl();
				}
			},error : function() {
				layer.closeAll();
				layer.alert("发生异常！");
			}
		});
	}
}

/**
 * 保存大换小
 */
function save(){
	var draftUrl = $("#draftUrl").val();
	var money = $("#money").val();
	var bank = $("#bank").val();
	var printDate = $("#printDate").val();
	var endDate = $("#end").val();
	var exchangeDate = $("#exchangeDate").val();
	var exchangeDate = $("#exchangeDate").val();
	var exchangeDemand = $("#exchangeDemand").val();
	var contractUrl = $("#contractUrl0").val();

	if(draftUrl==null||draftUrl==""){
		layer.alert("请上传票面！");
		return;
	}
	if(money==null||money==""){
		layer.alert("请输入票面金额！");
		return;
	}
	if(bank==null||bank==""){
		layer.alert("请输入承兑行！");
		return;
	}
	if(printDate==null||printDate==""){
		layer.alert("请选择开票日期！");
		return;
	}
	if(endDate==null||endDate==""){
		layer.alert("请选择到期日期！");
		return;
	}
	if(exchangeDate==null||exchangeDate==""){
		layer.alert("请选择置换日期！");
		return;
	}
	if(exchangeDemand==null||exchangeDemand==""){
		layer.alert("请输入换票需求！");
		return;
	}
	if(contractUrl==null||contractUrl==""){
		layer.alert("请上传贸易合同！");
		return;
	}

	var num = document.getElementsByClassName('addContract-num');
	
	if(num.length>2){
		for (var i = 1;i < num.length-1; i++) {
			contractUrl = contractUrl +"," + $("#contractUrl"+i).val();
		}
	}
	var data = {memberId:memberId,"draftExchangeDetails[0].draftUrl":draftUrl,exchangeDate:exchangeDate,exchangeDemand:exchangeDemand,contractUrl:contractUrl,type:"B2S",
			"draftExchangeDetails[0].money":money,"draftExchangeDetails[0].bank":bank,"draftExchangeDetails[0].printDate":printDate,
			"draftExchangeDetails[0].endDate":endDate};
	layer.load(2);
	$.ajax({
		url:"${bootAppPath}/draftexchange/save",
		type:"POST",
		data:data,
		dataType:"json",
		success:function(data){
			layer.closeAll();
			if(data.data.response == 'success'){
				window.location.href ="${basePath}/bigsmall/big/list";
			}else{
				layer.alert(data.data.msg);
			}
		},error:function(json){
			layer.closeAll();
			console.log("保存数据失败！");
		}
	});
}

/**
 * 获取剩余天数
 */
function surplusDay(){
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
	
	var end = $("#end").val();
	var date2 = new Date(start);//开始时间
	var date3 = new Date(end);//结束时间

	var date4=date3.getTime()-date2.getTime(); //时间差的毫秒数
	//计算出相差天数
	var days=Math.floor(date4/(24*3600*1000));
	if(days==null||days==""){
		days=0;
	}
	$("#surplusDays").html(days);
}

/**
 * 添加贸易合同
 */
function addContractUrl(){
	var num = document.getElementsByClassName('addContract-num')
	var html = '<label for="file'+num.length+'" class="uploadImg addContract-num" title="" onchange="uploadFile('+num.length+');">';
	html += '<input type="file" name="file" id="file'+num.length+'" multiple="multiple" /> ';
	html += '<input type="hidden" name="contractUrl'+num.length+'" value="'+num.length+'" id="contractUrl'+num.length+'"/>  ';
	html += '<img id="img'+num.length+'" src="https://img.utiexian.com/website/daxiaopiao-img/review/wendang.png" alt="uploadImg">';
	html += '<p class="fileName'+num.length+'"></p></label>';
	$(".addContract").append(html);
}

/**
 *  大小票的跳转
 */
function enterprise(){
	$.ajax({
		url:"${bootAppPath}/draftexchangecheck/get/by/memberid",
		type:"POST",
		data:{memberId:memberId},
		dataType:"json",
		success:function(data){
			console.log(data);
			if(data.data.response == 'success'){
				var data = data.data.data;
				if(data!=null&&data!=""){
					window.location.href ="${basePath}/bigsmall/qiye/xiangqing";
				}else{
					window.location.href ="${basePath}/bigsmall/qiye/yushen";
				}
			}else{
				layer.alert(data.data.msg);
			}
		},error:function(json){
			layer.alert("获取信息失败！");
		}
	});
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
</script>
[/@main.body]