<!doctype html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>APP消息推送</title>
	<link rel="stylesheet" type="text/css" href="../css/skin-1.css"/>
	<link rel="stylesheet" href="../commons/styles/reset.css"/>
	<link rel="stylesheet" href="../commons/styles/bootstrap.css"/>
	<link rel="stylesheet" href="../commons/styles/bootstrap-datetimepicker.min.css"/>
	<link rel="stylesheet" href="../commons/styles/default.css"/>
	<link href="../commons/styles/bootstrap3.css" rel="stylesheet">
	
	<script src="../commons/scripts/jquery-1.10.1.min.js"></script>
	<script src="../commons/scripts/bootstrap.min.js"></script>
	<script src="../commons/scripts/bootstrap.js"></script>
	<script type="text/javascript" src="../commons/scripts/WebCalendar.js"></script>
</head>
<body>
<div class="panel panel-default">
	<div class="panel-heading">APP消息推送</div>
	<form id="R01" action="${rc.contextPath}/pushlog/save" class="form-horizontal">
	<div class="panel-body">
		<div class="form-group">
        	<label for="banner_text" class="col-sm-2 control-label text-left">注册时间</label>
        	<div class="col-sm-6">
	        	<input style="height:30px;width:90px;" type="text" id="start" name="start" value="${now}" placeholder="起始时间" onclick="SelectDate(this,'yyyy-MM-dd');" readonly/>
	        	<input style="height:30px;width:90px;" type="text" id="end" name="end" value="${now}" placeholder="截止时间" onclick="SelectDate(this,'yyyy-MM-dd');" readonly/>
        	</div>
        </div>
        <div class="form-group">
        	<label for="banner_text" class="col-sm-2 control-label text-left">推送对象</label>
        	<div class="col-sm-6">
	        	<select id="type" name="type">
	        		<option value="0">所有用户</option>
	        		<option value="1">认证机构</option>
	        		<option value="2">认证企业</option>
	        	</select>
        	</div>
        </div>
        <div class="form-group">
        	<label for="banner_text" class="col-sm-2 control-label text-left">推送区域</label>
        	<div class="col-sm-6">
	        	<select id="cityId" name="cityId">
	        		<option style="color:red;" value="">--全国--</option>
	        		[#list citys as c]
	        		<option value="${c.id}">${c.name}</option>
	        		[/#list]
	        	</select>
	        	<span style="color:red;font-size:9px;">注：推送区域包含企业贴现地址、机构收票地址</span>
        	</div>
        </div>
        <div class="form-group">
            <label for="banner_text" class="col-sm-2 control-label text-left">消息内容</label>
            <div class="col-sm-6">
                <textarea id="content" name="content" maxlength="72" onchange="this.value=this.value.substring(0, 72)" onkeydown="this.value=this.value.substring(0, 72)" onkeyup="this.value=this.value.substring(0, 72)" class="form-control" rows="3" placeholder="字数限制为72个字以内。">${info.content}</textarea>
            </div>
        </div>
        <div class="form-group">
			<div class="col-sm-10">
	             <input type="button" onclick="save();" value="推送" class="btn btn-danger col-sm-1">
			</div>
		</div>
	</div>
	</form>
</div>
</body>
<script type="text/javascript">
function save(){
	var type = $("#type").val();
	var content = $("#content").val();
	var start = $("#start").val();
	var end = $("#end").val();
	var cityId = $("#cityId").val();
	if($.trim(content) == ""){
		alert("请输入消息内容！");
		return false;
	}
    if (!confirm("确定推送该消息？")) {
        return false;
    }
    var params = {
   		type:type,
   		content:content,
   		start:start,
   		end:end
    };
    if(cityId!=null && cityId!=""){
    	params.cityId = cityId;
    }
    console.log(params);
	$.ajax({
		type: "POST",
     	url: "${rc.contextPath}/pushlog/save",
     	data: params,
     	dataType:"json",
     	success:function(data){
     		if(data.response=="success"){
     			alert(data.msg);
     			window.location.href = "${rc.contextPath}/pushlog/page";
     		}else{
     			alert(data.msg);
     		}
     	}
	});
}
</script>
</html>