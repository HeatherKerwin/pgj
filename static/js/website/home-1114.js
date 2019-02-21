/*
* 过度动画
* */
$(document).ready(function(){
    var a,b,c,d;
    a = $(window).height();    //浏览器窗口高度
    c = $(".homeContent").offset().top;
    d = $("#calculator").offset().top;
    $(window).scroll(function(){
        b = $(this).scrollTop();   //页面滚动的高度
        if ( b < 10 ) {
            $(".headMenu").css("background","rgba(0,0,0,0.3)");
        }else{
            if(a + b > c){
            $(".headMenu").css("background","#000");
            if (a + b > d){
                $("#calculator").addClass("transformJSQ");
                $("#offer").addClass("transformSSBJ");
            }else {
                $("#calculator").removeClass("transformJSQ");
                $("#offer").removeClass("transformSSBJ");
            }
        };
        }


    });
});

/*
* 过度动画
* */