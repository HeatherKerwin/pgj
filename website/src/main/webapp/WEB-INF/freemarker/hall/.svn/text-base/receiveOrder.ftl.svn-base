[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/order.css"/>
[@main.header currentmenu='1'/]

<!--商票报价 -->
<div class="flex flex-direction-column mt25 w1150 bc bcwhite pt30 pl25 pr25 pb25 mb20">
    <!--订单状态类型-->
    <div class="flex-row flex-justify-space-between bbe0e0e0 h27 lh18 mt30">
        <div class="flex-row flex-direction-row">
            <span class="cd43c33 f18 fb">银票报价</span>
            <div class="ml30" id="place"><img src="https://img.utiexian.com/website/receive/position.png" class="h16 mr3 mt3"><span class="place"></span></div>
        </div>
        <div class="flex-row flex-direction-row">
            <div class="c717583">下单日期:<span class="begintime"></span></div>
        </div>
    </div>

    <!--订单详情-->
    <div class="flex-row flex-direction-column w bae0e0e0 mt10">
        <!--情况2-->
		<div class="orderyin2 pr">
			<div class="ticketReturn pa t70 l_50 none"><img src="${imagePath}/website/paperMarket/returnTicket.png" alt="回头票"></div>
			<div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 tc bbe0e0e0 c717583">
				<div class="w232">银票订单号</div>
				<div class="w158">票面金额</div>
				<div class="w320">承兑行</div>
				<div class="w129">保证金</div>
	        	<div class="w320">票据特征</div>
			</div>
	    	<div class="flex-row flex-justify-space-between bbe0e0e0">
	        	<div class="flex-row flex-direction-column Rright">
	            	<div class="flex-row flex-direction-row tc lh45 pt10 pb10 bbe0e0e0">
	                	<div class="w232 Rright pt15">
	                		<span class="ordernumber fl ml20 lh20"></span>
	                		<a class="fl ml10 download" title="下载合同"><img src="https://img.utiexian.com/website/receive/down.png" width="21" height="21" alt="下载合同"></a>
	                	</div>
	                	<div class="w158 cd43c33 Rright"><span class="allmoney"></span>万元</div>
	                	<div class="w320 Rright bank wsn oh toe"></div>
	                	<div class="w129"><span class="deposit"></span>元</div>
	            	</div>
	            	<div class="flex-row flex-justify-space-between w pt10 pb10">
	                	<div class="flex-row flex-direction-column w212 pl20 lh30 Rright">
							<div>到期日期：<span class="endtime"></span></div>
							<div>计息天数：<span class="jxts"></span>天</div>
						</div>
	                	<div class="flex-row flex-direction-row w599 flex-align-self-baseline mt10">
	                    	<div class="w60 ml20">备注：</div>
	                    	<p class="w500 mr20 memberother"></p>
	                	</div>
	            	</div>
	        	</div>
	        	<div class="flex-row flex-direction-row flex-wrap w320 lh34 tc">
	            	<div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16">电票</div>
	            	<div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 accept_time_dian"></div>
	        	</div>
	    	</div>
		</div>
		<!--情况3-->
		<div class="orderyin1 pr">
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
	    	<div class="flex-row flex-justify-space-between bbe0e0e0">
	        	<div class="flex-row flex-direction-column Rright">
					<div class="flex-row flex-direction-row tc lh45 pt10 pb10 bbe0e0e0">
						<div class="w232 Rright pt15">
							<span class="ordernumber fl ml20 lh20"></span>
	                		<a class="fl ml10 download" title="下载合同"><img src="https://img.utiexian.com/website/receive/down.png" width="21" height="21" alt="下载合同"></a>
						</div>
						<div class="w155 cd43c33 Rright"><span class="allmoney"></span>万元</div>
						<div class="w90 Rright"><span class="endorse"></span>手</div>
						<div class="w182 Rright endtime"></div>
						<div class="w90 Rright"><span class="jxts"></span>天</div>
						<div class="w90"><span class="deposit"></span>元</div>
					</div>
					<div class="flex-row flex-justify-space-between w pt10 pb10">
	                	<div class="flex-row flex-direction-row w599 flex-align-self-baseline mt10">
	                    	<div class="w60 ml20">备注：</div>
	                    	<p class="w500 mr20 memberother"></p>
	                	</div>
	            	</div>
				</div>
	        	<div class="flex-row flex-direction-row flex-wrap w320 lh34 tc">
	            	<div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16">纸票</div>
	            	<div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 type2"></div>
	            	<div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 flaw_ticket_zhi">瑕疵票</div>
					<div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 need_todoor">需要上门</div>
	        	</div>
	    	</div>
		</div>
        <!-- 企业信息 -->
        <div class="flex-row flex-direction-row lh40 pt10 pb8 tc bbe0e0e0 fb">
            <div class="flex-row flex-direction-row w590">
                <div class="ml40 c717583 mr30">成单率</div>
                <div class="mr120"><span class="singleRate"></span>%</div>
                <div class="mr30 c717583">确认背书时间</div>
                <div id="endorseTime"></div>
            </div>
            <div class="flex-row flex-direction-row">
                <div class="mr20 c717583">企业评分</div>
                <div class="flex-row flex-direction-row w460">
                    <div class="flex-col-4">价格真实：<span class="price"></span></div>
                    <div class="flex-col-4">服务态度：<span class="service"></span></div>
                    <div class="flex-col-4">确认效率：<span class="speed"></span></div>
                </div>
            </div>
        </div>

        <!--企业信息-->
        <div class="flex-row flex-direction-row flex-justify-space-around h88 c717583 none bns_company">
            <div class="lh86">下单企业名称：<span class="cblack company"></span></div>
            <div class="lh86">联系人：<span class="cblack name"></span></div>
            <div class="flex-row flex-direction-column mt30 tc">
                <div>联系方式：<span class="cblack phone"></span></div>
                <div class="cblack">（联系出票企业时请说是在票据管家平台上看到的）</div>
            </div>
        </div>
    </div>

    <div class="lh42 bbe0e0e0 w mt10 fb">我的报价</div>
    <div class="flex-row flex-direction-column w bte0e0e0 ble0e0e0 bre0e0e0 mt10">
        <!--我的报价-->
        <div class="flex-row flex-direction-column w lh50">
            <div class="flex-row flex-direction-row w tc bcf9f9f9 bbe0e0e0 c717583">
                <div class="flex-col-6">扣息</div>
                <div class="flex-col-6">每十万扣息</div>
                <div class="flex-col-6">打款金额<span class="cd43c33">(电票包含鉴证服务费)</span></div>
            </div>
            <div class="flex-row flex-direction-row w tc bbe0e0e0 pt10 pb10">
                <div class="flex-col-6 cd43c33 Rright"><span id="txlx"></span>元</div>
                <div class="flex-col-6 cd43c33 Rright"><span class="price2"></span>元</div>
                <div class="flex-col-6 cd43c33"><span id="txje"></span>元</div>
            </div>
        </div>
        <!-- 查看票面 -->
        <div class="flex-row flex-direction-column w tc bbe0e0e0 bcf9f9f9" id="picpath">
        	<p class="w mt40">票面信息</p>
            <div class="flex-justify-center w mt20 mb30">
                <a href="" target="_blank" id="zoom"><img src="" class="w400 h220 picpath"></a>
            </div>
        </div>
        
         <!-- 查看背书-->
        <div class="flex-row flex-direction-column w tc bbe0e0e0 none" id="endorse_pic_path">
            <p class="w mt40">上传背书凭证</p>
            <label for="uploadImg" class="flex-justify-center w mt16 mb16">
                <img src="" class="w400 h220 endorse_pic_path">
            </label>
        </div>

        <!-- 提示与操作按钮 -->
        <div class="flex-row flex-justify-space-between pt15 pb10 bcf9f9f9 lh40 bbe0e0e0">
            <div class="flex-row flex-direction-row">
                <img src="https://img.utiexian.com/website/discount/timer.png" class="wa h20 mt10 ml10">
                <div class="f18 ml8 mr10 cd43c33 minute_show"></div>
                <!--文字提示-->
                <div id="prompt"></div>
            </div>
            <div class="flex-row flex-direction-row-reverse">
            	<input type="hidden" value="" id="discId"/>
            	<input type="hidden" value="" id="distId"/>
            	<input type="hidden" value="" id="state"/>
            	<input type="hidden" value="" id="type1"/>
            	<input type="hidden" value="" id="cib_id"/>
            	<input type="hidden" value="" id="bns_memberId"/>
            	<a href="${basePath}/piaojuxueyuan/index?type=5" id="toGuideBtn"><button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none">打款指南</button></a>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="payBtn">支付票款</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w160 f18 mr10 none" id="SeePayBtn">查看票款是否到账</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="endorseBtn">签收背书</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="cancelBtn">取消订单</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="evaluateBtn">去评价</button>
                <button class="h40 bcf9f9f9 cd43c33 bad43c33 br5 w100 f18 mr10 none" id="updatePrice">修改报价</button>
            </div>
        </div>
    </div>

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
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--确认背书-->
                <div class="flex-row flex-direction-column flex-alignItems-center w hmin150 none" id="endorseWindow">
                    <div class="flex-row flex-alignItems-center flex-justify-center h100 f24">
                        我已收到背书！点击确认按钮后，票款将立即打入票方账户。
                    </div>
                    <!--按钮操作-->
                    <div class="flex-row flex-justify-center lh30 mt20">
                        <input type="button" value="确认" id="confirm_endorse" class="w120 h35 ba bad43c33 bcd43c33 cwhite br3 mr20">
                    </div>
                </div>
                
                <!-- 下载交易合同 -->
                <div class="flex-row flex-direction-column w lh30 mt20 none contract">
                    <div class="flex-row flex-direction-column pl20">
					 	<input type="email" id="email" maxlength="30" class="w310 h30 bae0e0e0 br3 ti10 ml70 validate[required,custom[email]]" placeholder="请输入您的邮箱号">
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
                        	<option value="1">资金不足</option>
                            <option value="2">票面信息输入有误</option>
                            <option value="3">不在业务范围内</option>
                            <option value="4">已提前出票</option>
                            <option value="5">其它</option>
                        </select>
                    </div>
                    <textarea class="h120 bae0e0e0 br3 pt10 pr10 pl10 mt20 none validate[required]" id="reasonOther" maxlength="100" placeholder="最多100字的理由。"></textarea>
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
                            <div class="cd43c33 w60 h24 lh24 br5 bad43c33 tc ml20" id="comment_type">标签</div>
                        </div>
                        <div class="flex-row flex-direction-row mt10">
                            <div id="comment_name"></div>
                            <div class="ml20" id="comment_phone"></div>
                        </div>
                    </div>
                    <div class="h2 xuxian w mt10"></div>
                    <div class="lh30">发表评价：</div>
                    <textarea class="h120 bae0e0e0 br3 pt10 pr10 pl10" id="content" maxlength="200" placeholder="请输入15~200个字符"></textarea>
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

<input type="hidden" value="" id="bind_id" />
<input type="hidden" value="" id="draft_no" />
<input type="hidden" value="" id="jd_discount_url" />
[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${basePath}/js/layer/layer.js"></script>
<script type="text/javascript">
	var cib_login_url_org;//兴业登录的路径机构端
    $(function(){
    	loadDate();
    });
    
    var id = ${id};
	var memberId = ${member.id};
	var orgId = ${orgId};
	
	/**
	* 页面初始化加载数据
	*/
    function loadDate(){
    	var url = '${bootAppPath}/distributeorder/get/details';
		var params = {id:id};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				var disc = data.data.data.disc;
				$("#bind_id").val(disc.dist_bind_id);
				$("#draft_no").val(disc.draft_no);
				if(disc.cib_cancel != null){
					$("#cib_id").val(disc.cib_cancel.id);
					if(disc.cib_cancel.bnsTime != null){
						if(disc.cib_cancel.orgTime != null){
							if(disc.state == 0){
								$("#prompt").html("无效订单");
							}else{
								$("#prompt").html("双方同意取消订单，订单处理中！");
							}
						}else{
							$("#prompt").html("票方已经申请取消订单，如有异议，请联系客服！");
							$("#cancelBtn").removeClass("none");
						}
					}else if(disc.cib_cancel.orgTime != null){
						$("#prompt").html("资方已经申请取消订单，如有异议，请联系客服！");
					}
				}else{
					_timer(disc.create_time,disc.state);
					if(disc.state != 1){
						$(".bns_company").removeClass("none");
					}
					if(disc.state == 1){
						$("#updatePrice").removeClass("none");
					}else if(disc.state == 4){
						$("#prompt").html("支付票款");
						if(disc.advance_time != null){
							if(disc.dist_bind_id==null||disc.dist_bind_id==""){//兴业支付
								seePayBtn();
							}else{
								cib_login_url_org = disc.cashier_desk_url+"&cb=${basePath}/hall/index2";
							}
							$("#SeePayBtn").removeClass("none");
						}else{
							$("#payBtn").removeClass("none");
						}
						$("#cancelBtn").removeClass("none");
					}else if(disc.state == 7){
						if(disc.dist_bind_id == null || disc.dist_bind_id == ""){
							$("#endorseBtn").removeClass("none");
							$("#cancelBtn").removeClass("none");
						}
						$("#prompt").html("签收背书");
					}else if(disc.state == 5){
						$("#prompt").html("等待企业收款");
					}else if(disc.state == 2){
						if(disc.dist_bind_id == null || disc.dist_bind_id == ""){
							$("#cancelBtn").removeClass("none");
						}
						$("#prompt").html("等待企业背书");
					}else if(disc.state == 3){
						if(disc.c_id != null && disc.c_id >0){
							$("#prompt").html("已完成");
						}else{
							$("#prompt").html("等待评价");
							$("#evaluateBtn").removeClass("none");
						}
					}
				}
				$("#discId").val(disc.id);
				$("#distId").val(disc.dist_id);
				$("#state").val(disc.state);
				$("#type1").val(disc.type1);
				$(".ordernumber").html(disc.ordernumber);
				$(".allmoney").html(disc.allmoney);
				$(".endtime").html(disc.endtime);
				$(".jxts").html(disc.jxts);
				$(".deposit").html(disc.deposit);
				$(".begintime").html(disc.begintime);
				$(".type2").html(getBank(disc.type2));
				$(".memberother").html(disc.memberother);
				$(".endorse").html(disc.endorse);
				$("#txje").html(disc.txje);
				$("#txlx").html(disc.txlx);
				$(".bank").html(disc.bank);
				$(".price2").html(((disc.txlx)/(disc.allmoney)*10).toFixed(2));
				$("#bns_memberId").val(disc.bns_member_id);
				if(disc.back_endorse == "T"){
					$(".ticketReturn").removeClass("none");
				}
				
				if(disc.picpath != null){
					$(".picpath").attr("src","${bootPic}"+disc.picpath);
					$("#zoom").attr("href","${bootPic}"+disc.picpath);
				}else{
					$("#picpath").addClass("none");
				}
				if(disc.type1 == 1){
					$(".place").html(disc.place);
					$(".orderyin2").addClass("none");
					if(disc.need_todoor == 0){
						$(".need_todoor").addClass("none");
					}
					if(disc.flaw_ticket == 1){
						$(".flaw_ticket_zhi").addClass("none");
					}
					if(disc.state == 7){
						if(disc.endorse_pic_path != null){
							$(".endorse_pic_path").attr("src","${bootPic}"+disc.endorse_pic_path);
							$("#endorse_pic_path").removeClass("none");
						}else{
							$("#endorse_pic_path").addClass("none");
						}
					}
				}else{
					$("#toGuideBtn").removeClass("none");
					$("#place").addClass("none");
					$(".orderyin1").addClass("none");
					if(disc.accept_time == 0){
						$(".accept_time_dian").html("半年期");
					}else if(disc.accept_time == 1){
						$(".accept_time_dian").html("一年期");
					}
				}
				
				var comment = data.data.data.comment;
				$(".singleRate").html(((comment.singleRate)*100).toFixed(2));
				$("#endorseTime").html(getTime(comment.endorseTime));
				if(comment.price != "--"){
					$(".price").html(comment.price.toFixed(2));
				}else{
					$(".price").html("--");
				}
				if(comment.service != "--"){
					$(".service").html(comment.service.toFixed(2));
				}else{
					$(".service").html("--");
				}
				if(comment.speed != "--"){
					$(".speed").html(comment.speed.toFixed(2));
				}else{
					$(".speed").html("--");
				}
				
				var orgInfo = data.data.data.orgInfo;
				if(orgInfo!=null&&orgInfo!=""){
					$(".company").html(orgInfo.company);
					$(".name").html(orgInfo.name);
				}
				
				$(".company").html(data.data.data.company);
				$(".name").html(data.data.data.name);
				
				var member = data.data.data.member;
				$(".phone").html(member.mobile);
				
				paymoney();
			}
		}
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
	
	//关闭按钮
	$("#closeBtn, .closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#endorseWindow").addClass("none"); //背书
	    $("#evaluateWindow").addClass("none"); //评价
	    $("#appealWindow").addClass("none"); //申诉
	    $("#cancelWindow").addClass("none"); //取消
	    $("#contractWindow").addClass("none"); //转让合同
	    $(".contract").addClass("none"); //下载合同
	});
	
	//拒绝订单理由显示隐藏
	$("#cancelBtn").click(function(){
		$("#title").html("取消订单");
	    $("#maskWindow").removeClass("none");
	    $("#cancelWindow").removeClass("none");
	});
	
	//填写理由
	$("#reason").change(function(){
	    var value = $(this).val();
	    if(value=="5"){
	        $("#reasonOther").removeClass("none");
	    }else{
	        $("#reasonOther").addClass("none");
	    }
	});
	
	//去评价打开评论窗口
	$("#evaluateBtn").click(function(){
		$("#title").html("评论企业");
		var name = $(".name").html();
		var company = $(".company").html();
		var phone = $(".phone").html();
		var type1 = $("#type1").val();
		
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
	
	//支付票款
	$("#payBtn").click(function(){
		var type1 = $("#type1").val();
		if(type1 == 1){
			agreeContract();
		}else{
			var distId = $("#distId").val();
			var bind_id = $("#bind_id").val();
			if(bind_id!=null&&bind_id!=""){//京东合同
				$("#iframe").attr("src","${basePath}/jd/agreements/contract?distId="+distId+"&orderType=DISCOUNTRECORD");
			}else{//兴业合同
				$("#iframe").attr("src","${basePath}/agreements/contract?distId="+distId+"&orderType=DISCOUNTRECORD");
			}
			$("#contractWindow").removeClass("none");
		}
	});
	
	/**
	* 同意票据转让合同
	*/
	function agreeContract(){
		layer.load(2);
		var type1 = $("#type1").val();
		var id = $("#distId").val();
		var state = $("#state").val();
		if(type1 == 2){
			var newTab = window.open("${basePath}/loading");
		}
		setTimeout(function(){
			var url = '${bootAppPath}/distributeorder/update/advancetime';
			var params = {id:id,state:state,memberId:memberId};
			var data = bootPost(url,params);
			console.log(data);
			if(data != null){
				if(data.data.response == 'success'){
					layer.closeAll('loading');//关闭加载图标
					if (type1 == 2){//电票的走
						if(data.data.data!=null&&data.data.data.body != null){//京东支付
							if(data.data.data.body.cashierDeskUrl != null && data.data.data.body.cashierDeskUrl != ""){
								newTab.location.href = data.data.data.body.cashierDeskUrl+"&cb=${basePath}/hall/index2";
							}
						}else{
							if(data.data.data!=null && data.data.data.error_msg!=null){
								alert(data.data.data.error_msg);
								return;
							}
							if(data.data.data!=null && data.data.data.return_msg!=null){
								alert(data.data.data.return_msg);
								return;
							}
							if(data.data.data.biz_order.status == 12){ 
								alert("订单打款成功");
								location.href = "${basePath}/hall/index2";
							}else if (data.data.data.biz_order.status == 11){
								if(cib_login_url_org != null && cib_login_url_org != ""){
									newTab.location.href = cib_login_url_org;
								}
							}else{
								alert("订单出错!请联系客服");
							}
						}
					}else{//纸票的走
						alert("订单打款成功");
						location.href = "${basePath}/hall/index2";
					}
				}else{
					layer.closeAll('loading');//关闭加载图标
					alert(data.data.msg);
				}
			}else{
				layer.closeAll('loading');//关闭加载图标
			}
		},100);
	};
	
	/**
	* 查看支付票款
	*/
	$("#SeePayBtn").click(function(){
		if(cib_login_url_org != null && cib_login_url_org != ""){
			window.open(cib_login_url_org);
		}
	});
	
	/**
	* 获取机构兴业的登录路径
	*/
	function seePayBtn(){
		var url = '${bootAppPath}/cib/corp/query';
		var params = {memberId:memberId,type:1};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				cib_login_url_org = data.data.data.login_url;
			}else{
				alert(data.data.msg);
			}
		}
	}
	
	/**
	* 确认签收背书
	*/
	$("#endorseBtn").click(function(){
		var id = $("#distId").val();
		var state = $("#state").val();
		var type1 = $("#type1").val();
		
		var url = '${bootAppPath}/distributeorder/update/confirmendorsetime';
		var params = {id:id,state:state};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				if(type1 == 1){
					alert("确认签收成功");
					location.href = "${basePath}/hall/index2";
				}else{
					if(data.data.data!=null && data.data.data.error_msg!=null){
						alert(data.data.data.error_msg);
						return;
					}
					if(data.data.data!=null && data.data.data.return_msg!=null){
						alert(data.data.data.return_msg);
						return;
					}
					
					if(data.data.data.return_code == '99' && data.data.data.trans_status!='F'){
						 if(data.data.data.biz_order.applications[0].endorse_status == 'S') { 
							 alert('确认签收成功');
							 location.href = "${basePath}/hall/index2";
						 } else if (data.data.data.biz_order.applications[0].endorse_status == 'F'){
							 alert('票据还未在网银上签收，请在网银实际签收后再到这里确认签收。');
						 } else {
							 alert('已提交，等待审核。'+data.data.data.biz_order.applications[0].endorse_desc);
						 } 
					}else{
						alert(data.data.data.error_msg+"，请联系客服！");
					}
				}
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
		var params = {service:service,speed:speed,price:price,dcrdId:dcrdId,dtboId:dtboId,role:1,type:0,operatorId:memberId,content:content};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				$("#maskWindow").addClass("none");
				$("#evaluateWindow").addClass("none");
				alert("评论成功");
				location.href = "${basePath}/distributeorder/list";
			}
		}
	});
	
	/**
	* 机构取消订单的操作
	*/
	$("#confirm_reason").click(function(){
		var state = $("#state").val();
		var reason = $("#reason").val();
		var reasonOther = $("#reasonOther").val();
		var id ;
		var type1 = $("#type1").val();
		var cib_id = $("#cib_id").val();

		if(reason == 5){
			if(!$("#reasonOther").validationEngine("validate")){
				setTimeout(function(){$(".formError").hide();},2000);
				$("#reasonOther").focus();
				return ;
			 }
		}
		
		var url;
		var params = {cancel:reason,cancelCause:reasonOther};
		
		if(state == 2 || state == 7 || state == 4){
			id = $("#discId").val();
			url = '${bootAppPath}/cibcancel/save';
			params.orderType = 'DISCOUNTRECORD';
			params.type = 1;
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
				location.href = "${basePath}/distributeorder/list";
			}
		}
	});
	
	/**
	* 下载合同
	*/
	$(".download").click(function(){
		var dist_bind_id = $("#bind_id").val();
		if(dist_bind_id!=null){
			var distId = $("#distId").val();
			var url = '${bootAppPath}/order/sceneevi/url';
			var params = {orderId:distId,orderType:"DISTRIBUTEORDER"};
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
	* 接单时间倒计时
	*/
	var timerArr = new Array();
    function _timer(create_time,state){
    	if(state == 0 || state == 3){
			$('.minute_show').html('('+0+'分'+0+'秒'+')');
			clearInterval(_t);
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
	* 计算实付金额
	*/
	function paymoney(){
    	var txje = $("#txje").html();
    	var allmoney = $(".allmoney").html();
		var receivables = txje*10000;
			
		var params = {money:allmoney*10000,txje:receivables};
		
		var dist_bind_id = $("#bind_id").val();
		var draft_no = $("#draft_no").val();
		if(dist_bind_id !=null&&dist_bind_id!=""){
			params.draftNo = draft_no;
			params.bindId = dist_bind_id;
		}
		var url = '${bootAppPath}/dispatch/fee';
		var data = bootPost(url,params);
		
		if(data != null){
			if(data.data.response == 'success'){
				var type1 = $("#type1").val();
				if(type1 == 2){
					var appraisalFee = data.data.data;
					var actualMoney = parseFloat(receivables) + parseFloat(appraisalFee);
					$("#txje").html(fmoney(actualMoney,2));
				}else{
					$("#txje").html(fmoney(receivables,2));
				}
			}else{
				alert(data.data.msg);
			}
		}
	};
	
	/**
	* 机构对报价进行修改
	*/
	$("#updatePrice").click(function(){
		var id = $("#discId").val();
		var distId = $("#distId").val();
		var bns_memberId = $("#bns_memberId").val();
    	var order_type = 'DISCOUNTRECORD';

    	var map = new Map();
		map.put("_PAGE", "/hall/order_update_bj");//必传
		map.put("id", id);
		map.put("distId", distId);
		map.put("bns_memberId", bns_memberId);
		map.put("order_type", order_type);
		_OPENPAGE_FORM(map);
	});
	
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