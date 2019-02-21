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
	            <div class="formTitle">票据属性：</div>
	            <ul class="choseBtn">
	                <li><input type="radio" name="type1" value="1" id="type1_0" class="radio" checked="checked"/><label for="type1_0">纸票</label></li>
	                <li><input type="radio" name="type1" value="2" id="type1_1" class="radio"/><label for="type1_1">电票</label></li>
	            </ul>
	        </li>
	        <li class="ELE hide">
	            <div class="formTitle">承兑期限：</div>
	            <ul class="choseBtn">
	                <li><input type="radio" name="acceptTime" value="0" id="acceptTime_0" class="radio" checked="checked"/><label for="acceptTime_0">半年期</label></li>
	                <li><input type="radio" name="acceptTime" value="1" id="acceptTime_1" class="radio"/><label for="acceptTime_1">一年期</label></li>
	            </ul>
	        </li>
	        <li class="ELE hide">
	            <div class="formTitle">交易模式：</div>
	            <ul class="choseBtn">
	                <li><input type="radio" name="tradeModel" value="0" id="tradeModel_0" class="radio" checked="checked"/><label for="tradeModel_0" class="w100">先背书后打款</label></li>
	                <li><input type="radio" name="tradeModel" value="1" id="tradeModel_1" class="radio"/><label for="tradeModel_1" class="w100">先打款后背书</label></li>
	            </ul>
	        </li>
	        <li>
	            <div class="formTitle">承兑企业：</div>
	            <input type="text" name="bank" id="bank" class="w200" placeholder="请输入承兑企业全称"/>
	        </li>
	        <li>
	            <div class="formTitle">总金额：</div>
	            <input type="number" name="allmoney" id="allmoney" class="w100" placeholder="请输入总金额"/>
	            <span>万元</span>
	        </li>
	        <li>
	            <div class="formTitle">开票日期：</div>
	            <input type="date" name="printDate" id="printDate"/>
	        </li>
	        <li>
	            <div class="formTitle">贴现日期：</div>
	            <input type="date" name="startDate" id="startDate"/>
	        </li>
	        <li>
	            <div class="formTitle">到期日期：</div>
	            <input type="date" name="endDate" id="endDate"/>
	        </li>
	        <li>
	            <div class="formTitle">背书：</div>
	            <div class="beishu">
	                <a class="reduce" onClick="setAmount.reduce('#endorse')" href="javascript:void(0)">
	                    <img src="${imagePath}/rywap/discount/jian.png" />
	                </a>
	                <input type="text" name="endorse" value="1" id="endorse" onKeyUp="setAmount.modify('#endorse')" class="ub ub-f1 text tx-c ulev-1" readonly="readonly"/>
	                <a class="add" onClick="setAmount.add('#endorse')" href="javascript:void(0)">
	                    <img src="${imagePath}/rywap/discount/jia.png" />
	                </a>
	            </div>
	            <span>手</span>
	        </li>
	        <li>
	            <div class="formTitle">是否上门：</div>
	            <ul class="choseBtn">
	                <li><input type="radio" name="needTodoor" value="1" id="yes1" class="radio" /><label for="yes1" class="radioSelect">是</label></li>
	                <li><input type="radio" name="needTodoor" value="0" id="no1" class="radio" /><label for="no1">否</label></li>
	            </ul>
	        </li>
	        <li>
	            <div class="formTitle">备注(选填)：</div>
	            <input type="text" name="remarks" id="remarks" class="w200" maxlength="150" placeholder="请输入备注信息"/>
	        </li>
	    </ul>
	
	    <ul class="mt10">
	        <li>
	            <div class="formTitle">票已开出：</div>
	            <ul class="choseBtn">
	                <li><input type="radio" name="hasTicket" value="0" id="hasTicket0" class="radio" checked="checked"/><label for="hasTicket0" class="radioSelect">是</label></li>
	                <li><input type="radio" name="hasTicket" value="1" id="hasTicket1" class="radio"/><label for="hasTicket1">否</label></li>
	            </ul>
	        </li>
	        <li class="HASTICKET bnone">
	            <div class="formTitle">上传票据正面</div>
	        </li>
	        <li class="HASTICKET bnone" style="height: auto !important;line-height: 20px !important;">
		        <p class="font10">上传正面的清晰图片(可遮掉右上角票号),电子汇票请使用电脑截图。</p>
		        <div id="form1" runat="server" class="mt10">
					<input type="hidden" id="FRONT"/>
					<label class="upload" for="imgInp">
			           	<img id="upload_img" src="${imagePath}/rywap/discount/upload.png" alt="请选择图片" class="imgShow"/>
					</label>
		           	<input type="file" id="imgInp" name="fileUpload" class="fileUpload ladda-button" data-style="zoom-out" accept="image/png,image/jpg,image/jpeg" style="display: none;"/>
				</div>
		        <div class="cred font14 template">查询票据样例</div>
		        <img src="${imagePath}/rywap/discount/pjyl.jpg" alt="票据样例" class="templateImg hide"/>
	        </li>
	    </ul>
	
	    <div class="mt10">
	        <div class="checkIcon on_check onCheck">
	            <input id="switch" type="checkbox" class="radioclass" checked="checked">
	        </div>
	        <div>我已阅读并同意<a href="${basePath}/wap/login/statement" class="cred">《票管家免责声明》</a></div>
	    </div>
	    <div class="formBtn">
	        <input type="button" value="生成订单" onclick="save();"/>
	    </div>
	</form>
