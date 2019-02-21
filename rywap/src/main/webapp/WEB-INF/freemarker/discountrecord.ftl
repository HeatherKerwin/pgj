[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header/]

<link rel="stylesheet" href="${staticPath}/css/rywap/form.css">
<link rel="stylesheet" href="${staticPath}/css/rywap/discount.css">
<div class="wrapper">
	<form action="" class="form">
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
	            <div class="formTitle">承兑行：</div>
	            <select id="type2">
	                <option value=1>国股</option>
	                <option value=8>大商</option>
	                <option value=2>小商</option>
	                <option value=3>外资</option>
	                <option value=4>农商</option>
	                <option value=5>农合</option>
	                <option value=6>农信</option>
	                <option value=7>村镇</option>
	            </select>
	        </li>
	        <li>
	            <div class="formTitle">付款行：</div>
	            <input type="text" name="bank" id="bank" class="w200" placeholder="请输入付款行"/>
	        </li>
	        <li>
	            <div class="formTitle">票面金额：</div>
	            <input type="number" name="allmoney" id="allmoney" class="w100" placeholder="请输入金额" onchange="myExcel();"/>
	            <span>万元</span>
	        </li>
	        <li>
	            <div class="formTitle">贴现日期：</div>
	            <input type="date" name="startDate" id="startDate" onchange="myExcel();"/>
	        </li>
	        <li>
	            <div class="formTitle">到期日期：</div>
	            <input type="date" name="endDate" id="endDate" onchange="myExcel();"/>
	        </li>
	        <li>
	            <div class="formTitle">背书：</div>
	            <div class="beishu">
	                <div class="reduce" onClick="setAmount.reduce('#endorse')">
	                    <img src="${imagePath}/rywap/discount/jian.png"/>
	                </div>
	                <input type="text" name="endorse" value="1" id="endorse" onKeyUp="setAmount.modify('#endorse')" class="ub ub-f1 text tx-c ulev-1" readonly="readonly"/>
	                <div class="add" onClick="setAmount.add('#endorse')">
	                    <img src="${imagePath}/rywap/discount/jia.png"/>
	                </div>
	            </div>
	            <span>手</span>
	        </li>
	        <li>
	            <div class="formTitle">是否上门：</div>
	            <ul class="choseBtn">
	                <li><input type="radio" name="needTodoor" value="1" id="needTodoor_0" class="radio" checked="checked"/><label for="needTodoor_0">是</label></li>
	                <li><input type="radio" name="needTodoor" value="0" id="needTodoor_1" class="radio"/><label for="needTodoor_1">否</label></li>
	            </ul>
	        </li>
	        <li>
	            <div class="formTitle">备注(选填)：</div>
	            <input type="text" name="" id="remarks" class="w200" placeholder="请输入备注信息"/>
	        </li>
	        <li>
	            <div class="formTitle">计息天数：</div>
	            <span id="day">--</span>
	        </li>
	        <li>
	            <div class="formTitle">参考贴现利息:</div>
	            <span id="price">--</span>
	            <input type="button" id="" value="重新计算"/>
	        </li>
	    </ul>
	
	    <ul class="mt10">
	        <li>
	            <div class="formTitle">票已开出：</div>
	            <ul class="choseBtn">
	                <li><input type="radio" name="hasTicket" value="0" id="hasTicket_0" class="radio" checked="checked"/><label for="hasTicket_0">是</label></li>
	                <li><input type="radio" name="hasTicket" value="1" id="hasTicket_1" class="radio"/><label for="hasTicket_1">否</label></li>
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
			           	<img id="upload_img" src="${imagePath}/rywap/discount/upload.png" alt="请选择图片"  class="imgShow"/>
					</label>
		           	<input type="file" id="imgInp" name="fileUpload" class="fileUpload ladda-button" data-style="zoom-out" accept="image/png,image/jpg,image/jpeg" style="display: none;"/>
				</div>
		        <div class="cred font14 template">查询票据样例</div>
		        <img src="${imagePath}/rywap/discount/pjyl.jpg" alt="票据样例" class="templateImg hide"/>
	        </li>
	    </ul>
	
	    <ul class="HASTICKET mt10">
	        <li>
	            <div class="formTitle">是否瑕疵票：</div>
	            <ul class="choseBtn">
	                <li><input type="radio" name="flawTicket" value="0" id="flawTicket_0" class="radio"/><label for="flawTicket_0">是</label></li>
	                <li><input type="radio" name="flawTicket" value="1" id="flawTicket_1" class="radio" checked="checked"/><label for="flawTicket_1">否</label></li>
	            </ul>
	        </li>
	        <p class="cred font10">注：如果是瑕疵票，机构端跟您交易时可能会收取额外处理费用。</p>
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
	
	initDate();//初始化数据
	initFromCalOrInq();//来自计算器的数据填充
	
	$(".fileUpload").liteUploader({
	    script: BASEPATH+"/wap/pic/uploadpic",
	    params: {type:'avatar'},
	    rules: {
	        allowedFileTypes: "image/jpeg,image/png,image/jpeg",
	        maxSize: 1024000 * 3
		}
	}).on("lu:success", function (e, data) {
		if(data){
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
		readURL(this);
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
	var type1 = $('input[name="type1"]:checked').val();
    var type2 = $('#type2').val();
    var bank = $("#bank").val();
    var start = $("#startDate").val();
    var end = $("#endDate").val();
    var allmoney = $("#allmoney").val();
    var needTodoor = $('input[name="needTodoor"]:checked').val();//需要上门
    var endorse = $("#endorse").val();
    var hasTicket = $('input[name="hasTicket"]:checked').val();
    var front = $("#FRONT").val();//正面图片
    var des = $("#memberother").val();//备注
    var flawTicket = $('input[name="flawTicket1"]:checked').val();
    if(bank==null || $.trim(bank)==""){
    	myToast("请输入付款行");
        return;
    }
    if(allmoney==null || $.trim(allmoney)==""){
    	myToast("请输入总金额");
        return;
    }
    if(start==null || $.trim(start)==""){
    	myToast("请输入贴现日期");
        return;
    }else if(end==null || $.trim(end)==""){
    	myToast("请输入到期日期");
        return;
    }
    var params = {
		allmoney:allmoney,
		begintime:dateFormat(start),
		endtime:dateFormat(end),
		memberid:memberId,
		belong:"0",
		salepriceid:"0",
		type1:type1,
		type2:type2,
		memberother:des,
		bank:bank,
		needTodoor:needTodoor,
		endorse:endorse,
		hasTicket:hasTicket,
		flawTicket:flawTicket
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
		url:BASEPATH+"/wap/discountrecord/save",
		type:"POST",
		data: params,
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				window.location.href = BASEPATH+"/wap/discountrecord/confirm/page?id=" + data.data.id;
			}else{
				myError(data.msg);
				return;
			}
		}
	});
}

