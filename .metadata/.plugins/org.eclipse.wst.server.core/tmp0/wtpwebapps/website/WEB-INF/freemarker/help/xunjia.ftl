[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.help3]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1'/]

<div class="w1200 bc mt20 mb20">
    [@main.secondaryheader topindex="1"/]
    <div class="w ha">
        [@main.left remark='2' leftindex='3'/]
        <!--右侧内容 -->
        <div class="fl w997 h700 pb20 bcwhite bae0e0e0">
            <div class="w997 h34 lh34 f14 bcfaf7f7">
                <div class="fl ml10">我要询价</div>
            </div>
            <div class="wa ha pl20 pr20">
                <!--问题循环-->
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">1.价格是成交价格吗？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">平台是多家机构一点报价，查询到的价格是经过对比后的最优价格，最后成交价格在票据和贴现过程中提出的票据出入不大的前提下，出入并不会很大。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">2.价格都是实时价格么？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">平台有相关督促以及奖励机制保证报出的价格都是当天即时报价保证和市场同步。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">3.询价流程是怎样的？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">成为我们的用户后，点击软件进入到主界面后，会有一个叫做“我要询价”按钮，点击进去输入想要查询的票据属性(承兑行，开票时间，到期时间，面额等等),最后在菜单的最后一栏点击询价即可。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">4.询价可以查询的地理范围？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">目前的报价为全国报价，不区分城市。</div>
                    <div class="cb"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]
<script type="text/javascript">
$(".toggle").toggle(function(){
        $(this).children("img").attr('src',"${image_url}/website/help/down.png");
        $(this).children("p").addClass("cd43c33");
        $(this).parent().children(".problemBG").removeClass("none");
    },function(){
            $(this).children("img").attr('src',"${image_url}/website/help/open.png");
            $(this).children("p").removeClass("cd43c33");
            $(this).parent().children(".problemBG").addClass("none");
    }
);
</script>
[@main.footer/]
[/@main.body]