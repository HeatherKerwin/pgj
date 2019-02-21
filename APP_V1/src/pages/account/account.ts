import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-account',
  templateUrl: 'account.html'
})
export class AccountPage {
  segmentsArray = ['account1','account2','account3'];
  segmentModel: string = this.segmentsArray[0];

  public memberId:'';
  public orgId:'';

  public account1s = [];
  public account1:any = {};
  public account2s = [];
  public account2:any = {};
  public account3s = [];
  public account3:any = {};

  constructor(
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService
  ) {

    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
      this.loadeaccount1();
      this.loadeaccount2();
      this.loadeaccount3();
    })
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
    })

  }

  loadeaccount1(){
    let data:any={
      memberId:this.memberId,
      type:'CZ',
    }
    this.apiSev.api("newpost", "accountlog/page", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.account1s=objs.data.list;
      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);

  }

  loadeaccount2(){
    let data:any={
      memberId:this.memberId,
      type:'TX',
    }
    this.apiSev.api("newpost", "accountlog/page", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.account2s=objs.data.list;
      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);

  }

  loadeaccount3(){
    let data:any={
      memberId:this.memberId,
      type:'USE',
    }
    this.apiSev.api("newpost", "accountlog/page", (res) => {
      let objs = res.data;
      if (objs.response == 'success') {
        this.account3s=objs.data.list;
      } else {
        this.apiSev.itip(objs.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);

  }

  //左右滑动切换列表
  swipeEvent(event){
    //向左滑
    if(event.direction==2){
      if(this.segmentsArray.indexOf(this.segmentModel)<2){
        this.segmentModel = this.segmentsArray[this.segmentsArray.indexOf(this.segmentModel)+1];
      }
    }
    //向右滑
    if(event.direction==4){
      if(this.segmentsArray.indexOf(this.segmentModel)>0){
        this.segmentModel = this.segmentsArray[this.segmentsArray.indexOf(this.segmentModel)-1];
      }
    }
  }

}
