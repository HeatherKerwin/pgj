[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]

<!--贴现地址-->
<!-- 
	提高百度地图所在的层级
 -->
<style>
.tangram-suggestion-main  {
    z-index: 13;
}
</style>
<div class="w1200 h800 bc mt20 mb20">
[@main.left remark='1' /]
   <!--右侧内容 -->
    <div class="fl w997 h800 bcwhite bae0e0e0">
    	
    	<input type="hidden" id="zhid" name="memberid"  value="${member.id}">
    
        <div class="w997 h34 lh34 ti10 bcfaf7f7">贴现地址</div>
        <input type="hidden" id="jiazai"  value="1"/>
        <!--无地址-->
        <div class="w997 ha none wdz">
            <div class="w238 bc">
                <img src="${basePath }/images/PJGJ/place/noPlace.png" width="234" height="120" class="bc mt100">
            </div>
            <div class="w364 h34 lh34 b0 br5 bcd43c33 cwhite bc f16 mt30 addcity" >
                <img src="${basePath }/images/PJGJ/place/add.png" width="16" height="16" class="mt8 ml133 mr20" />新增地址
            </div>
        </div>
        <!--有地址-->
        <div class="pl20 pr20 ydz">
            <div class="mt30 fb bb3_e0e0e0 pb8">
                <div class="fl f18 lh20">您已保存了<span id ="num1">0</span>条地址，还可以再保存<span id="num2">10</span>条地址：</div>
                <a href="#" class="fr dsb f12 c3366cc addcity lh20">+ 新增贴现地址 </a>
                <div class="cb"></div>
            </div>
            <div class="w bte0e0e0 ble0e0e0 bre0e0e0 ha mt30">
                <div class="w h52 tc lh52 bcf9f9f9 bbe0e0e0 c717583">
                    <div class="fl w450 bre0e0e0">地址</div>
                    <div class="fl w90 bre0e0e0">联系人</div>
                    <div class="fl w144 bre0e0e0">联系方式</div>
                    <div class="fl w270">操作</div>
                </div>
                <ul id="content">
                </ul>
            </div>
        </div>
    </div>
</div>
  [@main.right /]
<div class="w h pf bc05 zi10 top none" id="bnsaddcity">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <div class="f9f9f9 pl50 pt50" id="">
                <div class="fl">
                    <div class="f18 fb">编辑此贴现地址：</div>
                    <div class="w mt20">
                        <div class="fl tl w85 lh27">联系人：</div>
                        <input type="text" name="name" id="tjname" placeholder="请输入您的姓名" class="validate[required] w238 h27 lh27 bae0e0e0 ti8">
                    </div>
                    <div class="mt20 pl85 f14">
                        <div class="fl">
                            <input type="radio" name="sex" value="1" class="fl w12 h12 mt3 TXradio radio2" id="men" checked/>
                            <label class="ml20" for="men">男士</label>
                        </div>
                        <div class="fl ml50">
                            <input type="radio" name="sex" value="2" class="fl w12 h12 mt3 TXradio radio2" id="woman"/>
                            <label class="ml20" for="woman">女士</label>
                        </div>
                        <div class="cb"></div>
                    </div>
                    <div class="w mt20">
                        <div class="fl tl w85 lh27">联系电话：</div>
                        <input type="text" name="mobile" id="tjmobile" placeholder="请输入您的联系电话" class="validate[required],validate[custom[phone]] w238 h27 lh27 bae0e0e0 ti8">
                    </div>
                    <div class="w mt20">
                        <div class="fl tl w85 lh27">我的位置：</div>
                        <input type="text" name="address" id="tjaddress" placeholder="请输入您的位置，如北京市朝阳区" class="validate[required] w238 h27 lh27 bae0e0e0 ti8">
                    	<!-- <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div> -->
                    </div>
                </div>
               <!--  <div class="fr w300 h200 bae0e0e0 mr20" id="pcmap">地图</div> -->
                <div class="cb"></div>
                <div class="w mt20">
                    <div class="fl tl w85 lh27">详细地址：</div>
                    <input type="text" name="other" id="tjother" maxlength="140" placeholder="请输入您的详细地址" class="w491 h27 lh27 bae0e0e0 ti8">
                </div>
                <div class="w mt20">
                <div class="fl tl w85 lh27">交易城市：</div>
	                <select id="initcity" class="w320 h40 select320 ti10"></select>
	            </div>
                <div class="f12 mt20 cd43c33">注：交易城市默认是您所在位置的城市，但如果您询价时发现其他城市的价格更低，您可以选择其他交易城市。</div>
                <div class="f14 mt30">
                    <div class="fl mr10">
                        <input name="need_inv" type="checkbox" id="MRDZ" class="w12 h12 checkbox2" value="1">
                    </div>
                    <label class="fl" for="MRDZ">设为默认贴现地址</label>
                </div>
               <!--  <input type="hidden" name="longitude" id="tjlon" />
	            <input type="hidden" name="latitude" id="tjlat"/> -->
	            <input type="hidden" name="id"  id="tjid"/>
	            <input type="hidden" name="memberId"  value="${member.id}">
                <div class="cb"></div>
                <input type="button" id="baocun" class="w268 h44 bc lh45 b0 cwhite bcfc4d42 br5 f18 tc ml190 mt59" value="保存">
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${jsPath}/map.js"></script>

