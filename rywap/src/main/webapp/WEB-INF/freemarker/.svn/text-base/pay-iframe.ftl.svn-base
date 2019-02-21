[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title="票据管家"]

<div class="wrapper" style="margin-top: 0">
    <iframe id="myIframe" src="${url}" scrolling="no" frameborder="0" width="100%" height="100%"></iframe>
</div>

<script type="text/javascript" src="${staticPath}/js/rywap/base64.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var _url = "${url}";
	$("#myIframe").attr("src",new Base64().decode(_url));//解密
});
</script>
[@main.footer/]
[/@main.body]