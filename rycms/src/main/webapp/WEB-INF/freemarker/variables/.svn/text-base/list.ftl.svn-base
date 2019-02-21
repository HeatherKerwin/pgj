<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>优惠消息</title>
<link rel="stylesheet" type="text/css" href="../css/skin-1.css" />
<link rel="stylesheet" href="../commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="../commons/styles/bootstrap.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../commons/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="../commons/styles/default.css" />
<script type="text/javascript" src="../commons/scripts/WebCalendar.js"></script>
</head>
<script language="javascript">
	
	function confirmdelete(newsid){
		if(confirm("确定删除吗?")){
			location.href="${rc.contextPath}/preferentialInfo/del?id="+newsid;
		}
	}
	
	function badd() {
		window.location.href = "${rc.contextPath}/variables/add";
	}
	
</script>
<body>
<#import "/common/data.ftl" as data>
<#import "/common/function.ftl" as fun>
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../images/sctg/mail_left_bg.gif"><img src="../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../images/sctg/content_bg.gif">
    	<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" >
	        <tr>
	          <td height="31"><div class="title">优惠消息</div></td>
	        </tr>
    	</table>
    </td>
    <td width="16" valign="top" background="../images/sctg/mail_right_bg.gif"><img src="../images/sctg/nav_right_bg.gif" width="16" height="29" /></td>
  </tr>
  <!-- 中间部分开始 -->
  
  <tr> 
    
    <!--第一行左边框-->
    <td valign="middle" background="../images/sctg/mail_left_bg.gif">&nbsp;</td>
    <!--第一行中间内容-->
    <td valign="top" bgcolor="#F7F8F9"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <!-- 空白行-->
        <tr>
          <td colspan="2" valign="top">&nbsp;</td>
          <td>&nbsp;</td>
          <td valign="top">&nbsp;</td>
        </tr>
        <tr style="text-align:left;">
        	<td>
        		<input class="btn" style="width:100px;line-height:30px;margin-top:5px;" type="button" value="新增优惠信息" onclick="badd();"/>
        	</td>
        </tr> 
        
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
                        <th>code</th>
						<th>name</th>
						<th>remarks</th>
						<th>values</th> 
						<th>操作</th>  
                      </tr>
                 <#if pageModel??>
					<#list pageModel.results as var>                     
                      <tr align="center" class="d">
                        <td class="dd">${var.code}</td>
                        <td>${var.name}</td>
                         <td>${var.remarks}</td>
                        <td class="dd">${var.value}</td> 
                        <td class="dd"><a href="${rc.contextPath}/variables/get?id=${var.id}">修改</a></td>                                                
                      </tr>
					</#list>
			     </#if>    
				                                      
                    </table>
                  </form></td>
              </tr>
              	
            </table></td>
          <td width="2%">&nbsp;</td>
        </tr>
           <tr>
	      <td colspan="7">
			    <div id="pager">
			  		<#import "/common/pager.ftl" as q>
			  		<#if pageModel.totalCount??>
			  			<@q.pager  pageNo=pageModel.currentPage  pageSize=pageModel.pageSize  recordCount=pageModel.totalCount  toURL="/rycms/variables/index"  />
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
          <td width="51%" class="left_txt"><img src="../images/sctg/icon_mail.gif" width="16" height="11"> 客户服务邮箱：ryfinance@ryfinance.com<br />
            <img src="../images/sctg/icon_phone.gif" width="17" height="14"> 官方网站：<a href="https://www.utiexian.com/" target="_blank">睿银金融服务有限公司</a></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table></td>
    <td background="../images/sctg/mail_right_bg.gif">&nbsp;</td>
  </tr>
  <!-- 底部部分 -->
  <tr>
    <td valign="bottom" background="../images/sctg/mail_left_bg.gif"><img src="../images/sctg/buttom_left.gif" width="17" height="17" /></td>
    <td background="../images/sctg/buttom_bgs.gif"><img src="../images/sctg/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="../images/sctg/mail_right_bg.gif"><img src="../images/sctg/buttom_right.gif" width="16" height="17" /></td>
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