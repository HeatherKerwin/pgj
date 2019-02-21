[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery("#formID").validationEngine();
});

function toDo(){
	if($("#req").validationEngine("validate")){
		return;
	}
	alert("1111");
}
</script>

<div class="mt16 w1200 bc mb20 pl20 pr20 pb75 bcwhite">
	<form id="formID" method="post">
		<input value="" class="validate[required] text-input" type="text" name="req" id="req" />
		
		<input class="submit" type="submit" value="提交表单"/>
		
	</form>
	<input type="button" value="非表单" onclick="toDo();">
</div>
[@main.footer/]
[/@main.body]