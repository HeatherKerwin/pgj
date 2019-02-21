/**
 * Created by Ry-kaifa on 2016/10/13.
 */
/*报价选择条件*/

//勾选
$(".TXchecked").on("click",function(){
    $(this).hasClass("on_check")? $(this).removeClass("on_check"):$(this).addClass("on_check");
})
