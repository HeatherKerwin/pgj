[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-接单大厅']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
[@main.header currentmenu='1' topindex='4'/]

<div class="w1200 bc">
    <!--查询-->
    <div class="w1198 h90 mt20 bae0e0e0 bcwhite f14">
        <div class="fl mt30 ml20">
            <div class="fl lh30 mr20">票据状态</div>
            <select id="type" class="fl w148 h29 ti20 select148">
                <option class="ti20" value="">全部</option>
                <option class="ti20" value="1">已贴现</option>
                <option class="ti20" value="0">未贴现</option>
            </select>
        </div>
        <div class="fl mt30 ml80">
            <div class="fl lh30 mr20">票据属性</div>
            <select id="shuxing" class="fl w148 h29 ti20 select148">
                <option class="ti20" value="">全部</option>
                <option class="ti20" value="1">纸票</option>
                <option class="ti20" value="2">电票</option>
            </select>
        </div>
        <div class="fl mt30 ml80">
            <div class="fl lh30 mr20">是否设置票据提醒</div>
            <select id="notice" class="fl w148 h29 ti20 select148">
                <option class="ti20" value="">全部</option>
                <option class="ti20" value="1">是</option>
                <option class="ti20" value="0">否</option>
            </select>
        </div>
        <input type="button" value="查询" class="fl w110 h34 f14 lh34 tc b0 bce84c3d cwhite br3 mt30 ml80 cp" onclick="serach()">
    </div>
<!--功能按钮-->
    <div class="mt20">
        <div class="fl w102 h58 bcwhite bae0e0e0 f12 tc c3366cc mr30 pb10">
            <a href="${basePath}/zhangbu/add" class="mt8">
            	<img src="${image_url}/website/manage/add.png" width="20" height="20" class="mt12 bc">
            </a>
            <div class="mt8">新增票据</div>
        </div>

        <div class="fl w102 h58 bcwhite bae0e0e0 f12 tc c3366cc mr30 pb10">
        	<a href="${basePath}/zhangbu/report" class="mt8">
            	<img src="${image_url}/website/manage/report.png" width="20" height="20" class="mt12 bc">
            </a>
            <div class="mt8">查看报表</div>
        </div>
        <div class="fl w102 h58 bcwhite bae0e0e0 f12 tc c3366cc pb10">
            <a href="#" onclick="downExcle()"><img src="${image_url}/website/manage/excel.png" width="20" height="20" class="mt12 bc"></a>
            <div class="mt8">导出excel</div>
        </div>
        <div class="cb"></div>
    </div>
<!--列表-->
	<div class="w1200 mt20 ble0e0e0 bre0e0e0 bte0e0e0 mb50">
		<div class="tc bbe0e0e0 c717583 f14">
            <div class="fl w97 h52 pt20 bre0e0e0">票据状态</div>
            <div class="fl w97 w97 h52 pt20 bre0e0e0">票据属性</div>
            <div class="fl w83 w97 h52 pt20 bre0e0e0">承兑行</div>
            <div class="fl w123 w97 h52 pt20 bre0e0e0">总金额</div>
            <div class="fl w123 w97 h52 pt20 bre0e0e0">到期日期</div>
            <div class="fl w138 w97 h52 pt20 bre0e0e0">备注</div>
            <div class="fl w97 w97 h52 pt20  bre0e0e0 lh18">是否设置<br>票据提醒</div>
            <div class="fl w123 w97 h52 pt20 bre0e0e0">添加时间</div>
            <div class="fl w297 w97 h52 pt20">操作</div>
            <div class="cb"></div>
        </div>
    	<div id="content">
    	</div>
    	<div id="pager" class="mb20"></div>
   	</div>
   	<div class="cb"></div>
</div>   	
[@main.right /]
<!--弹窗提醒-->
<div class="w h pf bc05 zi10 top none" id="piaojutixing">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <div class="pr l80 t57">
                <div class="">
                    <input type="checkbox" name="checkbox1" id="checkbox1" class="checkbox1 w14 h14" checked="checked">
                    <label for="checkbox1" class="f14 ce84c3d">设置到期提醒</label>
                </div>
                <div class="mt20">
                    <input class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 mt8 ml20 ti8 inline bcwhite" id="tixing" name="tixing" readonly="readonly"/>
            		<label class="fl w30 h27 rili mt8" for="tixing"></label>
                </div>
                <div class="cb"></div>
                <div class="mt20">
                    <div class="fl fb">备注：</div>
                    <textarea class="fl w540 h120 bae0e0e0 pt10 ti8 lh20" id="accountdesc" placeholder="备注最多可输入140个字......"></textarea>
                </div>
                <input type="hidden" value="" id="acc">
                <input type="hidden" value="" id="allprice">
                <input type="hidden" value="" id="daoqidate">
                <input type="button" class="w268 h44 lh44 f18 b0 br5 cwhite bcfc4d42 mt40 ml157" value="保存" onclick="save()">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="ZHANGBU">
{{#each results}}
        <ul class="w bcwhite">
            <li class="tc bbe0e0e0 pr">
                <div class="fl w97 h75 pt20 bre0e0e0">
                    <div class="f16 {{tiexian1 istiexian}}">{{tiexian2 istiexian}}</div>
                    <div class="f12 mt10">（{{tiexian3 istiexian daoqidate tiexianType}}）</div>
                </div>
                <div class="fl w97 w97 h75 pt20 bre0e0e0">{{piaojushuxing}}</div>
                <div class="fl w83 w97 h75 pt20 bre0e0e0">{{toType1 type1}}</div>
                <div class="fl w123 w97 h75 pt20 bre0e0e0">{{allprice}}万元</div>
                <div class="fl w123 w97 h75 pt20 bre0e0e0">{{formatDate daoqidate}}</div>
                <div class="fl w100 pl20 pr18 pb15 h60 pt20 bre0e0e0 tl oh toe">{{accountdesc}}</div>
				<div class="fl w97 w97 h75 pt20  bre0e0e0">
                    <div class="{{toNotice noticeaddtime}}">已设置</div>
                    <input class="w56 h30 lh30 b0 br3 bc778ffd cwhite mt10 tc cp" onclick="tan({{id}})" value="{{toNotice1 noticeaddtime}}">
                </div>
                <div class="fl w123 w97 h75 pt20 bre0e0e0">{{formatDate createTime}}</div>
                <div class="fl w297 w97 h75 pt20 f16">
                    <div class="h30" style="margin: 0 auto;width: auto">
                        <input type="button" class="{{tiexian4 istiexian tiexianType}} fl w56 h30 lh30 tc cwhite bc778ffd br3 b0 ml20 cp" value="修改" onclick="updatepiaoju({{id}})">
                        <input type="button" class="fl w56 h30 lh30 tc cwhite bc778ffd br3 b0 ml25 cp" value="删除" onclick="del({{id}})">
                        <input type="button" class="{{tiexian5 istiexian}} fl w96 h30 lh30 tc cwhite bc778ffd br3 b0 ml25 cp" value="一键贴现" onclick="tiexian({{id}})">
                    </div>
                </div>
                <div class="cb"></div>
            </li>
        </ul>
{{/each}}
</script>
<script type="text/javascript">
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
 * 是否贴现
 */
Handlebars.registerHelper('tiexian1', function(num, options) {
    if(num!=null){
    	if(num==0){
    		return "cd43c33";
    	}else{
    		return "";
    	}
    }
});

/* 
 * 是否贴现
 */
Handlebars.registerHelper('tiexian2', function(num, options) {
  if(num!=null){
  	if(num==0){
  		return "未贴现";
  	}else{
  		return "已贴现";
  	}
  }
});

/* 
 * 是否贴现
 */
Handlebars.registerHelper('tiexian3', function(num,date,type, options) {
    if(num!=null){
    	if(num==0){
    		if(date!=null){
    			date = date.replace(/-/g, "/");
    	        var d = new Date(date);
    	        var daoqiDate = d.format('yyyy-MM-dd hh:mm:ss');
    	        var currentdate = new Date().format('yyyy-MM-dd hh:mm:ss');
    	        if(daoqiDate>=currentdate){
    	        	return "未过期";
    	        }else{
    	        	return "已过期";
    	        }
    		}
    	}else if(num==1){
    		if(type!=null){
    			return type;
    		}
    	}
    }
});
/* 
 * 是否贴现
 */
Handlebars.registerHelper('tiexian4', function(num,type,options) {
    if(num!=null){
    	if(num==0){
    		return "";
    	}else if(num==1){
    		if(type=="手动"){
    			return "";
    		}else{
    			return "none";
    		}
    	}
    }
});
/* 
 * 是否贴现
 */
Handlebars.registerHelper('tiexian5', function(num, options) {
    if(num!=null){
    	if(num==0){
    		return "";
    	}else{
    		return "none";
    	}
    }
});
/* 
 * 承兑行
 */
Handlebars.registerHelper('toType1', function(num, options) {
   	if(num!=null){
       	return getBank(num);
    }
});
/* 
 * 是否设置票据提醒
 */
Handlebars.registerHelper('toNotice', function(num, options) {
   	if(num!=null){
       	return "";
    }else{
    	return "none";
    }
});
/* 
 * 是否设置票据提醒
 */
Handlebars.registerHelper('toNotice1', function(num, options) {
   	if(num!=null){
       	return "更改";
    }else{
    	return "设置";
    }
});
/* 
 * 弹框
 */
function tan(id){
	$("#piaojutixing").removeClass("none");
	$.ajax({
		url:"${basePath}/zhangbu/toupdate",
		type:"POST",
		data:{"id":id},
		dataType:"json",
		success:function(data){
			if(data.response == 'success'){
				if(data.data!=null){
					if(data.noticerecord!=null){
						$("#tixing").val(data.noticetime);
					}else{
						$("#tixing").val("");
					}
					$("#acc").val(id);
					$("#allprice").val(data.data.allprice);
					$("#daoqidate").val(data.daoqidate);
				}
			}
		}
	})
}
$("#redClose").click(function(){
	$("#piaojutixing").addClass("none");
});
/*
 * 票据提醒
 */
$("input[name='checkbox1']").change(function() {
    if($(this).attr("checked") == "checked"){
    	$("#tixing").removeAttr("disabled");//日期启用
    }else{
    	$("#tixing").attr("disabled","disabled");//日期禁用
    	$("#tixing").val("");
    }
});
/*
 * 设置提醒时间
 */
function save(){
	var accId = $("#acc").val();
	var allprice = $("#allprice").val();
	var daoqidate = $("#daoqidate").val();
	var tixing = $("#tixing").val();
	var accountdesc = $("#accountdesc").val();
	if(tixing==null || $.trim(tixing)==""){
		alert("请输入提醒时间");
		return;
	}
	$.ajax({
		url:"${basePath}/zhangbu/updatenotice",
		type:"POST",
		data:{"id":accId,"allprice":allprice,"daoqidate":daoqidate,"tixing":tixing,"accountdesc":accountdesc},
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				$("#piaojutixing").addClass("none");
				alert("操作成功！")
				serach();
			}
		}
	});
}
/*
 * 分页查询
 */
var pageIndex = 1;//分页
function serach(){
	var type = $("#type").val();
	var shuxing = $("#shuxing").val();
	var notice = $("#notice").val();
	$('#pager').sjAjaxPager({
		url:"${basePath}/zhangbu/search",
        pageIndex:pageIndex,
        pageSize:15,
        searchParam: {
        	type: type,
            shuxing:shuxing,
            notice:notice
        },beforeSend: function () {
        },success: function (data) {
        	var source = $("#ZHANGBU").html();
            var template = Handlebars.compile(source);
            var html = template(data.data);
            $("#content").html(html);
        },complete: function(){
        },error:function(){
        	
        }
    });
}
$(function(){
	serach();
	wwwPath = "${basePath}";
	actionLog(wwwPath,"action13");//票据管理
});
/*
 * 删除
 */
function del(id){
	if(confirm("确定删除该条纪录吗?")){
		wwwPath = "${basePath}";
		actionLog(wwwPath,"action134");
		$.ajax({
			url:"${basePath}/zhangbu/delete",
			type:"POST",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data.response == "success"){
					alert(data.msg);
					serach();
				}else{
					alert(data.msg);
				}
			}
		});
	}
}
/*
 * 一键贴现
 */
