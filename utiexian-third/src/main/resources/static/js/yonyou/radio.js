$(document).ready(function() {
    /*radio单选效果*/
    $(".ryRadio").click(function () {
        $(this).parents("ul.ryRadioCheckd").children("li").children("label").removeClass('checked');
        if ($(this).attr("checked") == "checked") {
            $(this).parent("li").children("label").addClass('checked');
        };
    });
})

