[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家-工具-shibor查询"]
[@main.header currentmenu='2'/]
<div class="wrapper">
	<!--选择时间-->
	<div class="time row">
		<div class="flex1" onclick="leftday();">
			<img src="${imagePath}/rywap/shibor/last.png" class="leftday">
		</div>
		<div class="flex2 tac" id="dayshow">2017-07-19</div>
		<div class="flex1 tar" onclick="rightday();">
			<img src="${imagePath}/rywap/shibor/next.png" class="rightday">
		</div>
		<div class="cleaar">
		</div>
	</div>
	<!--图标-->
    <div class="shibor">
        <div class="shibor_top row">
            <div class="flex1">
                期限
            </div>
            <div class="flex1">
                shibor(%)
            </div>
            <div class="flex1">
                涨跌(BP)
            </div>
        </div>
    </div>
    <div id="biaotou">
	<ul class="shibor_con tac">
	  	<li class="row">
		    <div class='flex1'>
			    <img class="shiborIcon1" src="${imagePath}/rywap/shibor/arrow.png">
			    <span>O/N</span>
		    </div>
		    <div class='flex1' id="shibor1">000</div>
			<div class='flex1'>
				<img class='shiborIcon2' id="pp1" src=''>
				<span id="updown1">0.00</span>
		    </div>
	    </li>
	  	<li class="row">
		    <div class='flex1'>
			    <img class='shiborIcon1' src="${imagePath}/rywap/shibor/arrow.png">
			    <span>1W</span>
		    </div>
		    <div class='flex1' id="shibor2">000</div>
			<div class='flex1'>
				<img class='shiborIcon2' id="pp2" src=''>
				<span id="updown2">0.00</span>
		    </div>
	    </li>
	  	<li class="row">
		    <div class='flex1'>
			    <img class='shiborIcon1' src="${imagePath}/rywap/shibor/arrow.png">
			    <span>2W</span>
		    </div>
		    <div class='flex1' id="shibor3">000</div>
			<div class='flex1'>
				<img class='shiborIcon2' id="pp3" src=''>
				<span id="updown3">0.00</span>
		    </div>
	    </li>
	  	<li class="row">
		    <div class='flex1'>
			    <img class='shiborIcon1' src="${imagePath}/rywap/shibor/arrow.png">
			    <span>1M</span>
		    </div>
		    <div class='flex1' id="shibor4">000</div>
			<div class='flex1'>
				<img class='shiborIcon2' id="pp4" src=''>
				<span id="updown4">0.00</span>
		    </div>
	    </li>
	  	<li class="row">
		    <div class='flex1'>
			    <img class='shiborIcon1' src="${imagePath}/rywap/shibor/arrow.png">
			    <span>3M</span>
		    </div>
		    <div class='flex1' id="shibor5">000</div>
			<div class='flex1'>
				<img class='shiborIcon2' id="pp5" src=''>
				<span id="updown5">0.00</span>
		    </div>
	    </li>
	  	<li class="row">
		    <div class='flex1'>
			    <img class='shiborIcon1' src="${imagePath}/rywap/shibor/arrow.png">
			    <span>6M</span>
		    </div>
		    <div class='flex1' id="shibor6">000</div>
			<div class='flex1'>
				<img class='shiborIcon2' id="pp6" src=''>
				<span id="updown6">0.00</span>
		    </div>
	    </li>
	  	<li class="row">
		    <div class='flex1'>
			   	<img class='shiborIcon1' src="${imagePath}/rywap/shibor/arrow.png">
			    <span>9M</span>
		    </div>
		    <div class='flex1' id="shibor7">000</div>
			<div class='flex1'>
				<img class='shiborIcon2' id="pp7" src=''>
				<span id="updown7">0.00</span>
		    </div>
	    </li>
	  	<li class="row">
		    <div class='flex1'>
			    <img class='shiborIcon1' src="${imagePath}/rywap/shibor/arrow.png">
			    <span>1Y</span>
		    </div>
		    <div class='flex1' id="shibor8">000</div>
			<div class='flex1'>
				<img class='shiborIcon2' id="pp8" src=''>
				<span id="updown8">0.00</span>
		    </div>
	    </li>
	</ul>
    </div>
</div>
<link rel="stylesheet" href="${staticPath}/css/rywap/shibor.css"/>
<script type="text/javascript" src="${staticPath}/js/rywap/shibor.js"></script>
<script>
	$(document).ready(function() {
		loadData();
	});
	
	//查询
	function search(day) {
		$.ajax({
			url: '${basePath}/wap/tool/findshibor',
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
						$("#pp1").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp1").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown2.indexOf("-")==-1){
						$("#pp2").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp2").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown3.indexOf("-")==-1){
						$("#pp3").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp3").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown4.indexOf("-")==-1){
						$("#pp4").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp4").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown5.indexOf("-")==-1){
						$("#pp5").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp5").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown6.indexOf("-")==-1){
						$("#pp6").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp6").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown7.indexOf("-")==-1){
						$("#pp7").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp7").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown8.indexOf("-")==-1){
						$("#pp8").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp8").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown1==0){
						$("#pp1").css("visibility","hidden");
					}else{
						$("#pp1").css("visibility","visible");
					}
					if(data.updown2==0){
						$("#pp2").css("visibility","hidden");
					}else{
						$("#pp2").css("visibility","visible");
					}
					if(data.updown3==0){
						$("#pp3").css("visibility","hidden");
					}else{
						$("#pp3").css("visibility","visible");
					}
					if(data.updown4==0){
						$("#pp4").css("visibility","hidden");
					}else{
						$("#pp4").css("visibility","visible");
					}
					if(data.updown5==0){
						$("#pp5").css("visibility","hidden");
					}else{
						$("#pp5").css("visibility","visible");
					}
					if(data.updown6==0){
						$("#pp6").css("visibility","hidden");
					}else{
						$("#pp6").css("visibility","visible");
					}
					if(data.updown7==0){
						$("#pp7").css("visibility","hidden");
					}else{
						$("#pp7").css("visibility","visible");
					}
					if(data.updown8==0){
						$("#pp8").css("visibility","hidden");
					}else{
						$("#pp8").css("visibility","visible");
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
			url: '${basePath}/wap/tool/shibor/init',
			type: 'POST',
			dataType: 'json',
			data: {},
			success: function(data){
				if(data.response=="success"){
					var data=data.data;
					$("#dayshow").html(data.day);
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
						$("#pp1").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp1").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown2.indexOf("-")==-1){
						$("#pp2").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp2").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown3.indexOf("-")==-1){
						$("#pp3").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp3").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown4.indexOf("-")==-1){
						$("#pp4").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp4").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown5.indexOf("-")==-1){
						$("#pp5").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp5").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown6.indexOf("-")==-1){
						$("#pp6").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp6").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown7.indexOf("-")==-1){
						$("#pp7").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp7").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown8.indexOf("-")==-1){
						$("#pp8").attr("src","${imagePath}/rywap/shibor/up.png");
					}else{
						$("#pp8").attr("src","${imagePath}/rywap/shibor/down.png");
					}
					if(data.updown1==0){
						$("#pp1").css("visibility","hidden");
					}else{
						$("#pp1").css("visibility","visible");
					}
					if(data.updown2==0){
						$("#pp2").css("visibility","hidden");
					}else{
						$("#pp2").css("visibility","visible");
					}
					if(data.updown3==0){
						$("#pp3").css("visibility","hidden");
					}else{
						$("#pp3").css("visibility","visible");
					}
					if(data.updown4==0){
						$("#pp4").css("visibility","hidden");
					}else{
						$("#pp4").css("visibility","visible");
					}
					if(data.updown5==0){
						$("#pp5").css("visibility","hidden");
					}else{
						$("#pp5").css("visibility","visible");
					}
					if(data.updown6==0){
						$("#pp6").css("visibility","hidden");
					}else{
						$("#pp6").css("visibility","visible");
					}
					if(data.updown7==0){
						$("#pp7").css("visibility","hidden");
					}else{
						$("#pp7").css("visibility","visible");
					}
					if(data.updown8==0){
						$("#pp8").css("visibility","hidden");
					}else{
						$("#pp8").css("visibility","visible");
					}
				}else{
					
				}
			}
		});
	}
	function leftday(){
		var dayshow = $("#dayshow").html();
		dayshow = new Date(Date.parse(dayshow.replace(/-/g, "/")));
		dayshow = dayshow.getTime()-24*60*60*1000;
		search(timetostr(dayshow));
		$("#dayshow").html(timetostr(dayshow));
	}
	function rightday(){
		var dayshow = $("#dayshow").html();
		dayshow = new Date(Date.parse(dayshow.replace(/-/g, "/")));
		dayshow = dayshow.getTime()+24*60*60*1000;
		search(timetostr(dayshow));
		$("#dayshow").html(timetostr(dayshow));
	}
</script>
[@main.footer/]
[/@main.body]