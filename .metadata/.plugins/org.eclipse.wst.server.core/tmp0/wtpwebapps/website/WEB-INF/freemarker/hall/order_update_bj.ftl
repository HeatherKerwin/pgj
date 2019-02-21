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
            <span class="cd43c33 f18 fb orderType"></span>
            <div class="ml30 place"><img src="https://img.utiexian.com/website/receive/position.png" class="h16 mr3 mt3"><span id="place"></span></div>
        </div>
        <div class="flex-row flex-direction-row">
            <div class="c717583">下单日期:<span class="begintime"></span></div>
        </div>
    </div>

    <!--订单详情-->
    <div class="flex-row flex-direction-column w bae0e0e0 mt10 ordershang pr">
    	<div class="ticketReturn pa t70 l_50 none"><img src="${imagePath}/website/paperMarket/returnTicket.png" alt="回头票"></div>
        <div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 tc bbe0e0e0 c717583">
            <div class="w232">商票订单号</div>
            <div class="w155">票面金额</div>
            <div class="w105">背书</div>
            <div class="w216">承兑企业</div>
            <div class="w122">保证金</div>
            <div class="w320">票据特征</div>
        </div>
        <div class="flex-row flex-justify-space-between bbe0e0e0">
            <div class="flex-row flex-direction-column w830 Rright">
                <div class="flex-row flex-direction-row tc lh45 pt10 pb10 bbe0e0e0">
                    <div class="w232 Rright ordernumber"></div>
                    <div class="w155 cd43c33 Rright"><span class="allmoney"></span>万元</div>
                    <div class="w105 Rright"><span class="endorse"></span>手</div>
                    <div class="w216 Rright bank"></div>
                    <div class="w122"><span class="deposit"></span>元</div>
                </div>
                <div class="flex-row flex-justify-space-between w pt10 pb10">
                    <div class="flex-row flex-direction-column w212 pl20 lh30 Rright">
                        <div>开票日期：<span class="printtime"></span></div>
                        <div>到期日期：<span class="endtime"></span></div>
                        <div>计息天数：<span class="jxts"></span>天</div>
                    </div>
                    <div class="flex-row flex-direction-row w599 flex-align-self-baseline">
                        <div class="w60 ml20">备注：</div>
                        <p class="w500 mr20 memberother"></p>
                    </div>
                </div>
            </div>
            <div class="flex-row flex-direction-row flex-wrap w320 lh34 tc">
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 dian_type1"></div>
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 need_todoor none">是否上门</div>
                <div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 accept_time_dian none"></div>
            </div>
        </div>
        <!-- 企业信息 -->
        <div class="flex-row flex-direction-row lh40 pt10 pb8 tc fb">
            <div class="flex-row flex-direction-row w590">
                <div class="ml40 c717583 mr30">成单率</div>
                <div class="mr120"><span class="singleRate"></span>%</div>
                <div class="mr30 c717583">确认背书时间</div>
                <div class="endorseTime"></div>
            </div>
            <div class="flex-row flex-direction-row">
                <div class="mr20 c717583">企业评分</div>
                <div class="flex-row flex-direction-row w460">
                    <div class="flex-col-4">价格真实：<span class="price"></span></div>
                    <div class="flex-col-4">服务态度：<span class="service"></span> </div>
                    <div class="flex-col-4">确认效率：<span class="speed"></span></div>
                </div>
            </div>
        </div>

    </div>
   
	<!--情况2-->
	<div class="flex-row flex-direction-column w bae0e0e0 mt10 orderyin2 pr">
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
                	<div class="w232 Rright ordernumber"></div>
                	<div class="w158 cd43c33 Rright"><span class="allmoney"></span>万元</div>
                	<div class="w320 Rright bank"></div>
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
            	<div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 accept_time_dian none"></div>
        	</div>
    	</div>
    	 <!-- 企业信息 -->
        <div class="flex-row flex-direction-row lh40 pt10 pb8 tc fb">
            <div class="flex-row flex-direction-row w590">
                <div class="ml40 c717583 mr30">成单率</div>
                <div class="mr120"><span class="singleRate"></span>%</div>
                <div class="mr30 c717583">确认背书时间</div>
                <div class="endorseTime"></div>
            </div>
            <div class="flex-row flex-direction-row">
                <div class="mr20 c717583">企业评分</div>
                <div class="flex-row flex-direction-row w460">
                    <div class="flex-col-4">价格真实：<span class="price"></span></div>
                    <div class="flex-col-4">服务态度：<span class="service"></span> </div>
                    <div class="flex-col-4">确认效率：<span class="speed"></span></div>
                </div>
            </div>
        </div>
	</div>
	<!--情况3-->
	<div class="flex-row flex-direction-column w bae0e0e0 mt10 orderyin1 pr">
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
					<div class="w232 Rright ordernumber"></div>
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
            	<div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 flaw_ticket none">瑕疵票</div>
				<div class="c00a5f2 w96 h35 br10 ba00a5f2 ml35 mt16 need_todoor none">需要上门</div>
        	</div>
    	</div>
    	 <!-- 企业信息 -->
        <div class="flex-row flex-direction-row lh40 pt10 pb8 tc fb">
            <div class="flex-row flex-direction-row w590">
                <div class="ml40 c717583 mr30">成单率</div>
                <div class="mr120"><span class="singleRate"></span>%</div>
                <div class="mr30 c717583">确认背书时间</div>
                <div class="endorseTime"></div>
            </div>
            <div class="flex-row flex-direction-row">
                <div class="mr20 c717583">企业评分</div>
                <div class="flex-row flex-direction-row w460">
                    <div class="flex-col-4">价格真实：<span class="price"></span></div>
                    <div class="flex-col-4">服务态度：<span class="service"></span> </div>
                    <div class="flex-col-4">确认效率：<span class="speed"></span></div>
                </div>
            </div>
        </div>
	</div>

    <div class="lh42 bbe0e0e0 w mt10 fb">
        我的报价
    </div>
    <div class="flex-row flex-direction-column w bae0e0e0 mt10">
        <!--订单超时-我的报价-->
        <div class="flex-row flex-direction-column w lh50">
            <div class="flex-row flex-direction-row w tc bcf9f9f9 bbe0e0e0 c717583">
                <div class="flex-col-4">共扣息</div>
                <div class="flex-col-4">每十万贴息</div>
                <div class="flex-col-4">票款</div>
                <div class="flex-col-4 cp c3366cc appraisalFee societe none">鉴证服务费<span class="societeName f10"></span></div>
                <div class="flex-col-4 appraisalFee none">实付金额</div>
            </div>
            <div class="flex-row flex-direction-row w tc pt10 pb10">
                <div class="flex-col-4 Rright"><span><input type="text" placeholder="请输入金额" class="w90 b0 h30 lh30 bae0e0e0 ti8 money validate[required],validate[custom[onlyNumberSp]]" onblur="paymoney();"></span>元</div>
                <div class="flex-col-4 cd43c33 Rright"><span class="txlx"></span>元</div>
                <div class="flex-col-4 Rright"><span id="paid"></span>元</div>
                <input type="hidden" value="" id="txje">
                <div class="flex-col-4 cp Rright appraisalFee societe none"><span id="appraisalFee"></span>元</div>
                <div class="flex-col-4 appraisalFee none"><span id="actualMoney"></span>元</div>
            </div>
        </div>
    </div>

    <div class="flex-row flex-justify-space-between bae0e0e0 pl20 pt20 pb20 pr30 lh30 mt20">
	    <div class="flex-row flex-direction-row">
		    <div class="c717583">票面：</div><span id="promptStr" class="c717583">无票面</span>
		    <img src="" alt="" id="picpath" width="410" height="170" class="ml35 cp none">
		</div>
    </div>
    <!-- 提示与操作按钮 -->
    <div class="flex-row flex-justify-space-between w bcf9f9f9 lh50 bae0e0e0 mt20">
        <div class="flex-row flex-direction-row">
            <img src="https://img.utiexian.com/website/discount/timer.png" class="wa h20 mt16 ml10">
            <div class="f18 ml8 mr10 cd43c33 minute_show"></div>
        </div>
        <div class="flex-row">
            <input type="hidden" value="" id="did">
	        <input type="hidden" value="" id="is_designated">
	        <input type="hidden" value="" id="orderId">
	        <input type="hidden" value="" id="type1">
	        <input type="hidden" value="" id="need_todoor">
	        <div class="flex-row flex-direction-row-reverse">
	            <button class="bcd43c33 bad43c33 cwhite w150 f18 cp" id="confirmPrice">修改报价</button>
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
                <label for="closeIcon" id="closeBtn" class="cp">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
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
                    <input type="button" value="关闭" class="w150 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn cp">
                    <input type="button" value="确认报价" id="discountSuccess" onclick="confirmPrice();" class="w150 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 cp">
                </div>
            </div>
            
            <!-- 保证金说明 -->
            <div class="mt20 lh30 pl20 pr20 ti32 none" id="depositExplain">
	            <p>为了保证交易双方的安全，票据管家将对双方收取履约保证金。若交易顺利完成，保证金返还双方账户，若交易过程中出现违约行为，保证金将作为补偿，退还至未违约方账户。</p>
	            <p>当您进行精准报价时收取的保证金，若出票方未选择您作为交易对象，该保证金在票方选择其他资方后马上返还。</p>
            </div>
         </div>
    </div>
