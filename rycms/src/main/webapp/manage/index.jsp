<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>睿银管理后台</title>
</head>
<frameset rows="75,*"  frameborder="no" border="0" framespacing="0">
	<frame src="<%=path %>/commons/top.html" noresize="noresize" frameborder="no" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
<frameset cols="200,*" id="frame">
	<frame src="<%=path %>/commons/left.jsp" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	<frame src="" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>