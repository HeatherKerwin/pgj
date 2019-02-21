[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="工具"]
[@main.header currentmenu='2'/]
<div class="wrapper">
    <ul class="list">
        <li onclick="inquiry();"><a href="javascript:void(0);"><img src="${imagePath}/rywap/tool/xunjia.png" alt="" class="vam listLeft"/><span>银票询价</span><i class="listRight"></i></a></li>
        <li onclick="calculator();"><a href="javascript:void(0);"><img src="${imagePath}/rywap/tool/jisuanqi.png" alt="" class="vam listLeft"/><span>贴现计算器</span><i class="listRight"></i></a></li>
        <li onclick="downLoadApp();"><a href="javascript:void(0);"><img src="${imagePath}/rywap/tool/chafu.png" alt="" class="vam listLeft"/><span>银票查询查复</span><i class="listRight"></i></a></li>
    </ul>
    <ul class="list mt10">
        <li onclick="urge();"><a href="javascript:void(0);"><img src="${imagePath}/rywap/tool/gongcui.png" alt="" class="vam listLeft"/><span>公催查询</span><i class="listRight"></i></a></li>
        <li onclick="bank();"><a href="javascript:void(0);"><img src="${imagePath}/rywap/tool/bank.png" alt="" class="vam listLeft"/><span>联行号查询</span><i class="listRight"></i></a></li>
    </ul>
    <ul class="list mt10">
        <li onclick="shibor();"><a href="javascript:void(0);"><img src="${imagePath}/rywap/tool/shibor.png" alt="" class="vam listLeft"/><span>shibor查询</span><i class="listRight"></i></a></li>
    </ul>
</div>
[@main.footer/]
[/@main.body]