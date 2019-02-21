<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>价格修改</title>
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/skin-1.css" />
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/bootstrap.css"/>
<link rel="stylesheet"  href="${rc.contextPath}/commons/styles/bootstrap-datetimepicker.min.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${rc.contextPath}/commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${rc.contextPath}/commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/commons/scripts/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${rc.contextPath}/commons/scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<link rel="stylesheet" href="${rc.contextPath}/commons/styles/default.css" />

<!--编辑器-->
<!-- 
<link rel="stylesheet" href="${rc.contextPath}/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${rc.contextPath}/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="${rc.contextPath}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${rc.contextPath}/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${rc.contextPath}/kindeditor/plugins/code/prettify.js"></script>
 -->
<script type="text/javascript" src="${rc.contextPath}/commons/scripts/WebCalendar.js"></script>

<script>
	function chooseSubmitMethod(flag){
		if(confirm("确定执行操作吗")){
			$('#form1').submit();
		}
	}
	
	function checkForm(){
		return true;
	}
	function search(){
		location.href="${rc.contextPath}/currentprice/list/?type4="+$("#type4").val()+"&type3="+$("#type3").val()+"&type5="+$("#type5").val();
	}
</script>
</head>
<body>

<form method="post" action="${rc.contextPath}/currentprice/update/" onsubmit="return checkForm()" id="form1">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="${rc.contextPath}/images/sctg/mail_left_bg.gif"><img src="${rc.contextPath}/images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="${rc.contextPath}/images/sctg/content_bg.gif"><table width="100%" height="58" border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td height="31"><div class="title">价格修改 </div></td>
        </tr>
      </table></td>
    <td width="16" valign="top" background="${rc.contextPath}/images/sctg/mail_right_bg.gif"><img src="${rc.contextPath}/images/sctg/nav_right_bg.gif" width="16" height="29" /></td>
  </tr>
  <!-- 中间部分开始 -->
  
  <tr> 
    
    <!--第一行左边框-->
    <td valign="middle" background="${rc.contextPath}/images/sctg/mail_left_bg.gif">&nbsp;</td>
    <!--第一行中间内容-->
    <td valign="top" bgcolor="#F7F8F9"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <!-- 空白行-->
        <tr>
          <td colspan="2" valign="top">&nbsp;</td>
          <td>&nbsp;</td>
          <td valign="top">&nbsp;</td>
        </tr>
        <tr style="border:#CCC 1px solid; margin:6px 10px;">
          <td colspan="12"><!--<table class="title-1">
              <tr>
                <td ><h1 style="letter-spacing:3px;">价格修改 </h1></td>
              </tr>
            </table>-->
            
            <table class="title-2">
              <tr>
                <td  >&nbsp;</td>
                <td width="10%"  style="text-align:right;">类型</td>
                	<td width="20%">
                		<select name="type3" id="type3" onchange="search()">
					 <#if cp.type3==1>
						<option value="1" selected="selected">买断</option>
						<option value="2">带票</option>
					 <#elseif cp.type3==2>	
						<option value="1">买断</option>
						<option value="2" selected="selected">带票</option>	
					<#else>	
				 		<option value="1">买断</option>
						<option value="2">带票</option>	
					 </#if>
			</select>
                	</td>
                <td  width="20">&nbsp;</td>
                
                <td width="10%" style="text-align:right;">区域</td>
                	<td width="20%">
                			<select name="type4" id="type4" onchange="search()">
					 <#if cp.type4==1>
						 <option value="1" selected="selected">长三角</option>
					<#else>	
				 		 <option value="1">长三角</option>
					 </#if>
		   
		
	    </select>
                	</td>
                <td >&nbsp;</td>
                
                
                 <td  width="20">&nbsp;</td>
                
                <td width="10%" style="text-align:right;">期限</td>
                	<td width="20%">
                			<select name="type5" id="type5" onchange="search()">
				 	<#if cp.type5==1>
							<option value="1" selected="selected">3个月</option>
							<option value="2">6个月</option>
					 <#elseif cp.type5==2>	
						<option value="1">3个月</option>
						<option value="2" selected="selected">6个月</option>	
					<#else>	
				 		<option value="1">3个月</option>
						<option value="2">6个月</option>	
					 </#if>
				</select>	
                	</td>
                <td >&nbsp;</td>
                
              </tr>
            </table>
           <!-- <table class="title-3">
              <tr>
              	<td>&nbsp;</td>
                <td ><input class="btn" style=" width:100px; line-height:30px;" type="button" value="查询" onclick="query()" ></td>
                <td>&nbsp;</td>
              </tr>
            </table>-->
            </td>
        </tr>
        
        <tr >
        	<td>&nbsp;</td>
        </tr>
        
        <tr style="text-align:left; margin-top:6px;">
              <td ><input class="btn" style=" width:100px; line-height:30px;" type="button" value="修改" onclick="chooseSubmitMethod()" /></td>
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
        	<table width="1600px">
        		 <tr>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="280px" align="center">&nbsp;</td>
		</tr>
		<tr>
			<td rowspan="7" width="20px">500万以上</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="b1" style="width: 120px;" value="${hb1.price }"/></td>
			<td rowspan="7" width="20px">100万到500万</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="m1" style="width: 120px;" value="${hm1.price }"/></td>
			<td rowspan="7" width="20px">50万到100万</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="s1" style="width: 120px;" value="${hs1.price }"/></td>
			<td rowspan="7" width="20px">50万以下</td>
			<td width="100px" align="center">国股</td>
			<td width="280px"><input type="text" name="ss1" style="width: 120px;" value="${hss1.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="b2" style="width: 120px;" value="${hb2.price }"/></td>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="m2" style="width: 120px;" value="${hm2.price }"/></td>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="s2" style="width: 120px;" value="${hs2.price }"/></td>
			<td width="100px" align="center">城商</td>
			<td width="280px"><input type="text" name="ss2" style="width: 120px;" value="${hss2.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="b3" style="width: 120px;" value="${hb3.price }"/></td>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="m3" style="width: 120px;" value="${hm3.price }"/></td>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="s3" style="width: 120px;" value="${hs3.price }"/></td>
			<td width="100px" align="center">外资</td>
			<td width="280px"><input type="text" name="ss3" style="width: 120px;" value="${hss3.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="b4" style="width: 120px;" value="${hb4.price }"/></td>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="m4" style="width: 120px;" value="${hm4.price }"/></td>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="s4" style="width: 120px;" value="${hs4.price }"/></td>
			<td width="100px" align="center">农商</td>
			<td width="280px"><input type="text" name="ss4" style="width: 120px;" value="${hss4.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="b5" style="width: 120px;" value="${hb5.price }"/></td>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="m5" style="width: 120px;" value="${hm5.price }"/></td>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="s5" style="width: 120px;" value="${hs5.price }"/></td>
			<td width="100px" align="center">农合</td>
			<td width="280px"><input type="text" name="ss5" style="width: 120px;" value="${hss5.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="b6" style="width: 120px;" value="${hb6.price }"/></td>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="m6" style="width: 120px;" value="${hm6.price }"/></td>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="s6" style="width: 120px;" value="${hs6.price }"/></td>
			<td width="100px" align="center">农信</td>
			<td width="280px"><input type="text" name="ss6" style="width: 120px;" value="${hss6.price }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="b7" style="width: 120px;" value="${hb7.price }"/></td>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="m7" style="width: 120px;" value="${hm7.price }"/></td>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="s7" style="width: 120px;" value="${hs7.price }"/></td>
			<td width="100px" align="center">村镇</td>
			<td width="280px"><input type="text" name="ss7" style="width: 120px;" value="${hss7.price }"/></td>
		</tr>
        	
        	
        	</table>
        </td>
        </tr>
        
       
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
          <td width="51%" class="left_txt"><img src="${rc.contextPath}/images/sctg/icon_mail.gif" width="16" height="11"> 客户服务邮箱：ryfinance@ryfinance.com<br />
            <img src="${rc.contextPath}/images/sctg/icon_phone.gif" width="17" height="14"> 官方网站：<a href="https://www.utiexian.com/" target="_blank">睿银金融服务有限公司</a></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table></td>
    <td background="${rc.contextPath}/images/sctg/mail_right_bg.gif">&nbsp;</td>
  </tr>
  <!-- 底部部分 -->
  <tr>
    <td  valign="bottom" background="${rc.contextPath}/images/sctg/mail_left_bg.gif"><img src="${rc.contextPath}/images/sctg/buttom_left.gif" width="17" height="17" /></td>
    <td   background="${rc.contextPath}/images/sctg/buttom_bgs.gif"><img src="${rc.contextPath}/images/sctg/buttom_bgs.gif" width="17" height="17"></td>
    <td  valign="bottom" background="${rc.contextPath}/images/sctg/mail_right_bg.gif"><img src="${rc.contextPath}/images/sctg/buttom_right.gif" width="16" height="17" /></td>
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