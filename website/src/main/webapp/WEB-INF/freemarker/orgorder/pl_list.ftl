[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-批量订单']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='1'/]

<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1'/]
    <!--右侧内容 -->
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 ti10 bcfaf7f7">批量订单</div>
        <!--银票订单-->
        <!--订单tab-->
        <ul class="h52 f16 c2d2d2d lh50 bae0e0e0 tc bcwhite orderTab">
            <li class="w166 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="orderTab" id="order1" class="none" checked="checked" value="0"><a href="#" class="cd43c33" onclick="loadData(0)"><label for="order1" class="dsb cp orderTab_css">全部订单</label></a>
            </li>
            <li class="w165 lh50 fl bre0e0e0 pr zi10">
                <input type="radio" name="orderTab" id="order2" class="none" value="1"><a href="#" class="c2d2d2d" onclick="loadData(1)"><label for="order2" class="dsb cp orderTab_css">交易中</label></a>
            	<div class="w20 h20 br30 bcfc4d42 cwhite lh20 f14 tc pa t13 r30 zi13 none" id="list"></div>
            </li>
            <li class="w166 lh50 fl bre0e0e0">
                <input type="radio" name="orderTab" id="order3" class="none" value="2"><a href="#" class="c2d2d2d" onclick="loadData(2)"><label for="order3" class="dsb cp orderTab_css">已完成</label></a>
            </li>
            <li class="w166 lh50 fl bre0e0e0">
                <input type="radio" name="orderTab" id="order4" class="none" value="3"><a href="#" class="c2d2d2d" onclick="loadData(3)"><label for="order4" class="dsb cp orderTab_css">无效订单</label></a>
            </li>
        </ul>
        <!--条件-->
        <div class="pl20 mt30 ml20 mr20 f14 bae0e0e0">
            <div class="mt20">
                <div class="fl w65 fb lh30">时间</div>
                <div class="fl">
                    <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline" id="start" readonly="readonly"/>
                    <label class="fl w30 h27 rili" for="start"></label>
                    <img src="${image_url}/website/manage/xian.png" width="34" height="1" class="fl ml20 mr20 mt13" />
                    <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline" id="end" readonly="readonly"/>
                    <label class="fl w30 h27 rili" for="end"></label>
                </div>
                <div class="cb"></div>
            </div>
            <div class="mt20 mb20">
                <div class="fl w65 fb lh30">票据金额</div>
                <div class="fl lh30">
                    <div class="fl">从</div>
                    <input type="text" class="fl w148 h29 bae0e0e0 br5 ml6 ti10" id="minMoney">
                    <div class="fl ml6">至</div>
                    <input type="text" class="fl w148 h29 bae0e0e0 br5 ml6 ti10" id="maxMoney">
                    <div class="fl ml6">万元</div>
                </div>
                <input type="button" class="fl w110 h34 lh34 bce84c3d b0 br5 cwhite ml40 f16 cp" value="搜索" onclick="search()">
                <div class="cb"></div>
            </div>
        </div>
        <!--从这里开始复制列表-->
        <div class="pl20 pr20 mb50 mt30" id="content">
        
        </div>
        <!--到这里结束复制列表-->
        <div class="bc">
        	<div id="pager"></div>
        </div>
    </div>
    <div class="cb"></div>
</div>

<!--理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="reason">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <div class="h70"></div>
            <div class="ml25 f16 lh40">
                <div class="fl w138 c7d7d7d">请选择取消理由：</div>
                <select class="fl w320 h40 select320 ti8" id="cancel">
                    <option value="1">额度不够</option>
                    <option value="2">不在业务范围内</option>
                    <option value="3">银行大额支付系统已关闭</option>
                    <option value="4">其他</option>
                </select>
            </div>
            <div class="cb"></div>
            <!-- 其他-->
            <textarea placeholder="请您输入不少于十五字的理由！" type="text" class="w500 h210 bae0e0e0 mt30 ml160 ti8 pt15 none" id="reason1_div"></textarea>
            <div class="cb"></div>
            <input type="button" id="cancelButton" value="确认" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b60 l_50 ml-130 cp" onclick="cancelDtbo()">
        </div>
    </div>
</div>
[@main.right /]

<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="DISTRIBUTEORDERPL">
{{#each results}}
<div class="mt30 fb f18 lh20 bb3_e0e0e0 cd43c33">
    {{toEnum state}}
</div>
<div class="mt30 bte0e0e0 bre0e0e0 ble0e0e0 mb20">
<div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
    <div class="fl w220">批量订单号</div>
    <div class="fl w210">票据总金额</div>
    <div class="fl w100">票据总数量</div>
    <div class="fl w125">票据到期天数</div>
    <div class="fl w300">票据特征</div>
</div>
<div class="cb"></div>
<div class="h265 bcwhite bbe0e0e0 pt25 pb25">
    <div class="fl w655 Rright">
        <div class="bbe0e0e0 tc f16 h90 pb25">
            <div class="fl w210 pl10 Rright h90 lh20 tl">
                <a href="${basePath}/distributeorderpl/detail?no={{no}}"><div class="c3366cc f16 ti8">{{no}}</div></a>
            </div>
            <div class="fl w210 Rright h90 lh30">
                <div class=""><span class="cd43c33">{{allmoney}}</span>万元</div>
                <div class="">票面最小金额为<span class="cd43c33">{{min_money}}</span>万元</div>
                <div class="">票面最大金额为<span class="cd43c33">{{max_money}}</span>万元</div>
            </div>
            <div class="fl w100 Rright h90 lh30"><span class="cd43c33">{{amount}}</span>张</div>
            <div class="fl w125 h90 lh30">
                <div class="">最短<span class="cd43c33">{{min_expiry_day}}</span>天</div>
                <div class="">最长<span class="cd43c33">{{max_expiry_day}}</span>天</div>
            </div>
        </div>
        <div class="cb"></div>
        <div class="tc f16 pt25 h90">
            <div class="fl w210 Rright h110 tl ml10">
                <div class="ti8">包含承兑行：</div>
                <ul class="ti8 mt16 lh35">
                    <li class="fl mr30">{{toTypePl type2}}</li>
                </ul>
            </div>
            <div class="fl pl20 h90">
                <div class="fl w50 h90">备注：</div>
                <div class="fl w345 h90 tl lh20">{{remarks}}
                </div>
            </div>
        </div>
    </div>
    <div class="fl w300">
        <div class="tc lh35">
            <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">{{toType1 type1}}</span>
            <span class="{{toTicket flaw_ticket}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">瑕疵票</span>
            <span class="{{toDoor need_todoor}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">需要上门</span>
        </div>
    </div>
</div>
<div class="cb"></div>
<div class="h65 lh65 pl10 ti8 bbe0e0e0">
    订单有效期至：<span>{{formatDate endtime}}</span>
</div>
<div class="cb"></div>
<div class="bbe0e0e0 tc pt25 pb25">
    <div class="fl f14 ml10 c717583">
        持票企业：<span class="f16 c2d2d2d">{{company}}</span>
    </div>
    <div class="fl f14 ml100 c717583">
        联系人：<span class="f16 c2d2d2d">{{member_name}}</span>
    </div>
    <div class="fl f14 ml100 c717583 tl">
        <div class="ti8">联系方式：<span class="f16 c2d2d2d">{{toMobile state member_mobile}}</span></div>
        <div class="f12 mt10 c2d2d2d">（联系贴现机构时请说是在票据管家平台上看到的）</div>
    </div>
    <div class="cb"></div>
</div>
<div class="{{toShow state}} w h70 bbe0e0e0 bcf9f9f9 tc f18 pt20">
    <a href="#" class="{{toShow state}} fr c2d2d2d ba2_e0e0e0 br3 dsb w166 h45 lh45 mr40" onclick="tan('{{no}}')">取消订单</a>
</div>
</div>
{{/each}}
</script>
<script type="text/javascript">
/*
 * 页面初始化
 */
$(document).ready(function() {
    loadData(0);
    initBadge();
});

/*
 * 订单状态切换
 */
$(".orderTab_css").click(function(){
	$(".orderTab li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
	$(".orderTab li").removeClass("bbd43c33");
	$(".orderTab li").children("label").removeClass("f20");
	$(this).parent().prev().attr("checked",true);
    $(this).parents("li").addClass("bbd43c33");
    $(this).parents("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
});

/* 
 * 格式化日期 
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

/* 
 * 订单状态
 */
Handlebars.registerHelper('toEnum', function(num, options) {
    if (num == 0 || num == -1 || num == -2) {
        return "无效订单";
    } else if (num == 1) {
        //return "待交易";暂无待交易
    } else if (num == 2) {
        return "交易中";
    } else if (num == 3) {
        return "已完成";
    }
});

/* 
 * 格式化票据类型
 */
Handlebars.registerHelper('toType1', function(num, options) {
    if(num!=null){
    	return getBA(num);
    }
});

/* 
 * 格式化瑕疵票
 */
Handlebars.registerHelper('toTicket', function(num, options) {
    if(num!=null){
    	if(num==0){
    		return "none";
    	}
    }
});

/* 
 * 格式化上门需求
 */
Handlebars.registerHelper('toDoor', function(num, options) {
    if(num!=null){
    	if(num==0){
    		return "none";
    	}
    }
});

/* 
 * 批量订单承兑行
 */
Handlebars.registerHelper('toTypePl', function(num, options) {
	return getType2(num,false);
});

/* 
 * 显示按钮 
 */
Handlebars.registerHelper('toShow', function(state,options) {
	if(state != 2){
        return "none";
    }
});

/* 
 * 格式化手机号
 */
Handlebars.registerHelper('toMobile', function(state,num, options) {
    if(num!=null){
    	if(state==0 || state==-1 || state==-2){
    		return hideMobile(num);
        }else if(state==1){
        	//return "待交易";暂无待交易
        }else if(state==2){
        	return num;
        }else if(state==3){
        	return hideMobile(num);
        }
    }
});

/*
 * 条件搜索
 */
function search(){
	var num = 0;
	$("input[name='orderTab']").each(function(){
		if($(this).attr("checked") == "checked"){
			num = $(this).val();
		}
	});
	loadData(num);
}

/*
 * 分页获取列表
 */
var pageIndex = 1;//分页
function loadData(num){
	var pageIndex = 1;//分页
	var state =  null;
    var start = $("#start").val();
    var end = $("#end").val();
    var minMoney = $("#minMoney").val();
    var maxMoney = $("#maxMoney").val();
    if (num == 0) {
        state = null;//全部
    } else if (num == 1) {
        state = "2";//交易中
    } else if (num == 2) {
        state = "3";//已完成
    } else if (num == 3) {
     	state = "0,-1,-2"
    }
	$('#pager').sjAjaxPager({
        url: "${basePath}/distributeorderpl/list/search",
        pageIndex:1,
        pageSize:10,
        searchParam: {
        	start:start,
        	end:end,
        	minMoney:minMoney,
        	maxMoney:maxMoney,
        	states:state,
        },beforeSend: function () {
        },success: function (data) {
        	var source = $("#DISTRIBUTEORDERPL").html();
            var template = Handlebars.compile(source);
            var html = template(data.data);
            $("#content").html(html);
        },complete: function(){
        },error:function(){
        	
        }
    });
}

/**
 * 初始化加载角标 
 */
function initBadge(){
	$.ajax({
		url:"${basePath}/distributeorderpl/badge",
		type:"POST",
		data:"",
		dataType:"json",
		success:function(data){
			if(data.response=='success'){
				if(data.list>0){
					$("#list").text(data.list);
					$("#list").removeClass("none");
				}else{
					$("#list").addClass("none");
				}
			}
		}
	});
}

var jgno = null;//当前订单号
var currentState = null;//当前订单状态

/*
 * 取消订单弹框
 */
function tan(no){
	$("#reason").removeClass("none");
	initData(no);
}
function initData(no){
	$.ajax({
		url:"${basePath}/distributeorderpl/get",
		data:{"no":no},
		dataType:"json",
		success:function(data){
			currentState = data.data.state;
		}
	});
	jgno = no;
}

/*
 * 取消订单
 */
function cancelDtbo(){
	$("#cancelButton").attr("disabled","disabled");
    var reason = $("#cancel").find("option").not(function(){ return !this.selected }).text();//拒绝原因（文本）
    var lostCause = null;//其他理由
    var cancel = $("#cancel").val();
 	if(cancel==4){//其他
        lostCause = $("#reason1_div").val();
        reason = lostCause;
        if (lostCause.length < 15) {
            alert("请你输入不少于15字的理由");
            $("#cancelButton").removeAttr("disabled");
            return;
        }
    }else{
        lostCause = null;
    }
    var data = "no="+jgno+"&cancel="+cancel+"&reason="+reason+"&currentState="+currentState;
    $.ajax({
    	url:"${basePath}/distributeorderpl/cancel",
    	type:"POST",
    	data:data,
    	dataType:"json",
    	success:function(data){
    		if (data.response == 'success') {
    			alert("取消成功!");
    			$("#reason").addClass("none");
    			//取消订单后去哪个页面
    			loadData(0);
    			initBadge();
    			$("#cancelButton").removeAttr("disabled");
            }else{
                alert(data.msg);
                $("#cancelButton").removeAttr("disabled");
            }
    	},
    	error:function(data){
    		$("#cancelButton").removeAttr("disabled");
    	}
    });
}

/*
 * 拒绝订单理由显示隐藏
 */
$("#redClose").click(function(){
    $("#reason").addClass("none");
});

/*
 * 填写理由
 */
$("#cancel").change(function(){
    var value = $(this).val();
    if(value==4){
        $("#reason1_div").removeClass("none");
    }else{
        $("#reason1_div").addClass("none");
    }
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
    }
};
laydate(start);
laydate(end);
</script>
[@main.footer/]
[/@main.body]