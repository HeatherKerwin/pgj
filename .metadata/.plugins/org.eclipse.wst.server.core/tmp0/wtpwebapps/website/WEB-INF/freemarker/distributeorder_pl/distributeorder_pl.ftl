[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家--批量接单详情']
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
        <div class="fl" id="address"> </div>
        <div class="fl c808080 ml20" id="member_mobile" > </div>
        <div class="fl 808080 ml60">
            <i class="fl w9 h14 position"></i>
            <div class="fl ml8" id="distance"></div>
        </div>
    </div>
    <div class="cb"></div>
    <div class="fb lh180 cblack mt30 bbe0e0e0">
        确认订单信息
    </div>
    <div class="mt30 bae0e0e0 mb20">
        <div class="w h50 bcf9f9f9 lh50 f14 c717583 tc bbe0e0e0">
            <div class="fl w232">批量订单号</div>
            <div class="fl w260">票据总金额</div>
            <div class="fl w150">票据总数量</div>
            <div class="fl w184">票据到期天数</div>
            <div class="fl w312">票据特征</div>
        </div>
        <div class="cb"></div>
        <div class="h265 bcwhite bbe0e0e0 pt25 pb25">
            <div class="fl w827 Rright">
                <div class="bbe0e0e0 tc f16 h90 pb25">
                    <div class="fl w222 Rright h90 tl ml10 ti8"  id="no">
                    </div>
                    <div class="fl w260 Rright h90 lh30">
                        <div class=""><span id="allmoney" class="cd43c33"></span>万元</div>
                        <div class="">票面最小金额为<span id="minMoney"></span>万元</div>
                        <div class="">票面最大金额为<span id="maxMoney"></span>万元</div>
                    </div>
                    <div class="fl w150 Rright h90 lh30" id="amount" class="cd43c33"></div>
                    <div class="fl w184 h90 lh30">
                        <div class="">最短<span id="minExpiryDay" class="cd43c33"></span>天</div>
                        <div class="">最长<span id="maxExpiryDay" class="cd43c33"></span>天</div>
                    </div>
                </div>
                <div class="cb"></div>
                <div class="tc f16 pt25 h90">
                    <div class="fl w220 Rright h110 tl ml10">
                        <div class="ti8">包含承兑行：</div>
                        	<div id="type2"></div>
                    </div>
                    <div class="fl pl40 h90">
                        <div class="fl w50 h90">备注：</div>
                        <div class="fl w460 h90 tl" id="remarks">
                        </div>
                    </div>
                </div>
            </div>
            <div class="fl w300">
                <div class="tc lh35">
                    <div class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="type1"></div>
                    <div class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="flaw_ticket"></div>
                    <div class="fl c00a5f2 dsb w96 h35 br10 ba00a5f2 ml30 mt25" id="needTodoor"></div>
                </div>
            </div>
        </div>
        <div class="cb"></div>
        <div class="h65 lh65 ml10 ti8">
            订单有效期至：<span id="endtime"></span>
        </div>
        <div class="cb"></div>
        <div class="bte0e0e0 bcf9f9f9 tc pt25 pb25">
            <div class="fl f14 ml10 c717583">
                持票企业：<span class="f16 c2d2d2d" id="company"></span>
            </div>
            <div class="fl f14 ml100 c717583">
                联系人：<span class="f16 c2d2d2d" id="member_name"></span>
            </div>
            <div class="fl f14 ml100 c717583 tl">
                <div class="ti8">联系方式：<span class="f16 c2d2d2d" id="member_mobile1"></span></div>
                <div class="f12 mt10 c2d2d2d">（联系贴现机构时请说是在票据管家平台上看到的）</div>
            </div>
            <div class="cb"></div>
        </div>
    </div>
    <div class="w h70 bae0e0e0 bcf9f9f9 tc pt15 f18 mt20 mb30">
        <div class="fl ml10 mt16 f14 cd43c33">注：请与出票方电话确认可以成交此单后再接受订单，否则请拒绝订单哦。</div>
        <div id="okDtbo" class="fr cd43c33 bad43c33 br3 dsb w238 h45 lh45 mr40 cp">
            接受订单<span id="minute_show"></span>
        </div>
        <div id="cancelDtbo"  class="fr c2d2d2d bae0e0e0 br3 dsb w166 h45 lh45 mr40 cp">取消订单</div>
    </div>
    <div class="cb"></div>
</div>
<!--批量取消订单弹窗-->
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
            <textarea placeholder="请您输入不少于十五字的理由！" type="text" class="w500 h210 bae0e0e0 mt20 ml209 ti8 pt15 none" id="reason_div"></textarea>
            <div class="cb"></div>
            <input type="button" id="sure" value="确认" class="w260 h45 lh45 bc bcfc4d42 b0 br5 f18 cwhite pa b30 l_50 ml-130">
        </div>
    </div>
</div>

<script type="text/javascript" src="${jsPath}/distance.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=RZNGEivCrVHp06sXAM6rxlquUSOLB3xx&s=1"></script>
<script type="text/javascript">
	var _take;	
	var _state;
	var longitude = null;//"116.331398";//经度
	var latitude =null;// "39.897445";//纬度
	var map = null;
	
	var _longitude = null;//贴现经度
	var _latitude = null;//贴现纬度

    var needTodoor= null;
    var currentState = null;//当前订单状态
    $(document).ready(function() {
    	loaddata();
    	
    	wwwPath = "${basePath}";
    	actionLog(wwwPath,"action_99");//接单按钮统计
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
			cancel();
        });
		$("#sure").on("click",function(){
			cancelDtbo();
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
			url: '${basePath}/distributeorderpl/get',
			type: 'POST',
			data: {'no':"${no}",},
			dataType: 'json',
			success: function(result){
				var data = eval(result);
				if(data.response == "success"){
					var result=data.data;
					_take=result.take;
					_state=result.state;
			    	if(_take!=null||_state!=1){
						alert("页面已过期");
						window.location.href="${basePath}/hall/index";
						return ;
					}
					currentState = result.state;
					$("#address").text(result.address);
					$("#member_mobile").text(result.member_mobile);
					$("#member_mobile1").text(result.member_mobile);
					$("#member_name").text(result.member_name);
					$("#company").text(result.company);
					$("#type2").html(getType2(result.type2,true));
					var type1 = result.type1;
					if(type1==1){
						$("#type1").text("纸票");
		            }else{
		            	$("#type1").text("电票");
		            }
					needTodoor = result.need_todoor;
				    if(needTodoor==1){//需要上门
						$("#needTodoor").text("需要上门");
					}else if(needTodoor==0){//不需要上门
						$("#needTodoor").hide();
					}else{
						alert("上门获取异常！！");
					}
				    if(result.flaw_ticket==0){
						$("#flaw_ticket").text("瑕疵票");
					}else{
						$("#flaw_ticket").hide();
					}
				    $("#no").text(result.no);
					$("#allmoney").text(result.allmoney);
					if(result.remarks!=null) $("#remarks").text(result.remarks);
					
	                $("#minMoney").text(result.min_money);
	                $("#maxMoney").text(result.max_money);
	                $("#amount").text(result.amount);
	                $("#minExpiryDay").text(result.min_expiry_day);
	                $("#maxExpiryDay").text(result.max_expiry_day);
	                
	                var endtime = result.endtime;
	                if(endtime!=null){
	                    endtime = endtime.replace(/-/g, "/");
	                    $("#endtime").text(new Date(endtime).format("yyyy-MM-dd"));
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
    	$.ajax({
    		url: '${basePath}/distributeorderpl/accept',
			type: 'POST',
			data: {'no':"${no}",'longitude':longitude,'latitude':latitude,},
			dataType: 'json',
			success: function(result){
				var data = eval(result);
				if(data.response == "success"){
		             window.location.href="${basePath}/distributeorderpl/success";
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
        	url:"${basePath}/distributeorderpl/cancel",
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

[@main.footer /]
[/@main.body]