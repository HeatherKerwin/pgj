[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header title='贴现地址'/]

<link rel="stylesheet" type="text/css" href="${staticPath}/css/rywap/form.css"/>
<link rel="stylesheet" type="text/css" href="${staticPath}/css/rywap/city.css"/>
<script type="text/javascript" src="${staticPath}/js/rywap/wx-place.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=RZNGEivCrVHp06sXAM6rxlquUSOLB3xx&s=1"></script>
<div class="wrapper mt44">
	<form action="" class="form">
	    <ul>
	        <li>
	            <div class="formTitle">联系人：</div>
	            <input type="text" name="name" id="name" placeholder="你的姓名" value="${address.name}"/>
	        </li>
	        <li>
	            <div class="formTitle">称呼：</div>
	            <ul class="choseBtn">
	                <li>
	                	<input type="radio" name="sex" value="1" id="men" class="radio" [#if address.sex!=2]checked[/#if]/>
	                	<label for="men" class="[#if address.sex!=2]radioSelect[/#if]">先生</label>
	               	</li>
	                <li>
	                	<input type="radio" name="sex" value="2" id="women" class="radio" [#if address.sex==2]checked[/#if]/>
	                	<label for="women" class="[#if address.sex==2]radioSelect[/#if]">女士</label>
	               	</li>
	            </ul>
	        </li>
	        <li>
	            <div class="formTitle">联系电话：</div>
	            <input type="tel" name="mobile" id="mobile" placeholder="你的手机号" value="${address.mobile}"/>
	        </li>
	        <li>
	            <div class="formTitle">我的位置：</div>
	            <input type="text" name="address" id="address" placeholder="请输入关键字，如东方明珠" autocomplete="off"/>
				<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:100%;height:auto; display:none;"></div>
	        </li>
	        <li>
	            <div class="formTitle">详细地址：</div>
	            <input type="text" name="other" id="other" placeholder="详细地址（如门牌号等）" value="${address.other}"/>
	        </li>
	        <li style="height:200px !important;">
	            <div id="allmap" style="width:100%;height:200px;"></div>
	        </li>
	        <li>
	            <div class="formTitle">交易城市：</div>
	            <span id="gr_zone_ids" data-id="[#if address.cityId!=null]${address.cityId}[#else]310100[/#if]">
	            [#if address.place!=null]
	      			${address.place}
	      		[#else]
	      			上海市
	      		[/#if]
	      		</span>
	            <a id="zone_ids" href="javascript:(0);" class="cred">&nbsp;更改</a>
	        </li>
	    </ul>
	    <a href="javascript:void(0);" class="ADDADDRESS Btn"><input type="button" value="确定" onclick="save();"/></a>
	    <p class="cred font12 p10">注：交易城市默认是您所在位置的城市，但如果您询价时发现其他城市的价格更低，您可以选择其他交易城市。</p>
	    <input type="hidden" id="longitude" value="${longitude}"/>
		<input type="hidden" id="latitude" value="${latitude}"/>
		<input type="hidden" id="id" value="${address.id}"/>
	</form>
</div>
<script type="text/javascript">
var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});// 左上角，添加比例尺
var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件

var longitude = "";//经度
var latitude = "";//纬度
var map = null;
var memberId;
$(document).ready(function(){
	memberId = "${member.id}";//获取登录用户主键
	
	var address_lon = $("#longitude").val();
	var address_lat = $("#latitude").val();
	if(address_lon!=null && $.trim(address_lon).length>0){//编辑贴现地址（获取初始的经纬度）
		longitude = address_lon;
		latitude = address_lat;
	}

	map = new BMap.Map("allmap");
	var point = new BMap.Point(longitude,latitude);
	map.centerAndZoom(point,13);

	var geolocation = new BMap.Geolocation();
	if(address_lon==null || $.trim(address_lon).length<=0){//无初始地址
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
	}else{
		myToLngLat(longitude,latitude);
	}
	
	map.enableScrollWheelZoom(true);
	map.disableDoubleClickZoom(true);
	map.addEventListener("click", addMarker);
	add_control();
	
	//设置我的位置
	var address_ = "${address.address}";
	if(address_!=null && $.trim(address_).length>0){
		setTimeout(function(){
			$("#address").val(address_);
		},10);
	}
});

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
		$("#address").val(addComp.province + addComp.city + addComp.district +  addComp.street + addComp.streetNumber);
	});
}

/**
 * 设置经纬度
 */
function setLngLat(point){
	longitude = point.lng;//经度
	latitude = point.lat;//纬度
	$("#longitude").val(longitude);
	$("#latitude").val(latitude);
}

//建立一个自动完成的对象
var ac = new BMap.Autocomplete({
	"input" : "address",
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
	G("searchResultPanel").innerHTML = "onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
	setPlace();
});

/**
 * 城市定位
 */
function myToCity(){
	var city = document.getElementById("address").value;
	if(city != ""){
		map.centerAndZoom(city,13);//用城市名设置地图中心点
	}
}

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
function myToLngLat(lon_,lat_){
	map.clearOverlays();
	var new_point = new BMap.Point(lon_,lat_);
	var marker = new BMap.Marker(new_point);//创建标注
	map.addOverlay(marker);//将标注添加到地图中
	map.panTo(new_point);
}

/**
 * 保存贴现地址
 */
function save() {
    var name = $("#name").val();
    var sex = $('input[name="sex"]:checked').val();
    var mobile = $("#mobile").val();
    var address = $("#address").val();
    var other = $("#other").val();
    var cityCode = $("#gr_zone_ids").attr("data-id");
    var place = $("#gr_zone_ids").text();
    var longitude = $("#longitude").val();
    var latitude = $("#latitude").val();
    if (cityCode==null || $.trim(cityCode)==""){
    	myToast("请选择一个交易城市");
        return;
    }
    if (name==null || $.trim(name)=="" || name=="undefined") {
    	myToast("请输入您的姓名");
        return;
    }
    if (mobile==null || $.trim(mobile)=="" || !checkMobile(mobile)) {
    	myToast("请输入正确的联系电话");
        return;
    }
    if (longitude==null || $.trim(longitude)=="") {
    	myToast("请选择位置");
        return;
    }
    if (memberId==null || $.trim(memberId)==""){
    	myToast("操作失败：数据异常");
        return;
    }
    var params = {
    		name:name,
    		sex:sex,
    		mobile:mobile,
    		address:address,
    		other:other,
    		cityCode:cityCode,
    		place:$.trim(place),
    		longitude:longitude,
    		latitude:latitude,
    		memberId:memberId
        };
    var id = $("#id").val();
    if(id!=null && $.trim(id)!=""){
    	params.id = id;
    }
    $.ajax({
		url:BASEPATH+"/wap/address/save",
		type:"POST",
		data: params,
		dataType:"json",
		success:function(data){
			if(data.response=="success"){
				window.location.href = BASEPATH+"/wap/address/list?memberId=" + memberId;
			}else{
				myError(data.msg);
				return;
			}
		}
	});
}
</script>

[@main.footer/]
[/@main.body]