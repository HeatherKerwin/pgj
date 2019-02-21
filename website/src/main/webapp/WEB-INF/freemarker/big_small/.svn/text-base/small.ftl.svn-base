[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='小换大']
[@main.header currentmenu='1' topindex='2'/]
<link href="${cssPath}/website/daxiaopiao-190109/reste.css" rel="stylesheet">
<link href="${pluPath}/data/need/laydate.css" rel="stylesheet">
<link href="${pluPath}/data/skins/yahui/laydate.css" rel="stylesheet" id="LayDateSkin">
<link href="${cssPath}/website/daxiaopiao-190109/common.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/reviewTab.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/review.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/form.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/xiaopiao.css" rel="stylesheet">
<link href="${cssPath}/yonyou/mask.css" rel="stylesheet">

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
		<div class="topLabel">小票换大票</div>
		<div class="formCon">
			<div class="formRow clearfix">
				<div class="formRow-label"></div>
				<div class="formRow-con">
					<div class="addPiao" id="AddPiao" style="font-size: 20px">+
						添加票据</div>
					<div class="form-table">
						<form id="my-form">
							<table>
								<thead>
									<tr>
										<th>票面金额</th>
										<th>承兑行</th>
										<th>开票日期</th>
										<th>到期日</th>
										<th>剩余天数</th>
										<th>票面图片</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="ticketList">
									<tr id="noTicket">
										<td colspan="7" style="color: gray">暂无票据，请上传 ！</td>
									</tr>
								</tbody>
							</table>
							<input type="hidden" value="${member.id}" name="memberId" /> <input
								type="hidden" value="S2B" name="type" />
						</form>
					</div>
				</div>
			</div>
			<div class="formRow clearfix">
				<div class="formRow-label">
					<span style="color: red">* </span>置换日期：
				</div>
				<div class="formRow-con">
					<div class="form-col shijian">
						<input type="text" id="exchangeDate" class="inp-warning"
							placeholder="" readonly> <span></span>
					</div>
					<div class="form-col">
						<p>
							<span style="color: red">* </span>置换的日期距离开票日期大于30天，通过票据管家递交换票申请，费率会有2BP的优惠。
						</p>
					</div>
				</div>
			</div>

			<div class="formRow clearfix">
				<div class="formRow-label">
					<span style="color: red">* </span>换票需求：
				</div>
				<div class="formRow-con">
					<div class="form-col textarea">
						<textarea class="inp-warning" id="exchangeDemand"
							placeholder="请填写换票的张数、金额、期限等要求。例如：需要用4张5万元票据置换一张30万票据，到期日在2019年7月1日-7月31日之间"
							onfocus="this.placeholder=''"
							onblur="this.placeholder='请填写换票的张数、金额、期限等要求。例如：需要用4张5万元票据置换一张30万票据，到期日在2019年7月1日-7月31日之间'"></textarea>
					</div>
				</div>
			</div>

			<div class="formRow clearfix">
				<div class="formRow-label">
					<span style="color: red">* </span>贸易合同：
				</div>
				<div class="formRow-con">
					<div class="form-col upload-file">
						<div class="uploadImgRow addContract">
							<label for="file0" class="uploadImg addContract-num" onchange="uploadFile('0');"> 
								<input type="file" name="file" id="file0" /> 
								<input type="hidden" name="contractUrl0" id="contractUrl0" /> 
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

