[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='3'/]

<!--贴现地址-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
   <!--右侧内容 -->
    <div class="fl w997 ha pb20 bcwhite bae0e0e0">
        <div class="w997 h34 lh34 ti10 bcfaf7f7">批量订单</div>
        <!--批量订单-->
        <!--订单tab-->
        <ul class="h52 f16 c2d2d2d lh50 bte0e0e0 bbe0e0e0 tc bcwhite orderTab">
            <li class="w166 lh50 fl bre0e0e0 bbd43c33">
                <input type="radio" name="orderTab" id="order1" value="0" class="none" checked><a href="#" class="cd43c33"><label for="order1" class="dsb orderTab1">全部订单</label></a>
            </li>
            <li class="w165 lh50 fl bre0e0e0 pr zi10">
                <input type="radio" name="orderTab" id="order2" value="1" class="none"><a href="#" class="c2d2d2d"><label for="order2" class="dsb orderTab1">待交易</label></a>
            	<div class="w20 h20 br30 bcfc4d42 cwhite lh20 f14 tc pa t13 r30 zi13 badgepay none"></div>
            </li>
            <li class="w165 lh50 fl bre0e0e0 pr zi10">
                <input type="radio" name="orderTab" id="order3" value="2" class="none"><a href="#" class="c2d2d2d"><label for="order3" class="dsb orderTab1">交易中</label></a>
           		<div class="w20 h20 br30 bcfc4d42 cwhite lh20 f14 tc pa t13 r30 zi13 badgetrade none"></div>
            </li>
            <li class="w165 lh50 fl bre0e0e0 pr zi10">
                <input type="radio" name="orderTab" id="order4" value="3" class="none"><a href="#" class="c2d2d2d"><label for="order4" class="dsb orderTab1">待评价</label></a>
            	<div class="w20 h20 br30 bcfc4d42 cwhite lh20 f14 tc pa t13 r30 zi13 badgeevaluated none"></div>
            </li>
            <li class="w165 lh50 fl bre0e0e0">
                <input type="radio" name="orderTab" id="order5" value="4" class="none"><a href="#" class="c2d2d2d"><label for="order5" class="dsb orderTab1">已完成</label></a>
            </li>
            <li class="w166 lh50 fl">
                <input type="radio" name="orderTab" id="order6" value="5" class="none"><a href="#" class="c2d2d2d"><label for="order6" class="dsb orderTab1">无效订单</label></a>
            </li>
        </ul>
        <!--条件-->
        <div class="pl20 mt30 ml20 mr20 f14 bae0e0e0 br5">
            <div class="mt20">
                <div class="fl w65 fb lh30">时间</div>
                <div class="fl">
                    <input class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline" id="start">
                    <label class="fl w30 h27 rili" for="start"></label>
                    <img src="${image_url}/website/manage/xian.png" width="34" height="1" class="fl ml20 mr20 mt13">
                    <input class="fl w100 bae0e0e0 h27 lh27 c2d2d2d mr10 ti8 inline" id="end">
                    <label class="fl w30 h27 rili" for="end"></label>
                </div>
                <div class="cb"></div>
            </div>
            <div class="mt20 mb20">
                <div class="fl w65 fb lh30">票据金额</div>
                <div class="fl lh30">
                    <div class="fl">从</div>
                    <input type="text" class="fl w148 h29 bae0e0e0 br5 ml6 ti10" id="minallmoney">
                    <div class="fl ml6">至</div>
                    <input type="text" class="fl w148 h29 bae0e0e0 br5 ml6 ti10" id="maxallmoney">
                    <div class="fl ml6">万元</div>
                </div>
                <input type="button" id="sousuo" class="fl w110 h34 lh34 bce84c3d b0 br5 cwhite ml40" value="搜索">
                <div class="cb"></div>
            </div>
        </div>
        <!--从这里开始复制列表-->
        <div class="pl20 pr20 mb20 mt30" id="content">
        
        </div>
        <!--到这里结束复制列表-->
        <div id="pager"></div>
    </div>
    <div class="cb"></div>
</div>
  [@main.right /]
