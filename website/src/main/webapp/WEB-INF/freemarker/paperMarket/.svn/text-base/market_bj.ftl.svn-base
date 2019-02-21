[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>

[@main.header currentmenu='1'/]

<div>
    <div class="w1200 bc bcwhite box-sizing box-shadow mt20 pl30 pr30 pt30 pb30">
        <!-- 订单类别、时间 -->
        <div>

        </div>
        <p class="ce84c3d f16">
            <span class="orderType"></span><span class="c717583 f14 ml20">下单时间：<span class="begintime"></span></span>
        </p>
        <!-- 订单类别、时间 end -->
        <!-- 订单信息 -->
        <div class="mt20">
            <!-- 表头 -->
            <div class="clearfix oh bcf9f9f9 c717583 tc box-sizing bae0e0e0 f16 lh45">
                <div class="fl w190">票面金额</div>
                <div class="fl w200">到期日期/计息天数</div>
                <div class="fl w130">承兑行类型</div>
                <div class="fl w140">保证金</div>
                <div class="fl w290">承兑行</div>
                <div class="fl w187">备注</div>
            </div>
            <!-- 信息 -->
            <div class="bae0e0e0 mt20 f16 lh45 box-shadow1">
                <div class="bcf9f9f9 bbe0e0e0 box-sizing box-sizing c717583 pr h45 clearfix">
                    <img class="pa left top" src="" id="typeImg" alt="商票电票">
                    <div class="fl pl50">票号 <span id="draft_no"></span></div>
                    <div class="fr f14 ce84c3d cp pr10"></div>
                </div>
                <div class="w clearfix">
                    <div class="fl oh clearfix Rright box-sizing pl10 pr10">
                        <div class="clearfix oh bbe0e0e0 c666666 pt10 pb8 lh57 tc">
                            <div class="fl w180 Rright ce84c3d"><span class="allmoney"></span>万元</div>
                            <div class="fl w200 Rright"><span class="endtime"></span>/<span class="jxts"></span>天</div>
                            <div class="fl w130 Rright"><span class="type2"></span></div>
                            <div class="fl w140 Rright"><span class="deposit"></span>元</div>
                            <div class="fl w280"><span class="bank"></span></div>
                        </div>
                        <div class="clearfix oh c2d2d2d">
                            <div class="fl f14 c717583 mr20">成单率 <span class="c2d2d2d f16 singleRate">%</span></div>
                            <div class="fl f14 c717583 mr180">确认背书时间 <span class="c2d2d2d f16 endorseTime"></span></div>
                            <div class="fl f14 c717583 mr20">企业评分</div>
                            <div class="fl f16 c717583 c2d2d2d mr20">价格真实：<span class="price"></span></div>
                            <div class="fl f16 c717583 c2d2d2d mr20">服务态度：<span class="service"></span></div>
                            <div class="fl f16 c717583 c2d2d2d mr20">确认效率：<span class="speed"></span></div>
                        </div>
                    </div>
                    <div class="fl w184 h110 box-sizing pt10 pl7 f14 c666666 lh20 oh120 memberother"></div>
                </div>

            </div>
        </div>
        <!-- 订单信息 end-->

        <!-- 我的报价 -->
        <div class="mt20">
            <p class="bl3_e84c3d pl10">我的报价</p>

            <div class="clearfix oh bcwhite mt20 pl3 pr3 pb6">
                <div class="fl clearfix oh w556 br5 bt3_e84c3d lh45 tc box-sizing box-shadow pb20">
                    <div class="clearfix oh bcf9f9f9 c717583 ">
                        <div class="fl w_33">共扣息</div>
                        <div class="fl w_33">每十万贴息</div>
                        <div class="fl w_33">年利率</div>
                    </div>
                    <div class="clearfix oh pt10 pb10">
                        <div class="fl w_33 Rright"><span><input type="text" placeholder="请输入金额" class="w90 b0 h30 lh30 bae0e0e0 ti8 money validate[required],validate[custom[onlyNumberSp]]" onblur="paymoney();"></span>元</div>
                        <div class="fl w_33 Rright"><span class="txlx"></span></div>
                        <div class="fl w_33"><span class="rate"></span>%</div>
                    </div>
                    <div class="clearfix oh bcf9f9f9 c717583">
                        <div class="fl w_33">票款</div>
                        <div class="fl w_33 c3366cc appraisalFee societe none" id="Authentication">鉴证服务费<span class="societeName f14"></span></div>
                        <div class="fl w_33 appraisalFee none">实付金额</div>
                    </div>
                    <div class="clearfix oh pt10 pb10">
                        <div class="fl w_33 Rright">
                            <span id="paid"></span>元
                        </div>
                        <input type="hidden" value="" id="txje">
                        <div class="fl w_33 Rright appraisalFee societe none"><span id="appraisalFee"></span>元</div>
                        <div class="fl w_33 appraisalFee none"><span id="actualMoney"></span>元</div>
                    </div>

                    <div id="bankChoseBtn" class="br3 bae0e0e0_xx pt20 pb15 ml20 mr20 mt20 mb20 tc cp none jd_status" onclick="selectJdCibBank();">
                        <img src="https://img.utiexian.com/website/180903jingdong/order/add.png" alt="选择银行卡">
                        <p class="c979797">选择银行卡</p>
                    </div>
                    <div id="selectRole" style="color: red" class="none">
                        <span id="selectRoleJd">京东报价</span>  <span id="selectRoleXy">兴业报价</span>
                    </div>
                    <div class="br3 bae0e0e0_xx pt20 pb15 ml20 mr20 mt20 mb20 tc cp none selectJdCib" onclick="selectJdCibBank();">
                        <div class="flex-row flex-direction-column w220 bae0e0e0">
							<div class="flex-row bcf9f9f9 ml10" id="selectJdCibName">
								
							</div>
							<div class="flex-row flex-direction-row flex-justify-space-between bcwhite f14 c717583">
								<div class="ml10" id="selectJdCibNo"></div>
							</div>
						</div>
                    </div>
                    
                </div>

                <div class="fr clearfix oh w556 h420 br5 bt3_e84c3d box-sizing box-shadow pb20">
                    <div class="fl w_50 box-sizing pl30 lh24">
                        <div class="w mt20">
                            <p class="c717583 f14">保证金</p>
                            <p><span id="deposit"></span>元<span class="c3366cc f12">（交易完成后退回）</span></p>
                        </div>
                        <div class="w mt20" id="todoorPrice">
                            <p class="c717583 f14">上门费用</p>
                            <p class="ce84c3d">
                            	<input type="number" class="w100 mr10 b0 lh30 ti8 todoorPrice validate[required],validate[custom[onlyNumberSp]]" placeholder="请输入金额">元
                            </p>
                        </div>
                        <div class="w mt20 signatureFee">
                            <p class="c717583 f14">电子签章</p>
                            <p><span id="signatureFee">5</span>元</p>
                        </div>
                        <div class="w mt20 isVip none">
                            <p class="c717583 f14">撮合服务费</p>
                            <p class="ce84c3d"><span style="text-decoration:line-through;" class="fee isVip none"></span>元<img src="https://img.utiexian.com/website/PJGJ/redPackets/yearVpIcon.png" alt=""> 会员免费 <span class="f12">（票管家收取）</span></p>
                        </div>
                        <div class="w mt20 noVip none">
                            <p class="c717583 f14">撮合服务费<span class="fee"></span>元</p>
                            <p class="ccccccc none noCoupon">无可用红包 <span class="f12 c3366cc">（成为会员享优惠）</span></p>
                            <p class="ce84c3d none haveCoupon" id="openCoupon">红包<img src="https://img.utiexian.com/website/discount/redPacketsIcon.png" alt="" width="15" height="16" class="mr5"><span id="couponNum"></span></p>
                        </div>
                        <div class="w mt20">
                            <p class="c717583 f14">实付</p>
                            <p class="ce84c3d"><span class="actualPay"></span>元</p>
                        </div>
                    </div>

                    <div class="fl w_50">
                        <p class="c717583 mt20">票面</p>
                        <span id="promptStr" class="c717583">无票面</span>
		    			<a href="javascript:void(0);" id="zoom" target="_blank"><img src="" alt="" id="picpath" class="w240 h280 bc mt20 bcf8f8f8 none"></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- 我的报价 end -->

        <!-- 一口价 -->
        <div class="clearfix oh box-shadow tc mt20 pl20 pr20 none buyoutPrice">
            <div class="fl w_50 pt20 pb20">
                <p class="f14">一口价   共扣息：<span class="f16 ce84c3d" id="discount"></span>元</p>
                <p class="f14 mt10">
                    每十万：<span class="c878787" id="price2"></span>元&nbsp;&nbsp;&nbsp;&nbsp;
                    年利率：<span class="c878787" id="price"></span>%&nbsp;&nbsp;&nbsp;&nbsp;
                    票款：<span class="c878787" id="buyoutPrice"></span>元
                </p>
                <button class="w300 h40 f18 lh40 tc cwhite dsib br3 b0 bcfb7227 cp mt10" id="buyoutBtn">
                    买断 <span class="f16">（<span class="buyout_paid"></span>元）</span>
                </button>
            </div>
            <div class="fl w_50 pt20 pb20">
                <p class="f14">钱包余额&nbsp;&nbsp;<span class="f16 ce84c3d moneys" ></span>元&nbsp;&nbsp;&nbsp;&nbsp;实付&nbsp;&nbsp;<span class="f16 ce84c3d actualPay"></span>元</p>
                <div class="clearfix dsib oh f14 mt10">
                    截止时间：<span class="minute_show"></span>
                </div>
                <button class="w300 h40 f18 lh40 tc cwhite dsib br3 b0 bce84c3d cp mt10 confirmPrice">
                    确认订单
                </button>
            </div>
        </div>
		<!-- 报价 -->
        <div class="w box-sizing pl20 lh50 h50 bae0e0e0 box-shadow mt20 none noBuyoutPrice">
            <div class="fl f14 c666666">截止时间：<span class="minute_show"></span></div>

            <button type="button" class="fr f16 h bce84c3d b0 cwhite dsib cp w200 tc confirmPrice" id="confirmPrice">确认订单</button>
            <div class="fr mr20">钱包余额 <span class="ce84c3d moneys"></span>元</div>
            <div class="fr mr20">实付 <span class="ce84c3d actualPay"></span>元</div>
        </div>
        <!-- 报价 end -->

    </div>
</div>

<div class="w h pf bc05 zi10 top none"  id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="maskTitle"></div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn" class="dsb tc w20 cp">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--银行列表区域-->
                <div class="flex-row flex-direction-column w none" id="bankWindow">
                    <ul class="clearfix hmax200 f13 lh24 c7d7d7d box-sizing pl40 pr20 oya myScrollbar bankChoseDiv">
                        
                    </ul>
                    <div class="flex-row flex-align-self-center lh35 mt20">
                        <input type="button" value="确认选择" class="w140 h35 ba bad43c33 bce84c3d cwhite br3 cp" id="confirmSelectBank">
                    </div>
                </div>
                <!-- 银行列表区域 end-->
                
                <!-- 兴业数金鉴证服务费 -->
            <div class="flex-row flex-direction-column w lh30 none" id="societeWindow">
                <div class="flex-row pl10 pr10 mt40 flex-direction-column c545563 lh30">
	                <div class="flex-row flex-justify-space-between">
	                	<div>收费方：兴业数字金融股份有限公司</div>
	                	<div>单位：人民币（元）</div>
	                </div>
	                <div class="flex-row flex-direction-column tc bae0e0e0">
		                <div class="flex-row h40 lh40 fb f16 bbe0e0e0">
		                	<div class="flex-col-4 bre0e0e0">项目</div>
		                	<div class="flex-col-4 bre0e0e0">内容</div>
		                	<div class="flex-col-4">费用</div>
		                </div>
		                <div class="flex-row bbe0e0e0">
		                	<div class="flex-col-4 bre0e0e0">鉴证费用</div>
		                	<div class="flex-col-4 bre0e0e0">根据交易金额按比例收取</div>
		                	<div class="flex-col-4">0.6BP(最低6元最高300元封顶)</div>
		                </div>
	                </div>
	                <div class="flex-row flex-justify-end ce84c3d">
	                	<div>1BP=0.01%</div>
	                </div>
	                <div></div>
                </div>
                <!--按钮操作-->
                <div class="flex-row flex-justify-center lh30 mt30">
                    <input type="button" value="知道了" class="w140 h35 ba bad43c33 bcd43c33 cwhite br3 cp closeBtn">
                </div>
            </div>
            
            <!-- 京东鉴证服务费 -->
            <div class="flex-row flex-direction-column w lh30 none" id="jdWindow">
                <div class="flex-row pl10 pr10 mt40 flex-direction-column c545563 lh30">
	                <div class="flex-row flex-justify-space-between">
	                	<div>收费方：大同京东宜票金融信息科技有限公司</div>
	                	<div>单位：人民币（元）</div>
	                </div>
	                <div class="flex-row flex-direction-column tc bae0e0e0">
		                <div class="flex-row h40 lh40 fb f16 bbe0e0e0">
		                	<div class="flex-col-4 bre0e0e0">类别</div>
		                	<div class="flex-col-4 bre0e0e0">项目</div>
		                	<div class="flex-col-4 bre0e0e0">内容</div>
		                	<div class="flex-col-4">费用</div>
		                </div>
		                <div class="flex-row bbe0e0e0">
		                	<div class="flex-col-4 bre0e0e0">银行承兑汇票</div>
		                	<div class="flex-col-4 bre0e0e0">根据票面金额比例收取服务费</div>
		                	<div class="flex-col-4 bre0e0e0">票面金额 200 万元以内，包括 200 万</div>
		                	<div class="flex-col-4">免费</div>
		                </div>
		                <div class="flex-row bbe0e0e0">
		                	<div class="flex-col-4 bre0e0e0">银行承兑汇票</div>
		                	<div class="flex-col-4 bre0e0e0">根据票面金额比例收取服务费</div>
		                	<div class="flex-col-4 bre0e0e0">票面金额 200 万以</div>
		                	<div class="flex-col-4">交易金额的 0.0025%，(单张收费最高 200 元封顶)</div>
		                </div>
		                <div class="flex-row bbe0e0e0">
		                	<div class="flex-col-4 bre0e0e0">商业承兑汇票</div>
		                	<div class="flex-col-4 bre0e0e0">根据票面金额比例收取服务费</div>
		                	<div class="flex-col-4 bre0e0e0">不区分票面金额</div>
		                	<div class="flex-col-4">交易金额的 0.02%，(单张收费最低 50 元，最高 300 元封顶)</div>
		                </div>
	                </div>
	                <div class="flex-row flex-justify-end ce84c3d">
	                	<div>费用包含电子签章费5元&nbsp;&nbsp;&nbsp;1BP=0.01%</div>
	                </div>
	                <div></div>
                </div>
                <!--按钮操作-->
                <div class="flex-row flex-justify-center lh30 mt30">
                    <input type="button" value="知道了" class="w140 h35 ba bad43c33 bcd43c33 cwhite br3 cp closeBtn">
                </div>
            </div>
                
                <div class="pl20 pr20 coupon none">
		            <!--使用红包-->
			        <!--使用红包-->
			        <div class="flex-row flex-direction-row flex-justify-space-between flex-wrap h300 none" style="overflow: auto" id="couponWindow">
			        	
			        </div>
				</div>

                <!-- 查看示例 -->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150 pt20 none" id="examplesWindow">
                    <img src="https://img.utiexian.com/website/180903jingdong/kaihu/yingyezhizhao.png" alt="示例">
                </div>
                <!-- 查看示例 end -->
                
                <!-- 确认报价 -->
            <div class="flex-row flex-direction-column w lh30 none" id="confirmPriceWindow">
                <div class="flex-row flex-justify-center mt40">
                票方将收到的票款为：
                	<span class="cd43c33 paid"></span>
                </div>
                <div class="flex-row flex-justify-center mt10">
               您的实际支付金额为：
                	<span class="c3366cc actualMoney"></span>
                </div>
                <!--按钮操作-->
                <div class="flex-row flex-justify-center lh30 mt30">
                    <input type="button" value="修改报价" class="w150 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn cp">
                    <input type="button" value="确认报价并支付" id="discountSuccess" onclick="confirmPrice();" class="w150 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 cp none">
                	<input type="button" value="余额不足，去充值" id="recharge" class="w150 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 cp none" >
                </div>
            </div>
            
            <!-- 一口价 -->
            <div class="flex-row flex-direction-column w lh30 none" id="buyoutWindow">
            	<p class="ti32 pl20 pr20">选择一口价买断，支付保证金后，无需票方选择，直接生成订单，并进入交易扣款流程。</p>
	            <div class="flex-row flex-justify-center mt10">
                票方将收到的票款为：
                	<span class="cd43c33 buyout_paid"></span>元
                </div>
                <div class="flex-row flex-justify-center">
                执剑人账户支付金额：
                	<span class="cd43c33 buyout_actual"></span>元
                </div>
                <div class="flex-row flex-justify-center">
                钱包支付金额：
                	<span class="cd43c33 actualPay"></span>元
                </div>
                <!--按钮操作-->
                <div class="flex-row flex-justify-center lh30 mt30">
                    <input type="button" value="取消" class="w150 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn cp">
                    <input type="button" value="确认买断" id="confirmBuyout"  class="w150 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 cp">
                </div>
            </div>
            </div>
        </div>
    </div>
</div>

<!-- 票据转让合同 -->
<div class="w h pf bc05 zi200 top none" id="contractWindow">
    <div class="flex flex-alignItems-center flex-justify-center bc" style="height:80%;">
        <div class="flex-row flex-direction-column w h bcwhite bae0e0e0 br3 pb20 mt100">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>票据转让合同</div>
                <div class="flex-row flex-direction-row-reverse lh30">
	                <input type="button" value="签署同意" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20" onclick="agreeContract()">
	                <input type="button" value="取消" class="w90 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn">
	            </div>
            </div>
            <div class="pl20 pr20" style="height:85%;">
                <iframe style="width:100%;height:100%;" src="" id="iframe"></iframe>
            </div>
         </div>
    </div>
</div>

<input type="hidden" value="" id="did">
<input type="hidden" value="" id="is_designated">
<input type="hidden" value="" id="orderId">
<input type="hidden" value="" id="type1">
<input type="hidden" value="" id="need_todoor">
	        
<input type="hidden" id="couponId" name="couponId">
<input type="hidden" id="disc_bind_id" name="disc_bind_id">
<input type="hidden" id="disc_v_acct_acct_no" name="disc_v_acct_acct_no">
<input type="hidden" id="dist_bind_id" name="dist_bind_id">

[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="COUPON">
{{#each coupon}}
	<div class="flex-row flex-direction-row w240 h80 bae0e0e0 cp cpH mt25 br3" onclick="selectCoupon({{id}},{{money}});">
		<div class="w90 flex-row h flex-direction-column tc bcfc4d42 rbDashed">
			<div class="cwhite f20 mt3">¥<span class="f30 ml3">{{money}}</span></div>
			<div class="bc f12 bcwhite cfc4d42 br3 lh22 mt6 w70">满<span>20</span>元可用</div>
		</div>
		<div class="flex-row h flex-direction-column pl20">
			<div class="mt16 f14">{{toCouponType couponType}}</div>
			<div class="mt10 f10 c7d7d7d"><span>{{formatDate endDate}}</span>到期</div>
		</div>
	</div>
{{/each}}
</script>

<script type="text/x-handlebars-template" id="BANK">
{{#each data}}
	<li class="fl w200 h86 bcf9f9f9 bae0e0e0 box-sizing pt6 pb6 pl10 pr10 br5 mt10 mr30 box-shadow {{toBank bindId}}" data-no="{{bankName}}" data-b="{{bindId}}" data-name="{{accountNo}}" onclick="selectBankClass(this);">
		<input type="radio" name="bankChose" id="bankChose{{accountNo}}" class="none">
		<label class="w flex-row flex-direction-column cp" for="bankChose3{{accountNo}}">
			<p>{{toBankNoHide accountNo}}</p>
			<p>{{toBankBranchHide bankName}}</p>
		</label>
	</li>
{{/each}}
</script>

<script>
/* 
 *银行卡号的隐藏
 */
Handlebars.registerHelper('toBank', function(bind_id, options) {
	if(bind_id==null || bind_id ==""){
		return "none";
	}
});

/* 
 *银行卡号的隐藏
 */
Handlebars.registerHelper('toBankNoHide', function(accountNo, options) {
	var a = accountNo.substring(0,4);
	var b = accountNo.length - 3;
	var c = accountNo.substring(b);
	return a + "***" + c;
});

/* 
 *银行支行名称的隐藏
 */
Handlebars.registerHelper('toBankBranchHide', function(toBankBranchHide, options) {
	var a = toBankBranchHide.length;
	if(a > 6){
		var b = toBankBranchHide.substring(0,5);
		return b + "...";
	}else{
		return toBankBranchHide;
	}
});

/**
* 红包类型格式化
*/
Handlebars.registerHelper('toCouponType', function(couponType,options) {
    if(couponType=='GENERAL'){
        return "交易通用红包";
    }else if(couponType=='DISC'){
        return "交易红包";
    }else if(couponType=='DIST'){
        return "接单红包";
    }else if(couponType=='INQUIRYREPLY'){
        return "查询查复红包";
    }
});

/**
*	时间格式化
*/
Handlebars.registerHelper('formatDate', function(num, options) {
    if(num!=null){
        num = num.replace(/-/g, "/");
        var d = new Date(num);
        return d.format('yyyy-MM-dd');
    }else{
        return "--";
    }
});

	var id = '${id}';
	var bns_memberId = '${bns_memberId}';
	var order_type = '${order_type}';
	var memberId = '${member.id}';
	var orgId = '${orgId}';

	var jd_status = false;
	var cib_status = false;
    $(function () {
		loadDate();
		getBalance();
    });
    
    /**
	*展示订单
	*/
	function loadDate(){
		var url = '${bootAppPath}/dispatch/show/dist/price';
		var params = {id:id,order_type:order_type,memberId:bns_memberId,orgId:orgId};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				var data = data.data.data;
				console.log(data);
				_timer(data.create_time);
				if(data.buyout_price != null && data.buyout_price != ""){//可以一口价进行报价
					$(".buyoutPrice").removeClass("none");
					loadBuyoutPrice(data.allmoney,data.buyout_price,data.jxts);
				}else{
					$(".noBuyoutPrice").removeClass("none");
				}
				$("#draft_no").html(data.draft_no);
				$("#type1").val(data.type1);
				if(data.type1 == 1){//纸票
					if(data.has_ticket == 0){//票已开出	显示票面
						$("#promptStr").addClass("none");
						$("#picpath").attr("src","${bootPic}"+data.picpath);
						$("#zoom").attr("href","${bootPic}"+data.picpath);
						$("#picpath").removeClass("none");
					}else{
						$("picpath").addClass("none");
						$("promptStr").removeClass("none");
					}
				}else if(data.type1 == 2){//电票	显示票面
					$("#promptStr").addClass("none");
					$("#picpath").attr("src","${bootPic}"+data.picpath);
					$("#zoom").attr("href","${bootPic}"+data.picpath);
					$("#picpath").removeClass("none");
				}
				if(order_type == "DISCOUNTRECORD"){
					$(".ordershang").addClass("none");
					if(data.type1 == 1){
						$(".orderyin2").addClass("none");
						$("#typeImg").attr("src","https://img.utiexian.com/website/180606/yinzhi.png");
					}else if(data.type1 == 2){
						$(".accept_time_dian").removeClass("none");
						$(".orderyin1").addClass("none");
						$(".place").addClass("none");
						$("#todoorPrice").addClass("none");
						$("#typeImg").attr("src","https://img.utiexian.com/website/180606/yindian.png");
					}

					if(data.allmoney >300){
						$("#deposit").html(200);
					}else{
						$("#deposit").html(100);
					}
					$(".orderType").html("银票订单");
				}else if(order_type == "DISCOUNTRECORDSP"){
					$(".orderType").html("商票订单");
					$(".orderyin1").addClass("none");
					$(".orderyin2").addClass("none");
					$("#deposit").html(200);
					if(data.type1 == 1){
						$(".dian_type1").html("纸票");
						$("#typeImg").attr("src","https://img.utiexian.com/website/180606/shangzhi.png");
					}else if(data.type1 == 2){
						$(".dian_type1").html("电票");
						$(".place").addClass("none");
						$("#todoorPrice").addClass("none");
						$(".accept_time_dian").removeClass("none");
						$("#typeImg").attr("src","https://img.utiexian.com/website/180606/shangdian.png");
					}
				}
				
				$(".place").html(data.place);
				$(".memberother").html(data.memberother);
				
				if(data.type1 == 2){//展示手续费
					$(".appraisalFee").removeClass("none");
				}else if(data.type1 == 1){
					if(data.need_todoor == 1){
						$("#need_todoor").val(data.need_todoor);
					}else{
						$("#todoorPrice").addClass("none");
					}
				}
				
				$(".allmoney").html(data.allmoney);
				$(".deposit").html(data.deposit);
				$(".jxts").html(data.jxts);
				$(".endtime").html(data.endtime);
				$(".bank").html(data.bank);
				$(".begintime").html(data.begintime);
				$(".endorse").html(data.endorse);
				$(".printtime").html(data.printtime);
				
				$("#is_designated").val(data.is_designated);
				$("#did").val(data.did);
				$("#orderId").val(data.id);
				
				if(data.back_endorse == "T"){
					$(".ticketReturn").removeClass("none");
				}
				
				$(".type2").html(getBank(data.type2));
				if(data.need_todoor == 1){
					$(".need_todoor").removeClass("none");
				}
				
				$(".singleRate").html((data.singleRate)*100);
				$(".endorseTime").html(getTime(data.endorseTime));
				if(data.price != "--"){
					$(".price").html(data.price.toFixed(2));
				}else{
					$(".price").html("--");
				}
				if(data.service != "--"){
					$(".service").html(data.service.toFixed(2));
				}else{
					$(".service").html("--");
				}
				if(data.speed != "--"){
					$(".speed").html(data.speed.toFixed(2));
				}else{
					$(".speed").html("--");
				}
				if(data.disc_v_acct_acct_no!=null&&data.disc_v_acct_acct_no!=""){//说明票方支持兴业
					$("#disc_v_acct_acct_no").val(data.disc_v_acct_acct_no);
				}
				
				if(data.disc_bind_id!=null&&data.disc_bind_id!=""){//说明票方支持京东
					$("#disc_bind_id").val(data.disc_bind_id);
					loadJdCibStatus();//查看资方是否支持京东
				}
				loadVip();
			}else{
				alert("获取数据失败");
			}
		}
	};
	
	/**
    * 加载京东兴业开户的状态
    */
    function loadJdCibStatus(){
    	var url = '${bootAppPath}/jdjr/cib/account';
    	var params = {memberId:memberId,type:1};
    	var res = bootPost(url,params);
    	if(res != null){
    		var data = res.data;
    		if (data.response == 'success') {
    			var jdjr = data.data.jdjr;
    			var cib = data.data.cib;
    			if(jdjr!=null&&jdjr.status==2&&jdjr.check_state=="PASS"){
   					jd_status = true;
   					$(".signatureFee").addClass("none");
					$(".societeName").html("(含电子签章费)")
   					$("#bankChoseBtn").removeClass("none");
   					loadJdCard();
   					
   				}
   				if(cib!=null&&cib.status==2&&cib.cib_check_state=="PASS"){
   					cib_status = true;
   				}
   				if(jdjr!=null&&jdjr.status==2&&jdjr.check_state=="PASS"&&cib!=null&&cib.status==2&&cib.cib_check_state=="PASS"){
    				var disc_v_acct_acct_no = $("#disc_v_acct_acct_no").val();
    				if(disc_v_acct_acct_no!=null&&disc_v_acct_acct_no!=""){
    					$("#selectRole").removeClass("none");
    					$("#selectRoleJd").addClass("none");
    				}
    			}
    		}
    	}
    }
	
    /**
	* 选择京东报价
	*/
	$("#selectRoleJd").click(function(){
		jd_status = true;
		cib_status = false;
		$("#bankChoseBtn").removeClass("none");
		
		$(".signatureFee").addClass("none");
		$(".societeName").html("(含电子签章费)");
		$("#selectRoleXy").removeClass("none");
		$("#selectRoleJd").addClass("none");
		loadVip();
		$(".money").val("");
	});
	
	/**
	* 选择兴业报价
	*/
	$("#selectRoleXy").click(function(){
		jd_status = false;
		cib_status = true;
		$("#bankChoseBtn").addClass("none");
		
		$("#selectRoleXy").addClass("none");
		$("#selectRoleJd").removeClass("none");
		$(".signatureFee").removeClass("none");
		$(".societeName").html("");
		loadVip();
		$(".money").val("");
	});
	
    /**
	* 计算实付金额
	*/
	function paymoney(){
		if(!$(".money").validationEngine("validate")){
			$(".money").focus();
			return ;
		}
		var money = $(".money").val();
		var allmoney = $(".allmoney").html();
		var type1 = $("#type1").val();
		var jxts = $(".jxts").html();
		
		if(parseInt(allmoney*10000)<parseInt(money)){
			alert("扣息不能大于票面金额")
			$(".money").val("");
			$("#paid").html("");
			return ;
		}
		var receivables = ((allmoney*10000)-money);
		$("#paid").html(fmoney(receivables,2));
		$("#txje").val(receivables);
		$(".paid").html(fmoney(receivables,2)+"元");
		
		var params = {money:(allmoney*10000),txje:receivables,jxts:jxts};
		var url = '${bootAppPath}/dispatch/fee';
		var dist_bind_id = $("#disc_bind_id").val();
		var draft_no = $("#draft_no").html();
		if(jd_status){
			params.draftNo = draft_no;
			params.bindId = dist_bind_id;
		}
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				var appraisalFee = data.data.data.fee;
				if(type1 == 1){
					$(".actualMoney").html(fmoney(receivables,2)+"元");
				}else{
					$("#appraisalFee").html(appraisalFee);
					var actualMoney = parseFloat(receivables) + parseFloat(appraisalFee);
					$("#actualMoney").html(fmoney(actualMoney,2));
					$(".actualMoney").html(fmoney(actualMoney,2)+"元");
				}
				$(".rate").html(data.data.data.price);
				$(".txlx").html(data.data.data.price2);
			}else{
				alert(data.data.msg);
			}
		}
	};
    
    /*关闭所有弹窗*/
    $("#closeBtn ,.closeBtn").click(function(){
        $("#maskWindow").addClass("none"); /*内容区域*/
        $("#bankWindow").addClass("none"); /*银行列表区域*/
        
        $("#maskWindow").addClass("none");
		$("#buyoutWindow").addClass("none");
		
		$("#maskWindow").addClass("none");
		$("#confirmPriceWindow").addClass("none");
		
	    $("#societeWindow").addClass("none")//兴业数金
	    $("#jdWindow").addClass("none")//京东
    });
    
    /**
     * 鉴证费说明
     */
     $("#Authentication").click(function(){
     	if(jd_status){
 	    	$("#jdWindow").removeClass("none")//京东
     	}else{
     		$("#societeWindow").removeClass("none")//兴业数金
     	}
     	$("#maskWindow").removeClass("none"); /*内容区域*/
     });
    
    /**
	*  加载京东和兴业的账户
	*/
	function loadJdCard(){
		var	url = '${bootAppPath}/jdjr/cib/account/card';
		var params = {memberId:memberId,type:1};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				var source = $("#BANK").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data);
	            $(".bankChoseDiv").html(html);
			}else{
				alert(data.data.msg);
			}
		}
	};
	
	/**
	* 一口价展示
	*/
	function loadBuyoutPrice(allmoney,buyoutPrice,jxts){
    	var money = allmoney*10000;
    	var discount = parseInt(money) - parseInt(buyoutPrice);
    	
    	var params = {money:money,txje:buyoutPrice,jxts:jxts};
		var url = '${bootAppPath}/dispatch/fee';
		var dist_bind_id = $("#disc_bind_id").val();
		var draft_no = $("#draft_no").html();
		if(jd_status){
			params.draftNo = draft_no;
			params.bindId = dist_bind_id;
		}
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				$("#buyoutPrice").html(buyoutPrice);//票款
				$("#discount").html(discount);//共扣息
				$("#price2").html(data.data.data.price2);//每十万
				$("#price").html(data.data.data.price);//年利率
				
				$(".buyout_paid").html(buyoutPrice);//票方收到票款
				$(".buyout_actual").html(parseFloat(buyoutPrice)+parseFloat(data.data.data.fee));//资方实际支付金额
			}else{
				alert(data.data.msg);
			}
		}
	};
	
	/**
	* 确认报价的弹窗
	*/
	$(".confirmPrice").click(function(){
		if(!$(".money").validationEngine("validate")){
			$(".money").focus();
			return ;
		}
		var txje = $("#txje").val(); 
		if(txje == null || txje == ""){
			alert("请填入合适的总扣息！")
			return ;
		}
		var type1 = $("#type1").val();
		if(type1 == 1){
			var need_todoor = $("#need_todoor").val();
			if(need_todoor == 1){//需要上传上门费用
				if(!$(".todoorPrice").validationEngine("validate")){
					$(".todoorPrice").focus();
					return ;
				}
			}
		}
		
		var disc_bind_id = $("#disc_bind_id").val();
		var dist_bind_id = $("#dist_bind_id").val();
		if(jd_status){//资方可以京东报价
			if(disc_bind_id!=null&&disc_bind_id!=""){//票方支持京东报价
				if(dist_bind_id==null|| dist_bind_id==""){
					alert("请选择一张银行卡");
					return ;
				}
			}
		}
		
		var money = $("#money").html();
		var deposit = $(".actualPay").html();

		if(parseInt(money) < parseInt(deposit)){
			$("#recharge").removeClass("none");
			$("#discountSuccess").addClass("none");
		}else{
			$("#recharge").addClass("none");
			$("#discountSuccess").removeClass("none");
		}
		
		$("#maskTitle").html("确认报价");
		$("#maskWindow").removeClass("none");
		$("#confirmPriceWindow").removeClass("none");
	});
	
	/**
	* 机构确认报价
	*/
	function confirmPrice(){
		var jxts = $(".jxts").html();
		var paid = $("#txje").val();
		var txlx = $(".money").val();
		var deposit = $("#deposit").html();
		var todoor_price = $(".todoorPrice").val();
		var orderId = $("#orderId").val();
		var txje = parseInt(paid)/10000;

		var url = '${bootAppPath}/dispatch/save/disp/dist/price';
		var params = {orderType:order_type,orgId:orgId,orderId:orderId,
					txje:txje,txlx:txlx,todoor_price:todoor_price};
		
		var disc_bind_id = $("#disc_bind_id").val();
		var dist_bind_id = $("#dist_bind_id").val();
		if(jd_status){//资方可以京东报价
			if(disc_bind_id!=null&&disc_bind_id!=""){//票方支持京东报价
				if(dist_bind_id==null|| dist_bind_id==""){
					alert("请选择一张银行卡");
					return ;
				}else{
					params.bindId = dist_bind_id;
				}
			}
		}
		
		var couponId = $("#couponId").val();
		if(couponId != null && couponId != ""){//红包
			params.couponId = couponId;
		}

		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				//跳转到列表
				location.href = "${basePath}/hall/index2";
			}else{
				alert(data.data.msg);
			}
		}
	};
	
	/**
	* 买断一口价
	*/
	$("#buyoutBtn").click(function(){
		$("#maskTitle").html("一口买断");
		$("#maskWindow").removeClass("none");
		$("#buyoutWindow").removeClass("none");
	});
	
	/**
	* 确认买断
	*/
	$("#confirmBuyout").click(function(){
		var money = $(".moneys").html();
		var deposit = $(".actualPay").html();
		var orderId = $("#orderId").val();

		if(parseInt(money) < parseInt(deposit)){
			alert("账户余额不足，请充值！");
			return ;
		}
		
		var params = {orderId:orderId,orderType:order_type,orgId:orgId};
		var url = '${bootAppPath}/dispatch/save/buyoutprice';
		
		var disc_bind_id = $("#disc_bind_id").val();
		var dist_bind_id = $("#dist_bind_id").val();
		if(jd_status){//资方可以京东报价
			if(disc_bind_id!=null&&disc_bind_id!=""){//票方支持京东报价
				if(dist_bind_id==null|| dist_bind_id==""){
					alert("请选择一张银行卡");
					return ;
				}else{
					params.bindId = dist_bind_id;
				}
			}
		}
		
		var couponId = $("#couponId").val();
		if(couponId != null && couponId != ""){//红包
			params.couponId = couponId;
		}

		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				location.href = "${basePath}/hall/index2";
			}else{
				alert(data.data.msg);
			}
		}
	});
	
	/**
	* 加载用户是否为会员
	*/
	function loadVip(){
    	if(memberId == null || memberId == "")return;
    	var url = "${bootAppPath}/vipmember/get/by/memberid";
    	var type1 = $("#type1").val();
    	var params = {memberId:memberId,vipType:0};
    	var data = bootPost(url,params);
    	if(data != null){
    		if(data.data.response == 'success'){
        		$(".fee").html(parseInt(data.data.data.fee));
    			if(data.data.data.vipMember != null){
    				if(type1 == 1){
    					$(".actualPay").html(parseInt($("#deposit").html()));
    				}else{
    					if(jd_status){
        					$(".actualPay").html(parseInt($("#deposit").html()));
    					}else{
    						$(".signatureFee").removeClass("none");
        					$(".actualPay").html(parseInt($("#deposit").html())+parseInt($("#signatureFee").html()));
    					}
    				}
    				$(".isVip").removeClass("none");
    				$("#fee").html(parseInt(data.data.data.fee));
    			}else{
    				$(".noVip").removeClass("none");
    				$("#needCoupon").removeClass("none");
    				if(type1 == 1){
    					$(".actualPay").html(parseInt($("#deposit").html())+parseInt(data.data.data.fee));
    				}else{
    					if(jd_status){
        					$(".actualPay").html(parseInt($("#deposit").html())+parseInt(data.data.data.fee));
    					}else{
    						$(".signatureFee").removeClass("none");
        					$(".actualPay").html(parseInt($("#deposit").html())+parseInt(data.data.data.fee)+parseInt($("#signatureFee").html()));
    					}
    				}
    				
    				if(data.data.data.coupon != null){
    					if(data.data.data.coupon.length >0){
    						$(".haveCoupon").removeClass("none");
        	        		$("#couponNum").html(data.data.data.coupon.length+"个可用红包");
        	        		var source = $("#COUPON").html();
        		            var template = Handlebars.compile(source);
        		            var html = template(data.data.data);
        		            $("#couponWindow").html(html);
    					}else{
    						$(".noCoupon").removeClass("none");
    					}
    				}else{
    					$(".noCoupon").removeClass("none");
    				}
    			}
			}else{
				alert(data.data.msg);
			}
    	}
	};
	
	/**
	* 获取余额
	*/
	function getBalance(){
		var allmoney = $(".allmoney").html();
		var url = '${bootAppPath}/dispatch/get/deposit';
		var params = {memberId:memberId,allmoney:allmoney,order_type:order_type};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				var money = data.data.data.money;
				var deposit = $("#deposit").html();
				if(deposit > money){
					$("#money").html(money);
					$(".moneys").html(money);
				}else{
					$("#money").html(money);
					$(".moneys").html(money);
					$("#recharge").addClass("none");
				}
			}else{
				alert("获取数据失败");
			}
		}
	};
	
	/**
    * 打开红包弹窗
    */
    $("#openCoupon").click(function(){
    	$(".coupon").removeClass("none");
    	$("#maskWindow").removeClass("none");
    	$("#couponWindow").removeClass("none");
    	$("#maskTitle").html("选择红包");
    });
	
	/**
	* 选择要使用的红包
	*/
	function selectCoupon(id,money){
		$("#couponId").val(id);
		$("#couponNum").html("-"+money);
		
		$("#maskWindow").addClass("none");
		$("#couponWindow").addClass("none");//红包
		
		$(".coupon").addClass("none");
		
		var type1 = $("#type1").val();
		
		if(type1 == 1){
			$(".actualPay").html(parseInt(parseInt($("#deposit").html())+parseInt($(".fee").html())-parseInt(money)));
		}else{
			if(jd_status){
				$(".actualPay").html(parseInt(parseInt($("#deposit").html())+parseInt($(".fee").html())-parseInt(money)));
			}else{
				$(".actualPay").html(parseInt(parseInt($("#deposit").html())+parseInt($(".fee").html())+parseInt($("#signatureFee").html())-parseInt(money)));
			}
		}
	};

    /*-----打开弹窗-----*/
    //选择银行卡
    function selectJdCibBank(){
		$("#maskTitle").html("选择银行卡");
        $("#maskWindow").removeClass("none");
        $("#bankWindow").removeClass("none");
    };

    /*选择银行卡*/
    function choseBank() {
        $("input[name='bankChose']").click(function () {
            $(this).parents("ul.bankChoseDiv").children("li").removeClass('bad43c33 bce84c3d cwhite').addClass('bcf9f9f9 bae0e0e0');
            if ($(this).attr("checked") == "checked") {
                $(this).parent("li").removeClass('bcf9f9f9 bae0e0e0').addClass('bad43c33 bce84c3d cwhite');
            }
        });
    }
    
    /*选择银行卡*/
    function selectBankClass(obj) {
		var no = $(obj).attr("data-no");
		var name = $(obj).attr("data-name");
		var bindId = $(obj).attr("data-b");
		
		$("#selectJdCibNo").html(no);
		$("#selectJdCibName").html(name);
		$("#dist_bind_id").val(bindId);
		$("#selectRoleXy").addClass("none");
		
	    $(obj).parents("ul.bankChoseDiv").children("li").removeClass('bad43c33 bce84c3d cwhite').addClass('bcf9f9f9 bae0e0e0');
	    $(obj).removeClass('bcf9f9f9 bae0e0e0').addClass('bad43c33 bce84c3d cwhite');
    }
    
    /**
     * 确定选择银行卡
     */
     $("#confirmSelectBank").click(function(){
     	$("#bankWindow").addClass("none");
     	$("#maskWindow").addClass("none");
     	$("#bankChoseBtn").addClass("none");
     	$(".selectJdCib").removeClass("none");
     });
    
     /**
 	* 充值余额
 	*/
 	$("#recharge").click(function(){
 		$(".money").val("");
 		location.href = "${basePath}/bisicmessage/deposit";
 	});
	
	/**
	* 接单时间倒计时
	*/
	var timerArr = new Array();
    function _timer(create_time){
    	
    	var date1 = new Date();//'2017-9-26 15:36:00'
		var date2 = new Date(create_time); 

		date2.setMinutes(date2.getMinutes() + 105 , date2.getSeconds(), 0);
		var s1 = date1.getTime();
		var s2 = date2.getTime(); 
		
		var intDiff = Math.floor((s2 - s1)/1000); 
		
        var _t = window.setInterval(function(){
        	timerArr[timerArr.length] = _t;
            var day=0,
				hour=0,
				minute=0,
				second=0;//时间默认值
            if(intDiff > 0){
                minute = Math.floor(intDiff / 60);
                second = Math.floor(intDiff) - (minute * 60);
            }
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            $('.minute_show').html('('+minute+'分'+second+'秒'+')');
            if(intDiff<=0){
                clearInterval(_t);
            }
            intDiff--;
        }, 1000);
    };
    
    /**
	* 返回分秒
	*/
	function getTime(num){
		if(num == 0){
			return "--";
		}
		var minute = parseInt(num/60);
		var second = num % 60;
		return minute+"分"+second+"秒";
	};
	
	/**
	* 格式化金额
	*/
	function fmoney(s, n) { 
		n = n > 0 && n <= 20 ? n : 2; 
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
		var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1]; 
		t = ""; 
		for (i = 0; i < l.length; i++) { 
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : ""); 
		} 
		return t.split("").reverse().join("") + "." + r; 
	};
</script>