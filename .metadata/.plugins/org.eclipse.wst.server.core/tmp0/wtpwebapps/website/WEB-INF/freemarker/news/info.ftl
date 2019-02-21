[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body head={"keywords":"${news.title!''}，票据贴现，银行承兑汇票，贴现率，市场信息","description":"票据管家撮合票据贴现交易，同时还提供票据相关资讯","title":"${news.title!''}-票据管家"}]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/market.css"/>
[@main.header currentmenu='1' returnIndex="2"/]

<!--贴现输入表单-->
<div class="mt16 w1200 bc mb20 pl20 pr20 pb75 bcwhite">
    <div class="pt30 pb30 bbe0e0e0">
        <div class="f24 fl">${news.title}</div>
        <div class="fr f14">
            <div class="fl">
            	[#if news.type==1]票据新闻[/#if]
                [#if news.type==2]金融动态[/#if]
                [#if news.type==3]管家说事[/#if]
                [#if news.type==4]媒体报道[/#if]
            </div>
            <div class="fl ml6 w2 h16 bc2d2d2d"></div>
            <div class="fl ml6">${news.publishtimeStr }</div>
        </div>
        <div class="cb"></div>
    </div>
    <div class="f14 lh30 mt30 pb30 ti2 c7a7a7a bbe0e0e0">
       ${news.content }
    </div>
    <a href="${basePath}/news/index" class="c3366cc f14 mt30 fr">返回列表</a>
</div>
[@main.right /]
<script type="text/javascript">
$(function(){
	wwwPath = "${basePath}";
	actionLog(wwwPath,"action8");//市场信息
});
</script>
<!--foot-->
[@main.footer/]
[/@main.body]