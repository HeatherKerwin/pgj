<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <title>票据管家---扫码反馈</title>
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

<!--成功、非首次扫描-->
<div class="div1" style="display:none;">
    <p id="div1_1" style="display:none;">此用户为邀请嘉宾，欢迎进场。</p>
    <p id="div1_2" style="display:none;">此用户已被验证过，请核对用户信息后允许进场。</p>
    <ul>
        <li>企业名称<span id="company"></span></li>
        <li>联系人<span id="name"></span></li>
        <li>联系方式<span id="phone"></span></li>
    </ul>
</div>

<!--个人扫描-->
<div class="div2" style="display:none;">
    <p>此二维码用于11月25号参加票管家企业座谈会入场时使用，欢迎届时光临。</p>
	<br>
    <p><span>活动时间：</span>2016年11月25日星期五14：00</p>
    <p><span>活动地址：</span>上海浦东新区陆家嘴环路1288号凯宾斯基大酒店二楼哈瓦那厅</p>
</div>

<!--二维码过期-->
<div class="div3" style="display:none;">
    <p>很抱歉，经审核，您无法参加本次票管家举行的企业座谈会，敬请期待票管家其他活动。</p>
    <p>如有疑问，
    <p>请拨打票管家客服热线：</p>
    <p>400-067-0002</p>
    <p>接听时间为工作日09：00-17:30</p>
</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	var id = '${id}';
	var phone = '${phone}';
	var code = localStorage.getItem("AUTH-CODE");
	if(id==null || id=="")id = "";
	if(phone==null || phone=="")phone = "";
	if(code==null || code=="")code = "";

	$.ajax({
		url:"../discussion/check/do.htm",
		type:"post",
		data:{"id":id,"phone":phone,"code":code},
		dataType:"json",
		success:function(data){
			setInfo(data);
		}
	});
});

/**
 * 设置基本信息
 */
function setInfo(data){
	var response = data.response;
	var msg = data.msg;
	var code = data.CODE;
	if(response=="success"){
		$("#div1_1").show();
		$(".div1").show();
	}else{
		if("NOAUTH"==code){
			$(".div2").show();
		}else if("USED"==code){
			$("#div1_2").show();
			$(".div1").show();
		}else if("ERROR"==code){
			$(".div3").show();
		}
	}
	
	var result = data.data;
	
	if(result!=null){
		$("#company").text(result.company);
		$("#name").text(result.name);
		$("#phone").text(result.phone);
	}
}
</script>
</html>