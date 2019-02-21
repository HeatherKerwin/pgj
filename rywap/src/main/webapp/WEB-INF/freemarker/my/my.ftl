[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家-我"]
[@main.header currentmenu='3'/]
<script type="text/javascript" src="${staticPath}/js/rywap/jquery.min.js"></script>
<div class="wrapper">
    <ul class="list">
        <li onclick="downLoadApp();"><a href="javascript:void(0)"><img src="${imagePath}/rywap/my/yinpiao.png" alt="" class="vam listLeft"/><span>银票贴现订单</span><i class="listRight"></i></a></li>
        <li onclick="downLoadApp();"><a href="javascript:void(0)"><img src="${imagePath}/rywap/my/sahngpiao.png" alt="" class="vam listLeft"/><span>商票贴现订单</span><i class="listRight"></i></a></li>
    </ul>
    <ul class="list mt10">
        <li onclick="downLoadApp();"><a href="javascript:void(0)"><img src="${imagePath}/rywap/my/chaxun.png" alt="" class="vam listLeft"/><span>我的查询查复</span><i class="listRight"></i></a></li>
    </ul>
    <ul class="list mt10">
        <li onclick="about();"><a href="javascript:void(0)"><img src="${imagePath}/rywap/my/about.png" alt="" class="vam listLeft"/><span>关于我们</span><i class="listRight"></i></a></li>
        <li onclick="password();"><a href="javascript:void(0)"><img src="${imagePath}/rywap/my/huanmima.png" alt="" class="vam listLeft"/><span>找回密码</span><i class="listRight"></i></a></li>
    </ul>
</div>    
[@main.footer/]
[/@main.body]