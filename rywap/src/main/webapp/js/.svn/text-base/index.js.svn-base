var BASEPATH = "";
$(document).ready(function() {
	/*菜单-more下拉选择公用*/
	$(".more").mouseenter(function () {
		$(".moreTab").slideDown(); //慢慢展开
	}).mouseleave(function () {
	    $(".moreTab").slideUp();//慢慢收起
	});

	/*radio文字按钮单选样式变化公用*/
	$("input[type=radio]").each(function(){
		if($(this).attr("checked")){
			$(this).parent("li").children("label").addClass("radioSelect");
		}
		
	    $(this).click(function(){
	        $(this).parents("ul.choseBtn").children("li").children("label").removeClass("radioSelect");
	        $(this).parent("li").children("label").addClass("radioSelect");
	    });
	});

	$(".radioIcon").on("click",function(){
	    $(this).addClass("onRadio").siblings().removeClass("onRadio");
	});

	$(".checkIcon").on("click",function(){
		var tag = $(this).children("input");
		if(tag.is(':checked')){
			tag.removeAttr("checked");
		}else{
			tag.attr("checked",true).prop("checked","checked");
		}
	    $(this).hasClass("onCheck")? $(this).removeClass("onCheck"):$(this).addClass("onCheck");
	});
});