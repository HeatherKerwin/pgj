[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
[@main.header currentmenu='1' topindex='2'/]


<!--贴现输入表单-->
<div class="mt16 w1200 bc">
	<input type=hidden name="membermobile" value="${member.mobile}">
    <input type="hidden" id="addressId" name="addressId"  value="">
    <div class="w1200 h52 bcwhite">
        <ul class="f16 c2d2d2d lh50 bae0e0e0 tc TXtab">
            <li id="yptx_d" class="w250 lh50 fl bre0e0e0">银票电票</li>
            <li id="yptx_z" class="w250 lh50 fl bre0e0e0">银票纸票</li>
            <li id="sptx_d" class="w250 lh50 fl bre0e0e0">商票电票</li>
            <li id="sptx_z" class="w250 lh50 fl bre0e0e0 bbd43c33 cd43c33">商票纸票</li>
        </ul>
    </div>
    <div class="mt12 bc bae0e0e0 bcwhite pl20 pr20 pb15 f14 c2d2d2d">
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">票面金额：</div>
            <input type="text" id="allmoney" maxlength="13" name="allmoney" placeholder="请输入金额" class="validate[custom[number]],validate[custom[money]],validate[required] fl w129 h27 lh27 bae0e0e0 mt8 ti8">
            <div class="fl f14 lh45 ml10">万元</div>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">承兑企业：</div>
            <input type="text" name="bank" id="bank"  placeholder="请输入承兑企业全称" class="validate[required],validate[max[60]] w400 h27 lh27 bae0e0e0 mt8 ti8">
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">开票日期：</div>
            <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline mt8" id="first" />
            <label class="fl w30 h27 rili mt8" for="first"></label>
            <input name="first" type="hidden">
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">交易日期：</div>
            <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline mt8" id="start" />
            <label class="fl w30 h27 rili mt8" for="start"></label>
            <input name="start" type="hidden">
            <input type="hidden" name="jxts" value="183" />
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">到期日期：</div>
            <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline mt8" id="end" />
            <label class="fl w30 h27 rili mt8" for="end"></label>
            <input name="end" type="hidden">
        </div>
        <div class="w h45 xuxian lh45 zhi">
            <div class="fl tl w100 fb">出票地址：</div>
            <p class="fl" id="Noaddress">暂未添加任何地址</p>
            <p class="fl" id="address"></p>
            <p class="fl ml15 c717583" id="phone"></p>
            <input type="hidden" value="" id="sex"/>
            <input type="hidden" value="" id="name"/>
            <input type="hidden" value="" id="other"/>
            <input type="hidden" value="" id="cityId"/>
            <input type="hidden" value="" id="place"/>
            <input type="button" value="选择地址" class="fl bcwhite b0 h27 mt10 ml40 c3366cc cp changeBtn">
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">背书：</div>
            <div class="fl TXjj1 w100 h28 mt8 mr10">
                <div class="reduce fl" onClick="setAmount1.reduce('#endorse')" href="javascript:void(0)">
                    <img src="${image_url}/website/discount/jian.png" class="w20 h2 mt10 ml5 vm"/>
                </div>
                <input readonly="readonly" type="text" name="endorse" value="0" id="endorse" onKeyUp="setAmount1.modify('#endorse')" class="fl b0 bcn f14 lh27 tc w40 h27 ml5" />
                <div class="add mt4 ml5 fl" onClick="setAmount1.add('#endorse')" href="javascript:void(0)">
                    <img src="${image_url}/website/discount/jia.png" class="w20 h20" />
                </div>
            </div>
            <p class="lh45 fl">手</p>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">回头票：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes4" class="none" name="backEndorse" value="T"><label class="fl tc w46 h27 br3 returnTicket_opt_css" for="yes4">是</label></li>
                <li class="fl"><input type="radio" id="no4" class="none" name="backEndorse" value="F"><label class="fl tc w46 h27 br3 returnTicket_opt_css" for="no4">否</label></li>
            </ul>
            <label class="fl tl fb lh45">指票据背书中出现回头背书现象的票据，如A公司背给B公司后，B公司又背给了A公司。</label>
        </div>
        <div class="w h45 xuxian zhi">
            <div class="fl tl w100 fb lh45">是否需要上门：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes1" class="none" name="needTodoor" value="1" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d shangmen_opt_css" for="yes1">是</label></li>
                <li class="fl"><input type="radio" id="no1" class="none" name="needTodoor" value="0"><label class="fl tc w46 h27 br3 shangmen_opt_css" for="no1">否</label></li>
            </ul>
        </div>
        <div class="w h130 xuxian">
            <div class="fl tl w100 fb lh45">备注（选填）：</div>
            <textarea style='resize: none;' maxlength="200" name="remarks" id="remarks" class="fl ti8 w500 h100 bae0e0e0 bcwhite mt8 f14 pt10" name="" placeholder="备注最多可输入200个字......"></textarea>
        </div>
        <div class="w h45 xuxian zhi">
            <div class="fl tl w100 fb lh45">票已开出：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes2" class="none" name="hasTicket" value="0" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d kaichu_opt_css" for="yes2">是</label></li>
                <li class="fl"><input type="radio" id="no2" class="none" name="hasTicket" value="1"><label class="fl tc w46 h27 br3 kaichu_opt_css" for="no2">否</label></li>
            </ul>
        </div>
        <div class="w mt20 pb8 pl100 xuxian tupian">
            <p>请上传正面的清晰图片（可遮掉右上角票号），电子汇票请使用电脑截图。</p>
            <div class="w">
	            <div class="mt30 w240 dsib mr20">
	                <label for="fileToUpload" class="w240 h150">
	                	<img class="w240 h150 dsb" id="img" src="${image_url}/website/discount/photo.png" >
	                	<input type="file" name="fileToUpload" id="fileToUpload" class="none">
	                	<input type="hidden" name="picpath" id="picpath"  style="display:none;">
	                </label>
	            </div>
            </div>
        </div>
    </div>
    <div class="h50 bcf9f9f9 ble0e0e0 bre0e0e0 bbe0e0e0 lh50 pl20">
        <input type="button" id="button" value="生成订单" class="fr f18 cwhite bcd43c33 w166 lh50 b0" />
    </div>
    <div class="w h40 bcwhite ble0e0e0 bre0e0e0 bbe0e0e0">
    </div>
</div>
  [@main.right /]
  
<!--贴现地址弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w800 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>编辑出票地址</div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
            	<!-- 已有地址选择 -->
            	<div class="flex-row flex-direction-column w none" id="palceListWindow">
	                
            	</div>
                <div class="flex-row h34 mt10 c3366cc cp none" id="placeAddWindow">
                    + 新增地址 <img src="https://img.utiexian.com/website/discount/up.png" id="src" width="10" height="6" class="mt13 ml12">
                </div>
            	<!-- 新增贴现地址 -->
            	<div class="flex-row flex-direction-column w lh30 none" id="placeEditWindow">
                    <div class="flex-row  h34 mt10">
                        <div class="w120">联系人：</div>
                        <input type="text" class="validate[required] w240 bae0e0e0 br3 ti10 h35" id="tjname" placeholder="请输入您的名字" />
                    </div>
                    <div class="flex-row  h34 mt10">
                        <div class="w120">联系电话：</div>
                        <input type="text" class="validate[required],validate[custom[phone]] w240 bae0e0e0 br3 ti10 h35" id="tjmobile" placeholder="请输入您的联系电话" />
                    </div>
                    <div class="flex-row  h34 mt10">
                        <div class="w120">我的位置：</div>
                        <input type="text" class="validate[required] w320 bae0e0e0 br3 ti10 h35" id="tjaddress" placeholder="请输入您的位置，如北京市朝阳区" />
                    </div>
                    <div class="flex-row mt10">
                        <div class="w120">详细地址：</div>
                        <textarea class="w300 h50 bae0e0e0 br3 pt10 pr10 pl10 mt10" id="tjother" maxlength="200" placeholder="请输您的详细地址"></textarea>
                    </div>
                    <div class="flex-row mt10">
                        <div class="w120">交易城市：</div>
                        <select id="initcity" class="w320 h40 select320 ti10"></select>
                    </div>
                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt20">
                    	<input type="hidden" value="" id="tjid"/>
                        <input type="button" value="保存" onclick="saveAddress();" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 mr20">
                    </div>
                </div>
            </div>
       </div>
   </div>
</div>
<!--提示弹窗-->
<div class="wa pf t400 r90 zi10">
    <!--问题-->
    <div class="w190 pr top z12">
        <div class="w190 h30 f14 lh30 bc778ffd cwhite tc">常见问题</div>
        <p class="wa f12 pt25 pb20 lh30 c2d2d2d bcwhite cp pl10 dsb" id="problem">
            1. 商票贴现步骤？
        </p>
        <div class="w190 bcf7f7f6 f12 lh30 pt25 pb20">
            <div class="ml10">如有其他问题，请详见</div>
            <div class="ml10">【帮助中心】-【常见问题】</div>
        </div>
    </div>
    <div class="w500 ha pa t25 r190 zi13 answer c777777 pt10 pb10 pl10 pr25 none" id="answer">
        <h2 class="f16 fb mt6">1. 商票贴现步骤？</h2>
        <p class="f14 ti20 lh18 mt10">用户点击商票贴现直接生成订单，无须缴纳保证金，票据管家收到订单后经过后台智能运算，会立即派送给所有符合条件的机构，机构在30分钟内可以针对这张票据报价后进行接单，一旦机构接单后会被推送至企业端，由企业来选择机构进行后续贴现交易。</p>
    </div>
</div>

<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>
<script type="text/javascript" src="${jsPath}/discount.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>

<style>
.addressList:HOVER{
	border: 1px solid #d43c33;
}
</style>
<script type="text/x-handlebars-template" id="ADDRESS">
{{#each data}}
	<div class="flex-row flex-justify-space-between pl10 pr10 h34 mt10 addressList">
		<label for="place{{id}}" class="flex-row cp flex-alignItems-center">
			<input type="radio" name="place" value="" id="place{{id}}" onclick="selectAddress('{{id}}');" class="radio2 w12 h12 mr10">
			<div>{{address}}<span>（{{name}}）</span></div>
			<div class="ml15 c717583">{{mobile}}</div>
		</label>
		<div class="flex-row editBtn">
			<img src="https://img.utiexian.com/website/discount/edit.png" onclick="updateAddress('{{id}}');" width="18" height="18" class="mt8">
		</div>
	</div>
{{/each}}
</script>
<script type="text/javascript">
	var memberId = ${member.id};
	
	$(function(){
		var img_upload=document.getElementById("fileToUpload");
		$("#fileToUpload").val("");
		// 添加功能出发监听事件
		img_upload.addEventListener('change',readFile,false);
		
		loadDefaultAddress();
	});
		
	/**
	* boot 项目的图片上传
	*/
	function readFile(){
		var file=this.files[0];
		if(!/image\/\w+/.test(file.type)){ 
			alert("请确保文件为图像类型"); 
			return false; 
		}
		var reader=new FileReader();
		reader.readAsDataURL(file);
		reader.onload=function(){
			var index = this.result.indexOf(",");
			var base64Image =  this.result.substr(index+1);
			var url = '${bootAppPath}/upload/image';
			var params = {base64Image:base64Image};
			var data = bootPost(url,params);
			console.log(data);
			if(data != null){
				if(data.data.response == 'success'){
					$("#picpath").val(data.data.data.base64Image);
					$("#img").attr("src","${bootPic}"+data.data.data.base64Image);
				}
			}	
		}
	};

	//关闭按钮
	$("#closeBtn , .closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#palceListWindow").addClass("none"); //已有地址列表
	    $("#placeAddWindow").addClass("none"); //点击新建地址
	    $("#placeEditWindow").addClass("none"); //更改地址
	});
	
	//更改地址
	$(".changeBtn").click(function(){
	    $("#maskWindow").removeClass("none");
	    $("#palceListWindow").removeClass("none");
	    $("#placeAddWindow").removeClass("none");
	    loadAddressList();
	});
	
	//新建地址
    $("#placeAddWindow").toggle(function(){
    	emptyAddress();
    	var p= "上海市";//默认城市为上海
    	$("#src").attr("src","https://img.utiexian.com/website/discount/down.png")
		$("#initcity option[value='"+p+"']").attr("selected","selected");
	    $("#placeEditWindow").removeClass("none");
    },function(){
    	emptyAddress();
    	var p= "上海市";//默认城市为上海
    	$("#src").attr("src","https://img.utiexian.com/website/discount/up.png")
		$("#initcity option[value='"+p+"']").attr("selected","selected");
	    $("#placeEditWindow").addClass("none");
    });
	
	/**
	* 修改贴现地址
	*/
	function updateAddress(id){
		$(".addressList").removeClass("bad43c33");
		$("#place"+id).parents(".addressList").addClass("bad43c33");
		 $.ajax({
	 		type: "POST",
	      	url: "${basePath}/address/get",
	      	data: {addressId:id},
	      	dataType:"json",
	      	success:function(data){
				if(data.response == "success"){
					$("#maskWindow").removeClass("none");
				    $("#placeEditWindow").removeClass("none");
				    $("#src").attr("src","https://img.utiexian.com/website/discount/down.png")
				    
				    $("#tjname").val(data.data.name);
					$("#tjmobile").val(data.data.mobile);
					$("#tjaddress").val(data.data.address);
					$("#tjother").val(data.data.other);
					$("#tjid").val(data.data.id);
					var p= data.data.place;
					$("#initcity option[value='"+p+"']").attr("selected","selected");
	        	   
				}else{
	        	   alert(data.msg);
	           }
			}
		});
	};
	
	/**
	* 选择地址
	*/
	function selectAddress(id){
		var addressId = $("#addressId").val(id);
		$("#maskWindow").addClass("none");
	    $("#palceListWindow").addClass("none"); //已有地址列表
	    $("#placeAddWindow").addClass("none"); //点击新建地址
	    $("#placeEditWindow").addClass("none"); //更改地址
		loadDefaultAddress();
	}
	
	/**
	* 清空地址
	*/
	function emptyAddress(){
		$("#tjname").val("");
		$("#tjmobile").val("");
		$("#tjaddress").val("");
		$("#tjother").val("");
		$("#tjplace").val("");
		$("#tjid").val("");
	}
	
	/**
	* 保存交易地址
	*/
	function saveAddress(){
		if(!$("#tjname").validationEngine("validate")){
			$("#tjname").focus();
			return ;
		 }
		 if(!$("#tjmobile").validationEngine("validate")){
			$("#tjmobile").focus();
			return ;
		 }
		 if(!$("#tjaddress").validationEngine("validate")){
			$("#tjaddress").focus();
			return ;
		 }
		 
		 if($.trim($("#tjid").val()).length == 0 && ($("input[name='place']").length-1) == 10){
			 alert("贴现地址已经10个，不能添加");
			 return ;
		 }
		 var name = $("#tjname").val();
		 
		 var mobile = $("#tjmobile").val();
		 var address = $("#tjaddress").val();
		 var other = $("#tjother").val();
		 var place = $("#initcity").val();
		 var id = $("#tjid").val();
		 var state = 1;
		
		 $.ajax({
			type: "POST",
	      	url: "${basePath}/address/save",
	      	data: {name:name,sex:1,mobile:mobile,address:address,other:other,place:place,
	      		id:id,memberId:memberId,state:state},
	      	dataType:"json",
	      	success:function(data){
	      		if(data.response == "success"){
	      			loadAddressList();
	      			emptyAddress();
	      		}else{
	      			alert("保存失败")
	      		}
	      	}
		 }); 
	};
	
	/**
	* 加载地址列表
	*/
	function loadAddressList(){
		$.ajax({
	 		type: "POST",
	      	url: "${basePath}/discountrecord/addresslist",
	      	data: {memberId:memberId},
	      	dataType:"json",
	      	success:function(data){
	            var source = $("#ADDRESS").html();
	            var template = Handlebars.compile(source);
	            var html = template(data);
	            $("#palceListWindow").html(html);
	            initCity();
	            
	     	 }
		});
	}
	
	/**
	* 初始化地图的城市选择
	*/
	function initCity(){
		$.ajax({
			type: "POST",
	      	url: "${basePath}/bisicmessage/init/city",
	      	data: {},
	      	dataType:"json",
	      	success:function(data){
	          	if(data.response == "success"){
	          		var phtml = "";
	                for(var i=0;i<data.c.length;i++){
	                    var provice = data.c[i];
	                    phtml += "<option value='"+provice.name+"'>"+provice.name+"</option>";
	                }
	                $("#initcity").html(phtml);
	          	}
	     	}
		});
	};
	
	/**
	* 加载默认贴现地址
	*/
	function loadDefaultAddress(){
		var addressId = $("#addressId").val();
		var data = {memberId:memberId};
		
		if(addressId!=null && addressId != ''){
			data.addressId = addressId;
		}
		 $.ajax({
			type: "POST",
	     	url: "${basePath}/address/get/default",
	     	data: data,
	     	dataType:"json",
	     	success:function(retValue){
	     		var data = retValue.data;
	     		if(data != null && data != ""){
	     			$("#Noaddress").addClass("none");
	     			$("#address").removeClass("none");
		   			$("#address").html(data.address);
		   			$("#phone").html(data.mobile);
		   			$("#other").val(data.other);
		   			$("#cityId").val(data.cityId);
		   			$("#sex").val(data.sex);
		   			$("#place").val(data.place);
		   			$("#name").val(data.name);
	     		}else{
	     			$("#address").addClass("none");
	     		}
	    	}
		 });
	};
	
	//计息天数
    function jixidate(){
    	var type1 = 1;
    	var type2 = $("#type2").val();
		var end = $("input[name='end']").val();
		var start = $("input[name='start']").val();
    	var allmoney = $("#allmoney").val();
    	
    	if($.trim(allmoney).length == 0){
    		alert("金额不能为空");
    		return ;
    	}
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/excel/price",
	     	data: {type1:type1,type2:type2,startDate:start,endDate:end,allmoneys:allmoney},
	     	dataType:"json",
	     	success:function(retValue){
	     		var data = retValue.jxts;
	     		if(data == 'undefined' || typeof(data) == 'undefined'){
		     		$("input[name='jxts']").val(0);
	     		}else{
		     		$("input[name='jxts']").val(data);
	     		}
	    	}
		 })	
    };
	
	//生成订单
	$("#button").click(function(){
		if($("input[type='button']").attr("disabled")){
			return;
		}
		
		if(!$("#bank").validationEngine("validate")){
			$("#bank").focus();
			return ;
		}
		
		if(!$("#allmoney").validationEngine("validate")){
			$("#allmoney").focus();
			return ;
		}
		
		if($.trim($("#allmoney").val()).length == 0 || $("#allmoney").val() == null){
			alert("请选择总金额");
			return ;
		};
		
		var map = new Map();
		var type1 = 1;//$("input:radio[name='type1']:checked").val();
		var allmoney = $("#allmoney").val();
		var bank = $("#bank").val();
		var end = $("input[name='end']").val();
		var start = $("input[name='start']").val();
		var print = $("input[name='first']").val();
		var acceptTime = $("input:radio[name='acceptTime']:checked").val();//期限
		var jxts = $("input[name='jxts']").val();
		var endorse = $("#endorse").val();
		var needTodoor = $("input:radio[name='needTodoor']:checked").val();//是否需要上门
		var backEndorse = $("input:radio[name='backEndorse']:checked").val();//是否是回头票
		var remarks = $("#remarks").val();
		var hasTicket = $("input:radio[name='hasTicket']:checked").val();//票是否开出
		var flawTicket = $("input:radio[name='flawTicket']:checked").val();//票是否瑕疵
		var draftNo = $("#draftNo").val();
		var cnapsCode = $("#cnapsCode").val();
		var picpath = $("#picpath").val();
		if(backEndorse==null||backEndorse==""){
			layer.alert("请选择票据是否是回头票");
			return ;
		}

		var memberId = ${member.id};
		var url = '${bootAppPath}/discountrecordsp/tiexian';

		var params = {type1:type1,bank:bank,start:start,end:end,print:print,backEndorse:backEndorse,
				endorse:endorse,allmoney:allmoney,remarks:remarks,jxts:jxts,memberId:memberId};
		
		if(type1 == 1){//纸票
			params.needTodoor = needTodoor;
			params.hasTicket = hasTicket;
			
			var  membername= $("#name").val();
			var  membersex= $("#sex").val();
			var  membermobile= $("#phone").html();
			var  address= $("#address").html();
			var  cityId= $("#cityId").val();
			var  place= $("#place").val();
			
			if($.trim(address).length == 0){
				alert("请选择一个地址");
				return; 
			}
			
			params.memberName = membername;
			params.memberSex = membersex;
			params.memberMobile = membermobile;
			params.address = address;
			params.cityId = cityId;
			params.place = place; 
			
			if(hasTicket ==0){
				if($.trim($("#picpath").val()).length == 0 || $("#picpath").val() == null){
					alert("请选择上传的票面");
					return ;
				};
				params.picpath = picpath;
			}
		}else if(type1 == 2){//电票
			if(!$("#draftNo").validationEngine("validate")){
				$("#draftNo").focus();
				return ;
			}
		
			if(!$("#cnapsCode").validationEngine("validate")){
				$("#cnapsCode").focus();
				return ;
			}
			
			if($.trim($("#picpath").val()).length == 0 || $("#picpath").val() == null){
				alert("请选择上传的票面");
				return ;
			};
			
			params.acceptTime = acceptTime;
			params.picpath = picpath;
			params.draftNo = draftNo;
			params.cnapsCode = cnapsCode;
		}else{
			alert("出现异常，票据属性不能为空");
			return ;
		}
		$("input[type='button']").attr("disabled","disabled");//按钮禁用
		
		var data = bootPost(url,params);
		if(data.data.response == 'success'){
			map.put("discId", data.data.data.id);//类型
		}else{
			alert("保存数据失败");
			return;
		}
		map.put("_PAGE", "/discountrecord/discount_yp2");//必传
		map.put("ym", "sp");
		_OPENPAGE_FORM(map);
	});
		
    //日历
    !function(){
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
    }();
    //日期范围限制
