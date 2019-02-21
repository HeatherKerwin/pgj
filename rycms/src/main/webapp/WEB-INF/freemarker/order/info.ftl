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
<script type="text/javascript" language="javascript">
	function chooseSubmitMethod(flag){
		var flag1 = $("#flag1").val();
		var reason = $("#reasondesc").val();
		var discountrecordid = $("input[name=discountrecordid]").val();
		var hongbaoid = $("input[name=hongbaoid]").val();
		var reasondesc ="";
		if(flag1=='-1'|| flag1 == '0'){
			if(reason == null || reason.trim().length==0){
				alert("请填写原因");
				return;
			}
		}
		if(confirm("确定执行操作吗")){
			if(reason != "" || reason != null){
				reasondesc = encodeURI(encodeURI(reason));
			}
			//window.location.href="../../discountrecord/update/?flag1="+flag1+"&discountrecordid="+discountrecordid+"&hongbaoid="+hongbaoid+"&reasondesc="+reasondesc;
			$("#form1").submit();
		}
	}
	
	function checkForm(){
		return true;
	}
</script>
<body>
<form method="post" action="${rc.contextPath}/discountrecord/update/" onsubmit="return checkForm()" id="form1">
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../../images/sctg/content_bg.gif"><table width="100%" height="50" border="0" cellpadding="0" cellspacing="0" background="./../../images/sctg/content_bg.gif">
        <tr>
          <td height="50"><div class="title">订单详情</div></td>
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
                <input type="hidden" name="id" value="${message.id}"/>
                    <table width="60%"  class="cont sctg tr_color">
					 <tr style=" text-align:center;">
                        <td>&nbsp;</td>
                        <td>
                        		<#if discountrecord.orderflag==1>
					        		<font color="red">待确认</font>>>待验票>>待收款>>已完成
					        	<#elseif discountrecord.orderflag==2>
					        		<font color="red">待验票</font>>>待收款>>已完成
					        	<#elseif discountrecord.orderflag==3>
					        		待确认>>待验票>>待收款>><font color="red">已完成</font>
					        	<#elseif discountrecord.orderflag==4>
					        		待确认>>待验票>><font color="red">待收款</font>>>已完成
					        	<#elseif discountrecord.orderflag==-1>
					        		订单失败
					        	<#elseif discountrecord.orderflag==0>
					        		订单取消
					        	</#if>
                        </td>
                        <td>&nbsp;</td>
                      </tr>                    	
                    
                      <tr align="center" class="d">
	                    <th>下单人</th>
	                    <td>
	                    	${member.mobile }
	                    </td>
                      </tr>
                      <tr align="center" class="d">
                        <th>类型</th>
                        <td>
                        	<#if (discountrecord.type1 == 1)>
                        		纸票
                        	</#if>
                        	<#if (discountrecord.type1 == 2)>
                        		电票
                        	</#if>
                        </td>                        
                      </tr>
                      <tr align="center" class="d">
                        <th>类型</th>
                        <td>${discountrecord.type2show}</td>
                      </tr>
                      <tr align="center" class="d">
                        <th>出票时间</th>
                        <td>
                        	${discountrecord.begintime }
                        </td>
                      </tr>
                      <tr align="center" class="d">
                        <th id="minprice">到期时间</th>
                        <td>
                        	${discountrecord.endtime }
                        </td>
                      </tr>
                      
                      <tr align="center" class="d">
                        <th id="minprice">地方</th>
                        <td>
                        	${discountrecord.place }
                        </td>
                      </tr>
                      
                      <tr align="center" class="d">
                        <th id="minprice">总额</th>
                        <td>
                        	${discountrecord.allmoney }
                        </td>
                      </tr>
                      
                      <tr align="center" class="d">
                        <th id="minprice">红包金额<</th>
                        <td>
                        	${hongbaojine}
                        </td>
                      </tr>
                      
                      <tr align="center" class="d">
                        <th id="minprice">联系号码</th>
                        <td>
                        	${discountrecord.membermobile }
                        </td>
                      </tr>
                      
                       <tr align="center" class="d">
                        <th id="minprice">联系人</th>
                        <td>
                        	${discountrecord.membername }
                        </td>
                      </tr>
                      
                       <tr align="center" class="d">
                        <th id="minprice">图片</th>
                        <td>
                        	<#if discountrecord.picpath??>
						    	<#list discountrecord as item>
						    		<img style="width: 470px; height: 355px;" src="${item.picpath}"/>
						    	</#list>
						   </#if>
                        </td>
                      </tr>
                      
                      
                       <tr align="center" class="d">
                        <th id="minprice">备注</th>
                        <td>
                        	${discountrecord.memberother }
                        </td>
                      </tr>
                      
                      
                       <tr align="center" class="d">
                        <th id="minprice">状态</th>
                        <td>
                        
                       	    <input type="hidden" name="discountrecordid" value="${discountrecord.id}"/>
							<input type="hidden" name="hongbaoid" value="${hongbaoid}"/>
                        	
                        	<select id="flag1" name="flag1">
								<option value="-1" <#if discountrecord.orderflag ==-1>selected="selected"</#if>>订单失败</option>
								<option value="0"  <#if discountrecord.orderflag == 0>selected="selected"</#if>>无效订单</option>
								<option value="1"  <#if discountrecord.orderflag == 1>selected="selected"</#if>>待确认</option>
								<option value="2"  <#if discountrecord.orderflag == 2>selected="selected"</#if>>待验票</option>					
								<option value="3"  <#if discountrecord.orderflag == 3>selected="selected"</#if>>已完成</option>
								<option value="4"  <#if discountrecord.orderflag == 4>selected="selected"</#if>>待收款</option>
							</select>
                        </td>
                      </tr>
                      
                      
                       <tr align="center" class="d">
                        <th id="minprice">原因描述</th>
                        <td>
                        	<textarea id="reasondesc" name="reasondesc">${discountrecord.reasondesc }</textarea>
                        </td>
                      </tr>
                     <tr style=" text-align:center;">
                        <td>&nbsp;</td>
                        <td><input class="sctg-i"  type="button" value="修改" onclick="chooseSubmitMethod()" ></td>
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