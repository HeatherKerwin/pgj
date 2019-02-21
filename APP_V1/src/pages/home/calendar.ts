import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { DomSanitizer } from "@angular/platform-browser";
import { Storage } from '@ionic/storage';
import { apiService } from "../../api.service";

@Component({
  selector: 'page-calendar',
  templateUrl: 'calendar.html'
})
export class Calendar {
  //切换
  segmentsArray = ['manage1','manage2','manage3',];
  segmentModel: string = this.segmentsArray[0];


  //日历传参
  public idx=1;

  //图片路径
  public Calendar='';

  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public sanitizer: DomSanitizer, public apiSev: apiService,) {
    this.cbChaXunrili();
  }


  ionViewDidEnter(){
  }

  close (e) {
  	this.navCtrl.pop();
    e.preventDefault();
  }



  cbChaXunrili(){
    let data:any={
      code:'index_rili',
      sort:this.idx,
    };
    this.apiSev.api("post", "app/bannernew/get", (res) => {
      this.Calendar = res[0].path
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);

  }

  //头部右侧图标按钮
  btnIcon(){
    if(this.segmentsArray.indexOf(this.segmentModel)==0){
      this.idx = 1;
      this.cbChaXunrili();
    }
    if(this.segmentsArray.indexOf(this.segmentModel)==1) {
      this.idx = 2;
      this.cbChaXunrili();
    }
    if(this.segmentsArray.indexOf(this.segmentModel)==2) {
      this.idx = 3;
      this.cbChaXunrili();
    }
  }


  //左右滑动切换列表
  swipeEvent(event){
    //向左滑
    if(event.direction==1){
      if(this.segmentsArray.indexOf(this.segmentModel)<2){
        this.segmentModel = this.segmentsArray[this.segmentsArray.indexOf(this.segmentModel)+1];
      }
    }
    //向右滑
    if(event.direction==3){
      if(this.segmentsArray.indexOf(this.segmentModel)>0){
        this.segmentModel = this.segmentsArray[this.segmentsArray.indexOf(this.segmentModel)-1];
      }
    }
  }

}
