<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
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
		<button type="button" class="btn btn-success" id="add" onclick="javascript:window.location.href='<%=path %>/manage/newsAdd.jsp'">+ 新增市场信息</button>
		起始时间:<input type="text" name="begintime" id="begintime" value="${begintimeStr}" placeholder="起始时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
		截止时间:<input type="text" name="endtime" id="endtime" value="${endtimeStr}" placeholder="截止时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
		<button type="button" class="btn btn-success" id="add" onclick="search()">搜索</button>
	</div>
	<div class="right">
	</div>
</div>
<form action="" id="form1" method="post">
<table class="table table-bordered table-hover">
	<thead>
	  <tr>
		<th class="text-center td-checkbox"><input type="checkbox" id="chk-all" /></th>
		<th class="text-left">标题</th>
		<th class="text-left">类型</th>
		<th class="text-left">时间</th>
		<th class="text-left">删除</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach items="${pr.results}" var="news">
		<tr>
			<td class="text-center td-checkbox"><input type="checkbox" id="" value="${news.id }" name="idcheckbox" /></td>
			<td class="text-left td-name">
				<a href="<%=path%>/news/toupdate/?id=${news.id}">${news.title }</a>
			</td>
			<td class="text-left td-name">
				<c:if test="${news.type==1 }">票据新闻</c:if>
				<c:if test="${news.type==2 }">银行动态</c:if>
				<c:if test="${news.type==3 }">市场分析</c:if>
				<c:if test="${news.type==4 }">相关新闻</c:if>
			</td>
			<td class="text-left td-name">
				${news.publishtime }
			</td>
			<td onclick="confirmdelete(${news.id })">
				删除
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</form>

<div class="pagination text-center">
	<ul>
		<c:if test="${pr.pageCount!=0 }">
	      	<c:if test="${pr.currentPage!=1 }">
	      		<li><a href="#" onclick="jumpPage(${pr.currentPage-1 })">&laquo;</a></li>
	      	</c:if>
	      	<c:if test="${(pr.currentPage-4>0)&&(pr.currentPage+1>pr.pageCount) }">
	      		<li><a href="#" onclick="jumpPage(${request.pr.currentPage-4})">${pr.currentPage-4 }</a></li>
	      	</c:if>
	      	<c:if test="${(pr.currentPage-3>0)&&(pr.currentPage+2>pr.pageCount)}">
	      		<li><a href="#" onclick="jumpPage(${pr.currentPage-3})">${pr.currentPage-3 }</a></li>
	      	</c:if>
	      	<c:if test="${(pr.currentPage-2>0)}">
	      		<li><a href="#" onclick="jumpPage(${pr.currentPage-2})">${pr.currentPage-2}</a></li>
	      	</c:if>
	      	<c:if test="${(pr.currentPage-1>0)}">
	      		<li><a href="#" onclick="jumpPage(${pr.currentPage-1})">${pr.currentPage-1}</a></li>
	      	</c:if>
	      	<li class="active"><a href="#" onclick="jumpPage(${pr.currentPage})" class="select">${pr.currentPage}</a></li>
	      	<c:if test="${pr.currentPage+1<=pr.pageCount}">
	      		<li><a href="#" onclick="jumpPage(${pr.currentPage+1})">${pr.currentPage+1}</a></li>
	      	</c:if>
	      	<c:if test="${pr.currentPage+2<=pr.pageCount }">
	      		<li><a href="#" onclick="jumpPage(${pr.currentPage+2})">${pr.currentPage+2}</a></li>
	      	</c:if>
	      	<c:if test="${pr.currentPage+3<=pr.pageCount&&pr.currentPage-2<=0}">
	      		<li><a href="#" onclick="jumpPage(${pr.currentPage+3})">${pr.currentPage+3}</a></li>
	      	</c:if>
	      	<c:if test="${pr.currentPage+4<=pr.pageCount&&pr.currentPage-3<=0 }">
	      		<li><a href="#" onclick="jumpPage(${pr.currentPage+4})">${pr.currentPage+4}</a></li>
	      	</c:if>
	      	<c:if test="${pr.currentPage!=pr.pageCount }">
	      		<li><a href="#" onclick="jumpPage(${pr.currentPage+1})">&raquo;</a></li>
	      	</c:if>
	      </c:if>
	</ul>
</div>

<script language="javascript">

	$(document).on('click','#chk-all',function(){
        $(this).closest('table').find('input[name="idcheckbox"]').prop('checked', this.checked);
	});

	$(document).on('click','.td-name',function(){
        var obj = $(this).closest('tr').find('input');
        if(obj.prop('checked') == true){   
          obj.prop('checked',false);  
        } else{   
          obj.prop('checked',true); 
		}
	});
	function search(id){
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		window.location.href="<%=path%>/news/search/?beginDate="+begintime+"&endDate="+endtime;
	}
	
	function jumpPage(jumpPageNumber){
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		window.location.href="<%=path%>/news/search/?currentPage="+jumpPageNumber+"&beginDate="+begintime+"&endDate="+endtime;
	}
	function confirmdelete(newsid){
		if(confirm("确定删除吗?")){
			location.href="<%=path%>/news/delete/?id="+newsid;
		}
	}
</script>
</body>
</html>