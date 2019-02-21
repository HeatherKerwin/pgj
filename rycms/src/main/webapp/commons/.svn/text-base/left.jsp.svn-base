</html><%@page import="com.ry.core.entity.Admin"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<script src="scripts/jquery-1.10.1.min.js" language="javascript"></script>
<link rel="stylesheet" href="styles/reset.css"/> 
<style>
body {
	background-color:#E6E6FA;
	color:#222;
}
html {
	border-right: 1px solid #bdbdbd;
	height: 100%;
}

a {
	color:#222;
}

ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
li {
	font-size: 12px;
	line-height: 30px;
	list-style-type: none;
	display: block;
	text-decoration: none;
	padding-left: 0px;
}

span a:link {
	font-size: 12px;
	line-height: 30px;
	height: 30px;
	display: block;
	text-align: left;
	padding-left: 30px;
	margin: 0px;
	padding-left: 30px;
	overflow: hidden;
	text-decoration: none;
	background-image: url(images/arror_down.png);
	background-repeat: no-repeat;
	background-position: 10px 7px;
	
}
span a:visited {
	font-size: 12px;
	line-height: 30px;
	display: block;
	text-align: left;
	margin: 0px;
	padding-left: 30px;
	height: 30px;
	text-decoration: none;
}
span a:active {
	font-size: 12px;
	line-height: 30px;
	height: 30px;
	display: block;
	text-align: left;
	margin: 0px;
	padding-left: 30px;
	overflow: hidden;
	text-decoration: none;
}
span a:hover {
	font-size: 12px;
	line-height: 30px;
	text-align: left;
	display: block;
	margin: 0px;
	padding-left: 30px;
	height: 30px;
	text-decoration: none;
	background-color: #BCD2EE;
}
li dl dt { height:30px; repeat-y;}
li dl dt a {
	font-size: 12px;	line-height: 30px;
	color: #333333;	height: 30px;
	display: block;
	overflow: hidden;
	text-decoration: none;
	padding-left: 50px;
}
li dl dt a:hover{
		background-color: #DEDEDE;
}

.current{
	background-color:#DEDEDE;
}

.current_{
	background-color:#BCD2EE;
	border-right:3px solid #3399FF;
}
</style>
</head>

<body>

<ul id="sidemenu" class="MM">


 <c:forEach items="${menus}" var="menu" >
    <li><span><a href="#"><c:out value="${menu.name}"></c:out></a></span>
     <dl class="down">
    <c:forEach items="${menu.childs}" var="menu1" >
    <li>
    <c:choose>
							<c:when test="${empty menu1.href}">  
						     <dt>	<a href="javascript:void(0);" target="main">
									${menu1.name}
									<c:if test="${not empty menu1.childs}">
										<img class="more1" src="${path }/images/more.png"/>
									</c:if>
								</a> </dt>
						  	</c:when>
						  	<c:otherwise> 
						    <dt>	<a href="${path}/${menu1.href}" target="main">
									${menu1.name}
									<c:if test="${not empty menu1.childs}">
										<img class="more1" src="${path }/images/more.png"/>
									</c:if>
								</a> </dt>
						  	</c:otherwise>
	</c:choose>
	
	<ul class="thr" style="text-indent:50px">
								<c:forEach items="${menu1.childs}" var="menu2">
									<li>
									<dt>	<a href="${path }/${menu2.href }" target="main">
											${menu2.name}
										</a> </dt>
									</li>
								</c:forEach>
							</ul>
       </li>
       </c:forEach>
        </dl>
        </li>
      </c:forEach>
</ul>
        
<script type="text/javascript" language="javascript">
$(document).ready(function (e) {
	$("#sidemenu li dl").slideUp(0);
	$("#sidemenu li span").click(function () {
		$("#sidemenu li span").removeClass("nav_on");
		$(this).addClass("nav_on");
		$(this).parent().siblings().find("dl").slideUp(400);
		$(this).next().slideToggle(200);
	});
	$("#sidemenu li ul.down").slideToggle(0);
	
	$("dt a").click(function(){
		$(".current").removeClass("current");
		$(this).addClass("current");
	});
	
	$("li span a").click(function(){
		$(".current_").removeClass("current_");
		$(this).addClass("current_");
	});
	
});
</script>
</body>
</html>