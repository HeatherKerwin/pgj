[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header currentmenu='3'/]

<link rel="stylesheet" href="${staticPath}/css/rywap/reason.css"/>
<div class="wrapper">
    <div class="reasonSelect">
        <label class="fl" for="select">请选择拒绝理由</label>
        <select name="cancel" id="select" class="fr">
            <option value="0" selected="selected">票面信息输入有误</option>
            <option value="1">只为熟悉流程和询问价格</option>
            <option value="2">价格不合适</option>
            <option value="3">已提前出票</option>
            <option value="4">其它</option>
        </select>
    </div>
    <input id="orderflag" type="hidden" name="currentState" value="${orderflag}" />
    <input id="id" type="hidden" name="id" value="${id}" />
    <textarea name="reason" id="textarea" class="hide" placeholder="请您输入不少于15字的理由..."></textarea>
    <a class="Btn" href="javascript:void(0)">
        <input type="button" id="cancel" value="确定取消订单"/>
    </a>
</div>	    
<script>
    /*选择其他填写理由*/
    $("#select").on("click",function(){
        var thisIndex = $("#select").val();
        if ("4" == thisIndex) {
            $("#textarea").removeClass("hide");
        } else{
            $("#textarea").addClass("hide");
        }
    });
    
    /**
    * 提交取消订单
    */
    $("#cancel").click(function(){
    	var cancel = $("#select").val();
    	var reason = $("#textarea").val();
    	var id = $("#id").val();
    	var currentState = $("#orderflag").val();
    	if(cancel == 4 && $.trim(reason) == 0){
    		myToast("取消理由不能为空");
    		return;
    	}
    	$.ajax({
			url:"${basePath}/wap/discountrecordsp/cancel",
			type:"POST",
			data:{"id":id,"currentState":currentState,"cancel":cancel,"reason":reason},
			dataType:"json",
			success:function(data){
				if(data.response == "success"){
					mySuccess(data.msg,success);
				}else{
					myError(data.msg);
				}
			}
		});
    });
    
    /**
    *	取消成功，跳转到订单列表
    */
    function success(){
    	window.location.href="${basePath}/wap/discountrecordsp/page";
    }
</script>

[@main.footer/]
[/@main.body]