[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.piaoju4]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1'/]

<!--贴现输入表单-->
<div class="w1200 bc ha pb20 bcwhite bae0e0e0 mb20">
	[@main.secondaryheader topindex="2"/]
    <!--左菜单-->
    [@main.left remark='4' leftindex='4' leftindex1='0'/]
    <div class="fl w997 pb20 bcwhite bae0e0e0" style="min-height:700px;">
    	<div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">${piaojujiangtang.title}</div>
        </div>
        <div class="wa ha pl20 pr20 pt20 pb20 ti20 lh24">
       		${piaojujiangtang.content}
       	</div>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]
<script type="text/javascript">
</script>
<!--foot-->
[@main.footer/]
[/@main.body]