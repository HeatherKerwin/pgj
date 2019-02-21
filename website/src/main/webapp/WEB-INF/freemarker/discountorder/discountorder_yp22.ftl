[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='3'/]

<!--确认下单-->
<div class="mt25 w1150 bc bcwhite pt30 pl50 pr25 pb35 mb20">
    <!--订单状态-->
        <div class="fl">
            <img src="${image_url}/website/discount/state11.png" width="165" height="30">
            <p class="cbac6fd mt10">确认贴现订单</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state23.png" width="165" height="30">
            <p class="c3366cc mt10">付押金保障交易</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state31.png" width="165" height="30">
            <p class="ccccccc mt10">交易跟踪</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state41.png" width="165" height="30">
            <p class="ccccccc mt10">评价机构</p>
        </div>
    </div>
    <div class="cb"></div>
    <div class="f20 fb mt30">您的订单已生成成功，感谢您使用票据管家贴现。</div>
    <div class="f14 fb mt40">
        <div class="fl w80">您可以进行</div>
        <a href="#" class="fl c3366cc">交易跟踪</a>
    </div>
    <div class="cb"></div>
    <div class="fl f14 fb mt25">
        <div class="fl w80">或者</div>
        <a href="#" class="fl c3366cc">再下一单</a>
    </div>
    <!--票面-->
    <img src="${image_url}/website/discount/1.png" class="fr w530 h300 mr40" >
    <div class="cb"></div>
</div>
[@main.footer/]
[/@main.body]