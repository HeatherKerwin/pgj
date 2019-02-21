[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
[@main.header currentmenu='1' topindex='2'/]

<!--贴现输入表单-->
<div class="mt16 w1200 bc">
	<form action="${basePath}/discountrecordpl/tiexian" method="post" id="form1">
    <div class="w1200 h52 bcwhite">
        <ul class="f16 c2d2d2d lh50 bae0e0e0 tc TXtab">
            <li id="yptx" class="w250 lh50 fl bre0e0e0 ">银票贴现</li>
            <li id="sptx"class="w250 lh50 fl bre0e0e0">商票贴现</li>
            <li id="pltx" class="w250 lh50 fl bre0e0e0 bbd43c33 cd43c33">批量贴现</li>
        </ul>
    </div>
    <div class="mt12 bc bae0e0e0 bcwhite pl20 pr20 pb15 f14 c2d2d2d">
        <div class="w h45 xuxian">
            <div class="fl tl w170 fb lh45">票据属性：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="zhi" class="none" name="type1" value="1" checked><label class="fl tc w46 h27 br3 bill_opt_css" for="zhi">纸票</label></li>
                <li class="fl"><input type="radio" id="dian" class="none" name="type1" value="2"><label class="fl tc w46 h27 br3 cwhite bce84c3d bill_opt_css" for="dian">电票</label></li>
            </ul>
        </div>
        <div class="w h45">
            <div class="fl tl w170 fb lh45">票据总金额：</div>
            <input type="text" id="allmoney"  name="allmoney" placeholder="请输入总金额" class="validate[custom[Okmoney]],validate[custom[number]],validate[custom[money]],validate[required] fl w129 h27 lh27 bae0e0e0 mt8 ti8">
            <div class="fl f14 lh45 ml10">万元</div>
        </div>
        <div class="w h45">
            <div class="fl tl w170 fb lh45">票据最小金额：</div>
            <input type="text" id="minmoney" name="minmoney" placeholder="请输入最小金额" class="validate[custom[Okmoney]],validate[custom[number]],validate[custom[money]],validate[required]  fl w129 h27 lh27 bae0e0e0 mt8 ti8">
            <div class="fl f14 lh45 ml10">万元</div>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w170 fb lh45">票据最大金额：</div>
            <input type="text" id="maxmoney" name="maxmoney" placeholder="请输入最大金额" class="validate[custom[Okmoney]],validate[custom[number]],validate[custom[money]],validate[required]  fl w129 h27 lh27 bae0e0e0 mt8 ti8">
            <div class="fl f14 lh45 ml10">万元</div>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w170 fb lh45">承兑行类型（可多选）：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="checkbox" id="guogu" class="none" name="type3" value="1" checked="checked"><label class="fl tc w46 h27 br3 cwhite bce84c3d type3_opt_css guogu1" checked="checked" for="guogu">国股</label></li>
                <li class="fl mr20"><input type="checkbox" id="dashang" class="none" name="type3" value="2"><label class="fl tc w46 h27 br3 type3_opt_css dashang1" for="dashang">大商</label></li>
                <li class="fl mr20"><input type="checkbox" id="xiaoshang" class="none" name="type3" value="3"><label class="fl tc w46 h27 br3 type3_opt_css xiaoshang1" for="xiaoshang">小商</label></li>
                <li class="fl mr20"><input type="checkbox" id="sannong" class="none" name="type3" value="4"><label class="fl tc w46 h27 br3 type3_opt_css sannong1" for="sannong">三农</label></li>
                <li class="fl mr20"><input type="checkbox" id="other" class="none" name="type3" value="5"><label class="fl tc w46 h27 br3 type3_opt_css other1" for="other">其它</label></li>
            </ul>
        </div>
        <input type="hidden" name="type2" class="type2_opt_css" >
        <div class="w h45 xuxian">
            <div class="fl tl w170 fb lh45">票据总数量：</div>
            <input type="text" id="num" name="num" placeholder="请输入数量" class="validate[custom[Okmoney]],validate[custom[number]],validate[required]  fl w129 h27 lh27 bae0e0e0 mt8 ti8">
            <div class="fl f14 lh45 ml10">张</div>
        </div>
        <div class="w h45">
            <div class="fl tl w170 fb lh45">票据到期天数：</div>
        </div>
        <div class="w h45">
            <div class="fl tl w170 fb lh45">票据最短到期天数：</div>
            <input type="text" id="min_expiry_day" name="min_expiry_day" placeholder="请输入天数" class="validate[custom[Okmoney]],validate[custom[number]],validate[required] fl w129 h27 lh27 bae0e0e0 mt8 ti8">
            <div class="fl f14 lh45 ml10">天</div>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w170 fb lh45">票据最长到期天数：</div>
            <input type="text" id="max_expiry_day" name="max_expiry_day" placeholder="请输入天数" class="validate[custom[Okmoney]],validate[custom[number]],validate[required] fl w129 h27 lh27 bae0e0e0 mt8 ti8">
            <div class="fl f14 lh45 ml10">天</div>
        </div>

        <div class="w h45 xuxian">
            <div class="fl tl w170 fb lh45">订单有效期至：</div>
            <input type="text" class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline" id="first" />
            <label class="fl w30 h27 rili" for="first"></label>
            <input id="firstdate" name="firstdate" type="hidden" />
        </div>
        <div class="w xuxian pb15">
            <div class="fl tl w170 fb lh45">是否瑕疵票：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes3" class="none" name="flawTicket" value="0"><label class="fl tc w46 h27 br3 flawTicket_opt_css" for="yes3">是</label></li>
                <li class="fl"><input type="radio" id="no3" class="none" name="flawTicket" value="1" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d flawTicket_opt_css" for="no3">否</label></li>
            </ul>
            <div class="cb"></div>
            <p class="f12 cd43c33 ml170">注：如果是瑕疵票，机构端跟您交易时可能会收取额外处理费用。</p>
        </div>
        <div class="w h45 xuxian">
            <div class="fl tl w170 fb lh45">要求上门：</div>
            <ul class="pt10 fl TXcheckbox lh27">
                <li class="fl mr20"><input type="radio" id="yes1" class="none" name="needTodoor" value="1" checked><label class="fl tc w46 h27 br3 cwhite bce84c3d shangmen_opt_css" for="yes1">是</label></li>
                <li class="fl"><input type="radio" id="no1" class="none" name="needTodoor" value="0"><label class="fl tc w46 h27 br3 shangmen_opt_css" for="no1">否</label></li>
            </ul>
        </div>
        <div class="w h130 xuxian">
            <div class="fl tl w170 fb lh45">备注（选填）：</div>
            <textarea style='resize: none;' maxlength="140" class="fl ti8 w500 h100 bae0e0e0 bcwhite mt8 f14 pt10" name="remarks" placeholder="备注最多可输入140个字......"></textarea>
        </div>
    </div>
    <div class="h50 bcf9f9f9 ble0e0e0 bre0e0e0 bbe0e0e0 lh50 pl20">
        <div class="fl f12">
            <div class="TXchecked on_check fl mt20 mr10">
                <input name="need_inv" type="checkbox" style="height:20px;width:20px;" class="radioclass input" value="1">
            </div>
            <p class="cblack fl">我已阅读并同意<a href="${basePath }/statement" class="cd43c33">《票管家免责声明》</a></p>
        </div>
        <input type="button" value="生成订单" class="fr f18 cwhite bcd43c33 w166 lh50 b0" />
    </div>
    <div class="w h40 bcwhite ble0e0e0 bre0e0e0 bbe0e0e0">
    </div>
    </form>
</div>
  [@main.right /]
<!--提示弹窗-->
<div class="wa pf t400 r90 zi10 matter">
    <!--问题-->
    <div class="w190 pr top z12">
        <div class="w190 h30 f14 lh30 bc778ffd cwhite tc">常见问题</div>
        <p class="wa f12 pt25 pb20 lh30 c2d2d2d bcwhite cp pl10 dsb" id="problem">
            1.什么是批量贴现？<br>2. 批量贴现步骤？
        </p>
        <div class="w190 bcf7f7f6 f12 lh30 pt25 pb20">
            <div class="ml10">如有其他问题，请详见</div>
            <div class="ml10">【帮助中心】-【常见问题】</div>
        </div>
    </div>
    <div class="w500 ha pa t25 r190 zi13 answer c777777 pt10 pb10 pl10 pr25 none" id="answer">
        <h2 class="f16 fb mt6">1.什么是批量贴现？</h2>
        <p class="f14 ti20 lh18 mt10">多张承兑行到期时间等票据贴现属性不统一的票同时都由一个客户提出的贴现要求，可以作为批量贴现进行处理。</p>
        <h2 class="f16 fb mt10">2. 为什么要缴纳保证金？</h2>
        <p class="f14 ti20 lh18 mt10">用户点击批量贴现直接生成订单，无须缴纳保证金，票据管家收到订单后经过后台智能运算，会立即派送给所有符合条件的机构，机构在订单有效期内可以在经过跟机构沟通后选择是否接单，一旦机构接单后会联系持票用户进行线下交易。</p>
    </div>
</div>


<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript">
$(document).ready(function() {
})

function jiejia(){
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountrecord/jiejia",
	     	data: {},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			alert(data.msg);
	     		}else{
	     			
	     		}
	    	}
		 })	
    	
    }
	//最小金额的判断
	$("#minmoney").blur(function(){
		var allmoney = parseInt($("#allmoney").val());
		var minmoney = parseInt($("#minmoney").val());
		if(minmoney >= allmoney){
			 $("#minmoney").validationEngine('showPrompt','*最小金额不能大于总金额',null,null,true);
			 setTimeout(function(){$("#minmoney").validationEngine('hide');},2000);
			 $("#minmoney").focus();
			 return ;
		 }
	})
	
	//最大金额的判断
	$("#maxmoney").blur(function(){
		var allmoney = parseInt($("#allmoney").val());
		var minmoney = parseInt($("#minmoney").val());
		var maxmoney = parseInt($("#maxmoney").val());
		if(minmoney >= maxmoney){
			 $("#maxmoney").validationEngine('showPrompt','*最小金额不能大于最大金额',null,null,true);
			 setTimeout(function(){$("#maxmoney").validationEngine('hide');},2000);
			 $("#maxmoney").focus();
			 return;
		 }else if(allmoney <= maxmoney){
			 $("#maxmoney").validationEngine('showPrompt','*最大金额不能大于总金额',null,null,true);
			 setTimeout(function(){$("#maxmoney").validationEngine('hide');},2000);
			 $("#maxmoney").focus();
			 return ;
		 }
	});
	
	//最长天数的验证
	$("#max_expiry_day").blur(function(){
		var min_expiry_day = parseInt($("#min_expiry_day").val());
		var max_expiry_day = parseInt($("#max_expiry_day").val());
		if(max_expiry_day < min_expiry_day){
			$("#max_expiry_day").validationEngine('showPrompt','*最短天数不能大于最长天数',null,null,true);
			setTimeout(function(){$("#max_expiry_day").validationEngine('hide');},2000);
			$("#max_expiry_day").focus();
			return ;
		}
	});
	
	//生成订单
	$("input[type='button']").click(function(){
		if($("input[type='button']").attr("disabled")){
			return;
		}
		
		if(!$("#allmoney").validationEngine("validate")){
			$("#allmoney").focus();
			return ;
		}
		if(!$("#minmoney").validationEngine("validate")){
			$("#minmoney").focus();
			return ;
		}
		if(!$("#maxmoney").validationEngine("validate")){
			$("#maxmoney").focus();
			return ;
		}
		if(!$("#num").validationEngine("validate")){
			$("#num").focus();
			return ;
		}
		
		if(!$("#min_expiry_day").validationEngine("validate")){
			$("#min_expiry_day").focus();
			return ;
		}
		
		if(!$("#max_expiry_day").validationEngine("validate")){
			$("#max_expiry_day").focus();
			return ;
		}
		
		if(!($(".TXchecked").hasClass("on_check"))){
			alert("请先同意票管家协议");
			return ;
		};
		
		var p="";
		$(".type3_opt_css").each(function(){
			if($(this).attr("checked") == "checked"){
				if($.trim(p).length != 0){
					p = p+","+ $(this).prev().val();
				}else{
					p = $(this).prev().val();
				}
			}
		});
		
		$(".type2_opt_css").val(p);
		if($.trim(p).length == 0){
			alert("请选择承兑类型");
			return ;
		}
		
		if($.trim($("#allmoney").val()).length == 0 || $("#allmoney").val() == null){
			alert("请选择总金额");
			return ;
		};
		jiejia();
		$("input[type='button']").attr("disabled","disabled");//按钮禁用
		document.getElementById("form1").submit();
	});

    //日历
    !function(){
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
    }();
