import {Component} from '@angular/core';
import {NavController, ViewController,NavParams} from 'ionic-angular';
import {apiService} from "../../api.service";
import {Storage} from '@ionic/storage';
import {OpenaccountSuccessPage} from "../openaccount/openaccountSuccess";
import {jdbanklistPage} from "./jdbanklist";


@Component({
  selector: 'page-jdbankbdB',
  templateUrl: 'jdbankbdB.html'
})


export class jdbankbdBPage {

  public memberId = '';
  public orgType:any;
  public meType:any;

  //按钮判断
  public Tonosuccess = true;
  public Tosuccess = false;

  //京东数组
  public jdData:any={};
  public source:any;


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data)=>{
      this.memberId=data.id;
    });
    //京东绑卡信息
    this.jdData = this.params.get("BANK");
    this.source=this.params.get("SOURCE");

  }


  switching(){
    if(this.jdData.allprice > 0){
      this.Tonosuccess = false;
      this.Tosuccess = true;
    }else {
      this.Tonosuccess = true;
      this.Tosuccess = false;
    }
  }

  //取京东信息
  realnamepaycheckmoney(){
    let data:any={
      memberId:this.memberId,
      payOrderId:this.jdData.payOrderId,
      amount:this.apiSev.toFix((this.jdData.allprice*100),0)
    };
    this.apiSev.api("newpost", "jdjrcard/verifyAgencyDefpay", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        if(data.data.bindFlag==1){
          if(this.source==1){
            this.navCtrl.pop();
            this.navCtrl.pop();
          }else {
            this.navCtrl.pop();
          }
          this.apiSev.itip("绑卡成功");
        }else if(data.data.bindFlag==1000){
          this.apiSev.itip("此卡已存在、请忽重复绑卡");
        } else {
          this.apiSev.itip("金额有误，您还可以输入"+data.data.remainConfirmFailNum+"次");
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
