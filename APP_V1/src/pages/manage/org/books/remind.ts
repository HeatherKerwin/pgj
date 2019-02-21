import { Component } from '@angular/core';
import { NavController, ViewController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../../../api.service";
import { ReminddetailsPageB } from "../../../../pages/manage/org/books/remind-details";

@Component({
  selector: 'page-remind',
  templateUrl: 'remind.html'
})
export class RemindPageB {

  //查询数据
  public values = [];
  public value:any={};
  public memberId='';

  public pageIndex='1';
  public remindList=false;
  public remindNo=false;
  public isOk=false;
  public orgId='';

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public apiSev: apiService,
    public viewCtrl: ViewController
  ) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    });
    this.storage.get('ORGID').then((data)=>{
      this.orgId=data;
      this.loadData(1);
    });

  }

  loadData(pageIndex){
    let data:any={
      memberid:this.orgId,
      belong:'1'
    }
    this.apiSev.api("post", "zhangbu/piaojuremindlist", (res) => {
        this.remindList=true;
        this.remindNo=false;
        if(res[0].allprice == null){
          this.remindList=false;
          this.remindNo=true;
        }else {
          let objs = res;
          this.isOk = false;
          if (objs.length > 0) {
            this.remindList = true;
            this.remindNo = false;
            if (objs.length == 10) {//说明可能还有数据
              this.isOk = true;
            }
            for (let i = 0; i < objs.length; i++) {
              this.values.push(objs[i]);
            }
          }
        }
    },(error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 500,data);
  }
  //下滑加载
  doInfinite(infiniteScroll) {
    setTimeout(() => {

      if(this.isOk){
        this.pageIndex=this.pageIndex+1;
        this.loadData(this.pageIndex);
        this.isOk = false;
      }else{
        infiniteScroll.enable(false);
      }
      infiniteScroll.complete();
    }, 500);
  }

  Selected(id){
    this.navCtrl.push(ReminddetailsPageB,{
      accId:id
    });
  }
}
