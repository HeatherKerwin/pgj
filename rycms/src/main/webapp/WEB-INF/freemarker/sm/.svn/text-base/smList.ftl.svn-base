<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="../../commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="../../commons/styles/bootstrap.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../commons/scripts/bootstrap.min.js"></script>
<link rel="stylesheet" href="../../commons/styles/default.css" />
</head>

<body>
<div id="top">
	<div class="left">
		<button type="button" class="btn btn-success" id="add" onclick="javascript:window.location.href='../../manage/servicememberAdd.jsp'">+ 新增推荐人</button>
		
	</div>
	<div class="right">
		
	</div>
</div>
<form action="" id="form1" method="post">
<table class="table table-bordered table-hover">
	<thead>
	  <tr>
		<th class="text-center td-checkbox"><input type="checkbox" id="chk-all" /></th>
		<th class="text-left">名字</th>
		<th class="text-left">编号</th>
		<th class="text-left">上月活</th>
		<th class="text-left">本月活</th>
		<th class="text-left">上周活</th>
		<th class="text-left">本周活</th>
		<th class="text-left">本周新增用户</th>
		<th class="text-left">本月新增用户</th>
		<th class="text-left">删除</th>
	  </tr>
	</thead>
	<tbody>
	<#list lastMonthData as data>
		<tr>
			<td class="text-center td-checkbox"><input type="checkbox" id="" value="${data[3]}" name="idcheckbox" /></td>
			<td class="text-left td-name">
				<a href="<%=path%>/servicemember/toupdate/?id=${data[3]}">${data[0] }</a>
			</td>
			<td class="text-left td-name">
				${data[1] }
			</td>
			<td class="text-left td-name">
				${data[2] }
			</td>
			<td class="text-left td-name">
				${monthDataMap[data[1]] }
			</td>
			<td class="text-left td-name">
				${lastWeekDataMap[data[1]] }
			</td>
			<td class="text-left td-name">
				${weekDataMap[data[1]] }
			</td>
			<td class="text-left td-name">
				${weekCustomDataMap[data[1]] }
			</td>
			<td class="text-left td-name">
				${monthCustomDataMap[data[1]] }
			</td>
			<td class="text-left td-name" onclick="confirmdelete(${data[3]})">
				删除
			</td>
		</tr>
	</#list>
	</tbody>
</table>
</form>
<#--
<div class="pagination text-center">
	<ul>
		<c:if test="${pageModel.pageCount!=0 }">
	      	<c:if test="${pageModel.currentPage!=1 }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-1 })">&laquo;</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-4>0)&&(pageModel.currentPage+1>pageModel.totalPages) }">
	      		<li><a href="#" onclick="jumpPage(${request.pageModel.currentPage-4})">${pageModel.currentPage-4 }</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-3>0)&&(pageModel.currentPage+2>pageModel.pageCount)}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-3})">${pageModel.currentPage-3 }</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-2>0)}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-2})">${pageModel.currentPage-2}</a></li>
	      	</c:if>
	      	<c:if test="${(pageModel.currentPage-1>0)}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage-1})">${pageModel.currentPage-1}</a></li>
	      	</c:if>
	      	<li class="active"><a href="#" onclick="jumpPage(${pageModel.currentPage})" class="select">${pageModel.currentPage}</a></li>
	      	<c:if test="${pageModel.currentPage+1<=pageModel.pageCount}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+1})">${pageModel.currentPage+1}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage+2<=pageModel.pageCount }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+2})">${pageModel.currentPage+2}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage+3<=pageModel.pageCount&&pageModel.currentPage-2<=0}">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+3})">${pageModel.currentPage+3}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage+4<=pageModel.pageCount&&pageModel.currentPage-3<=0 }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+4})">${pageModel.currentPage+4}</a></li>
	      	</c:if>
	      	<c:if test="${pageModel.currentPage!=pageModel.pageCount }">
	      		<li><a href="#" onclick="jumpPage(${pageModel.currentPage+1})">&raquo;</a></li>
	      	</c:if>
	      </c:if>
	</ul>
</div>-->

<script language="javascript">

	$(document).on('click','#chk-all',function(){
        $(this).closest('table').find('input[name="idcheckbox"]').prop('checked', this.checked);
	});

	$(document).on('click','.td-name',function(){
        var obj = $(this).closest('tr').find('input');
        if(obj.prop('checked') == true){   
          obj.prop('checked',false);  
        } else{   
          obj.prop('checked',true); 
		}
	});
	
	function updateStatus(flag){
		var checkboxObj = document.getElementsByName("idcheckbox");
		var chooseFlag = false;
		for(var i = 0; i < checkboxObj.length;i++){
			if(checkboxObj[i].checked){
				chooseFlag = true;
				break;
			}
		}
		if(chooseFlag){
			if(confirm("确定更改状态吗")){
				$("#choosestatus").val(flag);
				$("#form1").submit();
			}
		}
	}
	function jumpPage(jumpPageNumber){
		
		window.location.href="<%=path%>/servicemember/list/?currentPage="+jumpPageNumber;
	}
	function search(){
		window.location.href="<%=path%>/servicemember/list/?currentPage=1";
	}
	
	function confirmdelete(servicememberid){
		if(confirm("确定删除吗?")){
			//location.href="<%=path%>/servicemember/delete/?id="+servicememberid;
		}
	}
</script>
</body>
</html>