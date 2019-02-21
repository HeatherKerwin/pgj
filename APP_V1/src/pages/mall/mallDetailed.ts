import { Component } from '@angular/core';
import { NavController, ViewController , NavParams} from 'ionic-angular';
import { Storage } from '@ionic/storage';
import { ToastController } from 'ionic-angular';
import { apiService } from "../../api.service";

@Component({
  selector: 'page-mallDetailed',
  templateUrl: 'mallDetailed.html'
})
export class mallDetailedPage {
  public memberId:any;
  public pageIndex:any = 1;
  public isOk:boolean; //是否还有数据

  public integrals=[];
  public integral:any={};

  constructor(
    public toastCtrl: ToastController,
    public storage: Storage,
    public navCtrl: NavController,
    public viewCtrl: ViewController,
    public apiSev: apiService,
    public params: NavParams
  ) {
    this.storage.get('userInfo').then((data)=>{//memberId
      this.memberId=data.id;
      this.loadData(1);
    })
  }

  loadData(pageIndex) {
    let data:any={
      memberId:this.memberId,
      pageIndex:pageIndex
    }
    this.apiSev.api("post", "app/integraltradingdetail/getlist", (res) => {
      if (res.response == 'success') {
        let objs=res.data;
        this.isOk=false;
        if(objs.length>0){
          if(objs.length==10){//说明可能还有数据
            this.isOk = true;
          }
          for (let i=0;i<objs.length;i++){
            this.integrals.push(objs[i]);
            if(this.integrals[i].state == 1){//1加积分，0减积分
              this.integrals[i].add ="+";
            }else{
              this.integrals[i].add = "-";
            }
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

}
