$(document).ready(function(){
	var html = "<div class='header backBtn'>";
		html += "<a class='new-a-back' href='javascript:history.back();'>";
		html += "<span><img src='"+BASEPATH+"/images/common/back.png'></span>";
		html += "</a>";
		html += "<h2>"+$("header").attr("data-name")+"</h2>";
		html += "</div>";
	$("header").html(html);
});
  
  
  
