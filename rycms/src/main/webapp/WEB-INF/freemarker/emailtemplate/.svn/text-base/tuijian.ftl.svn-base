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
    <th>发送时间</th>
    <th>获取时间范围</th>
    <th>用户手机</th>
    <th>推荐人</th>
    <th>活跃天数</th>
</tr>
<#if groom??>
<#list groom as temp>
<tr>
	<td>${temp.date}</td>
	<td>${temp.range}</td>
    <td>${temp.mobile}</td>
    <td>${temp.recommendpeople}</td>
    <td>${temp.activefate}</td>
</tr>
</#list>
</#if>
</table>
</body>
</html>
