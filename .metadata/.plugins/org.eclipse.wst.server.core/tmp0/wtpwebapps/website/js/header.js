/**
 * Created by Ry-kaifa on 2016/10/12.
 */
//顶部user
$(".top-user .top-user-box").mouseover(function(){
    $(this).addClass("u-d");
    $(".top-user .top-user-box #user-down").show();
})
$(".top-user .top-user-box").mouseout(function(){
    $(this).removeClass("u-d");
    $(".top-user .top-user-box #user-down").hide();
})