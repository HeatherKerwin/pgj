[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-修改票据管理']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
[@main.header currentmenu='1' topindex='4'/]

<div class="w1200 bc bae0e0e0 bcwhite mt20 mb20">
	<form id="form" method="post"  action="${basePath}/zhangbu/editpiaoju">
    <div class="pl20 pr20 f14 c2d2d2d">
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">票据状态：</div>
            <ul class="pt10 fl MGcheckbox lh27">
            	<input type="hidden" id="acc" name="id" value="${id}"/>
            	<li class="fl mr20"><input type="radio" id="yes" class="none" name="type" value="1" checked><label class="fl tc w60 h27 br3 cwhite bce84c3d tiexian_css" for="yes">已贴现</label></li>
            	<li class="fl"><input type="radio" id="no" class="none" name="type" value="0"><label class="fl tc w60 h27 br3 tiexian_css" for="no">未贴现</label></li>
            </ul>
        </div>
        <div class="w h45 xuxian"> 
            <div class="fl tl w100 fb lh45">票据属性：</div>
            <ul class="pt10 fl MGcheckbox lh27">
            	<input type="hidden" id="shuxing" value="1">
                <li class="fl mr20"><input type="radio" id="zhi" class="none" name="shuxing" value="1" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d shuxing_css" for="zhi">纸票</label></li>
                <li class="fl"><input type="radio" id="dian" class="none" name="shuxing" value="2"><label class="fl tc w46 h27 br3 shuxing_css" for="dian">电票</label></li>
            </ul>
        </div>
        <div class="w h45 xuxian none" id="acceptdate">
            <div class="fl tl w100 fb lh45">承兑期限：</div>
            <ul class="pt10 fl MGcheckbox lh27">
            	<input type="hidden" id="acceptTime" value="0">
                <li class="fl mr20"><input type="radio" id="bannian" class="none" name="acceptTime" value="0" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d acceptTime_css" for="bannian">半年期</label></li>
                <li class="fl"><input type="radio" id="yinian" class="none" name="acceptTime" value="1"><label class="fl tc w46 h27 br3 acceptTime_css" for="yinian">一年期</label></li>
            </ul>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">承兑行类型：</div>
            <ul class="pt10 fl MGcheckbox lh27">
            	<input type="hidden" id="type1" value="1">
                <li class="fl mr20"><input type="radio" id="guogu" class="none" name="type1" value="1" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d type1_css" for="guogu">国股</label></li>
                <li class="fl mr20"><input type="radio" id="xiaoshang" class="none" name="type1" value="2"><label class="fl tc w46 h27 br3 type1_css" for="xiaoshang">小商</label></li>
                <li class="fl mr20"><input type="radio" id="waizi" class="none" name="type1" value="3"><label class="fl tc w46 h27 br3 type1_css" for="waizi">外资</label></li>
                <li class="fl mr20"><input type="radio" id="nongshang" class="none" name="type1" value="4"><label class="fl tc w46 h27 br3 type1_css" for="nongshang">农商</label></li>
                <li class="fl mr20"><input type="radio" id="nonghe" class="none" name="type1" value="5"><label class="fl tc w46 h27 br3 type1_css" for="nonghe">农合</label></li>
                <li class="fl mr20"><input type="radio" id="nongxin" class="none" name="type1" value="6"><label class="fl tc w46 h27 br3 type1_css" for="nongxin">农信</label></li>
                <li class="fl mr20"><input type="radio" id="cunzhen" class="none" name="type1" value="7"><label class="fl tc w46 h27 br3 type1_css" for="cunzhen">村镇</label></li>
                <li class="fl"><input type="radio" id="dashang" class="none" name="type1" value="8"><label class="fl tc w46 h27 br3 type1_css" for="dashang">大商</label></li>
            </ul>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">票据金额：</div>
            <input id="allprice" type="text" name="allprice" placeholder="请输入金额" class="fl w129 h27 lh27 bae0e0e0 mt8 ti8 tiexianlixiBlur">
            <div class="fl f14 lh45 ml10">万元</div>
        </div>
        <div class="w h45 lh45" id="price">
            <div class="fl tl w100 fb">价格：</div>
            <div class="fl w129 Interestdiv1" id="month1">
                <span class="fl ml10 ce84c3d">月息</span>
            </div>
            <div class="fl w129 Interestdiv1 none" id="year1">
                <span class="fl ml10 ce84c3d">年息</span>
            </div>
            <div class="fl w129 Interestdiv1" id="ten1">
                <span class="fl ml10">每十万扣</span>
            </div>
            <div class="cb"></div>
        </div>
        <div class="w h45 xuxian lh27" id="lilv">
            <div class="fl tc ml100" id="month">
                <input id="yuelilv" name="yuelilv" type="text" class="fl w68 h27 bae0e0e0 f14 tc " placeholder="00.00" onblur="jisuan()">
                <div class="fl ml10 mr10"><span class="w16 mr20">‰</span></div>
            </div>
            <div class="fl tc ml100 none" id="year">
                <input id="nianlilv" name="nianlilv" type="text" class="fl w68 h27 bae0e0e0 f14 tc" placeholder="00.00" onblur="jisuan1()">
                <div class="fl ml10 mr10"><span class="f14 mr20 tc">%</span></div>
            </div>
            <div class="fl tc ml100 none" id="ten">
                <input id="tenlilv" name="ten" type="text" class="fl w140 h27 bae0e0e0 f14 tc" placeholder="00.00" onblur="jisuan2()">
                <div class="fl ml10 mr10">元</div>
            </div>
            <div class="cb"></div>
        </div>
        <div class="w h45">
            <div class="fl tl w100 fb lh45">贴现日期：</div>
            <input class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 mt8 ti8 inline" id="start" name="start" readonly="readonly"/>
            <label class="fl w30 h27 rili mt8" for="start"></label>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w100 fb lh45">到期日期：</div>
            <input class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 mt8 ti8 inline" id="end" name="end" readonly="readonly"/>
            <label class="fl w30 h27 rili mt8" for="end"></label>
        </div>
        <div class="w h45 xuxian" id="lixi">
            <div class="fl tl w100 fb lh45">贴现利息：</div>
            <input id="tiexianlixi" name="tiexianlixi" type="text" placeholder="请输入金额" class="fl w129 h27 lh27 bae0e0e0 mt8 ti8" readonly="readonly">
            <div class="fl f14 lh45 ml10">元</div>
        </div>
        <div class="w h45 xuxian" id="jine">
            <div class="fl tl w100 fb lh45">贴现金额：</div>
            <input id="tiexianjine" name="tiexianjine" type="text" placeholder="请输入金额" class="fl w129 h27 lh27 bae0e0e0 mt8 ti8" readonly="readonly">
            <div class="fl f14 lh45 ml10">万元</div>
        </div>
        <div class="w mt10">
            <input type="checkbox" name="jytx" id="jytx" class="checkbox1 w14 h14" checked="checked">
            <label for="jytx" class="fb ce84c3d">设置到期提醒</label>
        </div>
        <div class="w h45 xuxian">
            <input class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 mt8 ml20 ti8 inline bcwhite" id="tixing" name="tixing" readonly="readonly"/>
            <label class="fl w30 h27 rili mt8" for="tixing"></label>
        </div>
        <div class="w mt10 pb10 xuxian" id="yincang">
            <div class="fl fb w100">备注：</div>
            <textarea name="accountdesc" id="accountdesc" class="fl w540 h120 bae0e0e0 pt10 ti8" placeholder="备注最多可输入140个字......" maxlength="140" style='resize: none;'></textarea>
            <div class=" cb"></div>
        </div>
    </div>
    <div class="w h70 bte0e0e0 bbe0e0e0 bcf9f9f9 tc pt15 f18 mt20 mb30">
        <a href="javascript:void(0);" class="fr cd43c33 bad43c33 br3 dsb w166 h45 lh45 mr40" onclick="save()">保存</a>
        <a href="javascript:void(0);" id="yijiantiexian" class="fr cd43c33 bad43c33 br3 dsb w166 h45 lh45 mr40 none" onclick="tiexian()">一键贴现</a>
    </div>
    </form>
</div>
[@main.right /]
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript">
/*
 * 贴现切换
 */
$(".tiexian_css").click(function () {
    if ($(this).prev().val() == "1") {
          $("#yes").parents("li").children("label").addClass("cwhite bce84c3d");
          $("#no").parents("li").children("label").removeClass("cwhite bce84c3d");
          $("#yes").attr("checked",true);
          $("#no").attr("checked",false);
          $("#yijiantiexian").addClass("none");
          $("#price").removeClass("none");
          $("#lilv").removeClass("none");
          $("#lixi").removeClass("none");
          $("#jine").removeClass("none");
    }else{
        $("#no").parents("li").children("label").addClass("cwhite bce84c3d");
        $("#yes").parents("li").children("label").removeClass("cwhite bce84c3d");
        $("#no").attr("checked",true);
        $("#yes").attr("checked",false);
        $("#yijiantiexian").removeClass("none");
        $("#price").addClass("none");
        $("#lilv").addClass("none");
        $("#lixi").addClass("none");
        $("#jine").addClass("none");
    }
});

/*
 * 票据属性切换
 */
$(".shuxing_css").click(function () {
    if ($(this).prev().val() == "1") {
          $("#zhi").parents("li").children("label").addClass("cwhite bce84c3d");
          $("#dian").parents("li").children("label").removeClass("cwhite bce84c3d");
          $("#zhi").attr("checked",true);
          $("#dian").attr("checked",false);
          $("#acceptdate").addClass("none");
          $("#year1").addClass("none");
          $("#month1").removeClass("none");
          $("#ten1").removeClass("none");
          $("#ten1").children("span").removeClass("ce84c3d");
          $("#year").addClass("none");
          $("#month").removeClass("none");
          $("#ten").addClass("none");
          $("#allprice").val("");
          $("#nianlilv").val("");
          $("#tenlilv").val("");
    }else{
        $("#dian").parents("li").children("label").addClass("cwhite bce84c3d");
        $("#zhi").parents("li").children("label").removeClass("cwhite bce84c3d");
        $("#dian").attr("checked",true);
        $("#zhi").attr("checked",false);
        $("#acceptdate").removeClass("none");
        $("#year1").removeClass("none");
        $("#month1").addClass("none");
        $("#ten1").addClass("none");
        $("#year").removeClass("none");
        $("#month").addClass("none");
        $("#ten").addClass("none");
        $("#allprice").val("");
        $("#yuelilv").val("");
        $("#tenlilv").val("");
    }
    if ($(this).prev().attr("checked") == "checked") {
        $("#shuxing").val($(this).prev().val());
    }
});

/*
 * 承兑期限切换
 */
 $(".acceptTime_css").click(function () {
    $(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
    $(this).parents("ul").children("li").children("input").attr("checked",false);
    if($(this).prev().val() == "0"){
    	$("#bannian").next().addClass("cwhite bce84c3d");
    	$("#acceptTime").val($(this).prev().val());
    	$("#bannian").attr("checked",true);
    }else{
    	$("#yinian").next().addClass("cwhite bce84c3d");
    	$("#acceptTime").val($(this).prev().val());
    	$("#yinian").attr("checked",true);
    }
});

/*
 * 承兑行类型 
 */
 $(".type1_css").click(function(){
	$(this).parents("ul").children("li").children("label").removeClass("cwhite bce84c3d");
	$(this).parents("ul").children("li").children("input").attr("checked",false);
	if($(this).prev().val() == "1"){ 
		$("#guogu").next().addClass("cwhite bce84c3d");
		$("#type1").val($(this).prev().val());
		$("#guogu").attr("checked",true);
	}
	if($(this).prev().val() == "2"){ 
		$("#xiaoshang").next().addClass("cwhite bce84c3d");
		$("#type1").val($(this).prev().val());
		$("#xiaoshang").attr("checked",true);
	}
	if($(this).prev().val() == "3"){ 
		$("#waizi").next().addClass("cwhite bce84c3d");
		$("#type1").val($(this).prev().val());
		$("#waizi").attr("checked",true);
	}
	if($(this).prev().val() == "4"){ 
		$("#nongshang").next().addClass("cwhite bce84c3d");
		$("#type1").val($(this).prev().val());
		$("#nongshang").attr("checked",true);
	}
	if($(this).prev().val() == "5"){ 
		$("#nonghe").next().addClass("cwhite bce84c3d");
		$("#type1").val($(this).prev().val());
		$("#nonghe").attr("checked",true);
	}
	if($(this).prev().val() == "6"){ 
		$("#nongxin").next().addClass("cwhite bce84c3d");
		$("#type1").val($(this).prev().val());
		$("#nongxin").attr("checked",true);
	}
	if($(this).prev().val() == "7"){ 
		$("#cunzhen").next().addClass("cwhite bce84c3d");
		$("#type1").val($(this).prev().val());
		$("#cunzhen").attr("checked",true);
	}
	if($(this).prev().val() == "8"){ 
		$("#dashang").next().addClass("cwhite bce84c3d");
		$("#type1").val($(this).prev().val());
		$("#dashang").attr("checked",true);
	}
});

/*
 * 票据提醒
 */
$("input[name='jytx']").change(function() {
    if($(this).attr("checked") == "checked"){
    	$("#tixing").removeAttr("disabled");//日期启用
    }else{
    	$("#tixing").attr("disabled","disabled");//日期禁用
    	$("#tixing").val("");
    }
});

/*
 * 选择利息方式
 */
$("input[name='Interest']").change(function () {
    $(".Interestdiv1").children("label").removeClass("ce84c3d");
    if ($(this).attr("checked") == "checked") {
        $(this).parent("div.Interestdiv1").children("label").addClass("ce84c3d");
    }
    if ($(this).val() == "2"){
        $("#month").addClass("none");
        $("#year").removeClass("none");
        $("#ten").addClass("none");
    }
    if ($(this).val() == "3"){
        $("#month").addClass("none");
        $("#year").addClass("none");
        $("#ten").removeClass("none");
    }
    if ($(this).val() == "1"){
        $("#month").removeClass("none");
        $("#year").addClass("none");
        $("#ten").addClass("none");
    }
});

/*
 * 验证票据金额
 */
function checkPrice(str) {
	var telReg = !!str.match(/^([+]?\d{1,10})(\.\d{1,4})?$/);
	if(telReg == false){
		return false;
	} else {
		return true;
	}
}

/*
 * 验证利率
 */
function checkLilv(str) {
	var telReg = !!str.match(/^([+]?\d{1,2})(\.\d{1,3})?$/);
	if(telReg == false){
		return false;
	} else {
		return true;
	}
}

/*
 * 验证十万利息
 */
function checkTen(str) {
	var telReg = !!str.match(/^[1-9]{5}$/);
	if(telReg == false){
		return false;
	} else {
		return true;
	}
}


/*
 * 票据金额及时验证
 */
$("#allprice").blur(function(){
	var allprice = $("#allprice").val();
	if(allprice==""){
		$("#allprice").validationEngine('showPrompt','* 票据金额不能为空',null,null,true);
		setTimeout(function(){$("#allprice").validationEngine('hide');},2000);
		return;
	}
	if(!checkPrice(allprice)){
		$("#allprice").validationEngine('showPrompt','* 请输入正确的票据金额',null,null,true);
		setTimeout(function(){$("#allprice").validationEngine('hide');},2000);
		return;
	}
	if($("#zhi").attr("checked") == "checked"){
		if(allprice>=500){
			$("#month1").removeClass("none");
	        $("#ten1").addClass("none");
	        $("#ten1").children("span").removeClass("ce84c3d");
	        $("#month").removeClass("none");
	        $("#ten").addClass("none");
	        $("#tenlilv").val("");
		}else if(allprice<500 && allprice!=""){
			$("#month1").addClass("none");
	        $("#ten1").removeClass("none");
	        $("#ten1").children("span").addClass("ce84c3d");
	        $("#month").addClass("none");
	        $("#ten").removeClass("none");
	        $("#yuelilv").val("");
		}else if(allprice==""){
			$("#month1").removeClass("none");
	        $("#ten1").removeClass("none");
	        $("#ten1").children("span").removeClass("ce84c3d");
	        $("#month").removeClass("none");
	        $("#ten").addClass("none");
	        $("#nianlilv").val("");
	        $("#yuelilv").val("");
		}
	}
	jisuan();
	jisuan1();
	jisuan2();
});

/*
 * 计息方式金额及时验证
 */
$("#lilv").find("input").blur(function(){
	var yuelilv = $("#yuelilv").val();
	var nianlilv = $("#nianlilv").val();
	var tenlilv = $("#tenlilv").val();
	if(!checkLilv(yuelilv)){
		$("#yuelilv").validationEngine('showPrompt','* 请正确填写报价',null,null,true);
		setTimeout(function(){$("#yuelilv").validationEngine('hide');},2000);
		$("#yuelilv").val("");
		$("#yuelilv").focus();
		return;
	}
	if(!checkLilv(nianlilv)){
		$("#nianlilv").validationEngine('showPrompt','* 请正确填写报价',null,null,true);
		setTimeout(function(){$("#nianlilv").validationEngine('hide');},2000);
		$("#nianlilv").val("");
		$("#nianlilv").focus();
		return;
	}
	if(!checkTen(tenlilv)){
		$("#tenlilv").validationEngine('showPrompt','* 请正确填写报价',null,null,true);
		setTimeout(function(){$("#tenlilv").validationEngine('hide');},2000);
		$("#tenlilv").val("");
		$("#tenlilv").focus();
		return;
	}
});

/*
 * 月息计算贴现利息
 */
function jisuan(){
	if ($("#allprice").val() == "" || isNaN($("#yuelilv").val())) {
        return;
    }
	var num1 = 0;
    var num2 = 0;
    var num3 = 0;
    
    var allprice = $("#allprice").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var yuelilv = $("#yuelilv").val();
    if(allprice!=null && $.trim(allprice)!="") num1 = Number(allprice)/100000
    
    num2 = getDateDiff(start,end);
    if(yuelilv!=null && $.trim(yuelilv)!="") num3 = yuelilv/30/1000*100000;
    
    var txlx = Number(num1)*Number(num2)*Number(num3)*10000;
    $("#tiexianlixi").val(txlx.toFixed(2));
    $("#tiexianjine").val(Number(allprice)-txlx/10000);
}

/*
 * 年息计算贴现利息
 */
function jisuan1(){
	if ($("#allprice").val() == "" || $("#nianlilv").val() == "") {
        return;
    }
	var num1 = 0;
    var num2 = 0;
    var num3 = 0;
    var num4 = 0
    
    var allprice = $("#allprice").val();
    var start = $("#start").val();
    var end = $("#end").val();
    var nianlilv = $("#nianlilv").val();
    if(allprice!=null && $.trim(allprice)!="") num1 = Number(allprice)/100000
    
    num2 = getDateDiff(start,end);
    if(nianlilv!=null && $.trim(nianlilv)!="") num3 = nianlilv/ 1.2 * 10000 / 10000;
    num4 = num3/30/1000*100000;
    var txlx = Number(num1)*Number(num2)*Number(num4)*10000;
    $("#tiexianlixi").val(txlx.toFixed(2));
    $("#tiexianjine").val(Number(allprice)-txlx/10000);
}

/*
 * 每十万扣计算贴现利息
 */
function jisuan2(){
	if ($("#allprice").val() == "" || $("#tenlilv").val() == "") {
        return;
    }
	var num1 = 0;
    
    var allprice = $("#allprice").val();
    var tenlilv = $("#tenlilv").val();
    if(allprice!=null && $.trim(allprice)!="")
	var	txlx = allprice/10*tenlilv;
    $("#tiexianlixi").val(txlx.toFixed(2));
    $("#tiexianjine").val(Number(allprice)-txlx/10000);
}

/*
 * 一键贴现
 */
function tiexian(){
	var map = new Map();
	var allprice = $("#allprice").val();
	var shuxing = $("#shuxing").val();
	var acceptTime = $("#acceptTime").val();
	var type1 = $("#type1").val();
	var accountdesc= $("#accountdesc").val();
	if(allprice == ''){
        alert("请输入票据金额!");
        return;
    }
	if(shuxing=="1"){
		acceptTime = "";
	}
	map.put("_PAGE", "discountrecord/discount_yp1");//必传
	map.put("shuxing", shuxing);
	map.put("acceptTime",acceptTime);
	map.put("type1",type1);
	map.put("accountdesc",accountdesc);
	map.put("allmoney",allprice);
	_OPENPAGE_FORM(map);
}

/*
 * 加载数据
 */
function loadpiaoju(){
	var id = $("#acc").val();
	$.ajax({
		url:"${basePath}/zhangbu/toupdate",
		type:"POST",
		data:{"id":id},
		dataType:"json",
		success:function(data){
			if(data.response == 'success'){
				var acc = data.data;
				if(acc.isTiexian!=null){
					if(acc.isTiexian == "1"){
						$("#yes").attr("checked","checked");
						$("#no").removeAttr("checked");
						$("#yes").next().addClass("cwhite bce84c3d");
						$("#no").next().removeClass("cwhite bce84c3d");
						$("#tiexianlixi").val(acc.tiexianlixi);
						$("#tiexianjine").val(acc.tiexianjine);
						$("#yuelilv").val(acc.yuelilv);
						$("#nianlilv").val(acc.nianlilv);
						$("#ten").val(acc.ten);
					}else{
						$("#yes").removeAttr("checked");
						$("#no").attr("checked","checked");
						$("#yes").next().removeClass("cwhite bce84c3d"); 
						$("#no").next().addClass("cwhite bce84c3d");
						$("#price").addClass("none");
						$("#lixi").addClass("none");
						$("#jine").addClass("none");
						$("#lilv").addClass("none");
						$("#yijiantiexian").removeClass("none");
						$("#tiexianlixi").val("");
						$("#tiexianjine").val("");
					}
				}
				if(acc.piaojushuxing == "纸票"){
					$("#zhi").attr("checked","checked");
					$("#dian").removeAttr("checked");
					$("#zhi").next().addClass("cwhite bce84c3d");
					$("#dian").next().removeClass("cwhite bce84c3d");
					var allprice = acc.allprice;
					if(allprice>=500){
						$("#month1").removeClass("none");
				        $("#ten1").addClass("none");
				        $("#ten1").children("span").removeClass("ce84c3d");
				        $("#month").removeClass("none");
				        $("#ten").addClass("none");
				        $("#tenlilv").val("");
				        $("#yuelilv").val(acc.yuelilv);
					}else if(allprice<500 && allprice!=""){
						$("#month1").addClass("none");
				        $("#ten1").removeClass("none");
				        $("#ten1").children("span").addClass("ce84c3d");
				        $("#month").addClass("none");
				        $("#ten").removeClass("none");
				        $("#yuelilv").val("");
				        $("#tenlilv").val(acc.ten);
					}
				}else if(acc.piaojushuxing == "电票"){
			        $("#year1").removeClass("none");
			        $("#month1").addClass("none");
			        $("#ten1").addClass("none");
			        $("#year").removeClass("none");
			        $("#month").addClass("none");
			        $("#ten").addClass("none");
			        $("#yuelilv").val("");
			        $("#tenlilv").val("");
					$("#zhi").removeAttr("checked");
					$("#dian").attr("checked","checked");
					$("#zhi").next().removeClass("cwhite bce84c3d");
					$("#dian").next().addClass("cwhite bce84c3d");
					$("#acceptdate").removeClass("none");
					if(acc.acceptTime==0){
						$("#bannian").attr("checked","checked");
						$("#yinian").removeAttr("checked");
						$("#bannian").next().addClass("cwhite bce84c3d");
						$("#yinian").next().removeClass("cwhite bce84c3d");
					}else{
						$("#bannian").removeAttr("checked");
						$("#yinian").attr("checked","checked");
						$("#bannian").next().removeClass("cwhite bce84c3d");
						$("#yinian").next().addClass("cwhite bce84c3d");
					}
					$("#nianlilv").val(acc.nianlilv);
				}
				if(acc.type1!=null){
					switch (acc.type1) {
					case 1:
						$("#guogu").attr("checked","checked");
						$("#guogu").next().addClass("cwhite bce84c3d");
						break;
					case 2:
						$("#guogu").removeAttr("checked");
						$("#xiaoshang").attr("checked","checked");
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
						$("#dashang").attr("checked","checked");
						$("#guogu").removeAttr("checked");
						$("#dashang").next().addClass("cwhite bce84c3d");
						$("#guogu").next().removeClass("cwhite bce84c3d");
						break;
					default:
						break;
					}
				}
				$("#allprice").val(acc.allprice);
				$("#start").val(data.tiexiandate);
				$("#end").val(data.daoqidate);
				if(data.noticetime!=null){
					$("#tixing").val(data.noticetime);
					$("#tixing").removeAttr("disabled");//日期启用
				}else{
					$("#tixing").val("");
				}
				$("#accountdesc").val(acc.accountdesc);
			}
		}
	});
}
$(function(){
	loadpiaoju();
});

/*
 * 修改
 */
function save(){
	var allprice = $("#allprice").val();
	var tiexianlixi = $("#tiexianlixi").val();
	var tiexianjine = $("#tiexianjine").val();
	var yuelilv = $("#yuelilv").val();
	var nianlilv = $("#nianlilv").val();
	var tenlilv = $("#tenlilv").val();
	if(allprice==""){
		$("#allprice").validationEngine('showPrompt','* 票据金额不能为空',null,null,true);
		setTimeout(function(){$("#allprice").validationEngine('hide');},2000);
		$("#allprice").focus();
		return;
	}
	if(!checkPrice(allprice)){
		$("#allprice").validationEngine('showPrompt','* 请输入正确的票据金额',null,null,true);
		setTimeout(function(){$("#allprice").validationEngine('hide');},2000);
		$("#allprice").focus();
		return;
	}
	if($("#yes").attr("checked") == "checked"){//已贴现
		if(tiexianlixi == ''){
	    	alert("请输入贴现利息!");
	    	return;
	    }
	    if($("#zhi").attr("checked") == "checked"){
	    	if(allprice>=500){
	    		if(yuelilv ==''){
	    			$("#yuelilv").validationEngine('showPrompt','* 计息方式不能为空',null,null,true);
	    			setTimeout(function(){$("#yuelilv").validationEngine('hide');},2000);
	    			$("#yuelilv").focus();
	    			return;
	    		}
	    	}else{
	    		if(tenlilv ==''){
	    			$("#tenlilv").validationEngine('showPrompt','* 计息方式不能为空',null,null,true);
	    			setTimeout(function(){$("#tenlilv").validationEngine('hide');},2000);
	    			$("#tenlilv").focus();
	    			return;
	    		}
	    	}
	    }
	    if($("#dian").attr("checked") == "checked"){
	    	if(nianlilv ==''){
	    		$("#nianlilv").validationEngine('showPrompt','* 计息方式不能为空',null,null,true);
    			setTimeout(function(){$("#nianlilv").validationEngine('hide');},2000);
    			$("#nianlilv").focus();
				return;
			}
	    }
	    if(tiexianjine != (Number(allprice)-tiexianlixi/10000)){
			alert("贴现金额有误");
			$("#tiexianjine").val("");
			return;
		}
	}
    $("#form").submit();
}

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
        $("#start").text(datas);
        jisuan();
		jisuan1();
		jisuan2();
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
        $("#end").text(datas);
        jisuan();
		jisuan1();
		jisuan2();
    }
};
//提醒日期
var tixing = {
    elem: '#tixing',
    format: 'YYYY-MM-DD',
    min: laydate.now(),
    max: '2099-06-16',
    istime: true,
    istoday: false,
    choose: function(datas){
        start.max = datas; //结束日选好后，充值开始日的最大日期
        $("#tixing").text(datas);
    }
};
laydate(start);
laydate(end);
laydate(tixing);
laydate({
    elem: '#first',
    format: 'YYYY-MM-DD',
    festival: true, //显示节日
    choose: function(datas){ //选择日期完毕的回调
    }
});

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
$("input[name='start']").val(fullyear+"-"+month+"-"+date);


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
$("input[name='end']").val(endfullyear+"-"+endmonth+"-"+enddate);
$("#tixing").val(endfullyear+"-"+endmonth+"-"+enddate);
$("input[name='tixing']").val(endfullyear+"-"+endmonth+"-"+enddate);
</script>
<!--foot-->
[@main.footer/]
[/@main.body]