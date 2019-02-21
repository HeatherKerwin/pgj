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
<!-- <link rel="stylesheet" href="../commons/styles/bootstrap.css"/> -->
<script src="${rc.contextPath}/js/dataTables/jquery.dataTables.js"></script>
<script src="${rc.contextPath}/js/dataTables/fnReloadAjax.js"></script>
 <script src="${rc.contextPath}/js/layer/layer.min.js"></script>
<script src="${rc.contextPath}/js/layer/extend/layer.ext.js"></script>
<script src="${rc.contextPath}/js/jquery.form.js"></script>
<script src="${rc.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${rc.contextPath}/commons/scripts/WebCalendar.js"></script>
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
						<td colspan="4" width="100%">
						<table width="100%">
						<tr width="100%">
							<td width="90%" >
								<form method = "post" id = "myForm">
									<input type="hidden" <#if isMy??>value="${isMy}"</#if> name="isMy"><!-- 判断是否我的客户报价页面 -->
									<table width="100%" >
										<tr width="100%" height="30px">
							                <td width="10%" style="text-align:right;"> 报价时间</td>
							                <td width="30%">
							                	<input type="text" style="width: 45%" id="begintime" name ="beginDate" value="<#if orgInfoForm.beginDate??>${orgInfoForm.beginDate}<#else>${.now?string("yyyy-MM-dd")}</#if>" placeholder="起始时间"   onclick="SelectDate(this,'yyyy-MM-dd')" readonly />
							                <span style="width: 10%">至</span>
							                	<input type="text" style="width: 45%" id="endtime" name ="endDate" value="<#if orgInfoForm.endDate??>${orgInfoForm.endDate}<#else>${.now?string("yyyy-MM-dd")}</#if>" placeholder="截止时间"   onclick="SelectDate(this,'yyyy-MM-dd')" readonly />
							                </td>
							                <td width="15%" style="text-align:right;">客户归属</td>
							                <td width="15%">
												<select class="text"  name ="ascriptionState"	 id="isnew"	 style="width: 173px;">
													<option value="">请选择</option>
													<option value="0" <#if orgInfoForm.ascriptionState==0>selected</#if>>销售营销用户</option>
													<option value="1" <#if orgInfoForm.ascriptionState==1>selected</#if>>许可营销用户</option>
													<option value="2" <#if orgInfoForm.ascriptionState==2>selected</#if>>转化营销用户</option>
													<option value="3" <#if orgInfoForm.ascriptionState==3>selected</#if>>平台营销用户</option>
												</select>										
											</td>
											<#if !isMy??>
												<td width="15%" style="text-align:right;">业务员</td>
								                <td width="15%">
													<select class="text" name ="serviceMemberId" id="serviceMemberId" value="0" style="width: 173px;">
														<option value="">请选择</option>
														<#list ServiceMember as sm>
														    <option value="${sm.id}" <#if orgInfoForm.serviceMemberId==sm.id>selected</#if>>${sm.servicemember}</option>
														</#list>
													</select>										
												</td>
											</#if>
								        </tr>
										<tr width="100%" height="30px">
							                <td width="10%" style="text-align:right;">注册时间</td>
							                <td width="30%">
							                	<input type="text" style="width: 45%" id="regBeginDate" name ="regBeginDate" value="<#if orgInfoForm.regBeginDate??>${orgInfoForm.regBeginDate}<#else></#if>" placeholder="起始时间"   onclick="SelectDate(this,'yyyy-MM-dd')" readonly />
							                <span style="width: 10%">至</span>
							                	<input type="text" style="width: 45%" id="regEndDate" name ="regEndDate" value="<#if orgInfoForm.regBeginDate??>${orgInfoForm.regEndDate}<#else></#if>" placeholder="截止时间"   onclick="SelectDate(this,'yyyy-MM-dd')" readonly />
							                </td>
							                <td width="15%" style="text-align:right;">机构名称</td>
							                <td width="15%">
							                	<input type="text" name="company" value="<#if orgInfoForm.company??>${orgInfoForm.company}</#if>" style="width:173px;">
											</td>
											<td width="15%" style="text-align:right;">注册手机号</td>
							                <td width="15%">
							                	<input type="text" value="<#if orgInfoForm.mobile??>${orgInfoForm.mobile}</#if>" name ="mobile" tyle="width:173px;">
											</td>
								        </tr>
										<tr width="100%" height="30px">
							                <td width="10%" style="text-align:right;"> 认证时间</td>
							                <td width="30%">
							                	<input type="text" style="width: 45%" id="authBeginDate" name ="authBeginDate" value="<#if orgInfoForm.authBeginDate??>${orgInfoForm.authBeginDate}<#else></#if>" placeholder="起始时间"   onclick="SelectDate(this,'yyyy-MM-dd')" readonly />
							                <span style="width: 10%">至</span>
							                	<input type="text" style="width: 45%" id="authEndDate" name ="authEndDate" value="<#if orgInfoForm.authEndDate??>${orgInfoForm.authEndDate}<#else></#if>" placeholder="截止时间"   onclick="SelectDate(this,'yyyy-MM-dd')" readonly />
							                </td>
							                <td width="15%" style="text-align:right;">银票有无报价</td>
							                <td width="15%">
							                	<select class="text" name ="yin" id="yin" style="width: 173px;">
							                		<option value="">请选择</option>
													<option value="0" <#if orgInfoForm.yin==0>selected </#if>>银票有报价</option>
													<option value="1" <#if orgInfoForm.yin==1>selected </#if>>银票无报价</option>
												</select>
											</td>
											<td width="15%" style="text-align:right;">商票有无报价</td>
							                <td width="15%">
							                	<select class="text" name ="shang" id="shang" style="width: 173px;">
							                		<option value="">请选择</option>
													<option value="0" <#if orgInfoForm.shang==0>selected </#if>>商票有报价</option>
													<option value="1" <#if orgInfoForm.shang==1>selected </#if>>商票无报价</option>
												</select>
											</td>
								        </tr>
									</table>
									<table width="45%" >
										<tr  height="30px">
											<td  style="text-align:right;">报价类型</td>
							                <td >
												<select class="text"  name ="priceType"	 id="priceType"	 style="width: 100%">
													<option value="">请选择</option>
													<option value="1"<#if orgInfoForm.priceType==1>selected</#if>>大电银有报价</option>
													<option value="2"<#if orgInfoForm.priceType==2>selected</#if>>小电银有报价</option>
													<option value="3"<#if orgInfoForm.priceType==3>selected</#if>>纸银有报价</option>
													<option value="4"<#if orgInfoForm.priceType==4>selected</#if>>电商有报价</option>
													<option value="5"<#if orgInfoForm.priceType==5>selected</#if>>纸商有报价</option>
													<option value="6"<#if orgInfoForm.priceType==6>selected</#if>>国股有报价</option>
													<option value="7"<#if orgInfoForm.priceType==7>selected</#if>>城商有报价</option>
													<option value="8"<#if orgInfoForm.priceType==8>selected</#if>>三农有报价</option>
													<option value="9"<#if orgInfoForm.priceType==9>selected</#if>>外资有报价</option>
													<option value="10"<#if orgInfoForm.priceType==10>selected</#if>>村镇有报价</option>
												</select>										
											</td>
										</tr>
										<tr width="100%" height="30px">
											<td width="10%"></td>
											<td width="10%"><input style="width: 100%" type="button" onclick="submitmyForm()" value="查询" />
											<td width="10%"></td>
											<td width="10%"><input style="width: 100%" type="button" id="tjexport" value="按条件导出excel" /></td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
						</table></td>
					</tr>
					<!-- 一条线 -->
					<tr>
						<td height="40" colspan="4">
						<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC"></table>
						</td>
					</tr>
		         	<tr width="100%">
		          		<td width="100%">
		          		<div style="width: 1350px;height:350px;overflow: auto;">
		                    <table style="width: 2000px;">
		                      <tr style="width: 1500px;"> 
		                        <th style="width: 300px;">公司名称</th>
		                        <th >注册人</th>
		                        <th>手机号</th>
								<th>客户归属</th>
								<th>业务员</th>
								<th>注册日期</th>
								<th>认证日期</th>
								<th>许可日期</th>
								<th>转化日期</th>
								<th>操作</th>
		                      </tr>
		                 <#if pageModel??>
							<#list pageModel.results as var>       
		                      <tr align="center" class="d">
		                        <td>${var.org}</td>
		                        <td>${var.name}</td>
		                        <td>${var.phone}</td>
		                        <td>
		                        	<#if var.ascription_state==0>销售营销客户</#if>
		                        	<#if var.ascription_state==1>许可营销客户</#if>
		                        	<#if var.ascription_state==2>转化营销客户</#if>
		                        	<#if var.ascription_state==3>平台营销客户</#if>
		                        </td>
		                        <td>${var.servicemember}</td>
		                        <td>${var.regtime}</td>
		                        <td>${var.authTime}</td>
		                        <td>${var.permit_time}</td>
		                        <td><#if var.c_time??>${(var.c_time*1000)?number_to_datetime}</#if></td>
		                        <td class="dd"><a href="${rc.contextPath}/historyprice/get?orgId=${var.orgId}&memberId=${var.member_id}&phone=${var.phone}">详情</a></td>                                                
		                      </tr>
							</#list>
					     </#if>
						      <tr align="center" class="d1">
		                      </tr>
		                     
						      </tr>
		                    </table>
		                    </div>
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
			  			<#if isMy??>
			  				<@q.pager  pageNo=pageModel.currentPage  pageSize=pageModel.pageSize  recordCount=pageModel.totalCount  toURL="/rycms/historyprice/chaxun?isMy=${isMy}" />
						<#else>
							<@q.pager  pageNo=pageModel.currentPage  pageSize=pageModel.pageSize  recordCount=pageModel.totalCount  toURL="/rycms/historyprice/chaxun" />
						</#if>
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
						<td  class="left_txt"><img src="${rc.contextPath}/images/icon_mail.gif" width="16" height="11">
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
	
	/**
	*按条件导出excel
	*/
	$("#tjexport").click(function(){ 
		$("#myForm").attr("action","${rc.contextPath}/historyprice/downloadExcel");
		$("#myForm").submit();
	});
	
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
	
	function query(){
		var beginDate = $("#begintime").val();
		var endDate = $("#endtime").val();
		var isnew = $("#isnew").val();
		var company = $("#jigouname").val();
		window.location.href="${rc.contextPath}/historyprice/chaxun?beginDate="+beginDate+"&endDate="+endDate+"&isnew="+isnew+"&company="+company;
	}
	function submitmyForm(){
		$("#myForm").attr("action","${rc.contextPath}/historyprice/chaxun");
		$("#myForm").submit();
	}
	
</script>
</html>