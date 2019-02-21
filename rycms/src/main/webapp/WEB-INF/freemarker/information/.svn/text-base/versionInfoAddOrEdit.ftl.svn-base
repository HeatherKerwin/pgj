<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>xi</title>
<link rel="stylesheet" type="text/css" href="../css/skin-1.css" />
<link rel="stylesheet" href="../commons/styles/reset.css" />
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="../commons/styles/bootstrap.css"/>
<link rel="stylesheet"  href="../commons/styles/bootstrap-datetimepicker.min.css"/>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../commons/scripts/jquery-1.10.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../commons/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="../commons/scripts/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../commons/scripts/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<link rel="stylesheet" href="../commons/styles/default.css" />
<link href="../commons/styles/bootstrap3.css" rel="stylesheet">
<!-- 上传组件Uploadify -->
<script src="../commons/scripts/jquery.uploadify.js"></script>

<script type="text/javascript" src="../commons/scripts/WebCalendar.js"></script>
<script src="../commons/scripts/jquery-1.10.1.min.js"></script>
<script src="../commons/scripts/bootstrap.js"></script>
<!--编辑器-->
<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6WIEK15yVVcchWSWBg1GVSx8"></script>

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
</script>
</head>
<body>
<div class="panel panel-default">
        <div class="panel-heading">app下载页维护</div>
        <!--查询条件-->
        <div class="panel-body">
            <form id="form1" action="../versioninfo/save" class="form-horizontal">
            <#if edit??>
        		<input name="id" type="hidden" value="${info.id}"/>
        	</#if>
        		<input name="state" type="hidden" value="${info.state}"/>
                <div class="form-group">
                    <!--版本-->
                    <label class="control-label col-sm-2" for="banner_edition">版本</label>
                    <div class="col-sm-2">
                        <input type="text" name="version" value="${info.version}" class="form-control" id="banner_edition">
                    </div>
                    <!--系统-->
                    <label class="control-label col-sm-2" for="banner_system">系统</label>
                    <div class="col-sm-2">
                        <input type="text" name="xitong" value="${info.xitong}" class="form-control" id="banner_system">
                    </div>
                </div>
                <div class="form-group">
                    <!--大小-->
                    <label class="control-label col-sm-2" for="banner_size">大小</label>
                    <div class="col-sm-2">
                        <input type="text" name="size" value="${info.size}" class="form-control" id="banner_size">
                    </div>
                    <!--更新时间-->
                    <div class="edit-box">
						<label class="control-label col-sm-2" for="banner_size">更新时间</label>
						<div class="col-sm-2">
							<input name="shijian" id="day" type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" readonly style="width:206px;cursor:pointer" value="<#if info.shijian??>${info.shijian?string('yyyy-MM-dd hh:mm:ss')}</#if>"/>   
						</div>
					</div>
                </div>
                <!-- 更新内容-->
                <div class="form-group">
                    <label for="banner_text" class="col-sm-2 control-label text-left">更新内容</label>
                    <div class="col-sm-6">
                        <textarea id="banner_text" name="content" class="form-control" rows="3" placeholder="更新内容字数限制为500个字以内。">${info.content}</textarea>
                    </div>
                </div>
                <!-- 应用介绍-->
                <div class="form-group">
                    <label for="banner_introduce" class="col-sm-2 control-label text-left">应用介绍</label>
                    <div class="col-sm-6">
                        <textarea id="banner_introduce" name="jieshao" class="form-control" rows="3" placeholder="应用介绍字数限制为500个字以内。">${info.jieshao}</textarea>
                    </div>
                </div>
                <!-- 上传图片-->
                <div class="form-group">
                    <div class="form-group">
                        <label for="banner_browse1" class="col-sm-2 control-label text-left">banner1</label>
                        <div class="col-sm-10">
                            <input type="file" id="fileId1" name="file" value="浏览" class="col-sm-4">
                            <button type="button" id="uploadClickInput2" class="btn btn-danger col-sm-1" data-dismiss="modal" onclick="uploadImg('banner1','banner1path','fileId1');">上传</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <#if info.banner1??>
					            <img class="img" id="banner1" onerror="javascript:this.src='../images/nopic.png';" src="${info.banner1}" alt="图片在此显示"  />	
					            <input type="hidden" id="banner1path" name="banner1path" value="${info.banner1}"/>
			            <#else>
					            <img class="img" id="banner1" alt="图片在此显示"  />
					            <input type="hidden" id="banner1path" name="banner1path" value=""/>	
					    </#if>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group">
                    	<label for="banner_browse1" class="col-sm-2 control-label text-left">banner2</label>
                        <div class="col-sm-10">
                            <input type="file" id="fileId2" name="file" value="浏览" class="col-sm-4">
                            <button type="button" id="uploadClickInput2" class="btn btn-danger col-sm-1" data-dismiss="modal" onclick="uploadImg('banner2','banner2path','fileId2');">上传</button>
                        </div>
                    </div>
                    <div class="form-group">
					    <#if info.banner2??>
					            <img class="img" id="banner2" onerror="javascript:this.src='../images/nopic.png';" src="${info.banner2}" alt="图片在此显示"  />	
					            <input type="hidden" id="banner2path" name="banner2path" value="${info.banner2}"/>
			            <#else>
					            <img class="img" id="banner2" alt="图片在此显示"  />
					            <input type="hidden" id="banner2path" name="banner2path" value=""/>	
					    </#if>
                    </div>
                </div>
                <div class="form-group">
                        <div class="col-sm-10">
                            <input type="button" onclick="chooseSubmitMethod1();" value="保存" class="btn btn-danger col-sm-1">
                        </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 产品列表结束 --> 
<!-- 分页按钮--> 
<!-- 底部部分 -->

</body>
</html>