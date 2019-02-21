<%@page import="com.ry.util.page.PageResults"%>
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
		起始时间:<input type="text" name="begintime" id="begintime" value="${begintimeStr}" placeholder="起始时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
		截止时间:<input type="text" name="endtime" id="endtime" value="${endtimeStr}" placeholder="截止时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
		状态:
			<select name="orderflag" id="orderflag">
				<option value="" <c:if test="${orderflag=='' }">selected="selected"</c:if>>全部</option>
				 
				<option value="1" <c:if test="${orderflag=='1' }">selected="selected"</c:if>>待确认</option>
				<option value="2" <c:if test="${orderflag=='2' }">selected="selected"</c:if>>已确认</option>
				<option value="3" <c:if test="${orderflag=='3' }">selected="selected"</c:if>>已完成</option>
				
				<option value="0" <c:if test="${orderflag=='0' }">selected="selected"</c:if>>无效订单</option>
				<option value="-1" <c:if test="${orderflag=='-1' }">selected="selected"</c:if>>超额订单</option>
			</select>
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
		<th class="text-left">订单号</th>
		<th class="text-left">下单日期</th>
		<th class="text-left">订单状态</th>
		<th class="text-left">总额</th>
		<th class="text-left">总额情况</th>
		<th class="text-left">推荐人编号</th>
		<th class="text-left">删除</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach items="${discountrecordList}" var="discountrecord">
		<tr>
			<td class="text-center td-checkbox"><input type="checkbox" id="" value="${discountrecord.id }" name="idcheckbox" /></td>
			<td class="text-left td-name">
				<a href="<%=path%>/discountrecord/bupdate/?discountrecordid=${discountrecord.id}">${discountrecord.ordernumber }</a>
			</td>
			<td class="text-left td-name">
				${discountrecord.ordertimeshow }
			</td>
			<td class="text-left td-name">
				<c:if test="${discountrecord.orderflag==-1 }">超额订单</c:if>
				<c:if test="${discountrecord.orderflag==0 }">无效订单</c:if>
				<c:if test="${discountrecord.orderflag==1 }">待确认</c:if>
				<c:if test="${discountrecord.orderflag==2 }">已确认</c:if>
				<c:if test="${discountrecord.orderflag==3 }">已完成</c:if>
			</td>
			<td class="text-left td-name">
				${discountrecord.allmoney }
			</td>
			<td class="text-left td-name">
				<c:if test="${discountrecord.orderflag==-1 }"><font color="red">超出</font></c:if>
				<c:if test="${discountrecord.orderflag!=-1 }">正常</c:if>
			</td>
			<td class="text-left td-name">
				${discountrecord.recommendpeople}
			</td>
			<td class="text-left td-name" onclick="confirmdelete(${discountrecord.id})">
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
	      	<c:if test="${(pageModel.currentPage-4>0)&&(pageModel.currentPage+1>pageModel.pageCount) }">
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
	window.onload = function(){
		setTimeout("location.reload()",5000);
	}
	
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
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		var orderflag = $("#orderflag").val();
		window.location.href="<%=path%>/discountrecord/list/?currentPage="+jumpPageNumber+"&begintime="+begintime+"&endtime="+endtime+"&orderflag="+orderflag;
	}
	
	function search(){
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		var orderflag = $("#orderflag").val();
		window.location.href="<%=path%>/discountrecord/list/?begintime="+begintime+"&endtime="+endtime+"&orderflag="+orderflag;
	}
	
	function confirmdelete(discountrecordid){
		if(confirm("确定删除吗?")){
			location.href="<%=path%>/discountrecord/delete/?discountrecordid="+discountrecordid;
		}
	}
</script>
</body>
</html>