<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String path = request.getContextPath();
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>意见反馈</title>
	<link rel="stylesheet" href="<%=path %>/commons/styles/reset.css" />
	<link rel="stylesheet" href="<%=path %>/commons/styles/bootstrap.css"/>
	<script src="<%=path %>/commons/scripts/jquery-1.10.1.min.js"></script>
	<script src="<%=path %>/commons/scripts/bootstrap.min.js"></script>
	<link rel="stylesheet" href="<%=path %>/commons/styles/default.css"/>
	<script type="text/javascript" src="<%=path%>/commons/scripts/WebCalendar.js"></script>
	<style type="text/css">
	.my-td{
		width:22%;
		overflow:hidden;
		text-overflow: ellipsis; /* for IE */  
	    -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
		white-space:nowrap;
		word-wrap:break-word;
		word-break:break-all;
	}
	</style>
</head>

<body>
<div id="top">
	<div class="left">
		手机号：<input type="text" name="mobile" id="mobile" value="${mobile}" placeholder="请输入手机号码"/>
		起始时间:<input type="text" name="begintime" id="begintime" value="${begintimeStr}" placeholder="起始时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
		截止时间:<input type="text" name="endtime" id="endtime" value="${endtimeStr}" placeholder="截止时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
		<button type="button" class="btn btn-success" id="add" onclick="search()">搜索</button>
	</div>
</div>

<form action="" id="form1" method="post">
<table class="table table-bordered table-hover" style="table-layout:fixed;">
	<thead>
	  <tr>
		<th class="text-center td-checkbox"><input type="checkbox" id="chk-all" /></th>
		<th class="text-left my-td">用户名</th>
		<th class="text-left my-td">手机号</th>
		<th class="text-left my-td">时间</th>
		<th class="text-left my-td">回访备注</th>
		<th class="text-left my-td" style="width:120px;">操作</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach items="${messageList}" var="message">
		<tr>
			<td class="text-center td-checkbox">
				<input type="checkbox" id="" value="${message.id }" name="idcheckbox" />
			</td>
			<td class="text-left td-name my-td">
				${message.memberName}
			</td>
			<td class="text-left td-name my-td">
				${message.messagemobile}
			</td>
			<td class="text-left td-name my-td">
				${fn:substring(message.messagetime, 0, 19)}
			</td>
			<td class="text-left td-name my-td" title="${message.returnVisit}">
				${message.returnVisit}
			</td>
			<td class="text-left td-name my-td">
				<a href="<%=path%>/message/toupdate/?id=${message.id}" style="color:red;">
					查看
				</a>
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
		var mobile = $("#mobile").val();
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		window.location.href="<%=path%>/message/search/?mobile="+mobile+"&beginDate="+begintime+"&endDate="+endtime;
	}
	
	function jumpPage(jumpPageNumber){
		var mobile = $("#mobile").val();
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		window.location.href="<%=path%>/message/search/?currentPage="+jumpPageNumber+"&mobile="+mobile+"&beginDate="+begintime+"&endDate="+endtime;
	}
	
	function confirmdelete(messageid){
		if(confirm("确定删除吗?")){
			location.href="<%=path%>/message/delete/?id="+messageid;
		}
	}
</script>
</body>
</html>