//    贴现日期
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD',
        //min: laydate.now(), //设定最小日期为当前日期
        min: '1900-01-01', //设定最小日期
        max: '2099-06-16', //最大日期
        istime: true,
        istoday: false,
        choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
            $("input[name='start']").val(datas);
            jixidate();
        }
    };
//    到期日期
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD',
        min: laydate.now(),
        max: '2099-06-16',
        istime: true,
        istoday: false,
        choose: function(datas){
            start.max = datas; //结束日选好后，充值开始日的最大日期
            $("input[name='end']").val(datas);
            jixidate();
        }
    };
    laydate(start);
    laydate(end);
//    开票日期
    laydate({
        elem: '#first',
        format: 'YYYY-MM-DD',
        festival: true, //显示节日
        choose: function(datas){ //选择日期完毕的回调
        	$("input[name='first']").val(datas);
        }
    });

//    单选选择radio
    $(".bill_opt_css").click(function () {
   		 if ($(this).prev().val() == "2") {
                $(".SXdiv").removeClass("none");
                $(".zhi").addClass("none");
                $("#dian").parents("li").children("label").addClass("cwhite bce84c3d");
                $("#zhi").parents("li").children("label").removeClass("cwhite bce84c3d");
                $("#dian").attr("checked",true);
                $("#zhi").attr("checked",false);
                $(".tupian").removeClass("none");
          }
          else{
              $(".SXdiv").addClass("none");
              $(".zhi").removeClass("none");
              $("#zhi").parents("li").children("label").addClass("cwhite bce84c3d");
              $("#dian").parents("li").children("label").removeClass("cwhite bce84c3d");
              $("#zhi").attr("checked",true);
              $("#dian").attr("checked",false);
              var hasTicket = $("input:radio[name='hasTicket']:checked").val();//票是否开出
              if(hasTicket == 0){
            	  $(".tupian").removeClass("none");
              }else if(hasTicket == 1){
            	  $(".tupian").addClass("none");
              }
          }
    });
    $(".shangmen_opt_css").click(function () {
      if ($(this).prev().val() == "1") {
            $("#yes1").parents("li").children("label").addClass("cwhite bce84c3d");
            $("#no1").parents("li").children("label").removeClass("cwhite bce84c3d");
            $("#yes1").attr("checked",true);
            $("#no1").attr("checked",false);
      }
      else{
          $("#no1").parents("li").children("label").addClass("cwhite bce84c3d");
          $("#yes1").parents("li").children("label").removeClass("cwhite bce84c3d");
          $("#no1").attr("checked",true);
          $("#yes1").attr("checked",false);
      }
    });
    $(".kaichu_opt_css").click(function () {
      if ($(this).prev().val() == "0") {
            $("#yes2").parents("li").children("label").addClass("cwhite bce84c3d");
            $("#no2").parents("li").children("label").removeClass("cwhite bce84c3d");
            $("#yes2").attr("checked",true);
            $("#no2").attr("checked",false);
            $(".tupian").removeClass("none");
      }
      else{
          $("#no2").parents("li").children("label").addClass("cwhite bce84c3d");
          $("#yes2").parents("li").children("label").removeClass("cwhite bce84c3d");
          $("#no2").attr("checked",true);
          $("#yes2").attr("checked",false);
          $(".tupian").addClass("none");
      }
    });
    $(".returnTicket_opt_css").click(function () {
   	 if ($(this).prev().val() == "T") {
            $("#yes4").parents("li").children("label").addClass("cwhite bce84c3d");
            $("#no4").parents("li").children("label").removeClass("cwhite bce84c3d");
            $("#yes4").attr("checked",true);
            $("#no4").attr("checked",false);
      }
      else{
          $("#no4").parents("li").children("label").addClass("cwhite bce84c3d");
          $("#yes4").parents("li").children("label").removeClass("cwhite bce84c3d");
          $("#no4").attr("checked",true);
          $("#yes4").attr("checked",false);
      }
   });
    $(".qixian_opt_css").click(function () {
    	 if ($(this).prev().val() == "0") {
			$("#half").parents("li").children("label").addClass("cwhite bce84c3d");
			$("#year").parents("li").children("label").removeClass("cwhite bce84c3d");
			$("#half").attr("checked",true);
			$("#year").attr("checked",false);
       	}
       	else{
           	$("#year").parents("li").children("label").addClass("cwhite bce84c3d");
           	$("#half").parents("li").children("label").removeClass("cwhite bce84c3d");
           	$("#year").attr("checked",true);
			$("#half").attr("checked",false);
       	}
    	 
		var now = new Date();
   		var fullyear = now.getFullYear();
   		var month = now.getMonth() + 1;
   		var date = now.getDate();
   		if (month < 10) {
   			month = "0" + month;
   		}
   		if (date < 10) {
   			date = "0" + date;
   		}	
   		$("#start").val(fullyear+"-"+month+"-"+date);
   		$("input[name='start']").val(fullyear+"-"+month+"-"+date);

   		var begintimelong = Date.parse(new Date());
   		var endtime12 ;
   		
   		if($(this).prev().val() == "0"){
        	$("input[name='jxts']").val(183);
   			endtime12 = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
   		}else {
        	$("input[name='jxts']").val(365);
   			endtime12 = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
   		}
   	    var endfullyear = endtime12.getFullYear();
   	    var endmonth = endtime12.getMonth() + 1;
   	    var enddate = endtime12.getDate();
   		if (endmonth < 10) {
   			endmonth = "0" + endmonth;
   		}
   		if (enddate < 10) {
   			enddate = "0" + enddate;
   		}
   		
   		$("#end").val(endfullyear+"-"+endmonth+"-"+enddate);
   		$("input[name='end']").val(endfullyear+"-"+endmonth+"-"+enddate);	
    });
    
    $(".moshi_opt_css").click(function () {
		if ($(this).prev().val() == "0") {
			$("#pattern1").parents("li").children("label").addClass("cwhite bce84c3d");
			$("#pattern2").parents("li").children("label").removeClass("cwhite bce84c3d");
			$("#pattern1").attr("checked",true);
			$("#pattern2").attr("checked",false);
		}
       	else{
			$("#pattern2").parents("li").children("label").addClass("cwhite bce84c3d");
           	$("#pattern1").parents("li").children("label").removeClass("cwhite bce84c3d");
           	$("#pattern2").attr("checked",true);
			$("#pattern1").attr("checked",false);
       	}
    });
    
    /**
    * 选择页面跳转
    */
    $("#yptx_d").click(function(){
		checkAccount(0,'/discountrecord/discount_yp1');
	});
	$("#sptx_d").click(function(){
		checkAccount(0,'/discountrecord/discount_sp1');
	});
	$("#yptx_z").click(function(){
		checkAccount(0,'/discountrecord/discount_yp_zhi');
	});
	$("#sptx_z").click(function(){
		checkAccount(0,'/discountrecord/discount_sp_zhi');
	});
	
    // radio鼠标触发事件
    $(".TXradio").mouseover(function(){
        $(".TXradio").addClass("bcd43c33");
    });
    $("p").mouseout(function(){
        $(".TXradio").removeClass("bcd43c33");
    });

    //复选勾选协议
   /*  $(".TXchecked").on("click",function(){
        $(this).hasClass("on_check")? $(this).removeClass("on_check"):$(this).addClass("on_check");
    }) */
    
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
	$("#start").val(fullyear+"-"+month+"-"+date);
	$("#first").val(fullyear+"-"+month+"-"+date);
	
	$("input[name='start']").val(fullyear+"-"+month+"-"+date);
	$("input[name='first']").val(fullyear+"-"+month+"-"+date);
	
	var begintimelong = Date.parse(new Date());
	var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
    var endfullyear = endtime.getFullYear();
    //获取完整的年份(4位,1970-????)
    var endmonth = endtime.getMonth() + 1;
    //获取当前月份(0-11,0代表1月)
    var enddate = endtime.getDate();
	//获取当前日(1-31)
	if (endmonth < 10) {
		endmonth = "0" + endmonth;
	}
	if (enddate < 10) {
		enddate = "0" + enddate;
	}
	$("#end").val(endfullyear+"-"+endmonth+"-"+enddate);
	$("input[name='end']").val(endfullyear+"-"+endmonth+"-"+enddate);
	
    //    问题答案
    $("div #problem").mouseover(function(){
        $("#answer").fadeIn("slow");
    })
    $("div #problem").mouseout(function(){
        $("#answer").fadeOut("slow");
    });
</script>

[@main.footer/]
[/@main.body]