[#include "/common/setting.ftl"]
<script type="text/javascript">
    //加入收藏
	function join_favorite(siteUrl, siteName){
 		//判断浏览器是否支持document.all         
		if(document.all){ 
		//如果支持则用external方式加入收藏夹                       
			try {
				window.external.addFavorite(siteUrl, siteName);
			} catch (e) {
			    alert("本浏览器不支持该操作，请使用Ctrl+D快捷键进行添加操作!");    
			}
		} else if (window.sidebar) {
			//如果支持window.sidebar，则用下列方式加入收藏夹  (火狐)  
			window.sidebar.addPanel(siteName, siteUrl, "");
		} else if (/Chrome/.test(window.navigator.userAgent)) {//基于chrome内核的浏览器不支持收藏，360，chrome
			alert("该浏览器不支持该操作，请使用Ctrl+D快捷键进行添加操作!");
		} else {
			alert("你的浏览器不支持该操作，请使用Ctrl+D快捷键进行添加操作!");
		}
	}
	
   function suggest() {
       $("#yijian").removeClass("none");
   }
   function yjclose() {
       $("#yijian").addClass("none");
   }
   function yjsave(){
	   if(!$("#messagecontent").validationEngine("validate")){
			$("#messagecontent").focus();
   		 	setTimeout(function(){$("#messagecontent").validationEngine('hideAll');},1000);
   		 	return;
   	   }
	   if(!$("#messagenumber").validationEngine("validate")){
			$("#messagenumber").focus();
  		 	setTimeout(function(){$("#messagenumber").validationEngine('hideAll');},1000);
  		 	return;
  	   }
	   var messagecontent=$("#messagecontent").val();
	   var messagenumber=$("#messagenumber").val();
	   $.ajax({
			url:"${basePath}/homepage/message/save",
			type:"POST",
			data:{'messagenumber':messagenumber,'messagecontent':messagecontent},
			dataType:"json",
			success:function(data){
				if(data.response == 'success'){
					alert(data.msg);
					$("#yijian").addClass("none");
				}
			}
		});
	   pyRegisterCvt1();//品友转化代码（统计首页意见反馈功能）
   }
</script>
