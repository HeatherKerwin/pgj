import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-redList',
  templateUrl: 'redList.html'
})


export class RedListPage {

  public memberId='';
  public myred:any=[];
  public isOk:boolean=false;
  public pageNo = 1;
  public RedType:any='';


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public params: NavParams, public apiSev: apiService) {
    let RedType = this.params.get("RedType"); //角色类型
    this.RedType = RedType;
  }

  ionViewDidEnter(){
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      this.loadData(this.pageNo);
    });
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
            if(objs.length==10){//说明可能还有数据
              this.isOk = true;
            }
            for (let i=0;i<objs.length;i++){
              this.myred.push(objs[i]);
            }
          }
        }
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
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

  //点击红包
  Payments(id,money,couponType){
    if (this.RedType == 0) {
      if (couponType == 'GENERAL' || couponType == 'DISC') {
        this.apiSev.parmData = {
          RedID: id,
          RedMoney: money
        };
        this.navCtrl.pop();
      } else {
        this.apiSev.itip("您只能选择交易红包和通用红包");
        return;
      }
    }

    if (this.RedType == 1) {
      if (couponType == 'GENERAL' || couponType == 'DIST') {
        this.apiSev.parmData = {
          RedID: id,
          RedMoney: money
        };
        this.navCtrl.pop();
      } else {
        this.apiSev.itip("您只能选择接单红包和通用红包");
        return;
      }
    }
  }
}
