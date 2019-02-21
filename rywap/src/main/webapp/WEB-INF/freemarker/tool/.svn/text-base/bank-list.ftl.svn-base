[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家-工具-询价"]
[@main.header currentmenu='2'/]
<link rel="stylesheet" href="${staticPath}/css/rywap/urge.css">
<div class="wrapper">
	<div class="bold urgeTitle bcwhite font16">查询结果</div>
    <ul class="urgeList" id="pageData">
    </ul>
    <form id="paramForm">
	    <input type="hidden" name="pageNo" value="1" id="pageNo">
	    <input type="hidden" name="yinhang" id="yinhang">
	    <input type="hidden" name="provice" id="provice">
	    <input type="hidden" name="city" id="city">
	    <input type="hidden" name="keyword" id="keyword">
    </form>
</div>
<script type="text/javascript" src="${staticPath}/js/rywap/scroll.js"></script>
<script type="text/javascript">
initScroll(BASEPATH+"lianhangSearch",'#paramForm','scrollData');
	/**
	 * 分页数据
	 * @param url
	 * @param ele
	 * @param pageNo
	 */
	function scrollData(url,formId){
		if(isOver){return;}
		isOver = true;
		var data = {};
		var pageNo = $("#pageNo").val();
		if(formId){
			data = $(formId).serialize();
		}else{
			data = {'pageNo':pageNo};
		}
		$("#pageNo").val(parseInt(pageNo)+1);
		$.ajax({
			url : url,
			type : "post",
			data : data,
			dataType : "json",
			success : function(data){
				console.log(data);
				if ($("#noData")&&pageNo==1){
					if(!data){
						var height=window.innerHeight-$(".wrap>.head").height()-$(".wrap>.idea-head").height();
						$("#noData").height(height).show();
					}
				}
				if(data){
					var html='';
					$.each(data.data,function(i,item){
						html+='<li class="urgeListCon">';
						html+='<ul>';
						html+='<li>'+item.yinhangdesc+'</div>';//具体银行名称
						html+='<li>支付联行号：<span>'+item.lianhanghao+'</span></li>';//支付联行号
						html+='<li>地址：<span>'+item.address+'</span></li>';//地址
						html+='<li>电话：<a href="tel:'+item.phone+'" class="cblue">'+item.phone+'</a></li>';//电话
						html+='</ul>';
						html+='</li>';
					});
					$('#pageData').append(html);
					isOver = false;
				}else{
					isOver = true;
				}				
			},
			error : function(){
				myToast("网络异常");
			}
		});
	}
	
    $(function(){
    	$("#yinhang").val(window.localStorage.getItem("yinhang"));
    	$("#provice").val(window.localStorage.getItem("provice"));
    	$("#city").val(window.localStorage.getItem("city"));
    	$("#keyword").val(window.localStorage.getItem("keyword"));
        $("#pageData").on("swipeRight",function(){
            var index = $(".cloud-tab").children("li.active").index();
            if(index-1<0){
                index=4;
            }else{
                index--;
            }
            location.href=$(".cloud-tab").children("li").eq(index).data("url");

        }).on("swipeLeft",function(){
            var index = $(".cloud-tab").children("li.active").index();
            if(index+1>4){
                index=0;
            }else{
                index++;
            }
            location.href=$(".cloud-tab").children("li").eq(index).data("url");
        });
    });
</script>
[@main.footer/]
[/@main.body]