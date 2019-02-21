import {LoadingController, ToastController , AlertController,Platform} from 'ionic-angular';
import {Storage} from '@ionic/storage';
import {Injectable} from '@angular/core';
import {InAppBrowser} from "@ionic-native/in-app-browser";
import {Http,RequestOptions,Headers,Response} from '@angular/http';
import 'rxjs/add/operator/map';
import { Md5 } from 'ts-md5/dist/md5';

@Injectable()
export class apiService {
  public isLoading = false;
  //public getImgUrl = "https://img.utiexian.com/";  //图片服务器
  public appId = "wxfcf5788bc89da8db";  //微信appId
  public appSecret = "d0347590863908e5382c06254a7b21e5";  //微信appSecret
  public getImgUrl = "http://192.168.1.161:8011/"; //测试图片服务器
  //public getOrderImgUrl = "https://upload.utiexian.com:666/";      //票据图片服务器
  public getOrderImgUrl = "http://192.168.1.161:8011/"; //测试票据图片服务器

  //public HONGBAOURL="https://m.utiexian.com";//
  //public HONGBAOURL="https://test.utiexian.com/rymobile";
  public HONGBAOURL="http://192.168.1.40:8888/rymobile";

  getUrl() {
    //return "https://app.utiexian.com/";//正式服务器
    //return "https://test.utiexian.com/ryapp/";//测试服务器
    return "http://192.168.1.40:8888/ryapp/";//测试服务器
  }

  //wap测试环境URL
  getWapUrl() {
    //return "https://wap.utiexian.com/";//正式服务器
    //return "https://test.utiexian.com/rywap/";//测试服务器
    return "http://192.168.1.40:8888/rywap/";//测试服务器
  }

  getNewUrl() {
    //return "https://api.utiexian.com/"; //正式服务器
    return "http://192.168.1.161:8080/";//测试服务器
  }

  //web
  getWebsite(){
    //return "https://www.utiexian.com/"; //正式服务器
    return "http://192.168.1.40:8888/website/";//测试服务器
  }
  public tempId = "0";
  public pass = "0";       //开户绑定审核跳转
  public parmData:any = {};
  public Singular = '';    //tabs机构接单参数
  public SingularB = '';    //tabs企业接单参数
  public systeminfo='';     //系统未读参数
  public myChart:any;
  public MyID='';
  public MyPhone='';

  public hsLists:any=[];    //核算列表
  public newState:any=0;  //票据核算的状态

  //银行卡存储
  public BANKPAY:any={};

  //支付宝存储
  public ALIPAY:any={};

  //开户 兴业-京东
  public cibrz:any=0;
  public jdrz:any=0;


  //京东支行
  public occBankChildName:any=''; //支行名称
  public occBankChildCode:any=''; //支行code
  //绑卡数据
  public jdjrcard:any={};

  constructor(
    public alertCtrl: AlertController,
    public loadingCtrl: LoadingController,
    public toasts: ToastController,
    public http: Http,
    public storage: Storage,
    private inAppBrowser: InAppBrowser,
    public platform: Platform) {

  }

  //渠道
  public static getQuDao() {
    return "208";
  }

  //版本号
  public getVersion() {
    return "03300100";
  }

  public getVersionIos() {
    return "03301";
  }

  getPinfen(price){

	  if (price=="1")
		  return "assets/images/order/xingone.png";
	  if (price=="2")
		  return "assets/images/order/xingtwo.png";
	  if (price=="3")
		  return "assets/images/order/xingthree.png";
	  if (price=="4")
		  return "assets/images/order/xingfour.png";
	  if (price=="5")
		  return "assets/images/order/xingfive.png";
	  return "assets/images/order/xingxingss.png";
  }

  itip(msg = "网络无响应，请重试", dur = 2000) {
    let toastUsernameError = this.toasts.create({
      message: msg,
      duration: dur,
      position: 'bottom',
      showCloseButton: false,
      closeButtonText: "确定",
      cssClass: "bgRed"
    })
    toastUsernameError.present();
  }

