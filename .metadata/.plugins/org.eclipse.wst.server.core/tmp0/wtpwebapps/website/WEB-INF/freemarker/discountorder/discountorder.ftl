[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='1'/]

<div class="flex flex-direction-column mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态类型-->
    <div class="flex-row flex-justify-space-between bbe0e0e0 h27 lh18 mt30">
        <div class="flex-row flex-direction-row">
            <span class="cd43c33 f18 fb titlePrompt" id="titlePrompt"></span>
            <div class="ml30 place none"><img src="${image_url}/website/receive/position.png" class="h16 mr3 mt3"><span id="place"></span></div>
        </div>
        <div class="c717583">下单日期:<span id="begintime"></span></div>
    </div>

    <!--订单详情纸票 -->
    <div class="flex-row flex-direction-column w bae0e0e0 mt10 yinzhi pr">
    	<div class="ticketReturn pa t70 l_50 none"><img src="${imagePath}/website/paperMarket/returnTicket.png" alt="回头票"></div>
        <div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 tc bbe0e0e0 c717583">
            <div class="w232">银票订单号</div>
			<div class="w155">票面金额</div>
			<div class="w90">背书</div>
			<div class="w182">到期日期</div>
			<div class="w90">计息天数</div>
			<div class="w90">保证金</div>
			<div class="w320">票据特征</div>
        </div>
        <div class="flex-row flex-justify-space-between">
            <div class="flex-row flex-direction-column w830 Rright">
                <div class="flex-row flex-direction-row tc lh45 pt10 pb10 bbe0e0e0">
                    <div class="w232 Rright pt15">
						<span class="ordernumber fl ml20 lh20"></span>
                		<a class="fl ml10 download" title="下载合同"><img src="https://img.utiexian.com/website/receive/down.png" width="21" height="21" alt="下载合同"></a>
					</div>
                    <div class="w155 cd43c33 Rright" id="allmoney_zhi"></div>
                    <div class="w105 Rright" id="endorse_zhi"></div>
					<div class="w182 Rright" id="endtime_zhi"></div>
					<div class="w90 Rright" id="jxts_zhi"></div>
					<div class="w90" id="deposit_zhi"></div>
                </div>
                <div class="flex-row flex-justify-space-between w pt10 pb10">
                    <div class="flex-row flex-direction-row w599 flex-align-self-baseline">
                        <div class="w60 ml20">备注：</div>
                        <p class="w500 mr20" id="memberother_zhi"></p>
                    </div>
                </div>
            </div>
            <div class="flex-row flex-direction-row flex-wrap w320 lh34 tc">
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16" id="type1_zhi"></div>
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16" id="type2_zhi"></div>
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16" id="need_todoor_zhi">需要上门</div>
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16" id="flaw_ticket_zhi">瑕疵票</div>
            </div>
        </div>
    </div>
    
    <!--订单详情电票 -->
    <div class="flex-row flex-direction-column w bae0e0e0 mt10 yindian pr">
    	<div class="ticketReturn pa t70 l_50 none"><img src="${imagePath}/website/paperMarket/returnTicket.png" alt="回头票"></div>
		<div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 tc bbe0e0e0 c717583">
			<div class="w232">银票订单号</div>
			<div class="w158">票面金额</div>
			<div class="w320">承兑行</div>
			<div class="w129">保证金</div>
        	<div class="w320">票据特征</div>
		</div>
    	<div class="flex-row flex-justify-space-between">
        	<div class="flex-row flex-direction-column Rright">
            	<div class="flex-row flex-direction-row tc lh45 pt10 pb10 bbe0e0e0">
                	<div class="w232 Rright pt15">
						<span class="ordernumber fl ml20 lh20"></span>
                		<a class="fl ml10 download" title="下载合同"><img src="https://img.utiexian.com/website/receive/down.png" width="21" height="21" alt="下载合同"></a>
					</div>
                	<div class="w158 cd43c33 Rright" id="allmoney_dian"></div>
                	<div class="w320 Rright" id="bank"></div>
                	<div class="w129" id="deposit_dian"></div>
            	</div>
            	<div class="flex-row flex-justify-space-between w pt10 pb10">
            		<div class="flex-row flex-direction-column w212 pl20 lh30 Rright">
						<div>到期日期：<span id="endtime_dian"></span></div>
						<div>计息天数：<span id="jxts_dian"></span></div>
                	</div>
                	<div class="flex-row flex-direction-row w599 flex-align-self-baseline mt10">
                    	<div class="w60 ml20">备注：</div>
                    	<p class="w500 mr20" id="memberother_dian"></p>
                	</div>
            	</div>
        	</div>
        	<div class="flex-row flex-direction-row flex-wrap w320 lh34 tc">
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16" id="type1_dian"></div>
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16" id="type2_dian"></div>
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16" id="accept_time_dian"></div>
            </div>
    	</div>
	</div>

    <!--父级-->
    <div class="flex-row flex-direction-column w bae0e0e0 mt16 selectOrg">
    	<!-- 机构信息 -->
        <div class="pr yinzhi noOrg">
            <div class="pa top right w80 h24 lh24 tc cwhite f13 superiority"></div>
            <div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 f14 tc bbe0e0e0 c717583">
                <div class="w180">票款金额</div>
                <div class="w100">每十万贴息</div>
                <div class="w90">上门费用</div>
                <div class="w90">订单成交率</div>
                <div class="w100">打款速度</div>
                <div class="w113">历史报价持续性</div>
                <div class="w480">机构评分</div>
            </div>
            <div class="flex-row flex-direction-row lh40 pt10 pb8 tc bbe0e0e0 fb">
                <div class="flex-row flex-direction-row">
                    <div class="w180 cd43c33 Rright"><span class="txje"></span>万元</div>
                    <div class="w100 cd43c33 Rright"><span class="txlx"></span>元</div>
                    <div class="w90 Rright"><span class="todoor_price"></span>元</div>
                    <div class="w90 Rright"><span class="singleRate"></span>%</div>
                    <div class="w100 Rright"><span class="advanceTime"></span></div>
                    <div class="w113 Rright"><span class="priceDurative"></span>%</div>
                </div>
                <div class="flex-row flex-direction-row w480">
                    <div class="flex-col-4">价格真实：<span class="price"></span></div>
                    <div class="flex-col-4">服务态度：<span class="service"></span></div>
                    <div class="flex-col-4">确认效率：<span class="speed"></span></div>
                </div>
            </div>
        </div>
        <div class="yindian noOrg">
            <div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 f14 tc bbe0e0e0 c717583">
                <div class="w182">票款金额</div>
                <div class="w100">每十万贴息</div>
                <div class="w110">订单成交率</div>
                <div class="w120">打款速度</div>
                <div class="w144">历史报价持续性</div>
                <div class="w491">机构评分</div>
            </div>
            <div class="flex-row flex-direction-row lh40 pt10 pb8 tc bbe0e0e0 fb">
                <div class="flex-row flex-direction-row">
                    <div class="w182 cd43c33 Rright"><span class="txje"></span>万元</div>
                    <div class="w100 cd43c33 Rright"><span class="txlx"></span>元</div>
                    <div class="w110 Rright"><span class="singleRate"></span>%</div>
                    <div class="w120 Rright"><span class="advanceTime"></span></div>
                    <div class="w144 Rright"><span class="priceDurative"></span>%</div>
                </div>
                <div class="flex-row flex-direction-row w491">
                    <div class="flex-col-4">价格真实：<span class="price"></span></div>
                    <div class="flex-col-4">服务态度：<span class="service"></span> </div>
                    <div class="flex-col-4">确认效率：<span class="speed"></span></div>
                </div>
            </div>
        </div>
        <!--资方信息-->
        <div class="flex-row flex-direction-column flex-justify-space-around bbe0e0e0 c717583 noOrg tc noOrgPay">
        	<div class="flex-row flex-direction-row lh40">
        		<div class="flex-col-6">账户名：<span class="cblack" id="bankAcctAcctName"></span></div>
            	<div class="flex-col-6">联系人：<span class="cblack" id="name"></span></div>
        	</div>
            <div class="flex-row flex-direction-row">
            	<div class="flex-col-6 lh52">背书接收账户：<span class="cblack" id="bankAcctAcctNo"></span></div>
	            <div class="flex-col-6 flex-direction-column">
	                <div>联系方式：<span class="cblack" id="phone"></span></div>
	                <div class="cblack">（联系机构时请说是在票据管家平台上看到的）</div>
	            </div>
            </div>
            <div class="flex-row flex-direction-row lh40">
        		<div class="flex-col-6">支行行号：<span class="cblack" id="bankAcctCnapsCode"></span></div>
        		<div class="flex-col-6">报价资方名称：<span class="cblack" id="company"></span></div>
        	</div>
        	<div class="flex-row flex-direction-row lh40">
        		<div class="flex-col-6">支行全称：<span class="cblack" id="bankChildName"></span></div>
        	</div>
        </div>
        
        <div class="flex-row flex-direction-row w bbe0e0e0 none endorse_pic_path">
            <div class="mt16 ml30">上传背书凭证</div>
            <label for="uploadImg" class="mt16 ml30 mb16">
                <img src="https://img.utiexian.com/website/PJGJ/account/upload.png" id="noimg" alt="" width="156" height="156">
                <img src="https://img.utiexian.com/website/discount/pjyl.jpg" id="img" class="w400 h220 none">
                <input type="file" id="uploadImg" class="none">
                <input type="hidden" id="endorse_pic_path">
            </label>
        </div>
        <!-- 查看票面 -->
        <div class="flex-row flex-justify-row bbe0e0e0 pt15 pb15 picpath">
       		<a class="ml50 bcn b0 dsb viewPic">查看票面</a>
       		<img src="https://img.utiexian.com/website/discount/pjyl.jpg" class="w400 h220 ml50 showPic" style="display: none">
        </div>
        
        <!-- 提示与操作按钮 -->
        <div class="flex-row flex-justify-space-between pt15 pb10 bcf9f9f9 lh40">
            <div class="flex-row flex-direction-row">
                <img src="${image_url}/website/discount/timer.png" class="wa h20 mt10 ml10 ordertime">
                <div class="f18 ml8 mr10 cd43c33 minute_show ordertime"></div>
                <!--文字提示-->
                <div id="prompt" class="titlePrompt"></div>
            </div>
            <div class="flex-row flex-direction-row-reverse">
            	<input type="hidden" value="" id="discId">
            	<input type="hidden" value="" id="distId">
            	<input type="hidden" value="" id="orderflag">
            	<input type="hidden" value="" class="type1">
            	<input type="hidden" value="" id="cib_id">
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="endorse">确认背书</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="gathering">确认收款</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="evaluateBtn">去评价</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="cancel">取消订单</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="pay">去支付</button>
            </div>
        </div>
    </div>
    <div class="c717583 mt10 priceOrgNum none">已有<span id="priceOrgNum"></span>家机构报价过该订单</div>
	<!-- 派单选择机构 -->
    <div class="flex-row flex-direction-column w mt16 none" id="priceOrg"></div>

