[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.calculator]
[@main.header currentmenu='1' topindex='5'/]
 [@main.right /]
<div class="w1200 bc mt20 mb20 bcwhite">
    <div class="w bcf4f4f4 h100 lh100 f36 ti65 fb">贴现计算器</div>
    <div class="w1198 bae0e0e0 h750">
        <div class="w h83 lh83 f22 bcf4f4f4">
            <div class="fl w_50">
                <div class="ml165">贴现利息（元）：<span class="cd43c33 f24" id="c_txlx">00.00</span></div>
            </div>
            <div class="fl w_50">
                <div class="">贴现金额（万元）：<span class="cd43c33 f24" id="c_txje">00.00</span></div>
            </div>
        </div>
        <div class="ml100 mr100">
            <div class="wa mt30 lh40">
                <div class="fl w_50 none">
                    <div class="fl w100 ml65">票据属性：</div>
                    <select class="fl w245 h40 select245 f18 ti25" id="c_type1">
                       <option value="1">纸票</option>
                       <option value="2" selected="selected">电票</option>
                    </select>
                </div>
                <div class="fl w_50 none">
                    <div class="fl w100">承兑行类型：</div>
                    <select class="fl w245 h40 select245 f18 ti25"  id="c_type2">
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
                    <div class="fl w100 ml65">总金额：</div>
                    <input id="c_allmoney" type="text" class="fl w245 h40 f18 ti25 bae0e0e0 br5 validate[required,custom[allprice]]" placeholder="请输入票据金额">
                    <div class="fl ml10">万元</div>
                </div>
                <div id="c_years" class="fl w_50 none">
                    <div class="fl w100">承兑期限：</div>
                     <select class="fl w245 h40 select245 f18 ti25" id="c_acceptTime">
                       <option value="0">半年期</option>
                       <option value="1"  selected="selected">一年期</option>
                    </select>
                </div>
                <div class="fl w_50">
                    <div class="fl w100">计息天数：</div>
                    <div class="fl f16"><span class="f18" id="c_jxts"></span>天</div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40">
                <div class="fl w_50">
                    <div class="fl w100 ml65">贴现日期：</div>
                    <input readonly="readonly" type="text" id="c_startDate" class="fl w245 h40 f18 ti25 bcf5f5f5 bae0e0e0 br5" placeholder="请选择贴现日期">
                </div>
                <div class="fl w_50">
                    <div class="fl w100">到期日期：</div>
                    <input readonly="readonly" type="text" id="c_endDate" class="fl w245 h40 f18 ti25 bcf5f5f5 bae0e0e0 br5" placeholder="请选择到期日期">
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40 lh40  ">
                <div class="fl w_50">
                    <div class="fl w100 ml65">调整天数：</div>
                    <input type="text" id="c_day" class="fl w245 h40 f18 ti25 bae0e0e0 br5 validate[maxSize[2],custom[integer1]]" placeholder="请输入天数">
                    <div class="fl ml10">天</div><input type="hidden" id="c_day-hidden"/>
                </div>
                <div class="fl w_50" id="shouxu">
                    <div class="fl w100">手续费：</div>
                    <input type="text" id="c_poundage" class="fl w245 h40 f18 ti25 bae0e0e0 br5 validate[custom[baojianumber]]" placeholder="请输入手续费">
                    <div class="fl ml10">元</div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40">
                <div class="fl w_50">
                    <div class="fl w100 ml65">年利率：</div>
                    <input type="text" class="fl w68 h27 bae0e0e0 f14 tc validate[custom[jisuan]]" placeholder="00.00" onblur="rateYear()" id="rateYear">
                    <div class="fl ml10 mr10"><span class="f14 mr20 tc" >%</span></div>
                </div>
                <div class="fl w_50" id="shouxu">
                    <div class="fl w100">月利率：</div>
                    <input id="rate" type="text" class="fl w68 h27 bae0e0e0 f14 tc validate[custom[jisuan]]" onblur="rate()" placeholder="00.00">
                    <div class="fl ml10 mr10"><span class="w16 mr20" >‰</span>+</div>
                    <input type="text" class="fl w68 h27 bae0e0e0 f14 tc ml20 validate[custom[baojianumber]]" id="rate1" placeholder="0">
                    <div class="fl ml10 mr10">元</div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt30 lh40 lh40  ">
                <div class="fl w_50">
                    <div class="fl w100 ml65">每十万扣：</div>
                    <input type="text" id="rate2" class="fl w140 h27 bae0e0e0 f14 tc validate[custom[tennumber]]" onblur="rate2()" placeholder="00.00">
                </div>
                <div class="cb"></div>
            </div>
            <div class="wa mt59 lh60">
                <div class="fl w_33">
                	<input readonly="readonly" class="w250 h60 bcf2f2f2 b0 br5 tc f24 cp" onclick="c_clear();" value="清空">
                </div>
                <div class="fl w_33">
                    <input class="w250 h60 bcd43c33 b0 br5 cwhite tc f24 cp" value="贴现" onclick="checkAccount(0,'/discountrecord/discount_yp1');" readonly="readonly">
                </div>
                <div class="fl w_33">
                	<input class="w250 h60 bcd43c33 b0 br5 cwhite tc f24 cp" value="一键测算" readonly="readonly" onclick="c_jisuan()" type="button">
                </div>
                <div class="cb"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript">
	var memberId="${member.id}";
    $(document).ready(function() {
			setPanel();
    	   $("#c_type1").change(function (){
    		   if($("#c_type1").val()==2){
    				var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
    				var endfullyear = endtime.getFullYear();
    				//获取完整的年份(4位,1970-????)
    				var endmonth = endtime.getMonth() + 1;
    				//获取当前月份(0-11,0代表1月)
    				var enddate = endtime.getDate();
    				$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
    			}else{
    				var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
    				var endfullyear = endtime.getFullYear();
    				//获取完整的年份(4位,1970-????)
    				var endmonth = endtime.getMonth() + 1;
    				//获取当前月份(0-11,0代表1月)
    				var enddate = endtime.getDate();
    				$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
    			}
    			setPanel();//设置对应的票据价格
    		})
    		$("#c_allmoney").change(function (){
    			if(!$("#c_allmoney").validationEngine("validate")){
    	    		$("#c_allmoney").focus();
    	    		setTimeout(function(){$("#c_allmoney").validationEngine('hideAll');},1000);
    	    		return;
    	    	}
    			setPanel();//设置对应的票据价格
    		})
    		
    		loaddate();//初始化日期和城市
    		
    		$("#c_acceptTime").change(function (){
    			if($("#c_acceptTime").val()==1){
    				var endtime = new Date(begintimelong + 360 * 24 * 60 * 60 * 1000);
    				var endfullyear = endtime.getFullYear();
    				//获取完整的年份(4位,1970-????)
    				var endmonth = endtime.getMonth() + 1;
    				//获取当前月份(0-11,0代表1月)
    				var enddate = endtime.getDate();
    				$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
    			}else{
    				var endtime = new Date(begintimelong + 180 * 24 * 60 * 60 * 1000);
    				var endfullyear = endtime.getFullYear();
    				//获取完整的年份(4位,1970-????)
    				var endmonth = endtime.getMonth() + 1;
    				//获取当前月份(0-11,0代表1月)
    				var enddate = endtime.getDate();
    				$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//询价
    			}
    			setPanel();
    		})
    	
    })
   $("#c_day").blur(function (){
		var day=$("#c_day").val();
		var dayold=$("#c_day-hidden").val();
		var reg=/^[1-9][0-9]*$/;//正整数
		if (!reg.test(day)) {
			alert("请输入正整数调整日期");
			$("#c_day").val(dayold);//重置
		}else{
			$("#c_day-hidden").val(day);
			var days=$("#c_jxts").html();
			$("#c_jxts").html(Number(days)-Number(dayold)+Number(day));
		}
	})
    function loaddate(){//初始化日期和城市
    	$("#c_startDate").val(fullyear + "-" + month + "-" + date);//计算器
		$("#c_endDate").val(endfullyear + "-" + endmonth + "-" + enddate);//计算器
    }
    
	//计算器的票据价格类型
	function setPanel(){
		initExcel();
	}
	
	function initExcel(){
		if($("#c_allmoney").val()=="")return;
        var type1 = $("#c_type1").val();
        var type2 = $("#c_type2").val();
        var start = $("#c_startDate").val();
        var end = $("#c_endDate").val();
        var allmoney = $("#c_allmoney").val();
        var acceptTime = $("#c_acceptTime").val();
        $.ajax({
			url:"${basePath}/homepage/calculator/init",
			type:"post",
			data:{"type2":type2,"type1":type1,"limit":acceptTime,"allmoney":allmoney,"start":start,"end":end,},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					 $("#c_day").val(data.tzts);
		             $("#c_day-hidden").val(data.tzts);
		             
		            if($("#c_type1").val()==2){
	                    if(data.rate!=null && data.rate!="" && data.rate!="--"){
	                        $("#rateYear").val(data.rate);
	                    }
	                }else{
	                    if(data.rate!=null && data.rate!="" && data.rate!="--"){
	                    	if($("#c_allmoney").val()>=500){
	                    		$("#rate").val(data.rate);//月（年）利率
	                    	}else{
	                    		$("#rate2").val(data.rate2);//每十万贴息
	                    	}
	                    }
	                }
	                $("#c_jxts").text(data.jxts);//计息天数
				}
			}	
        })
	}
	
	function tenTom(ten,jxts){
	    if(jxts==null)return;
	    var res = Number(ten);
	    var result = res/Number(jxts)/100000*1000*30;
	    return result.toFixed(2);
	}
	
	function getTXJE(allmoney,txlx,poundage){
	    var res = (Number(allmoney)*10000)-Number(txlx);
	    if(poundage!=null)res -= Number(poundage);
	    return (res/10000).toFixed(2);
	}
	
	function c_jisuan(){
        var type1 = $("#c_type1").val();
        var type2 = $("#c_type2").val();
        var start = $("#c_startDate").val();
        var end = $("#c_endDate").val();
        var allmoney = $("#c_allmoney").val();
        var rate1 = $("#rate1").val();
        var jxts=$("#c_jxts").text();
        var rate2=$("#rate2").val();
        var rate = $("#rateYear").val();//年息 电票
        if(!$("#c_allmoney").validationEngine("validate")){
    		$("#c_allmoney").focus();
    		setTimeout(function(){$("#c_allmoney").validationEngine('hideAll');},1000);
    		return;
    	}
        if(!$("#c_poundage").validationEngine("validate")){
    		$("#c_poundage").focus();
    		setTimeout(function(){$("#c_poundage").validationEngine('hideAll');},1000);
    		return;
    	}
        if(type1==2){//电票
        	if(!$("#rateYear").validationEngine("validate")){
        		$("#rateYear").focus();
        		setTimeout(function(){$("#rateYear").validationEngine('hideAll');},1000);
        		return;
        	}
        }else if(type1==1 && Number(allmoney)>=500){
        	if(!$("#rate").validationEngine("validate")){
        		$("#rate").focus();
        		setTimeout(function(){$("#rate").validationEngine('hideAll');},1000);
        		return;
        	}
        	rate = $("#rate").val();//月利率 纸票大票
        }else{
        	if(!$("#rate2").validationEngine("validate")){
        		$("#rate2").focus();
        		setTimeout(function(){$("#rate2").validationEngine('hideAll');},1000);
        		return;
        	}
        	rate2=tenTom(rate2,jxts);
        }
        if(start>end){
        	alert("贴现日期不能超过到期日期");
        	return;
        }
        $.ajax({
			url:"${basePath}/homepage/calculator1",
			type:"post",
			data:{"type2":type2,"type1":type1,"rate":rate,"rate1":rate1,"rate2":rate2,"allmoney":allmoney,"start":start,"end":end,"jxts":jxts},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					 $("#c_txlx").text(data.txlx);//贴现利息
					 var allmoney = $("#c_allmoney").val();
		             var poundage = $("#c_poundage").val();
		             var txje = getTXJE(allmoney,data.txlx,poundage);
		             $("#c_txje").text(txje);//贴现金额
				}
			}
        })	
	}
	
	/**
	*  点击提交按钮，调转到贴现的功能页面
	*/
	function tiexian(){
		if($.trim(memberId).length>0){
			var map = new Map();
			var allprice = $("#c_allmoney").val();
			var shuxing = $("#c_type1").val();
			var acceptTime = $("#c_acceptTime").val();
			var type1 = $("#c_type2").val();
			var days = $("#c_jxts").val();
			var begintime = $("#c_startDate").val();
			var endtime = $("#c_endDate").val();
			if(allprice==""){
				$("#c_allmoney").validationEngine('showPrompt','* 请输入票据金额',null,null,true);
				setTimeout(function(){$("#c_allmoney").validationEngine('hide');},2000);
				$("#c_allmoney").focus();
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
	    !function() {
		laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
	    }();
    
    	var start1 = {
			elem : '#c_startDate',
			format : 'YYYY-MM-DD',
			//min: laydate.now(), //设定最小日期为当前日期
			min : '1900-01-01', //设定最小日期
			max : '2099-06-16', //最大日期
			istime : true,
			istoday : false,
			choose : function(datas) {
				initExcel();
				end1.min = datas; //开始日选好后，重置结束日的最小日期
				end1.start = datas; //将结束日的初始值设定为开始日
			}
		};
		//    到期日期
		var end1 = {
			elem : '#c_endDate',
			format : 'YYYY-MM-DD',
			min : laydate.now(),
			max : '2099-06-16',
			istime : true,
			istoday : false,
			choose : function(datas) {
				initExcel();
				start1.max = datas; //结束日选好后，充值开始日的最大日期
			}
		};
		laydate(start1);
		laydate(end1);
		
		function c_clear() {
			$("#c_jxts").text("");
			$("#c_allmoney").val("");
			$("#c_day").val("0");
			$("#c_txlx").text("");
			$("#c_txje").text("");
			$("#c_poundage").val("");
			$("#rate").val("");
			$("#rate1").val("");
			$("#rate2").val("");
			$("#rateYear").val("");
		}
		
		/**
		* 年利率修改触发
		*/
		function rateYear(){
			var rateYear = $("#rateYear").val();
			var rate =(Number(rateYear)/12*10).toFixed(2);
			var jxts = $("#c_jxts").html();
			var rate1 = $("#rate1").val();
			var rate2 = Number(rate1) + (Number(rate*1)/30/1000*100000*Number(jxts));
			$("#rate").val((rate*1).toFixed(2));
		    $("#rate2").val((rate2*1).toFixed(2));
		};
		
		/**
		* 月利率修改触发
		*/
		function rate(){
			var jxts = $("#c_jxts").html();
			var allmoney = $("#c_allmoney").val();
			var rate = $("#rate").val();
			var rate1 = $("#rate1").val();
		    var rate2 = Number(rate1) + (Number(rate*1)/30/1000*100000*Number(jxts));
		    $("#rate2").val((rate2*1).toFixed(2));
		    $("#rateYear").val((rate*12/10).toFixed(2));
		};
		
		/**
		* 每十万修改触发
		*/
		function rate2(){
			var jxts = $("#c_jxts").html();
			var allmoney = $("#c_allmoney").val();
			var rate2 = $("#rate2").val();
			var rate1 = $("#rate1").val();
		    var rate =  (Number(rate2*1)-Number(rate1))/Number(jxts*1)/100000*1000*30;
		    $("#rate").val((rate*1).toFixed(2));
		    $("#rateYear").val((rate*12/10).toFixed(2));
		};
</script>
[@main.footer/]
[/@main.body]