  /**
   * @param method     请求方法
   * @param url        请求url
   * @param success    成功回调
   * @param failed     失败回调
   * @param data       请求数据
   */
  api(method, url, success, failed, dur, data = {}) {


    let loading = this.loadingCtrl.create({
      //content: '请等待',
      duration: 5000, //单位是毫秒
      spinner: "ios"
    });
	if (dur>5000) loading.present();

    let urls = this.getUrl() + url;



    /*if (!this.isLoading) {
      setTimeout(() => {
        if (this.isLoading == true)
          loading.present();
      }, dur);
    }*/

    switch (method) {

      case('get'):
        this.isLoading = true;
        this.http.get(urls).subscribe((res) => {
          this.isLoading = false;
          console.log("start1:" + JSON.stringify(res));
          let resData;
          try {
            resData = res.json();
          } catch (err) {
            console.log("err1");
            console.log(err + 'GODNESS')
            resData = "{'response':'err'}";
          }
          if (dur>5000) loading.dismiss();
          success(resData);

        }, error => {
          this.isLoading = false;
          console.log("err2");
          let errorData = {}; //error.json();
          if (dur>5000) loading.dismiss();

          failed(errorData);
        });
        break;

      case('getLink')://调用第三方接口
        this.isLoading = true;
        this.http.get(url).subscribe((res) => {
          this.isLoading = false;
          console.log("start1:" + JSON.stringify(res));
          console.log(res);
          let resData;
          try {
            resData = res.json();
          } catch (err) {
            console.log(err + 'GODNESS')
            resData = "{'response':'err'}";
          }
          if (dur>5000) loading.dismiss();
          success(resData);

        }, error => {
          this.isLoading = false;
          console.log("err2");
          let errorData = {}; //error.json();
          if (dur>5000) loading.dismiss();

          failed(errorData);
        });
        break;

      case('post'):
        this.isLoading = true;


			let result = [];
			 for (let key in data) {
			   key = encodeURIComponent(key);
			   let values = data[key];
			   if (values && values.constructor == Array) {
				 let queryValues = [];
				 for (let i = 0, len = values.length, value; i < len; i++) {
				   value = values[i];
				   queryValues.push(this.toQueryPair(key, value));
				 }
				 result = result.concat(queryValues);
			   } else {
				 result.push(this.toQueryPair(key, values));
			   }
			}
			let parmStr = result.join('&');

			console.log(parmStr);

			let headers = new Headers({
			'Content-Type': 'application/x-www-form-urlencoded'
			});
			let options = new RequestOptions({
				headers: headers
			});

        this.http.post(urls, parmStr,options).subscribe((res) => {
          this.isLoading = false;
          let resData;
          try {
            resData = res.json();
          } catch (err) {
            resData = "";
          }

		  if (dur>5000) loading.dismiss();
          success(resData);


        }, error => {
          this.isLoading = false;
          let errorData = error.json();
          if (dur>5000) loading.dismiss();

          this.itip(errorData.message);
          failed(errorData);
        });
        break;


	  case('newpost'):
        this.isLoading = true;

		this.storage.get('lgtoken').then((dataA)=>{

			let result = [];
			for (let key in data) {
			   key = encodeURIComponent(key);
			   let values = data[key];
			   /*if (values && values.constructor == Array) {
				 let queryValues = [];
				 for (let i = 0, len = values.length, value; i < len; i++) {
				   value = values[i];
				   queryValues.push(this.toQueryPair(key, value));
				 }
				 result = result.concat(queryValues);
			   } else {*/
				 result.push(this.toQueryPair(key, values));
			   //}
			}
			let reParmStr = result.join('&');
			result.push(this.toQueryPair("_csrf", dataA));
			let parmStr = result.join('&');

			console.log(parmStr);

			let headers = new Headers({
				'Content-Type': 'application/x-www-form-urlencoded'
			});
			let options = new RequestOptions({
				headers: headers
			});

			this.http.post( this.getNewUrl() + url, parmStr,options).subscribe((res) => {
				console.log('b2');

				this.isLoading = false;
				let resData;
				try {
					resData = res.json();
				} catch (err) {

					resData = "";
				}
				console.log(resData);
				if (dur>5000) loading.dismiss();
				if(resData.sign=='LOGIN'){
          this.reLogin(success,url,reParmStr);
        }else {
          success(resData);
        }

			}, error => {
			    this.isLoading = false;
			    let errorData = error.json();
					if (dur>5000) loading.dismiss();
					this.reLogin(success,url,reParmStr);
			});
		});
		break;

	  case('newget'):
		{
			let result = [];
			for (let key in data) {
			   key = encodeURIComponent(key);
			   let values = data[key];
			   if (values && values.constructor == Array) {
				 let queryValues = [];
				 for (let i = 0, len = values.length, value; i < len; i++) {
				   value = values[i];
				   queryValues.push(this.toQueryPair(key, value));
				 }
				 result = result.concat(queryValues);
			   } else {
				 result.push(this.toQueryPair(key, values));
			   }
			}

			let parmStr = result.join('&');

			this.http.get( this.getNewUrl() + url+'?'+ parmStr).subscribe((res) => {

				let resData;
				try {
					resData = res.json();
				} catch (err) {

					resData = "";
				}
				if (dur>5000) loading.dismiss();
				success(resData);


			}, error => {

				if (dur>5000) loading.dismiss();

			});

		}
    }



  }