function tiexian(id){
	$.ajax({
		url:"${basePath}/zhangbu/toupdate",
		type:"POST",
		data:{"id":id},
		dataType:"json",
		success:function(data){
			if(data.response == 'success'){
				if(data.data!=null){
					var map = new Map();
					var allprice = data.data.allprice;
					var shuxing = null;
					var acceptTime = data.data.acceptTime;
					var type1 = data.data.type1;
					var accountdesc= data.data.accountdesc;
					if(acceptTime == '0' || acceptTime=='1'){
						shuxing = "2";
					}else{
						shuxing = "1";
					}
					if(allprice==""){
						$("#allprice").validationEngine('showPrompt','* 请输入票据金额',null,null,true);
						setTimeout(function(){$("#allprice").validationEngine('hide');},2000);
						$("#allprice").focus();
						return;
					}
					var url ;
					if(shuxing == 1){
						url = "discountrecord/discount_yp_zhi";
					}else{
						url = "discountrecord/discount_yp1";
					}
					map.put("_PAGE", url);//必传
					map.put("shuxing", shuxing);
					map.put("acceptTime",acceptTime);
					map.put("type1",type1);
					map.put("accountdesc",accountdesc);
					map.put("allmoney",allprice);
					_OPENPAGE_FORM(map);
				}
			}
		}
	});
}
/*
 * 导出excle 
 */
function downExcle(){
	var type = $("#type").val();
	var shuxing = $("#shuxing").val();
	var notice = $("#notice").val();
	window.location.href="${basePath}/zhangbu/excle?type="+type+"&shuxing="+shuxing+"&notice="+notice;
}
/*
 * 修改票据 
 */
function updatepiaoju(id){
	window.location.href="${basePath}/zhangbu/get?id="+id;
}
//日历
!function(){
    laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
}();
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
        $("#tixing").val(datas);
    }
};
laydate(tixing);
laydate({
    elem: '#first',
    format: 'YYYY-MM-DD',
    festival: true, //显示节日
    choose: function(datas){ //选择日期完毕的回调
    }
});
</script>
<!--foot-->
[@main.footer/]
[/@main.body]