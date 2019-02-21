[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.guanyu3]
[@main.header currentmenu='1' topindex="0" /]

<div class="w1200 bc mt20 mb20">
    <!--左菜单-->
    [@main.left remark='3' leftindex='3' /]
    <div class="fl w970 ml6 bcwhite c666666 pb30">
        <h1 class="f24 mt30 tc">合作伙伴</h1>
        <div class="bae0e0e0 br5 ml20 mr20 mt30 pt30 pl20 pr20 pb30">
            <div class="ml20 mr20">
                <div class="w_33 fl h65"><img src="${image_url}/website/about/gs.png"></div>
                <div class="w_33 fl h65"><img src="${image_url}/website/about/js.png"></div>
                <div class="w_33 fl h65"><img src="${image_url}/website/about/jt.png"></div>
            </div>
            <div class="ml20 mr20">
                <div class="w_33 fl h65"><img src="${image_url}/website/about/ny.png"></div>
                <div class="w_33 fl h65"><img src="${image_url}/website/about/ms.png"></div>
                <div class="w_33 fl h65"><img src="${image_url}/website/about/zx.png"></div>
            </div>
            <div class="ml20 mr20">
                <div class="w_33 fl h65"><img src="${image_url}/website/about/dl.png"></div>
                <div class="w_33 fl h65"><img src="${image_url}/website/about/gd.png"></div>
                <div class="w_33 fl h65"><img src="${image_url}/website/about/pa.png"></div>

            </div>
            <div class="ml20 mr20">
                <div class="w_33 fl h65"><img src="${image_url}/website/about/ge.png"></div>
                <div class="w_33 fl h65"><img src="${image_url}/website/about/ph.png"></div>
                <div class="w_33 fl h65"><img src="${image_url}/website/about/lf.png"></div>
            </div>
            <div class="ml20 mr20">
                <div class="w_33 fl h65"><img src="${image_url}/website/about/my.png"></div>
            </div>
            <div class="cb"></div>
        </div>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]
[@main.footer/]
[/@main.body]