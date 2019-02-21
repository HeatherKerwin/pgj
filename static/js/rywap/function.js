//============================================公共方法部分================================================================
Date.prototype.format = function (fmt) {
    var paddNum = function(num){
      num += "";
      return num.replace(/^(\d)$/,"0$1");
    }
    //指定格式字符
    var cfg = {
       yyyy : this.getFullYear() //年 : 4位
      ,yy : this.getFullYear().toString().substring(2)//年 : 2位
      ,M  : this.getMonth() + 1  //月 : 如果1位的时候不补0
      ,MM : paddNum(this.getMonth() + 1) //月 : 如果1位的时候补0
      ,d  : this.getDate()   //日 : 如果1位的时候不补0
      ,dd : paddNum(this.getDate())//日 : 如果1位的时候补0
      ,hh : paddNum(this.getHours())  //时
      ,mm : paddNum(this.getMinutes()) //分
      ,ss : paddNum(this.getSeconds()) //秒
    }
    fmt || (fmt = "yyyy-MM-dd hh:mm:ss");
    return fmt.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
}

/**
 * 自定义选择radio
 * @param {Object} val 当前值
 * @WKX
 */
jQuery.fn.setRadio = function (val) {
    $(this).parent("li").children("label").removeClass("radioSelect");
    
    var name = $(this).attr("name");
    var c = $("input[name='" + name + "'][value='"+ val +"']");
    c.attr("checked","checked").prop('checked',true);//设置选中的值
    c.parent("li").children("label").addClass("radioSelect");//设置样式
}

/**
 * 本地缓存使用后清空
 * @param {Object} key
 */
function getLocalAndClean(key){
    var value = localStorage.getItem(key);
    localStorage.removeItem(key);//清除来源
    return value;
}

/**
 * 隐藏手机号中间四位 
 * @param {Object} mob
 */
function hideMobile(mob){
    if(mob!=null && mob.length<=11){
        return mob.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');
    }
}

/**
 * 验证手机号（电话号码）
 */
function checkMobile(str) {
    var telReg = !!str.match(/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$)/);
    return telReg;
}

/**
 * 验证手机号码格式是否存在
 * @param {Object} mob
 */
function validateMobile(mobile){
	var validate = /^(13[0-9]|15[012356789]|17[01235678]|18[0-9]|14[57])[0-9]{8}$/;
    var flag = validate.test(mobile); //true
    return flag;
}

/**
 * 时间格式化
 */
