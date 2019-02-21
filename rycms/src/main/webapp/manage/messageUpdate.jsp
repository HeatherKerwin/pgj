<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%
		String path = request.getContextPath();
	%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户反馈详情</title>
	<link rel="stylesheet" href="<%=path%>/commons/styles/reset.css" />
	<link rel="stylesheet" href="<%=path%>/commons/styles/bootstrap.css"/>
	<link rel="stylesheet"  href="<%=path%>/commons/styles/bootstrap-datetimepicker.min.css"/>
	<link rel="stylesheet" href="<%=path %>/commons/styles/default.css" />
	
	<script src="<%=path%>/commons/scripts/jquery-1.10.1.min.js"></script>
	<script src="<%=path%>/commons/scripts/bootstrap.min.js"></script>
	<style type="text/css">
	.my-div{
		width:500px;
	}
	</style>
</head>
<body>
	<form method="post" id="my-form" action="<%=path%>/message/save">
		<input type="hidden" name="id" value="${message.id}"/>
		<div id="edit">
			<div id="edit-main">
				<div class="edit-box">
					<div class="label"><label>用户名：</label></div>
					<div>
						${message.memberName}
					</div>
				</div><br/>
				<div class="edit-box">
					<div class="label"><label>手机号：</label></div>
					<div>
						${message.messagemobile}
					</div>
				</div><br/>
				<div class="edit-box">
					<div class="label"><label>时间：</label></div>
					<div>
						${fn:substring(message.messagetime, 0, 19)}
					</div>
				</div><br/>
				<div class="edit-box">
					<div class="label"><label>反馈内容：</label></div>
					<div>
						<textarea name="messagecontent" cols="50" rows="5" readonly="readonly">${message.messagecontent}</textarea>
					</div>
				</div>
				<div class="edit-box">
					<div class="label"><label>回访备注：</label></div>
					<div>
						<textarea name="returnVisit" cols="50" rows="5">${message.returnVisit}</textarea>
					</div>
				</div>
			</div>
			<input type="submit" class="btn btn-success" value="提交"/>
		</div>
	</form>
</body>
</html>