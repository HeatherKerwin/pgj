/**
 *
 * @authors BB
 * @date    2019-01-23 16:22:39
 * @version $Id$: verticalTab.js 01
 */
function verticalTab() { //纵向选择tab
  var tabIndex ;
  $(".verticalTabBtn").click(function () {
    $(this).parents("ul.verticalTab").children("li").children("a").removeClass('checked');
    if ($(this).attr("checked") == "checked") {
        $(this).parent("a").addClass('checked');

        tabIndex = $(this).val();
        var page = ".page" + tabIndex;
        $(page).siblings().hide();
        $(page).show();
    }
  });
}