<!--理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="window">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose"></div>

        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <!--取消订单理由-->
            <div class="pr h500 t45 none" id="reasonDiv">
                <div class="ml25 f16 lh40">
                    <div class="fl w138 c7d7d7d">请选择取消理由：</div>
                    <input type="hidden" id="qxid"  />
                    <input type="hidden" id="qxzt"  />
                    <select class="fl w320 h40 select320 ti8" id="cancel">
                        <option value="0">票面信息输入有误</option>
                        <option value="1">只为熟悉流程和询问价格</option>
                        <option value="2">价格不合适</option>
                        <option value="3">已通过其他方式出票</option>
                        <option value="4">其他</option>
                    </select>
                </div>
                <!-- 其他-->
                <textarea placeholder="请您输入不少于十五字的理由！" type="text" class="w500 h210 bae0e0e0 mt20 ml160 ti8 pt15 none" id="reason1_div"></textarea>
                <!--票据有问题-->
                <input type="button" value="确认" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b30 l_50 ml-130" id="infor">
            </div>
            <!-- 确认订单-->
            <div class="pr t125 pl25 pr25 none" id="finishDiv">
                <div class="">我已收到以下贴现订单的款项:</div>
                <ul class="bae0e0e0 bcwhite tc pt10 pb10 mt20">
                    <li class="fl w232 h90 Rright">
                        <div class="f14 lh30 c717583">批量订单号</div>
                        <div class="w f16 lh60 qrno" id="qrno"></div>
                    </li>
                    <li class="fl w120 h90 Rright">
                        <div class="f14 lh30 c717583">总金额</div>
                        <div class="w f16 lh60" id="qrallmoney"></div>
                    </li>
                    <li class="fl w75 h90 Rright">
                        <div class="f14 lh30 c717583">张数</div>
                        <div class="w f16 lh60" id="qrnum"></div>
                    </li>
                    <li class="fl w270 h90">
                        <div class="f14 lh30 c717583">天数</div>
                        <div class="f16 lh30">最短<span class="duan"></span>天</div>
                        <div class="f16 lh30">最长<span class="chang"></span>天</div>
                    </li>
                    <input id="qrid" type="hidden" />
                    <div class="cb"></div>
                </ul>
                <div class="w268 bc mt100">
                    <input type="button" id="qrddwc" class="w268 h45 lh45 cwhite br5 b0 bcfc4d42 bc mt25 f18" value="确认已完成">
                </div>
            </div>
            <!-- 编辑评价-->
            <div class="pr t25 pl25 pr25 none" id="evaluateDiv1">
                <div class="w700">
                	<input  type="hidden" id="dcrdId" />
                    <div class="f16 xuxian pb10">
                        <div class="fl c7d7d7d">交易机构：</div>
                        <div class="fl" id="pjcompany"></div>
                        <div class="cb"></div>
                    </div>
                    <div class="pb10 xuxian">
                        <div class="mt10 c7d7d7d">订单简表：</div>
                        <ul class="bae0e0e0 bcwhite tc pt10 pb10 mt10">
                            <li class="fl w232  Rright">
		                        <div class="f14  c717583">批量订单号</div>
		                        <div class="w f16 lh40 mt8 qrno" id="pjno"></div>
		                    </li>
		                    <li class="fl w120  Rright">
		                        <div class="f14  c717583">总金额</div>
		                        <div class="w f16 lh40 mt8" id="pjallmoney"></div>
		                    </li>
		                    <li class="fl w75  Rright">
		                        <div class="f14  c717583">张数</div>
		                        <div class="w f16 lh40 mt8" id="pjnum"></div>
		                    </li>
		                    <li class="fl w270 ">
		                        <div class="f14  c717583">天数</div>
		                        <div class="f16 lh20 mt8">最短<span class="pjduan"></span>天</div>
		                        <div class="f16 lh20">最长<span class="pjchang"></span>天</div>
		                    </li>
		                    <input id="dcrdId" type="hidden" />
                            <div class="cb"></div>
                        </ul>
                    </div>
                    <div class="pb10">
                        <div class="mt10 c7d7d7d">发表评价：</div>
                        <textarea style="resize:none" maxlength="140" class="fl ti8 w700 h100 bae0e0e0 bcwhite mt12 f14 pt10" name="" id="contents" placeholder="请畅所欲言吧......"></textarea>
                    </div>
                    <div class="cb"></div>
                    <div class="mt16">
                        <div class="fl f16 lh24">是否上门</div>
                        <ul class="fl lh27 ml30">
                            <li class="fl mr20 h21"><input type="radio" id="yes" class="fl radio1 w21 h21" name="isToDoor" value="1" checked="checked"><label class="fl ml10" for="yes">是</label></li>
                            <li class="fl h21"><input type="radio" id="no" class="fl radio1 w21 h21" name="isToDoor" value="0"><label class="fl ml10" for="no">否</label></li>
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
                <input type="button" class="w268 h45 lh45 cwhite br5 b0 bcfc4d42 bc mt25 ml243 f18" value="发表评价" id="evaluate2">
            </div>
            <!-- 评价成功-->
            <div class="pr t170 none tc none" id="evaluateDiv3">
                <div class="f24 fb">您已评论成功， 感谢您使用票管家贴现</div>
                <div class="f24 mt30 lh24">
                    <div class="fl f18 ml160">您可以进行</div>
                    <a href="${basePath}/discountrecord/discount?ym=pl" class="fl f18 c3366cc ml25">再下一单</a>
                </div>
                <div class="w268 bc mt65">
                    <input type="button" class="w268 h45 lh45 cwhite br5 b0 bcfc4d42 bc mt25 f18" value="暂时不需要"  id="evaluate4">
                </div>
            </div>
        </div>

    </div>
