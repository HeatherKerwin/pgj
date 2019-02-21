<#import "/common/data.ftl" as data>
<#import "/common/function.ftl" as fun>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>节假日管理</title>
	<link rel="stylesheet" type="text/css" href="../css/skin-1.css" />
	<link rel="stylesheet" href="../commons/styles/reset.css" />
	<link rel="stylesheet" href="../commons/styles/bootstrap.css"/>
	<link rel="stylesheet" href="../commons/styles/default.css" />
	
	<link rel="stylesheet" type="text/css" href="../css/notice/notice.css">
	<link rel="Stylesheet" type="text/css" href="../css/notice/notice-dialog.css" />
	<script type="text/javascript" src="../js/laydate.js"></script>
	
	<script src="../commons/scripts/jquery-1.10.1.min.js"></script>
	<script src="../commons/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="../commons/scripts/WebCalendar.js"></script>
</head>
<script type="text/javascript">
function textCounter(field, countfield, maxlimit) {
	if ($("#"+field).val().length > maxlimit){
		$("#"+field).val($("#"+field).val().substring(0, maxlimit));
	}else{
		$("#"+countfield).val(maxlimit - $("#"+field).val().length);
	}
}

$(function ($) {//弹窗
	//关闭
	$(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
		$("#LoginBox1,#LoginBox2,#LoginBox3").fadeOut("fast");
		$("#mask").css({ display: 'none' });
	});
});

function add() {
	document.getElementById("holidayTips").reset();
	$("body").append("<div id='mask'></div>");
	$("#mask").addClass("mask").fadeIn("slow");
	$("#LoginBox3").fadeIn("slow");
}

/**
 * 获取节假日
 */
function getNotice(id){
	$(".NOTICEADD").remove();//打开面板时删除之前加载的补班
	
	var url = "${rc.contextPath}/tiexian/findall/get?id="+id;
	$.ajax({
		url:url,
		type:"post",
		dataType: "json",
		data:{"id":id},
		success:function(data){
			if(data.response=="success"){
				var notice = data.notice;
				$("#id").val(notice.id);
				$("#start").val(notice.startDate.split(" ")[0]);
				$("#end").val(notice.endDate.split(" ")[0]);
				$("#name").val(notice.name);
				$("#content").val(notice.content);
				$("#alert").val(notice.alert);
				$("#remark").val(notice.remark);
				
				var adds = data.noticeAdds;
				for (var i=0;i<adds.length;i++){
					var a = adds[i];
					addNoticeAdd(a.id,a.day);
				}
				
				textCounter('content','remLen',30);
				textCounter('alert','remLen_',30);
				textCounter('remark','remLen__',30);
			}else{
				alert(data.msg);
			}
		}
	});
	$("body").append("<div id='mask'></div>");
	$("#mask").addClass("mask").fadeIn("slow");
	$("#LoginBox3").fadeIn("slow");
}

/**
 * 删除节假日补班
 */
function delNotice(id){
	if(confirm("确定删除吗?")){
		var url = "${rc.contextPath}/tiexian/findall/del?id="+id;
		$.ajax({
			url:url,
			type:"post",
			dataType: "json",
			data:{"id":id},
			success:function(data){
				if(data.response=="success"){
					turnOverPage(1);
				}else{
					alert(data.msg);
				}
			}
		});
	}
}

/**
 * 删除补班
 */
function delNoticeAdd(tag){
	var id = $(tag).attr("data-id");
	if(id!=null && id!=""){
		$.ajax({
			url:"${rc.contextPath}/tiexian/findall/noticeadd/del",
			type:"post",
			dataType: "json",
			data:{"id":id},
			success:function(data){
				if(data.response=="success"){
					$(tag).parent().remove();
				}else{
					alert(data.msg);
				}
			}
		});
	}
}

var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
function generateMixed(n) {
     var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*35);
         res += chars[id];
     }
     return res;
}

/**
 * 添加补班
 */