</div>
  [@main.right /]
<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="title"></div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn" class="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--选择机构-->
                <div class="flex-row flex-direction-column w lh30 none" id="selectionWindow">
                    <div class="flex-row pl105 mt40">
						公司名称：<span id="confirm_company"></span>
                        <input type="hidden" id="selectDistId" value=""/>
                    </div>
                    <div class="flex-row pl105">
						票款金额：<span class="cd43c33" id="confirm_txje"></span>
                    </div>
                    <div class="flex-row pl105">
						您是否选择该机构？
                    </div>
                    <!--按钮操作-->
                    <div class="flex-row flex-direction-row-reverse lh30 mt30">
                        <input type="button" value="确定" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20" onclick="selectUpdate()">
                        <input type="button" value="取消" class="w90 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn">
                    </div>
                </div>
                
                <!-- 下载交易合同 -->
                <div class="flex-row flex-direction-column w lh30 mt20 none contract">
                    <div class="flex-row flex-direction-column pl20">
					 	<input type="email" id="email" maxlength="30" class="w310 h30 bae0e0e0 br3 ti10 ml70 validate[required,custom[email]]" placeholder="请输入经办人邮箱号">
                    </div>
                    <div class="flex-row flex-direction-column pl20">
						<input type="button" onclick="sendEmail();" value="发送" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 ml200 mt10 cp" id="sendEmail">
                    </div>
                </div>

                <!--取消订单-->
                <div class="flex-row flex-direction-column w lh30 none" id="cancelWindow">
                    <div class="flex-row flex-justify-space-between h34 mt30">
                        <div class="fb tr">请选择取消理由：</div>
                        <select class="w400 bae0e0e0 br3 ti10 select400 h35" id="reason">
                            <option value="0">票面信息输入有误</option>
                            <option value="1">只为熟悉流程和询问价格</option>
                            <option value="2">价格不合适</option>
                            <option value="3">已提前出票</option>
                            <option value="4">其它</option>
                        </select>
                    </div>
                    <textarea class="h120 bae0e0e0 br3 pt10 pr10 pl10 mt20 none validate[required]"  id="reasonOther" maxlength="100" placeholder="最多100字的理由。"></textarea>
                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt40 mb20">
                        <input type="button" value="确定取消订单" id="confirm_reason" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 mr20">
                    </div>
                </div>
                
                <!--评价-->
                <div class="flex-row flex-direction-column w lh30 none" id="evaluateWindow">
                    <div class="flex-row flex-direction-column bae0e0e0 br5 pl10 pr10 pt10 pb100 mt20">
                        <div class="flex-row flex-direction-row">
                            <div id="comment_company"></div>
                            <div class="cd43c33 w60 h24 lh24 br5 bad43c33 tc ml20" id="comment_type"></div>
                        </div>
                        <div class="flex-row flex-direction-row mt10">
                            <div id="comment_name"></div>
                            <div class="ml20" id="comment_phone"></div>
                        </div>
                    </div>
                    <div class="h2 xuxian w mt10"></div>
                    <div class="lh30">发表评价：</div>
                    <textarea class="h120 bae0e0e0 br3 pt10 pr10 pl10" id="content" maxlength="200" placeholder="最多200个字符"></textarea>
                    <div class="flex-row flex-direction-column mt10 ">
                        <div class="flex-row flex-direction-row">
                            <div class="f18 mr10">价格真实</div>
                            <div class="flex-row flex-direction-row">
                                <label for="price1" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="price" id="price1" value="1" class="none">
                                </label>
                                <label for="price2" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="price" id="price2" value="2" class="none">
                                </label>
                                <label for="price3" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="price" id="price3" value="3" class="none">
                                </label>
                                <label for="price4" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="price" id="price4" value="4" class="none">
                                </label>
                                <label for="price5" class="evaluateRadio star2 w25 h24">
                                    <input type="radio" name="price" id="price5" value="5" class="none">
                                </label>
                            </div>
                        </div>
                        <div class="flex-row flex-direction-row">
                            <div class="f18 mr10">服务态度</div>
                            <div class="flex-row flex-direction-row">
                                <label for="service1" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="service" id="service1" value="1" class="none">
                                </label>
                                <label for="service2" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="service" id="service2" value="2" class="none">
                                </label>
                                <label for="service3" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="service" id="service3" value="3" class="none">
                                </label>
                                <label for="service4" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="service" id="service4" value="4" class="none">
                                </label>
                                <label for="service5" class="evaluateRadio star2 w25 h24">
                                    <input type="radio" name="service" id="service5" value="5" class="none">
                                </label>
                            </div>
                        </div>
                        <div class="flex-row flex-direction-row">
                            <div class="f18 mr10">确认效率</div>
                            <div class="flex-row flex-direction-row">
                                <label for="efficiency1" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="efficiency" id="efficiency1" value="1" class="none">
                                </label>
                                <label for="efficiency2" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="efficiency" id="efficiency2" value="2" class="none">
                                </label>
                                <label for="efficiency3" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="efficiency" id="efficiency3" value="3" class="none">
                                </label>
                                <label for="efficiency4" class="evaluateRadio star2 w25 h24 mr10">
                                    <input type="radio" name="efficiency" id="efficiency4" value="4" class="none">
                                </label>
                                <label for="efficiency5" class="evaluateRadio star2 w25 h24">
                                    <input type="radio" name="efficiency" id="efficiency5" value="5" class="none">
                                </label>
                            </div>
                        </div>
                    </div>
                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt40 mb20">
                        <input type="button" value="发表评价" id="comment" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 mr20">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 票据转让合同 -->
