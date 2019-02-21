[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
[@main.header currentmenu='1'/]

<!--贴现成功 -->
<div class="flex flex-direction-column mt25 w1200 bc bcwhite flex-justify-center pb30 mb30">
    <div class="flex mt25 w bc bcwhite flex-justify-center ">
        <div class="flex-row w900">
            <img src="https://img.utiexian.com/website/discount/Success.gif" alt="" width="900" height="741">
        </div>
    </div>
    <p class="tc w mt30">订单已生成，请耐心等待机构报价~</p>
    <div class="flex-row flex-justify-center w mt30">
        <input type="button" value="订单追踪" id="paysuccess" class="w290 h52 lh52 br3 bcd43c33 bad43c33 cwhite f18">
    </div>
</div>      
[@main.footer/]
[/@main.body]
<script type="text/javascript">
/**
 *  点击交易跟踪
 */
 $("#paysuccess").click(function(){
 	location.href = "${basePath}/hall/disc/index";
 });
</script>
