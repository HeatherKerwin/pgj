// JavaScript Document

function GetRandomNum(Min, Max) {

    var Range = Max - Min;

    var Rand = Math.random();

    return (Min + Math.round(Rand * Range));

}

function doIt(a, title) {
    $("#img").rotate({
        angle: 0,
        duration: 5000,
        animateTo: a,
        callback: function () {
            myAlert(a,title)
        }
    });
}

var error_num;
$(document).ready(function () {
    $("#tip").hover(
        function () {
            $(this).attr("src", "images/four2.png");
        },
        function () {
            $(this).attr("src", "images/four1.png");
        }
    );

    var roll = function () {
        $(this).unbind('mouseenter').unbind('mouseleave');
        $(this).attr("src", "images/four2.png");
        if (parseInt($(".coud_num").html()) <= 0) {
            alert("您的抽奖机会已用完，分享可增加抽奖机会！");
            var json_error = "error_0";
            return false;
        }

        $(this).unbind("click", roll);

        var angle = 0;

        var Rand_num = GetRandomNum(0, 99);

        switch (Rand_num) {
            case "102":
                var json_error = "win_1";
                break;
            case "100":
                var json_error = "win_2";
                break;
            case "101":
                var json_error = "win_3";
                break;
            default :
                var json_error = "error_2";

        }
        var storage=window.localStorage;
        var boolean = storage.getItem("wechat");
        var num = 1;
        if(boolean){
        	switch (json_error) {
	            case "error_0":
	                alert("您的抽奖机会已用完，分享可增加抽奖机会！");
	                return false;
	                break;
	            case "win_1"://iPhoneX-0个
	                var $dushu = 1080;
	                break;
	            case "win_2"://iPad-0个
	                var $dushu = 1140;
	                break;
	            case "win_3": //音响-0个
	                var $dushu = 1200;
	                break;
	            case "error_2":
	            	num=2;
	                error_num = GetRandomNum(0, 99);
	                console.log(error_num);
	                if (1 <= error_num && error_num <= 2) {//靠枕
	                    var $dushu = 1260;
	                    doIt(1260, "恭喜您获得靠枕!");
	                    $("#goods").html("恭喜您获得靠枕!");
	                    $("#prize").val(2);
	                } else if (3 <= error_num && error_num <= 4) {//充电宝
	                    var $dushu = 1200;
	                    doIt(1200, "恭喜您获得充电宝!");
	                    $("#goods").html("恭喜您获得充电宝!");
	                    $("#prize").val(3);
	                } else if (5 <= error_num && error_num <= 99) {//红包
	                    var $dushu = 1500;
	                    doIt(1500, "恭喜您获得贴现红包!");
	                    $("#goods").html("恭喜您获得贴现红包!");
	                    $("#prize").val(1);
	                }
	                break;
	            default:
	
	        }
        }else{
        	switch (json_error) {
	            case "error_0":
	                alert("您的抽奖机会已用完，分享可增加抽奖机会！");
	                return false;
	                break;
	            case "win_1"://iPhoneX-0个
	                var $dushu = 1080;
	                break;
	            case "win_2"://iPad-0个
	                var $dushu = 1140;
	                break;
	            case "win_3": //音响-0个
	                var $dushu = 1200;
	                break;
	            case "error_2":
	            	num=1;
	                error_num = GetRandomNum(0, 99);
	                console.log(error_num);
	                if (1 <= error_num && error_num <= 99) {//红包
	                    var $dushu = 1500;
	                    doIt(1500, "恭喜您获得贴现红包!");
	                    $("#goods").html("恭喜您获得贴现红包!");
	                    $("#prize").val(1);
	                }
	                break;
	            default:
	
	        }
        }
        

        var zhuan = setInterval(function () {
            angle += 15;
            if (angle >= $dushu) {
                clearInterval(zhuan);
                switch (json_error) {

                    case "win_1":
                        console.log("恭喜您获得iPhone6X手机!");
                        $("#goods").html("恭喜您获得iPhone6X手机!");
                        break;
                    case "win_2":
                        console.log("恭喜您获得iPad!");
                        $("#goods").html("恭喜您获得iPad!");
                        break;
                    case "win_3":
                        console.log("恭喜您获得智能音响!");
                        $("#goods").html("恭喜您获得智能音响!");
                        break;
                    case "error_2":
                    	if(num == 1){
                    		 if(1<=error_num&&error_num<=99){
                                 console.log("恭喜您获得贴现红包!");
                                 $("#goods").html("恭喜您获得贴现红包!");
                                 $("#prize").val(1);
                             }
                             break;
                    	}else{
                    		if(1<=error_num&&error_num<=2){
                                console.log("恭喜您获得靠枕!");
                                $("#goods").html("恭喜您获得靠枕!");
                                $("#prize").val(2);
                            }else if(3<=error_num&&error_num<=4){
                                console.log("恭喜您获得充电宝!");
                                $("#goods").html("恭喜您获得充电宝!");
                                $("#prize").val(3);
                            }else if(5<=error_num&&error_num<=99){
                                console.log("恭喜您获得贴现红包!");
                                $("#goods").html("恭喜您获得贴现红包!");
                                $("#prize").val(1);
                            }
                            break;
                    	}
                        
                    default:
                }

                var coud_num = $(".coud_num").html() - 1;
                $(".coud_num").html(coud_num);
                var storage=window.localStorage;
                storage.setItem("has_ticket",coud_num);
                $("#tip").bind("click", roll);

                $("#tip").attr("src", "images/four1.png");
                $("#tip").hover(
                    function () {
                        $(this).attr("src", "images/four1.png");
                    },
                    function () {
                        $(this).attr("src", "images/four2.png");
                    }
                );


            }
        }, 50);

    }

    $("#tip").bind("click", roll);

});
document.onselectstart = new Function("event.returnValue=false;");
document.oncontextmenu = new Function("event.returnValue=false;");
