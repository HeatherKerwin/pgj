[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.shibor]
[@main.header currentmenu='1' topindex="5"/]
 [@main.right /]
<div class="w1200 bc mt20 mb20 bcwhite">
    <div class="w bcf4f4f4 h100 lh100 f36 ti65 fb">Shibor查询</div>
    <div class="w1198 bae0e0e0 h750">
        <div class="wa h40 f18 lh40 mt40 ml200">
            <div class="fl">请选择查询日期：</div>
            <input id="startDate" type="text" class="fl w246 h40 bae0e0e0 bcf5f5f5 f18 ti25" placeholder="请选择查询日期" readonly="readonly">
        </div>
        <div class="w798 f18 bc bae0e0e0 mt20 tc" id="biaotou">
            <p class="fb h34 lh34 bcd43c33 cwhite">
                <span class="fl w266">期限</span>
                <span class="fl w266">Shibor(%)</span>
                <span class="fl w266">涨跌(BP)</span>
            </p>
            <ul class="time1 fl w266 lh70">
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d">O/N</a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d">1W</a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d">2W</a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d">1M</a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d">3M</a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d">6M</a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d">9M</a></li>
                <li><a href="javascript:void(0)" class="c2d2d2d">1Y</a></li>
            </ul>
            <ul class="sh fl w266 lh70">
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d" id="shibor1"></a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d" id="shibor2"></a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d" id="shibor3"></a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d" id="shibor4"></a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d" id="shibor5"></a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d" id="shibor6"></a></li>
                <li class="bbe0e0e0"><a href="javascript:void(0)" class="c2d2d2d" id="shibor7"></a></li>
                <li><a href="javascript:void(0)" class="c2d2d2d" id="shibor8"></a></li>
            </ul>
            <ul class="bp fl w266 lh70">
                <li class="bbe0e0e0 pp1"><a href="javascript:void(0)" class="c2d2d2d" id="updown1"></a></li>
                <li class="bbe0e0e0 pp2"><a href="javascript:void(0)" class="cfa3c3c" id="updown2"></a></li>
                <li class="bbe0e0e0 pp3"><a href="javascript:void(0)" class="c7790fe" id="updown3"></a></li>
                <li class="bbe0e0e0 pp4"><a href="javascript:void(0)" class="c7790fe" id="updown4"></a></li>
                <li class="bbe0e0e0 pp5"><a href="javascript:void(0)" class="c2d2d2d" id="updown5"></a></li>
                <li class="bbe0e0e0 pp6"><a href="javascript:void(0)" class="cfa3c3c" id="updown6"></a></li>
                <li class="bbe0e0e0 pp7"><a href="javascript:void(0)" class="c2d2d2d" id="updown7"></a></li>
                <li class="pp8"><a href="javascript:void(0)" class="c2d2d2d" id="updown8"></a></li>
            </ul>
            <div class="cb"></div>
        </div><!--/rate-->
    </div>