</div>

[@main.footer/]
[/@main.body]



<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>

<script type="text/x-handlebars-template" id="DISCOUNTRECORD">
{{#each results}}
		<div class="mt30 fb f18 lh20 bb3_e0e0e0 cd43c33">
		   	{{toEnum orderflag  commentId}}
		</div>
		<div class="mt30 bte0e0e0 ble0e0e0 bre0e0e0 mb20">
		    <div class="f14">
		        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
		            <div class="fl w220">批量订单号</div>
		            <div class="fl w210">票据总金额</div>
		            <div class="fl w100">票据总数量</div>
		            <div class="fl w125">票据到期天数</div>
		            <div class="fl w300">票据特征</div>
		        </div>
		        <div class="cb"></div>
		        <div class="h265 bcwhite bbe0e0e0 pt25 pb25">
		            <div class="fl w655 Rright">
		                <div class="bbe0e0e0 tc f16 h90 pb25">
		                    <div class="fl w204 Rright h40 tl c3366cc f16 xzym" data-no="{{no}}">
                                {{no}}
                            </div>
		                    <div class="fl w210 Rright h90 lh30">
		                        <div class=""><span style="color:red">{{allmoney}}</span>万元</div>
		                        <div class="">票面最小金额为<span style="color:red">{{min_money}}</span>万元</div>
		                        <div class="">票面最大金额为<span style="color:red">{{max_money}}</span>万元</div>
		                    </div>
		                    <div class="fl w100 Rright h90 lh30"><span style="color:red">{{amount}}</span>张</div>
		                    <div class="fl w125 h90 lh30">
		                        <div class="">最短<span style="color:red">{{min_expiry_day}}</span>天</div>
		                        <div class="">最长<span style="color:red">{{max_expiry_day}}</span>天</div>
		                    </div>
		                </div>
		                <div class="cb"></div>
		                <div class="tc f16 pt25 h90">
		                    <div class="fl w210 Rright h110 tl ml10">
		                        <div class="ti8">包含承兑行：</div>
		                        <ul class="ti8 mt16 lh35 lis" >
		                        </ul>
		                    </div>
		                    <div class="fl pl20 h90">
		                        <div class="fl w50 h90">备注：</div>
		                        <div class="fl w345 h90 tl lh20">{{remarks}}
		                        </div>
		                    </div>
		                </div>
		            </div>
		            <div class="fl w300">
		                <div class="tc lh35">
		                    <a href="#" class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">{{toType type1}}</a>
		                    <a href="#" class="{{toTicket flaw_ticket}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">瑕疵票</a>
		                    <a href="#" class="{{toDoor need_todoor}} fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25">需要上门</a>
		                </div>
		            </div>
		        </div>
		        <div class="cb"></div>
		        <div class="h65 lh65 ti8 ml10 mr10 xuxian">
		            订单有效期至：<span>{{formatDate endtime}}</span>
		        </div>
		        <div class="cb"></div>
		        <div class="w f14 c717583 mt25 pb25 ml10 mr10 xuxian {{hasOrg org_id}}">
		            推荐机构：<span class="f16 c2d2d2d">{{company}}</span>
		        </div>
		        <div class="pb25 bbe0e0e0 mt25 {{hasOrg org_id}}">
		            <div class="fl w440 Rright mr20">
		                <div class="fl w144 f14 c717583 tl ml10">
		                    联系人：<span class="f16 c2d2d2d">{{name}}</span>
		                </div>
		                <div class="fl f14 c717583 tl">
		                    <div class="ti8">联系方式：<span class="f16 c2d2d2d">{{toPhone orderflag phone}}</span></div>
		                    <div class="f12 mt10 c2d2d2d">（联系贴现机构时请说是在票据管家平台上看到的）</div>
		                </div>
		                <div class="cb"></div>
		            </div>
		            <div class="fl">
		                <div class="fl">评价：</div>
		                <div class="fl w420 tc">
		                    <div class="fl w105">
		                        <div class="c717583 f14">上门率</div>
		                        <div class="mt10"><span style="color:red">{{pjdoor}}</span></div>
		                    </div>
		                    <div class="fl w105">
		                        <div class="c717583 f14">价格真实</div>
		                        <div class="mt10"><span style="color:red">{{pjprice}}</span></div>
		                    </div>
		                    <div class="fl w105">
		                        <div class="c717583 f14">服务态度</div>
		                        <div class="mt10"><span style="color:red">{{pjservice}}</span></div>
		                    </div>
		                    <div class="fl w105">
		                        <div class="c717583 f14">打款速度</div>
		                        <div class="mt10"><span style="color:red">{{pjspeed}}</span></div>
		                    </div>
		                </div>
		            </div>
		            <div class="cb"></div>
		        </div>
		        <div class="w h70 bbe0e0e0 bcf9f9f9 tc f18 pt20">
		            <a href="#" data-no="{{no}}" class="{{toShow orderflag  commentId 1}} fr cd43c33 ba2_fc4d42 br3 dsb w166 h45 lh45 mr40 evaluate1" id="evaluate1">去评价</a>
		            <a href="#" data-no="{{no}}" class="{{toShow orderflag  commentId 2}} fr cd43c33 ba2_fc4d42 br3 dsb w166 h45 lh45 mr40 finish" id="finish">确认已完成</a>
		            <a href="#" data-no="{{no}}" data-orderflag="{{orderflag}}" class="{{toShow orderflag  commentId 3}} fr c2d2d2d ba2_e0e0e0 br3 dsb w166 h45 lh45 mr40 reason" id="reason">取消订单</a>
		        </div>
		    </div>
		</div>
{{/each}}
</script>

<script type="text/javascript">
	Handlebars.registerHelper('toSml', function(sml,options) {
	    if(sml==null || sml ==""){
	        return "--";
	    }else {
	        return sml+"%";
	    }
	});
	Handlebars.registerHelper('toPhone', function(orderflag,phone,options) {
	    if(orderflag==3){
	        return hideMobile(phone);
	    }else {
	        return phone;
	    }
	});
	Handlebars.registerHelper('toShow', function(orderflag,com,num,options) {
		 if(1 == orderflag){
	        if(num ==1 || num == 2){
	            return "none";
	        }
	    }else if(2 == orderflag){
	        if(num == 1 ){
	            return "none";
	        }
	    }else if(3 == orderflag){
	        if(num == 1  && com != null){//待评论
	            return "none";
	        }else if(num != 1){
	        	return "none";
	        }
	    }else if(0 == orderflag || -2 == orderflag || -1 == orderflag){
	    	return "none";
	    }
	    return null;
	    
	});
	Handlebars.registerHelper('toEnum', function(orderflag,com,options) {
		if(com==null && orderflag==3){
	        return "待评价";
	    }else{
	        return getBnsStatePL(orderflag,false);
	    }
	});
	Handlebars.registerHelper('formatDate', function(num, options) {
	    if(num!=null){
	        num = num.replace(/-/g, "/");
	        var d = new Date(num);
	        return d.format('yyyy-MM-dd');
	    }else{
	        return "--";
	    }
	});
	Handlebars.registerHelper('toType', function(num, options) {
	    if(num==1){
	        return "纸票";
	    }else{
	        return "电票";
	    }
	});
	
	Handlebars.registerHelper('toPrice', function(rate,rate1,rate2,way,type1, options) {
	    return getPriceYp(rate,rate1,rate2,way,type1);
	});

	/* 
	 * 格式化上门需求
	 */
	Handlebars.registerHelper('toDoor', function(num, options) {
	    if(num!=null){
	    	if(num==0){
	    		return "none";
	    	}
	    }
	});
	
	/* 
	 * 格式化瑕疵票
	 */
	Handlebars.registerHelper('toTicket', function(num, options) {
	    if(num!=null){
	    	if(num==1){
	    		return "none";
	    	}
	    }
	});
	
	Handlebars.registerHelper('hasOrg', function(num, options) {
	    if(num==null){
	        return "none";
	    }
	});

</script>

<script type="text/javascript">
	/**
	* 初始化页面加载
	*/
	function loadDate(){
		var org =0 ;
		org = $('input[name="orderTab"]:checked').val();
		var data = {};
		
		if(org == 0){
			//查询所有的订单
        }else if(org == 1){
        	//查询待交易的订单
        	data.orderflag = 1;
        }else if(org == 2){
        	//查询交易中的订单
        	data.orderflag = 2;
        }else if(org == 3){
        	//查询待评价的订单
        	data.orderflag = 3;
        	data.comment = 1;
        }else if(org == 4){
        	//查询完成的订单
        	data.orderflag = 3;
        	data.comment = 0;
        }else if(org == 5 ){
        	//查询无效的订单
        	data.wuxiao1 = 0;
        	data.wuxiao2 = -2;
        }
		
		var start = $("#start").val();
		var end = $("#end").val();
		var maxallmoney = $("#maxallmoney").val();
		var minallmoney = $("#minallmoney").val();
		
		if(minallmoney=="" || minallmoney == null){
			minallmoney = 0;
		}
		if(start!=""){
			data.start1 = start;
		}
		if(end!=""){
			data.end1 = end;
		}
		if(maxallmoney!=""){
			data.maxallmoney = maxallmoney;
		}
		if(minallmoney!=""){
			data.minallmoney = minallmoney;
		} 
		
		var pageIndex = 1;//分页
		$('#pager').sjAjaxPager({
			
		    url: "${basePath}/discountorderpl/list",
		    searchParam:data,
		    beforeSend: function () {
		    	
		    },success: function (data) {
		    	var source = $("#DISCOUNTRECORD").html();
		        var template = Handlebars.compile(source);
		        var html = template(data.data);
		        $("#content").html(html);
		        
		        $(".lis").each(function(i){
		        	$(this).html(getType2pl(data.data.results[i].type2,true));
		        })
		        
		        $(".reason").live("click",qxdd);
		        $(".finish").live("click",qrwc);
		        $(".evaluate1").live("click",qpj);
		        $(".xzym").live("click",xzym);
		        
		    },complete: function(){
		    },error:function(){
		    	
		    }
		});
	};
	
	/**
	* 加载角标
	*/
	function badge(){
		 $.ajax({
				type: "POST",
		     	url: "${basePath}/discountrecord/bns/badge",
		     	data: {type:"pl"},
		     	dataType:"json",
		     	success:function(data){
		     		if(data.response == "success"){
		     			if(data.list_4>0){
		     				$(".badgepay").text(data.list_4);
		     				$(".badgepay").removeClass("none");
		     			}
		     			if(data.list_>0){
		     				$(".badgetrade").text(data.list_);
		     				$(".badgetrade").removeClass("none");
		     			}
		     			if(data.list_2>0){
		     				$(".badgeevaluated").text(data.list_2);
		     				$(".badgeevaluated").removeClass("none");
		     			}
		     		}else{
		     			alert(data.msg)
		     		}
		    	}
		});
	};

	$(function(){
		loadDate();
	    badge();
	})
</script>
<script type="text/javascript">

	/**
	* 跳转到详情页面
	*/
	function xzym(){
		var id = $(this).attr("data-no");
		location.href = "${basePath}/discountorderpl/optpage?no="+id;
	}
	
	/**
	* 打开取消订单的弹出
	*/
	function qxdd(){
		$("#qxid").val($(this).attr("data-no"));
		$("#qxzt").val($(this).attr("data-orderflag"));
		$("#window").removeClass("none");
	    $("#reasonDiv").removeClass("none");
	}
	
	/**
	* 打开确认完成的弹出
	*/
	function qrwc(){
		var id = $(this).attr("data-no");
	    $.ajax({
			type: "POST",
	     	url: "${basePath}/discountorderpl/get",
	     	data: {id:id},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
					$("#qrno").text(data.data.no);
					$("#qrallmoney").text(data.data.allmoney+"万元");
					$("#qrnum").text(data.data.amount+"张");
					$("#qrbank").text(data.data.bank);
					$(".chang").text(data.data.max_expiry_day);
					$(".duan").text(data.data.min_expiry_day);
					$("#qrid").val(data.data.no);
	     		}else{
	     			alert(data.msg)
	     		}
	    	}
		 });
	     $("#window").removeClass("none");
	     $("#finishDiv").removeClass("none");
	}
	
	/**
	* 打开评价页面的弹出
	*/
	function qpj(){
		$("#window").removeClass("none");
        $("#evaluateDiv1").removeClass("none");
        var id = $(this).attr("data-no");
        $.ajax({
			type: "POST",
	     	url: "${basePath}/discountorderpl/get",
	     	data: {id:id},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
					$("#pjcompany").text(data.data.company);
					$("#pjno").text(data.data.no);
					$("#pjallmoney").text(data.data.allmoney+"万元");
					$("#pjnum").text(data.data.amount+"张");
					$(".pjchang").text(data.data.max_expiry_day);
					$(".pjduan").text(data.data.min_expiry_day);
					$("#dcrdId").val(data.data.no);
	     		}else{
	     			alert(data.msg)
	     		}
	    	}
		 });
	}