</div>
<!-- 放大图片 -->
<div class="w h pf bc05 zi200 top none" id="zoomWindow">
    <div class="flex flex-alignItems-center flex-justify-center bc w1200 h600">
        <div class="flex-row flex-direction-column w h bcwhite bae0e0e0 br3 pb20 mt100">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>票面</div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn" class="cp">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <div class="pl20 pr20  h500">
		        <img alt="" src="" id="zoom" style=" width: 100%; height: 100%;">
            </div>
         </div>
    </div>
</div>
<input type="hidden" id="couponId" name="couponId">
<input type="hidden" id="disc_bind_id" name="disc_bind_id">
<input type="hidden" id="draft_no" name="draft_no">
[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/javascript">
	var id = '${id}';
	var distId = '${distId}';
	var bns_memberId = '${bns_memberId}';
	var order_type = '${order_type}';
	var memberId = ${member.id};
	var orgId = ${orgId};
	
	/**
	*展示订单
	*/
	function loadDate(){
		var url = '${bootAppPath}/distributeorder/get/details';
		if(order_type == "DISCOUNTRECORD"){
			url = '${bootAppPath}/distributeorder/get/details';
		}else if(order_type == "DISCOUNTRECORDSP"){
			url = '${bootAppPath}/distributeordersp/get/details';
		}
		var params = {id:distId};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == 'success'){
				var comment = data.data.data.comment;
				var data = data.data.data.disc;
				_timer(data.create_time);
				$("#type1").val(data.type1);
				$(".ordernumber").html(data.ordernumber);
				$("#draft_no").val(data.draft_no);
				if(data.type1 == 1){//纸票
					if(data.has_ticket == 0){//票已开出	显示票面
						$("#promptStr").addClass("none");
						$("#picpath").attr("src","${bootPic}"+data.picpath);
						$("#zoom").attr("src","${bootPic}"+data.picpath);
						$("#picpath").removeClass("none");
					}else{
						$("picpath").addClass("none");
						$("promptStr").removeClass("none");
					}
				}else if(data.type1 == 2){//电票	显示票面
					$("#promptStr").addClass("none");
					$("#picpath").attr("src","${bootPic}"+data.picpath);
					$("#zoom").attr("src","${bootPic}"+data.picpath);
					$("#picpath").removeClass("none");
				}
				if(order_type == "DISCOUNTRECORD"){
					$(".ordershang").addClass("none");
					if(data.type1 == 1){
						$(".orderyin2").addClass("none");
					}else if(data.type1 == 2){
						$(".accept_time_dian").removeClass("none");
						$(".orderyin1").addClass("none");
						$(".place").addClass("none");
						$("#todoorPrice").addClass("none");
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
					}else if(data.type1 == 2){
						$(".dian_type1").html("电票");
						$(".place").addClass("none");
						$("#todoorPrice").addClass("none");
						$(".accept_time_dian").removeClass("none");
					}
				}
				
				$(".place").html(data.place);
				$(".memberother").html(data.memberother);
				
				$("#disc_bind_id").val(data.dist_bind_id);
				
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
				$(".bank").html(data.bank);
				$(".endtime").html(data.endtime);
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
				if(data.flaw_ticket == 0){
					$(".flaw_ticket").removeClass("none");
				}
				
				if(data.accept_time == 1){
					$(".accept_time_dian").html("一年期");
				}else{
					$(".accept_time_dian").html("半年期");
				}
				
				
				$(".singleRate").html(((comment.singleRate)*100).toFixed(2));
				$(".endorseTime").html(getTime(comment.endorseTime));
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
			}else{
				alert("获取数据失败");
			}
		}
	};
	
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
		
		if(parseInt(allmoney*10000)<parseInt(money)){
			alert("扣息不能大于票面金额")
			$(".money").val("");
			$("#paid").html("");
			return ;
		}
		var receivables = ((allmoney*10000)-money);
		$("#paid").html(fmoney(receivables,2));
		$("#txje").val(receivables);
		$(".txlx").html((money/allmoney*10).toFixed(2));

		if(type1 == 1){
			$(".actualMoney").html(fmoney(receivables,2)+"元");
			$(".paid").html(fmoney(receivables,2)+"元");
		}else{
			
			$(".paid").html(fmoney(receivables,2)+"元");
			
			var params = {money:(allmoney*10000),txje:receivables};
			
			var disc_bind_id = $("#disc_bind_id").val();
			var draft_no = $("#draft_no").val();
			if(disc_bind_id !=null&&disc_bind_id!=""){
				params.draftNo = draft_no;
				params.bindId = disc_bind_id;
				
				$(".societeName").html("(含电子签章费)");
			}
			
			var url = '${bootAppPath}/dispatch/fee';
			var data = bootPost(url,params);
			
			if(data != null){
				if(data.data.response == 'success'){
					var appraisalFee = data.data.data;
					$("#appraisalFee").html(appraisalFee);
					var actualMoney = parseFloat(receivables) + parseFloat(appraisalFee);
					$("#actualMoney").html(fmoney(actualMoney,2));
					$(".actualMoney").html(fmoney(actualMoney,2)+"元");
				}else{
					alert(data.data.msg);
				}
			}
		}
		
	};

    $(function(){
        loadDate();
    });
	
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
	* 机构确认报价
	*/
	function confirmPrice(){
		var paid = $("#txje").val();
		var txlx = $(".money").val();
		
		var txje = parseInt(paid)/10000;
		var url ;
		
		if(order_type == "DISCOUNTRECORD"){
			url = '${bootAppPath}/distributeorder/update/txlx'
		}else{
			url = '${bootAppPath}/distributeordersp/update/txlx'
		}
		var params = {id:distId,txje:txje,txlx:txlx};
		
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
	
	//关闭按钮
	$("#closeBtn , .closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#codeWindow").addClass("none"); //发送验证码
	    $("#societeWindow").addClass("none")//兴业数金
	    $("#jdWindow").addClass("none")//兴业数金
	    $("#confirmPriceWindow").addClass("none");//确认订单
	    $("#couponWindow").addClass("none");//红包
	    $("#depositExplain").addClass("none");//红包
	    $("#zoomWindow").addClass("none");//放大的图片
	});
	
	/**
	* 确认报价的弹窗
	*/
	$("#confirmPrice").click(function(){
		if(!$(".money").validationEngine("validate")){
			$(".money").focus();
			return ;
		}
		
		var money = $("#money").html();
		var deposit = $(".actualPay").html();
		
		$("#title").html("确认报价");
		$("#maskWindow").removeClass("none");
		$("#confirmPriceWindow").removeClass("none");
	});
	
	/**
	* 查看兴业数金的手续费的计算方式
	*/
	$(".societe").click(function(){
		var bind_id = $("#disc_bind_id").val();
    	if(bind_id!=null&&bind_id!=""){
	    	$("#jdWindow").removeClass("none");//兴业数金
    	}else{
    		$("#societeWindow").removeClass("none");//兴业数金
    	}
    	$("#title").html("手续费计算公式");
		$("#maskWindow").removeClass("none");
	});
	
	/**
	* 保证金说明的弹窗
	*/
	$(".depositExplain").click(function(){
		$("#depositExplain").removeClass("none");
		$("#maskWindow").removeClass("none");
		$("#title").html("保证金说明");
	});
	
	/**
	* 展示放大的图片
	*/
	$("#picpath").click(function(){
		$("#zoomWindow").removeClass("none");
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