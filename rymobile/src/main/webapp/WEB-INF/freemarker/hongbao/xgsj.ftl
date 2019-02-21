<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>使用红包</title>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/rymobile/hb.css" />
<script type="text/javascript" src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
</head>
<script type="text/javascript">
//验证手机号码
function checkMobile(str) {
	var telReg = !!str.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
	//如果手机号码不能通过验证
	return telReg;
}
function updatemobile() {	
	var phone = $("#phonetxt").val();	
	var oldphone = $("#oldphone").val();
	var sid = $("#sid").val();
	var price = $("#price").val();
	var enddate = $("#enddate").val();
	if(!checkMobile(phone)){
		alert("手机号码不正确!");		
		return;
	} else {
		$.ajax({
			url:"../hongbao/updatemobile.htm",
			type:"post",
			data:{"phone":phone,"sid":sid,"oldphone":oldphone},
			dataType:"json",
			success:function(data){				
				if(data[0].response=="success"){
				    var msg = data[0].msg;
					if (msg == '修改成功!') {
						$("#oldphone").val(phone);
						$("#phonetxt").val('');
						window.location.href='../hongbao/syhb.htm?phone='+phone+'&&sid='+sid+"&&price="+price+"&&enddate="+enddate;						
					}
					alert(msg);					
				}else{
					alert("取消失败！");	
				}
			}
		});		
	}
}
</script>
<body>
<input type="hidden" id="sid" value="${sid}" /> 
<input type="hidden" id="price" value="${price}" /><input type="hidden" id="enddate" value="${enddate}" />
<div class="t1">
	<div style="height:10%;">&nbsp;</div>
    <ul class="t2">
    	
    	<li><input class="t-t2" style="  background:#fff;" type="text" tabindex="2" id="oldphone"  value="${phone}" disabled="flise"　readOnly="true" /> </li>
        <li ><input class="t-t2" type="text" id="phonetxt" placeholder="请输入新的手机号"></li>
        <li><input class="t-t3" type="button" onclick="updatemobile()" value="确认修改 " style="color:#FF0000;"></li>
        <div style="height:10%;">&nbsp;</div>
    </ul>
</div>
<div  class="t3">
	<h3 style="text-align:center;">说明</h3>
    <p>修改手机后，下次领取红包时，红包将自动放入新手机账户</p>
	<div style="height:30%;">&nbsp;</div>
</div>

</body>
</html>