</div>
<script src="${staticPath}/js/rywap/jquery.liteuploader.min.js"></script>	
<script type="text/javascript">
var memberId = null;
$(document).ready(function(){
	memberId = "${member.id}";//获取登录用户主键
	
	initDate();
	initAddress();//加载贴现地址
	$("#noAddress,#hasAddress").on("click",function(){
		if (typeof localStorage === 'object') {
		    try {
		    	localStorage.setItem("CALLBACKURL","/wap/discountrecordsp");
				window.location.href = BASEPATH+"/wap/address/list?memberId=" + memberId;
		    } catch (e) {
		        Storage.prototype._setItem = Storage.prototype.setItem;
		        Storage.prototype.setItem = function() {};
		        myToast("您的浏览器处于无痕浏览，请切换浏览模式");
		    }
		}
	});
	
	$(".fileUpload").liteUploader({
	    script: BASEPATH+"/wap/pic/uploadpic",
	    params: {type:'avatar'},
	    rules: {
	        allowedFileTypes: "image/jpeg,image/png,image/jpeg",
	        maxSize: 1024000 * 3
		}
	}).on("lu:success", function (e, data) {
		if(data){
			readURL(this);
			$("#FRONT").val(data);
		}
	}).on("lu:errors", function (e, data) {
		if(data && data.length > 0){
			var item = data[0];
			if(item && item.errors && item.errors.length > 0){
				var err = item.errors[0];
				if(err.type == 'size'){
					myToast("图片大小超出限制,请修改后上传");		
				}
			}
		}
	});
	$(".fileUpload").change(function () {
	    $(this).data("liteUploader").startUpload();
	});
});

/**
 * 上传图片
 */
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#upload_img').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

/**
 * 保存
 */
function save(){
	if(!$("#switch").is(':checked')){
		myToast("你只有同意该声明才可下单");
        return;
    }
	var type1 = $('input[name="type1"]:checked').val();//票据类型
    var bank = $("#bank").val();
    var allmoney = $("#allmoney").val();
    var print = $("#printDate").val();
    var start = $("#startDate").val();
    var end = $("#endDate").val();
    var endorse = $("#endorse").val();
    var needTodoor = $('input[name="needTodoor"]:checked').val();//是否上门
    var hasTicket = $('input[name="hasTicket"]:checked').val();//票已开出
    var front = $("#FRONT").val();//正面图片
    var remarks = $("#remarks").val();//备注

    var lon = $("#longitude").val();
    var lat = $("#latitude").val();
    if(lon==null || $.trim(lon)==""){
    	myToast("贴现地址不完整");
        return;
    }
    if(print==null || $.trim(print)==""){
    	myToast("请输入开票日期");
        return;
    }else if(start==null || $.trim(start)==""){
    	myToast("请输入贴现日期");
        return;
    }else if(end==null || $.trim(end)==""){
    	myToast("请输入到期日期");
        return;
    }
    
    var params = {
		type1:type1,
		bank:bank,
		allmoney:allmoney,
		print:dateFormat(print),
		start:dateFormat(start),
		end:dateFormat(end),
		memberId:memberId,
		remarks:remarks,
		endorse:endorse,
		hasTicket:hasTicket,
		needTodoor:needTodoor,
		memberName:$("#membername").val(),
		memberSex:$("#membersex").val(),
		memberMobile:$("#membermobile").val(),
		address:$("#address").val(),
		place:$("#place").val(),
		cityId:$("#cityId").val(),
		longitude:lon,
		latitude:lat
	};
	if(type1==2){//电票
		params.acceptTime = $('input[name="acceptTime"]:checked').val();//承兑期限
		params.tradeModel = $('input[name="tradeModel"]:checked').val();//交易模式
	}
    if(hasTicket==0){//票已开出（才上传图片）
    	if(front==null || $.trim(front)==""){
        	myToast("请上传票据图片");
            return;
        }
    	params.picpath = front;
    }
    $.ajax({
		url:BASEPATH+"/wap/discountrecordsp/save",
		type:"POST",
		data: params,
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				window.location.href = BASEPATH+"/wap/discountrecordsp/page";
			}else{
				myError(data.msg);
				return;
			}
		}
	});
}

