[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-票据管理']
[@main.header currentmenu='1' topindex='4'/]
<script type="text/javascript" src="https://static.utiexian.com/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/plugins/highcharts/modules/exporting.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/website/radio.js"></script>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">

<div class="w1200 bc">
    <!-- 票据管理菜单 -->
    <div class="w clearfix mt20">
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

    <div class="w h60 lh40 box-shadow box-sizing pt10 pb10 pl20 pr20 bcwhite mt20 clearfix">
        <div class="fl f16">
            <label for="">货款总额：</label>
            <input type="number" class="w230 h40 bae0e0e0 bcwhite mr10 box-sizing pl10" id="allmoney">
            <span>元</span>
        </div>

        <div class="fr tc f14">
            <button class="w100 h40 bcf9f9f9 bae0e0e0 br3 c2d2d2d cp mr10" onclick="loadDateHolad();">手动选择</button>
            <button class="w100 h40 bcd43c33 bad43c33 br3 cwhite cp" onclick="save(1);">一键查询</button>
        </div>
    </div>

    <!-- 到期日期提醒 -->
    <div class="w mt20 bcwhite box-shadow box-sizing pl20 pt20 pr20 pb20" id="endDates">
        <h4>预付货款票核算：</h4>
        <!-- 列表 -->
        <div class="w box-sizing bte0e0e0 ble0e0e0 bre0e0e0 tc mt20">
            <div class="h60 lh60 bcf9f9f9 f14 c717583 clearfix bbe0e0e0 box-sizing">
                <div class="fl w300 bre0e0e0 box-sizing">承兑行</div>
                <div class="fl w420 bre0e0e0 box-sizing">来票公司</div>
                <div class="fl w140 bre0e0e0 box-sizing">添加时间</div>
                <div class="fl w130 bre0e0e0 box-sizing">到期日</div>
                <div class="fl w166">票面金额（元）</div>
            </div>
            <!-- 列表 -->
            <ul id="dairuzhang"></ul>

            <!-- 总计 -->
            <div class="bbe0e0e0 h60 lh60 clearfix none" id="zongji">
                <div class="fl w300 bre0e0e0 box-sizing">总计</div>
                <div class="fr w170 ce84c3d allmoney"></div>
            </div>

            <!-- 总计 -->
            <div class="bbe0e0e0 h70 lh70 clearfix bcf9f9f9 none" id="shoupiao">
                <div class="fl pl20 shoupiao"></div>
                <div class="fr w165 h44 lh45 bad43c33 cd43c33 br5 tc f18 mt13 mr20">去收票</div>
            </div>
            
            <!-- 总计 -->
            <div class="bbe0e0e0 h70 lh70 clearfix bcf9f9f9 none" id="chupiao">
                <div class="fl pl20 chupiao"></div>
                <div class="fr w165 h44 lh45 bad43c33 cd43c33 br5 tc f18 mt13 mr20">去出票</div>
            </div>

        </div>
        <!-- 换一批 -->
        <div class="f18 c3366cc tl pl20 lh50 cp" onclick="moreItem();">换一批>></div>
    </div>
    <!-- 到期日期提醒 end -->

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

        <!--弹窗内容-->
        <div class="w box-sizing pl20 pr20 oya" style="max-height: 500px;">
            <div class="w" style="display: none" id="moreWindow">
                <!-- 列表 -->
                <div class="w f14 box-sizing bte0e0e0 ble0e0e0 bre0e0e0 tc mt20">
                    <div class="h40 lh40 bcf9f9f9 f14 c717583 clearfix bbe0e0e0 box-sizing">
                        <div class="fl w180 bre0e0e0 box-sizing">承兑行</div>
                        <div class="fl w120 bre0e0e0 box-sizing">到期日</div>
                        <div class="fl w120 bre0e0e0 box-sizing">票面金额（元）</div>
                        <div class="fl w100">操作</div>
                    </div>
                    <!-- 列表 -->
                    <ul id="dairuzhang1" class="h200">
                        
                    </ul>

                    <div class="w h50 bte0e0e0 bbe0e0e0 oh pager none">
        				<div class="clearfix" id="pager"></div>
					</div>

                    <div class="w h40 bcf9f9f9 box-sizing pt10 none selectNum">
                        <div class="c3366cc cp unl lh20 cp" onclick="choosedItem();">已选中<span class="cd43c33 num">0</span>张票据</div>
                    </div>
                </div>
                <div class="w h45 lh45 bcf2f2f2 bae0e0e0 topHeight">
                    <div class="fl f15">合计：<span class="cd43c33" id="heji">0</span>元</div>
                    <button class="fr w180 h45 bad43c33 bcd43c33 cwhite cp" onclick="save('2');">保存方案</button>
                </div>
            </div>
        </div>
        [@main.popup/]
    </div>
</div>

<!-- 已选择票 -->
<div class="w h pf bc05 zi12 top" style="display: none" id="choosedWindow1">

    <div class=" w540 bcwhite bae0e0e0 br3 box-sizing pb20 zi13 pa" style="display: none; left: calc(50% - 270px) ;" id="choosedWindow2">
        <div class="w tc clearfix h40 lh40 bcf9f9f9">
            <div class="fl w450 ml20">已选中<span class="cd43c33 num">0</span>张票据</div>
            <div class="fr cd43c33 mr20" onclick="empty();">清空</div>
        </div>

        <!-- 列表 -->
        <div class="w f14 box-sizing bte0e0e0 tc">
            <div class="h40 lh40 bcf9f9f9 f14 c717583 clearfix bbe0e0e0 box-sizing">
                <div class="fl w180 bre0e0e0 box-sizing">承兑行</div>
                <div class="fl w120 bre0e0e0 box-sizing">到期日</div>
                <div class="fl w120 bre0e0e0 box-sizing">票面金额（元）</div>
                <div class="fl w110">操作</div>
            </div>
            <!-- 列表 -->
            <ul id="dairuzhang2" class="w h200 oya">
                
            </ul>
        </div>
    </div>

</div>

<input type="hidden" id="draftRecordIds" />
<input type="hidden" class="heji" value="0">
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="DRAFTCALCULATE">
{{#each draftCalculateEachs}}
	<li class="pr bbe0e0e0 box-sizing">
		<div class="pa top left z12 w44 h44">
   			<img src="https://img.utiexian.com/website/180606/shangdian.png" class="{{toDraftType draft_type 1}}" width="44" height="44" alt="商票电票">
			<img src="https://img.utiexian.com/website/180606/shangzhi.png" class="{{toDraftType draft_type 2}}" width="44" height="44" alt="商票纸票">
 			<img src="https://img.utiexian.com/website/180606/yindian.png" class="{{toDraftType draft_type 3}}" width="44" height="44" alt="银票电票">
    		<img src="https://img.utiexian.com/website/180606/yinzhi.png" class="{{toDraftType draft_type 4}}" width="44" height="44" alt="银票纸票">
		</div>
		<div class="clearfix f15 h86 lh86">
			<div class="fl w300 bre0e0e0 box-sizing pl35 pr10 pt20 pb15 h">
				<span class="lh24 text-line2">{{bank}}</span>
			</div>
			<div class="fl w420 bre0e0e0 box-sizing">{{toDraftFrom draft_from}}</div>
			<div class="fl w140 bre0e0e0 box-sizing">{{formatDate create_time}}</div>
			<div class="fl w130 bre0e0e0 box-sizing pl35 pr10 pt20 pb15 h">
				<span class="lh24 text-line2">{{formatDate expiry_date}}</span>
			</div>
			<div class="fl w166">{{allmoney}}</div>
		</div>
	</li>
{{/each}}
</script>
<script type="text/x-handlebars-template" id="SELECTDRAFTCULATE">
{{#each list}}
	<li class="pr bbe0e0e0 box-sizing">
		<div class="pa top left z12 w30 h30">
   			<img src="https://img.utiexian.com/website/180606/shangdian.png" class="{{toDraftType draftType 1}}" width="30" height="30" alt="商票电票">
			<img src="https://img.utiexian.com/website/180606/shangzhi.png" class="{{toDraftType draftType 2}}" width="30" height="30" alt="商票纸票">
 			<img src="https://img.utiexian.com/website/180606/yindian.png" class="{{toDraftType draftType 3}}" width="30" height="30" alt="银票电票">
    		<img src="https://img.utiexian.com/website/180606/yinzhi.png" class="{{toDraftType draftType 4}}" width="30" height="30" alt="银票纸票">
		</div>
		<div class="clearfix f15 h40 lh40">
			<div class="fl w180 bre0e0e0 box-sizing pl20 oh toe wsn" id="bank{{id}}">{{bank}}</div>
			<div class="fl w120 bre0e0e0 box-sizing" id="expiryDate{{id}}">{{formatDate expiryDate}}</div>
			<div class="fl w120 bre0e0e0 box-sizing" id="allmoney{{id}}" data-value="{{allmoney}}">{{toAllmoney allmoney}}</div>
			<div class="fl w100 c3366cc cp add" id="add{{id}}" onclick="add({{id}})">增加</div>
			<div class="fl w100 c3366cc cp none add_" id="add_{{id}}">--</div>
			<input type="hidden" id="draftType{{id}}" value="{{draftType}}">
		</div>
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
*金额格式化
*/
Handlebars.registerHelper('toAllmoney', function(allmoney, options) {
    return fmoney(allmoney,2);
});

/**
*来源
*/
Handlebars.registerHelper('toDraftFrom', function(draftFrom, options) {
    if(draftFrom!=null&&draftFrom!=""){
    	return draftFrom;
    }else{
    	return "--";
    }
});

var memberId = '${member.id}';
$(document).ready(function() {
	loadDate();
});

/* 关闭弹窗 */
$("#closeBtn , .closeBtn").on("click",function(){
    $("#windowTitle").html("");
    $("#maskWindow").hide();
    $("#setWindow").hide();
    $("#editWindow").hide();
    $("#addWindow").hide();
    $("#moreWindow").hide();
    $("#addWindow").hide();
    $("#addAllWindow").hide();
    empty();
});

/* 换一批 */
function moreItem(){
	save(1);
}

/* 已选择的票据 */
function choosedItem(){
    $("#choosedWindow2").css("bottom", 
    document.body.clientHeight-document.getElementById("moreWindow").offsetTop-$("#moreWindow").height()+50);
    $("#choosedWindow2").show("slow");
    $("#choosedWindow1").show();
}

/* 关闭已选票据弹窗 */
$("#choosedWindow1").bind("click",function(e){
    if ($("#choosedWindow2").css("display","block")){
        //id为choosedWindow2的是弹窗内容，id为open的是打开弹窗的遮罩层
        if($(e.target).closest("#choosedWindow2").length == 0){
            //点击id为choosedWindow2之外，则触发
            $("#choosedWindow1").hide();
            $("#choosedWindow2").hide("slow");

        }
    }
});

/**
 *  加载票据
 */
function loadDate(){
	var params = {memberId:memberId};
	var url = '${bootAppPath}/draftcalculate/get';
	
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			var draftCalculate = data.data.data.draftCalculate;
			if(draftCalculate != null){
				var source = $("#DRAFTCALCULATE").html();
            	var template = Handlebars.compile(source);
				var html = template(data.data.data);
            	$("#dairuzhang").html(html);
				
				$("#allmoney").val(draftCalculate.allmoney);
				
				var allmoney = 0;
				for(var i=0;i<data.data.data.draftCalculateEachs.length;i++){
					allmoney = parseFloat(allmoney) + parseFloat(data.data.data.draftCalculateEachs[i].allmoney)
				}
					
				$(".allmoney").html(fmoney(allmoney,2));
				
				$("#chupiao").addClass("none");
				$("#shoupiao").addClass("none");
				if(parseFloat(draftCalculate.allmoney)-parseFloat(allmoney)<0){
					$("#chupiao").removeClass("none");
					$(".chupiao").html("票据金额超出货款金额，您可以选择将以上票据的其中一张贴现（即出票、卖票），将收到的票款补足剩余货款。");
				}else if(parseFloat(draftCalculate.allmoney)-parseFloat(allmoney)>0){
					var money = (parseFloat(draftCalculate.allmoney)-parseFloat(allmoney)).toFixed(2);
					$("#shoupiao").removeClass("none");
					$(".shoupiao").html("票据金额不足以支付货款，您可以选择支付"+money+"元现金，或者收一张票面"+money+"元的票据支付");
				}
				
				$("#zongji").removeClass("none");
			}
		}else{
			alert(data.data.msg);
		}
	};
};

/**
 * 加载票据（已持有）
 */
function loadDateHolad(){
	var data = {memberId:memberId,recordType:"HOLD"};
	$("#pager").sjAjaxPager({
        url: "${bootAppPath}/draftrecord/page",
        pageSize:10,
        myTotalType:"BOOT",
        searchParam: data,
        beforeSend: function () {
        	
        },success: function (data) {
       		var source = $("#SELECTDRAFTCULATE").html();
            var template = Handlebars.compile(source);
            var html = template(data.data.data);
            $("#dairuzhang1").html(html);
            if(data.data.data.list != null && data.data.data.list.length>0){
            	$(".pager").removeClass("none")
            	$(".selectNum").removeClass("none");
            	
            	$("#windowTitle").html("手动选择");
                $("#maskWindow").show();
                $("#moreWindow").show();
            }
            
        },complete: function(){
        }
    });
};

/**
 * 选择票据（选中）
 */
function add(id){
	var draftRecordIds = $("#draftRecordIds").val();
	if(draftRecordIds != null && draftRecordIds != ""){
		draftRecordIds = draftRecordIds + "," +id;
		$("#draftRecordIds").val(draftRecordIds);
	}else{
		$("#draftRecordIds").val(id);
	}
	var array = draftRecordIds.split(",");
	
	var draftType = $("#draftType"+id).val();

	var html = '<li class="pr bbe0e0e0 box-sizing" id="piaoju'+id+'"><div class="pa top left z12 w30 h30">';
	if(draftType == "YZ"){
		html += '<img src="https://img.utiexian.com/website/180606/yinzhi.png" width="30" height="30" alt="商票电票" title="商票电票">';
	}else if(draftType == "YD"){
		html += '<img src="https://img.utiexian.com/website/180606/yindian.png" width="30" height="30" alt="商票电票" title="商票电票">';
	}else if(draftType == "SZ"){
		html += '<img src="https://img.utiexian.com/website/180606/shangzhi.png" width="30" height="30" alt="商票电票" title="商票电票">';
	}else if(draftType == "SD"){
		html += '<img src="https://img.utiexian.com/website/180606/shangdian.png" width="30" height="30" alt="商票电票" title="商票电票">';
	}
	html += '</div><div class="clearfix f15 h40 lh40"><div class="fl w180 bre0e0e0 box-sizing pl20 oh toe wsn">';
	
	var bank = $("#bank"+id).html();
	var expiryDate = $("#expiryDate"+id).html();
	var allmoney = $("#allmoney"+id).attr("data-value");
	html += bank+'</div><div class="fl w120 bre0e0e0 box-sizing">'+expiryDate+'</div><div class="fl w120 bre0e0e0 box-sizing">';
	html += fmoney(allmoney,2)+'</div><div class="fl w100 c3366cc cp" onclick="del('+id+');">删除</div></div></li>';
	
	$(".num").html(array.length);
	
	$("#add"+id).addClass("none");
	$("#add_"+id).removeClass("none");
	
	var heji = $(".heji").val();
	heji = parseFloat(heji) + parseFloat(allmoney);
	$("#heji").html(fmoney(heji,2));
	$(".heji").val(heji);
	
	$("#dairuzhang2").append(html);
};

/**
 * 删除选中的票据
 */
 var isBoolean = true;
function del(id){
	if(!isBoolean)return;
	isBoolean = false;
	$("#add"+id).removeClass("none");
	$("#add_"+id).addClass("none");
	
	var num = $(".num").html();
	num = parseInt(num)-1;
	$(".num").html(num);
	
	var allmoney = $("#allmoney"+id).html();
	var heji = $(".heji").val();
	heji = parseFloat(heji) - parseFloat(allmoney);
	$("#heji").html((heji).toFixed(2));
	$(".heji").val(heji);
	
	var parent=document.getElementById("dairuzhang2");
	var child=document.getElementById("piaoju"+id);
	parent.removeChild(child);
	
	var draftRecordIds = $("#draftRecordIds").val();
	var array = draftRecordIds.split(",");
	
	var draftRecordId = "";
	for (var i = 0; i < array.length; i++) {
		if(array[i] != id){
			if(draftRecordId != null && draftRecordId != ""){
				draftRecordId = draftRecordId+"," + array[i];
			}else{
				draftRecordId = array[i];
			}
		}
	}
	
	$("#draftRecordIds").val(draftRecordId);
	isBoolean = true;
};

/**
 *  清空选择
 */
function empty(){
	$("#draftRecordIds").val("");
	$(".num").html(0);
	$("#heji").html(0);
	$(".heji").val(0);
	
	$("#dairuzhang2").html("");
	$(".add").removeClass("none");
	$(".add_").addClass("none");
};

/**
 * 保存数据
 */
function save(num){
	var allmoney;
	if(num == 1){
		allmoney = $("#allmoney").val()
	}else{
		allmoney = $(".heji").val()
	}
	var params = {allmoney:allmoney,memberId:memberId};
	var draftRecordIds = $("#draftRecordIds").val();
	if(draftRecordIds.length > 0){
		params.draftRecordIds = draftRecordIds;
	}
	
	var url = '${bootAppPath}/draftcalculate/save';
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
		    loadDate();
		    
		    $("#windowTitle").html("");
		    $("#maskWindow").hide();
		    $("#setWindow").hide();
		    $("#editWindow").hide();
		    $("#addWindow").hide();
		    $("#moreWindow").hide();
		    empty();
		}else{
			alert(data.data.msg);
		}
	}
};

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

/**
 * 收票
 */
$("#shoupiao").click(function(){
	checkAccount(1,'/hall/receiveOrderall1');
});

/**
 * 出票
 */
$("#chupiao").click(function(){
	checkAccount(0,'/discountrecord/discount_yp1');
});

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

//票据赎回时间
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
laydate(add_Out_Date);
laydate(add_In_Date);
laydate(noticedate);
</script>
[@main.footer/]
[/@main.body]