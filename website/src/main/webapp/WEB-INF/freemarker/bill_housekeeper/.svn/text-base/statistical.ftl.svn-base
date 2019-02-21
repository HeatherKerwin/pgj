[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-票据管理']
[@main.header currentmenu='1' topindex='4'/]
<script type="text/javascript" src="https://static.utiexian.com/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/plugins/highcharts/modules/exporting.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/website/radio.js"></script>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">

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
    
    <!--tab 菜单-->
    <ul class="w box-shadow box-sizing h51 orderTabNew mt10">
        <li class="w300 h">
            <a class="h50 lh50 f14 tabChecked" href="#">
                <label for="time1"><input type="radio" value="0" name="orderTabBtn" class="orderTabBtn timeDate" id="time1" checked>最近一月</label>
            </a>
        </li>
        <li class="w300 h">
            <a class="h50 lh50 f14" href="#">
                <label for="time2"><input type="radio" value="1" name="orderTabBtn" class="orderTabBtn timeDate" id="time2">最近三月</label>
            </a>
        </li>
        <li class="w300 h">
            <a class="h50 lh50 f14" href="#">
                <label for="time3"><input type="radio" value="2" name="orderTabBtn" class="orderTabBtn timeDate" id="time3">最近半年</label>
            </a>
        </li>
        <li class="w300 h">
            <a class="h50 lh50 f14" href="#">
                <label for="time4"><input type="radio" value="3" name="orderTabBtn" class="orderTabBtn timeDate" id="time4">最近一年</label>
            </a>
        </li>
    </ul>
    
    <!-- 查询 -->
    <div class="w h60 box-sizing pt20 pb10 pl30 lh30 bcwhite clearfix">
        <div class="fl fb">时间：</div>
        <input type="text" class="fl w100 h30 box-sizing bae0e0e0 br3 ml20 pl10 pr10" readonly id="startDate">
        <label class="fl w30 h27 rili ml10 mt1 cp" for="startDate"></label>

        <div class="fl ml10 cababab">——</div>
        <input type="text" class="fl w100 h30 box-sizing bae0e0e0 br3 ml10 pl10 pr10" readonly id="endDate">
        <label class="fl w30 h27 rili ml10 mt1 cp" for="endDate"></label>

        <button class="fl w80 h30 ba bae84c3d bce84c3d cwhite br3 ml20 cp" onclick="loadStatistical();">查询</button>
    </div>

    <!-- 分布图 -->
    <div class="w bcwhite mt10 pt20 pl20 pr20 pb50 box-shadow box-sizing">
        <div class="w">
            <!-- 标签 -->
            <div class="bl3_fc4d42 pl10 h24 lh24">已出票票据</div>
            <div class="w clearfix oh pt10">
                <!-- 贴现金额分布 -->
                <div class="w_50 fl lh20 tc f12">
                    <!-- 标题 -->
                    <h5>贴现金额分布</h5>
                    <!-- 时间 -->
                    <p class="cababab time"></p>
                    <!-- 分布饼图 -->
                    <canvas id="myChart1"></canvas>
                </div>

                <!-- 贴现金额分布 -->
                <div class="w_50 fl lh20 tc f12">
                    <!-- 标题 -->
                    <h5>贴现利息分布</h5>
                    <!-- 时间 -->
                    <p class="cababab time"></p>
                    <!-- 分布饼图 -->
                    <canvas id="myChart2"></canvas>
                </div>
            </div>

        </div>
        <div class="w clearfix oh pt10 mt20">
            <!-- 贴现金额分布 -->
            <div class="w_50 fl lh20 tc f12">
                <!-- 标签 -->
                <div class="bl3_fc4d42 pl10 f16 h24 lh24 tl">持有中票据</div>
                <!-- 标题 -->
                <h5>银票总金额分布</h5>
                <!-- 时间 -->
                <p class="cababab time"></p>
                <!-- 分布饼图 -->
                <canvas id="myChart3"></canvas>
            </div>

            <!-- 贴现金额分布 -->
            <div class="w_50 fl lh20 tc f12">
                <!-- 标签 -->
                <div class="bl3_fc4d42 pl10 f16 h24 lh24 tl">待入账票据</div>
                <!-- 标题 -->
                <h5>银票总金额分布</h5>
                <!-- 时间 -->
                <p class="cababab time"></p>
                <!-- 分布饼图 -->
                <canvas id="myChart4"></canvas>
            </div>
        </div>
		<div class="w clearfix oh pt10 mt20">
            <!-- 票据状态分布 -->
            <div class="w_50 fl lh20 tc f12">
                <!-- 标签 -->
                <div class="bl3_fc4d42 pl10 f16 h24 lh24 tl">票据状态分布</div>
                <!-- 标题 -->
                <h5>待入账&nbsp;&nbsp;质押中&nbsp;&nbsp;持有中&nbsp;&nbsp;已出库</h5>
                <!-- 时间 -->
                <p class="cababab time"></p>
                <!-- 分布饼图 -->
                <canvas id="myChart5"></canvas>
            </div>

            <!-- 票据种类分布 -->
            <div class="w_50 fl lh20 tc f12">
                <!-- 标签 -->
                <div class="bl3_fc4d42 pl10 f16 h24 lh24 tl">票据种类分布</div>
                <!-- 标题 -->
                <h5>银电&nbsp;&nbsp;银纸&nbsp;&nbsp;商电&nbsp;&nbsp;商纸</h5>
                <!-- 时间 -->
                <p class="cababab time"></p>
                <!-- 分布饼图 -->
                <canvas id="myChart6"></canvas>
            </div>
        </div>
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
        
        <div class="flex-row flex-direction-column w lh30 mt20 none contract">
			<div class="flex-row flex-direction-column pl20">
				<input type="email" id="email" maxlength="30" class="w310 h30 bae0e0e0 br3 ti10 ml70 validate[required,custom[email]]" placeholder="请输入经办人邮箱号">
			</div>
			<div class="flex-row flex-direction-column pl20">
				<input type="button"  onclick="excel();" value="发送" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 ml200 mt10 cp" id="sendEmail">
            </div>
        </div>
        
        [@main.popup/]
    </div>
</div>

<script type="text/javascript" src="https://static.utiexian.com/js/common/chartJs/Chart.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/website/tab.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/plugins/data/laydate.js"></script>
<script>
    $(document).ready(function() {
    	loadStatistical();
    })

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
        elem : '#startDate',
        format : 'YYYY-MM-DD',
        //min: laydate.now(), //设定最小日期为当前日期
        min : '1900-01-01', //设定最小日期
        max : '2099-06-16', //最大日期
        istime :true,
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
        istime :true,
        istoday : false,
        choose : function(datas) {
            start.max = datas; //结束日选好后，充值开始日的最大日期
        }
    };

    laydate(start);
    laydate(end);
    
    var n = Date.parse(new Date());
    var now = new Date(n - 30 * 24 * 60 * 60 * 1000);
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
	$("#startDate").val(fullyear+"-"+month+"-"+date);
	
	var begintimelong = new Date();
    var endfullyear = begintimelong.getFullYear();
    //获取完整的年份(4位,1970-????)
    var endmonth = begintimelong.getMonth() + 1;
    //获取当前月份(0-11,0代表1月)
    var enddate = begintimelong.getDate();
	//获取当前日(1-31)
	if (endmonth < 10) {
		endmonth = "0" + endmonth;
	}
	if (enddate < 10) {
		enddate = "0" + enddate;
	}
	$("#endDate").val(endfullyear+"-"+endmonth+"-"+enddate);

	$(".time").html(fullyear+"-"+month+"-"+date+"~~"+endfullyear+"-"+endmonth+"-"+enddate)
	
    var ctx1 = document.getElementById("myChart1");
    var ctx2 = document.getElementById("myChart2");
    var ctx3 = document.getElementById("myChart3");
    var ctx4 = document.getElementById("myChart4");
    var ctx5 = document.getElementById("myChart5");
    var ctx6 = document.getElementById("myChart6");
    
    /**
     *  加载票据
     */
    function loadStatistical(){
    	var startDate = $("#startDate").val();
    	var endDate = $("#endDate").val();
    	
    	var params = {memberId:memberId,start:startDate,end:endDate};
    	var url = '${bootAppPath}/draftrecord/chart';
    	
    	var data = bootPost(url,params);
    	if(data != null){
    		console.log(data);
    		if(data.data.response == 'success'){
    			var arr1 = new Array();
    			var arr2 = new Array();
    			var arr3 = new Array();
    			var arr4 = new Array();
    			var arr5 = new Array();
    			var arr6 = new Array();
    			var labels = ['国股','小商','外资','农商','农合','农信','村镇','大商'];
    			var color = ['#36a2eb','#cc65fe' , '#ffce56', '#fff000','#ffceff','#36ffff','#cfffff'];
    			
    			if(data.data.data.out != null){
   					if(data.data.data.out.t1 != null){
   						arr1[0] = data.data.data.out.t1.txje;
   						arr2[0] = data.data.data.out.t1.txlx;
       				}else{
       					arr1[0] = null;
       					arr2[0] = null;
       				}
   					
   					if(data.data.data.out.t2 != null){
   						arr1[1] = data.data.data.out.t2.txje;
   						arr2[1] = data.data.data.out.t2.txlx;
       				}else{
       					arr1[1] = null;
       					arr2[1] = null;
       				}
   					
   					if(data.data.data.out.t3 != null){
   						arr1[2] = data.data.data.out.t3.txje;
   						arr2[2] = data.data.data.out.t3.txlx;
       				}else{
       					arr1[2] = null;
       					arr2[2] = null;
       				}
   					
   					if(data.data.data.out.t4 != null){
   						arr1[3] = data.data.data.out.t4.txje;
   						arr2[3] = data.data.data.out.t4.txlx;
       				}else{
       					arr1[3] = null;
       					arr2[3] = null;
       				}
   					
   					if(data.data.data.out.t5 != null){
   						arr1[4] = data.data.data.out.t5.txje;
   						arr2[4] = data.data.data.out.t5.txlx;
       				}else{
       					arr1[4] = null;
       					arr2[4] = null;
       				}
   					
   					if(data.data.data.out.t6 != null){
   						arr1[5] = data.data.data.out.t6.txje;
   						arr2[5] = data.data.data.out.t6.txlx;
       				}else{
       					arr1[5] = null;
       					arr2[5] = null;
       				}
   					
   					if(data.data.data.out.t7 != null){
   						arr1[6] = data.data.data.out.t7.txje;
   						arr2[6] = data.data.data.out.t7.txlx;
       				}else{
       					arr1[6] = null;
       					arr2[6] = null;
       				}
   					
   					if(data.data.data.out.t8 != null){
   						arr1[7] = data.data.data.out.t8.txje;
   						arr2[7] = data.data.data.out.t8.txlx;
       				}else{
       					arr1[7] = null;
       					arr2[7] = null;
       				}
    			}
    			var datas = arr1;
    			var pieData = {
   			        datasets: [{
   			            data: datas, //单个区块数值占比
   			            backgroundColor: color //单个区块对应颜色
   			        }],
   			        labels: labels //单个区块名字
   			    };
    			
    			var myDoughnutChart1 = new Chart(ctx1, {
    		        type: 'pie',
    		        data: pieData
    		    });
    			
    			datas = arr2;
    			pieData = {
   			        datasets: [{
   			            data: datas, //单个区块数值占比
   			            backgroundColor: color //单个区块对应颜色
   			        }],
   			        labels: labels //单个区块名字
				};
        			
       			var myDoughnutChart2 = new Chart(ctx2, {
       		        type: 'pie',
       		        data: pieData
       		    });
       			
    			if(data.data.data.hold != null){
    				var hold = data.data.data.hold;
   					if(hold.t1 != null && hold.t1.allmoney != null){
   						arr3[0] = hold.t1.allmoney;
       				}else{
       					arr3[0] = null;
       				}
   					
   					if(hold.t2 != null && hold.t2.allmoney != null){
   						arr3[1] = hold.t2.allmoney;
       				}else{
       					arr3[1] = null;
       				}
   					
   					if(hold.t3 != null && hold.t3.allmoney != null){
   						arr3[2] = hold.t3.allmoney;
       				}else{
       					arr3[2] = null;
       				}
   					
   					if(hold.t4 != null && hold.t4.allmoney != null){
   						arr3[3] = hold.t4.allmoney;
       				}else{
       					arr3[3] = null;
       				}
   					
   					if(hold.t5 != null && hold.t5.allmoney != null){
   						arr3[4] = hold.t5.allmoney;
       				}else{
       					arr3[4] = null;
       				}
   					
   					if(hold.t6 != null && hold.t6.allmoney != null){
   						arr3[5] = hold.t6.allmoney;
       				}else{
       					arr3[5] = null;
       				}
   					
   					if(hold.t7 != null && hold.t7.allmoney != null){
   						arr3[6] = hold.t7.allmoney;
       				}else{
       					arr3[6] = null;
       				}
   					
   					if(hold.t8 != null && hold.t8.allmoney != null){
   						arr3[7] = hold.t8.allmoney;
       				}else{
       					arr3[7] = null;
       				}
    			}
    			datas = arr3;
    			var pieData = {
   			        datasets: [{
   			            data: datas, //单个区块数值占比
   			            backgroundColor: color //单个区块对应颜色
   			        }],
   			        labels: labels //单个区块名字
   			    };
    			
    			var myDoughnutChart3 = new Chart(ctx3, {
    		        type: 'pie',
    		        data: pieData
    		    });
    			
    			if(data.data.data.in != null){
    				var _in = data.data.data.in;
   					if(_in.t1 != null && _in.t1.allmoney != null){
   						arr4[0] = _in.t1.allmoney;
       				}else{
       					arr4[0] = null;
       				}
   					
   					if(_in.t2 != null && _in.t2.allmoney != null){
   						arr4[1] = _in.t2.allmoney;
       				}else{
       					arr4[1] = null;
       				}
   					
   					if(_in.t3 != null && _in.t3.allmoney != null){
   						arr4[2] = _in.t3.allmoney;
       				}else{
       					arr4[2] = null;
       				}
   					
   					if(_in.t4 != null && _in.t4.allmoney != null){
   						arr4[3] = _in.t4.allmoney;
       				}else{
       					arr4[3] = null;
       				}
   					
   					if(_in.t5 != null && _in.t5.allmoney != null){
   						arr4[4] = _in.t5.allmoney;
       				}else{
       					arr4[4] = null;
       				}
   					
   					if(_in.t6 != null && _in.t6.allmoney != null){
   						arr4[5] = _in.t6.allmoney;
       				}else{
       					arr4[5] = null;
       				}
   					
   					if(_in.t7 != null && _in.t7.allmoney != null){
   						arr4[6] = _in.t7.allmoney;
       				}else{
       					arr4[6] = null;
       				}
   					
   					if(_in.t8 != null && _in.t8.allmoney != null){
   						arr4[7] = _in.t8.allmoney;
       				}else{
       					arr4[7] = null;
       				}
    			}
    			datas = arr4;
    			var pieData = {
   			        datasets: [{
   			            data: datas, //单个区块数值占比
   			            backgroundColor: color //单个区块对应颜色
   			        }],
   			        labels: labels //单个区块名字
   			    };
    			
    			var myDoughnutChart4 = new Chart(ctx4, {
    		        type: 'pie',
    		        data: pieData
    		    });
    			
    			if(data.data.data.recordType != null){
    				var recordType = data.data.data.recordType;
   					if(recordType.in != null &&recordType.in != null){
   						arr5[0] = recordType.in;
       				}else{
       					arr5[0] = null;
       				}
   					if(recordType.pledge != null &&recordType.pledge != null){
   						arr5[1] = recordType.pledge;
       				}else{
       					arr5[1] = null;
       				}
   					if(recordType.hold != null &&recordType.hold != null){
   						arr5[2] = recordType.hold;
       				}else{
       					arr5[2] = null;
       				}
   					if(recordType.out != null &&recordType.out != null){
   						arr5[3] = recordType.out;
       				}else{
       					arr5[3] = null;
       				}
   					
    			}
    			datas = arr5;
    			labels = ['待入账','质押中','持有中','已出库'];
    			color = ['#36a2eb','#cc65fe' , '#ffceff', '#fff000'];
    			var pieData = {
   			        datasets: [{
   			            data: datas, //单个区块数值占比
   			            backgroundColor: color //单个区块对应颜色
   			        }],
   			        labels: labels //单个区块名字
   			    };
    			
    			var myDoughnutChart5 = new Chart(ctx5, {
    		        type: 'pie',
    		        data: pieData
    		    });
    			
    			if(data.data.data.draftType != null){
    				var draftType = data.data.data.draftType;
   					if(draftType.yz != null &&draftType.yz != null){
   						arr6[0] = draftType.yz;
       				}else{
       					arr6[0] = null;
       				}
   					if(draftType.yd != null &&draftType.yd != null){
   						arr6[1] = draftType.yd;
       				}else{
       					arr6[1] = null;
       				}
   					if(draftType.sz != null &&draftType.sz != null){
   						arr6[2] = draftType.sz;
       				}else{
       					arr6[2] = null;
       				}
   					if(draftType.sd != null &&draftType.sd != null){
   						arr6[3] = draftType.sd;
       				}else{
       					arr6[3] = null;
       				}
   					
    			}
    			datas = arr6;
    			labels = ['银纸 ','银电',' 商纸','商电 '];
    			color = ['#36a2eb','#cc65fe' , '#ffceff', '#fff000'];
    			var pieData = {
   			        datasets: [{
   			            data: datas, //单个区块数值占比
   			            backgroundColor: color //单个区块对应颜色
   			        }],
   			        labels: labels //单个区块名字
   			    };
    			
    			var myDoughnutChart6 = new Chart(ctx6, {
    		        type: 'pie',
    		        data: pieData
    		    });
    		}else{
    			alert(data.data.msg);
    		}
    	};
    };

    /**
    * 查看不同时间
    */
    $(".timeDate").click(function(){
    	var orderTabBtn = $("input:radio[name='orderTabBtn']:checked").val();//
    	var day ;
    	if(orderTabBtn == 0){
    		day = 30;
    	}else if(orderTabBtn == 1){
    		day = 90;
    	}else if(orderTabBtn == 2){
    		day = 180;
    	}else if(orderTabBtn == 3){
    		day = 360;
    	}
    	
    	var n = Date.parse(new Date());
        var now = new Date(n - day * 24 * 60 * 60 * 1000);
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
    	$("#startDate").val(fullyear+"-"+month+"-"+date);
    	
    	var startDate = $("#startDate").val();
    	var endDate = $("#endDate").val();

    	$(".time").html(startDate+"~~"+endDate);
    	
    	loadStatistical();
    });
    
    /* 关闭弹窗 */
    $("#closeBtn , .closeBtn").on("click",function(){
        $("#windowTitle").html("");
        $("#maskWindow").hide();
        $(".contract").addClass("none");
        $("#addWindow").hide();
        $("#jiluWindow").hide();
        $("#addAllWindow").hide();
    });
    
    /**
     *导出excel弹窗
     */
    $("#excel").click(function(){
    	$("#maskWindow").show();
    	$(".contract").removeClass("none");
    	$("#windowTitle").html("导出excel");
    });
    
    /**
     *导出excel
     */
    function excel(){
    	var startDate = $("#startDate").val();
    	var endDate = $("#endDate").val();
    	var email = $("#email").val();
    	
    	var params = {memberId:memberId,start:startDate,end:endDate,email:email};
    	var url = '${bootAppPath}/draftrecord/export/email';
    	
    	var data = bootPost(url,params);
    	if(data != null){
    		console.log(data);
    		if(data.data.response == 'success'){
    			alert("导出成功");
				$("#maskWindow").hide();
				$(".contract").addClass("none");
    		}else{
    			alert(data.data.msg);
    		}
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