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
</head>

<body>
<form action="" id="form1" method="post">
<table class="table table-bordered table-hover">
	<thead>
	  <tr>
		
		<th class="text-left">承兑行类型</th>
		<th class="text-left">属性</th>
		<th class="text-left">属性</th>
		<th class="text-left">地域</th>
		<th class="text-left">价格</th>
		<th class="text-left">操作</th>
	  </tr>
	</thead>
	<tbody>
		<tr>
			<td class="text-left td-name">
				<select name="type1" style="width: 100px;" id="type1" onchange="search()">
					<!-- <option value="-1">请选择</option> -->
					<option value="-1">请选择</option>
					<option value="1">国股</option>
					<option value="2">城商</option>
					<option value="3">外资</option>
					<option value="4">农商</option>
					<option value="5">农合</option>
					<option value="6">农信</option>
					<option value="7">村镇</option>
				</select>
			</td>
			<td class="text-left td-name">
				<select name="type2" style="width: 100px;" id="type2" onchange="search()">
					<!-- <option value="-1">请选择</option> -->
					<option value="-1">请选择</option>
					<option value="1">大票</option>
					<option value="2">小票</option>
				</select>
			</td>
			<td class="text-left td-name">
				<select name="type3" style="width: 100px;" id="type3" onchange="search()">
					<!-- <option value="-1">请选择</option> -->
					<option value="-1">请选择</option>
					<option value="1">买断</option>
					<option value="2">带票</option>
				</select>
			</td>
			<td class="text-left td-name">
				<select name="type4" style="width: 100px;" id="type4" onchange="search()">
					<!-- <option value="-1">请选择</option> -->
					<option value="-1">请选择</option>
					<option value="1">长三角</option>
					<option value="2">珠三角</option>
					<option value="3">华中</option>
					<option value="4">环渤海</option>
					<option value="5">西南</option>
				</select>
			</td>
			<td class="text-left td-name" id="showcurrentprice" style="width: 100px;">
				<c:if test="${currentprice.type2==1 }"><input type="text" value="${currentprice.price }" id="price${currentprice.id}"/>‰</c:if>
				<c:if test="${currentprice.type2==2 }"><input type="text" value="${currentprice.price }" id="price${currentprice.id}"/></c:if>
			</td>
			<td class="text-left td-name">
				<a href="javascript:void(0)" onclick="update()">修改</a>
			</td>
		</tr>
	</tbody>
</table>
</form>

<div class="pagination text-center">
	<ul>
		
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
	function update(){
		var price = $("#price").val();
		var type1= $("#type1").val();
		var type2= $("#type2").val();
		var type3= $("#type3").val();
		var type4= $("#type4").val();
		window.location.href="<%=path%>/admin/currentpriceServlet.do?method=update&price="+price+"&type1="+type1+"&type2="+type2+"&type3="+type3+"&type4="+type4;
	}
	
	function search(){
		var type1= $("#type1").val();
		var type2= $("#type2").val();
		var type3= $("#type3").val();
		var type4= $("#type4").val();
		if(type1!=-1&&type2!=-1&&type3!=-1&&type4!=-1){
			$.ajax({
				url:"<%=path%>/admin/currentpriceServlet.do?method=find",
				type:"post",
				data:{"type1":type1,"type2":type2,"type3":type3,"type4":type4},
				//dataType:"json",
				success:function(data){
					if(type2==1){
						$("#showcurrentprice").html("<input type='text' id='price' value='"+data+"' style='width: 30px;margin:0;'/>‰");
					}
					if(type2==2){
						$("#showcurrentprice").html("<input type='text' id='price' value='"+data+"' style='width: 30px;margin:0;'/>");
					}
				}
			});
		}
	}
</script>
</body>
</html>