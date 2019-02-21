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
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=path %>/commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=path %>/commons/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=path %>/commons/styles/default.css" />
<script type="text/javascript" src="<%=path%>/commons/scripts/WebCalendar.js"></script>
</head>

<body>
<div id="top">
	<div class="left">
		<button type="button" class="btn btn-success" id="add" onclick="javascript:window.location.href='<%=path %>/discountrecord/daylimitbadd/'">+ 新增额度</button>
		&nbsp;&nbsp;&nbsp;
		<input type="text" name="day" id="day" value="${day }" placeholder="日期" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
		<button type="button" class="btn btn-success" id="add" onclick="search()">搜索</button>
	</div>
	<div class="right">
	</div>
</div>
<c:if test="${flag==1 }">
<form action="<%=path %>/discountrecord/daylimitupdate/" id="form1" method="post">
<div id="edit">
	<input type="hidden" name="id" value="${daylimit.id }"/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>额度：</label></div>
			<div class="">
				<input type="text" name="limitprice" value="${daylimit.limitprice }"/> 万元
			</div>
		</div>
		<br/>
	</div>
		<div id="edit-main1">
		<div class="edit-box">
			<div class="label" style="width: 120px"><label>该天已用额度：</label></div>
			<div class="">
				<input type="text" name="allmoneytotal" value="${allmoneytotal }"/> 万元
			</div>
		</div>
		<br/>
	</div>
	<button type="submit" class="btn btn-success" id="add">修改</button>
</div>

</form>
</c:if>
<script>
	function search(){
		window.location.href = "<%=path%>/discountrecord/daylimitlist/?day="+$('#day').val();
	}
</script>
</body>
</html>