function addNoticeAdd(i,day){
	var id = generateMixed(6);
	var html_ = "";
	html_ += '<div class="NOTICEADD" style="margin-bottom:3px;">';
	html_ += '<input type="hidden" name="notice_add_id" value="'+ i +'">';
	html_ += '<input id="'+ id +'" name="notice_add_day" value="'+ day +'" class="laydate-icon" style="width:120px;" onclick="laydate({elem:\'#'+ id +'\',istime:true,istoday:true,format:\'YYYY-MM-DD\'});">';
	html_ += '<a href="javascript:void(0);" onclick="delNoticeAdd(this);" data-id="'+ i +'" style="color:red;">删除</a>';
	html_ += '</div>';
	$("#add-btn").before(html_);
}
</script>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="17" valign="top" background="../images/sctg/mail_left_bg.gif"><img src="../images/sctg/left_top_right.gif" width="17" height="29" /></td>
	    <td valign="top" background="../images/sctg/content_bg.gif">
	    	<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" >
		        <tr>
		          <td height="31"><div class="title">节假日管理</div></td>
		        </tr>
	    	</table>
	    </td>
	    <td width="16" valign="top" background="../images/sctg/mail_right_bg.gif"><img src="../images/sctg/nav_right_bg.gif" width="16" height="29" /></td>
	 </tr>
	 
	 <tr>
	    <td valign="middle" background="../images/sctg/mail_left_bg.gif">&nbsp;</td>
	    
	    
	    <td valign="top" bgcolor="#F7F8F9">
	    <div style="padding:5px 0px;background-color:#E8E8E8">
	    	<form action="${rc.contextPath}/tiexian/findall/save" method="post">
	    		<input type="hidden" name="id" value="${notice.id}">
	    		<input type="hidden" name="code" value="OFFDAY">
				<h4 style="padding-left:10px;">非工作日</h4>
				<table class="title-2">
					<tr>
		                <td width="20%" style="text-align:right;">提示语</td>
		                <td width="60%" align="left">
		                	<input type="text" name="content" size="30" style="width:400px" value="${notice.content}"/>
		                </td>
		                <td width="20%" style="text-align:right;">&nbsp;</td>
					</tr>
					<tr>
		                <td width="20%" style="text-align:right;">弹出框</td>
		                <td width="60%" align="left">
		                	<input type="text" name="alert" size="30" style="width:400px" value="${notice.alert}"/>
		                </td>
		                <td width="20%" style="text-align:right;">&nbsp;</td>
					</tr>
					<tr>
		                <td width="20%" style="text-align:right;">&nbsp;</td>
		                <td width="60%" align="left">
		                	<input class="btn" type="submit" value="保存">
		                </td>
		                <td width="20%" style="text-align:right;">&nbsp;</td>
					</tr>
				</table>
	        </form>
	    </div>
        <form id="form" action="${rc.contextPath}/tiexian/findall" method="post">
			<table class="title-2">
				<tr>
	                <td width="20%" style="text-align:right;">节日名称</td>
	                <td width="30%">
	                	<input type="text" id="name" name="name"/>
	                </td>
	                <td>
		                <input class="btn" type="submit" value="查询">
	                </td>
				</tr>
			</table>
        </form>
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	        	<td colspan="2" valign="top">&nbsp;</td>
	        	<td>&nbsp;</td>
				<td valign="top">&nbsp;</td>
	        </tr>
	        <tr style="text-align:left;">
	        	<td>
	        		<input class="btn" style="width:100px;line-height:30px;margin-top:5px;" type="button" value="新增节假日" onclick="add();"/>
	        	</td>
	        </tr> 
	        <tr>
				<td height="40" colspan="4">
					<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC"></table>
				</td>
	        </tr>
	        <tr>
	          	<td width="2%">&nbsp;</td>
	          	<td width="96%">
	          	<table class="title-6" style="margin-left:8%;" width="80%"  >
					<tr>
						<td colspan="2">
							<table class="cont tr_color" style="width:80%;table-layout:fixed;">
								<tr style="">
									<th>名称</th>
									<th>开始日期</th>
									<th>结束日期</th>
									<th>弹窗提示</th>
									<th>滚动提示</th>
									<th>操作</th>
								</tr>
								<#if pageModel??>
								<#list pageModel.results as var>                     
									<tr align="center" class="d">
				                        <td title="${var.name}">${var.name}</td>
				                        <td>
					                        <#if var.startDate??>
												${var.startDate?string("yyyy-MM-dd")}
											</#if>
				                        </td>
				                        <td>
				                        	<#if var.endDate??>
												${var.endDate?string("yyyy-MM-dd")}
											</#if>
				                        </td>
			                        	<td title="${var.alert}">${var.alert}</td>
				                        <td class="dd" title="${var.content}">${var.content}</td> 
				                        <td class="dd">
				                        	<a href="javascript:void(0);" onclick="getNotice('${var.id}');"><span style="color:blue;">修改</span></a>
				                        	<a href="javascript:void(0);" onclick="delNotice('${var.id}');"><span style="color:red;">删除</span></a>
				                        </td>                                                
			                    	</tr>
								</#list>
								</#if>    
							</table>
						</td>
					</tr>
				</table>
				</td>
	          	<td width="2%">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="7">
					<div id="pager">
						<#import "/common/pager.ftl" as q>
				  		<#if pageModel.totalCount??>
							<@q.pager pageNo=pageModel.currentPage pageSize=pageModel.pageSize recordCount=pageModel.totalCount toURL="${rc.contextPath}/tiexian/findall"/>
				  		</#if>
				    </div>	
			    </td>
	       	</tr> 
	        <tr>
	        	<td height="40" colspan="4">
		          	<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC"></table>
	        	</td>
	        </tr>
			<tr>
				<td height="30px"></td>
	        </tr>
			<tr>
				<td width="2%">&nbsp;</td>
				<td width="51%" class="left_txt">
					<img src="../images/sctg/icon_mail.gif" width="16" height="11"> 客户服务邮箱：ryfinance@ryfinance.com<br/>
					<img src="../images/sctg/icon_phone.gif" width="17" height="14"> 官方网站：<a href="https://www.utiexian.com/" target="_blank">睿银金融服务有限公司</a>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	    <td background="../images/sctg/mail_right_bg.gif">&nbsp;</td>
	</tr>
	<tr>
		<td valign="bottom" background="../images/sctg/mail_left_bg.gif"><img src="../images/sctg/buttom_left.gif" width="17" height="17" /></td>
	    <td background="../images/sctg/buttom_bgs.gif"><img src="../images/sctg/buttom_bgs.gif" width="17" height="17"></td>
	    <td valign="bottom" background="../images/sctg/mail_right_bg.gif"><img src="../images/sctg/buttom_right.gif" width="16" height="17" /></td>
	</tr>
	<tr style="height:10px;">
		<td>&nbsp;</td>
	</tr>
