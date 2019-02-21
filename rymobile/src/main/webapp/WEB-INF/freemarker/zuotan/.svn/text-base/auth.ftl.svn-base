<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <title>票据管家---扫码认证</title>
    <link href="https://static.utiexian.com/css/rymobile/zuotan/media.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
    <style>
        body{
            background: #eeeeee;
            font-size: 2.5rem;
            padding: 20% 5% 0;
        }
        p{
            line-height: 3rem;
        }
        .div1 ul{
            height: auto;
            border: 1px #dbdbdb solid;
            background: #fff;
            border-radius: 1rem;
            padding: 1rem;
            margin: 2rem 0;
            line-height: 2.5rem;
            color: #2d2d2d;
        }
        .div1 ul li span{
            margin-left: 2rem;
        }
        .div2 p span{
            font-weight: 600;
        }
        .div3{}
    </style>
</head>

<body>
	<!--成功、非首次扫描-->
	<div class="div1">
	    <p id="div1_1">授权成功</p>
	</div>
</body>
<script type="text/javascript">
	var code = '${code}';
	if(code!=null && code!=""){
		localStorage.setItem("AUTH-CODE",code);
	}else{
		$("#div1_1").text("授权失败！");
	}
</script>
</html>