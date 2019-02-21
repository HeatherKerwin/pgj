import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../../../api.service";
import { ManageeditPageB } from "../../../manage/org/books/edit";

@Component({
  selector: 'page-month',
  templateUrl: 'month.html'
})
export class MonthPageB {

  //切换
  segmentsArray = ['monthdiscount','monthundiscount'];
  pet: string = this.segmentsArray[0];

  public monthA={
    monthDate:new Date().toISOString(),
  };
  public monthB={
    monthDate:new Date().toISOString(),
  };

  public valueAs = [];
  public valueBs = [];
  public orgId='';

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService,
    public viewCtrl: ViewController
  ){
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
      this.loaddata(new Date(),1);
      this.loaddata(new Date(),0);
    });
  }

  leftmonthA() {
    let monthshowA = new Date(this.monthA.monthDate);
    monthshowA.setMonth(monthshowA.getMonth()-1);
    this.monthA.monthDate = monthshowA.toISOString();
    this.loaddata(new Date(this.monthA.monthDate),1);
  }
  rightmonthA() {
    let monthshowA = new Date(this.monthA.monthDate);
    monthshowA.setMonth(monthshowA.getMonth()+1);
    this.monthA.monthDate = monthshowA.toISOString();
    this.loaddata(new Date(this.monthA.monthDate),1);
  }
  leftmonthB() {
    let monthshowB = new Date(this.monthB.monthDate);
    monthshowB.setMonth(monthshowB.getMonth()-1);
    this.monthB.monthDate = monthshowB.toISOString();
    this.loaddata(new Date(this.monthB.monthDate),0);
  }
  rightmonthB() {
    let monthshowB = new Date(this.monthB.monthDate);
    monthshowB.setMonth(monthshowB.getMonth()+1);
    this.monthB.monthDate = monthshowB.toISOString();
    this.loaddata(new Date(this.monthB.monthDate),0);
  }

  //查询数据
  loaddata(month,type){
    let monthdate = month.getFullYear()+"-"+(month.getMonth()+1);
    this.storage.get('ORGID').then((data)=>{
      this.apiSev.api("get", "zhangbu/piaojulist/?memberId="+data+"&size=3&belong=1&day="+monthdate+"&istiexian="+type, (res) => {
        if (type==1){
          this.valueAs.length=0;
          this.valueAs=res;
        }
        if (type==0){
          this.valueBs.length=0;
          this.valueBs=res;
        }
        if (res==''){
          this.apiSev.itip('本月没有数据！');
        }
      }, (error) => {},1,{});
    })
  }

  //打开票据编辑
  openEdit(tiexianId){
    this.navCtrl.push(ManageeditPageB,{
      tiexianId : tiexianId
    });
  }

  //左右滑动切换列表
  swipeEvent(event){
    //向左滑
    if(event.direction==2){
      if(this.segmentsArray.indexOf(this.pet)<3){
        this.pet = this.segmentsArray[this.segmentsArray.indexOf(this.pet)+1];
      }
    }
    //向右滑
    if(event.direction==4){
      if(this.segmentsArray.indexOf(this.pet)>0){
        this.pet = this.segmentsArray[this.segmentsArray.indexOf(this.pet)-1];
      }
    }
  }

}
