[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='3'/]

<link rel="stylesheet" href="${staticPath}/css/rywap/myquery.css"/>
<link rel="stylesheet" href="${staticPath}/css/rywap/window.css"/>

<div class="wrapper">
    <ul class="queryList" id="pageData">
    
    </ul>
</div>    
	<form id="paramForm">
		<input type="hidden" name="pageNo" value="1" id="pageNo">
		<input type="hidden" name="memberId" value="${member.id}" id="memberId">
	</form>
	<form action="${basePath }/wap/inquiryreply/skipdetails" method="post" id="from1">
		<input type="hidden" name="id" value="" id="id">
	</form>
<script type="text/javascript" src="${staticPath}/js/rywap/base64.js"></script>	
<script src="${staticPath}/js/rywap/scroll.js" type="text/javascript"></script>
<script src="${staticPath}/js/rywap/maskWindow.js" type="text/javascript"></script>
<script type="text/javascript">
var memberId = null;
initScroll('${basePath}/wap/inquiryreply/list','#paramForm','scrollData');

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
					html += '<li><div class="queryData" data-no="'+item.no+'" onclick="skipdetails('+item.id+');"><ul><li><div class="fl mr10">时间</div>';
					html += '<div class="fl">'+item.create_time+'</div><div class="fr cred">';
					if(item.pay_state == 0){
						html+='等待付款';
					}else{
						html+='付款成功';
					}
					html += '</div></li><li><div class="fl mr10">查询查复</div><div class="fl">'+item.no+'</div>';
					html += '</li><li> <div class="fl mr10">查询查复金额</div><div class="fl">'+item.money+'万元</div>';
					html += '</li></ul></div><div class="queryEdit">';        
		                
			    	if(item.state!=4){
						html+='<input type="button" value="联系客服" onclick="telBtn();" class="fr whiteBtn telBtn"/>';
					}
					if(item.pay_state == 0){
						html+='<input type="button" value="付款" data-no="'+item.no+'" data-state="'+item.pay_way+'"  class="fr redBtn"/>';
					}    
			        html += '</div></li>';    

				});
				$('#pageData').append(html);
				isOver = false;
				$(".redBtn").on("click", pay);
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
 	memberId = ${member.id};

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

/**
*	跳转到详细页面
*/
function skipdetails(id){
	$("#id").val(id);
	$("#from1").submit();
}

/*拨打电话*/
function telBtn(){
     var param = {
         tel:true
     }
     createPanle(param);
 };
 
/**
*	查询查复支付金额
*/
function pay(){
	var safari = false;
    if(isSafari=navigator.userAgent.indexOf("Safari")>0) {   
    	safari = true; 
    } 
 	var no = $(this).attr("data-no");
 	var state = $(this).attr("data-state");
 	var _URL = '';
 	if(state == 3){
 		if(safari){
			window.location.href = '${appPath}/app/inquiryReply/pay?orderAmount='+30+'&memberId='+memberId+'&orderId='+no;
			return;
		}else{
			_URL = '${appPath}/app/inquiryReply/pay?orderAmount='+30+'&memberId='+memberId+'&orderId='+no;
		}
	}else if(state == 4){
		_URL = "${mobilePath}/baofoo/pay?orderNo=" + no + "&type=0";
 	}
 	window.location.href = BASEPATH+"/wap/pay?url=" + new Base64().encode(_URL);
 }
</script>
[@main.footer/]
[/@main.body]