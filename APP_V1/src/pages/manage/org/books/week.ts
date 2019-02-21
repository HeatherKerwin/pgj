import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../../../api.service";
import { ManageeditPageB } from "../../../manage/org/books/edit";

@Component({
  selector: 'page-week',
  templateUrl: 'week.html'
})
export class WeekPageB {

  //切换
  segmentsArray = ['weekdiscount','weekundiscount'];
  pet: string = this.segmentsArray[0];

  public weekA={
    weekDate:this.getWeekDate(new Date()),
  };
  public weekB={
    weekDate:this.getWeekDate(new Date()),
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
      this.loadMethodA();
      this.loadMethodB();
    });
  }

  // 周
  getWeekDate(now){
    //当前日期
    let nowDayOfWeek = now.getDay();         //今天本周的第几天
    if(nowDayOfWeek == 0){
      nowDayOfWeek = 6;
    }else{
      nowDayOfWeek -= 1;
    }
    let nowDay = now.getDate();              //当前日
    let nowMonth = now.getMonth();           //当前月
    let nowYear = now.getFullYear();             //当前年
    nowYear += (nowYear < 2000) ? 1900 : 0;  //
    let date1 = new Date(nowYear,nowMonth,nowDay - nowDayOfWeek );
    let date2 = new Date(nowYear,nowMonth,date1.getDate()+6);
    return date1.getFullYear()+ "-" + (Number(date1.getMonth()) + 1) + "-" + date1.getDate() + "~" + (Number(date2.getMonth()) + 1) + "-" + date2.getDate();
  }
  loadMethodA(){
    let begin = this.weekA.weekDate.trim().split('~')[0];
    this.loaddata(begin,1);
  }
  loadMethodB(){
    let begin = this.weekB.weekDate.trim().split('~')[0];
    this.loaddata(begin,0);
  }
  leftweekA(){
    let timeArray = this.weekA.weekDate.split("~");
    let beginTimeArray = timeArray[0].split("-");
    let beginDate = new Date(Number(beginTimeArray[0]),(Number(beginTimeArray[1]) - 1),Number(beginTimeArray[2]) - 6);
    this.weekA.weekDate=this.getWeekDate(beginDate);
    timeArray = this.weekA.weekDate.split("~");
    beginTimeArray = timeArray[0].split("-");
    let begin = beginTimeArray[0] + "-" + beginTimeArray[1] + "-" + beginTimeArray[2];
    this.loaddata(begin,1);
  }
  rightweekA(){
    let timeArray = this.weekA.weekDate.split("~");
    let beginTimeArray = timeArray[0].split("-");
    let beginDate = new Date(Number(beginTimeArray[0]),(Number(beginTimeArray[1]) - 1),Number(beginTimeArray[2]) + 13);
    this.weekA.weekDate=this.getWeekDate(beginDate);
    timeArray = this.weekA.weekDate.split("~");
    beginTimeArray = timeArray[0].split("-");
    let begin = beginTimeArray[0] + "-" + beginTimeArray[1] + "-" + beginTimeArray[2];
    this.loaddata(begin,1);
  }
  leftweekB(){
    let timeArray = this.weekB.weekDate.split("~");
    let beginTimeArray = timeArray[0].split("-");
    let beginDate = new Date(Number(beginTimeArray[0]),(Number(beginTimeArray[1]) - 1),Number(beginTimeArray[2]) - 6);
    this.weekB.weekDate=this.getWeekDate(beginDate);
    timeArray = this.weekB.weekDate.split("~");
    beginTimeArray = timeArray[0].split("-");
    let begin = beginTimeArray[0] + "-" + beginTimeArray[1] + "-" + beginTimeArray[2];
    this.loaddata(begin,0);
  }
  rightweekB(){
    let timeArray = this.weekB.weekDate.split("~");
    let beginTimeArray = timeArray[0].split("-");
    let beginDate = new Date(Number(beginTimeArray[0]),(Number(beginTimeArray[1]) - 1),Number(beginTimeArray[2]) + 13);
    this.weekB.weekDate=this.getWeekDate(beginDate);
    timeArray = this.weekB.weekDate.split("~");
    beginTimeArray = timeArray[0].split("-");
    let begin = beginTimeArray[0] + "-" + beginTimeArray[1] + "-" + beginTimeArray[2];
    this.loaddata(begin,0);
  }

  //查询数据
  loaddata(begin,type) {
    this.storage.get('get').then((data)=>{
      this.apiSev.api("get", "zhangbu/piaojulist/?memberId="+data+"&size=2&belong=1&day="+begin+"&istiexian="+type, (res) => {
        if (type==1){
          this.valueAs.length=0;
          this.valueAs=res;
        }
        if (type==0){
          this.valueBs.length=0;
          this.valueBs=res;
        }
        if (res==''){
          this.apiSev.itip('本周没有数据！');
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
