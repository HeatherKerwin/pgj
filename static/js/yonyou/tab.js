$(document).ready(function() {
    /*银、纸票-纵栏tab*/
    $(".typeTabBtn").click(function () {
        $(this).parents("ul.typeTab").children("li").children("a").removeClass('checked');
        if ($(this).attr("checked") == "checked") {
            $(this).parents("a").addClass('checked');
        };
    });

    /*订单-横栏选择tab*/
    $(".orderTabBtn").change(function () {
        console.log("orderTabBtn");
        $(this).parents("ul.orderTab").children("li").children("a").removeClass('checked');
        if ($(this).attr("checked") == "checked") {
            $(this).parent("label").parent("a").addClass('checked');
        }
    });
})