import { Component } from '@angular/core';
import { NavController, ViewController, NavParams } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { Storage } from '@ionic/storage';
import { DiscountYPPage } from '../../discount/discountYP';
import {el} from "@angular/platform-browser/testing/src/browser_util";

@Component({
  selector: 'page-counter',
  templateUrl: 'counter.html'
})
export class CounterPage {

  public CounterButton:boolean;
  public orgType=0;

  //报价传值
  public item:any={
};

  public counter:any= {
    piaojushuxing:'0',//票据属性
    pjtype:2,//票据属性选择
    isShow:true,//承兑期限显示隐藏
    limit: 1,//承兑期限
    chengduihang: '1',//承兑行
    allprice: "",//金额
    yuelilv:'',//月利率
    yuelilvs:false,//月利率判断
    nianlilv:'',//年利率
    nianlilvs:true,//年利率判断
    citys: '0', //城市
    iscity:false, //交易城市
    swtiexi:'',   //每10万贴息
    shiwantiexi:false, //每10万贴息判断
    begintime: new Date().toISOString(),//下单日期
    endtime: new Date(new Date().getTime()+360*24*3600*1000).toISOString(),//到期日期
    min: new Date().toISOString(),//到期日期最小值
    remindtime:'',//提醒日期
    poundage:'',//手续费
    amount:'', //月利率金额
    tzts:0,
  };


  //输出
  public rate = '请输入年利率';   //(月)年利率
  public price = '请输入月利率';
  public rate1 :any= '金额';   //月利率金额
  public rate2:any = '请输入每十万贴息';   //每十万贴息
  public rateYear = '';
  public tzts:any = '';    //调整天数
  public jxts:any = '等待计算结果';    //计息天数
  public txlx :any= '等待计算结果';    //贴现利息
  public txje:any = '等待计算结果';    //贴现金额
  public memberId: any;
  public orderDetailS=false;


  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
    });
    let orgType = this.params.get("ORG_TYPE"); //角色类型
      this.orgType = orgType;
/*      if(this.orgType == 0){
        this.CounterButton = true;
      }else if(this.orgType == 1){
        this.CounterButton = false;
      }*/

  //初始化计息天数
    this.jxts=((new Date(this.counter.endtime).getTime() - new Date(this.counter.begintime).getTime())/(24 * 60 * 60 * 1000)).toFixed(0);
  }



/*
  //每次进入页面都加载
  ionViewDidEnter(){
    //this.initFromCalOrInq();//初始化来自贴现计算的数据
  }
*/

  //初始化获取数据源
  ionViewDidEnter(){
    let type1 = this.params.get("TEMP_TYPE1",);       //票据类型
    let type2 = this.params.get("TEMP_TYPE2",);  //票据承兑行类型
    let allmoney = this.params.get("TEMP_ALLMONEY",);  //金额
    let begintime = this.params.get("TEMP_START",); //下单日期
    if(begintime!=null) {begintime=begintime.replace(/-/g, "/");}
    let endtime = this.params.get("TEMP_END",);     //到期日期
    if(endtime!=null) {endtime=endtime.replace(/-/g, "/");}
    let jxts = this.params.get("TEMP_DAY",);  //计息天数
    let orderDetailS = this.params.get("ORDERDEATAIL",);
    if (orderDetailS ==null || orderDetailS =='' || orderDetailS =='undefined'){
      this.orderDetailS = false;
    }else {
      this.orderDetailS = true;
    }

    if(type1!=null)this.counter.pjtype=type1;
    if(type2!=null)this.counter.chengduihang=type2;
    if(type1 == 2){
      let limit = this.params.get("TEMP_ACCEPTTIME",);       //承兑期限
      if(limit!=null)this.counter.limit=limit;
    }else if(type1 ==1){
      this.counter.piaojushuxing = "1";
      this.counter.isShow = false;
      this.counter.iscity = true;
      this.counter.shiwantiexi = true;
      this.counter.yuelilvs = true;
      this.counter.nianlilvs = false;
/*      this.changeAcceptTime();*/
    }
    if(jxts!=null){ this.jxts = jxts}
    if(allmoney!=null){this.counter.allprice=allmoney;}
    if(begintime!=null){this.counter.begintime=new Date(begintime).getFullYear() + "-" + ("0"+(new Date(begintime).getMonth() + 1)).slice(-2) + "-" + new Date(begintime).getDate();}
    if(endtime!=null){this.counter.endtime=new Date(endtime).getFullYear() + "-" + ("0"+(new Date(endtime).getMonth() + 1)).slice(-2) + "-" + new Date(endtime).getDate();}
/*    this.initExcel();*/
  }

