<!-- 新增票据方法公用 -->
<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>
<script type="text/javascript">
$(function(){
	/**
	 * 新增票据
	 */
	$("#addBill").click(function(){
		$("#windowTitle").html("新增票据");
		$("#addWindow").show();
		$("#maskWindow").show();
			
		var now = new Date();
		var fullyear = now.getFullYear();
	   	//获取完整的年份(4位,1970-????)
		var month = now.getMonth() + 1;
		//获取当前月份(0-11,0代表1月)
		var date = now.getDate();
		//获取当前日(1-31)
		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}	
		$("#add_tiexianDate").val(fullyear+"-"+month+"-"+date);
		
		var begintimelong = Date.parse(new Date());
		var endtime12 = new Date(begintimelong + 183 * 24 * 60 * 60 * 1000);
	    var endfullyear = endtime12.getFullYear();
	    //获取完整的年份(4位,1970-????)
	    var endmonth = endtime12.getMonth() + 1;
	    //获取当前月份(0-11,0代表1月)
	    var enddate = endtime12.getDate();
		//获取当前日(1-31)
		if (endmonth < 10) {
			endmonth = "0" + endmonth;
		}
		if (enddate < 10) {
			enddate = "0" + enddate;
		}
		$("#add_endDate").val(endfullyear+"-"+endmonth+"-"+enddate);
		
		var begintimelong = Date.parse(endtime12);
		var endtime12 = new Date(begintimelong - 10 * 24 * 60 * 60 * 1000);
	    var endfullyear = endtime12.getFullYear();
	    //获取完整的年份(4位,1970-????)
	    var endmonth = endtime12.getMonth() + 1;
	    //获取当前月份(0-11,0代表1月)
	    var enddate = endtime12.getDate();
		//获取当前日(1-31)
		if (endmonth < 10) {
			endmonth = "0" + endmonth;
		}
		if (enddate < 10) {
			enddate = "0" + enddate;
		}
		$("#add_noticeDate").val(endfullyear+"-"+endmonth+"-"+enddate);
		
		var recordNum = $("input:radio[name='recordType']:checked").val();
		if(recordNum == 1){
			$(".add_inData").hide();
			$(".add_noticeDate").hide();
			$(".add_In_Date").hide();
			$(".add_Out_Date").hide();
			$(".add_pledge").hide();
			$(".add_tiexianDate").show();
			$(".add_endDate").removeClass("fl");
			$(".add_endDate").addClass("fr");
		}else if(recordNum == 2){
			$(".add_inData").hide();
			$(".add_tiexianDate").hide();
			$(".add_txje").hide();
			$(".add_In_Date").hide();
			$(".add_Out_Date").hide();
			$(".add_pledge").hide();
			$(".add_noticeDate").show();
			$(".add_endDate").removeClass("fr");
			$(".add_endDate").addClass("fl");
		}else if(recordNum == 3){
			$(".add_draftType").hide();
			$(".add_draftNo").hide();
			$(".add_date").hide();
			$(".add_txje").hide();
			$(".add_draftTo").hide();
			$(".add_bank").hide();
			$(".add_In_Date").hide();
			$(".add_Out_Date").hide();
			$(".add_pledge").hide();
		}else if(recordNum == 4){
			$(".add_inData").hide();
			$(".add_tiexianDate").hide();
			$(".add_txje").hide();
			$(".add_noticeDate").hide();
			$(".add_Out_Date").show();
			$(".add_In_Date").show();
			$(".add_pledge").show();
			$(".add_endDate").removeClass("fr");
			$(".add_endDate").addClass("fl");
		}else{
			$(".add_inData").hide();
			$(".add_noticeDate").hide();
			$(".add_In_Date").hide();
			$(".add_Out_Date").hide();
			$(".add_pledge").hide();
			$(".add_tiexianDate").show();
			$(".add_endDate").removeClass("fl");
			$(".add_endDate").addClass("fr");
		}
	});
	
	/**
	* 批量添加
	*/
	$("#addAllBill").click(function(){
		$("#windowTitle").html("批量添加");
		$("#addAllWindow").show();
		$("#maskWindow").show();
	});
	
	/**
	* 表格上传
	*/
	$("#updateForm").click(function(){
		if(memberId==null)return;
		if($("#file").val() == ""){
			alert("请选择文件");
			return;
		}
		var data = {"memberId":memberId};
		$.ajaxFileUpload({
			url : '${bootAppPath}/draftrecordimport/save',
			secureuri : false,
			dataType : 'JSON',
			data : data,
			fileElementId : "file",
			success : function(data) {
				if(data.data.response == 'success'){
					if(data.data.data!=null&&data.data.data.amount==0){
						alert("你上传的表格正在审核中！");
					}else{
						alert("新增成功");
					}
					
				    $("#windowTitle").html("");
				    $("#maskWindow").hide();
				    $("#addAllWindow").hide();
				}else{
					alert(data.data.msg);
				}
			},error : function() {
				alert("你上传的表格正在审核中！");
				
				 $("#windowTitle").html("");
				 $("#maskWindow").hide();
				 $("#addAllWindow").hide();
			}
		});
	});

	/**
	 * 选择票据属性(新增)
	 */
	$("input[name='add_draftType']").click(function(){
		var type2 = $(this).val();
		$("input[name='add_draftType']").each(function(){
			$(this).attr("checked",false);
			$(this).next().removeClass("checked");
			if($(this).val() == type2){
				$(this).attr("checked",true);
				$(this).next().addClass("checked");
			}
		});
	});
	
	/**
	* tab切换(已出库、持有中、待入账)
	*/
	$(".orderTabBtn").change(function(){
		$(this).parents("ul.orderTab").children("li").children("a").removeClass('tabChecked');
		if ($(this).attr("checked")) {
			$(this).parent("label").parent("a").addClass('tabChecked');
			$("#addWindow .xuxian").show();
			if($(this).val() == 1){
				$(".add_inData").hide();
				$(".add_noticeDate").hide();
				$(".add_In_Date").hide();
				$(".add_Out_Date").hide();
				$(".add_pledge").hide();
				$(".add_tiexianDate").show();
				$(".add_endDate").removeClass("fl");
				$(".add_endDate").addClass("fr");
			}else if($(this).val() == 2){
				$(".add_inData").hide();
				$(".add_tiexianDate").hide();
				$(".add_txje").hide();
				$(".add_In_Date").hide();
				$(".add_Out_Date").hide();
				$(".add_pledge").hide();
				$(".add_noticeDate").show();
				$(".add_endDate").removeClass("fr");
				$(".add_endDate").addClass("fl");
			}else if($(this).val() == 3){
				$(".add_draftType").hide();
				$(".add_draftNo").hide();
				$(".add_date").hide();
				$(".add_txje").hide();
				$(".add_draftTo").hide();
				$(".add_bank").hide();
				$(".add_In_Date").hide();
				$(".add_Out_Date").hide();
				$(".add_pledge").hide();
			}else if($(this).val() == 4){
				$(".add_inData").hide();
				$(".add_tiexianDate").hide();
				$(".add_txje").hide();
				$(".add_noticeDate").hide();
				$(".add_In_Date").show();
				$(".add_Out_Date").show();
				$(".add_pledge").show();
				$(".add_endDate").removeClass("fr");
				$(".add_endDate").addClass("fl");
			}
		}
	});
});

