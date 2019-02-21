$(function(){
	//  账户信息列表
    $("#account").click(function() {
        $("#accountCon").fadeToggle("slow");
    })
	//    贴现订单列表
    $("#discount").click(function() {
        $("#discountCon").fadeToggle("slow");
    })
    var discountLi1 = document.querySelectorAll("ul#accountCon li,ul#discountCon li,ul#collectCon li");
    for(var i1 = 0; i1 < discountLi1.length; i1++){
        discountLi1[i1].onmouseover = function(){
            $(this).children("a").addClass("cd43c33");
            $(this).children("a").children("img").removeClass("none");
            $(this).parent("ul").parent("li").children().eq(0).addClass("cd43c33");
        }
        discountLi1[i1].onmouseout = function(){
            $(this).children("a").removeClass("cd43c33");
            $(this).children("a").children("img").addClass("none");
            $(this).parent("ul").parent("li").children().eq(0).removeClass("cd43c33");
        }
    }
	//    收票订单列表
    $("#collect").click(function() {
        $("#collectCon").fadeToggle("slow");
    });
    $(".PJGJli").mouseover(function(){
	    $(this).children("a").addClass("cd43c33");
	    $(this).children("a").children("img").removeClass("none");
	    $(this).parent("ul").parent("li").children().eq(0).addClass("cd43c33");
	});
	$(".PJGJli").mouseout(function(){
	    if(!$(this).hasClass("MY-SELECT")){
	        $(this).children("a").removeClass("cd43c33");
	        $(this).children("a").children("img").addClass("none");
	        $(this).parent("ul").parent("li").children().eq(0).removeClass("cd43c33");
	    }
	});
		
	/*
	 * 帮助中心常见问题列表 
	 */
	$(".help").mouseover(function(){
        $(this).children("a").addClass("cd43c33").addClass("c2d2d2d");
        $(this).children("img").removeClass("none");
        $(this).parent("ul").children().eq(0).addClass("cd43c33");
    });
	
	$(".help").mouseout(function(){
        if(!$(this).hasClass("MY-SELECT")){
            $(this).children("a").removeClass("cd43c33");
            $(this).children("img").addClass("none");
            $(this).parent("ul").children().eq(0).removeClass("cd43c33");
        }
    });
	
    $(".help").each(function(){
        $(this).click(function(){
            $(this).parent("ul").children("li").removeClass("MY-SELECT");
            $(this).parent("ul").children("li").children("a").removeClass("cd43c33").addClass("c2d2d2d");
            $(this).parent("ul").children("li").children("img").addClass("none");
            $(this).addClass("MY-SELECT");
            $(this).children("a").addClass("cd43c33");
            $(this).children("img").removeClass("none");
        })
    });

    /*
     * 帮助中心票据学院列表 
     */
    $("#xinshouzhiyin").toggle(function(){
            $(this).removeClass("cd43c33").addClass("c2d2d2d");
            $("#zhiyin").fadeOut("slow");
        },function(){
            $(this).removeClass("cd43c33");
            $("#zhiyin").fadeIn("slow");
        }
    );
    $("#chupiaozhinan").toggle(function(){
            $(this).removeClass("cd43c33").addClass("c2d2d2d");
            $("#chupiao").fadeOut("slow");
        },function(){
            $(this).removeClass("cd43c33");
            $("#chupiao").fadeIn("slow");
        }
    );
    $("#shoupiaozhinan").toggle(function(){
            $(this).removeClass("cd43c33").addClass("c2d2d2d");
            $("#shoupiao").fadeOut("slow");
        },function(){
            $(this).removeClass("cd43c33");
            $("#shoupiao").fadeIn("slow");
        }
    );
    $("ul#zhiyin li , ul#chupiao li , ul#shoupiao li").mouseover(function(){
        $(this).children("a").addClass("cd43c33");
        $(this).children("a").children("img").removeClass("none");
        $(this).parent("ul").parent("li").children().eq(0).addClass("cd43c33");
    });
    $("ul#zhiyin li , ul#chupiao li , ul#shoupiao li").mouseout(function(){
        if(!$(this).hasClass("MY-SELECT1")){
            $(this).children("a").removeClass("cd43c33").addClass("c2d2d2d");
            $(this).children("a").children("img").addClass("none");
            $(this).parent("ul").parent("li").children().eq(0).addClass("cd43c33").removeClass("c2d2d2d");
        }
    });
    $(".title2").each(function(){
        $(this).click(function(){
            $(".title1").removeClass("cd43c33").addClass("c2d2d2d");
            $(".icon1").addClass("none");
            $(".icon2").addClass("none");
            $(".PJGJli2").addClass("none");
            $(this).addClass("cd43c33");
            $(this).parent().addClass("MY-SELECT1");
            $(this).children("img").removeClass("none");
        })
    });


    $(".PJGJli2").mouseover(function(){
        $(this).children("a").addClass("cd43c33");
        $(this).children("a").children("img").removeClass("none");
        $(this).parent("ul").parent("li").children().eq(0).addClass("cd43c33");
    });
    $(".PJGJli2").mouseout(function(){
        if(!$(this).hasClass("MY-SELECT2")){
            $(this).children("a").removeClass("cd43c33").addClass("c2d2d2d");
            $(this).children("a").children("img").addClass("none");
            $(this).parent("ul").parent("li").children().eq(0).removeClass("cd43c33").addClass("c2d2d2d");
        }
    });
    $(".title1").each(function(){
        $(this).click(function(){
        	$("#xinshouzhiyin").removeClass("cd43c33").addClass("c2d2d2d");
            $(".title2").removeClass("cd43c33").addClass("c2d2d2d");
            $(".icon2").addClass("none");
            $("ul#zhiyin li").removeClass("MY-SELECT1");
            $(".icon1").addClass("none");
            $(this).addClass("cd43c33");
            $(this).parent().addClass("MY-SELECT2");
            $(this).children("img").removeClass("none");
        })
    });
});
