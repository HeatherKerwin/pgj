[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='宝付支付-支付']
[@main.header currentmenu='1' topindex="0"/]

<div class="w h100 bcwhite">
    <div class="w1200 bc h100 pr">
        <img src="${image_url}/website/baofoo/baofu-logo.png" class="fl mt17" width="152" height="63">
    </div>
</div>
<div class="w1200 bc mt20">
    <div id="RESULTT" class="w1200 h420 bc mt20 mb20 bcwhite pt50 none" align="center">
        <div class="w240">
            <img src="${image_url}/rymobile/baofoo/icon-success.png" width="70" height="70" class="fl">
            <div class="fl f30 lh70 ml20">交易成功！</div>
            <div class="cb"></div>
        </div>
        <div class="wa mt30 lh50">
            <input class="w250 h50 bcd43c33 b0 br5 cwhite tc f24 cp" readonly="readonly" value="返回商户平台" onclick="redirectUrl();">
        </div>
    </div>
	<div id="RESULTF" class="w1200 h420 bc mt20 mb20 bcwhite pt50 none" align="center">
        <div class="w240">
            <img src="${image_url}/rymobile/baofoo/icon-fail.png" width="70" height="70" class="fl">
            <div class="fl f30 lh70 ml20">支付失败！</div>
            <div class="cb"></div>
        </div>
        <div class=" f20 mt30">银行交易失败，详情请联系客服！</div>
        <div class="wa mt30 lh50 ">
            <input class="w250 h50 bcd43c33 b0 br5 cwhite tc f24 cp" readonly="readonly" value="返回" onclick="window.history.back();">
        </div>
    </div>
</div>
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
[@main.footer/]
[/@main.body]