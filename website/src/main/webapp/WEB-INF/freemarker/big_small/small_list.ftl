[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='小票列表']
[@main.header currentmenu='1' topindex='2'/]
<link href="${cssPath}/website/daxiaopiao-190109/reste.css" rel="stylesheet">
<link href="${pluPath}/data/need/laydate.css" rel="stylesheet">
<link href="${pluPath}/data/skins/yahui/laydate.css" rel="stylesheet" id="LayDateSkin">
<link href="${cssPath}/website/daxiaopiao-190109/common.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/reviewTab.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/review.css" rel="stylesheet">
<link href="${cssPath}/website/verticalTab.css" rel="stylesheet">
<link href="${cssPath}/website/daxiaopiao-190109/note.css" rel="stylesheet">

<script type="text/javascript" src="${comPath}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${jsPath}/verticalTab.js"></script>
<script type="text/javascript" src="${jsPath}/daxiaopiao-190109/noteList.js"></script>

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
		<div>
			<img src="https://img.utiexian.com/website/daxiaopiao-img/review/dingdan.png" alt="换票订单">
			<p>换票订单</p>
		</div>
	</div>
	<!-- 切换 end -->

	<!-- content -->
	<div class="noteWarp clearfix">
		<div class="noteTab">
			<ul class="verticalTab">
				<li onclick="bigList();"><a href="#" class=""> <input type="radio"
						name="verticalTab" value="1" class="verticalTabBtn"
						id="verticalTab1"> <label for="verticalTab1">大票换小票</label>
				</a></li>
				<li><a href="javascript:void(0);" class="checked"> <input type="radio"
						name="verticalTab" value="2" class="verticalTabBtn"
						id="verticalTab2"> <label for="verticalTab2">小票换大票</label>
				</a></li>
			</ul>
		</div>

		<div class="noteCon">
			<!-- 小票 -->
			<div id="xiaopiao" class="page2" style="display: black;">
				<div class="note-table">
					<table>
						<thead>
							<tr>
								<th>总金额</th>
								<th>置换日期</th>
								<th>最早到期日</th>
								<th>最晚到期日</th>
								<th>张数</th>
								<th>审核结果</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>

					<div class="noteList itemTabels" id="content" style="display: black">
						
					</div>
				</div>
			</div>
			<!-- 小票 end -->
			<!-- pages -->
			<div class="pages" id="pager"></div>
			<!-- pages end -->
		</div>
	</div>
</div>
<input type="hidden" value="" id="draftId" />
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/javascript" src="${basePath}/js/layer/layer.js"></script>
<script type="text/x-handlebars-template" id="BIGLIST">
{{#each list}}
	<div class="noteItem">
		<table>
			<tr>
				<td>{{toAllmoney detail}}</td>
				<td>{{formatDate exchange_date}}</td>
				<td>{{formatDate min_endtime}}</td>
				<td>{{formatDate max_endtime}}</td>
				<td>{{toNum detail}}</td>
				<td class="text-success">{{toState state}}</td>
				<td class="text-primary">
					<a class="click text-primary" href="${basePath}/bigsmall/{{id}}/detail">查看</a>丨
					<span class="click" onclick="del('{{id}}')">删除</span>
				</td>
			</tr>
		</table>

		<div class="itemTabel" style="display: none">
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
				<table>
					{{#each detail}}
						<tr>
							<td>{{money}}</td>
							<td>{{bank}}</td>
							<td>{{formatDate printDate}}</td>
							<td>{{formatDate endDate}}</td>
							<td>{{toDays endDate}}</td>
							<td><a class="text-primary click" href="${imagePath}{{draftUrl}}" target="_blank">查看</a></td>
						</tr>
					{{/each}}
				</table>
			</table>
		</div>
		<div class="noteItemIcon" style="transform: rotate(360deg);" onclick="show(this);" data-name="none"></div>
	</div>
{{/each}}
</script>
<script>
/* 
 * 展示总金额
 */
Handlebars.registerHelper('toAllmoney', function(detail, options) {
	if(detail!=null){
		var allmoney=0;
		for (var i = 0; i < detail.length; i++) {
			allmoney += detail[i].money;
		}
		return allmoney;
	}else{
		return "--";
	}
});

/* 
 * 展示总金额
 */
Handlebars.registerHelper('toNum', function(detail, options) {
	if(detail!=null){
		return detail.length;
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
 * 订单状态
 */
Handlebars.registerHelper('toState', function(state, options) {
	if(state == "PENDING"){
		return "待审核";
	}else if(state == "FAILED"){
		return "不可置换";
	}else if(state == "SUCCESS"){
		return "可置换";
	}
});

  $(document).ready(function(){
	  loadDate();
  })
  
  /**
	* 页面初始化 加载数据
	*/
	function loadDate(){
    	layer.load(2);
		var data = {memberId:memberId,type:"S2B"};
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/draftexchange/page/info",
	        pageSize:5,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	layer.closeAll();
	        	console.log(data);
        		var source = $("#BIGLIST").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data.data);
	            $("#content").html(html);
	            
	        },complete: function(){
	        },error:function(data){
	        	layer.closeAll();
	        	layer.alert("获取信息失败");
	        }
	    });
	};
	
	/**
	*
	*/
	function show(name){
		var a = $(name).attr("data-name");
		if(a == "none"){
			$(name).prev().fadeIn();
			$(name).attr("data-name","black");
			$(name).css({'transform':'rotate(180deg)'});
		}else{
			$(name).prev().fadeOut();
			$(name).attr("data-name","none");
			$(name).css({'transform':'rotate(360deg)'});
		}
	}
	
	/**
	* 删除订单
	*/
	function del(id){
		layer.load(2);
		$.ajax({
			url:"${bootAppPath}/draftexchange/del",
			type:"POST",
			data:{memberId:memberId,id:id},
			dataType:"json",
			success:function(data){
				layer.closeAll();
				if(data.data.response == 'success'){
					layer.alert(data.data.msg);
					loadDate();
				}else{
					layer.alert(data.data.msg);
				}
			},error:function(json){
				layer.alert("获取企业信息失败！");
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
	* 跳转到大换小列表
	*/
	function bigList(){
		window.location.href ="${basePath}/bigsmall/big/list";
	}
</script>
[/@main.body]