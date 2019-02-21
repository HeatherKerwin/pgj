	/*根据不同操作类型进行统计*/
	
	/*$(document).ready(function(){
		initClickCount("pc");
	});*/
	

	var domain='';
	var url = window.location.href;
	if(url.indexOf("ryfinance.com") > -1){
		domain = "ryfinance.com";
	}
	if(url.indexOf("utiexian.com") > -1){
		domain = "utiexian.com";
	}
	$(document).ready(function (){
		var from = getParam('from');
		if(url.indexOf("360media")>-1){
			from = '360media';
		}else if(url.indexOf("360")>-1){
			from = '360';
		}else if(url.indexOf("bihe")>-1){
			from = 'bihe';
		}else if(url.indexOf("uc")>-1){
			from = 'uc';
		}else if(url.indexOf("sougou")>-1){
			from = 'sougou';
		}else if(url.indexOf("pinyou")>-1){
			from = 'pinyou';
		}else if(url.indexOf("ad7")>-1){
			from = 'ad7';
		}else if(url.indexOf("shenma")>-1){
			from = 'shenma';
		}
		if(from!= ""&&from!=null&&from!=undefined ){
			if(domain!=''){
				$.cookie("hezuo",from,{path:'/',domain: domain});
			}else{
				$.cookie("hezuo",from,{path:'/'});
			}
			
		}
	});	
	/*初始访问*/
	function initClickCount(style){
		var uuid = getUuid();
		if(setCookie("uuid", uuid) == ""){
			uuid = $.cookie("uuid");
		}
		/*$.cookie("hezuo", from);*/
		var url = location.href;
		$.ajax({
			type:"post",
			url:mobilePath + '/tg/clickCount/initSave',//从后台获取ip
			data:{"url":url,"style":style,"uuid":uuid},
			dataType:"text",
			success:function(data){
    			if(data=="success"){
    				
    			}else{
    				
    			}
    		}
		});
	
	}
	
	function getUuid() {
		var s = [];
		var hexDigits = "0123456789abcdef";
		for (var i = 0; i < 36; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
		s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
		s[8] = s[13] = s[18] = s[23] = "-";
		
		var uuid = s.join("");
		return uuid;
	} 
	
	/*统计访问IP量*/
	function countByIp(){
		$.ajax({
			type:"post",
			url:mobilePath + "/tg/clickCount/countByIp",
			data:{},
			dataType:"text",
			success:function(data){
				if(data=="success"){
					
				}else{
					
				}
			}
		});
	}
	
	/*根据style和code进行数据统计*/
	function save(style,code,href){
		if(code!="visit"){
			return;
		}
		var rurl = document.referrer;
		var referrerUrl = rurl; //来源地址
		var url = location.href; //当前地址
		var uuid = getUuid();
		if(url.indexOf("ryfinance.com") > -1 || url.indexOf("utiexian.com") > -1){
			if(setCookie("uuid", uuid) == ""){
				uuid = $.cookie("uuid");
			}
		}
		$.ajax({
			type:"post",
			url:mobilePath + "/tg/clickCount/save",//从后台获取ip
			data:{"url":url,"style":style,"referrerUrl":referrerUrl,"code":code,"uuid":uuid},
			dataType:"text",
			success:function(data){
				if(data=="success"){
					if(href == null || href == ""){
						
					}else if(href.indexOf("http") != -1){
						window.location.href = href;
					}else if(href.indexOf("#") != -1 && url.indexOf("#") != -1){//锚记路径
						window.location.href = href;
					}else if(href.indexOf("#") != -1){
						window.location.hash = href;
					}else{
						window.location.href = href;
					}
				}else{
					
				}
			}
		});
	}
	
	
	/*根据style和code进行数据统计*/
	function save1(style,code,href){
		if(code!="visit"){
			return;
		}
		var rurl = document.referrer;
		var referrerUrl = rurl; //来源地址
		var url = location.href; //当前地址
		var uuid = getUuid();
		if(setCookie("uuid", uuid) == ""){
			uuid = $.cookie("uuid");
		}
		$.ajax({
			type:"get",
			url:mobilePath + "/tg/clickCount/save",//从后台获取ip
			data:{"url":url,"style":style,"referrerUrl":referrerUrl,"code":code,"uuid":uuid},
			dataType:"text",
			async:false
		});
		if(href == null || href == ""){
			
		}else if(href.indexOf("http") != -1){
			window.location.href = href;
		}else if(href.indexOf("#") != -1){//锚记路径
			window.location.hash = href;
		}else{
			window.location.href = href;
		}
	}
	
	
	
	
	/*根据style和code进行统计*/
	function count(style,code){
		$.ajax({
			type:"post",
			url:mobilePath + "/tg/clickCount/count",
			data:{"style":style,"code":code},
			dataType:"text",
			success:function(data){
				if(data=="success"){
					
				}else{
						
				}
			}
		})
	}
	
	function saveByUrl(style,key){//根据url来保存记录
		var style = getUrlParam1(style);//style代表pc，app
		var code = getUrlParam1(key);//sitestatistics.js获取url
		if(code != null || code != ""){
			save(style,code,"");
		}
	}

	
	/*根据url中的参数获得参数值*/
	function getParam(paramName)
	{
	        paramValue ="";
	        isFound =false;
	        if (this.location.search.indexOf("?") ==0&&this.location.search.indexOf("=")>1)
	        {
	            arrSource = unescape(this.location.search).substring(1,this.location.search.length).split("&");
	            i =0;
	            while (i < arrSource.length &&!isFound)
	            {
	                if (arrSource[i].indexOf("=") >0)
	                {
	                     if (arrSource[i].split("=")[0].toLowerCase()==paramName.toLowerCase())
	                     {
	                        paramValue = arrSource[i].split("=")[1];
	                        isFound =true;
	                     }
	                }
	                i++;
	            }  
	        }
	   return paramValue;
	}

	
	/*跨域，style和code进行数据统计*/
	function saveCross(style,code,href){
		var style = getParam("style");
		if(style == null || style == "" || style == undefined){
			
		}else{
			var rurl = document.referrer;
			var referrerUrl = rurl; //来源地址
			var url = location.href; //当前地址
			
			$.ajax({
				type:"get",
				url:mobilePath + "/tg/clickCount/save",//从后台获取ip
				data:{"url":url,"style":style,"referrerUrl":referrerUrl,"code":code},
				dataType : "jsonp",
				async:false
			});
			window.location = href;
		}
		
	}

/*	
	function getCookie(cname) {
	    var name = cname + "=";
	    var ca = document.cookie.split(';');
	    for(var i=0; i<ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') c = c.substring(1);
	        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
	    }
	    return "";
	}*/
	
	
	
	
	function setCookie(key,value){
		var temp = $.cookie(key);
		if(temp == "" || temp == null){
			if(domain!=''){
				$.cookie(key, value, { expires: 30, path: '/',domain:domain });
			}else{
				$.cookie(key, value, { expires: 30, path: '/' });
			}	
			return value;
		}else{
			return "";
		}
	}