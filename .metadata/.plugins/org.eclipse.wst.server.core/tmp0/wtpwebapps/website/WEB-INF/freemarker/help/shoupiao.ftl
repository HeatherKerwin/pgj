[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.help5]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1'/]

<div class="w1200 bc mt20 mb20">
    [@main.secondaryheader topindex="1"/]
    <div class="w ha">
        [@main.left remark='2' leftindex='5'/]
        <!--右侧内容 -->
        <div class="fl w997 h700 pb20 bcwhite bae0e0e0">
            <div class="w997 h34 lh34 f14 bcfaf7f7">
                <div class="fl ml10">报价收票</div>
            </div>
            <div class="wa ha pl20 pr20">
                <!--问题循环-->
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">1.如何报价？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">在认证成为相关机构用户后，进入到机构方页面，根据业务内容不同，选择不同的票据属性进行相关报价，一旦报价成功后，当票据管家有符合此类报价的订单在平台上生成时，票据管家会按照智能算法推送给相应机构，机构再对该票报一个实际交易的价格，从而促成贴现交易的完成。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">2.如何跟踪收票订单？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">在【我】-【我的订单】里点击进去就能看见当天有发生业务关系的相关订单。或者也可以向客服进行确认查询。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">3.在票据管家平台接单会收取费用么？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">不收取任何费用，在交易过程中会需要贴现方和收票方都缴纳一部分保证金作为保证交易的顺利进行，这笔保证金也会在交易完成后返回到双方账户。<br>电票在交易时，会在支付的票款中扣除兴业银行手续费，其中包含电票的鉴证费和跨行转账的手续费，会在支付票款时扣除，这一部分费用由兴业数金收取。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">4.在纸票收票过程出现贴现方在线上上传的票面与线下成交时拿出的票面出入太大该怎么办？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">与客服进行沟通，票据管家后台在经过核实后，将会将事先企业方付的保证金作为赔偿转到贴现方账户作为补偿(核实内容主要有票据属性是否和上传票面统一，机构方当天对该票的报价和实际价格报价出入等)。</div>
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