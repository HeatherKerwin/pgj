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
 * 机构订单状态 
 */
function getOrgState(key,flag){
    var keys = new Array("0","1","2","3","4","5");
    var values = new Array("无效订单","待确认","验票中","已完成","待付款","已付款");
    if(flag==true){
        if("0"==key || "-1"==key || "-2"==key)
            return keys[0];
        else if("1"==key)
            return keys[1];
        else if("2"==key)
            return keys[2];
        else if("3"==key)
            return keys[3];
        else if("4"==key)
            return keys[4];
        else if("5"==key)
            return keys[5];
        else
            return key;
    }else{
        if("0"==key || "-1"==key || "-2"==key)
            return values[0];
        else if("1"==key)
            return values[1];
        else if("2"==key)
            return values[2];
        else if("3"==key)
            return values[3];
        else if("4"==key)
            return values[4];
        else if("5"==key)
            return values[5];
        else
            return "未知";
    }
}

/**
 * 企业订单状态 
 * @param {Object} key
 * @param {Object} flag
 */
function getBnsState(key,flag){
    var values = new Array("删除（待复核）","订单失败","无效订单","待确认","验票中","已完成","待收款");
    if(flag==true){
        if("NOAUDIT"==key)
            return values[0];
        else if("FAILED"==key)
            return values[1];
        else if("INVALID"==key)
            return values[2];
        else if("UNCONFIRM"==key)
            return values[3];
        else if("CONFIRM"==key)
            return values[4];
        else if("FINISH"==key)
            return values[5];
        else if("UNTRANSACTION"==key)
            return values[6];
        else
            return "未知";
    }else{
        if("-2"==key)
            return values[0];
        else if("-1"==key)
            return values[1];
        else if("0"==key)
            return values[2];
        else if("1"==key)
            return values[3];
        else if("2"==key)
            return values[4];
        else if("3"==key)
            return values[5];
        else if("4"==key)
            return values[6];
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
        if(type=="1"){//国股
            cdh = "国股";
        }else if(type=="2"){//城商（小商）
            cdh = "小商";
        }else if(type=="3"){//外资
            cdh = "外资";
        }else if(type=="4"){//农商
            cdh = "农商";
        }else if(type=="5"){//农合
            cdh = "农合";
        }else if(type=="6"){//农信
            cdh = "农信";
        }else if(type=="7"){//村镇
            cdh = "村镇";
        }else if(type=="8"){//大商
            cdh = "大商";
        }
    }
    return cdh;
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
 * 月利率转年利率 
 */
function mToy(m){
    if(m==null)return m;
    return Number(m*12/10).toFixed(3);
}

/**
 * 月利率转 每十万贴息
 * @param {Object} m 月利息
 * @param {Object} p 参数
 */
function mToten(m,p,jxts){
    if(jxts==null)return;
    var res = Number(m)/30/1000*100000*Number(jxts);
    if(p!=null)res += Number(p);
    return res.toFixed(2);
}

/**
 * 每十万贴息 转 月利率
 * @param {Object} ten 每十万贴息
 * @param {Object} p 参数
 */
function tenTom(ten,p,jxts){
    if(jxts==null)return;
    var res = 0;
    if(p!=null)res = Number(ten)-Number(p);
    var result = res/Number(jxts)/100000*1000*30;
    return result.toFixed(2);
}

/**
 * 年利率转月利率 
 */
function yTom(y){
    if(y==null)return y;
    return (y/12*10).toFixed(2);
}

/**
 * 根据总金额和计息天数和月利率获取贴现利息
 * @param {Object} allmoney
 * @param {Object} day
 * @param {Object} rate
 */
function getTXLX(allmoney,day,rate){
   return (allmoney*10000)*day*(rate/30/1000);
}

/**
 * 计算贴现金额（根据总金额、贴现利息、手续费）  
 * @param {Object} allmoney 总金额
 * @param {Object} txlx 贴现利息
 * @param {Object} poundage 手续费
 */
function getTXJE(allmoney,txlx,poundage){
    var res = (Number(allmoney)*10000)-Number(txlx);
    if(poundage!=null)res -= Number(poundage);
    return (res/10000).toFixed(2);
}

/**
 * 根据主键和订单不同枚举打开不同页面（无效订单0、待确认1、验票中2、待付款4、已完成3、已付款5） 
 * @param {Object} id
 * @param {Object} state
 */
function openOrderPage(id,state){
    localStorage["DISTRIBUTEORDERID"] = id;
    if("1"==state){
        Ruiyinopen('org_orderdetails1','org_orderdetails1.html');
    }else if("2"==state){
        Ruiyinopen('org_orderdetails2','org_orderdetails2.html');
    }else if("3"==state || "0"==state|| "-1"==state|| "-2"==state){
        Ruiyinopen('org_orderdetails5','org_orderdetails5.html');
    }else if("4"==state){
        Ruiyinopen('org_orderdetails3','org_orderdetails3.html');
    }else if("5"==state){
        Ruiyinopen('org_orderdetails4','org_orderdetails4.html');
    }
}

/**
 * 根据主键和订单状态不同打开不同页面订单状态（-2删除（待复核）、-1订单失败、0无效订单、1待确认、2验票中、3已完成、4代收款、） 
 * @param {Object} id
 * @param {Object} state
 */
function openRecordPage(id,state){
    localStorage["DISCOUNTRECORDID"] = id;
    if("1"==state){
        Ruiyinopen('bns_orderdetails1','bns_orderdetails1.html');
    }else if("2"==state){
        Ruiyinopen('bns_orderdetails2','bns_orderdetails2.html');
    }else if("3"==state){
        Ruiyinopen('bns_orderdetails4','bns_orderdetails4.html');
    }else if("4"==state){
        Ruiyinopen('bns_orderdetails3','bns_orderdetails3.html');
    }else if("0"==state){
        Ruiyinopen('bns_orderdetails5','bns_orderdetails5.html');
    }
}

/**
 * 查询查复付款状态  等待付款：0    付款成功：1    已退款：2
 */
function paystate(flag){
    var values = new Array("等待付款","付款成功","已退款");
    if(flag=="0"){
        return values[0];
    }else if(flag=="1"){
        return values[1];
    }else if(flag=="2"){
        return values[2];
    }else{
        return "未知";
    }
}

/**
 * 查询查复付款方式     支付宝：0 微信：1
 * 注：该方法存在重复，请使用getPayWay
 */
function payway(flag){
    var values = new Array('支付宝','微信','银联','快钱');
    if(flag=='0'){
        return values[0];
    }else if(flag=='1'){
        return values[1];
    }else if(flag=='2'){
        return values[2];
    }else if(flag=='3'){
        return values[3];
    }else{
        return "未知";
    }
}

/**
 * 查询查复是否需要发票        是：0  否：1
 */
function needinvoice(flag){
    var values = new Array('是','否');
    if(flag=='0'){
        return values[0];
    }else if(flag=='1'){
        return values[1];
    }else{
        return "未知";
    }
}

/**
 * 查询查复验票结果： 待验票0、成功1、失败2
 */
function inquerystate(flag){
    var values = new Array('待验票','成功','失败');
    if(flag=='0'){
        return values[0];
    }else if(flag=='1'){
        return values[1];
    }else if(flag=='2'){
        return values[2];
    }else{
        return "未知";
    }
}

/**
 * 获取支付方式 
 * @param {Object} flag
 */
function getPayWay(flag){
    var values = new Array('支付宝','微信支付','银联在线',"快钱支付");
    if(flag=='0'){
        return values[0];
    }else if(flag=='1'){
        return values[1];
    }else if(flag=='2'){
        return values[2];
    }else if(flag=='3'){
        return values[3];
    }else{
        return "--";
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
 * APP2.3 银票订单状态 
 * @param {Object} key
 * @param {Object} flag
 */
function getBnsStateYP(key,flag){
    var values = new Array("无效订单","交易中","已完成");
    if(flag==true){
        if("NOAUDIT"==key || "FAILED"==key || "INVALID"==key)
            return values[0];
        else if("UNCONFIRM"==key || "CONFIRM"==key || "UNTRANSACTION"==key)
            return values[1];
        else if("FINISH"==key)
            return values[2];
        else
            return "未知";
    }else{
        if("-2"==key || "-1"==key || "0"==key)
            return values[0];
        else if("1"==key || "2"==key || "4"==key)
            return values[1];
        else if("3"==key)
            return values[2];
        else
            return "未知";
    }
}

/**
 * 结构端银票状态 
 */
function getOrgStateYP(key){
    var values = new Array("无效订单","交易中","已完成");
    if("-2"==key || "-1"==key || "0"==key)
        return values[0];
    else if("1"==key || "2"==key || "4"==key || "5"==key)
        return values[1];
    else if("3"==key)
        return values[2];
    else
        return "未知";
}

/**
 * APP2.3 机构端批量状态
 */
function getOrgStatePL(key){
    var values = new Array("无效订单","交易中","已完成");
    if("-2"==key || "-1"==key || "0"==key)
        return values[0];
    else if("1"==key || "2"==key || "4"==key || "5"==key)
        return values[1];
    else if("3"==key)
        return values[2];
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
 * 所需提供材料
 * @param {Object} stuff
 */
function getNeedStuff(stuff){
    if(stuff==null || stuff.trim()=="")return null;
    var html = '';
    var values = new Array("","贸易合同","增值税发票","盖章","法人身份证","经办人身份证","开户许可证","营业执照","税务登记证","组织机构代码证","贷款卡","保函","以上所有纸质材料的复印件");
    var ss = stuff.split(",");
    for(var i=0;i<ss.length;i++){
        html += '<div class="ufl uinn-t30 uinn-b30 uinn-l30 uinn-r30 umar-r30 umar-b50 uba bc-border uc-a4">'
        html += values[ss[i]];
        html += '</div>';
    }
    return html;
}

/**
 * 批量订单承兑行类型 
 * @param {Object} type2
 * @param {Object} needStyle 需要样式（true\false）
 */
function getType2(type2,needStyle){
    if(type2==null || type2.trim()=="")return null;
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
 * 隐藏手机号中间四位 
 * @param {Object} mob
 */
function hideMobile(mob){
    if(mob!=null && mob.length<=11){
        return mob.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');
    }
}

/**
 * 承兑期限（0.半年期，1.一年期） 
 * @param {Object} index
 */
function getAcceptTime(index){
    var values = new Array("半年期","一年期");
    return values[index];
}

/**
 * 交易模式（0.先背书后打款，1.先打款后背书） 
 * @param {Object} index
 */
function getTradeModel(index){
    var values = new Array("先背书后打款","先打款后背书");
    return values[index];
}

/**
 * 要求上门（1是 0否） 
 * @param {Object} index
 * @param {Object} sign 
 */
function getNeedTodoor(index,sign){
    if(sign){
        var values = new Array("企业未要求上门","企业要求上门");
        return values[index];
    }else{
        var values = new Array("否","是");
        return values[index];
    }
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
            if(rate1!=null && rate1!="--" && rate1.trim()!="")html+="+"+rate1+"元";
        }else{
            html = rate+"‰";
            if(rate1!=null && rate1!="--" && rate1.trim()!="")html+="+"+rate1+"元";
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
 *验证手机是否符合需求 
 */
function validateMobile(mobile){
    var validate = /^(13[0-9]|15[012356789]|17[01235678]|18[0-9]|14[57])[0-9]{8}$/;
    var flag = validate.test(mobile); //true
    return flag;
}
