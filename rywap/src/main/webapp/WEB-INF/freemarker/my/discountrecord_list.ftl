[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='3'/]

<link rel="stylesheet" href="${staticPath}/css/rywap/order.css"/>
<link rel="stylesheet" href="${staticPath}/css/rywap/window.css"/>

<!--容器-->
<div class="wrapper">
    <ul class="orderList" id ="pageData" >
        
    </ul>
</div>
	<form id="paramForm">
		<input type="hidden" name="pageNo" value="1" id="pageNo">
		<input type="hidden" name="memberId" value="${member.id}" id="memberId">
	</form>
	<form id="form1" method="post" action="${basePath }/wap/discountrecord/skip/cancel">
		<input type="hidden" name="id" id="discountrecordId">
	</form>
</div>
<script type="text/javascript" src="${staticPath}/js/rywap/base64.js"></script>
<script src="${staticPath}/js/rywap/scroll.js" type="text/javascript"></script>
<script src="${staticPath}/js/rywap/maskWindow.js" type="text/javascript"></script>
<script type="text/javascript">
var memberId = null;
memberId = ${member.id};
initScroll('${basePath}/wap/discountrecord/list','#paramForm','scrollData');

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
					html += '<img src="${imagePath }/rywap/order/'+type+'.png" alt="" class="orderIcon"/><div class="fl">';
		            html += '到期日：<span>'+item.endtime+'</span></div><div class="fr cred">';
		            var state ;
		            if(item.orderflag == 0 || item.orderflag == -2){
		            	state = "无效订单";
		            }else if(item.orderflag == 1 && item.deposit_state == 0){
		            	state = "待支付";
		            }else if((item.orderflag == 1 || item.orderflag == 4) && item.deposit_state == 1){
		            	state = "交易中";
		            }else if(item.orderflag == 4){
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
		            if(item.memberother!= null){
		            	html += item.memberother;
		            }
		            html += '</span></div>';         
		           	if(item.org_id != null){
		           		html += '<div class="orderCon3"><ul class="row"><li class="flex1"><div class="outH">交易机构：<span>';
		           		html += item.company+'</span></div><div>贴现价格：<span class="cblue">'+1+'%</span></div>';
		           		html += '</li><li class="flex3" onclick ="tel();"><a href="#" class="orderTel"><img src="${imagePath }/rywap/order/dianhualiu.png" alt="" class="vam"/>';
		           		html += '</a></li></ul></div>';
		           	}
		            html += '</div><div class="orderBtm">';
		                      
		            if(item.orderflag == 1 && item.deposit_state == 1){
		            	html += '<a href="javascript:void(0);" class="dib fr"><input type="button" value="取消订单" data-id ="'+item.id+'" class="cancel"/></a>';
		            }else if(item.orderflag == 1 && item.deposit_state == 0){
		            	html += '<a href="javascript:void(0);" class="dib fr"><input data-id ="'+item.id+'" type="button" value="取消订单" class="cancel"/></a>';
		            	html += '<a href="#" class="dib fr"><input type="button" data-id="'+item.id+'" value="付款" class="red pay"/></a></div></li>';
		            }else if(item.orderflag == 3 && item.commentId == null){
		            	html += '<a href="javascript:void(0);" id="pingjia" class="dib fr"><input class="mask" type="button" value="去评价"/></a>';
		            }else if(item.orderflag == 4){
		            	html += '<a href="javascript:void(0);" class="dib fr"><input data-id ="'+item.id+'" type="button" value="取消订单" class="cancel"/></a>';
		            	html += '<a href="#" id="queren" class="dib fr"><input type="button" value="确认已完成" class="red mask"/></a>';
		            }    
		       
				});
				$('#pageData').append(html);
				isOver = false;
				$(".cancel").on("click", cancel);//取消订单
				$(".mask").on("click", mask);//弹出遮罩层
				$(".pay").on("click", pay);//支付
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
	$("#discountrecordId").val(id);
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

/**
*	支付订单
*/
function pay(){
	var safari = false;
    if(isSafari=navigator.userAgent.indexOf("Safari")>0) {   
    	safari = true;
    } 
	var id = $(this).attr("data-id");
	$.ajax({
		url:"${basePath}/wap/discountrecord/get/info",
		type:"POST",
		data:{"id":id},
		dataType:"json",
		success:function(data){
			if(data.response == "success"){
				var result = data.data;
                   dcrdId = result.id;
                   if(result.longitude==null || result.longitude==""){//为设置贴现地址
                	   window.location.href = BASEPATH+"/wap/discountrecord/confirm/page?id=" + id;
                   }else{
                        var bnsNo = result.bnsNo;
                        var payWay = result.payWay;
                        var memberId = result.memberid;
                        var money = result.deposit;
                        var _URL = '';
						if(payWay==3){
							if(safari){
								window.location.href = '${appPath}/app/discountrecord/pay?orderAmount='+money+'&memberId='+memberId+'&orderId='+bnsNo;
								return;
							}else{
	       						_URL = '${appPath}/app/discountrecord/pay?orderAmount='+money+'&memberId='+memberId+'&orderId='+bnsNo;
							}
	       				}else if(payWay==4){
	       					_URL = "${mobilePath}/baofoo/pay?orderNo=" + bnsNo + "&type=1";
	       				}
	       				window.location.href = BASEPATH+"/wap/pay?url=" + new Base64().encode(_URL);
                   }	
			}else{
			}
		}
	});
};
</script>
[@main.footer/]
[/@main.body]