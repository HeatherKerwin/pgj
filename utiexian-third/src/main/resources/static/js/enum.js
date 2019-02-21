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
 * 批量订单承兑行类型 
 * @param {Object} type2
 * @param {Object} needStyle 需要样式（true\false）
 */
function getType2pl(type2,needStyle){
    if(type2==null || $.trim(type2)=="")return null;
    var html = '';
    var values = new Array("","国股","大商","小商","三农","其它");
    var t = type2.split(",");
    if(needStyle){
        for(var i=0;i<t.length;i++){
            html += '<li class="fl mr30">'+values[t[i]]+'</li>';
        }
    }else{
        for(var i=0;i<t.length;i++){
            html += values[t[i]]+" ";
        }
    }
    return html;
}

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
 * APP2.3 商票订单状态
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
 * 所需提供材料
 * @param {Object} stuff
 */
function getNeedStuff1(stuff){
    if(stuff==null || $.trim(stuff)=="")return null;
    var html = '';
    var values = new Array("","贸易合同","增值税发票","盖章","法人身份证","经办人身份证","开户许可证","营业执照","税务登记证","组织机构代码证","贷款卡","保函","以上所有纸质材料的复印件");
    var ss = stuff.split(",");
    for(var i=0;i<ss.length;i++){
        if(ss[i] != ""){
        	html += '<a href="#" class="fl c2d2d2d ba2_e0e0e0 br3 dsb pl10 pr10 h34 ml25 mt20">'
            html += values[ss[i]];
            html += '</a>';
        }
    }
    return html;
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
 * 消息类型 
 * @param {Object} type
 */
function getNewsType(type) {
    var newsType = "";
    if(type!=null){
        if(type=="1"){
        	newsType = "票据新闻";
        }else if(type=="2"){
        	newsType = "金融动态";
        }else if(type=="3"){
        	newsType = "管家说事";
        }else if(type=="4"){
        	newsType = "媒体报道";
        }
    }
    return newsType;
}

/**
 * 票据类型 
 * @param {Object} type
 */
function getBA(type){
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

/**
 * 批量订单承兑行类型 
 * @param {Object} type2
 * @param {Object} needStyle 需要样式（true\false）
 */
function getType2(type2,needStyle){
    if(type2==null || $.trim(type2)=="")return null;
    var html = '';
    var values = new Array("","国股","大商","小商","三农","其它");
    var t = type2.split(",");
    if(needStyle){
        for(var i=0;i<t.length;i++){
            html += '<div class="ub ub-w20 ub-pc uinn-t30 uinn-b30 umar-r30 uba bc-border uc-a4">'+values[t[i]]+'</div>';
        }
    }else{
        for(var i=0;i<t.length;i++){
            html += values[t[i]]+" ";
        }
    }
    return html;
}

/**
 *计算时间差 
 * */
function getDateDiff(start,end){
	var time1 = $.trim(start.replace(/-/g,'/').replace(/T|Z/g,' '));
	var time2 = $.trim(end.replace(/-/g,'/').replace(/T|Z/g,' '));
    var a = new Date(time1);
    var b = new Date(time2);
    return (b.getTime()-a.getTime())/1000/60/60/24;
}

/**
 * 企业端显示（机构报价） 
 * @param {Object} rate
 * @param {Object} rate1
 * @param {Object} rate2
 * @param {Object} way
 * @param {Object} type1
 */
function getPriceYp(rate,rate1,rate2,way,type1){
    var html = "";
    if(way==0){//利率+参数
        if(type1==2){
            html = rate+"%";
            if(rate1!=null && rate1!="--" && $.trim(rate1)!="")html+="+"+rate1+"元";
        }else{
            html = rate+"‰";
            if(rate1!=null && rate1!="--" && $.trim(rate1)!="")html+="+"+rate1+"元";
        }
    }else{//每十万
        html = rate2+"元/10万";
    }
    if(html!=null && html!=""){
        return html;
    }else if(type1==2){
        return "--%";
    }else{
        return "--‰";
    }
}
/**
 * 所需提供材料
 * @param {Object} stuff
 */
function getNeedStuff(stuff){
    if(stuff==null || $.trim(stuff)=="")return null;
    var html = '';
    var values = new Array("","贸易合同","增值税发票","盖章（法人章、财务章、公章）","法人身份证","经办人身份证","开户许可证","营业执照","税务登记证","组织机构代码证","贷款卡","保函","以上所有纸质材料的复印件");
    var ss = stuff.split(",");
    for(var i=0;i<ss.length;i++){
        html += '<div class="fl w190 h45">'
        html += values[ss[i]];
        html += '</div>';
    }
    return html;
}

/**
 * 关键字搜索 
 */
var DOM = (document.getElementById) ? 1 : 0;    
var NS4 = (document.layers) ? 1 : 0;    
var IE4 = 0;    
if (document.all){    
    IE4 = 1;    
    DOM = 0;    
}   
var win = window;       
var n   = 0;   
function findKey(id) {    
    if (document.getElementById(id).value != ""){
    	findInPage(document.getElementById(id).value); 
    	actionLog("action121");//常见问题搜索
    }    
}   
function findInPage(str) { 
	var txt, i, found;   
	if(str == "")return false;   
	if(DOM){    
	    win.find(str, false, true);    
	    return true;    
	}   
	if(NS4){    
	    if (!win.find(str))    
	        while(win.find(str, false, true))    
	            n++;    
	    else    
	        n++;   
	    if (n == 0)    
	        alert("未找到指定内容.");    
	}   
	if(IE4){    
	    txt = win.document.body.createTextRange();   
	    for (i = 0; i <= n && (found = txt.findText(str)) != false; i++) {    
	        txt.moveStart("character", 1);    
	        txt.moveEnd("textedit");    
	    }   
	if(found){    
	    txt.moveStart("character", -1);    
	    txt.findText(str);    
	    txt.select();    
	    txt.scrollIntoView();    
	    n++;    
	}else {    
	    if (n > 0) {    
	        n = 0;    
	        findInPage(str);    
	    }else    
	        alert("未找到指定内容.");    
	    }    
	}   
	return false;    
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