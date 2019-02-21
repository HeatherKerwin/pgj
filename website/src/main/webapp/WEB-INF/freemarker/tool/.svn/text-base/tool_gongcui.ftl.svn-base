[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.gongcui]
[@main.header currentmenu='1' topindex="5"/]
 [@main.right /]
<div class="w1200 bc mt20 mb20 bcwhite">
    <div class="w bcf4f4f4 h100 lh100 f36 ti65 fb">公催查询</div>
    <div class="w1198 bae0e0e0 h608">
        <div class="ml100 mt50 lh60">
            <input type="text" class="fl w364 h60 bae0e0e0 br5 f18 ti25 validate[required,custom[integer1],maxSize[16]]" placeholder="请输入16位汇票票号" id="gongcuinumber">
            <input type="button" class="fl w250 h60 cwhite bcd43c33 b0 br5 f18 tc ml40 cp" onclick="gongcuisearch()" value="汇票查询">
        </div>
        <div class="cb"></div>
        <p class="ml100 f14 c4c4c4c mt30 lh30">提示：<br>1. 请输入完整的16位汇票票号<br>2. 仅提供最近6个月公示催告<br>3. 免责声明：票据管家旨在最大化帮助用户避免汇票业务风险，因票据管家未能收录数据而使用户遇到的风险，与票管家无关，由用户自己承担。
        </p>
    </div>
</div>
<script type="text/javascript">
	function gongcuisearch(){
		if(!$("#gongcuinumber").validationEngine("validate")){
    		$("#gongcuinumber").focus();
    		setTimeout(function(){$("#gongcuinumber").validationEngine('hideAll');},2000);
    		return;
    	}
		var gongcuinumber = $("#gongcuinumber").val();
		$.ajax({
			url:"${basePath}/homepage/gongcui",
			type:"post",
			data:{"gongcuinumber":gongcuinumber},
			dataType:"json",
			success:function(data){
				if(data.response=='success'){
					var gongcui = data.msg;
					alert("汇票票号："+gongcui.gongcuinumber+"\n申请人："+gongcui.gongcuimember+"\n法院："+gongcui.fayuan+"\n时间："+gongcui.gongcuidateStr);
					
				}else{
					alert(data.msg);
				}
			}
		});
	}
</script>

[@main.footer/]
[/@main.body]