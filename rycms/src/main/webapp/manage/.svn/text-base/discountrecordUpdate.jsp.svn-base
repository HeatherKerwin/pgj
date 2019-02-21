<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ry.core.entity.Discountrecord"%>    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="<%=path %>/commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=path %>/commons/styles/bootstrap.css"/>
<link rel="stylesheet"  href="<%=path %>/commons/styles/bootstrap-datetimepicker.min.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=path %>/commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=path %>/commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/commons/scripts/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path %>/commons/scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>


<link rel="stylesheet" href="<%=path %>/commons/styles/default.css" />

<script src="<%=path %>/commons/scripts/ajaxupload.js"></script>
<!-- 上传组件Uploadify -->
<script src="<%=path %>/commons/scripts/jquery.uploadify.js"></script>

<script type="text/javascript" src="<%=path%>/commons/scripts/WebCalendar.js"></script>

<!--编辑器-->
<link rel="stylesheet" href="<%=path %>/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="<%=path %>/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=path %>/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="<%=path %>/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=path %>/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6WIEK15yVVcchWSWBg1GVSx8"></script>

<script>
	function chooseSubmitMethod(flag){
		var flag1 = $("#flag1").val();
		if(flag1=='-1'|| flag1 == '0'){
			var reason = $("#reasondesc").val();
			if(reason == null || reason.trim().length==0){
				alert("请填写原因");
				return;
			}
		}
		if(confirm("确定执行操作吗")){
			$("#my-form").submit();
		}
	}
	
	function checkForm(){
		return true;
	}
	
	function flag2Click() {
		$("#reasondesc").val('');
		if ($("#flag2").find("option:selected").val() == 0) {
			$("div[name='reasonDiv']").show();			
		} else {
			$("#flag3").val(-1);
			$("div[name='reasonDiv']").hide();	
			$("div[name='reasondescDiv']").hide();			
		}
	}
	
	function flag3Click() {
		$("#reasondesc").val('');
		if ($("#flag3").find("option:selected").val() == 5) {
			$("div[name='reasondescDiv']").show();	
		} else {			
			$("div[name='reasondescDiv']").hide();			
		}
	}
	
	$(function(){
		
		var confirmflag = '${discountrecord.confirmflag}';

		if (confirmflag > 0) {
			$("div[name='reasonDiv']").hide();	
		} else {
			$("div[name='reasonDiv']").show();
		}
		
		var reasonflag = '${discountrecord.reasonflag}';
		if (reasonflag < 5) {
			$("div[name='reasondescDiv']").hide();	
		} else {
			$("div[name='reasondescDiv']").show();	
		}
		
	});
</script>
</head>


<body>

<c:if test="${discountrecord.orderflag==1 }"><font color="red">待确认</font>>>待验票>>待收款>>已完成</c:if>
<c:if test="${discountrecord.orderflag==2 }">待确认>><font color="red">待验票</font>>>待收款>>已完成</c:if>
<c:if test="${discountrecord.orderflag==4 }">待确认>>待验票>><font color="red">待收款</font>>>已完成</c:if>
<c:if test="${discountrecord.orderflag==3 }">待确认>>待验票>>待收款>><font color="red">已完成</font></c:if>
<c:if test="${discountrecord.orderflag==-1 }">订单失败</c:if>
<c:if test="${discountrecord.orderflag==0 }">订单取消</c:if>

<div id="edit" style="margin-top:15px;">
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>下单人</label></div>
			<div class="">
				${member.mobile }
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>类型</label></div>
			<div class="">
				&nbsp;<c:if test="${discountrecord.type1==1 }">纸票</c:if><c:if test="${discountrecord.type1==2 }">电票</c:if>
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>类型</label></div>
			<div class="">
				&nbsp;${discountrecord.type2show}
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>出票时间</label></div>
			<div class="">
				&nbsp;${discountrecord.begintime }
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>到期时间</label></div>
			<div class="">
				&nbsp;${discountrecord.endtime }
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>地方</label></div>
			<div class="">
				&nbsp;${discountrecord.place }
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>总额</label></div>
			<div class="">
				&nbsp;${discountrecord.allmoney }
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>红包金额</label></div>
			<div class="">
				&nbsp;${hongbaojine}
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>联系号码</label></div>
			<div class="">
				&nbsp;${discountrecord.membermobile }
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>联系人</label></div>
			<div class="">
				&nbsp;${discountrecord.membername }
			</div>
		</div>
	</div>
	<br/>
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>图片</label></div>
			<div class="">			
			<%     				
				Discountrecord dis = (Discountrecord)request.getAttribute("discountrecord");	
				if(dis.getPicpath() != null && dis.getPicpath() != "") {
					String picpaths = dis.getPicpath();
					String[] pics = picpaths.split(",");
				    request.setAttribute("pics", pics);				    	
				}
				
			%>
			<c:forEach items="${pics}" var="item">		           
		           <img src="${item}"/>
		    </c:forEach>
				
			</div>
		</div>
	</div>
	<br/>	
	<div id="edit-main">
		<div class="edit-box">
			<div class="label"><label>备注</label></div>
			<div class="">
				&nbsp;${discountrecord.memberother }
			</div>
		</div>
	</div>
	<br/>
	<form id="my-form" action="<%=path%>/discountrecord/update/" method="post">
		<input type="hidden" name="discountrecordid" value="${discountrecord.id}"/>
		<input type="hidden" name="hongbaoid" value="${hongbaoid}"/>
		<div id="edit-main">
			<div class="edit-box">
				<div class="label"><label>状态</label></div>
				<div class="">
					<select id="flag1" name="flag1">
						<option value="-1" <c:if test="${discountrecord.orderflag==-1 }">selected="selected"</c:if>>订单失败</option>
						<option value="0" <c:if test="${discountrecord.orderflag==0 }">selected="selected"</c:if>>无效订单</option>
						<option value="1" <c:if test="${discountrecord.orderflag==1 }">selected="selected"</c:if>>待确认</option>
						<option value="2" <c:if test="${discountrecord.orderflag==2 }">selected="selected"</c:if>>待验票</option>					
						<option value="4" <c:if test="${discountrecord.orderflag==4 }">selected="selected"</c:if>>待收款</option>
						<option value="3" <c:if test="${discountrecord.orderflag==3 }">selected="selected"</c:if>>已完成</option>
					</select>
				</div>
			</div>
		</div>
		
		<div id="edit-main">
			<div class="edit-box">
				<div class="label"><label>原因描述</label></div>
				<div class="">
					<textarea id="reasondesc" name="reasondesc">${discountrecord.reasondesc }</textarea>
				</div>
			</div>
		</div>
	</form>
</div>
<div id="bottom">
  <div class="left">
		<button type="button" class="btn btn-success" id="publish" onclick="chooseSubmitMethod(1)"> 修改 </button>
	</div>
</div>
</body>
</html>