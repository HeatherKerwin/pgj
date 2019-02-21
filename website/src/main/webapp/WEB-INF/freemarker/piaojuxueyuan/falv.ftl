[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.piaoju4]
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/help.css"/>
[@main.header currentmenu='1'/]

<div class="w1200 bc mt20 mb20">
    [@main.secondaryheader topindex="2" search="2"/]
    <div class="w ha">
        [@main.left remark='4' leftindex='4' leftindex1='0'/]
        <!--右侧内容 -->
        <div class="fl w997 pb20 bcwhite bae0e0e0" style="min-height:700px;">
            <div class="w997 h34 lh34 f14 bcfaf7f7">
                <div class="fl ml10"> 法律法规</div>
            </div>
            <p id="serach" class="f16 mt16 mb30">与"<span id="serach1" class="cd43c33"></span>"相关，共为您搜索到<span id="serach2" class="cd43c33"></span>条内容</p>
            <div class="wa ha pl20 pr20" id="content">
                <!--问题循环-->
                <div class="w ha xuxian mt10 pb10">
                    
                </div>
            </div>
        </div>
    </div>
    <div class="cb"></div>
</div>
[@main.right /]

<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/x-handlebars-template" id="PIAOJUXUEYUAN">
{{#each data}}
<div class="w ha xuxian mt20 pb20">
<div class="w ha cp toggle" >
    <a href="${basePath}/piaojuxueyuan/detail/{{id}}?type=3" class="mt12 lh24 pt20 pb10 pl10 pr10 c2d2d2d">{{titleShow}}</a>
    <div class="cb"></div>
</div>
</div>
{{/each}}
</script>
<script type="text/javascript">
$(function(){
	loadData(0);
});
$(".toggle").toggle(function(){
        $(this).children("img").attr('src',"${image_url}/website/help/down.png");
        $(this).children("p").addClass("cd43c33");
        $(this).parent().children(".problemBG").removeClass("none");
    },function(){
            $(this).children("img").attr('src',"${image_url}/website/help/open.png");
            $(this).children("p").removeClass("cd43c33");
            $(this).parent().children(".problemBG").addClass("none");
    }
);
/*
 * 加载数据
 */
function loadData(num){
	var keyword = $("#keyword").val();
	if(num==0){
		$("#serach").addClass("none");
	}else{
		if(keyword==''){
			$("#serach").addClass("none");
		}
	}
	$.ajax({
		url: "${basePath}/piaojuxueyuan/list",
		type:"post",
		data:{"keyword":keyword,"type":4},
		dataType:"json",
		success:function(data){
			if(data.response == "success"){
				if(num==0){
					$("#serach").addClass("none");
				}else{
					if(keyword==''){
						$("#serach").addClass("none");
					}else{
						$("#serach").removeClass("none");
						$("#serach1").text(keyword);
						$("#serach2").text(data.data.length);
					}
				}
				var source = $("#PIAOJUXUEYUAN").html();
	            var template = Handlebars.compile(source);
	            var html = template(data);
	            $("#content").html(html);
			}
		}
	})
}
</script>
[@main.footer/]
[/@main.body]