import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-orderShoukuanB',
  templateUrl: 'orderShoukuanB.html'
})
export class OrderShoukuanPageB {
  public memberId;
  public mobile;
  public corp_no;
  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      let data1 = {
        memberId:this.memberId,
        type:0,
      };
      this.apiSev.api("newpost", "cib/corp/query", (res) => {
        this.mobile=res.data.data.cib.contactMobile;
        this.corp_no = res.data.data.corp.corp_no;
      }, (error) => {},1000,data1);
    });
  }

  close(){
	  this.viewCtrl.dismiss();
  }




}
