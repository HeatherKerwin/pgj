[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-票据管理']
[@main.header currentmenu='1' topindex='4'/]
<script type="text/javascript" src="https://static.utiexian.com/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/plugins/highcharts/modules/exporting.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/website/radio.js"></script>

<div class="w1200 bc">
    <!-- 票据管理菜单 -->
    <div class="w clearfix mt20">
        <!--tab 菜单-->
        <ul class="fl bcwhite f16 c2d2d2d lh50 tc TXtab oh mt6">
            <li class="w200 lh50 fl bre0e0e0"><a href="${basePath}/zhangbu/account/book/out" class="c2d2d2d dsb">已出库</a></li>
            <li class="w200 lh50 fl bre0e0e0"><a href="${basePath}/zhangbu/account/book/hold" class="c2d2d2d dsb">持有中</a></li>
            <li class="w200 lh50 fl bre0e0e0 bbd43c33"><a href="${basePath}/zhangbu/account/book/pledge" class="cd43c33 dsb">质押中</a></li>
            <li class="w200 lh50 fl"><a href="${basePath}/zhangbu/account/book/in" class="c2d2d2d dsb">待入账</a></li>
        </ul>

        <!--菜单-->
        <div class="fr clearfix tc c3366cc f12">
            <!-- 新增票据 -->
            <div class="bcwhite box-sizing box-shadow w110 h60 fl pt10 cp" id="addBill">
                <img src="https://img.utiexian.com/website/manage/add.png" alt="新增票据">
                <p>新增票据</p>
            </div>
            <!-- 批量导入票据 -->
            <div class="bcwhite box-sizing box-shadow w110 h60 fl ml20 pt10 cp" id="addAllBill">
                <img src="https://img.utiexian.com/website/manage/add.png" alt="批量导入票据">
                <p>批量导入票据</p>
            </div>
            <!-- 票据提醒 -->
            <div class="bcwhite box-sizing box-shadow w110 h60 fl ml20 pt10 cp">
                <a href="${basePath}/zhangbu/main"><img src="https://img.utiexian.com/website/manage/tixing.png" alt="票据提醒"></a>
                <p><a href="${basePath}/zhangbu/main">票据提醒</a></p>
            </div>
        </div>
    </div>
    <!-- 票据管理菜单 end -->

    <!-- 到期日期提醒 -->
    <div class="w mt20 bcwhite box-shadow box-sizing pl20 pt20 pr20 pb20" id="endDates">
    	<div class="w tc">
	    	<div class="lh30 pt15 pb20 pl20 f14 clearfix">
	    		<div class="fl">添加时间：</div>
	            <div class="fl"><input type="text" class="bae0e0e0 br3 h30" id="search_start_Date" readonly="readonly">~</div>
	            <div class="fl"><input type="text" class="bae0e0e0 br3 h30 ml10" id="search_end_Date" readonly="readonly"></div>
	    		<div class="fl w200 m150">承兑行类型：</div>
	    		<div class="fl w200">
					<select class="w170 h30 select107 pl10 pr10 box-sizing" id="type">
						<option value="0">请选择</option>
						<option value="1">国股</option>
						<option value="8">大商</option>
						<option value="2">小商</option>
						<option value="3">农商</option>
						<option value="4">农信</option>
						<option value="5">农合</option>
						<option value="6">外资</option>
						<option value="7">村镇</option>
					</select>
				</div>
				<div class="fl"><input type="button" class="w150 h30 ba bad43c33 bcd43c33 mr20 cwhite br3 cp" value="查询" readonly="readonly" onclick="loadDatePledge();"></div>
	    	
	    		<!-- 批量导入票据历史记录 -->
			    <div class="fr tc c3366cc f12" onclick="loadJiLu();">
			        <div class="bcwhite box-sizing box-shadow w110 h60 fl cp">
			            <img src="https://img.utiexian.com/website/manage/jilu.png" alt="批量导入票据记录">
			            <p>批量导入票据记录</p>
			        </div>
			    </div>
	    	</div>
	    </div>
        <!-- 列表 -->
        <div class="w box-sizing bte0e0e0 ble0e0e0 bre0e0e0 tc mt20">
            <div class="h60 lh60 bcf9f9f9 f14 c717583 clearfix bbe0e0e0 box-sizing">
                <div class="fl w300 bre0e0e0 box-sizing">承兑行</div>
                <div class="fl w130 bre0e0e0 box-sizing">票面金额（元）</div>
                <div class="fl w130 bre0e0e0 box-sizing">质押金额（元）</div>
                <div class="fl w130 bre0e0e0 box-sizing">到期日</div>
                <div class="fl w130 bre0e0e0 box-sizing">来票公司</div>
                <div class="fl w130 bre0e0e0 box-sizing">赎回提醒</div>
                <div class="fl w208">操作</div>
            </div>
            <ul id="dairuzhang"></ul>
        </div>
    </div>
    <!-- 到期日期提醒 end -->
    
    <!-- 分页 -->
    <div class="oh tc">
        <div class="clearfix" id="pager"></div>
    </div>
</div>

<!--弹窗-->
<div class="w h pf bc05 zi10 top" style="display: none" id="maskWindow">
    <div class="bc w590 bcwhite bae0e0e0 br3 box-sizing pb20 mt170">
        <div class="w h57 lh57 box-sizing pl20 pr20 bbe0e0e0 clearfix">
            <!--弹窗名称-->
            <div class="fl" id="windowTitle"></div>
            <!--关闭按钮-->
            <label for="closeIcon" class="fr cp mt20 dsb" id="closeBtn">
                <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                <button id="closeIcon" class="oln none"></button>
            </label>
        </div>
        
        <!-- 批量导入记录 -->
        <div class="flex-row flex-direction-column w tc" id="jiluWindow" style="display: none">
            <div class="flex-row flex-direction-column w570 bte0e0e0 ble0e0e0 bre0e0e0 mt20">
				<ul class="w clearfix oh f12 lh30 bbe0e0e0">
				    <li class="fl w260 bre0e0e0">表格名称</li>
				    <li class="fl w150 bre0e0e0">上传日期</li>
				    <li class="fl w155">上传状态</li>
				</ul>
				<!--列表展示-->
				<div class="flex-row flex-direction-column f14 lh40">
				  	<ul class="w oya hmax200 tc" id="content1">
						
					</ul>
				</div>
			</div>
        </div>
		<!-- 批量导入记录 end-->

        <!--弹窗内容-->
        <div class="w box-sizing pl20 pr20 oya" style="max-height: 500px;">
            <!-- 编辑 -->
            <div class="w lh30" style="display: none" id="editWindow">
                <div class="bl3_fc4d42 pl10 cfc4d42 fb f18 h20 lh20 mt30">信息补充</div>

                <div class="w h30 mt20 clearfix pb10 xuxian">
                    <div class="fl w100 fb">票据属性：</div>
                    <ul class="fl ryRadioCheckd mt3 ml20">
                        <li>
                            <input class="ryRadio" type="radio" id="type0" name="type" value="0" checked>
                            <label class="checked" for="type0">银电</label>
                        </li>
                        <li>
                            <input class="ryRadio" type="radio" id="type1" name="type" value="1">
                            <label for="type1">银纸</label>
                        </li>
                        <li>
                            <input class="ryRadio" type="radio" id="type2" name="type" value="2">
                            <label for="type2">商电</label>
                        </li>
                        <li>
                            <input class="ryRadio" type="radio" id="type3" name="type" value="3">
                            <label for="type3">商纸</label>
                        </li>
                    </ul>
                </div>

                <div class="w mt10 clearfix pb10 xuxian">
                    <div class="fl w100 fb">承兑行：</div>
                    <input id="bank" type="text" class="fl w200 h30 box-sizing bae0e0e0 br3 ml20 pl10 pr10 validate[required]" placeholder="请输入承兑行全称">
                </div>

                <div class="w mt10 clearfix pb10 xuxian">
                    <div class="fl w100 fb">票号：</div>
                    <input id="draftNo" type="text" class="fl w200 h30 box-sizing bae0e0e0 br3 ml20 pl10 pr10 validate[custom[draftNo]]" placeholder="请输入票号（选填）">
                </div>

                <div class="w mt10 clearfix pb10 xuxian">
                    <div class="fl w100 fb">票面金额：</div>
                    <input id="allmoney" type="text" class="fl w200 h30 box-sizing bae0e0e0 br3 ml20 pl10 pr10 validate[custom[Okmoney]],validate[required],validate[custom[number]],validate[custom[money]]" placeholder="请输入票面金额">
                </div>

                <div class="w mt10 clearfix pb10 xuxian">
                    <div class="fr clearfix">
                        <div class="fl w100 fb">赎回提醒：</div>
                        <input type="text" class="fl w100 h30 box-sizing bae0e0e0 br3 ml20 pl10 pr10" id="pledge_in_date" readonly="readonly">
                        <div class="fl w30 h27 rili ml10 mt1 cp"></div>
                    </div>
                    <div class="fl clearfix">
                        <div class="fl w100 fb">到期日期：</div>
                        <input type="text" class="fl w100 h30 box-sizing bae0e0e0 br3 ml20 pl10 pr10 validate[required]" id="endDate" readonly="readonly">
                        <div class="fl w30 h27 rili ml10 mt1 cp"></div>
                    </div>
                </div>
                
                <div class="w mt10 clearfix pb10 xuxian">
                    <div class="clearfix">
                        <div class="fl w100 fb">质押日期：</div>
                        <input type="text" class="fl w100 h30 box-sizing bae0e0e0 br3 ml20 pl10 pr10" id="pledge_out_date" readonly="readonly">
                        <div class="fl w30 h27 rili ml10 mt1 cp"></div>
                    </div>
                </div>
                
                <div class="w mt10 clearfix pb10 xuxian">
                    <div class="fl w100 fb">质押金额：</div>
                    <input type="text" id="pledgeMoney" class="fl w120 h30 box-sizing bae0e0e0 br3 ml20 pl10 pr10 validate[required]">
                </div>

                <div class="w mt10 clearfix pb10 xuxian">
                    <div class="fl w100 fb">来票公司：</div>
                    <textarea id="draftFrom" class="fl w250 h80 bae0e0e0 br3 ml20 box-sizing pt10 pr10 pl10" maxlength="200" placeholder="选填"></textarea>
                </div>

                <div class="w mt10 clearfix pb10 xuxian">
                    <div class="fl w100 fb">票据去处：</div>
                    <textarea id="draftTo" class="fl w250 h80 bae0e0e0 br3 ml20 box-sizing pt10 pr10 pl10" maxlength="200" placeholder="选填"></textarea>
                </div>

                <!--按钮操作-->
                <div class="w mt20 clearfix tc">
                    <input type="button" value="保存" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 cp" onclick="save(1);">
                    <input type="button" value="保存为持有中" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 cp" onclick="save(2);">
                </div>
            </div>
        </div>
        
		[@main.popup/]
    </div>
</div>

<input type="hidden" id="billId" />
<input type="hidden" id="json"/>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="HOLD">
{{#each list}}
	<li class="pr bbe0e0e0 box-sizing">
		<div class="pa top left z12 w44 h44">
   			<img src="https://img.utiexian.com/website/180606/shangdian.png" class="{{toDraftType draftType 1}}" width="44" height="44" alt="商票电票">
			<img src="https://img.utiexian.com/website/180606/shangzhi.png" class="{{toDraftType draftType 2}}" width="44" height="44" alt="商票纸票">
 			<img src="https://img.utiexian.com/website/180606/yindian.png" class="{{toDraftType draftType 3}}" width="44" height="44" alt="银票电票">
    		<img src="https://img.utiexian.com/website/180606/yinzhi.png" class="{{toDraftType draftType 4}}" width="44" height="44" alt="银票纸票">
		</div>
		<div class="clearfix f15 h86 lh86">
			<div class="fl w300 bre0e0e0 box-sizing pl35 pr10 pt20 pb15 h">
				<span class="lh24 text-line2">{{bank}}</span>
			</div>
			<div class="fl w130 bre0e0e0 box-sizing">{{toAllmoney allmoney}}</div>
			<div class="fl w130 bre0e0e0 box-sizing">{{toAllmoney pledgeMoney}}</div>
			<div class="fl w130 bre0e0e0 box-sizing">{{formatDate expiryDate}}</div>
			<div class="fl w130 bre0e0e0 box-sizing pl35 pr10 pt20 pb15 h">
				<span class="lh24 text-line2">{{draftFrom}}</span>
			</div>
			<div class="fl w130 bre0e0e0 box-sizing">{{formatDate pledgeInDate}}</div>
			<div class="fl w208">
				<a class="fl c3366cc cp w75 ml10" onclick="editList({{id}})">编辑</a>
				<a class="fl c3366cc w75 cp" onclick="deleteList({{id}});">删除</a>
			</div>
		</div>
	</li>
{{/each}}
</script>
<script type="text/x-handlebars-template" id="JILUS">
{{#each list}}
	<li class="flex-row flex-direction-row flex-justify-center bbe0e0e0 h40">
		<div class="bre0e0e0 w260">{{file_name}}</div>
		<div class="bre0e0e0 w150 oh wsn toe">{{create_time}}</div>
		<div class="w155">{{toAmount amount}}</div>
	</li>
{{/each}}
</script>
<script>
/**
* 票据图标（订单类型：银纸、银电、商纸、商电）
*/
Handlebars.registerHelper('toDraftType', function(draftType,num, options) {
	if(draftType == "YZ"){
		if(num != 4){
			return "none";
		}
	}else if(draftType == "YD"){
		if(num != 3){
			return "none";
		}
	}else if(draftType == "SZ"){
		if(num != 2){
			return "none";
		}
	}else if(draftType == "SD"){
		if(num != 1){
			return "none";
		}
	}
});

/**
* 时间格式化
*/
Handlebars.registerHelper('formatDate', function(num, options) {
    if(num!=null){
        num = num.replace(/-/g, "/");
        var d = new Date(num);
        return d.format('yyyy-MM-dd');
    }else{
        return "--";
    }
});

/**
* 导入状态
*/
Handlebars.registerHelper('toAmount', function(num, options) {
    if(num==0){
        return "审核中";
    }else{
        return "导入成功";
    }
});

/**
*金额格式化
*/
Handlebars.registerHelper('toAllmoney', function(allmoney, options) {
	if(allmoney!=null&&allmoney!=""){
		return fmoney(allmoney,2);
	}else{
		return "--";
	}
});

var memberId = '${member.id}';
$(document).ready(function() {
	loadDatePledge();
});

/* 设置时间 */
function setDate(id){
	$("#windowTitle").html("到期提醒");
	
	$("#billId").val(id);
	
    $("#maskWindow").show();
    $("#setWindow").show();
};

/* 关闭弹窗 */
$("#closeBtn , .closeBtn").on("click",function(){
    $("#windowTitle").html("");
    $("#maskWindow").hide();
    $("#setWindow").hide();
    $("#editWindow").hide();
    $("#addWindow").hide();
    $("#jiluWindow").hide();
    $("#addAllWindow").hide();
});

/**
 *  加载票据提醒（已持有）
 */
function loadDatePledge(){
    var data = {memberId:memberId,recordType:"PLEDGE"};
    var startDate = $("#search_start_Date").val();
    var endDate = $("#search_end_Date").val();
    var type2 = $("#type").val();
    
    if(startDate!=null&&startDate!=""){
    	data.start = startDate;
    }
    if(endDate!=null&&endDate!=""){
    	data.end = endDate;
    }
    if(type2!=null&&type2>0){
    	data.type2 = type2;
    }
	$("#pager").sjAjaxPager({
        url: "${bootAppPath}/draftrecord/page",
        pageSize:10,
        myTotalType:"BOOT",
        searchParam: data,
        beforeSend: function () {
        	
        },success: function (data) {
       		var source = $("#HOLD").html();
            var template = Handlebars.compile(source);
            var html = template(data.data.data);
            $("#dairuzhang").html(html);
        },complete: function(){
        }
    });
};

/**
 *  加载记录
 */
function loadJiLu(){
	$("#windowTitle").html("用户批量上传记录");
    
    var data = {memberId:memberId};
    $("#pager").sjAjaxPager({
        url: "${bootAppPath}/draftrecordimport/page/info",
        pageSize:10,
        myTotalType:"BOOT",
        searchParam: data,
        beforeSend: function () {
        	
        },success: function (data) {
        	console.log(data);
        	if(data.data.data!=null&&data.data.data.total!=null&&data.data.data.total>0){
        		$("#jiluWindow").show();
                $("#maskWindow").show();
        		var source = $("#JILUS").html();
                var template = Handlebars.compile(source);
                var html = template(data.data.data);
                $("#content1").html(html);
        	}else{
        		alert("您还没有导入过数据");
        	}
        },complete: function(){
        }
    });
}

/* 编辑 */
function editList(id){
	$("#billId").val(id);
	var params = {id:id};
	var url = '${bootAppPath}/draftrecord/get';
	
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			var data = data.data.data;
			$("#draftFrom").val(data.draftFrom);
			$("#draftTo").val(data.draftTo);
			$("#allmoney").val((data.allmoney)/10000);
			$("#bank").val(data.bank);
			$("#draftNo").val(data.draftNo);
			$("#pledge_in_date").val(data.pledgeInDate);
			$("#pledge_out_date").val(data.pledgeOutDate);
			$("#pledgeMoney").val(data.pledgeMoney);
			$("#endDate").val(data.expiryDate);
			
			if(data.draftType == "YZ"){
				$("input[name='type']").each(function(){
					$(this).attr("checked",false);
					$(this).next().removeClass("checked");
					if($(this).val() == 1){
						$(this).attr("checked",true);
						$(this).next().addClass("checked");
					}
				});
			}else if(data.draftType == "YD"){
				$("input[name='type']").each(function(){
					$(this).attr("checked",false);
					$(this).next().removeClass("checked");
					if($(this).val() == 0){
						$(this).attr("checked",true);
						$(this).next().addClass("checked");
					}
				});
			}else if(data.draftType == "SZ"){
				$("input[name='type']").each(function(){
					$(this).attr("checked",false);
					$(this).next().removeClass("checked");
					if($(this).val() == 3){
						$(this).attr("checked",true);
						$(this).next().addClass("checked");
					}
				});
			}else if(data.draftType == "SD"){
				$("input[name='type']").each(function(){
					$(this).attr("checked",false);
					$(this).next().removeClass("checked");
					if($(this).val() == 2){
						$(this).attr("checked",true);
						$(this).next().addClass("checked");
					}
				});
			}
			
			$("#windowTitle").html("编辑票据");
			$("#maskWindow").show();
			$("#editWindow").show();
		}else{
			alert(data.data.msg);
		}
	};
};

/**
 * 保存数据
 */
function save(num){
	var id = $("#billId").val();
	
	var draftFrom = $("#draftFrom").val();
	var draftTo = $("#draftTo").val();
	var allmoney = $("#allmoney").val();
	var bank = $("#bank").val();
	var draftNo = $("#draftNo").val();
	var expiryDate = $("#endDate").val();
	var txje = $("#txje").val();
	
	var pledgeMoney = $("#pledgeMoney").val();
	var pledge_in_date = $("#pledge_in_date").val();
	var pledge_out_date = $("#pledge_out_date").val();
	var json = $("#json").val();
	
	if(!$("#pledgeDate").validationEngine("validate")){
		$("#pledgeDate").focus();
		return ;
	};
	
	if(!$("#pledgeMoney").validationEngine("validate")){
		$("#pledgeMoney").focus();
		return ;
	};
	
	if(!$("#allmoney").validationEngine("validate")){
		$("#allmoney").focus();
		return ;
	};
	
	if(!$("#endDate").validationEngine("validate")){
		$("#endDate").focus();
		return ;
	};
	
	if(!$("#bank").validationEngine("validate")){
		$("#bank").focus();
		return ;
	};
	
	if(!$("#draftNo").validationEngine("validate")){
		$("#draftNo").focus();
		return ;
	};
	
	var draftType;
	$("input[name='type']").each(function(){
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
	
	var params = {id:id,memberId:memberId,draftType:draftType,bank:bank,draftNo:draftNo,allmoney:(parseFloat(allmoney)*10000),
			expiryDate:expiryDate,draftFrom:draftFrom,draftTo:draftTo,json:json};
	
	if(num==1){
		params.recordType = 'PLEDGE';
		params.pledgeMoney = pledgeMoney;
		params.pledgeInDate = pledge_in_date;
		params.pledgeOutDate = pledge_out_date;
	}else{
		params.recordType = 'HOLD';
	}
	
	var url = '${bootAppPath}/draftrecord/save';
	
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			alert("修改成功");
			
			$("#windowTitle").html("");
		    $("#maskWindow").hide();
		    $("#setWindow").hide();
		    $("#editWindow").hide();
		    $("#json").val("");
		    loadDateHold();
		}else{
			alert(data.data.msg);
		}
	}
};

/**
 * 扩充字段的变更
 */
function change(obj){
	var name = $(obj).attr("data-name");
	var v = $(obj).val();
	var s = $("#json").val();
	
	var json = JSON.parse(s);
	$.each(json, function (n, value) {
		if(n==name){
			value.value= v;
		}
	});
	var jsonStr = JSON.stringify(json);
	$("#json").val(jsonStr);
}

/**
 * 删除订单
 */
function deleteList(id){
	var params = {id:id};
	var url = '${bootAppPath}/draftrecord/del';
	
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			alert("删除成功");
			loadDateHold();
		}else{
			alert(data.data.msh);
		}
	}
};

//初始化日期
var begintimelong = Date.parse(new Date());
var begintime = new Date(begintimelong);
var fullyear = begintime.getFullYear();
//获取完整的年份(4位,1970-????)
var month = begintime.getMonth() + 1;
//获取当前月份(0-11,0代表1月)
var date = begintime.getDate();
//获取当前日(1-31)

var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
var endfullyear = endtime.getFullYear();
//获取完整的年份(4位,1970-????)
var endmonth = endtime.getMonth() + 1;
//获取当前月份(0-11,0代表1月)
var enddate = endtime.getDate();
//获取当前日(1-31)

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

//日历
!function(){
    laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
}();
//日期范围限制
//    贴现日期
var start = {
    elem : '#pledgeDate',
    format : 'YYYY-MM-DD',
    //min: laydate.now(), //设定最小日期为当前日期
    min : '1900-01-01', //设定最小日期
    max : '2099-06-16', //最大日期
    istoday : false,
    choose : function(datas) {
        end.min = datas; //开始日选好后，重置结束日的最小日期
        end.start = datas //将结束日的初始值设定为开始日
    }
};
//    到期日期
var end = {
    elem : '#endDate',
    format : 'YYYY-MM-DD',
    min : laydate.now(),
    max : '2099-06-16',
    istoday : false,
    choose : function(datas) {
        start.max = datas; //结束日选好后，充值开始日的最大日期
        $("#endDate").val(datas);
    }
};

var indate = {
    elem : '#inDate',
    format : 'YYYY-MM-DD',
    //min: laydate.now(), //设定最小日期为当前日期
    min : '1900-01-01', //设定最小日期
    max : '2099-06-16', //最大日期
    istoday : false,
    choose : function(datas) {
        end.min = datas; //开始日选好后，重置结束日的最小日期
        $("#inDate").val(datas); //将结束日的初始值设定为开始日
    }
};

//贴现日期
var add_start = {
    elem : '#add_tiexianDate',
    format : 'YYYY-MM-DD',
    //min: laydate.now(), //设定最小日期为当前日期
    min : '1900-01-01', //设定最小日期
    max : '2099-06-16', //最大日期
    istoday : false,
    choose : function(datas) {
        add_end.min = datas; //开始日选好后，重置结束日的最小日期
        add_end.start = datas //将结束日的初始值设定为开始日
    }
};

//到期日期
var add_end = {
    elem : '#add_endDate',
    format : 'YYYY-MM-DD',
    min : laydate.now(),
    max : '2099-06-16',
    istoday : false,
    choose : function(datas) {
        add_start.max = datas; //结束日选好后，充值开始日的最大日期
        $("#add_endDate").val(datas);
        updateEndDate();
    }
};

var add_inData = {
    elem : '#add_inData',
    format : 'YYYY-MM-DD',
    //min: laydate.now(), //设定最小日期为当前日期
    min : '1900-01-01', //设定最小日期
    max : '2099-06-16', //最大日期
    istoday : false,
    choose : function(datas) {
        end.min = datas; //开始日选好后，重置结束日的最小日期
        $("#add_inData").val(datas); //将结束日的初始值设定为开始日
    }
};
	
var noticedate = {
    elem : '#add_noticeDate',
    format : 'YYYY-MM-DD',
    //min: laydate.now(), //设定最小日期为当前日期
    min : '1900-01-01', //设定最小日期
    max : '2099-06-16', //最大日期
    istoday : false,
    choose : function(datas) {
        end.min = datas; //开始日选好后，重置结束日的最小日期
        $("#add_noticeDate").val(datas); //将结束日的初始值设定为开始日
    }
};

//质押赎回提醒时间
var pledge_in_date = {
    elem : '#pledge_in_date',
    format : 'YYYY-MM-DD',
    min : laydate.now(),
    max : '2099-06-16',
    istoday : false,
    choose : function(datas) {
        
    }
};

//质押时间
var pledge_out_date = {
    elem : '#pledge_out_date',
    format : 'YYYY-MM-DD',
    min : '1900-01-01',
    max : '2099-06-16',
    istoday : false,
    choose : function(datas) {
        
    }
};

//票据质押的时间
var add_Out_Date = {
	elem : '#add_Out_Date',
	format : 'YYYY-MM-DD',
	//min: laydate.now(), //设定最小日期为当前日期
	min : '1900-01-01', //设定最小日期
	max : '2099-06-16', //最大日期
	istoday : false,
	choose : function(datas) {
	   
	}
};

//查询开始时间
var search_start_Date = {
    elem : '#search_start_Date',
    format : 'YYYY-MM-DD',
    //min: laydate.now(), //设定最小日期为当前日期
    min : '1900-01-01', //设定最小日期
    max : laydate.now(), //最大日期
    istoday : false,
    choose : function(datas) {
    	search_end_Date.min = datas; //开始日选好后，重置结束日的最小日期
        search_end_Date.start = datas //将结束日的初始值设定为开始日
    }
};
//查询到期日期
var search_end_Date = {
    elem : '#search_end_Date',
    format : 'YYYY-MM-DD',
    min : '1900-01-01', //设定最小日期
    max : '2099-06-16', //最大日期
    istoday : false,
    choose : function(datas) {
    	search_start_Date.max = datas; //结束日选好后，充值开始日的最大日期
    }
};

//票据质押赎回时间
var add_In_Date = {
	elem : '#add_In_Date',
	format : 'YYYY-MM-DD',
	//min: laydate.now(), //设定最小日期为当前日期
	min : '1900-01-01', //设定最小日期
	max : '2099-06-16', //最大日期
	istoday : false,
	choose : function(datas) {
	   
	}
};

laydate(add_start);
laydate(add_end);
laydate(add_inData);
laydate(noticedate);
laydate(start);
laydate(end);
laydate(indate);

laydate(pledge_in_date);
laydate(pledge_out_date);
laydate(add_Out_Date);
laydate(add_In_Date);

laydate(search_start_Date);
laydate(search_end_Date);
</script>
[@main.footer/]
[/@main.body]