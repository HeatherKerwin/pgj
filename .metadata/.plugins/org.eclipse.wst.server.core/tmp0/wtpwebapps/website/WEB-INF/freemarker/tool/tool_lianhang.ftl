[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl" ]
[@main.body head=seoMap.lianhang]
[@main.header currentmenu='1' topindex="5"/]
 [@main.right /]
<div class="w1200 bc mt20 mb20 bcwhite">
    <div class="w bcf4f4f4 h100 lh100 f36 ti65 fb">联行号查询</div>
    <div class="w1198 bae0e0e0 ha">
        <div class="ml70 mr70 pb30 xuxian">
            <div class="mt40 h40 lh40">
                <div class="fl w100">银行名称：</div>
                <select class="w663 h40 select663 f16 ti25 validate[required]" id="yinhang">
            	<option value=''>请选择银行</option>
                <option value='城商行'>城商行</option>
                <option value='城市信用社'>城市信用社</option>
                <option value='工商银行'>工商银行</option>
                <option value='光大银行'>光大银行</option>
                <option value='广发银行'>广发银行</option>
                <option value='合作联社'>合作联社</option>
                <option value='合作银行'>合作银行</option>
                <option value='华夏银行'>华夏银行</option>
                <option value='建设银行'>建设银行</option>
                <option value='交通银行'>交通银行</option>
                <option value='民生银行'>民生银行</option>
                <option value='农村信用社'>农村信用社</option>
                <option value='农业发展'>农业发展</option>
                <option value='农业银行'>农业银行</option>
                <option value='商业银行'>商业银行</option>
                <option value='深圳发展银行'>深圳发展银行</option>
                <option value='信用合作社'>信用合作社</option>
                <option value='兴业银行'>兴业银行</option>
                <option value='邮储银行'>邮储银行</option>
                <option value='招商银行'>招商银行</option>
                <option value='中国人民银行'>中国人民银行</option>
                <option value='中国银行'>中国银行</option>
                <option value='中信银行'>中信银行</option>
                </select>
            </div>
            <div class="mt40 h40 lh40">
                <div class="fl w100">所在省：</div>
                <select class="w400 h40 select400 f16 ti25 validate[required]" id="provice" onchange="choose2(this)">
                </select>
            </div>
            <div class="mt40 h40 lh40">
                <div class="fl w100">所在市：</div>
                <select class="w400 h40 select400 f16 ti25 validate[required]" id="city">
                    <option value="">请选择城市</option>
                </select>
            </div>
            <div class="mt40 h40 lh40">
                <div class="fl w100">关键字：</div>
                <input type="text" class="fl w400 h40 f18 ti25 bae0e0e0 br3 validate[maxSize[10]]" placeholder="请输入查询条件" id="keyword">
                <p class="fl f12 lh16 ml15">请输入关键字并点击查询按钮。关键字的输入方法：××银行海淀区支行世纪城分理处，<br>可以输入“xx银行 海淀 世纪城”作为关键字。多个关键字以空格隔开，区分先后顺序，<br>最多支持三个关键字。</p>
            </div>
            <div class="mt40 h40 lh40">
                <div class="fl w100">图形验证码：</div>
                <input type="text" id="imgCode1" class="fl w182 h40 f18 ti25 bae0e0e0 br3 validate[required]" placeholder="验证码">
                <img id="dlimg" class="fl w182 h40 f18 ti25 bae0e0e0 br3 ml35" src="${basePath}/member/image"/>
                <a href="#" onclick="change('dlimg');" class="blue fl f18 c3366cc lh40 ml15 dsb" >换一个</a>
            </div>
            <input type="button" class="w250 h60 f24 lh60 bcd43c33 cwhite b0 br5 mt50 ml140 dsb cp" value="查询" onclick="yanzheng('imgCode1')">
        </div>
        <div class="ml70 mr70 bae0e0e0 mt30 none" id="biaotou">
            <div class="w h50 lh50 c717583 bcf9f9f9 bbe0e0e0 " >
                <div class="fl w370 ti65">银行名称</div>
                <div class="fl w151 tc">支付联行号</div>
                <div class="fl w410 tc">地址</div>
                <div class="fl w125 tc">电话</div>
            </div>
             <!--列表循环-->
        	<div id="content">
        	</div>
        	<!--到这里结束复制列表-->
        	<div class="bc"></div>
       	    <div id="pager"></div>
        </div>
        <div class="cb"></div>
    </div>
</div>



<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="LIANHANG">
{{#each results}}
	<div class="w h68 pt10 pb10 lh68">
		<div class="fl w370 h68 Rright ti15 dian">{{yinhangdesc}}</div>
		<div class="fl w150 h68 Rright tc">{{lianhanghao}}</div>
		<div class="fl w410 h68 Rright ti15 dian">{{address}}</div>
		<div class="fl w125 h68 tc">{{phone}}</div>
	</div>
	
{{/each}}
</script>
<script type="text/javascript">
	var pageIndex = 1;//分页
	var citylist;//根据省份选择城市
	$(document).ready(function(){
		$.ajax({
			url:"${basePath}/homepage/provicecity",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data){
				var data = eval(data);
				if(data.response=='success'){
					var provicelist = data.provicelist;
					var provicehtml = "<option value=''>请选择省份</option>";
					for(var i=0;i<provicelist.length;i++){
						var provice = provicelist[i];
						provicehtml += "<option value='"+provice.name+"'>"+provice.name+"</option>";
					}
					citylist = data.citylist;
					$("#provice").html(provicehtml);
				}
			}
		});
	
		$("#keyword").blur(function(){
			var array=new Array();
			var keyword=$("#keyword").val();
			var array=keyword.split(/\s+/g);
			if(array.length>3){
				alert("请输入3个以内的关键字");
				$("#keyword").val("");
				$("#keyword").focus();
			}
		})
		
	})
	
	function lianhangsearch(){
		var city = $("#city").val();
		var provice = $("#provice").val();
		var keyword = $("#keyword").val();
		var yinhang = $("#yinhang").val();
		if(!$("#yinhang").validationEngine("validate")){
    		$("#yinhang").focus();
    		setTimeout(function(){$("#yinhang").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#provice").validationEngine("validate")){
    		$("#provice").focus();
    		setTimeout(function(){$("#provice").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#city").validationEngine("validate")){
    		$("#city").focus();
    		setTimeout(function(){$("#city").validationEngine('hideAll');},1000);
    		return;
    	}
		if(!$("#keyword").validationEngine("validate")){
    		$("#keyword").focus();
    		setTimeout(function(){$("#keyword").validationEngine('hideAll');},1000);
    		return;
    	}
		$('#pager').sjAjaxPager({
	        url: "${basePath}/homepage/lianhang/search",
	        pageIndex:1,
	        pageSize:10,
	        searchParam: {
	        	city:city,
	        	yinhang:yinhang,
	        	keyword:keyword,
	        	provice:provice,
	        },beforeSend: function () {
	        },success: function (data) {
	        	change('dlimg');
	        	$("#imgCode1").val("");
	        	if(data.response=="success"){
		        	$("#biaotou").removeClass("none");
		        	var source = $("#LIANHANG").html();
		            var template = Handlebars.compile(source);
		            var html = template(data.data);
		            $("#content").html(html);
	        	}else{
	        		$("#biaotou").addClass("none");
	        		alert(data.msg);
	        	}

	        },complete: function(){
	        },error:function(){
	        	
	        }
	    });
	}
	function choose2(obj){
		var cityhtml = "<option value=''>请选择城市</option>";
		for(var i=0;i<citylist.length;i++){
			if(citylist[i].pname==$(obj).val()){
				cityhtml += "<option value='"+citylist[i].name+"'>"+citylist[i].name+"</option>";
			}
		}
		$("#city").html(cityhtml);
	}
	/*
	 * 换一张图形码
	 */
	function change(img){
		$("#"+img).attr("src","${basePath}/member/image?s="+new Date());
	}
	/*
	 * 快速登录验证安全码
	 */
	function yanzheng(imgCode){
		var imgCode = $("#"+imgCode).val();
		if(!$("#imgCode1").validationEngine("validate")){
			$("#imgCode1").focus();
			return;
		}
		$.ajax({
			url:"${basePath}/member/yzimage",
			type:"post",
			data:{"imgCode":imgCode},
			dataType:"text",
			success:function(data){
				if("success"==data){
					lianhangsearch();
				}else{
					$("#imgCode1").validationEngine('showPrompt','* 验证码不正确',null,null,true);
					setTimeout(function(){$("#imgCode1").validationEngine('hide');},3000);
				}
			}
		});
	}
</script>
[@main.footer/]
[/@main.body]