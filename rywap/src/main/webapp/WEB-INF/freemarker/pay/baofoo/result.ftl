<!DOCTYPE html>
<html>
	<meta charset="UTF-8">
	[#include '/common/setting.ftl']
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta name="format-detection" content="telephone=no">
	<title>宝付支付</title>
	<link rel="shortcut icon" href="https://m.utiexian.com/favicon.ico"/>
    <link href="https://static.utiexian.com/css/rymobile/baofoo.css" type="text/css" rel="stylesheet">
    <script src="https://static.utiexian.com/js/common/jquery-1.8.3.min.js"></script>
</head>
<body>
    <div class="w nav">
        <div id="RESULTT" class="w result none">
            <div class="pay-result">
                <img src="https://img.utiexian.com/rymobile/baofoo/icon-success.png" class="fl">
                <div class="fl">交易成功！</div>
            </div>
            <input class="btn" type="button" value="返回商户平台" onclick="redirectUrl();">
        </div>
        <div id="RESULTF" class="w result none">
            <div class="pay-result">
                <img src="https://img.utiexian.com/rymobile/baofoo/icon-fail.png" class="fl">
                <div class="fl">支付失败！</div>
            </div>
            <p>银行卡交易失败，详情请联系票据管家客服！</p>
            <input class="btn" type="button" value="返回" onclick="window.history.back();">
        </div>
    </div>
</body>
<script type="text/javascript">
$(document).ready(function () {
	var code = "${code}";
	if(code!=null && "T"==code){
		$("#RESULTT").removeClass("none");
	}else{
		$("#RESULTF").removeClass("none");
	}
});

function redirectUrl(){
	window.location.href = "${basePath}${redirect_url}";
}
</script>
</html>