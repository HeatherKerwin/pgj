[#include "/common/setting.ftl"]
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>票据管家（示例参考）</title>
    <link rel="shortcut icon" href="https://img.utiexian.com/common/icon/32.png">
    <link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/hezuo/main.css"/>
</head>
<body>
	<button onclick="setUrl('/hezuo/query');">查询查复</button>
	<button onclick="setUrl('/hezuo/inquiry');">询价</button>
	<button onclick="setUrl('/hezuo/calculator');">计算器</button>
	<button onclick="setUrl('/hezuo/bank');">联行号</button>
	<iframe id="myIframe" src='${basePath}/hezuo/query' scrolling="no" frameborder="0" class="w940 h420"></iframe>
</body>
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="https://static.utiexian.com/js/rywap/base64.js"></script>
<script type="text/javascript">
function setUrl(u){
	$("#myIframe").attr("src","${basePath}"+u);
}
</script>