</script>


<script>
//        选择tab
        $(".orderTab1").click(function () {
        	$(".orderTab li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
        	$(".orderTab li").removeClass("bbd43c33");
        	$(".orderTab li").children("label").removeClass("f20");
           
            $(this).parent().prev().attr("checked",true);
	        $(this).parents("li").addClass("bbd43c33");
	        $(this).parents("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
            loadDate();
    	    badge(); 
        });
    //日历
    !function(){
        laydate.skin('dahong');//切换皮肤，请查看skins下面皮肤库
    }();
    //日期范围限制
    //    贴现日期
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD',
        //min: laydate.now(), //设定最小日期为当前日期
        min: '1900-01-01', //设定最小日期
        max: '2099-06-16', //最大日期
        istime: true,
        istoday: false,
        choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    //    到期日期
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD',
        min: laydate.now(),
        max: '2099-06-16',
        istime: true,
        istoday: false,
        choose: function(datas){
            start.max = datas; //结束日选好后，充值开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);

    //    拒绝订单理由显示隐藏
    $("#reason").click(function(){
        $("#window").removeClass("none");
        $("#reasonDiv").removeClass("none");
    });
    //    填写理由
    $("#cancel").change(function(){
        var value = $(this).val();
        if(value==4){
            $("#reason1_div").removeClass("none");
        }else{
            $("#reason1_div").addClass("none");
        }
    });

    //    确认订单显示隐藏
    $("#finish").click(function(){
        $("#window").removeClass("none");
        $("#finishDiv").removeClass("none");
    });
    //    去编辑评价显示隐藏
    $("#evaluate1").click(function(){
        $("#window").removeClass("none");
        $("#evaluateDiv1").removeClass("none");
    });
    //    确认评价显示隐藏
    $("#evaluate2").click(function(){
    	if($("#evaluate2").attr("disabled")){
			return;
		}
        var isToDoor ;
    	$("input[name='isToDoor']").each(function(){
    		if($(this).attr("checked") == "checked"){
    			isToDoor = $(this).val();
    		}
    	})
    	var price ;
    	$("input[name='price']").each(function(){
    		if($(this).attr("checked") == "checked"){
    			price = $(this).val();
    		}
    	})
    	var service ;
    	$("input[name='service']").each(function(){
    		if($(this).attr("checked") == "checked"){
    			service = $(this).val();
    		}
    	})
    	var speed ;
    	$("input[name='speed']").each(function(){
    		if($(this).attr("checked") == "checked"){
    			speed = $(this).val();
    		}
    	})
    	var content = $("#contents").val();
    	if($.trim(content).length == 0 || content == null || content == ""){
    		alert("请填写评论后在提交");
    		return ;
    	}
    	var dcrdId = $("#dcrdId").val();
    	$("#evaluate2").attr("disabled","disabled");//按钮禁用
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountorderpl/comments/save",
	     	data: {no:dcrdId,isToDoor:isToDoor,price:price,service:service,speed:speed,
	     		content:content,type:2},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			$("#evaluate2").removeAttr("disabled");//按钮启用
	     			$("#window").removeClass("none");
	     	        $("#evaluateDiv1").addClass("none");
	     	        $("#evaluateDiv3").removeClass("none");
	     	        loadDate();
	     		    badge();
	     		}else{
	     			$("#evaluate2").removeAttr("disabled");//按钮启用
	     			alert(data.msg)
	     		}
	    	}
		 })	
    });
    //    评价成功显示隐藏
    $("#evaluate3").click(function(){
        $("#window").removeClass("none");
        $("#evaluateDiv2").addClass("none");
        $("#evaluateDiv3").removeClass("none");
    });
    $("#evaluate4").click(function(){
        $("#window").addClass("none");
        $("#evaluateDiv3").addClass("none");
    });
    $(".redClose").click(function(){
        $("#window").addClass("none");
        $("#reasonDiv").addClass("none");
        $("#finishDiv").addClass("none");
        $("#evaluateDiv1").addClass("none");
        $("#evaluateDiv2").addClass("none");
    });

    //    评价星级
    $(".estimate").click(function(){
        var o = $(this);
        o.children().attr('checked', 'checked');
        with(o){
            removeClass("star2").addClass("star1");
            nextAll().removeClass("star1").addClass("star2");
            prevAll().removeClass("star2").addClass("star1");
        }
    });
    
    /**
    * 确认取消订单
    */
    $("#infor").click(function(){
    	if($("#infor").attr("disabled")){
			return;
		}
    	var id = $("#qxid").val();
    	var currentState = $("#qxzt").val();
    	var cancel = $("#cancel").val();
    	var reason = $("#reason1_div").val();
    	if(cancel==4 && reason=="" ){
    		alert("请输入取消订单的理由");
    		return ;
    	}
    	if(cancel==4 && $.trim(reason).length<15){
    		alert("请填写15字的理由");
    		return;
    	}
    	$("#infor").attr("disabled","disabled");//按钮禁用
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountorderpl/cancel",
	     	data: {id:id,currentState:currentState,cancel:cancel,reason:reason},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			$("#infor").removeAttr("disabled");//按钮启用
	     			$("#window").addClass("none");
	     	        $("#reasonDiv").addClass("none");
	     	        $("#finishDiv").addClass("none");
	     	        $("#evaluateDiv1").addClass("none");
	     	        $("#evaluateDiv2").addClass("none");
	     	        loadDate();
	     		    badge();
	     		}else{
	     			$("#infor").removeAttr("disabled");//按钮启用
	     			alert(data.msg)
	     		}
	     		
	    	}
		 })	
    });
    
    /**
    * 确认完成订单
    */
    $("#qrddwc").click(function(){
    	$.ajax({
			type: "POST",
	     	url: "${basePath}/discountorderpl/update/finish",
	     	data: {id:$("#qrid").val()},
	     	dataType:"json",
	     	success:function(data){
	     		if(data.response == "success"){
	     			$("#window").addClass("none");
	     	        $("#reasonDiv").addClass("none");
	     	        $("#finishDiv").addClass("none");
	     	        $("#evaluateDiv1").addClass("none");
	     	        $("#evaluateDiv2").addClass("none");
	     		}else{
	     			alert(data.msg)
	     		}
	    	}
		 })	
    });
    
    /**
    * 搜索
    */
    $("#sousuo").click(function(){
    	loadDate();
    })
</script>