<script type="text/x-handlebars-template" id="ADDRESS">
{{#each data}}
	<li class="w h57 lh57 bbe0e0e0 tc sl">
        <div class="fl w410 pl20 pr20 bre0e0e0 tl dian">{{address}}</div>
        <div class="fl w90 bre0e0e0 oh wsn">{{name}}</div>
        <div class="fl w144 bre0e0e0">{{mobile}}</div>
        <div class="fl w270 tc">
            <input type="button" data-id="{{id}}" class="fl w56 h30 lh30 bc778ffd b0 br5 cwhite f16 mt13 ml65 xg" value="修改" />
            <input type="button" data-id="{{id}}" class="fl w56 h30 lh30 bc778ffd b0 br5 cwhite f16 mt13 ml25 sc" value="删除" />
        </div>
    </li>
{{/each}}
</script>

<script type="text/javascript">

	Handlebars.registerHelper('toSex', function(num, options) {
	    if(num==1){//男
	        return "先生";
	    }else{
	        return "女士";
	    }
	});
	Handlebars.registerHelper('toState', function(num, options) {
	    if(num==0){
	        return "checked";
	    }
	});
	
	$(document).ready(function() {
		loadData();
	});
	
	//页面初始化加载地址
	function loadData(){
		 $.ajax({
	 		type: "POST",
	      	url: "${basePath}/discountrecord/addresslist",
	      	data: {memberId:$("#zhid").val()},
	      	dataType:"json",
	      	success:function(data){
	      		initCity();
	            if(typeof(data.data[0]) != "undefined"){
	            	$("#num1").text(data.num);
	            	$("#num2").text(10-(data.num));
	            	var source = $("#ADDRESS").html();
		            var template = Handlebars.compile(source);
		            var html = template(data);
		            $("#content").html(html);
		           
		            
		            var jiazai = $("#jiazai").val();
		            if(jiazai == 1){
		            	$("#jiazai").val("2");
		            	//修改地址事件绑定
			            $(".xg").live("click",xgdizhi);
			           //删除地址事件绑定
			            $(".sc").live("click",scdizhi);
		            }
		            $(".wdz").addClass("none");
	            	$(".ydz").removeClass("none");
	            }else{
	            	$(".wdz").removeClass("none");
	            	$(".ydz").addClass("none");
	            }
	     	 }
	 	 });
	}
	
	/**
	* 初始化地图的城市选择
	*/
	function initCity(){
		$.ajax({
	 		type: "POST",
	      	url: "${basePath}/bisicmessage/init/city",
	      	data: {},
	      	dataType:"json",
	      	success:function(data){
	          	if(data.response == "success"){
	          		var phtml = "";
	                for(var i=0;i<data.c.length;i++){
	                    var provice = data.c[i];
	                    phtml += "<option value='"+provice.name+"'>"+provice.name+"</option>";
	                }
	                $("#initcity").html(phtml);
	          	}
	     	}
	 	 });
	};

	$(".redClose").click(function(){
		$("#bnsaddcity").addClass("none");
	})
	
	$(".addcity").click(function(){
		if($(".sl").length == 10){
			 alert("贴现地址已经10个，不能添加");
			 return ;
		}
		
		$("#tjname").val("");
		$("#tjmobile").val("");
		$("#tjaddress").val("");
		$("#tjother").val("");
		$("#tjplace").val("");
	/* 	$("#tjlon").val("");
		$("#tjlat").val(""); */
		$("#tjid").val("");
		
		var p= "上海市";
  	   	$("#initcity option[value='"+p+"']").attr("selected","selected");
		$("#bnsaddcity").removeClass("none");
	})
	
	//修改地址事件绑定的方法
	function xgdizhi(){
		var id = $(this).attr("data-id");
		$.ajax({
	 		type: "POST",
	      	url: "${basePath}/address/get",
	      	data: {addressId:id},
	      	dataType:"json",
	      	success:function(data){
	           if(data.response == "success"){
	        	   $("#bnsaddcity").removeClass("none");  
	        	   $("#tjname").val(data.data.name);
	        	   $("#tjmobile").val(data.data.mobile);
	        	   $("#tjaddress").val(data.data.address);
	        	   $("#tjother").val(data.data.other);
	        	   $("#tjid").val(data.data.id);
	        	 /*   $("#tjlon").val(data.data.longitude);
	        	   $("#tjlat").val(data.data.latitude); */
	        	   
	        	   var p= data.data.place;
	        	   $("#initcity option[value='"+p+"']").attr("selected","selected");
	        	   
	        	   map.clearOverlays();
				   var new_point = new BMap.Point(data.data.longitude,data.data.latitude);
				   var marker = new BMap.Marker(new_point);//创建标注
				   map.addOverlay(marker);//将标注添加到地图中
				   map.panTo(new_point);
	        	   
	        	   if(data.data.sex==2){
	        		   $("#woman").attr("checked","checked");
	                   $("#men").removeAttr("checked");
	        	   }else{
	        		   $("#men").attr("checked","checked");
	                   $("#woman").removeAttr("checked");
	        	   }
	           }else{
	        	   alert(data.msg);
	           }
	            
	     	}
	 });
	}
	//删除地址事件绑定的方法
	function scdizhi(){
		var id = $(this).attr("data-id");
		var memberId = $("#zhid").val();
		$.ajax({
	 		type: "POST",
	      	url: "${basePath}/address/del",
	      	data: {id:id,memberId:memberId},
	      	dataType:"json",
	      	success:function(data){
	           if(data.response == "success"){
	        	  alert("删除成功");
	        	  wwwPath = "${basePath}";
	     		  actionLog(wwwPath,"action135");
	        	  loadData();
	           }else{
	        	   alert(data.msg);
	           }
	     	}
	    });
	}
	
	//保存贴现地址
	$("#baocun").click(function(){
		if($("#baocun").attr("disabled")){
			return;
		}
		if(!$("#tjname").validationEngine("validate")){
			$("#tjname").focus();
			return ;
		}
		if(!$("#tjmobile").validationEngine("validate")){
			$("#tjmobile").focus();
			return ;
		}
		if(!$("#tjaddress").validationEngine("validate")){
			$("#tjaddress").focus();
			return ;
		}
		
		
		/*  if($.trim($("#tjlat").val()).length == 0){
			 alert("贴现地址不完整，请填写完整");
			 return;
		 } */
		 var name = $("#tjname").val();
		 var sex;
		 $("input[name='sex']").each(function(){
			 if($(this).attr("checked") == "checked"){
				 sex = $(this).val();
			 }
		 })
		 var mobile = $("#tjmobile").val();
		 var address = $("#tjaddress").val();
		 
		 
		 var other = $("#tjother").val();
		 var place = $("#initcity").val();
		/*  var longitude = $("#tjlon").val();
		 var latitude = $("#tjlat").val(); */
		 var id = $("#tjid").val();
		 var memberId = $("input[name='memberId']").val();
		 var state;
		 if($("input[name='need_inv']").attr("checked") == "checked"){
			 state = 0;
		 }else{
			 state = 1;
		 }
		 $("#baocun").attr("disabled","disabled");//按钮禁用
		 wwwPath = "${basePath}";
		 actionLog(wwwPath,"action135");
		 $.ajax({
		 		type: "POST",
		      	url: "${basePath}/address/save",
		      	data: {name:name,sex:sex,mobile:mobile,address:address,other:other,place:place,
		      		id:id,memberId:memberId,state:state},
		      	dataType:"json",
		      	success:function(data){
		      		if(data.response == "success"){
		      			$("#baocun").removeAttr("disabled");//按钮启用
		      			loadData();
		      			$("#tjname").val("");
		      			$("#tjmobile").val("");
		      			$("#tjaddress").val("");
		      			$("#tjother").val("");
		      			$("#tjplace").val("");
		      		/* 	$("#tjlon").val("");
		      			$("#tjlat").val(""); */
		      			$("#tjid").val("");
		      			$("#bnsaddcity").addClass("none");
		      		}else{
		      			$("#baocun").removeAttr("disabled");//按钮启用
		      			alert("保存失败")
		      		}
		      	}
		 });
	});
</script>


<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=RZNGEivCrVHp06sXAM6rxlquUSOLB3xx&s=1"></script>

<script type="text/javascript">
	//主要是地图的处理
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
	var map = null;
		map = new BMap.Map("pcmap");
		var longitude = "121.506854";//经度
		var latitude = "31.2408";//纬度
		var point = new BMap.Point(longitude,latitude);
		map.centerAndZoom(point,13);
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				map.addOverlay(mk);
				map.panTo(r.point);
				setLngLat(r.point);
			}else {
				alert('failed'+this.getStatus());
			}
		},{enableHighAccuracy: true});
		
		map.enableScrollWheelZoom(true);
		map.disableDoubleClickZoom(true);
		map.addEventListener("click", addMarker);
		add_control();
		
	/**
	 * 添加控件和比例尺
	 */
	function add_control(){
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     
	}

	/**
	 * 设置标记
	 */
	function addMarker(e){
		map.clearOverlays();
		
		var point = new BMap.Point(e.point.lng, e.point.lat);
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
		setLngLat(e.point);
		var gc = new BMap.Geocoder();
		gc.getLocation(point, function(rs){
		var addComp = rs.addressComponents;
			$("#tjaddress").val(addComp.province + addComp.city + addComp.district +  addComp.street + addComp.streetNumber);
		});
	}

	/**
	 * 设置经纬度
	 */
	function setLngLat(point){
		longitude = point.lng;//经度
		latitude = point.lat;//纬度
		$("#tjlon").val(longitude);
		$("#tjlat").val(latitude);
	}

	/**
	 * 城市定位
	 */
	function myToCity(){
		map.clearOverlays();
		var city = document.getElementById("tjaddress").value;
		if(city != ""){
			map.centerAndZoom(city,13);//用城市名设置地图中心点
		}
		var geocoder = new BMap.Geocoder();  
		//获取起始地址经纬度  
		geocoder.getPoint(city,  function(point){  
            if(point){  
                longitude = point.lng;  
                latitude = point.lat;
                var new_point = new BMap.Point(longitude,latitude);
                var marker = new BMap.Marker(new_point);//创建标注
                map.addOverlay(marker);//将标注添加到地图中
                setLngLat(new_point);
            }  
		});  
	 };



	//建立一个自动完成的对象
	var ac = new BMap.Autocomplete({
		"input" : "tjaddress",
		"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {//鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province + _value.city + _value.district + _value.street + _value.business;
		}
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province + _value.city + _value.district + _value.street + _value.business;
		}
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {//鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
		$("#tjaddress").val(myValue);
		myToCity();
	});

	function setPlace(){
		map.clearOverlays();//清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;//获取第一个智能搜索的结果
			map.centerAndZoom(pp, 13);
			map.addOverlay(new BMap.Marker(pp));//添加标注
			setLngLat(pp);
		}
		var local = new BMap.LocalSearch(map, {//智能搜索
			onSearchComplete: myFun
		});
		local.search(myValue);
	}

	function G(id) {
		return document.getElementById(id);
	}

	//用经纬度设置地图中心点
	function myToLngLat(){
		if(document.getElementById("yclon").value != "" && document.getElementById("yclat").value != ""){
			map.clearOverlays();
			var new_point = new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
			map.centerAndZoom(new_point,13);
			var marker = new BMap.Marker(new_point);//创建标注
			map.addOverlay(marker);//将标注添加到地图中
			map.panTo(new_point); 
			map.setCenter(new_point);
		}
	}
</script>
[@main.footer/]
[/@main.body]