    reLogin(success,url,reParmStr){

		this.storage.get('userInfo').then((dataA)=>{
			this.http.get(this.getNewUrl()+'index').subscribe((res) => {

				let resData;
				try {
				  resData = res.json();
				} catch (error) {
				  resData = "";
				}
				console.log(resData);
				let headers = new Headers({
					'Content-Type': 'application/x-www-form-urlencoded'
				});
				let options = new RequestOptions({
					headers: headers
				});

				let sign = Md5.hashStr(dataA.mobile+'SIGN:@UTIEXIAN@50965066'+dataA.id) ;
				let body= "type=3&username="+dataA.mobile+"&password="+sign+"&_csrf="+resData.token;

				this.http.post(this.getNewUrl()+'login', body, options )
				.subscribe(data1 => {
					console.log('relogin1');
					let recData;
					try {
					  recData = data1.json();
					} catch (err1) {
					  recData = "";
					}
					console.log(recData);

					this.storage.set('lgtoken', recData.token);

					this.loginOK(success,url,reParmStr,recData.token);

				}, err1 => {});


			}, error => {});
		})
	}

	loginOK(success,url,reParmStr,token){


			let headers = new Headers({
				'Content-Type': 'application/x-www-form-urlencoded'
			});
			let options = new RequestOptions({
				headers: headers
			});
			console.log('relogin2');

			reParmStr = reParmStr+'&_csrf='+token;
			console.log(reParmStr);
			this.http.post( this.getNewUrl() + url, reParmStr,options).subscribe((res) => {
				console.log('relogin3');

				let resData;
				try {
					resData = res.json();
				} catch (err) {

					resData = "";
				}
				success(resData);

			}, error => {

			});

	}

   getToken(){
	   this.storage.get('lgtoken').then((res2)=>{
		   console.log(res2);
		   return res2;
	   });
   }

   toQueryPair(key, value) {
     if (typeof value == 'undefined') {
       return key;
     }
     return key + '=' + encodeURIComponent(value === null ? '' : String(value));
   }




    loginpost(success, failed, username,password,type,code){

		this.http.get(this.getNewUrl()+'index').subscribe((res) => {
            console.log("-------------------------res------------------------");
            console.log(res);
            let resData;
            try {
              resData = res.json();
            } catch (err) {
              resData = "";
            }
            console.log(resData);
			let headers = new Headers({
				'Content-Type': 'application/x-www-form-urlencoded'
			});
			let options = new RequestOptions({
				headers: headers
			});

			let body= "username="+username+"&password="+password+"&_csrf="+resData.token;

			if (type=='2'){
				console.log(body);
				body= "username="+username+"&type=2&smscode="+code+"&_csrf="+resData.token;
			}


			//return new Promise((resolve, reject) => {
			this.http.post(this.getNewUrl()+'login', body, options )
			//.map(res => res.json())
			.subscribe(data1 => {
        console.log("-------------------------data1------------------------");
        console.log(JSON.stringify(data1));
				let recData;
				try {
				  recData = data1.json();
				} catch (err) {
				  recData = "";
				}

				success(recData);}, err => failed(err));
			//});


        }, error => {
        });

	}

  /**
   * 浅拷贝
   */
  shallowCopy(obj) {                   // fix bug for angular2 what inherit deep copy
    let store = JSON.stringify(obj);
    let shallow = JSON.parse(store);
    return shallow
  }

  /**
   * 票据类型的返回
   */
  public pjtype(type) {
    if (type == 1) {
      return "国股";
    }
    if (type == 8) {
      return "大商";
    }
    if (type == 2) {
      return "小商";
    }
    if (type == 3) {
      return "外资";
    }
    if (type == 4) {
      return "农商";
    }
    if (type == 5) {
      return "农合";
    }
    if (type == 6) {
      return "农信";
    }
    if (type == 7) {
      return "村镇";
    }
	return "　　";
  }
  //支付状态
  public payStateStr(payState){
    if(payState == 0){
      return '待付款';
    }
    if(payState == 1){
      return '支付成功';
    }
    if(payState == 2){
      return '已退款';
    }
  }

