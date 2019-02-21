[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
[@main.header currentmenu='3' topindex='2'/]

<!--确认下单-->
<div class="mt25 w1150 bc bcwhite pt30 pl50 pr25 pb35 mb20">
    <!--订单状态-->
    <div class="w654 bc f12 tc">
        <div class="fl">
            <img src="${image_url}/website/discount/state11.png" width="165" height="30">
            <p class="cbac6fd mt10">确认贴现订单</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state22.png" width="165" height="30">
            <p class="c3366cc mt10">付押金保障交易</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state3.png" width="165" height="30">
            <p class="ccccccc mt10">交易跟踪</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state4.png" width="165" height="30">
            <p class="ccccccc mt10">评价机构</p>
        </div>
    </div>
    <div class="cb"></div>
    <div class="f20 fb mt30">您的订单已生成成功，感谢您使用票据管家贴现。</div>
    <div class="f14 fb mt40">
        <div class="fl w80">您可以进行</div>
        <a href="${basePath }/discountorder/discount?ym=pl" class="fl c3366cc cp">交易跟踪</a>
    </div>
    <div class="cb"></div>
    <div class="fl f14 fb mt25">
        <div class="fl w80">或者</div>
        <a href="${basePath }/discountrecord/discount?ym=pl" class="fl c3366cc">再下一单</a>
    </div>
    <!--票面-->
    <img src="${image_url}/website/activity/ZTbanner.jpg" class="fr w530 h300 mr40 img" >
    <div class="cb"></div>
</div>
  [@main.right /]
<script >
	/**
	* 跳转到当前活动的页面
	*/
	$(".img").click(function(){
		location.href = "https://bbs.utiexian.com";
	});
</script>

[@main.footer/]
[/@main.body]