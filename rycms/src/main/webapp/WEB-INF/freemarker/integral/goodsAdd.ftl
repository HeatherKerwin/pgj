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
<!-- 上传组件Uploadify -->
<script src="../commons/scripts/jquery-1.10.1.min.js"></script>
<script src="../commons/scripts/jquery.uploadify.js"></script>
<script src="../commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<style>
    .filePrew {
        display: block;
        overflow: hidden;
        position: absolute;
        top: 0;
        left: 15px;
        width: 54px;
        height: 34px;
        font-size: 100px;
        opacity: 0;
        filter: alpha(opacity=0);
    }

</style>
<body>
    <div class="panel panel-default">
        <div class="panel-heading">新增商品</div>
        <!--查询条件-->
        <div class="panel-body">
        
            <form id="formSearch" class="form-horizontal">
            	<input type="hidden" name="id" value="${goods.id}">
            	<input type="hidden" name="no" value="${goods.no}">
            	<input type="hidden" name="state" value="${goods.state}">
            	<input type="hidden" name="hotGoods" value="${goods.hotGoods}">
            	<!-- 上传图片-->
                <div class="form-group">
                    <div class="form-group">
                        <label class="col-md-2 control-label text-left">商品图片</label>
                        <div class="col-md-3">
                            <a class="btn btn-danger" href="javascript:void(0);">
                                <span>浏览</span>
                                <input type="file" id="fileId1" name="file" class="filePrew" data-dismiss="modal" />
                            </a>
                            <button type="button" id="uploadClickInput1" class="btn btn-danger" onclick="uploadImg('banner1','banner1path','fileId1');" data-dismiss="modal">上传</button>
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-danger" href="javascript:void(0);">
                                <span>浏览</span>
                                <input type="file" id="fileId2" name="file" class="filePrew" data-dismiss="modal" />
                            </a>
                            <button type="button" id="uploadClickInput2" class="btn btn-danger" onclick="uploadImg('banner2','banner2path','fileId2');" data-dismiss="modal">上传</button>
                            <button type="button" id="mall_add2" class="btn btn-danger" onclick="deleteImg(2);" data-dismiss="modal">删除</button>
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-danger" href="javascript:void(0);">
                                <span>浏览</span>
                                <input type="file" id="fileId3" name="file" class="filePrew" data-dismiss="modal" />
                            </a>
                            <button type="button" id="uploadClickInput3" class="btn btn-danger" onclick="uploadImg('banner3','banner3path','fileId3');" data-dismiss="modal">上传</button>
                            <button type="button" id="mall_add2" class="btn btn-danger" onclick="deleteImg(3);" data-dismiss="modal">删除</button>
                        </div>
                    </div>
                    <div class="form-group">
	                    <#if goods.banner1??>
	                        <img src="${goods.banner1}" id="banner1" alt="..." class="img-responsive col-md-3 col-md-offset-2">
	                        <input type="hidden" id="banner1path" name="banner1path" value="${goods.banner1}"/>
						<#else>
	                    	<img src="" id="banner1" alt="..." class="img-responsive col-md-3 col-md-offset-2">
	                        <input type="hidden" id="banner1path" name="banner1path" value=""/>
	                    </#if>
	                    <#if goods.banner2??>
	                    	<img src="${goods.banner2}" id="banner2" alt="..." class="img-responsive col-md-3">
	                        <input type="hidden" id="banner2path" name="banner2path" value="${goods.banner2}"/>
	                    <#else>
	                    	<img src="" id="banner2" alt="..." class="img-responsive col-md-3">
	                        <input type="hidden" id="banner2path" name="banner2path" value=""/>
	                    </#if>
	                    <#if goods.banner3??>
	                        <img src="${goods.banner3}" id="banner3" alt="..." class="img-responsive col-md-3">
	                        <input type="hidden" id="banner3path" name="banner3path" value="${goods.banner3}"/>
	                    <#else>
	                    	<img src="" id="banner3" alt="..." class="img-responsive col-md-3">
	                        <input type="hidden" id="banner3path" name="banner3path" value=""/>
	                    </#if>
                    </div>
                </div>
                <div class="form-group">
                    <!--商品名字-->
                    <label class="control-label col-md-2" for="goodsName">商品名称</label>
                    <div class="col-md-6">
                        <input type="text" name="goodsName" value="${goods.goodsName}" class="form-control" id="goodsName">
                    	您还可以输入<span id="sp">30</span></span>字
                    </div>
                </div>
                <div class="form-group">
                    <!--价格-->
                    <label class="control-label col-md-2" for="integral">价格</label>
                    <div class="col-md-1">
                        <input type="text" class="form-control" value="${goods.integral}" name="integral" id="integral">
                    </div>
                    <div class="control-label col-md-1">
                        积分
                    </div>
                    <!--库存-->
                    <label class="control-label col-md-2" for="stock">库存</label>
                    <div class="col-md-2">
                        <input type="text" class="form-control" value="${goods.stock}" name="stock" id="stock">
                    </div>
                </div>
                <div class="form-group">
                    <!--排序-->
                    <label class="control-label col-md-2" for="sort">排序</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" value="${goods.sort}" name="sort" id="sort">
                    </div>
                </div>
                <!-- 商品参数-->
                <div class="form-group">
                    <label for="mall_parameter" class="col-md-2 control-label text-left">商品参数</label>
                    <div class="col-md-6">
                        <textarea id="goodsParam" name="goodsParam" class="form-control" rows="3" placeholder="不超过200字">${goods.goodsParam}</textarea>
                    	您还可以输入<span id="sp1">200</span></span>字
                    </div>
                </div>
                <!-- 商品说明-->
                <div class="form-group">
                    <label for="mall_text" class="col-md-2 control-label text-left">商品说明</label>
                    <div class="col-md-6">
                        <textarea id="goodsExplain" name="goodsExplain" class="form-control" rows="3" placeholder="不超过200字">${goods.goodsExplain}
                        </textarea>您还可以输入<span id="sp2">200</span></span>字
                    </div>
                </div>
                <!-- 上传图片-->
                <div class="form-group">
                    <div class="form-group">
                        <label class="col-md-2 control-label text-left">请添加（1~3）张介绍图片</label>
                        <div class="col-md-3">
                            <a class="btn btn-danger" href="javascript:void(0);">
                                <span>浏览</span>
                                <input type="file" id="fileId4" name="file" class="filePrew" data-dismiss="modal" />
                            </a>
                            <button type="button" id="mall_add1" class="btn btn-danger" onclick="uploadImg('explainBanner1','explainBanner1path','fileId4');" data-dismiss="modal">上传</button>
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-danger" href="javascript:void(0);">
                                <span>浏览</span>
                                <input type="file" id="fileId5" name="file" class="filePrew" data-dismiss="modal" />
                            </a>
                            <button type="button" id="mall_add2" class="btn btn-danger" onclick="uploadImg('explainBanner2','explainBanner2path','fileId5');" data-dismiss="modal">上传</button>
                        	<button type="button" id="mall_add2" class="btn btn-danger" onclick="deleteImg(5);" data-dismiss="modal">删除</button>
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-danger" href="javascript:void(0);">
                                <span>浏览</span>
                                <input type="file" id="fileId6" name="file" class="filePrew" data-dismiss="modal" />
                            </a>
                            <button type="button" id="mall_add3" class="btn btn-danger" onclick="uploadImg('explainBanner3','explainBanner3path','fileId6');" data-dismiss="modal">上传</button>
                        	<button type="button" id="mall_add2" class="btn btn-danger" onclick="deleteImg(6);" data-dismiss="modal">删除</button>
                        </div>
                    </div>
                    <div class="form-group">
                    <#if goods.explainBanner1??>
                        <img src="${goods.explainBanner1}" alt="..." id="explainBanner1" class="img-responsive col-md-3 col-md-offset-2">
                        <input type="hidden" id="explainBanner1path" name="explainBanner1path" value="${goods.explainBanner1}"/>
                    <#else>
                    	<img src="" alt="..." id="explainBanner1" class="img-responsive col-md-3 col-md-offset-2">
                        <input type="hidden" id="explainBanner1path" name="explainBanner1path" value=""/>
                    </#if>
                    <#if goods.explainBanner2??>
                        <img src="${goods.explainBanner2}" alt="..." id="explainBanner2" class="img-responsive col-md-3 ">
                        <input type="hidden" id="explainBanner2path" name="explainBanner2path" value="${goods.explainBanner2}"/>
                    <#else>
                    	<img src="" alt="..." id="explainBanner2" class="img-responsive col-md-3 ">
                        <input type="hidden" id="explainBanner2path" name="explainBanner2path" value=""/>
                    </#if>
                    <#if goods.explainBanner3??>
                        <img src="${goods.explainBanner3}" alt="..." id="explainBanner3" class="img-responsive col-md-3 ">
                        <input type="hidden" id="explainBanner3path" name="explainBanner3path" value="${goods.explainBanner3}"/>
                    <#else>
                    	<img src="" alt="..." id="explainBanner3" class="img-responsive col-md-3 ">
                        <input type="hidden" id="explainBanner3path" name="explainBanner3path" value=""/>
                    </#if>
                    </div>
                </div>
            </form>
            <button type="button" id="baocun" onclick="baocun();" class="btn btn-danger" data-dismiss="modal">保存</button>
        </div>
    </div>

	<script type="text/javascript">
	function uploadImg(targetId, targetInputId,uploadInputId) {		
		var uploadId="ryUpload";	
		if(uploadInputId==undefined){
			var fileName=jQuery("#wokeUpload").val();
		}else{
			var fileName=jQuery("#"+uploadInputId).val();
			uploadId=uploadInputId;
		}
		var strtype=fileName.substring(fileName.length-3);
		strtype=strtype.toLowerCase();
		if (strtype!="jpg"&&strtype!="gif"&&strtype!="png"){
			alert("请上传JPG、GIF、PNG格式的图片！");
				return false;
		}
		$.ajaxFileUpload({
			url : '../pic/upload/',
			secureuri : false,
			dataType : 'json',
			data : {
				"targetDom" : "#"+targetId+",#"+targetInputId
			},
			fileElementId : uploadId,
			success : function(data) {
					if(data=="error"){
						alert("上传失败！");
					}else{
						$("#" + targetInputId).val(data);
						$("#" + targetId).attr("src", data);
					} 
			},
			error : function() {
				alert("上传失败！");
			}
		});
	}
	function chooseSubmitMethod1(){
		if(confirm("确定执行操作吗")){
			$('#form1').submit();
		}
	}
	/**
	*  图片删除
	*/
	function deleteImg(num){
		if(num==2){
			$("#banner2path").val("");
			$("#banner2").attr("src","");
		}else if(num==3){
			$("#banner3path").val("");
			$("#banner3").attr("src","");
		}else if(num==5){
			$("#explainBanner2path").val("");
			$("#explainBanner2").attr("src","");
		}else if(num==6){
			$("#explainBanner3path").val("");
			$("#explainBanner3").attr("src","");
		}
	}
	
	function baocun(){
		if($("#banner1path").val()==""){
			alert("请至少选择一张商品图");
			return;
		}
		if($("#explainBanner1path").val()==""){
			alert("请至少选择一张商品介绍图");
			return;
		}
		$("#formSearch").attr("action","../goods/save");
		$("#formSearch").submit();
	}
	
	limitNumber("goodsName", "sp", 30);//输入框id,统计元素id,允许输入字符数  
	limitNumber("goodsExplain", "sp2", 200);//输入框id,统计元素id,允许输入字符数  
	limitNumber("goodsParam", "sp1", 200);//输入框id,统计元素id,允许输入字符数  
    function limitNumber(input, span, maxlength) {  
        var obj = document.getElementById(input);  
        var showObj = document.getElementById(span);  
  
        if (window.attachEvent) {//由于有兼容性问题需要做是否是ie的判断  
            obj.onpropertychange = function () {  
                var length = (this.value.length).toString();  
                if (length <= maxlength) {  
                    showObj.innerHTML = maxlength-length;  
                } else {  
                    showObj.innerHTML = maxlength-maxlength;  
                    this.value = this.value.substr(0, maxlength);//截取前十个字符显示到输入框  
                }  
            }  
        } else {  
            obj.oninput = function () {  
                var length = (this.value.length).toString();  
                if (length <= maxlength) {  
                    showObj.innerHTML = maxlength-length;  
                } else {  
                    showObj.innerHTML = maxlength-maxlength;  
                    this.value = this.value.substr(0, maxlength);  
                }  
            }  
        }  
    }  
	</script>
</body>
</html>