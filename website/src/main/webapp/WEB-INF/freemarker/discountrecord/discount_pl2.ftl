[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
[@main.header currentmenu='2' topindex='2'/]

<!--确认下单-->
<div class="mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态-->
    <form action="${basePath }/discountrecordpl/qrdd" method="post" id="form1">
    <div class="w654 bc f12 tc">
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
    </div>
    <div class="cb"></div>
    <!--选择地址-->
    <div class="fb f14 lh180 cblack mt20 bbe0e0e0">
        <div class="fl cblack">确认贴现地址</div>
        <div class="fr f12 c3366cc none">管理贴现地址</div>
        <div class="cb"></div>
    </div>
    <!--无地址-->
    <p id="ycdz" class="mt20 mb20 f14">暂未添加任何贴现地址</p>
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
                <input type="text" name="mobile" id="tjmobile" placeholder="请输入联系电话" class="validate[required],validate[custom[phone]] w238 h27 lh27 bae0e0e0 ti8">
            </div>
            <div class="w mt16">
                <div class="fl tl w85 lh27">我的位置：</div>
                <input type="text" onblur="myToCity();" name="address" id="tjaddress" placeholder="你的位置" class="validate[required] w654 h27 lh27 bae0e0e0 ti8">
            	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
            </div>	
            <div class="w mt16">
                <div class="fl tl w85 lh27">详细地址：</div>
                <input type="text" name="other" id="tjother" maxlength="140" placeholder="详细地址" class="w238 h27 lh27 bae0e0e0 ti8">
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
    <!--确认信息-->
    <div class="fb f14 lh180 cblack mt20 bbe0e0e0">
        确认订单信息
    </div>
    <div class="mt30 bae0e0e0 mb20">
        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
            <div class="fl w232">批量订单号</div>
            <div class="fl w260">票据总金额</div>
            <div class="fl w150">票据总数量</div>
            <div class="fl w184">票据到期天数</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="cb"></div>
        <div class="h265 bcwhite bbe0e0e0 pt25 pb25">
            <div class="fl w827 Rright">
                <div class="bbe0e0e0 tc f16 h90 pb25">
                    <div class="fl w222 Rright h90 tl ml10">
                        <div class="c3366cc ti8">${discountrecordPl.no }</div>
                    </div>
                    <input type="hidden" name="ordernumber" value="${discountrecordPl.no }" />
                    <div class="fl w260 Rright h90 lh30">
                        <div class=""><span style="color:red">${discountrecordPl.allmoney }</span>万元</div>
                        <div class="">票面最小金额为<span style="color:red">${discountrecordPl.minMoney }</span>万元</div>
                        <div class="">票面最大金额为<span style="color:red">${discountrecordPl.maxMoney}</span>万元</div>
                    </div>
                    <div class="fl w150 Rright h90 lh30"><span style="color:red">${discountrecordPl.amount }</span>张</div>
                    <div class="fl w184 h90 lh30">
                        <div class="">最短<span style="color:red">${discountrecordPl.minExpiryDay}</span>天</div>
                        <div class="">最长<span style="color:red">${discountrecordPl.maxExpiryDay }</span>天</div>
                    </div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h90">
                    <div class="fl w220 Rright h110 tl ml10">
                        <div class="ti8">包含承兑行：</div>
                        <ul class="ti8 mt16 lh35">
                        	[#if (guogu ==1) ]
		                		<li class="fl mr30">国股</li>
			            	[#else]
			            	[/#if]
			            	[#if (dashang ==2) ]
		                		<li class="fl mr30">大商</li>
			            	[#else]
			            	[/#if]
			            	[#if (xiaoshang ==3) ]
		                		<li class="fl mr30">小商</li>
			            	[#else]
			            	[/#if]
			            	[#if (sannong ==4) ]
		                		<li class="fl mr30">三农</li>
			            	[#else]
			            	[/#if]
			            	[#if (qita ==5) ]
		                		<li class="fl mr30">其他</li>
			            	[#else]
			            	[/#if]
                        </ul>
                    </div>
                    <div class="fl pl40 h90">
                        <div class="fl w50 h90">备注：</div>
                        <div class="fl w460 h90 tl">${discountrecordPl.remarks }
                        </div>
                    </div>
                </div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    [#if (discountrecordPl.type1==1) ]
                		<a href="#" class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">纸票</a>
	            	[#else]
	            		<a href="#" class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">电票</a>
	            	[/#if]
                    [#if (discountrecordPl.flawTicket==0)]
                		<a href="#" class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">瑕疵票</a>
	            	[#else]
	            	[/#if]
	            	[#if (discountrecordPl.needTodoor==1) ]
                		<a href="#" class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">需要上门</a>
	            	[#else]
	            	[/#if]
                </div>
            </div>
        </div>
        <div class="cb"></div>
        <div class="h65 lh65 ml10 ti8">
            订单有效期至：<span>${firstdate }</span>
        </div>
        <div class="cb"></div>
    </div>
    <!--支付方式-->
    <div class="h50 bae0e0e0 bcf9f9f9 lh50 mt20">
        <input type="button" id="qrdd" value="生成订单" class="fr f18 cwhite bcd43c33 w166 lh50 b0" />
    </div>
    <div class="cb"></div>
    </form>
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
		                  $(this).parents("li").children("label").addClass("f20");
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
	            $(this).parents("li").children("label").addClass("f16");
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
		 
		 if($.trim($("#yclat").val()).length  == 0){
			 alert("贴现地址不完整，请填写完整");
			 return;
		 }
		 
		 wwwPath = "${basePath}";
		 actionLog(wwwPath,"action96");
		 
		 document.getElementById("form1").submit();
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
		 if( $.trim($("#tjid").val()).length == 0 && ($("input[name='place']").length-1) == 10){
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
		 });
		 var mobile = $("#tjmobile").val();
		 var address = $("#tjaddress").val();
		 if($.trim(address).length == 0){
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
	
    //    鼠标事件
    $(".TXradio").mouseover(function(){
        $(".TXradio").addClass("bcd43c33");
    });
    $("p").mouseout(function(){
        $(".TXradio").removeClass("bcd43c33");
    });

    //复选勾选
    $(".MRchecked").on("click",function(){
        $(this).hasClass("on_check1")? $(this).removeClass("on_check1"):$(this).addClass("on_check1");
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

    //倒计时
    var intDiff = parseInt(600);//倒计时总秒数量
    function timer(intDiff){
        var _t = window.setInterval(function(){
            var day=0,
                    hour=0,
                    minute=0,
                    second=0;//时间默认值
            if(intDiff > 0){
                minute = Math.floor(intDiff / 60);
                second = Math.floor(intDiff) - (minute * 60);
            }
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            $('#minute_show1').html('('+minute+'分'+second+'秒'+')');
            intDiff--;
        }, 1000);
    }
    $(function(){
        timer(intDiff);
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
