/**
 *
 * @authors BB
 * @date    2019-01-24 16:06:14
 * @version $Id$ noteList.js 01
 */

function noteList(){
  $(".noteItemIcon").click(function() {
  //
  if($(this).prev(".itemTabel").is(":hidden")){
    //
    if(!$(this).prev(".itemTabel").is(":animated")) {
      $(this).css({'transform':'rotate(180deg)'});
      //
      $(this).prev(".itemTabel").animate({
          height: 'show'
        }, 100)
        //siblings
        .end().siblings().find(".itemTabel").hide(100);
    }
  } else {
    //
    if(!$(this).prev(".itemTabel").is(":animated")) {
      $(this).css({'transform':'rotate(360deg)'});
      $(this).prev(".itemTabel").animate({
          height: 'hide'
        }, 100)
        .end().siblings().find(".itemTabel").hide(100);
    }
  }
});
}