//    订单有效期至
    laydate({
        elem: '#first',
        format: 'YYYY-MM-DD',
        festival: true, //显示节日
        choose: function(datas){ //选择日期完毕的回调
        	$("#firstdate").val(datas);
        }
    });

//    单选选择radio
    $(".bill_opt_css").click(function () {
      if ($(this).prev().val() == "1") {
            $("#zhi").parents("li").children("label").addClass("cwhite bce84c3d");
            $("#dian").parents("li").children("label").removeClass("cwhite bce84c3d");
            $("#zhi").attr("checked",true);
            $("#dian").attr("checked",false);
      }
      else{
          $("#dian").parents("li").children("label").addClass("cwhite bce84c3d");
          $("#zhi").parents("li").children("label").removeClass("cwhite bce84c3d");
          $("#dian").attr("checked",true);
          $("#zhi").attr("checked",false);
      }
    })
    $(".type3_opt_css").click(function () {
    	if($(this).prev().val() == "1"){
    		if ($(this).attr("checked") == "checked") {
                $("#guogu").next().removeClass("cwhite bce84c3d");
                $(".guogu1").attr("checked",false);
            }
            else {
            	$("#guogu").next().addClass("cwhite bce84c3d");
            	$(".guogu1").attr("checked",true);
            }
    	}else if($(this).prev().val() == "2"){
    		if ($(this).attr("checked") == "checked") {
                $("#dashang").next().removeClass("cwhite bce84c3d");
                $(".dashang1").attr("checked",false);
            }
            else {
            	$(".dashang1").attr("checked",true);
            	$("#dashang").next().addClass("cwhite bce84c3d");
            }
    	}else if($(this).prev().val() == "3"){
    		if ($(this).attr("checked") == "checked") {
                $("#xiaoshang").next().removeClass("cwhite bce84c3d");
                $(".xiaoshang1").attr("checked",false);
            }
            else {
            	$(".xiaoshang1").attr("checked",true);
            	$("#xiaoshang").next().addClass("cwhite bce84c3d");
            }
    	}else if($(this).prev().val() == "4"){
    		if ($(this).attr("checked") == "checked") {
                $("#sannong").next().removeClass("cwhite bce84c3d");
                $(".sannong1").attr("checked",false);
            }
            else {
            	$("#sannong").next().addClass("cwhite bce84c3d");
            	$(".sannong1").attr("checked",true);
            }
    	}else if($(this).prev().val() == "5"){
    		if ($(this).attr("checked") == "checked") {
                $("#other").next().removeClass("cwhite bce84c3d");
                $(".other1").attr("checked",false);
            }
            else {
            	$("#other").next().addClass("cwhite bce84c3d");
            	$(".other1").attr("checked",true);
            }
    	}
    });
    $(".flawTicket_opt_css").click(function () {
      if ($(this).prev().val() == "1") {
            $("#no3").parents("li").children("label").addClass("cwhite bce84c3d");
            $("#yes3").parents("li").children("label").removeClass("cwhite bce84c3d");
            $("#no3").attr("checked",true);
            $("#yes3").attr("checked",false);
      }
      else{
          $("#yes3").parents("li").children("label").addClass("cwhite bce84c3d");
          $("#no3").parents("li").children("label").removeClass("cwhite bce84c3d");
          $("#yes3").attr("checked",true);
          $("#no3").attr("checked",false);
      }
    })
    $(".shangmen_opt_css").click(function () {
    	if ($(this).prev().val() == "1") {
            $("#yes1").parents("li").children("label").addClass("cwhite bce84c3d");
            $("#no1").parents("li").children("label").removeClass("cwhite bce84c3d");
            $("#yes1").attr("checked",true);
            $("#no1").attr("checked",false);
      }
      else{
          $("#no1").parents("li").children("label").addClass("cwhite bce84c3d");
          $("#yes1").parents("li").children("label").removeClass("cwhite bce84c3d");
          $("#no1").attr("checked",true);
          $("#yes1").attr("checked",false);
      }
    })
    
    $("#yptx").click(function(){
		location.href = "${basePath}/discountrecord/discount?ym=yp";
	})
	$("#sptx").click(function(){
		location.href = "${basePath}/discountrecord/discount?ym=sp";
	})
	$("#pltx").click(function(){
		location.href = "${basePath}/discountrecord/discount?ym=pl";
	});
	
    // radio鼠标触发事件
    $(".TXradio").mouseover(function(){
        $(".TXradio").addClass("bcd43c33");
    });
    $("p").mouseout(function(){
        $(".TXradio").removeClass("bcd43c33");
    });

    //复选勾选协议
    $(".TXchecked").on("click",function(){
        $(this).hasClass("on_check")? $(this).removeClass("on_check"):$(this).addClass("on_check");
    })
    
    var now = new Date();
	var fullyear = now.getFullYear();
   	//获取完整的年份(4位,1970-????)
	var month = now.getMonth() + 1;
	//获取当前月份(0-11,0代表1月)
	var date = now.getDate();
	//获取当前日(1-31)
	if (month < 10) {
		month = "0" + month;
	}
	if (date < 10) {
		date = "0" + date;
	}	
	$("#first").val(fullyear+"-"+month+"-"+date);
	$("#firstdate").val(fullyear+"-"+month+"-"+date);
	

    //    问题答案
    $("div #problem").mouseover(function(){
        $("#answer").fadeIn("slow");
    })
    $("div #problem").mouseout(function(){
        $("#answer").fadeOut("slow");
    });
    
</script>

[@main.footer/]
[/@main.body]
