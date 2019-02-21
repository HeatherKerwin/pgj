[#include "/common/setting.ftl"]
<script type="text/javascript">
//报价收票 
//onmousemove="openOffer(this)" onmouseout="closeOffer(this)"
$(document).ready(function() {
	$("#colorOffer").mouseover(function () {
	    $("#imgOffer").attr("src","${image_url}/website/header/down.png");
	    $("#offerList").removeClass("none");
	})
	$("#colorOffer").mouseout(function () {
	    $("#imgOffer").attr("src","${image_url}/website/header/open.png");
	    $("#offerList").addClass("none");
	})
	$("#colorTool").mouseover(function () {
		$("#imgTool").attr("src","${image_url}/website/header/down.png");
		$("#toolList").removeClass("none");
	})
	$("#colorTool").mouseout(function () {
		$("#imgTool").attr("src","${image_url}/website/header/open.png");
		$("#toolList").addClass("none");
	})
	
	
})

</script>
