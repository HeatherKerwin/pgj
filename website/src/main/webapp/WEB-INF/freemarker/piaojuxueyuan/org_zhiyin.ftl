[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.piaoju6]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1'/]

<div class="w1200 bc mt20 mb20">
	[@main.secondaryheader topindex="2"/]
    [@main.left remark='4' leftindex='1' leftindex1='2'/]
    <!--右侧内容 -->
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">机构用户指引</div>
        </div>
        <div class="wa h700 pl20 pr20">
        	<img alt="" src="${image_url}/website/help/ORG.jpg" width="733" height="463" class="mt25 bc dsb">
            <div class="f30 mt80 fb tc">准备好您的收票之旅了吗？</div>
            <input type="button" class="w249 h50 b0 br5 bc778ffd cwhite f18 lh50 tc dsb bc mt30 cp" value="我要收票" onclick="shoupiao()">
        </div>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]
<script type="text/javascript">
function shoupiao(){
	window.location.href = "${basePath}/hall/index";
}
</script>
<!--foot-->
[@main.footer/]
[/@main.body]