/**
 * 初始化时间
 */
function initDate(){
	var begintimelong = Date.parse(new Date());
    var begintime = new Date(begintimelong);
    var fullyear = begintime.getFullYear();//获取完整的年份(4位,1970-????)
    var month = begintime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
    var date = begintime.getDate();//获取当前日(1-31)

    var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
    var endfullyear = endtime.getFullYear();//获取完整的年份(4位,1970-????)
    var endmonth = endtime.getMonth() + 1;//获取当前月份(0-11,0代表1月)
    var enddate = endtime.getDate();//获取当前日(1-31)

    if (month < 10) {
        month = "0" + month;
    }
    if (date < 10) {
        date = "0" + date;
    }
    if (endmonth < 10) {
        endmonth = "0" + endmonth;
    }
    if (enddate < 10) {
        enddate = "0" + enddate;
    }
    var begindatestr = fullyear + "-" + month + "-" + date;
    var enddatestr = endfullyear + "-" + endmonth + "-" + enddate;
    $("#printDate").val(begindatestr);
    $("#startDate").val(begindatestr);
    $("#endDate").val(enddatestr);
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
 * 票已开出
 */
$("input[name=hasTicket]").change(function(){
	if ("1"==$(this).val()) {
        $(".HASTICKET").addClass("hide");
    } else{
        $(".HASTICKET").removeClass("hide");
    }
});

/**
 * 背书手数加减
 */
var setAmount = {
    min:0,
    max:999,
    reg:function(x) {
        return new RegExp("^[0-9]\\d*$").test(x);
    },
    amount:function(obj, mode) {
        var x = $(obj).val();
        if (this.reg(x)) {
            if (mode) {
                x++;
            } else {
                x--;
            }
        } else {
            uexToast("请输入正确的背书手数！");
            $(obj).val(0);
        }
        return x;
    },
    reduce:function(obj) {
        var x = this.amount(obj, false);
        if (x >= this.min) {
            $(obj).val(x);
            recalc();
        } else {
            uexToast("背书手数最少为" + this.min);
            $(obj).val(0);
        }
    },
    add:function(obj) {
        var x = this.amount(obj, true);
        if (x <= this.max) {
            $(obj).val(x);
        } else {
            uexToast("背书手数最多为" + this.max);
            $(obj).val(999);
        }
    },
    modify:function(obj) {
        var x = $(obj).val();
        if (x<this.min||x>this.max||!this.reg(x)) {
            uexToast("请输入正确的背书手数！");
            $(obj).val(0);
        }
    }
}

/**
 * 查看票样
 */
$(".template").click(function() {
    if($(".templateImg").css("display")=="none"){
        $(".templateImg").slideDown(100);
    }else {
        $(".templateImg").slideUp(100);
    }
});

/**
 * 纸电票
 */
$("input[name=type1]").change(function(){
	if ("1"==$(this).val()) {
        $(".ELE").addClass("hide");
    } else{
        $(".ELE").removeClass("hide");
    }
});

</script>
[@main.footer/]
[/@main.body]