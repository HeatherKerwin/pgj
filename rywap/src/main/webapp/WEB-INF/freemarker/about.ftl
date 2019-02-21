[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='3'/]

<link rel="stylesheet" href="${staticPath}/css/rywap/about.css"/>
<link rel="stylesheet" href="${staticPath}/css/rywap/form.css"/>
<div class="wrapper">
    <!--logio-->
    <div class="bcwhite w100p">
        <img src="${imagePath}/rywap/common/logo2.png" alt="" class="logo"/>
    </div>
    <div class="biaoqian">
        <span>关于我们</span>
        <p>
            票据管家是上海票管家金融服务有限公司开发的一款专业票据管理手机软件。<br/>本软件专业、全面的整理和收录了央行以及全国范围内的法院汇票挂失公示催告信息，各大银行联行号查询，汇票贴现计算，Shibor数据查询等服务；并向用户提供更加专业完整的票据信息管理，票据的贴现记录、成本计算、到期票据提醒等各项功能和服务。</p>
    </div>

    <div class="form mt10">
        <ul>
            <li>联系我们</li>
            <li><a href="tel:400-067-0002"><img src="${imagePath}/rywap/about/dianhua.png" class="formIcon" alt=""/><span class="font14">电话：400-067-0002</span></a></li>
            <li><a href="mailto:ryfinance@ryfinance.com"><img src="${imagePath}/rywap/about/xinfeng.png" class="formIcon" alt=""/><span class="font14">邮箱：service@utiexian.com</span></a></li>
            <li><a href="http://t.cn/RS9N8N4"><img src="${imagePath}/rywap/about/Shape.png" class="formIcon" alt=""/><span class="font14">APP下载：http://t.cn/RS9N8N4</span></a></li>
            <li><a href="http://www.utiexian.com"><img src="${imagePath}/rywap/about/xinfeng.png" class="formIcon" alt=""/><span class="font14">官网：http://www.utiexian.com</span></a></li>
        </ul>
    </div>
</div>   
[@main.footer/]
[/@main.body]