<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <title>票据管家---手机号验证</title>
    <link href="https://static.utiexian.com/css/rymobile/zuotan/media.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
</head>
<style>
    body{
        background: #fff;
    }
    .phone{
        width: 90%;
        height: 4rem;
        margin: 65% 5% 2rem;
        border: 1px #27a476 solid;
        border-radius: 0.4rem;
        color: #2d2d2d;
        font-size: 2rem;
        line-height: 4rem;
        text-indent: 1rem;
    }
    .infor{
        width: 75%;
        height: 4rem;
        margin: 2rem 12.5%;
        color: #fff;
        font-size: 3rem;
        line-height: 4rem;
        border-radius: 0.5rem;
        border: none;
        background: #27a476;
    }
</style>
<body>
    <form id="zuotan" action="../discussion/check.htm">
    	<input type="number" name="phone" id="phone" placeholder="请输入手机号" class="phone">
    	<input type="button" value="提交" class="infor" onclick="toDo();">
    </form>
</body>
<script type="text/javascript">
function checkPhone(str) {
    var telReg = !!str.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
    //如果手机号码不能通过验证
    if(telReg == false){
        return false;
    } else {
        return true;
    }
}
function toDo(){
	var phone = $("#phone").val();
	if (checkPhone(phone)) {
		$("#zuotan").submit();
	}else{
		alert("请输入正确的手机号！");
        return false;	
	}
}
</script>
</html>