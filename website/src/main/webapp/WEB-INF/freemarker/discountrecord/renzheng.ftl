[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
 [@main.right /]
  <!--认证-->
<div class="pr t_20 " id="">
	<div class="f36 w tc">您需要认证后才能继续贴现哦</div>
	<input type="button"
		class="w268 h45 lh45 cwhite b0 bcfc4d42 br5 tc bc ml243 mt59 f18 cp"
		onclick="renzheng();" value="我要认证">
</div>
<script type="text/javascript">
	function renzheng(){
		window.location.href="${basePath}/bisicmessage/authentication";//最后改成认证页面
	}
</script>

[@main.footer/]
[/@main.body]