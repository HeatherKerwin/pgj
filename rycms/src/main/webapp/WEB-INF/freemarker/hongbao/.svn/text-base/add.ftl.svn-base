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

$(function(){
	$("#maxtr").hide();
})

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
	var type = $("input[name='type']:checked").val();
	var maxprice = $("#maxprice").val();	
	if (type == 1) {		
		if (!checkPrice(maxprice)) {
			alert("最高红包金额输入有误！请重新输入!");	
			return;	
		}		
	}
	var price = $("#totalprice").val();
	var name = $("#name").val();
	var startdateStr = $("#startdateStr").val();
	var enddateStr = $("#enddateStr").val();
	if (startdateStr == '' || enddateStr == '') {
		alert("请输入开始时间和结束时间!");
		return;
	}	
	if (!checkPrice(price)) {
		alert("价格输入有误！请重新输入!");		
	} else if (isNull(name)){
		alert("请输入活动名称");
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

function checkradio() {
	var item = $(":radio:checked"); 
	var len=item.length; 
	if(len>0){ 
	    var value = $(":radio:checked").val(); 
	    if (value == 1) {
			$("#minprice").html("最小红包金额");
			$("#maxtr").show();
		}
		if (value == 0) {
			$("#minprice").html("红包金额");
			$("#maxtr").hide();
		}
	} 
}
</script>
<body>
<form method="post" action="../../hongbao/save" onsubmit="return checkForm()" id="form1">
<input type="hidden" name="tagCode" value="HONGBAO"/>
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../../images/sctg/content_bg.gif"><table width="100%" height="50" border="0" cellpadding="0" cellspacing="0" background="./../../images/sctg/content_bg.gif">
        <tr>
          <td height="50"><div class="title">红包活动</div></td>
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
                        <th>红包类型</th>
                        <td>
                        	<select id="tagId" name="tagId">
		            			<#list tags as tag>
		            				<option value="${tag.id}">${tag.name}</option>
		            			</#list>
		            		</select>
                        </td>
                      </tr>
                      <tr align="center" class="d">
                        <th>活动名称</th>
                        <td><input type="text" id="name" name="name" ></td>
                      </tr>
                      <tr align="center" class="d">
                        <th>红包总金额</th>
                        <td><input type="text" id="totalprice" name="totalprice" ></td>
                      </tr>
                      <tr align="center" class="d">
                        <th>红包发放类型</th>
                        <td>
                        <input type="radio" style="width:30px;" onclick="checkradio()" name="type" checked="" value="0" />固定金额                                                
                        <input type="radio" style="width:30px;" onclick="checkradio()" name="type" value="1" />随机金额
                        </td>
                      </tr>
                      <tr align="center" class="d">
                        <th id="minprice" >红包金额</th>
                        <td><input type="text" id="price" name="price" ></td>
                      </tr>
                      <tr id="maxtr" align="center" class="d">
                        <th>最高红包金额</th>
                        <td><input type="text" id="maxprice" name="maxprice" ></td>
                      </tr>
                      <tr align="center" class="d">
                        <th>红包个数</th>
                        <td><input type="text" id="totalnum" name="totalnum" ></td>
                      </tr>                      
                      <tr align="center" class="d">
                        <th>贴现额度</th>
                        <td><input type="text" id="limitprice" name="limitprice" >万</td>
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
                        <th>红包发起人</th>
                        <td><input id="header" name="header" type="text"></td>
                      </tr>
                      <tr align="center" class="d">
                        <th>链接</th>
                        <td><input id="lianjie" name="lianjie" type="text"></td>
                      </tr>
                      <tr align="center" class="d">
                        <th>使用有效期限</th>
                        <td><input id="usedays" name="usedays" type="text">天</td>
                      </tr>
                      <tr align="center" class="d">
                        <th>分享有效期限</th>
                        <td><input id="sharedays" name="sharedays" type="text">天</td>
                      </tr>
                      <tr align="center" class="d" >
                        <th>有效红包个数</th>
                        <td><input id="youxiaogeshu" type="text" readonly />个</td>
                      </tr>
                      <tr align="center" class="d">
                        <th>有效红包金额</th>
                        <td><input id="youxiaojine" type="text" readonly />元</td>
                      </tr>
                      <tr align="center" class="d">
                        <th>返回红包金额</th>
                        <td><input id="fanhuijine" type="text" readonly />元</td>
                      </tr>
                      <tr align="center" class="d">
                        <th>有效红包转换率</th>
                        <td><input id="zhuanhuanlv" type="text" readonly />%</td>
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