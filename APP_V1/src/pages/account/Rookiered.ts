import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';
import { MyredgadPage } from './myredgad';

@Component({
  selector: 'page-Rookiered',
  templateUrl: 'Rookiered.html'
})


export class RookieredPage {

  public memberId='';
  public orgType:any;


  constructor(public storage: Storage, public navCtrl: NavController, public viewCtrl: ViewController, public apiSev: apiService,public params: NavParams) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id
    });
    let orgType = this.params.get("ORG_TYPE"); //角色类型
    this.orgType = orgType;
  }

  redNew(){
    let data: any = {
      memberId: this.memberId,
    };
    this.apiSev.api("newpost", "coupon/register/get", (res) => {
      let data = res.data;
      if (data.response == 'success') {
        this.apiSev.itip("恭喜您获得"+data.data+"个红包");
        this.navCtrl.push(MyredgadPage,{
          ORG_TYPE:this.orgType,  //企业 出票方
          MY_RED:1
        });
      } else {
        this.apiSev.itip(data.msg);
      }
    }, (error) => {
      this.apiSev.itip('操作失败！');
      return;
    }, 6100, data);
  }
}
