[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]

<div class="mt25 w1150 bc bcwhite pt30 pl50 pr25 pb35 mb20">
    <!--订单状态-->
    <div class="f20 fb mt30">您的订单已生成成功，感谢您使用票据管家进行查询查复。</div>
    <div class="mt12">我们将尽快以短信方式通知您查询查复结果，但由于各出票行反馈时间有所差异，一般为1-2个工作日内，请耐心等待结果。</div>
    <div class="f14 fb mt40 none">
        <div class="fl w100">你还可以继续</div>
        <a href="${basePath}/inquiryreply/inquiryreply" class="fl c3366cc">再来一单</a>
    </div>
    <!--票面-->
    <img src="${image_url}/website/activity/ZTbanner.jpg" class="fr w530 h300 mr40 img" >
    <div class="cb"></div>
</div>
  [@main.right /]
<script>
	/**
	* 跳转到当前活动的页面
	*/
	$(".img").click(function(){
		location.href = "https://bbs.utiexian.com";
	});
</script>
[@main.footer/]
[/@main.body]