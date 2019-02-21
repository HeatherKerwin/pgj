<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>贴现额度</title>
<link rel="stylesheet" type="text/css" href="../../css/skin-1.css" />
<link rel="stylesheet" href="../../commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="../../commons/styles/bootstrap.css"/>
<link rel="stylesheet"  href="../../commons/styles/bootstrap-datetimepicker.min.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="../../commons/scripts/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../commons/scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<link rel="stylesheet" href="../../commons/styles/default.css" />

<!--编辑器-->
<!-- 
<link rel="stylesheet" href="../../kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="../../kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="../../kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="../../kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="../../kindeditor/plugins/code/prettify.js"></script>
 -->
<script type="text/javascript" src="../../commons/scripts/WebCalendar.js"></script>

<script>
	function chooseSubmitMethod(flag){
		if(confirm("确定执行操作吗")){
			<!-- $('#form1').submit(); -->
			location.href="../../discountrecord/daylimitupdate/?id="+$("input[name=id]").val()+"&limitprice="+$("input[name=limitprice]").val()+"&day="+$('#day').val();
		}
	}
	
	function checkForm(){
		return true;
	}
	
	function query(){
		window.location.href = "../../discountrecord/daylimitlist/?day="+$('#day').val();
	}
	
	function changeTable(){
	 var updateButton = document.getElementById("updateButton");
	 var addButton = document.getElementById("addButton");
	 var addSubmit = document.getElementById("addSubmit");
	 var showTable =  document.getElementById("showTable");
	 var addTable =  document.getElementById("addTable");
		if(showTable != null){
			showTable.style.display="none";
		}
		if(addTable != null){
			 addTable.style.display="";
		}
		if(updateButton != null){
			updateButton.type ="hidden";
		}
		if(addButton != null){
			addButton.type ="hidden";
		}
		addSubmit.type ="button";
	}
	
	function addQuota(){
		var addLimitprice = $("#addLimitprice").val();
		var day = $("#addDay").val();
		location.href="../../discountrecord/daylimitadd/?day="+day+"&limitprice="+addLimitprice;
	}
	
</script>
</head>
<body>

<form method="post" action="../../discountrecord/daylimitupdate/" onsubmit="return checkForm()" id="form1">
<input type="hidden" name="id" value="${daylimit.id }"/>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../../images/sctg/content_bg.gif"><table width="100%" height="58" border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td height="31"><div class="title">贴现额度 </div></td>
        </tr>
      </table></td>
    <td width="16" valign="top" background="../../images/sctg/mail_right_bg.gif"><img src="../../images/sctg/nav_right_bg.gif" width="16" height="29" /></td>
  </tr>
  <!-- 中间部分开始 -->
  
  <tr> 
    
    <!--第一行左边框-->
    <td valign="middle" background="../../images/sctg/mail_left_bg.gif">&nbsp;</td>
    <!--第一行中间内容-->
    <td valign="top" bgcolor="#F7F8F9"><table width="1380px;" border="0" align="center" cellpadding="0" cellspacing="0">
        <!-- 空白行-->
        <tr>
          <td colspan="2" valign="top">&nbsp;</td>
          <td>&nbsp;</td>
          <td valign="top">&nbsp;</td>
        </tr>
        <tr style="border:#CCC 1px solid; margin:6px 10px;">
          <td colspan="12"><!--<table class="title-1">
              <tr>
                <td ><h1 style="letter-spacing:3px;">贴现额度 </h1></td>
              </tr>
            </table>-->
            
            <table class="title-2">
              <tr>
                <td  >&nbsp;</td>
                <td width="40%"  style="text-align:right;">日期</td>
                 <#if day??>
                	<td width="60%"><input type="text" id="day" value="${day}" onclick="SelectDate(this,'yyyy-MM-dd')" readonly /></td>
                <#else>
                	<td width="20%"><input type="text" id="day" value="日期" onclick="SelectDate(this,'yyyy-MM-dd')" readonly /></td>
                </#if>
                <td >&nbsp;</td>
                
              </tr>
            </table>
           <table class="title-3">
              <tr>
              	<td>&nbsp;</td>
                <td ><input class="btn" style=" width:100px; line-height:30px;" type="button" value="查询" onclick="query()" ></td>
                <td>&nbsp;</td>
              </tr>
            </table>
            </td>
        </tr>
        
        <tr >
        	<td>&nbsp;</td>
        </tr>
        
        <tr style="text-align:left; margin-top:6px;">
              <td ><input class="btn" style=" width:100px; line-height:30px;" type="button"  id="addButton" value="新增额度" onclick="changeTable()" />
              	   <input class="btn" style=" width:100px; line-height:30px;" type="hidden"  id="addSubmit" value="添加" onclick="addQuota()" />
              	<#if flag==1>
              		<input class="btn" style=" width:100px; line-height:30px;" type="button" id="updateButton" value="修改" onclick="chooseSubmitMethod()" />
               <#else>
               		
               </#if>
               
              </td>
        </tr> 
        
        <!-- 一条线 --> 
        <!-- 一条线 -->
        <tr>
          <td height="40" colspan="">
        	<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC"></table>
         </td>
        </tr>
        <!-- 产品列表开始 -->
        <tr>
        	<td colspan="6">
        	<#if flag==1>
        			<table width="100%" id="showTable">
	        			<tr>
	        				<td>额 &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;度：</td>
	        				<td><input type="text" name="limitprice" style="width: 220px;" value="${daylimit.limitprice }"/>万元</td>
	        			</tr>
	        			<tr>
	        				<td>已用额度：</td>
	        				<td><input type="text" name="allmoneytotal" style="width: 220px;" value="${allmoneytotal }"/>万元</td>
	        			</tr>
        			</table>
        	<#else>
        	
        	</#if>
        	</td>
        </tr>
        	<td colspan="6">
        	<table width="100%" id="addTable" style="display: none">
        			<tr>
        				<td>日&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;期：</td>
        				<td><input type="text" id="addDay" value="日期" style="width: 220px;"  onclick="SelectDate(this,'yyyy-MM-dd')" readonly /></td>
        			</tr>
        			<tr>
        				<td>额&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;度：</td>
        				<td><input type="text" id="addLimitprice" style="width: 220px;" value=""/>万元</td>
        			</tr>
        	</table>
       	</td>
        <!-- 产品列表结束 -->
        
        <!-- 一条线 -->
        <tr>
          <td height="40" colspan="12"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              
            </table></td>
            
        </tr>
        <tr >
          <td height="30px"></td>
        </tr>
        <tr >
          <td width="2%">&nbsp;</td>
          <td width="51%" class="left_txt"><img src="../../images/sctg/icon_mail.gif" width="16" height="11"> 客户服务邮箱：ryfinance@ryfinance.com<br />
            <img src="../../images/sctg/icon_phone.gif" width="17" height="14"> 官方网站：<a href="https://www.utiexian.com/" target="_blank">睿银金融服务有限公司</a></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table></td>
    <td background="../../images/sctg/mail_right_bg.gif">&nbsp;</td>
  </tr>
  <!-- 底部部分 -->
  <tr>
    <td valign="bottom" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/buttom_left.gif" width="17" height="17" /></td>
    <td background="../../images/sctg/buttom_bgs.gif"><img src="../../images/sctg/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="../../images/sctg/mail_right_bg.gif"><img src="../../images/sctg/buttom_right.gif" width="16" height="17" /></td>
  </tr>
</table>

<!-- 产品列表结束 --> 
<!-- 分页按钮--> 
<!-- 底部部分 -->

<tr style="height:10px;">
  <td>&nbsp;</td>
</tr>
</from>
</body>
</html>