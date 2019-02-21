[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家-公催查询"]
<link rel="stylesheet" href="${staticPath}/css/rywap/urge.css">
[@main.header currentmenu='1'/]
<div class="wrapper">
    <div class="bold urgeTitle bcwhite font16">查询结果</div>
    [#if gongcui??]
    <ul class="urgeList">
        <li class="urgeListCon">
            <ul>
                <li>汇票票号：<span>${gongcui.gongcuinumber}</span></li>
                <li>申请人：<span>${gongcui.gongcuimember}</span></li>
                <li>法院：<span>${gongcui.fayuan}</span></li>
                <li>时间：<span>${gongcui.gongcuidate}</span></li>
            </ul>
        </li>
    </ul>
    [#else]
  
  	[/#if]
</div>
[@main.footer/]
[/@main.body]