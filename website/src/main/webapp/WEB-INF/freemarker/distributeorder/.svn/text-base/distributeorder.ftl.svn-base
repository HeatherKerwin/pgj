[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家--银票接单详情']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='1' topindex="3"/]
 [@main.right /]
<!--银票理由弹窗-->
<div class="w h pf bc05 zi10 top none" id="reason">
    <div class="w800 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose"></div>

        <div class="w792 h600 pr t4 l4 bcf5f5f5 zi12">
            <div class="h20"></div>
            <div class="ml25 f16 lh40">
                <div class="fl w184 c7d7d7d">请选择取消理由：</div>
                <select class="fl w320 h40 select320 ti8" id="cancel1">
                    <option value="1">额度不够</option>
                    <option value="2">票据有问题</option>
                    <option value="3">不在业务范围内</option>
                    <option value="4">银行大额支付系统已关闭</option>
                    <option value="5">其他</option>
                </select>
            </div>
            <div class="cb"></div>
            <!-- 其他-->
            <textarea placeholder="请您输入不少于十五字的理由！" type="text" class="w500 h210 bae0e0e0 mt20 ml209 ti8 pt15 none" maxlength="140" id="reason1_div" style='resize: none;'></textarea>
            <div class="cb"></div>
            <!--票据有问题-->
            <div class="ml25 mt16 none" id="reason2_div">
                <div class="mt16 f16 lh40">
                    <div class="fl w184 c7d7d7d">请选择验票失败理由：</div>
                    <select class="fl w320 h40 select320 ti8" id="cancel2">
                        <option value="1">票面信息不完整</option>
                        <option value="2">票据不真实</option>
                        <option value="3">出票人印章与出票人不符合</option>
                        <option value="4">印章不清晰</option>
                        <option value="5">被背书人填写不正确</option>
                    </select>
                </div>
                <div class="cb"></div>
                <div class="c7d7d7d mt16">上传验票失败凭证<span class="ml20 cd43c33">(最多上传6张)</span></div>
                <div class="w750 h365 mt16 bae0e0e0 bcwhite">
                    <div id="addImg1" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg1','picpath1')"></div>
                        <img id="img1" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath1" style="display:none;"/>
                    </div>
                    <div id="addImg2" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg2','picpath2')"></div>
                        <img id="img2" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath2" style="display:none;"/>
                    </div>
                    <div id="addImg3" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg3','picpath3')"></div>
                        <img id="img3" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath3" style="display:none;"/>
                    </div>
                    <div id="addImg4" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg4','picpath4')"></div>
                        <img id="img4" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath4" style="display:none;"/>
                    </div>
                    <div id="addImg5" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg5','picpath5')"></div>
                        <img id="img5" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath5" style="display:none;"/>
                    </div>
                    <div id="addImg6" class="fl w200 h144 mt20 ml25 pr bae0e0e0 none">
                        <div class="w20 h20 pa t-10 l190 zi11 closeImg" onclick="deleteImg('addImg6','picpath6')"></div>
                        <img id="img6" src="" class="w200 h144 pr top left b0">
                        <input type="hidden" name="picpath" id="picpath6" style="display:none;"/>
                    </div>
                    <div id="images" class="fl mt20 ml25 pr">
                        <p><img width="200" height="144" src="${image_url}/website/receive/phone2.png" onclick="fileSelect();"></p>
                        <div class="wa">
		                	<input type="file" name="fileToUpload" id="fileToUpload" class="fl w200 h24">
		                    <input type="button"  class="fl w50 h24 ml15" value="上传" id="" onclick="fileSelected('img','picpath','fileToUpload');" >
		                    <input type="hidden" class="fl" name="picpath" id="picpath"  style="display:none;">
		                    <div class="cb"></div>
		                </div>
                    </div>
                    <div class="cb"></div>
                </div>
            </div>
            <input type="button" value="确认" id="cancelButton" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b20 l_50 ml-130" onclick="cancelDtbo()">
        </div>
    </div>
</div>
	<!--贴现输入表单-->
	<div class="f14 mt16 w1150 bc bcwhite bae0e0e0 mt20 mb80 pl25 pr25">
	 <div id="allmap" class="none" style="width:100%;height:400px;"></div>
	    <div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0">
	        待接单
	    </div>
	    
	    <input type="hidden" value="${member.id}" id="memberId" >
	    <div class="fb lh180 cblack mt30 bbe0e0e0">
	        确认贴现地址
	    </div>
	    <div class="mt30 c2d2d2d">
	        <div class="fl" id="address" > </div>
	        <div class="fl c808080 ml20" id="membermobile" ></div>
	        <div class="fl 808080 ml60">
	            <i class="fl w9 h14 position"></i>
	            <div class="fl ml8" id="distance"></div>
	        </div>
	        <div class="fl cd43c33 ml20 SHANGMEN none">企业要求上门</div>
	        <div class="fl cd43c33 ml20 NOSHANGMEN none">企业未要求上门</div>
	    </div>
	    
	    <div class="SHANGMEN none">
	    <div class="cb"></div>
		    <div class="mt20 lh27">
		        <div class="fl">请填写上门费用：</div>
		        <input id="todoorPrice" type="text" name="doorprice" class="fl w70 h27 bae0e0e0 lh27 tc bc0 validate[required,custom[integer]]" placeholder="0">
		        <div class="fl ml10">元</div>
		    </div>
		    <div class="cb"></div>
		    <div class="mt20 lh27">
		        <div class="fl">请预估上门时间：</div>
		        <select id="todoorTime" class="fl w148 h29 select1 ti8" selectedindex="0">
		            <option class="" value="0">2小时以内</option>
		            <option class="" value="1">4小时以内</option>
		            <option class="" value="2">6小时以内</option>
		            <option class="" value="3">8小时以内</option>
		            <option class="" value="4">8小时以上</option>
		        </select>
		    </div>
	    </div>
	    
	    <div class="cb"></div>
	    <div class="fb lh180 cblack mt30 bbe0e0e0">
	        确认订单信息
	    </div>
	    <div class="mt30 bae0e0e0 mb20">
	        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
	            <div class="fl w232">银票订单号</div>
	            <div class="fl w155">总金额</div>
	            <div class="fl w105">背书</div>
	            <div class="fl w212">付款行</div>
	            <div class="fl w122">保证金</div>
	            <div class="fl w312">票据特征</div>
	        </div>
	        <div class="h190 bcwhite bbe0e0e0 pt10 pb25">
	            <div class="fl w827 h190 pl10 Rright">
	                <div class="bbe0e0e0 tc f16 h50 lh40">
	                    <div class="fl w220 Rright h40" id="ordernumber">
	                        
	                    </div>
	                    <div class="fl w155 Rright h40 cd43c33" id="allmoney"></div>
	                    <div class="fl w105 Rright h40" id="endorse"></div>
	                    <div class="fl w210 Rright h40" id="bank"></div>
	                    <div class="fl w120 h50 h40 cd43c33" id="deposit"></div>
	                    <input type="hidden" id="depositHide"/>
	                </div>
	                <div class="cb"></div>
	                <div class="tc f16 pt25 h90">
	                    <div class="fl w220 Rright h90 tl">
	                        <div class="ml30" id="begintime"></div>
	                        <div class="ml30 mt20" id="endtime"></div>
	                        <div class="ml30 mt20" id="jxts"></div>
	                    </div>
	                    <div class="fl pl40 h90">
	                        <div class="fl w50 h90">备注：</div>
	                        <div class="fl w460 h90 tl" id="memberother">
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="fl w300">
	                <div class="tc lh35">
	                    <div  class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="type1"></div>
	                    <div  class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="flaw_ticket"></div>
	                    <div  class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="type2"></div>
	                    <div  class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25 " id="needTodoor"></div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!--价格微调-->
	    <div class="mt20 bae0e0e0 pl10 pb25">
	        <div class="mt20">
	            <div class="fl tl w148 fb">今日参考贴现价格：</div>
	            <p class="fl" id="priceShow"></p>
	        </div>
	        <input type="hidden" id="way"/>
	        <div class="cb"></div>
	        <div class="mt25">
	            <div class="fl tl w148 fb mt4">您可以微调价格：</div>
	            <!-- -->
	            <div id="way1" class="none fl TXjj2 w158 h28 mr10">
	                <div class="reduce fl w20 h2 ml5" onClick="setAmount2.reduce('#price2')" >
	                    <img src="${image_url}/website/receive/jian100.png" class="w25 h12 dsb mt8 vm"/>
	                </div>
	                <input type="text" name="qty_item_1" value="1920" id="price2" onKeyUp="setAmount2.modify('#price2')" class="fl b0 bcn f14 lh27 tc w80 h27 ml5" />
	                <p class="fl w16 lh27">元</p>
	                <div class="add mt6 ml5 fl" onClick="setAmount2.add('#price2')" >
	                    <img src="${image_url}/website/receive/jia100.png" class="w25 h12" />
	                </div>
	            </div>
	            <!-- -->
	            <div id="way0" class="none fl TXjj3 W200 h28 mr10">
	                <div class="reduce fl w37 h2" onClick="setAmount3.reduce('#price')" >
	                    <img src="${image_url}/website/receive/jian01.png" class="w21 h12 dsb ml15 mt8 vm"/>
	                </div>
	                <input type="text" name="qty_item_13" value="0.58" id="price" onKeyUp="setAmount3.modify('#price')" class="fl b0 bcn f14 lh27 tc w80 ml15 h27" />
	                <p class="fl w14 lh27">%</p>
	                <div class="add fl" onClick="setAmount3.add('#price')" >
	                    <img src="${image_url}/website/receive/jia01.png" class="w21 h12 dsb ml15 mt8" />
	                </div>
	                <input type="hidden" id="price1"/>
	            </div>
	            <p id="price1Show" class="none fl lh27">+ 50 元</p>
	        </div>
	        <div class="cb"></div>
	    </div>
	    <!--票面-->
	    <div class="bae0e0e0 mt20 pb25" id="piaomian">
	        <div class="pl10 h50 lh50 c717583 bcf9f9f9 bbe0e0e0">票面</div>
	        <div id="FRONT" ></div>
	    </div>
	    <!--支付方式-->
	    <div class="bae0e0e0 mt20 pl10 bcf9f9f9 h50 f16 lh50">
	       <!--  <div class="fl ">保证金支付方式：</div>
	        <p class="fl">支付宝</p> -->
	        <div class="fl ">保证金支付方式：</div>
	        <!--<p class="fl">支付宝</p>-->
	        <input type="radio" name="payWay" id="payWay1" class="fl w12 h12 mt20 ml20 mr5 TXradio radio2" checked="checked" value="3">
	        <label for="payWay1"><img src="http://img.utiexian.com/website/img/logo-kuaiqian.png" class="fl w60 mt10"/></label>
	        <input type="radio" name="payWay" id="payWay2" class="fl w12 h12 mt20 ml30 mr5 TXradio radio2" value="4">
	        <label for="payWay2"><img src="http://img.utiexian.com/website/baofoo/baofu-logo.png" class="fl w85 mt10"/></label>
	        <p class="f12 cd43c33 ml100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：由于各银行退款时间差异，保证金的返还可能有延迟，请您耐心等待！</p>
	    </div>
	    <div class="cb"></div>
	    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20 mb165">
	        <div id="okDtbo" class="fr cd43c33 bad43c33 br3 dsb w238 h45 lh45 mr40 cp">
	            接受订单<span id="minute_show">(10分00秒)</span>
	        </div>
	        <div id="" class="fr c2d2d2d bae0e0e0 br3 dsb w166 h45 lh45 mr40 cp" onclick="tan()">取消订单</div>
	    </div>
	    <div class="cb"></div>
	</div>
	<input id="shddh" class="none" name="shddh"  />
<!--支付弹窗-->
<section class="w h pf bc05 zi10 top none" id="window">
    <div class="w758 h608 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w750 h600 pr t4 l4 bcf5f5f5 zi12">
            <!--评价-->
            <div class="w tc">
                <div class="f30 fb w pt50">温馨提示</div>
                <div class="f24 mt100 lh50">请您在新打开的支付页面上进行支付。<br>支付完成前，请不要关闭该窗口。</div>
                <div class="w268 bc mt50">
                    <input type="button" id="paysuccess" class="w268 h45 lh45 cwhite br5 b0 bcfc4d42 bc mt25 f18" value="订单跟踪">
                </div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="${comPath}/ajaxfileupload.js"></script>
<script type="text/javascript" src="${jsPath}/distance.js"></script>	
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=RZNGEivCrVHp06sXAM6rxlquUSOLB3xx&s=1"></script>
<script type="text/javascript">
	var _deState;
	var _state;
	
	var longitude = null;//"116.331398";//经度
	var latitude =null;// "39.897445";//纬度
	var map = null;
	
	var _longitude = null;//贴现经度
	var _latitude = null;//贴现纬度
	
	var currentState = null;//当前银票订单状态
    var timeOut = false;//接单时间限制
    var needTodoor= null;
    var todoorPrice = "";
	var todoorTime = "";
	var no="${no}";
	$(document).ready(function(){
		loaddata();
		
		wwwPath = "${basePath}";
		actionLog(wwwPath,"action_97");//接单按钮统计
		map = new BMap.Map("allmap");
		var point = new BMap.Point(longitude,latitude);
		map.centerAndZoom(point,12);// 初始化地图,设置中心点坐标和地图级别
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				map.addOverlay(mk);
				map.panTo(r.point);
				setLngLat(r.point);
			}else {
				alert('failed'+this.getStatus());
			}
		},{enableHighAccuracy: true});
		
		
		
		$("#okDtbo").on("click",function(){
            okDtbo();
        });
		$("#cancelDtbo").on("click",function(){
            cancelDtbo();
        });
		
		$("input[name='doorprice']").keyup(function(){
	        var temp = $(this).val();
	        $(this).val(temp.replace(/\-/g,''));
	    });
		
		$("#redClose").click(function(){
			$("#reason").addClass("none");
		});
	})

	/**
     * 设置经纬度
	 */
	function setLngLat(point){
		longitude = point.lng;//经度
		latitude = point.lat;//纬度
		getDistance();
	}
	
	/**
     * 初始化数据
	 */
	function loaddata(){
		$.ajax({
				url: '${basePath}/distributeorder/get/wait',
				type: 'POST',
				data: {'no':"${no}"},
				dataType: 'json',
				success: function(result){
					var data = eval(result);
					if(data.response == "success"){
						var result=data.data;
						_deState=result.depositState;
						_state=result.state;
						if(_deState==1||_state!=1){
							alert("页面已过期");
							window.location.href="${basePath}/hall/index";
							return;
						}
						_timer(result.timer);
						$("#address").text(result.address);
						$("#membermobile").text(result.membermobile);
						$("#ordernumber").text(result.ordernumber);
						$("#allmoney").text(result.allmoney+"万元");
						if(result.endorse!=null) $("#endorse").text(result.endorse+"手");
						
						if(result.bank!=null) $("#bank").text(result.bank);
						$("#deposit").text(result.deposit+"元");
						$("#depositHide").val(result.deposit);
						if(result.begintime!=null) $("#begintime").text("贴现日期："+result.begintime);
						if(result.endtime!=null) $("#endtime").text("到期日期："+result.endtime);
		                if(result.jxts!=null) $("#jxts").text("计息天数："+result.jxts);
		                if(result.memberother!=null) $("#memberother").text(result.memberother);
						if(result.type1==1){
							$("#type1").text("纸票");
						}else{
							$("#type1").text("电票");
						}
						if(result.type2==1){
							$("#type2").text("国股");
						}else if(result.type2==2){
							$("#type2").text("小商");
						}else if(result.type2==3){
							$("#type2").text("外资");
						}else if(result.type2==4){
							$("#type2").text("农商");
						}else if(result.type2==5){
							$("#type2").text("农合");
						}else if(result.type2==6){
							$("#type2").text("农信");
						}else if(result.type2==7){
							$("#type2").text("村镇");
						}else{
							$("#type2").text("大商");
						}
						needTodoor = result.need_todoor;
					    if(needTodoor==1){//需要上门
							$("#needTodoor").text("需要上门");
							$(".SHANGMEN").removeClass("none");
							$(".NOSHANGMEN").addClass("none");
						}else if(needTodoor==0){//不需要上门
							$("#needTodoor").hide();
							$(".NOSHANGMEN").removeClass("none");
							$(".SHANGMEN").addClass("none");
						}else{
							alert("上门获取异常！！");
						}
					    if(result.flaw_ticket==0){
							$("#flaw_ticket").text("瑕疵票");
						}else{
							$("#flaw_ticket").hide();
						}
					    
					    var way = result.way;
					    if(way==null) way=0;//默认利率报价
		                $("#way").val(way);
		                if(way==0){//方式A：月利率+参数
		                    $("#way0").removeClass("none");
		                    var html = result.price;
		                    if(1==result.type1){//纸票
		                         html += "‰";
		                    }else{
		                        html += "%";
		                    }
		                    if(result.price1!=null){
		                    	$("#price1Show").removeClass("none");
		                        html += "&nbsp;+"+result.price1+"元";
		                        $("#price1").val(result.price1);
		                        $("#price1Show").text(result.price1+"元");
		                    }
		                    $("#priceShow").html(html);
		                    $("#price").val(result.price);
		                }else if(way==1){//方式B：每十万贴现成本
		                    $("#way1").removeClass("none");
		                    var html = result.price2+"元";
		                    $("#priceShow").html(html);
		                    $("#price2").val(result.price2);
		                }
					    
		                //票面
		                var _img = result.picpath;
		                if(_img!=null && $.trim(_img)!=""){
		                    var _arr = _img.split(",");
		                    var _imgs = "";
		                    if(_arr!=null && _arr.length>0){
		                        for(var i=0;i<_arr.length;i++){
		                            var temp = _arr[i];
		                            if(temp!=null && $.trim(temp)!=""){
		                            	_imgs += '<img src="${imagePath}'+temp+'" class="w860 h230 bc mt25 ml143"/>';
		                            }
		                        }
		                        $("#FRONT").html(_imgs);
		                        $("#piaomian").removeClass("none");
		                    }
		                }else{
		                	$("#piaomian").addClass("none");
		                }
		                _longitude = result.longitude;
		                _latitude =	result.latitude;
		                getDistance();
		                
					}
					else{
						alert(data.msg);
					}
					
				}
			});
	
	}
	
    function getDistance(){
    	if(_longitude!=null && latitude!=null){
			var distance = _myGetDistance(_latitude, _longitude, latitude, longitude);
			$("#distance").text("距离您"+distance.toFixed(2) +"公里");
		}
    }
	
	/**
     * 接单 
     */
    function okDtbo(){
        if(timeOut)return;//接单超时
        var deposit = $("#depositHide").val();
        var payWay = $("input:radio[name='payWay']:checked").val();//支付方式
        if(needTodoor==1){
        	var todoorPrice = $("#todoorPrice").val();
        	var todoorTime = $("#todoorTime").val();
        	if(!$("#todoorPrice").validationEngine("validate")){
        		$("#todoorPrice").focus();
        		setTimeout(function(){$("#todoorPrice").validationEngine('hideAll');},2000);
        		return;
        	}
        }
        var way=$("#way").val();
        var price=$("#price").val();
        var price1=$("#price1").val();
        var price2=$("#price2").val();
        
        var alert = "您确定要接受该订单吗？";
        if(deposit!=0)alert += "接受后将跳转至支付";
        var conf = confirm(alert);
        if(conf==true) {
        	$.ajax({
        		url: '${basePath}/distributeorder/accept',
				type: 'POST',
				async: false, 
				data: {'no':"${no}",'bail':deposit,'payWay':payWay,'longitude':longitude,'latitude':latitude,'todoorPrice':todoorPrice,'todoorTime':todoorTime,'way':way,'price':price,'price1':price1,'price2':price2},
				dataType: 'json',
				success: function(result){
					var data = eval(result);
					if(data.response == "success"){
						 var deposit = $("#depositHide").val();
						 if(deposit==0){//不支付保证金
							 window.location.href="${basePath}/distributeorder/hall";
						 }else{
							 $("#window").removeClass("none");
							 $("#shddh").val(data.bnsNo);
							 if(payWay==3){
								 window.open("${basePath}/distributeorder/bill99pay?deposit="+deposit+"&out_trade_no="+data.bnsNo);
							 }else if(payWay==4){
								 window.open("${basePath}/baofoo/pay?orderNo="+data.bnsNo+"&type=2");
							 }
						 }
					}else if (data.response == 'failed') {
		                  var conf=confirm(" 操作失败，请稍候再试!");
		                  if (conf==true){
		                	  window.location.href="${basePath}/distributeorder/hall";
		                  }else{
		                	  return;
		                  }
		             };
		         }	
        	});
        }else{
        	return;
        } 
    }
    
	/**
     * 倒计时 
     */
    function _timer(intDiff){
        var _t = window.setInterval(function(){
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
            $('#minute_show').html(minute+'分'+second+'秒');
            if(intDiff<=0){
                clearInterval(_t);
                timeOut = true;//接单时间限制
            }
            intDiff--;
        }, 1000);
    }
	/**
     * 删除图片
     */
    function deleteImg(addImg,picpath,num){
    		$("#"+addImg).addClass("none");
    		$("#"+picpath).val("");
    		$("#images").removeClass("none");
    }
	/**
     * 上传图片
     */
    function fileSelected(targetId, targetInputId,uploadInputId,images) {	
    	var uploadId="ryUpload";	
    	if(uploadInputId==undefined){
    		var fileName=jQuery("#wokeUpload").val();
    	}else{
    		var fileName=jQuery("#"+uploadInputId).val();
    		uploadId=uploadInputId;
    	}
    	
    	var strtype=fileName.substring(fileName.length-3);
    	strtype=strtype.toLowerCase();
    	if (strtype!="jpg"&&strtype!="gif"&&strtype!="png"){
    		alert("请上传JPG、GIF、PNG格式的图片！");
    		return false;
    	}
    	$.ajaxFileUpload({
    		url : '${basePath}/uploadpic',
    		secureuri : false,
    		dataType : 'json',
    		data : {
    		},
    		fileElementId : uploadId,
    		success : function(data) {
    			if(data=="error"){
    				alert("上传失败！");
    			}else{
    				for(var i=1;i<=6;i++){
    					if($("#picpath" + i).val()=="" || $("#img" + i).attr("src")==null){
    						$("#img" + i).attr("src", data)==null;
    						$("#picpath" + i).val(data);
    						$("#addImg" + i).removeClass("none");
    						return;
    					}
    					if(i==5){
    						$("#images").addClass("none");
    					}
    				}
    			}
    		},
    		error : function() {
    			alert("上传失败！");
    		}
    	});
    }

	/**
     * 银票拒绝订单（或验票失败）
     */
    function cancelDtbo(){
    	$("#cancelButton").attr("disabled","disabled");
        var reason = $("#cancel1").find("option").not(function(){ return !this.selected }).text();//拒绝原因（文本）
        var lostCause = "";//其他理由
        var cancel1 = $("#cancel1").val();
        var cancel2 = $("#cancel2").val();
        
        var images = '';
        $("input[name='picpath']").each(function(i,o){
        	if(images!='')images+=",";
        	images+=$(o).val();
        });
        if(cancel1==2){//票据有问题
            if(images==null || images==""){
                alert("请上传图片");
                $("#cancelButton").removeAttr("disabled");
                return;
            }
            reason = $("#cancel2").find("option").not(function(){ return !this.selected }).text();
        }else if(cancel1==5){//其他
            lostCause = $("#reason1_div").val();
            reason = lostCause;
            cancel2 = null;
            if (lostCause.length < 15) {
                alert("请你输入不少于15字的理由");
                $("#cancelButton").removeAttr("disabled");
                return;
            }
        }else{
            cancel2 = null;
            lostCause = "";
        }
        var data="no="+no+"&cancel1="+cancel1+"&cancel2="+cancel2+"&reason="+
        			reason+"&lostCause="+lostCause+"&currentState="+currentState+"&images="+images;
        $.ajax({
        	url:"${basePath}/distributeorder/cancel",
        	type:"POST",
        	data:data,
        	dataType:"json",
        	success:function(data){
        		if (data.response == 'success') {
        			alert("取消成功!");
        			$("#reason").addClass("none");
        			$("#cancelButton").removeAttr("disabled");
        			window.location.href="${basePath}/hall/index";
                }else{
                	$("#cancelButton").removeAttr("disabled");
                    alert(data.msg);
                }
        	},
        	error:function(data){
        		$("#cancelButton").removeAttr("disabled");
        	}
        });
    }
  
	/**
     * 银票拒绝订单理由显示隐藏
     */
    function tan(){
    	initData();
    	$("#reason").removeClass("none");
    }
	
	/**
     * 银票填写理由 
     */ 
    $("#cancel1").change(function(){
        var value = $(this).val();
        if(value==2){
            $("#reason1_div").addClass("none");
            $("#reason2_div").removeClass("none");
        }else if(value==5){
            $("#reason1_div").removeClass("none");
            $("#reason2_div").addClass("none");
        }else{
            $("#reason1_div").addClass("none");
            $("#reason2_div").addClass("none");
        }
    });
	
	/**
     * 取消时加载其订单状态
     */ 
    function initData(){
    	$.ajax({
    		url:"${basePath}/distributeorder/get",
    		data:{"no":no},
    		dataType:"json",
    		success:function(data){
    			currentState = data.data.state;
    		}
    	});
    }
	
    /**
	 *  点击交易跟踪
	 */
	 $("#paysuccess").click(function(){
	 	location.href = "${basePath}/distributeorder/list";
	 });
</script>
[@main.footer/]
[/@main.body]
