<script type="text/javascript">
var memberId = '${member.id}';
var bool = false;

/**
 * 用户操作认证拦截的选择（对需要开户的操作进行校验）
 * @param path 跳转的页面路径
 * @param type 0出票方   1资方（收票方）
 */
function checkAccount(type,path){
	//checkAuthentication(type,path,null);
	checkJdCib(type,path);
};

/**
 * 库存下单校验（认证后可以贴现）
 * @param path 跳转的页面路径
 * @param id 库存清单的主键
 */
function discountAuthentication(path,id){
	checkAuthentication(0,path,id);
};

/**
 * 需要认证的拦截校验
 * @param type 0出票方   1资方（收票方）
 * @param path 跳转的页面路径
 */
function checkJdCib(type,path){
	var url = '${bootAppPath}/jdjr/cib/account';
	if(memberId == null || memberId == ""){
		if(path != null && path != ""){
			location.href = "${basePath}/login";
		}
	}
	var params = {memberId:memberId,type:type};
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		if (data.response == 'success') {
			var jdjr = data.data.jdjr;
			var cib = data.data.cib;
			if(path!=null&&path!=""){//存在有页面跳转
				if((jdjr!=null&&jdjr.status==2&&jdjr.check_state=="PASS")||(cib!=null&&cib.status==2&&cib.cib_check_state=="PASS")){//开户成功
					var map = new Map();
					map.put("_PAGE", path);//必传
					map.put("role", type);
					
					_OPENPAGE_FORM(map);
				}else{
					inspectJdCib();
				}
			}else{
				inspectJdCib();
			}
		}
	}
}

/**
 * 需要认证的拦截校验
 * @param type 0出票方   1资方（收票方）
 * @param path 跳转的页面路径
 * @param id 库存清单的主键
 */
function checkAuthentication(type,path,id){
	var url = '${bootAppPath}/orginfo/rz';
	if(memberId == null || memberId == ""){
		if(path != null && path != ""){
			location.href = "${basePath}/login";
		}
	}
	var params = {memberId:memberId,type:type};
	var res = bootPost(url,params);
	if(res != null){
		var data = res.data;
		if (data.response == 'success') {
			var active = data.data;
			var cib = active.cib;
			var cibCheckState;
			if(active.info!=null){
				cibCheckState = active.info.cibCheckState;
			}
			if(path!=''){
				if(cibCheckState != 'PASS' && cib.status != 2){//没开户
					var map = new Map();
					map.put("_PAGE", "price/renzheng");//必传
					map.put("role", type);
					_OPENPAGE_FORM(map);
				}else{//已开户
					var map = new Map();
					map.put("_PAGE", path);//必传
					map.put("role", type);
					map.put("orgId", active.info.orgId);
					if(id != null)map.put("inventoryId",id);//库存清单的Id
					
					_OPENPAGE_FORM(map);
				}
			}else{
				if((cibCheckState != "PENDING"&&cibCheckState != "PASS")||cibCheckState == "NOPASS"){//orgInfo的审核状态
					if(cibCheckState == "NOPASS"&&cib.name == null){
						//绑定审核失败，回到绑定审核第二步页面，页面中重新获取开户信息将审核中按钮隐藏，重新开户按钮显示
						if(type == 0 && !bool){
							bool = true;
							checkAuthentication(1,path,null);
							return ;
						}
						var map = new Map();
						map.put("_PAGE", "bisic_message/authentication_orgUser2");//必传
						map.put("bizLicenceRegisteredNo", active.info.blNumber);//注册号
						map.put("role", type);
						_OPENPAGE_FORM(map);
						return;
					}
					if(cib.name == null || cib.status==7){//没有企业名称，或者有，开户信息已失效
						if(type == 0 && !bool){
							bool = true;
							checkAuthentication(1,path,null);
							return ;
						}else if(bool && type == 1){
							checkAuthentication(0,path,null);
							return ;
						}else{
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_open");//必传
							map.put("role", type);
							_OPENPAGE_FORM(map);
						}
					}else if(cib.imgPath20 == ""||cib.imgPath20 == null){//图片为空，去证件上传页面
						if(type == 0 && !bool){
							bool = true;
							checkAuthentication(1,path,null);
							return ;
						}
						var map = new Map();
						map.put("_PAGE", "bisic_message/authentication_orgUpload");//必传
						map.put("role", type);
						_OPENPAGE_FORM(map);
					}else if(cib.status == 0){//开户待审核
						if(type == 0 && !bool){
							bool = true;
							checkAuthentication(1,path,null);
							return ;
						}
						//到审核的页面，可修改信息和图片
						var map = new Map();
						map.put("_PAGE", "bisic_message/authentication_orgExamine");//必传
						map.put("role", type);
						map.put("cibId", cib.id);
						_OPENPAGE_FORM(map);
					}else if(cib.status == 1){//开户审核失败
						if(type == 0 && !bool){
							bool = true;
							checkAuthentication(1,path,null);
							return ;
						}
						var map = new Map();
						map.put("_PAGE", "bisic_message/authentication_orgExamine");//必传
						map.put("role", type);
						map.put("cibId", cib.id);
						_OPENPAGE_FORM(map);
					}else if(cib.status == 5){//审核通过,待鉴定
						//到小额鉴定
						if(type == 0 && !bool){
							bool = true;
							checkAuthentication(1,path,null);
							return ;
						}
						var map = new Map();
						map.put("_PAGE", "bisic_message/authentication_orgFinish");//必传
						map.put("role", type);
						map.put("bankAcctAcctNo", cib.bankAcctAcctNo);//银行账号
						map.put("bankAcctAcctName", cib.bankAcctAcctName);//银行账户名
						map.put("cibId", cib.id);//银行账户名
						_OPENPAGE_FORM(map);
					}else if(cib.status == 6){//小额鉴定失败
						//重新鉴定
						if(type == 0 && !bool){
							bool = true;
							checkAuthentication(1,path,null);
							return ;
						}
						var map = new Map();
						map.put("_PAGE", "bisic_message/authentication_orgFinish");//必传
						map.put("role", type);
						_OPENPAGE_FORM(map);
					}else if(cib.status == 2){
						//已通过审核显示开户信息
						var map = new Map();
						map.put("_PAGE", "bisic_message/authentication_success");//必传
						map.put("openFlag", "ok");
						map.put("role", type);
						_OPENPAGE_FORM(map);
					}
				}else{//已有orgInfo
					if(active.info.cibCheckState == 'PENDING'){
						if(type == 0 && !bool){
							bool = true;
							checkAuthentication(1,path,null);
							return ;
						}
						//绑定审核中
						var map = new Map();
						map.put("_PAGE", "bisic_message/authentication_orgUser2");//必传
						map.put("role", type);
						_OPENPAGE_FORM(map);
					}else if(active.info.cibCheckState == 'PASS'){
						if(localStorage["FLAG"]!="1"){//直接去开户信息
							//已通过审核显示开户信息
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_success");//必传
							map.put("openFlag", "ok");
							map.put("role", type);
							_OPENPAGE_FORM(map);
						}else{//去绑定完成页面
							var map = new Map();
							map.put("_PAGE", "bisic_message/authentication_orgUser3");//必传
							map.put("role", type);
							map.put("name", cib.name);//公司名
							map.put("bizLicenceRegisteredNo", cib.bizLicenceRegisteredNo);//注册号
							_OPENPAGE_FORM(map);
							
						}
					}
				}
			}
			
		}
	}
}

