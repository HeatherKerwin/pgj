[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>

[@main.header currentmenu='1'/]
 [@main.right /]
<div class="w h pf bc05 zi10 top" id="closewindow">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11" >
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
          
            <!--评价-->
            <div class="" id="" >
                <div class="f18 ml310 pt20">
                    <div class="fl fb">发表评价</div>
                    <div class="fl">（<span class="cfc4d42" id="has"></span>/<span id="allmount"></span>）</div>
                </div>
                <div class="cb"></div>
                <div class="w700 ml25 mr25 mt20">
                    <div class="f16 xuxian pb10">
                        <div class="fl c7d7d7d">交易机构：</div>
                        <div class="fl" id="company" ></div>
                        <div class="cb"></div>
                    </div>
                    <div class="pb10 xuxian">
                        <div class="mt10 c7d7d7d">订单简表：</div>
                        <ul class="bae0e0e0 bcwhite tc pt10 pb10 mt10">
                            <li class="fl w232 Rright">
                                <div class="f14 c717583">银票订单号</div>
                                <div class="ordernumber w f16 mt10"></div>
                            </li>
                            <li class="fl w120 Rright">
                                <div class="f14 c717583">总金额</div>
                                <div class="allmoney w f16 mt10"></div>
                            </li>
                            <li id="endorse" class="fl w75 Rright">
                                <div class="f14 c717583">背书</div>
                                <div class="endorse w f16 mt10"></div>
                            </li>
                            <li id="bank" class="fl w180 Rright">
                                <div class="bankname f14 c717583">付款行</div>
                                <div class="bank w f16 mt10"></div>
                            </li>
                            <li id="amount" class="fl w180 Rright">
                                <div class="f14 c717583">总数量</div>
                                <div class="amount w f16 mt10"></div>
                            </li>
                            <li id="deposit" class="fl w90">
                                <div class="f14 c717583">保证金</div>
                                <div class="deposit w f16 mt10"></div>
                            </li>
                            <div class="cb"></div>
                        </ul>
                    </div>
                    <div class="pb10">
                        <div class="mt10 c7d7d7d">发表评价：</div>
                        <textarea class="fl ti8 w700 h100 bae0e0e0 bcwhite mt12 f14 pt10 validate[required]" name="" id="content" placeholder="请畅所欲言吧......" maxlength="140" style='resize: none;'></textarea>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">是否上门</div>
                        <ul class="fl lh27 ml30">
                            <li class="fl mr20 h21"><input type="radio" id="yes" class="fl radio1 w21 h21" name="isToDoor" value="1" ><label class="fl ml10" for="yes">是</label></li>
                            <li class="fl h21"><input type="radio" id="no" class="fl radio1 w21 h21" name="isToDoor" value="0" checked="checked"><label class="fl ml10" for="no">否</label></li>
                        </ul>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">价格真实</div>
                        <div class="fl ml30">
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="price" value="1"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="price" value="2"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="price" value="3"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="price" value="4"/>
                            </div>
                            <div class="fl w25 h24 star2 estimate">
                                <input type="radio" name="price" value="5"/>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">服务态度</div>
                        <div class="fl ml30">
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="service" value="1"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="service" value="2"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="service" value="3"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="service" value="4"/>
                            </div>
                            <div class="fl w25 h24 star2 estimate">
                                <input type="radio" name="service" value="5"/>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">打款速度</div>
                        <div class="fl ml30">
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="speed" value="1"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="speed" value="2"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="speed" value="3"/>
                            </div>
                            <div class="fl w25 h24 mr20 star2 estimate">
                                <input type="radio" name="speed" value="4"/>
                            </div>
                            <div class="fl w25 h24 star2 estimate">
                                <input type="radio" name="speed" value="5"/>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                </div>
                <input type="hidden" id="dcrdId"/>
                <input type="hidden" id="pricetype"/>
                <input type="hidden" id="dtboId"/>
                <input type="button" class="w268 h45 lh45 cwhite br3 b0 bcfc4d42 bc mt25 ml243 cp" onclick="save();" value="发表评价" id="pingjia">
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	var memberId="${member.id}";
	var unComments = new Array();//未评论对象数组
	//评价星级
	$(".estimate").click(function(){
	    var o = $(this);
	    o.children().attr('checked', 'checked');
	    with(o){
	        removeClass("star2").addClass("star1");
	        nextAll().removeClass("star1").addClass("star2");
	        prevAll().removeClass("star2").addClass("star1");
	    }
	});
	
	$(document).ready(function() {
		$("#redClose").click(function(){
	    	$("#closewindow").addClass("none");
	    });
		initUnComments();
	})
	
	/**
	 * 加载未评论的订单
	 */	
	function initUnComments(){
		$.ajax({
			url:"${basePath}/discountrecord/uncomments",
			type:"post",
			data:{},
			dataType:"json",
			success:function(data){
				if(data.response == "success"){
					var result = data.data;
					if ( result!=null && result.length>0) {
	                    for(r in result){
	                        unComments[r] = result[r];
	                    }
	                    $("#has").text("0");
	                    $("#allmount").text(result.length);
	                    initData(unComments[0]);
	                }else if(result==null || result.length==0){
	                	$("#closewindow").addClass("none");
	                }
				}else{
					$("#has").validationEngine('showPrompt','* 网络有问题',null,null,true);
    				setTimeout(function(){$("#has").validationEngine('hideAll');},2000);
				}
			}
		})
		
	}
	
	/**
	 * 初始加载评论内容
	 */		
    function initData(obj){
        var id=obj.id;
        var type=obj.type;
        $("#dcrdId").val(id);
        $("#pricetype").val(type);
        cleanFrom();//初始化表单
        $.ajax({
			url:"${basePath}/discountrecord/comments/show",
			type:"post",
			data:{'id':id,'type':type},
			dataType:"json",
			success:function(data){
				if(data.response == "success"){
					var result = data.data;
					$("#company").text(result.company);
					$(".allmoney").text(result.allmoney+"万元");
					$("#dtboId").val(result.dtboId);
					if(type==0){
						$(".ordernumber").text(result.ordernumber);
						$(".bank").text(result.bank);
						$(".bankname").text("付款行");
						$(".endorse").text(result.endorse+"手");
						$(".deposit").text(result.deposit+"元");
						$("#amount").hide();$("#bank").show();$("#endorse").show();$("#deposit").show();
					}else if(type==1){
						$(".ordernumber").text(result.no);
						$(".bank").text(result.bank);
						$(".bankname").text("承兑企业");
						$(".endorse").text(result.endorse+"手");
						$("#amount").hide();$("#bank").show();$("#endorse").show();$("#deposit").hide();
					}else{
						$(".ordernumber").text(result.no);
						$(".amount").text(result.amount+"张");
						$("#amount").show();$("#bank").hide();$("#endorse").hide();$("#deposit").hide();
					}
				}
			}
		})
    }
    
    /**
     * 初始化表单 
     */
    function cleanFrom(){
        $("#content").val("");
        $("input[name=isToDoor]:eq(0)").attr("checked",true);
        $("input[name=price]").attr("checked",false);
        $("input[name=service]").attr("checked",false);
        $("input[name=speed]").attr("checked",false);
        $("#company").val("");
        $("#dtboId").val("");
        $(".star1").removeClass("star1").addClass("star2");
    }
    
    /**
     * 保存评论 
     */
    function save(){
        var has = $("#has").text();
        var allmount = $("#allmount").text();
        if(has==allmount){//此为关闭,一般不会触发
            window.location.href="${basePath}/discountrecord/assess";
        }
        var type=$("#pricetype").val();
        var dcrdId = $("#dcrdId").val();
        var dtboId = $("#dtboId").val();
        var content = $("#content").val();
        var isToDoor = $('input:radio[name=isToDoor]:checked').val();
        var price = $('input:radio[name=price]:checked').val();
        var service = $('input:radio[name=service]:checked').val();
        var speed = $('input:radio[name=speed]:checked').val();
        if(!$("#content").validationEngine("validate")){
    		$("#content").focus();
    		setTimeout(function(){$("#content").validationEngine('hide');},1000);
    		return;
    	}
        if(price==null || service==null || speed==null){
        	$("#pingjia").validationEngine('showPrompt','* 请填写评价',null,null,true);
			setTimeout(function(){$("#pingjia").validationEngine('hideAll');},1000);
            return;
        }
        $.ajax({
			url:"${basePath}/discountrecord/comments/save",
			type:"post",
			data:{'type':type,'dcrdId':dcrdId,'dtboId':dtboId,'content':content,'operatorId':memberId,'isToDoor':isToDoor,'price':price,'service':service,'speed':speed,},
			dataType:"json",
			success:function(data){
				if(data.response == "success"){
					if (data.response == 'success' ) {
						var has = $("#has").text();
	                    var allmount = $("#allmount").text();
	                    has = Number(has)+1;
	                    $("#has").text(has);
	                    if(has==allmount){//此为关闭
	                    	window.location.href="${basePath}/discountrecord/assess";
	                    }else{
	                    	initData(unComments[has]);
	                    }
	                }
				}
			}
		})
    }  
</script>
[@main.footer/]
[/@main.body]