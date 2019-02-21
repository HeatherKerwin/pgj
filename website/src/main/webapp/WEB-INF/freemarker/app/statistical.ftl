<!DOCTYPE html>
<html lang="en">
[#include "/common/setting.ftl"]
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的票据管理-统计分析</title>
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
	<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
</head>
<body>

<div class="w flex flex-direction-column">
    <div class="w flex-direction-column bcwhite pt20 pl10 pr10 lh30">
        <div class="w flex-row flex-direction-row">
            <label class="w100">统计时间段：</label>
            <span >
                <select class="w120 h30 bae0e0e0 br3 select107 pl10 pr20" id="selectTime">
                    <option value="0">最近一月</option>
                    <option value="1">最近三月</option>
                    <option value="2">最近半年</option>
                    <option value="3">最近一年</option>
                </select>
            </span>
        </div>
        <div class="w flex-row flex-direction-row bbe0e0e0 pb10 mt10">
            <label class="w100">具体日期：</label>
            <div class="flex-col-4 time"></div>
        </div>
    </div>

    <div class="w flex-direction-column">
        <!-- 已出库票据 -->
        <div class="w bbe0e0e0 bcwhite pt20 pl10 pr10">
            <div class="bl3_fc4d42 pl10 h24 lh24 tl">已出库票据</div>
            <!-- 贴现金额分布 -->
            <div class="w tc flex-alignItems-center bbe0e0e0 mt10 pb20">
                <div>贴现金额分布</div>
                <div class="cababab f12 mt6 time"></div>
                <!-- 分布饼图 -->
                <canvas id="myChart1"></canvas>
            </div>
            <!-- 贴现金额分布 end -->

            <!-- 贴现利息分布 -->
            <div class="w tc flex-alignItems-center mt10 pb20">
                <div>贴现利息分布</div>
                <div class="cababab f12 mt6 time"></div>
                <!-- 分布饼图 -->
                <canvas id="myChart2"></canvas>
            </div>
            <!-- 贴现利息分布 end -->
        </div>
        <!-- 已出库票据 end -->

        <!-- 持有中票据 -->
        <div class="w bbe0e0e0 bcwhite pt20 pl10 pr10 mt10">
            <!-- 标签 -->
            <div class="bl3_fc4d42 pl10 h24 lh24 tl">持有中票据</div>
            <!-- 标题 -->
            <div class="w tc flex-alignItems-center bbe0e0e0 mt10 pb20">
                <div>银票总金额分布</div>
                <div class="cababab f12 mt6 time"></div>
                <!-- 分布饼图 -->
                <canvas id="myChart3"></canvas>
            </div>

        </div>
        <!-- 持有中票据 end -->

        <!-- 待入账票据 -->
        <div class="w bbe0e0e0 bcwhite pt20 pl10 pr10 mt10">
            <!-- 标签 -->
            <div class="bl3_fc4d42 pl10 h24 lh24 tl">待入账票据</div>
            <!-- 标题 -->
            <div class="w tc flex-alignItems-center bbe0e0e0 mt10 pb20">
                <div>银票总金额分布</div>
                <div class="cababab f12 mt6 time"></div>
                <!-- 分布饼图 -->
                <canvas id="myChart4"></canvas>
            </div>

        </div>
        <!-- 待入账票据 end -->
        
        <!-- 票据状态分布 -->
        <div class="w bbe0e0e0 bcwhite pt20 pl10 pr10 mt10">
            <!-- 标签 -->
            <div class="bl3_fc4d42 pl10 h24 lh24 tl">票据状态分布</div>
            <!-- 标题 -->
            <div class="w tc flex-alignItems-center bbe0e0e0 mt10 pb20">
                <div>待入账&nbsp;&nbsp;质押中&nbsp;&nbsp;持有中&nbsp;&nbsp;已出库</div>
                <div class="cababab f12 mt6 time"></div>
                <!-- 分布饼图 -->
                <canvas id="myChart5"></canvas>
            </div>

        </div>
        <!-- 票据状态分布  end -->
        
        <!-- 票据种类分布 -->
        <div class="w bbe0e0e0 bcwhite pt20 pl10 pr10 mt10">
            <!-- 标签 -->
            <div class="bl3_fc4d42 pl10 h24 lh24 tl">票据种类分布</div>
            <!-- 标题 -->
            <div class="w tc flex-alignItems-center bbe0e0e0 mt10 pb20">
                <div>银电&nbsp;&nbsp;银纸&nbsp;&nbsp;商电&nbsp;&nbsp;商纸</div>
                <div class="cababab f12 mt6 time"></div>
                <!-- 分布饼图 -->
                <canvas id="myChart6"></canvas>
            </div>

        </div>
        <!-- 票据种类分布 end -->
    </div>
</div>

<input type="hidden" value="" id="startDate" />
<input type="hidden" value="" id="endDate" />

<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/common/chartJs/Chart.js"></script>

</body>
<script>
    $(document).ready(function() {
    	loadStatistical();
    });
    
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
	
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	$(".time").html(startDate+"~~"+endDate);

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
    	var memberId= '${memberId}';
    	var startDate = $("#startDate").val();
    	var endDate = $("#endDate").val();
    	
    	var params = {memberId:memberId,start:startDate,end:endDate};
    	var url = '${bootAppPath}/draftrecord/chart';
    	
    	$.ajax({
    		url:url,
    		type:"POST",
    		data:params,
    		dataType:"json",
    		async: false,
    		success:function(data){
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
    		}
    	});
    };

    /**
    * 查看不同时间
    */
    $("#selectTime").change(function(){
    	var selectTime = $("#selectTime").val();//
    	var day ;
    	if(selectTime == 0){
    		day = 30;
    	}else if(selectTime == 1){
    		day = 90;
    	}else if(selectTime == 2){
    		day = 180;
    	}else if(selectTime == 3){
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
</script>
</html>