/**
* 新增保存数据
*/
function saveBill(){
	var draftType;//票据类型
	$("input[name='add_draftType']").each(function(){
		if($(this).attr("checked")){
			if($(this).val() == 1){
				draftType = "YZ"
			}else if($(this).val() == 0){
				draftType = "YD"
			}else if($(this).val() == 3){
				draftType = "SZ"
			}else if($(this).val() == 2){
				draftType = "SD"
			}
		}
	});
	
	var bank = $("#add_bank").val();
	var draftNo = $("#add_draftNo").val();
	var inData = $("#add_inData").val();
	var allmoney = $("#add_allmoney").val();
	var tiexianDate = $("#add_tiexianDate").val();
	var noticeDate = $("#add_noticeDate").val();
	var expiryDate = $("#add_endDate").val();
	var txje = $("#add_txje").val();
	var draftFrom = $("#add_draftFrom").val();
	var draftTo = $("#add_draftTo").val();
	
	var pledgeMoney = $("#add_pledge").val();
	var pledgeOutDate = $("#add_Out_Date").val();
	var pledgeInDate = $("#add_In_Date").val();
	
	var recordNum = $("input:radio[name='recordType']:checked").val();
	var recordType ;//类型
	if(!$("#add_allmoney").validationEngine("validate")){
		$("#add_allmoney").focus();
		return ;
	};
	if(recordNum == 1 || recordNum == 2){
		if(!$("#add_draftNo").validationEngine("validate")){
			$("#add_draftNo").focus();
			return ;
		};
		
		if(!$("#add_bank").validationEngine("validate")){
			$("#add_bank").focus();
			return ;
		};
		
		if(!$("#add_endDate").validationEngine("validate")){
			$("#add_endDate").focus();
			return ;
		};
	}
	var params = {memberId:memberId,draftFrom:draftFrom,allmoney:(parseFloat(allmoney)*10000)};
	if(recordNum == 1){//已出库
		if(!$("#add_tiexianDate").validationEngine("validate")){
			$("#add_tiexianDate").focus();
			return ;
		};
		
		if(!$("#add_txje").validationEngine("validate")){
			$("#add_txje").focus();
			return ;
		};
		
		params.recordType = "OUT";
		params.draftNo = draftNo;
		params.tiexianDate = tiexianDate;
		params.expiryDate = expiryDate;
		params.txje = txje;
		params.draftTo = draftTo;
		params.draftType = draftType;
		params.bank = bank;
	}else if(recordNum == 2){//已持有
		if(!$("#add_noticeDate").validationEngine("validate")){
			$("#add_noticeDate").focus();
			return ;
		};
		params.recordType = "HOLD";
		params.draftNo = draftNo;
		params.noticeDate = noticeDate;
		params.expiryDate = expiryDate;
		params.draftTo = draftTo;
		params.draftType = draftType;
		params.bank = bank;
	}else if(recordNum == 3){//待入账
		if(!$("#add_inData").validationEngine("validate")){
			$("#add_inData").focus();
			return ;
		};
		params.draftType = 'YD';
		params.recordType = "IN";
		params.inData = inData;
	}else if(recordNum == 4){//待入账
		if(!$("#add_pledge").validationEngine("validate")){
			$("#add_pledge").focus();
			return ;
		};
		params.recordType = "PLEDGE";
		params.pledgeMoney = pledgeMoney;
		params.pledgeInDate = pledgeInDate;
		params.pledgeOutDate = pledgeOutDate;
		
		params.draftNo = draftNo;
		params.expiryDate = expiryDate;
		params.draftTo = draftTo;
		params.draftType = draftType;
		params.bank = bank;
	}
	
	var url = '${bootAppPath}/draftrecord/save';
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			alert("新增成功");
			location.reload();
		    $("#windowTitle").html("");
		    $("#maskWindow").hide();
		    $("#addWindow").hide();
		}else{
			alert(data.data.msg);
		}
	}
};

