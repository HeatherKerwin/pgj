<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售数据统计</title>
</head>
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:14px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
	margin:120px auto;
}
table.gridtable th {
	background-color:#c3dde0;
	border-width: 1px;
	padding: 8px;
	text-align:center;
	border-style: solid;
	border-color: #a9c6c9;
}
table.gridtable tr {
	background-color:#d4e3e5;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	text-align:center;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>
<body>
<#import "/common/data.ftl" as data>
<#import "/common/function.ftl" as fun>

<table class="gridtable">
<tr>
	<th>统计时间</th>
    <th>推广号</th>
    <th>推荐注册人数</th>
</tr>
<#if datalist??>
<#list datalist as temp>
<tr>
	<td>${temp.start}</td>
    <td>${temp.recommendpeople}</td>
    <td>${temp.num}</td>
</tr>
</#list>
</#if>
</table>

<table class="gridtable">
<#if datalistweek??>
<tr>
	<th>统计时间</th>
    <th>推广号</th>
    <th>推荐注册人数</th>
</tr>
<#list datalistweek as temp>
<tr>
	<td>${temp.start}</td>
    <td>${temp.recommendpeople}</td>
    <td>${temp.num}</td>
</tr>
</#list>
</#if>
</table>

<table class="gridtable">
<#if datalistmonth??>
<tr>
	<th>统计时间</th>
    <th>推广号</th>
    <th>推荐注册人数</th>
</tr>
<#list datalistmonth as temp>
<tr>
	<td>${temp.start}</td>
    <td>${temp.recommendpeople}</td>
    <td>${temp.num}</td>
</tr>
</#list>
</#if>
</table>

</body>
</html>
