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
            <li id="yptx_z" class="w250 lh50 fl bre0e0e0 bbd43c33 cd43c33">银票纸票</li>
            <li id="sptx_d" class="w250 lh50 fl bre0e0e0">商票电票</li>
            <li id="sptx_z" class="w250 lh50 fl bre0e0e0">商票纸票</li>
        </ul>
    </div>
    <div class="mt12 bc bae0e0e0 bcwhite pl20 pr20 pb15 f14 c2d2d2d">
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">票面金额：</div>
            <input id="allmoney" name="allmoney" maxlength="13" type="text" placeholder="请输入金额" class="fl w129 h27 lh27 bae0e0e0 mt8 ti8 validate[required],validate[custom[number]],validate[custom[money]]">
            <div class="fl f14 lh45 ml10">万元</div>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">承兑行类型：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="guogu" class="none" name="type2" value="1" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d type2_opt_css" for="guogu">国股</label></li>
               	<li class="fl mr20"><input type="radio" id="dashang" class="none" name="type2" value="8"><label class="fl tc w46 h27 br3 type2_opt_css" for="dashang">大商</label></li>
                <li class="fl mr20"><input type="radio" id="xiaoshang" class="none" name="type2" value="2"><label class="fl tc w46 h27 br3 type2_opt_css" for="xiaoshang">小商</label></li>
                <li class="fl mr20"><input type="radio" id="waizi" class="none" name="type2" value="3"><label class="fl tc w46 h27 br3 type2_opt_css" for="waizi">外资</label></li>
                <li class="fl mr20"><input type="radio" id="nongshang" class="none" name="type2" value="4"><label class="fl tc w46 h27 br3 type2_opt_css" for="nongshang">农商</label></li>
                <li class="fl mr20"><input type="radio" id="nonghe" class="none" name="type2" value="5"><label class="fl tc w46 h27 br3 type2_opt_css" for="nonghe">农合</label></li>
                <li class="fl mr20"><input type="radio" id="nongxin" class="none" name="type2" value="6"><label class="fl tc w46 h27 br3 type2_opt_css" for="nongxin">农信</label></li>
                <li class="fl mr20"><input type="radio" id="cunzhen" class="none" name="type2" value="7"><label class="fl tc w46 h27 br3 type2_opt_css" for="cunzhen">村镇</label></li>
            </ul>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">交易日期：</div>
            <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 mt8 ti8 inline bcwhite" id="start" />
            <label class="fl w30 h27 rili mt8" for="start"></label>
            <input type="hidden" name="begintime" />
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">到期日期：</div>
            <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 mt8 ti8 inline bcwhite" id="end" />
            <label class="fl w30 h27 rili mt8" for="end"></label>
            <input type="hidden" name="endtime" />
        </div>
        <div class="w h45 lh45 xuxian">
            <div class="fl tl w100 fb">计息天数：</div>
            <p id="jxts"  class="fl">183天</p>
            <input type="hidden" name="jxts" value="183" />
        </div>
        <div class="w h45 xuxian lh45">
            <div class="fl tl w100 fb">参考利息：</div>
            <p id="salepriceid" class="fl">0元</p>
            <input type="hidden" name="salepriceid" value="0" />
            <input type="button" value="重新计算" class="fl w80 h27 mt10 ml40 bcd43c33 bad43c33 br3 cwhite">
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
        <div class="w h45 xuxian endorse">
            <div class="fl tl w100 fb lh45">背书：</div>
            <div class="fl TXjj1 w100 h28 mt8 mr10">
                <div class="reduce fl w20 h2 mt12 ml5" onClick="setAmount1.reduce('#endorse')" href="javascript:void(0)">
                    <img src="${image_url}/website/discount/jian.png" class="w20 h2 vm"/>
                </div>
                <input readonly="readonly" type="text" name="endorse" value="0" id="endorse" onKeyUp="setAmount1.modify('#endorse')" class="fl b0 bcn f14 lh27 tc w40 h27 ml5" />
                <div class="add mt4 ml5 fl" onClick="setAmount1.add('#endorse')" href="javascript:void(0)">
                    <img src="${image_url}/website/discount/jia.png" class="w20 h20" />
                </div>
            </div>
            <p class="lh45 fl">手</p>
        </div>
        <div class="w h45 xuxian zhi">
            <div class="fl tl w100 fb lh45">是否需要上门：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes1" class="none" name="needTodoor" value="1" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d needTodoor_opt_css" for="yes1">是</label></li>
                <li class="fl"><input type="radio" id="no1" class="none" name="needTodoor" value="0"><label class="fl tc w46 h27 br3 needTodoor_opt_css" for="no1">否</label></li>
            </ul>
        </div>
        <div class="w h130 xuxian">
            <div class="fl tl w100 fb lh45">备注（选填）：</div>
            <textarea id="memberother" name="memberother" maxlength="200" style='resize: none;' class="fl ti8 w500 h100 bae0e0e0 bcwhite mt8 f14 pt10" name="" placeholder="备注最多可输入200个字......"></textarea>
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
            <div class="fl tl w100 fb lh45">票已开出：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes2" class="none" name="hasTicket" value="0" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d hasTicket_opt_css" for="yes2">是</label></li>
                <li class="fl"><input type="radio" id="no2" class="none" name="hasTicket" value="1"><label class="fl tc w46 h27 br3 hasTicket_opt_css" for="no2">否</label></li>
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
        <div class="w xuxian pb15 zhi">
            <div class="fl tl w100 fb lh45">瑕疵票：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes3" class="none" name="flawTicket" value="0"><label class="fl tc w46 h27 br3 flawTicket_opt_css" for="yes3">是</label></li>
                <li class="fl"><input type="radio" id="no3" class="none" name="flawTicket" value="1"  checked><label class="fl tc w46 h27 br3 cwhite bce84c3d flawTicket_opt_css" for="no3">否</label></li>
            </ul>
            <div class="cb"></div>
            <p class="f12 cd43c33 ml100">注：如果是瑕疵票，机构端跟您交易时可能会收取额外处理费用。</p>
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
            1. 银票贴现步骤？<br>2. 为什么要缴纳保证金？<br>3. 保证金何时退还？
        </p>
        <div class="w190 bcf7f7f6 f12 lh30 pt25 pb20">
            <div class="ml10">如有其他问题，请详见</div>
            <div class="ml10">【帮助中心】-【常见问题】</div>
        </div>
    </div>
    <div class="w500 ha pa t-40 r190 zi13 answer c777777 pt10 pb10 pl10 pr25 none" id="answer">
        <h2 class="f16 fb mt6">1. 银票贴现步骤？</h2>
        <p class="f14 ti20 lh18 mt10"> 用户在可以先在平台利用询价功能获取单一价格后，可以使用这个指导价格去市场进行议价，也可以直接在票据管家平台进行银票贴现，在询价菜单底部有直接生成订单的按钮或者在首页上也有贴现的直接跳转按钮都可以进行相关操作，双方付完保证金后，生成相关订单，票据管家收到订单后经过后台智能运算，会立即派送给最符合条件的一家机构，机构在10分钟内可以微调价格后进行接单，一旦机构接单后会联系持票用户进行线下交易。</p>
        <h2 class="f16 fb mt10">2. 为什么要缴纳保证金？</h2>
        <p class="f14 ti20 lh18 mt10">贴现过程中为了保证交易的顺利进行贴现方和机构方都会付一笔资金放到平台上，交易顺利进行保证金会流转到双方账户，而如果出现虚假报价，虚假票面而影响交易质量保证金将作为赔偿补偿到对方账户上（比如贴现方票据信息造假则补偿给机构方，机构方虚假报价则补偿到贴现方）。</p>
        <h2 class="f16 fb mt10">3. 保证金何时退还？</h2>
        <p class="f14 ti20 lh18 mt10 mb5">交易顺利进行后保证金会重新流转到双方账户。</p>
    </div>
