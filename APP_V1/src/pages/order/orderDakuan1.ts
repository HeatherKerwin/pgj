import { Component } from '@angular/core';
import { NavController, ViewController,NavParams } from 'ionic-angular';
import { apiService } from "../../api.service";
import { Storage } from '@ionic/storage';

@Component({
  selector: 'page-orderDakuan1',
  templateUrl: 'orderDakuan1.html'
})
export class OrderDakuan1Page {
  public memberId;
  public mobile;
  public corp_no;
  constructor(public storage: Storage,public navCtrl: NavController, public viewCtrl: ViewController,public params: NavParams,public apiSev: apiService) {
    this.storage.get('userInfo').then((data) => {
      this.memberId = data.id;
      let data1 = {
        memberId:this.memberId,
        type:1,
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

  //承兑方   打款   1
  //出票方   提现   0
}
