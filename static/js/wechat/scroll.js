var isOver = false;
var loaded = false; 

function initScroll(url,tag,m){
	var func = eval(m);
	$(document).bind("ready", function(){
		if(!loaded){
			loaded = true;
			$("#pageNo").val(1);
			new func(url,tag);
//			$('#footer').waypoint(function (a, b) {
//				$("#pageNo").val(parseInt($("#pageNo").val())+1);
//				scrollData();
//				
//				$('#footer').waypoint({ offset: '100%'    });
//			}, { offset: '100%'    });
		}
	});
	
	$(window).bind("scroll.myscroll", function () {
		//document.documentElement.scrollTop 一直都是0
		//document.body.scrollTop 滚动条距顶部的高度
		//$(document).height() 文档高度
		//$(window).height() 可视高度
		var top = document.documentElement.scrollTop + document.body.scrollTop;
		var textheight = $(document).height();
		var flag = "no";
		if ((textheight-$(document).scrollTop())<=(window.innerHeight)) {
			new func(url,tag);
		}
	});
}