/*  //选择票据属性
  attribute(pjtype) {
    this.counter.pjtype = 2;
      if(this.counter.pjtype == 2){
        this.counter.piaojushuxing = "0";
        this.counter.isShow = true;
        this.counter.iscity = false;
        this.counter.shiwantiexi = false;
        this.counter.yuelilvs = false;
        this.counter.nianlilvs = true;
        if(this.counter.limit == 1){
          let timeEnd= new Date(this.counter.begintime).getTime()+360*24*3600*1000;
          this.counter.endtime = new Date(timeEnd).toISOString();
        }else if(this.counter.limit == 0){
          let timeEnd= new Date(this.counter.begintime).getTime()+180*24*3600*1000;
          this.counter.endtime = new Date(timeEnd).toISOString();
        }
      }
      else if(this.counter.pjtype == 1){
        this.counter.piaojushuxing = "1";
        this.counter.isShow = false;
        this.counter.iscity = true;
        this.counter.shiwantiexi = true;
        this.counter.yuelilvs = true;
        this.counter.nianlilvs = false;
        this.changeAcceptTime();
      }
    }*/

/*  changeAcceptTime(){
    let timeEnd= new Date(this.counter.begintime).getTime()+180*24*3600*1000;
    this.counter.endtime = new Date(timeEnd).toISOString();
  }*/

  //计息天数
  initExcel() {
    let begintime = new Date(this.counter.begintime).getFullYear() + "-" + ("0"+(new Date(this.counter.begintime).getMonth() + 1)).slice(-2) + "-" + new Date(this.counter.begintime).getDate();
    let endtime = new Date(this.counter.endtime).getFullYear() + "-" + ("0"+(new Date(this.counter.endtime).getMonth() + 1)).slice(-2) + "-" + new Date(this.counter.endtime).getDate();
    let counter:any = {
      type1: 2,
      type2: 1,
      start: begintime,
      end: endtime,
      allmoney:this.counter.allprice,
      cityName:'全国',
      cityId:1,
      limit:2
    };


    this.apiSev.api("post", "app/excel/price", (res) => {
      this.tzts = res.tzts;
      console.log("jxts"+res.jxts);
      if (res.response == 'success'){
        this.jxts = res.jxts;       //计息天数
          if(res.rate!=null && res.rate!="" && res.rate!="--"){
            this.rate = res.rate;  //输出(月)年利率
            this.rate1= res.rate1; //参数
            this.rate2= res.rate2; //每十万贴息
            this.rateYear= res.rate;
        }
      }
      if(res.txlx!=null && res.txlx!=""){
        this.txlx= res.txlx;//贴现利息
        if(this.counter.poundage != null && this.counter.poundage != "" && this.counter.poundage != undefined){
          this.txje=Number(this.txje)-Number(this.counter.poundage);
        }else {
          this.txje=Number(this.txje)-Number(0);
        }
        this.txje=this.getTXJE(); //贴现金额
      }

    }, (error) => {
      this.apiSev.itip('暂无数据');
    }, 500, counter);
  }

  //贴现金额计算
  getTXJE(){
    let res = (Number(this.counter.allprice)*10000)-Number(this.txlx);
    if(this.counter.poundage!=null)res -= Number(this.counter.poundage);
    return (res/10000).toFixed(2);
  }

  //改变月利率联动十万贴息
  Myuelilv(){
    let res = (Number(this.counter.swtiexi*1)-Number(this.counter.amount*1))/Number(this.jxts*1)/100000*1000*30;
    return res.toFixed(2);
  }

  //改变十万贴息联动月利率
  Tswtiexi(){
    let res = Number(this.counter.amount) + (Number(this.counter.yuelilv*1)/30/1000*100000*Number(this.jxts));
    return res.toFixed(2);
  }

  changeday() {
    this.jxts=this.jxts*1 - this.counter.tzts*1 + this.tzts*1;
    this.counter.tzts = this.tzts;
  }

  //根据总金额和计息天数和月利率获取贴现利息
  getTXLX(){
    let res = (Number(this.counter.allprice)*10000)*Number(this.txje)*(Number(this.counter.yuelilv)/30/1000);
    return res.toFixed(3);
  }

  //年利率转月利率
  yTom(){
     this.counter.yuelilv=(Number(this.counter.nianlilv)/12*10).toFixed(2);
    this.counter.swtiexi=this.Tswtiexi();

  }

  changeM(){
    if(this.counter.allprice==null ||this.counter.allprice=='') {
      this.apiSev.itip("请输入总金额");
      return;
    }
    this.counter.swtiexi=this.Tswtiexi();
    this.counter.nianlilv=this.counter.yuelilv*12/10
  }

  changeT(){
    if(this.counter.allprice==null ||this.counter.allprice=='') {
      this.apiSev.itip("请输入总金额");
      return;
    }
    this.counter.yuelilv=this.Myuelilv();
  }

