[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>

<!--开户材料-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
        <div class="w h34 lh34 f14 bcfaf7f7 flex-row flex-justify-space-between">
            <div class="ml10">开户管理</div>
            <div class="mr10 c3366cc cp">开户材料</div>
        </div>
        <!--开户材料-->
        <div class="pl100 f14">
            <div class="flex flex-direction-column w ">
                <div class="mt40">开户之前，需要准备什么资料？</div>
                <div class="flex-row flex-direction-row mt20 w728 bae0e0e0 f16 lh34">
                    <div class="flex-col-6 flex-direction-column bre0e0e0 pl20 pr20 pt10">
                        <div>1、营业执照正本扫描件。</div>
                        <div>2、法定代表人的身份证正、反面扫描件。</div>
                        <div>3、<span class="cd43c33">授权委托书</span>扫描件：出票方在线上传扫描件；收票方需在线上传扫描件，同时原件须线下快递至兴业。</div>
                    </div>
                    <div class="flex-col-6 flex-direction-column pl20 pr20 pt10">
                        <div>4、对公银行账号：出票方可关联全国任一银行对公账户；收票方需关联兴业银行对公账户<span class="cd43c33">（须开通电票功能和网上采购员权限）</span>。</div>
                        <div>5、承诺函：收票方需在线上<span class="cd43c33">报价承诺函</span>扫描件。</div>
                    </div>
                </div>
                <div class="mt20">*扫描件必须为彩色原件的扫描件</div>
                <div class="flex-row flex-direction-row mt30 w400 none">
                    <div class="flex-col-4 flex-direction-column flex-justify-center tc">
                        <div>授权委托书（票方）</div>
                        <div class="flex-row flex-justify-center w mt20">
                            <img src="${imagePath}website/PJGJ/account/bookIcon.png" alt="授权委托书">
                        </div>
                        <input type="button" class="w50 bce84c3d bad43c33 cwhite br3 h24 lh24 mt10 cp" value="下载">
                    </div>
                    <div class="flex-col-4 flex-direction-column flex-justify-center tc">
                        <div>授权委托书（资方）</div>
                        <div class="flex-row flex-justify-center w mt20">
                            <img src="${imagePath}website/PJGJ/account/bookIcon.png" alt="授权委托书（资方）">
                        </div>
                        <input type="button" class="w50 bce84c3d bad43c33 cwhite br3 h24 lh24 mt10 cp" value="下载">
                    </div>
                    <div class="flex-col-4 flex-direction-column flex-justify-center tc">
                        <div>报价承诺函</div>
                        <div class="flex-row flex-justify-center w mt20">
                            <img src="${imagePath}website/PJGJ/account/articleIcon.png" alt="授权委托书">
                        </div>
                        <input type="button" class="w50 bce84c3d bad43c33 cwhite br3 h24 lh24 mt10 cp" value="下载">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="cb"></div>
</div>
  [@main.right /]
 
[@main.footer/]
[/@main.body]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script>
	$(document).ready(function () {
	//  选择tab
	  $("input[name='rzTab']").change(function () {
	      $(this).parents("ul").children("li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
	      $(this).parents("ul").children("li").removeClass("bbd43c33");
	      $(this).parents("ul").children("li").children("label").removeClass("f20");
	      if ($(this).attr("checked") == "checked") {
	          $(this).parent("li").addClass("bbd43c33");
	          $(this).parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
	      }
	  })
	});
</script>

