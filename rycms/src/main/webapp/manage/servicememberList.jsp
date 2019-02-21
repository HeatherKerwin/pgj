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
		<button type="button" class="btn btn-success" id="add" onclick="javascript:window.location.href='<%=path %>/manage/servicememberAdd.jsp'">+ 新增推荐人</button>
		&nbsp;总日活${allactivecount }
	</div>
	<div class="right">
		
	</div>
</div>
<form action="" id="form1" method="post">
<table class="table table-bordered table-hover">
	<thead>
	  <tr>
		<th class="text-center td-checkbox"><input type="checkbox" id="chk-all" /></th>
		<th class="text-left">名字</th>
		<th class="text-left">编号</th>
		<th class="text-left">日活</th>
		<th class="text-left">月日活</th>
		<th class="text-left">总日活</th>
		<th class="text-left">用户总数</th>
		<th class="text-left">占比</th>
		<th class="text-left">本月新增用户</th>
		<th class="text-left">删除</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach items="${pageModel.results}" var="servicemember">
		<tr>
			<td class="text-center td-checkbox"><input type="checkbox" id="" value="${servicemember.id }" name="idcheckbox" /></td>
			<td class="text-left td-name">
				<a href="<%=path%>/servicemember/toupdate/?id=${servicemember.id}">${servicemember.servicemember }</a>
			</td>
			<td class="text-left td-name">
				${servicemember.servicenumber }
			</td>
			<td class="text-left td-name">
				${servicemember.daycount }
			</td>
			<td class="text-left td-name">
				${servicemember.monthcount }
			</td>
			<td class="text-left td-name">
				${servicemember.allcount }
			</td>
			<td class="text-left td-name">
				${servicemember.membercount }
			</td>
			<td class="text-left td-name">
				<c:if test="${servicemember.membercount==0 }">--</c:if>
				<c:if test="${servicemember.membercount!=0 }"><fmt:formatNumber value="${(servicemember.monthcount/(servicemember.membercount*22))*100 }" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>  %</c:if>
			</td>
			<td class="text-left td-name">
				${servicemember.addmembercount }
			</td>
			<td class="text-left td-name" onclick="confirmdelete(${servicemember.id})">
				删除
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</form>

<div class="pagination text-center">
	<ul>
		<c:if test="${pageModel.pageCount!=0 }">
	      	<c:if test="${pageModel.currentPage!=1 }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-1 })">&laquo;</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-4>0)&&(pageModel.currentPage+1>pageModel.totalPages) }">
	      		<li><a href="#" onclick="jumpPage(${request.pageModel.currentPage-4})">${pageModel.currentPage-4 }</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-3>0)&&(pageModel.currentPage+2>pageModel.pageCount)}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-3})">${pageModel.currentPage-3 }</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-2>0)}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-2})">${pageModel.currentPage-2}</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-1>0)}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-1})">${pageModel.currentPage-1}</a></li>
	      	</c:if>
	      	<li class="active"><a href="#" onclick="jumpPage(${pageModel.currentPage})" class="select">${pageModel.currentPage}</a></li>
	      	<c:if test="${pageModel.currentPage+1<=pageModel.pageCount}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+1})">${pageModel.currentPage+1}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage+2<=pageModel.pageCount }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+2})">${pageModel.currentPage+2}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage+3<=pageModel.pageCount&&pageModel.currentPage-2<=0}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+3})">${pageModel.currentPage+3}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage+4<=pageModel.pageCount&&pageModel.currentPage-3<=0 }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+4})">${pageModel.currentPage+4}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage!=pageModel.pageCount }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+1})">&raquo;</a></li>
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
	
	function updateStatus(flag){
		var checkboxObj = document.getElementsByName("idcheckbox");
		var chooseFlag = false;
		for(var i = 0; i < checkboxObj.length;i++){
			if(checkboxObj[i].checked){
				chooseFlag = true;
				break;
			}
		}
		if(chooseFlag){
			if(confirm("确定更改状态吗")){
				$("#choosestatus").val(flag);
				$("#form1").submit();
			}
		}
	}
	function jumpPage(jumpPageNumber){
		
		window.location.href="<%=path%>/servicemember/list/?currentPage="+jumpPageNumber;
	}
	function search(){
		window.location.href="<%=path%>/servicemember/list/?currentPage=1";
	}
	
	function confirmdelete(servicememberid){
		if(confirm("确定删除吗?")){
			location.href="<%=path%>/servicemember/delete/?id="+servicememberid;
		}
	}
</script>
</body>
</html>