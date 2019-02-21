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

<!--编辑器-->
<!-- 
<link rel="stylesheet" href="<%=path %>/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="<%=path %>/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=path %>/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="<%=path %>/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=path %>/kindeditor/plugins/code/prettify.js"></script>
 -->
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
<form method="post" action="<%=path %>/currentprice/update/" onsubmit="return checkForm()" id="form1">
<div id="edit">
	<table width="1600px">
		<tr>
			<td colspan="6" align="center" width="1600px">			
			类型<select name="type3" id="type3" onchange="search()">
				<option value="1" <c:if test="${cp.type3==1 }">selected="selected"</c:if>>买断</option>
				<option value="2" <c:if test="${cp.type3==2 }">selected="selected"</c:if>>带票</option>
			</select>
		    区域<select name="type4" id="type4" onchange="search()">
		    <option value="1" <c:if test="${cp.type4==1 }">selected="selected"</c:if>>长三角</option>
		    </select>
			期限<select name="type5" id="type5" onchange="search()">
				<option value="1" <c:if test="${cp.type5==1 }">selected="selected"</c:if>>3个月</option>
				<option value="2" <c:if test="${cp.type5==2 }">selected="selected"</c:if>>6个月</option>
			</select>			
			</td>
		</tr>
		<tr>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
		</tr>
		<tr>
			<td rowspan="7" width="20px">500万以上</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="b1" style="width: 120px;" value="${hb1.price }"/></td>
			<td rowspan="7" width="20px">100万到500万</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="m1" style="width: 120px;" value="${hm1.price }"/></td>
			<td rowspan="7" width="20px">50万到100万</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="s1" style="width: 120px;" value="${hs1.price }"/></td>
			<td rowspan="7" width="20px">50万以下</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="ss1" style="width: 120px;" value="${hss1.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="b2" style="width: 120px;" value="${hb2.price }"/></td>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="m2" style="width: 120px;" value="${hm2.price }"/></td>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="s2" style="width: 120px;" value="${hs2.price }"/></td>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="ss2" style="width: 120px;" value="${hss2.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="b3" style="width: 120px;" value="${hb3.price }"/></td>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="m3" style="width: 120px;" value="${hm3.price }"/></td>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="s3" style="width: 120px;" value="${hs3.price }"/></td>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="ss3" style="width: 120px;" value="${hss3.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="b4" style="width: 120px;" value="${hb4.price }"/></td>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="m4" style="width: 120px;" value="${hm4.price }"/></td>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="s4" style="width: 120px;" value="${hs4.price }"/></td>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="ss4" style="width: 120px;" value="${hss4.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="b5" style="width: 120px;" value="${hb5.price }"/></td>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="m5" style="width: 120px;" value="${hm5.price }"/></td>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="s5" style="width: 120px;" value="${hs5.price }"/></td>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="ss5" style="width: 120px;" value="${hss5.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="b6" style="width: 120px;" value="${hb6.price }"/></td>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="m6" style="width: 120px;" value="${hm6.price }"/></td>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="s6" style="width: 120px;" value="${hs6.price }"/></td>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="ss6" style="width: 120px;" value="${hss6.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="b7" style="width: 120px;" value="${hb7.price }"/></td>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="m7" style="width: 120px;" value="${hm7.price }"/></td>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="s7" style="width: 120px;" value="${hs7.price }"/></td>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="ss7" style="width: 120px;" value="${hss7.price }"/></td>
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
		location.href="<%=path %>/currentprice/list/?type4="+$("#type4").val()+"&type3="+$("#type3").val()+"&type5="+$("#type5").val();
	}
</script>
</body>
</html>