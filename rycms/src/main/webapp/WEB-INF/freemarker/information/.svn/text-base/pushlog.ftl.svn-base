<#import "/common/data.ftl" as data>
<#import "/common/function.ftl" as fun>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>APP消息推送</title>
	<link rel="stylesheet" type="text/css" href="../css/skin-1.css" />
	<link rel="stylesheet" href="../commons/styles/reset.css" />
	<link rel="stylesheet" href="../commons/styles/bootstrap.css"/>
	<link rel="stylesheet" href="../commons/styles/default.css" />
	
	<script type="text/javascript" src="../js/laydate.js"></script>
	
	<script src="../commons/scripts/jquery-1.10.1.min.js"></script>
	<script src="../commons/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="../commons/scripts/WebCalendar.js"></script>
</head>
<script type="text/javascript">
function add(){
	window.location.href = "${rc.contextPath}/pushlog/add";
}
</script>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="17" valign="top" background="../images/sctg/mail_left_bg.gif"><img src="../images/sctg/left_top_right.gif" width="17" height="29"/></td>
	    <td valign="top" background="../images/sctg/content_bg.gif">
	    	<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0">
		        <tr>
		          <td height="31"><div class="title">APP消息推送</div></td>
		        </tr>
	    	</table>
	    </td>
	    <td width="16" valign="top" background="../images/sctg/mail_right_bg.gif"><img src="../images/sctg/nav_right_bg.gif" width="16" height="29"/></td>
	 </tr>
	<tr>
		<td colspan="3">
            <form action="${rc.contextPath}/pushlog/page">
            	<table class="title-2">
	            	<tr>
		                <td style="text-align:right;">推送时间：</td>
	                	<td>
	                		<input type="text" id="start" name="start" value="${start}" placeholder="起始时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
	               		</td>
		                <td style="text-align:center;">到</td>
		                <td>
		                	<input type="text" id="end" name="end" value="${end}" placeholder="截止时间" onclick="SelectDate(this,'yyyy-MM-dd')" readonly/>
	                	</td>
			        </tr>
			        <tr>
			        	<td style="text-align:right;">推送对象：</td>
	                	<td>
	                		<select id="type" name="type">
	                			<option value="0" [#if type==0]selected[/#if]>所有用户</option>
	                			<option value="1" [#if type==1]selected[/#if]>认证机构</option>
	                			<option value="2" [#if type==2]selected[/#if]>认证企业</option>
	                		</select>
	               		</td>
			        	<td style="text-align:right;">推送区域：</td>
	                	<td>
	                		<select id="cityId" name="cityId">
				        		<option style="color:red;" value="">--全国--</option>
			        			<#list citys as c>
				        			<#if c.id==cityId>
				        			<option value="${c.id}" selected="selected">${c.name}</option>
				        			<#else>
				        			<option value="${c.id}">${c.name}</option>
				        			</#if>
				        		</#list>
				        	</select>
	               		</td>
		                <td><input class="btn" style=" width:100px; line-height:30px;" type="submit" value="查询"></td>
		                <td><input class="btn" style=" width:100px; line-height:30px;" type="button" value="新增" onclick="add();"></td>
			        </tr>
			 	</table>
            </form>
		</td>
	</tr>
	<tr>
	    <td valign="middle" background="../images/sctg/mail_left_bg.gif">&nbsp;</td>
	    <td valign="top" bgcolor="#F7F8F9">
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
				<td height="40" colspan="4">
					<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC"></table>
				</td>
	        </tr>
	        <tr>
	          	<td width="2%">&nbsp;</td>
	          	<td width="96%">
	          	<table class="title-6" style="margin-left:8%;" width="80%"  >
					<tr>
						<td colspan="2">
							<table class="cont tr_color" style="width:80%;table-layout:fixed;">
								<tr style="">
									<th>推送时间</th>
									<th>消息内容</th>
									<th>推送数量</th>
									<th>成功数量</th>
									<th>推送对象</th>
									<th>推送区域</th>
								</tr>
								<#if pageModel??>
								<#list pageModel.results as var>                     
									<tr align="center" class="d">
				                        <td>
				                        	<#if var.createTime??>
												${var.createTime?string("yyyy-MM-dd HH:mm:ss")}
											</#if>
				                        </td>
				                        <td title="${var.content}">
					                        ${var.content}
				                        </td>
				                        <td>
				                        	${var.amount}
				                        </td>
			                        	<td>
			                        		${var.successAmount}
			                        	</td>
				                        <td class="dd">
				                        	<#if var.type==0>
				                        		<font style="color:red;">所有用户</font>
				                        	<#elseif var.type==1>
				                        		<font style="color:blue;">认证机构</font>
				                        	<#elseif var.type==2>
				                        		<font style="color:green;">认证企业</font>
				                        	</#if>
				                        </td>
				                        <td>
			                        		<#if var.cityId??>
			                        			<#list citys as c>
			                        				<#if var.cityId==c.id>
			                        					${c.name}
			                        				</#if>
			                        			</#list>
			                        		<#else>
			                        		<font style="color:red;">全国</font>
			                        		</#if>
			                        	</td>
			                    	</tr>
								</#list>
								</#if>    
							</table>
						</td>
					</tr>
				</table>
				</td>
	          	<td width="2%">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="7">
					<div id="pager">
						<#import "/common/pager.ftl" as q>
				  		<#if pageModel.totalCount??>
							<@q.pager pageNo=pageModel.currentPage pageSize=pageModel.pageSize recordCount=pageModel.totalCount toURL="${rc.contextPath}/pushlog/page"/>
				  		</#if>
				    </div>	
			    </td>
	       	</tr> 
	        <tr>
	        	<td height="40" colspan="4">
		          	<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC"></table>
	        	</td>
	        </tr>
			<tr>
				<td height="30px"></td>
	        </tr>
			<tr>
				<td width="2%">&nbsp;</td>
				<td width="51%" class="left_txt">
					<img src="../images/sctg/icon_mail.gif" width="16" height="11"> 客户服务邮箱：ryfinance@ryfinance.com<br/>
					<img src="../images/sctg/icon_phone.gif" width="17" height="14"> 官方网站：<a href="https://www.utiexian.com/" target="_blank">睿银金融服务有限公司</a>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	    <td background="../images/sctg/mail_right_bg.gif">&nbsp;</td>
	</tr>
	<tr>
		<td valign="bottom" background="../images/sctg/mail_left_bg.gif"><img src="../images/sctg/buttom_left.gif" width="17" height="17" /></td>
	    <td background="../images/sctg/buttom_bgs.gif"><img src="../images/sctg/buttom_bgs.gif" width="17" height="17"></td>
	    <td valign="bottom" background="../images/sctg/mail_right_bg.gif"><img src="../images/sctg/buttom_right.gif" width="16" height="17" /></td>
	</tr>
	<tr style="height:10px;">
		<td>&nbsp;</td>
	</tr>
</table>
</body>
</html>