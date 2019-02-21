var a_id = 26;//推广活动主键
var activity_id = 16;//红包活动主键

// JavaScript Document
function GetRandomNum(Min,Max){  
	var Range = Max - Min;  
	var Rand = Math.random();  
	return(Min + Math.round(Rand * Range));  
}  

/**
 * 转盘旋转（及完成事件回调）
 * @param a 旋转度数
 * @param title 提示内容
 * @param type 类型（0实物、1话费、2红包）
 */
function doIt(a,title,type){
	localStorage["GOODS"] = title;//奖品
	localStorage["TYPE"] = type;//类型（控制不同 页面）
	$("#img").rotate({
		angle:0, 
		duration: 5000,
		animateTo: a,
		callback:function(){
			myAlert(title,type);
		}
	});
}

var error_num;
$(document).ready(function(){
	$("#tip").hover(
		function () {
			$(this).attr("src","../../../images/zhuanpan/four2.png");
		},
		function () {
			$(this).attr("src","../../../images/zhuanpan/four1.png");
		}
	); 
	
	var roll = function(){
		$("#tip").unbind('mouseenter').unbind('mouseleave'); 
		$("#tip").attr("src","../../../images/zhuanpan/four2.png");
		if(parseInt($(".coud_num").html())<= 0){
			Alert("您的抽奖机会已用完,每个用户仅限抽奖一次！");
			var json_error= "error_0";
			return false;
		}

		$("#tip").unbind("click",lotteryAuth);
		
		var angle = 0;
		var Rand_num = GetRandomNum(1,99);
		switch(Rand_num){ 
		case 0:
			var json_error="win_1";
			break;
		case 100:
			var json_error="win_2";
			break;
		case 101:
			var json_error="win_3";
			break;
		case 5:
			var json_error="win_4";
			break;
	    default :
			var json_error="error_2";											
		
		}
		switch(json_error){
			case "win_1"://iPhone6S
				var $dushu= 1080;
				break;
			case "win_2"://iWatch
				var $dushu= 1170;  						
				break;
			case "win_3"://自拍杆
				var $dushu= 1130;
				break;   
			case "win_4": //充电宝
				var $dushu= 1010;
				doIt(1010,"恭喜您获充电宝!",0);
				break;
			case "error_2":
				error_num = GetRandomNum(1,99);
				if(1<=error_num&&error_num<=6){//笔筒
					var $dushu= 1050;
					doIt(1050,"恭喜您获得笔筒!",0);
				}else if(7<=error_num&&error_num<=12){//指甲钳
					var $dushu= 1210;
					doIt(1210,"恭喜您获得指甲钳!",0);
				}else if(13<=error_num&&error_num<=14){//u盘
					var $dushu= 1250;
					doIt(1250,"恭喜您获得u盘!",0);
				}else if(15<=error_num&&error_num<=25){//话费
					var $dushu= 1290;
					doIt(1290,"恭喜您获得10元手机话费!",1);
				}else if(26<=error_num&&error_num<=99){//红包
					var $dushu= 1330;
					doIt(1330,"恭喜您获得贴现红包!",2);
				}
				break;   
			default: 		
		}
		
	    var zhuan=setInterval(function(){
			angle+=15;
			 
			if(angle >= $dushu){
				clearInterval(zhuan);
				switch(json_error){ 
					case "win_1":
						break;
					case "win_2":
						break;
					case "win_3":   
						break;	
					case "win_4":
						break;
					case "error_2":
						break;
					default:
				}
				
				$(".coud_num").html($(".coud_num").html()-1);
				$("#tip").bind("click",lotteryAuth);
				
				$("#tip").attr("src","../../../images/zhuanpan/four1.png");
				$("#tip").hover(
					function () {
						$(this).attr("src","../../../images/zhuanpan/four1.png");
					},
					function () {
						$(this).attr("src","../../../images/zhuanpan/four2.png");
					}
				); 
			}				
		},50);				
	}
	
	var lotteryAuth = function(){
		var memberId = localStorage["memberId"];
		if(memberId=='undefined' || memberId==undefined){
			Alert("您的抽奖机会已用完,每个用户仅限抽奖一次！");
			return false;
		}
		$.ajax({
			url:"../../../app/lotteryAuth.htm",
			type:"post",
			data:{"memberId":memberId},
			dataType:"json",
			success:function(data){
				var result = eval(data);
				if(result.response=="success"){//注册成功（登录）
					roll();
				}else{
					Alert("您的抽奖机会已用完,每个用户仅限抽奖一次！");
					return false;
				}
			}
		});
	}
	
	$("#tip").bind("click",lotteryAuth);
});
document.onselectstart=new Function("event.returnValue=false;");
document.oncontextmenu=new Function("event.returnValue=false;");