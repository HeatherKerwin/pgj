<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=path %>/commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=path %>/commons/styles/bootstrap.css"/>
<link rel="stylesheet"  href="<%=path %>/commons/styles/bootstrap-datetimepicker.min.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=path %>/commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=path %>/commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/commons/scripts/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path %>/commons/scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>


<link rel="stylesheet" href="<%=path %>/commons/styles/default.css" />

<script src="<%=path %>/commons/scripts/ajaxupload.js"></script>
<!-- 上传组件Uploadify -->
<script src="<%=path %>/commons/scripts/jquery.uploadify.js"></script>

<script type="text/javascript" src="<%=path%>/commons/scripts/WebCalendar.js"></script>

<!--编辑器-->
<link rel="stylesheet" href="<%=path %>/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="<%=path %>/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=path %>/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="<%=path %>/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=path %>/kindeditor/plugins/code/prettify.js"></script>

<script>
	
	function chooseSubmitMethod(flag){
		if(confirm("确定执行操作吗")){
			$('#form1').submit();
		}
	}
	
	function checkForm(){
		return true;
	}
</script>
</head>


<body>
<form method="post" action="<%=path %>/admin/appversionupdate/" onsubmit="return checkForm()" id="form1">

<div id="edit">

	<div id="edit-main">
		<div class="edit-box">
			<div class="label" style="width: 120px"><label>ios版本</label></div>
			<div class="">
				<input type="text" name="iosversion" value="${appversion.iosversion }"/>
			</div>
		</div>
		<div class="edit-box">
			<div class="label" style="width: 120px"><label>android版本</label></div>
			<div class="">
				<input type="text" name="androidversion" value="${appversion.androidversion }"/>
			</div>
		</div>
		<div class="edit-box">
			<div class="label" style="width: 120px"><label>ios简介</label></div>
			<div class="">
				<input type="text" name="iosdesc" value="${appversion.iosdesc }"/>
			</div>
		</div>
		<div class="edit-box">
			<div class="label" style="width: 120px"><label>android简介</label></div>
			<div class="">
				<input type="text" name="androiddesc" value="${appversion.androiddesc }"/>
			</div>
		</div>
		<div class="edit-box">
			<div class="label" style="width: 120px"><label>ios强制更新</label></div>
			<div class="">
				<select name="iosflag">
					<c:choose>
       					<c:when test="${appversion.iosflag == '1'}">
       						<option value="0">否</option>
							<option value="1" selected="selected">是</option>				              
					    </c:when>					    
					    <c:otherwise>
					        <option value="0" selected="selected">否</option>
							<option value="1">是</option>   
					    </c:otherwise>
					</c:choose>
				</select>
			</div>
		</div>
		<div class="edit-box">
			<div class="label" style="width: 120px"><label>android强制更新</label></div>
			<div class="">
				<select name="androidflag">
					<c:choose>
       					<c:when test="${appversion.androidflag == '1'}">
       						<option value="0">否</option>
							<option value="1" selected="selected">是</option>				              
					    </c:when>					    
					    <c:otherwise>
					        <option value="0" selected="selected">否</option>
							<option value="1">是</option>   
					    </c:otherwise>
					</c:choose>
					
				</select>
			</div>
		</div>
	</div>


</div>

<div id="bottom">
  <div class="left">
		<button type="button" class="btn btn-success" id="publish" onclick="chooseSubmitMethod(1)"> 修改 </button>
	</div>
</div>
</form>
<script>
$(document).ready(function(){
	var button1 = $('#singlepic1'), interval1;	
	new AjaxUpload(button1, {
		action: '<%=path%>/manage/picuploadJson.do', 
		name: 'pic1button',
		responseType: 'text/html',
		onSubmit : function(file, ext){
			// change button1 text, when user selects file			
			button1.html('上传中');
							
			// If you want to allow uploading only 1 file at time,
			// you can disable upload button1
			this.disable();
			
			// Uploding -> Uploading. -> Uploading...
			interval1 = window.setInterval(function(){
				var text = button1.val();
				if (text.length < 13){
					button1.html(text + '.');					
				} else {
					button1.html('上传中');				
				}
			}, 200);
		},
		onComplete: function(file, response){
			if (response == "") {
                alert("上传失败请重试");
                button1.html('选择');
				window.clearInterval(interval1);
				// enable upload button1
				this.enable();
            } else {
                if(response=='error'){
	                alert("上传失败请重试");
	                button1.html('选择');
					window.clearInterval(interval1);
					// enable upload button1
					this.enable();
				}else{
					$("#singlepicdiv1").html("<img src='<%=path%>/"+response+"' width='100px' height='100px'/><input type='hidden' value='"+response+"' name='pic'>");
					button1.html('重新选择');
					window.clearInterval(interval1);
					// enable upload button1
					this.enable();
				}
            }				
		}
	});
});
</script>
</body>
</html>