<div class="w h pf bc05 zi200 top none" id="contractWindow">
    <div class="flex flex-alignItems-center flex-justify-center bc w1000" style="height:80%;">
        <div class="flex-row flex-direction-column w h bcwhite bae0e0e0 br3 pb20 mt100">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>票据转让合同</div>
                <div class="flex-row flex-direction-row-reverse lh30">
	                <input type="button" value="签署同意" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20" onclick="agreeContract()">
	                <input type="button" value="取消" class="w90 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn">
	            </div>
            </div>
            <!---->
            <div class="pl20 pr20" style="height:85%;">
                <iframe style="width:100%;height:100%;" src="" id="iframe"></iframe>
            </div>
         </div>
    </div>
</div>
<input type="hidden" id="txlx"/>
<input type="hidden" id="allmoney"/>
<input type="hidden" id="dist_bind_id"/>
[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/javascript" src="${basePath}/js/layer/layer.js"></script>
<script type="text/x-handlebars-template" id="COMPANYLIST">
{{#each listmap}}
	<label class="{{toCompany todoor_price 1}} chooseJigou" for="choseJG{{distId}}">
		<div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 f13 tc bbe0e0e0 c717583 pr">
			<img  src="https://img.utiexian.com/website/180903jingdong/order/xingye-small.png" class="chooseBankIcon {{toStatus dist_v_acct_acct_no dist_bind_id 1}}">
			<img  src="https://img.utiexian.com/website/180903jingdong/order/jingdong-small.png" class="chooseBankIcon {{toStatus dist_v_acct_acct_no dist_bind_id 2}}">
			<div style="width:200px;">票款金额</div>
			<div style="width:100px;">每十万贴息</div>
			<div style="width:100px;">年利率</div>
			<div style="width:100px;">上门费用</div>
			<div style="width:100px;">订单成交率</div>
			<div style="width:100px;">打款速度</div>
			<div class="w110">历史报价持续性</div>
			<div style="width:339px;">机构评分</div>
			<div class="chooseBtn">选择该报价</div>
		</div>
		<div class="flex-row flex-direction-row lh40 pt10 pb8 tc fb f13">
			<div class="flex-row flex-direction-row">
				<div style="width:200px;" class="cd43c33 Rright">{{txje}}万元</div>
				<div style="width:100px;" class="Rright">{{price2}}元</div>
				<div style="width:100px;" class="Rright">{{price0}}%</div>
				<div style="width:100px;" class="Rright">{{todoor_price}}元</div>
				<div style="width:100px;" class="Rright">{{singleRate}}%</div>
				<div style="width:100px;" class="Rright">{{toAdvanceTime advanceTime}}</div>
				<div class="w110 Rright">{{priceDurative}}%</div>
			</div>
			<div class="flex-row flex-direction-row" style="width:340px;">
				<div class="flex-col-4">价格真实：{{toComments price}}</div>      
				<div class="flex-col-4">服务态度：{{toComments service}}</div>
				<div class="flex-col-4">确认效率：{{toComments speed}}</div>      
			</div>
		</div>
		<div class="pa bottom right w25 h25">     
			<input type="radio" name="choseJG" id="choseJG{{distId}}" class="chooseJG" value="{{company}}"/>
			<input type="hidden" name="discId" value="{{distId}}"/>
			<input type="hidden" name="txje" value="{{txje}}"/>
			<input type="hidden" name="txlx" value="{{txlx}}"/>
			<input type="hidden" name="dist_bind_id" value="{{dist_bind_id}}"/>
		</div>
	</label>
	<label class="{{toCompany todoor_price 2}} chooseJigou" for="choseJG{{distId}}">
		<div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 f13 tc bbe0e0e0 c717583 pr">
			<img  src="https://img.utiexian.com/website/180903jingdong/order/xingye-small.png" class="chooseBankIcon {{toStatus dist_v_acct_acct_no dist_bind_id 1}}">
			<img  src="https://img.utiexian.com/website/180903jingdong/order/jingdong-small.png" class="chooseBankIcon {{toStatus dist_v_acct_acct_no dist_bind_id 2}}">
			<div class="w212">票款金额</div>
			<div style="width:100px;">每十万贴息</div>
			<div style="width:100px;">年利率</div>
			<div style="width:100px;">订单成交率</div>
			<div style="width:100px;">打款速度</div>
			<div class="w144">历史报价持续性</div>
			<div style="width:392px;">机构评分</div>
			<div class="chooseBtn">选择该报价</div>
		</div>
		<div class="flex-row flex-direction-row lh40 pt10 pb8 tc fb f13">
			<div class="flex-row flex-direction-row">
				<div class="w212 cd43c33 Rright">{{txje}}万元</div>
				<div style="width:100px;" class="Rright">{{price2}}元</div>
				<div style="width:100px;" class="Rright">{{price0}}%</div>
				<div style="width:100px;" class="Rright">{{singleRate}}%</div>
				<div style="width:100px;" class="Rright">{{toAdvanceTime advanceTime}}</div>
				<div class="w144 Rright">{{priceDurative}}%</div>
			</div>
			<div class="flex-row flex-direction-row" style="width:393px;">
				<div class="flex-col-4">价格真实：{{toComments price}}</div>      
				<div class="flex-col-4">服务态度：{{toComments service}}</div>
				<div class="flex-col-4">确认效率：{{toComments speed}}</div>      
			</div>
		</div>
		<div class="pa bottom right w25 h25">     
			<input type="radio" name="choseJG" id="choseJG{{distId}}" class="chooseJG" value="{{company}}"/>
			<input type="hidden" name="discId" value="{{distId}}"/>
			<input type="hidden" name="txje" value="{{txje}}"/>
			<input type="hidden" name="txlx" value="{{txlx}}"/>
			<input type="hidden" name="dist_bind_id" value="{{dist_bind_id}}"/>
		</div>
	</label>
{{/each}}
</script>
<script type="text/javascript">
Handlebars.registerHelper('toAdvanceTime', function(advanceTime, options) {
    return getTime(advanceTime);
});

Handlebars.registerHelper('toComments', function(comments, options) {
    return commentsToFixed(comments);
});

Handlebars.registerHelper('toStatus', function(dist_v_acct_acct_no ,dist_bind_id,num, options) {
    if(num==1){
    	if(dist_v_acct_acct_no!=null&&dist_v_acct_acct_no!=""){
    	}else{
    		return "none";
    	}
    }else if(num==2){
    	if(dist_bind_id!=null&&dist_bind_id!=""){
    	}else{
    		return "none";
    	}
	}	
});

Handlebars.registerHelper('toCompany', function(todoor_price,num, options) {
	if(todoor_price != null){
		if(num == 2){
			return "none";
		}
	}else{
		if(num == 1){
			return "none";
		}
	}
});

	var memberId = ${member.id};
	var id = ${id};
	var orderflag = ${orderflag};
	var priceOrgNum = 0;

	var jd_status = false;
	$(function(){
		var img_upload=document.getElementById("uploadImg");
		
		// 添加功能出发监听事件
		img_upload.addEventListener('change',readFile,false);
		loadDate();
	});
	
	/**
	* boot 项目的图片上传
	*/
	function readFile(){
		var file=this.files[0];
		if(!/image\/\w+/.test(file.type)){ 
			alert("请确保文件为图像类型"); 
			return false; 
		}
		var reader=new FileReader();
		reader.readAsDataURL(file);
		reader.onload=function(){
			var index = this.result.indexOf(",");
			var base64Image =  this.result.substr(index+1);
			var url = '${bootAppPath}/upload/image';
			var params = {base64Image:base64Image};
			var data = bootPost(url,params);
			console.log(data);
			if(data != null){
				if(data.data.response == 'success'){
					$("#noimg").addClass("none");
					$("#img").removeClass("none");
					$("#endorse_pic_path").val(data.data.data.base64Image);
					$("#img").attr("src","${bootPic}"+data.data.data.base64Image);
				}
			}	
		}
	};
	
	/**
	* 初始化页面加载数据
	*/
	function loadDate(){
		if(orderflag != 1){
			getOrderInfo();
		}else{
			exhibitionOrder();
		}
	}; 
	
	/**
	*展示订单
	*/
	function exhibitionOrder(){
		var url = '${bootAppPath}/discountrecord/details';
		var params = {discId:id,order_type:'DISCOUNTRECORD'};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				$(".chooseJG").live("click",choseJG);
				var data = data.data.data;
				
				$("#orderflag").val(data.orderflag);
				$("#discId").val(data.id);
				$("#distId").val(data.dist_id);
				_timer(data.create_time,data.deposit_state,data.orderflag);
				if(data.orderflag == 0){
					$(".titlePrompt").html("无效订单");
				}else if(data.orderflag == 1){
					$("#cancel").removeClass("none");
					
					$(".titlePrompt").html("派单中");
					$(".noOrg").addClass("none");
					$("#priceOrg").removeClass("none");
					$(".priceOrgNum").removeClass("none");
					if(data.deposit_state == 0){
						$("#pay").removeClass("none");
					}
					
					if(data.listmap!=null){
						$("#priceOrgNum").html(data.listmap.length);
					}else{
						$("#priceOrgNum").html(0);
					}
					
					var source = $("#COMPANYLIST").html();
		            var template = Handlebars.compile(source);
		            var html = template(data);
		            $("#priceOrg").html(html);
					
				}else if(data.orderflag == 4){
					$(".noOrg").addClass("none");
				}
				$("#begintime").html(data.begintime);
				$(".txje").html(data.txje);
				$(".todoor_price").html(data.todoor_price);
				$("#bank").html(data.bank);
				
				if(data.place != null){
					$(".place").removeClass("none");
					$("#place").html(data.place);
				}
				if(data.picpath != null && $.trim(data.picpath).length >0){
					$(".showPic").attr("src","${bootPic}"+data.picpath);
				}else {
					$(".picpath").addClass("none");
				}
				$(".type1").val(data.type1);
				if(data.type1 == 1){
					$(".yindian").addClass("none");
					$(".ordernumber").html(data.ordernumber);
					$("#allmoney_zhi").html(data.allmoney+"万元");
					$("#allmoney").val(data.allmoney);
					$("#endtime_zhi").html(data.endtime);
					$("#endorse_zhi").html(data.endorse+"手");
					$("#jxts_zhi").html(data.jxts+"天");
					$("#deposit_zhi").html(data.deposit+"元");
					$("#memberother_zhi").html(data.memberother);
					$("#type1_zhi").html("纸票");
					$("#type2_zhi").html(getBank(data.type2));
					if(data.need_todoor == 0){
						$("#need_todoor_zhi").addClass("none");
					}
					if(data.flaw_ticket == 1){
						$("#flaw_ticket_zhi").addClass("none");
					}
					$("#type2_zhi").html(getBank(data.type2));
					
				}else if(data.type1 == 2){
					$(".yinzhi").addClass("none");
					$(".ordernumber").html(data.ordernumber);
					$("#allmoney_dian").html(data.allmoney+"万元");
					$("#allmoney").val(data.allmoney);
					$("#endtime_dian").html(data.endtime);
					$("#jxts_dian").html(data.jxts+"天");
					$("#deposit_dian").html(data.deposit+"元");
					$("#memberother_dian").html(data.memberother);
					$("#type1_dian").html("电票");
					$("#type2_dian").html(getBank(data.type2));
					if(data.accept_time == 1){
						$("#accept_time_dian").html("一年期");
					}else{
						$("#accept_time_dian").html("半年期");
					}
				}
				
				if(data.back_endorse == "T"){
					$(".ticketReturn").removeClass("none");
				}
			}else{
				alert("获取数据失败");
			}
		}
	};
	
	/**
	* 确认选定机构
	*/
	function selectUpdate(){
		var type1 = $(".type1").val();
		if(type1 == 1){
			agreeContract();
		}else{
			var distId = $("#selectDistId").val();
			
			if(jd_status){//京东合同
				$("#iframe").attr("src","${basePath}/jd/agreements/contract?distId="+distId+"&orderType=DISCOUNTRECORD");
			}else{//兴业合同
				$("#iframe").attr("src","${basePath}/agreements/contract?distId="+distId+"&orderType=DISCOUNTRECORD");
			}
			
			
			$("#selectionWindow").addClass("none");
			$("#maskWindow").addClass("none");
			$("#contractWindow").removeClass("none");
		}
	};
	
	/**
	* 同意票据转让合同
	*/
	function agreeContract(){
		layer.load(2);
		var selectDistId = $("#selectDistId").val();
		var txlx = $("#txlx").val();

		var url = '${bootAppPath}/discountrecord/update/select';
		var params = {selectDistId:selectDistId,orderflag:orderflag,id:id,txlx:txlx,memberId:memberId};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				layer.closeAll('loading');//关闭加载图标
				location.href = "${basePath}/hall/disc/index";
			}else{
				layer.closeAll('loading');//关闭加载图标
				alert(data.data.msg);
			}
		}else{
			layer.closeAll('loading');//关闭加载图标
		}
	};
	
	/**
	* 获取机构信息
	*/
	function getOrderInfo(){
		var url = '${bootAppPath}/order/isselect/details';
		var params = {discId:id,order_type:'DISCOUNTRECORD'};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				var orgInfo = data.data.data.listmap[0];
				var cib = data.data.data.cib;
				var cib_cancel = data.data.data.cib_cancel;
				var data = data.data.data;
				
				$("#dist_bind_id").val(data.dist_bind_id);
				
				$("#orderflag").val(data.orderflag);
				$("#discId").val(data.id);
				$("#distId").val(data.dist_id);
				$(".type1").val(data.type1);

				if(cib_cancel != null){
					$("#cib_id").val(cib_cancel.id);
					if(cib_cancel.orgTime != null){
						if(cib_cancel.bnsTime != null){
							if(data.orderflag == 0){
								$(".titlePrompt").html("无效订单");
								$("#prompt").html("无效订单");
							}else{
								$(".titlePrompt").html("双方同意取消订单，订单处理中！");
								$("#prompt").html("双方同意取消订单，订单处理中！");
							}
						}else{
							$(".titlePrompt").html("资方已经申请取消订单");
							$("#prompt").html("资方已经申请取消订单，如有异议，请联系客服！");
							$("#cancel").removeClass("none");
						}
					}else if(cib_cancel.bnsTime != null){
						$(".titlePrompt").html("票方已经申请取消订单");
						$("#prompt").html("票方已经申请取消订单，如有异议，请联系客服！");
					}
				}else{
					_timer(data.create_time,data.deposit_state,data.orderflag);
					if(data.orderflag == 0){
						$(".titlePrompt").html("无效订单");
					}else if(data.orderflag == 1){
						$(".noOrg").addClass("none");
						$("#begintime").html(data.begintime);
						$("#priceOrg").removeClass("none");
						$(".priceOrgNum").removeClass("none");
						$(".titlePrompt").html("派单中");
						
						$("#cancel").removeClass("none");
					}else if(data.orderflag == 2){
						if(data.dist_bind_id == null || data.dist_bind_id == ""){
							$("#endorse").removeClass("none");
						}
						$("#cancel").removeClass("none");
						$(".titlePrompt").html("背书中");
					}else if(data.orderflag == 3 && (data.c_id == null || $.trim(data.c_id).length == 0)){
						$("#evaluateBtn").removeClass("none");
						$(".titlePrompt").html("待评价");
					}else if(data.orderflag == 4){
						$(".noOrg").addClass("none");
						$("#cancel").removeClass("none");
						$(".titlePrompt").html("等待机构打款");
					}else if(data.orderflag == 5){
						$("#gathering").removeClass("none");
						$(".titlePrompt").html("等待收款");
					}else if(data.orderflag == 7){
						if(data.dist_bind_id == null || data.dist_bind_id == ""){
							$("#endorse").removeClass("none");
						}
						$("#cancel").removeClass("none");
						$(".titlePrompt").html("背书中");
					}else if(data.orderflag == 3 && data.c_id != null){
						$(".titlePrompt").html("已完成");
					}
				}
				
				$("#begintime").html(data.begintime);
				$("#bank").html(data.bank);
				
				if(data.back_endorse == "T"){
					$(".ticketReturn").removeClass("none");
				}
				
				if(data.picpath != null && $.trim(data.picpath).length >0){
					$(".showPic").attr("src","${bootPic}"+data.picpath);
				}else {
					$(".picpath").addClass("none");
				}
				if(data.place != null){
					$(".place").removeClass("none");
					$("#place").html(data.place);
				}
				if(data.type1 == 1){
					$(".yindian").addClass("none");
					$(".ordernumber").html(data.ordernumber);
					$("#allmoney_zhi").html(data.allmoney+"万元");
					$("#allmoney").val(data.allmoney);
					$("#endtime_zhi").html(data.endtime);
					$("#endorse_zhi").html(data.endorse+"手");
					$("#jxts_zhi").html(data.jxts+"天");
					$("#deposit_zhi").html(data.deposit+"元");
					$("#memberother_zhi").html(data.memberother);
					$("#type1_zhi").html("纸票");
					$("#type2_zhi").html(getBank(data.type2));
					if(data.need_todoor == 0){
						$("#need_todoor_zhi").addClass("none");
					}
					if(data.flaw_ticket == 1){
						$("#flaw_ticket_zhi").addClass("none");
					}
					$("#type2_zhi").html(getBank(data.type2));
					if(data.orderflag == 2){
						$(".endorse_pic_path").removeClass("none");
					}
					
				}else if(data.type1 == 2){
					$(".yinzhi").addClass("none");
					$(".ordernumber").html(data.ordernumber);
					$("#allmoney_dian").html(data.allmoney+"万元");
					$("#allmoney").val(data.allmoney);
					$("#endtime_dian").html(data.endtime);
					$("#jxts_dian").html(data.jxts+"天");
					$("#deposit_dian").html(data.deposit+"元");
					$("#memberother_dian").html(data.memberother);
					$("#type1_dian").html("电票");
					$("#type2_dian").html(getBank(data.type2));
					if(data.accept_time == 1){
						$("#accept_time_dian").html("一年期");
					}else{
						$("#accept_time_dian").html("半年期");
					}
				}
				
				if(orgInfo != null){
					$("#company").html(orgInfo.company);
					$("#name").html(orgInfo.name);
					$("#phone").html(orgInfo.mobile);
					
					$(".txje").html(orgInfo.txje);
					$(".txlx").html((orgInfo.txlx*10/data.allmoney).toFixed(2));
					$(".todoor_price").html(orgInfo.todoor_price);
					
					$(".singleRate").html(((orgInfo.singleRate)*100).toFixed(2));
					$(".advanceTime").html(getTime(orgInfo.advanceTime));
					$(".priceDurative").html(((orgInfo.priceDurative)*100).toFixed(2));
					if(orgInfo.price != "--"){
						$(".price").html(orgInfo.price.toFixed(2));
					}else{
						$(".price").html("--");
					}
					if(orgInfo.service != "--"){
						$(".service").html(orgInfo.service.toFixed(2));
					}else{
						$(".service").html("--");
					}
					if(orgInfo.speed != "--"){
						$(".speed").html(orgInfo.speed.toFixed(2));
					}else{
						$(".speed").html("--");
					}
				}else{
					$(".noOrg").addClass("none");
				}
				
				if(cib != null){
					$("#bankAcctAcctNo").html(data.cib.bankAcctAcctNo);
					$("#bankAcctCnapsCode").html(data.cib.bankAcctCnapsCode);
					$("#bankAcctAcctName").html(data.cib.bankAcctAcctName);
					$("#bankChildName").html(data.cib.bankChildName);
					
					if(data.dist_bind_id!=null){
						$("#company").html(cib.blic_company_name);
						$("#name").html(cib.lep_name);
					}
				}
			}else{
				alert("获取数据失败");
			}
		}
	};
	
	/**
	* 倒计时
	*/
	var timerArr = new Array();
    function _timer(create_time,deposit_state,orderflag){
    	if(deposit_state == 0){//未支付保证金，不显示倒计时
    		$(".ordertime").addClass("none");
    		return ;
    	}
    	if(orderflag == 0  || orderflag == 3){
			clearInterval(_t);
			$('.minute_show').html('('+0+'分'+0+'秒'+')');
			return;
		}
    	
    	var date1 = new Date();//'2017-9-26 15:36:00'
		var date2 = new Date(create_time); 

		date2.setMinutes(date2.getMinutes() + 120 , date2.getSeconds(), 0);
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
    * 选择机构
    */
    function choseJG(){
    	$("#title").html("选择机构");
    	$("input[name='choseJG']").parents("label").removeClass("bad43c33");
    	$("#selectionWindow").removeClass("none");
		$("#maskWindow").removeClass("none");
    	if ($(this).attr("checked") == "checked") {
			$("#confirm_company").html($(this).val());
			$("#selectDistId").val($(this).next().val());
			$("#confirm_txje").html($(this).next().next().val()+'万元');
			
			$("#txlx").val($(this).next().next().next().val());
			$(this).parents("label").addClass("bad43c33");
			
			var bind_id = $(this).next().next().next().next().val();
			if(bind_id!=null&&bind_id!=""){
				jd_status = true;
			}else{
				jd_status = false;
			}
   		 }
    };

	//关闭按钮
	$(".closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#endorseWindow").addClass("none"); //背书
	    $("#selectionWindow").addClass("none"); //选择机构
	    $("#appealWindow").addClass("none"); //申诉
	    $("#cancelWindow").addClass("none"); //取消
	    $("#contractWindow").addClass("none"); //转让合同
	    $(".contract").addClass("none"); //下载合同
	});
	
	//拒绝订单理由显示隐藏
	$("#cancel").click(function(){
	    $("#maskWindow").removeClass("none");
	    $("#cancelWindow").removeClass("none");
	});
	//    填写理由
	$("#reason").change(function(){
	    var value = $(this).val();
	    if(value=="4"){
	        $("#reasonOther").removeClass("none");
	    }else{
	        $("#reasonOther").addClass("none");
	    }
	});
	
	//背书
	$("#endorseBtn").click(function(){
	    $("#maskWindow").removeClass("none");
	    $("#endorseWindow").removeClass("none");
	});
	
	//去评价打开评论窗口
	$("#evaluateBtn").click(function(){
		$("#title").html("评论企业");
		var name = $("#name").html();
		var company = $("#company").html();
		var phone = $("#phone").html();
		var type1 = $(".type1").val();
		
		$("#comment_company").html(company);
		$("#comment_name").html(name);
		$("#comment_phone").html(phone);
		if(type1 == 1){
			$("#comment_type").html("银纸");
		}else{
			$("#comment_type").html("银电");
		}
	    $("#maskWindow").removeClass("none");
	    $("#evaluateWindow").removeClass("none");
	});
	
	 //评价星级
    $(".evaluateRadio").click(function(){
        var o = $(this);
        o.children().attr('checked', 'checked');
        with(o){
            removeClass("star2").addClass("star1");
            nextAll().removeClass("star1").addClass("star2");
            prevAll().removeClass("star2").addClass("star1");
        }
    });
	 
	 //查看票面
    $(".viewPic").toggle(
    	function (){
    		$(".showPic").show();
    	},function (){
    		$(".showPic").hide();
    	}
 	);
	 
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
	* 去支付
	*/
	$("#pay").click(function(){
    	var discId = $("#discId").val();
    	var map = new Map();
		map.put("discId",discId );//类型
		map.put("ym",'yp');
		map.put("_PAGE", "/discountrecord/discount_yp2");//必传
		_OPENPAGE_FORM(map);
	});
	
	/**
	* 上传背书
	*/
	$("#endorse").click(function(){
		var id = $("#discId").val();
		var orderflag = $("#orderflag").val();
		var type1 = $(".type1").val();
		var endorsePicPath = $("#endorse_pic_path").val();
		if(type1 == 1){
			if($.trim(endorsePicPath).length == 0 || endorsePicPath == ""){
				alert("请上传背书");
				return ;
			}
		}
		var url = '${bootAppPath}/discountrecord/update/endorsetime';
		var params = {id:id,orderflag:orderflag,endorsePicPath:endorsePicPath};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				alert("背书上传成功");
				location.href = "${basePath}/hall/disc/index";
			}else{
				alert(data.data.msg);
			}
		}
	});
	
	/**
	* 确认收到款项
	*/
	$("#gathering").click(function(){
		var id = $("#discId").val();
		var orderflag = $("#orderflag").val();
		
		var url = '${bootAppPath}/discountrecord/update/confirmaccounttime';
		var params = {id:id,orderflag:orderflag,memberId:memberId};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				alert("已收到票款");
				location.href = "${basePath }/discountorder/discount?ym=yp";
			}else{
				alert(data.data.msg);
			}
		}
	});
	
	/**
	* 发表评论，机构保存对企业的评论
	*/
	$("#comment").click(function(){
		var service = $("input[name='service']:checked").val();
		var speed = $("input[name='efficiency']:checked").val();
		var price = $("input[name='price']:checked").val();
		var dcrdId = $("#discId").val();
		var dtboId = $("#distId").val();
		var content = $("#content").val();
		if(service == null || service == ''){
			alert("请在评论后在提交。");
			return;
		}
		if(speed == null || speed == ''){
			alert("请在评论后在提交。");
			return;
		}
		if(price == null || price == ''){
			alert("请在评论后在提交。");
			return;
		}

		var url = '${bootAppPath}/comments/save';
		var params = {service:service,speed:speed,price:price,dcrdId:dcrdId,dtboId:dtboId,role:0,type:0,operatorId:memberId,content:content};
		var data = bootPost(url,params);

		if(data != null){
			if(data.data.response == 'success'){
				$("#maskWindow").addClass("none");
				$("#evaluateWindow").addClass("none");
				alert("评论成功");
				location.href = "${basePath }/discountorder/discount?ym=yp";
			}else{
				alert(data.data.msg);
			}
		}
	});
	
	/**
	* 企业取消订单的操作
	*/
	$("#confirm_reason").click(function(){
		var orderflag = $("#orderflag").val();
		var reason = $("#reason").val();
		var reasonOther = $("#reasonOther").val();
		var id = $("#discId").val();
		var type1 = $(".type1").val();
		var cib_id = $("#cib_id").val();

		if(reason == 4){
			if(!$("#reasonOther").validationEngine("validate")){
				$("#reasonOther").focus();
				return ;
			 }
		}
		
		var url;
		var params = {cancel:reason,cancelCause:reasonOther};
		
		if(orderflag == 1){
			url = '${bootAppPath}/discountrecord/cancel/unconfirm';
			params.id = id;
			params.orderflag = orderflag;
		}else if(orderflag == 2 || orderflag == 7 || orderflag == 4){
			url = '${bootAppPath}/cibcancel/save';
			params.orderType = 'DISCOUNTRECORD';
			params.type = 0;
			params.memberId = memberId;
			params.orderId = id;
			if(cib_id != null){
				params.id = cib_id;
			}
		}

		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				alert("取消成功");
				location.href = "${basePath}/hall/disc/index";
			}else{
				alert(data.data.msg);
			}
		}
	});
	
	/**
	* 下载合同
	*/
	$(".download").click(function(){
		var dist_bind_id = $("#dist_bind_id").val();
		if(dist_bind_id!=null){
			var discId = $("#discId").val();
			var url = '${bootAppPath}/order/sceneevi/url';
			var params = {orderId:discId,orderType:"DISCOUNTRECORD"};
			var data = bootPost(url,params);
			console.log(data);
			if(data != null){
				if(data.data.response == 'success'){
					window.location.href=data.data.data.url;
				}
			}
		}else{
			$(".contract").removeClass("none");
			$("#maskWindow").removeClass("none");
			$("#title").html("发送合同至您的邮箱");
		}
	});
	
	/**
	*发送电子合同
	*/
	function sendEmail(){
		var no = $(".ordernumber").html();
		var email = $("#email").val();
		if(!$("#email").validationEngine("validate")){
			$("#email").focus();
			setTimeout(function(){$("#email").validationEngine('hideAll');},2000);
		    return;
		} 
		var url = "${bootAppPath}/cib/econtract/email";
		var params = {no:no,email:email};
		var data = bootPost(url,params);
		$("#sendEmail").attr("disabled","disabled");//按钮禁用
		if(data != null){
			if(data.data.response == 'success'){
				alert("合同已经发送到您的邮箱");
				$("#sendEmail").removeAttr("disabled");//按钮启用
			}else{
				alert(data.data.msg);
				$("#sendEmail").removeAttr("disabled");//按钮启用
			}
		}
	};
	
	/**
	* 格式化评论
	*/
	function commentsToFixed(comments){
		if(comments != null && comments != "--"){
			return (comments*1).toFixed(2);
			
		}else{
			return comments;
		}
	};
</script>