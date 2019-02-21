<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>意见反馈</title>
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/skin-1.css" />
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/bootstrap.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${rc.contextPath}/commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${rc.contextPath}/commons/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="${rc.contextPath}/commons/styles/default.css" />
<script type="text/javascript" src="${rc.contextPath}/commons/scripts/WebCalendar.js"></script>
</head>
<script language="javascript">
	function search(id){
		var mobile = $("#mobile").val();
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		window.location.href="${rc.contextPath}/message/search/?mobile="+mobile+"&beginDate="+begintime+"&endDate="+endtime;
	}
</script>
<body>
<#import "/common/data.ftl" as data>
<#import "/common/function.ftl" as fun>
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="${rc.contextPath}/images/sctg/mail_left_bg.gif"><img src="${rc.contextPath}/images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="${rc.contextPath}/images/sctg/content_bg.gif">
    	<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" >
	        <tr>
	          <td height="31"><div class="title">意见反馈</div></td>
	        </tr>
    	</table>
    </td>
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
          <td colspan="4">
            
            <table class="title-2">
              <tr>
              	<td width="12%" style="text-align:right;">手机号</td>
            	<td width="15%">
            		<input type="text" name="mobile" id="mobile" value="${mobile }"/>
            	</td>
                <td width="10%" style="text-align:right;"> 起始时间</td>
                <td width="15%">
                	<input type="text" iname="begintime" id="begintime" placeholder="开始时间" <#if begintimeStr??> value="${begintimeStr}" </#if> onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
                </td>
                <td width="10%" style="text-align:right;">截止时间</td>
                <td width="15%"><input type="text" name="endtime" id="endtime" <#if endtimeStr??> value="${endtimeStr}"</#if>  placeholder="结束时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/></td>
                
	            <table class="title-3" style="margin-bottom:5px;">
	              <tr>
	              	<td>&nbsp;</td>
	                <td><input class="btn" style=" width:100px; line-height:30px;" type="button" value="查询" onclick="search();" ></td>
	                <td>&nbsp;</td>
	              </tr>
	            </table> 
            </td>
        </tr>
       <!-- <tr style="text-align:left;">
        	<td>
        		<input class="btn" style="width:100px;line-height:30px;margin-top:5px;" type="button" value="添加" onclick="badd();"/>
        	</td>
        </tr> -->
        
        <!-- 一条线 -->
        <tr>
          <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              
            </table></td>
            
        </tr>
        <!-- 产品列表开始 -->
        
        
        <tr>
          <td width="2%">&nbsp;</td>
          <td width="96%"><table class="title-6" style="margin-left:8%;" width="80%"  >
              <tr>
                <td colspan="2"><form action="" method="">
                    <table  class="cont tr_color" style="width:80%;table-layout:fixed;">
                      <tr>                      
                        <th >用户名</th>
                        <th >手机号</th>
                        <th >时间</th>
                        <th>回访备注</th>
                        <th>操作</th>
                      </tr>
	                 <#if pageModel?? && pageModel.results??>
						<#list pageModel.results as message>                     
	                      <tr align="center" class="d">
	                        <td class="dd">${message.memberName}</td>
	                        <td class="dd">${message.messagemobile}</td>
	                        <td class="dd">${(message.messagetime?string("yyyy-MM-dd HH:mm:ss"))!''}  </td>
	                        <td class="dd" title="${message.returnVisit}">${message.returnVisit}</td>
	                        
	                        <td class="dd"><a href="${rc.contextPath}/message/toupdate/?id=${message.id}" style="color:blue;">查看</a></td>
	                      </tr>
						</#list>
				     </#if>    
                    </table>
                  </form>
                 </td>
              </tr>
              	
            </table></td>
          <td width="2%">&nbsp;</td>
        </tr>
        <tr>
	      <td colspan="7">
			    <div id="pager">
			  		<#import "/common/pager.ftl" as q>
			  		<#if pageModel.totalCount??>
			  			<@q.pager  pageNo=pageModel.currentPage  pageSize=pageModel.pageSize  recordCount=pageModel.totalCount  toURL="${rc.contextPath}/member/list/"  />
			  		</#if>
			    </div>	
		    </td>
       	</tr> 
        <!-- 产品列表结束 -->
        
        <!-- 一条线 -->
        <tr>
        	<td height="40" colspan="4">
	          	<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
	            </table>
        	</td>
        </tr>
        <tr >
          <td height="30px"></td>
        </tr>
        <tr>
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
    <td valign="bottom" background="${rc.contextPath}/images/sctg/mail_left_bg.gif"><img src="${rc.contextPath}/images/sctg/buttom_left.gif" width="17" height="17" /></td>
    <td background="${rc.contextPath}/images/sctg/buttom_bgs.gif"><img src="${rc.contextPath}/images/sctg/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="${rc.contextPath}/images/sctg/mail_right_bg.gif"><img src="${rc.contextPath}/images/sctg/buttom_right.gif" width="16" height="17" /></td>
  </tr>
</table>

<!-- 产品列表结束 --> 
<!-- 分页按钮--> 
<!-- 底部部分 -->

<tr style="height:10px;">
  <td>&nbsp;</td>
</tr>
</body>
</html>