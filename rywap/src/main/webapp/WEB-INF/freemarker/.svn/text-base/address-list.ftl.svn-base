[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]

[@main.body title="票据管家"]
[@main.header title='贴现地址'/]

<link rel="stylesheet" href="${staticPath}/css/rywap/discount.css">
<div class="wrapper mt44">
[#if address??]
	<ul class="placeList">
	[#list address as add]
	<li>
        <div class="ADDRESS plaec" data-id="${add.id}">
            <div class="plaecLeft"></div>
            <div class="pelacShow">
                <div class="row">
                    <h2 class="flex1">${add.name}</h2>
                    <div class="flex1">
                    	[#if add.sex==1]
							先生
						[#else]
							女士
						[/#if]
                    </div>
                    <div class="flex2">${add.mobile}</div>
                </div>
                <div class="outH font12">${add.address} ${add.other}</div>
            </div>
        </div>
        <div class="placeEdit row">
            <div class="flex1">
                <input type="radio" id="chose-${add.id}" name="setDefault" class="radioIcon2 [#if add.state==0]onRadio[/#if]" [#if add.state==0]checked[/#if] value="${add.id}"/>
                <label for="chose-${add.id}">默认地址</label>
            </div>
            <a href="javascript:void(0);" class="EDIT flex1 dib editBtn">
                <img src="${imagePath}/rywap/discount/edit.png" class="vam"/>
                <label for="edit">编辑</label>
            </a>
            <a href="javascript:void(0);" class="DELETE flex1 dib deleteBtn">
                <img src="${imagePath}/rywap/discount/delete.png" class="vam"/>
                <label for="delete">删除</label>
            </a>
        </div>
    </li>
	[/#list]
	</ul>
[#else]
	<div class="tac font16 cgray">
	    <img src="${imagePath}/rywap/discount/address.png" alt="" class="vam noPlace"/>
	    <p>您还没有添加任何贴现地址呐！</p>
	</div>
[/#if]
<a href="javascript:void(0);" class="ADDADDRESS Btn mt10"><input type="button" value="新增地址"/></a>
</div>
<script type="text/javascript">
var memberId;
$(document).ready(function(){
	memberId = "${member.id}";//获取登录用户主键
	
	$(".ADDRESS").on("click",function(){
		var id = $(this).attr("data-id");

		var u = getLocalAndClean("CALLBACKURL");
		if(u!=null && $.trim(u).length>0){
			localStorage.setItem("ADDRESSID",id);//保存缓存
			window.location.href = BASEPATH + u;
		}
	});
	
	$(".DELETE").on("click",function(){
		var id = $(this).parents("div").prev().attr("data-id");
		var tag = $(this).parents("div").parents("li");
		$.ajax({
			url:BASEPATH+"/wap/address/del",
			type:"POST",
			data: {"id":id},
			dataType:"json",
			success:function(data){
				if(data.response=="success"){
					tag.remove();
				}else{
					myError(data.msg);
				}
			}
		});
	});
	
	$(".EDIT").on("click",function(){
		var id = $(this).parents("div").prev().attr("data-id");
		window.location.href = BASEPATH + "/wap/address/get?addressId=" + id;
	});
	
	$(".ADDADDRESS").on("click",function(){
		window.location.href = BASEPATH + "/wap/address/get";
	});
});

/**
 * 点击选择指定地址，回到调用页面
 */
$('input[name="setDefault"]').change(function(){
	$('input[name="setDefault"]').removeClass("onRadio");
	
	var de = $('input[name="setDefault"]:checked').val();
	var c = $("input[name='setDefault'][value='"+ de +"']");
	c.addClass("onRadio");
	
	var params = {
    		id:de
    	};
    if(memberId!=null && $.trim(memberId).length>0){
    	params.memberId = memberId;
    }
    $.ajax({
		url:BASEPATH+"/wap/address/set/default",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(data){
			if(data.response!="success"){
				myError(data.msg,pageLoad);
			}
		}
	});
});

/**
 * 重新加载
 */
function pageLoad(){
	window.location.reload();
}
</script>

[@main.footer/]
[/@main.body]