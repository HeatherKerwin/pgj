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
<script type="text/javascript" src="../../commons/scripts/WebCalendar.js"></script>

<title>无标题文档</title>
</head>
<script type="text/javascript">
//验证价格
function checkPrice(str) {
	var telReg = !!str.match(/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/);
	//如果手机号码不能通过验证
	return telReg;
}
function isNull(str){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
function chooseSubmitMethod(flag){	
	var price = $("#price").val();
	var shichang = $("#shichang").val();
	var startdateStr = $("#startdateStr").val();
	var enddateStr = $("#enddateStr").val();
	if (startdateStr == '' || enddateStr == '') {
		alert("请输入开始时间和结束时间!");
		return;
	}	
	
	if (!checkPrice(price)) {
		alert("价格输入有误！请重新输入!");		
	} else if (isNull(shichang)){
		alert("请输入市场名称");
	} else {
			if(confirm("确定执行操作吗")){
			$('#form1').submit();
		}
	}
	
}
function checkForm(){
	var price = $('#price').val();
	return true;
}

</script>
<body>
<form method="post" action="../../activity/add/" onsubmit="return checkForm()" id="form1">
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../../images/sctg/content_bg.gif"><table width="100%" height="50" border="0" cellpadding="0" cellspacing="0" background="./../../images/sctg/content_bg.gif">
        <tr>
          <td height="50"><div class="title">市场推广</div></td>
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
                <td colspan="2"><form style="overflow-y: scroll;" action="" method="">
                    <table width="60%"  class="cont sctg tr_color">
                      <tr align="center" class="d">
                        <th>市场名称</th>
                        <td><input type="text" id="shichang" name="shichang" ></td>
                      </tr>
                      <tr align="center" class="d">
                        <th>推广费用</th>
                        <td><input type="text" id="price" name="price" ></td>
                      </tr>
                      <tr align="center" class="d">
                        <th>开始时间</th>
                        <td>
                         <input type="text" id="startdateStr" name="startdateStr" placeholder="开始时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly />                                            
                        </td>
                      </tr>
                      <tr align="center" class="d">
                        <th>结束时间</th>
                        <td>
                         <input type="text" id="enddateStr" name="enddateStr" placeholder="结束时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly />                        
                        </td>
                      </tr>
                      <tr align="center" class="d">
                        <th>负责人</th>
                        <td><input id="header" name="header" type="text"></td>
                      </tr>
                      <tr align="center" class="d">
                        <th>链接</th>
                        <td><input id="url" name="url" type="text"></td>
                      </tr>
                      <tr align="center" class="d">
                        <th>备注</th>
                        <td><textarea  rows="3" id="remarks" name="remarks" style=" width:50%; border:1 solid #888888;font-size:14px;padding:3px;"></textarea>
                      </tr>
                      <tr style=" text-align:center;">
                        <td>&nbsp;</td>
                        <td><input class="sctg-i"  type="button" value="提交" onclick="chooseSubmitMethod(1)" ></td>
                        <td>&nbsp;</td>
                      </tr>
                    </table>
                  </form></td>
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