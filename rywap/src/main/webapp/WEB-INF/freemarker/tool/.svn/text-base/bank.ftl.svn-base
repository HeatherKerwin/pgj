[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家-工具-询价"]
[@main.header currentmenu='2'/]
<div class="wrapper">
<form action="" class="form bcwhite" id = "myForm">
    <ul>
        <li>
            <lable class="formTitle">银行名称：</lable>
            <select name="yinhang" id="yinhang" class="select">
            	<option value=''>请选择银行</option>
				<option value='城商行'>城商行</option>
				<option value='城市信用社'>城市信用社</option>
				<option value='工商银行'>工商银行</option>
				<option value='光大银行'>光大银行</option>
				<option value='广发银行'>广发银行</option>
				<option value='合作联社'>合作联社</option>
				<option value='合作银行'>合作银行</option>
				<option value='华夏银行'>华夏银行</option>
				<option value='建设银行'>建设银行</option>
				<option value='交通银行'>交通银行</option>
				<option value='民生银行'>民生银行</option>
				<option value='农村信用社'>农村信用社</option>
				<option value='农业发展'>农业发展</option>
				<option value='农业银行'>农业银行</option>
				<option value='商业银行'>商业银行</option>
				<option value='深圳发展银行'>深圳发展银行</option>
				<option value='信用合作社'>信用合作社</option>
				<option value='兴业银行'>兴业银行</option>
				<option value='邮储银行'>邮储银行</option>
				<option value='招商银行'>招商银行</option>
				<option value='中国人民银行'>中国人民银行</option>
				<option value='中国银行'>中国银行</option>
				<option value='中信银行'>中信银行</option>
            </select></li>
        <li>
            <lable class="formTitle">省份：</lable>
            <select name="provice" id="provice" class="select" onchange="choose2(this);"></select></li>
        <li>
            <lable class="formTitle">城市：</lable>
            <select name="city" id="city" class="select">
            	<option value=''>请选择城市</option>
            </select></li>
        <li>
            <lable class="formTitle">关键字</lable>
            <input type="text" name="keyword" id="keyword" placeholder="请输入查询条件"/>
    </ul>
</form>
<p class="cred font12 p10" id="flag">*你可以直接输入关键字进行搜索</p>
<a class="Btn" href="javascript:void(0);"><input type="button" onclick="lianhangsearch()" value="查询"/></a>
</div>
<link rel="apple-touch-icon" href="${imagePath}/common/icon.png">
<link rel="Shortcut Icon" href="${imagePath}/common/icon.png" type="image/x-icon">
<link rel="stylesheet" href="${staticPath}/css/rywap/bank.css"/>
<link rel="stylesheet" href="${staticPath}/css/rywap/form.css"/>
<script type="text/javascript">
	var citylist;//根据省份选择城市
	$(document).ready(function(){
		$("#yinhang").blur(function(){
			$("#flag").html("你可以直接输入关键字进行查询");
		});
		$("#provice").blur(function(){
			$("#flag").html("你可以直接输入关键字进行查询");
		});
		$("#city").blur(function(){
			$("#flag").html("你可以直接输入关键字进行查询");
		});
		$.ajax({
			url:BASEPATH+"/wap/tool/provicecity",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data){
				var data = eval(data);
				if(data.response=='success'){
					var provicelist = data.provicelist;
					var provicehtml = "<option value=''>请选择省份</option>";
					for(var i=0;i<provicelist.length;i++){
						var provice = provicelist[i];
						provicehtml += "<option value='"+provice.name+"'>"+provice.name+"</option>";
					}
					citylist = data.citylist;
					$("#provice").html(provicehtml);
				}
			}
		});
		
		$("#keyword").blur(function(){
			var array=new Array();
			var keyword=$("#keyword").val();
			var array=keyword.split(/\s+/g);
			if(array.length>3){
				myToast("请输入3个以内的关键字");
				$("#keyword").val("");
				$("#keyword").focus();
			}
		})
	
	})
	
	function choose2(obj){
		var cityhtml = "<option value=''>请选择城市</option>";
		for(var i=0;i<citylist.length;i++){
			if(citylist[i].pname==$(obj).val()){
				cityhtml += "<option value='"+citylist[i].name+"'>"+citylist[i].name+"</option>";
			}
		}
		$("#city").html(cityhtml);
	}
	
	/*
	 *响应查询按钮
	 */
	function lianhangsearch(){
		var yinhang = $("#yinhang").val();
		var provice = $("#provice").val();
		var city = $("#city").val();
		var keyword = $("#keyword").val();
		if((yinhang!=""&&provice!=""&&city!="")||keyword!=""){
			window.localStorage.setItem("yinhang",yinhang);
			window.localStorage.setItem("provice",provice);
			window.localStorage.setItem("city",city);
			window.localStorage.setItem("keyword",keyword);
			$("#myForm").attr("action",BASEPATH+"/wap/tool/lianhangSearch");
			$("#myForm").submit();
		}else{
			$("#flag").html("请选择查询条件");
		}
	}
</script>
[@main.footer/]
[/@main.body]