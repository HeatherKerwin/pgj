<%@page import="com.ruiyin.model.PageModel"%>
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

<c:if test="${flag==1 }">

<form action="<%=path %>/admin/gongjuServlet.do?method=shiborupdate" id="form1" method="post">
<div id="top">
	<div class="left">
		<!-- <button type="button" class="btn btn-success" id="add" onclick="javascript:window.location.href='<%=path %>/admin/gongjuServlet.do?method=shiborbadd'">+ 新增shibor</button>
		&nbsp;&nbsp;&nbsp; -->
		<input type="text" name="day" id="day" value="${day }" placeholder="日期" onclick="SelectDate(this,'yyyy-MM-dd')" readonly onchange="search()"/>
		<!-- <button type="button" class="btn btn-success" id="add" onclick="search()">搜索</button> -->
	</div>
	<div class="right">
	</div>
</div>
<div id="edit">
	<input type="hidden" name="id" value="${shibor.id }"/>
	<table style="width: 350px;">
		<tr>
			<td width="100px">期限</td>
			<td width="100px" align="center">shibor</td>
			<td width="50px" align="center">&nbsp;</td>
			<td width="100px" align="center">涨跌</td>
		</tr>
		<tr>
			<td>O/N</td>
			<td><input type="text" name="shibor1" value="${shibor.shibor1 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown1" value="${shibor.updown1 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>1W</td>
			<td><input type="text" name="shibor2" value="${shibor.shibor2 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown2" value="${shibor.updown2 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>2W</td>
			<td><input type="text" name="shibor3" value="${shibor.shibor3 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown3" value="${shibor.updown3 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>1M</td>
			<td><input type="text" name="shibor4" value="${shibor.shibor4 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown4" value="${shibor.updown4 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>3M</td>
			<td><input type="text" name="shibor5" value="${shibor.shibor5 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown5" value="${shibor.updown5 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>6M</td>
			<td><input type="text" name="shibor6" value="${shibor.shibor6 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown6" value="${shibor.updown6 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>9M</td>
			<td><input type="text" name="shibor7" value="${shibor.shibor7 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown7" value="${shibor.updown7 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>1Y</td>
			<td><input type="text" name="shibor8" value="${shibor.shibor8 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown8" value="${shibor.updown8 }" style="width: 100px;"/></td>
		</tr>
	</table>
</div>
<button type="submit" class="btn btn-success" id="add">修改</button>
</form>
</c:if>	

<c:if test="${flag==0 }">

<form action="<%=path %>/admin/gongjuServlet.do?method=shiboradd" id="form1" method="post">
<div id="top">
	<div class="left">
		<!-- <button type="button" class="btn btn-success" id="add" onclick="javascript:window.location.href='<%=path %>/admin/gongjuServlet.do?method=shiborbadd'">+ 新增shibor</button>
		&nbsp;&nbsp;&nbsp; -->
		<input type="text" name="day" id="day" value="${day }" placeholder="日期" onclick="SelectDate(this,'yyyy-MM-dd')" readonly onchange="search()"/>
		<!-- <button type="button" class="btn btn-success" id="add" onclick="search()">搜索</button> -->
	</div>
	<div class="right">
	</div>
</div>
<div id="edit">
	<table style="width: 350px;">
		<tr>
			<td width="100px">期限</td>
			<td width="100px" align="center">shibor</td>
			<td width="50px" align="center">&nbsp;</td>
			<td width="100px" align="center">涨跌</td>
		</tr>
		<tr>
			<td>O/N</td>
			<td><input type="text" name="shibor1" value="${shibor.shibor1 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown1" value="${shibor.updown1 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>1W</td>
			<td><input type="text" name="shibor2" value="${shibor.shibor2 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown2" value="${shibor.updown2 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>2W</td>
			<td><input type="text" name="shibor3" value="${shibor.shibor3 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown3" value="${shibor.updown3 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>1M</td>
			<td><input type="text" name="shibor4" value="${shibor.shibor4 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown4" value="${shibor.updown4 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>3M</td>
			<td><input type="text" name="shibor5" value="${shibor.shibor5 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown5" value="${shibor.updown5 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>6M</td>
			<td><input type="text" name="shibor6" value="${shibor.shibor6 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown6" value="${shibor.updown6 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>9M</td>
			<td><input type="text" name="shibor7" value="${shibor.shibor7 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown7" value="${shibor.updown7 }" style="width: 100px;"/></td>
		</tr>
		<tr>
			<td>1Y</td>
			<td><input type="text" name="shibor8" value="${shibor.shibor8 }" style="width: 100px;"/></td>
			<td>&nbsp;</td>
			<td><input type="text" name="updown8" value="${shibor.updown8 }" style="width: 100px;"/></td>
		</tr>
	</table>
</div>
<button type="submit" class="btn btn-success" id="add">添加</button>
</form>
</c:if>
<script>
	function search(){
		window.location.href = "<%=path%>/admin/gongjuServlet.do?method=shiborsearch&day="+$('#day').val();
	}
</script>
</body>
</html>