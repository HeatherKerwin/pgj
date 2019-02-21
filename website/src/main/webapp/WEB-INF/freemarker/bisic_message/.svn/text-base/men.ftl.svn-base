[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]


<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
     [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 h700 pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">推荐人</div>
        </div>
        <!--表单-->
        <div class="mt30 ml70 f14">
            <div class="w h34 mt30" id="minv">
                <input id="minvs" type="text" maxlength="4" class="fl w200 h40 lh40 bae0e0e0 ti10 validate[custom[number]]" placeholder="请输入您收到的推荐码">
                <input type="button" id="baocun" class="w108 h40 lh40 bce84c3d cwhite tv br5 b0 ml25 f14" value="保存">
            </div>
            <div class="mt30 none" id="phone">
                <div class="fl w144">推荐您的好友号码为：</div>
                <div class="fl" id="tjh"></div>
                <div class="cb"></div>
            </div>
            <div class="mt30">
                <div class="fl w144">我已推荐人数</div>
                <div class="fl" id="num"></div>
                <div class="cb"></div>
            </div>
            <div class="mt20">
                <div class="fl w144">我的专属推荐码</div>
                <div class="fl" id="mytuijian"></div>
                <div class="cb"></div>
            </div>
            <div class="mt30">
                <div class="fl w144">微信扫一扫邀请好友</div>
                <div class="cb"></div>
            </div>
            <div id="code" class="mt30"></div>
        </div>
    </div>
    <div class="cb"></div>
</div>
 [@main.right /]
[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${comPath}/jquery.qrcode.min.js"></script>
<script type="text/javascript">
	$(function(){
		loadDate();
		wwwPath = "${basePath}";
		actionLog(wwwPath,"action137");
	});
	
	/**
	* 初始化加载数据
	*/
	function loadDate(){
		$.ajax({
	 		type: "POST",
	      	url: "${basePath}/bisicmessagemember/member/get",
	      	data: {},
	      	dataType:"json",
	      	success:function(data){
	          	$("#num").text(data.data.count);
	          	$("#mytuijian").text(data.data.myInvitationCode);
	          	
	          	$("#code").qrcode({
	          		render   : "table",
	          		width: 150,
	          		height:150,
	          		text: "https://m.utiexian.com/app/register.htm?code="+data.data.myInvitationCode
	          	});
	          	
	          	if(data.data.recommendpeople != null && data.data.recommendpeople != ""){
	          		$("#tjh").text(data.data.recommendpeople);
	      			$("#minv").addClass("none");
	      			$("#phone").removeClass("none");
	      		}
	     	}
	 	 });
	};
	
	/**
	* 填写推荐号后保存数据
	*/
	$("#baocun").click(function(){
		if(!$("#minvs").validationEngine("validate")){
			$("#minvs").focus();
			return ;
		}
		
		if($("#baocun").attr("disabled")){
			return;
		}
		var minv = $("#minvs").val();
		if($.trim(minv).length == 0){
			alert("推荐号不能是空")
		};
		$("#baocun").attr("disabled","disabled");//按钮禁用
		$.ajax({
	 		type: "POST",
	      	url: "${basePath}/bisicmessagemember/member/update",
	      	data: {minv:minv},
	      	dataType:"json",
	      	success:function(data){
	      		if(data.response == "success"){
	      			loadDate();
	      		}else{
	      			$("#baocun").removeAttr("disabled");//按钮启用
	      			alert(data.msg);
	      		}
	     	}
	 	 });
	});
</script>

