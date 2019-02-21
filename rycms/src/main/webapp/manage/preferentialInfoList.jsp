<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String path = request.getContextPath();
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>优惠消息</title>
	<link rel="stylesheet" href="<%=path %>/commons/styles/reset.css"/>
	<link rel="stylesheet" href="<%=path %>/commons/styles/bootstrap.css"/>
	<script src="<%=path %>/commons/scripts/jquery-1.10.1.min.js"></script>
	<script src="<%=path %>/commons/scripts/bootstrap.min.js"></script>
	<link rel="stylesheet" href="<%=path %>/commons/styles/default.css"/>
	<script type="text/javascript" src="<%=path%>/commons/scripts/WebCalendar.js"></script>
</head>

<body>
<div id="top">
	<div class="left">
		<button type="button" class="btn btn-success" id="add" onclick="javascript:window.location.href='<%=path %>/manage/preferentialInfoAdd.jsp'">+ 新增优惠信息</button>
	</div>
	<div class="right">
	</div>
</div>
<table class="table table-bordered table-hover">
	<thead>
	  <tr>
		<th class="text-center td-checkbox">
			<input type="checkbox" id="chk-all"/>
		</th>
		<th class="text-left">名称</th>
		<th class="text-left">请求</th>
		<th class="text-left">时间</th>
		<th class="text-left" style="width:120px;">操作</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach items="${pr.results}" var="info">
		<tr>
			<td class="text-center td-checkbox"><input type="checkbox" id="" value="${info.id}" name="idcheckbox"/></td>
			<td class="text-left td-name">
				<a href="<%=path%>/preferentialInfo/get?id=${info.id}">${info.title}</a>
			</td>
			<td class="text-left td-name">
				${info.url}
			</td>
			<td class="text-left td-name">
				${info.createTime}
			</td>
			<td class="text-left td-name">
				<a href="javascript:void(0);" style="color:red;" onclick="confirmdelete(${info.id})">删除</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

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
	
	function jumpPage(jumpPageNumber){
		window.location.href="<%=path%>/preferentialInfo/list?currentPage="+jumpPageNumber;
	}
	
	function confirmdelete(newsid){
		if(confirm("确定删除吗?")){
			location.href="<%=path%>/preferentialInfo/del?id="+newsid;
		}
	}
</script>
</body>
</html>