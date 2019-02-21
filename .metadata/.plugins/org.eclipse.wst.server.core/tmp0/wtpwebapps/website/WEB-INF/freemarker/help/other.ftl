[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.help8]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1'/]

<div class="w1200 bc mt20 mb20">
    [@main.secondaryheader topindex="1"/]
    <div class="w ha">
        [@main.left remark='2' leftindex='8'/]
        <!--右侧内容 -->
        <div class="fl w997 h700 pb20 bcwhite bae0e0e0">
            <div class="w997 h34 lh34 f14 bcfaf7f7">
                <div class="fl ml10">其他问题</div>
            </div>
            <div class="wa ha pl20 pr20">
                <!--问题循环-->
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">1.什么是保证金？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">贴现过程中为了保证交易的顺利进行贴现方和机构方都会付一笔资金放到平台上，交易顺利进行保证金会流转到双方账户，而如果出现虚假报价，虚假票面而影响交易质量保证金将作为赔偿补偿到对方账户上（比如贴现方票据信息造假则补偿给机构方，机构方虚假报价则补偿到贴现方）。</div>
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