</div>

  <script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		wwwPath = "${basePath}";
		actionLog(wwwPath,"action14");
		loadData();
	});
	//查询
	function search() {
		var day=$("#startDate").val();
		$.ajax({
			url: '${basePath}/homepage/shibor',
			type: 'POST',
			dataType: 'json',
			data: {'day':day},
			success: function(data){
				if(data.response=="success"){
					$("#biaotou").show();
					var data=data.data;
					$("#shibor1").text(data.shibor1);
					$("#shibor2").text(data.shibor2);
					$("#shibor3").text(data.shibor3);
					$("#shibor4").text(data.shibor4);
					$("#shibor5").text(data.shibor5);
					$("#shibor6").text(data.shibor6);
					$("#shibor7").text(data.shibor7);
					$("#shibor8").text(data.shibor8);
					$("#updown1").text(data.updown1);
					$("#updown2").text(data.updown2);
					$("#updown3").text(data.updown3);
					$("#updown4").text(data.updown4);
					$("#updown5").text(data.updown5);
					$("#updown6").text(data.updown6);
					$("#updown7").text(data.updown7);
					$("#updown8").text(data.updown8);
					if(data.updown1.indexOf("-")==-1){
						$(".pp1").addClass("bp22");$("#updown1").addClass("cfa3c3c");
					}else{
						$(".pp1").addClass("bp11");$("#updown1").addClass("c7790fe");
					}
					if(data.updown2.indexOf("-")==-1){
						$(".pp2").addClass("bp22");$("#updown2").addClass("cfa3c3c");
					}else{
						$(".pp2").addClass("bp11");$("#updown2").addClass("c7790fe");
					}
					if(data.updown3.indexOf("-")==-1){
						$(".pp3").addClass("bp22");$("#updown3").addClass("cfa3c3c");
					}else{
						$(".pp3").addClass("bp11");$("#updown3").addClass("c7790fe");
					}
					if(data.updown4.indexOf("-")==-1){
						$(".pp4").addClass("bp22");$("#updown4").addClass("cfa3c3c");
					}else{
						$(".pp4").addClass("bp11");$("#updown4").addClass("c7790fe");
					}
					if(data.updown5.indexOf("-")==-1){
						$(".pp5").addClass("bp22");$("#updown5").addClass("cfa3c3c");
					}else{
						$(".pp5").addClass("bp11");$("#updown5").addClass("c7790fe");
					}
					if(data.updown6.indexOf("-")==-1){
						$(".pp6").addClass("bp22");$("#updown6").addClass("cfa3c3c");
					}else{
						$(".pp6").addClass("bp11");$("#updown6").addClass("c7790fe");
					}
					if(data.updown7.indexOf("-")==-1){
						$(".pp7").addClass("bp22");$("#updown7").addClass("cfa3c3c");
					}else{
						$(".pp7").addClass("bp11");$("#updown7").addClass("c7790fe");
					}
					if(data.updown8.indexOf("-")==-1){
						$(".pp8").addClass("bp22");$("#updown8").addClass("cfa3c3c");
					}else{
						$(".pp8").addClass("bp11");$("#updown8").addClass("c7790fe");
					}
				}else{
					alert("当日无数据，请重新选择");
					$("#biaotou").hide();
				}
				 
			}
		});
		
	}

	function loadData(){
		$.ajax({
			url: '${basePath}/homepage/shibor/init',
			type: 'POST',
			dataType: 'json',
			data: {},
			success: function(data){
				if(data.response=="success"){
					var data=data.data;
					$("#startDate").val(data.day);
					$("#shibor1").text(data.shibor1);
					$("#shibor2").text(data.shibor2);
					$("#shibor3").text(data.shibor3);
					$("#shibor4").text(data.shibor4);
					$("#shibor5").text(data.shibor5);
					$("#shibor6").text(data.shibor6);
					$("#shibor7").text(data.shibor7);
					$("#shibor8").text(data.shibor8);
					$("#updown1").text(data.updown1);
					$("#updown2").text(data.updown2);
					$("#updown3").text(data.updown3);
					$("#updown4").text(data.updown4);
					$("#updown5").text(data.updown5);
					$("#updown6").text(data.updown6);
					$("#updown7").text(data.updown7);
					$("#updown8").text(data.updown8);
					if(data.updown1.indexOf("-")==-1){
						$(".pp1").addClass("bp22");$("#updown1").addClass("cfa3c3c");
					}else{
						$(".pp1").addClass("bp11");$("#updown1").addClass("c7790fe");
					}
					if(data.updown2.indexOf("-")==-1){
						$(".pp2").addClass("bp22");$("#updown2").addClass("cfa3c3c");
					}else{
						$(".pp2").addClass("bp11");$("#updown2").addClass("c7790fe");
					}
					if(data.updown3.indexOf("-")==-1){
						$(".pp3").addClass("bp22");$("#updown3").addClass("cfa3c3c");
					}else{
						$(".pp3").addClass("bp11");$("#updown3").addClass("c7790fe");
					}
					if(data.updown4.indexOf("-")==-1){
						$(".pp4").addClass("bp22");$("#updown4").addClass("cfa3c3c");
					}else{
						$(".pp4").addClass("bp11");$("#updown4").addClass("c7790fe");
					}
					if(data.updown5.indexOf("-")==-1){
						$(".pp5").addClass("bp22");$("#updown5").addClass("cfa3c3c");
					}else{
						$(".pp5").addClass("bp11");$("#updown5").addClass("c7790fe");
					}
					if(data.updown6.indexOf("-")==-1){
						$(".pp6").addClass("bp22");$("#updown6").addClass("cfa3c3c");
					}else{
						$(".pp6").addClass("bp11");$("#updown6").addClass("c7790fe");
					}
					if(data.updown7.indexOf("-")==-1){
						$(".pp7").addClass("bp22");$("#updown7").addClass("cfa3c3c");
					}else{
						$(".pp7").addClass("bp11");$("#updown7").addClass("c7790fe");
					}
					if(data.updown8.indexOf("-")==-1){
						$(".pp8").addClass("bp22");$("#updown8").addClass("cfa3c3c");
					}else{
						$(".pp8").addClass("bp11");$("#updown8").addClass("c7790fe");
					}
				}else{
					
				}
				 
			}
		});
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
			search();
		}
	};
	laydate(start);
</script>
[@main.footer/]
[/@main.body]