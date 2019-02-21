[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家--商票接单详情']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/receive.css"/>
[@main.header currentmenu='1' topindex="3"/]
 [@main.right /]
<div class="f14 mt16 w1150 bc bcwhite bae0e0e0 mt20 mb80 pl25 pr25">
 <div id="allmap" class="none" style="width:100%;height:400px;"></div>
    <div class="f18 fb lh180 cd43c33 mt30 bbe0e0e0">
        待接单
    </div>
   
    <div class="fb lh180 cblack mt30 bbe0e0e0">
        确认贴现地址
    </div>
    <div class="mt30 c2d2d2d">
        <div class="fl" id="address" > </div>
        <div class="fl c808080 ml20" id="member_mobile" > </div>
        <div class="fl 808080 ml60">
            <i class="fl w9 h14 position"></i>
            <div class="fl ml8" id="distance"></div>
        </div>
        <div class="fl cd43c33 ml20 SHANGMEN none">企业要求上门</div>
        <div class="fl c778ffd ml20 NOSHANGMEN none">企业未要求上门</div>
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
	        <select id="todoorTime" class="fl w148 h29 select1 ti8" >
	            <option class="" value="0" selected="selected" >2小时以内</option>
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
            <div class="fl w232">商票订单号</div>
            <div class="fl w155">总金额</div>
            <div class="fl w105">背书</div>
            <div class="fl w334">承兑企业</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="h210 bcwhite bbe0e0e0 pt25 pb25">
            <div class="fl w827 Rright">
                <div class="bbe0e0e0 tc f16 h65 lh40">
                    <div class="fl w222 Rright h40 tl ml10 ti8" id="no">
                        
                    </div>
                    <div class="fl w155 Rright h40 cd43c33" id="allmoney" ></div>
                    <div class="fl w105 Rright h40" id="endorse"></div>
                    <div class="fl w334 h40" id="bank"></div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h120">
                    <div class="fl w220 Rright h120 tl ml10">
                        <div class="ti8">开票日期：<span id="printtime"></span></div>
                        <div class="ti8 mt16">贴现日期：<span id="begintime"></span></div>
                        <div class="ti8 mt16">到期日期：<span id="endtime"></span></div>
                        <div class="ti8 mt16">计息天数：<span id="jxts"></span>天</div>
                    </div>
                    <div class="fl pl40 h123">
                        <div class="fl w50 h120">备注：</div>
                        <div class="fl w460 h120 tl" id="remarks">
                        </div>
                    </div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    <a href="#" class="c00a5f2 dsb w96 h35 br10 ba00a5f2 bc mt25" id="type1"></a>
                    <a href="#" class="c00a5f2 dsb w96 h35 br10 ba00a5f2 bc mt25" id="needTodoor"></a>
                </div>
            </div>
        </div>
        <div class="cb"></div>
    </div>
    <!--报价-->
    <div class="mt20 bae0e0e0 pl10 pb25">
        <div class="fl mt25 Rright Interestdiv1">
            <div class="tl fb f16">请填写您的报价<span class="f12">（仅需选择其中一种方式进行报价）</span>：</div>
            <div class="mt25 lh27 Interestdiv2">
                <div class="fl w129 Interestdiv3">
                    <input type="radio" name="way" value="0"  id="Interest1" class="fl w12 h12 mt6 ml10 radio2" checked="checked">
                    <label class="fl ml10 ce84c3d" for="Interest1">月息</label>
                </div>
                <div class="fl tc mr40">
                    <input type="text" id="price" class="fl w68 h27 bae0e0e0 f14 tc validate[custom[lilv]]" placeholder="00.00">
                    <div class="fl ml10 mr10"><span class="w16 mr20 cd43c33">‰</span>+</div>
                    <input type="text" id="price1" class="fl w68 h27 bae0e0e0 f14 tc ml20 validate[custom[baojianumber]]" placeholder="0">
                    <div class="fl ml10 mr10">元</div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="mt25 lh27 mt20 Interestdiv2">
                <div class="fl w129 Interestdiv3">
                    <input type="radio" name="way" value="2" id="Interest2" class="fl w12 h12 mt6 ml10 radio2">
                    <label class="fl ml10" for="Interest2">年息</label>
                </div>
                <div class="fl tc mr40">
                    <input type="text" id="nprice" class="fl w68 h27 bae0e0e0 f14 tc validate[custom[lilv]]" placeholder="00.00">
                    <div class="fl ml10 mr10"><span class="f14 mr20 tc cd43c33">% </span>+</div>
                    <input type="text" id="nprice1" class="fl w68 h27 bae0e0e0 f14 tc ml20 validate[custom[baojianumber]]" placeholder="0">
                    <div class="fl ml10 mr10">元</div>
                </div>
                <div class="cb"></div>
            </div>
            <div class="mt25 lh27 mt20 Interestdiv2">
                <div class="fl w129 Interestdiv3">
                    <input type="radio" name="way" value="1" id="Interest3" class="fl w12 h12 mt6 ml10 radio2">
                    <label class="fl ml10" for="Interest3">每十万扣</label>
                </div>
                <div class="fl tc mr40">
                    <input type="text" id="price2" class="fl w140 h27 bae0e0e0 f14 tc validate[custom[baojianumber]]" placeholder="00.00">
                    <div class="fl ml10 mr10">元</div>
                </div>
                <div class="cb"></div>
            </div>
        </div>
        <div class="fl pl40 mt25">
            <div class="f16 fb">请选择您需要持票企业提供的资料</div>
            <div class="w620 mt40">
                <div class="fl w190 h45">
                    <input type="checkbox" name="QYZL" id="ZL1" value="1" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL1">贸易合同</label>
                </div>
                <div class="fl w190 h45">
                    <input type="checkbox" name="QYZL" id="ZL2" value="2" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL2">增值税发票</label>
                </div>
                <div class="fl h45">
                    <input type="checkbox" name="QYZL" id="ZL3" value="3" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL3">盖章（法人章、财务章、公章）</label>
                </div>
                <div class="fl w190 h45">
                    <input type="checkbox" name="QYZL" id="ZL4" value="4" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL4">法人身份证</label>
                </div>
                <div class="fl w190 h45">
                    <input type="checkbox" name="QYZL" id="ZL5" value="5" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL5">经办人身份证</label>
                </div>
                <div class="fl h45">
                    <input type="checkbox" name="QYZL" id="ZL6" value="6" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL6">开户许可证</label>
                </div>
                <div class="fl w190 h45">
                    <input type="checkbox" name="QYZL" id="ZL7" value="7" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL7">营业执照</label>
                </div>
                    <input type="checkbox" name="QYZL" id="ZL8" value="8" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL8">税务登记证</label>
                <div>
                    <input type="checkbox" name="QYZL" id="ZL9" value="9" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL9">组织机构代码证</label>
                </div>
                <div class="fl w190 h45">
                    <input type="checkbox" name="QYZL" id="ZL10" value="10" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL10">贷款卡</label>
                </div>
                <div class="fl w190 h45">
                    <input type="checkbox" name="QYZL" id="ZL11" value="11" class="fl w14 h14 mr10 checkbox1">
                    <label class="fl" for="ZL11">保函</label>
                </div>
            </div>
            <div class="cb"></div>
            <div class="fl">
                <input type="checkbox" name="QYZL" id="ZL12" value="12" class="fl w14 h14 mr10 checkbox1">
                <label class="fl" for="ZL12">以上所选纸质材料的复印件</label>
            </div>
        </div>
        <div class="cb"></div>
    </div>
    <!--票面-->
    <div class="bae0e0e0 mt20 pb25" id="piaomian">
        <div class="pl10 h50 lh50 c717583 bcf9f9f9 bbe0e0e0">票面</div>
        <div id="FRONT" ></div>
    </div>
    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20 mb30">
        <div id="okDtbo" class="fr cd43c33 bad43c33 br3 dsb w238 h45 lh45 mr40 cp">
            接受订单<span id="minute_show">(10分00秒)</span>
        </div>
        <div id="cancelDtbo" class="fr c2d2d2d bae0e0e0 br3 dsb w166 h45 lh45 mr40 cp" >取消订单</div>
    </div>
    <div class="cb"></div>
</div>
<!--商票取消订单弹窗-->
<div class="w h pf bc05 zi10 top none" id="reason">
    <div class="w770 h584 bcfc4d42 br5 mt_5 l_50 ml-385 pr zi11">
        <!--关闭按钮-->
        <div class="w37 h37 pa t-15 r-15 zi13 redClose" id="redClose"></div>
        <!--理由表单-->
        <div class="w750 h558 pr t13 l10 bcf5f5f5 zi12">
            <div class="h45"></div>
            <div class="ml25 f16 lh40">
                <div class="fl w184 c7d7d7d">请选择取消理由：</div>
                <select class="fl w320 h40 select2 ti8" id="cancel">
                    <option value="1">额度不够</option>
                    <option value="2">价格不合适</option>
                    <option value="3">不在业务范围内</option>
                    <option value="4">银行大额支付系统已关闭</option>
                    <option value="5">其他</option>
                </select>
            </div>
            <div class="cb"></div>
            <!-- 其他-->
            <textarea placeholder="请您输入不少于十五字的理由！"  class="w500 h210 bae0e0e0 mt20 ml209 ti8 pt15 none" id="reason_div"></textarea>
            <div class="cb"></div>
            <input type="button" id="sure" value="确认" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b30 l_50 ml-130">
        </div>
    </div>
</div>


<script type="text/javascript" src="${jsPath}/distance.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=RZNGEivCrVHp06sXAM6rxlquUSOLB3xx&s=1"></script>
<script type="text/javascript">
	var _way;//过期判断
	var _state;//过期判断

	var map = null;
	var longitude = null;//"116.331398";//经度
	var latitude =null;// "39.897445";//纬度
	
	var _longitude = null;//贴现经度
	var _latitude = null;//贴现纬度

    var needStuff = "";
    var timeOut = false;//接单时间限制
    var needTodoor= null;
    var currentState = null;//当前订单状态
    $(document).ready(function() {
    	loaddata();
    	
    	wwwPath = "${basePath}";
    	actionLog(wwwPath,"action_98");//接单按钮统计
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
    	
    	
        //  选择利息方式
        $("input[name='Interest']").change(function () {
            $(".Interestdiv3").children("label").removeClass("ce84c3d");
            if ($(this).attr("checked") == "checked") {
                $(this).parent("div.Interestdiv3").children("label").addClass("ce84c3d");
            }
        })
		$("#okDtbo").on("click",function(){
            okDtbo();
        });
		$("#cancelDtbo").on("click",function(){
			cancel();
        });
		$("#sure").on("click",function(){
			cancelDtbo();
        });
		$("input[name='doorprice']").keyup(function(){
	        var temp = $(this).val();
	        $(this).val(temp.replace(/\-/g,''));
	    });
    });
    
    /**
     * 设置经纬度
	 */
	function setLngLat(point){
		longitude = point.lng;//经度
		latitude = point.lat;//纬度
		getDistance();
	}
    
    $("#redClose").click(function(){
    	$("#reason").addClass("none");
    });

    function cancel(){
    	$("#reason").removeClass("none");
    }
    
   
    
    function loaddata(){
		$.ajax({
			url: '${basePath}/distributeordersp/get',
			type: 'POST',
			data: {'no':"${no}"},
			dataType: 'json',
			success: function(result){
				var data = eval(result);
				if(data.response == "success"){
					var result=data.data;
					_way=result.way;
					_state=result.state;
					
					if(_way!=null||_state!=1){
						alert("页面已过期");
						window.location.href="${basePath}/hall/index";
						return;
					}
					currentState = result.state;
					_timer(result.timer);
					$("#address").text(result.address);
					$("#membermobile").text(result.membermobile);
					var type1 = result.type1;
					if(type1==1){
						$("#type1").text("纸票");
		            }else{
		            	$("#type1").text("电票");
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
				    $("#no").text(result.no);
					$("#allmoney").text(result.allmoney+"万元");
					if(result.endorse!=null) $("#endorse").text(result.endorse+"手");
					if(result.bank!=null) $("#bank").text(result.bank);
					if(result.remarks!=null) $("#remarks").text(result.remarks);
					
	                if(result.jxts!=null)$("#jxts").text(result.jxts);
	                var printtime = result.printtime;
	                if(printtime!=null){
	                    printtime = printtime.replace(/-/g, "/");
	                    $("#printtime").text(new Date(printtime).format("yyyy-MM-dd"));
	                }
	                var endtime = result.endtime;
	                if(endtime!=null){
	                    endtime = endtime.replace(/-/g, "/");
	                    $("#endtime").text(new Date(endtime).format("yyyy-MM-dd"));
	                }
	                var begintime = result.begintime;
	                if(begintime!=null){
	                	begintime = begintime.replace(/-/g, "/");
	                    $("#begintime").text(new Date(begintime).format("yyyy-MM-dd"));
	                }
					
					//票面
	                var _img = result.picpath;
	                if(_img!=null && $.trim(_img)!=""){
	                    var _arr = _img.split(",");
	                    var _imgs ="";
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
			}
		})	
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
        var price = $("#price").val();
        var price1 = $("#price1").val();
        var price2 = $("#price2").val();
        var way = $('input[name="way"]:checked').val();
        var needStuff = "";
        for(var i=1;i<13;i++){
        	if($("#ZL"+i).attr("checked")){
        		if(needStuff!=null && needStuff!="")needStuff += ",";
        		needStuff += $("#ZL"+i).val();
        	}  
        }
        if(way==0){
        	if($.trim(price)=="" && $.trim(price1)==""){
            	$("#price").addClass("validate[required]");
            	if(!$("#price").validationEngine("validate")){
            		$("#price").focus();
            		setTimeout(function(){$("#price").validationEngine('hideAll');},1000);
            		return;
            	}
	        }
        }else if(way==1){
        	$("#price2").addClass("validate[required]");
        	if(!$("#price2").validationEngine("validate")){
        		$("#price2").focus();
        		setTimeout(function(){$("#price2").validationEngine('hideAll');},1000);
        		return;
        	}
        }else{
        	price=$("#nprice").val();
        	price1=$("#nprice1").val();
        	if($.trim(price)=="" && $.trim(price1)==""){
            	$("#nprice").addClass("validate[required]");
            	if(!$("#nprice").validationEngine("validate")){
            		$("#nprice").focus();
            		setTimeout(function(){$("#nprice").validationEngine('hideAll');},1000);
            		return;
            	}
	        }


        }
        if(needTodoor==1){
        	var todoorPrice = $("#todoorPrice").val();
        	var todoorTime = $("#todoorTime").val();
        	if(!$("#todoorPrice").validationEngine("validate")){
        		$("#todoorPrice").focus();
        		setTimeout(function(){$("#todoorPrice").validationEngine('hideAll');},1000);
        		return;
        	}
        }
        
    	$.ajax({
    		url: '${basePath}/distributeordersp/accept',
			type: 'POST',
			data: {'no':"${no}",'needStuff':needStuff,'longitude':longitude,'latitude':latitude,'todoorPrice':todoorPrice,'todoorTime':todoorTime,'way':way,'price':price,'price1':price1,'price2':price2},
			dataType: 'json',
			success: function(result){
				var data = eval(result);
				if(data.response == "success"){
		             window.location.href="${basePath}/distributeordersp/success";
				}else if (data.response == 'failed') {
	                 alert("接单失败"); 
	            };
	         }	
    	})
	}	
    
	/**
     * 拒绝订单（或验票失败）
     */ 
    function cancelDtbo(){
    	$("#sure").attr("disabled","disabled");
        var reason = $("#cancel").find("option").not(function(){ return !this.selected }).text();//拒绝原因（文本）
        var lostCause = null;//其他理由
        var cancel = $("#cancel").val();
        
     	if(cancel==5){//其他
            lostCause = $("#reason_div").val();
            reason = lostCause;
            if (lostCause.length < 15) {
                alert("请你输入不少于15字的理由");
                $("#sure").removeAttr("disabled");
                return;
            }
        }else{
            lostCause = null;
        }
        var data="no="+"${no}"+"&cancel="+cancel+"&reason="+
        			reason+"&currentState="+currentState;
        $.ajax({
        	url:"${basePath}/distributeordersp/cancel",
        	type:"POST",
        	data:data,
        	dataType:"json",
        	success:function(data){
        		if (data.response == 'success') {
        			alert("取消成功!");
        			$("#reason").addClass("none");
        			$("#sure").removeAttr("disabled");
        			window.location.href="${basePath}/hall/index";
                }else{
                	$("#sure").removeAttr("disabled");
                    alert(data.msg);
                }
        	},
        	error:function(data){
        		$("#sure").removeAttr("disabled");
        	}
        });
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
     * 取消订单时理由是其他时可以填内容
     */ 
    $("#cancel").change(function(){
        var value = $(this).val();
        if(value==5){
            $("#reason_div").removeClass("none");
        }else{
            $("#reason_div").addClass("none");
            $("#reason_div").val("");
        }
    });
</script>
[@main.footer/]
[/@main.body]