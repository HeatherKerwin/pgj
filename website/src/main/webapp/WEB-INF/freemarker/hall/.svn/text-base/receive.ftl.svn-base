[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-接单大厅']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='1' topindex="3"/]

<!--接单-->
<div class="w1200 bc bcwhite bae0e0e0 mt30 mb20">
    <!-- 广告-->
    <img src="${image_url}/website/receive/receive.png" class="w h100">
    <div id="content">

    </div>
    <div id="content2" class="h350 none">
        <div class="w tc f36 mt_20">请报价后耐心等待票管家派单哦！</div>
    </div>
</div>
[@main.right /]
<!--银票理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="reason">
    <div class="w800 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose"></div>

        <div class="w792 h600 pr t4 l4 bcf5f5f5 zi12">
            <div class="h20"></div>
            <div class="ml25 f16 lh40">
                <div class="fl w184 c7d7d7d">请选择取消理由：</div>
                <select class="fl w320 h40 select320 ti8" id="cancel1">
                    <option value="1">额度不够</option>
                    <option value="2">票据有问题</option>
                    <option value="3">不在业务范围内</option>
                    <option value="4">银行大额支付系统已关闭</option>
                    <option value="5">其他</option>
                </select>
            </div>
            <div class="cb"></div>
            <!-- 其他-->
            <textarea placeholder="请您输入不少于十五字的理由！" class="w500 h210 bae0e0e0 mt20 ml209 ti8 pt15 none" id="reason1_div"></textarea>
            <div class="cb"></div>
            <!--票据有问题-->
            <div class="ml25 mt16 none" id="reason2_div">
                <div class="mt16 f16 lh40">
                    <div class="fl w184 c7d7d7d">请选择验票失败理由：</div>
                    <select class="fl w320 h40 select320 ti8" id="cancel2">
                        <option value="1">票面信息不完整</option>
                        <option value="2">票据不真实</option>
                        <option value="3">出票人印章与出票人不符合</option>
                        <option value="4">印章不清晰</option>
                        <option value="5">被背书人填写不正确</option>
                    </select>
                </div>
                <div class="cb"></div>
                <div class="c7d7d7d mt16">上传验票失败凭证<span class="ml20 cd43c33">(最多上传6张)</span></div>
                <div class="w750 h365 mt16 bae0e0e0 bcwhite">
                    <div id="addImg1" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg1','picpath1')"></div>
                        <img id="img1" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath1" style="display:none;"/>
                    </div>
                    <div id="addImg2" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg2','picpath2')"></div>
                        <img id="img2" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath2" style="display:none;"/>
                    </div>
                    <div id="addImg3" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg3','picpath3')"></div>
                        <img id="img3" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath3" style="display:none;"/>
                    </div>
                    <div id="addImg4" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg4','picpath4')"></div>
                        <img id="img4" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath4" style="display:none;"/>
                    </div>
                    <div id="addImg5" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg5','picpath5')"></div>
                        <img id="img5" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath5" style="display:none;"/>
                    </div>
                    <div id="addImg6" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg6','picpath6')"></div>
                        <img id="img6" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath6" style="display:none;"/>
                    </div>
                    <div id="images" class="fl mt20 ml25 pr">
                        <p><img width="200" height="144" src="${image_url}/website/receive/phone2.png" onclick="fileSelect();"></p>
                        <div class="wa">
		                	<input type="file" name="fileToUpload" id="fileToUpload" class="fl w200 h24">
		                    <input type="button"  class="fl w50 h24 ml15" value="上传" id="" onclick="fileSelected('img','picpath','fileToUpload');" >
		                    <input type="hidden" class="fl" name="picpath" id="picpath"  style="display:none;">
		                    <div class="cb"></div>
		                </div>
                    </div>
                    <div class="cb"></div>
                </div>
            </div>
            <input type="button" value="确认" id="cancelButton" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b20 l_50 ml-130 cp" onclick="cancelDtbo()">
        </div>
    </div>
</div>
<!--商票理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="reason1">
    <div class="w770 h584 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose1"></div>
        <!--理由表单-->
        <div class="w750 h558 pr t13 l10 bcf5f5f5 zi12">
            <div class="h45"></div>
            <div class="ml25 f16 lh40">
                <div class="fl w184 c7d7d7d">请选择取消理由：</div>
                <select class="fl w320 h40 select2 ti8" id="cancel">
                    <option value="1">额度不够</option>
                    <option value="2">价格不合适</option>
                    <option value="3">不在业务范围内</option>
                    <option value="4">银行大额支付系统已关闭</option>
                    <option value="5">其他</option>
                </select>
            </div>
            <div class="cb"></div>
            <!-- 其他-->
            <textarea placeholder="请您输入不少于十五字的理由！" class="w500 h210 bae0e0e0 mt20 ml209 ti8 pt15 none" id="reason_div"></textarea>
            <div class="cb"></div>
            <input type="button" value="确认" id="cancelButton1" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b30 l_50 ml-130 cp" onclick="cancelDtbo1()">
        </div>
    </div>
</div>
<!--批量理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="reason2">
    <div class="w770 h584 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose2"></div>
        <!--理由表单-->
        <div class="w750 h558 pr t13 l10 bcf5f5f5 zi12">
            <div class="h45"></div>
            <div class="ml25 f16 lh40">
                <div class="fl w184 c7d7d7d">请选择取消理由：</div>
                <select class="fl w320 h40 select2 ti8" id="cancel3">
                    <option value="1">额度不够</option>
                    <option value="2">价格不合适</option>
                    <option value="3">不在业务范围内</option>
                    <option value="4">银行大额支付系统已关闭</option>
                    <option value="5">其他</option>
                </select>
            </div>
            <div class="cb"></div>
            <!-- 其他-->
            <textarea placeholder="请您输入不少于十五字的理由！" class="w500 h210 bae0e0e0 mt20 ml209 ti8 pt15 none" id="reason_div1"></textarea>
            <div class="cb"></div>
            <input type="button" value="确认" id="cancelButton2" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b30 l_50 ml-130 cp" onclick="cancelDtbo2()">
        </div>
    </div>
</div>

<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<!-- 银票订单-->
<script type="text/x-handlebars-template" id="DISC">
{{#each data}}
    <div class="mt30 ml30 mr30 bae0e0e0 mb20">
        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
            <div class="fl w232">银票订单号</div>
            <div class="fl w155">总金额</div>
            <div class="fl w105">背书</div>
            <div class="fl w212">付款行</div>
            <div class="fl w122">保证金</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="h190 bcwhite bbe0e0e0 pt25 pb25">
            <div class="fl w827 h190 Rright">
                <div class="bbe0e0e0 tc f16 h75">
                    <div class="fl w222 Rright h50 tl ml10">
                        <a href="${basePath}/distributeorder/detail/wait?no={{no}}"><div class="c3366cc ti8">{{no}}</div></a>
                    </div>
                    <div class="fl w155 Rright h50 lh50"><span class="cd43c33">{{allmoney}}</span>万元</div>
                    <div class="fl w105 Rright h50 lh50">{{endorse}}手</div>
                    <div class="fl w212 Rright h50 lh50">{{bank}}</div>
                    <div class="fl w122 h50 lh50"><span class="cd43c33">{{bail}}</span>元</div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h90">
                    <div class="fl w220 Rright h90 tl ml10">
                        <div class="ti8">下单日期：<span>{{formatDate begintime}}</span></div>
                        <div class="ti8 mt16">到期日期：<span>{{formatDate endtime}}</span></div>
                        <div class="ti8 mt16">计息天数：<span>{{jxts}}</span>天</div>
                    </div>
                    <div class="fl pl40 h90">
                        <div class="fl w50 h90">备注：</div>
                        <div class="fl w460 h90 tl">{{memberother}}
                        </div>
                    </div>
                </div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">
						{{toType1 type1}}
                    </span>
                    <span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">
						{{toType2 type2}}
                    </span>
					<span class="{{toTicket flaw_ticket}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">瑕疵票</span>
					<span class="{{toDoor need_todoor}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">需要上门</span>	
                </div>
            </div>
        </div>
        <div class="w h70 bcf9f9f9 tc pt15 f18">
            <a href="${basePath}/distributeorder/detail/wait?no={{no}}" class="fr cd43c33 bad43c33 br3 dsb w238 h45 lh45 mr40">
                接受订单<span id="minute_show{{id}}" class="minute_show">{{setTimer timer id}}</span>
            </a>
            <a href="javascript:void(0)" class="fr c2d2d2d bae0e0e0 br3 dsb w166 h45 lh45 mr40" onclick="tan1('{{no}}')">取消订单</a>
        </div>
    </div>
{{/each}}
</script>
<!-- 商票订单-->
<script type="text/x-handlebars-template" id="DISCSP">
{{#each dataSp}}
    <div class="mt30 ml30 mr30 bae0e0e0 mb20">
        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
            <div class="fl w232">商票订单号</div>
            <div class="fl w155">总金额</div>
            <div class="fl w105">背书</div>
            <div class="fl w334">承兑企业</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="h210 bcwhite bbe0e0e0 pt25 pb25">
            <div class="fl w827 Rright">
                <div class="bbe0e0e0 tc f16 h75">
                    <div class="fl w222 Rright h50 tl ml10">
                        <a href="${basePath}/distributeordersp/detail/wait?no={{no}}"><div class="c3366cc ti8">{{no}}</div></a>
                    </div>
                    <div class="fl w155 Rright h50 lh50"><span class="cd43c33">{{allmoney}}</span>万元</div>
                    <div class="fl w105 Rright h50 lh50">{{endorse}}手</div>
                    <div class="fl w334 h50 lh50">{{bank}}</div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h120">
                    <div class="fl w220 Rright h120 tl ml10">
                        <div class="ti8">开票日期：<span>{{formatDate printtime}}</span></div>
                        <div class="ti8 mt16">下单日期：<span>{{formatDate begintime}}</span></div>
                        <div class="ti8 mt16">到期日期：<span>{{formatDate endtime}}</span></div>
                        <div class="ti8 mt16">计息天数：<span>{{jxts}}</span>天</div>
                    </div>
                    <div class="fl pl40 h123">
                        <div class="fl w50 h120">备注：</div>
                        <div class="fl w460 h120 tl">{{remarks}}
                        </div>
                    </div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                	<span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">
                    	{{toType1 type1}}
                    </span>
                    <span class="{{toDoor need_todoor}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">需要上门</span>
                </div>
            </div>
        </div>
        <div class="cb"></div>
        <div class="w h70 bcf9f9f9 tc pt15 f18">
            <a href="${basePath}/distributeordersp/detail/wait?no={{no}}" class="fr cd43c33 bad43c33 br3 dsb w238 h45 lh45 mr40">
                接受订单<span id="minute_show1{{id}}">{{setTimer timerSp id}}</span>
            </a>
            <a href="javascript:void(0)" class="fr c2d2d2d bae0e0e0 br3 dsb w166 h45 lh45 mr40" id="refuseSp" onclick="tan2('{{no}}')">取消订单</a>
        </div>
    </div>
{{/each}}
</script>
<!-- 批量订单-->
<script type="text/x-handlebars-template" id="DISCPL">
{{#each dataPl}}
    <div class="mt30 ml30 mr30 bae0e0e0 mb20">
        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
            <div class="fl w232">批量订单号</div>
            <div class="fl w260">票据总金额</div>
            <div class="fl w150">票据总数量</div>
            <div class="fl w184">票据到期天数</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="cb"></div>
        <div class="h265 bcwhite bbe0e0e0 pt25 pb25">
            <div class="fl w827 Rright">
                <div class="bbe0e0e0 tc f16 h90 pb25">
                    <div class="fl w222 Rright h90 tl ml10">
                        <a href="${basePath}/distributeorderpl/detail/wait?no={{no}}"><div class="c3366cc ti8">{{no}}</div></a>
                    </div>
                    <div class="fl w260 Rright h90 lh30">
                        <div class=""><span class="cd43c33">{{allmoney}}</span>万元</div>
                        <div class="">票面最小金额为<span class="cd43c33">{{min_money}}</span>万元</div>
                        <div class="">票面最大金额为<span class="cd43c33">{{max_money}}</span>万元</div>
                    </div>
                    <div class="fl w150 Rright h90 lh30"><span class="cd43c33">{{amount}}</span>张</div>
                    <div class="fl w184 h90 lh30">
                        <div class="">最短<span class="cd43c33">{{min_expiry_day}}</span>天</div>
                        <div class="">最长<span class="cd43c33">{{max_expiry_day}}</span>天</div>
                    </div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h90">
                    <div class="fl w220 Rright h110 tl ml10">
                        <div class="ti8">包含承兑行：</div>
                        <ul class="ti8 mt16 lh35">
							<li class="fl mr30">{{toTypePl type2}}</li>
                        </ul>
                    </div>
                    <div class="fl pl40 h90">
                        <div class="fl w50 h90">备注：</div>
                        <div class="fl w460 h90 tl">{{remarks}}
                        </div>
                    </div>
                </div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    <a href="#" class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">
                    	{{toType1 type1}}
                    </a>
                    <span class="{{toTicket flaw_ticket}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">瑕疵票</span>
                    <span class="{{toDoor need_todoor}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">要求上门</span>
                </div>
            </div>
        </div>
        <div class="cb"></div>
        <div class="h65 lh65 ml10 ti8">
            订单有效期至：<span>{{formatDate endtime}}</span>
        </div>
        <div class="cb"></div>
        <div class="w h70 bte0e0e0 bcf9f9f9 tc pt15 f18">
            <a href="${basePath}/distributeorderpl/detail/wait?no={{no}}" class="fr cd43c33 bad43c33 br3 dsb w238 h45 lh45 mr40">
                接受订单
            </a>
            <a href="javascript:void(0)" class="fr c2d2d2d bae0e0e0 br3 dsb w166 h45 lh45 mr40" onclick="tan3('{{no}}')">取消订单</a>
        </div>
    </div>
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
    	if(num==1){
    		return "none";
    	}
    }
});

/* 
 * 格式化承兑行
 */
Handlebars.registerHelper('toType2', function(num, options) {
    if(num!=null){
    	return getBank(num);
    }
});

/* 
 * 批量订单承兑行
 */
Handlebars.registerHelper('toTypePl', function(num, options) {
	return getType2(num,false);
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
 * 格式银票时间倒计时 
 */
Handlebars.registerHelper('setTimer', function(time,id, options) {
    _timer(time,id);
    _timer1(time,id);
});

$(function(){
	listorder();
});
//查询所有待接单
function listorder(){
	$.ajax({
		type:"POST",
		url:"${basePath}/hall/list",
		dataType: "json",
		success: function (data) {
			if(data.response == "success"){
				var source = $("#DISC").html();
	            var template = Handlebars.compile(source);
	            var html = template(data);
	            $("#content").html(html);
	            
	            source = $("#DISCSP").html();
	            template = Handlebars.compile(source);
	            $("#content").append(template(data));
	            
	            source = $("#DISCPL").html();
	            template = Handlebars.compile(source);
	            $("#content").append(template(data));
	          	//如果没有订单则显示提示
	            if($("#content").text() !=null && $.trim($("#content").text())==""){
	                $("#content2").removeClass("none");
	            }
			}else{
				$("#content2").removeClass("none");
			}
		},
		error: function () {
        }
	});
}
//删除图片
function deleteImg(addImg,picpath,num){
		$("#"+addImg).addClass("none");
		$("#"+picpath).val("");
		$("#images").removeClass("none");
}
//上传图片
function fileSelected(targetId, targetInputId,uploadInputId,images) {	
	var uploadId="ryUpload";	
	if(uploadInputId==undefined){
		var fileName=jQuery("#wokeUpload").val();
	}else{
		var fileName=jQuery("#"+uploadInputId).val();
		uploadId=uploadInputId;
	}
	
	var strtype=fileName.substring(fileName.length-3);
	strtype=strtype.toLowerCase();
	if (strtype!="jpg"&&strtype!="gif"&&strtype!="png"){
		alert("请上传JPG、GIF、PNG格式的图片！");
		return false;
	}
	$.ajaxFileUpload({
		url : '${basePath}/uploadpic',
		secureuri : false,
		dataType : 'json',
		data : {
		},
		fileElementId : uploadId,
		success : function(data) {
			if(data=="error"){
				alert("上传失败！");
			}else{
				for(var i=1;i<=6;i++){
					if($("#picpath" + i).val()=="" || $("#img" + i).attr("src")==null){
						$("#img" + i).attr("src", data)==null;
						$("#picpath" + i).val(data);
						$("#addImg" + i).removeClass("none");
						return;
					}
					if(i==5){
						$("#images").addClass("none");
					}
				}
			}
		},
		error : function() {
			alert("上传失败！");
		}
	});
}

var currentState = null;//当前银票订单状态
var currentState_sp = null;//当前商票订单状态
var currentState_pl = null;//当前批量订单状态
var jgno_yp = null;//当前银票订单id
var jgno_sp = null;//当前商票订单id
var jgno_pl = null;//当前批量订单id

/**
 * 加载机构银票订单数据 
 */
function initData1(no){
	$.ajax({
		url:"${basePath}/distributeorder/get",
		data:{"no":no},
		dataType:"json",
		success:function(data){
			currentState = data.data.state;
		}
	});
	jgno_yp = no;
}

/**
 * 加载机构商票订单数据 
 */
function initData2(no){
	$.ajax({
		url: '${basePath}/distributeordersp/get',
		type: 'POST',
		data: {'no':no},
		dataType:"json",
		success:function(data){
			currentState_sp = data.data.state;
		}
	});
	jgno_sp = no;
}

/**
 * 加载机构批量订单数据 
 */
function initData3(no){
	$.ajax({
		url:"${basePath}/distributeorderpl/get",
		data:{"no":no},
		dataType:"json",
		success:function(data){
			currentState_pl = data.data.state;
		}
	});
	jgno_pl = no;
}

/**
 * 银票拒绝订单（或验票失败）
 */
function cancelDtbo(){
	$("#cancelButton").attr("disabled","disabled");
    var reason = $("#cancel1").find("option").not(function(){ return !this.selected }).text();//拒绝原因（文本）
    var lostCause = "";//其他理由
    var cancel1 = $("#cancel1").val();
    var cancel2 = $("#cancel2").val();
    
    var images = '';
    $("input[name='picpath']").each(function(i,o){
    	if(images!='')images+=",";
    	images+=$(o).val();
    });
    if(cancel1==2){//票据有问题
        if(images==null || images==""){
            alert("请上传图片");
            return;
        }
        reason = $("#cancel2").find("option").not(function(){ return !this.selected }).text();
    }else if(cancel1==5){//其他
        lostCause = $("#reason1_div").val();
        reason = lostCause;
        cancel2 = null;
        if (lostCause.length < 15) {
            alert("请你输入不少于15字的理由");
            $("#cancelButton").removeAttr("disabled");
            return;
        }
    }else{
        cancel2 = null;
        lostCause = "";
    }
    var data="no="+jgno_yp+"&cancel1="+cancel1+"&cancel2="+cancel2+"&reason="+
    			reason+"&lostCause="+lostCause+"&currentState="+currentState+"&images="+images;
    $.ajax({
    	url:"${basePath}/distributeorder/cancel",
    	type:"POST",
    	data:data,
    	dataType:"json",
    	success:function(data){
    		if (data.response == 'success') {
    			alert("取消成功!");
    			$("#reason").addClass("none");
    			listorder();
    			$("#cancelButton").removeAttr("disabled");
            }else{
            	$("#cancelButton").removeAttr("disabled");
                alert(data.msg);
            }
    	},
    	error:function(data){
    		$("#cancelButton").removeAttr("disabled");
    	}
    });
}

/**
 * 商票拒绝订单
 */
function cancelDtbo1(){
	$("#cancelButton1").attr("disabled","disabled");
    var reason = $("#cancel").find("option").not(function(){ return !this.selected }).text();//拒绝原因（文本）
    var lostCause = "";//其他理由
    var cancel = $("#cancel").val();
    var memberId = $("#memberId").val();
 	if(cancel==5){//其他
        lostCause = $("#reason_div").val();
        reason = lostCause;
        if (lostCause.length < 15) {
            alert("请你输入不少于15字的理由");
            $("#cancelButton1").removeAttr("disabled");
            return;
        }
    }else{
        lostCause = "";
    }
    var data="no="+jgno_sp+"&cancel="+cancel+"&reason="+reason+"&currentState="+currentState_sp;
    $.ajax({
    	url:"${basePath}/distributeordersp/cancel",
    	type:"POST",
    	data:data,
    	dataType:"json",
    	success:function(data){
    		if (data.response == 'success') {
    			alert("取消成功!");
    			$("#reason1").addClass("none");
    			listorder();
    			$("#cancelButton1").removeAttr("disabled");
            }else{
            	$("#cancelButton1").removeAttr("disabled");
                alert(data.msg);
            }
    	},
    	error:function(data){
    		$("#cancelButton1").removeAttr("disabled");
    	}
    });
}

/**
 * 批量拒绝订单
 */
function cancelDtbo2(){
	$("#cancelButton2").attr("disabled","disabled");
    var reason = $("#cancel3").find("option").not(function(){ return !this.selected }).text();//拒绝原因（文本）
    var lostCause = "";//其他理由
    var cancel = $("#cancel3").val();
    var memberId = $("#memberId").val();
 	if(cancel==5){//其他
        lostCause = $("#reason_div1").val();
        reason = lostCause;
        if (lostCause.length < 15) {
            alert("请你输入不少于15字的理由");
            $("#cancelButton2").removeAttr("disabled");
            return;
        }
    }else{
        lostCause = "";
    }
    var data="no="+jgno_pl+"&cancel="+cancel+"&reason="+reason+"&currentState="+currentState_pl;
    $.ajax({
    	url:"${basePath}/distributeorderpl/cancel",
    	type:"POST",
    	data:data,
    	dataType:"json",
    	success:function(data){
    		if (data.response == 'success') {
    			alert("取消成功!");
    			$("#reason2").addClass("none");
    			listorder();
    			$("#cancelButton2").removeAttr("disabled");
            }else{
            	$("#cancelButton2").removeAttr("disabled");
                alert(data.msg);
            }
    	},
    	error:function(data){
    		$("#cancelButton2").removeAttr("disabled");
    	}
    });
}

/*
 * 银票接单倒计时
 */
var timerArr = new Array();
function _timer(intDiff,id){
    var _t = window.setInterval(function(){
    	timerArr[timerArr.length] = _t;
        var day=0,
                hour=0,
                minute=0,
                second=0;//时间默认值
        if(intDiff > 0){
            minute = Math.floor(intDiff / 60);
            second = Math.floor(intDiff) - (minute * 60);
        }
        if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second;
        $('#minute_show'+id).html('('+minute+'分'+second+'秒'+')');
        if(intDiff<=0){
            clearInterval(_t);
        }
        intDiff--;
    }, 1000);
}

/*
 * 商票接单倒计时
 */
var timerArr1 = new Array();
function _timer1(intDiff,id){
    var _t = window.setInterval(function(){
    	timerArr1[timerArr1.length] = _t;
        var day=0,
            hour=0,
            minute=0,
            second=0;//时间默认值
        if(intDiff > 0){
            minute = Math.floor(intDiff / 60);
            second = Math.floor(intDiff) - (minute * 60);
        }
        if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second;
        $('#minute_show1'+id).html('('+minute+'分'+second+'秒'+')');
        if(intDiff<=0){
            clearInterval(_t);
        }
        intDiff--;
    }, 1000);
}

/*
 * 银票拒绝订单理由显示隐藏
 */
function tan1(no){
	initData1(no);
	$("#reason").removeClass("none");
}

/*
 * 商票拒绝订单理由显示隐藏
 */
function tan2(no){
	initData2(no);
	$("#reason1").removeClass("none");
}

/*
 * 批量拒绝订单理由显示隐藏
 */
function tan3(no){
	initData3(no);
	$("#reason2").removeClass("none");
}
$("#redClose").click(function(){
	$("#reason").addClass("none");
});
$("#redClose1").click(function(){
	$("#reason1").addClass("none");
});
$("#redClose2").click(function(){
	$("#reason2").addClass("none");
});

/*
 * 银票填写理由
 */
$("#cancel1").change(function(){
    var value = $(this).val();
    if(value==2){
        $("#reason1_div").addClass("none");
        $("#reason2_div").removeClass("none");
    }else if(value==5){
        $("#reason1_div").removeClass("none");
        $("#reason2_div").addClass("none");
    }else{
        $("#reason1_div").addClass("none");
        $("#reason2_div").addClass("none");
    }
});

/*
 * 商票填写理由
 */
$("#cancel").change(function(){
    var value = $(this).val();
    if(value==5){
        $("#reason_div").removeClass("none");
    }else{
        $("#reason_div").addClass("none");
    }
});

/*
 * 批量填写理由
 */
$("#cancel3").change(function(){
    var value = $(this).val();
    if(value==5){
        $("#reason_div1").removeClass("none");
    }else{
        $("#reason_div1").addClass("none");
    }
});
</script>
[@main.footer /]
[/@main.body ]