  public getOrgTime(res) {

	if (res!=null){
	  if (res.orgTime!=null){
		  return true;
	  }
    }
	return false;
  }
  public getBnsTime(res) {

	if (res!=null){
	  if (res.bnsTime!=null){
		  return true;
	  }
    }
	return false;
  }

    public pjtypeimg(type1,order_type) {
    if ((type1 == 1)&&(order_type=="DISCOUNTRECORD")) { // 纸银
      return "yinzhi";
    }
    if ((type1 == 2)&&(order_type=="DISCOUNTRECORD")) { // 电银
      return "yindian";
    }
	if ((type1 == 1)&&(order_type=="DISCOUNTRECORDSP")) { // 纸商
      return "shangzhi";
    }
	if ((type1 == 2)&&(order_type=="DISCOUNTRECORDSP")) { // 电商
      return "shangdian";
    }
  }

  //时间戳转换为时间（年月日）
  public transformdate(data) {
    let myDate = new Date(data);
    let year = myDate.getFullYear();
    let month = myDate.getMonth() + 1;
    let day = myDate.getDate();
    let monthStr;
    let dayStr;
    if(month<10){
      monthStr = '0'+month;
    }else{
      monthStr = month;
    }
    if(day<10){
      dayStr = '0'+day;
    }else{
      dayStr = day;
    }

    return year + '-' + monthStr + '-' + dayStr;
  }

  public transformdateC(data) {
    let myDate = new Date(data.replace(/-/g, "/"));
    let year = myDate.getFullYear();
    let month = myDate.getMonth() + 1;
    let day = myDate.getDate();
    let monthStr;
    let dayStr;
    if(month<10){
      monthStr = '0'+month;
    }else{
      monthStr = month;
    }
    if(day<10){
      dayStr = '0'+day;
    }else{
      dayStr = day;
    }

    return year + '-' + monthStr + '-' + dayStr;
  }

  public transformdateB(data) {
    let myDate = new Date(data.replace(/-/g, "/"));
    let year = myDate.getFullYear();
    let month = myDate.getMonth() + 1;
    let day = myDate.getDate();
    let monthStr;
    let dayStr;
    if(month<10){
      monthStr = '0'+month;
    }else{
      monthStr = month;
    }
    if(day<10){
      dayStr = '0'+day;
    }else{
      dayStr = day;
    }

    return year + '年' + monthStr + '月' + dayStr;
  }

  //计算时间差
  getDateDiff(start,end){
    let a = new Date(start);
    let b = new Date(end);
    console.log("时间0==="+(b.getTime()-a.getTime())/1000/60/60/24);
    return (b.getTime()-a.getTime())/1000/60/60/24;
  }

