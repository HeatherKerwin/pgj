[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家-公催查询"]
<link rel="stylesheet" href="${staticPath}/css/rywap/urge.css">
[@main.header currentmenu='2'/]
<div class="wrapper">
    <div class="urgeInp">
        <div class="searchIcon"></div>
        <form id="urgeForm" action="${basePath}/wap/tool/urgeSearch" method="post">
			<input name="gongcuinumber" type="text" class="search_input" id="gongcuinumber" placeholder="请输入16位汇票票号">
		</form>
    </div>
    <div class="Btn">
        <input type="button" onclick="urgeSearch();" value="汇票查询"/>
    </div>
    <div class="tac font12 cred" id="flag"></div>
    <div class="urgeTXT"> 
        <h2>提示：</h2>
        <p>1、请输入完整的16位汇票票号</p>
        <p>2、仅提供最近6个月公示催告</p>
        <p>3、免责声明：本软件旨在最大化帮助用户避免汇票业务风险，因本软件未能收录数据而使用户遇到的风险，与本软件无关，由用户自己承担</p>
    </div>
</div>
<script>
	var isok = "false";
	$(function(){
		check();
	});
	
	
	function check(){
		$("#gongcuinumber").blur(function(){
			var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
			var gongcuinumber = $("#gongcuinumber").val();
			if (!re.test(gongcuinumber)) {
				$("#flag").html("您输入的汇票编号格式不正确");
			}else{
				if (gongcuinumber.length!=16) {
					$("#flag").html("您输入的汇票编号格式不正确");
				}else{
					$("#flag").html("");
					isok = "true";
				}
			}
		});
	}
	/*
	 *响应查询按钮
	 */
	function urgeSearch(){
		if(isok == "true"){
			$("#urgeForm").submit();
		}
	}
	 
</script>
[@main.footer/]
[/@main.body]