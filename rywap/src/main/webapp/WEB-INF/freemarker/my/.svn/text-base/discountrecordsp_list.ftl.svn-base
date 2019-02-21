[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='3'/]


<link rel="stylesheet" href="${staticPath}/css/rywap/order.css"/>
<link rel="stylesheet" href="${staticPath}/css/rywap/window.css"/>
<script src="${staticPath}/js/rywap/scroll.js" type="text/javascript"></script>
<script src="${staticPath}/js/rywap/maskWindow.js" type="text/javascript"></script>

<!--容器-->
<div class="wrapper">
    <ul class="orderList" id="pageData">
       
    </ul>
</div>    
    <form id="paramForm">
		<input type="hidden" name="pageNo" value="1" id="pageNo">
		<input type="hidden" name="memberId" value="${member.id}" id="memberId">
	</form>
	<form id="form1" method="post" action="${basePath}/wap/discountrecordsp/skip/cancel">
		<input type="hidden" name="id" id="discountrecordspId">
	</form>
<script>
var memberId = null;
memberId = ${member.id};
initScroll('${basePath}/wap/discountrecordsp/list','#paramForm','scrollData');
	
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
			data = {'pageNo':pageNo,"memberId": memberId};
		}
		$("#pageNo").val(parseInt(pageNo)+1);
		$.ajax({
			url : url,
			type : "post",
			data : data,
			dataType : "json",
			success : function(data){
				if ($("#noData")&&pageNo==1){
					if(!data){
						var height=window.innerHeight-$(".wrap>.head").height()-$(".wrap>.idea-head").height();
						$("#noData").height(height).show();
					}
				}
				if(data.data != null){
					var html='';
					$.each(data.data,function(i,item){
						html += '<li><div class="ordertop"><img src="${imagePath }/rywap/order/yin.png" alt="" class="orderIcon"/>';
						var type ;
						if(item.type1 == 1){
							type = "zhi";	
						}else{
							type = "dian";	
						}
						var time ;
						if(item.endtime!=null){
							item.endtime = item.endtime.replace(/-/g, "/");
			                var d = new Date(item.endtime);
			                time = d.format('yyyy-MM-dd');
			            }else{
			                time = "--";
			            }
						html += '<img src="${imagePath }/rywap/order/'+type+'.png" alt="" class="orderIcon"/><div class="fl">';
			            html += '到期日：<span>'+time+'</span></div><div class="fr cred">';
			            var state ;
			            if(item.orderflag == 0 || item.orderflag == -2){
			            	state = "无效订单";
			            }else if(item.orderflag == 1 ){
			            	state = "待交易";
			            }else if(item.orderflag == 2){
			            	state = "交易中";
			            }else if(item.orderflag == 3 && item.commentId == null){
			            	state = "待评价";
			            }else if(item.orderflag == 3 && item.commentId != null){
			            	state = "已完成";
			            }     
			            html += state+'</div></div><div class="orderCon"><div class="orderCon1"><ul class="row">';   
			            html += '<li class="flex3"><div class="orderTitle">总金额</div><div>'+item.allmoney+'万元</div></li>';
			            html += '<li class="flex2 tac"><div class="orderTitle">承兑企业</div><div class="outH">'+item.bank+'</div>';
			            html += '</li><li class="flex3 tar"><div class="orderTitle">背书</div><div>'+item.endorse+'手</div>';    
			            html += '</li></ul></div><div class="orderCon2 outH">备注：<span >';
			            if(item.remarks!= null){
			            	html += item.remarks;
			            }
			            html += '</span></div>';
			           	if(item.org_id != null){
			           		html += '<div class="orderCon3"><ul class="row"><li class="flex1"><div class="outH">交易机构：<span>';
			           		html += item.company+'</span></div>';
			           		
			           		var temp = null;
			                if(item.current!=null){
			                    temp = item.current;
			                }else if(item.recommend!=null){
			                    temp = item.recommend;
			                }
			                if(temp!=null && temp.jg){
			                	html += '<div class="biaoqian">价格低</div>';
			                }
			                if(temp!=null && temp.pf){
			                	html += '<div class="biaoqian">评分高</div>';
			                }
			                if(temp!=null && temp.jl){
			                	html += '<div class="biaoqian">距离近</div>';
			                }
			                if(temp!=null && temp.zls){
			                	html += '<div class="biaoqian">资料少</div>';
			                }
			                html += '</li><li class="flex3  tel">';
			                html += '<a href="#" class="orderTel">';
				            html += '<img src="${imagePath}/rywap/order/dianhualiu.png" alt="" class="vam"/></a></li></ul></div>';
			           		
				            if(item.other >1){
				           		html += '<div class="orderCon4"><a href="#" class="jigou"><img src="${imagePath}/rywap/common/right.png" alt="" class="fr"/>';
				           		html += '<div class="fr">查看其他<span class="cred">'+item.other+'</span>家推荐机构</div></a></div>';
				            }
			           	}
			         	html += '</div><div class="orderBtm">';
		                
			            if(item.orderflag == 1){
			            	html += '<a href="javascript:void(0);" class="dib fr"><input type="button" value="取消订单" data-id ="'+item.id+'" class="cancel"/></a>';
			            }else if(item.orderflag == 3 && item.commentId == null){
			            	html += '<a href="javascript:void(0);" id="pingjia" class="dib fr"><input class="mask" type="button" value="去评价"/></a>';
			            }else if(item.orderflag == 2){
			            	html += '<a href="javascript:void(0);" class="dib fr"><input data-id ="'+item.id+'" type="button" value="取消订单" class="cancel"/></a>';
			            	html += '<a href="#" id="queren" class="dib fr"><input type="button" value="确认已完成" class="red mask"/></a>';
			            }
			            html += "</div></li>";
					});
					$('#pageData').append(html);
					isOver = false;
					$(".cancel").on("click", cancel);//取消订单
					$(".mask").on("click", mask);//弹出遮罩层
					$(".tel").on("click", tel);//弹出遮罩层
				}else{
					isOver = true;
				}				
			},
			error : function(){
				alert("网络异常");
			}
		});
	}
	
	 $(function(){
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
 
	 /*拨打电话*/
    function tel(){
    	var param = {
			tel:true
    	}
 		createPanle(param);
    };
    
    /**
	*	取消订单
	*/ 
	function cancel(){
		var id = $(this).attr("data-id");
		$("#discountrecordspId").val(id);
		$("#form1").submit();
	};
	
	/**
	*	弹出下载的遮罩层
	*/
	function mask(){
		var param = {
			app:true
    	}
 		createPanle(param);
	};
</script>
[@main.footer/]
[/@main.body]