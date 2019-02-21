[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[#include "/common/data.ftl"]
[@main.body head=seoMap.mianze]
<style>
	.lh143{line-height: 143px}
</style>

<div class="w1150 bc bcwhite">
<div class="pl30 pr30 c595959 f16 lh26 pt40 pb50">
	<h1 class="f24 lh24 fb tc c2d2d2d mb30">票据应收款转让服务合同</h1>
	<p class="ti32">甲方（出票方）：<span id="bnsName"></span></p>
	<p class="ti32">法定代表人或负责人：<span id="bnsBizLicenceLegalName"></span></p>
	<p class="ti32">住所地：<span id="bnsBizLicenceAddr"></span></p>
	<br>
	<p class="ti32">乙方（收票方）：<span id="orgName"></span></p>
	<p class="ti32">法定代表人或负责人：<span id="orgBizLicenceLegalName"></span></p>
	<p class="ti32">住所地：<span id="orgBizLicenceAddr"></span></p>
	<br>
	<p class="ti32">交易订单号：<span id="no"></span></p>
	<p class="ti32">票号：<span id="draftNo"></span></p>
	<p class="ti32">承兑人全称：<span id="bank"></span></p>
	<p class="ti32">票面金额：<span id="allmoney"></span>(元)</p>
	<p class="ti32">交易金额（含手续费）：<span id="txje"></span>(元)</p>
	<br>
	<h2 class="f18 fb c2d2d2d mt16">鉴于：</h2>
	<p class="ti32">1、甲乙双方均为上海票管家金融服务有限公司（“票管家”）平台用户（“平台用户”），票管家是一家依托互联网金融创新技术，为广大票据供需双方提供专业、安全、便捷的信息服务和撮合交易的企业。甲乙双方均签署并同意票管家用户协议在内的全部平台交易规则。</p>
	<p class="ti32">2、兴业数字金融服务（上海）股份有限公司（“兴业数金”）为兴业银行集团面向中小银行、非银行金融机构、中小企业提供金融信息云服务的最主要平台，为兴业银行集团开展互联网金融科技创新的最主要平台。</p>
	<p class="ti32">3、为方便平台用户通过票管家平台线上服务系统办理票据应收款转让业务，票管家与兴业数金建立了合作关系。兴业数金将自主研发的“执剑人系统”与票管家平台交易系统端口对接，为在票管家平台依法合规开展票据流转业务的平台用户提供票据流转见证（包括票据基本信息、票据交易信息流转记录、交易明细）、票据流转资金代管、传递汇划指令等服务（“服务”）。</p>
	<p class="ti32">4、甲乙双方作为平台用户，同意兴业数金通过票管家平台向甲乙双方提供服务，并由兴业数金在兴业银行开立票据流转资金代管专户。甲乙双方自行对其提供给票管家平台/兴业数金的有关信息的真实性、充分性、可靠性、准确性、完整性和有效性负责。</p>
	
	<p class="ti32">为明确协议双方权利义务，甲乙双方根据《中华人民共和国合同法》《中华人民共和国票据法》及相关法律法规规定，秉承平等自愿、协作共赢的原则，经友好协商，达成如下协议内容：</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第一条 释义</h2>
	<p class="ti32">除非本合同上下文另有解释或文义另有所指，下列词语或简称具有如下含义：</p>
	<p class="ti32">一、甲方（出票方）：拟于票管家平台转让票据应收款的一方，甲方应为平台用户。</p>
	<p class="ti32">二、乙方（收票方）：拟于票管家平台受让票据应收款的一方，乙方应为平台用户。</p>
	<p class="ti32">三、票管家平台：票管家开发的，为票据交易双方提供交易撮合服务的互联网网站及交易系统。</p>
	<p class="ti32">四、代管专户：兴业数金为方便甲乙双方资金流转和安全而以其自身名义在兴业银行开立的票据流转资金代管专户。</p>
	<p class="ti32">五、票据应收款：票据承兑人承担的在票据到期日无条件支付的票据全部款项，包括但不限于票据付款人的付款、被追索人支付的金额和费用、出票人或承兑人返还的其余未支付票据金额相当的款项或利益、根据司法机关裁决或有权机关规定获得的任何形式的货币或者实物等。</p>
	<p class="ti32">六、票据应收款转让：甲乙双方通过票管家平台达成协议，甲方通过票据背书转让方式将票据应收款无条件不可撤销地转让给乙方的行为。</p>
	<p class="ti32">七、实扣金额：乙方实际应支付的款项，即票管家平台于乙方支付款项时从乙方账户里扣划至代管专户的款项，实扣金额=成交金额+转让服务费 。</p>
	<p class="ti32">八、成交金额：甲乙双方就票据应收款转让业务达成的成交金额 。</p>
	<p class="ti32">九、实收金额：票据应收款转让成功时自代管专户实际划入甲方收款账户的金额，实收金额=成交金额-提现费用。</p>
	<p class="ti32">十、转让服务费：兴业数金及/或兴业数金合作机构基于为甲乙双方提供票据应收款转让相关服务而向乙方单方收取的服务费。转让服务费包括服务费用、电子合同服务费及其他增值或综合费用（如有）。</p>
	<p class="ti32">十一、提现费用：兴业数金及/或兴业数金合作机构基于为甲乙双方提供票据应收款转让相关服务而向甲方单方收取的银行划转交易手续费。</p>
	<p class="ti32">十二、执剑人票据见证代管系统：由兴业数金研发，融合融资、见证、代管、支付四大功能为一体，为银行及金融机构、票据撮合平台及经纪、B2B电商平台、小微企业提供互联网票据业务综合服务的票据交易款安全代管系统。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第二条 票据应收款的转让</h2>
	<p class="ti32">甲方作为出票方，同意根据本合同约定的条款及条件通过票据背书转让方式向乙方转让相应的票据应收款，乙方作为收票方，同意根据本合同约定的条款及条件受让甲方合法持有的票据应收款并向甲方支付本合同约定的金额。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第三条 乙方转让款支付前提 </h2>
	<p class="ti32">如下条件全部获得满足的前提下，乙方有义务向甲方支付受让票据应收款的转让价款：</p>
	<p class="ti32">1、本合同已经签署并生效；</p>
	<p class="ti32">2、甲方转让的票据不存在任何争议、纠纷；</p>
	<p class="ti32">3、甲方已经将票据背书给乙方。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第四条 服务</h2>
	<p class="ti32">一、兴业数金及兴业数金合作机构根据票据应收款转让订单为甲乙双方提供服务。<span class="fb">甲乙双方无条件不可撤销地同意，当甲乙双方对票据应收款项下的票据信息确认一致时，即视为甲乙双方针对票据应收款转让的相关信息达成一致</span>：</p>
	<p class="ti32">1、甲方将拟转让的票据应收款项下的票据信息于票管家平台发布后，乙方对其进行报价，甲方同意实收金额及承担提现费用并确认交易后，订单生效，进入实际交易阶段。</p>
	<p class="ti32">2、乙方在有效时间内（乙方报价之时起30分钟内）成功提交有效代管资金支付指令和出金指令将实扣资金转入代管专户后，兴业数金将向甲方及票管家平台发出乙方实扣资金转入代管专户有效指令。</p>
	<p class="ti32">3、票管家平台收到兴业数金指令后，将对该笔转让订单显示“买方已付款，等待卖方确认背书”状态。</p>
	<p class="ti32">4、票据应收款在票管家平台上显示“买方已付款，等待卖方确认背书”状态后，甲方应当根据兴业数金执剑人票据见证代管系统及票管家平台的提示在有效时间（30分钟）内办理票据的背书手续。</p>
	<p class="ti32">5、甲方在有效时间（30分钟）内办理完毕票据背书的，乙方应在有效时间（30分钟）内点击确认完成签收操作。若乙方在有效时间（30分钟）内点击确认完成签收操作后，票管家平台对该笔转让订单自动显示为“买方确认签收”，则甲方可通过兴业数金执剑人票据见证代管系统发出指令将代管专户内数额等于实收金额的资金划入甲方指定入金关联账户。若乙方未在有效时间内点击确认完成签收操作，则视为乙方拒绝签收。</p>
	<p class="ti32">6、甲方未在有效时间（30分钟）内办理完毕票据背书手续的，乙方有权与票管家平台工作人员进行电话沟通：如沟通后乙方拒绝继续交易的，或票管家平台工作人员根据乙方指示与甲方沟通后，甲方明确拒绝确认本次票据背书的，则票管家平台有权在系统中取消订单；如经沟通后甲乙双方均同意继续本次交易的，则票管家平台有权在系统中修改订单状态，继续订单交易，甲方应当在额外有效时间（【30】分钟）内办理票据的背书手续，乙方应在额外有效时间（【30】分钟）内点击确认完成签收操作。</p>
	<p class="ti32">7、如代管专户内数额等于实收金额的资金划入甲方指定入金关联账户且甲乙双方用户均无违约行为的，票管家平台将在交易完成后【5】个工作日内将甲乙双方根据票管家用户协议等约定支付的履约保证金按原支付渠道无息退回甲乙双方账户。</p>
	<p class="ti32"> 如乙方未于有效时间内成功提交有效付款指令将资金转入代管专户，或乙方银行账户内资金不足实扣金额的，或乙方主动取消交易，或在有效时间内拒绝签收票据的，则票据应收款转让失败，票管家平台有权取消订单并将乙方根据票管家用户协议等约定支付的履约保证金直接划付至甲方账户。若乙方将资金转入代管专户，甲方未能根据前款约定在有效时间内完成票据背书，或甲方在票据背书后撤销背书、或甲方主动取消交易、或甲方在有效时间经过后同意背书而乙方拒绝与甲方继续交易的，则票据应收款转让失败，票管家平台有权取消订单并将甲方根据票管家用户协议等约定支付的履约保证金直接划付至乙方账户。</p>
	<p class="ti32">8、转让失败的，票管家平台取消订单，同时兴业数金代管专户内数额等于乙方实扣金额的资金退回乙方执剑人票据见证代管系统的电子帐户内，甲乙双方将无法对该笔票据应收款转让项下的订单进行任何操作。</p>
	<p class="ti32">二、票管家平台正常发起转让的时间段为银行工作日的9：00-16：30，在16：30后需要进行票据应收款转让见证审核的订单，将在次日进行。</p>
	<p class="ti32">三、为避免疑问，甲乙双方同意向兴业数金申请指定唯一的同名银行结算账户作为其出入金关联账户，甲乙双方在执剑人系统向代管专户开户银行提出的出入金指令必须是转入此关联账户或转出至此关联账户；甲乙任何一方如需增加或者变更出入金关联账户的，应向兴业数金提出书面申请并抄送票管家，兴业数金依据国家法律法规和政策规定审核确认后才能根据申请办理增加或变更手续。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第五条 款项支付</h2>
	<p class="ti32">一、本合同第三条之“票据应收款的转让款支付的前提条件”全部满足之后，乙方无条件不可撤销的同意兴业数金将乙方已转入代管专户中代管数额等于实收金额的资金通过代管专户转入甲方指定入金关联账户，并由票管家代为提取该笔业务的转让服务费，后续票管家将依据与兴业数金签署的合作协议将相应转让服务费划付至相关账户。</p>
	<p class="ti32">甲方无条件不可撤销的同意兴业数金将乙方已转入代管专户中代管数额等于提现费用的资金通过代管专户划入兴业数金合作机构指定账户，并将代管数额等于实收金额的资金通过代管专户转入甲方指定入金关联账户。</p>
	<p class="ti32">如因系统关闭或故障等特殊原因导致兴业数金无法在当日完成划款的，则兴业数金可于下一个工作日完成划款。</p>
	<p class="ti32">二、双方均确认及同意，甲方成交金额=乙方实扣金额-转让服务费</p>

	<h2 class="f18 fb c2d2d2d mt16">第六条 转让服务费及提现费用</h2>
	<p class="ti32">一、甲乙双方无条件不可撤销地同意，委托兴业数金通过其自主研发的执剑人票据见证代管系统平台为甲乙双方提供服务；甲乙双方知悉并同意，票管家及/或兴业数金可自行也可与兴业数金的合作机构联合提供金融科技及信息等方面的综合服务，同意转让服务费由乙方支付，票管家代为收取，后续票管家将依据与兴业数金签署的合作协议将相应转让服务费划付至相关账户；同意提现费用由甲方支付。</p>
	<p class="ti32">二、双方确认，转让服务费包括了兴业数金及/或兴业数金合作机构基于服务收取的相关费用。</p>
	<p class="ti32">三、转让服务费=服务费用+电子合同服务费+其他增值或综合费用，乙方同意根据本合同的约定支付转让服务费。同时，甲方同意根据本合同的约定支付提现费用。其中，</p>
	<p class="ti32">1、服务费用及提现费用根据交易金额按如下约定收取：</p>
	<br>
	<div class="flex flex-direction-column w bae0e0e0 lh35">
		<div class="flex-row w flex-justify-center bbe0e0e0 fb">费用分项表</div>
		<div class="flex-row flex-justify-end bbe0e0e0 pr50">单位：人民币（元）</div>
		<div class="flex-row w tc bbe0e0e0 bcf5f5f5">
			<div class="flex-col-2 bre0e0e0">序号</div>
			<div class="flex-col-2 bre0e0e0">项目</div>
			<div class="flex-col-2 bre0e0e0">描述</div>
			<div class="flex-col-2 bre0e0e0">内容</div>
			<div class="flex-col-2 bre0e0e0">费用</div>
			<div class="flex-col-2">支付方</div>
		</div>
		<div class="flex-row w tc bbe0e0e0">
			<div class="flex-col-2 bre0e0e0">一</div>
			<div class="flex-col-2 bre0e0e0">服务费用</div>
			<div class="flex-col-2 bre0e0e0">根据成交金额按比例收取</div>
			<div class="flex-col-2 bre0e0e0">根据成交金额按比例收取</div>
			<div class="flex-col-2 bre0e0e0">0.6BP(6元到300元)</div>
			<div class="flex-col-2">乙方</div>
		</div>
		<div class="flex-row w tc flex-alignItems-center">
			<div class="flex-col-2 bre0e0e0 lh143">二</div>
			<div class="flex-col-2 bre0e0e0 lh143">提现费用</div>
			<div class="flex-col-2 bre0e0e0 lh143">根据成交金额按比例收取</div>
			<div class="flex-col-2 bre0e0e0">
				<div class="flex-row w flex-direction-column">
					<div class="flex-row w flex-justify-center bbe0e0e0">10万以内(包括10万)</div>
					<div class="flex-row w flex-justify-center bbe0e0e0">10万到50万(包括50万)</div>
					<div class="flex-row w flex-justify-center bbe0e0e0">50万到100万(包括100万)</div>
					<div class="flex-row w flex-justify-center">100万以上</div>
				</div>
			</div>
			<div class="flex-col-2 bre0e0e0">
				<div class="flex-row w flex-direction-column">
					<div class="flex-row w flex-justify-center bbe0e0e0">10元</div>
					<div class="flex-row w flex-justify-center bbe0e0e0">15元</div>
					<div class="flex-row w flex-justify-center bbe0e0e0">20元</div>
					<div class="flex-row w flex-justify-center">0.002% 200元封顶</div>
				</div>
			</div>
			<div class="flex-col-2">
				<div class="flex-row w flex-direction-column">
					<div class="flex-row w flex-justify-center bbe0e0e0">甲方</div>
					<div class="flex-row w flex-justify-center bbe0e0e0">甲方</div>
					<div class="flex-row w flex-justify-center bbe0e0e0">甲方</div>
					<div class="flex-row w flex-justify-center">甲方</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<p class="ti32">2、乙方同意，协议双方使用兴业数金提供的电子合同线上签署服务的，电子合同服务费按照  伍  元/份的标准向乙方收取，电子合同服务包括电子合同线上签署服务、区块链存证服务。</p>
	<p class="ti32">3、业务合作过程中，兴业数金基于兴业银行集团优势及自身研发能力，为甲乙双方提供其他增值和综合服务的，该增值和综合服务相关费用具体收费标准和收费方式，由兴业数金和票管家另行通知，并向乙方收取。</p>
	<p class="ti32">四、乙方确认，乙方同时向代管专户划转成交金额及转让服务费。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第七条 甲方的承诺与保证</h2>
	<p class="ti32">一、甲方是一家根据中国法律设立的企业机构，自设立至今始终有效存续，且目前经营状况正常，具备签署和履行本合同项下全部权利和义务的民事权利能力和民事行为能力。</p>
	<p class="ti32">二、甲方签署并履行本合同项下的义务（包括但不限于向乙方转让票据应收款）已经得到了甲方内部充分的授权，所需的外部审批及同意（如有）亦已全部取得。</p>
	<p class="ti32">三、签署本合同是甲方的真实意思表示，签署和履行本合同不会导致违反适用于甲方的任何法律法规、规范性文件或甲方章程或类似文件，也不会违反其与任何第三方签订的任何协议或承诺；本合同生效后，将构成对甲方有法律约束力及执行力的义务约定。</p>
	<p class="ti32">四、甲方不存在任何可能对本合同履行产生重大不利影响的诉讼、仲裁、执行、申诉、复议、重组、破产、清算等程序及其他事件或情况。</p>
	<p class="ti32">五、票据应收款项下的票据为合法、真实、有效且不存在票据诈骗等违法犯罪情形，不属于风险票据、已挂失票据或公示催告票据，票据记载事项完整，甲方为该票据的合法持有人并享有完整的票据应收款。</p>
	<p class="ti32">六、票据为甲方背书受让的，甲方保证其直接前手的背书是真实有效的并对其真实性负责；票据为甲方以其他合法方式取得的，甲方保证其受让是合法有效的并对合法性负责。</p>
	<p class="ti32">七、甲乙双方之间除根据本合同转让票据应收款产生的权利及义务外，票据及票据应收款均不存在本合同未作披露的任何债务负担或者第三方的权利主张、抵消权、质押或其他负担，甲方享有的票据应收款及票据相关权益不存在本合同未作披露的法律上的障碍，且转让后票据应收款及其项下的票据上不存在本合同未作披露的任何债务负担或第三方的权利主张或抵消权。</p>
	<p class="ti32">八、本合同生效后，甲方无权也不得再做对票据应收款及其项下的票据进行除向乙方之外的任何第三方转让的行为或事项。</p>
	<p class="ti32">九、甲方向兴业数金及票管家平台提交的票据应收款信息、票据背书、账户信息等真实合法有效，甲方自行承担因其提供信息或者材料错误造成的一切损失和责任。</p>
	<p class="ti32">十、甲方已经授权票管家平台的合作机构通过电子商业汇票系统查询应收款项下票据的票面信息、背书记录、影像等相关信息，并无条件不可撤销同意委托兴业数金代管票据应收款转让项下的款项。</p>
	<p class="ti32">十一、票据应收款转让过程中，甲方所有操作必须依照兴业数金及票管家平台的提示进行并保障甲方联系方式的畅通，甲方自行承担因甲方操作失误造成的一切损失。</p>
	<p class="ti32">十二、甲方承诺放弃票据流转资金代管专户内的资金利息的任何权利。</p>
	<p class="ti32">十三、甲方承诺在票据交易流转中严格遵守《票据法》、《支付结算办法》等有关票据交易流转的全部法律法规及有关规定。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第八条 乙方的承诺与保证</h2>
	<p class="ti32">一、乙方是一家根据中国法律设立的企业机构，自设立至今始终有效存续，且目前经营状况正常，具备签署和履行本合同项下全部权利和义务的民事权利能力和民事行为能力。</p>
	<p class="ti32">二、乙方签署并履行本合同项下的义务已经得到了乙方内部充分的授权，所需的外部审批及同意（如有）亦已全部取得。</p>
	<p class="ti32">三、签署本合同是乙方的真实意思表示，签署和履行本合同不会导致违反适用于乙方的任何法律法规、规范性文件或乙方章程或类似文件，也不会违反其与任何第三方签订的任何协议或承诺；本合同生效后，将构成对乙方有法律约束力及执行力的义务约定。</p>
	<p class="ti32">四、乙方不存在任何可能对本合同履行产生重大不利影响的诉讼、仲裁、执行、申诉、复议、重组、破产、清算等程序及其他事件或情况。</p>
	<p class="ti32">五、乙方在票管家平台提交的票据应收款项下票据被背书账户、乙方名称等信息和材料真实合法有效，乙方承担因乙方提供的信息或材料错误造成的一切损失和责任。</p>
	<p class="ti32">六、乙方已经授权票管家平台的合作机构通过电子商业汇票系统查询应收款项下票据的票面信息、背书记录、影像等相关信息，并无条件不可撤销同意委托兴业数金代管票据应收款转让项下的款项。</p>
	<p class="ti32">七、票据应收款转让过程中，乙方所有操作必须依照兴业数金及票管家的提示进行并保障乙方联系方式的畅通，乙方自行承担因甲方操作失误造成的一切损失。</p>
	<p class="ti32">八、乙方承诺放弃票据流转资金代管专户内的资金利息的任何权利。</p>
	<p class="ti32">九、乙方承诺在票据交易流转中严格遵守《票据法》、《支付结算办法》等有关票据交易流转的全部法律法规及有关规定。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第九条 甲乙双方的特别承诺</h2>
	<p class="ti32">一、甲乙双方明确知悉票据可能存在多手背书、回头背书等背书情况，甲方背书之前的前手背书中可能存在无真实贸易背景和债务关系的背书情况，也可能存在前手已经背书至后手，但后手没有支付对价或者没有全部适当履行完成票据项下民事权利义务等方面的情况，从而影响甲方或乙方票据相关权利行使的情形，甲乙双方知悉由此产生的全部经济和法律后果并承诺，由此产生的全部经济和法律后果均由甲乙双方自行承担，与兴业数金、票管家平台及兴业数金、票管家平台的合作机构均无任何关系。</p>
	<p class="ti32">二、甲乙双方确认，票管家平台仅为本协议双方提供信息服务和撮合交易的服务，票管家平台未向协议任何一方收取任何服务费用，亦不就协议任何一方因本票据应收款转让业务遭受的任何损失承担责任。</p>
	<p class="ti32">三、如因兴业数金系统维护或升级的需要而需暂停网络服务、服务功能调整的，票管家将尽可能在网站上进行通告。票管家未进行通告，或者由于用户未能及时浏览通告信息而造成的损失，甲乙双方确认，票管家金融不承担任何责任。</p>
	<p class="ti32">四、甲乙双方同意并授权委托兴业数金对票据流转全部信息进行查询见证留存，对票据流转资金进行代管，知悉并同意兴业银行对票据流转相关资金进行划付；同时，知悉理解并同意兴业数金有权利用互联网+科技手段开展委托项下各类服务。</p>
	<p class="ti32">五、甲乙双方确认，本合同项下转让服务费、提现费用可能因国家法律法规、政策规定、兴业数金以及兴业数金合作机构等调整相关费用而产生变化，甲乙双方签署本合同即代表均已认可并接受该等转让服务费、提现费用的变更，并自愿承担相应的支付义务。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第十条 违约责任</h2>
	<p class="ti32">一、甲乙双方为完成票据应收款转让业务，已经根据用户协议等相关平台交易规则规定支付了履约担保金，如任何一方在票据应收款转让业务中出现了本合同第四条第7款之违约行为或违反了本合同约定的其他义务的，该等履约保证金根据票管家用户协议的约定由票管家平台直接划付至守约方账户，用于对守约方的赔偿；如该等履约保证金不足以支付守约方的全部直接损失的，违约方还有义务对不足部分予以赔偿。</p>
	<p class="ti32">若因任何一方违约行为造成合同主体以外的其他方损失，非违约方对合同主体以外其他方损失不承担任何责任。</p>
	<p class="ti32">二、如因甲乙双方的如下情形导致兴业数金任何损失的，由违约方自行向兴业数金承担违约及赔偿责任，与票管家无涉：</p>
	<p class="ti32">1、甲乙双方提供的信息和资料的真实性、完整性、有效性、合法性存在瑕疵；</p>
	<p class="ti32">2、甲乙双方与票据流转相关交易的合法性、合规性、有效性存在瑕疵；</p>
	<p class="ti32">3、乙方资金来源的合法性存在瑕疵；</p>
	<p class="ti32">4、乙方发出的划款指令超出资金代管专户限额；</p>
	<p class="ti32">5、票据流转交易项下票据非因兴业数金原因被采取冻结等限制措施影响甲乙任何一方票据权利行使或实现的；</p>
	<p class="ti32">6、其他因甲乙任何一方原因导致任何损失或责任的情形。</p>
	<p class="ti32">三、如因甲乙双方任何违约行为导致票管家平台任何损失的，违约方根据本合同及甲乙双方签署的票管家用户协议在内的全部平台交易规则就票管家平台全部损失承担赔偿责任。</p>
	<p class="ti32">四、甲乙双方确认，如因任何一方履行本合同产生侵权责任的，侵权方是该等责任的最终承担者。如被侵权方向票管家平台或兴业数金主张任何侵权赔偿责任的，实际侵权方应承担该等侵权赔偿责任，并确保票管家平台或兴业数金不因其侵权行为遭受任何损失或承担任何责任。</p>
	<p class="ti32">五、甲乙双方确认，任何一方违反本合同约定的，票管家平台或兴业数金均有权将其违约信息上报中国人民银行征信中心或者失信公示机构。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第十一条 争议解决</h2>
	<p class="ti32">一、本合同适用中华人民共和国法律（不含香港、澳门及台湾地区的法律），并依据其解释。</p>
	<p class="ti32">二、在合同履行过程中发生的争议，双方应以友好信任的精神协商解决；若争议发生三十（30）个工作日内仍未能通过协商解决时，应提交上海仲裁委员会，根据该会当时有效的仲裁规则进行仲裁，仲裁地点为上海，仲裁裁决是终局性的并对双方当事人具有约束力，仲裁费由败诉方承担。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第十二条 合同生效</h2>
	<p class="ti32">双方同意本合同满足以下任何一种形式即为生效：</p>
	<p class="ti32">一、本合同自甲乙双方根据电子签名相关法律法规线上签署之日生效。</p>
	<p class="ti32">二、本合同自甲乙双方通过票管家平台阅读并点击同意接受（包括勾选、点击确认等任一种方式）本合同后生效。</p>
	
	<h2 class="f18 fb c2d2d2d mt16">第十三条 其他事宜</h2>
	<p class="ti32">本合同未尽事宜，由双方协商并签订补充合同确定，补充合同与本合同具有同等法律效力。</p>
	<p class="ti32 fb c">甲乙双方均已通读本合同全部条款，甲方、乙方对各自在本合同项下的权利义务已经充分熟悉并理解，甲乙双方对本合同所有内容均无异议。</p>
	<br><br><br>
	<p class=""><div class="fl w_50 ti32">甲方：       （公司公章）</div>	乙方：       （公司公章）</p>
	<br><br><br>
	<p class="ti32 tr" id="time"></p>

</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	enableBootAuth();
	var buyout = '${buyout}';
	if(buyout != null && buyout){
		buyoutContract();
	}else{
		contract();
	}
});

