[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='绑定银行卡']
[@main.header currentmenu='1' topindex='1'/]

    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/form.css">

    <link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/cropper.min.css">
    <link rel="stylesheet" href="https://static.utiexian.com/css/ImgCropping/ImgCropping.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/yonyou/authentication.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/yonyou/mask.css">

    <script type="text/javascript" src="https://static.utiexian.com/js/ImgCropping/cropper.min.js"></script>
    <script type="text/javascript" src="https://static.utiexian.com/js/website/tab.js"></script>

<div class="w1200 ha bc mt20 mb20">
	<!--左侧菜单-->
	[@main.left remark='1' /]
	<div class="fl w997 box-shadow bcwhite box-sizing bc pl50 pr50 pt30 pb30">
	    <!-- 标题 -->
	    <div class="clearfix bbe0e0e0 pb10">
	        <div class="fl">银行卡绑定</div>
	    </div>
	    <!-- 标题 end -->
	
	    <!-- 开户进度 -->
	    <div class="clearfix w570 ml225 oh pt30 pb30">
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng1.png" alt="申请开户">
	        </div>
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng2-1.png" alt="审核">
	        </div>
	        <div class="fl w_33">
	            <img src="https://img.utiexian.com/website/180903jingdong/kaihu/liucheng3-1.png" alt="完成开户">
	        </div>
	    </div>
	    <!-- 开户进度 end -->
	
	    <!--切换-->
	    <div class="w570 bc bbe0e0e0">
	        <ul class="w300 box-shadow box-sizing h40 orderTabNew mt10">
	            <li class="w150 h40">
	                <a class="h40 lh40 f14 tabChecked" href="javascript:void(0);">
	                    <label for="bankTab1"><input type="radio" name="bankTab" value="0" class="orderTabBtn" id="bankTab1" checked>仅出票</label>
	                </a>
	            </li>
	            <li class="w150 h40">
	                <a class="h40 lh40 f14" href="javascript:void(0);">
	                    <label for="bankTab2"><input type="radio" name="bankTab" value="1" class="orderTabBtn" id="bankTab2">出票+收票</label>
	                </a>
	            </li>
	        </ul>
	    </div>
	    <!--切换 end-->
	
	    <!-- 内容 -->
	    <div class="w570 bc">
	        <div class="tab1">
	            <ul class="formGroup f14 c2d2d2d lh20">
	                <li class="formItem">
	                    <label for="" class="w120">户名：</label>
	                    <input type="text" id="blicCompanyName" class="w360" readonly value="">
	                </li>
	                <li class="formItem">
	                    <label for="" class="w120">开户行：</label>
	                    <select class="w320 select320 selectBank" id="selectBank" oninput="selectBank(this);"></select>
	                </li>
	                <li class="formItem none" id="zhushi">
	                    <p class="ml120 c868585">只有关联指定银行对公账户方可收票。京东目前已经达成合作的指定银行有：平安银行<a href="${basePath}/PAB.pdf" style="text-decoration:underline;" download>(授权流程下载)</a>、中信银行<a href="${basePath}/ECITIC.pdf" style="text-decoration:underline;" download>(授权流程下载)</a>。</p>
	                </li>
	                <li class="formItem">
	                    <label for="" class="w120">联行号：</label>
	                    <input type="text" class="w360 bae0e0e0 br3 cp souchBank" id="lianhanghao" readonly placeholder="请选择联行号" onclick="lianProvice()">
	                </li>
	                <li class="formItem">
	                    <label for="" class="w120">账号：</label>
	                    <div></div>
	                    <input type="text" id="occBankAccount" class="w360" placeholder="请输入绑定的银行卡">
	                </li>
	
	                <li class="formItem">
	                    <p class="ml120 c868585">点击申请鉴定按钮，关联银行账号里会收到一笔小额款项，将收到的金额填写上，最后点击“完成开户”，即可完成开户。</p>
	                </li>
	                <li class="formItem">
	                    <button type="button" onclick="save();">申请开户</button>
	                </li>
	            </ul>
	        </div>
	    </div>
	    <!-- 内容 end -->
	
	    <div id="souchBank" class="bcwhite w360 pl10 pr10 box-shadow pa f14 lh30 none">
	        <div class="bbe0e0e0">请选择联行号</div>
	        <ul class="clearfix oh bbe0e0e0 ble0e0e0 mt10 tab-head">
	            <li class="fl pl20 pr20 bte0e0e0 bre0e0e0 cp selected lian_provice" onclick="changeTab(1)">省级</li>
	            <li class="fl pl20 pr20 bte0e0e0 bre0e0e0 cp none lian_city" onclick="changeTab(2)">市级</li>
	            <li class="fl pl20 pr20 bte0e0e0 bre0e0e0 cp none lian_bank" onclick="changeTab(3)">分行名</li>
	        </ul>
	        <ul class="tab-contents h200">
	            <li class="tab-content h oya myScrollbar" id="provice_content">
	                <ul class="tc f12 clearfix oh mt10" id="provice">
	                    
	                </ul>
	            </li>
	            <li class="tab-content h oya myScrollbar none" id="city_content">
	                <ul class="tc f12 clearfix oh mt10" id="city">
	                    
	                </ul>
	            </li>
	            <li class="tab-content h oya myScrollbar none" id="name_content">
	                <ul class="" id="name">
	                    
	                </ul>
	            </li>
	        </ul>
	    </div>
	</div>
	<div class="cb"></div>
