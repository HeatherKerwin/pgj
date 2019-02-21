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
<link rel="stylesheet"  href="<%=path %>/commons/styles/bootstrap-datetimepicker.min.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=path %>/commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=path %>/commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/commons/scripts/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path %>/commons/scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>


<link rel="stylesheet" href="<%=path %>/commons/styles/default.css" />

<script src="<%=path %>/commons/scripts/ajaxupload.js"></script>
<!-- 上传组件Uploadify -->
<script src="<%=path %>/commons/scripts/jquery.uploadify.js"></script>

<script type="text/javascript" src="<%=path%>/commons/scripts/WebCalendar.js"></script>

<!--编辑器-->
<link rel="stylesheet" href="<%=path %>/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="<%=path %>/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=path %>/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="<%=path %>/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=path %>/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="<%=path%>/commons/scripts/WebCalendar.js"></script>

<script>
	function chooseSubmitMethod(flag){
		if(confirm("确定执行操作吗")){
			$('#form1').submit();
		}
	}
	
	function checkForm(){
		return true;
	}
</script>
</head>


<body>
<form method="post" action="<%=path %>/admin/updateprice/" onsubmit="return checkForm()" id="form1">
<div id="edit">
	<table width="800px">
		<tr>
			<td colspan="6" align="center" width="800px">
			日期<input type="text" name="day" id="day" value="${day }" placeholder="日期" onclick="SelectDate(this,'yyyy-MM-dd')" onchange="search()"  />
			时间<select name="time" id="time" onchange="search()"><option value="10:30" <c:if test="${time=='10:30' }">selected="selected"</c:if>>10:30</option><option value="14:30" <c:if test="${time=='14:30' }">selected="selected"</c:if>>14:30</option><option value="16:30" <c:if test="${time=='16:30' }">selected="selected"</c:if>>16:30</option></select>
			区域<select name="type4" id="type4" onchange="search()"><option value="1" <c:if test="${type4==1 }">selected="selected"</c:if>>长三角</option><option value="2" <c:if test="${type4==2 }">selected="selected"</c:if>>珠三角</option><option value="3" <c:if test="${type4==3 }">selected="selected"</c:if>>华中</option><option value="4" <c:if test="${type4==4 }">selected="selected"</c:if>>环渤海</option><option value="5" <c:if test="${type4==5 }">selected="selected"</c:if>>西南</option></select>
			</td>
		</tr>
		<tr>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
		</tr>
		<tr>
			<td rowspan="7" width="20px">大票买断</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="b1" style="width: 120px;" value="${hb1.price }"/></td>
			<td rowspan="7" width="20px">小票买断</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="s1" style="width: 120px;" value="${hs1.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="b2" style="width: 120px;" value="${hb2.price }"/></td>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="s2" style="width: 120px;" value="${hs2.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="b3" style="width: 120px;" value="${hb3.price }"/></td>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="s3" style="width: 120px;" value="${hs3.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="b4" style="width: 120px;" value="${hb4.price }"/></td>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="s4" style="width: 120px;" value="${hs4.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="b5" style="width: 120px;" value="${hb5.price }"/></td>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="s5" style="width: 120px;" value="${hs5.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="b6" style="width: 120px;" value="${hb6.price }"/></td>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="s6" style="width: 120px;" value="${hs6.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="b7" style="width: 120px;" value="${hb7.price }"/></td>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="s7" style="width: 120px;" value="${hs7.price }"/></td>
		</tr>
	</table>
</div>

<div id="bottom">
  <div class="left">
		<button type="button" class="btn btn-success" id="publish" onclick="chooseSubmitMethod(1)"> 修改 </button>
	</div>
</div>

</form>
<script>
	function search(){
		location.href="<%=path%>/admin/daypricesearch/?day="+$("#day").val()+"&type4="+$("#type4").val()+"&time="+$("#time").val();
	}
</script>
</body>
</html>