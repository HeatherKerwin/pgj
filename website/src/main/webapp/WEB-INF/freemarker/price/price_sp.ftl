	[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/offer.css"/>
[@main.header currentmenu='1' topindex="3"/]
 [@main.right /]
<div class="w1200 ha bc f16 c2d2d2d mb20">
    <!-- tab-->
    <div class="w1200 h52 bcwhite mt20">
        <ul class="w1198 h52 f16 c2d2d2d lh50 bae0e0e0 tc bcwhite offerTab1">
            <li class="w250 lh50 fl bre0e0e0" >
                <input type="radio" name="offerTab1" id="offerTab1" class="none"><label for="offerTab1" class="dsb"><a href="${basePath}/requirementsp/change" class="c2d2d2d">银票报价</a></label>
            </li>
            <li class="w250 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="offerTab1" id="offerTab2" class="none"><a class="cd43c33"><label for="offerTab2" class="dsb">商票报价</label></a>
            </li>
        </ul>
    </div>
    
    <!-- 报价-->
    <div class="w1200 h500 bae0e0e0 mt10 bcwhite">
        <!-- 菜单-->
        <div class="fl w200 h480 bre0e0e0 bbe0e0e0 bcf9f9f9">
            <ul class="w162 bc lh40 tc mt40">
                <li class="bcfc4d42 cwhite" id="order1"><a class="w160 h40  bae0e0e0 lh40 dsb offerTsb2 cp">填写额度</a></li>
                <li class="mt30" id="order2"><a class="w160 h40  bae0e0e0 lh40 dsb offerTsb2 cp">填写报价</a></li>
                <li class="mt30" id="order3"><a class="w160 h40  bae0e0e0 lh40 dsb offerTsb2 cp">查看报价</a></li>
            </ul>
        </div>
        
        <!--您今日所有票据的额度-->
        <div id="shangpiao1" class="fl w997 h500 bcwhite">
            <div class="mt30 ml50 lh34">
                <div class="fl fb">
                    请先输入您今日所有票据的额度
                </div>
                <input id="pricesp" type="text" class="fl w268 h34 bae0e0e0 ml20 validate[required,custom[edu]]">
                <div class="fl ml10">万元</div>
                <div class="cb"></div>
            </div>
            <div class="mt30 ml50 lh30">
                <label for="beizhu" class="fl fb">
                    备注
                </label>
                <textarea class="w500 bae0e0e0 ml30 h100 fl" id="remarkSp"></textarea>
                <div class="cb"></div>
            </div>
            <input id="tijiao" type="button" class="w200 h40 lh40 cwhite bce84c3d b0 br5 mt30 ml280 cp" value="提交" onclick="saveOrgLimit(0);">
        </div>
        
          <!--您今日所有票据的额度-->
        <div id="shangpiao2" class="fl w997 h500 lh34 bcwhite none">
            <div class="fb ml50 mt30">票据类型</div>
            <div class="mt10 ml50 xuxian pb20">
                <ul class="TXcheckbox lh27" id="TXcheckbox">
                    <li class="fl mr20"><input type="radio" id="zhi" class="none" name="checkbox1" value="" checked><label class="fl tc w46 h27" for="zhi">电票</label></li>
                    <li class="fl"><input type="radio" id="dian" class="none" name="checkbox1" value=""><label class="fl tc w46 h27 br3" for="dian">纸票</label></li>
                    <div class="cb"></div>
                </ul>
            </div>
            <div class="list" id="list">
                <div class="tab1">
                    <!-- 电票-->
                    <div class="h40 mt30 ml50">
		            	<input type="hidden" class="DIANID">
		                <div class="fl fb">电票收票金额区间：</div>
		                <input type="text" class="fl w150 h34 bae0e0e0 tc ml10 dianprice1 validate[required,custom[allprice]]" id="name5" placeholder="请输入金额" onblur="checkprice(5)">
		                <div class="fl ml10">万元</div>
		                <img src="${image_url}/website/img/qx.png" class="fl mt16 ml10" />
		                <input type="text" class="fl w150 h34 bae0e0e0 tc ml10 dianprice2 validate[required,custom[allprice]]" id="name6" placeholder="请输入金额" onblur="checkprice(6)">
		                <div class="fl ml10">万元</div>
		            </div>
		            <div class="h40 mt30 ml50">
		                <div class="fl fb">电票收票期限区间：</div>
		                <input type="text"  name="zhengshu" class="fl w150 h34 bae0e0e0 tc ml10 diandate1 validate[required],maxSize[3]" id="name7" placeholder="距离到期日" onblur="checkprice(7)">
		                <div class="fl ml10">天</div>
		                <img src="${image_url}/website/img/qx.png" class="fl mt16 ml10" />
		                <input type="text" name="zhengshu" class="fl w150 h34 bae0e0e0 tc ml10 diandate2 validate[required],maxSize[3]" id="name8" placeholder="距离到期日" onblur="checkprice(8)">
		                <div class="fl ml10">天</div>
		            </div>
		            <input type="button" class="w200 h40 lh40 cwhite bce84c3d b0 br5 mt30 ml190 f16" onclick="savedian(1);" value="提交">	
                </div>
                <div class="tab1 none">
                    <!--纸票-->
                    <div class="h40 mt30 ml50">
		                <input type="hidden" class="ZHIID">
		                <div class="fl fb">纸票收票金额区间：</div>
		                <input type="text" class="fl w150 h34 bae0e0e0 tc ml10 zhiprice1 validate[required,custom[allprice]]" id="name1" placeholder="请输入金额" onblur="checkprice(1)">
		                <div class="fl ml10">万元</div>
		                <img src="${image_url}/website/img/qx.png" class="fl mt16 ml10" />
		                <input type="text" class="fl w150 h34 bae0e0e0 tc ml10 zhiprice2 validate[required,custom[allprice]]" id="name2" placeholder="请输入金额" onblur="checkprice(2)">
		                <div class="fl ml10">万元</div>
		            </div>
		            <div class="h40 mt30 ml50 pb30 ">
		                <div class="fl fb">纸票收票期限区间：</div>
		                <input type="text"  name="zhengshu" class="fl w150 h34 bae0e0e0 tc ml10 zhidate1 validate[required],maxSize[3]" id="name3" placeholder="距离到期日" onblur="checkprice(3)">
		                <div class="fl ml10">天</div>
		                <img src="${image_url}/website/img/qx.png" class="fl mt16 ml10" />
		                <input type="text" name="zhengshu" class="fl w150 h34 bae0e0e0 tc ml10 zhidate2 validate[required],maxSize[3]" id="name4" placeholder="距离到期日" onblur="checkprice(4)">
		                <div class="fl ml10">天</div>
		            </div>
		            <input type="button" class="w200 h40 lh40 cwhite bce84c3d b0 br5 mt30 ml190 f16" onclick="savezhi(1);" value="提交">
                </div>
            </div>
        </div>
        
         <div id="shangpiao3" class="fl w997 lh34 bcwhite none">
            <!--您今日所有票据的额度-->
            <div class="ml50 mt20 xuxian">
                <div class="fl fb">
                    您今日所有票据的额度
                </div>
                <input id="pricesp1" type="text" class="fl w120 h30 lh30 bae0e0e0 ml20" readonly="readonly" >
                <div class="fl ml20">万元</div>
                <!-- 这个功能取消掉 --> 
                <input type="button" id="update" class="fl w80 h34 lh34 b0 br5 cwhite bce84c3d ml20 mb20 none" onclick="saveOrgLimit(1);" value="更改">
                <div class="cb"></div>
            </div>
            <!--您今日各类票据的报价-->
            <div class="ml50 mt20">
                <div class="fb">
                    您今日各类票据的报价
                </div>
                <div class="fl w h500 lh34">
                    <!--纸票-->
                    <div id="baojia1">
	                    <div class="h40 mt30 ml50">
	                        <div class="fl fb">纸票收票金额区间：</div>
	                        <input type="text" class="fl w150 h34 bae0e0e0 tc ml10 zhiprice1 validate[required,custom[allprice]]" id="nn1" placeholder="请输入金额" onblur="checkprice1(1)">
	                        <div class="fl ml10">万元</div>
	                        <img src="${image_url}/website/img/qx.png" class="fl mt16 ml10" />
	                        <input type="text" class="fl w150 h34 bae0e0e0 tc ml10 zhiprice2 validate[required,custom[allprice]]" id="nn2" placeholder="请输入金额" onblur="checkprice1(2)">
	                        <div class="fl ml10">万元</div>
	                    </div>
	                    <div class="h40 mt30 ml50">
	                        <div class="fl fb">纸票收票期限区间：</div>
	                        <input type="text"  name="zhengshu" class="fl w150 h34 bae0e0e0 tc ml10 zhidate1 validate[required],maxSize[3]" id="nn3" placeholder="距离到期日" onblur="checkprice1(3)">
	                        <div class="fl ml10">天</div>
	                        <img src="${image_url}/website/img/qx.png" class="fl mt16 ml10" />
	                        <input type="text" name="zhengshu" class="fl w150 h34 bae0e0e0 tc ml10 zhidate2 validate[required],maxSize[3]" id="nn4" placeholder="距离到期日" onblur="checkprice1(4)">
	                        <div class="fl ml10">天</div>
	                    </div>
	                    <input type="button" class="w200 h40 lh40 cwhite bce84c3d b0 br5 mt30 ml190 cp" onclick="savezhi(2);" value="更改">
                    </div>
                    <!-- 电票-->
                    <div id="baojia2">
	                    <div class="h40 mt30 ml50">
	                        <div class="fl fb">电票收票金额区间：</div>
	                        <input type="text" class="fl w150 h34 bae0e0e0 tc ml10 dianprice1 validate[required,custom[allprice]]" id="nn5" placeholder="请输入金额" onblur="checkprice1(5)">
	                        <div class="fl ml10">万元</div>
	                        <img src="${image_url}/website/img/qx.png" class="fl mt16 ml10" />
	                        <input type="text" class="fl w150 h34 bae0e0e0 tc ml10 dianprice2 validate[required,custom[allprice]]" id="nn6" placeholder="请输入金额" onblur="checkprice1(6)">
	                        <div class="fl ml10">万元</div>
	                    </div>
	                    <div class="h40 mt30 ml50">
	                        <div class="fl fb">电票收票期限区间：</div>
	                        <input type="text" name="zhengshu" class="fl w150 h34 bae0e0e0 tc ml10 diandate1 validate[required],maxSize[3]"  id="nn7" placeholder="距离到期日" onblur="checkprice1(7)">
	                        <div class="fl ml10">天</div>
	                        <img src="${image_url}/website/img/qx.png" class="fl mt16 ml10" />
	                        <input type="text" name="zhengshu" class="fl w150 h34 bae0e0e0 tc ml10 diandate2 validate[required],maxSize[3]" id="nn8" placeholder="距离到期日" onblur="checkprice1(8)">
	                        <div class="fl ml10">天</div>
	                    </div>
	                    <input type="button" class="w200 h40 lh40 cwhite bce84c3d b0 br5 mt30 ml190 cp" onclick="savedian(2);" value="更改">
                    </div>
                </div>
                <div class="cb"></div>
            </div>
        </div>
        
        <div class="cb"></div>
    </div>
    
    <div class="cb"></div>
</div>

<script type="text/javascript">
	var orgId = '${orgId}';
    var shangpiaoedu=null;
    
    function change(tab,content){
        var $tab =  $(tab);
        $tab.find("li").eq(0).addClass("bce84c3d cwhite br3");
        $tab.find("li").on("click",function(){
            var index = $(this).index();
            $(this).addClass("bce84c3d cwhite br3").siblings().removeClass("bce84c3d cwhite br3");
            $(content).children("div").eq(index).removeClass("none").siblings().addClass("none");
        })
    }
    change("#TXcheckbox","#list");
    
	$(document).ready(function() {
		
		 if(orgId==null && $.trim(orgId)==""){
			 alert("页面已经过时！");
			 window.location.href="${basePath}/price/change";
		 }
		 
		$("input").keyup(function(){
	        var temp = $(this).val();
	        $(this).val(temp.replace(/[^0-9.]/g,''));
	    });
		$("input[name='zhengshu']").keyup(function(){
	        var temp = $(this).val();
	        $(this).val(temp.replace(/[^0-9]/g,''));
	    });
		$("#order1").click(function(){
			$("#order1").addClass("bcfc4d42 cwhite");
			$("#order2").removeClass("bcfc4d42 cwhite");
			$("#order3").removeClass("bcfc4d42 cwhite");
			$("#shangpiao1").removeClass("none");$("#shangpiao2").addClass("none");$("#shangpiao3").addClass("none");
			loadOrgLimit(0);//加载今日报价额度
		});
		$("#order2").click(function(){
			tiaozhuan();
		});
		$("#order3").click(function(){
			if(!$("#pricesp").validationEngine("validate")){
	    		$("#pricesp").focus();
	    		setTimeout(function(){$("#pricesp").validationEngine('hide');},2000);
	    		return;
	    	}
			if(shangpiaoedu==null){
				$("#pricesp").focus();
				alert('请先提交');
				return;
			}
			$("#order3").addClass("bcfc4d42 cwhite");
			$("#order2").removeClass("bcfc4d42 cwhite");
			$("#order1").removeClass("bcfc4d42 cwhite");
			$("#shangpiao3").removeClass("none");$("#shangpiao2").addClass("none");$("#shangpiao1").addClass("none");
			loadOrgLimit(1);
		});
		loadOrgLimit(0);//加载今日报价额度
	})
	
	//跳转报价
	function tiaozhuan(){
		if(!$("#pricesp").validationEngine("validate")){
    		$("#pricesp").focus();
    		setTimeout(function(){$("#pricesp").validationEngine('hideAll');},2000);
    		return;
    	}
		if(shangpiaoedu==null){
			$("#pricesp").focus();
			alert('请先提交');
			return;
		}
		$("#order2").addClass("bcfc4d42 cwhite");
		$("#order1").removeClass("bcfc4d42 cwhite");
		$("#order3").removeClass("bcfc4d42 cwhite");
		$("#shangpiao2").removeClass("none");$("#shangpiao1").addClass("none");$("#shangpiao3").addClass("none");
		loadData(0);
	}
	
	//加载商票额度
	function loadOrgLimit(num){
		$.ajax({
			url:"${basePath}/requirementsp/getorglimit",
			type:"post",
			data:{"orgId":orgId},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					if(num==0){
						$("#pricesp").val(data.orgLimit.priceSp);
						$("#remarkSp").val(data.orgLimit.remarkSp);
						shangpiaoedu=data.orgLimit.priceSp;
					}else{
						$("#pricesp1").val(data.orgLimit.priceSp);
						loadData(1);
					}	
				}else{
					alert("请填写今日额度");
				}
			}
		});
	}
	
	//保存商票额度
	function saveOrgLimit(num){
		if($("#tijiao").attr("disabled")){
			return;
		}
		if(num==0){
			var price = $("#pricesp").val();
		}else{
			var price = $("#pricesp1").val();
		}
		var remarkSp = $("#remarkSp").val();
		if(!$("#pricesp").validationEngine("validate")){
    		$("#pricesp").focus();
    		setTimeout(function(){$("#pricesp").validationEngine('hide');},2000);
    		return;
    	}
		$("#tijiao").attr("disabled","disabled");//按钮禁用
		$.ajax({
			url:"${basePath}/requirementsp/update/priceandtime",
			type:"post",
			data:{"orgId":orgId,"priceSp":price,"remarkSp":remarkSp},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					$("#tijiao").removeAttr("disabled");//按钮启用
					shangpiaoedu=price;
					if(num==0)tiaozhuan();
				}else{
					$("#tijiao").removeAttr("disabled");//按钮启用
					$("#tijiao").validationEngine('showPrompt','* '+data.msg,null,null,true);
    				setTimeout(function(){$("#tijiao").validationEngine('hide');},1000);
				}
			}
		});
	}
	
	function loadData(num){
		$.ajax({
			url:"${basePath}/requirementsp/getbyorgid",
			type:"post",
			data:{"orgId":orgId},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					if(data.zhipiao != null ){
	                	var zhipiao=data.zhipiao;
	                	$(".ZHIID").val(zhipiao.id);
	                	$(".zhiprice1").val(zhipiao.minPrice);
	                    $(".zhiprice2").val(zhipiao.maxPrice);
	                    $(".zhidate1").val(zhipiao.minDay);
	                    $(".zhidate2").val(zhipiao.maxDay);
	                    if(num==1){
	                    	$("#baojia1").removeClass("none");
	                    }
	             	}else{
	             		if(num==1){
	             			$("#baojia1").addClass("none");
	             		}
	             	}
					if(data.dianpiao != null ){
		            	var dianpiao=data.dianpiao;
		            	$(".DIANID").val(dianpiao.id);
		            	$(".dianprice1").val(dianpiao.minPrice);
		             	$(".dianprice2").val(dianpiao.maxPrice);
		             	$(".diandate1").val(dianpiao.minDay);
		            	$(".diandate2").val(dianpiao.maxDay);
		            	if(num==1){
	                    	$("#baojia2").removeClass("none");
	                    }
		         	}else{
	             		if(num==1){
	             			$("#baojia2").addClass("none");
	             		}
	             	}
					if(data.zhipiao == null && data.dianpiao == null && num==1){
						alert("今日暂无报价，请先去报价吧！");
						tiaozhuan();
					}
				}else{
					alert("今日暂无报价！");
				}
			}
		});
	}
	
	//判断输入的大小是否符合
    function checkprice(num){
        if(num== 1){
            if(parseFloat($("#name1").val()) >= parseFloat($("#name2").val())){
                $("#name1").val("");
            	$("#name1").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#name1").validationEngine('hideAll');},1000);
            }
        }else if(num== 2){
        	if(parseFloat($("#name1").val()) >= parseFloat($("#name2").val())){
                $("#name2").val("");
            	$("#name2").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#name2").validationEngine('hideAll');},1000);
            }
        }else if(num== 3){
        	if(parseFloat($("#name3").val()) >= parseFloat($("#name4").val())){
                $("#name3").val("");
            	$("#name3").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#name3").validationEngine('hideAll');},1000);
            }
        }else if(num== 4){
        	if(parseFloat($("#name3").val()) >= parseFloat($("#name4").val())){
                $("#name4").val("");
            	$("#name4").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#name4").validationEngine('hideAll');},1000);
            }
        }else if(num== 5){
        	if(parseFloat($("#name5").val()) >= parseFloat($("#name6").val())){
                $("#name5").val("");
            	$("#name5").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#name5").validationEngine('hideAll');},1000);
            }
        }else if(num== 6){
        	if(parseFloat($("#name5").val()) >= parseFloat($("#name6").val())){
                $("#name6").val("");
            	$("#name6").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#name6").validationEngine('hideAll');},1000);
            }
        }else if(num== 7){
        	if(parseFloat($("#name7").val()) >= parseFloat($("#name8").val())){
                $("#name7").val("");
            	$("#name7").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#name7").validationEngine('hideAll');},1000);
            }
        }else if(num== 8){
        	if(parseFloat($("#name7").val()) >= parseFloat($("#name8").val())){
                $("#name8").val("");
            	$("#name8").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#name8").validationEngine('hideAll');},1000);
            }
        }
    }
	
	//判断输入的大小是否符合
    function checkprice1(num){
        if(num== 1){
            if(parseFloat($("#nn1").val()) >= parseFloat($("#nn2").val())){
                $("#nn1").val("");
            	$("#nn1").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#nn1").validationEngine('hideAll');},1000);
            }
        }else if(num== 2){
        	if(parseFloat($("#nn1").val()) >= parseFloat($("#nn2").val())){
                $("#nn2").val("");
            	$("#nn2").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#nn2").validationEngine('hideAll');},1000);
            }
        }else if(num== 3){
        	if(parseFloat($("#nn3").val()) >= parseFloat($("#nn4").val())){
                $("#nn3").val("");
            	$("#nn3").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#nn3").validationEngine('hideAll');},1000);
            }
        }else if(num== 4){
        	if(parseFloat($("#nn3").val()) >= parseFloat($("#nn4").val())){
                $("#nn4").val("");
            	$("#nn4").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#nn4").validationEngine('hideAll');},1000);
            }
        }else if(num== 5){
        	if(parseFloat($("#nn5").val()) >= parseFloat($("#nn6").val())){
                $("#nn5").val("");
            	$("#nn5").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#nn5").validationEngine('hideAll');},1000);
            }
        }else if(num== 6){
        	if(parseFloat($("#nn5").val()) >= parseFloat($("#nn6").val())){
                $("#nn6").val("");
            	$("#nn6").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#nn6").validationEngine('hideAll');},1000);
            }
        }else if(num== 7){
        	if(parseFloat($("#nn7").val()) >= parseFloat($("#nn8").val())){
                $("#nn7").val("");
            	$("#nn7").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#nn7").validationEngine('hideAll');},1000);
            }
        }else if(num== 8){
        	if(parseFloat($("#nn7").val()) >= parseFloat($("#nn8").val())){
                $("#nn8").val("");
            	$("#nn8").validationEngine('showPrompt','* 输入不规范',null,null,true);
    			setTimeout(function(){$("#nn8").validationEngine('hideAll');},1000);
            }
        }
    }
    
    function savezhi(num){//纸票
    	if(num==1){//order2的验证
    		if($("#savezhi1").attr("disabled")){
    			return;
    		}
	    	if(!$("#name1").validationEngine("validate")){
	    		$("#name1").focus();
	    		setTimeout(function(){$("#name1").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#name2").validationEngine("validate")){
	    		$("#name2").focus();
	    		setTimeout(function(){$("#name2").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#name3").validationEngine("validate")){
	    		$("#name3").focus();
	    		setTimeout(function(){$("#name3").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#name4").validationEngine("validate")){
	    		$("#name4").focus();
	    		setTimeout(function(){$("#name4").validationEngine('hideAll');},1000);
	    		return;
	    	}
	        var zhiprice1=$("#name1").val();
	        var zhiprice2=$("#name2").val();
	        var zhidate1=$("#name3").val();
	        var zhidate2=$("#name4").val();
	        $("#savezhi1").attr("disabled","disabled");//按钮禁用
    	}else{
    		if(!$("#nn1").validationEngine("validate")){
	    		$("#nn1").focus();
	    		setTimeout(function(){$("#nn1").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#nn2").validationEngine("validate")){
	    		$("#nn2").focus();
	    		setTimeout(function(){$("#nn2").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#nn3").validationEngine("validate")){
	    		$("#nn3").focus();
	    		setTimeout(function(){$("#nn3").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#nn4").validationEngine("validate")){
	    		$("#nn4").focus();
	    		setTimeout(function(){$("#nn4").validationEngine('hideAll');},1000);
	    		return;
	    	}
	        var zhiprice1=$("#nn1").val();
	        var zhiprice2=$("#nn2").val();
	        var zhidate1=$("#nn3").val();
	        var zhidate2=$("#nn4").val();
    	}
        var id=$(".ZHIID").val();
        $.ajax({
    		url:"${basePath}/requirementsp/update/pricesp",
    		type:"post",
    		data: {"id":id,"type":"1","orgId": orgId,"minPrice": zhiprice1, "maxPrice": zhiprice2,"minDay": zhidate1,"maxDay": zhidate2},
    		dataType:"text",
    		success:function(data){
    			$("#savezhi1").removeAttr("disabled");//按钮启用
    			var res = JSON.parse(data);
    			if(res.response=="success"){
    				alert(res.msg);
    			}else{
    				alert(res.msg);
    			}
    		}
    	});
    } 
    
    function savedian(num){//电票
    	if(num==1){
	    	if(!$("#name5").validationEngine("validate")){
	    		$("#name5").focus();
	    		setTimeout(function(){$("#name5").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#name6").validationEngine("validate")){
	    		$("#name6").focus();
	    		setTimeout(function(){$("#name6").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#name7").validationEngine("validate")){
	    		$("#name7").focus();
	    		setTimeout(function(){$("#name7").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#name8").validationEngine("validate")){
	    		$("#name8").focus();
	    		setTimeout(function(){$("#name8").validationEngine('hideAll');},1000);
	    		return;
	    	}
	        var dianprice1=$("#name5").val();
	        var dianprice2=$("#name6").val();
	        var diandate1=$("#name7").val();
	        var diandate2=$("#name8").val();
    	}else{
    		if(!$("#nn5").validationEngine("validate")){
	    		$("#nn5").focus();
	    		setTimeout(function(){$("#nn5").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#nn6").validationEngine("validate")){
	    		$("#nn6").focus();
	    		setTimeout(function(){$("#nn6").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#nn7").validationEngine("validate")){
	    		$("#nn7").focus();
	    		setTimeout(function(){$("#nn7").validationEngine('hideAll');},1000);
	    		return;
	    	}
	    	if(!$("#nn8").validationEngine("validate")){
	    		$("#nn8").focus();
	    		setTimeout(function(){$("#nn8").validationEngine('hideAll');},1000);
	    		return;
	    	}
	        var dianprice1=$("#nn5").val();
	        var dianprice2=$("#nn6").val();
	        var diandate1=$("#nn7").val();
	        var diandate2=$("#nn8").val();
    	}
        var id=$(".DIANID").val();
        $.ajax({
    		url:"${basePath}/requirementsp/update/pricesp",
    		type:"post",
    		data: {"id":id,"type":"2","orgId": orgId,"minPrice": dianprice1, "maxPrice": dianprice2,"minDay": diandate1,"maxDay": diandate2},
    		dataType:"text",
    		success:function(data){
    			var res = JSON.parse(data);
    			if(res.response=="success"){
    				alert(res.msg);
    			}else{
    				alert(res.msg);
    			}
    		}
    	});
    }
</script>
[@main.footer/]
[/@main.body]