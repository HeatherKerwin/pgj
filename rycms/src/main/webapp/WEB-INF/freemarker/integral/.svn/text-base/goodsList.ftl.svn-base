<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>新增商品</title>
</head>
<link rel="shortcut icon" href="https://img.utiexian.com/common/icon/32.png">
<link rel="icon" href="https://img.utiexian.com/common/icon/32.png" sizes="32x32">
<link rel="icon" href="https://img.utiexian.com/common/icon/192.png" sizes="192x192">
<link rel="apple-touch-icon-precomposed" href="https://img.utiexian.com/common/icon/180.png">
<meta name="msapplication-TileImage" content="https://img.utiexian.com/common/icon/270.png">
<link href="../commons/styles/bootstrap3.css" rel="stylesheet">
<body>
    <div class="panel panel-default">
        <div class="panel-heading">商品管理</div>
        <!--查询条件-->
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal" method="POST">
                <div class="form-group">
                    <!--商品编号-->
                    <label class="control-label col-sm-2" for="bns_search_number">商品编号</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="goodsNo" value="${goodsNo}" id="goodsNo">
                    </div>
                    <!--商品名字-->
                    <label class="control-label col-sm-2" for="mall_name">商品名字</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="goodsName" value="${goodsName}" id="goodsName">
                    </div>
                    <!--状态-->
                    <label class="control-label col-sm-2" for="mall_type">状态</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="state" id="state">
                            <option value="">全部</option>
                            <option value="0" <#if state== "0">selected="selected"</#if>>待上架</option>
                            <option value="1" <#if state== "1">selected="selected"</#if>>已上架</option>
                            <option value="2" <#if state== "2">selected="selected"</#if>>已售罄</option>
                            <option value="3" <#if state== "3">selected="selected"</#if>>已删除</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <!--金额-->
                    <label class="control-label col-sm-2" for="mall_money_begin">价格</label>
                    <div class="col-sm-1" style="margin-right: -8px">
                        <input type="text" name="minPrice" value="${minPrice}" class="form-control pull-left" id="minPrice">
                    </div>
                    <label for="mall_money_end" class="control-label pull-left">至</label>
                    <div class="col-sm-1" style="margin-left: -7px">
                        <input type="text" name="maxPrice" value="${maxPrice}" class="form-control" id="maxPrice" >
                    </div>
                    <!--金额-->
                    <label class="control-label col-sm-2" for="mall_number_begin">库存</label>
                    <div class="col-sm-1" style="margin-right: -8px">
                        <input type="text" name="minStock" value="${minStock}" class="form-control pull-left" id="minStock">
                    </div>
                    <label for="mall_number_end" class="control-label pull-left">至</label>
                    <div class="col-sm-1" style="margin-left: -7px">
                        <input type="text" name="maxStock" value="${maxStock}" class="form-control" id="maxStock" >
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 col-sm-offset-3" style="margin-top: 10px">
                        <button type="button" id="mall_" onclick="search();" class="btn btn-danger btn-block center-block">查询</button>
                    </div>
                    <div class="col-sm-2 col-sm-offset-1" style="margin-top: 10px">
                        <button type="button" onclick="add();" id="mall_add" class="btn btn-danger btn-block center-block">新增</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- 表格-->
        <div class="panel-body">
            <table class="table table-bordered">
                <thead>
                    <tr class="active">
                        <th>
                            商品编号
                        </th>
                        <th>
                            商品名称
                        </th>
                        <th>
                            价格（积分）
                        </th>
                        <th>
                            库存（件）
                        </th>
                        <th>
                            排序
                        </th>
                        <th>
                            状态
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>
                </thead>
                <tbody>
                <#if  pr??  && pr.results??>
						<#list pr.results as goods>                     
	                      <tr class="success">
	                        <td>
	                            ${goods.no}
	                        </td>
	                        <td>
	                            ${goods.goodsName}
	                            <#if goods.hotGoods==0>
	                            	（热门商品）
	                            </#if>
	                        </td>
	                        <td>
	                            ${goods.integral}
	                        </td>
	                        <td>
	                            ${goods.stock}
	                        </td>
	                        <td>
	                           	${goods.sort}
	                        </td>
	                        <td>
							    <#if goods.state==0>  
							       	 待上架
							    <#elseif goods.state==1>  
									已上架
							    <#elseif goods.state==2>  
							       	 已售罄
							    <#elseif goods.state==3>  
							       	 已删除
							    </#if>
	                        </td>
	                        <td>
	                        	<#if goods.state!=3>
		                            <a id="bns_mall_edit" href="#modal_bns_con" class="btn btn-danger btn-sm" role="button" onclick="edit(${goods.id});" data-toggle="modal">编辑</a>
		                            <a id="bns_mall_delete" href="#modal_bns_remark" class="btn btn-danger btn-sm" role="button" onclick="remove(${goods.id});" data-toggle="modal">删除</a>
	                        		<#if goods.state==0>
			                            <a id="bns_mall_delete" href="#modal_bns_remark" class="btn btn-danger btn-sm" onclick="putaway(${goods.id});" role="button" data-toggle="modal">上架</a>
		                        	</#if>
		                        	<#if goods.state==1>
		                        		<#if goods.hotGoods==0>
			                            	<a id="bns_mall_delete" href="#modal_bns_remark" class="btn btn-danger btn-sm" onclick="downhot(${goods.id});" role="button" data-toggle="modal">下热门</a>
		                        		<#else>
			                            	<a id="bns_mall_delete" href="#modal_bns_remark" class="btn btn-danger btn-sm" onclick="gohot(${goods.id});" role="button" data-toggle="modal">上热门</a>
		                        		</#if>
		                        	</#if>
	                        	</#if>
	                        </td>
	                    </tr>
						</#list>
				     </#if> 
                </tbody>
            </table>
            <div id="pager">
		  		<#import "/common/pager.ftl" as q>
		  		<#if pr.totalCount??>
		  			<@q.pager pageNo=pr.currentPage pageSize=pr.pageSize recordCount=pr.totalCount toURL="../goods/list"  />
		  		</#if>
		    </div>
            <div class="col-sm-2" style="margin-top: 10px">
                <button type="button" id="mall_all_up" onclick="putaway();" class="btn btn-danger btn-block center-block">将待上架商品全部上架</button>
            </div>
        </div>
    </div>
<script src="../commons/scripts/jquery-1.10.1.min.js"></script>
<script src="../commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript">
	function search(){
		$("#formSearch").action="../goods/list/";
		$("#formSearch").submit();
	}
	
	function add(){
		window.location.href="../goods/toAddOrEdit";
	}
	
	function edit(id){
		window.location.href="../goods/toAddOrEdit?id="+id;
	}
	
	function putaway(id){
		if(typeof(id)=="undefined"){
			window.location.href="../goods/putaway";
		}else{
			window.location.href="../goods/putaway?id="+id;
		}
	}
	
	function remove(id){
		if(confirm("确认删除此商品？"))
	 	{
	 		window.location.href="../goods/remove?id="+id;
	 	}
	}
	
	function gohot(id){
		window.location.href="../goods/gohot?id="+id;
	}
	
	function downhot(id){
		window.location.href="../goods/downhot?id="+id;
	}
</script>
</body>
</html>