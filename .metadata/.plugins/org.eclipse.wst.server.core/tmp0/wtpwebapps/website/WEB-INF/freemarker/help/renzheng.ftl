[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.help2]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1'/]

<div class="w1200 bc mt20 mb20">
    [@main.secondaryheader topindex="1"/]
    <div class="w ha">
        [@main.left remark='2' leftindex='2'/]
        <!--右侧内容 -->
        <div class="fl w997 h700 pb20 bcwhite bae0e0e0">
            <div class="w997 h34 lh34 f14 bcfaf7f7">
                <div class="fl ml10">如何开户</div>
            </div>
            <div class="wa ha pl20 pr20">
                <!--问题循环-->
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">1.我是持票企业如何开户？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">用手机号注册成为软件用户后进入到选择角色页面，根据客户自己的需求不同选择不同角色(我是出票方-持票企业，我是收票方-贴现机构),持票企业选择我是出票方并填写企业相关信息，通过小额鉴定后即可成为票据管家的开户企业。<br>若您的公司已开户，直接申请绑定该公司的账户即可。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">2.我是贴现机构如何开户？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">用手机号注册成为软件用户后进入到选择角色页面，根据客户自己的需求不同选择不同角色(我是出票方-持票企业，我是收票方-贴现机构),贴现机构选择机构端并填写机构相关信息，通过小额鉴定后即可成为票据管家的开户机构。<br>若您的公司已开户，直接申请绑定该公司的账户即可。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">3.持票企业开户需要多久？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">两个工作日以内</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">4.贴现机构开户需要多久？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">两个工作日以内</div>
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