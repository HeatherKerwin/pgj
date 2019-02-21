var from,url;
$(document).ready(function(){  
	// 获取合作单位名称
    from = getUrlParam('from');
//    if (from != null && from != '') {
//    	from = '';
//    }
	url = window.location.href;
	$.cookie("hezuo", from);
	$.ajax({ 
		type: "post",  
		url : "sitestatistics/add/",  		 
		data: 'from='+from+'&url='+url+"&type=PC", //发给php的数据有两项，分别是上面传来的u和p 
		success: function(data){} 
	}); 
			
})      	
	  
// 根据 参数名称 获取url 参数值
function getUrlParam(name) {  
    //构造一个含有目标参数的正则表达式对象  
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");  
    //匹配目标参数  
    var r = window.location.search.substr(1).match(reg);  
    //返回参数值  
    if (r!=null) return unescape(r[2]);  
    return null;  
}  	
