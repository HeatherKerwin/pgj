import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-myredgad',
  templateUrl: 'myredgad.html'
})


export class MyredgadPage {
  public memberId='';
  public guideA:boolean=true; //有效红包
  public guideB:boolean; //失效红包
  public guideC:boolean;
  public guideD:boolean;
  public guideE:boolean;
  public st= 's1';
  public isOk:boolean=false;
  public pageNo = 1;
  public myred =[];
  public myredB=[];
  public totalred :any= '';



  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService) {
  }

  ionViewDidEnter(){
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.loadData(this.pageNo);
    });
  }

  Changed(){
    if (this.st=='s1') {
      this.guideA = true;
      this.guideB = false;
      this.loadData(this.pageNo);
    }
    if(this.st=='s2') {
      this.guideA = false;
      this.guideB = true;
      this.loadDataB(this.pageNo);
    }
  }

  //加载账户信息
  loadData(pagenumber){
    this.myred=[];
    let data:any={
      memberId:this.memberId,
      pageIndex:pagenumber,
      couponState:'UNUSED'
    };
    this.apiSev.api("newpost", "coupon/page", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.list!=''&&data.data.list!=null){
          let objs = data.data.list;
          if(objs.length>0){
            this.isOk = false;
            this.guideC = false;
            this.totalred = objs.length;
            if(objs.length==10){//说明可能还有数据
              this.isOk = true;
            }
            for (let i=0;i<objs.length;i++){
              this.myred.push(objs[i]);
            }
          }
        }else {
          this.totalred = 0;
          this.guideC = true;
          this.guideD = true;
          this.guideE = false;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //加载账户信息
  loadDataB(pagenumber){
    this.myredB=[];
    let data:any={
      memberId:this.memberId,
      pageIndex:pagenumber,
      couponState:'INVALID'
    };
    this.apiSev.api("newpost", "coupon/page", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.list!=''&&data.data.list!=null){
          let objs = data.data.list;
          if(objs.length>0){
            this.isOk = false;
            this.guideC = false;
            if(objs.length==10){//说明可能还有数据
              this.isOk = true;
            }
            for (let i=0;i<objs.length;i++){
              this.myredB.push(objs[i]);
            }
          }
        }else {
          this.guideC = true;
          this.guideE = true;
          this.guideD = false;
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500, data);
  }

  //下滑加载
  doInfinite(infiniteScroll) {
    if(this.isOk){
      setTimeout(() => {
        this.pageNo=this.pageNo+1;
        this.loadData(this.pageNo);
        infiniteScroll.complete();
      }, 6100);
    }else{
      infiniteScroll.enable(false);
      infiniteScroll.complete();
    }
  }

}