/*  changeP(){
    this.initExcel();
    this.initFromCalOrInq();
  }*/

  //时期时时计算
  timidata(){
    console.log(((new Date(this.counter.endtime).getTime() - new Date(this.counter.begintime).getTime())/(24 * 60 * 60 * 1000)).toFixed(0));
    this.jxts=((new Date(this.counter.endtime).getTime() - new Date(this.counter.begintime).getTime())/(24 * 60 * 60 * 1000)).toFixed(0);
    if(this.counter.allprice != null && this.counter.allprice != "" && this.counter.allprice != undefined) {
      this.initExcel();
    }else {
      this.apiSev.itip("请先输入票面金额");
    }
  }




  reExcel() {
    let begintime = new Date(this.counter.begintime).getFullYear() + "-" + ("0" + (new Date(this.counter.begintime).getMonth() + 1)).slice(-2) + "-" + new Date(this.counter.begintime).getDate();
    let endtime = new Date(this.counter.endtime).getFullYear() + "-" + ("0" + (new Date(this.counter.endtime).getMonth() + 1)).slice(-2) + "-" + new Date(this.counter.endtime).getDate();
    if(this.counter.amount==null || this.counter.amount==''){
      this.counter.amount=0
    }
    let data: any = {
      type1: 2,
      type2: 1,
      start: begintime,
      end: endtime,
      allmoney: this.counter.allprice,
      rate1: this.counter.amount,
      memberid:this.memberId,
      belong:1
    };
    if (this.counter.allprice.length > 10) {
      this.apiSev.itip("您输入的金额有误,请输入小于千亿以下的金额");
      return;
    }

    if (this.counter.allprice == null || this.counter.allprice == '') {
      this.apiSev.itip("请输入总金额");
      return;
    }

    if (this.counter.yuelilv == null || this.counter.yuelilv == ""||this.counter.yuelilv == "0.00") {
      this.apiSev.itip("请输入月利率");
      return;
    }


    if (this.counter.nianlilv == null || this.counter.nianlilv == "") {
      this.apiSev.itip("请输入年利率");
      return;
    }else {
      data.rate = this.counter.nianlilv;
    }

    if (this.counter.begintime == null || this.counter.begintime == "") {
      this.apiSev.itip("请输入下单日期");
      return;
    }
    if (this.counter.endtime == null || this.counter.endtime == "") {
      this.apiSev.itip("请输入到期日期");
      return;
    }


    this.apiSev.api("post", "app/reexcel/price/", (res) => {
      if (res.response == 'success'){
        this.jxts = res.jxts;       //计息天数
        this.txlx= res.txlx;//贴现利息

        if(this.counter.poundage != null && this.counter.poundage != "" && this.counter.poundage != undefined){
          this.txje=Number(this.txje)-Number(this.counter.poundage);
        }else {
          this.txje=Number(this.txje)-Number(0);
        }
        //月利率判断
/*        if(this.counter.yuelilv == null && this.counter.yuelilv == "" && this.counter.yuelilv == undefined){
          this.counter.yuelilv = this.price;
        }*/
        //月利率金额判断
        if(this.counter.amount == null && this.counter.amount == "" && this.counter.amount == undefined){
          this.counter.amount = this.rate1;
        }
        this.txje=this.getTXJE(); //贴现金额

      }
    }, (error) => {
      this.apiSev.itip('暂无数据');
    }, 500, data);
  }

  qingqong(){
    if(this.counter.pjtype==2){
      this.counter.isShow=true;
      this.counter.limit= 1;
      this.counter.chengduihang= '1';
      this.counter.allprice="";
      this.counter.yuelilv='';
      this.counter.yuelilvs=false;
      this.counter.nianlilv='';
      this.counter.nianlilvs=true;
      this.counter.poundage='';
      this.counter.begintime= new Date().toISOString();//下单日期
      this.counter.endtime= new Date(new Date().getTime()+360*24*3600*1000).toISOString();//到期日期
      this.counter.min= new Date().toISOString();//到期日期最小值

    }else if(this.counter.pjtype==1){
      this.counter.isShow=false;
      this.counter.chengduihang= '1';
      this.counter.allprice="";
      this.counter.yuelilv='';
      this.counter.yuelilvs=true;
      this.counter.nianlilv='';
      this.counter.nianlilvs=false;
      this.counter.shiwantiexi=true;
      this.counter.poundage='';
      this.counter.begintime= new Date().toISOString();//下单日期
      this.counter.endtime= new Date(new Date().getTime()+180*24*3600*1000).toISOString();//到期日期
      this.counter.min= new Date().toISOString();//到期日期最小值
    }
    this.counter.swtiexi= '';
    this.counter.amount = '';
    this.tzts = '';
    this.rate = '请输入年利率';
    this.price = '请输入月利率';
    this.rate2 = '请输入每十万贴息';
    //this.jxts = '等待计算结果';    //计息天数
    this.txlx = '等待计算结果';    //贴现利息
    this.txje = '等待计算结果';    //贴现金额
  }

  //银票贴现传页面
  discountYPPage() {
    let type1 = this.counter.pjtype;       //票据类型
    let type2 = this.counter.chengduihang; //票据承兑行类型
    let allmoney = this.counter.allprice;  //金额
    let limit = this.counter.limit;        //承兑期限
    let begintime = new Date(this.counter.begintime).getFullYear() + "-" + ("0" + (new Date(this.counter.begintime).getMonth() + 1)).slice(-2) + "-" + new Date(this.counter.begintime).getDate(); //下单日期
    let endtime = new Date(this.counter.endtime).getFullYear() + "-" + ("0" + (new Date(this.counter.endtime).getMonth() + 1)).slice(-2) + "-" + new Date(this.counter.endtime).getDate();     //到期日期
    this.navCtrl.push(DiscountYPPage, {
      TEMP_TYPE1: type1,
      TEMP_ACCEPTTIME: limit,
      TEMP_TYPE2: type2,
      TEMP_ALLMONEY: allmoney,
      TEMP_START: begintime,
      TEMP_END: endtime,
      TEMP_TXLX: this.apiSev.toFix(this.txlx, 0),
    });
  }

  //返回报价页面
  Fuipop(){
    if(this.txlx*1 > 0){
      this.apiSev.parmData={
        Discountinterest:this.apiSev.toFix(this.txlx,0)
      }
    }
    this.navCtrl.pop();
  }



}
