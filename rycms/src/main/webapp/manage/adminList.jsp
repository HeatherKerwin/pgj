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
</head>

<body>
<div  id="top">
	<div class="left">
		<button type="button" class="btn btn-success" id="add" onclick="badd();">+ 新增客服</button>
		
	</div>
	<div class="right">
	<!-- 
		<div class="btn-group">
		  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">1/4 <span class="caret"></span></button>
		  <ul class="dropdown-menu">
			<li><a href="edit.html">2/4</a></li>
			<li><a href="edit.html">3/4</a></li>
			<li><a href="edit.html">4/4</a></li>
		  </ul>
		</div>
		<a href="edit.html" class="prev"></a>
		<a href="edit.html" class="next"></a>
	 -->
	</div>
</div>
<form action="" id="form1" method="post">
<table class="table table-bordered table-hover">
	<thead>
	  <tr>
		<th class="text-center td-checkbox"><input type="checkbox" id="chk-all" /></th>
		<th class="text-left">用户名</th>
		<th class="text-left">删除</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach items="${adminList}" var="admin">
		<tr>
			<td class="text-center td-checkbox"><input type="checkbox" id="" value="${admin.id }" name="idcheckbox" /></td>
			<td class="text-left td-name"><a href="<%=path%>/admin/bupdate/?id=${admin.id}">${admin.username }</a></td>
			<td class="text-left td-name"><a href="javascript:void(0)" onclick="deleteuser(${admin.id})">删除</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</form>

<div class="pagination text-center">
	<ul>
	</ul>
</div>

<script language="javascript">

	function deleteuser(userid){
		if(confirm("确定删除吗")){
			window.location.href= "<%=path%>/admin/delete/?id="+userid;
		}
	}
	
	function badd() {
		window.location.href = "../../admin/badd/";
	}
</script>
</body>
</html>