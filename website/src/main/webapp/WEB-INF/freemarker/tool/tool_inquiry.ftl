[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.inquiry]
[@main.header currentmenu='1' topindex="5"/]
 [@main.right /]
<div class="w1200 bc mt20 mb20 bcwhite">
    <div class="w bcf4f4f4 h100 lh100 f36 ti65 fb">银票询价</div>
    <div class="w1198 bae0e0e0 h608">
        <div class="w h83 lh83 f22 bcf4f4f4">
            <div class="fl w_50">
                <div class="ml165"><span id="showinfo1">每10万贴息:(买断)：</span><span class="cd43c33 f24" id="showinfo2">--</span></div>
            </div>
            <div class="fl w_50">
                <div class=""><span id="showinfo3">您的贴现利息:(买断)</span><span class="cd43c33 f24" id="showinfo4">--</span></div>
            </div>
        </div>
        <div class="ml100 mr100">
            <div class="wa mt30 lh40">
                <div class="fl w_50">
                    <div class="fl w100 ml65">票据属性：</div>
                    <select id="type6" class="fl w245 h40 select245 f18 ti25">
                        <option value="1">纸票</option>
                        <option value="2" selected="selected">电票</option>
                    </select>
                </div>
                <div class="fl w_50">
                    <div class="fl w115">承兑行类型：</div>
                    <select id="type1" class="fl w245 h40 select245 f18 ti25" id="type1">
                        <option value="1" selected="selected">国股</option>
                        <option value="8">大商</option>
                        <option value="2">小商</option>
                        <option value="3">外资</option>
                        <option value="4">农商</option>
                        <option value="5">农合</option>
                        <option value="6">农信</option>
                        <option value="7">村镇</option>
                    </select>
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40">
                <div class="fl w_50">
                    <div class="fl w100 ml65">票据金额：</div>
                    <input id="allmoney" type="text" class="fl w245 h40 f18 ti25 bae0e0e0 br5 validate[required,custom[allprice]]" placeholder="请输入票据金额">
                    <div class="fl ml10">万元</div>
                </div>
                <div class="wa mt30 lh40" id="years">
             	<div class="fl w115">承兑期限：</div>
                    <select id="limit" class="fl w245 h40 select245 f18 ti25">
                        <option value="0" >半年期</option>
                        <option value="1" selected="selected">一年期</option>
                    </select>
            	</div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40">
                <div class="fl w_50">
                    <div class="fl w100 ml65">贴现日期：</div>
                    <input id="startDate" type="text" class="fl w245 h40 f18 ti25 bcf5f5f5 bae0e0e0 br5" placeholder="请选择贴现日期" readonly="readonly">
                </div>
                <div class="fl w_50">
                    <div class="fl w115">到期日期：</div>
                    <input id="endDate" type="text" class="fl w245 h40 f18 ti25 bcf5f5f5 bae0e0e0 br5" placeholder="请选择到期日期" readonly="readonly">
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40">
                <div class="fl w_50">
                    <div class="fl w100 ml65">调整天数：</div>
                    <input type="text" id="day" class="fl w245 h40 f18 ti25 bae0e0e0 br5 validate[maxSize[2],custom[integer1]]" placeholder="请输入天数">
                    <div class="fl ml10">天</div><input type="hidden" id="day-hidden"/>
                </div>
                <div class="fl w_50">
                    <div class="fl w115">计息天数：</div>
                    <div class="fl f16"><span id="days" class="f18">183</span>天</div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="cb"></div>
            <div class="wa mt59 lh60">
                <div class="fl w_50">
                    <input class="w250 h60 bcd43c33 b0 br5 cwhite tc f24 cp ml170" readonly="readonly" value="询价" onclick="xunjia();">
                </div>
                <div class="fl w_50">
                    <input class="w250 h60 bcd43c33 b0 br5 cwhite tc f24 cp" value="贴现" onclick="checkAccount(0,'/discountrecord/discount_yp1');"  readonly="readonly">
                </div>
                <div class="cb"></div>
            </div>
        </div>
    </div>
