[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css">
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/discount.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/form.css">
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webBase.css">
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/webUI.css">
<style>
	/*红包右侧锯齿边框*/
    .rbDashed{
        position:relative;
        width:30%;
        height:100%;
        display:inline-block;
        border-radius: 3px 0 0 3px;
    }
    .rbDashed:after{
        content: "";
        position: absolute;
        top: -20px;
        display: block;
        width:10px;
        height: 100%;
        margin-top: 20px;
        background-size: 20px 10px;
    }
    .rbDashed:after{
        right: -10px;
        background-color: #fc4d42;
        background-position: 100% 15%;
        background-image: linear-gradient(-45deg, #fff 25%, transparent 25%, transparent),
        linear-gradient(-135deg, #fff 25%, transparent 25%, transparent),
        linear-gradient(-45deg, transparent 75%, #fff 75%),
        linear-gradient(-135deg, transparent 75%, #fff 75%);
    }
</style>
[@main.header currentmenu='2' topindex='2'/]

<div class="mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态-->
    <div class="w654 bc f12 tc">
        <div class="fl">
            <img src="${image_url}/website/discount/state13.png" width="165" height="30">
            <p class="c3366cc mt10">确认订单</p>
        </div> 
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state21.png" width="165" height="30">
            <p class="ccccccc mt10">交易跟踪</p>
        </div>
        <div class="fl ml-2">
            <img src="${image_url}/website/discount/state31.png" width="165" height="30">
            <p class="ccccccc mt10">交易评价</p>
        </div>
    </div>
    <div class="cb"></div>
    <!--确认信息-->
    <div class="fb f16 lh180 cblack mt20 bbe0e0e0">
        确认订单信息
    </div>
    <div class="flex flex-direction-column w f14">
        <div class="flex-row f16 h40 lh42 cd43c33 none type1">
            请选择该票据当前背书户的银行账号。
        </div>
        <div class="flex-row flex-direction-row bae0e0e0 bcf9f9f9 pt20 pb20">
            <div class="flex-row flex-direction-column w530 pl20 pr20 pt20 lh30 Rright" id="frame">
            	<ul class="flex-row flex-direction-row flex-wrap flex-justify-space-between none selectJdCib" onclick="selectJdCibBank();">
                   <li class="flex-row w245 h60 mb16">
						<label for="bank" class="flex-row flex-direction-row flex-alignItems-center cp">
							<div class="flex-row flex-direction-column w220 bae0e0e0 bankvAcctAcctNo" id="b">
								<div class="flex-row bcf9f9f9 ml10" id="selectJdCibName">
									
								</div>
								<div class="flex-row flex-direction-row flex-justify-space-between bcwhite f14 c717583">
									<div class="ml10" id="selectJdCibNo"></div>
								</div>
							</div>
						</label>
					</li>
                </ul>
                <!-- add -->
                <div class="flex-row flex-justify-end w245 h60 mb16 none" id="selectJdWindow" onclick="selectJdCibBank();">
                    <label for="bank_add" class="flex-row flex-alignItems-center flex-justify-center w220 bae0e0e0 bcwhite cp">
                        <input type="button" id="bank_add" class="none">
                        <img src="https://img.utiexian.com/website/180903jingdong/order/add.png" alt="选择银行卡" width="30" height="30">
                        <p class="c979797">选择银行卡</p>
                    </label>
                </div>
                <!-- add_item -->
                <div class="flex-row flex-direction-column pt15 pb15 w450 mb16 bae0e0e0 bcwhite ml20 lh34 none" id="bindingBankWindow">
                    <!--申请绑卡-->
                    <div class="flex-row flex-direction-column none" id="applyBankWindow">
                        <input type="button" value="请选择开户行" class="bae0e0e0 ti10 h34 mt10 ml20 w270 bankBtn cp" readonly id="bankstr">
                        <input type="hidden" id="bankAcctBankNo"/>
						<input type="hidden" id="bankAcctCnapsCode"/>
						<input type="hidden" id="bankAcctBankBranch"/>
                        <input type="text" placeholder="请输入对公账户账号" maxlength="18" id="bankAcctAcctNo" class="validate[required] bae0e0e0 ti10 h34 mt10 ml20 w270">
                        <input type="text" placeholder="请输入账户名" id="bankAcctAcctName" maxlength="60" class="validate[required] bae0e0e0 ti10 h34 mt10 ml20 w270">
                        <p class="cd43c33 f12 lh18 mt10 pl10 pr10">*请添加常用账户，绑定对公银行账户不超过四个。每次贴现时，需要选择贴现票据的最后一手背书银行账号，该账号同时也是本次贴现交易的收款账号！</p>
                        <input type="button" value="申请绑卡" id="bindingBank" class="bad43c33 cwhite h34 w150 bc mt16 bcd43c33 br3">
                    </div>
                    <!--完成绑卡-->
                    <div class="flex-row flex-direction-column none" id="finishBankWindow">
                        <div class="ml20">
                            户名：<span class="bankAcctAcctNo"></span>
                        </div>
                        <div class="ml20">
                            账号：<span class="bankAcctAcctName"></span>
                        </div>
                        <div class="ml20">
                            <input type="number" placeholder="请填写该银行卡收到的小额款项" onblur="appraisalFee();" id="appraisalFee" class="bae0e0e0 ti10 h34 mt10 w270 mr10">元
                        </div>
                        <p class="cd43c33 f12 lh18 mt10 pl10 pr10">已提交开户申请，申请绑定的银行账号里会收到一笔小额款项，将收到的金额填写上，最后点击“完成绑卡”，即可完成绑卡。</p>
                        <!--小额款项未输入了不可点击（灰色）-->
                        <input type="button" value="完成绑卡" id="confirmAppraisal" class="bad43c33 bcd43c33 cwhite h34 w150 bc mt16 br3 none">
                        <input type="button" value="完成绑卡" id="confirmAppraisalNo" class="bae0e0e0 bcf9f9f9 c2d2d2d h34 w150 bc mt16 br3" disabled>
                    </div>
                </div>
                
                <!-- 180116 -->
                <div class="flex-row">
                    <div class="w120">保证金：</div>
                    <div><span id="deposit"></span>元<span class="unl c3366cc ml15 cp depositExplain">（交易完成后退回）</span></div>
                </div>
                <!-- 180206 -->
		        <div class="flex-row">
		            <div class="w120">撮合服务费：</div>
		            <div class="cd43c33"><span class="fee noVip none"></span><span style="text-decoration:line-through;" class="fee none isVip"></span>元<span class="c727272 ml15">（票管家收取）</span></div>
		            <div class="cd43c33 isVip none"><img src="https://img.utiexian.com/website/PJGJ/redPackets/yearVpIcon.png" alt=""> 会员免费</div>
		        </div>
		        <div class="flex-row none" id="needCoupon">
		            <div class="w120">红包：</div>
		            <div class="flex-row flex-direction-row">
		                <span class="cbfbfbf none noCoupon">无可用红包</span>
		                <div class="flex-row cd43c33 unl flex-alignItems-center none haveCoupon" id="openCoupon"><img src="https://img.utiexian.com/website/discount/redPacketsIcon.png" alt="" width="15" height="16" class="mr5"><span id="couponNum"></span></div>
		                <a class="unl c3366cc ml15 cp" href="${basePath }/bisicmessage/vipMember">（成为会员享优惠）</a>
		            </div>
		        </div>
		        <!-- 180206结束 -->
		        
                <div class="flex-row flex-justify-space-between mt6">
                    <div class="flex-row flex-direction-row">
                        <div class="w120">是否指定机构：</div>
                        <div >
                            <label for="jigou1" class="flex-row w46 h24 lh24 br3 jigou1">
                                <input type="radio" name="jigou" value="0" id="jigou1" class="none">
                                <div class="w tc">是</div>
                            </label>
                            <label for="jigou2" class="flex-row w46 h24 lh24 br3 cwhite bce84c3d mr10 jigou2" checked>
                                <input type="radio" name="jigou" value="1" id="jigou2" class="none">
                                <div class="w tc">否</div>
                            </label>
                        </div>
                    </div>
                    <!-- 搜索 -->
                    <div class="flex-row flex-alignItems-center br3 bcwhite w100 bae0e0e0 w250 pl10 none choseJGBtn">
                        <input type="text" id="company" maxlength="25" class="h30 br3 w210 b0 mr5 oln" placeholder="请输入机构名称进行搜索">
                        <img src="https://img.utiexian.com/website/discount/search.png" alt="" width="24" onclick="loadDate()" height="24" class="cp">
                    </div>
                </div>
            </div>
            <div class="flex-row flex-direction-row pl30 picpath">
                <div class="c717583">票面：</div>
                <img src="" alt="" id="picpath" width="410" height="170" class="ml35">
            </div>
        </div>
        <!-- 搜索结果列表-可进行选择 -->
        <!-- 未搜索到 -->
        <div class="flex bcwhite h300 flex-justify-center flex-alignItems-center bbe0e0e0 ble0e0e0 bre0e0e0 nocompany none">
            <div class="flex-row">
                <img src="https://img.utiexian.com/website/discount/noMechanism.png" alt="" width="206" height="173">
            </div>
        </div>
        <!-- 有搜索到 -->
        <div class="flex flex-direction-column company ble0e0e0 bre0e0e0 none" id="content">
            
        </div>
        <div class="flex-row flex-direction-row-reverse none pager">
			<div id="pager"></div>
		</div>
        <!-- 选中的结果 -->
        <div class="flex-row flex-direction-row ble0e0e0 bre0e0e0 bbe0e0e0 pt20 pb20 none selectOrg">
            <div class="flex-row flex-justify-space-between w530 pl20 pr20">
                <div class="c717583">机构名称：<span class="cblack" id="selectcompany"></span></div>
                <div class="c717583">联系人：<span class="cblack" id="name"></span></div>
            </div>
            <div class="flex-row flex-direction-row pl50">
                <div class="c717583">评分：</div>
                <div class="flex-row flex-justify-space-around w450">
                    <div>价格真实：<span id="price"></span></div>
                    <div>服务态度：<span id="service"></span></div>
                    <div>确认效率：<span id="speed"></span></div>
                </div>
            </div>
        </div>
    </div>
    <div class="flex-row flex-justify-space-between w h50 bae0e0e0 bcf9f9f9 lh50 mt20">
    	<div class="flex-row pl20">
            <div class="mr10">钱包余额：</div>
            <div class="cd43c33"><span id="money"></span>元</div>
        </div>
    	<div class="flex-row">
            <div class="mr10">实付：</div>
            <div class="cd43c33 mr20"><span class="actualPay fb mr5 f18"></span>元</div>
            
            <input type="hidden" value="${discId}" id="discId">
	   		<input type="hidden" value="" id="selectOrgId">
	   		<input type="hidden" id="vAcctAcctNo" name="vAcctAcctNo">
	   		<input type="hidden" id="bindId" name="bindId">
	   		<input type="hidden" id="cibBankId" name="cibBankId">
	   		<input type="hidden" id="type1" name="type1">
	   		<input type="hidden" id="couponId" name="couponId">
	        <input type="button" id="confirm" value="确认订单" class=" f18 cwhite cp bcd43c33 w166 lh50 b0"/>
        </div>
        
    </div>
    <div class="cb"></div>
</div>

<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div id="title"></div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
                <!-- 不使用红包 -->
                <div class="closeBtn cp cd43c33 none">不使用红包</div>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
            	<!--银行列表区域-->
                <div class="flex-row flex-direction-column w none" id="jdBankWindow">
                    <ul class="clearfix hmax200 f13 lh24 c7d7d7d box-sizing pl40 pr20 oya myScrollbar bankChoseDiv" id="content1">
                        
                    </ul>

                    <div class="flex-row flex-align-self-center lh35 mt20">
                        <input type="button" value="绑定新卡" class="w140 h35 ba bae84c3d bcwhite ce84c3d br3 cp mr20" id="addBankBtn">
                        <input type="button" value="确认选择" class="w140 h35 ba bad43c33 bce84c3d cwhite br3 cp" id="confirmSelectBank">
                    </div>
                </div>
                <!-- 银行列表区域 end-->
                
                <!--新增银行卡-->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150 none" id="addBankWindow">
                    <ul class="formGroup f14 c2d2d2d lh20">
                        <li class="formItem">
                            <label for="" class="w120">户名：</label>
                            <input type="text" class="w360 blicCompanyName" readonly value="" id="blicCompanyName">
                        </li>
                        <li class="formItem">
                            <label for="" class="w120">开户行：</label>
                            <select class="w320 select320" id="selectBank" oninput="selectBank(this);">
                                
                            </select>
                        </li>
                        <li class="formItem pr">
                            <label for="" class="w120">联行号：</label>
                            <input type="text" class="w360 bae0e0e0 br3 cp lianhanghaoBtn" readonly placeholder="请选择联行号" id="lianhanghao" onclick="lianProvice()">
                            <!--省市区-->
                            <div id="souchBank" class="bcwhite w360 pl10 pr10 box-shadow pf f14 lh30 ml120 mt35 none">
                                <div class="bbe0e0e0">请选择联行号</div>
                                <ul class="clearfix oh bbe0e0e0 ble0e0e0 mt10 tab-head">
                                    <li class="fl pl20 pr20 bte0e0e0 bre0e0e0 cp selected lian_provice" onclick="changeTab(1)">省级</li>
	            					<li class="fl pl20 pr20 bte0e0e0 bre0e0e0 cp none lian_city" onclick="changeTab(2)">市级</li>
	            					<li class="fl pl20 pr20 bte0e0e0 bre0e0e0 cp none lian_bank" onclick="changeTab(3)">分行名</li>
                                </ul>
                                <ul class="tab-contents h200">
                                    <li class="tab-content h oya myScrollbar" id="provice_content">
						                <ul class="tc f12 clearfix oh mt10" id="provice">
						                    
						                </ul>
						            </li>
						            <li class="tab-content h oya myScrollbar none" id="city_content">
						                <ul class="tc f12 clearfix oh mt10" id="city">
						                    
						                </ul>
						            </li>
						            <li class="tab-content h oya myScrollbar none" id="name_content">
						                <ul class="" id="jd_name">
						                    
						                </ul>
						            </li>
                                </ul>
                            </div>
                            <!--省市区 end-->
                        </li>
                        <li class="formItem">
                            <label for="" class="w120">账号：</label>
                            <div></div>
                            <input type="text" id="occBankAccount" class="w360" placeholder="请输入绑定的银行卡">
                        </li>

                        <li class="formItem">
                            <p class="ml120 ce84c3d">
                                *请添加常用账户，绑定对公银行账户不超过四个。每次贴现时，需要选择贴现票据的最后一手背书银行账号，该账号同时也是本次贴现交易的收款账号！</p>
                        </li>
                    </ul>
                    <div class="flex-row flex-align-self-center lh35 mt20">
                        <input id="newBankBtn" type="button" value="申请绑卡" onclick="jdSave();" class="w140 h35 ba bad43c33 bce84c3d cwhite br3 cp">
                    </div>
                </div>
                <!-- 新增银行卡 end-->
                
                <!--京东新银行卡-->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150 none" id="newBankWindow">
                    <ul class="formGroup f14 c2d2d2d lh20">
                        <li class="formItem">
                            <label for="" class="w120">户名：</label>
                            <input type="text" class="w360 blicCompanyName" readonly value="">
                        </li>
                        <li class="formItem">
                            <label for="" class="w120">账号：</label>
                            <input type="text" class="w360 occBankAccount" readonly value="">
                        </li>
                        <li class="formItem">
                            <input type="number" oninput="money();" class="w360" id="jd_money" placeholder="请填写该银行开收到的小额款项">
                        </li>
                        <li class="formItem">
                            <p class="ce84c3d">已提交开户申请，申请绑定的银行账号里会收到一笔小额款项，将收到的金额填写上，最后点击“完成绑卡”，即可完成绑卡。</p>
                        </li>

                    </ul>
                    <div class="flex-row flex-align-self-center lh35 mt20">
                        <input type="button" value="完成绑卡" onclick="saveMoney();" id="jd_btn" class="w140 h35 ba bad43c33 bce84c3d cwhite br3 cp">
                    </div>
                </div>
                <!-- 新银行卡 end-->
            
                <!--选择机构-->
                <div class="flex-row flex-direction-column w lh30 none" id="selectionWindow">
                    <div class="flex-row pl105 mt40">
                        公司名称：<span id="confirm_company"></span>
                    <span id="selectId" class="none"></span> 
                    </div>
                    <div class="flex-row pl105">
                        您是否指定该机构作为唯一接单机构？
                    </div>
                    <!--按钮操作-->
                    <div class="flex-row flex-direction-row-reverse lh30 mt30">
                        <input type="button" value="确定" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 discountBtn">
                        <input type="button" value="取消" class="w90 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn">
                    </div>
                </div>
                
                <!--开户行名称-->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150 none" id="bankWindow">
                    <div class="flex-row flex-direction-row bae0e0e0 lh34 mt30 w385">
                        <input type="text" id="bank_branch" maxlength="25" class="flex-col-auto b0 ti8" placeholder="请输入分支行号或分支行关键词">
                        <input type="button" value="搜索" onclick="loadBank();" class="w80 bcd43c33 cwhite bad43c33 h34">
                    </div>
                    <div id="promptStr" class="none">暂无查询结果,您可以尝试更改查询条件搜索。</div>
                    <ul id="banks" class="flex-row flex-direction-column lh30 w bte0e0e0 ble0e0e0 bre0e0e0 mt30">
                    
                    </ul>
                    <div class="flex-row flex-direction-row w385 mt20 flex-justify-end">
                        <button class="prevPage none" onclick="prevPage();">上一页</button>
                        <button class="nextPage none" onclick="nextPage();">下一页</button>
                    </div>
                </div>
	            
	            <!-- 保证金说明 -->
	            <div class="mt20 lh30 ti32 none" id="depositExplain">
		            <p>为了保证交易双方的安全，票据管家将对贴现的双方收取履约保证金。若交易顺利完成，保证金返还双方账户，若交易过程中出现违约行为，保证金将作为补偿，退还至未违约方账户。</p>
		            <p>当您进行精准报价时收取的保证金，若出票方未选择您作为交易对象，该保证金在票方选择其他资方后马上返还。</p>
	            </div>
	            <!--使用红包-->
		        <div class="flex-row flex-direction-row flex-justify-space-between flex-wrap h300 none" style="overflow: auto" id="couponWindow"></div>
	            <!--支付，充值-->
			 	<div class="flex-row flex-direction-column w none recharge">
					<div class="flex-row flex-direction-row flex-justify-space-between bc w250 mt20">
	             		<div>实付：<span class="actualPay"></span>元</div>
	             	</div>
	             	<div class="flex-row flex-direction-row flex-justify-space-between bc w250 mt20">
	             		<div>钱包余额：<span class="money"></span>元</div>
	             	</div>
                    <div class="flex-row flex-justify-center lh30 mt30">
                        <input type="button" value="余额不足，去充值" id="recharge" class="w150 h35 ba bad43c33 bcd43c33 cwhite br3 none">
                        <input type="button" value="确认支付" id="discountSuccess" class="w150 h35 ba bad43c33 bcd43c33 cwhite br3 none">
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
	                <input type="button" value="签署同意" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20" onclick="discountSuccess()">
	                <input type="button" value="取消" class="w90 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn">
	            </div>
            </div>
            <div class="pl20 pr20" style="height:85%;">
                <iframe style="width:100%;height:100%;" src="" id="iframe"></iframe>
            </div>
         </div>
    </div>
</div>

<input type="hidden" id="lian_provice">
<input type="hidden" id="lian_provice_name">
<input type="hidden" id="lian_city">
<input type="hidden" id="lian_city_name">
<input type="hidden" id="lian_bank">

<input type="hidden" id="occBankName">
<input type="hidden" id="occBankCode">
<input type="hidden" id="cnapBankCode">
<input type="hidden" id="occBankChildName">
<input type="hidden" id="occBankChildCode">
<input type="hidden" id="payOrderId">
[@main.right /]
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=RZNGEivCrVHp06sXAM6rxlquUSOLB3xx&s=1"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="COMPANY">
{{#each list}}
	<div class="w flex-row flex-direction-column">
		<div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 f14 tc bbe0e0e0 c717583">
			<div class="w300">机构名称</div>
			<div class="w250">联系人</div>
			<div class="w600">评分</div>
		</div>
		<label class="pr selectionBtn" for="choseJG{{id}}" >
			<div class="flex-row flex-direction-row lh40 pt10 pb8 tc bbe0e0e0 fb">
				<div class="flex-row flex-direction-row">
					<div class="w300 Rright"><span id="company{{id}}">{{company}}</span></div>
					<div class="w250 Rright"><span id="name{{id}}">{{name}}（{{mobile}}）</span></div>
				</div>
				<div class="flex-row flex-direction-row w600">
					<div class="flex-col-4">价格真实：<span id="price{{id}}">{{toNum price}}</span></div>
					<div class="flex-col-4">服务态度：<span id="service{{id}}">{{toNum service}}</span></div>
					<div class="flex-col-4">确认效率：<span id="speed{{id}}">{{toNum speed}}</span></div>
				</div>
			</div>
			<div class="pa bottom right w25 h25">
				<input id="org_id{{id}}" type="hidden" value="{{org_id}}">
				<input type="radio" name="choseJG" id="choseJG{{id}}" data-id="{{id}}" class="choseJG">
			</div>
		</label>
	</div>
{{/each}}
</script>
<script type="text/x-handlebars-template" id="COUPON">
{{#each coupon}}
	<div class="flex-row flex-direction-row w240 h80 bae0e0e0 cp cpH mt25 br3" onclick="selectCoupon({{id}},{{money}});">
		<div class="w90 flex-row h flex-direction-column tc bcfc4d42 rbDashed">
			<div class="cwhite f20 mt3">¥<span class="f30 ml3">{{money}}</span></div>
			<div class="flex-row flex-justify-center f12 bcwhite cfc4d42 br3 lh22 mt6 w70">满<span>20</span>元可用</div>
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
	<li class="fl w200 h86 bcf9f9f9 bae0e0e0 box-sizing pt6 pb6 pl10 pr10 br5 mt10 mr30 box-shadow" data-no="{{accountNo}}" data-v="{{vAcctAcctNo}}" data-b="{{bindId}}" data-name="{{bankName}}" onclick="selectBankClass(this);">
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
	 * 展示评价
	 */
	Handlebars.registerHelper('toNum', function(num, options) {
		if(num != null && num != "" && num != "--"){
			return (num*1).toFixed(2);;
		}else{
			return "--";
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
	        return "贴现红包";
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
	
	var memberId = ${member.id};
	$(document).ready(function() {
		getDiscoutnInfo();
		loadVip();//判断是否是会员
		loadBank();
		loadJdCard();
		loadJdBank();
	});
	var ym = '${ym}';
	
	/**
	*  加载京东和兴业的账户
	*/
	function loadJdCard(){
		var	url = '${bootAppPath}/jdjr/cib/account/card';
		var params = {memberId:memberId,type:0};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				var source = $("#BANK").html();
	            var template = Handlebars.compile(source);
	            var html = template(data.data);
	            $("#content1").html(html);
			}else{
				alert(data.data.msg);
			}
		}
	};
	
	/*选择银行卡*/
    function selectBankClass(obj) {
		var no = $(obj).attr("data-no");
		var name = $(obj).attr("data-name");
		var vAcctAcctNo = $(obj).attr("data-v");
		var bindId = $(obj).attr("data-b");
		
		$("#selectJdCibNo").html(no);
		$("#selectJdCibName").html(name);
		$("#vAcctAcctNo").val(vAcctAcctNo);
		$("#bindId").val(bindId);
		
	    $(obj).parents("ul.bankChoseDiv").children("li").removeClass('bad43c33 bce84c3d cwhite').addClass('bcf9f9f9 bae0e0e0');
	    $(obj).removeClass('bcf9f9f9 bae0e0e0').addClass('bad43c33 bce84c3d cwhite');
    }
    
    /**
    * 确定选择银行卡
    */
    $("#confirmSelectBank").click(function(){
    	$("#jdBankWindow").addClass("none");
    	$("#maskWindow").addClass("none");
    	$("#selectJdWindow").addClass("none");
    	$(".selectJdCib").removeClass("none");
    });
    
    /**
    * 绑定新的银行卡
    */
    $("#addBankBtn").click(function(){
    	var url = '${bootAppPath}/jdjr/cib/account';
    	if(memberId == null || memberId == ""){
    		if(path != null && path != ""){
    			location.href = "${basePath}/login";
    		}
    	}
    	var params = {memberId:memberId,type:0};
    	var res = bootPost(url,params);
    	if(res != null){
    		var data = res.data;
    		if (data.response == 'success') {
    			var jdjr = data.data.jdjr;
    			var cib = data.data.cib;
    			$("#title").html("添加银行卡");
   				if(jdjr!=null&&jdjr.status==2&&jdjr.check_state=="PASS"){
   					$(".blicCompanyName").val(jdjr.blic_company_name);
   					$("#jdBankWindow").addClass("none");
   					$("#addBankWindow").removeClass("none");
   				}else if(cib!=null&&cib.status==2&&cib.cib_check_state=="PASS"){
   					$("#jdBankWindow").addClass("none");
   					$("#maskWindow").addClass("none");
   					loadBankCount();
   				}else{
   					$("#jdBankWindow").addClass("none");
   					$("#addBankWindow").removeClass("none");
   				}
    		}
    	}
    });
    
    /**
    * 选择绑定的银行卡
	*/    		
    function selectJdCibBank(){
    	$("#title").html("选择银行卡");
    	$("#jdBankWindow").removeClass("none");
		$("#maskWindow").removeClass("none");
    };
    
    /**
	* 加载银行
	*/
	function loadJdBank(){
		var url = '${bootAppPath}/jdjrcard/queryBankList';
 		var params = {};
 		var data = bootPost(url,params);
 		if(data != null){
 			if(data.data.response == 'success'){
 				if(data.data.data!=null){
 					var html = "<option value='-1'>请选择开户行</option>";
					for(var i =0;i<data.data.data.bankListResult.length;i++){
 						html += "<option data-Id='"+data.data.data.bankListResult[i].cnapBankCode+"' value='"+data.data.data.bankListResult[i].bankCode+"'>"+data.data.data.bankListResult[i].bankName+"</option>";
 					}
 					$("#selectBank").html(html);
 				}
 			}else{
 				alert(data.data.msg);
 			}
 		}
     };
     
	/**
	* 用户加载联行号省份
	*/
	function lianProvice(){
		var bankCode = $("#selectBank").val();
		if(bankCode == -1){
			alert("请先选择开户行！")
			return;
		}
		$("#souchBank").removeClass("none");
		$(".lian_provice").html("省份");
		$(".lian_provice").removeClass("none");
		$(".lian_city").addClass("none");
		$(".lian_bank").addClass("none");
		
		$("#provice_content").removeClass("none");
		$("#city_content").addClass("none");
		$("#name_content").addClass("none");
		
		var url = '${bootAppPath}/jdjrcard/queryProvincesAndCitys';
 		var params = {};
 		var data = bootPost(url,params);
 		if(data != null){
 			if(data.data.response == 'success'){
 				if(data.data.data!=null){
 					var html = "";
 					for(var i =0;i<data.data.data.provinces.length;i++){
 						html += "<li class='fl wmin90 cp' onclick=provice('"+data.data.data.provinces[i].provinceId+"','"+data.data.data.provinces[i].provinceName+"')>"+data.data.data.provinces[i].provinceName+"</li>";
 					}
 					$("#provice").html(html);
 				}
 			}else{
 				alert(data.data.msg);
 			}
 		}
	}
	
	/**
	*用户选择省份
	*/
	function provice(code,name){
		$("#lian_provice").val(code);
		$("#lian_provice_name").val(name);
		$(".lian_provice").html(name);
		$(".lian_city").html("城市");
		lianCity(code);
	};
     
	/**
	 * 用户加载联行号城市
	 */
	function lianCity(code){
		var url = '${bootAppPath}/jdjrcard/queryProvincesAndCitys';
		var params = {provinceId:code};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$(".lian_city").removeClass("none");
				$("#city_content").removeClass("none");
				$("#provice_content").addClass("none");
				console.log(data);
				if(data.data.data!=null){
					var html = "";
					for(var i =0;i<data.data.data.citys.length;i++){
						html += "<li class='fl wmin90 cp' onclick=city('"+data.data.data.citys[i].cityId+"','"+data.data.data.citys[i].cityName+"')>"+data.data.data.citys[i].cityName+"</li>";
					}
					$("#city").html(html);
				}
			}else{
				alert(data.data.msg);
			}
		}
	}
	
	/**
	*用户选择城市
	*/
	function city(code,name){
		$("#lian_city").val(code);
		$("#lian_city_name").val(name);
		$(".lian_city").html(name);
		lianBank(code);
	};
	
	/**
	 * 用户加载联行号支行
	 */
	 function lianBank(code){
		var url = '${bootAppPath}/jdjrcard/queryBankCnapsList';
		var cnapBankCode = $("#cnapBankCode").val();
		var params = {"cnapBankCode":cnapBankCode,"cityId":code};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$(".lian_bank").removeClass("none");
				$("#name_content").removeClass("none");
				$("#city_content").addClass("none");
				console.log(data);
				if(data.data.data!=null){
					var html = "";
					for(var i =0;i<data.data.data.bankChildInfoList.length;i++){
						html += "<li onclick=selectBranch('"+data.data.data.bankChildInfoList[i].bankCnaps+"','"+data.data.data.bankChildInfoList[i].bankChildName+"')>"+data.data.data.bankChildInfoList[i].bankChildName+"</li>";
					}
					$("#jd_name").html(html);
				}
			}else{
				alert(data.data.msg);
			}
		}
	}
     
    function changeTab(tab) {
        if(tab==1){
        	$(".lian_provice").removeClass("none");
        	$(".lian_city").addClass("none");
        	$(".lian_bank").addClass("none");
        	
        	$("#provice_content").removeClass("none");
        	$("#city_content").addClass("none");
        	$("#name_content").addClass("none");
        }else if(tab==2){
        	$(".lian_provice").removeClass("none");
        	$(".lian_city").removeClass("none");
        	$(".lian_bank").addClass("none");
        	
        	$("#provice_content").addClass("none");
        	$("#city_content").removeClass("none");
        	$("#name_content").addClass("none");
        }else{
        	
        }
    }
    
    /**
    * 选择支行
    */
    function selectBranch(code,name){
    	$("#souchBank").addClass("none")
    	$("#lianhanghao").val(code+":"+name)
    	$("#occBankChildName").val(name);
    	$("#occBankChildCode").val(code);
    }
    
    /**
     * 选择银行
     */
	function selectBank(obj){
		var options=$("#selectBank option:selected"); //获取选中的项
		var cnapBankCode = $(options).attr("data-id");
    	$("#occBankName").val(options.text());
    	$("#occBankCode").val(options.val());
    	$("#cnapBankCode").val(cnapBankCode);
	}
    
	/**
    * 保存开户
    */
    function jdSave(){
   	    var bankAccountName = $("#blicCompanyName").val();//账号名称
		var bankAccountNo = $("#occBankAccount").val();//银行账号
		var bankName = $("#occBankName").val();//开户行名称
		var bankCode = $("#occBankCode").val();//银行编码
		var bankChildName = $("#occBankChildName").val();//支行银行全称
		var bankCnaps = $("#occBankChildCode").val();//开户行联行号
		var provinceId = $("#lian_provice").val();
		var provinceName = $("#lian_provice_name").val();
		var cityId = $("#lian_city").val();
		var cityName = $("#lian_city_name").val();
		
		var url = '${bootAppPath}/jdjrcard/save';
		var params = {memberId:memberId,bankAccountName:bankAccountName,bankAccountNo:bankAccountNo,bankName:bankName,
				bankCode:bankCode,bankChildName:bankChildName,bankCnaps:bankCnaps,provinceId:provinceId,provinceName:provinceName,
				cityId:cityId,cityName:cityName};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$(".occBankAccount").val(bankAccountNo);
				$("#payOrderId").val(data.data.data.payOrderId);
				$("#addBankWindow").addClass("none");
				$("#newBankWindow").removeClass("none");
			}else{
				alert(data.data.msg);
			}
		}
    }
	
    /**
     * 用户输入金额
     */
    function money(){
    	var money = $("#jd_money").val();
    	if(money.length>0){
    		$("#jd_btn").attr("disabled",false);
    	}else{
    		$("#jd_btn").attr("disabled",true);
    	}
    }
    
    /**
     * 京东保存小额鉴定
     */
    function saveMoney(){
    	var amount = $("#jd_money").val();
    	var payOrderId = $("#payOrderId").val();
    	var url = '${bootAppPath}/jdjrcard/verifyAgencyDefpay';
    	var params = {payOrderId:payOrderId,amount:(amount*100),memberId:memberId};
    	var data = bootPost(url,params);
    	if(data != null){
    		if(data.data.response == 'success'){
    			$("#maskWindow").addClass("none");
				$("#newBankWindow").addClass("none");
    		}else{
    			alert(data.data.msg);
    		}
    	}
    }

	
	/**
	 * 是否进行机构搜索（是否指定机构）
	 * 0 ：搜索 1：不搜索
	 */
	$("input[name='jigou']").click(function(){
		if($(this).val() == 0){
			$(".choseJGBtn").removeClass("none");
			$(".jigou1").addClass("cwhite bce84c3d mr10");
			$(".jigou2").removeClass("cwhite bce84c3d mr10");
		}else{
			$(".choseJGBtn").addClass("none");
			$(".jigou1").removeClass("cwhite bce84c3d mr10");
			$(".jigou2").addClass("cwhite bce84c3d mr10");
			$("#selectOrgId").val("");
			$("#selectcompany").html("");
			$("#name").html("");
			$("#price").html("");
			$("#service").html("");
			$("#speed").html("");
			$(".selectOrg").addClass("none");
			
			$("#pager").html("");
			$(".pager").addClass("none");
			$(".company").addClass("none");
		}
	});
	
	/**
	* 获取机构内容,加载数据
	*/
	function loadDate(){
		var company = $("#company").val();
		var data = {company:company,removeMemberId:memberId};
		$(".selectOrg").addClass("none");
		$("#content").html("");
		$("#pager").html("");
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/orginfo/search",
	        pageIndex:pageIndex,
	        pageSize:5,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	if(data.data.response == "success"){
	        		if(data.data.data!=null){
	        			if(data.data.data.list.length >0){
			        		$(".nocompany").addClass("none");
			        		$("#content").removeClass("none");
			        		$(".pager").removeClass("none");
			        		var source = $("#COMPANY").html();
				            var template = Handlebars.compile(source);
				            var html = template(data.data.data);
				            $("#content").html(html);
				            
				            $(".choseJG").live("click",selectionBtn);
			        	}else{
			        		$(".nocompany").removeClass("none");
			        		$("#content").addClass("none");
			        		$(".pager").addClass("none");
			        	}
	        		}
	        	}else{
	        		alert(data.data.msg);
	        	}
	        },complete: function(){
	        },error:function(data){
				alert("出现异常");
	        }
	    });
	};
	
	//选择机构
	function selectionBtn(){
		var id = $(this).attr("data-id");
		$("#selectId").html(id);
		var company = $("#company"+id).html();
		$("#confirm_company").html(company);
		
		$("#maskWindow").removeClass("none");
	    $("#selectionWindow").removeClass("none");
	    $(".selectionBtn").removeClass("bad43c33");
	    $("#title").html("选择机构");
	    $(this).parents("label").addClass("bad43c33");
	}
	
	//确定选择机构
	$(".discountBtn").click(function(){
		var id = $("#selectId").html();
		var company = $("#company"+id).html();
		var name = $("#name"+id).html();
		var price = $("#price"+id).html();
		var service = $("#service"+id).html();
		var speed = $("#speed"+id).html();
		var orgId = $("#org_id"+id).val();
		
		$("#maskWindow").addClass("none");
	    $("#selectionWindow").addClass("none"); //选择机构
	    
	    $("#content").html("");
		$("#pager").html("");
		$(".nocompany").addClass("none");
		$("#content").addClass("none");
		$(".pager").addClass("none");
		
		$(".selectOrg").removeClass("none");
		
		$("#selectOrgId").val(orgId);
		$("#selectcompany").html(company);
		$("#name").html(name);
		$("#price").html(price);
		$("#service").html(service);
		$("#speed").html(speed);
	});
	
	//关闭按钮
	$("#closeBtn , .closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#selectionWindow").addClass("none"); //选择机构
	    $("#couponWindow").addClass("none");//红包
	    $("#depositExplain").addClass("none");//红包
		$(".recharge").addClass("none");
		$("#contractWindow").addClass("none");//签署合同
		$("#jdBankWindow").addClass("none");//选卡
		$("#addBankWindow").addClass("none");//添加卡
		$("#newBankWindow").addClass("none");
	});
	
	/**
	* 确认订单的弹出框
	*/
	$("#confirm").click(function(){
		var money = $("#money").html();
		var deposit = $("#deposit").html();
		
		if(parseInt(money) < parseInt(deposit)){
			$("#recharge").removeClass("none");
		}else{
			$("#discountSuccess").removeClass("none");
		}
		
		$("#title").html("确认订单");
		$("#maskWindow").removeClass("none");
		$(".recharge").removeClass("none");
	});
	
	/**
	* 充值钱包余额
	*/
	$("#recharge").click(function(){
		location.href = "${basePath }/bisicmessage/deposit";
	});
	
	/**
	* 确认订单
	*/
	$("#discountSuccess").click(function(){
		var type1 = $("#type1").val();
		if(type1 == 2){
			var vAcctAcctNo = $("#vAcctAcctNo").val();
			var bindId = $("#bindId").val();
			if((vAcctAcctNo == null || vAcctAcctNo == "")&&(bindId == null || bindId == "")){
				alert("请选择一家收款账户");
				return ;
			}
		}
			
		var jigou = $("input:radio[name='jigou']:checked").val();
		var selectOrgId = $("#selectOrgId").val();
		if(jigou == 0){
			if($.trim(selectOrgId).length == 0){
				alert("请选择要指定的机构");
				return ;
			}
		}
		var buyout = '${buyout}';
		if(buyout != null && buyout != ""){//一口价，需要提前签署合同
			var discId = $("#discId").val();
			var orderType = null;
			if(ym == 'yp'){
				orderType = 'DISCOUNTRECORD';
			}else if(ym == 'sp'){
				orderType = 'DISCOUNTRECORDSP';
			}
			if(bindId!= null && bindId != ""){//京东协议
				$("#iframe").attr("src","${basePath}/jd/agreements/contract?discId="+discId+"&orderType="+orderType+"&buyout=true&bindId="+bindId);
			}else{
				$("#iframe").attr("src","${basePath}/agreements/contract?discId="+discId+"&orderType="+orderType+"&buyout=true");
			}
			
			$("#contractWindow").removeClass("none");
		}else{
			discountSuccess();
		}
	});
	
	/**
	*
	*/
	function discountSuccess(){
		var selectOrgId = $("#selectOrgId").val();
		
		var discId = $("#discId").val();
		var type1 = $("#type1").val();
		
		var url = null;
		if(ym == 'yp'){
			url = '${bootAppPath}/discountrecord/update';
		}else if(ym == 'sp'){
			url = '${bootAppPath}/discountrecordsp/update';
		}
		
		var params = {id:discId,selectOrgId:selectOrgId,memberId:memberId};
		
		var couponId = $("#couponId").val();
		if(couponId != null && couponId != ""){//红包
			params.couponId = couponId;
		}
		if(type1 == 2){
			var vAcctAcctNo = $("#vAcctAcctNo").val();
			var bindId = $("#bindId").val();
			if((vAcctAcctNo == null || vAcctAcctNo == "")&&(bindId == null || bindId == "")){
				alert("请选择一家收款账户");
				return ;
			}else{
				params.vAcctAcctNo=vAcctAcctNo;
				params.bindId=bindId;
			}
		}

		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				location.href = "${basePath}/discountrecord/discountSuccess";
			}else{
				alert(data.data.msg);
			}
		}
	};
	
	/**
	* 加载数据
	*/
	function getDiscoutnInfo(){
		var discId = $("#discId").val();
		var url = null;
		
		if(ym == 'yp'){
			url = '${bootAppPath}/discountrecord/get';
		}else if(ym == 'sp'){
			url = '${bootAppPath}/discountrecordsp/get';
		}
		var params = {id:discId};
		var data = bootPost(url,params);
		if(data.data.response == 'success'){
			var data = data.data.data;
			var account = data.account;
			
			$("#money").html(account.money);
			$(".money").html(account.money);
			$("#deposit").html(data.deposit);

			if(account.money < data.deposit){
				$(".codeBtn").addClass("none");
			}else{
				$("#discountSuccess").removeAttr("disabled");//按钮启用
			}
			
			if(data.picpath != null && data.picpath != ''){
				$("#picpath").attr("src","${bootPic}"+data.picpath);
			}else{
				$(".picpath").addClass("none");
				$("#frame").removeClass("Rright");
			}
			$("#type1").val(data.type1);
			if(data.type1 == 2){
				$("#content1").removeClass("none");
				$(".type1").removeClass("none");
				$("#selectJdWindow").removeClass("none");
				loadJdCard();
			}
		}else{
			alert("获取数据失败");
		}
	};
	
	/**
	* 获取已经绑定的开户数量
	*/
	function loadBankCount(){
		var	url = '${bootAppPath}/cibbank/list';
		var params = {memberId:memberId,type:0};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				var count = data.data.data.length;
				if(count < 4){
					$("#bindingBankWindow").removeClass("none");
					$("#applyBankWindow").removeClass("none");
					$("#title").html("添加银行账户");
					$("#bank_add").attr({"disabled":"disabled"});
				}else{
					alert("绑卡数量已够，不能再绑！");
				}
			}else{
				alert(data.data.msg);
			}
		}
	};
	
    //鼠标事件
    $(".TXradio").mouseover(function(){
        $(".TXradio").addClass("bcd43c33");
    });
    $("p").mouseout(function(){
        $(".TXradio").removeClass("bcd43c33");
    });
    
    /**
    * 打开银行卡支行的弹窗
    */
    $(".bankBtn").click(function(){
    	$("#maskWindow").removeClass("none");
		$("#bankWindow").removeClass("none");
    });
    
    /**
    * 选定之后赋值
    */
    function choseBank(i){
    	$("#bankstr").val(banks[i].bank_branch+"("+banks[i].cnaps_code+")");
    	$("#bankAcctBankNo").val(banks[i].bank_no);
    	$("#bankAcctCnapsCode").val(banks[i].cnaps_code);
    	$("#bankAcctBankBranch").val(banks[i].bank_branch);
    	$("#maskWindow").addClass("none");
    	$("#alertWindow").addClass("none"); //温馨提示	
    };
    
	var pageIndex = 1; 
	var pageSize = 10;
	var bankList = [];
    /**
     * 加载查询数据
     */
    function loadBank(){
    	var bankBranch = $("#bank_branch").val();
    	var url = "${bootAppPath}/cib/query/back";
    	var params = {bank_branch:bankBranch,memberId:memberId};
    	var res = bootPost(url,params);
    	console.log(res);
    	pageIndex = 1;
    	if(res!=null){
    		var data = res.data;
    		bankList = data.data.banks;
    		var html = "";
    		if(typeof(data.data.return_msg)!="undefined"){
    			alert(data.data.return_msg+"，请联系客服");
    		}else{
    			if(typeof(data.data.banks)!="undefined"){
        			if(data.data.banks.length > 10){
        				$(".nextPage").removeClass("none");
        				for(var i=0;i<10;i++){
            				banks[i] = bankList[((pageIndex-1)*pageSize)+i];
            				html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
                            html += "<div class='flex-row w flex-justify-space-between'>";
                            html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
                            html += "<div>"+banks[i].bank_branch+"</div>";
                            html += "</div>";
                            html += "</li>";
            			}
        			}else{
        				for(var i=0;i<data.data.banks.length;i++){
            				banks[i] = bankList[((pageIndex-1)*pageSize)+i];
            				html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
                            html += "<div class='flex-row w flex-justify-space-between'>";
                            html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
                            html += "<div>"+banks[i].bank_branch+"</div>";
                            html += "</div>";
                            html += "</li>";
            			}
            			$(".nextPage").addClass("none");
        			}
        			
        			$(".prevPage").addClass("none");
        			$("#banks").html("");
            		$("#banks").append(html);
            		$("#promptStr").addClass("none");
        		}else{
        			$("#banks").html("");
        			$("#promptStr").removeClass("none");
        		}
    		}
    	}
    };
    
    /**
     * 上一页
     */
    function prevPage(){
    	if(pageIndex <= 2) $(".prevPage").addClass("none");
    	if((bankList.length/pageSize-1) < pageIndex)$(".nextPage").removeClass("none");
    	pageIndex = (pageIndex-1) < 1 ? pageIndex:pageIndex-1;
    	var html = "";
    	$("#banks").html("");
   		for(var i=0;i<10;i++){
       		banks[i] = bankList[((pageIndex-1)*pageSize)+i];
       		html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
               html += "<div class='flex-row w flex-justify-space-between'>";
               html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
               html += "<div>"+banks[i].bank_branch+"</div>";
               html += "</div>";
               html += "</li>";
       	}
    	$("#banks").append(html);
    };

    /**
    * 下一页
    */
    function nextPage(){
    	if(pageIndex ==1) $(".prevPage").removeClass("none");
    	if((bankList.length/pageSize) <= (pageIndex+1))$(".nextPage").addClass("none");
   
    	var html = "";
    	$("#banks").html("");
    	if((pageIndex+1) > (bankList.length/pageSize)){
    		for(var i=0;i<(bankList.length-pageSize*pageIndex);i++){
        		banks[i] = bankList[(pageIndex*pageSize)+i];
        		html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
                html += "<div class='flex-row w flex-justify-space-between'>";
                html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
                html += "<div>"+banks[i].bank_branch+"</div>";
                html += "</div>";
                html += "</li>";
        	}
    	}else{
    		for(var i=0;i<10;i++){
        		banks[i] = bankList[(pageIndex*pageSize)+i];
        		html += "<li class='bbe0e0e0 pl7 pr5' onclick='choseBank("+i+");'>";
                html += "<div class='flex-row w flex-justify-space-between'>";
                html += "<div class='w120'>"+banks[i].cnaps_code+"</div>";
                html += "<div>"+banks[i].bank_branch+"</div>";
                html += "</div>";
                html += "</li>";
        	}
    	}
    	$("#banks").append(html);
    	pageIndex = (pageIndex+1) > (bankList.length/pageSize) ? pageIndex:pageIndex+1;

    };
    
    /**
    * 提交申请绑卡
    */
    $("#bindingBank").click(function(){
    	if(!$("#bankAcctAcctNo").validationEngine("validate")){
			setTimeout(function(){$(".formError").hide();},2000);
			$("#bankAcctAcctNo").focus();
			return ;
		}
    	if(!$("#bankAcctAcctName").validationEngine("validate")){
    		setTimeout(function(){$(".formError").hide();},2000);
			$("#bankAcctAcctName").focus();
			return ;
		}
    	var bankAcctBankNo = $("#bankAcctBankNo").val();
    	var bankAcctCnapsCode = $("#bankAcctCnapsCode").val();
    	var bankAcctBankBranch = $("#bankAcctBankBranch").val();
    	var bankAcctAcctNo = $("#bankAcctAcctNo").val();
    	var bankAcctAcctName = $("#bankAcctAcctName").val();
    	if(bankAcctBankBranch == null || bankAcctBankBranch == ""){
    		alert("请选择一家支行");
    		return ;
    	}
    	
    	var	url = '${bootAppPath}/cibbank/save';
		var params = {memberId:memberId,type:0,bankAcctBankNo:bankAcctBankNo,bankAcctCnapsCode:bankAcctCnapsCode,bankAcctBankBranch:bankAcctBankBranch,
				bankAcctAcctNo:bankAcctAcctNo,bankAcctAcctName:bankAcctAcctName};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == "success"){
				if(data.data.data != null && data.data.data.error_msg != null){
					alert(data.data.data.error_msg);
				}else if(data.data.data != null && data.data.data.return_msg != null){
					alert(data.data.data.return_msg);
				}else{
					$("#finishBankWindow").removeClass("none");
					$("#applyBankWindow").addClass("none");
					$(".bankAcctAcctNo").html(bankAcctAcctNo);
					$(".bankAcctAcctName").html(bankAcctAcctName);
					$("#cibBankId").val(data.data.data.cibBankId);
				}
			}else{
				alert(data.data.msg);
			}
		}
    });
    
    /**
    *填写小额鉴定的处理
    */
    function appraisalFee(){
    	var appraisalFee = $("#appraisalFee").val();
    	if(appraisalFee == null || appraisalFee == ""){
    		$("#confirmAppraisalNo").removeClass("none");
    		$("#confirmAppraisal").addClass("none");
    	}else{
    		$("#confirmAppraisal").removeClass("none");
    		$("#confirmAppraisalNo").addClass("none");
    	}
    };
    
    /**
    * 提交小额鉴定	
    */
    $("#confirmAppraisal").click(function(){
    	var appraisalFee = $("#appraisalFee").val();
    	var cibBankId = $("#cibBankId").val();
    	var	url = '${bootAppPath}/cibbank/authenticate';

		var params = {memberId:memberId,cibBankId:cibBankId,amt:appraisalFee};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == "success"){
				$("#finishBankWindow").addClass("none");
				$("#bindingBankWindow").addClass("none");
				$("#bank_add").removeAttr("disabled");//按钮启用
				
				$("#bankAcctBankNo").val("");
		    	$("#bankAcctCnapsCode").val("");
		    	$("#bankAcctBankBranch").val("");
		    	$("#bankAcctAcctNo").val("");
		    	$("#bankAcctAcctName").val("");
		    	$("#bankstr").val("");
		    	
		    	loadJdCard();
			}else{
				alert(data.data.msg);
			}
		}
    });
    
    /**
    * 打开红包弹窗
    */
    $("#openCoupon").click(function(){
    	$("#maskWindow").removeClass("none");
    	$("#couponWindow").removeClass("none");
    	$("#title").html("选择红包");
    });
    
    /**
	* 加载用户是否为会员
	*/
	function loadVip(){
    	if(memberId == null || memberId == "")return;
    	var url = "${bootAppPath}/vipmember/get/by/memberid";
    	var params = {memberId:memberId,vipType:0};
    	var data = bootPost(url,params);
    	if(data != null){
    		if(data.data.response == 'success'){
        		$(".fee").html(parseInt(data.data.data.fee));
    			if(data.data.data.vipMember != null){
    				$(".isVip").removeClass("none");
    				$("#fee").html(parseInt(data.data.data.fee));
    				$(".actualPay").html(parseInt($("#deposit").html()));
    			}else{
    				$(".noVip").removeClass("none");
    				$("#needCoupon").removeClass("none");
    				$(".actualPay").html(parseInt($("#deposit").html())+parseInt(data.data.data.fee));
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
	* 选择要使用的红包
	*/
	function selectCoupon(id,money){
		$("#couponId").val(id);
		$("#couponNum").html("-"+money);
		
		$("#maskWindow").addClass("none");
		$("#couponWindow").addClass("none");//红包
		
		$(".actualPay").html(parseInt(parseInt($("#deposit").html())+parseInt($(".fee").html())-parseInt(money)));
	};
	
	/**
	* 保证金说明的弹窗
	*/
	$(".depositExplain").click(function(){
		$("#depositExplain").removeClass("none");
		$("#maskWindow").removeClass("none");
		$("#title").html("保证金说明");
	});
</script>
[@main.footer/]
[/@main.body]