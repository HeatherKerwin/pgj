import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
@Component({
  selector: 'page-meMessage',
  templateUrl: 'meMessage.html'
})
export class MeMessagePage {
  public isOk1:boolean; //是否还有数据
  public isOk2:boolean; //是否还有数据
  public pageNo1 = 1;
  public pageNo2 = 1;
  public discountsMessageList = [];
  public sysMessageList = [];
  public memberId;
  constructor(public storage: Storage,public navCtrl: NavController,public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.Read();
      this.initData1(this.pageNo1);
      this.NewSystem(this.pageNo2);
    });
  }

  //切换
  segmentsArray = ['sysMessage','discountsMessage'];
  pet: string = this.segmentsArray[0];

  initData1(pagenumber){
    this.isOk1 = false;
    this.apiSev.api(
      "get",
      "app/message/preferentialInfo?pageIndex="+pagenumber+"&memberId=1&belong=0",
      (res) => {
        if(res.state == 'success'){
          let objs = res.data;
          if(objs.length>0){
            if(objs.length==10){//说明可能还有数据
              this.isOk1 = true;
            }
            for (let i=0;i<objs.length;i++){
              this.discountsMessageList.push(objs[i]);
            }
          }
        }
      }, (error) => {},1000,{});
  }
  doInfinite1(infiniteScroll) {
    if(this.isOk1){
      setTimeout(() => {
        this.pageNo1=this.pageNo1+1;
        this.initData1(this.pageNo1);
        infiniteScroll.complete();
      }, 500);
    }else{
      infiniteScroll.enable(false);
      infiniteScroll.complete();
    }
  }
/*  initData2(pagenumber){
    this.isOk2 = false;
    this.apiSev.api(
      "get",
      "app/message/systemnews?pageIndex="+pagenumber+"&memberId=1&belong=0",
      (res) => {
        if(res.response == 'success'){
          let objs = res.data;
          if(objs.length>0){
            if(objs.length==10){//说明可能还有数据
              this.isOk2 = true;
            }
            for (let i=0;i<objs.length;i++){
              this.sysMessageList.push(objs[i]);
            }
          }
        }
      }, (error) => {},1000,{});
  }*/
  doInfinite2(infiniteScroll) {
    if(this.isOk2){
      setTimeout(() => {
        this.pageNo2=this.pageNo2+1;
        this.NewSystem(this.pageNo2);
        infiniteScroll.complete();
      }, 500);
    }else{
      infiniteScroll.enable(false);
      infiniteScroll.complete();
    }
  }
  //左右滑动切换列表
  swipeEvent(event){
    //向左滑
    if(event.direction==2){
      if(this.segmentsArray.indexOf(this.pet)==0){
        this.pet = this.segmentsArray[1];
      }
    }
    //向右滑
    if(event.direction==4){
      if(this.segmentsArray.indexOf(this.pet)==1){
        this.pet = this.segmentsArray[0];
      }
    }
  }

  //是否有新消息
  Read(){
    let data ={
      memberId:this.memberId
    };
    this.apiSev.api("newpost", "systeminfo/update/read", (res) => {
    },(error) => {
    }, 500,data);
  }

  //NEW系统消息
  NewSystem(pagenumber){
    this.isOk2 = false;
    let data:any={
      memberId:this.memberId,
      types:"DISCOUNTRECORD,DISTRIBUTEORDER,DISCOUNTRECORDSP,DISTRIBUTEORDERSP,DISCOUNTRECORDPL,DISTRIBUTEORDERPL,DISPATCH,SYSTEM",
      readState:'',
      pageIndex:pagenumber
    };
    this.apiSev.api("newpost", "systeminfo/page", (res) => {
      let data = res.data;
      if (data.response == 'success') {
          let objs = data.data.list;
          if(objs.length>0){
            if(objs.length==10){//说明可能还有数据
              this.isOk2 = true;
            }
            for (let i=0;i<objs.length;i++){
              this.sysMessageList.push(objs[i]);
            }
          }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }
}

