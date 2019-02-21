import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../../../api.service";
import { ManageeditPage } from "../../../manage/bns/books/edit";

@Component({
  selector: 'page-year',
  templateUrl: 'year.html'
})
export class YearPage {

  //切换
  segmentsArray = ['yeardiscount','yearundiscount'];
  pet: string = this.segmentsArray[0];

  public yearA={
    yearDate:new Date().toISOString(),
  };
  public yearB={
    yearDate:new Date().toISOString(),
  };

  public valueAs = [];
  public valueBs = [];

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService,
    public viewCtrl: ViewController
  ){
    this.loaddata(this.yearA.yearDate,1);
    this.loaddata(this.yearB.yearDate,0);
  }

  leftyearA() {
    let yearshowA = new Date(this.yearA.yearDate);
    yearshowA.setFullYear(yearshowA.getFullYear()-1);
    this.yearA.yearDate = yearshowA.toISOString();
    this.loaddata(new Date(this.yearA.yearDate),1);
  }
  rightyearA() {
    let yearshowA = new Date(this.yearA.yearDate);
    yearshowA.setFullYear(yearshowA.getFullYear()+1);
    this.yearA.yearDate = yearshowA.toISOString();
    this.loaddata(new Date(this.yearA.yearDate),1);
  }
  leftyearB() {
    let yearshowB = new Date(this.yearB.yearDate);
    yearshowB.setFullYear(yearshowB.getFullYear()-1);
    this.yearB.yearDate = yearshowB.toISOString();
    this.loaddata(new Date(this.yearB.yearDate),0);
  }
  rightyearB() {
    let yearshowB = new Date(this.yearB.yearDate);
    yearshowB.setFullYear(yearshowB.getFullYear()+1);
    this.yearB.yearDate = yearshowB.toISOString();
    this.loaddata(new Date(this.yearB.yearDate),0);
  }

  loaddata(year,type){
    year = new Date(year).getFullYear();
    this.storage.get('userInfo').then((data)=>{
      this.apiSev.api("get", "zhangbu/piaojulist/?memberId="+data.id+"&size=4&belong=0&day="+year+"&istiexian="+type, (res) => {
        if (type==1){
          this.valueAs.length=0;
          this.valueAs=res;
        }
        if (type==0){
          this.valueBs.length=0;
          this.valueBs=res;
        }
        if (res==''){
          this.apiSev.itip('本年没有数据！');
        }
      }, (error) => {},1,{});
    })
  }

  //打开票据编辑
  openEdit(tiexianId){
    this.navCtrl.push(ManageeditPage,{
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