/** 
 * 开启请求BOOT登录验证
 */
function enableBootAuth(){
	if('${member.mobile}'=='')return;
	
	var _USERNAME = '${member.mobile}';
	var _PASSWORD = hex_md5('${member.mobile}SIGN:@UTIEXIAN@50965066${member.id}');
	$(document).ajaxSend(function(e, xhr, options) {
		var _add = "_USERNAME=" + _USERNAME + "&source=PC&_PASSWORD=" + _PASSWORD;
		var _old = options.data;
		if($.trim(_old)==""){
			options.data = _add;
		}else{
			options.data += "&" + _add;
		}
	});
	$("form").append('<input type="hidden" name="_USERNAME" value="'+ _USERNAME +'" data-desc="登录校验"/>');
	$("form").append('<input type="hidden" name="_PASSWORD" value="'+ _PASSWORD +'" data-desc="登录校验"/>');
	$("form").append('<input type="hidden" name="source" value="PC" data-desc="标识来源"/>');
}

	var now = new Date();
	var fullyear = now.getFullYear();//获取完整的年份(4位,1970-????)
	var month = now.getMonth() + 1;//获取当前月份(0-11,0代表1月)
	var date = now.getDate();//获取当前日(1-31)
	
	if (month < 10) {
		month = "0" + month;
	}
	if (date < 10) {
		date = "0" + date;
	}
	$("#time").html(fullyear+"年"+month+"月"+date+"日");
	
	/**
	* 报价合同
	*/
	function contract(){
		var distId = '${distId}';
		var orderType = '${orderType}';
		var url = '${bootAppPath}/cib/contract/info/by/id';
		var params = {distId:distId,orderType:orderType};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$("#bnsName").html(data.data.data.bnsName);
				$("#bnsBizLicenceLegalName").html(data.data.data.bnsBizLicenceLegalName);
				$("#bnsBizLicenceAddr").html(data.data.data.bnsBizLicenceAddr);
				$("#orgName").html(data.data.data.orgName);
				$("#orgBizLicenceLegalName").html(data.data.data.orgBizLicenceLegalName);
				$("#orgBizLicenceAddr").html(data.data.data.orgBizLicenceAddr);
				$("#no").html(data.data.data.no);
				$("#draftNo").html(data.data.data.draftNo);
				$("#bank").html(data.data.data.bank);
				$("#allmoney").html(data.data.data.allmoney);
				$("#txje").html(data.data.data.txje);
			}else{
				alert(data.data.msg);
			}
		}	
	};
	
	/**
	* 一口价展示合同
	*/
	function buyoutContract(){
		var discId = '${discId}';
		var orderType = '${orderType}';
		var url = '${bootAppPath}/cib/contract/buyout/by/id';
		var params = {discId:discId,orderType:orderType};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				$("#bnsName").html(data.data.data.bnsName);
				$("#bnsBizLicenceLegalName").html(data.data.data.bnsBizLicenceLegalName);
				$("#bnsBizLicenceAddr").html(data.data.data.bnsBizLicenceAddr);
				
				$("#no").html(data.data.data.no);
				$("#draftNo").html(data.data.data.draftNo);
				$("#bank").html(data.data.data.bank);
				$("#allmoney").html(data.data.data.allmoney);
				$("#txje").html(data.data.data.txje);
			}else{
				alert(data.data.msg);
			}
		}	
	};
</script>
[/@main.body]