</div>

<input type="hidden" id="lian_provice">
<input type="hidden" id="lian_city">
<input type="hidden" id="lian_bank">
<input type="hidden" id="bankCode">

<input type="hidden" id="occBankName">
<input type="hidden" id="occBankCode">
<input type="hidden" id="occBankChildName">
<input type="hidden" id="occBankChildCode">

[@main.footer/]
[/@main.body]
<script type="text/javascript">
var memberId = '${member.id}';
    $(function(){
    	var blicCompanyName = localStorage.getItem("blicCompanyName");
    	$("#blicCompanyName").val(blicCompanyName);
      //  loadBank();
        $("#souchBank").css({"top": $(".souchBank").offset().top, "left":$(".souchBank").offset().left });
    });
    
	/**
	* 加载银行
	*/
	function loadBank(){
		var url = '${bootAppPath}/jdjr/querybankinfo';
 		var params = {};
 		var data = bootPost(url,params);
 		if(data != null){
 			if(data.data.response == 'success'){
 				if(data.data.data!=null){
 					var bankTab = $("input[name='bankTab']:checked").val();
 					var html = "<option value='-1'>请选择开户行</option>";
 					if(bankTab == 0){
 						for(var i =0;i<data.data.data.list.length;i++){
 	 						html += "<option value='"+data.data.data.list[i].bankCode+"'>"+data.data.data.list[i].bankName+"</option>";
 	 					}
 					}else if(bankTab == 1){
 						for(var i =0;i<data.data.data.list.length;i++){
 							if(data.data.data.list[i].bankCode ==302 || data.data.data.list[i].bankCode ==307){
								html += "<option value='"+data.data.data.list[i].bankCode+"'>"+data.data.data.list[i].bankName+"</option>";
 							}
 	 					}
 					}
 					
 					$("#selectBank").html(html);
 				}
 			}else{
 				alert(data.data.msg);
 			}
 		}
     };
     
	/**
	* 用户加载联行号省份
	*/
	function lianProvice(){
		var bankCode = $("#selectBank").val();
		if(bankCode == -1){
			alert("请先选择开户行！")
			return;
		}
		$("#souchBank").removeClass("none");
		$(".lian_provice").html("省份");
		$(".lian_provice").removeClass("none");
		$(".lian_city").addClass("none");
		$(".lian_bank").addClass("none");
		
		$("#provice_content").removeClass("none");
		$("#city_content").addClass("none");
		$("#name_content").addClass("none");
		
		var url = '${bootAppPath}/jdjr/queryprovince';
 		var params = {};
 		var data = bootPost(url,params);
 		if(data != null){
 			if(data.data.response == 'success'){
 				if(data.data.data!=null){
 					var html = "";
 					for(var i =0;i<data.data.data.list.length;i++){
 						html += "<li class='fl wmin90 cp' onclick=provice('"+data.data.data.list[i].provinceCode+"','"+data.data.data.list[i].provinceName+"')>"+data.data.data.list[i].provinceName+"</li>";
 					}
 					$("#provice").html(html);
 				}
 			}else{
 				alert(data.data.msg);
 			}
 		}
	}
	
	/**
	*用户选择省份
	*/
	function provice(code,name){
		$("#lian_provice").val(code);
		$(".lian_provice").html(name);
		$(".lian_city").html("城市");
		lianCity(code);
	};
     
	/**
	 * 用户加载联行号城市
	 */
	function lianCity(code){
		var url = '${bootAppPath}/jdjr/querycity';
		var params = {provinceCode:code};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$(".lian_city").removeClass("none");
				$("#city_content").removeClass("none");
				$("#provice_content").addClass("none");
				console.log(data);
				if(data.data.data!=null){
					var html = "";
					for(var i =0;i<data.data.data.list.length;i++){
						html += "<li class='fl wmin90 cp' onclick=city('"+data.data.data.list[i].cityCode+"','"+data.data.data.list[i].cityName+"')>"+data.data.data.list[i].cityName+"</li>";
					}
					$("#city").html(html);
				}
			}else{
				alert(data.data.msg);
			}
		}
	}
	
	/**
	*用户选择城市
	*/
	function city(code,name){
		$("#lian_city").val(code);
		$(".lian_city").html(name);
		lianBank(code);
	};
	
	/**
	 * 用户加载联行号支行
	 */
	 function lianBank(code){
		var url = '${bootAppPath}/jdjr/querybankcnapsinfo';
		var bankCode = $("#selectBank").val();
		var params = {bankCode:bankCode,cityCode:code};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$(".lian_bank").removeClass("none");
				$("#name_content").removeClass("none");
				$("#city_content").addClass("none");
				console.log(data);
				if(data.data.data!=null){
					var html = "";
					for(var i =0;i<data.data.data.list.length;i++){
						html += "<li onclick=selectBranch('"+data.data.data.list[i].bankCnapsCode+"','"+data.data.data.list[i].bankCnapsName+"')>"+data.data.data.list[i].bankCnapsName+"</li>";
					}
					$("#name").html(html);
				}
			}else{
				alert(data.data.msg);
			}
		}
	}
     
	$("input[name='bankTab']").click(function () {
		var bankTab = $("input[name='bankTab']:checked").val();
		if (bankTab == 0) {
			$("#zhushi").addClass("none");
		}else if (bankTab == 1) {
			$("#zhushi").removeClass("none");
		}
		loadBank();
		$("#lianhanghao").val("");
	});

    function changeTab(tab) {
        if(tab==1){
        	$(".lian_provice").removeClass("none");
        	$(".lian_city").addClass("none");
        	$(".lian_bank").addClass("none");
        	
        	$("#provice_content").removeClass("none");
        	$("#city_content").addClass("none");
        	$("#name_content").addClass("none");
        }else if(tab==2){
        	$(".lian_provice").removeClass("none");
        	$(".lian_city").removeClass("none");
        	$(".lian_bank").addClass("none");
        	
        	$("#provice_content").addClass("none");
        	$("#city_content").removeClass("none");
        	$("#name_content").addClass("none");
        }else{
        	
        }
    }
    
    /**
    * 选择支行
    */
    function selectBranch(code,name){
    	$("#souchBank").addClass("none")
    	$("#lianhanghao").val(code+":"+name)
    	$("#occBankChildName").val(name);
    	$("#occBankChildCode").val(code);
    }
    
    /**
     * 选择银行
     */
	function selectBank(obj){
		var options=$("#selectBank option:selected"); //获取选中的项
    	$("#occBankName").val(options.text());
    	$("#occBankCode").val(options.val());
	}
    
    /**
    * 保存开户
    */
    function save(){
    	var blicCompanyName = localStorage.getItem("blicCompanyName");
		var blicProvince = localStorage.getItem("blicProvince");
		var blicCity = localStorage.getItem("blicCity");
		var blicAddress = localStorage.getItem("blicAddress");
		var blicScope = localStorage.getItem("blicScope");
		var blicLongTerm = localStorage.getItem("blicLongTerm");
		var blicValidityEnd = localStorage.getItem("blicValidityEnd");
		var blicCardType = localStorage.getItem("blicCardType");
		var blicUscc = localStorage.getItem("blicUscc");
		var blicCardNo = localStorage.getItem("blicCardNo");
		var blicObaCardNo =	localStorage.getItem("blicObaCardNo");
		var blicTrcCardNo =	localStorage.getItem("blicTrcCardNo");
		var blicUrlA = localStorage.getItem("blicUrlA");//图片 
		
		var lepLongTerm = localStorage.getItem("lepLongTerm");
		var lepValidityEnd = localStorage.getItem("lepValidityEnd");
		var lepCardType = localStorage.getItem("lepCardType");
		var lepUrlA = localStorage.getItem("lepUrlA");
		var lepUrlB = localStorage.getItem("lepUrlB");
		var lepCardNo = localStorage.getItem("lepCardNo");
		var lepName = localStorage.getItem("lepName");
    	
		var occBankAccount = $("#occBankAccount").val();
		var occBankName = $("#occBankName").val();
		var occBankCode = $("#occBankCode").val();
		var occBankChildName = $("#occBankChildName").val();
		var occBankChildCode = $("#occBankChildCode").val();
		
		var url = '${bootAppPath}/jdjr/save';
		var params = {memberId:memberId,blicCompanyName:blicCompanyName,blicProvince:blicProvince,blicCity:blicCity,blicAddress:blicAddress,
				blicScope:blicScope,blicLongTerm:blicLongTerm,blicValidityEnd:blicValidityEnd,blicCardType:blicCardType,blicUscc:blicUscc,
				blicCardNo:blicCardNo,blicObaCardNo:blicObaCardNo,blicTrcCardNo:blicTrcCardNo,blicUrlA:blicUrlA,lepLongTerm:lepLongTerm,
				lepValidityEnd:lepValidityEnd,lepCardType:lepCardType,lepUrlA:lepUrlA,lepUrlB:lepUrlB,lepCardNo:lepCardNo,lepName:lepName,
				occBankAccount:occBankAccount,occBankName:occBankName,occBankCnap:occBankCode,occBankChildName:occBankChildName,
				occBankChildCode:occBankChildCode,companyType:"ENTER"};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				location.href = "${basePath}/jd/examine";
			}else{
				alert(data.data.msg);
			}
		}
    }
</script>
