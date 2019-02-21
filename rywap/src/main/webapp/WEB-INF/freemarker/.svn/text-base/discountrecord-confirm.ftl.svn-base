[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header/]

<link rel="stylesheet" href="${staticPath}/css/rywap/form.css">
<link rel="stylesheet" href="${staticPath}/css/rywap/discount.css">
<div class="wrapper">
	<div id="noAddress" class="plaec">
	    <div class="plaecLeft"></div>
	    <div class="placeChose">请选择贴现地址</div>
	    <div class="plaecRight"></div>
	</div>
	<div id="hasAddress" class="plaec hide">
		<input type="hidden" id="membername"/>
	    <input type="hidden" id="membersex"/>
	    <input type="hidden" id="membermobile"/>
	    <input type="hidden" id="address"/>
	    <input type="hidden" id="place"/>
	    <input type="hidden" id="cityId"/>
	    <input type="hidden" id="longitude"/>
	    <input type="hidden" id="latitude"/>
		<div class="plaecLeft"></div>
	    <div class="pelacShow">
	        <div class="row">
	            <h2 class="flex1" id="name">--</h2>
	            <div class="flex1" id="sex">--</div>
	            <div class="flex2" id="mobile">--</div>
	        </div>
	        <div class="outH font12" id="dizhi">--</div>
	    </div>
	    <div class="plaecRight"></div>
	</div>
	
	<form action="" class="form mt10">
	    <ul>
	        <li>
	            <div class="formTitle">保证金：</div>
	            <span id="deposit" class="cblue"></span>元
	        </li>
	        <li>
	            <div class="formTitle">支付方式：</div>
	            <select name="payWay" id="payWay">
	                <option value="3" selected="selected">快钱支付</option>
	                <option value="4">宝付支付</option>
	            </select>
	        </li>
	    </ul>
	    <ul>
	        <li>
	            <div class="formTitle bold">订单详情</div>
	        </li>
	        <li>
	            <div class="formTitle">票据属性：</div>
	            <span id="type1"></span>
	        </li>
	        <li>
	            <div class="formTitle">付款行：</div>
	            <span id="bank"></span>
	        </li>
	        <li>
	            <div class="formTitle">票面金额：</div>
	            <span id="allmoney"></span>万元
	        </li>
	        <li>
	            <div class="formTitle">贴现日期：</div>
	            <span id="begintime"></span>
	        </li>
	        <li>
	            <div class="formTitle">到期日期：</div>
	            <span id="endtime"></span>
	        </li>
	        <li>
	            <div class="formTitle">背书：</div>
	            <span id="endorse"></span>手
	        </li>
	        <li class="outH">
	            <div class="formTitle">备注：</div>
	            <span id="memberother"></span>
	        </li>
	    </ul>
	    <a class="formBtn" href="javascript:void(0);" onclick="toPay();">
	        <input type="button" value="生成订单" />
	    </a>
	</form>
</div>
<script type="text/javascript" src="${staticPath}/js/rywap/base64.js"></script>
<script type="text/javascript">
var memberId = null;
var discountrecordId = null;
$(document).ready(function(){
	memberId = "${member.id}";//获取登录用户主键
	discountrecordId = '${id}';
	
	initDate();
	initAddress();//加载贴现地址
	
	$("#noAddress,#hasAddress").on("click",function(){
		if (typeof localStorage === 'object') {
		    try {
		    	localStorage.setItem("CALLBACKURL","/wap/discountrecord/confirm/page?id=" + discountrecordId);
				window.location.href = BASEPATH+"/wap/address/list?memberId=" + memberId;
		    } catch (e) {
		        Storage.prototype._setItem = Storage.prototype.setItem;
		        Storage.prototype.setItem = function() {};
		        myToast("您的浏览器处于无痕浏览，请切换浏览模式");
		    }
		}
	});
});

/**
 * 初始化订单数据
 */
