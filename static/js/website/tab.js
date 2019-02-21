$(document).ready(function() {
    /*横栏选择tab*/
    $(".orderTabBtn").click(function () {
        $(this).parents("ul.orderTabNew").children("li").children("a").removeClass('tabChecked');
        if ($(this).attr("checked") == "checked") {
            console.log("11");
            $(this).parent("label").parent("a").addClass('tabChecked');
        }
    });
})