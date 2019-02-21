<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>会员信息</title>
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
<script type="text/javascript" src="../../js/conf.js"></script>
</head>
<script language="javascript">
	function jumpPage(jumpPageNumber){
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		var mobile = $("#mobile").val();
		var isAccreditation = $("#isAccreditation").val();
		var isAdimin = $("#adminId").val();
		window.location.href="../../member/saleslist/?currentPage="+jumpPageNumber+"&begintime="+begintime+"&endtime="+endtime+"&mobile="+mobile+"&isAccreditation="+isAccreditation+"&isAdiminId="+isAdimin;
	}
	
	function search(){
		var begintime = $("#begintime").val();
		var endtime = $("#endtime").val();
		var mobile = $("#mobile").val();
		var isAccreditation = $("#isAccreditation").val();
		var isAdimin = $("#adminId").val();
		window.location.href="../../member/saleslist/?begintime="+begintime+"&endtime="+endtime+"&mobile="+mobile+"&isAccreditation="+isAccreditation+"&isAdiminId="+isAdimin;
	}
	
</script>
<body>
<#import "/common/data.ftl" as data>
<#import "/common/function.ftl" as fun>
<table width="100%" height="100%"   border="0" cellpadding="0" cellspacing="0">
  <!-- 头部开始 -->
  <tr>
    <td width="17" valign="top" background="../../images/sctg/mail_left_bg.gif"><img src="../../images/sctg/left_top_right.gif" width="17" height="29" /></td>
    <td valign="top" background="../../images/sctg/content_bg.gif">
    	<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" >
	        <tr>
	          <td height="31"><div class="title">会员信息</div></td>
	        </tr>
    	</table>
    </td>
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
        <tr style="border:#CCC 1px solid; margin:6px 10px;">
          <td colspan="4">
            
            <table class="title-2">
              <tr>
                <td width="10%" style="text-align:right;"> 起始时间</td>
                <td width="15%">
                	<input type="text" name="begintime" id="begintime" placeholder="开始时间" <#if begintimeStr??> value="${begintimeStr}" </#if> onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
                </td>
                <td width="10%" style="text-align:right;">截止时间</td>
                <td width="15%"><input type="text" name="endtime" id="endtime" <#if endtimeStr??> value="${endtimeStr}"</#if>  placeholder="结束时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/></td>
                <td width="12%" style="text-align:right;">手机号</td>
            	<td width="15%">
            		<input type="text" name="mobile" id="mobile" />
            	</td>
            	<td width="12%" style="text-align:right;">是否认证</td>
            	<td width="15%">
            		<select name="isAccreditation" id="isAccreditation">
            			<option value="3">请选择</option>
            			<option value="0">未认证</option>
            			<option value="1">已认证</option>
            		</select>
            	</td>
            	<td width="12%" style="text-align:right;"><input type="hidden" value="${adminId}" id="adminId" name="adminId"/></td>
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
                        <th style="width: 140px;">注册手机号码</th>
                        <th style="width: 120px;">联系人</th>
                        <th style="width: 180px;">公司名称</th>
                        <th>是否通过认证</th>
                        <th>注册时间</th>
                        <th>业务员</th>
                      </tr>
                 <#if pageModel?? && pageModel.results??>
					<#list pageModel.results as member>                     
                      <tr align="center" class="d">
                        <td class="dd"><font color="blue">${member.mobile}</font></td>
                        <td class="dd">${member.name }</td>
                        <td class="dd">
                      		 ${member.company }
                        </td>
                       
                        <#if member.state==2>
                        	<td class="dd">已认证</td>
                        <#else>
					 		<td class="dd">未认证</td>
			      	 	</#if>  
                        
                        <td class="dd">${member.regtime }</td>
                        <td class="dd">${member.servicemember }</td>
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
			  			<@q.pager  pageNo=pageModel.currentPage  pageSize=pageModel.pageSize  recordCount=pageModel.totalCount  toURL="../../member/saleslist/"  />
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
</body>
</html>