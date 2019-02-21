[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='详情']
[@main.header currentmenu='1' topindex='2'/]
<link href="${cssPath}/website/daxiaopiao-190109/reste.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/common.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/reviewTab.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/review.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/noteDetail.css" rel="stylesheet">

<script type="text/javascript" src="${comPath}/jquery-1.8.3.min.js"></script>

	<div class="reviewWarp">
		<!-- 切换 -->
		<div class="reviewTab clearfix">
			<div onclick="enterprise();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/shehe.png" alt="企业预审">
				<p>企业预审</p>
			</div>
			<div onclick="bigSmall();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/dapiao.png" alt="大票换小票">
				<p>大票换小票</p>
			</div>
			<div onclick="smallBig();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/xiaopiao.png" alt="小票换大票">
				<p>小票换大票</p>
			</div>
			<div onclick="order();">
				<img src="https://img.utiexian.com/website/daxiaopiao-img/review/dingdan.png" alt="换票订单">
				<p>换票订单</p>
			</div>
		</div>
		<!-- 切换 end -->

		<div class="noteDetailCon">
			<div class="detailTitle">
				换票详情 <a href="javascript:history.back(-1)" class="text-primary click">返回</a>
			</div>
			<div class="detailCon">
				<div class="detailTable">
					<table>
						<thead>
							<tr>
								<th>票面金额</th>
								<th>承兑行</th>
								<th>开票日期</th>
								<th>到期日</th>
								<th>剩余天数</th>
								<th>票面图片</th>
							</tr>
						</thead>
						<tbody id="content">
							
						</tbody>
					</table>
				</div>

				<div class="detailResult">
					<div class="resultLabel">审核结果：</div>

					<div class="resultTishi">
						<img id="loadingIcon" src="https://img.utiexian.com/website/daxiaopiao-img/review/dengdai.png">
						<span id="loadingTitle"></span>
					</div>
				</div>

			</div>
		</div>
	</div>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/javascript" src="${basePath}/js/layer/layer.js"></script>
<script type="text/x-handlebars-template" id="BIGLIST">
{{#each detail}}
	<tr>
		<td>{{money}}</td>
		<td>{{bank}}</td>
		<td>{{formatDate printDate}}</td>
		<td>{{formatDate endDate}}</td>
		<td>{{toDays endDate}}</td>
		<td>
			<a href="${imagePath}/{{draftUrl}}" class="text-primary click" target="_blank">查看</a>
		</td>
	</tr>
{{/each}}
</script>
<script type="text/javascript">
/**
*	时间格式化
*/
Handlebars.registerHelper('formatDate', function(date, options) {
    if(date!=null){
    	date = date.replace(/-/g, "/");
        var d = new Date(date);
        return d.format('yyyy-MM-dd');
    }else{
        return "--";
    }
});

/* 
 * 展示剩余天数
 */
Handlebars.registerHelper('toDays', function(endDate, options) {
	if(endDate!=null){
		return surplusDay(endDate);
	}else{
		return "--";
	}
});
	$(function(){
		getInfo();
	});
	
	/**
	* 获取订单详情
	*/
	function getInfo(){
		layer.load(2);
		var id = '${id}';
     	$.ajax({
			url:"${bootAppPath}/draftexchange/get/info",
			type:"POST",
			data:{memberId:memberId,id:id},
			dataType:"json",
			success:function(data){
				layer.closeAll();
				console.log(data);
				if(data.data.response == 'success'){
					var source = $("#BIGLIST").html();
		            var template = Handlebars.compile(source);
		            var html = template(data.data.data);
		            $("#content").html(html);
		            
		            var state = data.data.data.draftExchange.state;
		            
		            if(state == "PENDING"){
		        		$("#loadingTitle").html("审核中，请耐心等待~");
		        	}else if(state == "FAILED"){
		        		$("#loadingTitle").html("不可置换");
		        		$("#loadingIcon").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/shibai.png");
		        	}else if(state == "SUCCESS"){
		        		$("#loadingTitle").html("可置换");
		        		$("#loadingIcon").attr("src","https://img.utiexian.com/website/daxiaopiao-img/review/tongguo.png");
		        	}
				}else{
					layer.closeAll();
					layer.alert(data.data.msg);
				}
			},error:function(json){
				layer.closeAll();
				layer.alert("审核信息失败！");
			}
		});
	}
	
	/**
     * 获取剩余天数
     */
    function surplusDay(end){
    	 var date = new Date();//开始时间
         var seperator1 = "-";
         var year = date.getFullYear();
         var month = date.getMonth() + 1;
         var strDate = date.getDate();
         if (month >= 1 && month <= 9) {
             month = "0" + month;
         }
         if (strDate >= 0 && strDate <= 9) {
             strDate = "0" + strDate;
         }
         var start = year + seperator1 + month + seperator1 + strDate;
    	
    	var date2 = new Date(start);//开始时间
    	var date3 = new Date(end);//结束时间

    	var date4=date3.getTime()-date2.getTime(); //时间差的毫秒数
    	//计算出相差天数
    	var days=Math.floor(date4/(24*3600*1000));
    	if(days==null||days==""){
    		days=0;
    	}
    	return days;
    }
	
    /**
	 *  大小票的跳转
	 */
	function enterprise(){
		$.ajax({
			url:"${bootAppPath}/draftexchangecheck/get/by/memberid",
			type:"POST",
			data:{memberId:memberId},
			dataType:"json",
			success:function(data){
				console.log(data);
				if(data.data.response == 'success'){
					var data = data.data.data;
					if(data!=null&&data!=""){
						window.location.href ="${basePath}/bigsmall/qiye/xiangqing";
					}else{
						window.location.href ="${basePath}/bigsmall/qiye/yushen";
					}
				}else{
					layer.alert(data.data.msg);
				}
			},error:function(json){
				layer.alert("获取信息失败！");
			}
		});
	}
	
	/**
	 *  大换小的跳转
	 */
	function bigSmall(){
		window.location.href ="${basePath}/bigsmall/big";
	}
	
	/**
	 *  小换大的跳转
	 */
	function smallBig(){
		window.location.href ="${basePath}/bigsmall/small";
	}
	
	/**
	 *  大小票的订单的跳转
	 */
	function order(){
		window.location.href ="${basePath}/bigsmall/big/list";
	}
</script>
[/@main.body]