[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.help1]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1' topindex="1"/]
<div class="w1200 bc mt20 mb20">
    [@main.secondaryheader topindex="1"/]
    <div class="w ha">
        [@main.left remark='2' leftindex='1'/]
        <!--右侧内容 -->
        <div class="fl w997 h700 pb20 bcwhite bae0e0e0">
            <div class="w997 h34 lh34 f14 bcfaf7f7">
                <div class="fl ml10">注册登录</div>
            </div>
            <div class="wa ha pl20 pr20">
                <!--问题循环-->
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">1.如何注册成为票据管家的用户？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">首先用手机号进行注册成为我们的软件用户，获取到相关验证码并输入进入到注册角色界面，根据自身需求不同注册成为相关贴现用户或收票用户(同一个手机号客户可同时成为贴现和收票客户)</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">2.验证码收不到怎么办？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">收取验证码会根据所处方位信号以及手机型号不同而有相关延迟，如果五分钟以后没有收取到任何验证码，重启软件，还是没有作用的话建议切换网络比如停止wifi使用4G或者停止4G使用wifi，以上过程都无作用的话请及时拨打客服电话请求人工服务。</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">3.如何更改密码？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">软件的登入界面中右下角有修改密码的入口，根据软件提示步骤操作即可</div>
                    <div class="cb"></div>
                </div>
                <div class="w ha xuxian mt20 pb20">
                    <div class="w ha cp toggle">
                        <img src="${image_url}/website/help/open.png" class="fl w14 h14 mt1">
                        <p class="fl f14 ml3">4.忘记密码改如何进行补救？</p>
                        <div class="cb"></div>
                    </div>
                    <div class="mt12 lh24 pt20 pb10 pl10 pr10 problemBG none">输入密码界面右侧有相关按钮，点击后根据页面提示操作即可</div>
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