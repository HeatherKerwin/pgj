[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.help4]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1'/]

<div class="w1200 bc mt20 mb20">
    [@main.secondaryheader topindex="1"/]
    <div class="w ha">
        [@main.left remark='2' leftindex='4'/]
        <!--右侧内容 -->
        <div class="fl w997 h700 pb20 bcwhite bae0e0e0">
            <div class="w997 h34 lh34 f14 bcfaf7f7">
                <div class="fl ml10">我要贴现</div>
            </div>
            <div class="wa ha pl20 pr20">
                <!--问题循环-->
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">1.如何贴现？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">在开户后，进入到出票方的角色页面，点击我要贴现的按钮，输入相关贴现票据属性(可以先行查询当天价格），最后点击贴现按钮即可。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">2.贴现过程中平台收费么？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">平台不收取任何手续费，在交易过程中会需要贴现方和收票方都缴纳一部分保证金作为保证交易的顺利进行，这笔保证金也会在交易完成后返回到双方账户。<br>电票在交易时，会在支付的票款中扣除兴业银行手续费，其中包含电票的鉴证费和跨行转账的手续费，会在支付票款时扣除，这一部分费用由兴业数金收取。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">3.如何跟踪订单？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">在我的订单里可以看到订单的处理情况，另外也可以拨打客服电话进行人工查询。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">4.纸票贴现过程中出现报价和实际交易的价格出入太大情况怎么办？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">与客服进行沟通，票据管家后台在经过核实后，将会将事先机构方付的保证金作为赔偿转到贴现方账户作为补偿(核实内容主要有票据属性是否和上传票面统一，机构方当天报价和实际价格报价出入等)。</div>
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