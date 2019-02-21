<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>历史价格</title>
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
			$('#form1').submit();
		}
	}
	
	function checkForm(){
		return true;
	}
	function search(){
		location.href="../../historyprice/search/?day="+$("#day").val()+"&type4="+$("#type4").val();
	}
</script>
</head>
<body>

<form method="post" action="../../historyprice/updateprice/" onsubmit="return checkForm()" id="form1">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../../images/sctg/content_bg.gif"><table width="100%" height="58" border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td height="31"><div class="title">历史价格 </div></td>
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
                <td ><h1 style="letter-spacing:3px;">历史价格 </h1></td>
              </tr>
            </table>-->
            
            <table class="title-2">
              <tr>
                <td  >&nbsp;</td>
                <td width="20%"  style="text-align:right;">日期</td>
                 <#if day??>
                	<td width="30%"><input type="text" id="day" name ="day" value="${day}" onclick="SelectDate(this,'yyyy-MM-dd')" onchange="search()" readonly /></td>
                <#else>
                	<td width="30%"><input type="text" id="day" name ="day" value="日期" onclick="SelectDate(this,'yyyy-MM-dd')" onchange="search()" readonly /></td>
                </#if>
                
                 <td  width="20">&nbsp;</td>
                
                <td width="10%" style="text-align:right;">区域</td>
                	<td width="20%">
                		<select name="type4" id="type4" onchange="search()">
								<option value="1" <#if type4==1>selected="selected"</#if>>长三角</option>
								<option value="2" <#if type4==2>selected="selected"</#if>>珠三角</option>
								<option value="3" <#if type4==3>selected="selected"</#if>>华中</option>
								<option value="4" <#if type4==4>selected="selected"</#if>>环渤海</option>
								<option value="5" <#if type4==5>selected="selected"</#if>>西南</option>
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
        <td colspan="4">
        	<table width="100%">
        		<tr>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="140px" style="margin-left: 50px;">月利息</td>
			<td width="140px">涨跌</td>
			<td width="20px">&nbsp;</td>
			<td width="100px">&nbsp;</td>
			<td width="140px" >月利息</td>
			<td width="140px">涨跌</td>
		</tr>
		<tr>
			<td rowspan="7" width="20px">大票买断</td>
			<td width="100px" align="center">国股</td>
			<td width="140px"><input type="text" name="b1" style="width: 120px;" value="${hb1.price }"/></td>
			<td width="140px"><input type="text" name="bd1" style="width: 120px;" value="${hb1.updown }"/></td>
			<td rowspan="7" width="20px">小票买断</td>
			<td width="100px" align="center">国股</td>
			<td width="140px"><input type="text" name="s1" style="width: 120px;" value="${hs1.price }"/></td>
			<td width="140px"><input type="text" name="sd1" style="width: 120px;" value="${hs1.updown }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">城商</td>
			<td width="140px"><input type="text" name="b2" style="width: 120px;" value="${hb2.price }"/></td>
			<td width="140px"><input type="text" name="bd2" style="width: 120px;" value="${hb2.updown }"/></td>
			<td width="100px" align="center">城商</td>
			<td width="140px"><input type="text" name="s2" style="width: 120px;" value="${hs2.price }"/></td>
			<td width="140px"><input type="text" name="sd2" style="width: 120px;" value="${hs2.updown }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">外资</td>
			<td width="140px"><input type="text" name="b3" style="width: 120px;" value="${hb3.price }"/></td>
			<td width="140px"><input type="text" name="bd3" style="width: 120px;" value="${hb3.updown }"/></td>
			<td width="100px" align="center">外资</td>
			<td width="140px"><input type="text" name="s3" style="width: 120px;" value="${hs3.price }"/></td>
			<td width="140px"><input type="text" name="sd3" style="width: 120px;" value="${hs3.updown }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农商</td>
			<td width="140px"><input type="text" name="b4" style="width: 120px;" value="${hb4.price }"/></td>
			<td width="140px"><input type="text" name="bd4" style="width: 120px;" value="${hb4.updown }"/></td>
			<td width="100px" align="center">农商</td>
			<td width="140px"><input type="text" name="s4" style="width: 120px;" value="${hs4.price }"/></td>
			<td width="140px"><input type="text" name="sd4" style="width: 120px;" value="${hs4.updown }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农合</td>
			<td width="140px"><input type="text" name="b5" style="width: 120px;" value="${hb5.price }"/></td>
			<td width="140px"><input type="text" name="bd5" style="width: 120px;" value="${hb5.updown }"/></td>
			<td width="100px" align="center">农合</td>
			<td width="140px"><input type="text" name="s5" style="width: 120px;" value="${hs5.price }"/></td>
			<td width="140px"><input type="text" name="sd5" style="width: 120px;" value="${hs5.updown }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">农信</td>
			<td width="140px"><input type="text" name="b6" style="width: 120px;" value="${hb6.price }"/></td>
			<td width="140px"><input type="text" name="bd6" style="width: 120px;" value="${hb6.updown }"/></td>
			<td width="100px" align="center">农信</td>
			<td width="140px"><input type="text" name="s6" style="width: 120px;" value="${hs6.price }"/></td>
			<td width="140px"><input type="text" name="sd6" style="width: 120px;" value="${hs6.updown }"/></td>
		</tr>
		<tr>
			<td width="100px" align="center">村镇</td>
			<td width="140px"><input type="text" name="b7" style="width: 120px;" value="${hb7.price }"/></td>
			<td width="140px"><input type="text" name="bd7" style="width: 120px;" value="${hb7.updown }"/></td>
			<td width="100px" align="center">村镇</td>
			<td width="140px"><input type="text" name="s7" style="width: 120px;" value="${hs7.price }"/></td>
			<td width="140px"><input type="text" name="sd7" style="width: 120px;" value="${hs7.updown }"/></td>
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