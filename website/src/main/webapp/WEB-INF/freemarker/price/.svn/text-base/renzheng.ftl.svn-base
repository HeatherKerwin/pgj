[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1' topindex="3"/]
 [@main.right /]
<div class="w h pf bc05 zi190 top" id="close">
    <div class="w758 bcfc4d42 br5 mt_8 l_50 ml-385 pr zi190" style="height: 308px">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi200 redClose"></div>

        <div class="w750 h300 pr t4 l4 bcf5f5f5 zi190">
            <!--认证-->
            <div class="pr pl30 pr30 t70">
                <div class="f20 w str ti32 c4c4c4c">
                    简单几步完成认证即可报价收票哦~
                </div>						
                <input type="button" class="w268 h45 lh45 cwhite b0 bcfc4d42 br3 tc bc mt59 f16 cp dsb" onclick="renzheng()" value="我要开户">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		if(${role}==1){//资方
			$(".str").text("为保障用户交易中的资金安全，票据管家联合兴业银行、兴业数金开通电票交易资金专管账户，彻底解决先背书还是先打款的问题。请先开户，开户通过之后方可体验新版报价收票功能。");
		}else if(${role}==0){//企业方
			$(".str").text("为保障用户交易中的资金安全，票据管家联合兴业银行、兴业数金开通电票交易资金专管账户，彻底解决先背书还是先打款的问题。请先开户，开户通过之后方可体验新版贴现功能。");
		}
	});
	function renzheng(){
		checkAccount(${role},'');
	}
	$(".redClose").bind("click",function(){
		$("#close").hide();
	});
</script>
[@main.footer/]
[/@main.body]