/**
 * 计算贴现利息
 */
function myExcel(){
    var type1 = $('input[name="type1"]:checked').val();
    var type2 = $('#type2').val();
    var start = $("#startDate").val();
    var end = $("#endDate").val();
    var allmoney = $("#allmoney").val();
    var acceptTime = $('input[name="acceptTime"]:checked').val();//承兑期限

    if(start==null || start.trim()==""){
        return;
    }else if(end==null || end.trim()==""){
        return;
    }else if(allmoney==null || allmoney.trim()==""){
        return;
    }
    var params = {
   		"type1":type1,
   		"type2":type2,
   		"start":dateFormat(start),
   		"end":dateFormat(end),
   		"allmoney":allmoney
   	};
    if(type1==2){
    	params.limit = acceptTime;
    }
    $.ajax({
		url:BASEPATH+"/wap/discountrecord/excel/price",
		type:"POST",
		data: params,
		dataType:"json",
		success:function(data){
			$("#day").html(data.jxts);
			$("#price").html(data.txlx);
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
    $("#startDate").val(begindatestr);
    $("#endDate").val(enddatestr);
}

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
        if (x<this.min || x>this.max || !this.reg(x)) {
            uexToast("请输入正确的背书手数！");
            $(obj).val(0);
        }
    }
}

/*查看票样*/
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
 * 初始化来自计算器或者询价页面的数据
 */
function initFromCalOrInq(){
    var type1 = getLocalAndClean("TEMP-TYPE1");//票据属性
    if(type1!=null){
        if(type1==2){//电票（有半年一年期）
            $(".ELE").removeClass("hide");
            var acceptTime = getLocalAndClean("TEMP-ACCEPTTIME");//承兑期限
            if(acceptTime!=null)$("input[name='acceptTime']").setRadio(acceptTime);
            var tradeModel = getLocalAndClean("TEMP-TRADEMODEL");//交易模式
            if(tradeModel!=null)$("input[name='accepTime']").setRadio(tradeModel);
        }
        
        var type2 = getLocalAndClean("TEMP-TYPE2");//承兑行类型
        var start = getLocalAndClean("TEMP-START");//贴现日期
        var end = getLocalAndClean("TEMP-END");//到期日期
        var allmoney = getLocalAndClean("TEMP-ALLMONEY");//总金额

        $("input[name='type1']").setRadio(type1);
        $("#type2").val(type2);
        if(start!=null)$("#startDate").val(start);
        if(end!=null)$("#endDate").val(end);
        $("#allmoney").val(allmoney);

        var hbjine = getLocalAndClean("hbjine");//红包金额
        var hid = getLocalAndClean("hid");//红包主键
        if(hbjine!=null)$("#hbjine").text(hbjine);
        if(hid!=null)$("#hid").val(hid);

        var front = getLocalAndClean("TEMP-FRONT");
        var back = getLocalAndClean("TEMP-BACK");

        var mobile = getLocalAndClean("TEMP-MOBILE");
        var des = getLocalAndClean("TEMP-OTHER");
        var name = getLocalAndClean("TEMP-NAME");
        var sex = getLocalAndClean("TEMP-SEX");
        
        var place = getLocalAndClean("TEMP-PLACE");
        var cityId = getLocalAndClean("TEMP-CITYID");
        var longitude = getLocalAndClean("TEMP-LONGITUDE");
        var latitude = getLocalAndClean("TEMP-LATITUDE");
        var address = getLocalAndClean("TEMP-ADDRESS");

        var day = getLocalAndClean("TEMP-DAY");
        var price = getLocalAndClean("TEMP-PRICE");
        if(front!=null && front.trim()!=""){
            $("#FRONT").val(front);
            $("#IMGFRONT").attr("src",front);
        }
        if(back!=null && back.trim()!=""){
            $("#BACK").val(back);
            $("#IMGBACK").attr("src",back);
        }
        if(mobile!=null)$("#membermobile").val(mobile);
        if(des!=null)$("#memberother").val(des);
        if(name!=null)$("#membername").val(name);
        $("#sex").val(sex);
        
        if(place!=null)$("#place").val(place);
        if(cityId!=null)$("#cityId").val(cityId);
        if(longitude!=null)$("#longitude").val(longitude);
        if(latitude!=null)$("#latitude").val(latitude);
        if(address!=null)$("#address").val(address);

        $("#day").text(day);
        $("#price").text(price);

        myExcel();//计算计息天数等
    }
}
</script>

[@main.footer/]
[/@main.body]