  checkMobile(str) {
    var telReg = !!str.match(/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$)/);
    return telReg;
  }


  /**
   * 获取storage
   */
  getStorage(item) {

  }

  /*手机号正则验证*/
  validateMobile(mobile){
    var validate = /^(13[0-9]|15[012356789]|17[01235678]|18[0-9]|14[57])[0-9]{8}$/;
    var flag = validate.test(mobile); //true
    return flag;
  }

  //--------------------订单上需要的函数-------------------开始
  qiehuan(flag){
	  if (flag==1)
	   return false;
   	  if (flag==0)
	   return true;
      return false;
  }

  dhpl(event){
    if(event!=null&&event!=''&&event!='undefined'){
      return event.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }else {
      return 0;
    }
  }

  shangmen(need_todoor,type1){
	  if ((type1==1)&&(need_todoor==0))
		  return true;
	  return false;
  }


  toFix(num,s){
	  if(num==undefined || num=="" || num==null || num=="--")
		  return "--";
	  let rec = (num/1.0).toFixed(s);
	  return Number(rec);
  }

  pjtypeP(order_type,type1,type2,accept_time){

	  let rec="";
	  if (order_type=='DISCOUNTRECORD')
		  rec = this.pjtype(type2);
	  if (type1==2) {

		if (accept_time==0){
		      rec ="半年期";
			}
			else {
	          rec ="一年期";
			}
	  }
	  return rec;
  }

  PrefixInteger(num, n) {
            return (Array(n).join('0') + num).slice(-n);
  }
  //--------------------订单上需要的函数-------------------结束


  /**
   * 通过浏览器打开url
   */
  openUrlByBrowser(url: string): void {
    this.inAppBrowser.create(url, '_system');
  }
  /*城市三级联动*/
  geCityData() {
    return this.http.get('assets/data/cityData.json').map((res: Response) => res.json());
  }

  Call(tel){
    let alert = this.alertCtrl.create({
      message: '确定要拨打'+tel+'吗？',
      buttons: [
        {
          text: '取消',
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          }
        },
        {
          text: '确定',
          handler: () => {
            window.open('tel:' + tel);
          }
        }
      ]
    });
    alert.present();
  }

  /**
   * 获取支付方式
   * @param {Object} flag
   */
  getPayWay(flag){
    var values = new Array('支付宝','微信支付','银联在线','快钱支付','宝付支付','押金支付');
    if(flag=='0'){
      return values[0];
    }else if(flag=='1'){
      return values[1];
    }else if(flag=='2'){
      return values[2];
    }else if(flag=='3'){
      return values[3];
    }else if(flag=='4'){
      return values[4];
    }else if(flag=='5') {
      return values[5];
    }else{
        return "--";
    }
  }

  /**
   * 查询查复是否需要发票        是：0  否：1
   */
  needinvoice(flag){
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
   * 时间显示为-分:秒
   */
  datemin(event) {
    let min = Math.floor(event / 60);//计算整数分
    let afterMin = event - min * 60;//取得算出分后剩余的秒数
    event = this.PrefixInteger(min, 2) + ':' + this.PrefixInteger(afterMin, 2);
  }

  /**
   * 输入框只允许输入正整数限制
   */
  check1(event){
    event.target.value = event.target.value.replace(/[^\d]/g,'');
  }
  /**
   * 输入框只允许输入数字和小数点限制
   */
  check2(event){
    event.target.value = (event.target.value.match(/\d+(\.\d{0,2})?/)||[''])[0]
  }
  /**
   * 输入框只允许输入数字和小数点4位限制
   */
  check2B(event){
    event.target.value = (event.target.value.match(/\d+(\.\d{0,4})?/)||[''])[0]
  }
  /**
   * 输入框中显示前4位和后4位中间为*号
   */
  check3(event){
    let a = event.substring(0,4);
    let b = event.length - 3;
    let c = event.substring(b);
    return a + ' '+ "***"+ ' ' + c;
  }

  /**
   * 银行输入框中显示前4位和后4位中间为*号
   */
  checkbank(event){
    if(event!=null&&event!=''&&event!='undefined'){
      let a = event.substring(0,4);
      let b = event.length - 4;
      let c = event.substring(b);
      return a + ' '+ "***"+ ' '+ "***"+ ' '+ "***"+ ' ' + c;
    }
  }

  /**
   * 输入框中显示前4位和后4位中间为*号
   */
  check4(event){
   let email = (event.target.value).match(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/);
   if(email){
   }else {
     this.itip("邮箱输入格式有误");
     return;
   }
  }
  /**
   * 手机号校验
   */
  check5(event){
    event.target.value =(event.target.value).match(/^(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/);
  }
  /**
   * 只能输入数字英文
   */
  check6(event){
    let value = event.target.value+"";
    let a =value.match(/^[0-9a-zA_Z]+$/);
    if(a==null){
      event.target.value=value.replace('/^[0-9a-zA_Z]+$/','');
      this.itip("只能输入数字英文");
    }
  }
  /**
   * 时间格式
   */
  check7(num){

    if(num!=undefined && num!="" && num!=null && num!="--"){
      if(num==0){
        return '--';
      }

      let min = Math.floor((num) / 60);//计算整数分
	  console.log('min'+min);
      let afterMin = num - min * 60;//取得算出分后剩余的秒数
	  console.log('afterMin'+afterMin);
      let rec = this.PrefixInteger(min, 2) + ':' + this.PrefixInteger(afterMin, 2);

	  if (min>59)
		return '大于一小时';
	  else
	    return rec;
    }
  }

  /**
   *
   * @param title  标题
   * @param content 提示内容
   * @param btnStr 按钮文字
   */
  alert(title,content,btnStr){
    let alert = this.alertCtrl.create({
      cssClass:'alertCss',
      title: title,
      subTitle: content,
      buttons: [btnStr],
      enableBackdropDismiss:false
    });
    alert.present();
  }
}
