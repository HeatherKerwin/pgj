[#import "/common/main.ftl" as main]
[#include "/common/setting.ftl"]
[@main.body title='票管家']
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/common/flex.css"/>
<link rel="stylesheet" type="text/css" href="https://static.utiexian.com/css/website/manage.css"/>
[@main.header currentmenu='2'/]

<!--订单大厅 -->
<div class="flex flex-direction-column mt25 w1200 bc bcwhite mb30">
    <!--搜索-->
    <div class="flex pt20 w bc bcwhite flex-justify-space-between flex-alignItems-center lh45 pb20 box-shadow">
        <input type="text" id="company" class="h45 br5 w1000 bae0e0e0 ml40">
        <input type="button" class="h45 br5 w80 bae0e0e0 mr40 bcwhite" onclick="loadDate()" value="搜索" />
    </div>
    <!--没有订单-->
    <div class="flex mt25 w bc bcwhite h500 flex-justify-center flex-alignItems-center nocompany">
        <div class="flex-row">
            <img src="https://img.utiexian.com/website/discount/noMechanism.png" alt="" width="206" height="173">
        </div>
    </div>
    <!--有订单-->
    <div class="flex flex-direction-column bc pl25 pr25 pb25 company">
        <div class="w flex-row flex-direction-column mt30">
            <div class="flex-row flex-direction-column w bte0e0e0 ble0e0e0 bre0e0e0 mt16" id="content">
            </div>
             <div class="flex-row flex-direction-row-reverse">
		    	<div id="pager"></div>
		    </div>
        </div>
    </div>
</div>

<!--弹窗-->
<div class="w h pf bc05 zi10 top none" id="maskWindow">
    <div class="flex flex-alignItems-center flex-justify-center w h">
        <div class="flex-row flex-direction-column w590 bcwhite bae0e0e0 br3 pb20">
            <div class="flex-row bbe0e0e0 flex-justify-space-between flex-alignItems-center h57 lh57 pl20 pr20">
                <!--弹窗名称-->
                <div>确定指认机构</div>
                <!--关闭按钮-->
                <label for="closeIcon" id="closeBtn">
                    <img src="https://img.utiexian.com/website/PJGJ/authentication/closeIcon.png" alt="关闭">
                    <button id="closeIcon" class="oln none"></button>
                </label>
            </div>
            <!--弹窗内容-->
            <div class="pl20 pr20">
                <!--选择机构-->
                <div class="flex-row flex-direction-column w lh30 none" id="selectionWindow">
                    <div class="flex-row pl105 mt40">
                        公司名称：<span id="confirm_company"></span>
                    <span id="selectId"></span> 
                    </div>
                    <div class="flex-row pl105">
                        您是否指定该机构？
                    </div>
                    <!--按钮操作-->
                    <div class="flex-row flex-direction-row-reverse lh30 mt30">
                        <input type="button" value="确定" class="w90 h35 ba bad43c33 bcd43c33 cwhite br3 mr20 discountBtn">
                        <input type="button" value="取消" class="w90 h35 ba bae0e0e0 bcf2f2f2 c2d2d2d br3 mr20 closeBtn">
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>	      
[@main.footer/]
[/@main.body]
<script type="text/javascript" src="${pluPath}/data/laydate.js"></script>
<script type="text/javascript" src="${comPath}/handlebars.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/jquery-ajax-pager.js"></script>
<script type="text/javascript" src="${pluPath}/ajaxPager/js-extend.js"></script>
<script type="text/x-handlebars-template" id="COMPANY">
{{#each list}}
	<label class="pr selectionBtn" for="choseJG{{id}}" >
		<div class="flex-row flex-direction-row h50 lh50 bcf9f9f9 f14 tc bbe0e0e0 c717583">
			<div class="w300">机构名称</div>
			<div class="w250">联系人</div>
			<div class="w600">评分</div>
		</div>
		<div class="flex-row flex-direction-row lh40 pt10 pb8 tc bbe0e0e0 fb">
			<div class="flex-row flex-direction-row">
				<div class="w300 Rright"><span id="company{{id}}">{{company}}</span></div>
				<div class="w250 Rright"><span id="name{{id}}">{{name}}</span></div>
			</div>
			<div class="flex-row flex-direction-row w600">
				<div class="flex-col-4">价格真实：<span id="price{{id}}">{{toFixed price}}.toFixed(2)</span></div>
				<div class="flex-col-4">服务态度：<span id="service{{id}}">{{service}}</span></div>
				<div class="flex-col-4">确认效率：<span id="speed{{id}}">{{speed}}</span></div>
  			</div>
		</div>
		<div class="pa bottom right w25 h25">
			<input id="org_id{{id}}" type="hidden" value="{{org_id}}">
			<input type="radio" name="choseJG" id="choseJG{{id}}" data-id="{{id}}" class="choseJG">
		</div>
	</label>
{{/each}}
</script>

<script type="text/javascript">
	/**
	* 获取机构内容,加载数据
	*/
	function loadDate(){
		var company = $("#company").val();
		var data = {company:company};
		
		var pageIndex = 1;//分页
	    $("#pager").sjAjaxPager({
	        url: "${bootAppPath}/orginfo/search",
	        pageIndex:pageIndex,
	        myTotalType:"BOOT",
	        searchParam: data,
	        beforeSend: function () {
	        	
	        },success: function (data) {
	        	if(data.data.data.list.length >0){
	        		console.log(data);
	        		$(".nocompany").addClass("none");
	        		var source = $("#COMPANY").html();
		            var template = Handlebars.compile(source);
		            var html = template(data.data.data);
		            $("#content").html(html);
		            
		            $(".choseJG").live("click",selectionBtn);
	        	}
	        },complete: function(){
	        },error:function(data){
	        	if(data.state == 403){
	        		bootLogin(url,params,false,true)
	        	}else{
	        		alert("出现异常");
	        	}
	        }
	    });
	}
	//关闭按钮
	$("#closeBtn , .closeBtn").click(function(){
	    $("#maskWindow").addClass("none");
	    $("#selectionWindow").addClass("none"); //选择机构
	});
	//选择机构
	function selectionBtn(){
		var id = $(this).attr("data-id");
		$("#selectId").html(id);
		var company = $("#company"+id).html();
		$("#confirm_company").html(company);
		$("#maskWindow").removeClass("none");
	    $("#selectionWindow").removeClass("none");
	    $(".selectionBtn").removeClass("bad43c33");
	    $(this).parents("label").addClass("bad43c33");
	}
	//选择机构
	$(".discountBtn").click(function(){
		var id = $("#selectId").html();
		var company = $("#company"+id).html();
		var name = $("#name"+id).html();
		var price = $("#price"+id).html();
		var service = $("#service"+id).html();
		var speed = $("#speed"+id).html();
		var orgId = $("#org_id"+id).val();

		localStorage.setItem("company",company);
		localStorage.setItem("name",name);
		localStorage.setItem("price",price);
		localStorage.setItem("service",service);
		localStorage.setItem("speed",speed);
		localStorage.setItem("orgId",orgId);
				
		window.history.go(-1);
	});
</script>
