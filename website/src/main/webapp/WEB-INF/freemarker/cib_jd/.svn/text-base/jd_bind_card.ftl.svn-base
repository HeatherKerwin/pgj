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
	
	    <!-- 内容 -->
	    <div class="w570 bc">
	        <div class="tab1">
	            <ul class="formGroup f14 c2d2d2d lh20">
	                <li class="formItem">
	                    <p class="ml120 c868585 ce84c3d">收票需绑定平安银行或中信银行的对公账户。出票可以绑定任意银行的对公账户。</p>
	                </li>
	                <li class="formItem">
	                    <label for="" class="w120">户名：</label>
	                    <input type="text" id="blicCompanyName" class="w360" readonly value="">
	                </li>
	                <li class="formItem">
	                    <label for="" class="w120">开户行：</label>
	                    <select class="w320 select320 selectBank" id="selectBank" oninput="selectBank(this);"></select>
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
	                    <button type="button" onclick="save();">申请绑卡</button>
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
<input type="hidden" id="lian_provice_name">
<input type="hidden" id="lian_city">
<input type="hidden" id="lian_city_name">
<input type="hidden" id="lian_bank">
<input type="hidden" id="bankCode">

<input type="hidden" id="occBankName">
<input type="hidden" id="occBankCode">
<input type="hidden" id="cnapBankCode">
<input type="hidden" id="occBankChildName">
<input type="hidden" id="occBankChildCode">

<link href="https://static.utiexian.com/css/common/select2.min.css" rel="stylesheet" />
<script src="https://static.utiexian.com/js/common/select2.min.js"></script>
<script src="https://static.utiexian.com/js/common/zh-CN.js"></script>
[@main.footer/]
[/@main.body]
<script type="text/javascript">
var memberId = '${member.id}';
    $(function(){
    	$("#selectBank").select2({
    		language: "zh-CN"
    	});
    	var blicCompanyName = localStorage.getItem("blicCompanyName");
    	$("#blicCompanyName").val(blicCompanyName);
        loadBank();
        $("#souchBank").css({"top": $(".souchBank").offset().top, "left":$(".souchBank").offset().left });
    	
        $("#selectBank").on("select2:select", function (evt) {
            //这里是选中触发的事件
            //evt.params.data 是选中项的信息
            selectBank(evt.params.data);
        });
    });
    
	/**
	* 加载银行
	*/
	function loadBank(){
		var url = '${bootAppPath}/jdjrcard/queryBankList';
 		var params = {};
 		var data = bootPost(url,params);
 		if(data != null){
 			if(data.data.response == 'success'){
 				if(data.data.data!=null){
 					var html = "<option value='-1'>请选择开户行</option>";
					for(var i =0;i<data.data.data.bankListResult.length;i++){
 						html += "<option data-Id='"+data.data.data.bankListResult[i].cnapBankCode+"' value='"+data.data.data.bankListResult[i].bankCode+"'>"+data.data.data.bankListResult[i].bankName+"</option>";
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
		
		var url = '${bootAppPath}/jdjrcard/queryProvincesAndCitys';
 		var params = {};
 		var data = bootPost(url,params);
 		if(data != null){
 			if(data.data.response == 'success'){
 				if(data.data.data!=null){
 					var html = "";
 					for(var i =0;i<data.data.data.provinces.length;i++){
 						html += "<li class='fl wmin90 cp' onclick=provice('"+data.data.data.provinces[i].provinceId+"','"+data.data.data.provinces[i].provinceName+"')>"+data.data.data.provinces[i].provinceName+"</li>";
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
		$("#lian_provice_name").val(name);
		$(".lian_provice").html(name);
		$(".lian_city").html("城市");
		lianCity(code);
	};
     
	/**
	 * 用户加载联行号城市
	 */
	function lianCity(code){
		var url = '${bootAppPath}/jdjrcard/queryProvincesAndCitys';
		var params = {provinceId:code};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$(".lian_city").removeClass("none");
				$("#city_content").removeClass("none");
				$("#provice_content").addClass("none");
				console.log(data);
				if(data.data.data!=null){
					var html = "";
					for(var i =0;i<data.data.data.citys.length;i++){
						html += "<li class='fl wmin90 cp' onclick=city('"+data.data.data.citys[i].cityId+"','"+data.data.data.citys[i].cityName+"')>"+data.data.data.citys[i].cityName+"</li>";
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
		$("#lian_city_name").val(name);
		$(".lian_city").html(name);
		lianBank(code);
	};
	
	/**
	 * 用户加载联行号支行
	 */
	 function lianBank(code){
		var url = '${bootAppPath}/jdjrcard/queryBankCnapsList';
		var cnapBankCode = $("#cnapBankCode").val();
		var params = {"cnapBankCode":cnapBankCode,"cityId":code};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$(".lian_bank").removeClass("none");
				$("#name_content").removeClass("none");
				$("#city_content").addClass("none");
				console.log(data);
				if(data.data.data!=null){
					var html = "";
					for(var i =0;i<data.data.data.bankChildInfoList.length;i++){
						html += "<li onclick=selectBranch('"+data.data.data.bankChildInfoList[i].bankCnaps+"','"+data.data.data.bankChildInfoList[i].bankChildName+"')>"+data.data.data.bankChildInfoList[i].bankChildName+"</li>";
					}
					$("#name").html(html);
				}
			}else{
				alert(data.data.msg);
			}
		}
	}
     
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
		var cnapBankCode = $(options).attr("data-id");
    	$("#occBankName").val(options.text());
    	$("#occBankCode").val(options.val());
    	$("#cnapBankCode").val(cnapBankCode);
	}
    
    /**
    * 保存开户
    */
    function save(){
   	    var bankAccountName = $("#blicCompanyName").val();//账号名称
		var bankAccountNo = $("#occBankAccount").val();//银行账号
		var bankName = $("#occBankName").val();//开户行名称
		var bankCode = $("#occBankCode").val();//银行编码
		var bankChildName = $("#occBankChildName").val();//支行银行全称
		var bankCnaps = $("#occBankChildCode").val();//开户行联行号
		var provinceId = $("#lian_provice").val();
		var provinceName = $("#lian_provice_name").val();
		var cityId = $("#lian_city").val();
		var cityName = $("#lian_city_name").val();
		
		var url = '${bootAppPath}/jdjrcard/save';
		var params = {memberId:memberId,bankAccountName:bankAccountName,bankAccountNo:bankAccountNo,bankName:bankName,
				bankCode:bankCode,bankChildName:bankChildName,bankCnaps:bankCnaps,provinceId:provinceId,provinceName:provinceName,
				cityId:cityId,cityName:cityName};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				localStorage.setItem("payOrderId",data.data.data.payOrderId);
				localStorage.setItem("blicCompanyName",bankAccountName);
				localStorage.setItem("bankAccountNo",bankAccountNo);
				location.href = "${basePath}/jd/bind/small/money";
			}else{
				alert(data.data.msg);
			}
		}
    }
</script>
