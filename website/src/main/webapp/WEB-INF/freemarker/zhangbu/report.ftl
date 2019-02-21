[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家-票据管理-查看报表']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
[@main.header currentmenu='1' topindex='4'/]

<div class="w1200 bc mt20 mb20">
    <div class="w1198 bae0e0e0 h83 bcwhite">
        <div class="fl f24 fb ml20 lh83 h83">票据管理统计表</div>
        <div class="fr w102 h58 bcwhite bae0e0e0 f12 tc c3366cc mt10 mr30 pb10">
            <a href="${basePath}/zhangbu/add"><img src="${image_url}/website/manage/add.png" width="20" height="20" class="mt10 bc"></a>
            <div class="mt8">新增票据</div>
        </div>
        <div class="cb"></div>
    </div>
    <div class="w1198 mt20 bae0e0e0 bcwhite">
        <ul class="w1198 h52 f16 c2d2d2d lh50 bbe0e0e0 tc manageTab">
            <li class="w250 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="manageTab" id="manage1" class="none"><a href="#" class="cd43c33" onclick="loadData(1)"><label for="manage1" class="dsb cp manageTab_css">最近一月</label></a>
            </li>
            <li class="w250 lh50 fl bre0e0e0">
                <input type="radio" name="manageTab" id="manage2" class="none"><a href="#" class="c2d2d2d" onclick="loadData(2)"><label for="manage2" class="dsb cp manageTab_css">最近三月</label></a>
            </li>
            <li class="w250 lh50 fl bre0e0e0">
                <input type="radio" name="manageTab" id="manage3" class="none"><a href="#" class="c2d2d2d" onclick="loadData(3)"><label for="manage3" class="dsb cp manageTab_css">最近半年</label></a>
            </li>
            <li class="w250 lh50 fl bre0e0e0"> 
                <input type="radio" name="manageTab" id="manage4" class="none"><a href="#" class="c2d2d2d" onclick="loadData(4)"><label for="manage4" class="dsb cp manageTab_css">最近一年</label></a>
            </li>
        </ul>
        <div class="w h60 bcwhite">
            <div class="fl mt16 lh27">
                <div class="fl ml60">从</div>
                <input type="text" class="fl w100 h27 lh27 bae0e0e0 ti8 ml20" id="start" name="start" readonly="readonly">
                <label for="start" class="fl rili w30 h27 ml10"></label>
                <div class="fl ml20">至</div>
                <input type="text" class="fl w100 h27 lh27 bae0e0e0 ti8 ml20" id="end" name="end" readonly="readonly">
                <label for="end" class="fl rili w30 h27 ml10"></label>
            </div>
            <input type="button" class="w110 h34 b0 br3 cwhite tc lh34 bce84c3d f14 ml60 mt13 cp" value="确认" onclick="loadData(0)">
        </div>
    </div>
    <div class="w1198 h1000 bae0e0e0 mt20 bcwhite">
        <div class="fl w331 tc mt30 ml170">
            <div class="f24 fb">银票贴现总金额</div>
            <div class="f14 mt10 times"></div>
            <div class="w300 h300 bae0e0e0 mt20 ml15" id="amount"></div>
            <div class="w331 h50 bae0e0e0 bcf9f9f9 mt30 tc f18 lh50">
                <div class="fl c717583 ml50">总金额：</div>
                <div class="fl fb"><span class="ce84c3d" id="allprice">0</span>万元</div>
            </div>
            <div class="w331 bte0e0e0 ble0e0e0 bre0e0e0 mt30 lh50 tc">
                <div class="h50 bcf9f9f9 bbe0e0e0 f14">
                    <div class="fl w81">承兑行</div>
                    <div class="fl w140">总金额</div>
                    <div class="fl w110">占比</div>
                </div>
                <ul class="f16" id="allprice1">
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">国股</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="guogu">0万元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">小商</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="xiaoshang">0万元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">外资</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="waizi">0万元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">农商</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="nongshang">0万元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">农合</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="nonghe">0万元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">农信</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="nongxin">0万元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">村镇</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="cunzhen">0万元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">大商</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="dashang">0万元</div>
                        <div class="fl w108">0%</div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="fr w331 tc mt30 mr170">
            <div class="f24 fb">银票贴现利息</div>
            <div class="f14 mt10 times"></div>
            <div class="w300 h300 bae0e0e0 mt20 ml15" id="amount1"></div>
            <div class="w331 h50 bae0e0e0 bcf9f9f9 mt30 tc f18 lh50">
                <div class="fl c717583 ml50">累计利息：</div>
                <div class="fl fb"><span class="ce84c3d" id="tiexianlixi">0</span>元</div>
            </div>
            <div class="w331 bte0e0e0 ble0e0e0 bre0e0e0 mt30 lh50 tc">
                <div class="h50 bcf9f9f9 bbe0e0e0 f14">
                    <div class="fl w81">承兑行</div>
                    <div class="fl w140">累计利息</div>
                    <div class="fl w110">占比</div>
                </div>
                <ul class="f16" id="tiexianlixi1">
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">国股</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="guogu1">0元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">小商</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="xiaoshang1">0元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">外资</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="waizi1">0元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">农商</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="nongshang1">0元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">农合</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="nonghe1">0元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">农信</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="nongxin1">0元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">村镇</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="cunzhen1">0元</div>
                        <div class="fl w108">0%</div>
                    </li>
                    <li class="w h50 bbe0e0e0">
                        <div class="fl w81 bre0e0e0">大商</div>
                        <div class="fl w140 bre0e0e0 ti25 tl" id="dashang1">0元</div>
                        <div class="fl w108">0%</div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
[@main.right /]

<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${pluPath}/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${pluPath}/highcharts/modules/exporting.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    wwwPath = "${basePath}";
	actionLog(wwwPath,"action131");//统计
});

