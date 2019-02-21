import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../../api.service";
import { ChartPage } from '../chart/chart';
import { DiscountYPPage } from '../../discount/discountYP';


@Component({
  selector: 'page-inquiry',
  templateUrl: 'inquiry.html'
})

export class InquiryPage {
/*
  cityData: any[] = []; //城市数据
  cityName: string = '上海市'; //初始化城市名
  code: string; //城市编码
*/

  public inquiry = {
    place: '',
    piaojushuxing:'0',//票据属性
    pjtype:2,//票据属性选择
    limit: 1,
    chengduihang: '1',//承兑行
    isShow:true,//承兑期限显示隐藏
    iscity:false,
    begintime: new Date().toISOString(),//下单日期
    endtime: new Date(new Date().getTime()+360*24*3600*1000).toISOString(),//到期日期
    min: new Date().toISOString(),//到期日期最小值
    allprice:'', //票据金额
    discount:true, //每十万贴息判断
    nianlilv:false //询价年利率判断
  };
  public jixidata ={
    tzts:0, //调整天数
    jxts:0  //计息天数
  };
  public discount={
    month:0,
    all:0
  };
  public result={
    price:'——',
    price2:'——'
  };
  public price3='——';
  public memberId;

  constructor(public toastCtrl: ToastController, public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,) {
    this.inquiry.place='上海市';
    this.jixidate();
    this.topBanner();
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id
    });
  }
  //选择票据属性
  attribute(e,pjtype) {
    if(this.inquiry.pjtype == 2){
      this.inquiry.piaojushuxing = "0";
      this.inquiry.isShow = true;
      this.inquiry.iscity = false;
      if(this.inquiry.limit == 1){
        let timeEnd= new Date(this.inquiry.begintime).getTime()+360*24*3600*1000;
        this.inquiry.endtime = new Date(timeEnd).toISOString();
      }else if(this.inquiry.limit == 0){
        let timeEnd= new Date(this.inquiry.begintime).getTime()+180*24*3600*1000;
        this.inquiry.endtime = new Date(timeEnd).toISOString();
      }
    }
    else if(this.inquiry.pjtype == 1){
      this.inquiry.piaojushuxing = "1";
      this.inquiry.isShow = false;
      this.inquiry.iscity = true;
      this.changeAcceptTime();
    }
    this.jixidate();
    e.preventDefault();
  }

  jixidate() {
    let begintime = new Date(this.inquiry.begintime).getFullYear() + "-" + ("0"+(new Date(this.inquiry.begintime).getMonth() + 1)).slice(-2) + "-" + new Date(this.inquiry.begintime).getDate();
    let endtime = new Date(this.inquiry.endtime).getFullYear() + "-" + ("0"+(new Date(this.inquiry.endtime).getMonth() + 1)).slice(-2) + "-" + new Date(this.inquiry.endtime).getDate();
    let inquiry = {
      type6: this.inquiry.pjtype,
      startDate: begintime,
      endDate: endtime,
    };
    this.apiSev.api("post", "app/xunjia2/jixidate/", (res) => {
      console.log(res);
      this.jixidata=res;
    }, (error) => {
      this.apiSev.itip('暂无数据');
    }, 500, inquiry)
  }

  topBanner(){
    let monthall ={};
    this.apiSev.api("post", "app/amounts/", (res) => {
      console.log(res);
      if (res.response == 'success'){
        this.discount = res;
      }
    }, (error) => {
      this.apiSev.itip('暂无数据');
    }, 500, monthall)
  }


  changeAcceptTime(){
    let timeEnd= new Date(this.inquiry.begintime).getTime()+180*24*3600*1000;
    this.inquiry.endtime = new Date(timeEnd).toISOString();
  }

  inquirydata(e) {
    let inquirydata:any = {
      type6: this.inquiry.pjtype,
      type1: this.inquiry.chengduihang,
      type3: 1,
      allmoney:this.inquiry.allprice, //总金额
      memberId:this.memberId,
      belong: 0,
      dates:this.jixidata.jxts, //计息天数
      cityName:'全国'   //交易城市
    };
    //判断选择电票纸票的时候传的值
    if (this.inquiry.pjtype==2){
      if(this.inquiry.allprice==null ||this.inquiry.allprice==''){
        this.apiSev.itip("请输入单张票据金额");
        return;
      }
      //type5类型值
      if (Number(this.jixidata.jxts) <= 90) {
        inquirydata.type5 = 1;
      } else if (Number(this.jixidata.jxts) <= 178) {
        inquirydata.type5 = 2;
      } else {
        inquirydata.type5 = 3;
      }
      //城市ID
      inquirydata.cityId=1;
      //承兑类型
      inquirydata.limit=this.inquiry.limit;
    }else if (this.inquiry.pjtype==1){
      //type5类型值
      inquirydata.type5 = 2;
      //只能小于等于300万
      if (Number(this.inquiry.allprice)>300){
        this.apiSev.itip("纸票票面金额不能高于300万");
        return;
      }

      if(this.inquiry.allprice==null ||this.inquiry.allprice==''){
        this.apiSev.itip("请输入单张票据金额");
        return;
      }
    }


    this.apiSev.api("post", "app/xunjia2/", (res) => {
      console.log(res);
      if (res.response == 'success') {
        this.result = res.data;
        this.price3 = res.txlx;
        if(this.inquiry.pjtype == 2){
          this.inquiry.nianlilv = true;
          this.inquiry.discount = false;
        }else if(this.inquiry.pjtype == 1){
          this.inquiry.nianlilv = false;
          this.inquiry.discount = true;
        }
      }else{
        this.apiSev.itip('暂无数据，请尝试更改条件');
        return;
      }
    }, (error) => {
      this.apiSev.itip('暂无数据');}, 500, inquirydata);
    e.preventDefault();
  }

  //查看票据指数
  chart(e){
    this.navCtrl.push(ChartPage);
    e.preventDefault();
  }

  //银票贴现传页面
  discountYPPage (e) {
    let type1 = this.inquiry.pjtype;       //票据类型
    let type2 = this.inquiry.chengduihang; //票据承兑行类型
    let allmoney = this.inquiry.allprice;  //金额
    this.navCtrl.push(DiscountYPPage, {
      TEMP_TYPE1: type1,
      TEMP_TYPE2: type2,
      TEMP_ALLMONEY:allmoney
    })
    e.preventDefault();
  }

}
