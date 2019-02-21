[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" href="https://static.utiexian.com/css/website/orderDetail.css">
[@main.header currentmenu='1'/]

<div class="ConDiv">
    <!--订单详情页：容器-->
    <div class="detailContainer">
        <!--头部信息-->
        <div class="detailHead">
            <div class="detailTitle">
                <h2 class="ce84c3d" id="title"></h2>
                <div class="detailLocation place none"><img src="https://img.utiexian.com/website/receive/position.png" alt="定位"><span id="place"></span></div>
            </div>
            <div class="detailDate">下单时间 <span class="begintime"></span></div>
        </div>

        <!--内容-->
        <div class="detailContent">

            <!--基本信息-->
            <!--银纸-->
            <div class="detailBasic yin_zhi none">
                <!--票据市场：回头票标志-->
                <div class="ticketReturn back_endorse none"><img src="${imagePath}/website/paperMarket/returnTicket.png" alt="回头票"></div>
                <!--票据各属性名-->
                <ul class="showTitle">
                    <li class="w240">银票订单号</li>
                    <li class="w155">票面金额</li>
                    <li class="w90">背书</li>
                    <li class="w173">到期日期</li>
                    <li class="w90">计息天数</li>
                    <li class="w90">保证金</li>
                    <li class="w310">票据特征</li>
                </ul>
                <!--票据各属性值-->
                <div class="showMain">
                    <div class="showLeft Rright">
                        <ul class="showLeftTop Rright">
                            <li class="w240 Rright ordernumber"></li>
                            <li class="w155 Rright ce84c3d money"></li>
                            <li class="w90 Rright endorse"></li>
                            <li class="w173 Rright endtime"></li>
                            <li class="w90 Rright jxts"></li>
                            <li class="w90 deposit"></li>
                        </ul>
                        <div class="showLeftBottom">
                            <p>备注：<span class="memberother"></span></p>
                        </div>
                    </div>
                    <!--票据特征-->
                    <ul class="showRight">
                        <li>纸票</li>
                        <li class="type2"></li>
                        <li class="none need_todoor">需要上门</li>
                        <li class="none flaw_ticket">瑕疵票</li>
                    </ul>
                </div>
            </div>

            <!--银电-->
            <div class="detailBasic yin_dian none">
                <!--票据市场：回头票标志-->
                <div class="ticketReturn back_endorse none"><img src="${imagePath}/website/paperMarket/returnTicket.png" alt="回头票"></div>
                <!--票据各属性名-->
                <ul class="showTitle">
                    <li class="w240">银票订单号</li>
                    <li class="w155">票面金额</li>
                    <li class="w200">到期日期</li>
                    <li class="w121">计息天数</li>
                    <li class="w122">保证金</li>
                    <li class="w310">票据特征</li>
                </ul>
                <!--票据各属性值-->
                <div class="showMain">
                    <div class="showLeft Rright">
                        <ul class="showLeftTop Rright">
                            <li class="w240 Rright ordernumber"></li>
                            <li class="w155 Rright ce84c3d money"></li>
                            <li class="w200 Rright endtime"></li>
                            <li class="w121 Rright jxts"></li>
                            <li class="w122 deposit"></li>
                        </ul>
                        <div class="showLeftBottom">
                            <p>备注：<span class="memberother"></span></p>
                        </div>
                    </div>
                    <!--票据特征-->
                    <ul class="showRight">
                        <li>电票</li>
                        <li class="type2"></li>
                        <li class="none flaw_ticket">瑕疵票</li>
                    </ul>
                </div>

            </div>

            <!--商票-->
            <div class="detailBasic shang none">
                <!--票据市场：回头票标志-->
                <div class="ticketReturn back_endorse none"><img src="${imagePath}/website/paperMarket/returnTicket.png" alt="回头票"></div>
                <!--票据各属性名-->
                <ul class="showTitle">
                    <li class="w240">商票订单号</li>
                    <li class="w155">票面金额</li>
                    <li class="w105">背书</li>
                    <li class="w216">承兑企业</li>
                    <li class="w122">保证金</li>
                    <li class="w310">票据特征</li>
                </ul>
                <!--票据各属性值-->
                <div class="showMain">
                    <div class="showLeft Rright">
                        <ul class="showLeftTop Rright">
                            <li class="w240 Rright ordernumber"></li>
                            <li class="w155 Rright ce84c3d money"></li>
                            <li class="w105 Rright endorse"></li>
                            <li class="w216 Rright bank"></li>
                            <li class="w120 deposit"></li>
                        </ul>
                        <div class="showLeftBottom">
                            <!--相关日期值-->
                            <div class="showDate Rright">
                                <div>开票日期：<span class="printtime"></span></div>
                                <div>到期日期：<span class="endtime"></span></div>
                                <div>计息天数：<span class="jxts"></span></div>
                            </div>
                            <div class="showNote">
                                <p>备注：<span class="memberother"></span></p>
                            </div>
                        </div>
                    </div>
                    <!--票据特征-->
                    <ul class="showRight">
                        <li class="type1"></li>
                        <li class="none need_todoor">需要上门</li>
                    </ul>
                </div>
            </div>
            <!--基本信息end-->

            <!--票据处理效率评分-->
            <div class="ScoreDiv none comments">
                <div class="successRate">成单率:<span class="singleRate"></span></div>
                <div class="endorseTime">确认背书时间:<span class="endorse_time"></span></div>
                <ul class="entScore">
                    <li>企业评分</li>
                    <li>价格真实：<span class="price"></span></li>
                    <li>服务态度：<span class="service"></span></li>
                    <li>确认效率：<span class="speed"></span></li>
                </ul>
            </div>
            <!--票据处理效率评分end-->

            <!--票面信息-->
            <div class="ticketPar">
                <!--模块标题-->
                <div class="modulTitle picpath none">票面</div>
                <!--票面-->
                <div class="ticketParCon">
                    <div class="ticketParTitle picpath none">票面</div>
                    <!--票面图片-->
                    <ul class="ticketParImg h200 tc picpath none">
                        <a href="" target="_blank" id="zoom"><img id="picpath" src="" alt="" class="wa h"></a>
                    </ul>
                    <!--票面注释-->
                    <div class="ticketParNotes prompt"></div>
                </div>
            </div>
            <!--票面信息end-->

        </div>
    </div>
</div>
[@main.footer/]
[/@main.body]
<script type="text/javascript">
var orderType = '${orderType}';
var discId = '${discId}';
var memberId ;
$(function(){
	loadOrderDetails();
	getComments();
});

/**
 * 加载订单的详细信息
 */
function loadOrderDetails(){	
	var url = '${bootAppPath}/out/order/details';
	var params = {orderType:orderType,discId:discId};
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		if (data.response == 'success') {
			console.log(data);
			if(data.data != null){
				if(data.data.order_type == "DISCOUNTRECORD"){
					$("#title").html("银票订单");
					if(data.data.type1 == 1){//纸票
						$(".yin_zhi").removeClass("none");
					}else if(data.data.type1 == 2){//电票
						$(".yin_dian").removeClass("none");
					}
				}else if(data.data.order_type == "DISCOUNTRECORDSP"){
					$("#title").html("商票订单");
					$(".shang").removeClass("none");
					$(".bank").html(data.data.bank);
					if(data.data.type1 == 1){//纸票
						$(".type1").html("纸票");
					}else if(data.data.type1 == 2){//电票
						$(".type1").html("电票");
					}
					
					$(".printtime").html(data.data.printtime);
				}
				
				$(".begintime").html(data.data.begintime);
				if(data.data.type1 == 1){//纸票
					if(data.data.place != null && data.data.place != ""){
						$(".place").removeClass("none");
						$("#place").html(data.data.place);
					}
					
					if(data.data.need_todoor == 1){
						$(".need_todoor").removeClass("none");
					}
					
					if(data.data.flaw_ticket == 0){
						$(".flaw_ticket").removeClass("none");
					}
				}
				
				$(".endorse").html(data.data.endorse+"手");
				$(".type2").html(getBank(data.data.type2));
				
				$(".ordernumber").html(data.data.ordernumber);
				$(".money").html(data.data.allmoney+"万元");
				$(".endtime").html(data.data.endtime);
				$(".jxts").html(data.data.jxts+"天");
				$(".deposit").html(data.data.deposit+"元");
				$(".memberother").html(data.data.memberother);
				if(data.data.back_endorse == "T"){
					$(".back_endorse").removeClass("none");
				}
				if(data.data.memberid != null){
					memberId = data.data.memberid;
				}
				if(data.data.picpath != null && data.data.picpath != ""){
					$(".picpath").removeClass("none");
					$("#picpath").attr("src","${bootPic}"+data.data.picpath);
					$("#zoom").attr("href","${bootPic}"+data.data.picpath);
				}
				if(data.data.orderflag == 1){
					$(".prompt").html("选择交易机构");
				}else if(data.data.orderflag == 3){
					$(".prompt").html("订单已完成");
				}else{
					$(".prompt").html("订单交易中");
				}
			}
		}else{
			alert(data.msg);
		}
	}
};

/**
 * 获取评分
 */
function getComments(){
	if(memberId == null || memberId == "") return ;
	var url = '${bootAppPath}/out/comments/get';
	var params = {memberId:memberId,role:1};
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			$(".comments").removeClass("none");
			var data = data.data.data;
			$(".singleRate").html((data.singleRate)*100+"%");
			$(".endorse_time").html(getTime(data.endorseTime));
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
</script>