</table>


<div id="LoginBox3">
	<div class="row1">
		节假日贴现提示
		<a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">
			×
		</a>
	</div>
	<form id="holidayTips" method="post" action="${rc.contextPath}/tiexian/findall/save">
		<input type="hidden" id="id" class="id" name="id" value="">
		<input type="hidden" class="code" name="code" value="FESTIVAL">
		<input type="hidden" class="pageName" name="pageName" value="prompt"/>
		<div class="demo3" style="margin-top: 10px;">
			<p class="in-l fl">
				&nbsp;名称：
			</p>
			<input id="name" class="in-t1" size="12" name="name" style="border:1px;">
			<div class="clear"></div>
			<ul class="inline">
				<li style="font-size:16px;float:left;width:80px;margin-left:30px;">
					<span style="margin-top: 10px;">开始日：</span>
				</li>
				<li style="width:200px;margin:0 36px;float:left;">
					<input class="laydate-icon" id="start" style="width: 185px;" name="start" readonly="readonly" onclick="laydate({elem:'#start',istime:true,istoday:true,max:$('#end').val(),format: 'YYYY-MM-DD'});" value="">
				</li>
				<li style="font-size:16px;float:left;width:80px;margin-left:30px;">
					结束日：
				</li>
				<li style="width:200px;margin:0 36px;float:left;margin-top: 10px;margin-bottom: 10px;">
					<input class="laydate-icon" id="end" name="end" style="width: 185px;" readonly="readonly" onclick="laydate({elem:'#end',istime:true,istoday:true,min:$('#start').val(),format: 'YYYY-MM-DD'});" value="">
				</li>
			</ul>
		</div>
		<p class="in-l fl">
			&nbsp;补班日：
		</p>
		<div class="in-tt1" style="margin-bottom: 10px;">
			<div id="add-btn" style="width:220px;">
				<a href="javascript:void(0);" onclick="addNoticeAdd('','');" style="color:blue;">添加</a>
			</div>
		</div>
		<p class="in-l fl">
			&nbsp;提示语：
		</p>
		<textarea class="in-t1 fl" id="content" name="content" cols="28" rows="2" oninput="textCounter('content','remLen',30);"></textarea>
		<div class="clear"></div>
		<p class="in-tt1">
			您还可以输入:
			<input id="remLen" name="remLen" type="text" value="30" size="15" style=" width: 70px;height: 15px;" readonly>
			个字符
		</p>
		<p class="in-l fl">
			&nbsp;弹出框：
		</p>
		<textarea class="in-t1" id="alert" name="alert" cols="28" rows="2" oninput="textCounter('alert','remLen_',30);"></textarea>
		<p class="in-tt1">
			您还可以输入:
			<input id="remLen_" name="remLen" type="text" value="50" size="15" style=" width: 70px;height: 15px;" readonly>
			个字符
		</p>
		<p class="in-l fl">
			&nbsp;备&nbsp;&nbsp;&nbsp;注：
		</p>
		<textarea class="in-t1" id="remark" name="remark" cols="28" rows="2" oninput="textCounter('remark','remLen__',30);"></textarea>
		<p class="in-tt1">
			您还可以输入:
			<input id="remLen__" name="remLen" type="text" value="50" size="15" style=" width: 70px;height: 15px;" readonly>
			个字符
		</p>
		<div class="row" style="margin-left: 45px;">
			<input type="submit" value="提交" class="loginbtn">
		</div>
	</form>
</div>
</body>
</html>