</div>

	<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript">
	var memberId="${member.id}";
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
	$("#type6").change(function (){
		if($("#type6").val()==2){
			$("#years").show();
			var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}else{
			var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
			$("#years").hide();
		}
		jixidate();	
	})
	$("#day").blur(function (){
		var day=$("#day").val();
		var dayold=$("#day-hidden").val();
		if(!$("#day").validationEngine("validate")){
    		$("#day").focus();
    		setTimeout(function(){$("#day").validationEngine('hideAll');},1000);
    		$("#day").val(dayold);//重置
    		return;
    	}else{
			$("#day-hidden").val(day);
			var days=$("#days").html();
			$("#days").html(Number(days)-Number(dayold)+Number(day));
		}
	})
	
	//计息天数
	function jixidate() {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var type6 = $("#type6").val();//纸票1、电票2
		$.ajax({
			url:"${basePath}/homepage/xunjia/jixidate",
			type:"post",
			data:{"startDate":startDate,"endDate":endDate,"type6":type6},
			dataType:"json",
			success:function(data){
                $("#days").html(data.jxts);
                $("#day-hidden").val(data.tzts);
                $("#day").val(data.tzts);
			}
		});
	}
	
	function loaddate(){
		$("#startDate").val(fullyear + "-" + month + "-" + date);
		$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);
		jixidate();
	}
	
	$("#limit").change(function (){
		if($("#limit").val()==1){
			var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}else{
			var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
			var endfullyear = endtime.getFullYear();
			//获取完整的年份(4位,1970-????)
			var endmonth = endtime.getMonth() + 1;
			//获取当前月份(0-11,0代表1月)
			var enddate = endtime.getDate();
			$("#endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
		}
		jixidate();
	})
	
	
	//询价
	function xunjia() {
		var limit=$("#limit").val();
		var type6 = $("#type6").val();//纸票1、电票2
		var type1 = $("#type1").val();//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
		var allmoney = $("#allmoney").val();//票据金额（对应type2 票据金额 :1:500以上 2:100-500 3:50-100  4:<50）
		if(!$("#allmoney").validationEngine("validate")){
    		$("#allmoney").focus();
    		setTimeout(function(){$("#allmoney").validationEngine('hideAll');},1000);
    		return;
    	}
		var type5="";
		var dates = $("#days").html();
		
		if(Number(allmoney)<500){//大票没有票据属性
        	if (Number(dates) <= 90) {
    			type5 = '1';
    		} else if (Number(dates) <= 178) {
    			type5 = '2';
    		} else {
    			type5 = '3';
    		}
        }
		
		if(type6 == 1){
			type5 = '2';
		}
		$.ajax({
			url:"${basePath}/homepage/xunjia/xunjia",
			type:"post",
			data:{"type6":type6,"type1":type1,"type3":"1","type5":type5,"limit":limit,"allmoney":allmoney,"dates":dates},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					 var result = data.data;
	                    var price = result.price;
	                    var price1 = result.price1;
	                    var price2 = result.price2;
	                    var price3 = data.txlx;
	                    if($("#type6").val()==1){//纸票
	                        if(data.type2==1){
	                            $("#showinfo1").html("当前价格-月利率:(买断)");
	                            if(price=='--'||price==''){
	                                alert("暂无该选项数据");
	                                $("#showinfo2").html("--");
	                            }else{
	                                $("#showinfo2").html(price+"‰");
	                            }
	                        }else{
	                            $("#showinfo1").html("每10万贴息:(买断)");
	                            if(price2=='--'||price2==''){
	                                $("#showinfo2").html("--");
	                            }else{
	                                $("#showinfo2").html(price2+"元");
	                            }
	                        }
	                        $("#showinfo3").html("您的贴现利息:(买断)");
	                    }else{//电票
	                        $("#showinfo1").html("当前价格-年利 率：");
	                        if(price=='--'||price==''){
	                            alert("暂无该选项数据");
	                            $("#showinfo2").html("--");
	                        }else{
	                            $("#showinfo2").html(price+"%");
	                        }
	                    }
	                    if(price3 != null && price3 != "" && price3!='--'){
	                        $("#showinfo3").html("您的贴息利息:(买断)");
	                        $("#showinfo4").html(price3+"元");
	                    }else{
	                        $("#showinfo3").html("您的贴息利息:(买断)");
	                        $("#showinfo4").html("--");
	                    }
				}else{
					$("#showinfo2").html("--");
					alert(data.msg);
				}
			}
		});
	}
	
	$(document).ready(function() {
		loaddate();
	})
	
	/**
	*  点击提交按钮，调转到贴现的功能页面
	*/
	function tiexian(){
		if($.trim(memberId).length>0){
			var map = new Map();
			var allprice = $("#allmoney").val();
			var shuxing = $("#type6").val();
			var acceptTime = $("#limit").val();
			var type1 = $("#type1").val();
			var days= $("#days").html();
			var begintime= $("#startDate").val();
			var endtime= $("#endDate").val();
			if(allprice==""){
				$("#allmoney").validationEngine('showPrompt','* 请输入票据金额',null,null,true);
				setTimeout(function(){$("#allmoney").validationEngine('hide');},2000);
				$("#allmoney").focus();
				return;
			}
			if(shuxing=="1"){
				acceptTime = "";
			}
			map.put("_PAGE", "discountrecord/discount_yp1");//必传
			map.put("shuxing", shuxing);
			map.put("acceptTime",acceptTime);
			map.put("type1",type1);
			map.put("days",days);
			map.put("allmoney",allprice);
			map.put("begintime",begintime);
			map.put("endtime",endtime);
			_OPENPAGE_FORM(map);
		}else{
			alert("你还没有登陆，请去登陆");
		}
	}


    //日历插件
	!function() {
		laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
	}();
	//日期范围限制
	//    贴现日期
	var start = {
		elem : '#startDate',
		format : 'YYYY-MM-DD',
		//min: laydate.now(), //设定最小日期为当前日期
		min : '1900-01-01', //设定最小日期
		max : '2099-06-16', //最大日期
		istime : true,
		istoday : false,
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
			end.start = datas //将结束日的初始值设定为开始日
			jixidate();
		}
	};
	//    到期日期
	var end = {
		elem : '#endDate',
		format : 'YYYY-MM-DD',
		min : laydate.now(),
		max : '2099-06-16',
		istime : true,
		istoday : false,
		choose : function(datas) {
			jixidate();
			start.max = datas; //结束日选好后，充值开始日的最大日期
		}
	};
	laydate(start);
	laydate(end);
	
	</script>
[@main.footer/]
[/@main.body]