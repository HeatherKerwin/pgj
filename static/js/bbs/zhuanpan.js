function GetRandomNum(Min,Max){  
	var Range = Max - Min;  
	var Rand = Math.random();  
	return(Min + Math.round(Rand * Range));  
}  

/**
 * 转盘旋转（及完成事件回调）
 * @param a 旋转度数
 * @param title 提示内容
 * @param genre 类型（0实物、1话费）
 */
function doIt(a,goods,genre){
	_goods = goods;
	_genre = genre;
	$("#img").rotate({
		angle:0,
		duration: 5000,
		animateTo: a,
		callback:function(){
			$("#tip").bind("click",lotteryAuth);
			saveAward(goods,genre);
		}
	});
}

/**
 * 保存奖励
 * @param title
 * @param type
 */
var _goods = null;
var _genre = null;
function saveAward(goods,genre){
	$.post(BASE + "/my/ticket/use",{goods:goods,genre:genre}, function(response){
		if(response){
			if(response.status == 200){
				var data = response.data;
				myAlert(_goods,_genre,data.aid,data.has);
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
}

var error_num;
$(document).ready(function(){
	$("#tip").hover(
		function () {
			$(this).attr("src",BASE+"/assets/images/bbs/four2.png");
		},
		function () {
			$(this).attr("src",BASE+"/assets/images/bbs/four1.png");
		}
	); 
	
	$("#tip").bind("click",lotteryAuth);
});

var lotteryAuth = function(){
	$.post(BASE + "/my/ticket/can", function(response){
		if(response){
			if(response.status == 200){
				$("#tip").unbind("click",lotteryAuth);
				roll();
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
}

var roll = function(){
	$(this).unbind('mouseenter').unbind('mouseleave');
	$(this).attr("src",BASE+"/assets/images/bbs/four2.png");

	var angle = 0;
	var Rand_num = GetRandomNum(1,1000);
	
	switch(Rand_num){ 
		case "0":
			var json_error="win_1";
			break;
		case "10000":
			var json_error="win_2";
			break;
	    default :
			var json_error="error_2";											
	}
	switch(json_error){   
		case "error_0":   
			alert("您的抽奖机会已用完！");
			return false;
			break;
		case "win_1"://iPhone7 plus
			var $dushu= 1400;
			break;
		case "win_2"://iWatch 2
			var $dushu= 1160;  						
			break;   
		case "error_2":
			error_num = GetRandomNum(1,100);
			if(6<=error_num&&error_num<=10){//充电宝
				var $dushu= 1320;
				doIt(1320,"恭喜您获得充电宝!",0);
			}else if(11<=error_num&&error_num<=20){//指甲钳
				var $dushu= 1200;
				doIt(1200,"恭喜您获得指甲钳套装!",0);
			}else if(21<=error_num&&error_num<=30){//笔筒
				var $dushu= 1370;
				doIt(1370,"恭喜您获得笔筒!",0);
			}else if(1<=error_num&&error_num<=5){//u盘
				var $dushu= 1110;
				doIt(1110,"恭喜您获得u盘!",0);
			}else if(31<=error_num&&error_num<=100){//话费
				var $dushu= 1260;
				doIt(1260,"恭喜您获得10元手机话费!",1);
			}
			break;   
		default: 		
	}
	
    var zhuan = setInterval(function(){
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
			
			$("#tip").attr("src",BASE+"/assets/images/bbs/four1.png");
			$("#tip").hover(
				function () {
					$(this).attr("src",BASE+"/assets/images/bbs/four1.png");
				},
				function () {
					$(this).attr("src",BASE+"/assets/images/bbs/four2.png");
				}
			);
		}
	},50);
}

document.onselectstart=new Function("event.returnValue=false;");
document.oncontextmenu=new Function("event.returnValue=false;");