/**
 * 计算扣息
 */
function kouxi(){
	var txje = $("#add_txje").val();
	var allmoney = $("#add_allmoney").val();
	if(txje != null && allmoney != null && txje != "" && allmoney != ""){
		$("#kouxi").html((parseFloat(allmoney*10000)-parseFloat(txje)).toFixed(2));
	}
};

/**
 *  到期日修改，提醒日期也改
 */
function updateEndDate(){
	var endtime12 = $("#add_endDate").val();
	var begintimelong = Date.parse(endtime12);
	var endtime12 = new Date(begintimelong - 10 * 24 * 60 * 60 * 1000);
    var endfullyear = endtime12.getFullYear();
    //获取完整的年份(4位,1970-????)
    var endmonth = endtime12.getMonth() + 1;
    //获取当前月份(0-11,0代表1月)
    var enddate = endtime12.getDate();
	//获取当前日(1-31)
	if (endmonth < 10) {
		endmonth = "0" + endmonth;
	}
	if (enddate < 10) {
		enddate = "0" + enddate;
	}
	$("#add_noticeDate").val(endfullyear+"-"+endmonth+"-"+enddate);
}

/**
* 格式化金额
*/
function fmoney(s, n) { 
	n = n > 0 && n <= 20 ? n : 2; 
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1]; 
	t = ""; 
	for (i = 0; i < l.length; i++) { 
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : ""); 
	} 
	return t.split("").reverse().join("") + "." + r; 
};
</script>