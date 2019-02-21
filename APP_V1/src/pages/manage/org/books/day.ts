import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../../../api.service";
import { ManageeditPageB } from "../../../manage/org/books/edit";

@Component({
  selector: 'page-day',
  templateUrl: 'day.html'
})
export class DayPageB {

  //切换
  segmentsArray = ['daydiscount','dayundiscount'];
  pet: string = this.segmentsArray[0];

  public dayA={
    dayDate:new Date().toISOString(),
  };
  public dayB={
    dayDate:new Date().toISOString(),
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
      this.loadData(new Date(),1);
      this.loadData(new Date(),0);
    });
  }

  leftdayA() {
    let dayshowA = new Date(this.dayA.dayDate).getTime() - 24 * 60 * 60 * 1000;
    this.dayA.dayDate = new Date(dayshowA).toISOString();
    this.loadData(new Date(this.dayA.dayDate),1);
  }
  rightdayA() {
    let dayshowA = new Date(this.dayA.dayDate).getTime() + 24 * 60 * 60 * 1000;
    this.dayA.dayDate = new Date(dayshowA).toISOString();
    this.loadData(new Date(this.dayA.dayDate),1);
  }
  leftdayB() {
    let dayshowB = new Date(this.dayB.dayDate).getTime() - 24 * 60 * 60 * 1000;
    this.dayB.dayDate = new Date(dayshowB).toISOString();
    this.loadData(new Date(this.dayB.dayDate),0);
  }
  rightdayB() {
    let dayshowB = new Date(this.dayB.dayDate).getTime() + 24 * 60 * 60 * 1000;
    this.dayB.dayDate = new Date(dayshowB).toISOString();
    this.loadData(new Date(this.dayB.dayDate),0);
  }

  //查数据
  loadData(obj,type){
    let obja = obj.getFullYear()+"-"+(obj.getMonth()+1)+"-"+obj.getDate();
    this.storage.get('ORGID').then((data)=>{
      this.apiSev.api("get", "/zhangbu/piaojulist/?memberId="+data+"&size=1&belong=1&day="+obja+"&istiexian="+type, (res) => {
        if (type==1){
          this.valueAs.length=0;
          this.valueAs=res;
        }
        if (type==0){
          this.valueBs.length=0;
          this.valueBs=res;
        }
        if (res==''){
          this.apiSev.itip('今日没有数据！');
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
