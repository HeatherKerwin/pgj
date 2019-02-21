<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站访问统计</title>
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
    <th>网站总访问量</th>
    <th>网站总独立用户</th>
    <th>统计日期</th>
</tr>
<tr>
    <td>${total[0]!0}</td>
    <td>${total[1]}</td>
    <td>${dataDate?string("yyyy-MM-dd")}</td>
</tr>

</table>

</body>
</html>
