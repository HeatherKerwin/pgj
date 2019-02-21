<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../../css/skin-1.css" />

<link rel="stylesheet" href="../../commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="../../commons/styles/bootstrap.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../commons/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="../../commons/styles/default.css" />
<title>无标题文档</title>
</head>
<script type="text/javascript" language="javascript">
	function chooseSubmitMethod(){
		if(confirm("确定执行操作吗")){
		var id = $("#id").val();
		var form1 =document.formSubmit;
			if(id == "" || id == null){
				form1.action="../../admin/add/"
			}else{
				form1.action="../../admin/update/"
			}
				form1.submit();
			}
	}
	
	function checkForm(){
		return true;
	}
</script>
<body>
<form  action="../../admin/update/" method="POST" onsubmit="return checkForm()" name="formSubmit">
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../../images/sctg/content_bg.gif"><table width="100%" height="50" border="0" cellpadding="0" cellspacing="0" background="./../../images/sctg/content_bg.gif">
        <tr>
          <td height="50">
          	<div class="title">
	          		<#if admin1.id?if_exists>
	          			人员权限
	          		<#else>
	          			新增人员
	          		</#if>
          	</div></td>
        </tr>
      </table></td>
    <td width="16" valign="top" background="../../images/sctg/mail_right_bg.gif"><img src="../../images/sctg/nav_right_bg.gif" width="16" height="29" /></td>
  </tr>
  <!-- 中间部分开始 -->
  <tr> 
    <!--第一行左边框-->
    <td valign="middle" background="../../images/sctg/mail_left_bg.gif">&nbsp;</td>
    <!--第一行中间内容-->
    <td valign="top" bgcolor="#F7F8F9"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <!-- 空白行-->
        <tr>
          <td colspan="2" valign="top">&nbsp;</td>
          <td>&nbsp;</td>
          <td valign="top">&nbsp;</td>
        </tr>
        <tr>
          <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
              </tr>
            </table></td>
        </tr>
   	<tr>
          <td width="2%">&nbsp;</td>
          <td width="96%"><table class="title-6" width="100%" >
              <tr>
                <td colspan="2">
                <form style="overflow-y: scroll;">
                    <table width="60%"  class="cont sctg tr_color">
                      <tr align="center" class="d">
	                    <th>姓名</th>
	                    <td>
	                    	<input type="hidden" name="id" id="id" value="${admin1.id }"/>
	                    	<#if admin.id??>
	                    		<input type="text" class="input-sm" name="username" id="username" value="${admin1.username }"/>
	                    	<#else>
	                    		<input type="text" class="input-sm" name="username" id="username" value=""/>
	                    	</#if>
	                    </td>
                      </tr>
                      <tr align="center" class="d">
                        <th>真实姓名</th>
                        <td><input type="text" class="input-sm" name="realname" id="realname" value="${admin1.realname }"/></td>                        
                      </tr>
                      <tr align="center" class="d">
                        <th>权限</th>
                        <td>
	                        <input type="checkbox" value="1" name="usertype"  <#if admin1.usertype?index_of("1")!=-1>checked="checked"</#if>/>询价管理</br>
							<input type="checkbox" value="2" name="usertype"  <#if admin1.usertype?index_of("2")!=-1>checked="checked"</#if>/>订单管理</br>
							<input type="checkbox" value="3" name="usertype"  <#if admin1.usertype?index_of("3")!=-1>checked="checked"</#if>/>信息中心</br>
							<input type="checkbox" value="4" name="usertype"  <#if admin1.usertype?index_of("4")!=-1>checked="checked"</#if>/>会员管理</br>
							<input type="checkbox" value="5" name="usertype"  <#if admin1.usertype?index_of("5")!=-1>checked="checked"</#if>/>活&nbsp;&nbsp;跃&nbsp;度</br>
							<input type="checkbox" value="6" name="usertype"  <#if admin1.usertype?index_of("6")!=-1>checked="checked"</#if>/>版本控制</br>
							<input type="checkbox" value="7" name="usertype"  <#if admin1.usertype?index_of("7")!=-1>checked="checked"</#if>/>推广管理</br>
							<input type="checkbox" value="8" name="usertype"  <#if admin1.usertype?index_of("8")!=-1>checked="checked"</#if>/>红包管理</br>
							<input type="checkbox" value="9" name="usertype"  <#if admin1.usertype?index_of("9")!=-1>checked="checked"</#if>/>广告管理</br>
							<input type="checkbox" value="10" name="usertype" <#if admin1.usertype?index_of("10")!=-1>checked="checked"</#if>/>消息管理</br>
							<input type="checkbox" value="11" name="usertype" <#if admin1.usertype?index_of("11")!=-1>checked="checked"</#if>/>意见反馈 </br>
						</td>
                      </tr>
                       <tr style="text-align:center; margin-top:6px;">
		              	<td  colspan="2"><input class="sctg-i"   type="button" <#if admin1.id??>value="修改"<#else>value="添加"</#if> onclick="chooseSubmitMethod()" /></td>
		        	 </tr> 
                    </table>
                  </form>
                 </td>
              </tr>
    		 </tr>
            </table></td>
          <td width="2%">&nbsp;</td>
        </tr> <tr >
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
</form>
</body>
</html>