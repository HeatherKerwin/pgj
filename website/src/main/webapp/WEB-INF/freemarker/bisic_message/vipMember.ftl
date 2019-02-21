[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
[@main.header currentmenu='1'/]
<style>
    /*会员卡背景色*/
    .grad {
        background: -webkit-linear-gradient(left, #c6a877, #e3d0a7 70%); /* Safari 5.1 - 6.0 */
        background: -o-linear-gradient(right, #c6a877, #e3d0a7 70%); /* Opera 11.1 - 12.0 */
        background: -moz-linear-gradient(right, #c6a877, #e3d0a7 70%); /* Firefox 3.6 - 15 */
        background: linear-gradient(to right, #c6a877, #e3d0a7 70%); /* 标准的语法 */
    }
    /* 鼠标选择效果  */
    .cpH:hover{
        /*background: rgba(255,255,255,0.1);*/
        border: 1px solid #e84c3d;
        box-shadow: 2px 3px 3px #555;
    }
</style>

<!--我的会员-->
<div class="w1200 ha bc mt20 mb20">
    <!--左侧菜单-->
    [@main.left remark='1' /]
    <!--右侧内容 -->
    <div class="fl w997 hmin688 pb20 bcwhite bae0e0e0">
    	<div class="w997 h34 lh34 f14 bcfaf7f7">
            <div class="fl ml10">我的会员</div>
        </div>
	    <div class="flex hmin688 flex-direction-column pl50 pr50">
	    	<!-- 选择不同角色 -->
	    	<ul class="flex-row flex-direction-row h52 f16 c2d2d2d lh50 bae0e0e0 tc bcwhite orderTab">
	            <li class="w166 lh50 bre0e0e0 bbd43c33">
	                <input type="radio" name="role" value="0" checked id="redTab1" class="none"><a href="#" class="cd43c33"><label for="redTab1" class="dsb">出票方</label></a>
	            </li>
	            <li class="w166 lh50 bre0e0e0">
	                <input type="radio" name="role" value="1" id="redTab2" class="none"><a href="#" class="c2d2d2d"><label for="redTab2" class="dsb">收票方</label></a>
	            </li>
	        </ul>
	        
	        <!--VIP会员卡-->
	        <div class="flex-row flex-direction-column grad w h300 mt20 cwhite vip none">
	            <!--未加入会员-->
	            <div class="flex-row flex-direction-column h pl65 pr69 noVip none">
	                <div class="flex-row flex-direction-row flex-alignItems-center flex-justify-space-between w mt65">
	                    <div class="f36">加入即享VIP会员</div>
	                    <div class="flex-row ca1835f flex-alignItems-center f22 lh22">
	                        <img src="https://img.utiexian.com/website/PJGJ/redPackets/joinIcon.png" alt="加入我们" width="23" height="22" class="flex mr10">加入我们
	                    </div>
	                </div>
	                <div class="mt100 f18 lh30">票据管家会员仅可减免票据管家服务<br>费，兴业数金收取的服务费无法免除。</div>
	            </div>
	
	            <!--已加入会员-->
	            <div class="flex-row flex-direction-column w h pr tc isVip none">
	                <img src="https://img.utiexian.com/website/PJGJ/redPackets/vipIcon.png" class="pa left bottom" alt="vip" width="228" height="127">
	                <div class="f36 mt65">VIP 会员卡 <span>${hideMobile(member.mobile)}</span></div>
	                <div class="mt100 f20">您的会员将于 <span id="endDate"></span> 到期</div>
	            </div>
	        </div>
	
	        <!--VIP会员套餐-->
	        <div class="flex-row flex-direction-column w mt30 vipPackage none">
	            <div class="bbec2c2c2 lh40 f16 w">VIP会员套餐</div>
	            <div class="flex-row flex-direction-row flex-justify-space-around mt40">
	                <!--年卡会员-->
	                <label class="flex-row flex-alignItems-center flex-justify-center bae0e0e0 w240 h174 pr cp cpH" for="vipYear">
	                    <input type="radio" name="vip" value="3" id="vipYear" class="none">
	                    <div class="flex-row flex-direction-column tc">
	                        <div class="flex-row flex-direction-row flex-alignItems-center">
	                            <div class="f24 mr20">年卡会员</div>
	                            <div class="cbfbfbf f18">12个月</div>
	                        </div>
	                        <div class="f30 ce84c3d mt25">¥ <span class="f40 vipYear"></span></div>
	                    </div>
	                    <img src="https://img.utiexian.com/website/PJGJ/redPackets/hyczIcon.png" alt="" width="27" height="27" class="pa right bottom none">
	                </label>
	                <!--季卡会员-->
	                <label class="flex-row flex-alignItems-center flex-justify-center bae0e0e0 w240 h174 pr cp cpH" for="vipQuarter">
	                    <input type="radio" name="vip" value="2" id="vipQuarter" class="none">
	                    <div class="flex-row flex-direction-column tc">
	                        <div class="flex-row flex-direction-row flex-alignItems-center">
	                            <div class="f24 mr20">季卡会员</div>
	                            <div class="cbfbfbf f18">3个月</div>
	                        </div>
	                        <div class="f30 ce84c3d mt25">¥ <span class="f40 vipQuarter">300</span></div>
	                    </div>
	                    <img src="https://img.utiexian.com/website/PJGJ/redPackets/hyczIcon.png" alt="" width="27" height="27" class="pa right bottom none">
	                </label>
	                <!--月卡会员-->
	                <label class="flex-row flex-alignItems-center flex-justify-center bae0e0e0 w240 h174 pr cp cpH" for="vipMonth">
	                    <input type="radio" name="vip" value="1" id="vipMonth" class="none">
	                    <div class="flex-row flex-direction-column tc">
	                        <div class="flex-row flex-direction-row flex-alignItems-center">
	                            <div class="f24 mr20">月卡会员</div>
	                            <div class="cbfbfbf f18">1个月</div>
	                        </div>
	                        <div class="f30 ce84c3d mt25">¥ <span class="f40 vipMonth">120</span></div>
	                    </div>
	                    <img src="https://img.utiexian.com/website/PJGJ/redPackets/hyczIcon.png" alt="" width="27" height="27" class="pa right bottom none">
	                </label>
	
	            </div>
	        </div>
	
	        <!--会员特权-->
	        <div class="flex-row flex-direction-column w mt30 vipPrivilege none">
	            <div class="bbec2c2c2 lh40 f16 w">会员特权</div>
	            <div class="flex-row flex-direction-row flex-justify-space-around mt40">
	                <!--免服务费-->
	                <div class="flex-row flex-direction-column flex-justify-center tc bae0e0e0 w240 h174 cp cpH">
	                    <div class="f24 mb20">免服务费</div>
	                    <img src="https://img.utiexian.com/website/PJGJ/redPackets/mffwIcon.png" alt="免服务费" width="67" height="61" class="bc">
	                </div>
	                <!--身份标识-->
	                <div class="flex-row flex-direction-column flex-justify-center tc bae0e0e0 w240 h174 cp cpH">
	                    <div class="f24 mb20">身份标识</div>
	                    <img src="https://img.utiexian.com/website/PJGJ/redPackets/sfbsIcon.png" alt="身份标识" width="80" height="62" class="bc">
	                </div>
	            </div>
	        </div>
	
	        <!--钱包余额-->
	        <div class="flex-row flex-direction-row flex-alignItems-center flex-justify-space-between h80 bae0e0e0 pl30 pr30 mt30 none" id="accountMoney">
	            <div class="flex-row flex-direction-row f18">
	                <div>钱包余额</div>
	                <div class="ce84c3d f24 ml70"><span id="money"></span>元</div>
	            </div>
	            <a class="w138 h34 f18 lh34 ce84c3d bae84c3d cp tc" href="${basePath }/bisicmessage/deposit">去充值</a>
	        </div>
	
	        <!--确认支付-->
	        <div class="flex-row flex-direction-row-reverse flex-alignItems-end mt30 confirm none">
	            <div class="w160 h44 f20 lh45 br3 cwhite bce84c3d ml35 cp tc" id="confirmPay">确认支付</div>
	            <div class="ce84c3d f40 lh37 vipMoney"></div>
	            <div class="f24">总计：</div>
	        </div>
	
	    </div>
    </div>
    <div class="cb"></div>
</div>

[@main.right /]
[@main.footer/]
[/@main.body]

<script type="text/javascript">
	var memberId = '${member.id}';
	$(function(){
		loadDate();
		loadMoney();
		loadVipInfo();
	});
	
	/**
	* 加载用户是否为会员
	*/
	function loadDate(){
    	if(memberId == null || memberId == "")return;
    	var url = "${bootAppPath}/vipmember/get/by/memberid";
    	var role = $("input:radio[name='role']:checked").val();
    	if(role == null || role == "") role = 0;
    	var params = {memberId:memberId,vipType:role};
    	var data = bootPost(url,params);
    	console.log(data);
    	if(data != null){
    		if(data.data.response == 'success'){
				$(".vip").removeClass("none");
				if(data.data.data.vipMember != null){
					$(".noVip").addClass("none");
					$(".isVip").removeClass("none");
					
					$(".vipPrivilege").removeClass("none");
					$(".vipPackage").addClass("none");
					$("#accountMoney").addClass("none");
					$(".confirm").addClass("none");
					
					var date = new Date(data.data.data.vipMember.endDate);
					$("#endDate").html(date.format('yyyy-MM-dd'));
					
				}else{
					$(".isVip").addClass("none");
					$(".noVip").removeClass("none");
					
					$(".vipPrivilege").addClass("none");
					$(".vipPackage").removeClass("none");
					$("#accountMoney").removeClass("none");
					$(".confirm").removeClass("none");
				}
			}else{
				alert(data.data.msg);
			}
    	}
	};
	
	//选择不同角色
	$("input[name='role']").change(function () {
	    $(this).parents("ul").children("li").children("a").children("label").addClass("c2d2d2d").removeClass('cd43c33');
	    $(this).parents("ul").children("li").removeClass("bbd43c33");
	    $(this).parents("ul").children("li").children("label").removeClass("f20");
	    if ($(this).attr("checked") == "checked") {
	        $(this).parent("li").addClass("bbd43c33");
	        $(this).parent("li").children("a").children("label").removeClass("c2d2d2d").addClass('cd43c33');
	        loadDate();
	    }
	});
	
	/**
	* 选择会员的期限
	*/
	$("input[name='vip']").click(function(){
		$("input[name='vip']").each(function () {
		    $(this).parent("label").removeClass("bbd43c33").addClass("bae0e0e0");
		    $(this).next().next().addClass("none");
		    if ($(this).attr("checked") == "checked") {
		    	$(this).parent("label").addClass("bbd43c33");
		    	$(this).next().next().removeClass("none");
		    	$(".vipMoney").html($(this).next().children("div").children("span").html());
		    }
		});
	});
	
	/**
	* 确认支付，充值会员
	*/
	$("#confirmPay").click(function(){
		var vipId = $("input:radio[name='vip']:checked").val();
		if(vipId == null || vipId == ""){
			alert("请选择VIP会员套餐");
			return;
		};
		var paymoney = $(".vipMoney").html();
		var money = $("#money").html();
		var role = $("input:radio[name='role']:checked").val();
		
		if(role == 1){
			if(vipId == 1){
				vipId = 4;
			}else if(vipId == 2){
				vipId = 5;
			}else if(vipId == 3){
				vipId = 6;
			}
		}
		
		if(parseInt(money) < parseInt(paymoney)){
			alert("钱包余额不足，请先充值。");
			return;
		}
		
		$("#confirmPay").attr({"disabled":"disabled"});
		var url = '${bootAppPath}/vipmember/save';
		var params = {memberId:memberId,vipType:role,vipId:vipId};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == "success"){
				$("#confirmPay").removeAttr("disabled");
				alert("购买成功。");
				loadDate();
				loadMoney();
			}else{
				$("#confirmPay").removeAttr("disabled");
				alert(data.data.msg);
			}
		}else{
			$("#confirmPay").removeAttr("disabled");
		}
	});
	
	/**
	* 加载用户的账户余额
	*/
	function loadMoney(){
		var url = '${bootAppPath}/account/info/by/member';
		var params = {memberId:memberId};
		var data = bootPost(url,params);
		if(data != null){
			if(data.data.response == 'success'){
				if(data.data.data.account.money != null){
					$("#money").html(data.data.data.account.money);
				}else{
					$("#money").html(0);
				}
			}else{
				alert("获取数据失败");
			}
		}
	};
	
	/**
	* 加载会员信息
	*/
	function loadVipInfo(){
		var url = '${bootAppPath}/vip/list';
		var role = $("input:radio[name='role']:checked").val();
		var params = {vipType:role};
		var data = bootPost(url,params);
		console.log(data);
		if(data != null){
			if(data.data.response == "success"){
				var vip = data.data.data;
				for(var i=0;i<vip.length;i++){
					if(vip[i].id == 1 || vip[i].id == 4){
						$(".vipMonth").html(vip[i].money);
					}else if(vip[i].id == 2 || vip[i].id == 5){
						$(".vipQuarter").html(vip[i].money);
					}else if(vip[i].id == 3 || vip[i].id == 6){
						$(".vipYear").html(vip[i].money);
					}
				}
			}else{
				alert(data.data.msg);
			}
		}
	};
</script>