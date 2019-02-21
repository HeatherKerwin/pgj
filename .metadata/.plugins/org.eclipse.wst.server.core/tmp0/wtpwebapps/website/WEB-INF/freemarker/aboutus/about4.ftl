[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.kefu]
[@main.header currentmenu='1' topindex="0"/]

<div class="w1200 bc mt20 mb20">
    <!--左菜单-->
    [@main.left remark='3' leftindex='4' /]
    <div class="fl w970 ml6 bcwhite c666666 pb30">
        <h1 class="f24 mt30 tc">联系我们</h1>
        <div class="bae0e0e0 br5 ml20 mr20 mt30 pt30 pl20 pr20 pb30 lh30">
            <img src="${image_url}/website/about/zgdt.png" class="w860 ha bc" />
            <div class="fb xuxian">服务联络</div>
            <p>
                联系电话：400-067-0002
            </p>
            <p>
                公司传真：50969678
            </p>
            <p>
                公司邮箱：ryfinance@ryfinance.com
            </p>
            <p>
                公司地址：上海市虹口区东大名路501白玉兰广场1207
            </p>

            <div class="cb"></div>
        </div>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]
[@main.footer/]
[/@main.body]