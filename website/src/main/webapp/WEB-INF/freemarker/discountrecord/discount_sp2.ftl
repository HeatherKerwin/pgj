[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
[@main.header currentmenu='2' topindex='2'/]

<!--确认下单-->
<div class="mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态-->
    <form action="${basePath }/discountrecordsp/qrdd" method="post" id="form1">
    <div class="w491 bc f12 tc">
        <div class="fl">
            <img src="${image_url}/website/discount/state1.png" width="165" height="30">
            <p class="c3366cc mt10">确认贴现订单</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state2.png" width="165" height="30">
            <p class="ccccccc mt10">交易跟踪</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state3.png" width="165" height="30">
            <p class="ccccccc mt10">评价机构</p>
        </div>
    </div>
    <div class="cb"></div>
    <!--选择地址-->
    <div class="fb f14 lh180 cblack mt20 bbe0e0e0">
        <div class="fl cblack">确认贴现地址</div>
        <div class="fr f12 c3366cc none">管理贴现地址</div>
        <div class="cb"></div>
    </div>
    <!--无地址-->
    <p id="ycdz"  class="mt20 mb20 f14" style="color:red">暂未添加任何贴现地址</p>
    <!--有地址-->
    
    <input type="hidden" id="ycphone" name="ycphone"  value="${member.mobile}">
    <input type="hidden" id="zhid" name="memberid"  value="${member.id}">
    <input type="hidden" id="yclon" name="yclon"  value="">
    <input type="hidden" id="yclat" name="yclat" value="">
    <input type="hidden" id="ycname"  name="ycname" value="">
    <input type="hidden" id="ycplace"  name="ycplace" value="">
    <input type="hidden" id="ycsex"  name="ycsex" value="">
    <input type="hidden" id="yccity_id" name="yccity_id" value="">
    <input type="hidden" id="ycaddress" name="ycaddress" value="">
    <input id="ddh" type="hidden" name="ordernumber" value="${discountrecordSp.no }">
    <ul class="mt12 bcwhite f14 c2d2d2d" id="content">
    	
    </ul>
    <a class="f12 c3366cc mt10 dsb h14" id="toggle"><div class="fl">+&nbsp;新增贴现地址</div><i class="fl w10 h6 down ml5 mt3" id="xjdz"></i> </a>
    <div class="cb"></div>
    
    <div class="bae0e0e0 f9f9f9 pl20 pb20 mt20 bcf9f9f9 none" id="xjdzDiv">
        <div class="fl">
            <div class="w mt10">
                <div class="fl tl w85 lh27">联系人：</div>
                <input type="text" name="name" id="tjname"  placeholder="请输入姓名" class="validate[required] w238 h27 lh27 bae0e0e0 ti8">
            </div>
            <div class="mt16 pl85 f14">
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
            <div class="w mt16">
                <div class="fl tl w85 lh27">联系电话：</div>
                <input type="text" name="mobile" id="tjmobile" placeholder="请输入联系电话" class="validate[required],validate[custom[phone]]  w238 h27 lh27 bae0e0e0 ti8">
            </div>
            <div class="w mt16">
                <div class="fl tl w85 lh27">我的位置：</div>
                <input type="text" onblur="myToCity();" name="address" id="tjaddress" placeholder="你的位置" class="validate[required] w654 h27 lh27 bae0e0e0 ti8">
            	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
            </div>	
            <div class="w mt16">
                <div class="fl tl w85 lh27">详细地址：</div>
                <input type="text" name="other" id="tjother"  maxlength="140" placeholder="详细地址" class="w238 h27 lh27 bae0e0e0 ti8">
            </div>
            <div class="w mt20">
                <div class="fl tl w85 lh27">交易城市：</div>
                <select id="initcity" class="w320 h40 select320 ti10"></select>
            </div>
            <input type="hidden" name="longitude" id="tjlon" />
            <input type="hidden" name="latitude" id="tjlat"/>
            <input type="hidden" name="id"  id="tjid"/>
            <input type="hidden" name="memberId"  value="${member.id}">
            <div class="f12 mt10 cd43c33">注：交易城市默认是您所在位置的城市，但如果您询价时发现其他城市的价格更低，您可以选择其他交易城市。</div>
            <div class="f14 mt20">
                <div class="fl mr10">
                    <input name="need_inv" type="checkbox" id="MRDZ" class="w12 h12 checkbox2" value="1">
                </div>
                <label class="fl" for="MRDZ">设为默认贴现地址</label>
            </div>
            <div class="cb"></div>
        </div>
        <div class="fr w300 h305 mt10 mr20 bae0e0e0" id="pcmap">这里显示地图</div>
        <div class="cb"></div>
        <input type="button" id="baocun" class="w166 h52 bc f18 bcd43c33 cwhite br5 b0 ml489" value="保存">
        <div class="cb"></div>
    </div>
    </form>
    <!--确认信息-->
    <div class="fb f14 lh180 cblack mt20 bbe0e0e0">
        确认订单信息
    </div>
    <div class="mt30 bae0e0e0 mb20">
        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
            <div class="fl w232">商票订单号</div>
            <div class="fl w155">总金额</div>
            <div class="fl w105">背书</div>
            <div class="fl w334">承兑企业</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="h210 bcwhite bbe0e0e0 pt25 pb25">
            <div class="fl w827 Rright">
                <div class="bbe0e0e0 tc f16 h75">
                    <div class="fl w222 Rright h50 tl ml10">
                        <div class="c3366cc ti8">${discountrecordSp.no }</div>
                    </div>
                    <div class="fl w155 Rright h50 lh50"><span style="color:red">${discountrecordSp.allmoney }</span>万元</div>
                    <div class="fl w105 Rright h50 lh50">${discountrecordSp.endorse }手</div>
                    <div class="fl w334 h50 lh50">${discountrecordSp.bank }</div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h120">
                    <div class="fl w220 Rright h120 tl ml10">
                        <div class="ti8">开票日期：<span>${first }</span></div>
                        <div class="ti8 mt16">贴现日期：<span>${start }</span></div>
                        <div class="ti8 mt16">到期日期：<span>${end}</span></div>
                        <div class="ti8 mt16">计息天数：<span>${jxts }</span>天</div>
                    </div>
                    <div class="fl pl40 h123">
                        <div class="fl w50 h120">备注：</div>
                        <div class="fl w460 h120 tl">${discountrecordSp.remarks}
                        </div>
                    </div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    [#if (discountrecordSp.type1==1) ]
                		<span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">纸票</span>
	            	[#else]
	            		<span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">电票</span>
	            	[/#if]
	            	[#if (discountrecordSp.needTodoor==1) ]
                		<span class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">需要上门</span>
	            	[#else]
	            	[/#if]
                </div>
            </div>
        </div>
        <div class="cb"></div>
    </div>
    <!--票面-->
     [#if (discountrecordSp.hasTicket==0) ]
     	<div class="bae0e0e0 mt20 pb25">
	        <div class="pl10 h50 lh50 c717583 bcf9f9f9 bbe0e0e0">票面</div>
	        <img src="${imagePath}${discountrecordSp.picpath}" class="w860 h230 bc mt25 ml143" >
    	</div>
  	[#else]
	[/#if]
    <!--支付方式-->
    <div class="h50 bae0e0e0 bcf9f9f9 lh50 mt20">
        <input type="button" id="qrdd" value="确认订单" class="fr f18 cp cwhite bcd43c33 w166 lh50 b0" />
    </div>
    <div class="cb"></div>
</div>
  [@main.right /]
  
  
  <script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${jsPath}/map.js"></script>
<script type="text/x-handlebars-template" id="ADDRESS">
{{#each data}}
	<li class="w1149 h37 lh37 mt10" name="ss">
        <input type="radio" name="place" data-id="{{id}}" value="{{id}}" {{toState state}} class="fl w12 h12 mt10 ml10 mr15 TXradio radio2">
        <label class="fl" >
            <p class="fl">{{address}}（{{name}}）</p>
            <p class="fl c808080 ml10">{{mobile}}</p>
            <p class="fl c808080 ml20">默认地址</p>
        </label>
        <span class="fr c3366cc f12 mr10 none" onclick="xgdz('{{id}}');"  data-id="{{id}}">修改本地址</span>
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
		loadDate();
	});
	
	//页面初始化加载地址
	function loadDate(){
		 $.ajax({
	 		type: "POST",
	      	url: "${basePath}/discountrecord/addresslist",
	      	data: {memberId:$("#zhid").val()},
	      	dataType:"json",
	      	success:function(data){
	            var source = $("#ADDRESS").html();
	            var template = Handlebars.compile(source);
	            var html = template(data);
	            $("#content").html(html);
	            initCity();
	            if(data.data[0].name!=null){
	            	$("#ycdz").addClass("none");
	            }
	            $("#yclon").val(data.data[0].longitude);
	            $("#yclat").val(data.data[0].latitude);
	            $("#ycname").val(data.data[0].name);
	            $("#ycplace").val(data.data[0].place);
	            $("#ycsex").val(data.data[0].sex);
	            $("#yccity_id").val(data.data[0].cityId);
	            $("#ycaddress").val(data.data[0].address);
	            
	            $("input[name='place']").each(function(){
		           	  if ($(this).attr("checked") == "checked") {
		                  $(this).parent().addClass("chose");
		                  $(this).parents("li").children("span").removeClass("none");
		                  $(this).parents("li").children("label").addClass("f16");
		              }else{
		            	  $(this).parents("li").children("label").children().eq(2).html("");
		              }
		           	  
	            })
	            $("input[name='place']").live("click",xzdizhi);
	     	 }
	 	 });
	}
	
	//在已有的地址中选择贴现地址
	function xzdizhi(){
		 $("input[name='place']").each(function(){
	        if ($(this).attr("checked") == "checked") {
	            $(this).parent().addClass("chose");
	            $(this).parents("li").children("span").removeClass("none");
	            $(this).parents("li").children("label").addClass("f20");
	            var id = $(this).val();
	            $.ajax({
			 		type: "POST",
			      	url: "${basePath}/address/get",
			      	data: {addressId:id},
			      	dataType:"json",
			      	success:function(data){
			           if(data.response == "success"){
			        	   $("#ycname").val(data.data.name);
			        	   $("#ycaddress").val(data.data.address);
			        	   $("#ycplace").val(data.data.place);
			        	   $("#yclon").val(data.data.longitude);
			        	   $("#yclat").val(data.data.latitude);
			        	   $("#ycsex").val(data.data.sex);
			        	   $("#yccity_id").val(data.data.cityId);
			        	   $("#ycaddress").val(data.data.address);
			           }else{
			        	   alert(data.msg);
			           }
			            
			     	}
			 	});
	        }else{
	        	$(this).parent("li").removeClass("chose");
	 	        $(this).parent("li").children("span").addClass("none");
	 	        $(this).parent("li").children("label").removeClass("f20");
	        }
	        
		 });
	 }
	
	//修改贴现地址
	function xgdz(id){
		 $.ajax({
		 		type: "POST",
		      	url: "${basePath}/address/get",
		      	data: {addressId:id},
		      	dataType:"json",
		      	success:function(data){
		           if(data.response == "success"){
		        	   $("#xjdzDiv").removeClass("none");  
		        	   $("#tjname").val(data.data.name);
		        	   $("#tjmobile").val(data.data.mobile);
		        	   $("#tjaddress").val(data.data.address);
		        	   $("#tjother").val(data.data.other);
		        	   $("#tjid").val(data.data.id);
		        	   $("#tjlon").val(data.data.longitude);
		        	   $("#tjlat").val(data.data.latitude);
		        	   
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

	 //确认订单
	 $("#qrdd").click(function(){
		 
		 if($.trim($("#yclat").val()).length == 0){
			 alert("贴现地址不完整，请填写完整");
			 return;
		 }
		 
		 wwwPath = "${basePath}";
		 actionLog(wwwPath,"action95");
		 
		 document.getElementById("form1").submit();
	 })
	 
	 //新增地址或者修改地址保存
	 $("#baocun").click(function(){
		 
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
		 
		 if($.trim($("#tjid").val()).length == 0 && ($("input[name='place']").length-1) == 10){
			 alert("贴现地址已经10个，不能添加");
			 return ;
		 }
		 if($.trim($("#tjlat").val()).length == 0){
			 alert("贴现地址不完整，请填写完整");
			 return;
		 }
		 var name = $("#tjname").val();
		 var sex;
		 $("input[name='sex']").each(function(){
			 if($(this).attr("checked") == "checked"){
				 sex = $(this).val();
			 }
		 })
		 var mobile = $("#tjmobile").val();
		 var address = $("#tjaddress").val();
		 if($.trim(address).length  == 0){
			 alert("贴现地址不完整，请填写完整");
			 return;
		 }
		 var other = $("#tjother").val();
		 var place = $("#initcity").val();
		 var longitude = $("#tjlon").val();
		 var latitude = $("#tjlat").val();
		 var id = $("#tjid").val();
		 var memberId = $("input[name='memberId']").val();
		 var state;
		 if($("input[name='need_inv']").attr("checked") == "checked"){
			 state = 0;
		 }else{
			 state = 1;
		 }
		 $.ajax({
		 		type: "POST",
		      	url: "${basePath}/address/save",
		      	data: {name:name,sex:sex,mobile:mobile,address:address,other:other,place:place,
		      		longitude:longitude,latitude:latitude,id:id,memberId:memberId,state:state},
		      	dataType:"json",
		      	success:function(data){
		      		if(data.response == "success"){
		      			loadDate();
		      			$("#tjname").val("");
		      			$("#tjmobile").val("");
		      			$("#tjaddress").val("");
		      			$("#tjother").val("");
		      			$("#tjplace").val("");
		      			$("#tjlon").val("");
		      			$("#tjlat").val("");
		      			$("#tjid").val("");
		      		}else{
		      			alert("保存失败")
		      		}
		      	}
		 });    	
	 });
	 
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
	 
    //    鼠标事件
    $(".TXradio").mouseover(function(){
        $(".TXradio").addClass("bcd43c33");
    });
    $("p").mouseout(function(){
        $(".TXradio").removeClass("bcd43c33");
    });

    //复选勾选
    $(".TXchecked").on("click",function(){
        $(this).hasClass("on_check")? $(this).removeClass("on_check"):$(this).addClass("on_check");
    })

    //    新建地址
    $("#toggle").toggle(function(){
    	var p= "上海市";//默认城市为上海
		$("#initcity option[value='"+p+"']").attr("selected","selected");
        $("#xjdz").addClass("up");
        $("#xjdz").removeClass("down");
        $("#xjdzDiv").slideDown(100);
    },function(){
    	var p= "上海市";//默认城市为上海
		$("#initcity option[value='"+p+"']").attr("selected","selected");
        $("#xjdz").removeClass("up");
        $("#xjdz").addClass("down");
        $("#xjdzDiv").slideUp(100);
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
	 }

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