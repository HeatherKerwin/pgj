<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>开户行行号</div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn">
                    <img src="${imagePath}website/PJGJ/authentication/closeIcon.png" alt="关闭" class="cp">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--温馨提示-->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150 none" id="bankWindow">
                    <div class="flex-row flex-direction-row bae0e0e0 lh34 mt30 w385">
                        <input type="text" id="bank_branch" maxlength="25" class="flex-col-auto b0 ti8" placeholder="请输入分行行号或支行关键词">
                        <input type="button" value="搜索" onclick="loadBank();" class="w80 bcd43c33 cwhite bad43c33 h34">
                    </div>
                    <div id="promptStr" class="none">暂无查询结果,您可以尝试更改查询条件搜索。</div>
                    <ul id="banks" style="text-algin:center;" class="flex-row flex-direction-column lh30 w bte0e0e0 ble0e0e0 bre0e0e0 mt30">
                    	
                    </ul>
                    <div class="flex-row flex-direction-row w385 mt20 flex-justify-end">
                        <button id="prev" class="none" onclick="prevPage();">上一页</button>
                        <button id="next" class="none" onclick="nextPage();">下一页</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
var pageIndex=1;
var pageSize=10;
var bankList = [];
var banks = [];
var orgState = localStorage["ORGSTATE"];
var bnsState = localStorage["BNSSTATE"];
var role = '${role}';
var memberId = '${member.id}';
$(document).ready(function () {
	console.log("${openFlag}");
	var openFlag = "${openFlag}";
	if(openFlag!=""){
		$("#rzTab3").parent().removeClass("none");
	}
	if(role == 1){
		$("#rzTab2").parent("li").addClass("bbd43c33");
		$("#rzTab2").parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
	}else{
		$("#rzTab1").parent("li").addClass("bbd43c33");
		$("#rzTab1").parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
	}
//  选择tab
	$("input[name='rzTab']").change(function () {
	    $(this).parents("ul").children("li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
	    $(this).parents("ul").children("li").removeClass("bbd43c33");
	    $(this).parents("ul").children("li").children("label").removeClass("f20");
	    if ($(this).attr("checked") == "checked") {
	        $(this).parent("li").addClass("bbd43c33");
	        $(this).parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
	    }
	    if ($(this).val() == "2") {//资方
	    	console.log("资方");
			localStorage["ORGTYPE"] = 1;//角色
			$("#tubiao").removeClass("none");
			checkAuthentication(1,'',null);
	    } else if($(this).val() == "1") {
	    	console.log("出票方");
			$("#tubiao").removeClass("none");
			checkAuthentication(0,'',null);
	    }else if($(this).val() == "0") {
	    	inspectJdCib();
	    }
	})
	 $(".toggle").toggle(function(){
	     $(this).html('<img src="https://img.utiexian.com/website/discount/up.png" alt="" width="20" height="12" class="mt16">');
	     $(this).parent().next(".toggleCon").slideUp(100);
	 },function(){
	     $(this).html('<img src="https://img.utiexian.com/website/discount/down.png" alt="" width="20" height="12" class="mt16">');
	     $(this).parent().next(".toggleCon").slideDown(100);
	 });
	//选择开户行
    $(".bankBtn").click(function(){
    	console.log("选择");
        $("#maskWindow").removeClass("none");
        $("#bankWindow").removeClass("none"); //选择开户行
    });
});

//关闭按钮
$("#closeBtn , .closeBtn").click(function(){
    $("#maskWindow").addClass("none");
    $("#bnsWindow").addClass("none"); //企业
    $("#orgWindow").addClass("none"); //机构
});
function loadBank(){
	var bankBranch = $("#bank_branch").val();
	var url = "${bootAppPath}/cib/query/back";
	var params = {bank_branch:bankBranch,memberId:memberId};
	var res = bootPost(url,params);
	if(res!=null){
		var data = res.data;
		console.log(data);
		if(typeof(data.data.return_msg)!="undefined"){
			alert(data.data.return_msg+"，请联系客服");
		}else{
			var html = "";
			if(typeof(data.data.banks)!="undefined"){
				bankList = data.data.banks;
				pageIndex=1;
				if(pageIndex == 1){
					$("#prev").addClass("none");
				}else{
					$("#prev").removeClass("none");
				}
				if(bankList.length > 10){
					$("#next").removeClass("none");
					for(var i=0;i<10;i++){
						banks[i] = bankList[((pageIndex-1)*pageSize)+i];
						html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
		                html += "<div class='flex-row w flex-justify-space-between'>";
		                html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
		                html += "<div>"+banks[i].bank_branch+"</div>";
		                html += "</div>";
		                html += "</li>";
					}
				}else{
					$("#next").addClass("none");
					for(var i=0;i<bankList.length;i++){
						banks[i] = bankList[((pageIndex-1)*pageSize)+i];
						html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
		                html += "<div class='flex-row w flex-justify-space-between'>";
		                html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
		                html += "<div>"+banks[i].bank_branch+"</div>";
		                html += "</div>";
		                html += "</li>";
					}
				}
				
				$("#banks").html("");
				$("#banks").append(html);
				$("#promptStr").addClass("none");
			}else{
				$("#banks").html("");
				$("#promptStr").removeClass("none");
			}
		}
	}
}
function prevPage(){
	pageIndex = (pageIndex-1) < 1 ? pageIndex:pageIndex-1;
	if(pageIndex == 1){
		$("#prev").addClass("none");
	}else{
		$("#prev").removeClass("none");
	}
	if(bankList.length/pageSize>pageIndex){
		$("#next").removeClass("none");
	}else{
		$("#next").addClass("none");
	}
	console.log(pageIndex);
	var html = "";
	$("#banks").html("");
	
	for(var i=0;i<10;i++){
		banks[i] = bankList[((pageIndex-1)*pageSize)+i];
		html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
        html += "<div class='flex-row w flex-justify-space-between'>";
        html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
        html += "<div>"+banks[i].bank_branch+"</div>";
        html += "</div>";
        html += "</li>";
	}
	$("#banks").append(html);
}

function nextPage(){
	pageIndex = (pageIndex+1) > (bankList.length/pageSize) ? pageIndex:pageIndex+1;
	console.log(pageIndex);
	if(pageIndex == 1){
		$("#prev").addClass("none");
	}else{
		$("#prev").removeClass("none");
	}
	if(bankList.length/pageSize>pageIndex){
		$("#next").removeClass("none");
	}else{
		$("#next").addClass("none");
	}
	var html = "";
	$("#banks").html("");
	if(bankList.length-pageIndex*10 > 10){
		for(var i=0;i<10;i++){
			banks[i] = bankList[((pageIndex-1)*pageSize)+i];
			html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
	        html += "<div class='flex-row w flex-justify-space-between'>";
	        html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
	        html += "<div>"+banks[i].bank_branch+"</div>";
	        html += "</div>";
	        html += "</li>";
		}
	}else{
		for(var i=0;i<bankList.length-pageIndex*10;i++){
			banks[i] = bankList[((pageIndex-1)*pageSize)+i];
			html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
	        html += "<div class='flex-row w flex-justify-space-between'>";
	        html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
	        html += "<div>"+banks[i].bank_branch+"</div>";
	        html += "</div>";
	        html += "</li>";
		}
	}
	
	$("#banks").append(html);
	console.log(banks);
}
function choseBank(i){
	console.log(i);
	$("#bankstr").val(banks[i].bank_branch+"("+banks[i].cnaps_code+")");
	$("#bankNo").val(banks[i].bank_no);
	$("#CnapsCode").val(banks[i].cnaps_code);
	$("#bankAcctBankBranch").val(banks[i].bank_branch);
	$("#maskWindow").addClass("none");
	$("#alertWindow").addClass("none"); //温馨提示	
}
</script>