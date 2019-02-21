[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]

<div class="mt16 w1200 bc mb20 pl20 pr20 pb75 bcwhite">
	<div id="allmap" style="width:100%;height:400px;"></div>
	<br>
	<button onclick="getLngLat();">获取当前位置</button>
	<label id="lng"></label>
	<label id="lat"></label>
	
	<br>
	城市名: <input id="suggestId" type="text" style="width:100px; margin-right:10px;" />
	<input type="button" value="城市定位" onclick="myToCity();" />
	<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
	
	<div id="r-result">
		经度: <input id="longitude" type="text" style="width:100px; margin-right:10px;" />
		纬度: <input id="latitude" type="text" style="width:100px; margin-right:10px;" />
		<input type="button" value="查询" onclick="myToLngLat();" />
	</div>
</div>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=RZNGEivCrVHp06sXAM6rxlquUSOLB3xx&s=1"></script>
<script type="text/javascript">
var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});// 左上角，添加比例尺
var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件

var longitude = "116.331398";//经度
var latitude = "39.897445";//纬度
var map = null;
$(document).ready(function(){
	map = new BMap.Map("allmap");
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
}

/**
 * 设置经纬度
 */
function setLngLat(point){
	longitude = point.lng;//经度
	latitude = point.lat;//纬度
}

/**
 * 获取经纬度
 */
function getLngLat(){
	$("#lng").text(longitude);
	$("#lat").text(latitude);
}

/**
 * 城市定位
 */
function myToCity(){
	var city = document.getElementById("suggestId").value;
	if(city != ""){
		map.centerAndZoom(city,11);//用城市名设置地图中心点
	}
}



//建立一个自动完成的对象
var ac = new BMap.Autocomplete({
	"input" : "suggestId",
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

function setPlace(){
	map.clearOverlays();//清除地图上所有覆盖物
	function myFun(){
		var pp = local.getResults().getPoi(0).point;//获取第一个智能搜索的结果
		map.centerAndZoom(pp, 16);
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
	if(document.getElementById("longitude").value != "" && document.getElementById("latitude").value != ""){
		map.clearOverlays();
		var new_point = new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
		var marker = new BMap.Marker(new_point);//创建标注
		map.addOverlay(marker);//将标注添加到地图中
		map.panTo(new_point);      
	}
}
</script>

[@main.footer/]
[/@main.body]