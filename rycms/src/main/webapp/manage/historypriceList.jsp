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
<div id="top">
	<div class="left">
		<button type="button" class="btn btn-success" id="add" onclick="javascript:window.location.href='<%=path %>/admin/historypriceServlet.do?method=badd'">+ 新增历史价格</button>
		&nbsp;&nbsp;&nbsp;
		<select name="type1" id="type1">
			<option value="">选择承兑行</option>
			<option value="1" <c:if test="${type1==1 }">selected="selected"</c:if>>国股</option>
			<option value="2" <c:if test="${type1==2 }">selected="selected"</c:if>>城商</option>
			<option value="3" <c:if test="${type1==3 }">selected="selected"</c:if>>外资</option>
			<option value="4" <c:if test="${type1==4 }">selected="selected"</c:if>>农商</option>
			<option value="5" <c:if test="${type1==5 }">selected="selected"</c:if>>农合</option>
			<option value="6" <c:if test="${type1==6 }">selected="selected"</c:if>>农信</option>
			<option value="7" <c:if test="${type1==7 }">selected="selected"</c:if>>村镇</option>
		</select>
		<select name="type4" id="type4">
			<option value="">选择区域</option>
			<option value="1" <c:if test="${type4==1 }">selected="selected"</c:if>>长三角</option>
			<option value="2" <c:if test="${type4==2 }">selected="selected"</c:if>>珠三角</option>
			<option value="3" <c:if test="${type4==3 }">selected="selected"</c:if>>华中</option>
			<option value="4" <c:if test="${type4==4 }">selected="selected"</c:if>>环渤海</option>
			<option value="5" <c:if test="${type4==5 }">selected="selected"</c:if>>西南</option>
		</select>
		<input type="text" name="day" id="day" value="${day}" placeholder="日期" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
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
		<th class="text-left">承兑行类型</th>
		<th class="text-left">属性</th>
		<th class="text-left">地域</th>
		<th class="text-left">日期</th>
		<th class="text-left">涨幅</th>
		<th class="text-left">价格</th>
		<th class="text-left">操作</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach items="${historypriceList}" var="historyprice">
		<tr>
			<td class="text-center td-checkbox"><input type="checkbox" id="" value="${historyprice.id }" name="idcheckbox" /></td>
			<td class="text-left td-name">
				<c:if test="${historyprice.type1==1 }">国股</c:if>
				<c:if test="${historyprice.type1==2 }">城商</c:if>
				<c:if test="${historyprice.type1==3 }">外资</c:if>
				<c:if test="${historyprice.type1==4 }">农商</c:if>
				<c:if test="${historyprice.type1==5 }">农合</c:if>
				<c:if test="${historyprice.type1==6 }">农信</c:if>
				<c:if test="${historyprice.type1==7 }">村镇</c:if>
			</td>
			<td class="text-left td-name">
				<c:if test="${historyprice.type2==1 }">大票</c:if>
				<c:if test="${historyprice.type2==2 }">小票</c:if>
			</td>
			<td class="text-left td-name">
				<c:if test="${dayprice.type4==1 }">长三角</c:if>
				<c:if test="${dayprice.type4==2 }">珠三角</c:if>
				<c:if test="${dayprice.type4==3 }">华中</c:if>
				<c:if test="${dayprice.type4==4 }">环渤海</c:if>
				<c:if test="${dayprice.type4==5 }">西南</c:if>
			</td>
			<td class="text-left td-name">
				${historyprice.day }
			</td>
			<td class="text-left td-name">
				${historyprice.updown }
			</td>
			<td class="text-left td-name">
				<c:if test="${historyprice.type2==1 }"><input type="text" value="${historyprice.price }" id="price${historyprice.id}"/>‰</c:if>
				<c:if test="${historyprice.type2==2 }"><input type="text" value="${historyprice.price }" id="price${historyprice.id}"/></c:if>
			</td>
			<td class="text-left td-name">
				<a href="javascript:void(0)" onclick="update(${historyprice.id})">修改</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</form>

<div class="pagination text-center">
	<ul>
		<c:if test="${pageModel.totalPages!=0 }">
	      	<c:if test="${pageModel.currentPage!=1 }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-1 })">&laquo;</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-4>0)&&(pageModel.currentPage+1>pageModel.totalPages) }">
	      		<li><a href="#" onclick="jumpPage(${request.pageModel.currentPage-4})">${pageModel.currentPage-4 }</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-3>0)&&(pageModel.currentPage+2>pageModel.totalPages)}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-3})">${pageModel.currentPage-3 }</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-2>0)}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-2})">${pageModel.currentPage-2}</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-1>0)}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-1})">${pageModel.currentPage-1}</a></li>
	      	</c:if>
	      	<li class="active"><a href="#" onclick="jumpPage(${pageModel.currentPage})" class="select">${pageModel.currentPage}</a></li>
	      	<c:if test="${pageModel.currentPage+1<=pageModel.totalPages}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+1})">${pageModel.currentPage+1}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage+2<=pageModel.totalPages }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+2})">${pageModel.currentPage+2}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage+3<=pageModel.totalPages&&pageModel.currentPage-2<=0}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+3})">${pageModel.currentPage+3}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage+4<=pageModel.totalPages&&pageModel.currentPage-3<=0 }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+4})">${pageModel.currentPage+4}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage!=pageModel.totalPages }">
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
		window.location.href="<%=path%>/admin/historypriceServlet.do?method=list&currentPage="+jumpPageNumber;
	}
	
	function search(){
		var type1 = $("#type1").val();
		var type4 = $("#type4").val();
		var day = $("#day").val();
		window.location.href="<%=path%>/admin/historypriceServlet.do?method=list&type1="+type1+"&type4="+type4+"&day="+day;
	}
	
	function update(id){
		window.location.href="<%=path%>/admin/historypriceServlet.do?method=update&id="+id+"&price="+$('#price'+id).val()+"&currentPage=${pageModel.currentPage}";
	}
</script>
</body>
</html>