<!-- mask -->
<div class="maskWindow" style="display: none;">
	<div class="maskBg"></div>
	<div class="maskWrap maskWrapSmall">
		<!-- maskTop -->
		<div class="maskTop">
			<div class="maskTitle">添加票据</div>
			<button class="maskCloseIcon" onclick="closeWin()">×</button>
		</div>
		<!-- maskTop -->

		<!-- maskCon -->
		<div class="maskCon">
			<p class="uploadTs">
				图片必传
			</p>
			<div class="maskDiv">
				<div class="mask-upload">
					<label for="draftUrl" class="uploadImg" title="" onchange="uploadImgFile();">
						<input type="file" name="file" id="draftUrl" multiple="multiple"style="display: none;" /> 
						<input type="hidden" name="draftUrl" id="add_draftUrl" />
						<img id="draftUrlImg" src="https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png" alt="票面">
					</label>
				</div>

				<ul class="maskForm">
					<li class="clearfix"><label for="">票面金额：</label> <input
						type="number" id="add_money" class="inp-warning"
						placeholder="请输入金额(单位：万元)"></li>
					<li class="clearfix"><label for="">承兑行：</label> <input
						type="text" id="add_bank" class="inp-warning" placeholder="输入承兑行">
					</li>
					<li class="clearfix"><label for="">开票日期：</label> <input
						type="text" id="add_printDate" class="inp-warning" readonly>
						<span class="rili"></span></li>
					<li class="clearfix"><label for="">到期日期：</label> <input
						type="text" id="add_endDate" class="inp-warning" readonly>
						<span class="rili"></span></li>
					<li class="clearfix"><label for="">剩余天数：</label>
						<div>
							<span id="add_days"></span>天
						</div></li>
				</ul>
			</div>
		</div>

		<!--弹窗底部操作-->
		<div class="maskFooter" id="maskFooter">
			<!--添加-->
			<button class="addPiaoBtn dangerBtn" onclick="addTicket()">添加</button>
			<!--添加 end-->

		</div>
		<!--弹窗底部操作end-->

	</div>
</div>

<script>
/**
 * 添加票据弹窗
 */
$("#AddPiao").click(function() {
	$(".maskWindow").fadeIn(500);
});

/**
 * 关闭弹窗
 */
function closeWin() {
    $(".maskWindow").fadeOut();
}

