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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6WIEK15yVVcchWSWBg1GVSx8"></script>

<script>
	function chooseSubmitMethod(flag){
		if(confirm("确定执行操作吗")){
			$('#form1').submit();
		}
	}
	
	function checkForm(){
		return true;
	}
	
	$("#submitButton").click(function(){
		$("#from1").submit();
	});
</script>
</head>


<body>
<form method="post" action="<%=path %>/admin/add/" onsubmit="return checkForm()" id="form1">
<div id="top">
	<div class="left">
		<button type="button" class="btn btn-success" id="publish" onclick="chooseSubmitMethod(1)"> 添加 </button>
	</div>
	<div class="right" id="box-pubdate">
     </div>
</div>


<div id="edit">


	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>用户名</label></div>
			<div class="input"><input type="text" class="input-sm" name="username" id="username"/></div>
		</div>
		<div class="edit-box">
			<div class="label"><label>真实姓名</label></div>
			<div class="input"><input type="text" class="input-sm" name="realname" id="realname"/></div>
		</div>
		<div class="edit-box">
			<div class="label"><label>密码</label></div>
			<div class="input"><input class="input-sm" name="password" id="password" type="text"/></div>
		</div>
		<div class="edit-box">
			<div class="label"><label>权限</label></div>
			<div>
				<input type="checkbox" value="1" name="usertype"/>询价管理
				<input type="checkbox" value="2" name="usertype"/>订单管理
				<input type="checkbox" value="3" name="usertype"/>信息中心
				<input type="checkbox" value="4" name="usertype"/>会员管理
				<input type="checkbox" value="5" name="usertype"/>活跃度
				<input type="checkbox" value="6" name="usertype"/>版本控制
				<input type="checkbox" value="7" name="usertype"/>推广管理
				<input type="checkbox" value="8" name="usertype"/>红包管理
				<input type="checkbox" value="9" name="usertype"/>广告管理
				<input type="checkbox" value="10" name="usertype"/>消息管理
				<input type="checkbox" value="11" name="usertype"/>意见反馈
			</div>
		</div>
		<br/>
	</div>


</div>

<div id="bottom">
  <div class="left">
		<button type="button" class="btn btn-success" id="publish" onclick="chooseSubmitMethod(1)"> 添加 </button>
	</div>
</div>

</form>
</body>
</html>