</div>

<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
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
			<input type="radio" name="place" id="place{{id}}" value="" onclick="selectAddress('{{id}}');" class="radio2 w12 h12 mr10">
			<div>{{address}}<span>（{{name}}）</span></div>
			<div class="ml15 c717583">{{mobile}}</div>
		</label>
		<div class="flex-row editBtn">
			<img src="https://img.utiexian.com/website/discount/edit.png" width="18" height="18" onclick="updateAddress('{{id}}');" class="mt8">
		</div>
	</div>
{{/each}}
</script>

<script type="text/javascript">
	$(function(){
		var img_upload=document.getElementById("fileToUpload");
		$("#fileToUpload").val("");
		// 添加功能出发监听事件
		img_upload.addEventListener('change',readFile,false);
		
		loadData();
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

	var memberId = ${member.id};
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
	};
	
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
	};
	
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
	};
	
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
		   		console.log(data);
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

	//生成订单
	$("#button").click(function(){
		if($("input[type='button']").attr("disabled")){
			return;
		}
		if(!$("#allmoney").validationEngine("validate")){
			$("#allmoney").focus();
			return ;
		}
		
		if(!$("#address").validationEngine("validate")){
			$("#address").focus();
			return ;
		}
		
		var map = new Map();
		var type1 = 1;//$("input:radio[name='type1']:checked").val();
		var type2 = $("input:radio[name='type2']:checked").val();
		var allmoney = $("#allmoney").val();
		var endtime = $("input[name='endtime']").val();
		var begintime = $("input[name='begintime']").val();
		var acceptTime = $("input:radio[name='acceptTime']:checked").val();//期限
		var jxts = $("input[name='jxts']").val();
		var endorse = $("#endorse").val();
		var needTodoor = $("input:radio[name='needTodoor']:checked").val();//是否需要上门
		var backEndorse = $("input:radio[name='backEndorse']:checked").val();//是否是回头票
		var memberother = $("#memberother").val();
		var hasTicket = $("input:radio[name='hasTicket']:checked").val();//票是否开出
		var flawTicket = $("input:radio[name='flawTicket']:checked").val();//票是否瑕疵
		var draftNo = $("#draftNo").val();
		var cnapsCode = $("#cnapsCode").val();
		var picpath = $("#picpath").val();
		
		if(backEndorse==null||backEndorse==""){
			layer.alert("请选择票据是否是回头票");
			return ;
		}
		
		var url = '${bootAppPath}/discountrecord/tiexian';
		
		var params = {type1:type1,type2:type2,begintime:begintime,endtime:endtime,backEndorse:backEndorse,
				allmoney:allmoney,memberother:memberother,jxts:jxts,memberid:memberId};
		
			params.endorse = endorse;
			params.needTodoor = needTodoor;
			params.flawTicket = flawTicket;
			params.hasTicket = hasTicket;
			
			var  membername= $("#name").val();
			var  membersex= $("#sex").val();
			var  membermobile= $("#phone").html();
			var  address= $("#address").html();
			var  cityId= $("#cityId").val();
			var  place= $("#place").val();
			
			if($.trim(address).length == 0){
				layer.alert("请选择一个地址");
				return; 
			}
			
			params.membername = membername;
			params.membersex = membersex;
			params.membermobile = membermobile;
			params.address = address;
			params.cityId = cityId;
			params.place = place;
			
			if(hasTicket ==0){
				if($.trim($("#picpath").val()).length == 0 || $("#picpath").val() == null){
					layer.alert("请选择上传的票面");
					return ;
				};
				params.picpath = picpath;
			}

		$("input[type='button']").attr("disabled","disabled");//按钮禁用
		var data = bootPost(url,params);
		if(data.data.response == 'success'){
			map.put("discId", data.data.data.id);//类型
		}else{
			alert(data.data.msg);
		 	return;
		}
		map.put("_PAGE", "/discountrecord/discount_yp2");//必传
		map.put("ym", "yp");
		_OPENPAGE_FORM(map);
		
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

    //日历
    !function(){
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
    }();
    //日期范围限制
	//贴现日期
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD',
        //min: laydate.now(), //设定最小日期为当前日期
        min: '1900-01-01', //设定最小日期
        max: '2099-06-16', //最大日期
        istime: true,//时分秒
        isclear: false,//清空
        istoday: false,//今天
        choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
            jixidate();
        }
    };
	//到期日期
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD',
        min: laydate.now(),
        max: '2099-06-16',
        istime: true,//时分秒
        isclear: false,//清空
        istoday: false,//今天
        choose: function(datas){
            start.max = datas; //结束日选好后，充值开始日的最大日期
            jixidate();
        }
    };
    laydate(start);
    laydate(end);
    
    //计息天数
    function jixidate(){
    	var type1 = 1;
    	var type2;
    	$(".type2_opt_css").each(function(){
    		if($(this).prev().attr("checked") == "checked"){
    			type2 = $(this).prev().val();
    		}
    	})
    	var start = $("#start").val();
    	var end = $("#end").val();
    	
    	$("input[name='begintime']").val(start);
    	$("input[name='endtime']").val(end);

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
	     		var salepriceid=0;
	     		if(parseInt(allmoney) >= 500){
	     			salepriceid = retValue.rate;
	     		}else{
	     			salepriceid = retValue.rate2;
	     		}
	     		var lx = retValue.txlx;
	     		if(data == 'undefined' || typeof(data) == 'undefined'){
	     			$("#jxts").html(0+"天");
		     		$("input[name='jxts']").val(0);
	     		}else{
	     			$("#jxts").html(data+"天");
		     		$("input[name='jxts']").val(data);
	     		}
	     		$("#salepriceid").html(lx);
	     		$("#salepriceid").val(salepriceid);
	    	}
		 })	
    };

	//票据类型    
    $(".type2_opt_css").click(function () {
    	 $(".type2_opt_css").each(function(){
         	$(this).prev().attr("checked",false);
         });
       if ($(this).prev().val() == "1") {    
            $("#guogu").next().addClass("cwhite bce84c3d");
            $("#dashang").next().removeClass("cwhite bce84c3d");
            $("#xiaoshang").next().removeClass("cwhite bce84c3d");
            $("#waizi").next().removeClass("cwhite bce84c3d");
            $("#nongshang").next().removeClass("cwhite bce84c3d");
            $("#nonghe").next().removeClass("cwhite bce84c3d");
            $("#nongxin").next().removeClass("cwhite bce84c3d");
            $("#cunzhen").next().removeClass("cwhite bce84c3d");
            $("#guogu").attr("checked",true);
       }else if($(this).prev().val() == "8"){
    	   $("#dashang").next().addClass("cwhite bce84c3d");
           $("#guogu").next().removeClass("cwhite bce84c3d");
           $("#xiaoshang").next().removeClass("cwhite bce84c3d");
           $("#waizi").next().removeClass("cwhite bce84c3d");
           $("#nongshang").next().removeClass("cwhite bce84c3d");
           $("#nonghe").next().removeClass("cwhite bce84c3d");
           $("#nongxin").next().removeClass("cwhite bce84c3d");
           $("#cunzhen").next().removeClass("cwhite bce84c3d");
           $("#dashang").attr("checked",true);
       }else if($(this).prev().val() == "2"){
    	   $("#xiaoshang").next().addClass("cwhite bce84c3d");
           $("#guogu").next().removeClass("cwhite bce84c3d");
           $("#dashang").next().removeClass("cwhite bce84c3d");
           $("#waizi").next().removeClass("cwhite bce84c3d");
           $("#nongshang").next().removeClass("cwhite bce84c3d");
           $("#nonghe").next().removeClass("cwhite bce84c3d");
           $("#nongxin").next().removeClass("cwhite bce84c3d");
           $("#cunzhen").next().removeClass("cwhite bce84c3d");
           $("#xiaoshang").attr("checked",true);
       }else if($(this).prev().val() == "3"){
    	   $("#waizi").next().addClass("cwhite bce84c3d");
           $("#guogu").next().removeClass("cwhite bce84c3d");
           $("#xiaoshang").next().removeClass("cwhite bce84c3d");
           $("#dashang").next().removeClass("cwhite bce84c3d");
           $("#nongshang").next().removeClass("cwhite bce84c3d");
           $("#nonghe").next().removeClass("cwhite bce84c3d");
           $("#nongxin").next().removeClass("cwhite bce84c3d");
           $("#cunzhen").next().removeClass("cwhite bce84c3d");
           $("#waizi").attr("checked",true);
       }else if($(this).prev().val() == "4"){
    	   $("#nongshang").next().addClass("cwhite bce84c3d");
           $("#guogu").next().removeClass("cwhite bce84c3d");
           $("#xiaoshang").next().removeClass("cwhite bce84c3d");
           $("#waizi").next().removeClass("cwhite bce84c3d");
           $("#dashang").next().removeClass("cwhite bce84c3d");
           $("#nonghe").next().removeClass("cwhite bce84c3d");
           $("#nongxin").next().removeClass("cwhite bce84c3d");
           $("#cunzhen").next().removeClass("cwhite bce84c3d");
           $("#nongshang").attr("checked",true);
       }else if($(this).prev().val() == "5"){
    	   $("#nonghe").next().addClass("cwhite bce84c3d");
           $("#guogu").next().removeClass("cwhite bce84c3d");
           $("#xiaoshang").next().removeClass("cwhite bce84c3d");
           $("#waizi").next().removeClass("cwhite bce84c3d");
           $("#dashang").next().removeClass("cwhite bce84c3d");
           $("#nongshang").next().removeClass("cwhite bce84c3d");
           $("#nongxin").next().removeClass("cwhite bce84c3d");
           $("#cunzhen").next().removeClass("cwhite bce84c3d");
           $("#nonghe").attr("checked",true);
       }else if($(this).prev().val() == "6"){
    	   $("#nongxin").next().addClass("cwhite bce84c3d");
           $("#guogu").next().removeClass("cwhite bce84c3d");
           $("#xiaoshang").next().removeClass("cwhite bce84c3d");
           $("#waizi").next().removeClass("cwhite bce84c3d");
           $("#dashang").next().removeClass("cwhite bce84c3d");
           $("#nongshang").next().removeClass("cwhite bce84c3d");
           $("#nonghe").next().removeClass("cwhite bce84c3d");
           $("#cunzhen").next().removeClass("cwhite bce84c3d");
           $("#nongxin").attr("checked",true);
       }else if($(this).prev().val() == "7"){
    	   $("#cunzhen").next().addClass("cwhite bce84c3d");
           $("#guogu").next().removeClass("cwhite bce84c3d");
           $("#xiaoshang").next().removeClass("cwhite bce84c3d");
           $("#waizi").next().removeClass("cwhite bce84c3d");
           $("#dashang").next().removeClass("cwhite bce84c3d");
           $("#nongshang").next().removeClass("cwhite bce84c3d");
           $("#nonghe").next().removeClass("cwhite bce84c3d");
           $("#nongxin").next().removeClass("cwhite bce84c3d");
           $("#cunzhen").attr("checked",true);
       }
    });
	//是否需要上门
    $(".needTodoor_opt_css").click(function () {
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
	//票是否开出
    $(".hasTicket_opt_css").click(function () {
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
	//是否是瑕疵票
    $(".flawTicket_opt_css").click(function () {
    	 if ($(this).prev().val() == "0") {
             $("#yes3").parents("li").children("label").addClass("cwhite bce84c3d");
             $("#no3").parents("li").children("label").removeClass("cwhite bce84c3d");
             $("#yes3").attr("checked",true);
             $("#no3").attr("checked",false);
       }
       else{
           $("#no3").parents("li").children("label").addClass("cwhite bce84c3d");
           $("#yes3").parents("li").children("label").removeClass("cwhite bce84c3d");
           $("#no3").attr("checked",true);
           $("#yes3").attr("checked",false);
       }
    });
	//是否是回头票
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

    // radio鼠标触发事件
    $(".TXradio").mouseover(function(){
        $(".TXradio").addClass("bcd43c33");
    });
    $("p").mouseout(function(){
        $(".TXradio").removeClass("bcd43c33");
    });
    
    //加载数据
    function loadData(){
    	var type1 = '${shuxing}';
    	var type2 = '${type1}';
    	var memberother = '${accountdesc}';
    	var allmoney = '${allmoney}';
    	var days = '${days}';
    	$("#allmoney").val(allmoney);
    	
    	if(days != null && days != ""){
    		$("#jxts").html(days+"天");
        	$("input[name='jxts']").val(days);
    	}else{
    		$("#jxts").html(183+"天");
        	$("input[name='jxts']").val(183);
    	}
    	
    	var begintime = '${begintime}';
    	var endtime = '${endtime}';
    	
    	if($.trim(begintime).length >0){
    		$("#start").val(begintime);
        	$("#begintime").val(begintime);
    	}
    	if($.trim(endtime).length >0){
    		$("#end").val(endtime);
        	$("#endtime").val(endtime);
    	}
    	
    	$("#memberother").val(memberother);
    	var a = parseInt(type2);
		switch (a) {
			case 1:
				$("#guogu").attr("checked","checked");
				$("#guogu").next().addClass("cwhite bce84c3d");
				break;
			case 2:
				$("#xiaoshang").attr("checked","checked");
				$("#guogu").removeAttr("checked");
				$("#xiaoshang").next().addClass("cwhite bce84c3d");
				$("#guogu").next().removeClass("cwhite bce84c3d");
				break;
			case 3:
				$("#waizi").attr("checked","checked");
				$("#guogu").removeAttr("checked");
				$("#waizi").next().addClass("cwhite bce84c3d");
				$("#guogu").next().removeClass("cwhite bce84c3d");
				break;
			case 4:
				$("#nongshang").attr("checked","checked");
				$("#guogu").removeAttr("checked");
				$("#nongshang").next().addClass("cwhite bce84c3d");
				$("#guogu").next().removeClass("cwhite bce84c3d");
				break;
			case 5:
				$("#nonghe").attr("checked","checked");
				$("#guogu").removeAttr("checked");
				$("#nonghe").next().addClass("cwhite bce84c3d");
				$("#guogu").next().removeClass("cwhite bce84c3d");
				break;
			case 6:
				$("#nongxin").attr("checked","checked");
				$("#guogu").removeAttr("checked");
				$("#nongxin").next().addClass("cwhite bce84c3d");
				$("#guogu").next().removeClass("cwhite bce84c3d");
				break;
			case 7:
				$("#cunzhen").attr("checked","checked");
				$("#guogu").removeAttr("checked");
				$("#cunzhen").next().addClass("cwhite bce84c3d");
				$("#guogu").next().removeClass("cwhite bce84c3d");
				break;
			case 8:
				$("#guogu").removeAttr("checked");
				$("#dashang").attr("checked","checked");
				$("#dashang").next().addClass("cwhite bce84c3d");
				$("#guogu").next().removeClass("cwhite bce84c3d");
				break;
			default:
				break;
		}
    }
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
	$("input[name='begintime']").val(fullyear+"-"+month+"-"+date);
	
	
	var begintimelong = Date.parse(new Date());
	var endtime12 = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
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
	$("#end").val(endfullyear+"-"+endmonth+"-"+enddate);
	$("input[name='endtime']").val(endfullyear+"-"+endmonth+"-"+enddate);
	

    //    问题答案
    $("div #problem").mouseover(function(){
        $("#answer").fadeIn("slow");
    });
    $("div #problem").mouseout(function(){
        $("#answer").fadeOut("slow");
    });
</script>

[@main.footer/]
[/@main.body]