/*
 * 时间切换
 */
$(".manageTab_css").click(function(){
	$(".manageTab li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
	$(".manageTab li").removeClass("bbd43c33");
	$(".manageTab li").children("label").removeClass("f20");
	$(this).parent().prev().attr("checked",true);
    $(this).parents("li").addClass("bbd43c33");
    $(this).parents("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
});

$(function () {
	loadData(1);
})
//加载数据
function loadData(num){
	var start = $("#start").val();
    var end = $("#end").val();
    $.ajax({
    	url:"${basePath}/zhangbu/tongji",
    	type:"POST",
    	data:{"type":num,"start":start,"end":end},
    	dataType:"json",
    	success:function(data){
    		if(data.response == "success"){
    			$("#start").val(data.start);
    			$("#end").val(data.end);
    			$(".times").text(data.start1+"-"+data.end1);
    			var tiexianlixi = data.tiexianlixi;
    			var allprice = data.allprice;
    			var guogu = 0;
                var dashang = 0;
                var xiaoshang = 0;
                var waizi = 0;
                var nongshang = 0;
                var nonghe = 0;
                var nongxin = 0;
                var cunzhen = 0;
                var guogu1 = 0;
                var dashang1 = 0;
                var xiaoshang1 = 0;
                var waizi1 = 0;
                var nongshang1 = 0;
                var nonghe1 = 0;
                var nongxin1 = 0;
                var cunzhen1 = 0;
    			if(tiexianlixi!=null){
    				$("#tiexianlixi").text(tiexianlixi);
    			}else{
    				$("#tiexianlixi").text(0);
    				$("#guogu1").text("0元");
    				$("#guogu1").next().text("0%");
    				$("#dashang1").text("0元");
    				$("#dashang1").next().text("0%");
    				$("#xiaoshang1").text("0元");
    				$("#xiaoshang1").next().text("0%");
    				$("#waizi1").text("0元");
    				$("#waizi1").next().text("0%");
    				$("#nongshang1").text("0元");
    				$("#nongshang1").next().text("0%");
    				$("#nonghe1").text("0元");
    				$("#nonghe1").next().text("0%");
    				$("#nongxin1").text("0元");
    				$("#nongxin1").next().text("0%");
    				$("#cunzhen1").text("0元");
    				$("#cunzhen1").next().text("0%");
    			}
    			if(allprice!=null){
    				$("#allprice").text(allprice);
    			}else{
    				$("#allprice").text(0);
    				$("#guogu").text("0万元");
    				$("#guogu").next().text("0%");
    				$("#dashang").text("0万元");
    				$("#dashang").next().text("0%");
    				$("#xiaoshang").text("0万元");
    				$("#xiaoshang").next().text("0%");
    				$("#waizi").text("0万元");
    				$("#waizi").next().text("0%");
    				$("#nongshang").text("0万元");
    				$("#nongshang").next().text("0%");
    				$("#nonghe").text("0万元");
    				$("#nonghe").next().text("0%");
    				$("#nongxin").text("0万元");
    				$("#nongxin").next().text("0%");
    				$("#cunzhen").text("0万元");
    				$("#cunzhen").next().text("0%");
    			}
    			var type1 = data.data;
    			for(var i=0;i<data.data.length;i++){
    				switch (data.data[i].type1) {
					case 1:
						$("#guogu").text(data.data[i].allprice+"万元");
						guogu = Number(data.data[i].allprice)/allprice;
						$("#guogu").next().text((guogu*100).toFixed(1)+"%");
						if(data.data[i].tiexianlixi==null || data.data[i].tiexianlixi==''){
							$("#guogu1").text(0+"元");
						}else{
							$("#guogu1").text(data.data[i].tiexianlixi+"元")
							guogu1 = Number(data.data[i].tiexianlixi)/tiexianlixi;
							$("#guogu1").next().text((guogu1*100).toFixed(1)+"%");
						}
						break;
					case 2:
						$("#xiaoshang").text(data.data[i].allprice+"万元");
						xiaoshang = Number(data.data[i].allprice)/allprice;
						$("#xiaoshang").next().text((xiaoshang*100).toFixed(1)+"%");
						if(data.data[i].tiexianlixi==null || data.data[i].tiexianlixi==''){
							$("#xiaoshang1").text(0+"元");
						}else{
							$("#xiaoshang1").text(data.data[i].tiexianlixi+"元")
							xiaoshang1 = Number(data.data[i].tiexianlixi)/tiexianlixi;
							$("#xiaoshang1").next().text((xiaoshang1*100).toFixed(1)+"%");
						}
						break;
					case 3:
						$("#waizi").text(data.data[i].allprice+"万元");
						waizi = Number(data.data[i].allprice)/allprice;
						$("#waizi").next().text((waizi*100).toFixed(1)+"%");
						if(data.data[i].tiexianlixi==null || data.data[i].tiexianlixi==''){
							$("#waizi1").text(0+"元");
						}else{
							$("#waizi1").text(data.data[i].tiexianlixi+"元")
							waizi1 = Number(data.data[i].tiexianlixi)/tiexianlixi;
							$("#waizi1").next().text((waizi1*100).toFixed(1)+"%");
						}
						break;
					case 4:
						$("#nongshang").text(data.data[i].allprice+"万元");
						nongshang = Number(data.data[i].allprice)/allprice;
						$("#nongshang").next().text((nongshang*100).toFixed(1)+"%");
						if(data.data[i].tiexianlixi==null || data.data[i].tiexianlixi==''){
							$("#nongshang1").text(0+"元");
						}else{
							$("#nongshang1").text(data.data[i].tiexianlixi+"元")
							nongshang1 = Number(data.data[i].tiexianlixi)/tiexianlixi;
							$("#nongshang1").next().text((nongshang1*100).toFixed(1)+"%");
						}
						break;
					case 5:
						$("#nonghe").text(data.data[i].allprice+"万元");
						nonghe = Number(data.data[i].allprice)/allprice;
						$("#nonghe").next().text((nonghe*100).toFixed(1)+"%");
						if(data.data[i].tiexianlixi==null || data.data[i].tiexianlixi==''){
							$("#nonghe1").text(0+"元");
						}else{
							$("#nonghe1").text(data.data[i].tiexianlixi+"元")
							nonghe1 = Number(data.data[i].tiexianlixi)/tiexianlixi;
							$("#nonghe1").next().text((nonghe1*100).toFixed(1)+"%");
						}
						break;
					case 6:
						$("#nongxin").text(data.data[i].allprice+"万元");
						nongxin = Number(data.data[i].allprice)/allprice;
						$("#nongxin").next().text((nongxin*100).toFixed(1)+"%");
						if(data.data[i].tiexianlixi==null || data.data[i].tiexianlixi==''){
							$("#nongxin1").text(0+"元");
						}else{
							$("#nongxin1").text(data.data[i].tiexianlixi+"元")
							nongxin1 = Number(data.data[i].tiexianlixi)/tiexianlixi;
							$("#nongxin1").next().text((nongxin1*100).toFixed(1)+"%");
						}
						break;
					case 7:
						$("#cunzhen").text(data.data[i].allprice+"万元");
						cunzhen = Number(data.data[i].allprice)/allprice;
						$("#cunzhen").next().text((cunzhen*100).toFixed(1)+"%");
						if(data.data[i].tiexianlixi==null || data.data[i].tiexianlixi==''){
							$("#cunzhen1").text(0+"元");
						}else{
							$("#cunzhen1").text(data.data[i].tiexianlixi+"元")
							cunzhen1 = Number(data.data[i].tiexianlixi)/tiexianlixi;
							$("#cunzhen1").next().text((cunzhen1*100).toFixed(1)+"%");
						}
						break;
					case 8:
						$("#dashang").text(data.data[i].allprice+"万元");
						dashang = Number(data.data[i].allprice)/allprice;
						$("#dashang").next().text((dashang*100).toFixed(1)+"%");
						if(data.data[i].tiexianlixi==null || data.data[i].tiexianlixi==''){
							$("#dashang1").text(0+"元");
						}else{
							$("#dashang1").text(data.data[i].tiexianlixi+"元")
							dashang1 = Number(data.data[i].tiexianlixi)/tiexianlixi;
							$("#dashang1").next().text((dashang1*100).toFixed(1)+"%");
						}
						break;
					default:
						break;
					}
    			}
    			if(allprice>0){
    				$("#amount").removeClass("none");
    				$('#amount').highcharts({
    			        chart: {
    			            plotBackgroundColor: null,
    			            plotBorderWidth: null,
    			            plotShadow: false
    			        },
    			        title: {
    			            text: '银票贴现总金额'+allprice+"万元"
    			        },
    			        tooltip: {
    			            pointFormat: '<b>{point.percentage:.1f}%</b>'
    			        },
    			        credits: {
    			            enabled:false
    			  		},
    			  		exporting: {
    			            enabled:false
    					},
    			        plotOptions: {
    			            pie: {
    			                allowPointSelect: true,
    			                cursor: 'pointer',
    			                dataLabels: {
    			                    enabled: false
    			                },
    			                showInLegend: true
    			            }
    			        },
    			        series: [{
    			            type: 'pie',
    			            data: [
    			                ['国股',guogu],
    			                {
    			                    name: '小商',
    			                    y: xiaoshang,
    			                    sliced: true,
    			                    selected: true
    			                },
    			                ['外资',waizi],
    			                ['农商',nongshang],
    			                ['农合',nonghe],
    			                ['农信',nongxin],
    			                ['村镇',cunzhen],
    			                ['大商',dashang]
    			            ]
    			        }]
    			    });
    			}else{
    				$("#amount").addClass("none");
    			}
    			if(tiexianlixi>0){
    				$("#amount1").removeClass("none");
    				$('#amount1').highcharts({
    			        chart: {
    			            plotBackgroundColor: null,
    			            plotBorderWidth: null,
    			            plotShadow: false
    			        },
    			        title: {
    			            text: '银票贴现总利息'+tiexianlixi+"元"
    			        },
    			        tooltip: {
    			            pointFormat: '<b>{point.percentage:.1f}%</b>'
    			        },
    			        credits: {
    			            enabled:false
    			  		},
    			  		exporting: {
    			            enabled:false
    					},
    			        plotOptions: {
    			            pie: {
    			                allowPointSelect: true,
    			                cursor: 'pointer',
    			                dataLabels: {
    			                    enabled: false
    			                },
    			                showInLegend: true
    			            }
    			        },
    			        series: [{
    			            type: 'pie',
    			            data: [
								['国股',guogu1],
    			                {
    			                    name: '小商',
    			                    y: xiaoshang1,
    			                    sliced: true,
    			                    selected: true
    			                },
    			                ['外资',waizi1],
    			                ['农商',nongshang1],
    			                ['农合',nonghe1],
    			                ['农信',nongxin1],
    			                ['村镇',cunzhen1],
    			                ['大商',dashang1]
    			            ]
    			        }]
    			    });
    			}else{
    				$("#amount1").addClass("none");
    			}
    		}else{
    			alert(data.msg);
    		}
    	}
    });
}

//日历
!function(){
    laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
}();
//日期范围限制
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
laydate({
    elem: '#first',
    format: 'YYYY-MM-DD',
    festival: true, //显示节日
    choose: function(datas){ //选择日期完毕的回调
    }
});

var now = new Date();
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
$("#start").text(fullyear+"-"+month+"-"+date);

var begintimelong = Date.parse(new Date());
var endtime12 = new Date(begintimelong + 30 * 24 * 60 * 60 * 1000);
var endfullyear = endtime12.getFullYear();
//获取完整的年份(4位,1970-????)
var endmonth = endtime12.getMonth() + 1;
//获取当前月份(0-11,0代表1月)
var enddate = endtime12.getDate();
//获取当前日(1-31)
if (endmonth < 10) {
	endmonth = "0" + endmonth;
}
if (enddate < 10) {
	enddate = "0" + enddate;
}
$("#end").text(endfullyear+"-"+endmonth+"-"+enddate);
</script>
<!--foot-->
[@main.footer/]
[/@main.body]