function initDate(){
	$.ajax({
		url:BASEPATH+"/wap/discountrecord/get",
		type:"POST",
		data: {id:'${id}'},
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				var result = data.data;
				
				$("#allmoney").text(result.allmoney);
                $("#type1").text(getType(result.type1));
                
                $("#type2").text(getBank(result.type2));
                $("#begintime").text(result.begintime);
                $("#endtime").text(result.endtime);
                $("#bank").text(result.bank);
                if(result.memberother!=null)$("#memberother").text(result.memberother);
                $("#deposit").text(result.deposit);
                $("#endorse").text(result.endorse);
                $("#flawTicket").text(getFlawTicket(result.flaw_ticket));
			}else{
				myError(data.msg);
				return;
			}
		}
	});
}

/**
 * 初始化贴现地址
 */
function initAddress(){
	var addressId = getLocalAndClean("ADDRESSID");//选择的贴现地址
	var params = {};
	if(memberId!=null && $.trim(memberId).length>0){
		params.memberId = memberId;
	}
	if(addressId!=null && $.trim(addressId).length>0){
		params.addressId = addressId;
	}
	$.ajax({
		url:BASEPATH+"/wap/address/get/default",
		type:"POST",
		data: params,
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				var result = data.data;
				$("#membername").val(result.name);
                $("#membersex").val(result.sex);
                $("#membermobile").val(result.mobile);
                var address = result.address;
                if(result.other!=null){
                    address += " "+result.other;
                }
                $("#address").val(address);
                $("#place").val(result.place);
                $("#cityId").val(result.cityId);
                $("#longitude").val(result.longitude);
                $("#latitude").val(result.latitude);
                
                var sex = result.sex;
                if(sex==1){
                    sex = "先生";
                }else{
                    sex = "女士";
                }
                $("#name").text(result.name);
                $("#sex").text(sex);
                $("#mobile").text(result.mobile);
                $("#dizhi").text(address);
                $("#noAddress").addClass("hide");
                $("#hasAddress").removeClass("hide");
			}else{
				$("#noAddress").removeClass("hide");
                $("#hasAddress").addClass("hide");
			}
		}
	});
}

/**
 * 去支付（先保存贴现地址）
 */
function toPay(){
	var safari = false;
    if(isSafari=navigator.userAgent.indexOf("Safari")>0) {   
    	safari = true; 
    } 
	var longitude = $("#longitude").val();
	var latitude = $("#latitude").val();
	if(longitude==null || $.trim(longitude).length<=0){
		uexToast("请选择贴现地址！");
		return;
	}
	if(latitude==null || $.trim(latitude).length<=0){
		uexToast("请选择贴现地址！");
		return;
	}
	var payWay = $("#payWay").val();
	var params = {
			id:discountrecordId,
			membername:$("#membername").val(),
			membersex:$("#membersex").val(),
			membermobile:$("#membermobile").val(),
			address:$("#address").val(),
			place:$("#place").val(),
			cityId:$("#cityId").val(),
			longitude:longitude,
			latitude:latitude,
			payWay:payWay
    	};
	$.ajax({
		url:BASEPATH+"/wap/discountrecord/pay/before",
		type:"POST",
		data: params,
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				var _URL = '';
				if(payWay==3){
					if(safari){
						window.location.href = '${appPath}/app/discountrecord/pay?orderAmount='+data.deposit+'&memberId='+memberId+'&orderId='+data.bnsNo;
						return;
					}else{
						_URL = '${appPath}/app/discountrecord/pay?orderAmount='+data.deposit+'&memberId='+memberId+'&orderId='+data.bnsNo;
					}
				}else if(payWay==4){
					_URL = "${mobilePath}/baofoo/pay?orderNo=" + data.bnsNo + "&type=1";
				}
				window.location.href = BASEPATH+"/wap/pay?url=" + new Base64().encode(_URL);
			}else{
				myError(data.msg);
			}
		}
	});
}
</script>

[@main.footer/]
[/@main.body]