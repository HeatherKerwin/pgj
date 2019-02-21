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
<div id="top" style="height: 100px;">
	<div class="left">
		已经注册用户数:${memberallcount }&nbsp;&nbsp;
		起始时间:<input type="text" name="begintime" id="begintime" value="${begintimeStr}" placeholder="起始时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
		截止时间:<input type="text" name="endtime" id="endtime" value="${endtimeStr}" placeholder="截止时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
		推荐人编号:<input type="text" name="recommendpeople" id="recommendpeople" value="${recommendpeople}" placeholder="推荐人编号"/>
		<button type="button" class="btn btn-success" id="add" onclick="search()">搜索</button>
		<input type="text" name="admindesc" id="admindesc"/>&nbsp;&nbsp;<input type="radio" value="1" name="sendmessage"/>&nbsp;&nbsp;全部人&nbsp;&nbsp;<input type="radio" value="2" name="sendmessage"/>&nbsp;&nbsp;所选人&nbsp;&nbsp;<input type="button" class="btn btn-success" value="发送" onclick="sendmessage()"/>
		<br/>
	</div>
	<div class="right">
	</div>
</div>
<form action="" id="form1" method="post">
<table class="table table-bordered table-hover">
	<thead>
	  <tr>
		<th class="text-center td-checkbox"><input type="checkbox" id="chk-all" /></th>
		<th class="text-left">手机号码</th>
		<th class="text-left">推荐人</th>
		<th class="text-left">注册日期</th>
		<th class="text-left">下单数</th>
		<th class="text-left">交易总额</th>
		<th class="text-left">真实姓名</th>
		<th class="text-left">月活</th>
		<th class="text-left">活跃度</th>		
	  </tr>
	</thead>
	<tbody>
	<c:forEach items="${memberList}" var="member">
		<tr>
			<td class="text-center td-checkbox"><input type="checkbox" id="" value="${member.id }" name="idcheckbox" /></td>
			<td class="text-left td-name">
				<a href="<%=path%>/member/bupdate/?memberid=${member.id}">${member.mobile }</a>
			</td>
			<td class="text-left td-name">
				${member.recommendpeople }
			</td>
			<td class="text-left td-name">
				${member.regtimeshow }
			</td>
			<td class="text-left td-name">
				${member.orderallcount }
			</td>
			<td class="text-left td-name">
				${member.orderallprice }
			</td>
			<td class="text-left td-name">
				${member.username }
			</td>
			<td class="text-left td-name">
				${member.monthactivecount }
			</td>
			<td class="text-left td-name">
				${member.activecount }
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

<div>
	
</div>
<script type="text/javascript" >

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
		var recommendpeople = $("#recommendpeople").val();
		window.location.href="<%=path%>/member/list/?currentPage="+jumpPageNumber+"&begintime="+begintime+"&endtime="+endtime+"&recommendpeople="+recommendpeople;
	}
	function search(){
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		var recommendpeople = $("#recommendpeople").val();
		window.location.href="<%=path%>/member/list/?begintime="+begintime+"&endtime="+endtime+"&recommendpeople="+recommendpeople;
	}
	
	function sendmessage(){
		var sendmessage = $('input[name="sendmessage"]:checked ').val();
		var idcheckbox = "";    
		  $('input[name="idcheckbox"]:checked').each(function(){    
		   idcheckbox += $(this).val()+",";    
		  }); 
		if(sendmessage!=undefined){
			$.ajax({
				url:"<%=path%>/member/sendmessage/",
				type:"post",
				data:{"admindesc":$("#admindesc").val(),"sendmessage":sendmessage,"idcheckbox":idcheckbox},
				success:function(data){
					alert("发布成功");
				}
			});
		}
	}
	
	function confirmdelete(memberid){
		if(confirm("确定删除吗?")){
			location.href="<%=path%>/member/delete/?memberid="+memberid;
		}
	}
</script>
</body>
</html>