/**
 * 校验京东的开户
 */
function inspectJdCib(){
	var url = '${bootAppPath}/jdjrbind/get/jdjr/jdjrbind';
	var params = {memberId:memberId};
	var data = bootPost(url,params);
	if(data != null){
		if(data.data.response == 'success'){
			if(data.data.data!=null){
				var cib = data.data.data.jdjr;
				var jdjrBind = data.data.data.jdjrBind;
				console.log(data);
				if(cib!=null){//是否已经开户
					if(cib.memberId == memberId){//母账户
						if(cib.status == 0){//待审核
							location.href="${basePath}/jd/examine";
						}else if(cib.status == 1){//审核失败
							location.href="${basePath}/jd/examine";
						}else if(cib.status == 2){//正常
							location.href="${basePath}/jd/success";
						}else if(cib.status == 3){//锁定
							location.href="${basePath}/jd/success";
						}else if(cib.status == 4){//无效
							location.href="${basePath}/jd/examine";
						}else if(cib.status == 5){//审核通过待鉴定
							location.href="${basePath}/jd/small/money";
						}else if(cib.status == 6){//鉴定失败
							location.href="${basePath}/jd/small/money";
						}else{
							location.href="${basePath}/jd/apply/open/account";
						}
					}else{
						if(jdjrBind.checkState=='PENDING'){//子账户开户审核
							location.href="${basePath}/jd/bind/examine";
						}else if(jdjrBind.checkState=='NOPASS'){//审核没通过的
							location.href="${basePath}/jd/bind/examine";
						}else if(jdjrBind.checkState=='PASS'){//已开通
							location.href="${basePath}/jd/success";
						}else{//审核没通过
							location.href="${basePath}/jd/bind/examine";
						}
					}
				}else{
					location.href="${basePath}/jd/apply/open/account";
				}
			}else{//还没有开户
				location.href="${basePath}/jd/apply/open/account";
			}
		}else{
			alert(data.data.msg);
		}
	}
}
</script>