/*
* 上传票面图片
*/
/* $("#draftUrl").change(function(){
	var reader = new FileReader();
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
				$("#draftUrlImg").attr("src","${bootPic}"+data.data.data.base64Image);
				$("#add_draftUrl").val(data.data.data.base64Image);
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
* 图片文件
*/
function uploadImgFile(){
	var draft = $("#draftUrl").val();
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
			fileElementId : "draftUrl",
			success : function(data) {
				layer.closeAll();
				$("#draftUrlImg").attr("src","${imagePath}"+data.filePath);
				$("#add_draftUrl").val(data.filePath);
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
 * 添加票
 */
function addTicket(){
	var num = document.getElementsByClassName('ticket');
	var draftUrl = $("#add_draftUrl").val();
	var money = $("#add_money").val();
	var bank = $("#add_bank").val();
	var printDate = $("#add_printDate").val();
	var endDate = $("#add_endDate").val();
	var days = $("#add_days").html();
	
	if(draftUrl==null||draftUrl==""){
		layer.alert("请上传票据！");
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
		layer.alert("请输入开票日期！");
		return;
	}
	if(endDate==null||endDate==""){
		layer.alert("请输入到期日期！");
		return;
	}
	
	var html = '<tr class="ticket" data-index="'+ num.length +'">';
	html += '<td><input type="hidden" class="money" name="draftExchangeDetails[' + num.length + '].money" value="'+ money +'">'+money+'</td>';
	html += '<td><input type="hidden" class="bank" name="draftExchangeDetails[' + num.length + '].bank" value="'+ bank +'">'+bank+'</td>';
	html += '<td><input type="hidden" class="printDate" name="draftExchangeDetails[' + num.length + '].printDate" value="'+ printDate +'">' + printDate +'</td>';
	html += '<td><input type="hidden" class="endDate" name="draftExchangeDetails[' + num.length + '].endDate" value="'+ endDate +'">'+endDate+'</td>';
	
	html += '<td class="days">'+days+'</td>';
	html += '<td><a href ="${bootPic}'+ draftUrl +'" target="_blank">票面<input name="draftExchangeDetails[' + num.length + '].draftUrl" class="draftUrl" type="hidden" value="'+draftUrl+'"/></a></td>';
	html += '<td onclick="del(this);" style="color:red">删除</td>';
	html += '</tr>';
	$("#noTicket").fadeOut();
	$("#ticketList").append(html);
	clean();
}

/**
 * 清空添加内容，同时关闭弹窗
 */
function clean(){
	$("#add_draftUrl").val("");
	$(".fileName").html("");
	$("#draftUrlImg").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/sahngchuan.png");
	$("#add_money").val("");
	$("#add_bank").val("");
	$("#add_printDate").val("");
	$("#add_endDate").val("");
	$("#add_days").html("");
	
	$(".maskWindow").fadeOut();
}

/**
 * 删除
 */
function del(id){
	var index = ($(id).parent().attr("data-index"));
	var num = document.getElementsByClassName('ticket');
	var arr = $(id).parent();
	for (var i = index; i < (num.length-1); i++) {
		var n = i;
		var key = "["+n+"]";
		arr.next().attr("data-index",i);
		for(var n=0;n<arr.next().find("input").length;n++){
			var className = arr.next().find("input")[n].className;
			arr.next().find("input")[n].name = "draftExchangeDetails["+i+"]."+className;
		}
		arr = arr.next();
	}
	$(id).parent().remove();
	if(num.length == 0){
		$("#noTicket").fadeIn();
	}
}

var memberId = '${member.id}';
/**
 * 保存小换大数据
 */
function save(){
	var num = document.getElementsByClassName('ticket');

	var data = $("#my-form").serializeArray();
	var exchangeDate = $("#exchangeDate").val();
	var exchangeDemand = $("#exchangeDemand").val();
	var contractUrl = $("#contractUrl0").val();
	
	if(num.length==0){
		layer.alert("请添加票据之后，再保存数据！！");
		return;
	}
	if(exchangeDate==null||exchangeDate==""){
		layer.alert("请选择置换日期！");
		return;
	}
	if(exchangeDemand==null||exchangeDemand==""){
		layer.alert("请输入置换的需求！");
		return;
	}
	if(contractUrl==null||contractUrl==""){
		layer.alert("请上传贸易合同！");
		return;
	}

	var contract_num = document.getElementsByClassName('addContract-num');
	if(contract_num.length>2){
		for (var i = 1;i < contract_num.length-1; i++) {
			contractUrl = contractUrl +"," + $("#contractUrl"+i).val();
		}
	}
	 
	data. push({"name":"exchangeDate","value":exchangeDate});
	data. push({"name":"exchangeDemand","value":exchangeDemand});
	data. push({"name":"contractUrl","value":contractUrl});
	layer.load(2);
	$.ajax({
		url:"${bootAppPath}/draftexchange/save",
		type:"POST",
		data:data,
		dataType:"json",
		success:function(data){
			layer.closeAll();
			if(data.data.response == 'success'){
				window.location.href ="${basePath}/bigsmall/small/list";
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
	$("#add_days").html(days);
}

//日历
!function(){
    laydate.skin('yahui');//切换皮肤，请查看skins下面皮肤库
}();
var printDate = {
	elem: '#add_printDate',
	format: 'YYYY-MM-DD',
	max: '2099-06-16', //最大日期
	choose: function(datas){
		
	}
};

var endDate = {
	elem: '#add_endDate',
	format: 'YYYY-MM-DD',
	min : laydate.now(), //最小日期
	max: '2099-06-16', //最大日期
	choose: function(datas){
		surplusDay(datas);
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
laydate(endDate);
laydate(exchangeDate);

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
 *  大换小的跳转
 */
function bigSmall(){
	window.location.href ="${basePath}/bigsmall/big";
}

/**
 *  大小票的订单的跳转
 */
function order(){
	window.location.href ="${basePath}/bigsmall/big/list";
}
</script>
[/@main.body]
