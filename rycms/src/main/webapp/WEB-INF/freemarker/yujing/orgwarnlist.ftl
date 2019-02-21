<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>
	客户端cms
</title>
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/Tmain.css" />
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/qstime.css" />
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/dataTables/dataTables.bootstrap.css"/>
<script type="text/javascript" src="${rc.contextPath}/js/laydate.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/Tmain.js"></script>
<script src="${rc.contextPath}/js/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="../commons/styles/bootstrap.css"/>
<script src="${rc.contextPath}/js/dataTables/jquery.dataTables.js"></script>
<script src="${rc.contextPath}/js/dataTables/fnReloadAjax.js"></script>
 <script src="${rc.contextPath}/js/layer/layer.min.js"></script>
<script src="${rc.contextPath}/js/layer/extend/layer.ext.js"></script>
<script src="${rc.contextPath}/js/jquery.form.js"></script>
<script src="${rc.contextPath}/js/common.js"></script>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<!-- 头部开始 -->
		
		<!-- 中间部分开始 -->
		<tr>
			<!--第一行左边框-->
			
			<!--第一行中间内容-->
			<td valign="top" bgcolor="#F7F8F9"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<!-- 空白行-->
					<tr>
						<td colspan="2" valign="top">&nbsp;</td>
						<td>&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>
					<tr width="100%">
						<td colspan="4" width="100%"><table width="100%">
								<tr width="100%">
									<td width="10%">&nbsp;</td>
									<td width="80%" align="center"><h3 style="letter-spacing:1px;">
											请填写完整搜索条件
										</h3></td>
									<td width="10%">&nbsp;</td>
								</tr>
							</table></td>
					</tr>
					<!-- 空白行-->
					<tr>
						<td colspan="2" valign="top">&nbsp;</td>
						<td>&nbsp;</td>
						<td valign="top">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4" width="100%"><table width="100%">
								<tr width="100%">
									<td width="5%">&nbsp;</td>
									<td width="90%" >
									<form method = "post"  action = "${rc.contextPath}/app/orgwarn/chaxun">
										<table width="100%" >
												<tr width="100%">
													<td width="1%">&nbsp;</td>
													<td width="10%" style="text-align:right;">机构名称：</td>
													<td width="10%"><input class="text" type="text" name="name"  id="jigouname" /></td>
	
													<td width="1%">&nbsp;</td>
													
													<td width="10%"><input type="submit" value="查询" />
													<td width="1%">&nbsp;</td>
												</tr>
												<tr width="100%" height="10px">&nbsp;<tr>
												<tr width="100%" class="mt5">
													 <td width="2%">&nbsp;</td>
													 
													
													<td width="1%">&nbsp;</td>
												</tr>
											</table>
											</form>
										</td>
										
									<td width="5%" >&nbsp;</td>
								</tr>
							</table></td>
					</tr>
					<!-- 一条线 -->
					<tr>
						<td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
								<tr>
									<td></td>
								</tr>
							</table></td>
					</tr>
					
					
					<tr>
						<td width="2%">&nbsp;</td>
						<td width="60%"><table>
								<tr><td>&nbsp;</td>
									<td></td>
									<td>&nbsp;</td>
									<td></td>
									<td>&nbsp;</td>
									<td><input style="padding:0 20px" type="button" id="export" value="导出excel" /></td>
									<td>&nbsp;</td>
								</tr>
							</table></td>
					</tr>
					   
         <tr>
          <td width="2%">&nbsp;</td>
          <td width="96%"><table class="title-6" style="margin-left:8%;" width="80%"  >
              <tr>
                <td colspan="2"><form action="" method="">
                    <table  class="cont tr_color" style="width:80%;table-layout:fixed;">
                      <tr>                      
                        <th>公司名称</th>
						
						<th>操作</th>  
                      </tr>
                      
                 <#if pageModel??>
					<#list pageModel.results as var>                     
                      <tr align="center" class="d">
                        
                        <td>${var.name}</td>
                        
                        <td class="dd"><a href="${rc.contextPath}/app/orgwarn/price/get?orgId=${var.orgId}&&id=${var.id}">详情</a></td>                                                
                      </tr>
                      
					</#list>
			     </#if>   
			      
				      <tr align="center" class="d1">
                      </tr>
                     
				      </tr>
				      
				      
				                                      
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
			  			<@q.pager  pageNo=pageModel.currentPage  pageSize=pageModel.pageSize  recordCount=pageModel.totalCount  toURL="/rycms/historyprice/baojia"  />
			  		</#if>
			    </div>	
		    </td>
       	</tr> 
					<!-- 产品列表开始 -->
					
					<!-- 产品列表结束 -->
					<tr>
						<td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
								<tr>
									<td></td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td width="2%">&nbsp;</td>
						<td width="51%" class="left_txt"><img src="${rc.contextPath}/images/icon_mail.gif" width="16" height="11">
							客户服务邮箱：ryfinance@ryfinance.com<br />
							<img src="${rc.contextPath}/images/icon_phone.gif" width="17" height="14">
							官方网站：
							<a href="https://www.utiexian.com/" target="_blank">
								上海票管家金融服务有限公司
							</a></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table></td>
			<td background="${rc.contextPath}/images/mail_right_bg.gif">&nbsp;</td>
		</tr>
		<!-- 底部部分 -->
		<tr>
			<td valign="bottom" background="${rc.contextPath}/images/mail_left_bg.gif"><img src="${rc.contextPath}/images/buttom_left.gif" width="17" height="17" /></td>
			<td background="${rc.contextPath}/images/buttom_bgs.gif"><img src="${rc.contextPath}/images/buttom_bgs.gif" width="17" height="17"></td>
			<td valign="bottom" background="${rc.contextPath}/images/mail_right_bg.gif"><img src="${rc.contextPath}/images/buttom_right.gif" width="16" height="17" /></td>
		</tr>
	</table>
	
<script language="javascript">

 $(document).ready(function() {
        
  
	});
	//查询
	function query(){
	//$(".d").hide();
	//$(".d1").show();
	//var name=$("#name").val();
	//var isnew=$("#isnew").val();
	
	}
	
	function checkedBox(){
		var sendmessage = $('input[name="sendmessage"]:checked ').val();
		var idcheckbox = "";    
		  $('input[name="idcheckbox"]:checked').each(function(){    
		   idcheckbox += $(this).val()+",";    
		  }); 
		return idcheckbox;
	}


	$(document).on('click','#chk-all',function(){
        $(this).closest('table').find('input[name="idcheckbox"]').prop('checked', this.checked);
	});

	$(document).on('click','.td-name',function(){
        var obj = $(this).closest('tr').find('input');
        if(obj.prop('checked') == true){   
          obj.prop('checked',true);  
        } else{   
          obj.prop('checked',false); 
		}
	});
</script>
</html>