function dateFormat(d){
	d = d.replace(/\//g,"-");
	d = d.replace("年","-");
	d = d.replace("月","-");
	d = d.replace("日","-");
	var temp = d.charAt(d.length - 1);
	if("-"==temp){
		d = d.substring(0,d.length - 1);
	}
	return d;
}

//============================================枚举部分================================================================

/**
 * 企业端批量订单状态 
 * @param {Object} key
 */
function getBnsStatePL(key){
    var values = new Array("无效订单","待交易","交易中","已完成");
    if("-2"==key || "-1"==key || "0"==key)
        return values[0];
    else if("1"==key )
        return values[1];
    else if("2"==key )
        return values[2];
    else if("3"==key)
        return values[3];
    else
        return "未知";
}

/**
 * 商票订单状态
 */
function getBnsStateSP(key,flag){
    var values = new Array("无效订单","待交易","交易中","已完成");
    if(flag==true){
        if("INVALID"==key)
            return values[0];
        else if("UNCONFIRM"==key)
            return values[1];
        else if("CONFIRM"==key)
            return values[2];
        else if("FINISH"==key)
            return values[3];
        else
            return "未知";
    }else{
        if("0"==key || "-2"==key)
            return values[0];
        else if("1"==key)
            return values[1];
        else if("2"==key)
            return values[2];
        else if("3"==key)
            return values[3];
        else
            return "未知";
    }
}

/**
 * 承兑行 
 * @param {Object} type
 */
function getBank(type) {
    var cdh = "";
    if(type!=null){
    	if(type==1){//国股
			cdh = "国股";
		}else if(type==2){//城商（小商）
			cdh = "小商";
		}else if(type==3){//外资
			cdh = "外资";
		}else if(type==4){//农商
			cdh = "农商";
		}else if(type==5){//农合
			cdh = "农合";
		}else if(type==6){//农信
			cdh = "农信";
		}else if(type==7){//村镇
			cdh = "村镇";
		}else if(type==8){//城商（大商）
			cdh = "大商";
		}
    }
    return cdh;
}

/**
 * 票据类型 
 * @param {Object} type
 */
function getType(type){
    var ba = "";
    if(type!=null){
        if(type=="1"){
            ba = "纸票";
        }else if(type=="2"){
            ba = "电票";
        }
    }
    return ba;
}

/**
 * 是否上门 
 * @param {Object} flag
 */
function getIsTodoor(flag){
    if(flag==1){
        return "是";
    }else if(flag==0){
        return "否";
    }else{
        return "未知";
    }
}

/**
 * 是否是瑕疵票
 * @param {Object} flag
 */
function getFlawTicket(flag){
    if(flag==0){
        return "是";
    }else if(flag==1){
        return "否";
    }else{
        return "未知";
    }
}

/**
 * 上门时间 
 * @param {Object} flag
 */
function getTodoorTime(flag){
    if(flag==0){
        return "2小时以内";
    }else if(flag==1){
        return "4小时以内";
    }else if(flag==2){
        return "6小时以内";
    }else if(flag==3){
        return "8小时以内";
    }else if(flag==4){
        return "8小时以上";
    }else{
        return "未知";
    }
}

//============================================公共请求方法部分================================================================

/**
 * 银票贴现
 */
function discountrecord(){
	window.location.href = BASEPATH + "/wap/discountrecord";
}

/**
 * 商票贴现
 */
function discountrecordsp(){
	window.location.href = BASEPATH + "/wap/discountrecordsp";
}

/**
 * 询价
 */
function inquiry(){
	window.location.href = BASEPATH + "/wap/tool/inquiry";
}

/**
 * 查询查复
 */
function inquiryreply(){
	window.location.href = BASEPATH + "/wap/inquiryreply";
}

/**
 * 银票贴现订单列表
 */
function discountrecordlist(){
	window.location.href = BASEPATH + "/wap/discountrecord/page";
}

/**
 * 商票贴现订单列表
 */
function discountrecordsplist(){
	window.location.href = BASEPATH + "/wap/discountrecordsp/page";
}

/**
 * 我的查询查复列表
 */
function inquiryreplylist(){
	window.location.href = BASEPATH + "/wap/inquiryreply/page";
}

/**
 * 关于我们
 */
function about(){
	window.location.href = BASEPATH + "/wap/member/about/page";
}

/**
 * 找回密码
 */
function password(){
	window.location.href = BASEPATH + "/wap/member/password/page";
}

/**
 * 公催
 */
function urge(){
	window.location.href = BASEPATH + "/wap/tool/urge";
}

/**
 * 计算器
 */
function calculator(){
	window.location.href = BASEPATH + "/wap/tool/calculator";
}

/**
 * 联行号
 */
function bank(){
	window.location.href = BASEPATH + "/wap/tool/bank";
}

/**
 * shibor
 */
function shibor(){
	window.location.href = BASEPATH + "/wap/tool/shibor";
}

/**
 * 登录
 */
function login(){
	window.location.href = BASEPATH + "/wap/login/page";
}

/**
 * 注册
 */
function register(){
	window.location.href = BASEPATH + "/wap/member/register/page";
}

/**
 * 退出登录
 */
function logout(){
	myConfirm("确定要退出系统吗？",function(){
		window.location.href = BASEPATH + "/wap/login/member/logout";
	});
}

/**
 * App专属功能，请下载app
 */
function downLoadApp(){
	myConfirm("马上下载票据管家APP，享受票据贴现一站式服务",function(){
		window.location.href = "https://m.utiexian.com/app/download.html";
	});
}